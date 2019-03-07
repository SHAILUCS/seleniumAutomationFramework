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

public class DataLoadingTMO {
	private SeleniumMethods com;
	public static String title = "Data Loading";

	public DataLoadingTMO() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	@FindBy(xpath = "//h1[contains(.,'Data Loading')]")
	private WebElement data_DataLoading_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(id = "P18_DATE_FROM")
	private WebElement text_FromDate;

	@FindBy(id = "P18_DATE_TO")
	private WebElement text_ToDate;

	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	private WebElement button_Refresh;

	@FindBy(xpath = "//span[contains(.,'No data found.')]")
	private WebElement data_NoDataFoundMessage;

	List<String> colNames = new ArrayList<>(Arrays.asList("","Filename","Create Date","Error Description","E-mail Sent"));

	public void verify_UI(String fromDate, String toDate) {
		ApexCommon comm = new ApexCommon();
		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_DataLoading_BreadCrumb, "Data Loading heading in Bread Crumb");
		com.isElementPresent(data_ReportParameters_Section, "Report Parameters Section");

		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(text_FromDate, "From Date textfield");
		com.isElementPresent(text_ToDate, "To Date textfield");
		com.isElementPresent(button_Refresh, "Refresh button");

		CustomReporter.report(STATUS.INFO, "Verifying the search result section");
		com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
		com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
		com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
		comm.verifyActionsPopupItems_WithSubscription();

		com.isElementPresent(data_NoDataFoundMessage, "Initial - No Data Found Message");

		if (performSearch(fromDate, toDate)) {
			//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);

		}

	}

	public boolean performSearch(String fromDate, String toDate) {
		ApexCommon comm = new ApexCommon();
		String message = "";
		if (fromDate != null) {
			com.sendKeys(text_FromDate, fromDate);
			message = message + " | fromDate= '" + fromDate + "'";
		}

		if (toDate != null) {
			com.sendKeys(text_ToDate, toDate);
			message = message + " | toDate= '" + toDate + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_Refresh);
		if (com.isElementPresent(comm.table_ResultTabHeader, "Result Table")) {
			return true;
		}

		return false;
	}



}
