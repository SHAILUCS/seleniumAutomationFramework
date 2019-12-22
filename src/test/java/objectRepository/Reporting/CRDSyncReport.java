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
import com.seleniumExceptionHandling.WebTable;

import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;
public class CRDSyncReport
{
    private SeleniumMethods com;
    private ApexCommon comm;
    public static String title = PagesTitle.CRDSyncReport .title;
    public CRDSyncReport () {
        PageFactory.initElements(DriverFactory.getDriver(), this);
        com = new SeleniumMethods();
        comm = new ApexCommon();
    }
    
    @FindBy(xpath="//li[.='IOTRON - Welcome'][contains(@class,'Breadcrumb')]")
    private WebElement breadcrumb_IOTRONWelcome;
    
    @FindBy(xpath="//li[.='Reporting'][contains(@class,'Breadcrumb')]")
    private WebElement breadcrumb_Reporting;
    
    @FindBy(xpath="//h1[contains(.,'CRD Sync Report')]")
    private WebElement data_CRDSyncReport_BreadCrumb;
    
    @FindBy(xpath="//h2[contains(.,'Parameters')]")
    private WebElement data_Parameters_Section;
    
    @FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Parameters')]//button[contains(@class,'hideShow')]")
    private WebElement hideShowIcon_Parameters;
    
    @FindBy(id="P620_CONTINENT_ID")
    private WebElement dropdown_Region;
    
    @FindBy(id="P620_COUNTRY_ID")
    private WebElement dropdown_Country;
    
    @FindBy(id="P620_PARTNER_MASTER_ENTITY_ID")
    private WebElement dropdown_Partner;
    
    @FindBy(id="P620_DISCOUNT_TYPE_ID")
    private WebElement dropdown_DirectHub;
    
    @FindBy(id="P620_ALIAS_PARTNER_MASTER_ENTI")
    private WebElement dropdown_AliasPMN;
    
    @FindBy(id="P620_MCC")
    private WebElement text_Mcc;
    
    @FindBy(id="P620_MNC")
    private WebElement text_Mnc;
    
    @FindBy(id="P620_AGREEMENT_SIGNED")
    private WebElement text_AgreementSignedDate;
    
    @FindBy(xpath="//button[contains(.,'Create')]")
    private WebElement button_Create;
    
    @FindBy(xpath="//button[contains(.,'Delete')]")
    private WebElement button_Delete;
    
    @FindBy(xpath="//button[contains(.,'Apply Changes')]")
    private WebElement button_ApplyChanges;
    
	private List<String> colNames = new ArrayList<>(Arrays.asList("Edit", "Region", "Country", "Partner",
			"Direct / Hub", "PMN", "Alias PMN", "MCC", "MNC", "Agreement Signed Date"));
    
    public void verify_UI(){
        CustomReporter.report(STATUS.INFO, "Breadcrumb Section verification");
        com.isElementPresent(breadcrumb_IOTRONWelcome,"breadcrumb_IOTRON Welcome");
        com.isElementPresent(breadcrumb_Reporting,"breadcrumb_Reporting");
        com.isElementPresent(data_CRDSyncReport_BreadCrumb,"data_CRD Sync Report_BreadCrumb");
        
        CustomReporter.report(STATUS.INFO, "Parameters Section verification");
        com.isElementPresent(data_Parameters_Section,"data_Parameters_Section");
        com.isElementPresent(hideShowIcon_Parameters,"hide Show Icon_Parameters");
        com.isElementPresent(dropdown_Region,"dropdown_Region");
        com.isElementPresent(dropdown_Country,"dropdown_Country");
        com.isElementPresent(dropdown_Partner,"dropdown_Partner");
        com.isElementPresent(dropdown_DirectHub,"dropdown_Direct Hub");
        com.isElementPresent(dropdown_AliasPMN,"dropdown_Alias PMN");
        com.isElementPresent(text_Mcc,"text_Mcc");
        com.isElementPresent(text_Mnc,"text_Mnc");
        com.isElementPresent(text_AgreementSignedDate,"text_Agreement Signed Date");
        com.isElementPresent(button_Create,"button_Create");
        
        CustomReporter.report(STATUS.INFO, "Report Section verification");
        com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
  		com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
  		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
  		com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
  		comm.verifyActionsPopupItems_WithoutSubscription();
      	
  		//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
  		comm.verifyColumnHeaders(comm.table_ResultTabHeader,colNames, 1);
  		
  		if(com.isElementPresent(comm.table_ResultTabData)){
  			WebTable tab= new WebTable(comm.table_ResultTabData);
  			int rowCount=tab.getRowCount();
  			if(rowCount>2){
  				com.click(tab.getChildObject(2, 1, "a", 0));
		        com.isElementPresent(button_Delete,"button_Delete");
		        com.isElementPresent(button_ApplyChanges,"button_ApplyChanges");
  			}
  		}
    }
    
}