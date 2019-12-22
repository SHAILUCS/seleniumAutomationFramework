package objectRepository.CustomisedReportsTMO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;
import objectRepository.common.TADIG;
import objectRepository.common.TrafficDirection;

public class MonthlyDiscountActual_VarianceAnalysis {
	private SeleniumMethods com;
	public static String title = "Monthly Discount Actual - Variance Analysis";

	public MonthlyDiscountActual_VarianceAnalysis() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	@FindBy(xpath = "//h1[contains(.,'Monthly Discount Actual - Variance Analysis')]")
	private WebElement data_MonthlyDiscountAccrualVarianceAnalysis_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Parameters')]")
	private WebElement data_Parameters_Section;

	@FindBy(xpath = "//b[contains(.,'Monthly Actual - Variance Analysis')]")
	private WebElement data_XVarianceAnalysis_SearchResultSection;

	@FindBy(id = "P14_MASTER_ENTITY_ID")
	private WebElement select_Client;

	@FindBy(id = "P14_TRAFFIC_PERIOD")
	private WebElement select_TrafficPeriod;

	@FindBy(id = "P14_TRAFFIC_DIRECTION")
	private WebElement select_TrafficDirection;

	@FindBy(id = "P14_DISCOUNT_TYPE")
	private WebElement select_DiscountType;

	@FindBy(css = "select[id^='P14_SNAP_DATE']")
	private WebElement select_ProcessingDate_PostMontEnd;

	@FindBy(css = "select[id^='P14_PRE_ME_PROC_DATE']")
	private WebElement select_ProcessingDate_PreMontEnd;

	@FindBy(xpath = "//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;

	@FindBy(xpath = "//button[contains(.,'Back')]")
	private WebElement button_Back;

	private By tempTableDeleteLater = By.className("a-IRR-table");

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,'Their PMN (TADIG) Code')]")
	private WebElement data_ReportBreakDown_TheirPMNTADIGCode;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,'Variance Value for Discount Applicable (%) > 100')]")
	private WebElement data_ReportBreakDown_VarianceValueForDiscountApplicable;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,\"Period = 'Actual'\")]")
	private WebElement data_ReportBreakDown_PeriodActual;

	private List<String> colNames = new ArrayList<String>(Arrays.asList("", "Company", "Operator Name", "Discount Type",
			"Direction", "Traffic Month", "Service Type", "Event Type", "Sum Of Number Of Calls",
			"Sum Of Real Duration (Minutes)", "Sum Of Charged Duration (Minutes)", "Sum Of Total Real Volume (MB)",
			"Sum Of Total Charged Volume (MB)", "Sum Of Charges W/O Taxes (Local Currency)",
			"Average IOT Rate Post Discount", "Average IOT Rate DCH TAP", "Discount Applicable", "Net Charges",
			"Variance Value for Real Volumes", "Variance Real Volume (%)", "Variance Value for Charged Volumes",
			"Variance Charged Volume (%)", "Variance Value for Discount Applicable",
			"Variance Value for Discount Applicable (%)", "Variance Average IOT DCH rate (%)",
			"Variance Average IOT Post Discount Rate (%)", "Year", "Period", "Note", "Snapshot Date (Post-Month End)",
			"Processing Date (Post-Month End)", "Snapshot Date (Pre-Month End)", "Processing Date (Pre-Month End)"));

	public boolean performSearch(String trafPeriod, TrafficDirection TrafficDirection, String discountType,
			String processingDatePostMonthEnd, String processingDatePreMonthEnd) {
		String message = "";
		com.selectByPartialVisibleText(select_Client, TADIG.USAW6.value);
		message = message + " | Client= '" + TADIG.USAW6.value + "'";
		// com.isElementNotPresent(comm.icon_SpinnerApexLoadingIndicator_Inline,"Inline
		// Spinner");

		if (trafPeriod != null) {
			com.selectByPartialVisibleText(select_TrafficPeriod, trafPeriod);
			message = message + " | trafPeriod= '" + trafPeriod + "'";
		}

		if (TrafficDirection != null) {
			com.selectByPartialVisibleText(select_TrafficDirection, TrafficDirection.value);
			message = message + " | TrafficDirection= '" + TrafficDirection.value + "'";
		}

		if (discountType != null) {
			com.selectByPartialVisibleText(select_DiscountType, discountType);
			message = message + " | discountType= '" + discountType + "'";
		}

		if (processingDatePostMonthEnd != null) {
			com.selectByPartialVisibleText(select_ProcessingDate_PostMontEnd, processingDatePostMonthEnd);
			message = message + " | processingDatePostMonthEnd= '" + processingDatePostMonthEnd + "'";
		}

		if (processingDatePreMonthEnd != null) {
			com.selectByPartialVisibleText(select_ProcessingDate_PreMontEnd, processingDatePreMonthEnd);
			message = message + " | processingDatePreMonthEnd= '" + processingDatePreMonthEnd + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_RefreshReport);
		if (com.isElementPresent(tempTableDeleteLater, "Result Table")) {
			return true;
		}

		return false;
	}

	public void verify_UI(String trafPeriod, TrafficDirection TrafficDirection, String discountType,
			String processingDatePostMonthEnd, String processingDatePreMonthEnd) {
		ApexCommon comm = new ApexCommon();
		CustomReporter.createNode("Sections verification");
		com.isElementPresent(data_MonthlyDiscountAccrualVarianceAnalysis_BreadCrumb,
				"Monthly Discount Accrual Variance Analysis heading in Bread Crumb");
		com.isElementPresent(data_Parameters_Section, "Parameters Section");

		CustomReporter.createNode("Fields verification");
		com.isElementPresent(select_Client, "Operator dropdown");
		com.isElementPresent(select_TrafficPeriod, "Traffic Period dropdown");
		com.isElementPresent(select_TrafficDirection, "Traffic Direction dropdown");
		com.isElementPresent(select_DiscountType, "Discount Type dropdown");
		com.isElementPresent(select_ProcessingDate_PostMontEnd, "Processing Date Post Month End dropdown");
		com.isElementPresent(select_ProcessingDate_PreMontEnd, "Processing Date Pre Month End dropdown");

		com.isElementPresent(button_RefreshReport, "Refresh Report button");
		com.isElementPresent(button_Back, "Back button");

		if (performSearch(trafPeriod, TrafficDirection, discountType, processingDatePostMonthEnd,
				processingDatePreMonthEnd)) {

			com.isElementPresent(data_XVarianceAnalysis_SearchResultSection,
					"X-Variance Analysis Search Result Section");
			com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
			com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
			com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
			com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
			comm.verifyActionsPopupItems_WithSubscription();

			CustomReporter.createNode("Verifying the report breakdown section");
			com.isElementPresent(data_ReportBreakDown_TheirPMNTADIGCode, "Their PMN (TADIG) Code");
			com.isElementPresent(data_ReportBreakDown_VarianceValueForDiscountApplicable,
					"Variance Value for Discount Applicable (%) > 100");
			com.isElementPresent(data_ReportBreakDown_PeriodActual, "Period = 'Actual'");

			//comm.printColumnHeaders(tempTableDeleteLater, 2);
			comm.verifyColumnHeaders(tempTableDeleteLater, colNames, 2);

		}

	}

}
