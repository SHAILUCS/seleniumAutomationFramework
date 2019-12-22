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
import objectRepository.common.PagesTitle;
import objectRepository.common.TADIG;
public class RateAuditReport
{
    private SeleniumMethods com;
    private ApexCommon comm;
    public static String title = PagesTitle.RateAuditReport .title;
    public RateAuditReport () {
        PageFactory.initElements(DriverFactory.getDriver(), this);
        com = new SeleniumMethods();
        comm = new ApexCommon();
    }
    
    @FindBy(xpath="//li[.='IOTRON - Welcome'][contains(@class,'Breadcrumb')]")
    private WebElement breadcrumb_IOTRONWelcome;
    
    @FindBy(xpath="//li[.='Reporting'][contains(@class,'Breadcrumb')]")
    private WebElement breadcrumb_Reporting;
    
    @FindBy(xpath="//h1[contains(.,'Rate Audit Report')]")
    private WebElement data_RateAuditReport_BreadCrumb;
    
    @FindBy(xpath="//h2[contains(.,'Parameters')]")
    private WebElement data_Parameters_Section;
    
    @FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Parameters')]//button[contains(@class,'hideShow')]")
    private WebElement hideShowIcon_Parameters;
    
    @FindBy(id="P597_CLIENT_ID")
    private WebElement dropdown_Operator;
    
    @FindBy(id="P597_TRAFFIC_PERIOD_LEFT")
    private WebElement shuttle_TrafficPeriod_Left;
    
    @FindBy(id="P597_TRAFFIC_PERIOD_RIGHT")
    private WebElement shuttle_TrafficPeriod_Right;
    
    @FindBy(id="P597_NET_GROSS_0")
    private WebElement radioButton_Gross;
    
    @FindBy(id="P597_NET_GROSS_1")
    private WebElement radioButton_Net;
    
    @FindBy(id="P597_CURRENCY_0")
    private WebElement radioButton_HomeCurrency;
    
    @FindBy(id="P597_CURRENCY_1")
    private WebElement radioButton_DiscountCurrency;
    
    @FindBy(xpath="//button[contains(.,'Refresh')]")
    private WebElement button_Refresh;
    
    @FindBy(xpath="//h2[contains(.,'Rate Audit Report')]")
    private WebElement data_RateAuditReport_Section;
    
    @FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Rate Audit Report')]//button[contains(@class,'hideShow')]")
    private WebElement hideShowIcon_RateAuditReport;
    
    @FindBy(xpath="//li[contains(@class,'filter')][contains(.,'Called Country is not null')]")
    private WebElement filter_CalledCountryIsNotNull;
    
   
	private List<String> colNames = new ArrayList<>(
			Arrays.asList("", "Traffic Direction", "Traffic Period", "Agreement Reference", "Client PMN", "Partner PMN",
					"Type", "LVL1", "Called Country", "Billed Traffic Volume [min/msg/kb]", "Charges", "Currency",
					"Rate Charged", "Correct Rate", "Variance", "Percent", "Comments"));
    
    public void verify_UI(TADIG TADIG, String[] trafPeriod, String grossOrNet, String homeOrDiscountCurrency) {
        CustomReporter.report(STATUS.INFO, "Breadcrumb Section verification");
        com.isElementPresent(breadcrumb_IOTRONWelcome,"breadcrumb_IOTRONWelcome");
        com.isElementPresent(breadcrumb_Reporting,"breadcrumb_Reporting");
        com.isElementPresent(data_RateAuditReport_BreadCrumb,"data_RateAuditReport_BreadCrumb");
        
        CustomReporter.report(STATUS.INFO, "Parameters Section verification");
        com.isElementPresent(data_Parameters_Section,"Parameters_Section heading");
        com.isElementPresent(hideShowIcon_Parameters,"Parameters section hide Show Icon");
        com.isElementPresent(dropdown_Operator,"Operator dropdown");
        com.isElementPresent(shuttle_TrafficPeriod_Left,"Traffic Period_Left shuttle");
        com.isElementPresent(shuttle_TrafficPeriod_Right,"Traffic Period_Right shuttle");
        com.isElementPresent(radioButton_Gross,"Gross radio Button");
        com.isElementPresent(radioButton_Net,"Net radio Button");
        com.isElementPresent(radioButton_HomeCurrency,"Home Currency radio Button");
        com.isElementPresent(radioButton_DiscountCurrency,"Discount Currency radio Button");
        com.isElementPresent(button_Refresh,"Refresh button");
        
        if (performSearch(TADIG, trafPeriod, grossOrNet, homeOrDiscountCurrency)) {
            com.isElementPresent(data_RateAuditReport_Section,"Rate Audit Report_Section heading");
            com.isElementPresent(hideShowIcon_RateAuditReport,"Rate Audit Report hide Show Icon");
            com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
			com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
			com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
			com.isElementPresent(comm.select_Reports, "Apex Toolbar - Reports dropdown");
			com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
			comm.verifyActionsPopupItems_WithSubscription();
            com.isElementPresent(filter_CalledCountryIsNotNull,"filter_Called Country Is Not Null");
            
            //comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
            comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);
            
		}
    }

	private boolean performSearch(TADIG TADIG, String[] trafPeriod, String grossOrNet, String homeOrDiscountCurrency) {
		
		String message = "";

		if (TADIG != null) {
			com.selectByPartialVisibleText(dropdown_Operator,TADIG.value);
			message = message + " | Operator= '" + TADIG.value + "'";
		}

		if (trafPeriod != null) {
			com.selectByPartialVisibleText_DoubleClick_FromArray(shuttle_TrafficPeriod_Left, trafPeriod);
			message = message + " | trafPeriod= '" + Arrays.toString(trafPeriod) + "'";
		}

		if (grossOrNet != null) {
			if (grossOrNet.toLowerCase().contains("gross")) {
				com.click(radioButton_Gross);
			} else {
				com.click(radioButton_Net);
			}
			message = message + " | grossOrNet= '" + grossOrNet + "'";
		}

		if (homeOrDiscountCurrency != null) {
			if (homeOrDiscountCurrency.toLowerCase().contains("home")) {
				com.click(radioButton_HomeCurrency);
			} else {
				com.click(radioButton_DiscountCurrency);
			}
			message = message + " | homeOrDiscountCurrency= '" + homeOrDiscountCurrency + "'";
		}


		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_Refresh);
		if (com.isElementPresent(data_RateAuditReport_Section, "Search Results")) {
			return true;
		}
		
		return false;
	}
    
    
    
}