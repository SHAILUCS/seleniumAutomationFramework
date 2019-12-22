package objectRepository.DealTracker.BudgetModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;

public class PricingCatalogue {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Pricing Catalogue";

	public PricingCatalogue() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Pricing Catalogue')]")
	private WebElement data_PricingCatalogue_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Parameters')]")
	private WebElement data_Parameters_Section;

	@FindBy(xpath = "//button[contains(.,'Refresh Page')]")
	private WebElement button_RefreshPage;

	private List<String> colNames = new ArrayList<>(
			Arrays.asList("Country", "Traffic Direction", "Service Type", "Event Type", "Reporting Year", "Rate"));

	@FindBy(xpath = "//span[contains(.,'1 - ')]")
	private WebElement data_Pagination;

	public void verify_UI() {
		com.isElementPresent(data_PricingCatalogue_BreadCrumb, "Pricing Catalogue heading in Bread Crumb");
		CustomReporter.report(STATUS.INFO, "Parameters Section verification");
		com.isElementPresent(data_Parameters_Section, "Parameters Section");

		com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex toolbar - Search Columns icon");
		com.isElementPresent(comm.text_Search, "Apex toolbar - Search textfield");
		com.isElementPresent(comm.button_Go, "Apex toolbar - Go button");
		com.isElementPresent(comm.select_RowsPerPage, "Apex toolbar - Rows dropdown");
		comm.verifyActionsPopupItems_WithoutSubscription();

		comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
		comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);

		com.isElementPresent(data_Pagination, "Pagination data " + com.getText(data_Pagination));

	}

}
