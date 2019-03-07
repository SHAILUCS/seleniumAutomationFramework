package objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;

public class EditIOTDiscountAgreement_Tab 
{
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	protected WebElement breadcrumb_IOTRONWelcome;
	
	@FindBy(xpath = "//li[contains(.,'Agreement Capture')]")
	protected WebElement breadcrumb_AgreementCapture;
	
	@FindBy(xpath = "//li[contains(.,'Maintain IOT Discount Agreements')]")
	protected WebElement breadcrumb_MaintainIOTDiscountAgreements;

	@FindBy(xpath = "//h1[contains(.,'Create / Edit IOT Discount Agreement')]")
	protected WebElement breadcrumb_CreateEditIOTDiscountAgreement;
		
	@FindBy(xpath = "//a[contains(.,'Create / Edit IOT Discount Agreement')]")
	private WebElement tab_CreateEditIOTDiscountAgreement;
	
	@FindBy(xpath = "//h2[contains(.,'Create / Edit IOT Discount Agreement')]")
	private WebElement region_CreateEditIOTDiscountAgreement;
	
	@FindBy(xpath = "//h2[contains(.,'Agreement Terms and Parameters')]")
	private WebElement region_AgreementTermsAndParameters;
	
	@FindBy(xpath = "//h2[contains(.,'Agreement Status Change History')]")
	private WebElement region_AgreementStatusChangeHistory;
	
	@FindBy(xpath = "//div[@data-label='Create / Edit IOT Discount Agreement']//button[contains(.,'Save Changes')]")
	private WebElement button_SaveChanges;
	
	@FindBy(xpath = "//button[contains(.,'Calculate Agreement')]")
	private WebElement button_CalculateAgreement;
	
	@FindBy(xpath = "//button[contains(.,'Delete Discount Agreement')]")
	private WebElement button_DeleteDiscountAgreement;
	
	@FindBy(xpath = "//button[contains(.,'Generate Term Sheet')]")
	private WebElement button_GenerateTermSheet;
	
	@FindBy(xpath = "//div[@data-label='Create / Edit IOT Discount Agreement']//label[contains(.,'Agreement Reference Template')]")
	private WebElement label_AgreementReferenceTemplate;
	
	@FindBy(xpath = "//select[@id='P303_IOT_ACR_TEMPLATE_ID']")
	private WebElement dropdown_AgreementReferenceTemplate;
	
	@FindBy(xpath = "//button[@title='Help Text: Agreement Reference Template']")
	private WebElement helpbutton_AgreementReferenceTemplate;
	
	@FindBy(xpath = "//label[@id='P303_CLIENT_AGREEMENT_REF_LABEL']")
	private WebElement label_AgreementReference;
	
	@FindBy(xpath = "//input[@id='P303_CLIENT_AGREEMENT_REF']")
	private WebElement textbox_AgreementReference;
	
	@FindBy(xpath = "//button[@title='Help Text: * Agreement Reference']")
	private WebElement helpbutton_AgreementReference;
	
	@FindBy(xpath = "//label[@id='P303_AGREEMENT_START_DATE_LABEL']")
	private WebElement label_AgreementStartDate ;
	
	@FindBy(xpath = "//input[@id='P303_AGREEMENT_START_DATE']")
	private WebElement textbox_AgreementStartDate;
	
	@FindBy(xpath = "//button[@title='Help Text: * Agreement Start Date']")
	private WebElement helpbutton_AgreementStartDate;
	
	@FindBy(xpath = "//label[@id='P303_AGREEMENT_END_DATE_LABEL']")
	private WebElement label_AgreementEndDate;
	
	@FindBy(xpath = "//input[@id='P303_AGREEMENT_END_DATE']")
	private WebElement textbox_AgreementEndDate;
	
	@FindBy(xpath = "//button[@title='Help Text: * Agreement End Date']")
	private WebElement helpbutton_AgreementEndDate;
	
	@FindBy(xpath = "//label[@id='P303_DISC_AGRMT_CURR_LABEL']")
	private WebElement label_DiscountAgreementCurrency;
	
	@FindBy(xpath = "//select[@id='P303_DISC_AGRMT_CURR']")
	private WebElement dropdown_DiscountAgreementCurrency;
	
	@FindBy(xpath = "//button[@title='Help Text: * Discount Agreement Currency']")
	private WebElement helpbutton_DiscountAgreementCurrency;
		
	@FindBy(xpath = "//label[@id='P303_IS_ROLLING_AGREEMENT_LABEL']")
	private WebElement label_ISROLLINGAGREEMENT;
	
	@FindBy(xpath = "//input[@id='P303_IS_ROLLING_AGREEMENT_0']")
	private WebElement checkbox_ISROLLINGAGREEMENT;
	
	@FindBy(xpath = "//button[@title='Help Text: Is Rolling Agreement']")
	private WebElement helpbutton_ISROLLINGAGREEMENT;
	
	@FindBy(xpath = "//label[@id='P303_INCLUDE_SATELLITE_LABEL']")
	private WebElement label_INCLUDESATELLITE;
	
	@FindBy(xpath = "//input[@id='P303_INCLUDE_SATELLITE_0']")
	private WebElement checkbox_INCLUDESATELLITE;
	
	@FindBy(xpath = "//button[@title='Help Text: Include Satellite']")
	private WebElement helpbutton_INCLUDESATELLITE;
	
	@FindBy(xpath = "//label[@id='P303_EXCLUDE_PREMIUM_NUM_LABEL']")
	private WebElement label_PREMIUM_NUM;
	
	@FindBy(xpath = "//fieldset[@id='P303_EXCLUDE_PREMIUM_NUM']")
	private WebElement checkbox_PREMIUM_NUM;
	
	@FindBy(xpath = "//button[@title='Help Text: Premium Numbers at Discount Rate']")
	private WebElement helpbutton_PREMIUM_NUM;
	
	@FindBy(xpath = "//label[@id='P303_IOT_AGREEMENT_PSS_ID_LABEL']")
	private WebElement label_AgreementStatus;
	
	@FindBy(xpath = "//select[@id='P303_IOT_AGREEMENT_PSS_ID']")
	private WebElement dropdown_AgreementStatus;
	
	@FindBy(xpath = "//button[@title='Help Text: Agreement Status']")
	private WebElement helpbutton_AgreementStatus;
	
	@FindBy(xpath = "//label[@id='P303_DO_NOT_CALCULATE_LABEL']")
	private WebElement label_DoNotCalculate;
	
	@FindBy(xpath = "//input[@id='P303_DO_NOT_CALCULATE_0']")
	private WebElement checkbox_DoNotCalculate;
	
	@FindBy(xpath = "//button[@title='Help Text: Do Not Calculate']")
	private WebElement helpbutton_DoNotCalculate;
	
	@FindBy(xpath = "//label[@id='P303_AGREEMENT_TYPE_ID_LABEL']")
	private WebElement label_AGREEMENTTYPE;
	
	@FindBy(xpath = "//button[@title='Help Text: Agreement Type']")
	private WebElement helpbutton_AGREEMENTTYPE;
	
	@FindBy(xpath = "//label[@id='P303_NEGOTIATOR_CONTACT_ID_LABEL']")
	private WebElement label_HomeAgreementNegotiator;
	
	@FindBy(xpath = "//select[@id='P303_NEGOTIATOR_CONTACT_ID']")
	private WebElement dropdown_HomeAgreementNegotiator;
	
	@FindBy(xpath = "//button[@title='Help Text: Home Agreement Negotiator']")
	private WebElement helpbutton_HomeAgreementNegotiator;
	
	@FindBy(xpath = "//label[@id='P303_PNR_NEGOTIATOR_CONTACT_ID_LABEL']")
	private WebElement label_PartnerAgreementNegotiator;
	
	@FindBy(xpath = "//select[@id='P303_NEGOTIATOR_CONTACT_ID']")
	private WebElement dropdown_PartnerAgreementNegotiator;
	
	@FindBy(xpath = "//button[@title='Help Text: Partner Agreement Negotiator']")
	private WebElement helpbutton_PartnerAgreementNegotiator;
	
	@FindBy(xpath = "//label[@id='P303_IOT_FORECAST_TYPE_ID_LABEL']")
	private WebElement label_ForcastMethod;
	
	@FindBy(xpath = "//select[@id='P303_NEGOTIATOR_CONTACT_ID']")
	private WebElement dropdown_ForcastMethod;
	
	@FindBy(xpath = "//button[@title='Help Text: Forecast Method']")
	private WebElement helpbutton_ForcastMethod;
		
	@FindBy(xpath = "//label[@id='P303_APPLICABLE_LAW_COUNTRY_ID_LABEL']")
	private WebElement label_APPLICABLE_LAW_COUNTRY;
	
	@FindBy(xpath = "//select[@id='P303_APPLICABLE_LAW_COUNTRY_ID']")
	private WebElement dropdown_APPLICABLE_LAW_COUNTRY;
	
	@FindBy(xpath = "//button[@title='Help Text: Applicable Law Country']")
	private WebElement helpbutton_APPLICABLE_LAW_COUNTRY;
		
	@FindBy(xpath = "//label[@id='P303_TERMINATION_NOTICE_PERIOD_LABEL']")
	private WebElement label_TERMINATION_NOTICE_PERIOD;
	
	@FindBy(xpath = "//input[@id='P303_TERMINATION_NOTICE_PERIOD']")
	private WebElement textbox_TERMINATION_NOTICE_PERIOD;
	
	@FindBy(xpath = "//button[@title='Help Text: Termination Notice Period Days']")
	private WebElement helpbutton_TERMINATION_NOTICE_PERIOD;
	
	@FindBy(xpath = "//label[@id='P303_DISPUTE_PERIOD_DAYS_LABEL']")
	private WebElement label_DISPUTE_PERIOD_DAYS;
	
	@FindBy(xpath = "//input[@id='P303_DISPUTE_PERIOD_DAYS']")
	private WebElement textbox_DISPUTE_PERIOD_DAYS;
	
	@FindBy(xpath = "//button[@title='Help Text: Dispute Period (Days)']")
	private WebElement helpbutton_DISPUTE_PERIOD_DAYS;
		
	@FindBy(xpath = "//label[@id='P303_RENEGOTIATION_NOTICE_DAYS_LABEL']")
	private WebElement label_RENEGOTIATION_NOTICE_DAYS;
	
	@FindBy(xpath = "//input[@id='P303_RENEGOTIATION_NOTICE_DAYS']")
	private WebElement textbox_RENEGOTIATION_NOTICE_DAYS;
	
	@FindBy(xpath = "//button[@title='Help Text: Renegotiation Notice Days']")
	private WebElement helpbutton_RENEGOTIATION_NOTICE_DAYS;
	
	@FindBy(xpath = "//label[@id='P303_EAE_DATE_LABEL']")
	private WebElement label_EAE_DATE;
	
	@FindBy(xpath = "//input[@id='P303_EAE_DATE']")
	private WebElement textbox_EAE_DATE;
	
	@FindBy(xpath = "//button[@title='Help Text: EAE Date']")
	private WebElement helpbutton_EAE_DATE;
	
	@FindBy(xpath = "//label[@id='P303_AGREEMENT_DATE_LABEL']")
	private WebElement label_AGREEMENT_DATE;
	
	@FindBy(xpath = "//input[@id='P303_AGREEMENT_DATE']")
	private WebElement textbox_AGREEMENT_DATE;
	
	@FindBy(xpath = "//button[@title='Help Text: Agreement Signed Date']")
	private WebElement helpbutton_AGREEMENT_DATE;
		
	@FindBy(xpath = "//label[@id='P303_SIGNATURE_STATUS_LABEL']")
	private WebElement label_SignatureStatus;
	
	@FindBy(xpath = "//button[@title='Help Text: Signature Status']")
	private WebElement helpbutton_SignatureStatus;
	
	@FindBy(xpath = "//label[@id='P303_NEXT_ACTION_LABEL']")
	private WebElement label_NextAction;
	
	@FindBy(xpath = "//select[@id='P303_NEXT_ACTION']")
	private WebElement dropdown_NextAction;
	
	@FindBy(xpath = "//button[@title='Help Text: Next Action']")
	private WebElement helpbutton_NextAction;
	
	@FindBy(xpath = "//span[contains(.,'* Mandatory Parameters')]")
	private WebElement note_MandatoryParameters;

	private SeleniumMethods com;
	
	public static String  title="Create / Edit IOT Discount Agreement";
	public EditIOTDiscountAgreement_Tab() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	public void UIVerification()
	{
		CustomReporter.createNode("Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountAgreements, "Maintain IOT Discount Agreements, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountAgreement, "Create Edit IOT Discount Agreement, BreadCrumb");
		com.isElementPresent(tab_CreateEditIOTDiscountAgreement, "Create Edit IOT Discount Agreement, Tab");		
		com.isElementPresent(region_CreateEditIOTDiscountAgreement, "Create Edit IOT Discount Agreement, Region");
		com.isElementPresent(region_AgreementTermsAndParameters, "Agreement Terms And Parameters, Region");
		com.isElementPresent(region_AgreementStatusChangeHistory, "Agreement Status Change History, Region");		
		com.isElementPresent(button_SaveChanges, "Save Changes, Button");
		com.isElementPresent(button_CalculateAgreement, "Calculate Agreement, Button");
		com.isElementPresent(button_DeleteDiscountAgreement, "Delete Discount Agreement, Button");
		com.isElementPresent(button_GenerateTermSheet, "Generate Term Sheet, Button");		
		com.isElementPresent(label_AgreementReferenceTemplate, "Agreement Reference Template, Label");
		com.isElementPresent(dropdown_AgreementReferenceTemplate, "Agreement Reference Template, dropdown");
		com.isElementPresent(helpbutton_AgreementReferenceTemplate, "Agreement Reference Template, helpbutton");		
		com.isElementPresent(label_AgreementReference, "AgreementReference, Label");
		com.isElementPresent(textbox_AgreementReference, "AgreementReference, textbox");
		com.isElementPresent(helpbutton_AgreementReference, "AgreementReference, helpbutton");		
		com.isElementPresent(label_AgreementStartDate, "Agreement Start Date, Label");
		com.isElementPresent(textbox_AgreementStartDate, "Agreement Start Date, textbox");
		com.isElementPresent(helpbutton_AgreementStartDate, "Agreement Start Date, helpbutton");		
		com.isElementPresent(label_AgreementEndDate, "Agreement End Date, Label");
		com.isElementPresent(textbox_AgreementEndDate, "Agreement End Date, textbox");
		com.isElementPresent(helpbutton_AgreementEndDate, "Agreement End Date, helpbutton");	
		com.isElementPresent(label_DiscountAgreementCurrency, "Discount Agreement Currency, Label");
		com.isElementPresent(dropdown_DiscountAgreementCurrency, "Discount Agreement Currency, dropdown");
		com.isElementPresent(helpbutton_DiscountAgreementCurrency, "Discount Agreement Currency, helpbutton");		
		com.isElementPresent(label_ISROLLINGAGREEMENT, "IS ROLLING AGREEMENT, Label");
		com.isElementPresent(checkbox_ISROLLINGAGREEMENT, "IS ROLLING AGREEMENT, textbox");
		com.isElementPresent(helpbutton_ISROLLINGAGREEMENT, "IS ROLLING AGREEMENT, helpbutton");	
		com.isElementPresent(label_INCLUDESATELLITE, "INCLUDE SATELLITE, Label");
		com.isElementPresent(checkbox_INCLUDESATELLITE, "INCLUDE SATELLITE, textbox");
		com.isElementPresent(helpbutton_INCLUDESATELLITE, "INCLUDE SATELLITE, helpbutton");	
		com.isElementPresent(label_PREMIUM_NUM, "PREMIUM_NUM, Label");
		com.isElementPresent(checkbox_PREMIUM_NUM, "PREMIUM_NUM, textbox");
		com.isElementPresent(helpbutton_PREMIUM_NUM, "PREMIUM_NUM, helpbutton");	
		com.isElementPresent(label_AgreementStatus, "Agreement Status, Label");
		com.isElementPresent(dropdown_AgreementStatus, "Agreement Status, dropdown");
		com.isElementPresent(helpbutton_AgreementStatus, "Agreement Status, helpbutton");
		com.isElementPresent(label_DoNotCalculate, "Do Not Calculate, Label");
		com.isElementPresent(checkbox_DoNotCalculate, "Do Not Calculate, textbox");
		com.isElementPresent(helpbutton_DoNotCalculate, "Do Not Calculate, helpbutton");	
		com.isElementPresent(label_AGREEMENTTYPE, "AGREEMENT TYPE, Label");
		com.isElementPresent(helpbutton_AGREEMENTTYPE, "AGREEMENT TYPE, helpbutton");
		com.isElementPresent(label_HomeAgreementNegotiator, "Home Agreement Negotiator, Label");
		com.isElementPresent(dropdown_HomeAgreementNegotiator, "Home Agreement Negotiator, dropdown");
		com.isElementPresent(helpbutton_HomeAgreementNegotiator, "Home Agreement Negotiator, helpbutton");
		com.isElementPresent(label_PartnerAgreementNegotiator, "Partner Agreement Negotiator, Label");
		com.isElementPresent(dropdown_PartnerAgreementNegotiator, "Partner Agreement Negotiator, dropdown");
		com.isElementPresent(helpbutton_PartnerAgreementNegotiator, "Partner Agreement Negotiator, helpbutton");	
		com.isElementPresent(label_ForcastMethod, "Forcast Method, Label");
		com.isElementPresent(dropdown_ForcastMethod, "Forcast Method, dropdown");
		com.isElementPresent(helpbutton_ForcastMethod, "Forcast Method, helpbutton");	
		com.isElementPresent(label_APPLICABLE_LAW_COUNTRY, "APPLICABLE_LAW_COUNTRY, Label");
		com.isElementPresent(dropdown_APPLICABLE_LAW_COUNTRY, "APPLICABLE_LAW_COUNTRY, dropdown");
		com.isElementPresent(helpbutton_APPLICABLE_LAW_COUNTRY, "APPLICABLE_LAW_COUNTRY, helpbutton");
		com.isElementPresent(label_TERMINATION_NOTICE_PERIOD, "TERMINATION_NOTICE_PERIOD, Label");
		com.isElementPresent(textbox_TERMINATION_NOTICE_PERIOD, "TERMINATION_NOTICE_PERIOD, textbox");
		com.isElementPresent(helpbutton_TERMINATION_NOTICE_PERIOD, "TERMINATION_NOTICE_PERIOD, helpbutton");
		com.isElementPresent(label_DISPUTE_PERIOD_DAYS, "DISPUTE_PERIOD_DAYS, Label");
		com.isElementPresent(textbox_DISPUTE_PERIOD_DAYS, "DISPUTE_PERIOD_DAYS, textbox");
		com.isElementPresent(helpbutton_DISPUTE_PERIOD_DAYS, "DISPUTE_PERIOD_DAYS, helpbutton");
		com.isElementPresent(label_RENEGOTIATION_NOTICE_DAYS, "RENEGOTIATION_NOTICE_DAYS, Label");
		com.isElementPresent(textbox_RENEGOTIATION_NOTICE_DAYS, "RENEGOTIATION_NOTICE_DAYS, textbox");
		com.isElementPresent(helpbutton_RENEGOTIATION_NOTICE_DAYS, "RENEGOTIATION_NOTICE_DAYS, helpbutton");
		com.isElementPresent(label_EAE_DATE, "EAE_DATE, Label");
		com.isElementPresent(textbox_EAE_DATE, "EAE_DATE, textbox");
		com.isElementPresent(helpbutton_EAE_DATE, "EAE_DATE, helpbutton");
		com.isElementPresent(label_AGREEMENT_DATE, "AGREEMENT_DATE, Label");
		com.isElementPresent(textbox_AGREEMENT_DATE, "AGREEMENT_DATE, textbox");
		com.isElementPresent(helpbutton_AGREEMENT_DATE, "AGREEMENT_DATE, helpbutton");
		com.isElementPresent(label_SignatureStatus, "Signature Status, Label");
		com.isElementPresent(helpbutton_SignatureStatus, "Signature Status, helpbutton");	
		com.isElementPresent(label_NextAction, "Next Action, Label");
		com.isElementPresent(dropdown_NextAction, "Next Action, dropdown");
		com.isElementPresent(helpbutton_NextAction, "Next Action, helpbutton");	
		com.isElementPresent(note_MandatoryParameters, "Mandatory Parameters, Note");
	}
	
	
	
	
}
