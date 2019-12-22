package objectRepository.AgreementCapture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;
import tests.MainRegression.Module_AgreementCapture;

/**
 * Page Factory Class for storing page objects and methods to perform action on
 * stored objects of P35
 * 
 * @author shailendra.rajawat 08-Mar-2019
 */
public class MaintainTrafficSegmentationRules_P35 {

	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.MaintainTrafficSegmentationRules_P35.title;

	public MaintainTrafficSegmentationRules_P35() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[.='Maintain Traffic Segmentation Rules']")
	private WebElement breadcrumb_MaintainTrafficSegmentationRules;

	@FindBy(xpath = "//h2[.='Home Operator']")
	private WebElement section_HomeOperator;

	@FindBy(xpath = "//div[contains(.,'Home Operator')][@class='t-Region-header']//button")
	private WebElement button_HomeOperator_HideShowButton;

	@FindBy(id = "P35_HOME_OPERATOR_LEFT")
	private WebElement shuttle_HomeOperator_Left;

	@FindBy(id = "P35_HOME_OPERATOR_RIGHT")
	private WebElement shuttle_HomeOperator_Right;

	@FindBy(xpath = "//button[.='Refresh']")
	private WebElement button_Refresh;

	@FindBy(xpath = "//h2[.='Request Resegmentation']")
	private WebElement section_RequestResegmentation;

	@FindBy(xpath = "//div[contains(.,'Request Resegmentation')][@class='t-Region-header']//button")
	private WebElement button_RequestResegmentation_HideShowButton;

	@FindBy(id = "P35_HOME_OPERATOR_TO_RESEGM")
	private WebElement select_HomeOperator;

	@FindBy(xpath = "//button[.='Perform Resegmentation']")
	private WebElement button_PerformResegmentation;

	@FindBy(xpath = "//h2[.='Maintain Traffic Segmentation Rules']")
	private WebElement section_MaintainTrafficSegmentationRules;

	@FindBy(xpath = "//div[contains(.,'Maintain Traffic Segmentation Rules')][@class='t-Region-header']//button")
	private WebElement button_MaintainTrafficSegmentationRules_HideShowButton;

	private List<String> colNames = new ArrayList<>(Arrays.asList());

	/**
	 * Verifies all relevant elements present on the P35 Page precon : P35
	 * should be displayed postcon : Search will be performed and all objects
	 * will be checked
	 * 
	 * @author shailendra.rajawat 08-Mar-2019
	 */
	public void verify_UI(String[] homeOperator) {
		com.isElementPresent(breadcrumb_MaintainTrafficSegmentationRules,
				"Maintain Traffic Segmentation Rules breadcrumb");

		CustomReporter.createNode("Verifying the Home Operator section");
		com.isElementPresent(section_HomeOperator, "section_Home Operator");
		com.isElementPresent(button_HomeOperator_HideShowButton, "Home Operator_Hide Show Button");
		com.isElementPresent(shuttle_HomeOperator_Left, "shuttle_Home Operator_Left");
		com.isElementPresent(shuttle_HomeOperator_Right, "shuttle_Home Operator_Right");
		com.isElementPresent(button_Refresh, "button_Refresh");

		CustomReporter.createNode("Verifying the Request Resegmentation section");
		com.isElementPresent(section_RequestResegmentation, "section_Request Resegmentation");
		if (com.isElementPresent(button_RequestResegmentation_HideShowButton,
				"Request Resegmentation_Hide Show Button")) {

			com.click_UsingAction(button_RequestResegmentation_HideShowButton,
					"Request Resegmentation_Hide Show Button");
			com.isElementPresent(select_HomeOperator, "select_Home Operator");
			com.isElementPresent(button_PerformResegmentation, "button_Perform Resegmentation");

			/*
			 * When user selects some PMNs in home operators(above shuttle),
			 * Request Resegmentation section's Home Operator dropdown is
			 * populated with those PMNs
			 */
			CustomReporter.createNode(
					"Verifying the Impact of Home Operator shuttle on Request Resegmentation section's Home Operator dropdown");
			com.selectByPartialVisibleText_DoubleClick_FromArray(shuttle_HomeOperator_Left, homeOperator);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			List<String> elementsInHomeOperatorDropdown = com.getAllOptionsVisibleText(select_HomeOperator);
			boolean bool = true;
			Arrays.sort(homeOperator);
			Collections.sort(elementsInHomeOperatorDropdown);
			for (int i = 0; i < homeOperator.length; i++) {
				if (!elementsInHomeOperatorDropdown.get(i).contains(homeOperator[i])) {
					bool = false;
					break;
				}
			}

			if (bool) {
				CustomReporter.report(STATUS.PASS,
						"All selected elements are getting displayed in Request Resegmentation section's Home Operator dropdown");
			} else {
				CustomReporter.report(STATUS.FAIL,
						"Selected elements are NOT getting displayed in Request Resegmentation section's Home Operator dropdown");
			}
			CustomReporter.report(STATUS.INFO, "Expected List " + Arrays.toString(homeOperator));
			CustomReporter.report(STATUS.INFO, "Actual List " + elementsInHomeOperatorDropdown);

		}
		CustomReporter.createNode("Verifying the Maintain Traffic Segmentation Rules Section");
		performSearch(homeOperator);

		com.isElementPresent(section_MaintainTrafficSegmentationRules, "section_Maintain Traffic Segmentation Rules");
		com.isElementPresent(button_MaintainTrafficSegmentationRules_HideShowButton,
				"Maintain Traffic Segmentation Rules Hide Show Button");

		com.isElementPresent(comm.icon_SelectColumnToSearch_GRID, "Select Column To Search_GRID");
		com.isElementPresent(comm.text_Search_GRID, "text_Search_GRID");
		com.isElementPresent(comm.button_Go, "button_Go");

		comm.verifyActionsPopupItems_GRID();

		com.isElementPresent(comm.button_Edit_GRID, "button_Edit_GRID");
		com.isElementPresent(comm.button_Save_GRID, "button_Save_GRID");
		com.isElementPresent(comm.button_AddRow_GRID, "button_AddRow_GRID");
		com.isElementPresent(comm.button_DeleteRows_GRID, "button_DeleteRows_GRID");
		com.isElementPresent(comm.button_DownloadXls_GRID, "button_Download Xls_GRID");
		com.isElementPresent(comm.button_Reset, "button_Reset");

		com.isElementPresent(comm.tab_Left_GRID_Head, "Grid Left Header");
		com.isElementPresent(comm.tab_Left_GRID_Body, "Grid Left Body");
		com.isElementPresent(comm.tab_Right_GRID_Head, "Grid Right Header");
		com.isElementPresent(comm.tab_Right_GRID_Body, "Grid Right Body");

	}

	/**
	 * Fill in the search fields and perform the search precon : P35 should be
	 * loaded postcon : Values will be filled in the fields, Refresh button will
	 * be clicked
	 * 
	 * @author shailendra.rajawat 12-Mar-2019
	 */
	private void performSearch(String[] homeOperator) {
		String message = " | ";

		if (homeOperator != null) {
			com.deselectAllValues_Shuttle_DoubleClick(shuttle_HomeOperator_Right);
			com.selectByPartialVisibleText_DoubleClick_FromArray(shuttle_HomeOperator_Left, homeOperator);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			message += "homeOperator = " + Arrays.toString(homeOperator) + " | ";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data:" + message);
		com.click(button_Refresh, "button_Refresh");
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);

		if (com.waitForElementTobe_Visible(section_MaintainTrafficSegmentationRules)) {
			CustomReporter.report(STATUS.PASS, "section_Maintain Traffic Segmentation Rules is displayed");
		} else {
			CustomReporter.report(STATUS.FAIL, "Stopping the execution as the search results are not found");
			Assert.fail("Stopping the execution as the search results are not found");
		}
	}

	/**
	 * <pre>
	 * Verifies the validation of Maintain Traffic Segmentation P35 Page
	 * This page contains a GRID with 6 editable columns, types of fields in each cell is written below
	 *   1. Partner PMN >> Shuttle
	 *   2. APN >> input
	 *   3. IMSI RANGE START >> 14-15 digit Numeric input
	 *   4. IMSI RANGE END >> 14-15 digit Numeric input
	 *   5. Valid From >> date input field
	 *   6. Valid To >> date input field
	 *   
	 * Validations to cover :- 
	 *   1. Saving Blank in all mandatory fields 
	 *   3. IMSI values less than 14 digits, and non numeric
	 *      from value is not greater than to value
	 *   4. Valid from to date field - not following the date format
	 *   	non numeric values
	 *      from value is not greater than to value
	 * </pre>
	 * 
	 * @param jsonObjName
	 *            Name of JSON Object/Test Method name
	 */
	public void verify_Validations(String jsonObjName) {

		JSONManager json = new JSONManager(Module_AgreementCapture.jsonFilePath, jsonObjName);
		performSearch(json.getStrArr("homeOpArr"));

		/*
		 * When the page is loaded, on clicking Add Row, A new row is getting
		 * added in 2nd position
		 */
		com.waitForElementTobe_Clickable(comm.button_AddRow_GRID);
		com.click(comm.button_AddRow_GRID, "Add Row Button");

		/*
		 * Verifying the validations
		 */
		json = new JSONManager(Module_AgreementCapture.jsonFilePath, jsonObjName, "validations", "AllBlank");
		fillValueAndCheckValidation(json, "AllBlank");
		
		json = new JSONManager(Module_AgreementCapture.jsonFilePath, jsonObjName, "validations", "imsiStartNonNumeric");
		fillValueAndCheckValidation(json, "imsiStartNonNumeric");

		json = new JSONManager(Module_AgreementCapture.jsonFilePath, jsonObjName, "validations", "imsiStartNon14Digit");
		fillValueAndCheckValidation(json, "imsiStartNon14Digit");
		
		json = new JSONManager(Module_AgreementCapture.jsonFilePath, jsonObjName, "validations", "imsiStartAndEndNon14Digit");
		fillValueAndCheckValidation(json, "imsiStartAndEndNon14Digit");
		
		json = new JSONManager(Module_AgreementCapture.jsonFilePath, jsonObjName, "validations", "imsiEndNon14Digit");
		fillValueAndCheckValidation(json, "imsiEndNon14Digit");
		
		json = new JSONManager(Module_AgreementCapture.jsonFilePath, jsonObjName, "validations", "imsiEndNon15Digit");
		fillValueAndCheckValidation(json, "imsiEndNon15Digit");
		
		json = new JSONManager(Module_AgreementCapture.jsonFilePath, jsonObjName, "validations", "imsiStartMoreThanEndNon14Digit");
		fillValueAndCheckValidation(json, "imsiStartMoreThanEndNon14Digit");

		json = new JSONManager(Module_AgreementCapture.jsonFilePath, jsonObjName, "validations", "imsiStartIs14DigitAndImsiEndIs15Digit");
		fillValueAndCheckValidation(json, "imsiStartIs14DigitAndImsiEndIs15Digit");
		
		json = new JSONManager(Module_AgreementCapture.jsonFilePath, jsonObjName, "validations", "imsiStartIs15DigitAndImsiEndIs14Digit");
		fillValueAndCheckValidation(json, "imsiStartIs15DigitAndImsiEndIs14Digit");
		
		json = new JSONManager(Module_AgreementCapture.jsonFilePath, jsonObjName, "validations", "validFromFormatCheck");
		fillValueAndCheckValidation(json, "validFromFormatCheck");
		
		json = new JSONManager(Module_AgreementCapture.jsonFilePath, jsonObjName, "validations", "validFromInvalidDateButProperFormat");
		fillValueAndCheckValidation(json, "validFromInvalidDateButProperFormat");
		
		json = new JSONManager(Module_AgreementCapture.jsonFilePath, jsonObjName, "validations", "validToFormatCheck");
		fillValueAndCheckValidation(json, "validToFormatCheck");
		
		json = new JSONManager(Module_AgreementCapture.jsonFilePath, jsonObjName, "validations", "validToInvalidDateButProperFormat");
		fillValueAndCheckValidation(json, "validToInvalidDateButProperFormat");
		
	}

	
	public static void main(String[] args) {
		JSONManager json = new JSONManager(Module_AgreementCapture.jsonFilePath, "T01_VerifyValidationsOnMaintainTrafficSegmentationRules_P35");
		System.out.println(json.getStr("validationsArr", 1, "imsiRangeStart"));
		
	}
	
	/**
	 * This method will fill the values in added row, and will click on Save
	 * button to verify the validation messages
	 * 
	 * @precon : A new should be added at 3rd position
	 * @postcon : Validation will be verified for passed json object
	 * 
	 * @param json
	 *            The JSON object which stores the validation message and test
	 *            data
	 * @param validation
	 *            it is just a message for creating a node in report
	 * 
	 * @author shailendra.rajawat 22 Mar 2019
	 */
	private void fillValueAndCheckValidation(JSONManager json, String validation) {
		int rowNum = 3;
		CustomReporter.createNode(validation);

		String validationMsgs[] = json.getStrArr("message");
		
		fillValuesInTheGRID(rowNum, json.getChildJSONObject("testData"));

		com.click(comm.button_Save_GRID, "Save button");
		
		if(! com.waitForElementTobe_NotVisible(comm.button_OK, 2) ){
			com.click(comm.button_OK);
			com.click(comm.button_Save_GRID);
		}
		

		for (int i = 0; i < validationMsgs.length; i++) {
			comm.verifyValidationMessage(validation, validationMsgs[i]);
		}
	}

	/**
	 * This method will fill the values passed from the json object
	 * 
	 * @param rowNum
	 *            The row num in which data is to be filled
	 * @param childObject
	 *            the json object from which data will be fetched
	 * 
	 * @precon GRID should be displayed, and should have the passed row
	 * @postcon One by one Data will the read from JSON and filled in the row
	 * @author shailendra.rajawat 22-Mar-2019
	 */
	private void fillValuesInTheGRID(int rowNum, JSONManager json) {

		String message = " | ";

		/*
		 * As Add Row button is clicked the shuttle should already be visible,
		 * so to make it hide, clicking on 3rd cell(APN) of passed row
		 */
		WebTable grid = new WebTable(comm.tab_Right_GRID_Body);
		
		com.click(grid.getCellObject(rowNum, 3));

		// Now filling in the value of APN from JSON
		String val = json.getStr("APN");
		if (val != null) {
			com.sendKeys(com.getInteractableWebElementFromList(comm.input_TextfieldVisible_GRID), val);
			message += " APN[" + val + "] | ";
		}

		val = json.getStr("imsiRangeStart");
		if (val != null) {
			comm.fillGRIDcellText(grid, rowNum, 4, val);
			message += " imsiRangeStart[" + val + "] | ";
		}

		val = json.getStr("imsiRangeEnd");
		if (val != null) {
			comm.fillGRIDcellText(grid, rowNum, 5, val);
			message += " imsiRangeEnd[" + val + "] | ";
		}

		val = json.getStr("validFrom");
		if (val != null) { 
			/*com.click(com.getInteractableWebElementFromList(By.xpath("//div[contains(@class,'GV')]/button[contains(@class,'date')]")));
			com.click(com.getInteractableWebElementFromList(By.xpath("//a[.='15']")));*/
			comm.fillGRIDcellText(grid, rowNum, 6, val);
			message += " validFrom[" + val + "] | ";
		}

		val = json.getStr("validTo");
		if (val != null) {
			comm.fillGRIDcellText(grid, rowNum, 7, val);
			message += " validTo[" + val + "] | ";
		}

		String[] valArr = json.getStrArr("partnerPMN");
		if (valArr != null) {
			com.doubleClick(grid.getCellObject(rowNum, 2));
			com.waitForElementTobe_Visible(comm.shuttle_RightVisible_GRID);
			com.deselectAllValues_Shuttle_DoubleClick(comm.shuttle_RightVisible_GRID);
			com.selectByPartialVisibleText_DoubleClick_FromArray(com.getInteractableWebElementFromList(comm.shuttle_LeftVisible_GRID), valArr);
			message += " partnerPMN[" + Arrays.toString(valArr) + "] | ";
		}

		CustomReporter.report(STATUS.INFO, "Filling up values in GRID "+message);
	}

}
