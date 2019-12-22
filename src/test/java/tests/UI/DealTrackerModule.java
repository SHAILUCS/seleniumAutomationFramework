package tests.UI;

import org.testng.annotations.Test;

import com.configData_Util.Util;

import objectRepository.DealTracker.BudgetModule.BudgetAdministration;
import objectRepository.DealTracker.BudgetModule.BudgetForecastCreation;
import objectRepository.DealTracker.BudgetModule.BudgetTrueup;
import objectRepository.DealTracker.BudgetModule.FutureFXRate;
import objectRepository.DealTracker.BudgetModule.PerformanceTracker;
import objectRepository.DealTracker.BudgetModule.PricingCatalogue;
import objectRepository.DealTracker.DTModule.ChangeAuditReport;
import objectRepository.DealTracker.DTModule.CurrentMonthSteering;
import objectRepository.DealTracker.DTModule.DailyTrafficAnalysisReport;
import objectRepository.DealTracker.DTModule.DataLoading;
import objectRepository.DealTracker.DTModule.DealTrackerHome;
import objectRepository.DealTracker.DTModule.DealTrackerSnapshotManagement;
import objectRepository.DealTracker.DTModule.NavigatorDT;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_AllAgreementsInSelectedCountries;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_Charges;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_EditMarketShareP7;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_EditMarketVolumeP5;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_EditOperatorGrowthP8;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_EditOutboundGrowthP6;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_EditProportionalShareP10;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_EditTAPRate;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_Header;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_MarketGrowth;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_MarketShareProportionalShare;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_MarketVolume;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_NetEffectiveRates;
import objectRepository.DealTracker.FlashModule.FlashReport;
import objectRepository.DealTracker.FlashModule.FlashReportArchive;
import objectRepository.DealTracker.FlashModule.YTDTrueup;
import objectRepository.common.Country;
import objectRepository.common.EventType;
import objectRepository.common.ServiceType;
import objectRepository.common.TADIG;
import objectRepository.common.TrafficDirection;

public class DealTrackerModule {

	@Test(description = "UI_DT_DTSummary_HEADER _Search Parameters(p4)")
	private void UI_DT_DTSummary_HEADER_4() {

		NavigatorDT nav=new NavigatorDT();
		DTSummary_Header dealTrackerSummary =nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		dealTrackerSummary.verify_UI();
	}

	@Test(description = "UI_DT_Deal Tracker Summary_Net Effective Rates Section(p4)")
	private void UI_DT_DTSummary_NetEffectiveRates_4() {

		NavigatorDT nav=new NavigatorDT();
		DTSummary_Header dealTrackerSummary =nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		TrafficDirection td[]={TrafficDirection.CustomerOutbound,TrafficDirection.VisitorInbound};
		Country c[]={Country.Afghanistan};
		if(dealTrackerSummary.performSearch("2018", null, TADIG.NLDPT, td, Util.getArray(ServiceType.VOICE), c, Util.getArray(EventType.MO))){
			DTSummary_NetEffectiveRates netEffectiveRates=new DTSummary_NetEffectiveRates();
			netEffectiveRates.verify_UI();
		}
	}

	@Test(description = "UI_DT_Deal Tracker Summary_All Agreements In Selected Countries Section(p4)")
	private void UI_DT_DTSummary_AllAgreementsInSelectedCountries_4() {

		NavigatorDT nav=new NavigatorDT();
		DTSummary_Header dealTrackerSummary =nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		TrafficDirection td[]={TrafficDirection.CustomerOutbound,TrafficDirection.VisitorInbound};
		Country c[]={Country.Afghanistan};
		if(dealTrackerSummary.performSearch("2018", null, TADIG.NLDPT, td, Util.getArray(ServiceType.VOICE), c, Util.getArray(EventType.MO))){
			DTSummary_AllAgreementsInSelectedCountries agreementsInSelectedCountries=new DTSummary_AllAgreementsInSelectedCountries();
			agreementsInSelectedCountries.verify_UI();
		}
	}

	@Test(description = "UI_DT_Deal Tracker Summary_Market Growth Section(p4)")
	private void UI_DT_DTSummary_MarketGrowth_4() {

		NavigatorDT nav=new NavigatorDT();
		DTSummary_Header dealTrackerSummary =nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		TrafficDirection td[]={TrafficDirection.CustomerOutbound,TrafficDirection.VisitorInbound};
		Country c[]={Country.Austria};
		if(dealTrackerSummary.performSearch("2018", null, TADIG.NLDPT, td, null, c, null)){
			DTSummary_MarketGrowth marketGrowth=new DTSummary_MarketGrowth();
			marketGrowth.verify_UI();
		}

	}

	@Test(description = "UI_DT_Market Share/Proportional Share Section(p4)")
	private void UI_DT_DTSummary_MarketShareProportionalShare_4() {

		NavigatorDT nav=new NavigatorDT();
		DTSummary_Header dealTrackerSummary =nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		TrafficDirection td[]={TrafficDirection.CustomerOutbound,TrafficDirection.VisitorInbound};
		Country c[]={Country.Austria};
		if(dealTrackerSummary.performSearch("2018", null, TADIG.NLDPT, td, null, c, null)){
			DTSummary_MarketShareProportionalShare marketShareProportionalShare=new DTSummary_MarketShareProportionalShare();
			marketShareProportionalShare.verify_UI();
		}

	}

	@Test(description = "UI_DT_Market Volume Section(p4)")
	private void UI_DT_DTSummary_MarketVolume_4() {

		NavigatorDT nav=new NavigatorDT();
		DTSummary_Header dealTrackerSummary =nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		TrafficDirection td[]={TrafficDirection.CustomerOutbound,TrafficDirection.VisitorInbound};
		Country c[]={Country.Austria};
		if(dealTrackerSummary.performSearch("2018", null, TADIG.NLDPT, td, null, c, null)){
			DTSummary_MarketVolume marketVolume=new DTSummary_MarketVolume();
			marketVolume.verify_UI();
		}

	}

	@Test(description = "UI_DT_Charges Section(p4)")
	private void UI_DT_DTSummary_Charges_4() {

		NavigatorDT nav=new NavigatorDT();
		DTSummary_Header dealTrackerSummary =nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		TrafficDirection td[]={TrafficDirection.CustomerOutbound,TrafficDirection.VisitorInbound};
		Country c[]={Country.Austria};
		if(dealTrackerSummary.performSearch("2018", null, TADIG.NLDPT, td, null, c, null)){
			DTSummary_Charges charges=new DTSummary_Charges();
			charges.verify_UI();
		}

	}


	@Test(description = "UI_DT_Edit Traffic Growth(p6)")
	private void UI_DT_EditTrafficGrowth_6() {

		NavigatorDT nav=new NavigatorDT();
		DTSummary_Header dealTrackerSummary =nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		TrafficDirection td[]={TrafficDirection.CustomerOutbound,TrafficDirection.VisitorInbound};
		Country c[]={Country.Austria};
		if(dealTrackerSummary.performSearch("2018", null, TADIG.NLDPT, td, null, c, null)){
			DTSummary_EditOutboundGrowthP6 editObGrowth=new DTSummary_EditOutboundGrowthP6();
			editObGrowth.navigateTo_EditOutboundGrowth().verify_UI();
		}

	}


	@Test(description = "UI_DT_Edit Operator Growth(p8)")
	private void UI_DT_EditOperatorGrowth_8() {

		NavigatorDT nav=new NavigatorDT();
		DTSummary_Header dealTrackerSummary =nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		TrafficDirection td[]={TrafficDirection.CustomerOutbound,TrafficDirection.VisitorInbound};
		Country c[]={Country.Austria};
		if(dealTrackerSummary.performSearch("2018", null, TADIG.NLDPT, td, null, c, null)){
			DTSummary_EditOperatorGrowthP8 editOperatorGrowth=new DTSummary_EditOperatorGrowthP8();
			editOperatorGrowth.navigateTo_EditOperatorGrowth().verify_UI();
		}

	}
	@Test(description = "UI_DT_Edit Market Share(p7)")
	private void UI_DT_EditMarketShare_7() {

		NavigatorDT nav=new NavigatorDT();
		DTSummary_Header dealTrackerSummary =nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		TrafficDirection td[]={TrafficDirection.CustomerOutbound,TrafficDirection.VisitorInbound};
		Country c[]={Country.Austria};
		if(dealTrackerSummary.performSearch("2018", null, TADIG.NLDPT, td, null, c, null)){
			DTSummary_EditMarketShareP7 marketShare=new DTSummary_EditMarketShareP7();
			marketShare.navigateTo_EditMarketShare().verify_UI();
		}

	}

	@Test(description = "UI_DT_Edit Proportional Share(p10)")
	private void UI_DT_EditProportionalShare_10() {

		NavigatorDT nav=new NavigatorDT();
		DTSummary_Header dealTrackerSummary =nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		TrafficDirection td[]={TrafficDirection.CustomerOutbound,TrafficDirection.VisitorInbound};
		Country c[]={Country.Austria};
		if(dealTrackerSummary.performSearch("2018", null, TADIG.NLDPT, td, null, c, null)){
			DTSummary_EditProportionalShareP10 proportionalShare=new DTSummary_EditProportionalShareP10();
			proportionalShare.NAVIGATE_To_EditProportionalShare().verify_UI();
		}

	}

	@Test(description = "UI_DT_Edit Market Volume(p5)")
	private void UI_DT_EditMarketVolume_5() {

		NavigatorDT nav=new NavigatorDT();
		DTSummary_Header dealTrackerSummary =nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		TrafficDirection td[]={TrafficDirection.CustomerOutbound,TrafficDirection.VisitorInbound};
		Country c[]={Country.Austria};
		if(dealTrackerSummary.performSearch("2018", null, TADIG.NLDPT, td, null, c, null)){
			DTSummary_EditMarketVolumeP5 marketVolume=new DTSummary_EditMarketVolumeP5();
			marketVolume.NAVIGATE_To_EditMarketVolume().verify_UI();
		}

	}


	@Test(description = "UI_DT_Edit TAP Rate(p15)")
	private void UI_DT_EditTapRate_15() {

		NavigatorDT nav=new NavigatorDT();
		DTSummary_Header dealTrackerSummary =nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSummary();
		TrafficDirection td[]={TrafficDirection.CustomerOutbound,TrafficDirection.VisitorInbound};
		Country c[]={Country.Austria};
		if(dealTrackerSummary.performSearch("2018", null, TADIG.NLDPT, td, null, c, null)){
			DTSummary_EditTAPRate tapRate=new DTSummary_EditTAPRate();
			tapRate.navigateTo_EditTapRate().verify_UI();
		}

	}

	@Test(description = "UI_DT_Current Month Steering(p2)")
	private void UI_DT_CurrentMonthSteering_2() {

		NavigatorDT nav=new NavigatorDT();
		CurrentMonthSteering currentMonthSteering =nav.to_IOTRONHomePage().to_DealTrackerHome().to_CurrentMonthSteering();
		currentMonthSteering.verify_UI(TADIG.NLDPT, null, null, null);
	}

	@Test(description = "UI_DT_Deal Tracker Snapshot Management(p9)")
	private void UI_DT_DealTrackerSnapshotManagement_9() {

		NavigatorDT nav=new NavigatorDT();
		DealTrackerSnapshotManagement snapshotManagement  =nav.to_IOTRONHomePage().to_DealTrackerHome().to_DealTrackerSnapshotManagement();
		snapshotManagement.verify_UI(TADIG.NLDPT);
	}

	/**
	 * For EE(GBROR,GBRME) user. Script failed due to, 
	 * Access denied by page security check issue
	 * */
	@Test(description = "UI_DT_Flash Report(p14)")
	private void UI_DT_FlashReport_14() {

		NavigatorDT nav=new NavigatorDT();
		FlashReport flashReport =nav.to_IOTRONHomePage().to_DealTrackerHome().to_FlashReport();
		Country[] c= {Country.Austria};
		flashReport.verify_UI("01-Nov-2018", "30-Nov-2018", null, Util.getArray("Not available"), null, null, null, c, null, null, null);
	}

	@Test(description = "UI_DT_YTD Trueup(p16)")
	private void UI_DT_YTDTrueup_16() {
		//User = EE-S
		NavigatorDT nav=new NavigatorDT();
		YTDTrueup yTDTrueup =nav.to_IOTRONHomePage().to_DealTrackerHome().to_YTDTrueup();
		Country[] c= {Country.Afghanistan};
		yTDTrueup.verify_UI("01-Jan-2017", null, null, null, null, null, null, c, null, null, null);
	}

	/**
	 * For EE(GBROR,GBRME) user. Script failed due to, 
	 * Access denied by page security check issue
	 * */
	@Test(description = "UI_DT_Flash report archive(p23)")
	private void UI_DT_FlashReportArchive_23() {

		NavigatorDT nav=new NavigatorDT();
		FlashReportArchive flashReportArchive =nav.to_IOTRONHomePage().to_DealTrackerHome().to_FlashReportArchive();
		flashReportArchive.verify_UI();
	}

	/**
	 * For EE(GBROR,GBRME) user. Script failed due to, 
	 * Access denied by page security check issue
	 * */
	@Test(description = "UI_DT_Performance Tracker(p19)")
	private void UI_DT_PerformanceTracker_19() {

		NavigatorDT nav=new NavigatorDT();
		PerformanceTracker performanceTracker =nav.to_IOTRONHomePage().to_DealTrackerHome().to_PerformanceTracker();

		String dateFrom="01-JAN-2018",dateTo="28-FEB-2018";
		ServiceType[] serviceType={ServiceType.VOICE};
		EventType[] eventType={EventType.MO};
		Country[] country={Country.Afghanistan};
		performanceTracker.verify_UI(null, dateFrom, dateTo, TrafficDirection.CustomerOutbound, serviceType, eventType, country, null, null, null);
	}

	/**
	 * For EE(GBROR,GBRME) user. Script failed due to, 
	 * Access denied by page security check issue
	 * */
	@Test(description = "UI_DT_Budget Administration(p18)")
	private void UI_DT_BudgetAdministration_18() {
		NavigatorDT nav=new NavigatorDT();
		BudgetAdministration budgetAdministration =nav.to_IOTRONHomePage().to_DealTrackerHome().to_BudgetAdministration();
		budgetAdministration.verify_UI();
	}	

	/**
	 * For EE(GBROR,GBRME) user. Script failed due to, 
	 * Access denied by page security check issue
	 * */
	@Test(description = "UI_DT_Budget/Forecast Creation(p22)")
	private void UI_DT_BudgetForecastCreation_22() {

		NavigatorDT nav=new NavigatorDT();
		BudgetForecastCreation budgetForecastCreation=nav.to_IOTRONHomePage().to_DealTrackerHome().to_BudgetForecastCreation();

		ServiceType[] serviceType={ServiceType.VOICE};
		EventType[] eventType={EventType.MO};
		Country[] country={Country.Afghanistan};
		budgetForecastCreation.verify_UI("01-JAN-2017", "31-DEC-2019", TrafficDirection.CustomerOutbound, serviceType, eventType, country, null, null, null, null);

	}

	/**
	 * For EE(GBROR,GBRME) user. Script failed due to, 
	 * Access denied by page security check issue
	 * */
	@Test(description = "UI_DT_Budget True-up(p20)")
	private void UI_DT_BudgetTrueup_20() {

		NavigatorDT nav=new NavigatorDT();
		BudgetTrueup budgetTrueup=nav.to_IOTRONHomePage().to_DealTrackerHome().to_BudgetTrueup();

		String dataSet="Budget-Training-2016_v1";
		ServiceType[] serviceType={ServiceType.VOICE};
		EventType[] eventType={EventType.MO};
		Country[] country={Country.Afghanistan};
		budgetTrueup.verify_UI(dataSet, null, null, null, TrafficDirection.CustomerOutbound, serviceType, eventType, country, null, null, null, null);
	}

	/**
	 * For EE(GBROR,GBRME) user. Script failed due to, 
	 * Access denied by page security check issue
	 * */
	@Test(description = "UI_DT_Future FX rate(p17)")
	private void UI_DT_FutureFXrate_17() {

		NavigatorDT nav=new NavigatorDT();
		FutureFXRate futureFXRate=nav.to_IOTRONHomePage().to_DealTrackerHome().to_FutureFXRate();
		futureFXRate.verify_UI();
	}

	/**
	 * For EE(GBROR,GBRME) user. Script failed due to, 
	 * Access denied by page security check issue
	 * */
	@Test(description = "UI_DT_Pricing Catalogue(p11)")
	private void UI_DT_PricingCatalogue_11() {

		NavigatorDT nav=new NavigatorDT();
		PricingCatalogue pricingCatalogue=nav.to_IOTRONHomePage().to_DealTrackerHome().to_PricingCatalogue();
		pricingCatalogue.verify_UI();
	}

	/**
	 * For EE(GBROR,GBRME) user. Script failed due to, 
	 * Access denied by page security check issue
	 * */
	@Test(description = "UI_DT_Daily Traffic Analysis Report(p12)")
	private void UI_DT_DailyTrafficAnalysisReport_12() {

		NavigatorDT nav=new NavigatorDT();
		DailyTrafficAnalysisReport dailyTrafficAnalysisReport=nav.to_IOTRONHomePage().to_DealTrackerHome().to_DailyTrafficAnalysisReportDT();
		String[] country={"Afghanistan"};
		String[] SubscriberType = {null};
		dailyTrafficAnalysisReport.verify_UI(null, null, TrafficDirection.CustomerOutbound, ServiceType.VOICE, EventType.MO, null, null, country, null);

	}

	/**
	 * For EE(GBROR,GBRME) user. Script failed due to, 
	 * Access denied by page security check issue
	 * */
	@Test(description = "UI_DT_Data Loading(p13)")
	private void UI_DT_DataLoading_13() {

		NavigatorDT nav=new NavigatorDT();
		DataLoading dataLoading=nav.to_IOTRONHomePage().to_DealTrackerHome().to_DataLoading();
		dataLoading.verify_UI();
	}

	/**
	 * For EE(GBROR,GBRME) user. Script failed due to, 
	 * Access denied by page security check issue
	 * */
	@Test(description = "UI_DT_Change Audit Report(p3)")
	private void UI_DT_ChangeAuditReport_3() {

		NavigatorDT nav=new NavigatorDT();
		ChangeAuditReport changeAuditReport=nav.to_IOTRONHomePage().to_DealTrackerHome().to_ChangeAuditReport();
		changeAuditReport.verify_UI("01-Jan-2018", null, null, TrafficDirection.CustomerOutbound, ServiceType.VOICE, EventType.MO, null);
	}

	@Test(description = "UI_DT_Header Data And Menu Links Verification")
	private void UI_DT_HeaderDataAndMenuLinksVerification() {

		NavigatorDT nav=new NavigatorDT();
		nav.to_IOTRONHomePage().to_DealTrackerHome();
		DealTrackerHome dealTrackerHome= new DealTrackerHome();
		dealTrackerHome.verifyMenuLinksAndHeaderData();

	}

}
