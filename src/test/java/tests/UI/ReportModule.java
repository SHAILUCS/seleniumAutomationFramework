package tests.UI;

import org.testng.annotations.Test;

import com.configData_Util.Util;

import objectRepository.Reporting.AgreementCatalogueReport;
import objectRepository.Reporting.AgreementPerformanceReportCreditNote;
import objectRepository.Reporting.AgreementPerformanceReportCreditNote_SingleMonth;
import objectRepository.Reporting.CRDSyncReport;
import objectRepository.Reporting.ChartCumulativeAnnualisedNetworkOutpaymentSavingsfromIOTs;
import objectRepository.Reporting.CommitmentReport_P492;
import objectRepository.Reporting.ConfigureIOTDiscountAgreementsforCalculationofCumulativeAnnualisedNetworkOutpaymentSavings;
import objectRepository.Reporting.CustomerOutboundRoamingMVNOSplitReport;
import objectRepository.Reporting.HutchQlikviewAccrualsReport;
import objectRepository.Reporting.HutchQlikviewReport;
import objectRepository.Reporting.IntraGroupEqualizationReport;
import objectRepository.Reporting.IntraGroupReporting;
import objectRepository.Reporting.MonthlyCNReport;
import objectRepository.Reporting.OutstandingRebatesPayable;
import objectRepository.Reporting.OutstandingRebatesReceivable;
import objectRepository.Reporting.OverviewReport_P507;
import objectRepository.Reporting.PerDayPerIMSIReport;
import objectRepository.Reporting.RODATReportFileExport;
import objectRepository.Reporting.RateAuditReport;
import objectRepository.Reporting.SavingsPerAgreement;
import objectRepository.Reporting.SavingsPerCountry;
import objectRepository.Reporting.ThresholdTrackerMatrixReport;
import objectRepository.Reporting.TotalAndPremiumNumberVolumeAndTAPCharges;
import objectRepository.Reporting.TrafficDistributionAndThresholdTrackerReport;
import objectRepository.common.Country;
import objectRepository.common.EventType;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import objectRepository.common.ServiceType;
import objectRepository.common.TADIG;
import objectRepository.common.TrafficDirection;

public class ReportModule {

	@Test(description = "UI_Rep_Overview Report with Nextgen user(p507)")
	public void UI_Rep_OverviewReport_NextgenUser() {
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting, ihp.link_Reporting_OverviewReport);
		OverviewReport_P507 overviewReport=new OverviewReport_P507();
		overviewReport.verify_UI();
	}

	@Test(description = "UI_Rep_Overview Report Search Result Section with Nextgen user(p507)")
	public void UI_Rep_OverviewReport_SearchResultSectionWithNgcUser() {

		Navigator navigate= new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting, ihp.link_Reporting_OverviewReport);
		OverviewReport_P507 overviewReport=new OverviewReport_P507();
		overviewReport.verify_SearchResultSection_UI();
	}


	@Test(description = "UI_Rep_Agreement Performance Report Credit Note_279")
	private void UI_Rep_AgreementPerformanceReportCreditNote_279() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AgreementPerformanceReportCreditNote, ihp.link_Reporting,ihp.link_Reporting_AgreementPerformanceReportCreditNote);
		AgreementPerformanceReportCreditNote agreementPerformanceReportCreditNote= new AgreementPerformanceReportCreditNote();
		agreementPerformanceReportCreditNote.verify_UI(TADIG.AUSTA, ServiceType.VOICE, EventType.MO, TrafficDirection.CustomerOutbound, null, null, null);
	}

	@Test(description = "UI_Rep_Agreement Performance Report (Single Month)_454")
	private void UI_Rep_AgreementPerformanceReportCreditNote_SingleMonth_454() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AgreementPerformanceReportCreditNoteSingleMonth, ihp.link_Reporting,ihp.link_Reporting_AgreementPerformanceReportCreditNoteSingleMonth);
		AgreementPerformanceReportCreditNote_SingleMonth agreementPerformanceReportCreditNote= new AgreementPerformanceReportCreditNote_SingleMonth();
		agreementPerformanceReportCreditNote.verify_UI(TADIG.AUSTA, ServiceType.VOICE, EventType.MO, TrafficDirection.CustomerOutbound, null, null, null);
	}

	@Test(description = "UI_Rep_RODAT Report File Export_278")
	private void UI_Rep_RODATReportFileExport_278() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.RODATReportFileExport, ihp.link_Reporting,ihp.link_Reporting_RODATReportFileExport);
		RODATReportFileExport rodatReportFileExport=new RODATReportFileExport();
		rodatReportFileExport.verify_UI();
	}

	@Test(description = "UI_Rep_Intra Group Reporting_491")
	private void UI_Rep_IntraGroupReporting_491() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.IntraGroupReporting, ihp.link_Reporting,ihp.link_Reporting_IntraGroupReporting);
		IntraGroupReporting intraGroupReporting=new IntraGroupReporting();
		intraGroupReporting.verify_UI(null, "0218", false, null);
	}

	@Test(description = "UI_Rep_Commitment Report_492")
	private void UI_Rep_CommitmentReport_492() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.CommitmentReport_P492, ihp.link_Reporting,ihp.link_Reporting_CommitmentReport);
		CommitmentReport_P492 commitmentReport=new CommitmentReport_P492();
		commitmentReport.verify_UI(Util.getArray("HKGTC"),"11/2017", null);
	}

	@Test(description = "UI_Rep_Threshold Tracker Matrix Report_520")
	private void UI_Rep_ThresholdTrackerMatrixReport_520() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.ThresholdTrackerMatrixReport, ihp.link_Reporting,ihp.link_Reporting_ThresholdTrackerMatrixReport);
		ThresholdTrackerMatrixReport thresholdTrackerMatrixReport=new ThresholdTrackerMatrixReport();
		thresholdTrackerMatrixReport.verify_UI("2 DEGREES",null,null);
	}

	@Test(description = "UI_Rep_Traffic Distribution And Threshold Tracker Report_508")
	private void UI_Rep_TrafficDistributionAndThresholdTrackerReport_508() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.TrafficDistributionandThresholdTrackerReport, ihp.link_Reporting,ihp.link_Reporting_TrafficDistributionAndThresholdTrackerReport);
		TrafficDistributionAndThresholdTrackerReport trafficDistributionAndThresholdTrackerReport=new TrafficDistributionAndThresholdTrackerReport();
		TADIG[] tadigs={TADIG.AUSTA};
		trafficDistributionAndThresholdTrackerReport.verify_UI(tadigs, "10/2017", null);

	}

	@Test(description = "UI_Rep_Intra Group Equalization Report_513")
	private void UI_Rep_IntraGroupEqualizationReport_513() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.IntraGroupEqualizationReport, ihp.link_Reporting,ihp.link_Reporting_IntraGroupEqualizationReport);
		IntraGroupEqualizationReport intraGroupEqualizationReport=new IntraGroupEqualizationReport();
		intraGroupEqualizationReport.verify_UI("2 DEGREES", null, null, null);
	}

	@Test(description = "UI_Rep_Customer/Outbound Roaming MVNO Split Report_552")
	private void UI_Rep_CustomerOutboundRoamingMVNOSplitReport_552() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.CustomerOutboundRoamingMVNOSplitReport, ihp.link_Reporting,ihp.link_Reporting_CustomerOutboundRoamingMVNOSplitReport);
		CustomerOutboundRoamingMVNOSplitReport CustomerOutboundRoamingMVNOSplitReport=new CustomerOutboundRoamingMVNOSplitReport();
		CustomerOutboundRoamingMVNOSplitReport.verify_UI(Util.getArray(TADIG.AUSTA,TADIG.ARGCM), "01-OCT-2017", "31-OCT-2017", null);
	}

	@Test(description = "UI_Rep_Rate Audit Report_597")
	private void UI_Rep_RateAuditReport_597() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.RateAuditReport, ihp.link_Reporting,ihp.link_Reporting_RateAuditReport);
		RateAuditReport RateAuditReport=new RateAuditReport();
		RateAuditReport.verify_UI(TADIG.AUSTA, Util.getArray("1017"), null, null);
	}

	@Test(description = "UI_Rep_Monthly CN report_609")
	private void UI_Rep_MonthlyCNReport_609() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MonthlyCNReport, ihp.link_Reporting,ihp.link_Reporting_MonthlyCNReport);
		MonthlyCNReport MonthlyCNReport=new MonthlyCNReport();
		MonthlyCNReport.verify_UI();
	}

	@Test(description = "UI_Rep_Per Day Per IMSI Report_613")
	private void UI_Rep_PerDayPerIMSIReport_613() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.PerDayPerIMSIReport, ihp.link_Reporting,ihp.link_Reporting_PerDayPerIMSIReport);
		PerDayPerIMSIReport PerDayPerIMSIReport=new PerDayPerIMSIReport();
		PerDayPerIMSIReport.verify_UI();
	}

	@Test(description = "UI_Rep_CRD Sync Report_620")
	private void UI_Rep_CRDSyncReport_620() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.CRDSyncReport, ihp.link_Reporting,ihp.link_Reporting_CRDSyncReport);
		CRDSyncReport CRDSyncReport=new CRDSyncReport();
		CRDSyncReport.verify_UI();
	}

	@Test(description = "UI_Rep_Hutch Qlikview Report_632")
	private void UI_Rep_HutchQlikviewReport_632() {
		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.HutchQlikviewReport, ihp.link_Reporting,ihp.link_Reporting_HutchQlikviewReport);
		HutchQlikviewReport HutchQlikviewReport=new HutchQlikviewReport();
		HutchQlikviewReport.verify_UI();
	}

	@Test(description = "UI_Rep_Hutch Qlikview Accruals Report_648")
	private void UI_Rep_HutchQlikviewAccrualsReport_648() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.HutchQlikviewAccrualsReport, ihp.link_Reporting,ihp.link_Reporting_HutchQlikviewAccrualsReport);
		HutchQlikviewAccrualsReport HutchQlikviewAccrualsReport=new HutchQlikviewAccrualsReport();
		HutchQlikviewAccrualsReport.verify_UI();
	}

	@Test(description = "UI_Rep_Total And Premium Number Volume And TAP Charges_634")
	private void UI_Rep_TotalAndPremiumNumberVolumeAndTAPCharges_634() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.TotalAndPremiumNumberVolumeAndTAPCharges, ihp.link_Reporting,ihp.link_Reporting_TotalAndPremiumNumberVolumeAndTAPCharges);
		TotalAndPremiumNumberVolumeAndTAPCharges TotalAndPremiumNumberVolumeAndTAPCharges=new TotalAndPremiumNumberVolumeAndTAPCharges();
		TotalAndPremiumNumberVolumeAndTAPCharges.verify_UI();
	}

	@Test(description = "UI_Rep_Agreement Catalogue Report_651")
	private void UI_Rep_AgreementCatalogueReport_651() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AgreementCatalogueReport, ihp.link_Reporting,ihp.link_Reporting_AgreementCatalogueReport);
		AgreementCatalogueReport AgreementCatalogueReport=new AgreementCatalogueReport();
		AgreementCatalogueReport.verify_UI(null, true, null, null, null, null, null);
	}

	@Test(description = "UI_Rep_Outstanding Rebates Payable_673")
	private void UI_Rep_OutstandingRebatesPayable_673() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.OutstandingRebatesPayable, ihp.link_Reporting,ihp.link_Reporting_OutstandingRebatesPayable);
		OutstandingRebatesPayable OutstandingRebatesPayable=new OutstandingRebatesPayable();
		OutstandingRebatesPayable.verify_UI(TADIG.AUSTA,null,null);
	}

	@Test(description = "UI_Rep_Outstanding Rebates Receivable_673")
	private void UI_Rep_OutstandingRebatesReceivable_673() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.OutstandingRebatesReceivable, ihp.link_Reporting,ihp.link_Reporting_OutstandingRebatesReceivable);
		OutstandingRebatesReceivable OutstandingRebatesReceivable=new OutstandingRebatesReceivable();
		OutstandingRebatesReceivable.verify_UI(TADIG.AUSTA,null,null);
	}

	@Test(description = "UI_Rep_Savings Per Agreement_674")
	private void UI_Rep_SavingsPerAgreement_674() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.SavingsPerAgreement, ihp.link_Reporting, ihp.link_Reporting_SavingsReport,ihp.link_Reporting_SavingsReport_SavingsPerAgreement);
		SavingsPerAgreement SavingsPerAgreement=new SavingsPerAgreement();
		SavingsPerAgreement.verify_UI(TADIG.AUSTA, "HUTCH GROUP EUROPE 2015", null, null, false);
	}

	@Test(description = "UI_Rep_Savings Per Country_677")
	private void UI_Rep_SavingsPerCountry_677() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.SavingsPerCountry, ihp.link_Reporting, ihp.link_Reporting_SavingsReport,ihp.link_Reporting_SavingsReport_SavingsPerCountry);
		SavingsPerCountry SavingsPerCountry=new SavingsPerCountry();
		SavingsPerCountry.verify_UI(TADIG.AUSTA, "2018", Country.Afghanistan, null);
	}

	@Test(description = "UI_Rep_Chart Cumulative Annualised Network Outpayment Savings from IOTs_678")
	private void UI_Rep_ChartCumulativeAnnualisedNetworkOutpaymentSavingsfromIOTs_678() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.ChartCumulativeAnnualisedNetworkOutpaymentSavingsFromIOTs, ihp.link_Reporting, ihp.link_Reporting_SavingsReport,ihp.link_Reporting_SavingsReport_ChartCumulativeAnnualisedNetworkOutpaymentSavingsFromIOTs);
		ChartCumulativeAnnualisedNetworkOutpaymentSavingsfromIOTs ChartObj=new ChartCumulativeAnnualisedNetworkOutpaymentSavingsfromIOTs();
		ChartObj.verify_UI();
	}

	@Test(description = "UI_Rep_Configure IOT Discount Agreements for Calculation of Cumulative Annualised Network Outpayment Savings_679")
	private void UI_Rep_ConfigureIOTDiscountAgreementsforCalculationofCumulativeAnnualisedNetworkOutpaymentSavings_679() {

		Navigator nav=new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.ChartCumulativeAnnualisedNetworkOutpaymentSavingsFromIOTs, ihp.link_Reporting, ihp.link_Reporting_SavingsReport,ihp.link_Reporting_SavingsReport_ChartCumulativeAnnualisedNetworkOutpaymentSavingsFromIOTs);
		ChartCumulativeAnnualisedNetworkOutpaymentSavingsfromIOTs ChartObj=new ChartCumulativeAnnualisedNetworkOutpaymentSavingsfromIOTs();
		ConfigureIOTDiscountAgreementsforCalculationofCumulativeAnnualisedNetworkOutpaymentSavings ConfigureObj=ChartObj.click_ConfigureIOTDiscountAgreementsForCalculation_Button(TADIG.AUSTA, "2018");
		ConfigureObj.verify_UI();
	}


}
