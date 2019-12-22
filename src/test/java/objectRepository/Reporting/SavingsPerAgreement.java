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
import objectRepository.common.Header;
import objectRepository.common.PagesTitle;
import objectRepository.common.TADIG;
public class SavingsPerAgreement extends Header
{
    private SeleniumMethods com;
    private ApexCommon comm;
    public static String title = PagesTitle.SavingsPerAgreement .title;
    public SavingsPerAgreement () {
        PageFactory.initElements(DriverFactory.getDriver(), this);
        com = new SeleniumMethods();
        comm = new ApexCommon();
    }
    
    @FindBy(xpath="//li[contains(normalize-space(.),'Savings per Agreement')][contains(@class,'Breadcrumb')]")
    private WebElement data_SavingsperAgreement_BreadCrumb;
    
    @FindBy(xpath="//h2[contains(.,'Report Parameters')]")
    private WebElement data_ReportParameters_Section;
    
    @FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Report Parameters')]//button[contains(@class,'hideShow')]")
    private WebElement hideShowIcon_ReportParameters;
    
    @FindBy(id="P674_CLIENT_MASTER_ENTITY_ID")
    private WebElement dropdown_ClientPMNs;
    
    @FindBy(id="P674_IOT_AGREEMENT_ID_NEW")
    private WebElement dropdown_NewDiscountAgreement;
    
    @FindBy(id="P674_IOT_AGREEMENT_ID_OLD")
    private WebElement dropdown_OldDiscountAgreement;
    
    @FindBy(id="P674_HOME_DISC_AGRE_CURR_0")
    private WebElement radioButton_Home_Currency;
    
    @FindBy(id="P674_HOME_DISC_AGRE_CURR_1")
    private WebElement radioButton_DiscountAgreement_Currency;
    
    @FindBy(id="P674_BREAKDOWN_PER_PMN_0")
    private WebElement checkBox_BreakdownPerPmn;
    
    @FindBy(xpath="//button[contains(.,'Refresh Report')]")
    private WebElement button_RefreshReport;
    
    private List<String> colNames = new ArrayList<>(Arrays.asList("Home PMN(s)", "Country", "Partner PMN(s)",
			"Operator / Group Name", "Agreement Reference", "Agreement Start Date", "Agreement End Date",
			"Traffic Direction", "Service Type", "Event Type", "Traffic Volume Actual", "Currency",
			"New Rate Excluding Tax", "Old Rate Excluding Tax", "New Discounted Charges Excluding Tax",
			"Old Discounted Charges Excluding Tax", "Savings Excluding Tax", "Savings %"));
    
    @FindBy(xpath="//h2[contains(.,'Savings per Financial Year')]")
    private WebElement data_SavingsperFinancialYear_Section;
    
    @FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Savings per Financial Year')]//button[contains(@class,'hideShow')]")
    private WebElement hideShowIcon_SavingsperFinancialYear;
    
    private By tab_SavingsperFinancialYear=By.cssSelector("table[summary='Savings per Financial Year']");
    
	private List<String> colNames_SavingsperFinancialYear = new ArrayList<>(
			Arrays.asList("", "Traffic Direction", "Currency"));
    
	public void verify_UI(TADIG TADIG, String newDiscountAgreement, String oldDiscountAgreement, String homeOrDiscountCurrency, boolean breakdownPerPmn) {
        CustomReporter.report(STATUS.INFO, "Breadcrumb Section verification");
        com.isElementPresent(breadCrumb_IotronWelcome,"bread Crumb_Iotron Welcome");
        com.isElementPresent(breadCrumb_Reporting,"bread Crumb_Reporting");
        com.isElementPresent(data_SavingsperAgreement_BreadCrumb,"data_Savings per Agreement_Bread Crumb");
        
        CustomReporter.report(STATUS.INFO, "Report Parameters Section verification");
        com.isElementPresent(data_ReportParameters_Section,"data_Report Parameters_Section heading");
        com.isElementPresent(hideShowIcon_ReportParameters,"hide Show Icon_Report Parameters");
        com.isElementPresent(dropdown_ClientPMNs,"dropdown_Client PMNs");
        com.isElementPresent(dropdown_NewDiscountAgreement,"dropdown_New Discount Agreement");
        com.isElementPresent(dropdown_OldDiscountAgreement,"dropdown_Old Discount Agreement");
        com.isElementPresent(radioButton_Home_Currency,"radio Button_Home_Currency");
        com.isElementPresent(radioButton_DiscountAgreement_Currency,"radio Button_Discount Agreement_Currency");
        
        com.isElementPresent(button_RefreshReport,"button_Refresh Report");
        if (performSearch(TADIG, newDiscountAgreement, oldDiscountAgreement, homeOrDiscountCurrency, breakdownPerPmn)) {
        	com.isElementPresent(checkBox_BreakdownPerPmn,"checkBox_Breakdown Per Pmn");
        	//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
        	comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);
        	
        	CustomReporter.report(STATUS.INFO, "Savings per Financial Year Section verification");
            com.isElementPresent(data_SavingsperFinancialYear_Section,"data_Savings per Financial Year_Section");
            com.isElementPresent(hideShowIcon_SavingsperFinancialYear,"hide Show Icon_Savings per Financial Year");
        
        	//comm.printColumnHeaders(tab_SavingsperFinancialYear, 1);
        	comm.verifyColumnHeaders(tab_SavingsperFinancialYear, colNames_SavingsperFinancialYear, 1);
            
        	
		}
        
    }

	private boolean performSearch(TADIG TADIG, String newDiscountAgreement, String oldDiscountAgreement, String homeOrDiscountCurrency, boolean breakdownPerPmn) {
		String message = "";

		if (TADIG != null) {
			com.selectByPartialVisibleText(dropdown_ClientPMNs,TADIG.value);
			message = message + " | ClientPMNs= '" + TADIG.value + "'";
			com.wait(3);
		}

		if (newDiscountAgreement != null) {
			com.selectByPartialVisibleText(dropdown_NewDiscountAgreement, newDiscountAgreement);
			message = message + " | newDiscountAgreement= '" + newDiscountAgreement + "'";
		}

		if (oldDiscountAgreement != null) {
			com.selectByPartialVisibleText(dropdown_OldDiscountAgreement, oldDiscountAgreement );
			message = message + " | oldDiscountAgreement= '" + oldDiscountAgreement + "'";
		}

		if (homeOrDiscountCurrency != null) {
			if (homeOrDiscountCurrency.toLowerCase().contains("home")) {
				com.click(radioButton_Home_Currency);
			} else{
				com.click(radioButton_DiscountAgreement_Currency);
			}
			message = message + " | homeOrDiscountCurrency= '" + homeOrDiscountCurrency + "'";
		}

		if (breakdownPerPmn) {
			com.click(checkBox_BreakdownPerPmn);
			message = message + " | breakdownPerPmn= '" + breakdownPerPmn + "'";
		}
		
		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_RefreshReport);
		if (com.isElementPresent(comm.table_ResultTabHeader, "Search Results")) {
			return true;
		}
		return false;
	}
    
    
}