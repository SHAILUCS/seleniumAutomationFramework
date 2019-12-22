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

public class IntraGroupReporting {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Intra Group Reporting";

	public IntraGroupReporting() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Intra Group Reporting')]")
	private WebElement data_IntraGroupReporting_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Select Group and Period')]")
	private WebElement data_SelectGroupAndPeriod_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Select Group and Period')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_SelectGroupAndPeriod;

	@FindBy(id = "P491_IOT_OPERATOR_GROUP_ID")
	private WebElement select_Group;
	
	@FindBy(id = "P491_TRAFFIC_PERIOD_ID")
	private WebElement select_TrafficPeriod;

	@FindBy(id = "P491_USE_HOME_CURRENCY_0")
	private WebElement checkbox_UseHomeCurrency;
	
	@FindBy(id = "P491_CURRENCY")
	private WebElement select_Currency;

	@FindBy(xpath="//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;
	
	@FindBy(xpath = "//h2[contains(.,'Monthly Total Revenue/Cost')]")
	private WebElement data_MonthlyTotalRevenueCost_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Monthly Total Revenue/Cost')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_MonthlyTotalRevenueCost;

	@FindBy(xpath = "//li[contains(@class,'controls-item--controlBreak')][contains(.,'Operator')]")
	private WebElement data_ControlBreak_Operator;
	
	private List<String> colNames = new ArrayList<>(
			Arrays.asList("Direction Ascending", "Currency", "Voice MO [Min]", "Voice MT [Min]", "Sms [Message]",
					"Data [MB]", "Voice MO Charge", "Voice MT Charge", "SMS Charge", "Data Charge"));

	public void verify_UI(String group,String trafPeriod,boolean true_UseHomeCurr,Currency currency) {

		com.isElementPresent(data_IntraGroupReporting_BreadCrumb,
				"Intra Group Reporting heading in Bread Crumb");

		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_SelectGroupAndPeriod_Section, "Select Group And Period Section");
		com.isElementPresent(hideshowbutton_SelectGroupAndPeriod, "Select Group And Period Section hide show icon");

		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(select_Group, "Group dropdown");
		com.isElementPresent(select_TrafficPeriod, "Traffic Period dropdown");
		com.isElementPresent(checkbox_UseHomeCurrency, "Use Home Currency checkbox");
		com.isElementPresent(select_Currency, "Currency dropdown");
		
		com.isElementPresent(button_RefreshReport, "Refresh Report button");
		
		if (performSearch(group, trafPeriod, true_UseHomeCurr, currency)) {
			CustomReporter.report(STATUS.INFO, "Search Results section verification");
			
			com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
			com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
			com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
			com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
			comm.verifyActionsPopupItems_WithoutSubscription();

			com.isElementPresent(data_ControlBreak_Operator, "Operator result breakdown");
			
			//comm.printColumnHeaders(comm.table_ResultTab, 2);
			comm.verifyColumnHeaders(comm.table_ResultTab, colNames, 2);

		}
	}

	private boolean performSearch(String group,String trafPeriod,boolean true_UseHomeCurr,Currency currency) {
		String message = "";

		if (group != null) {
			com.selectByPartialVisibleText(select_Group, group);
			message = message + " | group= '" + group + "'";
		}

		if (trafPeriod != null) {
			com.selectByVisibleText(select_TrafficPeriod, trafPeriod);
			message = message + " | trafPeriod= '" + trafPeriod + "'";
		}

		if (true_UseHomeCurr) {
			com.click(checkbox_UseHomeCurrency);	
			message = message + " | UseHomeCurr= '" + true_UseHomeCurr + "'";
		}

		if (currency != null) {
			com.selectByPartialVisibleText(select_Currency, currency.value);
			message = message + " | currency= '" + currency.value + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_RefreshReport);
		if (com.isElementPresent(comm.table_ResultTab, "Result Table")) {
			return true;
		}

		return false;
	}



	

}
