package objectRepository.Reporting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;
public class TotalAndPremiumNumberVolumeAndTAPCharges
{
    private SeleniumMethods com;
    private ApexCommon comm;
    public static String title = PagesTitle.TotalAndPremiumNumberVolumeAndTAPCharges .title;
    public TotalAndPremiumNumberVolumeAndTAPCharges () {
        PageFactory.initElements(DriverFactory.getDriver(), this);
        com = new SeleniumMethods();
        comm = new ApexCommon();
    }
    
    @FindBy(xpath="//li[.='IOTRON - Welcome'][contains(@class,'Breadcrumb')]")
    private WebElement breadcrumb_IOTRONWelcome;
    
    @FindBy(xpath="//li[.='Reporting'][contains(@class,'Breadcrumb')]")
    private WebElement breadcrumb_Reporting;
    
    @FindBy(xpath="//h1[contains(.,'Total and Premium Number Volume and TAP Charges')]")
    private WebElement data_TotalandPremiumNumberVolumeandTAPCharges_BreadCrumb;
    
    @FindBy(xpath="//h2[contains(.,'Parameters')]")
    private WebElement data_Parameters_Section;
    
    @FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Parameters')]//button[contains(@class,'hideShow')]")
    private WebElement hideShowIcon_Parameters;
    
    @FindBy(id="P634_CLIENT_ME_ID")
    private WebElement dropdown_ClientPmn;
    
    @FindBy(id="P634_TRAFFIC_DIRECTION")
    private WebElement dropdown_TrafficDirection;
    
    @FindBy(id="P634_SHOW_LIVE_AGRMTS_0")
    private WebElement radioButton_ShowLiveAgreements_Yes;
    
    @FindBy(id="P634_SHOW_LIVE_AGRMTS_1")
    private WebElement radioButton_ShowLiveAgreements_No;
    
    @FindBy(id="P634_IOT_AGREEMENT_ID")
    private WebElement dropdown_Agreement;
    
    @FindBy(id="P634_CURRENCY")
    private WebElement dropdown_Currency;
    
    @FindBy(xpath="//button[contains(.,'Refresh')]")
    private WebElement button_Refresh;
    
    @FindBy(xpath="//span[contains(.,'No data found')]")
    private WebElement data_NoDataFound_Message;
    
    //private List<String> colNames = new ArrayList<>(Arrays.asList());
    
    public void verify_UI(){
        CustomReporter.report(STATUS.INFO, "Breadcrumb Section verification");
        com.isElementPresent(breadcrumb_IOTRONWelcome,"breadcrumb_IOTRON Welcome");
        com.isElementPresent(breadcrumb_Reporting,"breadcrumb_Reporting");
        com.isElementPresent(data_TotalandPremiumNumberVolumeandTAPCharges_BreadCrumb,"data_Total and Premium Number Volume and TAP Charges_Bread Crumb");
        
        CustomReporter.report(STATUS.INFO, "Parameters Section verification");
        com.isElementPresent(data_Parameters_Section,"data_Parameters_Section");
        com.isElementPresent(hideShowIcon_Parameters,"hide Show Icon_Parameters");
        com.isElementPresent(dropdown_ClientPmn,"dropdown_Client Pmn");
        com.isElementPresent(dropdown_TrafficDirection,"dropdown_Traffic Direction");
        com.isElementPresent(radioButton_ShowLiveAgreements_Yes,"radio Button_Show Live Agreements_Yes");
        com.isElementPresent(radioButton_ShowLiveAgreements_No,"radio Button_Show Live Agreements_No");
        com.isElementPresent(dropdown_Agreement,"dropdown_Agreement");
        com.isElementPresent(dropdown_Currency,"dropdown_Currency");
        com.isElementPresent(button_Refresh,"button_Refresh");
        
        CustomReporter.report(STATUS.INFO, "Report Section verification");
        com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
  		com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
  		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
  		com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
  		comm.verifyActionsPopupItems_WithoutSubscription();
        com.isElementPresent(data_NoDataFound_Message,"data_No Data Found_Message");
    }
    
}