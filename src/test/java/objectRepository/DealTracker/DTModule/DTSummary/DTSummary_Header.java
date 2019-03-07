package objectRepository.DealTracker.DTModule.DTSummary;

import java.util.ArrayList;
import java.util.Arrays;
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
import objectRepository.common.ApexCommon;
import objectRepository.common.Country;
import objectRepository.common.EventType;
import objectRepository.common.ServiceType;
import objectRepository.common.TADIG;
import objectRepository.common.TrafficDirection;

public class DTSummary_Header {
	private SeleniumMethods com;
	private ApexCommon comm ;
	public static String title = "Deal Tracker";

	public DTSummary_Header() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Deal Tracker Summary')]")
	protected WebElement data_DealTrackerSummary_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Deals')]")
	protected WebElement data_Deals_Section;
	
	@FindBy(xpath = "//h2[contains(.,'Search Parameters')]")
	protected WebElement data_SearchParameters_Section;
	
	@FindBy(xpath = "//h2[contains(.,'Net Effective Rates')]")
	protected WebElement data_NetEffectiveRates_Section;
	
	@FindBy(id = "P4_REPORTING_YEAR")
	protected WebElement select_ReportingYear;

	@FindBy(id = "P4_IOT_AGREEMENT_ID")
	protected WebElement select_DealList;
	
	@FindBy(id = "P4_CLIENT_MASTER_ENTITY_ID")
	protected WebElement select_HomeOperator;
	
	@FindBy(id = "P4_TRAFFIC_DIRECTION_LEFT")
	protected WebElement select_TrafficDirectionLeft;
	
	@FindBy(id = "P4_TRAFFIC_DIRECTION_RIGHT")
	protected WebElement select_TrafficDirectionRight;
	
	@FindBy(id = "P4_COUNTRY_ID_LEFT")
	protected WebElement select_CountryLeft;
	
	@FindBy(id = "P4_COUNTRY_ID_RIGHT")
	protected WebElement select_CountryRight;
	
	@FindBy(id = "P4_SERVICE_TYPE_ID_LEFT")
	protected WebElement select_ServiceLeft;
	
	@FindBy(id = "P4_SERVICE_TYPE_ID_RIGHT")
	protected WebElement select_ServiceRight;
	
	@FindBy(id = "P4_EVENT_TYPE_ID_LEFT")
	protected WebElement select_EventLeft;
	
	@FindBy(id = "P4_EVENT_TYPE_ID_RIGHT")
	protected WebElement select_EventRight;
	
	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	protected WebElement button_Refresh;

	@FindBy(xpath = "//button[contains(.,'Reset')]")
	protected WebElement button_Reset;
	
	@FindBy(xpath = "//span[contains(.,'No data found.')]")
	protected WebElement data_NoDataFoundMessage;

	private List<String> tdItems=new ArrayList<>(Arrays.asList(TrafficDirection.CustomerOutbound.value,TrafficDirection.VisitorInbound.value));
	private List<String> stItems=new ArrayList<>(Arrays.asList(ServiceType.DATA.value,ServiceType.SMS.value,ServiceType.VOICE.value));
	private List<String> etItems=new ArrayList<>(Arrays.asList(EventType.MB.value,EventType.MO.value,EventType.MT.value));
		
	public void verify_UI() {
		CustomReporter.createNode("Sections verification");
		com.isElementPresent(data_DealTrackerSummary_BreadCrumb, "Deal Tracker Summary heading in Bread Crumb");
		com.isElementPresent(data_Deals_Section, "Deals Section");
		com.isElementPresent(data_SearchParameters_Section, "Search Parameters Section");

		CustomReporter.createNode("Fields verification");
		com.isElementPresent(select_ReportingYear, "Reporting Year dropdown");
		com.isElementPresent(select_DealList, "Deal List dropdown");
		
		com.isElementPresent(select_HomeOperator, "Home Operator dropdown");
		if(com.isElementPresent(select_TrafficDirectionLeft, "Traffic Direction Left multiselect")){
			Util.comparator_List(tdItems, com.getAllOptionsVisibleText(select_TrafficDirectionLeft), "Traffic Direction Dropdown");
		}
		
		com.selectByPartialVisibleText(select_HomeOperator, "NLDPT");
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		
		com.isElementPresent(select_TrafficDirectionRight, "Traffic Direction Right multiselect");
		com.isElementPresent(select_CountryLeft, "Country Left multiselect");
		com.isElementPresent(select_CountryRight, "Country Right multiselect");
		if(com.isElementPresent(select_ServiceLeft, "Service Left multiselect")){
			Util.comparator_List(stItems, com.getAllOptionsVisibleText(select_ServiceLeft), "Service Dropdown");
		}
		
		com.isElementPresent(select_ServiceRight, "Service Right multiselect");
		if(com.isElementPresent(select_EventLeft, "Event Left multiselect")){
			Util.comparator_List(etItems, com.getAllOptionsVisibleText(select_EventLeft), "Event Dropdown");
		}
		com.isElementPresent(select_EventRight, "Event Right multiselect");
		
		com.isElementPresent(button_Refresh, "Refresh button");
		com.isElementPresent(button_Reset, "Reset button");
		
		/*TrafficDirection td[]={TrafficDirection.CustomerOutbound,TrafficDirection.VisitorInbound};
		Country c[]={Country.Austria};
		if(performSearch(null, null, TADIG.GBROR, td, null, c, null)){
			CustomReporter.report(Status.PASS, "SEARCH DONE");
		}*/
		
	}

	public boolean performSearch(String repYear, String dealList,TADIG TADIG, TrafficDirection[] TrafficDirection, ServiceType[] ServiceType, Country[] Country, EventType[] EventType) {
		
		String message = "";
		com.javaScript_ScrollIntoMIDDLEView_AndHighlight(select_ReportingYear);
		if (repYear != null) {
			com.selectByPartialVisibleText(select_ReportingYear, repYear);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			message = message + " | ReportingYear= '" + repYear + "'";
		}
		
		if (dealList != null) {
			com.selectByPartialVisibleText(select_DealList, dealList);
			message = message + " | dealList= '" + dealList + "'";
		}

		if (TADIG != null) {
			com.selectByPartialVisibleText(select_HomeOperator, TADIG.value);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			message = message + " | HomeOperator= '" + TADIG.value + "'";
		}
		
		if (TrafficDirection != null) {
			com.deselectAllValues_Shuttle_DoubleClick(select_TrafficDirectionRight);
			comm.selectMultipleValues_DoubleClick_FromArray(select_TrafficDirectionLeft, TrafficDirection);
			message = message + " | TrafficDirection= '" + Arrays.toString(TrafficDirection) + "'";
		}
		
		if (ServiceType != null) {
			com.deselectAllValues_Shuttle_DoubleClick(select_ServiceRight);
			comm.selectMultipleValues_DoubleClick_FromArray(select_ServiceLeft, ServiceType);
			message = message + " | ServiceType= '" + Arrays.toString(ServiceType) + "'";
		}

		if (Country != null) {
			com.deselectAllValues_Shuttle_DoubleClick(select_CountryRight);
			comm.selectMultipleValues_DoubleClick_FromArray(select_CountryLeft, Country);
			message = message + " | Country= '" + Arrays.toString(Country) + "'";
		}

		if (EventType != null) {
			com.deselectAllValues_Shuttle_DoubleClick(select_EventRight);
			comm.selectMultipleValues_DoubleClick_FromArray(select_EventLeft, EventType);
			message = message + " | EventType= '" + Arrays.toString(EventType) + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_Refresh);
		if (com.isElementPresent(data_NetEffectiveRates_Section, "Search Results")) {
			return true;
		}

		return false;
	}

	public boolean performSearch(JSONManager json) {
		return performSearch(json.getStr("year"), json.getStr("dealList"), json.getStr("TADIG"), json.getStrArr("TrafficDirection"), json.getStrArr("ServiceType"), json.getStrArr("Country"), json.getStrArr("EventType"));
	}
		
	public boolean performSearch(String repYear, String dealList,String TADIG, String[] TrafficDirection, String[] ServiceType, String[] Country, String[] EventType) {
		
		String message = "";
		com.javaScript_ScrollIntoMIDDLEView_AndHighlight(select_ReportingYear);
		if (repYear != null) {
			com.selectByVisibleText(select_ReportingYear, repYear);
			//com.isElementNotPresent(comm.icon_SpinnerApex5LoadingIndicator);
			message = message + " | ReportingYear= '" + repYear + "'";
		}
		
		if (dealList != null) {
			com.selectByPartialVisibleText(select_DealList, dealList);
			message = message + " | dealList= '" +dealList + "'";
		}

		if (TADIG != null) {
			com.selectByPartialVisibleText(select_HomeOperator, TADIG);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			message = message + " | HomeOperator= '" + TADIG + "'";
		}
		
		if (TrafficDirection != null) {
			com.deselectAllValues_Shuttle_DoubleClick(select_TrafficDirectionRight);
			com.selectByPartialVisibleText_DoubleClick_FromArray(select_TrafficDirectionLeft, TrafficDirection);
			message = message + " | TrafficDirection= '" + Arrays.toString(TrafficDirection) + "'";
		}
		
		if (ServiceType != null) {
			com.deselectAllValues_Shuttle_DoubleClick(select_ServiceRight);
			com.selectByPartialVisibleText_DoubleClick_FromArray(select_ServiceLeft, ServiceType);
			message = message + " | ServiceType= '" + Arrays.toString(ServiceType) + "'";
		}

		if (Country != null) {
			com.deselectAllValues_Shuttle_DoubleClick(select_CountryRight);
			com.selectByVisibleText_DoubleClick_FromArray(select_CountryLeft, Country);
			message = message + " | Country= '" + Arrays.toString(Country) + "'";
		}

		if (EventType != null) {
			com.deselectAllValues_Shuttle_DoubleClick(select_EventRight);
			com.selectByPartialVisibleText_DoubleClick_FromArray(select_EventLeft, EventType);
			message = message + " | EventType= '" + Arrays.toString(EventType) + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_Refresh);
		if (com.isElementPresent(data_NetEffectiveRates_Section, "Search Results")) {
			return true;
		}

		return false;
	}

	public void DropDownVerifaction(String homeOp, List<String> list_expected) {
		com.selectByPartialVisibleText(select_HomeOperator, homeOp);
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		
		List<String> list_Actual=com.getAllOptionsVisibleText(select_CountryLeft);
		Util.comparator_List_NonNumbers(list_expected, list_Actual, "Country List for "+homeOp);
	}
	


}
