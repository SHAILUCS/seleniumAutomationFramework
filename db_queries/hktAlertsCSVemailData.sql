


          WITH
          --client parent meid and meid
          client_meid AS
           (SELECT o.PARENT_MASTER_ENTITY_ID
                  ,o.MASTER_ENTITY_ID
            FROM   prongc.operator o
            WHERE  o.MASTER_ENTITY_ID = 2264 --c_rec.main_master_entity_id
            AND    o.operator_type_id = prongc.pkg_ngc_constants.operator_type_id_gsm),
          
          --all client meids for multi pmn clinet
          client_meid_all AS
           (SELECT o.MASTER_ENTITY_ID
            FROM   client_meid
            JOIN   prongc.operator o ON (o.PARENT_MASTER_ENTITY_ID = client_meid.PARENT_MASTER_ENTITY_ID OR o.MASTER_ENTITY_ID = client_meid.MASTER_ENTITY_ID)
                          AND    o.operator_type_id = prongc.pkg_ngc_constants.operator_type_id_gsm),
          
          --parameters from configuration
          params AS
           (SELECT FX_FLUCTUATION_THRESHOLD
                  ,FX_COMPARISON_MONTHS_NUMBER
                  ,(SELECT HOME_CURRENCY
                    FROM   prongc.iot_client ic
                    WHERE  ic.master_entity_id = iasc.main_master_entity_id) HOME_CURRENCY
            FROM   prongc.IOT_ALERT_SUBS_CFG iasc
            WHERE  1 = 1
                  -- /*  
                  -- alert subscription is defined for main pmn
            AND    iasc.main_master_entity_id = (SELECT nvl(PARENT_MASTER_ENTITY_ID, MASTER_ENTITY_ID)
                                                 FROM   client_meid)
                  --*/
            AND    iasc.ALERT_SUBS_TYPE_ID = 4
            AND    iasc.ALERT_ENABLED = 1),
          
          --active agreements for all client meids for multi pmn clinet + all agreement level currencies
          active_iag AS
           (SELECT DISTINCT iaga.iot_agreement_id
                           ,iaga.rec_payment_curr
                           ,iaga.disc_agrmnt_curr
                           ,iaga.pay_payment_curr
            FROM   client_meid_all cma
            JOIN   prongc.iot_agreement_party iap ON iap.master_entity_id = cma.MASTER_ENTITY_ID
                                       AND    iap.client_side = 1
            JOIN   prongc.iot_agreement_all iaga ON iap.iot_agreement_id = iaga.iot_agreement_id
            WHERE  trunc(SYSDATE) BETWEEN iaga.agreement_start_date AND iaga.agreement_end_date
            AND    iaga.termination_date IS NULL),
          
          -- list of all currencies for alert
          all_currencies AS
           (SELECT ir.REBATE_CURRENCY curr
            FROM   active_iag
            JOIN   prongc.iot_rebate ir ON active_iag.iot_agreement_id = ir.iot_agreement_id
            UNION
            SELECT icb.ADJUSTED_REC_CURRENCY
            FROM   active_iag
            JOIN   prongc.IOT_CURRENCY_BREAKDOWN icb ON active_iag.iot_agreement_id = icb.iot_agreement_id
            UNION
            SELECT icb.ADJUSTED_PAY_CURRENCY
            FROM   active_iag
            JOIN   prongc.IOT_CURRENCY_BREAKDOWN icb ON active_iag.iot_agreement_id = icb.iot_agreement_id
            UNION
            SELECT rec_payment_curr
            FROM   active_iag
            UNION
            SELECT disc_agrmnt_curr
            FROM   active_iag
            UNION
            SELECT pay_payment_curr
            FROM   active_iag),
          
          DAT AS
           (SELECT tp.period_start_date
                  ,cr_from.PEGGED_RATE PEGGED_RATE_FROM
                  ,cr_from.TO_CURR CURR_FROM
                  ,cr_to.PEGGED_RATE PEGGED_RATE_TO
                  ,cr_to.TO_CURR CURR_TO
                  ,cr_from.PEGGED_RATE / nullif(cr_to.PEGGED_RATE, 0) from_to_ratio
                  ,LAST_VALUE(cr_from.PEGGED_RATE / nullif(cr_to.PEGGED_RATE, 0)) OVER(ORDER BY cr_from.TO_CURR, cr_to.TO_CURR) LAST_RATIO
            FROM   prongc.conversion_rate cr_from
            JOIN   prongc.TRAFFIC_PERIOD TP ON TP.TRAFFIC_PERIOD_ID = CR_FROM.TRAFFIC_PERIOD_ID
            LEFT   JOIN prongc.conversion_rate cr_TO ON cr_TO.TRAFFIC_PERIOD_ID = CR_FROM.TRAFFIC_PERIOD_ID
            WHERE  cr_from.to_curr = (SELECT HOME_CURRENCY
                                      FROM   params)
            AND    cr_from.FROM_CURR = 'SDR'
            AND    tp.period_start_date BETWEEN ADD_MONTHS(TRUNC(SYSDATE, 'MM'),
                                                           - (SELECT FX_COMPARISON_MONTHS_NUMBER +1
                                                             FROM   params)) AND TRUNC(SYSDATE, 'MM')
            AND    TP.operator_type_id = prongc.pkg_ngc_constants.operator_type_id_gsm
            AND    cr_from.to_curr <> cr_to.to_curr
            AND    cr_to.to_curr IN (SELECT curr
                                     FROM   all_currencies
                                     WHERE  curr IS NOT NULL)
            ORDER  BY cr_from.TO_CURR
                     ,cr_to.TO_CURR
                     ,tp.period_start_date)
                    
           SELECT PERIOD_START_DATE
                  ,PEGGED_RATE_FROM
                  ,CURR_FROM
                  ,PEGGED_RATE_TO
                  ,CURR_TO
                  ,FROM_TO_RATIO
                  ,LAST_RATIO
                  ,FROM_TO_RATIO - LAST_RATIO FLUCTUATION
                  ,100 * ((FROM_TO_RATIO / NULLIF(LAST_RATIO, 0)) - 1) FLUCTUATION_PERCENT
                  ,CASE
                     WHEN ABS(100 * ((FROM_TO_RATIO / NULLIF(LAST_RATIO, 0)) - 1)) >= (SELECT FX_FLUCTUATION_THRESHOLD
                                                                                       FROM   params) THEN
                      'Yes'
                     ELSE
                      'No'
                   END treshold_alert
            FROM   DAT
