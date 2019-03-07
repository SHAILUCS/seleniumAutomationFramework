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

public class BudgetTrueup {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Budget True-up";

	public BudgetTrueup() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	public void verify_UI(String dataSet, String comparator, String startDate, String endDate,
			TrafficDirection TrafficDirection, ServiceType[] ServiceType, EventType[] EventType, Country[] Country,
			String zone, TADIG[] TADIG, String agreementStatus, String negotiator) {

		com.isElementPresent(data_BudgetTrueup_BreadCrumb, "Budget True-up heading in Bread Crumb");
		verifyReportParametersSection();
		if (performSearch(dataSet, comparator, startDate, endDate, TrafficDirection, ServiceType, EventType, Country,
				zone, TADIG, agreementStatus, negotiator)) {
			verifyBudgetTrueupReportSection();
		}

	}

	@FindBy(xpath = "//h1[contains(.,'Budget True-up')]")
	private WebElement data_BudgetTrueup_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(id = "P20_SNAPSHOT_ID")
	private WebElement select_DataSet;

	@FindBy(id = "P20_COMP_SNAPSHOT_ID")
	private WebElement select_Comparator;

	@FindBy(id = "P20_DATE_FROM")
	private WebElement text_StartDate;

	@FindBy(id = "P20_DATE_TO")
	private WebElement text_EndDate;

	@FindBy(id = "P20_TRAFFIC_DIRECTION")
	private WebElement select_TrafficDirection;

	@FindBy(id = "P20_IOT_SERVICE_TYPE_ID_LEFT")
	private WebElement select_ServiceType_Left;

	@FindBy(id = "P20_IOT_SERVICE_TYPE_ID_RIGHT")
	private WebElement select_ServiceType_Right;

	@FindBy(id = "P20_IOT_EVENT_TYPE_ID_LEFT")
	private WebElement select_EventType_Left;

	@FindBy(id = "P20_IOT_EVENT_TYPE_ID_RIGHT")
	private WebElement select_EventType_Right;

	@FindBy(id = "P20_COUNTRY_ID_LEFT")
	private WebElement select_CountryLeft;

	@FindBy(id = "P20_COUNTRY_ID_RIGHT")
	private WebElement select_CountryRight;

	@FindBy(id = "P20_IOT_CLIENT_ZONE_ID")
	private WebElement select_Zone;

	@FindBy(id = "P20_PARTNER_LEFT")
	private WebElement select_PartnerLeft;

	@FindBy(id = "P20_PARTNER_RIGHT")
	private WebElement select_PartnerRight;

	@FindBy(id = "P20_AGREEMENT_STATUS")
	private WebElement select_AgreementStatus;

	@FindBy(id = "P20_NEGOTIATOR_CONTACT_ID")
	private WebElement select_Negotiator;

	@FindBy(xpath = "//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;

	private void verifyReportParametersSection() {
		CustomReporter.report(STATUS.INFO, "Report Parameters Section verification");
		com.isElementPresent(data_ReportParameters_Section, "Report Parameters Section");
		com.isElementPresent(select_DataSet, "Data Set dropdpwn");
		com.isElementPresent(select_Comparator, "Comparator dropdpwn");
		com.isElementPresent(text_StartDate, "Start Date field");
		com.isElementPresent(text_EndDate, "End Date field");
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
		com.isElementPresent(select_Negotiator, "Negotiator dropdown");
		com.isElementPresent(button_RefreshReport, "Refresh Report button");
	}

	private boolean performSearch(String dataSet, String comparator, String startDate, String endDate,
			TrafficDirection TrafficDirection, ServiceType[] ServiceType, EventType[] EventType, Country[] Country,
			String zone, TADIG[] TADIG, String agreementStatus, String negotiator) {
		String message = "";

		if (dataSet != null) {
			com.selectByPartialVisibleText(select_DataSet, dataSet);
			message = message + " | dataSet = '" + dataSet + "'";
		}

		if (comparator != null) {
			com.selectByPartialVisibleText(select_Comparator, comparator);
			message = message + " | comparator = '" + comparator + "'";
		}

		if (startDate != null) {
			com.sendKeys(text_StartDate, startDate);
			message = message + " | startDate= '" + startDate + "'";
		}

		if (endDate != null) {
			com.sendKeys(text_EndDate, endDate);
			message = message + " | endDate= '" + endDate + "'";
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

		if (negotiator != null) {
			com.selectByPartialVisibleText(select_Negotiator, negotiator);
			message = message + " | negotiator= '" + negotiator + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_RefreshReport);
		if (com.isElementPresent(data_BudgetTrueupReportSection, "Search results")) {
			return true;
		}

		return false;
	}

	@FindBy(xpath = "//h2[contains(.,'Budget True-up Report')]")
	private WebElement data_BudgetTrueupReportSection;

	private List<String> colNames = new ArrayList<>(Arrays.asList("Traffic Period", "Partner PMN", "Operator Name",
			"Country Ascending", "Region", "Traffic Direction", "Actual Forecast", "Negotiator",
			"Data set Volume Voice MO", "Data set Volume Voice MT", "Data set Volume SMS", "Data set Volume Data",
			"Data set TAP Rate Voice MO", "Data set TAP Rate Voice MT", "Data set TAP Rate SMS",
			"Data set TAP Rate Data", "Data set Net Rate Voice MO", "Data set Net Rate Voice MT",
			"Data set Net Rate SMS", "Data set Net Rate Data", "Data set TAP Charge Voice MO",
			"Data set TAP Charge Voice MT", "Data set TAP Charge SMS", "Data set TAP Charge Data",
			"Data set TAP Charge Total", "Data set Post-Discounted Charge Voice MO",
			"Data set Post-Discounted Charge Voice MT", "Data set Post-Discounted Charge SMS",
			"Data set Post-Discounted Charge Data", "Data set Post-Discounted Charge Total",
			"Data set Discount Achieved Voice MO", "Data set Discount Achieved Voice MT",
			"Data set Discount Achieved SMS", "Data set Discount Achieved Data", "Data set Discount Achieved Total",
			"Comparator Volume Voice MO", "Comparator Volume Voice MT", "Comparator Volume SMS",
			"Comparator Volume Data", "Comparator TAP Rate Voice MO", "Comparator TAP Rate Voice MT",
			"Comparator TAP Rate SMS", "Comparator TAP Rate Data", "Comparator Net Rate Voice MO",
			"Comparator Net Rate Voice MT", "Comparator Net Rate SMS", "Comparator Net Rate Data",
			"Comparator TAP Charge Voice MO", "Comparator TAP Charge Voice MT", "Comparator TAP Charge SMS",
			"Comparator TAP Charge Data", "Comparator TAP Charge Total", "Comparator Post-Discounted Charge Voice MO",
			"Comparator Post-Discounted Charge Voice MT", "Comparator Post-Discounted Charge SMS",
			"Comparator Post-Discounted Charge Data", "Comparator Post-Discounted Charge Total",
			"Comparator Discount Achieved Voice MO", "Comparator Discount Achieved Voice MT",
			"Comparator Discount Achieved SMS", "Comparator Discount Achieved Data",
			"Comparator Discount Achieved Total", "TAP Charge Variance Voice MO", "TAP Charge Variance Voice MT",
			"TAP Charge Variance SMS", "TAP Charge Variance Data", "TAP Charge Variance Total",
			"TAP Charge Volume Variance Voice MO", "TAP Charge Volume Variance Voice MT",
			"TAP Charge Volume Variance SMS", "TAP Charge Volume Variance Data", "TAP Charge Volume Variance Total",
			"TAP Charge Rate Variance Voice MO", "TAP Charge Rate Variance Voice MT", "TAP Charge Rate Variance SMS",
			"TAP Charge Rate Variance Data", "TAP Charge Rate Variance Total",
			"Post-Discounted Charge Variance Voice MO", "Post-Discounted Charge Variance Voice MT",
			"Post-Discounted Charge Variance SMS", "Post-Discounted Charge Variance Data",
			"Post-Discounted Charge Variance Total", "Post-Discounted Charge Volume Variance Voice MO",
			"Post-Discounted Charge Volume Variance Voice MT", "Post-Discounted Charge Volume Variance SMS",
			"Post-Discounted Charge Volume Variance Data", "Post-Discounted Charge Volume Variance Total",
			"Post-Discounted Charge Rate Variance Voice MO", "Post-Discounted Charge Rate Variance Voice MT",
			"Post-Discounted Charge Rate Variance SMS", "Post-Discounted Charge Rate Variance Data",
			"Post-Discounted Charge Rate Variance Total", "Discount Achieved Variance Voice MO",
			"Discount Achieved Variance Voice MT", "Discount Achieved Variance SMS", "Discount Achieved Variance Data",
			"Discount Achieved Variance Total", "Discount Achieved Volume Variance Voice MO",
			"Discount Achieved Volume Variance Voice MT", "Discount Achieved Volume Variance SMS",
			"Discount Achieved Volume Variance Data", "Discount Achieved Volume Variance Total",
			"Discount Achieved Rate Variance Voice MO", "Discount Achieved Rate Variance Voice MT",
			"Discount Achieved Rate Variance SMS", "Discount Achieved Rate Variance Data",
			"Discount Achieved Rate Variance Total"));

	@FindBy(xpath = "//span[contains(.,'1 - ')]")
	private WebElement data_Pagination;

	private void verifyBudgetTrueupReportSection() {
		CustomReporter.report(STATUS.INFO, "Budget True-up Report Section verification");
		com.isElementPresent(data_BudgetTrueupReportSection, "Budget True-up Report Section heading");

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
