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
import objectRepository.common.Header;
import objectRepository.common.PagesTitle;
import objectRepository.common.TADIG;

public class OutstandingRebatesPayable extends Header
{
    private SeleniumMethods com;
    private ApexCommon comm;
    public static String title = PagesTitle.OutstandingRebatesPayable .title;
    public OutstandingRebatesPayable () {
        PageFactory.initElements(DriverFactory.getDriver(), this);
        com = new SeleniumMethods();
        comm = new ApexCommon();
    }
    
    @FindBy(xpath="//li[contains(normalize-space(.),'Outstanding Rebates Payable')][contains(@class,'Breadcrumb')]")
    private WebElement data_OutstandingRebatesPayable_BreadCrumb;
    
    @FindBy(xpath="//h2[contains(.,'Report Parameters')]")
    private WebElement data_ReportParameters_Section;
    
    @FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Report Parameters')]//button[contains(@class,'hideShow')]")
    private WebElement hideShowIcon_ReportParameters;
    
    @FindBy(id="P673_CLIENT_MASTER_ENTITY_ID")
    private WebElement dropdown_HomeOperator;
    
    @FindBy(id="P673_FINANCIAL_YEAR")
    private WebElement dropdown_FinancialYear;
    
    @FindBy(id="P673_TAX_0")
    private WebElement radioButton_Tax_Gross;
    
    @FindBy(id="P673_TAX_1")
    private WebElement radioButton_Tax_Net;
    
    @FindBy(id="P673_TAX_2")
    private WebElement radioButton_Tax_Both;
    
    @FindBy(id="P673_CURRENCY_DISPLAY")
    private WebElement data_Currency_Display;
    
    @FindBy(xpath="//button[contains(.,'Refresh Report')]")
    private WebElement button_RefreshReport;
    
    @FindBy(xpath="//button[contains(.,'Reset Page')]")
    private WebElement button_ResetPage;
    
    @FindBy(xpath="//h2[contains(.,'Report Layout')]")
    private WebElement data_ReportLayout_Section;
    
    @FindBy(xpath="//button[@title='Maximize']")
    private WebElement button_MaximizeReport;
    
    @FindBy(xpath="//button[@title='RESTORE']")
    private WebElement button_RestoreReport;
    
    @FindBy(xpath="//span[contains(.,'No data found')]")
    private WebElement data_NoDataFound_Message;
    
	private List<String> colNames = new ArrayList<>(
			Arrays.asList("Roaming Partner", "Operator Group", "Agreement Reference Ascending", "Agreement Start Date",
					"Discount Achieved Incl. Tax July", "Discount Achieved Incl. Tax August",
					"Discount Achieved Incl. Tax September", "Discount Achieved Incl. Tax October",
					"Discount Achieved Incl. Tax November", "Discount Achieved Incl. Tax December",
					"Discount Achieved Incl. Tax January", "Discount Achieved Incl. Tax February",
					"Discount Achieved Incl. Tax March", "Discount Achieved Incl. Tax April",
					"Discount Achieved Incl. Tax May", "Discount Achieved Incl. Tax June", "YTD Total Incl. Tax",
					"Document Amount Incl. Tax", "Outstanding Payable Incl. Tax", "MoM Variance Last Month Incl. Tax"));
    
    public void verify_UI(TADIG TADIG, String finYear, String tax_Gross_Net_Both) {
        CustomReporter.report(STATUS.INFO, "Breadcrumb Section verification");
        com.isElementPresent(breadCrumb_IotronWelcome,"breadCrumb_Iotron Welcome");
        com.isElementPresent(breadCrumb_Reporting,"breadCrumb_Reporting");
        com.isElementPresent(data_OutstandingRebatesPayable_BreadCrumb,"data_Outstanding Rebates Payable_BreadCrumb");
        
        CustomReporter.report(STATUS.INFO, "Report Parameters Section verification");
        com.isElementPresent(data_ReportParameters_Section,"data_Report Parameters_Section heading");
        com.isElementPresent(hideShowIcon_ReportParameters,"hide Show Icon_Report Parameters");
        com.isElementPresent(dropdown_HomeOperator,"dropdown_Home Operator");
        com.isElementPresent(dropdown_FinancialYear,"dropdown_Financial Year");
        com.isElementPresent(radioButton_Tax_Gross,"radioButton_Tax_Gross");
        com.isElementPresent(radioButton_Tax_Net,"radioButton_Tax_Net");
        com.isElementPresent(radioButton_Tax_Both,"radioButton_Tax_Both");
        com.isElementPresent(data_Currency_Display,"Currency_Display data");
        com.isElementPresent(button_RefreshReport,"button_Refresh Report");
        com.isElementPresent(button_ResetPage,"button_Reset Page");
        
        CustomReporter.report(STATUS.INFO, "Result Section verification");
        com.isElementPresent(data_ReportLayout_Section,"data_Report Layout_Section heading");
        com.isElementPresent(data_NoDataFound_Message,"data_No Data Found_Message");
        
        if(performSearch(TADIG, finYear, tax_Gross_Net_Both)){
        	 if(com.isElementPresent(button_MaximizeReport,"button_Maximize Report")){
             	com.click(button_MaximizeReport,"button_Maximize Report");
             	com.isElementPresent(button_RestoreReport,"button_Restore Report");
             }
            
        	//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
        	comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);
        }
    }

	private boolean performSearch(TADIG TADIG, String finYear, String tax_Gross_Net_Both) {
		String message = "";

		if (TADIG != null) {
			com.selectByPartialVisibleText(dropdown_HomeOperator,TADIG.value);
			message = message + " | HomeOperator= '" + TADIG.value + "'";
			com.wait(3);
		}

		if (finYear != null) {
			com.selectByVisibleText(dropdown_FinancialYear, finYear);
			message = message + " | finYear= '" + finYear + "'";
		}

		if (tax_Gross_Net_Both != null) {
			if (tax_Gross_Net_Both.toLowerCase().contains("gross")) {
				com.click(radioButton_Tax_Gross);
			} else if (tax_Gross_Net_Both.toLowerCase().contains("net")) {
				com.click(radioButton_Tax_Net);
			}else{
				com.click(radioButton_Tax_Both);
			}
			message = message + " | tax_Gross_Net_Both= '" + tax_Gross_Net_Both + "'";
		}

		message = message + " | Displayed Currency= '" + com.getText(data_Currency_Display) + "'";
		
		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_RefreshReport);
		if (com.isElementPresent(comm.table_ResultTabHeader, "Search Results")) {
			return true;
		}
		return false;
	}
    
}