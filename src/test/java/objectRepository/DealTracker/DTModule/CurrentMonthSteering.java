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

public class CurrentMonthSteering {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Current Month Steering";

	public CurrentMonthSteering() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Current Month Steering')]")
	private WebElement data_CurrentMonthSteering_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(id = "P2_CLIENT_MASTER_ENTITY_ID")
	private WebElement select_HomeOperator;

	@FindBy(id = "P2_REPORTING_YEAR")
	private WebElement select_ReportingYear;

	@FindBy(id = "P2_NER_TYPE")
	private WebElement select_CalculationType;

	@FindBy(id = "P2_RUN_ID")
	private WebElement select_SnapshotDate;

	@FindBy(xpath = "//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;

	@FindBy(xpath = "//h2[contains(.,'Current Month Steering')]")
	private WebElement data_CurrentMonthSteering_SearchResultSection;
	
	

	private List<String> colNames = new ArrayList<>(Arrays.asList("Country Name Ascending","Traffic Direction","Partner PLMN","Partner Name","Service","Event","Reporting Year","Current NER Rate","Market Share","Traffic Period","TAP Rate","Yearly Volume"));

	public void verify_UI(TADIG TADIG, String reportingYear, String calculationType,
	String snapshotDate) {
		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_CurrentMonthSteering_BreadCrumb,
				"Current Month Steering heading in Bread Crumb");
		com.isElementPresent(data_ReportParameters_Section, "Report Parameters Section");

		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(select_HomeOperator, "Home Operator dropdown");
		com.isElementPresent(select_ReportingYear, "Reporting Year dropdown");
		com.isElementPresent(select_CalculationType, "Calculation Type dropdown");
		com.isElementPresent(select_SnapshotDate, "Snapshot Date dropdown");
		com.isElementPresent(button_RefreshReport, "Refresh Report button");

		if (performSearch(TADIG, reportingYear, calculationType, snapshotDate)){

			com.isElementPresent(data_CurrentMonthSteering_SearchResultSection,
					"Current Month Steering Search Result Section");
			com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
			com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
			com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
			com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
			comm.verifyActionsPopupItems_WithoutSubscription();

			//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);
		}
	}

	public boolean performSearch(TADIG TADIG, String reportingYear, String calculationType,
			String snapshotDate) {
		String message = "";
		if(TADIG!=null){
			com.selectByPartialVisibleText(select_HomeOperator, TADIG.value);
			message = message + " | Client= '" + TADIG.value + "'";
			com.wait(3);
		}
		
		
		if (reportingYear != null) {
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			com.selectByPartialVisibleText(select_ReportingYear, reportingYear);
			message = message + " | reportingYear= '" + reportingYear + "'";
		}

		if (calculationType != null) {
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			com.selectByPartialVisibleText(select_CalculationType, calculationType);
			message = message + " | calculationType= '" + calculationType + "'";
		}

		if (snapshotDate != null) {
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			com.selectByPartialVisibleText(select_SnapshotDate, snapshotDate);
			message = message + " | snapshotDate= '" + snapshotDate + "'";
		}
		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		com.wait(2);
		com.click(button_RefreshReport);
		if (com.isElementPresent(comm.table_ResultTabHeader, "Result Table")) {
			return true;
		}

		return false;
	}


}
