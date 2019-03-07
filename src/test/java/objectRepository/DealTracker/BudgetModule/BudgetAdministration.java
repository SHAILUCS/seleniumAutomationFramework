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

public class BudgetAdministration {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Budget Administration";

	public BudgetAdministration() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Budget Administration')]")
	private WebElement data_BudgetAdministration_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Snapshot Creation')]")
	private WebElement data_SnapshotCreation_Section;

	@FindBy(id = "P18_SNAPSHOT_NAME")
	private WebElement text_SnapshotName;

	@FindBy(id = "P18_IOT_DT_BUD_DATASET_ID")
	private WebElement select_DataSet;

	@FindBy(xpath = "//button[contains(.,'Generate Snapshot')]")
	private WebElement button_GenerateSnapshot;

	@FindBy(xpath = "//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;

	@FindBy(xpath = "//h2[contains(.,'Archive')]")
	private WebElement data_Archive_Section;

	private List<String> colNames = new ArrayList<>(Arrays.asList("", "", "Report Name", "Data Set", "Create Date",
			"Created By", "Start Date", "End Date", "Note"));

	@FindBy(xpath = "//span[contains(.,'1 - ')]")
	private WebElement data_Pagination;

	public void verify_UI() {
		com.isElementPresent(data_BudgetAdministration_BreadCrumb, "Budget Administration heading in Bread Crumb");
		CustomReporter.report(STATUS.INFO, "Snapshot Creation Section verification");
		com.isElementPresent(data_SnapshotCreation_Section, "Snapshot Creation Section heading");
		com.isElementPresent(text_SnapshotName, "Snapshot Name textfield");
		com.isElementPresent(select_DataSet, "Data Set dropdown");
		com.isElementPresent(button_GenerateSnapshot, "Generate Snapshot button");
		com.isElementPresent(button_RefreshReport, "Refresh Report button");

		CustomReporter.report(STATUS.INFO, "Archive Section verification");
		com.isElementPresent(data_Archive_Section, "Archive Section heading");

		com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex toolbar - Search Columns icon");
		com.isElementPresent(comm.text_Search, "Apex toolbar - Search textfield");
		com.isElementPresent(comm.button_Go, "Apex toolbar - Go button");
		com.isElementPresent(comm.select_RowsPerPage, "Apex toolbar - Rows dropdown");
		comm.verifyActionsPopupItems_WithoutSubscription();

		// comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
		comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);

		com.isElementPresent(data_Pagination, "Pagination data " + com.getText(data_Pagination));
	}

}
