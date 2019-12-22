package objectRepository.Reporting;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;
public class PerDayPerIMSIReport
{
    private SeleniumMethods com;
    private ApexCommon comm;
    public static String title = PagesTitle.PerDayPerIMSIReport .title;
    public PerDayPerIMSIReport () {
        PageFactory.initElements(DriverFactory.getDriver(), this);
        com = new SeleniumMethods();
        comm = new ApexCommon();
    }
    
    @FindBy(xpath="//li[.='IOTRON - Welcome'][contains(@class,'Breadcrumb')]")
    private WebElement breadcrumb_IOTRONWelcome;
    
    @FindBy(xpath="//li[.='Reporting'][contains(@class,'Breadcrumb')]")
    private WebElement breadcrumb_Reporting;
    
    @FindBy(xpath="//h1[contains(.,'Per Day Per IMSI Report')]")
    private WebElement data_PerDayPerIMSIReport_BreadCrumb;
    
    @FindBy(xpath="//h2[contains(.,'Parameters')]")
    private WebElement data_Parameters_Section;
    
    @FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Parameters')]//button[contains(@class,'hideShow')]")
    private WebElement hideShowIcon_Parameters;
    
    @FindBy(id="P613_SHOW_LIVE_AGRMTS_0")
    private WebElement radioButton_ShowLiveAgreements_Yes;
    
    @FindBy(id="P613_SHOW_LIVE_AGRMTS_1")
    private WebElement radioButton_ShowLiveAgreements_No;
    
    @FindBy(id="P613_IOT_AGREEMENT_ID")
    private WebElement dropdown_Agreement;
    
    @FindBy(id="P613_CURRENCY")
    private WebElement dropdown_Currency;
    
    @FindBy(id="P613_TRAFFIC_PERIOD")
    private WebElement dropdown_TrafficPeriod;
    
    @FindBy(id="P613_TRAFFIC_DIRECTION")
    private WebElement dropdown_TrafficDrirection;
    
    @FindBy(xpath="//button[contains(.,'Refresh Report')]")
    private WebElement button_RefreshReport;
    
    @FindBy(xpath="//li[contains(@class,'controlBreak')][contains(.,'Traffic Direction')]")
    private WebElement filter_TrafficDirection;
    
    @FindBy(xpath="//span[contains(.,'No data found')]")
    private WebElement data_NoDataFound_Message;
    
    //private List<String> colNames = new ArrayList<>(Arrays.asList());
    
    public void verify_UI(){
        CustomReporter.report(STATUS.INFO, "Section verification");
        com.isElementPresent(breadcrumb_IOTRONWelcome,"breadcrumb_IOTRON Welcome");
        com.isElementPresent(breadcrumb_Reporting,"breadcrumb_Reporting");
        com.isElementPresent(data_PerDayPerIMSIReport_BreadCrumb,"data_Per Day Per IMSI Report_BreadCrumb");
        com.isElementPresent(data_Parameters_Section,"data_Parameters_Section");
        com.isElementPresent(hideShowIcon_Parameters,"hide Show Icon_Parameters");
        com.isElementPresent(radioButton_ShowLiveAgreements_Yes,"radio Button_Show Live Agreements_Yes");
        com.isElementPresent(radioButton_ShowLiveAgreements_No,"radio Button_Show Live Agreements_No");
        com.isElementPresent(dropdown_Agreement,"dropdown_Agreement");
        com.isElementPresent(dropdown_Currency,"dropdown_Currency");
        com.isElementPresent(dropdown_TrafficPeriod,"dropdown_Traffic Period");
        com.isElementPresent(dropdown_TrafficDrirection,"dropdown_Traffic Drirection");
        com.isElementPresent(button_RefreshReport,"button_Refresh Report");
        
        CustomReporter.report(STATUS.INFO, "Search Results section verification");
        com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
		com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
		com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
		comm.verifyActionsPopupItems_WithoutSubscription();
        com.isElementPresent(filter_TrafficDirection,"filter_Traffic Direction");
        com.isElementPresent(data_NoDataFound_Message,"data_No Data Found_Message");
    }
    
}