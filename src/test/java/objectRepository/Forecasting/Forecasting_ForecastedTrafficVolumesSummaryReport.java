package objectRepository.Forecasting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;
import objectRepository.common.Country;
import objectRepository.common.EventType;
import objectRepository.common.ServiceType;
import objectRepository.common.TADIG;
import objectRepository.common.TrafficDirection;

public class Forecasting_ForecastedTrafficVolumesSummaryReport 
{
	private ApexCommon comm;
	private SeleniumMethods com;
	public static String title = "Forecasted Traffic Volumes Summary Report - Period, Global, Country & PMN";

	public Forecasting_ForecastedTrafficVolumesSummaryReport() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm=new ApexCommon();
	}
	
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	protected WebElement breadcrumb_IOTRONWelcome;
	
	@FindBy(xpath = "//li[contains(.,'Forecasting')]")
	protected WebElement breadcrumb_Forecasting;
	
	@FindBy(xpath = "//h1[contains(.,'Forecasted Traffic Volumes Summary Report - Period, Global, Country & PMN')]")
	private WebElement breadcrumb_ForecastedTrafficVolumesSummaryReportPeriodGlobalCountryPMN;
	
	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]//..//..//button")
	private WebElement hideshowbutton_ReportParameters;
	
	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]")
	private WebElement region_ReportParameters;
	
	@FindBy(xpath = "//label[@id='P493_HOME_OPERATOR_MEID_LABEL']")
	private WebElement label_HomeOperator;

	@FindBy(id = "P493_HOME_OPERATOR_MEID_LEFT")
	private WebElement select_HomeOperator_Left;
	
	@FindBy(id = "P493_HOME_OPERATOR_MEID_RIGHT")
	private WebElement select_HomeOperator_RIGHT;
	
	@FindBy(id = "P493_DATE_FROM_LABEL")
	private WebElement label_DateFrom;

	@FindBy(id = "P493_DATE_FROM")
	private WebElement text_DateFrom;
	
	@FindBy(xpath = "//button[@title='Help Text: Date From']")
	private WebElement helpbutton_DateFrom;
	
	@FindBy(id = "P493_DATE_TO_LABEL")
	private WebElement label_DateTo;

	@FindBy(id = "P493_DATE_TO")
	private WebElement text_DateTo;
	
	@FindBy(xpath = "//button[@title='Help Text: Date To']")
	private WebElement helpbutton_DateTo;
	
	@FindBy(id = "P493_TRAFFIC_DIRECTION_LABEL")
	private WebElement label_TrafficDirection;

	@FindBy(id = "P493_TRAFFIC_DIRECTION")
	private WebElement select_TrafficDirection;
	
	@FindBy(xpath = "//button[@title='Help Text: Traffic Direction']")
	private WebElement helpbutton_TrafficDirection;
	
	@FindBy(id = "P493_SERVICE_TYPE_ID_LABEL")
	private WebElement label_ServiceType;

	@FindBy(id = "P493_SERVICE_TYPE_ID")
	private WebElement select_ServiceType;
	
	@FindBy(xpath = "//button[@title='Help Text: Service Type']")
	private WebElement helpbutton_ServiceType;
	
	@FindBy(id = "P493_EVENT_TYPE_ID_LABEL")
	private WebElement label_EventType;

	@FindBy(id = "P493_EVENT_TYPE_ID")
	private WebElement select_EventType;
	
	@FindBy(xpath = "//button[@title='Help Text: Event Type']")
	private WebElement helpbutton_EventType;
	
	@FindBy(id = "P493_PARTNER_MASTER_ENTITY_ID_LABEL")
	private WebElement label_PMN;

	@FindBy(id = "P493_PARTNER_MASTER_ENTITY_ID_LEFT")
	private WebElement select_PMN_Left;
	
	@FindBy(id = "P493_PARTNER_MASTER_ENTITY_ID_RIGHT")
	private WebElement select_PMN_Right;
	
	@FindBy(xpath = "//button[@title='Help Text: PMN']")
	private WebElement helpbutton_PMN;
	
	@FindBy(id = "P493_COUNTRY_ID_LABEL")
	private WebElement label_Country;

	@FindBy(id = "P493_COUNTRY_ID_LEFT")
	private WebElement select_Country_Left;
	
	@FindBy(id = "P493_COUNTRY_ID_RIGHT")
	private WebElement select_Country_Right;
	
	@FindBy(xpath = "//button[@title='Help Text: Country']")
	private WebElement helpbutton_Country;
	
	@FindBy(id = "P493_OPERATOR_GROUP_ID_LABEL")
	private WebElement label_Group;

	@FindBy(id = "P493_OPERATOR_GROUP_ID")
	private WebElement select_Group;
	
	@FindBy(xpath = "//button[@title='Help Text: Group']")
	private WebElement helpbutton_Group;
	
	@FindBy(xpath = "//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;
	
	private List<String> tableColumns = new ArrayList<>(
			Arrays.asList("Traffic Direction", "Service Type", "Event Type", "Traffic Period", "Partner PMN Ascending",
					"Partner Name/ Group Name", "Country", "ISO Country Code", "Continent", "Agreement Reference",
					"Agreement Start Date", "Agreement End Date", "Actual/ Forecasted", "Traffic Volume",
					"Agreement Status", "Negotiator", "DCH Traffic Volume Actual", "DCH Traffic Volume Billed"));
	
	public void UIVerification(TADIG homeOperator,String DateFrom, String DateTo, TrafficDirection trafDirection, ServiceType servType,
			EventType evType, TADIG[] pmn, Country[] country, String group) 
	{	
		CustomReporter.createNode("Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_Forecasting, "Forecasting, BreadCrumb");
		com.isElementPresent(breadcrumb_ForecastedTrafficVolumesSummaryReportPeriodGlobalCountryPMN, "Financial Report, BreadCrumb");
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
		com.isElementPresent(button_RefreshReport, "RefreshReport, button");	
		
		CustomReporter.createNode("Verifying the Table columns");
		if(performSearch(homeOperator,DateFrom, DateTo, trafDirection, servType, evType,pmn, country, group)){
			
			comm.printColumnHeaders(comm.table_ResultTab, 2);
			comm.verifyColumnHeaders(comm.table_ResultTab,tableColumns, 2);
			}
		else{
			CustomReporter.report(STATUS.FAIL, "Table Column Not Found");
		}
		
		CustomReporter.createNode("Verifying Apex Search region content");
		ApexCommon comm= new ApexCommon();
		com.isElementPresent(comm.icon_SelectColumnToSearch, "SearchColumn,DropDown");	
		com.isElementPresent(comm.text_Search, "Search textfield, SearchBox");		
		com.isElementPresent(comm.button_Go, "Go Button, Button");
		com.isElementPresent(comm.label_Rows, "Num Row, Label");
		com.isElementPresent(comm.select_RowsPerPage, "Select Rows, DropDown");
		com.isElementPresent(comm.button_Action, "Action, Button");
		com.isElementPresent(comm.select_Reports, "Reports, DropDown");
	}
	
	private boolean performSearch(TADIG homeOperator,String DateFrom, String DateTo, TrafficDirection trafDirection, ServiceType servType,
			EventType evType, TADIG[] pmn, Country[] country, String group) {
		String message = "";
		
		if (homeOperator!= null){
			com.selectByPartialVisibleText_DoubleClick(select_HomeOperator_Left, homeOperator.value);
			message=message+"|homeOperator- '"+homeOperator.value+"'";
		}

		if (DateFrom != null) {
			com.sendKeys(text_DateFrom, DateFrom);
			message = message + " | DateFrom= '" + DateFrom + "'";
			com.wait(1);
		}

		if (DateTo != null) {
			com.sendKeys(text_DateTo, DateTo);
			message = message + " | DateTo= '" + DateTo + "'";
		}

		if (trafDirection != null) {
			com.selectByVisibleText(select_TrafficDirection, trafDirection.value);
			message = message + " | trafDirection= '" + trafDirection.value + "'";
		}
		
		if (servType != null) {
			com.selectByVisibleText(select_ServiceType, servType.value);
			message = message + " | servType= '" + servType.value + "'";
		}
		
		if (evType != null) {
			com.selectByVisibleText(select_EventType, evType.value);
			message = message + " | evType= '" + evType.value + "'";
		}
		
		if (pmn != null) {
			comm.selectMultipleValues_DoubleClick_FromArray(select_PMN_Left, pmn);
			message = message + " | pmn= '" + Arrays.toString(pmn) + "'";
		}
		
		if (country != null) {
			comm.selectMultipleValues_DoubleClick_FromArray(select_Country_Left, country);
			message = message + " | country= '" + Arrays.toString(country) + "'";
		}
		
		if (group != null) {
			com.selectByVisibleText(select_Group, group);
			message = message + " | group= '" + group + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);
		com.click(button_RefreshReport);
		if (com.isElementPresent(comm.table_ResultTab, "Result Table")) {
			return true;
		}

		return false;

	}
	
	
	private static void testarray(TADIG[] ta,Country[] ca, String[] sp, String ghj) {
		System.out.println(Arrays.toString(ca));
		System.out.println(Arrays.toString(ta));
		System.out.println(Arrays.toString(sp));
		System.out.println(ghj);
	}
	
	
	public static void main(String[] args) {
		
		TADIG[] tjkja = {TADIG.AUSTA,TADIG.ALBAM};
		Country[] klkl = {Country.Afghanistan,Country.Austria};
		String[] ghp = {"prafull","shailu"};
		
		testarray(tjkja, klkl,ghp,"sai");
		
		/*TADIG[] ta = {TADIG.AUSTA,TADIG.ALBAM};
		String[] test = {"hgfhg",";lk;l"};
		
		System.out.println(test);
		System.out.println(Arrays.toString(test));
		
		System.out.println(ta);
		System.out.println(Arrays.toString(ta));*/
	}

}
