package objectRepository.AgreementCapture;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class CreateIOTDiscountContactPage {
	
	
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	protected WebElement breadcrumb_IOTRONWelcome;
	
	@FindBy(xpath = "//li[contains(.,'Agreement Capture')]")
	protected WebElement breadcrumb_AgreementCapture;
	
	@FindBy(xpath = "//li[contains(.,'Maintain IOT Discount Party Details')]")
	protected WebElement breadcrumb_MaintainIOTDiscountPartyDetails;
	
	@FindBy(xpath = "//li[contains(.,'Create / Edit IOT Discount Party Details')]")
	protected WebElement breadcrumb_CreateEditIOTDiscountPartyDetails;
	
	@FindBy(xpath = "//h1[contains(.,'Create / Edit IOT Discount Contact')]")
	protected WebElement breadcrumb_CreateEditIOTDiscountContact;
	
	@FindBy(xpath = "//h2[contains(.,'IOT Discount Contact')]")
	protected WebElement region_IOTDiscountContact;
	
	@FindBy(xpath = "//button[contains(., 'Save New Contact')]")
	protected WebElement button_SaveNewContact;
	
	@FindBy(xpath = "//label[@id='P290_MASTER_ENTITY_ID_LABEL']")
	protected WebElement label_Organisation;
	
	@FindBy(xpath = "//span[contains(.,'AUSTA - Telstra Corporation Limited')]")
	protected WebElement data_ClientName;
	
	@FindBy(xpath = "//label[@id='P290_TITLE_LABEL']")
	protected WebElement label_Title;
	
	@FindBy(xpath = "//select[@id='P290_TITLE']")
	protected WebElement dropdown_Title;
	
	@FindBy(xpath = "//label[@id='P290_FIRST_NAME_LABEL']")
	protected WebElement label_FirstName;
	
	@FindBy(xpath = "//input[@id='P290_FIRST_NAME']")
	protected WebElement textbox_FirstName;
	
	@FindBy(xpath = "P290_LAST_NAME_LABEL")
	protected WebElement lable_LastName;

	@FindBy(xpath = "//input[@id='P290_LAST_NAME']")
	protected WebElement textbox_LastName;
	
	@FindBy(xpath = "//label[@id='P290_ROLE_TITLE_LABEL']")
	protected WebElement label_JobTitle;
	
	@FindBy(xpath = "//input[@id='P290_ROLE_TITLE']")
	protected WebElement textbox_JobTitle;
	
	@FindBy(xpath = "//label[@id='P290_EMAIL_LABEL']")
	protected WebElement label_Email;
	
	@FindBy(xpath = "//input[@id='P290_EMAIL']")
	protected WebElement textbox_Email;
	
	@FindBy(xpath = "//label[@id='P290_TEL_FIXED_OFFICE_LABEL']")
	protected WebElement label_Work;
	
	@FindBy(xpath = "//input[@id='P290_TEL_FIXED_OFFICE']")
	protected WebElement textbox_Work;
	
	@FindBy(xpath = "//label[@id='P290_MOBILE_LABEL']")
	protected WebElement label_Mobile;
	
	@FindBy(xpath = "//input[@id='P290_MOBILE']")
	protected WebElement textbox_Mobile;
	
	@FindBy(xpath = "//label[@id='P290_FAX_Label']")
	protected WebElement label_Fax;
	
	@FindBy(xpath = "//input[@id='P290_FAX']")
	protected WebElement textbox_Fax;
	
	@FindBy(xpath = "//label[@id='P290_RECEIVE_ALERTS_LABEL']")
	protected WebElement label_ReviewAlerts;
	
	@FindBy(xpath = "//label[@for='P290_RECEIVE_ALERTS_0']")
	protected WebElement checkbox_ReviewAlerts;
	
	@FindBy(xpath = "//button[@title='Help Text: Receive Alerts']")
	protected WebElement button_Help;
	
	@FindBy(xpath = "//label[@id='P290_IOT_CONTACT_ROLE_LABEL']")
	protected WebElement label_IOT_Contact_Role;
	
	@FindBy(xpath = "//select[@id='P290_IOT_CONTACT_ROLE_LEFT']")
	protected WebElement shuttle_IOT_Contact_Left;
	
	@FindBy(xpath = "//select[@id='P290_IOT_CONTACT_ROLE_RIGHT']")
	protected WebElement shuttle_IOT_Contact_Right;	
	
	@FindBy(xpath = "//label[@id='P290_IOT_OPERATOR_GROUP_ID_LABEL']")
	protected WebElement label_IOT_Operator_group;
	
	@FindBy(xpath = "//select[@id='P290_IOT_OPERATOR_GROUP_ID_LEFT']")
	protected WebElement shuttle_IOT_Operator_group_Left;
	
	@FindBy(xpath = "//select[@id='P290_IOT_OPERATOR_GROUP_ID_RIGHT']")
	protected WebElement shuttle_IOT_Operator_group_Right;
	
	
	private SeleniumMethods com;
	
	public static String  title="Create / Edit IOT Discount Contact";
	public CreateIOTDiscountContactPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	public void UIVerification()
	{
		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountPartyDetails, "Maintain IOT Discount Party Details, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountPartyDetails, "Create Edit IOT Discount Party Details, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountContact, "Create Edit IOT Discount Contact, BreadCrumb");
		com.isElementPresent(region_IOTDiscountContact, "IOT Discount Contact, Region");
		com.isElementPresent(button_SaveNewContact, "Save New Contact, Button");
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
