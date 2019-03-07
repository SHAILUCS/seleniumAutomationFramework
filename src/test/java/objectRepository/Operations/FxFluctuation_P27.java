package objectRepository.Operations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.jsonUtil.JSONManager;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;
import tests.MainRegression.HKT_Alerts_7465;

public class FxFluctuation_P27 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.FxFluctuation_P27.title;

	public FxFluctuation_P27() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(text(),'Fx Fluctuation')]")
	private WebElement heading_FxFluctuation;
	
	@FindBy(xpath = "//h2[contains(text(),'Fx Fluctuation')]")
	private WebElement section_FxFluctuation;
	
	@FindBy(id="P27_ALERT_ENABLED_0")
	private WebElement checkbox_Enabled;
	
	@FindBy(id = "P27_ALERT_SCHEDULED_DATE")
	private WebElement text_ScheduledDate;
	
	@FindBy(id = "P27_ALERT_NAME")
	private WebElement text_Name;
	
	@FindBy(id = "P27_ALERT_TEXT")
	private WebElement text_Text;
	
	@FindBy(id = "P27_EMAIL")
	private WebElement text_Email;
	
	@FindBy(id = "P27_FX_FLUCTUATION_THRESHOLD")
	private WebElement text_Threshold;
	
	@FindBy(id = "P27_FX_COMPARISON_MONTHS_NUMBE")
	private WebElement text_Months;
	
	@FindBy(xpath = "//button[.='Cancel']")
	private WebElement button_Cancel;
	
	@FindBy(xpath = "//button[.='Delete']")
	private WebElement button_Delete;
	
	@FindBy(xpath = "//button[.='Save']")
	private WebElement button_Save;
		
	public void verify_UI() {
		
		com.isElementPresent(heading_FxFluctuation,"FxFluctuation heading");
		com.isElementPresent(section_FxFluctuation,"FxFluctuation section");

		com.isElementPresent(checkbox_Enabled,"Enabled checkbox");
		com.isElementPresent(text_ScheduledDate,"Scheduled Date text");
		com.isElementPresent(text_Name,"Name text");
		com.isElementPresent(text_Text,"Alert text");
		com.isElementPresent(text_Email,"Email text");
		com.isElementPresent(text_Threshold,"Threshold text");
		com.isElementPresent(text_Months,"Months text");

		com.isElementPresent(button_Cancel,"Cancel button");
		if(!com.waitForElementTobe_NotVisible(button_Delete,0)){
			com.isElementPresent(button_Delete,"Delete button");	
		}
		com.isElementPresent(button_Save,"Save button");
	}
	
	public void verify_Validations() {
		//Enabling Alert as next tests may need this alert
		com.check_Checkbox(checkbox_Enabled);
		
		verifyAlertScheduledDateValidations();
		verifyAlertNameValidations();
		verifyAlertTextValidations();
		verifyEmailValidations();
		verifyFxFluctuationAlertThresholdValidations();
		verifyNoOfComparisonMonthsValidations();
		
		CustomReporter.createNode("All Valid values");
		com.click(button_Save,"After filling All Valid values Save button");
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		com.verifyPageTitle(AlertSubscription_P26.title, true);
	}
	
	private void verifyAlertScheduledDateValidations() {
		CustomReporter.createNode("Alert Scheduled Date");
		
		// Blank value
		com.sendKeys(text_ScheduledDate, "");
		com.click(button_Save);
		comm.verifyValidationMessage("","Alert Scheduled Date must have some value.");

		// Negative Number
		com.sendKeys(text_ScheduledDate, "-1");
		com.click(button_Save);
		comm.verifyValidationMessage("-1","Alert Scheduled Date is not between the valid range of 1 and 31.");

		// Symbols 
		com.sendKeys(text_ScheduledDate, "a");
		com.click(button_Save);
		comm.verifyValidationMessage("a","Alert Scheduled Date must be Numeric.");
		
		// Valid Value 
		com.sendKeys(text_ScheduledDate, "30");
		
	}

	private void verifyAlertNameValidations() {
		CustomReporter.createNode("Alert Name");
		
		// Blank value
		com.sendKeys(text_Name, "");
		com.click(button_Save);
		comm.verifyValidationMessage("","Alert Name must have some value.");
		
		// Valid Value 
		com.sendKeys(text_Name, "Test Fx Fluctuation");

	}
	private void verifyAlertTextValidations() {
		CustomReporter.createNode("Alert Text");
		
		// Blank value
		com.sendKeys(text_Text, "");
		com.click(button_Save);
		comm.verifyValidationMessage("","Alert Text must have some value.");
		
		// Valid Value
		com.sendKeys(text_Text, "Desc Test Fx Fluctuation");
	}
	
	private void verifyEmailValidations() {
		CustomReporter.createNode("Email");
		
		// Blank value
		com.sendKeys(text_Email, "");
		com.click(button_Save);
		comm.verifyValidationMessage("","Email must have some value.");

		// Invalid email separator
		com.sendKeys(text_Email, "Ivana.Jandrecic@nextgenclearing.com:shailendra.rajawat@nextgenclearing.com");
		com.click(button_Save);
		comm.verifyValidationMessage("Ivana.Jandrecic@nextgenclearing.com:shailendra.rajawat@nextgenclearing.com","Invalid Email Id.");

		// Missing @ Symbol 
		com.sendKeys(text_Email, "shailendra.rajawatnextgenclearing.com");
		com.click(button_Save);
		comm.verifyValidationMessage("shailendra.rajawatnextgenclearing.com","Invalid Email Id.");
		
		// Misplaced @ and . Symbol 
		com.sendKeys(text_Email, "shailendra.rajawat@.com");
		com.click(button_Save);
		comm.verifyValidationMessage("shailendra.rajawat@.com","Invalid Email Id.");

		// Valid Value
		com.sendKeys(text_Email, "Ivana.Jandrecic@nextgenclearing.com;shailendra.rajawat@nextgenclearing.com");
	}
	
	private void verifyFxFluctuationAlertThresholdValidations() {
		CustomReporter.createNode("Fx Fluctuation Alert Threshold");
		
		// Blank value
		com.sendKeys(text_Threshold, "");
		com.click(button_Save);
		comm.verifyValidationMessage("","Fx Fluctuation Alert Threshold% must have some value.");

		// Zero value
		com.sendKeys(text_Threshold, "0");
		com.click(button_Save);
		comm.verifyValidationMessage("0","Fx Fluctuation Alert Threshold% is less than specified minimum .0000000001.");

		// Any Symbol
		com.sendKeys(text_Threshold, "0,2");
		com.click(button_Save);
		comm.verifyValidationMessage("0,2","Fx Fluctuation Alert Threshold% must be Numeric.");
		
		// Valid Value
		com.sendKeys(text_Threshold, ".1");
	}
	
	private void verifyNoOfComparisonMonthsValidations() {
		CustomReporter.createNode("No Of Comparison Months");
		
		// Blank value
		com.sendKeys(text_Months, "");
		com.click(button_Save);
		comm.verifyValidationMessage("","No of Comparison Months must have some value.");

		// Zero value
		com.sendKeys(text_Months, "0");
		com.click(button_Save);
		comm.verifyValidationMessage("0","No of Comparison Months is not between the valid range of 1 and 36.");

		// Any Symbol
		com.sendKeys(text_Months, "2a");
		com.click(button_Save);
		comm.verifyValidationMessage("2a","No of Comparison Months must be Numeric.");
		
		// Valid Value
		com.sendKeys(text_Months, "5");
	}

	public void verify_CRUD(String methodName) {
		
		//CREATE
		CustomReporter.createNode("CREATE");
		JSONManager json=new JSONManager(HKT_Alerts_7465.jsonFilePath, methodName,"create");
		AlertSubscription_P26 p26=saveFxFluctuation(json);
		p26.verifyFxFluctSectionData(json);
		
		//EDIT
		CustomReporter.createNode("EDIT");
		p26.clickOnFxFluctEdit();
		json=new JSONManager(HKT_Alerts_7465.jsonFilePath, methodName,"edit");
		saveFxFluctuation(json);
		p26.verifyFxFluctSectionData(json);
		
		//CANCEL BUTTON CHECK
		CustomReporter.createNode("CANCEL BUTTON CHECK");
		p26.clickOnFxFluctEdit();
		
		fillAllFields(false, null, "alertName", "alertText", "email", "threshold", "comparisionMonths");
		com.click(button_Cancel,"Cancel button");
		com.verifyPageTitle(AlertSubscription_P26.title, true);
		p26.verifyFxFluctSectionData(json);
		
		//DELETE
		CustomReporter.createNode("DELETE");
		p26.clickOnFxFluctEdit();
		com.click(button_Delete,"Delete button");
		com.waitForElementTobe_Visible(comm.button_OK, "Delete confirmation popup");
		com.click(comm.button_OK);
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		com.verifyPageTitle(AlertSubscription_P26.title, true);
		json=new JSONManager(HKT_Alerts_7465.jsonFilePath, methodName,"delete");
		p26.verifyFxFluctSectionData(json);
		
	}

	public AlertSubscription_P26 saveFxFluctuation(JSONManager json) {
		return saveFxFluctuation(json.getBool("alertEnabled"), json.getStr("alertScheduledDate"), 
				json.getStr("alertName"), json.getStr("alertText"), json.getStr("email"), 
				json.getStr("threshold"), json.getStr("comparisionMonths"));
	}

	public AlertSubscription_P26 saveFxFluctuation(boolean alertEnabled, String alertScheduledDate, String alertName, String alertText,
			String email, String threshold, String comparisionMonths) {
		
		fillAllFields(alertEnabled, alertScheduledDate, alertName, alertText, email, threshold, comparisionMonths);
		
		com.click(button_Save,"save button");
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		com.verifyPageTitle(AlertSubscription_P26.title, true);
		
		if(!com.waitForElementTobe_NotVisible(By.xpath("//h2[contains(.,'Action Processed')]"), 1)){
			com.click(By.xpath("//div[contains(@class,'Alert')][contains(.,'Action Processed')]//button"));
		}
		
		return new AlertSubscription_P26();
	}
	
	private void fillAllFields(boolean alertEnabled, String alertScheduledDate, String alertName, String alertText,
			String email, String threshold, String comparisionMonths) {
		
		String msg="| alertEnabled= "+alertEnabled+" | ";
		
		if (alertEnabled) {
			com.check_Checkbox(checkbox_Enabled);
		}else {
			com.uncheck_Checkbox(checkbox_Enabled);
		}

		if (alertScheduledDate!=null) {
			com.sendKeys(text_ScheduledDate, alertScheduledDate);
			msg=msg+"alertScheduledDate= "+alertScheduledDate+" | ";
		}

		if (alertName!=null) {
			com.sendKeys(text_Name, alertName);
			msg=msg+"alertName= "+alertName+" | ";
		}

		if (alertText!=null) {
			com.sendKeys(text_Text, alertText);
			msg=msg+"alertText= "+alertText+" | ";
		}

		if (email!=null) {
			com.sendKeys(text_Email, email);
			msg=msg+"email= "+email+" | ";
		}

		if (threshold!=null) {
			com.sendKeys(text_Threshold, threshold);
			msg=msg+"threshold= "+threshold+" | ";
		}

		if (comparisionMonths!=null) {
			com.sendKeys(text_Months, comparisionMonths);
			msg=msg+"comparisionMonths= "+comparisionMonths+" | ";
		}
		
		CustomReporter.report(STATUS.INFO, "Filling the following fields "+msg);
	}
	
	
}
