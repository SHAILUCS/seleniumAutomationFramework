package objectRepository.Operations;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.jsonUtil.JSONManager;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;
import tests.MainRegression.HKT_Alerts_7465;

public class TrafficTrend_P31 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.TrafficTrend_P31.title;

	public TrafficTrend_P31() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h2[.='Traffic trend']")
	private WebElement section_TrafficTrend;

	@FindBy(id = "P31_ALERT_ENABLED_0")
	private WebElement checkbox_AlertEnabled;

	@FindBy(id = "P31_ALERT_SCHEDULED_DATE")
	private WebElement text_AlertScheduledDate;

	@FindBy(id = "P31_ALERT_NAME")
	private WebElement text_AlertName;

	@FindBy(id = "P31_ALERT_TEXT")
	private WebElement text_AlertText;

	@FindBy(id = "P31_EMAIL")
	private WebElement text_Email;

	@FindBy(id = "P31_MOM_0")
	private WebElement checkbox_Mom;

	@FindBy(id = "P31_YOY_0")
	private WebElement checkbox_Yoy;

	@FindBy(id = "P31_GROWTH_THRESHOLD")
	private WebElement text_GrowthThreshold;

	@FindBy(id = "P31_DROP_THRESHOLD")
	private WebElement text_DropThreshold;

	@FindBy(xpath = "//button[.='Cancel']")
	private WebElement button_Cancel;

	@FindBy(xpath = "//button[.='Delete']")
	private WebElement button_Delete;

	@FindBy(xpath = "//button[.='Save']")
	private WebElement button_Save;

	@FindBy(xpath = "//button[.='Create']")
	private WebElement button_Create;

	@FindBy(xpath = "//h2[.='Thresholds per PMNs (Operators)']")
	private WebElement section_ThresholdsPerPMNsOperators;

	@FindBy(xpath = "//button[contains(.,'Add Row')]")
	private WebElement button_AddRow;

	/**
	 * @author shailendra.rajawat Precondition : p26>>p31 should be loaded
	 *         Postcondition : page content will be verified
	 */
	public void verify_UI() {
		CustomReporter.createNode("Verifying the 'Traffic Trend' Section");
		com.isElementPresent(section_TrafficTrend, "section_Traffic Trend");
		com.isElementPresent(checkbox_AlertEnabled, "checkbox_Alert Enabled");
		com.isElementPresent(text_AlertScheduledDate, "text_Alert Scheduled Date");
		com.isElementPresent(text_AlertName, "text_Alert Name");
		com.isElementPresent(text_AlertText, "text_Alert Text");
		com.isElementPresent(text_Email, "text_Email");
		com.isElementPresent(checkbox_Mom, "checkbox_Mom");
		com.isElementPresent(checkbox_Yoy, "checkbox_Yoy");
		com.isElementPresent(text_GrowthThreshold, "text_Growth Threshold");
		com.isElementPresent(text_DropThreshold, "text_Drop Threshold");
		com.isElementPresent(button_Cancel, "button_Cancel");
		if (!com.waitForElementTobe_NotVisible(button_Delete, 0)) {
			com.isElementPresent(button_Delete, "Delete button");
		}

		if(!com.waitForElementTobe_NotVisible(button_Save,0)){
			com.isElementPresent(button_Save, "button_Save");
		}
		
		if(!com.waitForElementTobe_NotVisible(button_Create,0)){
			com.isElementPresent(button_Create, "button_Create");
		}

		CustomReporter.createNode("Verifying the 'Thresholds per PMNs (Operators)' Section");
		com.isElementPresent(comm.tab_Left_GRID_Head, "grid_Header_Checkbox");
		com.isElementPresent(comm.tab_Right_GRID_Head, "grid_Header_Col Names");
		com.isElementPresent(comm.tab_Left_GRID_Body, "grid_Body_Checkbox");
		com.isElementPresent(comm.tab_Right_GRID_Body, "grid_Body_Col Data");

	}

	/**
	 * @author shailendra.rajawat Precondition- p31 should be loaded via p26
	 *         Postcondition- fill perform Create Update and Delete operation on
	 *         Traffic Trend, will also verify the changes on p26
	 */
	public void verify_CRUD(String methodName) {
		// CREATE
		CustomReporter.createNode("CREATE");
		JSONManager json = new JSONManager(HKT_Alerts_7465.jsonFilePath, methodName, "create");
		AlertSubscription_P26 p26 = saveTrafficTrend(json);
		p26.verifyTrafficTrendSectionData(json);

		// EDIT
		CustomReporter.createNode("EDIT");
		p26.clickOnTrafTrendEdit();
		json = new JSONManager(HKT_Alerts_7465.jsonFilePath, methodName, "edit");
		saveTrafficTrend(json);
		p26.verifyTrafficTrendSectionData(json);

		// CANCEL BUTTON CHECK
		CustomReporter.createNode("CANCEL BUTTON CHECK");
		p26.clickOnTrafTrendEdit();

		fillAllFields(false, null, "alertName", "alertText", "email", true, true, "2", "-2");
		com.click(button_Cancel, "Cancel button");
		com.verifyPageTitle(AlertSubscription_P26.title, true);
		p26.verifyTrafficTrendSectionData(json);

		// DELETE
		CustomReporter.createNode("DELETE");
		p26.clickOnTrafTrendEdit();
		com.click(button_Delete, "Delete button");
		com.waitForElementTobe_Visible(comm.button_OK, "Delete confirmation popup");
		com.click(comm.button_OK);
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		com.verifyPageTitle(AlertSubscription_P26.title, true);
		json = new JSONManager(HKT_Alerts_7465.jsonFilePath, methodName, "delete");
		p26.verifyTrafficTrendSectionData(json);

	}

	/**
	 * @author shailendra.rajawat Precondition- p31 should be loaded via p26,
	 *         you should pass a json object which holds all values of form
	 *         Postcondition- fill all non null values of form, then save the
	 *         details after which app will navigate back to p26
	 */
	public AlertSubscription_P26 saveTrafficTrend(JSONManager json) {
		return saveTrafficTrend(json.getBool("alertEnabled"), json.getStr("alertScheduledDate"),
				json.getStr("alertName"), json.getStr("alertText"), json.getStr("email"), json.getBool("mom"),
				json.getBool("yoy"), json.getStr("growthThreshold"), json.getStr("dropThreshold"));
	}

	/**
	 * @author shailendra.rajawat Precondition- p31 should be loaded via p26
	 *         Postcondition- fill all non null values of form, then save the
	 *         details after which app will navigate back to p26
	 */
	public AlertSubscription_P26 saveTrafficTrend(boolean alertEnabled, String alertScheduledDate, String alertName,
			String alertText, String email, boolean mom, boolean yoy, String growthThreshold, String dropThreshold) {

		fillAllFields(alertEnabled, alertScheduledDate, alertName, alertText, email, mom, yoy, growthThreshold,
				dropThreshold);

		if(!com.waitForElementTobe_NotVisible(button_Create,0)){
			com.click(button_Create, "button_Create");
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			comm.closeApexAlert_SuccessMessagePopup("Action Processed");
		}

		if(!com.waitForElementTobe_NotVisible(button_Save,0)){
			com.click(button_Save, "button_Save");
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			com.verifyPageTitle(AlertSubscription_P26.title, true);
			comm.closeApexAlert_SuccessMessagePopup("Action Processed");
		}

		return new AlertSubscription_P26();
	}

	/**
	 * @author shailendra.rajawat Precondition- p31 should be loaded
	 *         Postcondition- fill all non null values of form
	 */
	private void fillAllFields(boolean alertEnabled, String alertScheduledDate, String alertName, String alertText,
			String email, boolean mom, boolean yoy, String growthThreshold, String dropThreshold) {

		String msg = "| alertEnabled= " + alertEnabled + " | ";

		if (alertEnabled) {
			com.check_Checkbox(checkbox_AlertEnabled);
		} else {
			com.uncheck_Checkbox(checkbox_AlertEnabled);
		}

		if (alertScheduledDate != null) {
			com.sendKeys(text_AlertScheduledDate, alertScheduledDate);
			msg = msg + "alertScheduledDate= " + alertScheduledDate + " | ";
		}

		if (alertName != null) {
			com.sendKeys(text_AlertName, alertName);
			msg = msg + "alertName= " + alertName + " | ";
		}

		if (alertText != null) {
			com.sendKeys(text_AlertText, alertText);
			msg = msg + "alertText= " + alertText + " | ";
		}

		if (email != null) {
			com.sendKeys(text_Email, email);
			msg = msg + "email= " + email + " | ";
		}

		msg = "| MoM= " + mom + " | ";
		if (mom) {
			com.check_Checkbox(checkbox_Mom);
		} else {
			com.uncheck_Checkbox(checkbox_Mom);
		}

		msg = "| YoY= " + yoy + " | ";
		if (yoy) {
			com.check_Checkbox(checkbox_Yoy);
		} else {
			com.uncheck_Checkbox(checkbox_Yoy);
		}

		if (growthThreshold != null) {
			com.sendKeys(text_GrowthThreshold, growthThreshold);
			msg = msg + "growthThreshold= " + growthThreshold + " | ";
		}

		if (dropThreshold != null) {
			com.sendKeys(text_DropThreshold, dropThreshold);
			msg = msg + "dropThreshold= " + dropThreshold + " | ";
		}
		CustomReporter.report(STATUS.INFO, "Filling the following fields " + msg);
	}

	/**
	 * @author shailendra.rajawat Precondition- p31 should be loaded
	 *         Postcondition- all validations will be verified, and a alert will
	 *         be saved with all valid data
	 */
	public void verify_Validations(String methodName) {
		// Enabling Alert as next tests may need this alert
		com.check_Checkbox(checkbox_AlertEnabled);

		JSONManager json = new JSONManager(HKT_Alerts_7465.jsonFilePath, methodName);
		verifyAlertScheduledDateValidations(json, "AlertScheduledDate");
		verifyAlertNameValidations(json, "AlertName");
		verifyAlertTextValidations(json, "AlertText");
		verifyEmailValidations(json, "Email");
		verifyMomYoyValidations();
		verifyGrowthThresholdValidations(json, "growthThreshold");
		verifyDropThresholdValidations(json, "dropThreshold");

		CustomReporter.createNode("All Valid values");
		com.click(!com.waitForElementTobe_NotVisible(button_Create, 0) ? button_Create : button_Save, "After filling All Valid values Save button");
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		comm.closeApexAlert_SuccessMessagePopup("Action Processed");

		/*
		 * After traffic trend alert is created, then Operators grid is getting
		 * displayed. thats why this validation is executing after saving all
		 * valid values
		 */
		verifyThresholdPerPmnsOperatorsValidations(json, "operators");

		com.click(!com.waitForElementTobe_NotVisible(button_Create, 0) ? button_Create : button_Save, "After filling All Valid values Save button");
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		comm.closeApexAlert_SuccessMessagePopup("Action Processed");

	}

	/**
	 * @author shailendra.rajawat Precondition- p31 should be loaded
	 *         Postcondition- will validate the operators grid validations
	 */
	private void verifyThresholdPerPmnsOperatorsValidations(JSONManager json, String jsonObj) {

		String title=com.getTitle();
		if(title.equals(AlertSubscription_P26.title)){
			CustomReporter.createNode("Application navigated to "+AlertSubscription_P26.title+" page, navigating back to "+TrafficTrend_P31.title+" page");
			AlertSubscription_P26 p26=new AlertSubscription_P26();
			com.click(p26.button_TrafficTrend_Edit, "button_TrafficTrend_Edit");
		}

		// This section of code will handle a special condition, sometimes when
		// we are not creating a new Traffic Trend alert. on clicking the save
		// button, we navigate back to "Alert Subscription" page26 so before
		// proceeding with GRID verification we need to navigate back to
		// "Traffic Trend" page31.
		// To achieve this what we can do is to just check p26 is getting
		// displayed, if yes then click on traffic trend Edit button

		CustomReporter.createNode("Thresholds per PMNs (Operators) GRID");

		com.javaScript_Click(button_AddRow, "Add row");

		WebTable tabData = new WebTable(com.getByObjectFromWebElement(comm.tab_Right_GRID_Body));

		int rowCount = tabData.getRowCount();

		if (rowCount >= 2) {
			int validationsArrLen = json.getJsonArrLength(jsonObj);
			for (int i = 0; i < validationsArrLen; i++) {
				String pmn=json.getStrArr(jsonObj, i, "pmn")[0];
				String growth=json.getStrArr(jsonObj, i, "growthThreshold")[0];
				String drop=json.getStrArr(jsonObj, i, "dropThreshold")[0];

				fillValuesInGRID(tabData, rowCount, pmn, growth, drop);

				com.click(!com.waitForElementTobe_NotVisible(button_Create, 0) ? button_Create : button_Save);
				comm.verifyValidationMessage("pmn:"+pmn+", growth:"+growth+", drop:"+drop,json.getStrArr(jsonObj, i, "validation")[0]);
				com.click(tabData.getCellObject(rowCount, 1));
			}
		} else {
			CustomReporter.report(STATUS.FAIL, "Grid contains no rows");
		}

		// Valid Value
		fillValuesInGRID(tabData, rowCount, "BELKO", "5", "-5");
	}

	private void fillValuesInGRID(WebTable tabData,int rowCount,String pmn,String growth,String drop) {
		com.selectByPartialVisibleText(com.switchTo_ActiveElement(), pmn);
		com.doubleClick(tabData.getCellObject(rowCount, 2));
		com.wait(1);
		com.switchTo_ActiveElement().clear();
		com.doubleClick(tabData.getCellObject(rowCount, 2));
		com.wait(1);
		com.switchTo_ActiveElement().sendKeys(growth);

		com.doubleClick(tabData.getCellObject(rowCount, 3));
		com.wait(1);
		com.switchTo_ActiveElement().clear();
		com.doubleClick(tabData.getCellObject(rowCount, 3));
		com.wait(1);
		com.switchTo_ActiveElement().sendKeys(drop+Keys.TAB);
	}

	/**
	 * @author shailendra.rajawat Precondition- p31 should be loaded
	 *         Postcondition- will validate the atleast one among mom and yoy
	 *         should be selected
	 */
	private void verifyMomYoyValidations() {
		CustomReporter.createNode("MoM and YoY");

		// Both unchecked
		com.uncheck_Checkbox(checkbox_Mom);
		com.uncheck_Checkbox(checkbox_Yoy);
		com.click(!com.waitForElementTobe_NotVisible(button_Create, 0) ? button_Create : button_Save);
		comm.verifyValidationMessage("unchecked MoM and YoY","YoY or MoM must be configured");

		// Valid Value
		com.check_Checkbox(checkbox_Mom);
		com.check_Checkbox(checkbox_Yoy);
	}

	/**
	 * @author shailendra.rajawat Precondition- p31 should be loaded
	 *         Postcondition- will fill all the values mentioned in JSON Array
	 *         and check the validations one by one
	 */
	private void fillValueAndCheckValidation(Object element, JSONManager json, String fieldName) {
		int validationsArrLen = json.getJsonArrLength(fieldName);
		for (int i = 0; i < validationsArrLen; i++) {
			String value=json.getStrArr(fieldName, i, "value")[0];
			com.sendKeys(element, value);
			com.click(!com.waitForElementTobe_NotVisible(button_Create, 0) ? button_Create : button_Save);
			comm.verifyValidationMessage(value,json.getStrArr(fieldName, i, "validation")[0]);
		}
	}

	/**
	 * @author shailendra.rajawat Precondition: p31 should be loaded
	 *         Postcondition: will validate the Alert Scheduled Date field
	 *         validations and fill a valid value
	 */
	private void verifyAlertScheduledDateValidations(JSONManager json, String fieldName) {
		CustomReporter.createNode("Alert Scheduled Date");

		fillValueAndCheckValidation(text_AlertScheduledDate, json, fieldName);

		// Valid Value
		com.sendKeys(text_AlertScheduledDate, "30");
	}

	/**
	 * @author shailendra.rajawat Precondition: p31 should be loaded
	 *         Postcondition: will validate the Alert Name field validations and
	 *         fill a valid value
	 */
	private void verifyAlertNameValidations(JSONManager json, String fieldName) {
		CustomReporter.createNode("Alert Name");

		fillValueAndCheckValidation(text_AlertName, json, fieldName);

		// Valid Value
		com.sendKeys(text_AlertName, "Test ");

	}

	/**
	 * @author shailendra.rajawat Precondition: p31 should be loaded
	 *         Postcondition: will validate the Alert Text field validations and
	 *         fill a valid value
	 */
	private void verifyAlertTextValidations(JSONManager json, String fieldName) {
		CustomReporter.createNode("Alert Text");

		fillValueAndCheckValidation(text_AlertText, json, fieldName);

		// Valid Value
		com.sendKeys(text_AlertText, "Desc Test Fx Fluctuation");
	}

	/**
	 * @author shailendra.rajawat Precondition: p31 should be loaded
	 *         Postcondition: will validate the Email field validations and fill
	 *         a valid value
	 */
	private void verifyEmailValidations(JSONManager json, String fieldName) {
		CustomReporter.createNode("Email");

		fillValueAndCheckValidation(text_Email, json, fieldName);

		// Valid Value
		com.sendKeys(text_Email, "Ivana.Jandrecic@nextgenclearing.com;shailendra.rajawat@nextgenclearing.com");
	}

	/**
	 * @author shailendra.rajawat Precondition: p31 should be loaded
	 *         Postcondition: will validate the Growth Threshold Text field
	 *         validations and fill a valid value
	 */
	private void verifyGrowthThresholdValidations(JSONManager json, String fieldName) {
		CustomReporter.createNode("Growth Threshold");

		fillValueAndCheckValidation(text_GrowthThreshold, json, fieldName);

		// Valid Value
		com.sendKeys(text_GrowthThreshold, "1");
	}

	/**
	 * @author shailendra.rajawat Precondition: p31 should be loaded
	 *         Postcondition: will validate the Drop Threshold Text field
	 *         validations and fill a valid value
	 */
	private void verifyDropThresholdValidations(JSONManager json, String fieldName) {
		CustomReporter.createNode("Drop Threshold");

		fillValueAndCheckValidation(text_DropThreshold, json, fieldName);

		// Valid Value
		com.sendKeys(text_DropThreshold, "-1");
	}

}
