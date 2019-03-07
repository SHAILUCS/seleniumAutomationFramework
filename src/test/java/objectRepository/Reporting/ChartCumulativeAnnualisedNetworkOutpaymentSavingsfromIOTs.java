package objectRepository.Reporting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.Header;
import objectRepository.common.PagesTitle;
import objectRepository.common.TADIG;
public class ChartCumulativeAnnualisedNetworkOutpaymentSavingsfromIOTs extends Header
{
    private SeleniumMethods com;
    public static String title = PagesTitle.ChartCumulativeAnnualisedNetworkOutpaymentSavingsFromIOTs.title;
    public ChartCumulativeAnnualisedNetworkOutpaymentSavingsfromIOTs () {
        PageFactory.initElements(DriverFactory.getDriver(), this);
        com = new SeleniumMethods();
    }
    
    @FindBy(xpath="//li[contains(normalize-space(.),'Chart Cumulative Annualised Network Outpayment Savings from IOTs')][contains(@class,'Breadcrumb')]")
	protected WebElement data_ChartCumulativeAnnualisedNetworkOutpaymentSavingsfromIOTs_BreadCrumb;
    
    @FindBy(xpath="//h2[contains(.,'Chart Configuration')]")
    private WebElement data_ChartConfiguration_Section;
    
    @FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Chart Configuration')]//button[contains(@class,'hideShow')]")
    private WebElement hideShowIcon_ChartConfiguration;
    
    @FindBy(id="P678_CLIENT_MASTER_ENTITY_ID")
    private WebElement dropdown_ClientPMNs;
    
    @FindBy(id="P678_FINANCIAL_YEAR")
    private WebElement dropdown_FinancialYear;
    
    @FindBy(xpath="//button[contains(.,'Create Chart')]")
    private WebElement button_CreateChart;
    
    @FindBy(xpath="//button[contains(.,'Configure IOT Discount Agreements for Calculation')]")
    private WebElement button_ConfigureIOTDiscountAgreementsforCalculation;
    
    
    public void verify_UI(){
    	CustomReporter.createNode("Breadcrumb Section verification");
        com.isElementPresent(breadCrumb_IotronWelcome,"bread Crumb_Iotron Welcome");
        com.isElementPresent(breadCrumb_Reporting,"bread Crumb_Reporting");
        com.isElementPresent(data_ChartCumulativeAnnualisedNetworkOutpaymentSavingsfromIOTs_BreadCrumb,"data_Chart Cumulative Annualised Network Outpayment Savings from IOTs_Bread Crumb");
        
        CustomReporter.createNode("Chart Configuration Section verification");
        com.isElementPresent(data_ChartConfiguration_Section,"data_Chart Configuration_Section");
        com.isElementPresent(hideShowIcon_ChartConfiguration,"hide Show Icon_Chart Configuration");
        com.isElementPresent(dropdown_ClientPMNs,"dropdown_Client PMNs");
        com.isElementPresent(dropdown_FinancialYear,"dropdown_Financial Year");
        com.isElementPresent(button_CreateChart,"button_Create Chart");
        com.isElementPresent(button_ConfigureIOTDiscountAgreementsforCalculation,"button_Configure IOT Discount Agreements for Calculation");
    }
    
    public ConfigureIOTDiscountAgreementsforCalculationofCumulativeAnnualisedNetworkOutpaymentSavings click_ConfigureIOTDiscountAgreementsForCalculation_Button(TADIG TADIG, String finYear){
    	fillBasicSearchFields(TADIG, finYear);
    	if(com.navigateToAndVerifyPageTitle(button_ConfigureIOTDiscountAgreementsforCalculation, PagesTitle.ConfigureIOTDiscountAgreementsforCalculationofCumulativeAnnualisedNetworkOutpaymentSavings.title)){
    		return new ConfigureIOTDiscountAgreementsforCalculationofCumulativeAnnualisedNetworkOutpaymentSavings();
    	}
    	return null;
    }

   private void fillBasicSearchFields(TADIG TADIG, String finYear) {
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

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

	}

	public boolean performSearch(TADIG TADIG, String finYear) {
		fillBasicSearchFields(TADIG, finYear);
		com.click(button_CreateChart);
		return true;
	}

    
    
}