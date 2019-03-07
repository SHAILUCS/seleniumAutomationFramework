package objectRepository.DealTracker.BudgetModule;

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

public class PerformanceTracker {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Performance Tracker";

	public PerformanceTracker() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	public void verify_UI(String[] dataSet, String dateFrom, String dateTo, TrafficDirection TrafficDirection,
			ServiceType[] ServiceType, EventType[] EventType, Country[] Country, String zone, TADIG[] TADIG,
			String agreementStatus) {

		com.isElementPresent(data_PerformanceTracker_BreadCrumb, "Performance Tracker heading in Bread Crumb");
		verifyReportParametersSection();
		if (performSearch(dataSet, dateFrom, dateTo, TrafficDirection, ServiceType, EventType, Country, zone, TADIG,
				agreementStatus)) {
			verifyPerformanceTrackerReportSection();
		}

	}

	@FindBy(xpath = "//h1[contains(.,'Performance Tracker')]")
	private WebElement data_PerformanceTracker_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(id = "P19_SNAPSHOT_ID_LEFT")
	private WebElement select_DataSet_Left;

	@FindBy(id = "P19_SNAPSHOT_ID_RIGHT")
	private WebElement select_DataSet_Right;

	@FindBy(id = "P19_DATE_FROM")
	private WebElement text_DateFrom;

	@FindBy(id = "P19_DATE_TO")
	private WebElement text_DateTo;

	@FindBy(id = "P19_TRAFFIC_DIRECTION")
	private WebElement select_TrafficDirection;

	@FindBy(id = "P19_IOT_SERVICE_TYPE_ID_LEFT")
	private WebElement select_ServiceType_Left;

	@FindBy(id = "P19_IOT_SERVICE_TYPE_ID_RIGHT")
	private WebElement select_ServiceType_Right;

	@FindBy(id = "P19_IOT_EVENT_TYPE_ID_LEFT")
	private WebElement select_EventType_Left;

	@FindBy(id = "P19_IOT_EVENT_TYPE_ID_RIGHT")
	private WebElement select_EventType_Right;

	@FindBy(id = "P19_COUNTRY_ID_LEFT")
	private WebElement select_CountryLeft;

	@FindBy(id = "P19_COUNTRY_ID_RIGHT")
	private WebElement select_CountryRight;

	@FindBy(id = "P19_IOT_CLIENT_ZONE_ID")
	private WebElement select_Zone;

	@FindBy(id = "P19_PARTNER_LEFT")
	private WebElement select_PartnerLeft;

	@FindBy(id = "P19_PARTNER_RIGHT")
	private WebElement select_PartnerRight;

	@FindBy(id = "P19_AGREEMENT_STATUS")
	private WebElement select_AgreementStatus;

	@FindBy(xpath = "//button[contains(.,'Refresh Report')]")
	private WebElement button_Refresh;

	private void verifyReportParametersSection() {
		CustomReporter.report(STATUS.INFO, "Report Parameters Section verification");
		com.isElementPresent(data_ReportParameters_Section, "Report Parameters Section");

		com.isElementPresent(select_DataSet_Left, "Data Set Left shuttle");
		com.isElementPresent(select_DataSet_Right, "Data Set Right shuttle");
		com.isElementPresent(text_DateFrom, "Date From field");
		com.isElementPresent(text_DateTo, "Date To field");
		com.isElementPresent(select_TrafficDirection, "Traffic Direction dropdown");
		com.isElementPresent(select_ServiceType_Left, "Service Type Left shuttle");
		com.isElementPresent(select_ServiceType_Right, "Service Type Right shuttle");
		com.isElementPresent(select_EventType_Left, "Event Type Left shuttle");
		com.isElementPresent(select_EventType_Right, "Event Type Right shuttle");
		com.isElementPresent(select_CountryLeft, "Country Left shuttle");
		com.isElementPresent(select_CountryRight, "Country Right shuttle");
		com.isElementPresent(select_Zone, "Zone dropdown");
		com.isElementPresent(select_PartnerLeft, "Partner Left shuttle");
		com.isElementPresent(select_PartnerRight, "Partner Right shuttle");
		com.isElementPresent(select_AgreementStatus, "Agreement Status dropdown");
		com.isElementPresent(button_Refresh, "Refresh button");
	}

	private boolean performSearch(String[] dataSet, String dateFrom, String dateTo, TrafficDirection TrafficDirection,
			ServiceType[] ServiceType, EventType[] EventType, Country[] Country, String zone, TADIG[] TADIG,
			String agreementStatus) {
		String message = "";

		if (dataSet != null) {
			com.selectByPartialVisibleText_DoubleClick_FromArray(select_DataSet_Left, dataSet);
			message = message + " | dataSet = '" + Arrays.toString(dataSet) + "'";
		}

		if (dateFrom != null) {
			com.sendKeys(text_DateFrom, dateFrom);
			message = message + " | dateFrom= '" + dateFrom + "'";
		}

		if (dateTo != null) {
			com.sendKeys(text_DateTo, dateTo);
			message = message + " | dateTo= '" + dateTo + "'";
		}

		if (TrafficDirection != null) {
			com.selectByPartialVisibleText(select_TrafficDirection, TrafficDirection.value);
			message = message + " | TrafficDirection= '" + TrafficDirection.value + "'";
		}

		if (ServiceType != null) {
			comm.selectMultipleValues_DoubleClick_FromArray(select_ServiceType_Left, ServiceType);
			message = message + " | ServiceType= '" + Arrays.toString(ServiceType) + "'";
		}

		if (EventType != null) {
			comm.selectMultipleValues_DoubleClick_FromArray(select_EventType_Left, EventType);
			message = message + " | EventType= '" + Arrays.toString(EventType) + "'";
		}

		if (Country != null) {
			comm.selectMultipleValues_DoubleClick_FromArray(select_CountryLeft, Country);
			message = message + " | Country= '" + Arrays.toString(Country) + "'";
		}

		if (zone != null) {
			com.selectByPartialVisibleText(select_Zone, zone);
			message = message + " | zone= '" + zone + "'";
		}

		if (TADIG != null) {
			comm.selectMultipleValues_DoubleClick_FromArray(select_PartnerLeft, TADIG);
			message = message + " | Partner= '" + Arrays.toString(TADIG) + "'";
		}

		if (agreementStatus != null) {
			com.selectByPartialVisibleText(select_AgreementStatus, agreementStatus);
			message = message + " | agreementStatus= '" + agreementStatus + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_Refresh);
		if (com.isElementPresent(data_PerformanceTrackerReport_Section, "Search results")) {
			return true;
		}

		return false;
	}

	@FindBy(xpath = "//h2[contains(.,'Performance Tracker Report')]")
	private WebElement data_PerformanceTrackerReport_Section;

	private List<String> colNames = new ArrayList<>(Arrays.asList("Traffic Period", "Partner PMN", "Operator Name",
			"Country Ascending", "Zone", "Agreement Reference", "Agreement Status", "Negotiator", "Discount Type",
			"Traffic Direction", "Service", "Event", "Volume", "TAP Rate (GBP)", "NER (GBP)", "TAP Charge (GBP)",
			"Post-Discounted Charge (GBP)", "Discount Achieved (GBP)", "Data Set"));

	@FindBy(xpath = "//span[contains(.,'1 - ')]")
	private WebElement data_Pagination;

	private void verifyPerformanceTrackerReportSection() {
		CustomReporter.report(STATUS.INFO, "Performance Tracker Report Section verification");
		com.isElementPresent(data_PerformanceTrackerReport_Section, "Performance Tracker Report Section heading");

		com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex toolbar - Search Columns icon");
		com.isElementPresent(comm.text_Search, "Apex toolbar - Search textfield");
		com.isElementPresent(comm.button_Go, "Apex toolbar - Go button");
		com.isElementPresent(comm.select_RowsPerPage, "Apex toolbar - Rows dropdown");
		comm.verifyActionsPopupItems_WithoutSubscription();

		//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
		comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);

		com.isElementPresent(data_Pagination, "Pagination data " + com.getText(data_Pagination));
	}
}
