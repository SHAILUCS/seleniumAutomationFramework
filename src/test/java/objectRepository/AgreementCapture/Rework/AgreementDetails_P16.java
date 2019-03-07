package objectRepository.AgreementCapture.Rework;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;



public class AgreementDetails_P16 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.AgreementDetails_P16.title;

	public AgreementDetails_P16() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//button[.='Cancel']")
	private WebElement button_Cancel;

	@FindBy(xpath = "//span[contains(.,'Agreement Details')]")
	private WebElement heading_AgreementDetailsActive;

	@FindBy(xpath = "//span[contains(.,'Discount Parameters')]")
	private WebElement heading_DiscountParameters;

	@FindBy(xpath = "//span[contains(.,'Settlement Details')]")
	private WebElement heading_SettlementDetails;

	@FindBy(xpath = "//button[.='Next']")
	private WebElement button_Next;

	@FindBy(xpath = "//h2[.='Agreement Parties']")
	private WebElement section_AgreementParties;

	@FindBy(id = "P16_HOME_GROUP")
	private WebElement select_AvailableHomeGroups;

	@FindBy(id = "P16_PARTNER_GROUP")
	private WebElement select_AvailablePartnerGroups;

	@FindBy(id = "P16_CLIENT_PARTIES_LEFT")
	private WebElement select_HomeParties_Excluded;

	@FindBy(id = "P16_CLIENT_PARTIES_RIGHT")
	private WebElement select_HomeParties_Included;

	@FindBy(id = "P16_PARTNER_PARTIES_LEFT")
	private WebElement select_PartnerParties_Excluded;

	@FindBy(id = "P16_PARTNER_PARTIES_RIGHT")
	private WebElement select_PartnerParties_Included;

	@FindBy(id = "P16_PARTNER_FILTER")
	private WebElement text_PartnerFilter;

	@FindBy(xpath = "//h2[.='Agreement terms and parameters']")
	private WebElement section_AgreementTermsAndParameters;

	@FindBy(id = "P16_IOT_ACR_TEMPLATE_ID")
	private WebElement select_AgreementReferenceTemplate;

	@FindBy(id = "P16_CLIENT_AGREEMENT_REF")
	private WebElement text_AgreementReference;

	@FindBy(id = "P16_CLL_DATE")
	private WebElement text_CommercialLaunchLetterDate;

	@FindBy(id = "P16_LEGAL_CONTRACT_ID")
	private WebElement text_LegalContractID;

	@FindBy(id = "P16_AGREEMENT_START_DATE")
	private WebElement text_AgreementStartDate;

	@FindBy(id = "P16_AGREEMENT_END_DATE")
	private WebElement text_AgreementEndDate;

	@FindBy(id = "P16_DISC_AGRMT_CURR")
	private WebElement select_DiscountAgreementCurrency;

	@FindBy(id = "P16_IOT_AGREEMENT_PSS_ID")
	private WebElement select_AgreementStatus;

	@FindBy(xpath = "//h2[.='Additional parameters']")
	private WebElement section_Additionalparameters;

	@FindBy(id = "P16_IS_ROLLING_AGREEMENT_0")
	private WebElement checkbox_IsRollingAgreement;

	@FindBy(id = "P16_INCLUDE_SATELLITE_0")
	private WebElement checkbox_IncludeSatellite;

	@FindBy(id = "P16_EXCLUDE_PREMIUM_NUM_0")
	private WebElement checkbox_PremiumNumbersAtDiscountRate;

	@FindBy(id = "P16_M2M_PSS_ID")
	private WebElement select_M2MStatus;

	@FindBy(id = "P16_DO_NOT_CALCULATE_0")
	private WebElement checkbox_DoNotCalculate;

	@FindBy(id = "P16_AGREEMENT_TYPE_ID")
	private WebElement select_AgreementType;

	@FindBy(id = "P16_HOME_GROUP_AGREEMENT_0")
	private WebElement checkbox_HomeGroupAgreement;

	@FindBy(id = "P16_IOT_DISCOUNT_TYPE_PSS_ID")
	private WebElement select_DiscountType;

	@FindBy(id = "P16_NEGOTIATOR_CONTACT_ID")
	private WebElement select_HomeAgreementNegotiator;

	@FindBy(id = "P16_PNR_NEGOTIATOR_CONTACT_ID")
	private WebElement select_PartnerAgreementNegotiator;

	@FindBy(id = "P16_IOT_FORECAST_TYPE_ID")
	private WebElement select_ForecastMethod;

	@FindBy(id = "P16_APPLICABLE_LAW_COUNTRY_ID")
	private WebElement select_ApplicableLawCountry;

	@FindBy(id = "P16_TERMINATION_NOTICE_PERIOD")
	private WebElement text_TerminationNoticePeriodDays;

	@FindBy(id = "P16_DISPUTE_PERIOD_DAYS")
	private WebElement text_DisputePeriodDays;

	@FindBy(id = "P16_RENEGOTIATION_NOTICE_DAYS")
	private WebElement text_RenegotiationNoticeDays;

	@FindBy(id = "P16_EAE_DATE")
	private WebElement text_EAEDate;

	@FindBy(id = "P16_AGREEMENT_DATE")
	private WebElement text_AgreementSignedDate;

	@FindBy(id = "P16_NEXT_ACTION")
	private WebElement select_NextAction;

	@FindBy(id = "P16_CFTNP_FLAG_0")
	private WebElement checkbox_ContinuationOfFixedTermAgreementNotPlanned;

	public void verify_UI_AUSTA() {
		CustomReporter.createNode("Top Header Section");
		com.isElementPresent(button_Cancel, "button_Cancel");
		com.isElementPresent(heading_AgreementDetailsActive, "heading_AgreementDetailsActive");
		com.isElementPresent(heading_DiscountParameters, "heading_DiscountParameters");
		com.isElementPresent(heading_SettlementDetails, "heading_SettlementDetails");
		com.isElementPresent(button_Next, "button_Next");

		CustomReporter.createNode("Agreement Parties Section");
		com.isElementPresent(section_AgreementParties, "section_AgreementParties");
		com.isElementPresent(select_AvailableHomeGroups, "select_AvailableHomeGroups");
		com.isElementPresent(select_AvailablePartnerGroups, "select_AvailablePartnerGroups");
		com.isElementPresent(select_HomeParties_Excluded, "select_HomeParties_Excluded");
		com.isElementPresent(select_HomeParties_Included, "select_HomeParties_Included");
		com.isElementPresent(select_PartnerParties_Excluded, "select_PartnerParties_Excluded");
		com.isElementPresent(select_PartnerParties_Included, "select_PartnerParties_Included");
		com.isElementPresent(text_PartnerFilter, "text_PartnerFilter");

		CustomReporter.createNode("Agreement Terms And Parameters Section");
		com.isElementPresent(section_AgreementTermsAndParameters, "section_AgreementTermsAndParameters");
		com.isElementPresent(select_AgreementReferenceTemplate, "select_AgreementReferenceTemplate");
		com.isElementPresent(text_AgreementReference, "text_AgreementReference");
		//com.isElementPresent(text_CommercialLaunchLetterDate, "text_CommercialLaunchLetterDate");
		//com.isElementPresent(text_LegalContractID, "text_LegalContractID");
		com.isElementPresent(text_AgreementStartDate, "text_AgreementStartDate");
		com.isElementPresent(text_AgreementEndDate, "text_AgreementEndDate");
		com.isElementPresent(select_DiscountAgreementCurrency, "select_DiscountAgreementCurrency");
		com.isElementPresent(select_AgreementStatus, "select_AgreementStatus");

		CustomReporter.createNode("Additional Parameters Section");
		com.isElementPresent(section_Additionalparameters, "section_Additionalparameters");
		com.isElementPresent(checkbox_IsRollingAgreement, "checkbox_IsRollingAgreement");
		com.isElementPresent(checkbox_IncludeSatellite, "checkbox_IncludeSatellite");
		com.isElementPresent(checkbox_PremiumNumbersAtDiscountRate, "checkbox_PremiumNumbersAtDiscountRate");
		//com.isElementPresent(select_M2MStatus, "select_M2MStatus");
		com.isElementPresent(checkbox_DoNotCalculate, "checkbox_DoNotCalculate");
		com.isElementPresent(select_AgreementType, "select_AgreementType");
		//com.isElementPresent(checkbox_HomeGroupAgreement, "checkbox_HomeGroupAgreement");
		//com.isElementPresent(select_DiscountType, "select_DiscountType");
		com.isElementPresent(select_HomeAgreementNegotiator, "select_HomeAgreementNegotiator");
		com.isElementPresent(select_PartnerAgreementNegotiator, "select_PartnerAgreementNegotiator");
		com.isElementPresent(select_ForecastMethod, "select_ForecastMethod");
		com.isElementPresent(select_ApplicableLawCountry, "select_ApplicableLawCountry");
		com.isElementPresent(text_TerminationNoticePeriodDays, "text_TerminationNoticePeriodDays");
		com.isElementPresent(text_DisputePeriodDays, "text_DisputePeriodDays");
		com.isElementPresent(text_RenegotiationNoticeDays, "text_RenegotiationNoticeDays");
		//com.isElementPresent(text_EAEDate, "text_EAEDate");
		com.isElementPresent(text_AgreementSignedDate, "text_AgreementSignedDate");
		com.isElementPresent(select_NextAction, "select_NextAction");
		com.isElementPresent(checkbox_ContinuationOfFixedTermAgreementNotPlanned,
				"checkbox_ContinuationOfFixedTermAgreementNotPlanned");
	}

	public void verify_UI_SWEHU() {
		CustomReporter.createNode("Top Header Section");
		com.isElementPresent(button_Cancel, "button_Cancel");
		com.isElementPresent(heading_AgreementDetailsActive, "heading_AgreementDetailsActive");
		com.isElementPresent(heading_DiscountParameters, "heading_DiscountParameters");
		com.isElementPresent(heading_SettlementDetails, "heading_SettlementDetails");
		com.isElementPresent(button_Next, "button_Next");

		CustomReporter.createNode("Agreement Parties Section");
		com.isElementPresent(section_AgreementParties, "section_AgreementParties");
		com.isElementPresent(select_AvailableHomeGroups, "select_AvailableHomeGroups");
		com.isElementPresent(select_AvailablePartnerGroups, "select_AvailablePartnerGroups");
		com.isElementPresent(select_HomeParties_Excluded, "select_HomeParties_Excluded");
		com.isElementPresent(select_HomeParties_Included, "select_HomeParties_Included");
		com.isElementPresent(select_PartnerParties_Excluded, "select_PartnerParties_Excluded");
		com.isElementPresent(select_PartnerParties_Included, "select_PartnerParties_Included");
		com.isElementPresent(text_PartnerFilter, "text_PartnerFilter");

		CustomReporter.createNode("Agreement Terms And Parameters Section");
		com.isElementPresent(section_AgreementTermsAndParameters, "section_AgreementTermsAndParameters");
		com.isElementPresent(select_AgreementReferenceTemplate, "select_AgreementReferenceTemplate");
		com.isElementPresent(text_AgreementReference, "text_AgreementReference");
		//com.isElementPresent(text_CommercialLaunchLetterDate, "text_CommercialLaunchLetterDate");
		com.isElementPresent(text_LegalContractID, "text_LegalContractID");
		com.isElementPresent(text_AgreementStartDate, "text_AgreementStartDate");
		com.isElementPresent(text_AgreementEndDate, "text_AgreementEndDate");
		com.isElementPresent(select_DiscountAgreementCurrency, "select_DiscountAgreementCurrency");
		com.isElementPresent(select_AgreementStatus, "select_AgreementStatus");

		CustomReporter.createNode("Additional Parameters Section");
		com.isElementPresent(section_Additionalparameters, "section_Additionalparameters");
		com.isElementPresent(checkbox_IsRollingAgreement, "checkbox_IsRollingAgreement");
		com.isElementPresent(checkbox_IncludeSatellite, "checkbox_IncludeSatellite");
		com.isElementPresent(checkbox_PremiumNumbersAtDiscountRate, "checkbox_PremiumNumbersAtDiscountRate");
		//com.isElementPresent(select_M2MStatus, "select_M2MStatus");
		com.isElementPresent(checkbox_DoNotCalculate, "checkbox_DoNotCalculate");
		com.isElementPresent(select_AgreementType, "select_AgreementType");
		com.isElementPresent(checkbox_HomeGroupAgreement, "checkbox_HomeGroupAgreement");
		//com.isElementPresent(select_DiscountType, "select_DiscountType");
		com.isElementPresent(select_HomeAgreementNegotiator, "select_HomeAgreementNegotiator");
		com.isElementPresent(select_PartnerAgreementNegotiator, "select_PartnerAgreementNegotiator");
		com.isElementPresent(select_ForecastMethod, "select_ForecastMethod");
		com.isElementPresent(select_ApplicableLawCountry, "select_ApplicableLawCountry");
		com.isElementPresent(text_TerminationNoticePeriodDays, "text_TerminationNoticePeriodDays");
		com.isElementPresent(text_DisputePeriodDays, "text_DisputePeriodDays");
		com.isElementPresent(text_RenegotiationNoticeDays, "text_RenegotiationNoticeDays");
		//com.isElementPresent(text_EAEDate, "text_EAEDate");
		com.isElementPresent(text_AgreementSignedDate, "text_AgreementSignedDate");
		com.isElementPresent(select_NextAction, "select_NextAction");
		com.isElementPresent(checkbox_ContinuationOfFixedTermAgreementNotPlanned,
				"checkbox_ContinuationOfFixedTermAgreementNotPlanned");
	}

	public void verify_UI_USAVZ() {
		CustomReporter.createNode("Top Header Section");
		com.isElementPresent(button_Cancel, "button_Cancel");
		com.isElementPresent(heading_AgreementDetailsActive, "heading_AgreementDetailsActive");
		com.isElementPresent(heading_DiscountParameters, "heading_DiscountParameters");
		com.isElementPresent(heading_SettlementDetails, "heading_SettlementDetails");
		com.isElementPresent(button_Next, "button_Next");

		CustomReporter.createNode("Agreement Parties Section");
		com.isElementPresent(section_AgreementParties, "section_AgreementParties");
		com.isElementPresent(select_AvailableHomeGroups, "select_AvailableHomeGroups");
		com.isElementPresent(select_AvailablePartnerGroups, "select_AvailablePartnerGroups");
		com.isElementPresent(select_HomeParties_Excluded, "select_HomeParties_Excluded");
		com.isElementPresent(select_HomeParties_Included, "select_HomeParties_Included");
		com.isElementPresent(select_PartnerParties_Excluded, "select_PartnerParties_Excluded");
		com.isElementPresent(select_PartnerParties_Included, "select_PartnerParties_Included");
		com.isElementPresent(text_PartnerFilter, "text_PartnerFilter");

		CustomReporter.createNode("Agreement Terms And Parameters Section");
		com.isElementPresent(section_AgreementTermsAndParameters, "section_AgreementTermsAndParameters");
		com.isElementPresent(select_AgreementReferenceTemplate, "select_AgreementReferenceTemplate");
		com.isElementPresent(text_AgreementReference, "text_AgreementReference");
		com.isElementPresent(text_CommercialLaunchLetterDate, "text_CommercialLaunchLetterDate");
		com.isElementPresent(text_LegalContractID, "text_LegalContractID");
		com.isElementPresent(text_AgreementStartDate, "text_AgreementStartDate");
		com.isElementPresent(text_AgreementEndDate, "text_AgreementEndDate");
		com.isElementPresent(select_DiscountAgreementCurrency, "select_DiscountAgreementCurrency");
		com.isElementPresent(select_AgreementStatus, "select_AgreementStatus");

		CustomReporter.createNode("Additional Parameters Section");
		com.isElementPresent(section_Additionalparameters, "section_Additionalparameters");
		com.isElementPresent(checkbox_IsRollingAgreement, "checkbox_IsRollingAgreement");
		com.isElementPresent(checkbox_IncludeSatellite, "checkbox_IncludeSatellite");
		com.isElementPresent(checkbox_PremiumNumbersAtDiscountRate, "checkbox_PremiumNumbersAtDiscountRate");
		com.isElementPresent(select_M2MStatus, "select_M2MStatus");
		com.isElementPresent(checkbox_DoNotCalculate, "checkbox_DoNotCalculate");
		com.isElementPresent(select_AgreementType, "select_AgreementType");
		//com.isElementPresent(checkbox_HomeGroupAgreement, "checkbox_HomeGroupAgreement");
		com.isElementPresent(select_DiscountType, "select_DiscountType");
		com.isElementPresent(select_HomeAgreementNegotiator, "select_HomeAgreementNegotiator");
		com.isElementPresent(select_PartnerAgreementNegotiator, "select_PartnerAgreementNegotiator");
		com.isElementPresent(select_ForecastMethod, "select_ForecastMethod");
		com.isElementPresent(select_ApplicableLawCountry, "select_ApplicableLawCountry");
		com.isElementPresent(text_TerminationNoticePeriodDays, "text_TerminationNoticePeriodDays");
		com.isElementPresent(text_DisputePeriodDays, "text_DisputePeriodDays");
		com.isElementPresent(text_RenegotiationNoticeDays, "text_RenegotiationNoticeDays");
		//com.isElementPresent(text_EAEDate, "text_EAEDate");
		com.isElementPresent(text_AgreementSignedDate, "text_AgreementSignedDate");
		com.isElementPresent(select_NextAction, "select_NextAction");
		com.isElementPresent(checkbox_ContinuationOfFixedTermAgreementNotPlanned,
				"checkbox_ContinuationOfFixedTermAgreementNotPlanned");
	}

	public void verify_UI_USAW6() {
		CustomReporter.createNode("Top Header Section");
		com.isElementPresent(button_Cancel, "button_Cancel");
		com.isElementPresent(heading_AgreementDetailsActive, "heading_AgreementDetailsActive");
		com.isElementPresent(heading_DiscountParameters, "heading_DiscountParameters");
		com.isElementPresent(heading_SettlementDetails, "heading_SettlementDetails");
		com.isElementPresent(button_Next, "button_Next");

		CustomReporter.createNode("Agreement Parties Section");
		com.isElementPresent(section_AgreementParties, "section_AgreementParties");
		com.isElementPresent(select_AvailableHomeGroups, "select_AvailableHomeGroups");
		com.isElementPresent(select_AvailablePartnerGroups, "select_AvailablePartnerGroups");
		com.isElementPresent(select_HomeParties_Excluded, "select_HomeParties_Excluded");
		com.isElementPresent(select_HomeParties_Included, "select_HomeParties_Included");
		com.isElementPresent(select_PartnerParties_Excluded, "select_PartnerParties_Excluded");
		com.isElementPresent(select_PartnerParties_Included, "select_PartnerParties_Included");
		com.isElementPresent(text_PartnerFilter, "text_PartnerFilter");

		CustomReporter.createNode("Agreement Terms And Parameters Section");
		com.isElementPresent(section_AgreementTermsAndParameters, "section_AgreementTermsAndParameters");
		com.isElementPresent(select_AgreementReferenceTemplate, "select_AgreementReferenceTemplate");
		com.isElementPresent(text_AgreementReference, "text_AgreementReference");
		//com.isElementPresent(text_CommercialLaunchLetterDate, "text_CommercialLaunchLetterDate");
		//com.isElementPresent(text_LegalContractID, "text_LegalContractID");
		com.isElementPresent(text_AgreementStartDate, "text_AgreementStartDate");
		com.isElementPresent(text_AgreementEndDate, "text_AgreementEndDate");
		com.isElementPresent(select_DiscountAgreementCurrency, "select_DiscountAgreementCurrency");
		com.isElementPresent(select_AgreementStatus, "select_AgreementStatus");

		CustomReporter.createNode("Additional Parameters Section");
		com.isElementPresent(section_Additionalparameters, "section_Additionalparameters");
		com.isElementPresent(checkbox_IsRollingAgreement, "checkbox_IsRollingAgreement");
		com.isElementPresent(checkbox_IncludeSatellite, "checkbox_IncludeSatellite");
		com.isElementPresent(checkbox_PremiumNumbersAtDiscountRate, "checkbox_PremiumNumbersAtDiscountRate");
		//com.isElementPresent(select_M2MStatus, "select_M2MStatus");
		com.isElementPresent(checkbox_DoNotCalculate, "checkbox_DoNotCalculate");
		com.isElementPresent(select_AgreementType, "select_AgreementType");
		com.isElementPresent(checkbox_HomeGroupAgreement, "checkbox_HomeGroupAgreement");
		com.isElementPresent(select_DiscountType, "select_DiscountType");
		com.isElementPresent(select_HomeAgreementNegotiator, "select_HomeAgreementNegotiator");
		com.isElementPresent(select_PartnerAgreementNegotiator, "select_PartnerAgreementNegotiator");
		com.isElementPresent(select_ForecastMethod, "select_ForecastMethod");
		com.isElementPresent(select_ApplicableLawCountry, "select_ApplicableLawCountry");
		com.isElementPresent(text_TerminationNoticePeriodDays, "text_TerminationNoticePeriodDays");
		com.isElementPresent(text_DisputePeriodDays, "text_DisputePeriodDays");
		com.isElementPresent(text_RenegotiationNoticeDays, "text_RenegotiationNoticeDays");
		com.isElementPresent(text_EAEDate, "text_EAEDate");
		com.isElementPresent(text_AgreementSignedDate, "text_AgreementSignedDate");
		com.isElementPresent(select_NextAction, "select_NextAction");
		com.isElementPresent(checkbox_ContinuationOfFixedTermAgreementNotPlanned,
				"checkbox_ContinuationOfFixedTermAgreementNotPlanned");
	}

	public void verify_UI_GBROR() {
		CustomReporter.createNode("Top Header Section");
		com.isElementPresent(button_Cancel, "button_Cancel");
		com.isElementPresent(heading_AgreementDetailsActive, "heading_AgreementDetailsActive");
		com.isElementPresent(heading_DiscountParameters, "heading_DiscountParameters");
		com.isElementPresent(heading_SettlementDetails, "heading_SettlementDetails");
		com.isElementPresent(button_Next, "button_Next");

		CustomReporter.createNode("Agreement Parties Section");
		com.isElementPresent(section_AgreementParties, "section_AgreementParties");
		com.isElementPresent(select_AvailableHomeGroups, "select_AvailableHomeGroups");
		com.isElementPresent(select_AvailablePartnerGroups, "select_AvailablePartnerGroups");
		com.isElementPresent(select_HomeParties_Excluded, "select_HomeParties_Excluded");
		com.isElementPresent(select_HomeParties_Included, "select_HomeParties_Included");
		com.isElementPresent(select_PartnerParties_Excluded, "select_PartnerParties_Excluded");
		com.isElementPresent(select_PartnerParties_Included, "select_PartnerParties_Included");
		com.isElementPresent(text_PartnerFilter, "text_PartnerFilter");

		CustomReporter.createNode("Agreement Terms And Parameters Section");
		com.isElementPresent(section_AgreementTermsAndParameters, "section_AgreementTermsAndParameters");
		com.isElementPresent(select_AgreementReferenceTemplate, "select_AgreementReferenceTemplate");
		com.isElementPresent(text_AgreementReference, "text_AgreementReference");
		//com.isElementPresent(text_CommercialLaunchLetterDate, "text_CommercialLaunchLetterDate");
		//com.isElementPresent(text_LegalContractID, "text_LegalContractID");
		com.isElementPresent(text_AgreementStartDate, "text_AgreementStartDate");
		com.isElementPresent(text_AgreementEndDate, "text_AgreementEndDate");
		com.isElementPresent(select_DiscountAgreementCurrency, "select_DiscountAgreementCurrency");
		com.isElementPresent(select_AgreementStatus, "select_AgreementStatus");

		CustomReporter.createNode("Additional Parameters Section");
		com.isElementPresent(section_Additionalparameters, "section_Additionalparameters");
		com.isElementPresent(checkbox_IsRollingAgreement, "checkbox_IsRollingAgreement");
		com.isElementPresent(checkbox_IncludeSatellite, "checkbox_IncludeSatellite");
		com.isElementPresent(checkbox_PremiumNumbersAtDiscountRate, "checkbox_PremiumNumbersAtDiscountRate");
		//com.isElementPresent(select_M2MStatus, "select_M2MStatus");
		com.isElementPresent(checkbox_DoNotCalculate, "checkbox_DoNotCalculate");
		com.isElementPresent(select_AgreementType, "select_AgreementType");
		//com.isElementPresent(checkbox_HomeGroupAgreement, "checkbox_HomeGroupAgreement");
		com.isElementPresent(select_DiscountType, "select_DiscountType");
		com.isElementPresent(select_HomeAgreementNegotiator, "select_HomeAgreementNegotiator");
		com.isElementPresent(select_PartnerAgreementNegotiator, "select_PartnerAgreementNegotiator");
		com.isElementPresent(select_ForecastMethod, "select_ForecastMethod");
		com.isElementPresent(select_ApplicableLawCountry, "select_ApplicableLawCountry");
		com.isElementPresent(text_TerminationNoticePeriodDays, "text_TerminationNoticePeriodDays");
		com.isElementPresent(text_DisputePeriodDays, "text_DisputePeriodDays");
		com.isElementPresent(text_RenegotiationNoticeDays, "text_RenegotiationNoticeDays");
		//com.isElementPresent(text_EAEDate, "text_EAEDate");
		com.isElementPresent(text_AgreementSignedDate, "text_AgreementSignedDate");
		com.isElementPresent(select_NextAction, "select_NextAction");
		com.isElementPresent(checkbox_ContinuationOfFixedTermAgreementNotPlanned,
				"checkbox_ContinuationOfFixedTermAgreementNotPlanned");
	}

	public void verify_UI_POL03() {
		CustomReporter.createNode("Top Header Section");
		com.isElementPresent(button_Cancel, "button_Cancel");
		com.isElementPresent(heading_AgreementDetailsActive, "heading_AgreementDetailsActive");
		com.isElementPresent(heading_DiscountParameters, "heading_DiscountParameters");
		com.isElementPresent(heading_SettlementDetails, "heading_SettlementDetails");
		com.isElementPresent(button_Next, "button_Next");

		CustomReporter.createNode("Agreement Parties Section");
		com.isElementPresent(section_AgreementParties, "section_AgreementParties");
		com.isElementPresent(select_AvailableHomeGroups, "select_AvailableHomeGroups");
		com.isElementPresent(select_AvailablePartnerGroups, "select_AvailablePartnerGroups");
		com.isElementPresent(select_HomeParties_Excluded, "select_HomeParties_Excluded");
		com.isElementPresent(select_HomeParties_Included, "select_HomeParties_Included");
		com.isElementPresent(select_PartnerParties_Excluded, "select_PartnerParties_Excluded");
		com.isElementPresent(select_PartnerParties_Included, "select_PartnerParties_Included");
		com.isElementPresent(text_PartnerFilter, "text_PartnerFilter");

		CustomReporter.createNode("Agreement Terms And Parameters Section");
		com.isElementPresent(section_AgreementTermsAndParameters, "section_AgreementTermsAndParameters");
		com.isElementPresent(select_AgreementReferenceTemplate, "select_AgreementReferenceTemplate");
		com.isElementPresent(text_AgreementReference, "text_AgreementReference");
		//com.isElementPresent(text_CommercialLaunchLetterDate,"text_CommercialLaunchLetterDate");
		//com.isElementPresent(text_LegalContractID,"text_LegalContractID");
		com.isElementPresent(text_AgreementStartDate, "text_AgreementStartDate");
		com.isElementPresent(text_AgreementEndDate, "text_AgreementEndDate");
		com.isElementPresent(select_DiscountAgreementCurrency, "select_DiscountAgreementCurrency");
		com.isElementPresent(select_AgreementStatus, "select_AgreementStatus");

		CustomReporter.createNode("Additional Parameters Section");
		com.isElementPresent(section_Additionalparameters, "section_Additionalparameters");
		com.isElementPresent(checkbox_IsRollingAgreement, "checkbox_IsRollingAgreement");
		com.isElementPresent(checkbox_IncludeSatellite, "checkbox_IncludeSatellite");
		com.isElementPresent(checkbox_PremiumNumbersAtDiscountRate, "checkbox_PremiumNumbersAtDiscountRate");
		//com.isElementPresent(select_M2MStatus,"select_M2MStatus");
		com.isElementPresent(checkbox_DoNotCalculate, "checkbox_DoNotCalculate");
		com.isElementPresent(select_AgreementType, "select_AgreementType");
		com.isElementPresent(checkbox_HomeGroupAgreement, "checkbox_HomeGroupAgreement");
		com.isElementPresent(select_DiscountType, "select_DiscountType");
		com.isElementPresent(select_HomeAgreementNegotiator, "select_HomeAgreementNegotiator");
		com.isElementPresent(select_PartnerAgreementNegotiator, "select_PartnerAgreementNegotiator");
		com.isElementPresent(select_ForecastMethod, "select_ForecastMethod");
		com.isElementPresent(select_ApplicableLawCountry, "select_ApplicableLawCountry");
		com.isElementPresent(text_TerminationNoticePeriodDays, "text_TerminationNoticePeriodDays");
		com.isElementPresent(text_DisputePeriodDays, "text_DisputePeriodDays");
		com.isElementPresent(text_RenegotiationNoticeDays, "text_RenegotiationNoticeDays");
		//com.isElementPresent(text_EAEDate,"text_EAEDate");
		com.isElementPresent(text_AgreementSignedDate, "text_AgreementSignedDate");
		com.isElementPresent(select_NextAction, "select_NextAction");
		com.isElementPresent(checkbox_ContinuationOfFixedTermAgreementNotPlanned,
				"checkbox_ContinuationOfFixedTermAgreementNotPlanned");
	}

	public void verify_UI_Base() {
		CustomReporter.createNode("Top Header Section");
		com.isElementPresent(button_Cancel, "button_Cancel");
		com.isElementPresent(heading_AgreementDetailsActive, "heading_AgreementDetailsActive");
		com.isElementPresent(heading_DiscountParameters, "heading_DiscountParameters");
		com.isElementPresent(heading_SettlementDetails, "heading_SettlementDetails");
		com.isElementPresent(button_Next, "button_Next");

		CustomReporter.createNode("Agreement Parties Section");
		com.isElementPresent(section_AgreementParties, "section_AgreementParties");
		com.isElementPresent(select_AvailableHomeGroups, "select_AvailableHomeGroups");
		com.isElementPresent(select_AvailablePartnerGroups, "select_AvailablePartnerGroups");
		com.isElementPresent(select_HomeParties_Excluded, "select_HomeParties_Excluded");
		com.isElementPresent(select_HomeParties_Included, "select_HomeParties_Included");
		com.isElementPresent(select_PartnerParties_Excluded, "select_PartnerParties_Excluded");
		com.isElementPresent(select_PartnerParties_Included, "select_PartnerParties_Included");
		com.isElementPresent(text_PartnerFilter, "text_PartnerFilter");

		CustomReporter.createNode("Agreement Terms And Parameters Section");
		com.isElementPresent(section_AgreementTermsAndParameters, "section_AgreementTermsAndParameters");
		com.isElementPresent(select_AgreementReferenceTemplate, "select_AgreementReferenceTemplate");
		com.isElementPresent(text_AgreementReference, "text_AgreementReference");
		com.isElementPresent(text_CommercialLaunchLetterDate, "text_CommercialLaunchLetterDate");
		com.isElementPresent(text_LegalContractID, "text_LegalContractID");
		com.isElementPresent(text_AgreementStartDate, "text_AgreementStartDate");
		com.isElementPresent(text_AgreementEndDate, "text_AgreementEndDate");
		com.isElementPresent(select_DiscountAgreementCurrency, "select_DiscountAgreementCurrency");
		com.isElementPresent(select_AgreementStatus, "select_AgreementStatus");

		CustomReporter.createNode("Additional Parameters Section");
		com.isElementPresent(section_Additionalparameters, "section_Additionalparameters");
		com.isElementPresent(checkbox_IsRollingAgreement, "checkbox_IsRollingAgreement");
		com.isElementPresent(checkbox_IncludeSatellite, "checkbox_IncludeSatellite");
		com.isElementPresent(checkbox_PremiumNumbersAtDiscountRate, "checkbox_PremiumNumbersAtDiscountRate");
		com.isElementPresent(select_M2MStatus, "select_M2MStatus");
		com.isElementPresent(checkbox_DoNotCalculate, "checkbox_DoNotCalculate");
		com.isElementPresent(select_AgreementType, "select_AgreementType");
		com.isElementPresent(checkbox_HomeGroupAgreement, "checkbox_HomeGroupAgreement");
		com.isElementPresent(select_DiscountType, "select_DiscountType");
		com.isElementPresent(select_HomeAgreementNegotiator, "select_HomeAgreementNegotiator");
		com.isElementPresent(select_PartnerAgreementNegotiator, "select_PartnerAgreementNegotiator");
		com.isElementPresent(select_ForecastMethod, "select_ForecastMethod");
		com.isElementPresent(select_ApplicableLawCountry, "select_ApplicableLawCountry");
		com.isElementPresent(text_TerminationNoticePeriodDays, "text_TerminationNoticePeriodDays");
		com.isElementPresent(text_DisputePeriodDays, "text_DisputePeriodDays");
		com.isElementPresent(text_RenegotiationNoticeDays, "text_RenegotiationNoticeDays");
		com.isElementPresent(text_EAEDate, "text_EAEDate");
		com.isElementPresent(text_AgreementSignedDate, "text_AgreementSignedDate");
		com.isElementPresent(select_NextAction, "select_NextAction");
		com.isElementPresent(checkbox_ContinuationOfFixedTermAgreementNotPlanned,
				"checkbox_ContinuationOfFixedTermAgreementNotPlanned");
	}

}