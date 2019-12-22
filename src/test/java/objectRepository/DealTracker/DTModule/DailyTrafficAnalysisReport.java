package objectRepository.DealTracker.DTModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;
import objectRepository.common.EventType;
import objectRepository.common.ServiceType;
import objectRepository.common.TADIG;
import objectRepository.common.TrafficDirection;

public class DailyTrafficAnalysisReport {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Daily Traffic Analysis report";

	public DailyTrafficAnalysisReport() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Daily Traffic Analysis report')]")
	private WebElement data_DailyTrafficAnalysisReport_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Parameters')]")
	private WebElement data_Parameters_Section;

	@FindBy(id = "P12_DATE_FROM")
	private WebElement text_DateFrom;

	@FindBy(id = "P12_DATE_TO")
	private WebElement text_DateTo;

	@FindBy(id = "P12_DIRECTION")
	private WebElement select_Direction;

	@FindBy(id = "P12_SERVICE")
	private WebElement select_Service;

	@FindBy(id = "P12_BRAND")
	private WebElement select_Brand;

	@FindBy(id = "P12_SUBSCRIBER_TYPE_LEFT")
	private WebElement select_SubscriberTypeLeft;

	@FindBy(id = "P12_SUBSCRIBER_TYPE_RIGHT")
	private WebElement select_SubscriberTypeRight;

	@FindBy(id = "P12_COUNTRY_LEFT")
	private WebElement select_CountryLeft;

	@FindBy(id = "P12_COUNTRY_RIGHT")
	private WebElement select_CountryRight;

	@FindBy(id = "P12_PARTNER_LEFT")
	private WebElement select_PartnerLeft;

	@FindBy(id = "P12_PARTNER_RIGHT")
	private WebElement select_PartnerRight;

	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	private WebElement button_Refresh;

	@FindBy(xpath = "//h2[contains(.,'Daily Traffic Analysis report')]")
	private WebElement data_DailyTrafficAnalysisReport_Section;

	@FindBy(xpath = "//span[contains(.,'1 -')]")
	private WebElement data_PaginationText;

	
	private List<String> colNames = new ArrayList<>(Arrays.asList("Call Date Ascending", "Direction", "Client PMN",
			"Subscriber Type", "Partner PMN", "Partner Name", "Country", "Service Type", "Actual Traffic Volume",
			"Billed Traffic Volume", "Net Charges", "Gross Charges"));

	public void verify_UI(String dateFrom, String dateTo, TrafficDirection TrafficDirection, ServiceType ServiceType,
			EventType EventType, TADIG brandTADIG, String[] SubscriberType, String[] Country, TADIG partnerTADIG) {
		com.isElementPresent(data_DailyTrafficAnalysisReport_BreadCrumb,
				"Daily Traffic Analysis Report heading in Bread Crumb");

		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_Parameters_Section, "Parameters Section");

		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(text_DateFrom, "Date From field");
		com.isElementPresent(text_DateTo, "Date To field");
		com.isElementPresent(select_Direction, "Direction dropdown");
		com.isElementPresent(select_Service, "Service dropdown");
		com.isElementPresent(select_Brand, "Brand dropdown");
		com.isElementPresent(select_SubscriberTypeLeft, "Subscriber Type Left shuttle");
		com.isElementPresent(select_SubscriberTypeRight, "Subscriber Type Right shuttle");
		com.isElementPresent(select_CountryLeft, "Country Left shuttle");
		com.isElementPresent(select_CountryLeft, "Country Right shuttle");
		com.isElementPresent(select_PartnerLeft, "Partner Left shuttle");
		com.isElementPresent(select_PartnerRight, "Partner Right shuttle");
		com.isElementPresent(button_Refresh, "Refresh button");

		if (performSearch(dateFrom, dateTo, TrafficDirection, ServiceType, EventType, brandTADIG, SubscriberType,
				Country, partnerTADIG)) {
			CustomReporter.report(STATUS.INFO, "Search Results section verification");
			com.isElementPresent(data_DailyTrafficAnalysisReport_Section, "Daily Traffic Analysis Report Section");

			com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
			com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
			com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
			com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
			comm.verifyActionsPopupItems_WithoutSubscription();

			//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);

			com.isElementPresent(data_PaginationText, "Pagination " + com.getText(data_PaginationText));
		}

	}

	private boolean performSearch(String dateFrom, String dateTo, TrafficDirection TrafficDirection,
			ServiceType ServiceType, EventType EventType, TADIG brandTADIG, String[] SubscriberType, String[] Country,
			TADIG partnerTADIG) {
		String message = "";

		if (dateFrom != null) {
			com.sendKeys(text_DateFrom, dateFrom);
			message = message + " | dateFrom= '" + dateFrom + "'";
		}

		if (dateTo != null) {
			com.sendKeys(text_DateTo, dateTo);
			message = message + " | dateTo= '" + dateTo + "'";
		}

		if (TrafficDirection != null) {
			com.selectByPartialVisibleText(select_Direction, TrafficDirection.value);
			message = message + " | TrafficDirection= '" + TrafficDirection.value + "'";
		}

		if (ServiceType != null && EventType != null) {
			com.selectByPartialVisibleText(select_Service, ServiceType.value + " " + EventType.value);
			message = message + " | Service= '" + ServiceType.value + " " + EventType.value + "'";
		}

		if (brandTADIG != null) {
			com.selectByPartialVisibleText(select_Brand, brandTADIG.value);
			message = message + " | brandTADIG= '" + brandTADIG.value + "'";
		}

		if (SubscriberType != null) {
			com.selectByPartialVisibleText_DoubleClick_FromArray(select_SubscriberTypeLeft, SubscriberType);
			message = message + " | SubscriberType= '" + Arrays.toString(SubscriberType) + "'";
		}

		if (Country != null) {
			com.selectByPartialVisibleText_DoubleClick_FromArray(select_CountryLeft, Country);
			message = message + " | Country= '" + Arrays.toString(Country) + "'";
		}

		if (partnerTADIG != null) {
			com.selectByPartialVisibleText_DoubleClick(select_PartnerLeft, partnerTADIG.value);
			message = message + " | partnerTADIG= '" + partnerTADIG.value + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_Refresh);
		if (com.isElementPresent(comm.table_ResultTabHeader, "Result Table")) {
			return true;
		}

		return false;
	}

}
