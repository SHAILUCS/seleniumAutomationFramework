package objectRepository.IOTRONSystemAdministration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.PagesTitle;

public class CreateClientJobTitle extends MaintainClientJobTitle
{
		private SeleniumMethods com;
	    public static String title = PagesTitle.CreateEditClientJobTitle .title;
	    public CreateClientJobTitle () {
	        PageFactory.initElements(DriverFactory.getDriver(), this);
	        com = new SeleniumMethods();
	    }
	      
	    @FindBy(xpath="//h1[contains(.,'Create/Edit Client Job Title')]")
		protected WebElement breadcrumb_CreateEditClientJobTitle;
	    
	    @FindBy(xpath="//h2[contains(.,'Create/Edit Client Job Title')]//..//..//button")
		protected WebElement hideshowbutton_MaintainClientJobTitle;

	    @FindBy(xpath="//h2[contains(.,'Create/Edit Client Job Title')]")
		protected WebElement region_CreateEditClientJobTitle;
	    
		@FindBy(xpath = "//label[@id='P696_HOME_OPERATOR_LABEL']")
		protected WebElement label_HOMEOPERATOR;
		
		@FindBy(xpath = "//label[@id='P696_JOB_TITLE_LABEL']")
		protected WebElement label_JobTitleName;
		
		@FindBy(xpath = "//input[@id='P696_JOB_TITLE']")
		protected WebElement textbox_JobTitleName;
		
		@FindBy(xpath = "//label[@id='P696_CREATED_LABEL']")
		protected WebElement label_CreatedBy;
		
		@FindBy(xpath = "//label[@id='P696_CREATE_DATE_LABEL']")
		protected WebElement label_CreatedDate;
		
		@FindBy(xpath = "//label[@id='P696_UPDATED_LABEL']")
		protected WebElement label_UpdatedBy;
		
		@FindBy(xpath = "//label[@id='P696_UPDATE_DATE_LABEL']")
		protected WebElement label_UpdateDate;
		
		@FindBy(xpath = "//button[contains(.,'Create')]")
		protected WebElement button_Create;
		
		@FindBy(xpath = "//button[contains(.,'Cancel')]")
		protected WebElement button_Cancel;

	public void NavigateToCreateClientJobTitle(String HomeOperator)
	{
		 com.selectByVisibleText(dropdown_HomeOperator, HomeOperator);
		 com.click(button_Create);
	}
		    
	public void UIVerification()
	{	
		NavigateToCreateClientJobTitle("Telstra Corporation Limited");
		CustomReporter.report(STATUS.INFO, "Verifying page content");
        com.isElementPresent(breadCrumb_IotronWelcome,"IOTRON Welcome, BreadCrumb");
        com.isElementPresent(breadCrumb_IOTRONSystemAdministration,"IOTRON System Administration, BreadCrumb");
        com.isElementPresent(breadcrumb_MaintainClientJobTitle,"Maintain Client Job Title, BreadCrumb");
        com.isElementPresent(breadcrumb_CreateEditClientJobTitle,"Create Edit Client Job Title, BreadCrumb"); 
        com.isElementPresent(hideshowbutton_MaintainClientJobTitle,"Maintain Client Job Title, HideShowButton");
        com.isElementPresent(region_CreateEditClientJobTitle,"Create Edit Client Job Title, Region");
        com.isElementPresent(label_HOMEOPERATOR,"Home Operator, Label");
        com.isElementPresent(label_JobTitleName,"Job Title Name, Label");
        com.isElementPresent(textbox_JobTitleName,"Job Title Name, Textbox");
        com.isElementPresent(label_CreatedBy,"Created By, Label");
        com.isElementPresent(label_CreatedDate,"Created Date, Label");
        com.isElementPresent(label_UpdatedBy,"Updated By, Label");
        com.isElementPresent(label_UpdateDate,"Update Date, Label");
        com.isElementPresent(button_Create,"Create, Label");
        com.isElementPresent(button_Cancel,"Cancel, Label");
 
	}	

}
