package objectRepository.Reporting;

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
import objectRepository.common.Country;
import objectRepository.common.Header;
import objectRepository.common.PagesTitle;
import objectRepository.common.TADIG;

public class SavingsPerCountry extends Header{
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.SavingsPerCountry.title;

	public SavingsPerCountry() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath="//li[contains(normalize-space(.),'Savings per Country')][contains(@class,'Breadcrumb')]")
	private WebElement data_SavingsperCountry_BreadCrumb;

	@FindBy(xpath="//h2[contains(.,'Report Parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Report Parameters')]//button[contains(@class,'hideShow')]")
	private WebElement hideShowIcon_ReportParameters;

	@FindBy(id="P677_CLIENT_MASTER_ENTITY_ID")
	private WebElement dropdown_ClientPMNs;

	@FindBy(id="P677_FINANCIAL_YEAR")
	private WebElement dropdown_FinancialYear;

	@FindBy(id="P677_ALT_COUNTRY_CODE")
	private WebElement dropdown_Country;

	@FindBy(id="P677_HOME_DISC_AGRMNT_CURR_0")
	private WebElement radioButton_Home_Currency;

	@FindBy(id="P677_HOME_DISC_AGRMNT_CURR_1")
	private WebElement radioButton_DiscountAgreement_Currency;

	@FindBy(xpath="//h2[contains(.,'Agreement Selection')]")
	private WebElement data_AgreementSelection_Section;
	
	@FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Agreement Selection')]//button[contains(@class,'hideShow')]")
	private WebElement hideShowIcon_AgreementSelection;

	private By tab = By.cssSelector("table[summary='Agreement Selection']");

	private List<String> colNames_AgreementSelection = new ArrayList<>(
			Arrays.asList("New Discount Agreement", "Old Discount Agreement"));
	
	@FindBy(xpath = "//button[.='Create Report']")
	private WebElement button_CreateReport;

	private List<String> colNames = new ArrayList<>(Arrays.asList("Home PMN(s)", "Country", "Partner PMN(s)",
			"Operator/Group Name", "Agreement Reference", "Agreement Start Date", "Agreement End Date",
			"Traffic Direction", "Service Type", "Event Type", "Traffic Volume Actual", "Currency",
			"New Rate Excluding Tax", "Old Rate Excluding Tax", "New Discounted Charges Excluding Tax",
			"Old Discounted Charges Excluding Tax", "Savings Excluding Tax", "Savings %"));
	
	@FindBy(xpath = "//span[contains(.,'1 -')]")
	private WebElement data_Pagination;

	public void verify_UI(TADIG TADIG, String finYear, Country Country, String homeOrDiscountCurrency) {
		CustomReporter.report(STATUS.INFO, "Breadcrumb Section verification");
		com.isElementPresent(breadCrumb_IotronWelcome,"bread Crumb_Iotron Welcome");
		com.isElementPresent(breadCrumb_Reporting,"bread Crumb_Reporting");
		com.isElementPresent(data_SavingsperCountry_BreadCrumb,"data_Savings per Country_Bread Crumb");

		CustomReporter.report(STATUS.INFO, "Report Parameters Section verification");
		com.isElementPresent(data_ReportParameters_Section,"data_Report Parameters_Section heading");
		com.isElementPresent(hideShowIcon_ReportParameters,"hide Show Icon_Report Parameters");
		com.isElementPresent(dropdown_ClientPMNs,"dropdown_Client PMNs");
		com.isElementPresent(dropdown_FinancialYear,"dropdown_Financial Year");
		com.isElementPresent(dropdown_Country,"dropdown_Country");
		com.isElementPresent(radioButton_Home_Currency,"radio Button_Home_Currency");
		com.isElementPresent(radioButton_DiscountAgreement_Currency,"radio Discount Agreement_Currency");
		
		CustomReporter.report(STATUS.INFO, "Report Parameters Section verification");
		com.isElementPresent(data_AgreementSelection_Section,"data_Agreement Selection_Section heading");
		com.isElementPresent(hideShowIcon_AgreementSelection,"hide Show Icon_Agreement Selection");
		comm.verifyColumnHeaders(tab, colNames_AgreementSelection, 1);
		
		com.isElementPresent(button_CreateReport,"button_Create Report");
		
		if (performSearch(TADIG, finYear, Country, homeOrDiscountCurrency)) {
			CustomReporter.report(STATUS.INFO, "Report Section verification");
	        com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
	  		com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
	  		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
	  		com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
	  		comm.verifyActionsPopupItems_WithoutSubscription();
	  		
			//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
			comm.verifyColumnHeaders(comm.table_ResultTabHeader,colNames, 1);
			com.isElementPresent(data_Pagination, "data_Pagination "+com.getText(data_Pagination));
		}
	}

	private boolean performSearch(TADIG TADIG, String finYear, Country Country, String homeOrDiscountCurrency) {
		String message = "";

		if (TADIG != null) {
			com.selectByPartialVisibleText(dropdown_ClientPMNs,TADIG.value);
			message = message + " | ClientPMNs= '" + TADIG.value + "'";
			com.wait(3);
		}

		if (finYear != null) {
			com.selectByPartialVisibleText(dropdown_FinancialYear, finYear);
			message = message + " | finYear= '" + finYear + "'";
		}

		if (Country != null) {
			com.selectByVisibleText(dropdown_Country, Country.value );
			message = message + " | Country= '" + Country.value + "'";
		}

		if (homeOrDiscountCurrency != null) {
			if (homeOrDiscountCurrency.toLowerCase().contains("home")) {
				com.click(radioButton_Home_Currency);
			} else{
				com.click(radioButton_DiscountAgreement_Currency);
			}
			message = message + " | homeOrDiscountCurrency= '" + homeOrDiscountCurrency + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_CreateReport);
		if (com.isElementPresent(comm.table_ResultTabHeader, "Search Results")) {
			return true;
		}
		return false;
	}

}
