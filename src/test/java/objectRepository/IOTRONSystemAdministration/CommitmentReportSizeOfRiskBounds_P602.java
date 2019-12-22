package objectRepository.IOTRONSystemAdministration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Util;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.common.ApexCommon;


/**
 * CommitmentReportSizeOfRiskBounds_P602.java
 * This is a Page Factory Class of "Commitment Report Size of Risk Bounds" Page P602
 * This class will store the Objects and methods that will work with those objects
 * @author shailendra.rajawat 09-Jan-19
 * */
public class CommitmentReportSizeOfRiskBounds_P602 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Commitment Report Size of Risk Bounds";

	public CommitmentReportSizeOfRiskBounds_P602() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	public void verify_UI() {

	}

	By tabBy=By.cssSelector("table[summary='Commitment Report Size of Risk Definition']");

	@FindBy(xpath = "//button[.='Submit']")
	private WebElement button_Submit;

	@FindBy(xpath = "//button[.='Cancel']")
	private WebElement button_Cancel;


	/**
	 * @author shailendra.rajawat 9-Jan-19
	 * <pre>
	 * It will <b>MODIFY</b> Lower and Upper Risk Bounds, Row by row
	 *  then it will submit the changes thus navigate back to P413 
	 *  Precon: P602 should be loaded
	 *  Postcon: After MODIFICATIONS of Risk Bounds, app will navigate to P413
	 *  @param modifyJson This json will hold the data of Lower and Upper bounds of 
	 *  Small,Medium,Large and Critical Risks
	 * </pre>
	 * */
	public void modifyBounds(JSONManager modifyJson) {

		//SMALL
		fillRowData("Small",modifyJson.getStr("small-lower"),modifyJson.getStr("small-upper"));

		//MEDIUM
		fillRowData("Medium",modifyJson.getStr("medium-lower"),modifyJson.getStr("medium-upper"));

		//LARGE
		fillRowData("Large",modifyJson.getStr("large-lower"),modifyJson.getStr("large-upper"));

		//CRITICAL
		fillRowData("Critical",modifyJson.getStr("critical-lower"),modifyJson.getStr("critical-upper"));

		//SAVING THE DATA
		com.click(button_Submit, "Submit Button");
		com.verifyPageTitle(CreateEditIOTClient_P413.title, true);
		comm.closeApexAlert_SuccessMessagePopup("updated");
	}

	public void modifyBounds(String[] riskType,String[] lower,String[] upper) {
		//CRITICAL
		for (int i = 0; i < upper.length; i++) {
			fillRowData(riskType[i],lower[i],upper[i]);
		}

		//SAVING THE DATA
		com.click(button_Submit, "Submit Button");
		com.verifyPageTitle(CreateEditIOTClient_P413.title, true);
	}

	/**
	 * @author shailendra.rajawat 9-Jan-19
	 * <pre>
	 * It will Fill Lower and Upper Risk Bounds for the passed Row
	 *  Precon: P602 should be loaded, and table should be visible
	 *  Postcon: Lower and Upper Bound of Row will be filled
	 *  @param riskType This may be any one of Small,Medium,Large or Critical 
	 *  @param lower This is the numeric value 
	 *  @param upper This is the numeric value 
	 * </pre>
	 * */
	private void fillRowData(String riskType,String lower,String upper) {
		WebTable tab=new WebTable(tabBy);
		tab.initHeaderColumnList(1);
		int colLower = tab.getColumnNumber("Lower Bound");
		int colUpper = tab.getColumnNumber("Upper Bound");


		int row=tab.getRowWithCellText(riskType);
		if(lower!=null){
			com.sendKeys(tab.getChildObject(row, colLower, "input", 0), lower);
		}

		if(upper!=null){
			com.sendKeys(tab.getChildObject(row, colUpper, "input", 0), upper);
		}
	}

	/**
	 * @author shailendra.rajawat 9-Jan-19
	 * <pre>
	 * It will MATCH Lower and Upper Risk Bounds for the passed Row
	 *  Precon: P602 should be loaded, and table should be visible
	 *  Postcon: Data of Lower and Upper Bound of Row will be matched
	 *  @param riskType This may be any one of Small,Medium,Large or Critical 
	 *  @param lower This is the numeric value 
	 *  @param upper This is the numeric value 
	 * </pre>
	 * */
	private void matchRowData(String riskType, String lower, String upper) {
		WebTable tab=new WebTable(tabBy);
		tab.initHeaderColumnList(1);
		int colLower = tab.getColumnNumber("Lower Bound");
		int colUpper = tab.getColumnNumber("Upper Bound");


		int row=tab.getRowWithCellText(riskType);
		String txt=com.getAttribute(tab.getChildObject(row, colLower, "input", 0), "value");
		Util.comparator_NonNumbers(lower, txt, "Lower value of "+riskType+" Risk Bound");

		txt=com.getAttribute(tab.getChildObject(row, colUpper, "input", 0), "value");
		Util.comparator_NonNumbers(upper, txt, "Upper value of "+riskType+" Risk Bound");
	}


	/**
	 * @author shailendra.rajawat 9-Jan-19
	 * <pre>
	 * It will <b>VERIFY</b> Lower and Upper Risk Bounds, Row by row
	 *  then it will navigate back to P413 
	 *  Precon: P602 should be loaded
	 *  Postcon: After VERIFICATON of Risk Bounds, app will navigate to P413
	 *  @param olderDataJson This json will hold the Previously filled data of Lower and Upper bounds of 
	 *  Small,Medium,Large and Critical Risks 
	 * </pre>
	 * */
	public void verifyBounds(JSONManager olderDataJson) {
		//SMALL
		matchRowData("Small",olderDataJson.getStr("small-lower"),olderDataJson.getStr("small-upper"));

		//MEDIUM
		matchRowData("Medium",olderDataJson.getStr("medium-lower"),olderDataJson.getStr("medium-upper"));

		//LARGE
		matchRowData("Large",olderDataJson.getStr("large-lower"),olderDataJson.getStr("large-upper"));

		//CRITICAL
		matchRowData("Critical",olderDataJson.getStr("critical-lower"),olderDataJson.getStr("critical-upper"));

		//SAVING THE DATA
		com.click(button_Cancel, "Cancel Button");
		com.verifyPageTitle(CreateEditIOTClient_P413.title, true);
	}

	/**
	 * This method will read all the lower and upper bounds Risk bounds, then store them in the JSON
	 * @author shailendra.rajawat 22 Jan 2019
	 * @param json the jsonManager object in which the data needs to be stored
	 * Precon: P602 should be loaded, and jsonManager object should point to proper json Object
	 * Postcon: P602 will be displayed, data will be stored in the json file for future reference   
	 * */
	public void storeBoundsInJsonFile(JSONManager json) {
		// SMALL
		storeRowDataInJson(json, "Small", "small-lower", "small-upper");

		// MEDIUM
		storeRowDataInJson(json, "Medium", "medium-lower", "medium-upper");

		// LARGE
		storeRowDataInJson(json, "Large", "large-lower", "large-upper");

		// CRITICAL
		storeRowDataInJson(json, "Critical", "critical-lower", "critical-upper");
	}

	/**
	 * <pre>
	 * It will READ and STORE Lower and Upper Risk Bounds for the passed riskType
	 *  Precon: P602 should be loaded, and table should be visible
	 *  Postcon: Data of Lower and Upper Bound of Row will be read and stored in json
	 *  @param json The jsonManager object which will hold the data
	 *  @param riskType This may be any one of Small,Medium,Large or Critical 
	 *  @param lower This is the numeric value 
	 *  @param upper This is the numeric value 
	 * </pre>
	 * @author shailendra.rajawat 22-Jan-19
	 * */
	private void storeRowDataInJson(JSONManager json, String riskType, String lower, String upper) {
		WebTable tab = new WebTable(tabBy);
		tab.initHeaderColumnList(1);
		int colLower = tab.getColumnNumber("Lower Bound");
		int colUpper = tab.getColumnNumber("Upper Bound");

		int row = tab.getRowWithCellText(riskType);
		String txt = com.getAttribute(tab.getChildObject(row, colLower, "input", 0), "value");
		json.put(lower, txt);
		txt = com.getAttribute(tab.getChildObject(row, colUpper, "input", 0), "value");
		json.put(upper, txt);
	}




}
