package objectRepository.AgreementCapture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.common.ApexCommon;
import objectRepository.common.TADIG;

public class CreateIOTDiscountPartyDetailsPage {
	
	@FindBy(xpath = "//h1[contains(.,'Create / Edit IOT Discount Party Details')]")
	protected WebElement breadcrumb_CreateEditIOTDiscountPartyDetails;
		
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	protected WebElement breadcrumb_IOTRONWelcome;
	
	@FindBy(xpath = "//li[contains(.,'Agreement Capture')]")
	protected WebElement breadcrumb_AgreementCapture;
	
	@FindBy(xpath = "//li[contains(.,'Maintain IOT Discount Party Details')]")
	protected WebElement breadcrumb_MaintainIOTDiscountPartyDetails;
		
	@FindBy(xpath = "//h2[contains(.,'IOT Discount Party')]")
	protected WebElement region_IOTDiscountParty;
	
	@FindBy(xpath = "//h2[contains(.,'Contacts')]")
	protected WebElement region_Contacts;
	
	@FindBy(xpath = "//h2[contains(.,'Addresses')]")
	protected WebElement region_Addresses;
	
	@FindBy(xpath = "//h2[contains(.,'Bank Accounts')]")
	protected WebElement region_BankAccounts;
	
	@FindBy(xpath = "//label[contains(.,'Discount Party')]")
	protected WebElement label_DiscountParty;
	
	@FindBy(xpath = "//select[@id='P299_MASTER_ENTITY_ID']")
	protected WebElement dropdown_MASTER_ENTITY_ID;
	
	protected By ContactTab = By.xpath("//table[@summary='Contacts']");
	
	@FindBy(xpath = "//button[.='Create New Contact']")
	protected WebElement button_CreateNewContact;
	
	@FindBy(xpath = "//button[.='Create New Address']")
	protected WebElement button_CreateNewAddress;
	
	@FindBy(xpath = "//button[.='Create New Account']")
	protected WebElement button_CreateNewAccount;
	
	protected List<String> tableColumns = new ArrayList<>(Arrays.asList("Edit","Contact Name","Job Title",
			"Operator Group","Email","Tel Fixed Office","Mobile"));
	
	
	private SeleniumMethods com;
	
	public static String  title="Create / Edit IOT Discount Party Details";
	public CreateIOTDiscountPartyDetailsPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	public void UIVerification() 
	{
		ApexCommon comm= new ApexCommon();
		com.selectByPartialVisibleText(dropdown_MASTER_ENTITY_ID, TADIG.AUSTA.value);
		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountPartyDetails, "Create/Edit IOT Discount Party Details, Breadcrumb");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, Breadcrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, Breadcrumb");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountPartyDetails, "Maintain IOT Discount Party Details, Breadcrumb");
		
		CustomReporter.report(STATUS.INFO, "Verifying Region content");
		com.isElementPresent(region_IOTDiscountParty, "IOT Discount Party, Region");
		com.isElementPresent(region_Contacts, "Contacts, Region");
		com.isElementPresent(region_Addresses, "Addresses, Region");
		com.isElementPresent(region_BankAccounts, "Bank Accounts, Region");
		
		CustomReporter.report(STATUS.INFO, "Verifying label, buttons & table content");
		com.isElementPresent(label_DiscountParty, "Discount Party, label");
		com.isElementPresent(ContactTab, "Contacts, table");	
		com.isElementPresent(button_CreateNewContact, "Create New Contact, button");
		com.isElementPresent(button_CreateNewAddress, "Create New Address, button");
		com.isElementPresent(button_CreateNewAccount, "Create New Account, button");
		
		
		CustomReporter.report(STATUS.INFO, "Verifying the Table columns");
		comm.printColumnHeaders(By.cssSelector("table[class='t-Report-report']"), 1);
		comm.verifyColumnHeaders(ContactTab, tableColumns, 1);
				
	}
	
	public CreateIOTDiscountContactPage SelectPartyandClickONCreateContact(TADIG TADIG) 
	{
		com.selectByPartialVisibleText(dropdown_MASTER_ENTITY_ID, TADIG.value);
		if(com.navigateToAndVerifyPageTitle(button_CreateNewContact, CreateIOTDiscountContactPage.title)){
			return new  CreateIOTDiscountContactPage();
		}
		return null;	
	}
	
	public EditIOTDiscountContactPage SelectPartyandClickONEditContact(TADIG TADIG) {
		com.selectByPartialVisibleText(dropdown_MASTER_ENTITY_ID, TADIG.value);
		WebTable tab = new WebTable(ContactTab);
		if(com.navigateToAndVerifyPageTitle(tab.getChildObject(2, 1, "img", 0), EditIOTDiscountContactPage.title)){
			return new EditIOTDiscountContactPage();
		}
		return null;		
	}

		
	public CreateEditIOTDiscountAddressPage SelectPartyandClickONCreateAddress(TADIG TADIG) 
	{
		com.selectByPartialVisibleText(dropdown_MASTER_ENTITY_ID, TADIG.value);
		if(com.navigateToAndVerifyPageTitle(button_CreateNewAddress, CreateEditIOTDiscountAddressPage.title)){
			return new  CreateEditIOTDiscountAddressPage();
		}
		return null;	
	}
	
	public CreateEditIOTDiscountBankAccountPage SelectPartyandClickONCreateBankAccount(TADIG TADIG) 
	{
		com.selectByPartialVisibleText(dropdown_MASTER_ENTITY_ID, TADIG.value);
		if(com.navigateToAndVerifyPageTitle(button_CreateNewAccount, CreateEditIOTDiscountBankAccountPage.title)){
			return new  CreateEditIOTDiscountBankAccountPage();
		}
		return null;	
	}

	

}
