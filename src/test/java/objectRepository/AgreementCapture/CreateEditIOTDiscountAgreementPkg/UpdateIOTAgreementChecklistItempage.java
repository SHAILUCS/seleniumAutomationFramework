package objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.AgreementCapture.AgreementChecklistReview;

public class UpdateIOTAgreementChecklistItempage extends AgreementChecklistReview
{
	
	@FindBy(xpath = "//li[contains(.,'Agreement Checklist Review')]")
	private WebElement breadcrumb_AgreementChecklistReview;
	
	@FindBy(xpath = "//h1[contains(.,'Update IOT Agreement Checklist Item')]")
	private WebElement breadcrumb_UpdateIOTAgreementChecklistItem;
	
	@FindBy(xpath = "//h2[contains(.,'Update IOT Agreement Checklist Item')]")
	private WebElement region_UpdateIOTAgreementChecklistItem;
	
	@FindBy(xpath = "//label[@id='P455_CHECKLIST_ITEM_TEXT_LABEL']")
	private WebElement label_ChecklistItem;
	
	@FindBy(xpath = "//label[@id='P455_COMPLETED_LABEL']")
	private WebElement label_Completed;
	
	@FindBy(xpath = "//input[@id='P455_COMPLETED_0']")
	private WebElement radiobutton_No;
	
	@FindBy(xpath = "//label[@for='P455_COMPLETED_0']")
	private WebElement label_No;
	
	@FindBy(xpath = "//input[@id='P455_COMPLETED_1']")
	private WebElement radiobutton_Yes;
	
	@FindBy(xpath = "//label[@for='P455_COMPLETED_1']")
	private WebElement label_Yes;
	
	@FindBy(xpath = "//input[@id='P455_COMPLETED_2']")
	private WebElement radiobutton_NA;
	
	@FindBy(xpath = "//label[@for='P455_COMPLETED_2']")
	private WebElement label_NA;
	
	@FindBy(xpath = "//label[@id='P455_DATE_COMPLETED_LABEL']")
	private WebElement label_DateCompleted;
	
	@FindBy(xpath = "//input[@id='P455_DATE_COMPLETED']")
	private WebElement textbox_DateCompleted;
	
	@FindBy(xpath = "//span[contains(.,'Cancel')]")
	private WebElement button_Cancel;
	
	@FindBy(xpath = "//span[contains(.,'Apply Changes')]")
	private WebElement button_ApplyChanges;	
	
	private SeleniumMethods com;
	
	public static String  title="Create / Edit IOT Discount Agreement";
	public UpdateIOTAgreementChecklistItempage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	
	public void UIVerification()
	{
		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementChecklistReview, "Agreement Checklist Review, BreadCrumb");
		com.isElementPresent(breadcrumb_UpdateIOTAgreementChecklistItem, "Update IOT Agreement Checklist Item, BreadCrumb");
		com.isElementPresent(region_UpdateIOTAgreementChecklistItem, "Update IOT Agreement Checklist Item, Region");	
		com.isElementPresent(label_ChecklistItem, "ChecklistItem, Label");	
		com.isElementPresent(label_Completed, "Completed, Label");
		com.isElementPresent(radiobutton_No, "No, radiobutton");
		com.isElementPresent(label_No, "No, Label");
		com.isElementPresent(radiobutton_Yes, "Yes, radiobutton");
		com.isElementPresent(label_Yes, "Yes, Label");
		com.isElementPresent(radiobutton_NA, "NA, radiobutton");
		com.isElementPresent(label_NA, "NA, Label");
		com.isElementPresent(label_DateCompleted, "Date Completed, Label");
		com.isElementPresent(textbox_DateCompleted, "Date Completed, Textbox");
		com.isElementPresent(button_Cancel, "Cancel, Button");
		com.isElementPresent(button_ApplyChanges, "ApplyChanges, Button");		
	}

}
