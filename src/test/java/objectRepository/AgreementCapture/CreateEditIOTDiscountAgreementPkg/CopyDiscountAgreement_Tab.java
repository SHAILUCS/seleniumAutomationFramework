package objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;

public class CopyDiscountAgreement_Tab extends EditIOTDiscountAgreement_Tab
{
	@FindBy(xpath = "//a[contains(.,'Copy Discount Agreement')]")
	private WebElement tab_CopyDiscountAgreement;
	
	@FindBy(xpath = "//h2[contains(.,'Copy Discount Agreement')]")
	private WebElement region_CopyDiscountAgreement;
	
	@FindBy(xpath = "//label[@id='P303_DISP_AGREEMENT_REF_LABEL']")
	private WebElement label_AgreementReference;
	
	@FindBy(xpath = "//button[@title='Help Text: * Agreement Reference']")
	private WebElement helpbutton_AgreementReference;
	
	@FindBy(xpath = "//label[@id='P303_NEW_ACR_TEMPLATE_ID_LABEL']")
	private WebElement label_NewAgreementReferenceTemplate;
	
	@FindBy(xpath = "//select[@id='P303_NEW_ACR_TEMPLATE_ID']")
	private WebElement dropdown_NewAgreementReferenceTemplate;
	
	@FindBy(xpath = "//button[@title='Help Text: New Agreement Reference Template']")
	private WebElement helpbutton_NewAgreementReferenceTemplate;
	
	@FindBy(xpath = "//label[@id='P303_COPY_AGREEMENT_REF_LABEL']")
	private WebElement label_NewAgreementReference;
	
	@FindBy(xpath = "//input[@id='P303_COPY_AGREEMENT_REF']")
	private WebElement textbox_NewAgreementReference;
	
	@FindBy(xpath = "//button[@title='Help Text: New Agreement Reference']")
	private WebElement helpbutton_NewAgreementReference;
	
	@FindBy(xpath = "//label[@id='P303_NEW_AGREEMENT_START_DATE_LABEL']")
	private WebElement label_NewAgreementStartDate;
	
	@FindBy(xpath = "//input[@id='P303_NEW_AGREEMENT_START_DATE']")
	private WebElement textbox_NewAgreementStartDate;
	
	@FindBy(xpath = "//button[@title='Help Text: New Agreement Start Date']")
	private WebElement helpbutton_NewAgreementStartDate;
	
	@FindBy(xpath = "//label[@id='P303_NEW_AGREEMENT_END_DATE_LABEL']")
	private WebElement label_NewAgreementEndDate;
	
	@FindBy(xpath = "//input[@id='P303_NEW_AGREEMENT_END_DATE']")
	private WebElement textbox_NewAgreementEndDate;
	
	@FindBy(xpath = "//button[@title='Help Text: New Agreement End Date']")
	private WebElement helpbutton_NewAgreementEndDate;
	
	@FindBy(xpath = "//label[@id='P303_COPY_PLMN_CODES_LABEL']")
	private WebElement label_UseOriginalPLMNCodes;
	
	@FindBy(xpath = "//input[@id='P303_COPY_PLMN_CODES_0']")
	private WebElement checkbox_UseOriginalPLMNCodes;
	
	@FindBy(xpath = "//button[@title='Help Text: Use original PLMN Codes']")
	private WebElement helpbutton_UseOriginalPLMNCodes;
	
	@FindBy(xpath = "//label[@id='P303_NEW_AGREEMENT_PSS_ID_LABEL']")
	private WebElement label_NewAgreementStatus;
	
	@FindBy(xpath = "//select[@id='P303_NEW_ACR_TEMPLATE_ID']")
	private WebElement dropdown_NewAgreementStatus;
	
	@FindBy(xpath = "//button[@title='Help Text: New Agreement Status']")
	private WebElement helpbutton_NewAgreementStatus;
	
	@FindBy(xpath = "//label[@id='P303_COPY_DELIMITER_LABEL']")
	private WebElement label_Selectdocumentsforcopying;
		
	@FindBy(xpath = "//label[@id='P303_COPY_IOT_DISC_AGR_LABEL']")
	private WebElement label_IOTDiscountAgreement;
	
	@FindBy(xpath = "//input[@id='P303_COPY_IOT_DISC_AGR_0']")
	private WebElement checkbox_IOTDiscountAgreement;
	
	@FindBy(xpath = "//button[@title='Help Text: IOT Discount Agreement']")
	private WebElement helpbutton_IOTDiscountAgreement;
	
	@FindBy(xpath = "//label[@id='P303_COPY_DEAL_SUMMARY_LABEL']")
	private WebElement label_DealSummary;
	
	@FindBy(xpath = "//input[@id='P303_COPY_DEAL_SUMMARY_0']")
	private WebElement checkbox_DealSummary;
	
	@FindBy(xpath = "//button[@title='Help Text: Deal Summary']")
	private WebElement helpbutton_DealSummary;
	
	@FindBy(xpath = "//label[@id='P303_COPY_DCH_DISCOUNT_FORM_LABEL']")
	private WebElement label_DCHDiscountForm;
	
	@FindBy(xpath = "//input[@id='P303_COPY_DCH_DISCOUNT_FORM_0']")
	private WebElement checkbox_DCHDiscountForm;
	
	@FindBy(xpath = "//button[@title='Help Text: DCH Discount Form']")
	private WebElement helpbutton_DCHDiscountForm;
	
	@FindBy(xpath = "//label[@id='P303_COPY_SETTLEMENT_STATEMENT_LABEL']")
	private WebElement label_SettlementStatement;
	
	@FindBy(xpath = "//input[@id='P303_COPY_SETTLEMENT_STATEMENT_0']")
	private WebElement checkbox_SettlementStatement;
	
	@FindBy(xpath = "//button[@title='Help Text: Settlement Statement']")
	private WebElement helpbutton_SettlementStatement;
		
	@FindBy(xpath = "//label[@id='P303_COPY_DEAL_SUMMARY_LABEL']")
	private WebElement label_CopyALLDocuments;
	
	@FindBy(xpath = "//input[@id='P303_COPY_ALL_DOCS_0']")
	private WebElement checkbox_CopyALLDocuments;
	
	@FindBy(xpath = "//button[@title='Help Text: Copy ALL Documents']")
	private WebElement helpbutton_CopyALLDocuments;
	
	@FindBy(xpath = "//button[@id='P303_COPY_AGREEMENT']")
	private WebElement helpbutton_CopyAgreement;
	
	private SeleniumMethods com;
	
	public static String  title="Create / Edit IOT Discount Agreement";
	public CopyDiscountAgreement_Tab() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	public void UIVerification()
	{
	
		com.click(tab_CopyDiscountAgreement, "Copy Discount Agreement Tab");
		CustomReporter.createNode("Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountAgreements, "Maintain IOT Discount Agreements, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountAgreement, "Create Edit IOT Discount Agreement, BreadCrumb");
		com.isElementPresent(region_CopyDiscountAgreement, "Copy Discount Agreement, Region");		
		com.isElementPresent(label_AgreementReference, "Agreement Reference, Label");
		com.isElementPresent(helpbutton_AgreementReference, "Agreement Reference, helpbutton");
		com.isElementPresent(label_NewAgreementReferenceTemplate, "New Agreement Reference Template, Label");
		com.isElementPresent(dropdown_NewAgreementReferenceTemplate, "New Agreement Reference Template, dropdown");
		com.isElementPresent(helpbutton_NewAgreementReferenceTemplate, "New Agreement Reference Template, helpbutton");
		com.isElementPresent(label_NewAgreementReference, "New Agreement Reference, Label");
		com.isElementPresent(textbox_NewAgreementReference, "New Agreement Reference, textbox");
		com.isElementPresent(helpbutton_NewAgreementReference, "New Agreement Reference, helpbutton");
		com.isElementPresent(label_NewAgreementStartDate, "New Agreement Start Date, Label");
		com.isElementPresent(textbox_NewAgreementStartDate, "New Agreement Start Date, textbox");
		com.isElementPresent(helpbutton_NewAgreementStartDate, "New Agreement Start Date, helpbutton");
		com.isElementPresent(label_NewAgreementEndDate, "New Agreement End Date, Label");
		com.isElementPresent(textbox_NewAgreementEndDate, "New Agreement End Date, textbox");
		com.isElementPresent(helpbutton_NewAgreementEndDate, "New Agreement End Date, helpbutton");
		com.isElementPresent(label_UseOriginalPLMNCodes, "Use Original PLMN Codes, Label");	
		com.isElementPresent(checkbox_UseOriginalPLMNCodes, "Use Original PLMN Codes, checkbox");
		com.isElementPresent(helpbutton_UseOriginalPLMNCodes, "Use Original PLMN Codes, helpbutton");
		com.isElementPresent(label_NewAgreementStatus, "New Agreement Status, Label");	
		com.isElementPresent(dropdown_NewAgreementStatus, "New Agreement Status, dropdown");
		com.isElementPresent(helpbutton_NewAgreementStatus, "New Agreement Status, helpbutton");
		com.isElementPresent(label_Selectdocumentsforcopying, "Select documents for copying, Label");
		com.isElementPresent(label_IOTDiscountAgreement, "IOT Discount Agreement, Label");
		com.isElementPresent(checkbox_IOTDiscountAgreement, "IOT Discount Agreement, checkbox");
		com.isElementPresent(helpbutton_IOTDiscountAgreement, "IOT Discount Agreement, helpbutton");
		com.isElementPresent(label_DealSummary, "Deal Summary, Label");
		com.isElementPresent(checkbox_DealSummary, "Deal Summary, checkbox");
		com.isElementPresent(helpbutton_DealSummary, "Deal Summary, helpbutton");	
		com.isElementPresent(label_DCHDiscountForm, "DCH Discount Form, Label");
		com.isElementPresent(checkbox_DCHDiscountForm, "DCH Discount Form, checkbox");
		com.isElementPresent(helpbutton_DCHDiscountForm, "DCH Discount Form, helpbutton");
		com.isElementPresent(label_SettlementStatement, "Settlement Statement, Label");
		com.isElementPresent(checkbox_SettlementStatement, "Settlement Statement, checkbox");
		com.isElementPresent(helpbutton_SettlementStatement, "Settlement Statement, helpbutton");	
		com.isElementPresent(label_CopyALLDocuments, "Copy ALL Documents, Label");
		com.isElementPresent(checkbox_CopyALLDocuments, "Copy ALL Documents, checkbox");
		com.isElementPresent(helpbutton_CopyALLDocuments, "Copy ALL Documents, helpbutton");
		com.isElementPresent(helpbutton_CopyAgreement, "Copy Agreement, helpbutton");
	}

}
