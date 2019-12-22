package tests.MainRegression;

import org.testng.annotations.Test;

import com.configData_Util.Constant;

import objectRepository.DealTracker.DTModule.NavigatorDT;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_EditOperatorGrowthP8;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_EditOutboundGrowthP6;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_EditProportionalShareP10;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_MarketGrowth;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_MarketShareProportionalShare;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_MarketVolume;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_ViewInboundGrowthP24;

public class KPN_DealTracker7216 {

	public static final String jsonFilePath = Constant.getResourcesFolderPath() + "KPN_DealTracker7216.json";
	
	/**@author prafull.barve*/
	@Test(priority = 12 ,description = "T01_7217 - Forecast Country Level Growth - Disable bulk update for IB traffic (FE)")
	private void T01_ForecastCountryLevelGrowthDisableBulkUpdateForIB_7217() {
		/*
		 *  7217 Forecast Country Level Growth - Disable bulk update for IB traffic (FE)
		 * 1. Login as Nextgen user 
		 * 2. Go to Forecasting-> Deal Tracker ->Deal tracker Module> Deal Tracker summary(P4)
		 */
		NavigatorDT nav=new NavigatorDT();
		nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		DTSummary_MarketGrowth DTSummary_MarketGrowth = new DTSummary_MarketGrowth();
		DTSummary_MarketGrowth.forecastCountryLevelGrowthDisableBulkUpdateForIB_7217();
	}

	@Test(priority = 5 ,description = "T08_7235 - Market Volume - adjust forecast for Voice MT, SMS MO, SMS MT and Data MB services (BE)")
	private void T08_MarketVolumeAdjustForecastForVoiceMtSmsMoSmsMtAndDataMBservicesBE_7235() {
		/*
		 * 7235 Market Volume - adjust forecast for Voice MT, SMS MO, SMS MT and Data MB services (BE) 
		 * 1. Login as Nextgen user 
		 * 2. Go to Forecasting-> Deal Tracker ->Deal tracker Module> Deal Tracker summary(P4)
		 */

		NavigatorDT nav=new NavigatorDT();
		nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		DTSummary_MarketVolume marketVolume=new DTSummary_MarketVolume();
		marketVolume.marketVolumeAdjustForecastForVoiceMtSmsMoSmsMtAndDataMBservicesBE_7235();
	}

	@Test(priority = 13 ,description = "T02_7218 - Forecast Country Level Growth - Manual manipulation for OB traffic (FE)")
	private void T02_ForecastCountryLevelGrowthManualManipulationForObTrafficFE_7218() {
		/*
		 * 1. Login as Nextgen user 
		 * 2. Go to Forecasting-> Deal Tracker ->Deal tracker Module> Deal Tracker summary(P4) 
		 * 3. Select Home Operator
		 */

		NavigatorDT nav=new NavigatorDT();
		nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		DTSummary_EditOutboundGrowthP6 DTS = new DTSummary_EditOutboundGrowthP6();
		DTS.forecastCountryLevelGrowthManualManipulationForObTrafficFE_7218();

	}
	
	/**@author prafull.barve*/
	@Test(/*priority = 13 ,*/description = "T12_7219 - Forecast Country Level Growth - Add Voice MO growth in report (FE)")
	private void T12_ForecastCountryLevelGrowthAddVoiceMOgrowthInreportFE_7219() {
		/*
		 * 1. Login as Nextgen user 
		 * 2. Go to Forecasting-> Deal Tracker ->Deal tracker Module> Deal Tracker summary(P4) 
		 * 3. Select Home Operator
		 */

		NavigatorDT nav=new NavigatorDT();
		nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		DTSummary_MarketGrowth DTSMK = new DTSummary_MarketGrowth();
		DTSMK.ForecastCountryLevelGrowthAddVoiceMOgrowthInreportFE_7219();

	}
	
	@Test(priority = 2 ,description = "T13_7230 - Forecast Country Level Growth - Enable view option for IB traffic (FE)")
	private void T13_ForecastCountryLevelGrowthEnableViewOptionForIBTrafficFE_7230() {
		/*
		 * 1. Login as Nextgen user 
		 * 2. Go to Forecasting-> Deal Tracker ->Deal tracker Module> Deal Tracker summary(P4) 
		 * 3. Select Home Operator
		 */

		NavigatorDT nav=new NavigatorDT();
		nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		DTSummary_ViewInboundGrowthP24 DTSMK = new DTSummary_ViewInboundGrowthP24();
		DTSMK.ForecastCountryLevelGrowthEnableViewOptionForIBTrafficFE_7230();

	}


	@Test(priority = 6 ,description = "T09_7236 - Proportional Market Share - remove correlation between Voice MO and other services (BE)")
	private void T09_ProportionalMarketShareRemoveCorrelationBetweenVoiceMOAndOtherServicesBE_7236() {
		/*
		 * 1. Login as Nextgen user 
		 * 2. Go to Forecasting-> Deal Tracker ->Deal tracker Module> Deal Tracker summary(P4) 
		 * 3. Select Home Operator
		 */
		NavigatorDT nav=new NavigatorDT();
		nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		DTSummary_EditProportionalShareP10 editPropShare = new DTSummary_EditProportionalShareP10();
		editPropShare.ProportionalMarketShareRemoveCorrelationBetweenVoiceMOAndOtherServicesBE_7236();
	}

	@Test(priority = 10 ,description = "T10_7237 - Proportional Market Share - remove correlation between Voice MO and other services for bulk update (FE)")
	private void T10_ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForBulkUpdateFE_7237() {
		/*
		 * 1. Login as Nextgen user 
		 * 2. Go to Forecasting-> Deal Tracker ->Deal tracker Module> Deal Tracker summary(P4) 
		 * 3. Select Home Operator
		 */
		NavigatorDT nav=new NavigatorDT();
		nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		DTSummary_MarketShareProportionalShare markPropShare = new DTSummary_MarketShareProportionalShare();
		markPropShare.ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForBulkUpdateFE_7237();
	}


	@Test(priority = 11 ,description = "T11_7238 - Proportional Market Share - remove correlation between Voice MO and other services for manual update (FE)")
	private void T11_ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForManualUpdateFE_7238() {
		/*
		 * 1. Login as Nextgen user 
		 * 2. Go to Forecasting-> Deal Tracker ->Deal tracker Module> Deal Tracker summary(P4) 
		 * 3. Select Home Operator
		 */
		NavigatorDT nav=new NavigatorDT();
		nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		DTSummary_EditProportionalShareP10 editPropShare = new DTSummary_EditProportionalShareP10();
		editPropShare.ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForManualUpdateFE_7238();
	}

	@Test(priority = 4 ,description = "T03_7220 - Forecast Country Level Growth - Add Voice MO growth in report (BE)")
	private void T03_ForecastCountryLevelGrowthAddVoiceMoGrowthInReportBE_7220() {
		/*
		 * 1. Login as Nextgen user 
		 * 2. Go to Forecasting-> Deal Tracker ->Deal tracker Module> Deal Tracker summary(P4) 
		 * 3. Select Home Operator
		 */
		NavigatorDT nav=new NavigatorDT();
		nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		DTSummary_MarketGrowth DTSummary_MarketGrowth = new DTSummary_MarketGrowth();
		DTSummary_MarketGrowth.ForecastCountryLevelGrowthAddVoiceMoGrowthInReportBE_7220();
	}


	/**@author prafull.barve*/
	@Test(priority = 1 ,description = "T04_7231 - Forecast Operator Level Growth - Add growth for all services and event types in report (FE)")
	private void T04_ForecastOperatorLevelGrowthAddgrowthforallservicesandeventtypesinreport_FE_7231() {
		/*
		 * 1. Login as Nextgen user 
		 * 2. Go to Forecasting-> Deal Tracker ->Deal tracker Module> Deal Tracker summary(P4) 
		 * 3. Select Home Operator
		 */
		NavigatorDT nav=new NavigatorDT();
		nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		DTSummary_MarketGrowth DTSummary_MarketGrowth = new DTSummary_MarketGrowth();
		DTSummary_MarketGrowth.ForecastOperatorLevelGrowthAddgrowthforallservicesandeventtypesinreport_FE_7231();
	}

	@Test(priority = 7 ,description = "T05_7232 - Forecast Operator Level Growth - Add Voice MT, SMS MO, SMS MT and Data MB growth in report (BE)")
	private void T05_ForecastOperatorLevelGrowthAddVoiceMtSmsMoSmsMtAndDataMbgrowthInReportBE_7232() {
		/*
		 * 1. Login as Nextgen user 
		 * 2. Go to Forecasting-> Deal Tracker ->Deal tracker Module> Deal Tracker summary(P4) 
		 * 3. Select Home Operator
		 */
		NavigatorDT nav=new NavigatorDT();
		nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		DTSummary_EditOperatorGrowthP8 editOperatorGrowthP8 = new DTSummary_EditOperatorGrowthP8();
		editOperatorGrowthP8.ForecastOperatorLevelGrowthAddVoiceMtSmsMoSmsMtAndDataMbgrowthInReportBE_7232();
	}
	
	@Test(priority = 8 ,description = "T06_7233 - Forecast Operator Level Growth - Enable bulk update for all services and event types (FE)")
	private void T06_ForecastOperatorLevelGrowthEnableBulkUpdateForAllServicesAndEventTypesFE_7233() {
		/*
		 * 1. Login as Nextgen user 
		 * 2. Go to Forecasting-> Deal Tracker ->Deal tracker Module> Deal Tracker summary(P4) 
		 * 3. Select Home Operator
		 */
		NavigatorDT nav=new NavigatorDT();
		nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		DTSummary_MarketGrowth marketGrowth = new DTSummary_MarketGrowth();
		marketGrowth.ForecastOperatorLevelGrowthEnableBulkUpdateForAllServicesAndEventTypesFE_7233();
	}

	@Test(priority = 14 ,description = "T07_7234 - Forecast Operator Level Growth - Enable manual edit for all services and event types (FE)")
	private void T07_ForecastOperatorLevelGrowthEnableManualEditForAllServicesAndEventTypesFE_7234() {
		/*
		 * 1. Login as Nextgen user 
		 * 2. Go to Forecasting-> Deal Tracker ->Deal tracker Module> Deal Tracker summary(P4) 
		 * 3. Select Home Operator
		 */
		NavigatorDT nav=new NavigatorDT();
		nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		DTSummary_MarketGrowth mareketGrowth = new DTSummary_MarketGrowth();
		mareketGrowth.ForecastOperatorLevelGrowthEnableManualEditForAllServicesAndEventTypesFE_7234();
	}

}
