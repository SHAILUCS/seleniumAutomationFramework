package objectRepository.Reporting;

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
import objectRepository.common.Currency;
import objectRepository.common.TADIG;

public class TrafficDistributionAndThresholdTrackerReport {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Traffic Distribution and Threshold Tracker Report";

	public TrafficDistributionAndThresholdTrackerReport() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Traffic Distribution and Threshold Tracker Report')]")
	private WebElement data_TrafficDistributionandThresholdTrackerReport_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Report Parameters')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_ReportParameters;

	@FindBy(id = "P508_HOME_PARTY_LEFT")
	private WebElement select_HomeParty_Left;
	
	@FindBy(id = "P508_HOME_PARTY_RIGHT")
	private WebElement select_HomeParty_Right;

	@FindBy(id = "P508_TRAFFIC_PERIOD")
	private WebElement select_TrafficPeriod;

	@FindBy(id = "P508_CURRENCY")
	private WebElement select_Currency;

	@FindBy(xpath="//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;

	@FindBy(xpath = "//ul[@class='a-IRR-controls']/li[contains(.,'achieved_vs_commited_no')]")
	private WebElement highlightFilter_achieved_vs_commited_no;
	
	@FindBy(xpath = "//ul[@class='a-IRR-controls']/li[contains(.,'achieved_vs_commited_yes')]")
	private WebElement highlightFilter_achieved_vs_commited_yes;
	
	@FindBy(xpath = "//ul[@class='a-IRR-controls']/li[contains(.,'achieved_vs_commited_local_no')]")
	private WebElement highlightFilter_achieved_vs_commited_local_no;
	
	@FindBy(xpath = "//ul[@class='a-IRR-controls']/li[contains(.,'achieved_vs_commited_local_yes')]")
	private WebElement highlightFilter_achieved_vs_commited_local_yes;	
	
	@FindBy(xpath = "//ul[@class='a-IRR-controls']/li[contains(.,'group_commitment_not_1st_rank')]")
	private WebElement highlightFilter_group_commitment_not_1st_rank;	
	
	@FindBy(xpath = "//ul[@class='a-IRR-controls']/li[contains(.,'forcasted_vs_commited_yes')]")
	private WebElement highlightFilter_forcasted_vs_commited_yes;	
	
	@FindBy(xpath = "//ul[@class='a-IRR-controls']/li[contains(.,'forcasted_vs_commited_local_no')]")
	private WebElement highlightFilter_forcasted_vs_commited_local_no;	
	
	@FindBy(xpath = "//ul[@class='a-IRR-controls']/li[contains(.,'forcasted_vs_commited_local_yes')]")
	private WebElement highlightFilter_forcasted_vs_commited_local_yes;	
	
	@FindBy(xpath = "//ul[@class='a-IRR-controls']/li[contains(.,'forcasted_vs_commited_no')]")
	private WebElement highlightFilter_forcasted_vs_commited_no;	
	
	private List<String> colNames = new ArrayList<>(Arrays.asList("Country", "Home Operator(s)", "Operator/Group Name",
			"Partner PMN", "MCC+MNC", "Service Type", "Event Type", "Traffic Volume Previous Period Cumulative",
			"Traffic Volume Previous Period Monthly", "Traffic Volume Current Period Cumulative",
			"Traffic Volume Current Period Monthly", "Group Commitment",
			"Committed Traffic Volume in Discounting Period", "Achieved vs. Commitment (Group deal) [%]:",
			"Forecasted vs. Commitment (Group deal) [%]", "Local Commitment",
			"Committed Traffic Volume in Discounting Period", "Achieved vs. Commitment (Local deal) [%]:",
			"Forecasted vs. Commitment (Local deal) [%]", "Average IOT Charge", "Ranking", "Iot Agreement Available"));

	public void verify_UI(TADIG[] tadigs,String trafPeriod,Currency Currency) {
		com.isElementPresent(data_TrafficDistributionandThresholdTrackerReport_BreadCrumb,
				"Traffic Distribution and Threshold Tracker Report heading in Bread Crumb");

		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_ReportParameters_Section, "Report Parameters Section");
		com.isElementPresent(hideshowbutton_ReportParameters, "Report Parameters Section hide show icon");

		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(select_HomeParty_Left, "Home Party Left shuttle");
		com.isElementPresent(select_HomeParty_Right, "Home Party Right shuttle");
		com.isElementPresent(select_TrafficPeriod, "Traffic Period dropdown");
		com.isElementPresent(select_Currency, "Currency dropdown");
		com.isElementPresent(button_RefreshReport, "Refresh Report button");

		if (performSearch(tadigs, trafPeriod, Currency)){
			com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex toolbar - Search Columns icon");
			com.isElementPresent(comm.text_Search, "Apex toolbar - Search textfield");
			com.isElementPresent(comm.button_Go, "Apex toolbar - Go button");
			com.isElementPresent(comm.select_RowsPerPage, "Apex toolbar - Rows dropdown");
			comm.verifyActionsPopupItems_WithSubscription();
			
			com.isElementPresent(highlightFilter_achieved_vs_commited_no,"achieved_vs_commited_no highlightFilter");
			com.isElementPresent(highlightFilter_achieved_vs_commited_yes,"achieved_vs_commited_yes highlightFilter");
			com.isElementPresent(highlightFilter_achieved_vs_commited_local_no,"achieved_vs_commited_local_no highlightFilter");
			com.isElementPresent(highlightFilter_achieved_vs_commited_local_yes,"achieved_vs_commited_local_yes highlightFilter");
			com.isElementPresent(highlightFilter_group_commitment_not_1st_rank,"group_commitment_not_1st_rank highlightFilter");
			com.isElementPresent(highlightFilter_forcasted_vs_commited_yes,"forcasted_vs_commited_yes highlightFilter");
			com.isElementPresent(highlightFilter_forcasted_vs_commited_local_no,"forcasted_vs_commited_local_no highlightFilter");
			com.isElementPresent(highlightFilter_forcasted_vs_commited_local_yes,"forcasted_vs_commited_local_yes highlightFilter");
			com.isElementPresent(highlightFilter_forcasted_vs_commited_no,"forcasted_vs_commited_no highlightFilter");
			
			//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);
		}
	}
	
	private boolean performSearch(TADIG[] tadigs,String trafPeriod,Currency Currency) {
		String message = "";

		if (tadigs != null) {
			comm.selectMultipleValues_DoubleClick_FromArray(select_HomeParty_Left, tadigs);
			message = message + " | HomeOperators= '" + Arrays.toString(tadigs) + "'";
			com.wait(4);
		}

		if (trafPeriod != null) {
			com.selectByVisibleText(select_TrafficPeriod, trafPeriod);
			message = message + " | trafPeriod= '" + trafPeriod+ "'";
		}

		if (Currency != null) {
			com.selectByPartialVisibleText(select_Currency, Currency.value);
			message = message + " | Currency= '" + Currency.value + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_RefreshReport);
		if (com.isElementPresent(comm.table_ResultTabHeader, "Search Results")) {
			return true;
		}

		return false;
	}


	
}
