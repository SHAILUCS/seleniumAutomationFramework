package objectRepository.CustomisedReportsTMO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;
import objectRepository.common.TADIG;

public class DiscountReportTMO {

	private SeleniumMethods com;
	public static String title = "Discount Report";

	public DiscountReportTMO() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	@FindBy(xpath = "//h1[contains(.,'Discount Report')]")
	private WebElement data_DiscountReport_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Parameters')]")
	private WebElement data_Parameters_Section;

	@FindBy(xpath = "//h2[contains(.,'Discount Report')]")
	private WebElement data_DiscountReport_SearchResultSection;

	@FindBy(id = "P5_CLIENT_MASTER_ENTITY_ID")
	private WebElement select_Client;

	@FindBy(id = "P5_TP_START")
	private WebElement text_TrafficPeriodStartDate;

	@FindBy(id = "P5_TP_END")
	private WebElement text_TrafficPeriodEndDate;

	@FindBy(id = "P5_SNAP_DATE")
	private WebElement select_ProcessingDate;

	@FindBy(id = "P5_DISCOUNT_TYPE")
	private WebElement select_DiscountType;

	@FindBy(id = "P5_CALC_TYPE_0")
	private WebElement checkbox_PerMonthBreakdown;

	@FindBy(xpath = "//button[contains(.,'Generate Report')]")
	private WebElement button_GenerateReport;

	@FindBy(xpath = "//button[contains(.,'Download CSV')]")
	private WebElement button_DownloadCSV;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,'Direction')]")
	private WebElement data_ReportBreakDown_Direction;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,'Call Type')]")
	private WebElement data_ReportBreakDown_CallType;
	
	private By tempTableDeleteLater=By.className("a-IRR-table");

	List<String> colNames = new ArrayList<>(Arrays.asList("Year", "Month", "Their PMN Code Ascending", "Operator Name",
			"Discount Type", "Agreement Reference", "Number Of Calls", "Real Duration (Minutes)",
			"Charged Duration (Minutes)", "Total Real MBs", "Total Charged MBs", "Charges w/o Taxes", "Discount Method",
			"Discount Applicable", "Net Charges"));

	public void verify_UI(String trafPeriodStart, String trafPeriodEnd, String processingDate, String discType,
			boolean perMonthBreakdown) {
		ApexCommon comm = new ApexCommon();
		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_DiscountReport_BreadCrumb, "Discount Report heading in Bread Crumb");
		com.isElementPresent(data_Parameters_Section, "Parameters Section");

		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(select_Client, "Client dropdown");
		com.isElementPresent(text_TrafficPeriodStartDate, "Traffic Period Start Date field");
		com.isElementPresent(text_TrafficPeriodEndDate, "Traffic Period End Date field");
		com.isElementPresent(select_ProcessingDate, "Processing Date dropdown");
		com.isElementPresent(select_DiscountType, "Discount Type dropdown");
		com.isElementPresent(checkbox_PerMonthBreakdown, "Per Month Breakdown checkbox");
		com.isElementPresent(button_GenerateReport, "Generate Report button");
		com.isElementPresent(button_DownloadCSV, "Download CSV button");

		if (performSearch(trafPeriodStart, trafPeriodEnd, processingDate, discType, perMonthBreakdown)) {

			com.isElementPresent(data_DiscountReport_SearchResultSection, "Discount Report Search Result Section");
			com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
			com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
			com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
			com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
			comm.verifyActionsPopupItems_WithSubscription();

			CustomReporter.report(STATUS.INFO, "Verifying the report breakdown section");
			com.isElementPresent(data_ReportBreakDown_Direction, "Direction breakdown");
			com.isElementPresent(data_ReportBreakDown_CallType, "Call Type breakdown");
			
			comm.verifyColumnHeaders(tempTableDeleteLater,colNames, 2);

		}

	}

	public boolean performSearch(String trafPeriodStart, String trafPeriodEnd, String processingDate, String discType,
			boolean perMonthBreakdown) {
		ApexCommon comm = new ApexCommon();
		String message = "";
		com.selectByPartialVisibleText(select_Client, TADIG.USAW6.value);
		message = message + " | Client= '" + TADIG.USAW6.value + "'";

		if (trafPeriodStart != null) {
			com.sendKeys(text_TrafficPeriodStartDate, trafPeriodStart, Keys.TAB);
			message = message + " | trafPeriodStart= '" + trafPeriodStart + "'";
		}

		if (trafPeriodEnd != null) {
			com.sendKeys(text_TrafficPeriodEndDate, trafPeriodEnd, Keys.TAB);
			message = message + " | trafPeriodEnd= '" + trafPeriodEnd + "'";
		}

		com.wait(2);
		if (com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator, "Inline spinner")) {
			if (processingDate != null) {
				com.selectByPartialVisibleText(select_ProcessingDate, processingDate);
				message = message + " | processingDate= '" + processingDate + "'";
			} else {
				com.selectByIndex(select_ProcessingDate, 0);
				message = message + " | processingDate= '"
						+ com.getText(com.getFirstSelectedOption(select_ProcessingDate)) + "'";
			}
		}

		if (discType != null) {
			com.selectByPartialVisibleText(select_DiscountType, discType);
			message = message + " | discType= '" + discType + "'";
		}

		if (perMonthBreakdown) {
			com.click(checkbox_PerMonthBreakdown);
			message = message + " | perMonthBreakdown= '" + perMonthBreakdown + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_GenerateReport);
		if (com.isElementPresent(tempTableDeleteLater, "Result Table")) {
			return true;
		}

		return false;
	}

}
