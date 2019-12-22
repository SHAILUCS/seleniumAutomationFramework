package objectRepository.Forecasting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.BusinessIntelligence.DailyTrafficAnalysisReport;
import objectRepository.common.ApexCommon;
import tests.MainRegression.Module_KPN_Op4;
import tests.MainRegression.Module_TMO;

public class FinancialReport_ForecastedTAPDiscountedCharges {
	private ApexCommon comm;
	private SeleniumMethods com;
	private WebTable tab;
	private int headerRow=2;
	public static String title = "Financial Report - Forecasted TAP & Discounted Charges";

	public FinancialReport_ForecastedTAPDiscountedCharges() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm=new ApexCommon();
		tab=new WebTable(comm.table_ResultTab);
	}

	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	protected WebElement breadcrumb_IOTRONWelcome;

	@FindBy(xpath = "//li[contains(.,'Forecasting')]")
	protected WebElement breadcrumb_Forecasting;

	@FindBy(xpath = "//h1[contains(.,'Financial Report - Forecasted TAP & Discounted Charges')]")
	private WebElement breadcrumb_FinancialReport;

	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]//..//..//button")
	private WebElement hideshowbutton_ReportParameters;

	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]")
	private WebElement region_ReportParameters;

	@FindBy(xpath = "//label[@id='P497_HOME_OPERATOR_MEID_LABEL']")
	private WebElement label_HomeOperator;

	@FindBy(id = "P497_HOME_OPERATOR_MEID_LEFT")
	private WebElement select_HomeOperator_Left;

	@FindBy(id = "P497_HOME_OPERATOR_MEID_RIGHT")
	private WebElement select_HomeOperator_RIGHT;

	@FindBy(id = "P497_DATE_FROM_LABEL")
	private WebElement label_DateFrom;

	@FindBy(id = "P497_DATE_FROM")
	private WebElement text_DateFrom;

	@FindBy(xpath = "//button[@title='Help Text: Date From']")
	private WebElement helpbutton_DateFrom;

	@FindBy(id = "P497_DATE_TO_LABEL")
	private WebElement label_DateTo;

	@FindBy(id = "P497_DATE_TO")
	private WebElement text_DateTo;

	@FindBy(xpath = "//button[@title='Help Text: Date To']")
	private WebElement helpbutton_DateTo;

	@FindBy(id = "P497_TRAFFIC_DIRECTION_LABEL")
	private WebElement label_TrafficDirection;

	@FindBy(id = "P497_TRAFFIC_DIRECTION")
	private WebElement select_TrafficDirection;

	@FindBy(xpath = "//button[@title='Help Text: Traffic Direction']")
	private WebElement helpbutton_TrafficDirection;

	@FindBy(id = "P497_SERVICE_TYPE_ID_LABEL")
	private WebElement label_ServiceType;

	@FindBy(id = "P497_SERVICE_TYPE_ID")
	private WebElement select_ServiceType;

	@FindBy(xpath = "//button[@title='Help Text: Service Type']")
	private WebElement helpbutton_ServiceType;

	@FindBy(id = "P497_EVENT_TYPE_ID_LABEL")
	private WebElement label_EventType;

	@FindBy(id = "P497_EVENT_TYPE_ID")
	private WebElement select_EventType;

	@FindBy(xpath = "//button[@title='Help Text: Event Type']")
	private WebElement helpbutton_EventType;

	@FindBy(id = "P497_CURRENCY_LABEL")
	private WebElement label_Currency;

	@FindBy(id = "P497_CURRENCY")
	private WebElement select_Currency;

	@FindBy(xpath = "//button[@title='Help Text: Currency']")
	private WebElement helpbutton_Currency;

	@FindBy(id = "P497_CONVERSION_RATE_METHOD_LABEL")
	private WebElement label_SDRExchangeRate;

	@FindBy(id = "P497_CONVERSION_RATE_METHOD")
	private WebElement select_SDRExchangeRate;

	@FindBy(xpath = "//button[@title='Help Text: SDR Exchange rate <BR>for future months']")
	private WebElement helpbutton_SDRExchangeRate;

	@FindBy(id = "P497_CONVERSION_RATE")
	private WebElement textbox_ConversionRate; 

	@FindBy(id = "P497_PARTNER_MASTER_ENTITY_ID_LABEL")
	private WebElement label_PMN;

	@FindBy(id = "P497_PARTNER_MASTER_ENTITY_ID_LEFT")
	private WebElement select_PMN_Left;

	@FindBy(id = "P497_PARTNER_MASTER_ENTITY_ID_RIGHT")
	private WebElement select_PMN_Right;

	@FindBy(xpath = "//button[@title='Help Text: PMN']")
	private WebElement helpbutton_PMN;

	@FindBy(id = "P497_COUNTRY_ID_LABEL")
	private WebElement label_Country;

	@FindBy(id = "P497_COUNTRY_ID_LEFT")
	private WebElement select_Country_Left;

	@FindBy(id = "P497_COUNTRY_ID_RIGHT")
	private WebElement select_Country_Right;

	@FindBy(xpath = "//button[@title='Help Text: Country']")
	private WebElement helpbutton_Country;

	@FindBy(id = "P497_OPERATOR_GROUP_ID_LABEL")
	private WebElement label_Group;

	@FindBy(id = "P497_OPERATOR_GROUP_ID")
	private WebElement select_Group;

	@FindBy(xpath = "//button[@title='Help Text: Group']")
	private WebElement helpbutton_Group;

	@FindBy(id = "P497_TAX_REQUIRED_LABEL")
	private WebElement label_TAX;

	@FindBy(id = "P497_TAX_REQUIRED_0")
	private WebElement radio_Tax_Net;

	@FindBy(xpath = "//label[@for='P497_TAX_REQUIRED_0']")
	private WebElement label_Tax_Net;

	@FindBy(id = "P497_TAX_REQUIRED_1")
	private WebElement radio_Tax_Gross;

	@FindBy(xpath = "//label[@for='P497_TAX_REQUIRED_1']")
	private WebElement label_Tax_Gross;

	@FindBy(xpath = "//button[@title='Help Text: Tax']")
	private WebElement helpbutton_Tax;

	@FindBy(xpath = "//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;

	@FindBy(xpath = "//button[contains(.,'Download CSV')]")
	private WebElement button_DownloadCsv;

	private List<String> tableColumns = new ArrayList<>(Arrays.asList("Traffic Direction","Service Type","Event Type","Traffic Period",
			"Partner PMN Ascending","Partner Name","Group Name","Country","ISO Country Code",
			"Continent","Agreement Reference","Agreement Start Date","Agreement End Date", "Actual/ Forecasted","Traffic Volume","TAP Charges",
			"TAP IOT Rate","Post-Discounted Charges","Post-Discounted IOT Rate"));

	public boolean performSearch(String homeOperator, String startEndTrafPeriod,
			String trafDirection, String servType, String evType, String curr, String[] pmn,
			String countryCommaSeparated, String group, String taxNetOrGross) {
		
		fillBasicSearchFields(homeOperator, startEndTrafPeriod, trafDirection, servType,
				evType, curr, pmn,countryCommaSeparated, group, taxNetOrGross);
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_RefreshReport);
		if (com.isElementPresent(comm.table_ResultTab)) {
			return true;
		}
		Assert.fail("Search failed on Fin Forecast Page, Result table "+comm.table_ResultTab+" Failed to display");
		return false;
	}

	private void fillBasicSearchFields(String homeOperator, String startEndTrafPeriod, 
			String trafDirection, String servType, String evType, String curr, String[] pmn, 
			String countryCommaSeparated, String group, String taxNetOrGross) {
		
		String message=" ";
		if (homeOperator!= null){
			com.deselectAllValues_Shuttle_DoubleClick(select_HomeOperator_RIGHT);
			com.selectByPartialVisibleText_DoubleClick(select_HomeOperator_Left, homeOperator);
			message=message+"|homeOperator- '"+homeOperator+"'";
		}

		if (startEndTrafPeriod!=null && !startEndTrafPeriod.split(",")[0].equals("null")){
			com.sendKeys(text_DateFrom, startEndTrafPeriod.split(",")[0]);
			message=message+"|startTrafficPeriod- '"+startEndTrafPeriod.split(",")[0]+"'";
		}

		if (startEndTrafPeriod!=null && !startEndTrafPeriod.split(",")[1].equals("null")){
			com.sendKeys(text_DateTo, startEndTrafPeriod.split(",")[1]);
			message=message+"|endTrafficPeriod- '"+startEndTrafPeriod.split(",")[1]+"'";
		}

		if (trafDirection != null){
			com.selectByPartialVisibleText(select_TrafficDirection, trafDirection);
			message=message+"|trafficDirection- '"+trafDirection+"'";
		}

		if (servType != null){
			com.selectByPartialVisibleText(select_ServiceType, servType);
			message=message+"|serviceType- '"+servType+"'";
		}

		if (evType != null){
			com.selectByPartialVisibleText(select_EventType, evType);
			message=message+"|eventType- '"+evType+"'";
		}

		if (curr != null){
			com.selectByPartialVisibleText(select_Currency, curr);
			message=message+"|currency- '"+curr+"'";
		}

		if (pmn != null){
			com.selectByPartialVisibleText_DoubleClick_FromArray(select_PMN_Left, pmn);
			message=message+"|pmn- '"+Arrays.toString(pmn)+"'";
		}

		if (countryCommaSeparated != null){
			com.selectByPartialVisibleText_DoubleClick_FromCommaSeparated(select_Country_Left, countryCommaSeparated);
			message=message+"|country- '"+countryCommaSeparated+"'";
		}

		if (group != null){
			com.selectByPartialVisibleText_DoubleClick_FromCommaSeparated(select_Group, group);
			message=message+"|group- '"+group+"'";
		}

		if (taxNetOrGross != null){
			switch (taxNetOrGross.toUpperCase()) {
			case "NET":
				com.javaScript_Click(radio_Tax_Net);
				break;
			case "GROSS":
				com.javaScript_Click(radio_Tax_Gross);
				break;
			}
			message=message+"|tax- '"+taxNetOrGross+"'";
		}


		CustomReporter.report(STATUS.INFO, "Performing search operation for data :"+message);
	}

	public void verify_DailyTrafficAnalysisReport_Data(String numOfMsg, String voiceMO_ActMin_BillMin_TapNet_TapGross,
			String dataMB_ActMb_BillMb, String methodName) {
		
		CustomReporter.createNode("For OB Sms Mo Net");
		
		JSONManager json = new JSONManager(Module_KPN_Op4.jsonFilePath, methodName, "FinForecastPage", "SmsMoNet");
		if(performSearch(json.getStr("homeOperator"), json.getStr("startEndTrafPeriod"),
				json.getStr("trafDirection"), json.getStr("servType"), json.getStr("evType"), json.getStr("curr"), json.getStrArr("pmn"),
				json.getStr("countryCommaSeparated"), json.getStr("group"), json.getStr("taxNetOrGross"))){
			String newColumnName="Traffic Vol_unRounded";
			comm.setRowsPerPage("All");
			comm.applyComputation(newColumnName, "", "Traffic Volume");

			int newCol=tab.initHeaderColumnList(headerRow).getColumnNumber(newColumnName);
			int startRow=headerRow+1;
			String data=comm.getSumOfColumnValues(startRow,newCol);
			Util.comparator_PageValues("Number of Messages", DailyTrafficAnalysisReport.title, numOfMsg, title, data);

			CustomReporter.createNode("For OB Voice Mo Net");
			
			json = new JSONManager(Module_KPN_Op4.jsonFilePath, methodName, "FinForecastPage", "VoiceMoNet");
			if(performSearch(json.getStr("homeOperator"), json.getStr("startEndTrafPeriod"),
					json.getStr("trafDirection"), json.getStr("servType"), json.getStr("evType"), json.getStr("curr"), json.getStrArr("pmn"),
					json.getStr("countryCommaSeparated"), json.getStr("group"), json.getStr("taxNetOrGross"))){
				newColumnName="DCH Traffic Volume Actual_U";
				comm.applyComputation(newColumnName, "", "DCH Traffic Volume Actual");
				newCol=tab.initHeaderColumnList(headerRow).getColumnNumber(newColumnName);
				data=comm.getSumOfColumnValues(startRow,newCol);
				Util.comparator_PageValues("Sum of Billed Minutes", DailyTrafficAnalysisReport.title, voiceMO_ActMin_BillMin_TapNet_TapGross.split(",")[0], title, data);

				newColumnName="DCH Traffic Volume Billed_U";
				comm.applyComputation(newColumnName, "", "DCH Traffic Volume Billed");
				newCol=tab.initHeaderColumnList(headerRow).getColumnNumber(newColumnName);
				data=comm.getSumOfColumnValues(startRow,newCol);
				Util.comparator_PageValues("Sum of Actual Minutes", DailyTrafficAnalysisReport.title, voiceMO_ActMin_BillMin_TapNet_TapGross.split(",")[1], title, data);

				newColumnName="DCH TAP Charges";
				newCol=tab.getColumnNumber(newColumnName);
				data=comm.getSumOfColumnValues(startRow,newCol);
				Util.comparator_PageValues("TAP Charges Net", DailyTrafficAnalysisReport.title, voiceMO_ActMin_BillMin_TapNet_TapGross.split(",")[2], title, data);

				CustomReporter.createNode("For OB Sms Mo Gross");
				
				json = new JSONManager(Module_KPN_Op4.jsonFilePath, methodName, "FinForecastPage", "VoiceMoGross");
				if(performSearch(json.getStr("homeOperator"), json.getStr("startEndTrafPeriod"),
						json.getStr("trafDirection"), json.getStr("servType"), json.getStr("evType"), json.getStr("curr"), json.getStrArr("pmn"),
						json.getStr("countryCommaSeparated"), json.getStr("group"), json.getStr("taxNetOrGross"))){
					data=comm.getSumOfColumnValues(startRow,newCol);
					Util.comparator_PageValues("TAP Charges Gross", DailyTrafficAnalysisReport.title, voiceMO_ActMin_BillMin_TapNet_TapGross.split(",")[3], title, data);

					CustomReporter.createNode("For OB Data MB Net");

					json = new JSONManager(Module_KPN_Op4.jsonFilePath, methodName, "FinForecastPage", "DataNet");
					if(performSearch(json.getStr("homeOperator"), json.getStr("startEndTrafPeriod"),
							json.getStr("trafDirection"), json.getStr("servType"), json.getStr("evType"), json.getStr("curr"), json.getStrArr("pmn"),
							json.getStr("countryCommaSeparated"), json.getStr("group"), json.getStr("taxNetOrGross"))){
						newColumnName="DCH Traffic Volume Actual_U";
						newCol=tab.getColumnNumber(newColumnName);
						data=comm.getSumOfColumnValues(startRow,newCol);
						Util.comparator_PageValues("Sum of Actual MB", DailyTrafficAnalysisReport.title, dataMB_ActMb_BillMb.split(",")[0], title, data);

						newColumnName="DCH Traffic Volume Billed_U";
						newCol=tab.getColumnNumber(newColumnName);
						data=comm.getSumOfColumnValues(startRow,newCol);
						Util.comparator_PageValues("Sum of Billed MB", DailyTrafficAnalysisReport.title, dataMB_ActMb_BillMb.split(",")[1], title, data);
					}	
				}	
			}
		}

	}

	public void UIVerification(String homeOperator, String startEndTrafPeriod,
			String trafDirection, String servType, String evType, String curr, String[] pmn,
			String countryCommaSeparated, String group, String taxNetOrGross) 
	{	
		CustomReporter.createNode("Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_Forecasting, "Forecasting, BreadCrumb");
		com.isElementPresent(breadcrumb_FinancialReport, "Financial Report, BreadCrumb");
		com.isElementPresent(hideshowbutton_ReportParameters, "Report Parameters, HideShowButton");
		com.isElementPresent(region_ReportParameters, "Report Parameters, Region");	
		com.isElementPresent(label_HomeOperator, "HomeOperator, Label");
		com.isElementPresent(select_HomeOperator_Left, "Home Operator_Left, select");
		com.isElementPresent(select_HomeOperator_RIGHT, "Home Operator_Right, select");
		com.isElementPresent(label_DateFrom, "Date From, Label");
		com.isElementPresent(text_DateFrom, "Date From, Text");
		com.isElementPresent(helpbutton_DateFrom, "Date From, HelpButton");
		com.isElementPresent(label_DateTo, "Date To, Label");
		com.isElementPresent(text_DateTo, "Date To, Text");
		com.isElementPresent(helpbutton_DateTo, "Date To, HelpButton");
		com.isElementPresent(label_TrafficDirection, "Traffic Direction, Label");
		com.isElementPresent(select_TrafficDirection, "Traffic Direction, select");
		com.isElementPresent(helpbutton_TrafficDirection, "Traffic Direction, HelpButton");
		com.isElementPresent(label_ServiceType, "Service Type, Label");
		com.isElementPresent(select_ServiceType, "Service Type, select");
		com.isElementPresent(helpbutton_ServiceType, "Service Type, HelpButton");
		com.isElementPresent(label_EventType, "Event Type, Label");
		com.isElementPresent(select_EventType, "Event Type, select");
		com.isElementPresent(helpbutton_EventType, "Event Type, HelpButton");
		com.isElementPresent(label_Currency, "Currency, Label");
		com.isElementPresent(select_Currency, "Currency, select");
		com.isElementPresent(helpbutton_Currency, "Currency, HelpButton");
		com.isElementPresent(label_SDRExchangeRate, "SDR Exchange Rate, Label");
		com.isElementPresent(select_SDRExchangeRate, "SDR Exchange Rate, select");
		com.isElementPresent(helpbutton_SDRExchangeRate, "SDR Exchange Rate, HelpButton");
		com.isElementPresent(textbox_ConversionRate, "Conversion Rate, textbox");
		com.isElementPresent(label_PMN, "PMN, Label");
		com.isElementPresent(select_PMN_Left, "PMN_Left, select");
		com.isElementPresent(select_PMN_Right, "PMN_Right, select");
		com.isElementPresent(helpbutton_PMN, "PMN, HelpButton");
		com.isElementPresent(label_Country, "Country, Label");
		com.isElementPresent(select_Country_Left, "Country_Left, select");
		com.isElementPresent(select_Country_Right, "Country_Right, select");
		com.isElementPresent(helpbutton_Country, "Country, HelpButton");
		com.isElementPresent(label_Group, "Group, Label");
		com.isElementPresent(select_Group, "Group, select");
		com.isElementPresent(helpbutton_Group, "Group, HelpButton");
		com.isElementPresent(label_TAX, "TAX, Label");
		com.isElementPresent(radio_Tax_Net, "Tax_Net, Radio");
		com.isElementPresent(label_Tax_Net, "Tax_Net, Label");
		com.isElementPresent(radio_Tax_Gross, "Tax_Gross, Radio");
		com.isElementPresent(label_Tax_Gross, "Tax_Gross, label");
		com.isElementPresent(helpbutton_Tax, "TAX, helpbutton");
		com.isElementPresent(button_RefreshReport, "RefreshReport, button");	

		CustomReporter.createNode("Verifying the Table columns");
		if(performSearch(homeOperator, startEndTrafPeriod, trafDirection, servType, evType, curr, pmn, countryCommaSeparated, group, taxNetOrGross)){
			comm.verifyColumnHeaders(comm.table_ResultTab,tableColumns, 2);
		}
		else{
			CustomReporter.report(STATUS.FAIL, "Table Column Not Found");

		}

		CustomReporter.createNode("Verifying Apex Search region content");
		com.isElementPresent(comm.icon_SelectColumnToSearch, "SearchColumn,DropDown");	
		com.isElementPresent(comm.text_Search, "Search textfield, SearchBox");		
		com.isElementPresent(comm.button_Go, "Go Button, Button");
		com.isElementPresent(comm.label_Rows, "Num Row, Label");
		com.isElementPresent(comm.select_RowsPerPage, "Select Rows, DropDown");
		com.isElementPresent(comm.button_Action, "Action, Button");
		com.isElementPresent(comm.select_Reports, "Reports, DropDown");
		com.isElementPresent(button_DownloadCsv, "Download Csv, button");	
	}

	public String get_TrafVol_AggrValue(String jsonFilePath, String... jsonObjName) {
		
		CustomReporter.createNode("Getting Traffic Volume Aggr Value for "+jsonObjName[jsonObjName.length-1]);
		
		JSONManager json = new JSONManager(jsonFilePath, jsonObjName);
		performSearch(json.getStr("homeOperator"), json.getStr("startEndTrafPeriod"),
				json.getStr("trafDirection"), json.getStr("servType"), json.getStr("evType"), json.getStr("curr"), json.getStrArr("pmn"),
				json.getStr("countryCommaSeparated"), json.getStr("group"), json.getStr("taxNetOrGross"));
		//Getting the Outbound "Traffic Volume"
		comm.applyAggregateFunction("Sum", "Traffic Volume");
		comm.setRowsPerPage("All");
		int trafVol_col=tab.initHeaderColumnList(headerRow).getColumnNumber("Traffic Volume");
		int lastRow=tab.getRowCount();
		String TrafVol_FinRep=tab.getCellText(lastRow, trafVol_col);
		CustomReporter.report(STATUS.INFO, jsonObjName[jsonObjName.length-1]+" - <br/>"
				+ "TrafVol_FinRep="+TrafVol_FinRep);
		return TrafVol_FinRep;
	}

	public HashMap<String, String> get_ibOb_TAPCharges_TrafVol(String methodName) {
		HashMap<String, String> ibOb_TAPCharges_TrafVol=new HashMap<>();
		
		CustomReporter.createNode("FOR IB");
		
		JSONManager json= new JSONManager(Module_TMO.jsonFilePath, methodName,"finForecastReport","searchIb");
		
		if (performSearch(json.getStr("homeOperator"), json.getStr("startEndTrafPeriod"), json.getStr("trafDirection"),
				json.getStr("servType"), json.getStr("evType"), json.getStr("curr"), json.getStrArr("pmn"),
				json.getStr("countryCommaSeparated"), json.getStr("group"), json.getStr("taxNetOrGross"))) {
			
			int trafficVolumeCol=tab.initHeaderColumnList(headerRow).getColumnNumber("Traffic Volume");
			int TapChargesCol=tab.getColumnNumber("TAP Charges");

			int lastRow=tab.getRowCount();
			
			String ibTrafficVolume=Util.removeCommas(tab.getCellText(lastRow, trafficVolumeCol));
			ibOb_TAPCharges_TrafVol.put("ibTrafVol", ibTrafficVolume);
			String ibTapCharges=Util.removeCommas(tab.getCellText(lastRow, TapChargesCol));
			ibOb_TAPCharges_TrafVol.put("ibTapCh", ibTapCharges);

			CustomReporter.createNode("FOR OB");
			
			json= new JSONManager(Module_TMO.jsonFilePath, methodName,"finForecastReport","searchOb");
			if (performSearch(json.getStr("homeOperator"), json.getStr("startEndTrafPeriod"), json.getStr("trafDirection"),
					json.getStr("servType"), json.getStr("evType"), json.getStr("curr"), json.getStrArr("pmn"),
					json.getStr("countryCommaSeparated"), json.getStr("group"), json.getStr("taxNetOrGross"))) {
				lastRow=tab.getRowCount();
				String obTrafficVolume=Util.removeCommas(tab.getCellText(lastRow, trafficVolumeCol));
				ibOb_TAPCharges_TrafVol.put("obTrafVol", obTrafficVolume);
				String obTapCharges=Util.removeCommas(tab.getCellText(lastRow, TapChargesCol));
				ibOb_TAPCharges_TrafVol.put("obTapCh", obTapCharges);
				return ibOb_TAPCharges_TrafVol;
			}
		}
		return null;
	}


}



