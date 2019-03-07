package objectRepository.AgreementCapture;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;

public class EditIOTDiscountContactPage extends CreateIOTDiscountContactPage
{
	
	@FindBy(xpath = "//button[contains(., 'Save Changes')]")
	protected WebElement button_SaveChanges;
	
	@FindBy(xpath = "//button[contains(., 'Delete Contact')]")
	protected WebElement button_DeleteContact;
	
	
	private SeleniumMethods com;
	
	public static String  title="Create / Edit IOT Discount Contact";
	public EditIOTDiscountContactPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	public void UIVerification() {
		
		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountPartyDetails, "Maintain IOT Discount Party Details, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountPartyDetails, "Create Edit IOT Discount Party Details, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountContact, "Create Edit IOT Discount Contact, BreadCrumb");
		com.isElementPresent(region_IOTDiscountContact, "IOT Discount Contact, Region");	
		com.isElementPresent(button_SaveChanges, "button_SaveChanges");
		com.isElementPresent(button_DeleteContact, "button_DeleteContact");
		com.isElementPresent(label_Organisation, "Organisation, Label");
		com.isElementPresent(data_ClientName, "Name, Data");
		com.isElementPresent(dropdown_Title, "Title, Dropdown");
		com.isElementPresent(textbox_FirstName, "First Name, Textbox");
		com.isElementPresent(textbox_LastName, "Last Name, Textbox");
		com.isElementPresent(textbox_JobTitle, "Job Title, Textbox");
		com.isElementPresent(textbox_Email, "Email, Textbox");	
		com.isElementPresent(textbox_Work, "Work, Textbox");
		com.isElementPresent(textbox_Mobile, "Mobile, Textbox");
		com.isElementPresent(textbox_Fax, "Fax, Textbox");		
		com.isElementPresent(checkbox_ReviewAlerts, "Review Alerts, Checkbox");
		com.isElementPresent(button_Help, "Help, Button");	
		com.isElementPresent(label_IOT_Contact_Role, "IOT_Contact_Role, Label");
		com.isElementPresent(shuttle_IOT_Contact_Left, "IOT_Contact_Left, Shuttle");
		com.isElementPresent(shuttle_IOT_Contact_Right, "IOT_Contact_Right, Shuttle");		
		com.isElementPresent(label_IOT_Operator_group, "Operator_group, Label");
		com.isElementPresent(shuttle_IOT_Operator_group_Left, "IOT_Operator_group_Left, Shuttle");
		com.isElementPresent(shuttle_IOT_Operator_group_Right, "IOT_Operator_group_Right, Shuttle");	

		
	}
	

}
