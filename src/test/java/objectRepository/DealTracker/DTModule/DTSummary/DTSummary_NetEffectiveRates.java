package objectRepository.DealTracker.DTModule.DTSummary;

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

public class DTSummary_NetEffectiveRates extends DTSummary_Header {

	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = DTSummary_Header.title;

	public DTSummary_NetEffectiveRates() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Net Effective Rates')]//button[contains(@class,'hideShow')]")
	private WebElement icon_Section_NetEffectiveRates_HideShow;

	@FindBy(xpath = "//button[.='Refresh NER']")
	private WebElement button_RefreshNER;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,\"Traffic Direction = 'Customer/Outbound'\")]")
	private WebElement data_AppliedFilter_TrafficDirectionCustomerOutbound;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,\"Client Agreement Ref\")]")
	private WebElement data_AppliedFilter_ClientAgreementRef;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,\"Agreement Start Date\")]")
	private WebElement data_AppliedFilter_AgreementStartDate;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,\"Agreement End Date\")]")
	private WebElement data_AppliedFilter_AgreementEndDate;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,\"RATE_CHANGE_BAD\")]")
	private WebElement data_AppliedFilter_RATE_CHANGE_BAD;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,\"RATE_CHANGE_GOOD2\")]")
	private WebElement data_AppliedFilter_RATE_CHANGE_GOOD2;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,\"RATE_CHANGE_BAD2\")]")
	private WebElement data_AppliedFilter_RATE_CHANGE_BAD2;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']//li[contains(.,\"RATE_CHANGE_GOOD\")]")
	private WebElement data_AppliedFilter_RATE_CHANGE_GOOD;

	private By table = By.className("a-IRR-table");

	private List<String> colNames = new ArrayList<>(
			Arrays.asList("Country Name", "Traffic Direction", "Service", "Event", "Reporting Year",
					"Current Rate (EUR)", "Expected Rate (EUR)", "Rate Change (EUR)", "Volume", "Latest Value (EUR)",
					"Discount Currency", "Current Rate (Discount Currency)", "Expected Rate (Discount Currency)",
					"Rate Change (Discount Currency)", "Latest Value (Discount Currency)"));

	public void verify_UI() {
		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_NetEffectiveRates_Section, "Net Effective Rates Section");
		com.isElementPresent(icon_Section_NetEffectiveRates_HideShow, "Net Effective Rates Section Hide Show button");
		com.click_UsingAction(icon_Section_NetEffectiveRates_HideShow);

		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(button_RefreshNER, "Refresh NER button");
		com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
		com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
		com.isElementPresent(comm.select_Reports, "Apex Toolbar - Go Button");
		com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
		comm.verifyActionsPopupItems_WithoutSubscription();

		CustomReporter.report(STATUS.INFO, "Applied filters");
		com.isElementPresent(data_AppliedFilter_TrafficDirectionCustomerOutbound,
				"Traffic Direction = 'Customer/Outbound' Filter");
		com.isElementPresent(data_AppliedFilter_ClientAgreementRef, "Client Agreement Ref Filter");
		com.isElementPresent(data_AppliedFilter_AgreementStartDate, "Agreement Start Date Filter");
		com.isElementPresent(data_AppliedFilter_AgreementEndDate, "Agreement End Date Filter");
		com.isElementPresent(data_AppliedFilter_RATE_CHANGE_BAD, "RATE_CHANGE_BAD Filter");
		com.isElementPresent(data_AppliedFilter_RATE_CHANGE_GOOD2, "RATE_CHANGE_GOOD2 Filter");
		com.isElementPresent(data_AppliedFilter_RATE_CHANGE_BAD2, "RATE_CHANGE_BAD2 Filter");
		com.isElementPresent(data_AppliedFilter_RATE_CHANGE_GOOD, "RATE_CHANGE_GOOD Filter");

		//comm.printColumnHeaders(table, 2);
		comm.verifyColumnHeaders(table, colNames, 2);
	}
}
