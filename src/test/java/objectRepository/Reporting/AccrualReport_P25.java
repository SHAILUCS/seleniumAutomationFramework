package objectRepository.Reporting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.common.ApexCommon;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import tests.MainRegression.HKT_AccrualReport_7490;

public class AccrualReport_P25 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.AccrualReport_P25.title;

	WebTable webTab_Head;
	WebTable webTab_Body;
	
	public AccrualReport_P25() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		webTab_Head=new WebTable(comm.table_ResultTabHeader);
		webTab_Body=new WebTable(comm.table_ResultTabData);
	}

	@FindBy(xpath = "//h1[.='Accrual Report']")
	private WebElement heading_AccrualReport;

	@FindBy(xpath = "//h2[.='Report Parameters']")
	private WebElement section_ReportParameters;
	
	@FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Report Parameters')]//button[contains(@class,'hideShow')]")
    private WebElement hideShowIcon_ReportParameters;
	
	@FindBy(id = "P25_CLIENT_MASTER_ENTITY_ID_LEFT")
	private WebElement shuttle_HomeOp_Left;
	
	@FindBy(id = "P25_CLIENT_MASTER_ENTITY_ID_RIGHT")
	private WebElement shuttle_HomeOp_Right;
	
	@FindBy(id = "P25_SERVICE_TYPE_ID")
	private WebElement select_ServiceType;
	
	private List<String> ServiceType_Values=new ArrayList<>(Arrays.asList("All","Voice","SMS","Data"));
	
	@FindBy(id = "P25_EVENT_TYPE_ID")
	private WebElement select_EventType;
	
	private List<String> EventType_Values=new ArrayList<>(Arrays.asList("All","MT","MO & MT","MO","MB"));
	
	@FindBy(id = "P25_TRAFFIC_DIRECTION")
	private WebElement select_TrafficDirection;
	
	private List<String> TrafficDirection_Values=new ArrayList<>(Arrays.asList("Customer/Outbound","Visitor/Inbound","Bi-Directional"));

	@FindBy(id = "P25_ORIGINAL_LATEST_0")
	private WebElement radio_InfoSet_Latest;

	@FindBy(id = "P25_ORIGINAL_LATEST_1")
	private WebElement radio_InfoSet_Original;
	
	@FindBy(id = "P25_TRAFFIC_PERIOD_ID")
	private WebElement select_TrafficPeriod;
	
	@FindBy(id = "P25_PMN_LEVEL_0")
	private WebElement checkbox_PMNBreakdown;
	
	@FindBy(xpath = "//button[.='Refresh Report']")
	private WebElement button_RefreshReport;
	
	@FindBy(xpath = "//button[.='Reset Page']")
	private WebElement button_ResetPage;
	
	@FindBy(xpath = "//button[.='Download']")
	private WebElement button_Download;

	@FindBy(xpath = "//a[.='Accrual report in agreement currency']")
	private WebElement tab_AgrCurr;

	
	private List<String> AgrCurr_ColNames = new ArrayList<>(Arrays.asList("Home Operator Name", "Partner Group Name",
			"Country", "Partner PMN", "Agreement Reference", "Legal Contract ID", "Agreement Start Date",
			"Agreement End Date", "Direction", "Agreement Currency", "MOC Local Traffic Volume",
			"MOC Local TAP Charges Excluding Tax", "MOC Local Discounted Charges Excluding Tax", "MOC Local Discount",
			"MOC Home Traffic Volume", "MOC Home TAP Charges Excluding Tax",
			"MOC Home Discounted Charges Excluding Tax", "MOC Home Discount", "MOC International Traffic Volume",
			"MOC International TAP Charges Excluding Tax", "MOC International Discounted Charges Excluding Tax",
			"MOC International Discount", "MTC Traffic Volume", "MTC TAP Charges Excluding Tax",
			"MTC Discounted Charges Excluding Tax", "MTC Discount", "SMS Traffic Volume",
			"SMS TAP Charges Excluding Tax", "SMS Discounted Charges Excluding Tax", "SMS Discount",
			"Data Traffic Volume", "Data TAP Charges Excluding Tax", "Data Discounted Charges Excluding Tax",
			"Data Discount", "Commitment flag", "IOT update indicator"));
	
	@FindBy(xpath = "//a[.='Accrual report in home currency']")
	private WebElement tab_HomeCurr;
	

	private List<String> HomeCurr_ColNames = new ArrayList<>(Arrays.asList("Home Operator Name", "Partner Group Name",
			"Country", "Partner PMN", "Agreement Reference", "Legal contract id", "Agreement Start Date",
			"Agreement End Date", "Direction", "Agreement Currency", "FX to Home Currency", "MOC Local Traffic Volume",
			"MOC Local TAP Charges Excluding Tax", "MOC Local Discounted Charges Excluding Tax", "MOC Local Discount",
			"MOC Home Traffic Volume", "MOC Home TAP Charges Excluding Tax",
			"MOC Home Discounted Charges Excluding Tax", "MOC Home Discount", "MOC International Traffic Volume",
			"MOC International TAP Charges Excluding Tax", "MOC International Discounted Charges Excluding Tax",
			"MOC International Discount", "MTC Traffic Volume", "MTC TAP Charges Excluding Tax",
			"MTC Discounted Charges Excluding Tax", "MTC Discount", "SMS Traffic Volume",
			"SMS TAP Charges Excluding Tax", "SMS Discounted Charges Excluding Tax", "SMS Discount",
			"Data Traffic Volume", "Data TAP Charges Excluding Tax", "Data Discounted Charges Excluding Tax",
			"Data Discount", "Commitment flag", "IOT update indicator"));
	
	@FindBy(xpath = "//a[.='Accrual report with current month data']")
	private WebElement tab_CurrMonData;

	private List<String> CurrMonData_colNames = new ArrayList<>(Arrays.asList("Traffic Period", "Home Operator Name",
			"Partner Group Name", "Country", "Partner PMN", "Agreement Reference", "Legal contract id",
			"Agreement Start Date", "Agreement End Date", "Direction", "Agreement Currency", "FX to home currency",
			"MOC Local Traffic Volume", "MOC Local TAP Charges Excluding Tax",
			"MOC Local Discounted Charges Excluding Tax", "MOC Local Discount", "MOC Home Traffic Volume",
			"MOC Home TAP Charges Excluding Tax", "MOC Home Discounted Charges Excluding Tax", "MOC Home Discount",
			"MOC International Traffic Volume", "MOC International TAP Charges Excluding Tax",
			"MOC International Discounted Charges Excluding Tax", "MOC International Discount", "MTC Traffic Volume",
			"MTC TAP Charges Excluding Tax", "MTC Discounted Charges Excluding Tax", "MTC Discount",
			"SMS Traffic Volume", "SMS TAP Charges Excluding Tax", "SMS Discounted Charges Excluding Tax",
			"SMS Discount", "Data Traffic Volume", "Data TAP Charges Excluding Tax",
			"Data Discounted Charges Excluding Tax", "Data Discount", "Commitment flag", "IOT update indicator"));

	public void verify_UI() {
		CustomReporter.createNode("Search parameters verification");
		
		com.isElementPresent(heading_AccrualReport, "heading_Accrual Report");
		com.isElementPresent(section_ReportParameters, "section_Report Parameters");
		com.isElementPresent(hideShowIcon_ReportParameters, "hide Show Icon_Report Parameters");
		com.isElementPresent(shuttle_HomeOp_Left, "shuttle_Home Op_Left");
		com.isElementPresent(shuttle_HomeOp_Right, "shuttle_Home Op_Right");
		com.isElementPresent(shuttle_HomeOp_Right, "shuttle_Home Op_Right");
		
		if(com.isElementPresent(select_ServiceType, "select_Service Type")){
			List<String> list_Actual=com.getAllOptionsVisibleText(select_ServiceType);
			Util.comparator_List_NonNumbers(ServiceType_Values, list_Actual, "Service Type");
		}
		
		if(com.isElementPresent(select_EventType, "select_Event Type")){
			List<String> list_Actual=com.getAllOptionsVisibleText(select_EventType);
			Util.comparator_List_NonNumbers(EventType_Values, list_Actual, "Event Type");
		}
		
		if(com.isElementPresent(select_TrafficDirection, "select_Traffic Direction")){
			List<String> list_Actual=com.getAllOptionsVisibleText(select_TrafficDirection);
			Util.comparator_List_NonNumbers(TrafficDirection_Values, list_Actual, "Traffic Direction");
		}
		
		com.isElementPresent(radio_InfoSet_Latest, "radio_Info Set_Latest");
		com.isElementPresent(radio_InfoSet_Original, "radio_Info Set_Original");
		com.isElementPresent(select_TrafficPeriod, "select_Traffic Period");
		com.isElementPresent(checkbox_PMNBreakdown, "checkbox_PMN Breakdown");
		com.isElementPresent(button_RefreshReport, "button_Refresh Report");
		com.isElementPresent(button_ResetPage, "button_Reset Page");
		com.isElementPresent(button_Download, "button_Download");
		
		performSearch(Util.getArray("HKGTC"), null, null, null, true, "0117", false);

		CustomReporter.createNode("Accrual report in agreement currency TAB");
		if (com.isElementPresent(tab_AgrCurr, "Accrual report in agreement currency TAB")) {
			com.click(tab_AgrCurr, "tab_Agr Curr");
			//comm.printColumnHeaders(AgrCurr_Head, 1);
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, AgrCurr_ColNames, 1);
		}

		CustomReporter.createNode("Accrual report in home currency TAB");
		if (com.isElementPresent(tab_HomeCurr, "Accrual report in home currency TAB")) {
			com.click(tab_HomeCurr, "tab_Hom Curr");
			//comm.printColumnHeaders(HomeCurr_Head, 1);
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, HomeCurr_ColNames, 1);
		}
		
		CustomReporter.createNode("Accrual report with current month data TAB");
		if (com.isElementPresent(tab_CurrMonData, "Accrual report with current month data TAB")) {
			com.click(tab_CurrMonData, "tab_Curr Mon Data");
			//comm.printColumnHeaders(CurrMonData_Head, 1);
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, CurrMonData_colNames, 1);
		}
	}

	public void performSearch(JSONManager json) {
		performSearch(json.getStrArr("homeOp"), json.getStr("servType"), json.getStr("evType"), json.getStr("trfDir"),
				json.getBool("true_ForLatest"), json.getStr("trfPer"), json.getBool("chbx_PmnBreakdown"));
	}
	
	public void performSearch(String[] homeOp, String servType, String evType, String trfDir, boolean true_ForLatest,
			String trfPer, boolean chbx_PmnBreakdown) {
		String message="| ";
		
		if (homeOp!=null) {
			com.deselectAllValues_Shuttle_DoubleClick(shuttle_HomeOp_Right);
			com.selectByPartialVisibleText_DoubleClick_FromArray(shuttle_HomeOp_Left, homeOp);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			message=message+" Home Op: "+Arrays.toString(homeOp)+" | ";
		}

		if (servType!=null) {
			com.selectByVisibleText(select_ServiceType, servType);
			message=message+" Service Type: ["+servType+"] | ";
		}
		
		if (evType!=null) {
			com.selectByVisibleText(select_EventType, evType);
			message=message+" Event Type: ["+evType+"] | ";
		}
		
		if (trfDir!=null) {
			com.selectByVisibleText(select_TrafficDirection, trfDir);
			message=message+" Traff Dir: ["+trfDir+"] | ";
		}
		
		if (true_ForLatest) {
			com.javaScript_Click(radio_InfoSet_Latest);
			message=message+" Information Set : [Latest] | ";
		}else {
			com.javaScript_Click(radio_InfoSet_Original);
			message=message+" Information Set : [Original] | ";
		}
		
		if (trfPer!=null) {
			com.selectByVisibleText(select_TrafficPeriod, trfPer);
			message=message+" Traff Period: ["+trfPer+"] | ";
		}
		
		if (chbx_PmnBreakdown) {
			com.javaScript_Click(checkbox_PMNBreakdown);
			message=message+" PMN Breakdown: [Yes] | ";
		}
		
		com.click(button_RefreshReport);
		
		CustomReporter.report(STATUS.INFO, "Performing Search with data: "+message);
		
		if (com.isElementPresent(tab_AgrCurr)) {
			CustomReporter.report(STATUS.PASS, "Search results displayed");
		}else {
			CustomReporter.report(STATUS.FAIL, "Search results NOT displayed");
			Assert.fail("Search results NOT displayed");
		}
	}
	
	public void T05_7490_AccrRep_P25_Verify_CurrentMonthDataRep_With_OvRep(String methodName) {
		// First fetching the data for 0117 month
		// Second fetching the data for 0217 month
		// This data will be used later
		readAccrualReportData_PutInRunTimeJSON_COMMON(tab_CurrMonData,methodName, "customerOB", "0217");
		readAccrualReportData_PutInRunTimeJSON_COMMON(tab_CurrMonData,methodName, "visitorIB", "0217");
		
		CustomReporter.createNode("Navigating to Overview Report to verify the values");
		
		Navigator nav=new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();
		nav.traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, h.link_Reporting, h.link_Reporting_OverviewReport);
		
		OverviewReport_P507 p507=new OverviewReport_P507();
		p507.T05_7490_AccrRep_P25_Verify_CurrentMonthDataRep_With_OvRep(methodName);
		

	}

	public void T04_AccrRep_P25_Verify_HomeCurrencyRep_With_OvRep(String methodName) {

		// First fetching the data for 0117 month
		// Second fetching the data for 0217 month
		// This data will be used later
		readAccrualReportData_PutInRunTimeJSON_COMMON(tab_HomeCurr,methodName, "customerOB", "0117");
		readAccrualReportData_PutInRunTimeJSON_COMMON(tab_HomeCurr,methodName, "customerOB", "0217");

		readAccrualReportData_PutInRunTimeJSON_COMMON(tab_HomeCurr,methodName, "visitorIB", "0117");
		readAccrualReportData_PutInRunTimeJSON_COMMON(tab_HomeCurr,methodName, "visitorIB", "0217");

		
		CustomReporter.createNode("Navigating to Overview Report to verify the values");
		
		Navigator nav=new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();
		nav.traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, h.link_Reporting, h.link_Reporting_OverviewReport);
		
		OverviewReport_P507 p507=new OverviewReport_P507();
		p507.T04_7490_AccrRep_P25_Verify_HomeCurrencyRepData_With_OvRep(methodName);
	}
	
	//TODO readAccrualReportData_PutInRunTimeJSON_COMMON
	private void readAccrualReportData_PutInRunTimeJSON_COMMON(Object tabObj,String methodName, String obIb, String trfPer) {
		
		CustomReporter.createNode("Getting the values from Accrual Report for "+obIb+" and "+trfPer+" month");
		
		JSONManager json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName);
		String agreementRef=json.getStr("agreementRef");
		
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName,"accrualReport",obIb,trfPer);
		
		performSearch(json);
		
		com.click(tabObj, com.getText(tabObj));
		
		comm.performSearch(agreementRef, "Agreement Reference");
		
		//Reading the column values and putting them in json for futher verification with overview report
		webTab_Head.initHeaderColumnList(1);

		//In case we are using agreement currency Table, then Fx Rate column will not be available
		String tableLocator=com.getByObjectFromWebElement(tabObj).toString();
		if (!tableLocator.toLowerCase().contains("agreement currency")) {
			CustomReporter.createNode(" >> Fx Rate");
			json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName, "runTimeData", trfPer, obIb);
			String data=webTab_Body.getCellText(2, webTab_Head.getColumnNumber("FX to Home Currency"));
			CustomReporter.report(STATUS.INFO, "For Col [FX to Home Currency]"+" displayed data ["+data+"]");
			json.put("FX to Home Currency", data);
		}
		
		CustomReporter.createNode(" >> VOICE MO >> Local");
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName, "runTimeData", trfPer, obIb,"voiceMO","Local");
		readTableData_Then_PutInJSON(webTab_Body, webTab_Head, json);

		CustomReporter.createNode(" >> VOICE MO >> Home");
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath,  methodName, "runTimeData", trfPer, obIb,"voiceMO","Home");
		readTableData_Then_PutInJSON(webTab_Body, webTab_Head, json);
		
		CustomReporter.createNode(" >> VOICE MO >> International");
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName, "runTimeData", trfPer, obIb,"voiceMO","International");
		readTableData_Then_PutInJSON(webTab_Body, webTab_Head, json);

		CustomReporter.createNode(" >> VOICE MT");
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName, "runTimeData", trfPer, obIb,"voiceMT");
		readTableData_Then_PutInJSON(webTab_Body, webTab_Head, json);

		CustomReporter.createNode(" >> SMS");
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName, "runTimeData", trfPer, obIb,"sms");
		readTableData_Then_PutInJSON(webTab_Body, webTab_Head, json);
		
		CustomReporter.createNode(" >> DATA");
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName, "runTimeData", trfPer, obIb,"data");
		readTableData_Then_PutInJSON(webTab_Body, webTab_Head, json);
	}

	public void T02_AccrRep_P25_Verify_AgreementCurrencyRepData_With_OvRep(String testName) {
		
		CustomReporter.createNode("Getting the values from Accrual Report");
		
		readAccrualReportData_PutInRunTimeJSON_COMMON(tab_AgrCurr,testName, "customerOB","0117");
		
		readAccrualReportData_PutInRunTimeJSON_COMMON(tab_AgrCurr,testName, "visitorIB","0117");
		
		CustomReporter.createNode("Navigating to Overview Report to verify the values");
		
		Navigator nav=new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();
		nav.traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, h.link_Reporting, h.link_Reporting_OverviewReport);
		
		OverviewReport_P507 p507=new OverviewReport_P507();
		p507.T02_7490_AccrRep_P25_Verify_AgreementCurrencyRepData_With_OvRep(testName);
		
	}
	
	private void readAccrualReportData_PutInRunTimeJSON(String methodName,String obIb){
		JSONManager json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName);
		String agreementRef=json.getStr("agreementRef");
		
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName,"accrualReport",obIb);
		
		performSearch(json.getStrArr("homeOp"), json.getStr("servType"), json.getStr("evType"), json.getStr("trfDir"),
				json.getBool("true_ForLatest"), json.getStr("trfPer"), json.getBool("chbx_PmnBreakdown"));
		
		comm.performSearch(agreementRef, "Agreement Reference");
		
		//Reading the column values and putting them in json for futher verification with overview report
		webTab_Head.initHeaderColumnList(1);
		
		CustomReporter.createNode("FOR "+obIb+" >> VOICE MO >> Local");
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName,"accrualReport",obIb,"runTimeData","voiceMO","Local");
		readTableData_Then_PutInJSON(webTab_Body, webTab_Head, json);

		CustomReporter.createNode("FOR "+obIb+" >> VOICE MO >> Home");
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName,"accrualReport",obIb,"runTimeData","voiceMO","Home");
		readTableData_Then_PutInJSON(webTab_Body, webTab_Head, json);
		
		CustomReporter.createNode("FOR "+obIb+" >> VOICE MO >> International");
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName,"accrualReport",obIb,"runTimeData","voiceMO","International");
		readTableData_Then_PutInJSON(webTab_Body, webTab_Head, json);

		CustomReporter.createNode("FOR "+obIb+" >> VOICE MT");
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName,"accrualReport",obIb,"runTimeData","voiceMT");
		readTableData_Then_PutInJSON(webTab_Body, webTab_Head, json);

		CustomReporter.createNode("FOR "+obIb+" >> SMS");
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName,"accrualReport",obIb,"runTimeData","sms");
		readTableData_Then_PutInJSON(webTab_Body, webTab_Head, json);
		
		CustomReporter.createNode("FOR "+obIb+" >> DATA");
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName,"accrualReport",obIb,"runTimeData","data");
		readTableData_Then_PutInJSON(webTab_Body, webTab_Head, json);
	}
	
	private void readTableData_Then_PutInJSON(WebTable tabData,WebTable tabHead,JSONManager json){
		Set<String> columnNames=json.keySet();
		for (String colName : columnNames) {
			String data=tabData.getCellText(2, tabHead.getColumnNumber(colName));
			CustomReporter.report(STATUS.INFO, "For Col ["+colName+"]"+" displayed data ["+data+"]");
			json.put(colName, data);
		}
	}
	
	public void AccrRep_P25_Verify_DownloadButtonsFunc(String methodName) {
		JSONManager json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName);
		String agreementRef=json.getStr("agreementRef");
		json=new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName);
		performSearch(json.getStrArr("homeOp"), json.getStr("servType"), json.getStr("evType"), json.getStr("trfDir"),
				json.getBool("true_ForLatest"), json.getStr("trfPer"), json.getBool("chbx_PmnBreakdown"));
		
		CustomReporter.createNode("Verifying CSV & HTM Files Download func of Agreement Currency Tab");
		
		comm.performSearch(agreementRef, "Agreement Reference");
		
		comm.downloadCSV();

		comm.downloadHTML();
		
		comm.downloadEMAIL(Constant.EMAIL, "Automation Testing");
		CustomReporter.report(STATUS.WARNING, "Test Email account required");
		
		Util.verifyFile_Downloaded("accrual_report_in_agreement_currency.csv", "CSV");
		
		Util.verifyFile_Downloaded("accrual_report_in_agreement_currency.htm", "HTM");
		
		CustomReporter.createNode("Verifying CSV & HTM Files Download func of Home Currency Tab");
		
		com.click(tab_HomeCurr, "Home Currency Tab");
		
		comm.performSearch(agreementRef, "Agreement Reference");
		
		comm.downloadCSV();

		comm.downloadHTML();
		
		comm.downloadEMAIL(Constant.EMAIL, "Automation Testing");
		CustomReporter.report(STATUS.WARNING, "Test Email account required");
		
		Util.verifyFile_Downloaded("accrual_report_in_home_currency.csv", "CSV");
		
		Util.verifyFile_Downloaded("accrual_report_in_home_currency.htm", "HTM");
		
		CustomReporter.createNode("Verifying CSV & HTM Files Download func of Current Month Data Tab");
		
		com.click(tab_CurrMonData, "Current Month Data Tab");
		
		comm.performSearch(agreementRef,"Agreement Reference");
		
		comm.downloadCSV();

		comm.downloadHTML();
		
		comm.downloadEMAIL(Constant.EMAIL, "Automation Testing");
		CustomReporter.report(STATUS.WARNING, "Test Email account required");
		
		Util.verifyFile_Downloaded("accrual_report_with_current_month_data.csv", "CSV");
		
		Util.verifyFile_Downloaded("accrual_report_with_current_month_data.htm", "HTML");
		
		CustomReporter.createNode("Verifying xls File Download func of All Tabs data");
		
		com.click(button_Download, "Download xls button");
		
		Util.verifyFile_Downloaded("accrual_report.xlsx", "XLSX");
		
	}
	
}