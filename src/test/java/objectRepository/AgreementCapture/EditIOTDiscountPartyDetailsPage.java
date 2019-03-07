package objectRepository.AgreementCapture;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;


public class EditIOTDiscountPartyDetailsPage extends CreateIOTDiscountPartyDetailsPage
{
	private SeleniumMethods com;
	public static String title = CreateIOTDiscountPartyDetailsPage.title;

	public EditIOTDiscountPartyDetailsPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	public void UIVerification() 
	{
		ApexCommon comm= new ApexCommon();
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
		com.isElementPresent(label_DiscountParty, "Discount Party");
		com.isElementPresent(button_CreateNewContact, "Create New Contact, button");
		com.isElementPresent(button_CreateNewAddress, "Create New Address, button");
		com.isElementPresent(button_CreateNewAccount, "Create New Account, button");
		
		CustomReporter.report(STATUS.INFO, "Verifying the Table columns");
		comm.printColumnHeaders(By.cssSelector("table[class='t-Report-report']"), 1);
		

				
	}
	
}
