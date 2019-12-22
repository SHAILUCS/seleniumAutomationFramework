package objectRepository.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class IOTRONHomePage extends Header{

	private SeleniumMethods com;
	private ApexCommon comm;
	private Navigator nav;
	public static String title = "IOTRON - Welcome";

	public IOTRONHomePage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		nav= new Navigator();
	}

	@FindBy(xpath = "//h2[contains(normalize-space(),'Agreement Alerts')]")
	public WebElement data_Section_AgreementAlerts;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(normalize-space(),'Agreement Alerts')]//button[contains(@class,'hideShow')]")
	public WebElement icon_Section_AgreementAlerts_HideShow;

	@FindBy(xpath = "//h1[contains(normalize-space(),'IOTRON - Welcome')]")
	public WebElement data_IOTRONWelcome_Breadcrumb;

	@FindBy(xpath = "//span[@class='a-IRR-message']")
	public WebElement data_Msg_ThisQueryReturns;

	public By link_Welcome=By.xpath("//a[normalize-space()='Welcome']");

	@FindBy(xpath = "//button[@title='Next']")
	public WebElement button_Next;

	@FindBy(xpath = "//span[contains(normalize-space(),'1 -')]")
	public WebElement data_Pagination;

	public List<String> tableColumns=new ArrayList<>(Arrays.asList("Review Alert","Agreement Reference","Alert Date","Alert Author"
			,"Alert Type","Alert Status","Review Date","Reviewed By","View Agreement"));

	public void UIVerification() 
	{	
		com.isElementPresent(data_IOTRONWelcome_Breadcrumb, "IOTRON Welcome, Data");
		verifyMenuLinksContent();
		verifyAgreementAlertsContent();
	}
	
	private void verifyMenuLinksContent() {
		CustomReporter.createNode("Verifying Links present on page");
		com.isElementPresent(link_IOTRONSystemAdministration, "IOTRON System Administration, Link");	
		com.isElementPresent(link_AgreementCapture, "Agreement Capture, Link");	
		com.isElementPresent(link_Reporting, "Reporting, Link");	
		com.isElementPresent(link_Forecasting, "Forecasting, Link");	
		com.isElementPresent(link_Settlement, "Settlement, Link");	
		com.isElementPresent(link_BusinessIntelligence, "Business Intelligence, Link");	
		com.isElementPresent(link_Operations, "Operations, Link");
	}
	
	private void verifyAgreementAlertsContent() {
		CustomReporter.createNode("Verifying Agreement Alerts Section content");
		com.isElementPresent(data_Section_AgreementAlerts, "Agreement Alerts Section Heading");
		com.isElementPresent(icon_Section_AgreementAlerts_HideShow, "Agreement Alerts hide show icon");

		com.isElementPresent(comm.icon_SelectColumnToSearch, "Search Icon, DropDown");	
		com.isElementPresent(comm.text_Search, "Search textfield, SearchBox");	
		com.isElementPresent(comm.button_Go, "Go button, button");	
		com.isElementPresent(comm.select_RowsPerPage, "select_Rows, Rows");
		comm.verifyActionsPopupItems_WithoutSubscription();

		com.isElementPresent(data_Msg_ThisQueryReturns, "Msg, Data "+com.getText(data_Msg_ThisQueryReturns));

		CustomReporter.report(STATUS.INFO, "Verifying the Table columns");
		comm.verifyColumnHeaders(comm.table_ResultTabHeader,tableColumns, 1);
		com.isElementPresent(data_Pagination, "Pagination data "+com.getText(data_Pagination));
		com.isElementPresent(button_Next, "Next, button");
	}
	
	public By link_BreadCrumb_IOTRONWelcome=By.xpath("//button[.='IOTRON - Welcome']");

	public By link_IOTRONSystemAdministration=By.xpath("//button[contains(normalize-space(),'IOTRON System Administration')]");
	
	public By link_IOTRONSystemAdministration_MaintainIOTClients=By.xpath("//a[normalize-space()='Maintain IOT Clients']");

	public By link_IOTRONSystemAdministration_QueryIOTSummary=By.xpath("//a[normalize-space()='Query IOT Summary']");

	public By link_IOTRONSystemAdministration_MaintainOperatorsHavingMoreThanOnePMNCode=By.xpath("//a[normalize-space()='Maintain Operators having more than one PMN code']");

	public By link_IOTRONSystemAdministration_BIDashboardParameters=By.xpath("//a[normalize-space()='BI Dashboard Parameters']");

	public By link_IOTRONSystemAdministration_IOTRONAuditReportIOTAgreements=By.xpath("//a[normalize-space()='IOTRON Audit Report (IOT Agreements)']");

	public By link_IOTRONSystemAdministration_IOTRONAuditReportCalculations=By.xpath("//a[normalize-space()='IOTRON Audit Report (Calculations)']");

	public By link_IOTRONSystemAdministration_InternalInvoicingReport=By.xpath("//a[normalize-space()='Internal Invoicing Report']");

	public By link_IOTRONSystemAdministration_MaintainPersonalFavouritesPages=By.xpath("//a[normalize-space()='Maintain Personal Favourites Pages']");

	public By link_IOTRONSystemAdministration_MaintainSettlementGrouping=By.xpath("//a[normalize-space()='Maintain Settlement grouping']");

	public By link_IOTRONSystemAdministration_IOTRONSettlementStatementDataCorrection=By.xpath("//a[normalize-space()='IOTRON Settlement Statement Data Correction']");

	public By link_IOTRONSystemAdministration_MaintainClientDefinedCountries=By.xpath("//a[normalize-space()='Maintain Client Defined Countries']");

	public By link_IOTRONSystemAdministration_MaintainClientDefinedCountriesMapping=By.xpath("//a[normalize-space()='Maintain Client Defined Countries Mapping']");

	public By link_IOTRONSystemAdministration_MaintainClientAgreementPhase=By.xpath("//a[normalize-space()='Maintain Client Agreement Phase']");

	public By link_IOTRONSystemAdministration_MaintainClientJobTitle=By.xpath("//a[normalize-space()='Maintain Client Job Title']");
	
	public void verifyNavigationLinksFunctionality_IOTRONSystemAdministration() {
		CustomReporter.createNode("Verifying IOTRON System Administration Module links");
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainIOTClients_P310, link_IOTRONSystemAdministration, link_IOTRONSystemAdministration_MaintainIOTClients);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.QueryIOTSummary, link_IOTRONSystemAdministration, link_IOTRONSystemAdministration_QueryIOTSummary);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainOperatorsHavingMoreThanOnePMNcode_P479, link_IOTRONSystemAdministration, link_IOTRONSystemAdministration_MaintainOperatorsHavingMoreThanOnePMNCode);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.BIDashboardParameters, link_IOTRONSystemAdministration, link_IOTRONSystemAdministration_BIDashboardParameters);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.IOTRONAuditReportIOTAgreements, link_IOTRONSystemAdministration, link_IOTRONSystemAdministration_IOTRONAuditReportIOTAgreements);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.IotronAuditReportCalculations, link_IOTRONSystemAdministration, link_IOTRONSystemAdministration_IOTRONAuditReportCalculations);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.InternalInvoicingReport, link_IOTRONSystemAdministration, link_IOTRONSystemAdministration_InternalInvoicingReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainPersonalFavouritesPages, link_IOTRONSystemAdministration, link_IOTRONSystemAdministration_MaintainPersonalFavouritesPages);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainSettlementgrouping, link_IOTRONSystemAdministration, link_IOTRONSystemAdministration_MaintainSettlementGrouping);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.IOTRONSettlementStatementDataCorrection, link_IOTRONSystemAdministration, link_IOTRONSystemAdministration_IOTRONSettlementStatementDataCorrection);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainClientDefinedCountries, link_IOTRONSystemAdministration, link_IOTRONSystemAdministration_MaintainClientDefinedCountries);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainClientDefinedCountriesMapping, link_IOTRONSystemAdministration, link_IOTRONSystemAdministration_MaintainClientDefinedCountriesMapping);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainClientAgreementPhase, link_IOTRONSystemAdministration, link_IOTRONSystemAdministration_MaintainClientAgreementPhase);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainClientJobTitle, link_IOTRONSystemAdministration, link_IOTRONSystemAdministration_MaintainClientJobTitle);
	}


	public By link_AgreementCapture=By.xpath("//button[contains(normalize-space(),'Agreement Capture')]");

	public By link_AgreementCapture_MaintainIOTDiscountAgreements=By.xpath("//a[normalize-space()='Maintain IOT Discount Agreements']");

	public By link_AgreementCapture_MaintainIOTDiscountPartyDetails=By.xpath("//a[normalize-space()='Maintain IOT Discount Party Details']");

	public By link_AgreementCapture_MaintainIOTOperatorGroups=By.xpath("//a[normalize-space()='Maintain IOT Operator Groups']");

	public By link_AgreementCapture_AgreementChecklistReview=By.xpath("//a[normalize-space()='Agreement Checklist Review']");

	public By link_AgreementCapture_ZoneDefinition=By.xpath("//a[normalize-space()='Zone Definition']");

	public By link_AgreementCapture_SOXReport=By.xpath("//a[normalize-space()='SOX Report']");

	public By link_AgreementCapture_ValueCommitmentFlexing=By.xpath("//a[normalize-space()='Value Commitment Flexing']");

	public void verifyNavigationLinksFunctionality_AgreementCapture() {
		CustomReporter.createNode("Verifying Agreement Capture Module links");
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainIOTDiscountAgreements_P301, link_AgreementCapture, link_AgreementCapture_MaintainIOTDiscountAgreements);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainIOTDiscountPartyDetails, link_AgreementCapture, link_AgreementCapture_MaintainIOTDiscountPartyDetails);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainIOTOperatorGroups, link_AgreementCapture, link_AgreementCapture_MaintainIOTOperatorGroups);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.AgreementChecklistReview, link_AgreementCapture, link_AgreementCapture_AgreementChecklistReview);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.ZoneDefinition, link_AgreementCapture, link_AgreementCapture_ZoneDefinition);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.SOXReport, link_AgreementCapture, link_AgreementCapture_SOXReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.ValueCommitmentFlexing, link_AgreementCapture, link_AgreementCapture_ValueCommitmentFlexing);
	}

	
	public By link_Reporting=By.xpath("//button[contains(normalize-space(),'Reporting')]");

	public By link_Reporting_CustomisedReportsTMO=By.xpath("//a[normalize-space()='Customised Reports (TMO)']");

	public By link_Reporting_AgreementPerformanceReportCreditNote=By.xpath("//a[normalize-space()='Agreement Performance Report Credit Note']");

	public By link_Reporting_AgreementPerformanceReportCreditNoteSingleMonth=By.xpath("//a[normalize-space()='Agreement Performance Report Credit Note (Single Month)']");

	public By link_Reporting_RODATReportFileExport=By.xpath("//a[normalize-space()='RODAT Report File Export']");

	public By link_Reporting_IntraGroupReporting=By.xpath("//a[normalize-space()='Intra Group Reporting']");

	public By link_Reporting_CommitmentReport=By.xpath("//a[normalize-space()='Commitment Report']");

	public By link_Reporting_OverviewReport=By.xpath("//a[normalize-space()='Overview Report']");

	public By link_Reporting_ThresholdTrackerMatrixReport=By.xpath("//a[normalize-space()='Threshold Tracker Matrix Report']");

	public By link_Reporting_TrafficDistributionAndThresholdTrackerReport=By.xpath("//a[normalize-space()='Traffic Distribution and Threshold Tracker Report']");

	public By link_Reporting_IntraGroupEqualizationReport=By.xpath("//a[normalize-space()='Intra Group Equalization Report']");

	public By link_Reporting_CustomerOutboundRoamingMVNOSplitReport=By.xpath("//a[normalize-space()='Customer/Outbound Roaming MVNO Split Report']");

	public By link_Reporting_RateAuditReport=By.xpath("//a[normalize-space()='Rate Audit Report']");

	public By link_Reporting_MonthlyCNReport=By.xpath("//a[normalize-space()='Monthly CN Report']");

	public By link_Reporting_PerDayPerIMSIReport=By.xpath("//a[normalize-space()='Per Day Per IMSI Report']");

	public By link_Reporting_CRDSyncReport=By.xpath("//a[normalize-space()='CRD Sync Report']");

	public By link_Reporting_HutchQlikviewReport=By.xpath("//a[normalize-space()='Hutch Qlikview Report']");

	public By link_Reporting_HutchQlikviewAccrualsReport=By.xpath("//a[contains(normalize-space(),'Hutch Qlikview Accruals Report')]");

	public By link_Reporting_TotalAndPremiumNumberVolumeAndTAPCharges=By.xpath("//a[contains(normalize-space(),'Total and Premium Number Volume and TAP Charges')]");

	public By link_Reporting_AgreementCatalogueReport=By.xpath("//a[contains(normalize-space(),'Agreement Catalogue Report')]");

	public By link_Reporting_OutstandingRebatesPayable=By.xpath("//a[contains(normalize-space(),'Outstanding Rebates Payable')]");

	public By link_Reporting_OutstandingRebatesReceivable=By.xpath("//a[contains(normalize-space(),'Outstanding Rebates Receivable')]");

	public By link_Reporting_SavingsReport=By.xpath("//button[contains(normalize-space(),'Savings Report')]");

	public By link_Reporting_SavingsReport_SavingsPerAgreement=By.xpath("//a[contains(normalize-space(),'Savings per Agreement')]");

	public By link_Reporting_SavingsReport_SavingsPerCountry=By.xpath("//a[contains(normalize-space(),'Savings per Country')]");

	public By link_Reporting_SavingsReport_ChartCumulativeAnnualisedNetworkOutpaymentSavingsFromIOTs=By.xpath("//a[contains(normalize-space(),'Chart Cumulative Annualised Network Outpayment Savings from IOTs')]");

	public By link_Reporting_PrePostMonthEndModule=By.xpath("//a[contains(normalize-space(),'Pre & Post Month End Module')]");

	public By link_Reporting_AccrualReport=By.xpath("//a[contains(normalize-space(),'Accrual Report')]");
	
	
	public void verifyNavigationLinksFunctionality_Reporting() {
		CustomReporter.createNode("Verifying Reporting Module links");
		nav.traverseMenu_VerifyPageTitle(PagesTitle.CustomisedReportsTMO, link_Reporting, link_Reporting_CustomisedReportsTMO);
		com.click(link_Header_IOTRON);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.AgreementPerformanceReportCreditNote, link_Reporting, link_Reporting_AgreementPerformanceReportCreditNote);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.AgreementPerformanceReportCreditNoteSingleMonth, link_Reporting, link_Reporting_AgreementPerformanceReportCreditNoteSingleMonth);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.RODATReportFileExport, link_Reporting, link_Reporting_RODATReportFileExport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.IntraGroupReporting, link_Reporting, link_Reporting_IntraGroupReporting);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.CommitmentReport_P492, link_Reporting, link_Reporting_CommitmentReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.OverviewReport_P507, link_Reporting, link_Reporting_OverviewReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.ThresholdTrackerMatrixReport, link_Reporting, link_Reporting_ThresholdTrackerMatrixReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.TrafficDistributionandThresholdTrackerReport, link_Reporting, link_Reporting_TrafficDistributionAndThresholdTrackerReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.IntraGroupEqualizationReport, link_Reporting, link_Reporting_IntraGroupEqualizationReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.CustomerOutboundRoamingMVNOSplitReport, link_Reporting, link_Reporting_CustomerOutboundRoamingMVNOSplitReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.RateAuditReport, link_Reporting, link_Reporting_RateAuditReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MonthlyCNReport, link_Reporting, link_Reporting_MonthlyCNReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.PerDayPerIMSIReport, link_Reporting, link_Reporting_PerDayPerIMSIReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.CRDSyncReport, link_Reporting, link_Reporting_CRDSyncReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.HutchQlikviewReport, link_Reporting, link_Reporting_HutchQlikviewReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.HutchQlikviewAccrualsReport, link_Reporting, link_Reporting_HutchQlikviewAccrualsReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.TotalAndPremiumNumberVolumeAndTAPCharges, link_Reporting, link_Reporting_TotalAndPremiumNumberVolumeAndTAPCharges);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.AgreementCatalogueReport, link_Reporting, link_Reporting_AgreementCatalogueReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.OutstandingRebatesPayable, link_Reporting, link_Reporting_OutstandingRebatesPayable);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.OutstandingRebatesReceivable, link_Reporting, link_Reporting_OutstandingRebatesReceivable);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.SavingsPerAgreement, link_Reporting, link_Reporting_SavingsReport ,link_Reporting_SavingsReport_SavingsPerAgreement);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.SavingsPerCountry, link_Reporting, link_Reporting_SavingsReport , link_Reporting_SavingsReport_SavingsPerCountry);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.ChartCumulativeAnnualisedNetworkOutpaymentSavingsFromIOTs, link_Reporting, link_Reporting_SavingsReport , link_Reporting_SavingsReport_ChartCumulativeAnnualisedNetworkOutpaymentSavingsFromIOTs);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.PrePostMonthEndModule, link_Reporting, link_Reporting_PrePostMonthEndModule);
		com.click(link_Header_IOTRON);
	}

	public By link_Forecasting=By.xpath("//button[contains(normalize-space(),'Forecasting')]");

	public By link_Forecasting_DealTracker=By.xpath("//a[normalize-space()='Deal Tracker']");

	public By link_Forecasting_ForecastReportPerAgreement=By.xpath("//a[normalize-space()='Forecast Report per Agreement']");

	public By link_Forecasting_ForecastedTrafficVolumesSummaryReportPeriodGlobalCountryPMN=By.xpath("//a[normalize-space()='Forecasted Traffic Volumes Summary Report - Period, Global, Country & PMN']");

	public By link_Forecasting_FinancialReportForecastedTAPDiscountedCharges=By.xpath("//a[normalize-space()='Financial Report - Forecasted TAP & Discounted Charges']");

	public void verifyNavigationLinksFunctionality_Forecasting() {
		CustomReporter.createNode("Verifying Forecasting Module links");
		nav.traverseMenu_VerifyPageTitle(PagesTitle.DealTracker, link_Forecasting, link_Forecasting_DealTracker);
		com.click(link_Header_IOTRON);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.ForecastReportperAgreement, link_Forecasting, link_Forecasting_ForecastReportPerAgreement);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.ForecastedTrafficVolumesSummaryReportPeriodGlobalCountryPMN, link_Forecasting, link_Forecasting_ForecastedTrafficVolumesSummaryReportPeriodGlobalCountryPMN);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.FinancialReportForecastedTAPDiscountedCharges, link_Forecasting, link_Forecasting_FinancialReportForecastedTAPDiscountedCharges);
	}
	
	public By link_Settlement=By.xpath("//button[contains(normalize-space(),'Settlement')]");

	public By link_Settlement_GenerateSettlementStatement=By.xpath("//a[normalize-space()='Generate Settlement Statement']");

	public By link_Settlement_SettlementStatementHistory=By.xpath("//a[normalize-space()='Settlement Statement History']");

	public By link_Settlement_CreditDebitNoteReport=By.xpath("//a[normalize-space()='Credit/Debit Note Report']");
	
	public By link_Settlement_SettlementReconciler=By.xpath("//a[normalize-space()='Settlement Reconciler']");
	
	
	public void verifyNavigationLinksFunctionality_Settlement() {
		CustomReporter.createNode("Verifying Settlement links");
		nav.traverseMenu_VerifyPageTitle(PagesTitle.GenerateSettlementStatement, link_Settlement, link_Settlement_GenerateSettlementStatement);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.SettlementStatementHistory, link_Settlement, link_Settlement_SettlementStatementHistory);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.CreditDebitNoteReport, link_Settlement, link_Settlement_CreditDebitNoteReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.SettlementReconciler_P526, link_Settlement, link_Settlement_SettlementReconciler);
	}

	public By link_BusinessIntelligence=By.xpath("//button[contains(normalize-space(),'Business Intelligence')]");

	public By link_BusinessIntelligence_ActiveIOTDiscountPartnerMap=By.xpath("//a[normalize-space()='Active IOT Discount Partner Map']");

	public By link_BusinessIntelligence_TrafficVolumes=By.xpath("//button[contains(normalize-space(),'Traffic Volumes')]");

	public By link_BusinessIntelligence_TrafficVolumes_TotalTrafficVolumesByCountry=By.xpath("//a[normalize-space()='Total Traffic Volumes by Country']");

	public By link_BusinessIntelligence_TrafficVolumes_MonthlyTrafficVolumesByCountryPartner=By.xpath("//a[normalize-space()='Monthly Traffic Volumes by Country & Partner']");

	public By link_BusinessIntelligence_TrafficVolumes_MonthlyTrafficVolumesByPartner=By.xpath("//a[normalize-space()='Monthly Traffic Volumes by Partner']");

	public By link_BusinessIntelligence_TrafficVolumes_GlobalTrafficVolumesTop50Partners=By.xpath("//a[normalize-space()='Global Traffic Volumes (Top 50 Partners)']");

	public By link_BusinessIntelligence_PostDiscountReports=By.xpath("//button[contains(normalize-space(),'Post-Discount Reports')]");

	public By link_BusinessIntelligence_PostDiscountReports_Dashboard=By.xpath("//a[normalize-space()='Dashboard']");

	public By link_BusinessIntelligence_PostDiscountReports_AverageIOTChargesByCountryPartner=By.xpath("//a[normalize-space()='Average IOT Charges by Country & Partner']");

	public By link_BusinessIntelligence_PostDiscountReports_HeatMapAverageIOTChargePerCountry=By.xpath("//a[normalize-space()='Heat Map: Average IOT Charge per Country']");

	public By link_BusinessIntelligence_DCHLevelReports=By.xpath("//button[contains(normalize-space(),'DCH Level Reports')]");

	public By link_BusinessIntelligence_DCHLevelReports_MonthlyAverageIOTChargesByCountry=By.xpath("//a[normalize-space()='Monthly Average IOT Charges by Country']");

	public By link_BusinessIntelligence_DCHLevelReports_MonthlyAverageIOTChargesByCountryPartner=By.xpath("//a[normalize-space()='Monthly Average IOT Charges by Country & Partner']");

	public By link_BusinessIntelligence_DCHLevelReports_MonthlyAverageIOTChargesByPartner=By.xpath("//a[normalize-space()='Monthly Average IOT Charges by Partner']");

	public By link_BusinessIntelligence_DCHLevelReports_GlobalChargesTop50Partners=By.xpath("//a[normalize-space()='Global Charges (Top 50 Partners)']");

	public By link_BusinessIntelligence_DCHLevelReports_AverageIOTChargePerUnitByPartnerTop50Partners=By.xpath("//a[normalize-space()='Average IOT Charge per Unit by Partner (Top 50 Partners)']");

	public By link_BusinessIntelligence_DCHLevelReports_HeatMapAverageIOTChargesByCountryPartner=By.xpath("//a[normalize-space()='Heat Map: Average IOT Charges by Country & Partner']");

	public By link_BusinessIntelligence_DCHLevelReports_HeatMapGapsInServicesBasedOnMissingTraffic=By.xpath("//a[normalize-space()='Heat Map: Gaps in Services (Based on Missing Traffic)']");

	public By link_BusinessIntelligence_DailyDCHLevelReporting=By.xpath("//button[contains(normalize-space(),'Daily DCH level Reporting')]");

	public By link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficAnalysisReport=By.xpath("//a[normalize-space()='Daily Traffic Analysis Report']");

	public By link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficVolumesByPartner=By.xpath("//a[normalize-space()='Daily Traffic Volumes by Partner']");

	public By link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficVolumesByCountryPartner=By.xpath("//a[normalize-space()='Daily Traffic Volumes by Country & Partner']");

	public By link_BusinessIntelligence_AlertSubscriptionReports=By.xpath("//button[contains(normalize-space(),'Alert Subscription Reports')]");

	public By link_BusinessIntelligence_AlertSubscriptionReports_FxFluctuationReport=By.xpath("//a[contains(normalize-space(),'Fx fluctuation report')]");
	
	public By link_BusinessIntelligence_AlertSubscriptionReports_TrafficTrendReport=By.xpath("//a[contains(normalize-space(),'Traffic trend report')]");
		
	public void verifyNavigationLinksFunctionality_BusinessIntelligence() {
		CustomReporter.createNode("Verifying Business Intelligence Module links");
		nav.traverseMenu_VerifyPageTitle(PagesTitle.ActiveIOTDiscountPartnerMap, link_BusinessIntelligence, link_BusinessIntelligence_ActiveIOTDiscountPartnerMap);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.TotalTrafficVolumesByCountry, link_BusinessIntelligence, link_BusinessIntelligence_TrafficVolumes, link_BusinessIntelligence_TrafficVolumes_TotalTrafficVolumesByCountry);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MonthlyTrafficVolumesByCountryPartner, link_BusinessIntelligence, link_BusinessIntelligence_TrafficVolumes, link_BusinessIntelligence_TrafficVolumes_MonthlyTrafficVolumesByCountryPartner);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MonthlyTrafficVolumesByPartner, link_BusinessIntelligence, link_BusinessIntelligence_TrafficVolumes, link_BusinessIntelligence_TrafficVolumes_MonthlyTrafficVolumesByPartner);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.GlobalTrafficVolumesTop50Partners, link_BusinessIntelligence, link_BusinessIntelligence_TrafficVolumes, link_BusinessIntelligence_TrafficVolumes_GlobalTrafficVolumesTop50Partners);

		nav.traverseMenu_VerifyPageTitle(PagesTitle.Dashboard, link_BusinessIntelligence, link_BusinessIntelligence_PostDiscountReports, link_BusinessIntelligence_PostDiscountReports_Dashboard);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.AverageIOTChargesByCountryPartner, link_BusinessIntelligence, link_BusinessIntelligence_PostDiscountReports, link_BusinessIntelligence_PostDiscountReports_AverageIOTChargesByCountryPartner);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.HeatMapAverageIOTChargePerCountry, link_BusinessIntelligence, link_BusinessIntelligence_PostDiscountReports, link_BusinessIntelligence_PostDiscountReports_HeatMapAverageIOTChargePerCountry);

		nav.traverseMenu_VerifyPageTitle(PagesTitle.MonthlyAverageIOTChargesByCountry, link_BusinessIntelligence, link_BusinessIntelligence_DCHLevelReports, link_BusinessIntelligence_DCHLevelReports_MonthlyAverageIOTChargesByCountry);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MonthlyAverageIOTChargesByCountryPartner, link_BusinessIntelligence, link_BusinessIntelligence_DCHLevelReports, link_BusinessIntelligence_DCHLevelReports_MonthlyAverageIOTChargesByCountryPartner);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MonthlyAverageIOTChargesByPartner, link_BusinessIntelligence, link_BusinessIntelligence_DCHLevelReports, link_BusinessIntelligence_DCHLevelReports_MonthlyAverageIOTChargesByPartner);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.GlobalChargesTop50Partners, link_BusinessIntelligence, link_BusinessIntelligence_DCHLevelReports, link_BusinessIntelligence_DCHLevelReports_GlobalChargesTop50Partners);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.AverageIOTChargePerUnitByPartnerTop50Partners, link_BusinessIntelligence, link_BusinessIntelligence_DCHLevelReports, link_BusinessIntelligence_DCHLevelReports_AverageIOTChargePerUnitByPartnerTop50Partners);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.HeatMapAverageIOTChargesByCountryPartner, link_BusinessIntelligence, link_BusinessIntelligence_DCHLevelReports, link_BusinessIntelligence_DCHLevelReports_HeatMapAverageIOTChargesByCountryPartner);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.HeatMapGapsInServicesBasedOnMissingTraffic, link_BusinessIntelligence, link_BusinessIntelligence_DCHLevelReports, link_BusinessIntelligence_DCHLevelReports_HeatMapGapsInServicesBasedOnMissingTraffic);
		

		nav.traverseMenu_VerifyPageTitle(PagesTitle.DailyTrafficAnalysisReport, link_BusinessIntelligence, link_BusinessIntelligence_DailyDCHLevelReporting, link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficAnalysisReport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.DailyTrafficVolumesByPartner, link_BusinessIntelligence, link_BusinessIntelligence_DailyDCHLevelReporting, link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficVolumesByPartner);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.DailyTrafficVolumesByCountryPartner, link_BusinessIntelligence, link_BusinessIntelligence_DailyDCHLevelReporting, link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficVolumesByCountryPartner);	
	}


	public By link_Operations=By.xpath("//button[contains(normalize-space(),'Operations')]");

	public By link_Operations_AuditReport=By.xpath("//a[normalize-space()='Audit Report']");

	public By link_Operations_AgreementSetUpErrors=By.xpath("//button[contains(normalize-space(),'Agreement Set-up Errors')]");

	public By link_Operations_AgreementSetUpErrors_OverlappingAgreements=By.xpath("//a[normalize-space()='Overlapping Agreements']");

	public By link_Operations_AgreementSetUpErrors_DiscountParameterNotDefinedForMonth=By.xpath("//a[normalize-space()='Discount parameter not defined for month']");

	public By link_Operations_AgreementSetUpErrors_DiscountValidOutsideAgreementDuration=By.xpath("//a[normalize-space()='Discount valid outside agreement duration']");

	public By link_Operations_AgreementSetUpErrors_MissingDiscountForTrafficRangeForSteppedTiered=By.xpath("//a[normalize-space()='Missing discount for traffic range for Stepped/Tiered']");

	public By link_Operations_AgreementSetUpErrors_SendOrPayTrafficCommitmentWithoutAdditionalDiscounts=By.xpath("//a[normalize-space()='Send or Pay Traffic Commitment without additional discount(s)']");

	public By link_Operations_AgreementSetUpErrors_SendOrPayFinancialCommitmentWithoutAdditionalDiscounts=By.xpath("//a[normalize-space()='Send or Pay Financial Commitment without additional discount(s)']");

	public By link_Operations_AgreementSetUpErrors_OverlappingDiscounts=By.xpath("//a[normalize-space()='Overlapping discounts']");

	public By link_Operations_AgreementSetUpErrors_InvisibleAgreements=By.xpath("//a[normalize-space()='Invisible agreements']");

	public By link_Operations_AgreementSetUpErrors_SendOrPayTrafficCommitmentWithoutLowerOrUpperBound=By.xpath("//a[normalize-space()='Send or Pay Traffic Commitment without lower or upper bound']");

	public By link_Operations_DataLoading=By.xpath("//button[contains(normalize-space(),'Data Loading')]");

	public By link_Operations_DataLoading_LoadIOTMFSData=By.xpath("//a[normalize-space()='Load IOT MFS Data']");

	public By link_Operations_DataLoading_ConfigureDataLoading=By.xpath("//a[normalize-space()='Configure Data Loading']");

	public By link_Operations_DataLoading_IOTDataPurge=By.xpath("//a[normalize-space()='IOT Data Purge']");

	public By link_Operations_DataLoading_LoadIOTReferenceData=By.xpath("//a[normalize-space()='Load IOT Reference Data']");

	public By link_Operations_DataLoading_IOTDiscountCalculationProgress=By.xpath("//a[normalize-space()='IOT Discount Calculation Progress']");

	public By link_Operations_DataLoading_LoadClientGeneratedForecastTrafficVolumes=By.xpath("//a[normalize-space()='Load Client Generated Forecast Traffic Volumes']");

	public By link_Operations_DataLoading_IOTDiscountCalculations=By.xpath("//a[normalize-space()='IOT Discount Calculations']");

	public By link_Operations_DataLoading_DataLoadingAdministration=By.xpath("//a[normalize-space()='Data Loading Administration']");

	public By link_Operations_DataLoading_CalculationSchedule_P689=By.xpath("//a[normalize-space()='Calculation Schedule']");

	public By link_Operations_DataLoading_DCHreport=By.xpath("//a[normalize-space()='DCH report']");

	public By link_Operations_DataLoading_ParallelQueueMonitor=By.xpath("//a[normalize-space()='Parallel Queue Monitor']");

	public By link_Operations_IOTRONUserConfiguration=By.xpath("//button[contains(normalize-space(),'IOTRON User Configuration')]");

	public By link_Operations_IOTRONUserConfiguration_CreateEditTeams=By.xpath("//a[normalize-space()='Create/Edit Teams']");

	public By link_Operations_IOTRONUserConfiguration_ContactRoles=By.xpath("//a[normalize-space()='Contact Roles']");

	public By link_Operations_IOTRONUserConfiguration_ContactRoleTeamAssignment=By.xpath("//a[normalize-space()='Contact Role & Team Assignment']");

	public By link_Operations_IOTRONUserConfiguration_UserFunctionAssignment=By.xpath("//a[normalize-space()='User Function Assignment']");

	public By link_Operations_IOTRONUserConfiguration_QuickUsersRolesManagement=By.xpath("//a[normalize-space()='Quick Users & Roles Management']");

	public By link_Operations_IOTRONUserConfiguration_UserDefinedAlerts=By.xpath("//a[normalize-space()='User Defined Alerts']");

	public By link_Operations_IOTRONUserConfiguration_CreateEditClientCustomChecklist=By.xpath("//a[normalize-space()='Create/Edit Client Custom Checklist']");
	
	public By link_Operations_IOTRONUserConfiguration_AlertSubscription=By.xpath("//a[normalize-space()='Alert Subscription']");
	
	public By link_Operations_TestingSuite=By.xpath("//button[contains(normalize-space(),'Testing Suite')]");
	
	public By link_Operations_TestingSuite_MaintainAgreementSet=By.xpath("//a[normalize-space()='Maintain Agreement Set']");
	
	public By link_Operations_TestingSuite_CalculationStatus=By.xpath("//a[normalize-space()='Calculation Status']");

	public By link_Operations_TestingSuite_LatestandOriginalDeltaReport=By.xpath("//a[normalize-space()='Latest and Original Delta Report']");

	public By link_Operations_TestingSuite_ForecastReportperAgreement=By.xpath("//a[normalize-space()='Forecast Report per Agreement']");
	
	public By link_Operations_TestingSuite_DeltaReportAdministration=By.xpath("//a[normalize-space()='Delta Report Administration']");
	
	public By link_AgreementCapture_MaintainTrafficSegmentationRules_P35 = By.xpath("//a[normalize-space()='Maintain Traffic Segmentation Rules']");

	public void verifyNavigationLinksFunctionality_Operations() {
		CustomReporter.createNode("Verifying Operations Module links");
		nav.traverseMenu_VerifyPageTitle(PagesTitle.AuditReport, link_Operations, link_Operations_AuditReport);
		CustomReporter.report(STATUS.WARNING, "Operations > Agreement Set-up Errors > Overlapping Agreements link is not working, when scripting");
		nav.traverseMenu_VerifyPageTitle(PagesTitle.DiscountParameterNotDefinedForMonth, link_Operations, link_Operations_AgreementSetUpErrors,link_Operations_AgreementSetUpErrors_DiscountParameterNotDefinedForMonth);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.DiscountValidOutsideAgreementDuration, link_Operations, link_Operations_AgreementSetUpErrors,link_Operations_AgreementSetUpErrors_DiscountValidOutsideAgreementDuration);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MissingDiscountForTrafficRangeForSteppedTiered, link_Operations, link_Operations_AgreementSetUpErrors,link_Operations_AgreementSetUpErrors_MissingDiscountForTrafficRangeForSteppedTiered);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.SendOrPayTrafficCommitmentWithoutAdditionalDiscounts, link_Operations, link_Operations_AgreementSetUpErrors,link_Operations_AgreementSetUpErrors_SendOrPayTrafficCommitmentWithoutAdditionalDiscounts);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.SendOrPayFinancialCommitmentWithoutAdditionalDiscounts, link_Operations, link_Operations_AgreementSetUpErrors,link_Operations_AgreementSetUpErrors_SendOrPayFinancialCommitmentWithoutAdditionalDiscounts);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.OverlappingDiscounts, link_Operations, link_Operations_AgreementSetUpErrors,link_Operations_AgreementSetUpErrors_OverlappingDiscounts);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.InvisibleAgreements, link_Operations, link_Operations_AgreementSetUpErrors,link_Operations_AgreementSetUpErrors_InvisibleAgreements);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.SendOrPayTrafficCommitmentWithoutLowerOrUpperBound, link_Operations, link_Operations_AgreementSetUpErrors,link_Operations_AgreementSetUpErrors_SendOrPayTrafficCommitmentWithoutLowerOrUpperBound);
		

		nav.traverseMenu_VerifyPageTitle(PagesTitle.LoadIOTMFSData, link_Operations, link_Operations_DataLoading,link_Operations_DataLoading_LoadIOTMFSData);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.ConfigureDataLoading, link_Operations, link_Operations_DataLoading,link_Operations_DataLoading_ConfigureDataLoading);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.IOTDataPurge, link_Operations, link_Operations_DataLoading,link_Operations_DataLoading_IOTDataPurge);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.LoadIOTReferenceData, link_Operations, link_Operations_DataLoading,link_Operations_DataLoading_LoadIOTReferenceData);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.IOTDiscountCalculationProgress, link_Operations, link_Operations_DataLoading,link_Operations_DataLoading_IOTDiscountCalculationProgress);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.LoadClientGeneratedForecastTrafficVolumes, link_Operations, link_Operations_DataLoading,link_Operations_DataLoading_LoadClientGeneratedForecastTrafficVolumes);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.IOTDiscountCalculations, link_Operations, link_Operations_DataLoading,link_Operations_DataLoading_IOTDiscountCalculations);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.DataLoadingAdministration, link_Operations, link_Operations_DataLoading,link_Operations_DataLoading_DataLoadingAdministration);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.CalculationSchedule, link_Operations, link_Operations_DataLoading,link_Operations_DataLoading_CalculationSchedule_P689);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.DCHReport, link_Operations, link_Operations_DataLoading,link_Operations_DataLoading_DCHreport);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.ParallelQueueMonitor, link_Operations, link_Operations_DataLoading,link_Operations_DataLoading_ParallelQueueMonitor);

		nav.traverseMenu_VerifyPageTitle(PagesTitle.CreateEditTeams, link_Operations, link_Operations_IOTRONUserConfiguration, link_Operations_IOTRONUserConfiguration_CreateEditTeams);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.ContactRoles, link_Operations, link_Operations_IOTRONUserConfiguration, link_Operations_IOTRONUserConfiguration_ContactRoles);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.ContactRoleTeamAssignment, link_Operations, link_Operations_IOTRONUserConfiguration, link_Operations_IOTRONUserConfiguration_ContactRoleTeamAssignment);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.UserFunctionAssignment, link_Operations, link_Operations_IOTRONUserConfiguration, link_Operations_IOTRONUserConfiguration_UserFunctionAssignment);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.QuickUsersRolesManagement, link_Operations, link_Operations_IOTRONUserConfiguration, link_Operations_IOTRONUserConfiguration_QuickUsersRolesManagement);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.UserDefinedAlerts, link_Operations, link_Operations_IOTRONUserConfiguration, link_Operations_IOTRONUserConfiguration_UserDefinedAlerts);
		nav.traverseMenu_VerifyPageTitle(PagesTitle.CreateEditClientCustomChecklist, link_Operations, link_Operations_IOTRONUserConfiguration, link_Operations_IOTRONUserConfiguration_CreateEditClientCustomChecklist);
		
	}
}
