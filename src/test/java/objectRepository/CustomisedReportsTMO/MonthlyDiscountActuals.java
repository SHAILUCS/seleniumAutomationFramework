package objectRepository.CustomisedReportsTMO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;
import objectRepository.common.Currency;
import objectRepository.common.ServiceType;
import objectRepository.common.TADIG;
import objectRepository.common.TrafficDirection;

public class MonthlyDiscountActuals {
	private SeleniumMethods com;
	public static String title = "Monthly Actuals";

	public MonthlyDiscountActuals() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	@FindBy(xpath = "//h1[contains(.,'Monthly Discount Actual')]")
	private WebElement data_MonthlyDiscountActual_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Report parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(id = "P16_MASTER_ENTITY_ID")
	private WebElement select_Operator;

	@FindBy(id = "P16_TRAFFIC_PERIOD")
	private WebElement select_TrafficPeriod;

	@FindBy(id = "P16_TRAFFIC_DIRECTION2")
	private WebElement data_TrafficDirection;

	@FindBy(id = "P16_SERVICE_TYPE")
	private WebElement data_ServiceType;

	@FindBy(id = "P16_CURRENCY")
	private WebElement data_Currency;

	@FindBy(id = "P16_SNAPSHOT_DATE")
	private WebElement select_ProcessingDate;

	@FindBy(id = "P16_DISCOUNT_TYPE")
	private WebElement select_DiscountType;

	@FindBy(xpath = "//button[contains(.,'Create Report')]")
	private WebElement button_CreateReport;

	private List<String> columnNames = new ArrayList<>(Arrays.asList("", "Client Agreement Ref", "Agreement Start Date",
			"Agreement End Date", "PMN", "Operator", "Discount Type", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
			"Aug", "Sep", "Oct", "Nov", "Dec", "Accrual", "Difference", "Diff (%)", "Ytd", "Snapshot Date",
			"Processing Date", "Service Type", "Traffic Direction", "Traffic Period", "Comments"));

	public void verify_UI(String traffPeriod, String post_procDate, String discType, TrafficDirection TrafficDirection,
			ServiceType ServiceType) {
		ApexCommon comm = new ApexCommon();
		com.isElementPresent(data_MonthlyDiscountActual_BreadCrumb, "'Monthly Discount Accrual' page heading");

		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_ReportParameters_Section, "Report Parameters section");

		CustomReporter.report(STATUS.INFO, "Search fields and buttons verification");
		com.isElementPresent(select_Operator, "Operator dropdown");
		com.isElementPresent(select_TrafficPeriod, "Traffic Period dropdown");

		Util.comparator_NonNumbers(TrafficDirection.value, com.getText(data_TrafficDirection),
				"Displayed Traffic Direction value");
		Util.comparator_NonNumbers(ServiceType.value, com.getText(data_ServiceType), "Displayed Service Type value");
		Util.comparator_NonNumbers(Currency.USD.value, com.getText(data_Currency), "Displayed currency value");

		com.isElementPresent(select_DiscountType, "Discount Type dropdown");
		com.isElementPresent(select_ProcessingDate, "Processing Date dropdown");
		com.isElementPresent(button_CreateReport, "Create Report button");

		CustomReporter.report(STATUS.INFO, "Search Result Section");
		if (performSearch(traffPeriod, post_procDate, discType)) {

			com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Column Selection icon");
			com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
			com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Select Rows dropdown");
			comm.verifyActionsPopupItems_WithSubscription();
			com.isElementPresent(By.xpath("//div[contains(.,'This report took')]"),
					"'This report took x.x seconds to run' message at bottom");
			
			comm.verifyColumnHeaders(comm.table_ResultTabHeader,columnNames, 1);
		}
	}

	private boolean performSearch(String traffPeriod, String procDate, String discType) {
		ApexCommon comm = new ApexCommon();
		fillBasicSearchFields(traffPeriod, procDate, discType);
		com.click(button_CreateReport);
		if (com.isElementPresent(comm.table_ResultTab)) {
			return true;
		}
		return false;
	}

	private void fillBasicSearchFields(String traffPeriod, String procDate, String discType) {
		String message = "";
		com.selectByPartialVisibleText(select_Operator, TADIG.USAW6.value);
		message = message + " | Client= '" + TADIG.USAW6.value + "'";

		if (traffPeriod != null) {
			com.selectByPartialVisibleText(select_TrafficPeriod, traffPeriod);
			com.wait(1);
			message = message + "| trafficPeriod- '" + traffPeriod + "'";
		}

		if (discType != null) {
			com.selectByPartialVisibleText(select_DiscountType, discType);
			com.wait(1);
			message = message + "| discountType- '" + discType + "'";
		}

		if (procDate != null) {
			com.selectByPartialVisibleText(select_ProcessingDate, procDate);
			com.wait(1);
			message = message + "| processingDate- '" + procDate + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

	}

}
