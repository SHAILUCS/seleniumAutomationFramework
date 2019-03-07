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

public class DailyTrafficVolumesByCountryAndPartner {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Daily Traffic Volume By Country And Partner";
	private WebTable tabData;
	private WebTable tabHead;
	
	List<String> columnList = new ArrayList<String>(
			Arrays.asList("Home Operator Ascending", "Partner Country", "Roaming Partner", "Traffic Direction",
					"Service Type", "Event Type", "Call Date", "Traffic Volume"));

	List<String> sortingOrder = new ArrayList<>(Arrays.asList("Home Operator:Ascending",
			"Roaming Partner:Ascending", "Traffic Direction:Ascending", "Service Type:Descending",
			"Event Type:Ascending", "Call Date:Ascending"));
	
	public DailyTrafficVolumesByCountryAndPartner() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm= new ApexCommon();
		tabData= new WebTable(comm.table_ResultTabData);
		tabHead= new WebTable(comm.table_ResultTabHeader);
	}

	@FindBy(xpath = "//h2[contains(.,'Home Operator')]")
	private WebElement data_Section_HomeOperator;

	@FindBy(xpath = "//h2[contains(.,'Reporting Parameters')]")
	private WebElement data_Section_ReportingParameters;

	@FindBy(xpath = "//h2[contains(.,'Country & Partner Charts')]")
	private WebElement data_Section_Charts;

	@FindBy(xpath = "//h2[contains(.,'Country & Partner Report')]")
	private WebElement data_Section_Report;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Country & Partner Report')]//button")
	private WebElement icon_Plus_ReportSection;

	@FindBy(id = "P687_HOME_MASTER_ENTITY_ID_LEFT")
	private WebElement select_HomeOperator_Left;
	
	@FindBy(id = "P687_HOME_MASTER_ENTITY_ID_RIGHT")
	private WebElement select_HomeOperator_Right;

	@FindBy(id = "P687_COUNTRY_LEFT")
	private WebElement select_PartnerCountry_Left;
	
	@FindBy(id = "P687_COUNTRY_RIGHT")
	private WebElement select_PartnerCountry_Right;

	@FindBy(id = "P687_REPORTING_START_DATE")
	private WebElement text_StartDate;

	@FindBy(id = "P687_REPORTING_END_DATE")
	private WebElement text_EndDate;

	@FindBy(id = "P687_PER_PARTNER_PMN_DETAIL_0")
	private WebElement checkbox_PerPartnerPMNDet;

	@FindBy(id = "P687_TRAFFIC_DIRECTION")
	private WebElement select_TraffDir;

	@FindBy(id = "P687_SERVICE_TYPE")
	private WebElement select_ServType;

	@FindBy(id = "P687_EVENT_TYPE")
	private WebElement select_EvType;

	@FindBy(id = "P687_ACTUAL_BILLED_0")
	private WebElement radio_Actual;

	@FindBy(id = "P687_ACTUAL_BILLED_1")
	private WebElement radio_Billed;

	@FindBy(id = "P687_INCLUDE_SPECIAL_SERVICE_0")
	private WebElement checkbox_IncludeSpecialService;

	@FindBy(id = "P687_INCLUDE_NATIONAL_ROAMING_0")
	private WebElement checkbox_IncludeNatRoam;

	@FindBy(xpath = "//button[.='Refresh']")
	private WebElement button_Refresh;

	public void verifyUIContent() {
		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_Section_HomeOperator, "Home Operator Section");
		com.isElementPresent(data_Section_ReportingParameters, "Reporting Parameters Section");
		com.isElementPresent(data_Section_Charts, "Charts Section");

		com.isElementPresent(data_Section_Report, "Report Section");
		if (com.isElementPresent(icon_Plus_ReportSection)) {
			CustomReporter.report(STATUS.PASS, "By default Report Section is displayed as minimized");
		} else {
			CustomReporter.report(STATUS.FAIL, "By default Report Section is NOT displayed as minimized");
		}

		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(select_HomeOperator_Left, "Home Operator shuttle");
		com.isElementPresent(select_PartnerCountry_Left, "Partner Country shuttle");

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

		if (com.isElementPresent(checkbox_PerPartnerPMNDet, "Per Partner PMN Detail checkbox")) {
			com.isSelected(checkbox_PerPartnerPMNDet, "Per Partner PMN Detail");
		}

		com.isElementPresent(select_TraffDir, "Traffic Direction dropdown");
		com.isElementPresent(select_ServType, "Service Type dropdown");
		com.isElementPresent(select_EvType, "Event Type dropdown");
		com.isElementPresent(radio_Actual, "Actual radio option");
		com.isElementPresent(radio_Billed, "Billed radio option");

		com.isElementPresent(checkbox_IncludeSpecialService, "Include Special Service checkbox");
		com.isElementPresent(checkbox_IncludeNatRoam, "Include National Roaming checkbox");

		com.isElementPresent(button_Refresh, "Refresh button");
		
	}

	public void fillBasicSearchFields(String HomeOp, String commaSeparatedPartCntry, String repStartDate,
			String repEndDate, boolean check_perPartPMNDet, String traffDir, String servType, String evType,
			boolean radio_actualBilled, boolean check_includeSpSer, boolean check_includeNatRoam) {
		
		String message="";
		if (HomeOp != null) {
			com.deselectAllValues_Shuttle_DoubleClick(select_HomeOperator_Right);
			com.selectByPartialVisibleText_DoubleClick_FromCommaSeparated(select_HomeOperator_Left, HomeOp);
			message=message+"| HomeOperators- '"+HomeOp+"'";
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator, 10);
		}

		if (commaSeparatedPartCntry != null){
			com.deselectAllValues_Shuttle_DoubleClick(select_PartnerCountry_Right);
			com.selectByPartialVisibleText_DoubleClick_FromCommaSeparated(select_PartnerCountry_Left, commaSeparatedPartCntry);
			message=message+"| PartnerCountry- '"+commaSeparatedPartCntry+"'";
		}

		if (repStartDate != null){
			com.sendKeys(text_StartDate, repStartDate);
			message=message+"| reportStartDate- '"+repStartDate+"'";
		}

		if (repEndDate != null){
			com.sendKeys(text_EndDate, repEndDate);
			message=message+"| reportEndDate- '"+repEndDate+"'";
		}

		if (check_perPartPMNDet){
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(checkbox_PerPartnerPMNDet);
			message=message+"| checkbox_perPartPMNDetails- '"+check_perPartPMNDet+"'";
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
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(radio_Billed);
			message=message+"| radio_actualBilled- '"+radio_actualBilled+"'";
		}

		if (check_includeSpSer){
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(checkbox_IncludeSpecialService);
			message=message+"| check_includeSpSer- '"+check_includeSpSer+"'";
		}

		if (check_includeNatRoam){
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(checkbox_IncludeNatRoam);
			message=message+"| check_includeNatRoam- '"+check_includeNatRoam+"'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: "+message);

	}

	private boolean performSearch(String HomeOp, String commaSeparatedPartCntry, String repStartDate,
			String repEndDate, boolean check_perPartPMNDet, String traffDir, String servType, String evType,
			boolean radio_actualBilled, boolean check_includeSpSer, boolean check_includeNatRoam) {
		
		fillBasicSearchFields(HomeOp, commaSeparatedPartCntry, repStartDate, repEndDate,
				check_perPartPMNDet, traffDir, servType, evType, radio_actualBilled, check_includeSpSer,
				check_includeNatRoam);
		com.click(button_Refresh);
		//com.click(icon_Plus_ReportSection);
		if (com.isElementPresent(comm.table_ResultTabHeader)) {
			return true;
		}
		return false;
	}

	public void verifyReportRegion(String methodName) {
		JSONManager json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName,"WithperPMNDet");
		if (performSearch(json.getStr("HomeOp"), json.getStr("commaSeparatedPartCntry"), json.getStr("repStartDate"),
				json.getStr("repEndDate"), json.getBool("check_perPartPMNDet"), json.getStr("traffDir"),
				json.getStr("servType"), json.getStr("evType"), json.getBool("radio_actualBilled"),
				json.getBool("check_includeSpSer"), json.getBool("check_includeNatRoam"))) {
			
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, columnList, 1);

			int roamingPart_col = tabHead.initHeaderColumnList(1).getColumnNumber("Roaming Partner");
			String partnerPMNColVal = tabData.getCellText(2, roamingPart_col);
			if (!partnerPMNColVal.equals("-")) {
				CustomReporter.report(STATUS.PASS,
						"Report is correctly displaying '" + partnerPMNColVal + "' value in 'Roaming Partner' column");
			} else {
				CustomReporter.report(STATUS.FAIL,
						"Report is incorrectly displaying '-' value in 'Roaming Partner' column, as the Per Partner PMN Detail checkbox is checked");
			}

			json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName,"WithOutperPMNDet");
			if (performSearch(json.getStr("HomeOp"), json.getStr("commaSeparatedPartCntry"), json.getStr("repStartDate"),
					json.getStr("repEndDate"), json.getBool("check_perPartPMNDet"), json.getStr("traffDir"),
					json.getStr("servType"), json.getStr("evType"), json.getBool("radio_actualBilled"),
					json.getBool("check_includeSpSer"), json.getBool("check_includeNatRoam"))) {
				partnerPMNColVal = tabData.getCellText(2, roamingPart_col);
				Util.comparator_PageValues("-", partnerPMNColVal, "'Roaming Partner' column");

				List<String> pageSortingOrder = comm.getSortingOrder_ColonSeparated();
				Util.comparator_List_NonNumbers(sortingOrder, pageSortingOrder, "Sorting Order");
			}

		}
	}

	public void verifyTrafficVolumeColumnData(String methodName) {
		
		String CB_VoiceMo_TrafVol = null, CB_SmsMo_TrafVol = null, CB_DataMb_TrafVol = null;
		String IB_VoiceMo_TrafVol = null, IB_SmsMo_TrafVol = null, IB_DataMb_TrafVol = null;
		
		JSONManager json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName,"OBVoiceMO");
		if (performSearch(json.getStr("HomeOp"), json.getStr("commaSeparatedPartCntry"), json.getStr("repStartDate"),
				json.getStr("repEndDate"), json.getBool("check_perPartPMNDet"), json.getStr("traffDir"),
				json.getStr("servType"), json.getStr("evType"), json.getBool("radio_actualBilled"),
				json.getBool("check_includeSpSer"), json.getBool("check_includeNatRoam"))) {
			
			tabHead.initHeaderColumnList(1);
			int trafVolCol = tabHead.getColumnNumber("Traffic Volume");
			comm.setRowsPerPage("All");
			comm.applyAggregateFunction("Sum", "Traffic Volume");
			int lastRow = tabData.getRowCount();
			CB_VoiceMo_TrafVol = tabData.getCellText(lastRow, trafVolCol);
			
			json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName,"OBSmsMO");
			if (performSearch(json.getStr("HomeOp"), json.getStr("commaSeparatedPartCntry"), json.getStr("repStartDate"),
					json.getStr("repEndDate"), json.getBool("check_perPartPMNDet"), json.getStr("traffDir"),
					json.getStr("servType"), json.getStr("evType"), json.getBool("radio_actualBilled"),
					json.getBool("check_includeSpSer"), json.getBool("check_includeNatRoam"))) {
				lastRow = tabData.getRowCount();
				CB_SmsMo_TrafVol = tabData.getCellText(lastRow, trafVolCol);
			}
			
			json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName,"OBDataMB");
			if (performSearch(json.getStr("HomeOp"), json.getStr("commaSeparatedPartCntry"), json.getStr("repStartDate"),
					json.getStr("repEndDate"), json.getBool("check_perPartPMNDet"), json.getStr("traffDir"),
					json.getStr("servType"), json.getStr("evType"), json.getBool("radio_actualBilled"),
					json.getBool("check_includeSpSer"), json.getBool("check_includeNatRoam"))) {
				lastRow = tabData.getRowCount();
				CB_DataMb_TrafVol = tabData.getCellText(lastRow, trafVolCol);
			}

			
			json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName,"IBVoiceMO");
			if (performSearch(json.getStr("HomeOp"), json.getStr("commaSeparatedPartCntry"), json.getStr("repStartDate"),
					json.getStr("repEndDate"), json.getBool("check_perPartPMNDet"), json.getStr("traffDir"),
					json.getStr("servType"), json.getStr("evType"), json.getBool("radio_actualBilled"),
					json.getBool("check_includeSpSer"), json.getBool("check_includeNatRoam"))) {
				lastRow = tabData.getRowCount();
				IB_VoiceMo_TrafVol = tabData.getCellText(lastRow, trafVolCol);
			}

			json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName,"IBSmsMO");
			if (performSearch(json.getStr("HomeOp"), json.getStr("commaSeparatedPartCntry"), json.getStr("repStartDate"),
					json.getStr("repEndDate"), json.getBool("check_perPartPMNDet"), json.getStr("traffDir"),
					json.getStr("servType"), json.getStr("evType"), json.getBool("radio_actualBilled"),
					json.getBool("check_includeSpSer"), json.getBool("check_includeNatRoam"))) {
				lastRow = tabData.getRowCount();
				IB_SmsMo_TrafVol = tabData.getCellText(lastRow, trafVolCol);
			}

			json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName,"IBDataMB");
			if (performSearch(json.getStr("HomeOp"), json.getStr("commaSeparatedPartCntry"), json.getStr("repStartDate"),
					json.getStr("repEndDate"), json.getBool("check_perPartPMNDet"), json.getStr("traffDir"),
					json.getStr("servType"), json.getStr("evType"), json.getBool("radio_actualBilled"),
					json.getBool("check_includeSpSer"), json.getBool("check_includeNatRoam"))) {
				lastRow = tabData.getRowCount();
				IB_DataMb_TrafVol = tabData.getCellText(lastRow, trafVolCol);
			}

			IOTRONHomePage ihp=new IOTRONHomePage();
			new Navigator().to_IOTRONHomePage().traverseMenu_VerifyPageTitle(
					PagesTitle.DailyTrafficAnalysisReport, ihp.link_BusinessIntelligence,
					ihp.link_BusinessIntelligence_DailyDCHLevelReporting,
					ihp.link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficAnalysisReport);

			DailyTrafficAnalysisReport dailyTrafficAnalysisReport = new DailyTrafficAnalysisReport();
			json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName, "OBVoiceMO");
			dailyTrafficAnalysisReport.verifyTrafficVolumeDataWith_DailyTrafficVolumesByCountryAndPartnerPage(
					json, CB_VoiceMo_TrafVol, CB_SmsMo_TrafVol, CB_DataMb_TrafVol,
					IB_VoiceMo_TrafVol, IB_SmsMo_TrafVol, IB_DataMb_TrafVol);
			
		}
	}

}
