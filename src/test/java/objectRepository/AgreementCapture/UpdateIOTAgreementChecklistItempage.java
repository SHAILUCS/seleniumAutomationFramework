package objectRepository.AgreementCapture;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class UpdateIOTAgreementChecklistItempage
{
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	private WebElement breadcrumb_IOTRONWelcome;
	
	@FindBy(xpath = "//a[contains(.,'Discount Agreement Management')]")
	private WebElement breadcrumb_DiscountAgreementManagement;
	
	@FindBy(xpath = "//li[contains(.,'Agreement Checklist Review')]")
	private WebElement breadcrumb_AgreementChecklistReview;
	
	@FindBy(xpath = "//h1[contains(.,'Update IOT Agreement Checklist Item')]")
	private WebElement breadcrumb_UpdateIOTAgreementChecklistItem;
	
	@FindBy(xpath = "//label[@id='P455_AGREEMENT_REFERENCE_LABEL']")
	private WebElement label_AGREEMENTREFERENCE;
	
	@FindBy(xpath = "//h2[contains(.,'Update IOT Agreement Checklist Item')]")
	protected WebElement region_UpdateIOTAgreementChecklistItem;
	
	@FindBy(xpath = "//label[@id='P455_CHECKLIST_ITEM_TEXT_LABEL']")
	private WebElement label_CHECKLISTITEMTEXTLABEL;
	
	@FindBy(xpath = "//label[@id='P455_COMPLETED_LABEL']")
	private WebElement label_Completed;
	
	@FindBy(id = "P455_COMPLETED_0")
	private WebElement radio_Completed_No;
	
	@FindBy(xpath = "//label[@for='P455_COMPLETED_0']")
	private WebElement label_NO;
	
	@FindBy(id = "P455_COMPLETED_1")
	private WebElement radio_Completed_Yes;
	
	@FindBy(xpath = "//label[@for='P455_COMPLETED_1']")
	private WebElement label_YES;
	
	@FindBy(id = "P455_COMPLETED_2")
	private WebElement radio_Completed_NA;
	
	@FindBy(xpath = "//label[@for='P455_COMPLETED_2']")
	private WebElement label_NA;
	
	@FindBy(xpath = "//label[@id='P455_DATE_COMPLETED_LABEL']")
	private WebElement label_DateCompleted;
	
	@FindBy(id = "P455_DATE_COMPLETED")
	private WebElement text_DateCompleted;
	
	@FindBy(xpath = "//button/span[@class='a-Icon icon-calendar']")
	private WebElement datepicker_DateCompleted;
	
	@FindBy(xpath = "//button/span[contains(.,'Cancel')]")
	private WebElement button_Cancel;
	
	@FindBy(xpath = "//button[contains(.,'Apply Changes')]")
	private WebElement button_ApplyChanges;
	
	private SeleniumMethods com;
	
	public static String  title="Update IOT Agreement Checklist Item";
	public UpdateIOTAgreementChecklistItempage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	public void UIVerification()
	{
		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_DiscountAgreementManagement, "Discount Agreement Management, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementChecklistReview, "Agreement Checklist Review, BreadCrumb");
		com.isElementPresent(breadcrumb_UpdateIOTAgreementChecklistItem, "Update IOT Agreement Checklist Item, BreadCrumb");
		com.isElementPresent(label_AGREEMENTREFERENCE, "AGREEMENT REFERENCE, Label");
		com.isElementPresent(region_UpdateIOTAgreementChecklistItem, "Update IOT Agreement Checklist Item, Region");
		com.isElementPresent(label_CHECKLISTITEMTEXTLABEL, "CHECKLIST ITEM TEXT LABEL, Label");
		com.isElementPresent(label_Completed, "Completed, Label");
		com.isElementPresent(radio_Completed_No, "NO, radiobutton");
		com.isElementPresent(label_NO, "NO, Label");
		com.isElementPresent(radio_Completed_Yes, "YES, radiobutton");
		com.isElementPresent(label_YES, "YES, Label");
		com.isElementPresent(radio_Completed_NA, "NA, radiobutton");
		com.isElementPresent(label_NA, "NA, Label");
		com.isElementPresent(label_DateCompleted, "Date Completed, Label");
		com.isElementPresent(text_DateCompleted, "Date Completed, textbox");
		com.isElementPresent(datepicker_DateCompleted, "Date Completed, datepicker");
		com.isElementPresent(button_Cancel, "Cancel, button");
		com.isElementPresent(button_ApplyChanges, "Apply Changes, button");
		
	}		
	
	
	public void makeChanges(String completedRadio,String dateCompleted) {
		CustomReporter.createNode("Making changes :- ["+completedRadio+"] Status "+" Date Completed ["+dateCompleted+"]");
		switch (completedRadio) {
		case "No":
			com.javaScript_Click(radio_Completed_No);
			break;
		case "Yes":
			com.javaScript_Click(radio_Completed_Yes);
			break;
		case "N/A":
			com.javaScript_Click(radio_Completed_NA);
			break;
		}
		com.sendKeys("Date Completed textbox", text_DateCompleted, dateCompleted);
		com.click(button_ApplyChanges,"Apply Changes");
	}
	

}
