package objectRepository.IOTRONSystemAdministration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.PagesTitle;

public class EditClientJobTitle extends CreateClientJobTitle
{
	private SeleniumMethods com;
    public static String title = PagesTitle.CreateEditClientJobTitle .title;
    public EditClientJobTitle () {
        PageFactory.initElements(DriverFactory.getDriver(), this);
        com = new SeleniumMethods();
    }
    
    @FindBy(xpath = "//button[contains(.,'Apply Changes')]")
	private WebElement button_ApplyChanges;
    

    
    public void UIVerification()
	{
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
        com.isElementPresent(button_ApplyChanges,"Apply Changes, Button");
        com.isElementPresent(button_Cancel,"Cancel, Label");
	}

}
