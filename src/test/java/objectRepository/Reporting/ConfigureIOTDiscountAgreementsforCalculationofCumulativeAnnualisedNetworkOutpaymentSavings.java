package objectRepository.Reporting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;
public class ConfigureIOTDiscountAgreementsforCalculationofCumulativeAnnualisedNetworkOutpaymentSavings extends ChartCumulativeAnnualisedNetworkOutpaymentSavingsfromIOTs
{
    private SeleniumMethods com;
    private ApexCommon comm;
    public static String title = PagesTitle.ConfigureIOTDiscountAgreementsforCalculationofCumulativeAnnualisedNetworkOutpaymentSavings .title;
    public ConfigureIOTDiscountAgreementsforCalculationofCumulativeAnnualisedNetworkOutpaymentSavings () {
        PageFactory.initElements(DriverFactory.getDriver(), this);
        com = new SeleniumMethods();
        comm = new ApexCommon();
    }
    
    @FindBy(xpath="//li[contains(normalize-space(.),'Configure IOT Discount Agreements for Calculation of Cumulative Annualised Network Outpayment Savings')][contains(@class,'Breadcrumb')]")
    private WebElement data_ConfigureIOTDiscountAgreementsforCalculationofCumulativeAnnualisedNetworkOutpaymentSavings_BreadCrumb;
    
    @FindBy(xpath="//h2[contains(.,'Agreement Selection')]")
    private WebElement data_AgreementSelection_Section;
    
    @FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Agreement Selection')]//button[contains(@class,'hideShow')]")
    private WebElement hideShowIcon_AgreementSelection;
    
    @FindBy(id="P679_SHOW_AGREEMENTS_0")
    private WebElement radioButton_ShowAgreement_All;
    
    @FindBy(id="P679_SHOW_AGREEMENTS_1")
    private WebElement radioButton_ShowAgreement_WithMissingOldDiscountAgreement;
    
    @FindBy(xpath="//button[contains(.,'Cancel')]")
    private WebElement button_Cancel;
    
    @FindBy(xpath="//button[contains(.,'Submit')]")
    private WebElement button_Submit;
    
    @FindBy(xpath="//input[@name=\"p_filter\"]")
    private WebElement textfield_AgreementReference_SearchDialog;
    
    @FindBy(css="input[value='Search']")
    private WebElement button_Search_SearchDialog;
    
    @FindBy(css="input[value='Close']")
    private WebElement button_Close_SearchDialog;
    
    @FindBy(xpath="//div[@class='t-PopupLOV-links']//a")
    private List<WebElement> links_AgreementReferences_SearchDialog;
    
    private By tab= By.cssSelector("table[summary='Agreement Selection']");
    
    private List<String> colNames = new ArrayList<>(Arrays.asList("New Discount Agreement","Old Discount Agreement"));
    
    public void verify_UI(){
    	CustomReporter.report(STATUS.INFO, "Breadcrumb Section verification");
        com.isElementPresent(breadCrumb_IotronWelcome,"bread Crumb_Iotron Welcome");
        com.isElementPresent(breadCrumb_Reporting,"bread Crumb_Reporting");
        com.isElementPresent(data_ChartCumulativeAnnualisedNetworkOutpaymentSavingsfromIOTs_BreadCrumb,"data_Chart Cumulative Annualised Network Outpayment Savings from IOTs_Bread Crumb");
        com.isElementPresent(data_ConfigureIOTDiscountAgreementsforCalculationofCumulativeAnnualisedNetworkOutpaymentSavings_BreadCrumb,"data_Configure IOT Discount Agreements for Calculation of Cumulative Annualised Network Outpayment Savings_Bread Crumb");
        
        CustomReporter.report(STATUS.INFO, "Agreement Selection Section verification");
        com.isElementPresent(data_AgreementSelection_Section,"data_Agreement Selection_Section");
        com.isElementPresent(hideShowIcon_AgreementSelection,"hide Show Icon_Agreement Selection");
        com.isElementPresent(radioButton_ShowAgreement_All,"radio Button_Show Agreement_All");
        com.isElementPresent(radioButton_ShowAgreement_WithMissingOldDiscountAgreement,"radio Button_Show Agreement_With Missing Old Discount Agreement");
        com.isElementPresent(button_Cancel,"button_Cancel");
        com.isElementPresent(button_Submit,"button_Submit");
        
        comm.verifyColumnHeaders(tab, colNames, 1);
        WebTable webTab= new WebTable(tab);
        int rowCount=webTab.getRowCount();
        if (rowCount>1) {
        	com.click(webTab.getChildObject(2, 2, "a", 0));
        	
        	String oldHandle=com.getWindowHandle();
        	Set<String> list=com.getWindowHandles();
        	for (String handle : list) {
        		com.switchTo_Window(handle);
        		if(com.getTitle().equals("Search Dialog")){
        			CustomReporter.report(STATUS.PASS, "Search Dialog popup displayed");
        			break;
        		}
			}
        	com.isElementPresent(textfield_AgreementReference_SearchDialog,"textfield_Agreement Reference_Search Dialog");
            com.isElementPresent(button_Search_SearchDialog,"button_Search_Search Dialog");
            com.isElementPresent(button_Close_SearchDialog,"button_Close_Search Dialog");
            if(links_AgreementReferences_SearchDialog.size()>0){
            	CustomReporter.report(STATUS.PASS, "Agreement list displayed successfully");
            }else {
            	CustomReporter.report(STATUS.FAIL, "Agreement list is not displayed");
            }
            com.close();
            com.switchTo_Window(oldHandle);
		}
       
    }
    
}