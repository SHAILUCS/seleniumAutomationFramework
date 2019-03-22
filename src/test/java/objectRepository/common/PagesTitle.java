package objectRepository.common;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This enum is created for storing the appication pages name and title constants
 * */
public enum PagesTitle {
	
	//TODO IOTRON System Administration Module 
	MaintainIOTClients_P310("Maintain IOT Clients"),
	QueryIOTSummary("Query IOT Summary"),
	MaintainOperatorsHavingMoreThanOnePMNcode_P479("Maintain Operators having more than one PMN code"),
	CreateEditOperatorHavingMoreThanOnePmnCode_P495("Create / Edit Operator having more than one PMN code"),
	BIDashboardParameters("BI Dashboard Parameters"),
	IOTRONAuditReportIOTAgreements("Audit Report"),
	IotronAuditReportCalculations("Iotron Audit Report (Calculations)"),
	InternalInvoicingReport("Internal Invoicing Report"),
	MaintainPersonalFavouritesPages("Maintain Personal Favourites Pages"),
	MaintainSettlementgrouping("Maintain Settlement grouping"),
	IOTRONSettlementStatementDataCorrection("IOTRON Settlement Statement Data Correction"),
	MaintainClientDefinedCountries("Maintain Client Defined Countries"),
	MaintainClientDefinedCountriesMapping("Maintain Client Defined Countries Mapping"),
	MaintainClientAgreementPhase("Maintain Client Agreement Phase"),
	MaintainClientJobTitle("Maintain Client Job Title"),
	CreateEditClientJobTitle("Create/Edit Client Job Title"),

	//TODO Agreement Capture Module
	MaintainIOTDiscountAgreements_P301("Maintain IOT Discount Agreements"),
	MaintainIOTDiscountPartyDetails("Maintain IOT Discount Party Details"),
	MaintainIOTOperatorGroups("Maintain IOT Operator Groups"),
	AgreementChecklistReview("Agreement Checklist Review"),
	ZoneDefinition("Zone Definition"),
	SOXReport("SOX Report"),
	ValueCommitmentFlexing("Value Commitment Flexing"),
	IOTAgreementDocuments_P419("IOT Agreement Documents"),
	AddIOTAgreementDocument_P414("Add IOT Agreement Document"),
	UpdateSignatureTracker_P180("Update Signature Tracker"),
	UpdateIOTAgreementChecklistItem_P338("Update IOT Agreement Checklist Item"),
	EditAgreementNote_P461("Edit Agreement Note"),
	CreateEditTrafficSplitLevels_P430("Create/Edit Traffic Split Levels"),
	CreateEditRoundingCorrectionFactors_P450("Create/Edit Rounding & Correction Factors"),
	CreateEditIOTManualForecast_P159("Create/Edit IOT Manual Forecast"),
	AgreementDetails_P16("Agreement Details"),
	
	//TODO Reporting Module
	CustomisedReportsTMO("IOTRON US Instance"),
	AgreementPerformanceReportCreditNote("Agreement Performance Report Credit Note"),
	AgreementPerformanceReportCreditNoteSingleMonth("Agreement Performance Report Credit Note (Single Month)"),
	RODATReportFileExport("RODAT Report File Export"),
	IntraGroupReporting("Intra Group Reporting"),
	CommitmentReport_P492("Commitment Report"),
	OverviewReport_P507("Overview Report"),
	ThresholdTrackerMatrixReport("Threshold Tracker Matrix Report"),
	TrafficDistributionandThresholdTrackerReport("Traffic Distribution and Threshold Tracker Report"),
	IntraGroupEqualizationReport("Intra Group Equalization Report"),
	CustomerOutboundRoamingMVNOSplitReport("Customer/Outbound Roaming MVNO Split Report"),
	RateAuditReport("Rate Audit Report"),
	MonthlyCNReport("Monthly CN report"),
	PerDayPerIMSIReport("Per Day Per IMSI Report"),
	CRDSyncReport("CRD Sync Report"),
	HutchQlikviewReport("Hutch Qlikview Report"),
	HutchQlikviewAccrualsReport("Hutch Qlikview Accruals Report"),
	TotalAndPremiumNumberVolumeAndTAPCharges("Total and Premium Number Volume and TAP Charges"),
	AgreementCatalogueReport("Agreement Catalogue Report"),
	OutstandingRebatesPayable("Outstanding Rebates"),
	OutstandingRebatesReceivable("Outstanding Rebates"),
	//TODO Savings Report Submenu
	SavingsPerAgreement("Savings per Agreement"),
	SavingsPerCountry("Savings per Country"),
	ChartCumulativeAnnualisedNetworkOutpaymentSavingsFromIOTs("Chart Cumulative Annualised Network Outpayment Savings from IOTs"),
	PrePostMonthEndModule("Welcome"),
	AccrualReport_P25("Accrual Report"),

	//TODO Forecasting Module
	DealTracker("Welcome"),
	ForecastReportperAgreement("Forecast Report per Agreement"),
	ForecastedTrafficVolumesSummaryReportPeriodGlobalCountryPMN("Forecasted Traffic Volumes Summary Report - Period, Global, Country & PMN"),
	FinancialReportForecastedTAPDiscountedCharges("Financial Report - Forecasted TAP & Discounted Charges"),

	//TODO Business Intelligence Module
	ActiveIOTDiscountPartnerMap("Active IOT Discount Partner Map"),
	FxFluctuationReport_P28("Fx fluctuation report"),
	TrafficTrend_P37("Traffic trend"),
	
	//TODO Traffic Volumes Submenu
	TotalTrafficVolumesByCountry("Total Traffic Volumes by Country"),
	MonthlyTrafficVolumesByCountryPartner("Monthly Traffic Volumes by Country & Partner"),
	MonthlyTrafficVolumesByPartner("Monthly Traffic Volumes by Partner"),
	GlobalTrafficVolumesTop50Partners("Global Traffic Volumes (Top 50 Partners)"),
	//TODO Post Discount Report Submenu
	Dashboard("Dashboard"),
	AverageIOTChargesByCountryPartner("Average IOT Charges by Country & Partner"),
	HeatMapAverageIOTChargePerCountry("Heat Map: Average IOT Charge per Country"),
	//TODO DCH Level Reports Submenu
	MonthlyAverageIOTChargesByCountry("Monthly Average IOT Charges by Country"),
	MonthlyAverageIOTChargesByCountryPartner("Monthly Average IOT Charges by Country & Partner"),
	MonthlyAverageIOTChargesByPartner("Monthly Average IOT Charges by Partner"),
	GlobalChargesTop50Partners("Global Charges (Top 50 Partners)"),
	AverageIOTChargePerUnitByPartnerTop50Partners("Average IOT Charge per Unit by Partner (Top 50 Partners)"),
	HeatMapAverageIOTChargesByCountryPartner("Heat Map: Average IOT Charges by Country & Partner"),
	HeatMapGapsInServicesBasedOnMissingTraffic("Heat Map: Gaps in Services (Based on Missing Traffic)"),
	//TODO Daily DCH Level Reporting Submenu
	DailyTrafficAnalysisReport("Daily Traffic Analysis Report"),
	DailyTrafficVolumesByPartner("Daily Traffic Volumes by Partner"),
	DailyTrafficVolumesByCountryPartner("Daily Traffic Volumes By Country & Partner"),

	//TODO Settlement module
	GenerateSettlementStatement("Generate Settlement Statement"),
	SettlementStatementHistory("Settlement Statement History"),
	CreditDebitNoteReport("Credit/Debit Note Report"),
	SettlementReconciler_P526("Settlement Reconciler"),

	//TODO Operations Module 
	AuditReport("Audit Report"),
	AlertSubscription_P26("Alert Subscription"),
	
	//TODO Agreement Set - up Errors submenu
	OverlappingAgreements(""),
	DiscountParameterNotDefinedForMonth("Discount parameter not defined for month"),
	DiscountValidOutsideAgreementDuration("Discount valid outside agreement duration"),
	MissingDiscountForTrafficRangeForSteppedTiered("Missing discount for traffic range for Stepped/Tiered"),
	SendOrPayTrafficCommitmentWithoutAdditionalDiscounts("Send or Pay Traffic Commitment without additional discount(s)"),
	SendOrPayFinancialCommitmentWithoutAdditionalDiscounts("Send or Pay Financial Commitment without additional discount(s)"),
	OverlappingDiscounts("Overlapping discounts"),
	InvisibleAgreements("Invisible agreements"),
	SendOrPayTrafficCommitmentWithoutLowerOrUpperBound("Send or Pay Traffic Commitment without lower or upper bound"),
	//TODO Data Loading Submenu
	LoadIOTMFSData("Load IOT MFS Data"),
	ConfigureDataLoading("Configure Data Loading"),
	IOTDataPurge("IOT Data Purge"),
	LoadIOTReferenceData("Load IOT Reference Data"),
	IOTDiscountCalculationProgress("IOT Discount Calculation Progress"),
	LoadClientGeneratedForecastTrafficVolumes("Load Client Generated Forecast Traffic Volumes"),
	IOTDiscountCalculations("IOT Discount Calculations"),
	DataLoadingAdministration("Data Loading Administration"),
	CalculationSchedule("Calculation Schedule"),
	DCHReport("DCH Report"),
	ParallelQueueMonitor("Parallel Queue Monitor"),
	
	//TODO IOTRON User Configuration Submenu
	CreateEditTeams("Create/Edit Teams"),
	ContactRoles("Contact Roles"),
	ContactRoleTeamAssignment("Contact Role & Team Assignment"),
	UserFunctionAssignment("User Function Assignment"),
	QuickUsersRolesManagement("Quick Users & Roles Management"),
	UserDefinedAlerts("User Defined Alerts"),
	CreateEditClientCustomChecklist("Create/Edit Client Custom Checklist"), 
	ConfigureIOTDiscountAgreementsforCalculationofCumulativeAnnualisedNetworkOutpaymentSavings("Configure IOT Discount Agreements for Calculation of Cumulative Annualised Network Outpayment Savings"),
	FxFluctuation_P27("Fx Fluctuation"),
	TrafficTrend_P31("Traffic trend"),
	
	//TODO Testing Suite SubMenu
	MaintainAgreementSet_P4("Maintain Agreement Set"),
	CalculationStatus_P5("Calculation Status"),
	LatestAndOriginalDeltaReport_P9("Latest and Original Delta Report"),
	ForecastReportPerAgreement_P639("Forecast Report per Agreement"),
	DeltaReportAdministration_P10("Delta Report Administration"),
	
	//TODO FCH Administration Module
	SystemUsers("System Users"),
	ManageIOTAddendums("Manage IOT Addendums"),
	AddEditGroupMemberAssociation_P356("Add/Edit Group Member Association"),
	
	//TODO TMO Module
	TAPAccrualActualTrueUp("TAP Accrual, Actual & True Up"), 
	SDRConversionRates_P392("SDR Conversion Rates"), MaintainTrafficSegmentationRules_P35("Maintain Traffic Segmentation Rules"),              
	
	;

	public String title;
	private PagesTitle(String value){
		this.title=value;
	}

	public static ArrayList<String> getValues(){
		ArrayList<String> list=new ArrayList<>();
		for (PagesTitle item : PagesTitle.values()) {
			list.add(item.title);
		}
		return list;
	} 
	
	public static HashMap<String,String> getMap(){
		PagesTitle[] allConstants = PagesTitle.class.getEnumConstants();
		HashMap<String,String> map=new HashMap<String, String>();
		
		for (PagesTitle constant : allConstants) {
			map.put(constant.toString(),constant.title);
		}
		
		return map;
	}
}
