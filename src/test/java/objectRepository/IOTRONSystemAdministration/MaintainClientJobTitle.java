package objectRepository.IOTRONSystemAdministration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.AgreementCapture.EditIOTDiscountContactPage;
import objectRepository.common.ApexCommon;
import objectRepository.common.Header;
import objectRepository.common.PagesTitle;
public class MaintainClientJobTitle extends Header
{
    private SeleniumMethods com;
    private ApexCommon comm;
    public static String title = PagesTitle.MaintainClientJobTitle .title;
    public MaintainClientJobTitle () {
        PageFactory.initElements(DriverFactory.getDriver(), this);
        com = new SeleniumMethods();
        comm = new ApexCommon();
    }
    
    @FindBy(xpath="//li[contains(.,'Maintain Client Job Title')]")
	protected WebElement breadcrumb_MaintainClientJobTitle;
     
    @FindBy(xpath="//h2[contains(.,'Maintain Client Job Title')]//..//..//button")
    private WebElement hideshowbutton_MaintainClientJobTitle;
    
    @FindBy(xpath="//h2[contains(.,'Maintain Client Job Title')]")
    private WebElement region_MaintainClientJobTitle;
    
    @FindBy(id="P695_CLIENT_MASTER_ENTITY_ID_LABEL")
    private WebElement label_HomeOperator;
    
    @FindBy(id="P695_CLIENT_MASTER_ENTITY_ID")
	protected WebElement dropdown_HomeOperator;
    
    @FindBy(id="P695_CREATE")
    private WebElement button_Create;
    
	private List<String> tableColumns = new ArrayList<>(Arrays.asList("", "Job Title Name Ascending", "Created By",
			"Create Date", "Updated By", "Update Date", "Master Entity Name"));
    
    public void UIVerification(String HomeOperator){
    	CustomReporter.createNode("Verifying page content");
        com.isElementPresent(breadCrumb_IotronWelcome,"IOTRON Welcome, BreadCrumb");
        com.isElementPresent(breadCrumb_IOTRONSystemAdministration,"IOTRON System Administration, BreadCrumb");
        com.isElementPresent(breadcrumb_MaintainClientJobTitle,"Maintain Client Job Title, BreadCrumb");
        com.isElementPresent(hideshowbutton_MaintainClientJobTitle,"Maintain Client Job Title, HideShowButton");
        com.isElementPresent(region_MaintainClientJobTitle,"Maintain Client Job Title, Region");
        com.isElementPresent(label_HomeOperator,"Home Operator, Label");
        com.isElementPresent(dropdown_HomeOperator,"Home Operator, DropDown");
        com.selectByVisibleText(dropdown_HomeOperator, HomeOperator);
        com.isElementPresent(button_Create,"Create, Button");
        
        CustomReporter.createNode("Verifying Apex Search region content");
		com.isElementPresent(comm.icon_SelectColumnToSearch, "Search Column,DropDown");		
		com.isElementPresent(comm.text_Search, "Search textfield, SearchBox");		
		com.isElementPresent(comm.button_Go, "Go Button, Button");		
		com.isElementPresent(comm.label_Rows, "Select Rows, label");
		com.isElementPresent(comm.select_RowsPerPage, "Select Rows, DropDown");
		com.isElementPresent(comm.button_Action, "Action, Button");		
		com.isElementPresent(comm.link_FooterTopButton, "FooterTopButton, Link");
		
		CustomReporter.createNode("Verifying the Table columns");
		//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
		comm.verifyColumnHeaders(comm.table_ResultTabHeader,tableColumns, 1);
    }
    
    public EditIOTDiscountContactPage NavigateToEditClientJobTitle(String HomeOperator)
	{
		 com.selectByVisibleText(dropdown_HomeOperator, HomeOperator);
		 WebTable tab = new WebTable(comm.table_ResultTabData);
		 if(com.navigateToAndVerifyPageTitle(tab.getChildObject(2, 1, "img", 0),EditClientJobTitle.title))
		 {
			 return new EditIOTDiscountContactPage();
		 }
		return null;
	}
    
   
    
}
