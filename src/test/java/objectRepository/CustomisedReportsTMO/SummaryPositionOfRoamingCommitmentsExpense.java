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

public class SummaryPositionOfRoamingCommitmentsExpense {
	private SeleniumMethods com;
	public static String title = "Summary Position of Roaming Commitments - Expense";

	public SummaryPositionOfRoamingCommitmentsExpense() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	@FindBy(xpath = "//h1[contains(.,'Summary Position of Roaming Commitments - Expense')]")
	private WebElement data_SummaryPositionOfRoamingCommitmentsExpense_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(id = "P8_MASTER_ENTITY_ID")
	private WebElement select_Operator;

	@FindBy(id = "P8_TRAFFIC_PERIOD")
	private WebElement select_TrafficPeriod;

	@FindBy(xpath = "//button[contains(.,'Create Report')]")
	private WebElement button_CreateReport;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,'Prior Projected unit <0')]")
	private WebElement data_ReportBreakDown_PriorProjectedUnit_0;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,'Projected < 0')]")
	private WebElement data_ReportBreakDown_Projected_0;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,'Projected unit <0')]")
	private WebElement data_ReportBreakDown_ProjectedUnit_0;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,'Prior Projected < 0')]")
	private WebElement data_ReportBreakDown_PriorProjected_0;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,'Fav Variance')]")
	private WebElement data_ReportBreakDown_FavVariance;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,'Unit Fav Variance')]")
	private WebElement data_ReportBreakDown_UnitFavVariance;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,'Accrued To Date')]")
	private WebElement data_ReportBreakDown_AccruedToDate;

	List<String> colNames = new ArrayList<>(Arrays.asList("", "Client Agreement Ref", "Commitment Type", "Call Type",
			"Commitment Traffic", "$ Commitment Amount", "Term", "Months Into Term", "Months To Go",
			"Unit Fulfillment To Date", "Unit Fulfillment To Date (%)", "Unit Commitment Remaining",
			"Unit Commitment Remaining (%)", "$ Fulfillment To Date", "$ Commitment Remaining",
			"Unit Projected (Shortfall) Or Excess", "$ Projected (Shortfall) Or Excess",
			"Prior Unit Projected (Shortfall) Or Excess", "$ Prior Projected (Shortfall) Or Excess", "$Fav Variance",
			"Unit Fav Variance", "Notes", "Accrued To Date", "Negotiator"));

	public void verify_UI(String trafPeriod) {
		ApexCommon comm = new ApexCommon();
		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_SummaryPositionOfRoamingCommitmentsExpense_BreadCrumb,
				"Summary Position Of Roaming Commitments Expense heading in Bread Crumb");
		com.isElementPresent(data_ReportParameters_Section, "Report Parameters Section");

		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(select_Operator, "Operator dropdown");
		com.isElementPresent(select_TrafficPeriod, "Traffic Period dropdown");
		com.isElementPresent(button_CreateReport, "Create Report button");

		if (performSearch(trafPeriod)) {
			com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
			com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
			com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
			com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
			comm.verifyActionsPopupItems_WithSubscription();

			CustomReporter.report(STATUS.INFO, "Verifying the report breakdown section");
			com.isElementPresent(data_ReportBreakDown_PriorProjectedUnit_0, "Prior projected unit <0");
			com.isElementPresent(data_ReportBreakDown_Projected_0, "Projected<0");
			com.isElementPresent(data_ReportBreakDown_ProjectedUnit_0, "Projected unit <0");
			com.isElementPresent(data_ReportBreakDown_PriorProjected_0, "Prior projected <0");
			com.isElementPresent(data_ReportBreakDown_FavVariance, "Fav Variance");
			com.isElementPresent(data_ReportBreakDown_UnitFavVariance, "Unit Fav Variance");
			com.isElementPresent(data_ReportBreakDown_AccruedToDate, "Accrued To Date");

			//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);

		}

	}

	public boolean performSearch(String trafPeriod) {
		ApexCommon comm = new ApexCommon();
		String message = "";
		com.selectByPartialVisibleText(select_Operator, TADIG.USAW6.value);
		message = message + " | Operator= '" + TADIG.USAW6.value + "'";

		if (trafPeriod != null) {
			com.selectByPartialVisibleText(select_TrafficPeriod, trafPeriod);
			message = message + " | trafPeriod= '" + trafPeriod + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_CreateReport);
		if (com.isElementPresent(comm.table_ResultTabHeader, "Result Table")) {
			return true;
		}

		return false;
	}

}
