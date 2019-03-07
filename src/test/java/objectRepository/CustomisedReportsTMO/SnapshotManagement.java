package objectRepository.CustomisedReportsTMO;

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
import objectRepository.common.TADIG;

public class SnapshotManagement {
	private SeleniumMethods com;
	public static String title = "Snapshot Management";

	public SnapshotManagement() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	@FindBy(xpath = "//h1[contains(.,'Snapshot Management')]")
	private WebElement data_SnapshotManagement_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Create Snapshot')]")
	private WebElement data_CreateSnapshot_Section;

	@FindBy(id = "P17_MASTER_ENTITY_ID")
	private WebElement select_Operator;

	@FindBy(id = "P17_PERIOD")
	private WebElement select_TrafficPeriod;

	@FindBy(id = "P17_SNAPSHOT_TYPE_0")
	private WebElement radio_PostMonthEnd;

	@FindBy(id = "P17_SNAPSHOT_TYPE_1")
	private WebElement radio_PreMonthEnd;

	@FindBy(id = "P17_FROM_DATE")
	private WebElement text_FromDate;

	@FindBy(id = "P17_TO_DATE")
	private WebElement text_ToDate;

	@FindBy(xpath = "//button[contains(.,'Purge Last Created Snapshot')]")
	private WebElement button_PurgeLastCreatedSnapshot;

	@FindBy(xpath = "//button[contains(.,'Refresh Status')]")
	private WebElement button_RefreshStatus;

	@FindBy(xpath = "//span[contains(.,'No data found.')]")
	private WebElement data_NoDataFoundMessage;

	List<String> colNames = new ArrayList<>(Arrays.asList("Snapshot Date", "Processing Start Date",
			"Processing End Date Descending", "Traffic Period Start Date", "Snapshot Type", "Created By"));

	public void verify_UI(String trafPeriod, String snapshotType_PrePost, String fromDate, String toDate) {
		ApexCommon comm = new ApexCommon();
		CustomReporter.createNode("Sections verification");
		com.isElementPresent(data_SnapshotManagement_BreadCrumb, "Snapshot Management heading in Bread Crumb");
		com.isElementPresent(data_CreateSnapshot_Section, "Report Parameters Section");

		CustomReporter.createNode("Fields verification");
		com.isElementPresent(select_Operator, "Operator dropdown");
		com.isElementPresent(select_TrafficPeriod, "Traffic Period dropdown");
		com.isElementPresent(radio_PostMonthEnd, "Post Month End radio button");
		com.isElementPresent(radio_PreMonthEnd, "Pre Month End radio button");
		com.isElementPresent(text_FromDate, "From Date textfield");
		com.isElementPresent(text_ToDate, "To Date textfield");
		com.isElementPresent(button_RefreshStatus, "Refresh Status button");

		CustomReporter.createNode("Verifying the search result section");
		com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
		com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
		com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
		comm.verifyActionsPopupItems_WithSubscription();

		com.isElementPresent(data_NoDataFoundMessage, "Initial - No Data Found Message");

		if (performSearch(trafPeriod, snapshotType_PrePost, fromDate, toDate)) {
			
			//DUE TO FATAL ERROR ADDED This line [no such element: Unable to locate element:]
			if (!com.waitForElementTobe_NotVisible(button_PurgeLastCreatedSnapshot, 1)) {
				CustomReporter.report(STATUS.PASS, "Purge Last Created Snapshot button is displayed");
			} else {
				CustomReporter.report(STATUS.WARNING, "Purge Last Created Snapshot button is NOT displayed");
			}
			//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);

		}

	}

	public boolean performSearch(String trafPeriod, String snapshotType_PrePost, String fromDate, String toDate) {
		ApexCommon comm = new ApexCommon();
		String message = "";
		com.selectByPartialVisibleText(select_Operator, TADIG.USAW6.value);
		message = message + " | Operator= '" + TADIG.USAW6.value + "'";

		if (trafPeriod != null) {
			com.selectByPartialVisibleText(select_TrafficPeriod, trafPeriod);
			message = message + " | trafPeriod= '" + trafPeriod + "'";
		}

		if (snapshotType_PrePost != null) {
			if (snapshotType_PrePost.toLowerCase().contains("pre")) {
				com.click(radio_PreMonthEnd);
			} else {
				com.click(radio_PostMonthEnd);
			}
			message = message + " | snapshotType_PrePost= '" + snapshotType_PrePost + "'";
		}

		if (fromDate != null) {
			com.sendKeys(text_FromDate, fromDate);
			message = message + " | fromDate= '" + fromDate + "'";
		}

		if (toDate != null) {
			com.sendKeys(text_ToDate, toDate);
			message = message + " | toDate= '" + toDate + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_RefreshStatus);
		if (com.isElementPresent(comm.table_ResultTabHeader, "Result Table")) {
			return true;
		}

		return false;
	}

}
