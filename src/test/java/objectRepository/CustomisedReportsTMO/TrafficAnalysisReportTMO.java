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

public class TrafficAnalysisReportTMO {
	private SeleniumMethods com;
	public static String title = "Traffic Analysis Report";

	public TrafficAnalysisReportTMO() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	@FindBy(xpath = "//h1[contains(.,'Traffic Analysis Report')]")
	private WebElement data_TrafficAnalysisReport_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Select Ranges')]")
	private WebElement data_SelectRanges_Section;

	@FindBy(id = "P19_SETTLEMENT_DATE_FROM")
	private WebElement text_SettlementDateFrom;

	@FindBy(id = "P19_SETTLEMENT_DATE_TO")
	private WebElement text_SettlementDateTo;

	@FindBy(id = "P19_PROCESSING_DATE_FROM")
	private WebElement text_ProcessingDateFrom;

	@FindBy(id = "P19_PROCESSING_DATE_TO")
	private WebElement text_ProcessingDateTo;

	@FindBy(xpath = "//button[contains(.,'Generate Report')]")
	private WebElement button_GenerateReport;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,\"Call Type = 'MOC'\")]")
	private WebElement data_ReportBreakDown_CallTypeMOC;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,\"Direction = 'Customer/Outbound'\")]")
	private WebElement data_ReportBreakDown_DirectionCustomerOutbound;


	List<String> colNames = new ArrayList<>(Arrays.asList("Settlement Date", "Processing Date", "Direction",
			"Call Type", "SMS", "Actual Minutes", "Actual MB", "Charges W/O Taxes", "Total Charges"));

	public void verify_UI(String settlementDateFrom, String settlementDateTo, String processingDateFrom,
			String processingDateTo) {
		ApexCommon comm = new ApexCommon();
		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_TrafficAnalysisReport_BreadCrumb, "Traffic Analysis Report heading in Bread Crumb");
		com.isElementPresent(data_SelectRanges_Section, "Select Ranges Section");

		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(text_SettlementDateFrom, "Settlement Date From textfield");
		com.isElementPresent(text_SettlementDateTo, "Settlement Date To textfield");

		com.isElementPresent(text_ProcessingDateFrom, "Processing Date From textfield");
		com.isElementPresent(text_ProcessingDateTo, "Processing Date To textfield");
		com.isElementPresent(button_GenerateReport, "Generate Report button");

		CustomReporter.report(STATUS.INFO, "Verifying the search result section");
		com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
		com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
		com.isElementPresent(comm.button_ViewReport, "Apex Toolbar - View Report Icon");
		com.isElementPresent(comm.button_ViewChart, "Apex Toolbar - View Chart Icon");
		com.isElementPresent(comm.button_ViewGroupBy, "Apex Toolbar - View Group By Icon");
		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
		com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
		comm.verifyActionsPopupItems_WithSubscription();

		com.isElementPresent(data_ReportBreakDown_CallTypeMOC, "Call Type = 'MOC'");
		com.isElementPresent(data_ReportBreakDown_DirectionCustomerOutbound, "Direction = 'Customer/Outbound'");


		if (performSearch(settlementDateFrom, settlementDateTo, processingDateFrom, processingDateTo)) {
			//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames,1);

		}

	}

	public boolean performSearch(String settlementDateFrom, String settlementDateTo, String processingDateFrom,
			String processingDateTo) {
		ApexCommon comm = new ApexCommon();
		String message = "";
		if (settlementDateFrom != null) {
			com.sendKeys(text_SettlementDateFrom, settlementDateFrom);
			message = message + " | settlementDateFrom= '" + settlementDateFrom + "'";
		}

		if (settlementDateTo != null) {
			com.sendKeys(text_SettlementDateTo, settlementDateTo);
			message = message + " | settlementDateTo= '" + settlementDateTo + "'";
		}
		if (processingDateFrom != null) {
			com.sendKeys(text_ProcessingDateFrom, processingDateFrom);
			message = message + " | processingDateFrom= '" + processingDateFrom + "'";
		}

		if (processingDateTo != null) {
			com.sendKeys(text_ProcessingDateTo, processingDateTo);
			message = message + " | processingDateTo= '" + processingDateTo + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_GenerateReport);
		if (com.isElementPresent(comm.table_ResultTabHeader, "Result Table")) {
			return true;
		}

		return false;
	}

}
