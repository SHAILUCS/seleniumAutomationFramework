package objectRepository.AgreementCapture;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class CreateEditIOTDiscountAddressPage {
	
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	protected WebElement breadcrumb_IOTRONWelcome;
	
	@FindBy(xpath = "//li[contains(.,'Agreement Capture')]")
	protected WebElement breadcrumb_AgreementCapture;
	
	@FindBy(xpath = "//li[contains(.,'Maintain IOT Discount Party Details')]")
	protected WebElement breadcrumb_MaintainIOTDiscountPartyDetails;
	
	@FindBy(xpath = "//li[contains(.,'Create / Edit IOT Discount Party Details')]")
	protected WebElement breadcrumb_CreateEditIOTDiscountPartyDetails;

	@FindBy(xpath = "//h1[contains(.,'Create / Edit IOT Discount Address')]")
	protected WebElement breadcrumb_CreateEditIOTDiscountAddress;
	
	@FindBy(xpath = "//h2[contains(.,'Create / Edit IOT Discount Address')]")
	protected WebElement region_CreateEditIOTDiscountAddress;
	
	@FindBy(xpath = "//label[@id='P296_MASTER_ENTITY_ID_LABEL']")
	protected WebElement label_Organisation;
	
	@FindBy(xpath = "//span[contains(.,'AUSTA - Telstra Corporation Limited')]")
	protected WebElement data_ClientName;
	
	@FindBy(xpath = "//label[@id='P296_ADDRESS_TYPE_ID_LABEL']")
	protected WebElement label_AddressType;
	
	@FindBy(xpath = "//select[@id='P296_ADDRESS_TYPE_ID']")
	protected WebElement dropdown_AddressType;
	
	@FindBy(xpath = "//label[@id='P296_ADDRESS_LABEL']")
	protected WebElement label_Address;
	
	@FindBy(xpath = "//textarea[@id='P296_ADDRESS']")
	protected WebElement textbox_Address;
	
	@FindBy(xpath = "//label[@id='P296_CITY_LABEL']")
	protected WebElement label_City;
	
	@FindBy(xpath = "//input[@id='P296_CITY']")
	protected WebElement textbox_City;
	
	@FindBy(xpath = "//label[@id='P296_POST_CODE_LABEL']")
	protected WebElement label_PostCode;
	
	@FindBy(xpath = "//input[@id='P296_POST_CODE']")
	protected WebElement textbox_PostCode;
	
	@FindBy(xpath = "//label[@id='P296_COUNTRY_ID_LABEL']")
	protected WebElement label_Country;
	
	@FindBy(xpath = "//select[@id='P296_COUNTRY_ID']")
	protected WebElement dropdown_Country;
	
	@FindBy(xpath = "//label[@id='P296_ADDITIONAL_INFO_LABEL']")
	protected WebElement label_AdditionalInfo;
	
	@FindBy(xpath = "//input[@id='P296_ADDITIONAL_INFO']")
	protected WebElement textbox_AdditionalInfo;
		
	@FindBy(xpath = "//span[@id='P296_MANDATORY']")
	protected WebElement message_MandatoryFields;
	
	@FindBy(xpath = "//button[contains(., 'Save New Address')]")
	protected WebElement button_SaveNewAddress;
	
	@FindBy(xpath = "//button[contains(., 'Delete Address')]")
	protected WebElement button_DeleteAddress;
	
	private SeleniumMethods com;
	
	public static String  title="Create / Edit IOT Discount Address";
	public CreateEditIOTDiscountAddressPage() {
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
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountAddress, "Create Edit IOT Discount Address, BreadCrumb");
		com.isElementPresent(region_CreateEditIOTDiscountAddress, "Create Edit IOT Discount Address, Region");
		com.isElementPresent(label_Organisation, "Organisation, Label");
		com.isElementPresent(data_ClientName, "Client Name, Data");
		com.isElementPresent(label_AddressType, "Address Type, Label");
		com.isElementPresent(dropdown_AddressType, "Address Type, DropDown");
		com.isElementPresent(label_Address, "Address, Label");
		com.isElementPresent(textbox_Address, "Address, textbox");
		com.isElementPresent(label_City, "City, Label");
		com.isElementPresent(textbox_City, "City, textbox");
		com.isElementPresent(label_PostCode, "Post Code, Label");
		com.isElementPresent(textbox_PostCode, "Post Code, textbox");
		com.isElementPresent(label_Country, "Country, Label");
		com.isElementPresent(dropdown_Country, "Country, DropDown");
		com.isElementPresent(label_AdditionalInfo, "Additional Info, Label");
		com.isElementPresent(textbox_AdditionalInfo, "Additional Info, Textbox");	
		com.isElementPresent(message_MandatoryFields, "Mandatory Fields, Message");
		com.isElementPresent(button_SaveNewAddress, "Save New Address, Button");
		com.isElementPresent(button_DeleteAddress, "Delete Address, Button");
		
	}

}
