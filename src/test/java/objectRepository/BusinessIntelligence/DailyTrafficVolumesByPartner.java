package objectRepository.BusinessIntelligence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.jsonUtil.JSONManager;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.common.ApexCommon;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import tests.MainRegression.Module_KPN_Op4;

public class DailyTrafficVolumesByPartner {
	private SeleniumMethods com;
	public static String title = "Daily Traffic Volumes by Partner";
	private ApexCommon comm;
	WebTable tabData;
	WebTable tabHead;

	public DailyTrafficVolumesByPartner() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		tabData = new WebTable(comm.table_ResultTabData);
		tabHead = new WebTable(comm.table_ResultTabHeader);
	}

	@FindBy(xpath = "//h2[contains(.,'Home Operator')]")
	private WebElement data_Section_HomeOperator;

	@FindBy(xpath = "//h2[contains(.,'Reporting Parameters')]")
	private WebElement data_Section_ReportingParameters;

	@FindBy(xpath = "//h2[contains(.,'by Partner Charts')]")
	private WebElement data_Section_Charts;

	@FindBy(xpath = "//h2[contains(.,'by Partner Report')]")
	private WebElement data_Section_Report;

	@FindBy(xpath = "//h2[contains(.,'by Partner Report')]//..//preceding-sibling::div//button")
	private WebElement icon_Plus_ReportSection;

	@FindBy(id = "P686_HOME_MASTER_ENTITY_ID_LEFT")
	private WebElement select_HomeOperator_Left;

	@FindBy(id = "P686_HOME_MASTER_ENTITY_ID_RIGHT")
	private WebElement select_HomeOperator_Right;

	@FindBy(id = "P686_ROAMING_PARTNER_LEFT")
	private WebElement select_RoamingPartner_Left;

	@FindBy(id = "P686_ROAMING_PARTNER_RIGHT")
	private WebElement select_RoamingPartner_Right;

	@FindBy(id = "P686_REPORTING_START_DATE")
	private WebElement text_StartDate;

	@FindBy(id = "P686_REPORTING_END_DATE")
	private WebElement text_EndDate;

	@FindBy(id = "P686_TRAFFIC_DIRECTION")
	private WebElement select_TraffDir;

	@FindBy(id = "P686_SERVICE_TYPE")
	private WebElement select_ServType;

	@FindBy(id = "P686_EVENT_TYPE")
	private WebElement select_EvType;

	@FindBy(id = "P686_ACTUAL_BILLED_0")
	private WebElement radio_Actual;

	@FindBy(id = "P686_ACTUAL_BILLED_1")
	private WebElement radio_Billed;

	@FindBy(id = "P686_INCLUDE_SS_0")
	private WebElement checkbox_IncludeSpecialService;

	@FindBy(id = "P686_INCLUDE_NATIONAL_ROAMING_0")
	private WebElement checkbox_IncludeNatRoam;

	@FindBy(xpath = "//button[.='Refresh']")
	private WebElement button_Refresh;

	private List<String> columnList = new ArrayList<String>(
			Arrays.asList("Home Operator Ascending", "Roaming Partner", "Traffic Direction",
					"Service Type", "Event Type", "Call Date", "Traffic Volume"));

	private List<String> sortingOrder = new ArrayList<>(Arrays.asList("Home Operator:Ascending",
			"Roaming Partner:Ascending", "Traffic Direction:Ascending", "Service Type:Descending",
			"Event Type:Ascending", "Call Date:Ascending"));


	public void verifyUIContent(String methodName) {
		JSONManager json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName);

		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_Section_HomeOperator, "Home Operator Section");
		com.isElementPresent(data_Section_ReportingParameters, "Reporting Parameters Section");


		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(select_HomeOperator_Left, "Home Operator Left shuttle");
		com.isElementPresent(select_HomeOperator_Right, "Home Operator Right shuttle");
		com.isElementPresent(select_RoamingPartner_Left, "Roaming Partner Left shuttle");
		com.isElementPresent(select_RoamingPartner_Right, "Roaming Partner Right shuttle");

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat form = new SimpleDateFormat("dd-MMM-yy");
		if (com.isElementPresent(text_EndDate, "Reporting End Date field")) {
			String pageval = com.getAttribute(text_EndDate, "value");
			String tempDate = form.format(cal.getTime());
			Util.comparator_PageValues(tempDate, pageval, "Reporting End Date");
		}
		if (com.isElementPresent(text_StartDate, "Reporting Start Date field")) {
			cal.add(Calendar.MONTH, -1);
			String pageval = com.getAttribute(text_StartDate, "value");
			String tempDate = form.format(cal.getTime());
			Util.comparator_PageValues(tempDate, pageval, "Reporting Start Date");
		}

		com.isElementPresent(select_TraffDir, "Traffic Direction dropdown");
		com.isElementPresent(select_ServType, "Service Type dropdown");
		com.isElementPresent(select_EvType, "Event Type dropdown");
		com.isElementPresent(radio_Actual, "Actual radio option");
		com.isElementPresent(radio_Billed, "Billed radio option");

		com.isElementPresent(checkbox_IncludeSpecialService, "Include Special Service checkbox");
		com.isElementPresent(checkbox_IncludeNatRoam, "Include National Roaming checkbox");

		com.isElementPresent(button_Refresh, "Refresh button");

		if(performSearch(json.getStr("HomeOp"), json.getStr("RoamPart"), json.getStr("repStartDate"),
				json.getStr("repEndDate"), json.getStr("traffDir"), json.getStr("servType"), json.getStr("evType"),
				json.getBool("radio_actualBilled"), json.getBool("check_includeSpSer"),
				json.getBool("check_includeNatRoam"))){

			com.isElementPresent(data_Section_Charts, "Charts Section");
			com.isElementPresent(data_Section_Report, "Report Section");
			if (com.isElementPresent(icon_Plus_ReportSection, "Report Section Expand icon")) {
				if (com.getAttribute(icon_Plus_ReportSection, "aria-expanded").equalsIgnoreCase("true")) {
					CustomReporter.report(STATUS.PASS, "By default Report Section is displayed as maximized");
				}else {
					CustomReporter.report(STATUS.FAIL, "By default Report Section is NOT displayed as maximized");
				}
			} 

		}
	}


	public void verifyReportRegion(String methodName) {
		JSONManager json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName);
		if(performSearch(json)){

			comm.verifyColumnHeaders(comm.table_ResultTabHeader, columnList, 1);
			List<String> pageSortingOrder = comm.getSortingOrder_ColonSeparated();
			Util.comparator_List_NonNumbers(sortingOrder, pageSortingOrder, "Sorting Order");

		}

	}


	public void fillBasicSearchFields(String HomeOp, String RoamPart, String repStartDate,
			String repEndDate, String traffDir, String servType, String evType,
			boolean radio_actualBilled, boolean check_includeSpSer, boolean check_includeNatRoam) {

		String message="";
		if (HomeOp != null) {
			com.deselectAllValues_Shuttle_DoubleClick(select_HomeOperator_Right);
			com.selectByPartialVisibleText_DoubleClick_FromCommaSeparated(select_HomeOperator_Left, HomeOp);
			message=message+"| HomeOperators- '"+HomeOp+"'";
		}

		if (RoamPart != null){
			com.selectByPartialVisibleText_DoubleClick_FromCommaSeparated(select_RoamingPartner_Left, RoamPart);
			message=message+"| RoamingPartner- '"+RoamPart+"'";
		}

		if (repStartDate != null){
			com.sendKeys(text_StartDate, repStartDate);
			message=message+"| reportStartDate- '"+repStartDate+"'";
		}

		if (repEndDate != null){
			com.sendKeys(text_EndDate, repEndDate);
			message=message+"| reportEndDate- '"+repEndDate+"'";
		}

		if (traffDir != null){
			com.selectByPartialVisibleText(select_TraffDir, traffDir);
			message=message+"| trafficDirection- '"+traffDir+"'";
		}

		if (servType != null){
			com.selectByPartialVisibleText(select_ServType, servType);
			message=message+"| serviceType- '"+servType+"'";
		}

		if (evType != null){
			com.selectByPartialVisibleText(select_EvType, evType);
			message=message+"| eventType- '"+evType+"'";
		}

		if (radio_actualBilled){
			com.click(radio_Billed);
			message=message+"| radio_actualBilled- '"+radio_actualBilled+"'";
		}

		if (check_includeSpSer){
			com.click(checkbox_IncludeSpecialService);
			message=message+"| check_includeSpSer- '"+check_includeSpSer+"'";
		}

		if (check_includeNatRoam){
			com.click(checkbox_IncludeNatRoam);
			message=message+"| check_includeNatRoam- '"+check_includeNatRoam+"'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: "+message);

	}

	private boolean performSearch(JSONManager json) {
		return performSearch(json.getStr("HomeOp"), json.getStr("RoamPart"), json.getStr("repStartDate"),
				json.getStr("repEndDate"), json.getStr("traffDir"), json.getStr("servType"), json.getStr("evType"),
				json.getBool("radio_actualBilled"), json.getBool("check_includeSpSer"),
				json.getBool("check_includeNatRoam"));
	}
	
	private boolean performSearch(String HomeOp, String RoamPart, String repStartDate,
			String repEndDate, String traffDir, String servType, String evType,
			boolean radio_actualBilled, boolean check_includeSpSer, boolean check_includeNatRoam) {

		fillBasicSearchFields(HomeOp, RoamPart, repStartDate, repEndDate,
				traffDir, servType, evType, radio_actualBilled, check_includeSpSer,
				check_includeNatRoam);
		com.click(button_Refresh);
		if (com.getAttribute(icon_Plus_ReportSection, "aria-expanded").equalsIgnoreCase("false")) {
			com.click(icon_Plus_ReportSection,"Report Section Expand Icon");
		}
		if (com.isElementPresent(comm.table_ResultTabHeader)) {
			return true;
		}
		return false;
	}


	public void verifyTrafficVolumeColumnData(String methodName) {

		CustomReporter.createNode("For OB Voice MO - Traf Vol");
		
		String CB_VoiceMo_TrafVol = null, CB_SmsMo_TrafVol = null, CB_DataMb_TrafVol = null;
		String IB_VoiceMo_TrafVol = null, IB_SmsMo_TrafVol = null, IB_DataMb_TrafVol = null;
		
		JSONManager json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName,"OBVoiceMO");
		if(performSearch(json)){

			tabHead.initHeaderColumnList(1);

			CustomReporter.report(STATUS.WARNING, "'Traffic Volume' Column is 'Trafiic Volume' on Page: "+title);
			String traffVolColName="Traffic Volume";
			int trafVolCol = tabHead.getColumnNumber(traffVolColName);
			comm.setRowsPerPage("All");
			comm.applyAggregateFunction("Sum", traffVolColName);
			int lastRow = tabData.getRowCount();
			CB_VoiceMo_TrafVol = tabData.getCellText(lastRow, trafVolCol);
			CustomReporter.report(STATUS.INFO, "CB_VoiceMo_TrafVol ="+CB_VoiceMo_TrafVol);
			
			CustomReporter.createNode("For OB Sms MO - Traf Vol");
			
			json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName,"OBSmsMO");
			if(performSearch(json)){
				lastRow = tabData.getRowCount();
				CB_SmsMo_TrafVol = tabData.getCellText(lastRow, trafVolCol);
				CustomReporter.report(STATUS.INFO, "CB_SmsMo_TrafVol ="+CB_SmsMo_TrafVol);
			}
			
			CustomReporter.createNode("For OB Data MB - Traf Vol");

			json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName,"OBDataMb");
			if(performSearch(json)){
				lastRow = tabData.getRowCount();
				CB_DataMb_TrafVol = tabData.getCellText(lastRow, trafVolCol);
				CustomReporter.report(STATUS.INFO, "CB_DataMb_TrafVol ="+CB_DataMb_TrafVol);
			}
			
			CustomReporter.createNode("For IB Voice MO - Traf Vol");

			json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName,"IBVoiceMo");
			if(performSearch(json)){
				lastRow = tabData.getRowCount();
				IB_VoiceMo_TrafVol = tabData.getCellText(lastRow, trafVolCol);
				CustomReporter.report(STATUS.INFO, "IB_VoiceMo_TrafVol ="+IB_VoiceMo_TrafVol);
			}

			CustomReporter.createNode("For IB Sms MO - Traf Vol");
			
			json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName,"IBSmsMo");
			if(performSearch(json)){
				lastRow = tabData.getRowCount();
				IB_SmsMo_TrafVol = tabData.getCellText(lastRow, trafVolCol);
				CustomReporter.report(STATUS.INFO, "IB_SmsMo_TrafVol ="+IB_SmsMo_TrafVol);
			}
			
			CustomReporter.createNode("For IB Data MB - Traf Vol");

			json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName,"IBDataMb");
			if(performSearch(json)){
				lastRow = tabData.getRowCount();
				IB_DataMb_TrafVol = tabData.getCellText(lastRow, trafVolCol);
				CustomReporter.report(STATUS.INFO, "IB_DataMb_TrafVol ="+IB_DataMb_TrafVol);
			}

			CustomReporter.createNode("Navigating to 'Daily Traffic Analysis Report' page for VERIFYING traf vol");
			
			Navigator navigate= new Navigator();
			IOTRONHomePage ihp=new IOTRONHomePage();

			navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(
					PagesTitle.DailyTrafficAnalysisReport, ihp.link_BusinessIntelligence,
					ihp.link_BusinessIntelligence_DailyDCHLevelReporting,
					ihp.link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficAnalysisReport);
			DailyTrafficAnalysisReport dailyTrafficAnalysisReport = new DailyTrafficAnalysisReport();
			json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName, "OBVoiceMO");
			
			dailyTrafficAnalysisReport.verifyTrafficVolumeDataWith_DailyTrafficVolumesByPartnerPage(
					json,CB_VoiceMo_TrafVol, CB_SmsMo_TrafVol, CB_DataMb_TrafVol,
					IB_VoiceMo_TrafVol, IB_SmsMo_TrafVol, IB_DataMb_TrafVol);
		}
	}	


}
