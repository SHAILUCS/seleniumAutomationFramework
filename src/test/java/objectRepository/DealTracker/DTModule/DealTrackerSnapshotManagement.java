package objectRepository.DealTracker.DTModule;

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

public class DealTrackerSnapshotManagement {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Deal Tracker Snapshot Management";

	public DealTrackerSnapshotManagement() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Deal Tracker Snapshot Management')]")
	private WebElement data_DealTrackerSnapshotManagement_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Create Snapshot')]")
	private WebElement data_CreateSnapshot_Section;
	
	@FindBy(id = "P9_CLIENT_MASTER_ENTITY_ID")
	private WebElement select_HomeOperator;

	@FindBy(id = "P9_TRAFFIC_PERIOD")
	private WebElement data_TrafficPeriod;
	
	@FindBy(xpath = "//button[contains(.,'Refresh Page')]")
	private WebElement button_RefreshPage;
	
	@FindBy(xpath = "//button[contains(.,'Create Snapshot')]")
	private WebElement button_CreateSnapshot;
	
	@FindBy(xpath = "//span[contains(.,'1 -')]")
	private WebElement data_PaginationText;
	
	
	private List<String> colNames = new ArrayList<>(Arrays.asList("Snapshot Date","Reporting Year","Snapshot Reference","Created By","Calculation Type"));

	public void verify_UI(TADIG TADIG) {
		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_DealTrackerSnapshotManagement_BreadCrumb,
				"Deal Tracker Snapshot Management heading in Bread Crumb");
		com.isElementPresent(data_CreateSnapshot_Section, "Create Snapshot Section");

		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(select_HomeOperator, "Home Operator dropdown");
		com.isElementPresent(button_RefreshPage, "Refresh Page button");
		com.isElementPresent(button_CreateSnapshot, "Create Snapshot button");
		
		com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
		com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
		com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
		comm.verifyActionsPopupItems_WithoutSubscription();
		
		if (performSearch(TADIG)){
			com.isElementPresent(data_TrafficPeriod, "Traffic Period value: {"+com.getText(data_TrafficPeriod)+"}");
			//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);
			com.isElementPresent(data_PaginationText, "Pagination "+com.getText(data_PaginationText));
		}
		
	}

	private boolean performSearch(TADIG TADIG) {
		String message = "";
		if(TADIG!=null){
			com.selectByPartialVisibleText(select_HomeOperator, TADIG.value);
			message = message + " | HomeOperator= '" + TADIG.value + "'";
		}
		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_RefreshPage);
		if (com.isElementPresent(comm.table_ResultTabHeader, "Result Table")) {
			return true;
		}

		return false;
	}

	

}
