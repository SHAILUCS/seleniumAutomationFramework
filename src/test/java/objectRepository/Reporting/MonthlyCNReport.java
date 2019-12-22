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
public class MonthlyCNReport
{
    private SeleniumMethods com;
    private ApexCommon comm;
    public static String title = PagesTitle.MonthlyCNReport .title;
    public MonthlyCNReport () {
        PageFactory.initElements(DriverFactory.getDriver(), this);
        com = new SeleniumMethods();
        comm = new ApexCommon();
    }
    
    @FindBy(xpath="//li[.='IOTRON - Welcome'][contains(@class,'Breadcrumb')]")
    private WebElement breadcrumb_IOTRONWelcome;
    
    @FindBy(xpath="//li[.='Reporting'][contains(@class,'Breadcrumb')]")
    private WebElement breadcrumb_Reporting;
    
    @FindBy(xpath="//h1[contains(.,'Monthly CN report')]")
    private WebElement data_MonthlyCNreport_BreadCrumb;
    
    @FindBy(xpath="//h2[contains(.,'Report Parameters')]")
    private WebElement data_ReportParameters_Section;
    
    @FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Report Parameters')]//button[contains(@class,'hideShow')]")
    private WebElement hideShowIcon_ReportParameters;
    
    @FindBy(id="P609_TRAFFIC_PERIOD")
    private WebElement dropdown_TrafficPeriod;
    
    @FindBy(id="P609_IOT_AGREEMENT_ID")
    private WebElement dropdown_AgreementReference;
    
    @FindBy(xpath="//button[contains(.,'Refresh Report')]")
    private WebElement button_RefreshReport;
    
    @FindBy(xpath="//h2[contains(.,'IMSI reporting - Monthly CN report')]")
    private WebElement data_IMSIReportingMonthlyCNReport_Section;
    
    @FindBy(xpath="//li[contains(@class,'controlBreak')][contains(.,'Agreement Reference')]")
    private WebElement filter_AgreementReference;
    
    @FindBy(xpath="//span[contains(.,'No data found')]")
    private WebElement data_NoDataFound_Message;
    
    //private List<String> colNames = new ArrayList<>(Arrays.asList());
    
    public void verify_UI(){
        CustomReporter.report(STATUS.INFO, "Breadcrumb Section verification");
        com.isElementPresent(breadcrumb_IOTRONWelcome,"breadcrumb_IOTRON Welcome");
        com.isElementPresent(breadcrumb_Reporting,"breadcrumb_Reporting");
        com.isElementPresent(data_MonthlyCNreport_BreadCrumb,"data_Monthly CN report_Bread Crumb");
        
        CustomReporter.report(STATUS.INFO, "Report Parameters Section verification");
        com.isElementPresent(data_ReportParameters_Section,"data_Report Parameters_Section");
        com.isElementPresent(hideShowIcon_ReportParameters,"hide Show Icon_Report Parameters");
        com.isElementPresent(dropdown_TrafficPeriod,"dropdown_Traffic Period");
        com.isElementPresent(dropdown_AgreementReference,"dropdown_Agreement Reference");
        com.isElementPresent(button_RefreshReport,"button_Refresh Report");
        
        CustomReporter.report(STATUS.INFO, "IMSI Reporting Monthly CN Report Section verification");
        com.isElementPresent(data_IMSIReportingMonthlyCNReport_Section,"data_IMSI Reporting Monthly CN Report_Section");
        com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
		com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
		com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
		comm.verifyActionsPopupItems_WithSubscription();
        com.isElementPresent(filter_AgreementReference,"filter_Agreement Reference");
        
        if (!com.waitForElementTobe_NotVisible(data_NoDataFound_Message, 1)) {
			CustomReporter.report(STATUS.PASS, "data_No Data Found_ Message is displayed");
		} else {
			CustomReporter.report(STATUS.WARNING, "data_No Data Found_ Message is NOT displayed");
		}
		
		
        
    }
    
}