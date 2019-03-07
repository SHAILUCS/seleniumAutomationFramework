package tests.UI;

import org.testng.annotations.Test;

import objectRepository.CustomisedReportsTMO.DataLoadingTMO;
import objectRepository.CustomisedReportsTMO.DiscountReportTMO;
import objectRepository.CustomisedReportsTMO.IotronUsInstance;
import objectRepository.CustomisedReportsTMO.MonthlyDiscountAccrual;
import objectRepository.CustomisedReportsTMO.MonthlyDiscountAccrual_VarianceAnalysis;
import objectRepository.CustomisedReportsTMO.MonthlyDiscountActual_VarianceAnalysis;
import objectRepository.CustomisedReportsTMO.MonthlyDiscountActuals;
import objectRepository.CustomisedReportsTMO.SnapshotManagement;
import objectRepository.CustomisedReportsTMO.SummaryPositionOfRoamingCommitmentsExpense;
import objectRepository.CustomisedReportsTMO.SummaryPositionOfRoamingCommitmentsRevenue;
import objectRepository.CustomisedReportsTMO.TapAccrualActualAndTrueUp;
import objectRepository.CustomisedReportsTMO.TrafficAnalysisReportTMO;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import objectRepository.common.ServiceType;
import objectRepository.common.TrafficDirection;

public class TMOModule {
	@Test(description = "UI_TMO_TAP Accrual, Actual & True Up(p2)")
	private void UI_TMO_TAPAccrualActualAndTrueUp_2() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.TAPAccrualActualTrueUp,
						tmo.link_TAPAccrualActualAndTrueUp);
		TapAccrualActualAndTrueUp tapAccrualActualAndTrueUp=new TapAccrualActualAndTrueUp();
			tapAccrualActualAndTrueUp.verify_UI(null, null, null);

	}

	@Test(description = "UI_TMO_Monthly Discount Accrual -IB Total(p10)")
	private void UI_TMO_MonthlyDiscountAccrual_IBTotal_10() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountAccrual,
						tmo.link_MonthlyDiscount_MonthlyDiscountAccrual_IBAccrualTotal);
		
			MonthlyDiscountAccrual monthlyDiscountAccrual= new MonthlyDiscountAccrual();
			monthlyDiscountAccrual.verify_UI(null, null, null, TrafficDirection.VisitorInbound, ServiceType.ALL);

	}

	@Test(description = "UI_TMO_Monthly Discount Accrual -IB Voice(p10)")
	private void UI_TMO_MonthlyDiscountAccrual_IBVoice_10() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountAccrual,
						tmo.link_MonthlyDiscount_MonthlyDiscountAccrual_IBAccrualVoice);
		
			MonthlyDiscountAccrual monthlyDiscountAccrual= new MonthlyDiscountAccrual();
			monthlyDiscountAccrual.verify_UI(null, null, null, TrafficDirection.VisitorInbound, ServiceType.VOICE);

	}

	@Test(description = "UI_TMO_Monthly Discount Accrual -IB Sms(p10)")
	private void UI_TMO_MonthlyDiscountAccrual_IBSms_10() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountAccrual,
						tmo.link_MonthlyDiscount_MonthlyDiscountAccrual_IBAccrualSMS);
		
			MonthlyDiscountAccrual monthlyDiscountAccrual= new MonthlyDiscountAccrual();
			monthlyDiscountAccrual.verify_UI(null, null, null, TrafficDirection.VisitorInbound, ServiceType.SMS);

	}

	@Test(description = "UI_TMO_Monthly Discount Accrual -IB Data(p10)")
	private void UI_TMO_MonthlyDiscountAccrual_IBData_10() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountAccrual,
						tmo.link_MonthlyDiscount_MonthlyDiscountAccrual_IBAccrualData);
		
			MonthlyDiscountAccrual monthlyDiscountAccrual= new MonthlyDiscountAccrual();
			monthlyDiscountAccrual.verify_UI(null, null, null, TrafficDirection.VisitorInbound, ServiceType.DATA);

	}

	@Test(description = "UI_TMO_Monthly Discount Accrual -OB Total(p10)")
	private void UI_TMO_MonthlyDiscountAccrual_OBTotal_10() {
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountAccrual,
						tmo.link_MonthlyDiscount_MonthlyDiscountAccrual_IBAccrualTotal);
		
			MonthlyDiscountAccrual monthlyDiscountAccrual= new MonthlyDiscountAccrual();
			monthlyDiscountAccrual.verify_UI(null, null, null, TrafficDirection.VisitorInbound, ServiceType.ALL);

	}

	@Test(description = "UI_TMO_Monthly Discount Accrual -OB Voice(p10)")
	private void UI_TMO_MonthlyDiscountAccrual_OBVoice_10() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountAccrual,
						tmo.link_MonthlyDiscount_MonthlyDiscountAccrual_OBAccrualVoice);
		
			MonthlyDiscountAccrual monthlyDiscountAccrual= new MonthlyDiscountAccrual();
			monthlyDiscountAccrual.verify_UI(null, null, null, TrafficDirection.CustomerOutbound, ServiceType.VOICE);

	}

	@Test(description = "UI_TMO_Monthly Discount Accrual -OB Sms(p10)")
	private void UI_TMO_MonthlyDiscountAccrual_OBSms_10() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountAccrual,
						tmo.link_MonthlyDiscount_MonthlyDiscountAccrual_OBAccrualSMS);
		
			MonthlyDiscountAccrual monthlyDiscountAccrual= new MonthlyDiscountAccrual();
			monthlyDiscountAccrual.verify_UI(null, null, null, TrafficDirection.CustomerOutbound, ServiceType.SMS);

	}

	@Test(description = "UI_TMO_Monthly Discount Accrual -OB Data(p10)")
	private void UI_TMO_MonthlyDiscountAccrual_OBData_10() {
		

		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountAccrual,
						tmo.link_MonthlyDiscount_MonthlyDiscountAccrual_OBAccrualData);
		
			MonthlyDiscountAccrual monthlyDiscountAccrual= new MonthlyDiscountAccrual();
			monthlyDiscountAccrual.verify_UI(null, null, null, TrafficDirection.CustomerOutbound, ServiceType.DATA);

	}

	@Test(description = "UI_TMO_Monthly Discount Actual -IB Total(p16)")
	private void UI_TMO_MonthlyDiscountActuals_IBTotal_16() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountActual,
						tmo.link_MonthlyDiscount_MonthlyDiscountActual_IBActualTotal);
		
			MonthlyDiscountActuals MonthlyDiscountActuals= new MonthlyDiscountActuals();
			MonthlyDiscountActuals.verify_UI(null, null, null, TrafficDirection.VisitorInbound, ServiceType.ALL);

	}

	@Test(description = "UI_TMO_Monthly Discount Actual -IB Voice(p16)")
	private void UI_TMO_MonthlyDiscountActuals_IBVoice_16() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountActual,
						tmo.link_MonthlyDiscount_MonthlyDiscountActual_IBActualVoice);
		
			MonthlyDiscountActuals MonthlyDiscountActuals= new MonthlyDiscountActuals();
			MonthlyDiscountActuals.verify_UI(null, null, null, TrafficDirection.VisitorInbound, ServiceType.VOICE);

	}

	@Test(description = "UI_TMO_Monthly Discount Actual -IB Sms(p16)")
	private void UI_TMO_MonthlyDiscountActuals_IBSms_16() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountActual,
						tmo.link_MonthlyDiscount_MonthlyDiscountActual_IBActualSMS);
		
			MonthlyDiscountActuals MonthlyDiscountActuals= new MonthlyDiscountActuals();
			MonthlyDiscountActuals.verify_UI(null, null, null, TrafficDirection.VisitorInbound, ServiceType.SMS);

	}

	@Test(description = "UI_TMO_Monthly Discount Actual -IB Data(p16)")
	private void UI_TMO_MonthlyDiscountActuals_IBData_16() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountActual,
						tmo.link_MonthlyDiscount_MonthlyDiscountActual_IBActualData);
		
			MonthlyDiscountActuals MonthlyDiscountActuals= new MonthlyDiscountActuals();
			MonthlyDiscountActuals.verify_UI(null, null, null, TrafficDirection.VisitorInbound, ServiceType.DATA);

	}

	@Test(description = "UI_TMO_Monthly Discount Actual -OB Total(p16)")
	private void UI_TMO_MonthlyDiscountActuals_OBTotal_16() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountActual,
						tmo.link_MonthlyDiscount_MonthlyDiscountActual_OBActualTotal);
		
			MonthlyDiscountActuals MonthlyDiscountActuals= new MonthlyDiscountActuals();
			MonthlyDiscountActuals.verify_UI(null, null, null, TrafficDirection.CustomerOutbound, ServiceType.ALL);

	}

	@Test(description = "UI_TMO_Monthly Discount Actual -OB Voice(p16)")
	private void UI_TMO_MonthlyDiscountActuals_OBVoice_16() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountActual,
						tmo.link_MonthlyDiscount_MonthlyDiscountActual_OBActualVoice);
		
			MonthlyDiscountActuals MonthlyDiscountActuals= new MonthlyDiscountActuals();
			MonthlyDiscountActuals.verify_UI(null, null, null, TrafficDirection.CustomerOutbound, ServiceType.VOICE);

	}

	@Test(description = "UI_TMO_Monthly Discount Actual -OB Sms(p16)")
	private void UI_TMO_MonthlyDiscountActuals_OBSms_16() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountActual,
						tmo.link_MonthlyDiscount_MonthlyDiscountActual_OBActualSMS);
		
			MonthlyDiscountActuals MonthlyDiscountActuals= new MonthlyDiscountActuals();
			MonthlyDiscountActuals.verify_UI(null, null, null, TrafficDirection.CustomerOutbound, ServiceType.SMS);

	}

	@Test(description = "UI_TMO_Monthly Discount Actual -OB Data(p16)")
	private void UI_TMO_MonthlyDiscountActuals_OBData_16() {
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_MonthlyDiscountActual,
						tmo.link_MonthlyDiscount_MonthlyDiscountActual_OBActualData);
		
			MonthlyDiscountActuals MonthlyDiscountActuals= new MonthlyDiscountActuals();
			MonthlyDiscountActuals.verify_UI(null, null, null, TrafficDirection.CustomerOutbound, ServiceType.DATA);

	}

	@Test(description = "UI_TMO_Discount Report(p5)")
	private void UI_TMO_DiscountReport_5() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( DiscountReportTMO.title,
						tmo.link_MonthlyDiscount, tmo.link_MonthlyDiscount_DiscountReport);
		
			DiscountReportTMO discountReportTMO= new DiscountReportTMO();
			discountReportTMO.verify_UI("01-Jan-2018", "31-Jan-2018", null, null, false);
	}

	@Test(description = "UI_TMO_Monthly Discount Accrual Variance Analysis(p11)")
	private void UI_TMO_MonthlyDiscountAccrual_VarianceAnalysis_11() {
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual_VarianceAnalysis.title,
						tmo.link_VarianceReport, tmo.link_VarianceReport_VarianceAccruals);
		
			MonthlyDiscountAccrual_VarianceAnalysis monthlyDiscountAccrual_VarianceAnalysis= new MonthlyDiscountAccrual_VarianceAnalysis();
			monthlyDiscountAccrual_VarianceAnalysis.verify_UI(null, null, null, null);
	}

	@Test(description = "UI_TMO_Monthly Discount Actual Variance Analysis(p14)")
	private void UI_TMO_MonthlyDiscountActual_VarianceAnalysis_14() {
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( MonthlyDiscountActual_VarianceAnalysis.title,
						tmo.link_VarianceReport, tmo.link_VarianceReport_VarianceActuals);
		
		MonthlyDiscountActual_VarianceAnalysis monthlyDiscountActual_VarianceAnalysis= new MonthlyDiscountActual_VarianceAnalysis();
		monthlyDiscountActual_VarianceAnalysis.verify_UI("1216", null, null, null,null);
	}

	@Test(description = "UI_TMO_Commitments Revenue(p4)")
	private void UI_TMO_Commitments_Revenue_4() {
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( SummaryPositionOfRoamingCommitmentsRevenue.title,
						tmo.link_CommitmentReports, tmo.link_CommitmentReports_CommitmentsRevenue);
		
		SummaryPositionOfRoamingCommitmentsRevenue commitmentsRevenue=new SummaryPositionOfRoamingCommitmentsRevenue();
		commitmentsRevenue.verify_UI("11/2017");
	}


	@Test(description = "UI_TMO_Commitments Expense(p8)")
	private void UI_TMO_Commitments_Expense_8() {
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( SummaryPositionOfRoamingCommitmentsExpense.title,
						tmo.link_CommitmentReports, tmo.link_CommitmentReports_CommitmentsExpense);
		
		SummaryPositionOfRoamingCommitmentsExpense ommitmentsExpense=new SummaryPositionOfRoamingCommitmentsExpense();
		ommitmentsExpense.verify_UI("11/2017");
	}

	@Test(description = "UI_TMO_Snapshot Management(p17)")
	private void UI_TMO_SnapshotManagement_17() {
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( SnapshotManagement.title,
						tmo.link_SnapshotManagement);
		
			SnapshotManagement snapshotManagement = new SnapshotManagement();
			snapshotManagement.verify_UI(null, null, null, null);
	}

	@Test(description = "UI_TMO_Data Loading(p18)")
	private void UI_TMO_DataLoading_18() {
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( DataLoadingTMO.title,
						tmo.link_DCHData,tmo.link_DCHData_DataLoading);
		
			DataLoadingTMO dataLoadingTMO = new DataLoadingTMO();
			dataLoadingTMO.verify_UI("10-JAN-2017","17-JAN-2017");
	}

	@Test(description = "UI_TMO_Traffic Analysis Report(p19)")
	private void UI_TMO_TrafficAnalysisReport_19() {
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
				navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
						ihp.link_Reporting_CustomisedReportsTMO);
				navigate.traverseMenu_VerifyPageTitle( TrafficAnalysisReportTMO.title,
						tmo.link_DCHData,tmo.link_DCHData_TrafficAnalysisReport);
			TrafficAnalysisReportTMO trafficAnalysisReportTMO = new TrafficAnalysisReportTMO();
			trafficAnalysisReportTMO.verify_UI("01-JAN-2017","31-JAN-2017","01-JAN-2017","31-JAN-2017");
	}

	@Test(description = "verify Navigation Links Functionality_ Agreement Capture(p252)")
	private void verifyNavigationLinksFunctionality_AgreementCapture() {
			Navigator nav= new Navigator();
			nav.to_IOTRONHomePage();
			IOTRONHomePage iotronHomePage=new IOTRONHomePage();
			iotronHomePage.verifyNavigationLinksFunctionality_AgreementCapture();
	}

}
