package objectRepository.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;

public class NGCHomePage {
	private SeleniumMethods com;

	@FindBy(xpath="//li/button/span[@class='t-Button-label']")
	public WebElement data_UserName;

	@FindBy(linkText="FCH")
	public WebElement link_FCH;

	@FindBy(linkText="IOTRON")
	public WebElement link_IOTRON;

	@FindBy(linkText="Feedback")
	public WebElement link_Feedback;
	
	@FindBy(linkText="Help")
	public WebElement link_Help;
	
	@FindBy(xpath = "//a[contains(.,'Logout')]")
	public WebElement link_Logout;
	
	@FindBy(partialLinkText="Welcome")
	public WebElement link_Welcome;
	
	@FindBy(xpath="(//button[contains(.,'Operators')])[1]")
	public WebElement link_Operators;

	@FindBy(xpath="//button[.='Roaming Agreements']")
	public WebElement link_Operators_RoamingAgreements;
	
	@FindBy(xpath="//button[.='Manage IOT Addenda']")
	public WebElement link_Operators_RoamingAgreements_ManageIOTAddenda;

	@FindBy(xpath="//a[.='Manage IOT Addenda']")
	public WebElement link_Operators_RoamingAgreements_ManageIOTAddenda_ManageIOTAddenda;
	
	@FindBy(xpath="//button[contains(.,'Receivables')]")
	public WebElement link_Receivables;
	
	@FindBy(xpath="//button[contains(.,'Payables')]")
	public WebElement link_Payables;
	
	@FindBy(xpath="//button[contains(.,'Credit & Debit Notes')]")
	public WebElement link_CreditDebitNotes;
	
	@FindBy(xpath="//button[contains(.,'Clearing & Settlement')]")
	public WebElement link_ClearingSettlement ;
	
	@FindBy(xpath="//button[contains(.,'Data Loading')]")
	public WebElement link_DataLoading;

	@FindBy(xpath="//button[contains(.,'SDR Rates')]")
	public WebElement link_DataLoading_SDRRates;
	
	@FindBy(xpath="//a[contains(.,'SDR Conversion Rates')]")
	public WebElement link_DataLoading_SDRConversionRates;
	
	@FindBy(xpath="//button[contains(.,'Reporting')]")
	public WebElement link_Reporting;
	
	@FindBy(linkText = "Issues")
	public WebElement link_Issues;

	@FindBy(xpath="//button[contains(.,'Administration')]")
	public WebElement link_Administration;
	
	@FindBy(xpath="//a[contains(.,'System Users')]")
	public WebElement link_Administration_SystemUsers;
	
	@FindBy(xpath = "//h2[.='Your Reminders']")
	public WebElement data_Section_YourReminders;
	
	@FindBy(css = "div[id$='_calendar']")
	public WebElement data_calendar;
	
	@FindBy(xpath = "//h2[.='IOTRON Common Operational Management - Quick Access']")
	public WebElement data_Section_IOTRONCommonOperationalManagementQuickAccess;

	@FindBy(linkText = "Agreement Management")
	public WebElement link_AgreementManagement;
	
	@FindBy(linkText = "Settlement Statement generation")
	public WebElement link_SettlementStatementGeneration;
	
	@FindBy(linkText = "View previously generated Settlement Statements")
	public WebElement link_ViewPreviouslyGeneratedSettlementStatements;
	
	@FindBy(linkText = "Agreements maturing in Previous/Current/Next Month")
	public WebElement link_AgreementsMaturingInPreviousCurrentNextMonth;
	
	@FindBy(xpath = "//h2[.='IOTRON Personal Favourites']")
	public WebElement data_Section_IOTRONPersonalFavourites;
	
	@FindBy(linkText = "Click to add a Report to Personal Favourites")
	public WebElement link_PersonalFavourites;
	
	@FindBy(xpath = "//h2[.='FCH Favourite Pages']")
	public WebElement data_Section_FCHFavouritePages;
	
	public static String title="Welcome to Nextgen Clearing";
	public NGCHomePage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com= new SeleniumMethods();
	}
	
	
	
	public boolean verifyLoggedInUser(String userName){
		boolean bool=false;
		WebElement user=com.getDynamicElement(By.xpath("//button[contains(.,'"+userName.toUpperCase()+"')]"+"|//span[contains(.,'"+userName.toUpperCase()+"')]"));
		if(user!=null){
			bool=true;
		}
		return bool;
	}

	public void verify_UI() {
		CustomReporter.report(STATUS.INFO, "Header Navigation Bar items");
		com.isElementPresent(data_UserName,"User Name");
		com.isElementPresent(link_FCH,"FCH link");
		com.isElementPresent(link_IOTRON,"IOTRON link");
		com.isElementPresent(link_Feedback,"Feedback link");
		com.isElementPresent(link_Help,"Help link");
		com.isElementPresent(link_Logout,"Logout link");

		CustomReporter.report(STATUS.INFO, "Menu Bar items");
		com.isElementPresent(link_Welcome,"Welcome link");
		com.isElementPresent(link_Operators,"Operators link");
		com.isElementPresent(link_Receivables,"Receivables link");
		com.isElementPresent(link_Payables,"Payables link");
		com.isElementPresent(link_CreditDebitNotes,"Credit & Debit Notes link");
		com.isElementPresent(link_ClearingSettlement,"Clearing & Settlement link");
		com.isElementPresent(link_DataLoading,"Data Loading link");
		com.isElementPresent(link_Reporting,"Reporting link");
		com.isElementPresent(link_Issues,"Issues link");
		com.isElementPresent(link_Administration,"Administration link");
		
		CustomReporter.report(STATUS.INFO, "Your Reminder section items");
		com.isElementPresent(data_Section_YourReminders,"Your Reminders Section");
		com.isElementPresent(data_calendar,"Calendar");
		
		CustomReporter.report(STATUS.INFO, "IOTRON Common Operational Management - Quick Access section items");
		com.isElementPresent(data_Section_IOTRONCommonOperationalManagementQuickAccess,"IOTRON Common Operational Management - Quick Access Section");
		com.isElementPresent(link_AgreementManagement,"Agreement Management link");
		com.isElementPresent(link_SettlementStatementGeneration,"Settlement Statement generation link");
		com.isElementPresent(link_ViewPreviouslyGeneratedSettlementStatements,"View previously generated Settlement Statements link");
		com.isElementPresent(link_AgreementsMaturingInPreviousCurrentNextMonth,"Agreements maturing in Previous/Current/Next Month link");
		
		CustomReporter.report(STATUS.INFO, "IOTRON Common Operational Management - Quick Access section items");
		com.isElementPresent(data_Section_IOTRONPersonalFavourites,"IOTRON Personal Favourites Section");
		com.isElementPresent(link_PersonalFavourites,"Personal Favourites link");
		
		CustomReporter.report(STATUS.INFO, "FCH Favourite Pages section items");
		com.isElementPresent(data_Section_FCHFavouritePages,"FCH Favourite Pages Section");
	}
	
}
