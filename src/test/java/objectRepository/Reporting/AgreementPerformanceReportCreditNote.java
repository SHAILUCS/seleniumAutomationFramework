package objectRepository.Reporting;

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

/**
 * @author shailendra.rajawat
 *
 */
public class AgreementPerformanceReportCreditNote {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Agreement Performance Report Credit Note";

	public AgreementPerformanceReportCreditNote() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Agreement Performance Report Credit Note')]")
	private WebElement data_AgreementPerformanceReportCreditNote_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Active Agreements')]")
	private WebElement data_ActiveAgreements_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Active Agreements')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_ActiveAgreements;

	@FindBy(id = "P279_CLIENT_MASTER_ENTITY_ID_LEFT")
	private WebElement select_HomeOperator_Left;
	
	@FindBy(id = "P279_CLIENT_MASTER_ENTITY_ID_RIGHT")
	private WebElement select_HomeOperator_Right;

	@FindBy(id = "P279_SERVICE_TYPE_ID")
	private WebElement select_ServiceType;
	
	@FindBy(id = "P279_EVENT_TYPE_ID")
	private WebElement select_EventType;
	
	@FindBy(id = "P279_TRAFFIC_DIRECTION")
	private WebElement select_TrafficDirection;

	@FindBy(id = "P279_REPORT_PERIOD")
	private WebElement select_ReportPeriod;

	@FindBy(id = "P279_ORIGINAL_LATEST_0")
	private WebElement radio_InformationSet_Latest;

	@FindBy(id = "P279_ORIGINAL_LATEST_1")
	private WebElement radio_InformationSet_Original;

	@FindBy(id = "P279_CUMULATIVE_MONTHLY_0")
	private WebElement radio_Cumulative;

	@FindBy(id = "P279_CUMULATIVE_MONTHLY_1")
	private WebElement radio_Monthly;

	@FindBy(xpath="//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;
	
	@FindBy(xpath="//button[contains(.,'Reset Page')]")
	private WebElement button_ResetPage;
	
	@FindBy(xpath = "//h2[contains(.,'Agreement Performance Report')]")
	private WebElement data_AgreementPerformanceReport_Section;
	
	@FindBy(xpath = "//li[contains(@class,'controls-item--controlBreak')][contains(.,'Operator Name')]")
	private WebElement data_ControlBreak_OperatorName;
	
	private List<String> colNames = new ArrayList<>(Arrays.asList(	
			"Agreement Reference Ascending", "Agreement Start Date", "Home Currency",
			"Discount Achieved Home Currency Incl. Tax December Last Year",
			"Discount Achieved Home Currency Incl. Tax January", "Discount Achieved Home Currency Incl. Tax February",
			"Discount Achieved Home Currency Incl. Tax March", "Discount Achieved Home Currency Incl. Tax April",
			"Discount Achieved Home Currency Incl. Tax May", "Discount Achieved Home Currency Incl. Tax June",
			"Discount Achieved Home Currency Incl. Tax July", "Discount Achieved Home Currency Incl. Tax August",
			"Discount Achieved Home Currency Incl. Tax September", "Discount Achieved Home Currency Incl. Tax October",
			"Discount Achieved Home Currency Incl. Tax November",
			"Discount Achieved Home Currency Incl. Tax December This Year", "Roaming Partners"));

	@FindBy(xpath = "//span[contains(.,'1 -')]")
	private WebElement data_PaginationText;

	public void verify_UI(TADIG TADIG,ServiceType ServiceType,EventType EventType,TrafficDirection TrafficDirection,String reportPeriod, String latestOrOriginal, String cumulativeOrMonthly ) {

		com.isElementPresent(data_AgreementPerformanceReportCreditNote_BreadCrumb,
				"Agreement Performance Report Credit Note heading in Bread Crumb");

		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_ActiveAgreements_Section, "Active Agreements Section");
		com.isElementPresent(hideshowbutton_ActiveAgreements, "Active Agreements Section hide show icon");

		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(select_HomeOperator_Left, "Home Operator Left shuttle");
		com.isElementPresent(select_HomeOperator_Right, "Home Operator Right shuttle");
		com.isElementPresent(select_ServiceType, "Service Type dropdown");
		com.isElementPresent(select_EventType, "Event Type dropdown");
		com.isElementPresent(select_TrafficDirection, "Traffic Direction dropdown");
		com.isElementPresent(select_ReportPeriod, "Report Period dropdown");
		com.isElementPresent(radio_InformationSet_Latest, "Information Set_Latest radio button");
		com.isElementPresent(radio_InformationSet_Original, "Information Set_Original radio button");
		com.isElementPresent(radio_Cumulative, "Cumulative radio button");
		com.isElementPresent(radio_Monthly, "Monthly radio button");
		
		com.isElementPresent(button_RefreshReport, "Refresh Report button");
		com.isElementPresent(button_ResetPage, "Reset Page button");


		if (performSearch(TADIG, ServiceType, EventType, TrafficDirection, reportPeriod, latestOrOriginal, cumulativeOrMonthly)) {
			CustomReporter.report(STATUS.INFO, "Search Results section verification");
			com.isElementPresent(data_AgreementPerformanceReport_Section, "Agreement Performance Report Section");

			com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
			com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
			com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
			com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
			comm.verifyActionsPopupItems_WithoutSubscription();

			com.isElementPresent(data_ControlBreak_OperatorName, "Operator Name result breakdown");
			
			//comm.printColumnHeaders(comm.table_ResultTab, 2);
			comm.verifyColumnHeaders(comm.table_ResultTab, colNames, 2);

			com.isElementPresent(data_PaginationText, "Pagination " + com.getText(data_PaginationText));	
		}
	}

	private boolean performSearch(TADIG TADIG,ServiceType ServiceType,EventType EventType,TrafficDirection TrafficDirection,String reportPeriod, String latestOrOriginal, String cumulativeOrMonthly ) {
		String message = "";

		if (TADIG != null) {
			com.selectByPartialVisibleText_DoubleClick(select_HomeOperator_Left, TADIG.value);
			message = message + " | TADIG= '" + TADIG.value + "'";
		}

		if (ServiceType != null) {
			com.selectByVisibleText(select_ServiceType, ServiceType.value);
			message = message + " | ServiceType= '" + ServiceType.value + "'";
		}

		if (EventType != null) {
			com.selectByVisibleText(select_EventType, EventType.value);
			message = message + " | EventType= '" + EventType.value + "'";
		}

		if (TrafficDirection != null) {
			com.selectByVisibleText(select_TrafficDirection, TrafficDirection.value);
			message = message + " | TrafficDirection= '" + TrafficDirection.value + "'";
		}

		if (reportPeriod != null) {
			com.selectByVisibleText(select_ReportPeriod, reportPeriod);
			message = message + " | reportPeriod= '" + reportPeriod + "'";
		}

		if (latestOrOriginal != null) {
			if (latestOrOriginal.toLowerCase().contains("latest")) {
				com.click(radio_InformationSet_Latest);	
			} else {
				com.click(radio_InformationSet_Original);
			}
			message = message + " | latestOrOriginal= '" + latestOrOriginal + "'";
		}

		if (cumulativeOrMonthly != null) {
			if (cumulativeOrMonthly.toLowerCase().contains("cumulative")) {
				com.click(radio_Cumulative);	
			} else {
				com.click(radio_Monthly);
			}
			message = message + " | cumulativeOrMonthly= '" + cumulativeOrMonthly + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_RefreshReport);
		if (com.isElementPresent(comm.table_ResultTab, "Result Table")) {
			return true;
		}

		return false;
	}

}
