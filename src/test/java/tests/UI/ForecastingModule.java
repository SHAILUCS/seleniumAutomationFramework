package tests.UI;

import org.testng.annotations.Test;

import objectRepository.Forecasting.FinancialReport_ForecastedTAPDiscountedCharges;
import objectRepository.Forecasting.ForecastReportPerAgreementPage;
import objectRepository.Forecasting.Forecasting_ForecastedTrafficVolumesSummaryReport;
import objectRepository.common.EventType;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import objectRepository.common.ServiceType;
import objectRepository.common.TADIG;
import objectRepository.common.TrafficDirection;

public class ForecastingModule {

	/**@author prafull.barve*/
	@Test(description="UI_Forecasting_ForecastReportperAgreement(p317)")
	private void UI_Forecasting_ForecastReportperAgreement_p317() 
	{
		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage Ihp = new IOTRONHomePage();
		nav.traverseMenu_VerifyPageTitle( PagesTitle.ForecastReportperAgreement, Ihp.link_Forecasting,Ihp.link_Forecasting_ForecastReportPerAgreement);
		ForecastReportPerAgreementPage ForecastReportPerAgreementPage = new ForecastReportPerAgreementPage();
		ForecastReportPerAgreementPage.UIVerification("2 D",ServiceType.VOICE,EventType.MO);
	}

	/**@author prafull.barve*/
	@Test(description="UI_Forecasting_FinancialReport_Forecasted TAP Discounted Charges(p497)")
	private void UI_Forecasting_FinancialReport_ForecastedTAPDiscountedCharges_p497() 
	{
		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage Ihp = new IOTRONHomePage();
		nav.traverseMenu_VerifyPageTitle( PagesTitle.FinancialReportForecastedTAPDiscountedCharges, Ihp.link_Forecasting,Ihp.link_Forecasting_FinancialReportForecastedTAPDiscountedCharges);
		FinancialReport_ForecastedTAPDiscountedCharges FinancialReport_ForecastedTAPDiscountedCharges = new FinancialReport_ForecastedTAPDiscountedCharges();
		FinancialReport_ForecastedTAPDiscountedCharges.UIVerification(TADIG.AUSTA.value, null, TrafficDirection.CustomerOutbound.value, ServiceType.VOICE.value, EventType.MO.value, null, null, null, null, null);

	}
	/**@author prafull.barve*/
	@Test(description="UI_Forecasting_Forecasted Traffic Volumes Summary Report(p493)")
	private void UI_Forecasting_ForecastedTrafficVolumesSummaryReport_p493() 
	{
		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage Ihp = new IOTRONHomePage();
		nav.traverseMenu_VerifyPageTitle( PagesTitle.ForecastedTrafficVolumesSummaryReportPeriodGlobalCountryPMN, Ihp.link_Forecasting,Ihp.link_Forecasting_ForecastedTrafficVolumesSummaryReportPeriodGlobalCountryPMN);
		Forecasting_ForecastedTrafficVolumesSummaryReport Forecasting_ForecastedTrafficVolumesSummaryReport = new Forecasting_ForecastedTrafficVolumesSummaryReport();
		//	TADIG[] tad = {TADIG.AUSTA, TADIG.ARGCM};
		//	Forecasting_ForecastedTrafficVolumesSummaryReport.UIVerification(TADIG.AUSTA,"01-FEB-2018", "31-MAR-2018", TrafficDirection.CustomerOutbound, ServiceType.VOICE, EventType.MO, tad, null, null);
		Forecasting_ForecastedTrafficVolumesSummaryReport.UIVerification(TADIG.AUSTA,"01-FEB-2018", "31-MAR-2018", TrafficDirection.CustomerOutbound, ServiceType.VOICE, EventType.MO, null, null, null);
		//	Forecasting_ForecastedTrafficVolumesSummaryReport.UIVerification(TADIG.AUSTA,"01-FEB-2018", "31-MAR-2018", TrafficDirection.CustomerOutbound, ServiceType.VOICE, EventType.MO, Util.getArray(TADIG.AUSOP, TADIG.GABCT, TADIG.ARGCM), null, null);
	}

}
