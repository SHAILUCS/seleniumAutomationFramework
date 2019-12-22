package objectRepository.AgreementCapture;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class CreateEditIOTDiscountBankAccountPage {
	
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	protected WebElement breadcrumb_IOTRONWelcome;
	
	@FindBy(xpath = "//li[contains(.,'Agreement Capture')]")
	protected WebElement breadcrumb_AgreementCapture;
	
	@FindBy(xpath = "//li[contains(.,'Maintain IOT Discount Party Details')]")
	protected WebElement breadcrumb_MaintainIOTDiscountPartyDetails;
	
	@FindBy(xpath = "//li[contains(.,'Create / Edit IOT Discount Party Details')]")
	protected WebElement breadcrumb_CreateEditIOTDiscountPartyDetails;
	
	@FindBy(xpath = "//h1[contains(.,'Create / Edit IOT Discount Bank Account')]")
	protected WebElement breadcrumb_CreateEditIOTDiscountBankAccount;
	
	@FindBy(xpath = "//h2[contains(.,'Create / Edit IOT Discount Bank Account')]")
	protected WebElement region_CreateEditIOTDiscountBankAccount;
	
	@FindBy(xpath = "//label[@id='P255_MASTER_ENTITY_ID_LABEL']")
	protected WebElement label_Organisation;
	
	@FindBy(xpath = "//span[contains(.,'AUSTA - Telstra Corporation Limited')]")
	protected WebElement data_ClientName;
	
	@FindBy(xpath = "//label[@id='P255_BANK_ACCOUNT_CURRENCY_LABEL']")
	protected WebElement label_BankAccountCurrency;
	
	@FindBy(xpath = "//select[@id='P255_BANK_ACCOUNT_CURRENCY']")
	protected WebElement dropdown_BankAccountCurrency;
	
	@FindBy(xpath = "//label[@id='P255_BANK_NAME_LABEL']")
	protected WebElement label_BankName;
	
	@FindBy(xpath = "//input[@id='P255_BANK_NAME']")
	protected WebElement textbox_BankName;
	
	@FindBy(xpath = "//label[@id='P255_POSTAL_ADDRESS_LABEL']")
	protected WebElement label_BankAddress;
	
	@FindBy(xpath = "//input[@id='P255_POSTAL_ADDRESS']")
	protected WebElement textbox_BankAddress;
	
	@FindBy(xpath = "//label[@id='P255_POST_CODE_LABEL']")
	protected WebElement label_PostCode;
	
	@FindBy(xpath = "//input[@id='P255_POST_CODE']")
	protected WebElement textbox_PostCode;
	
	@FindBy(xpath = "//label[@id='P255_COUNTRY_ID_LABEL']")
	protected WebElement label_Country;
	
	@FindBy(xpath = "//select[@id='P255_COUNTRY_ID']")
	protected WebElement dropdown_Country;
	
	@FindBy(xpath = "//label[@id='P255_SWIFT_ADDRESS_LABEL']")
	protected WebElement label_BankCode;
	
	@FindBy(xpath = "//input[@id='P255_SWIFT_ADDRESS']")
	protected WebElement textbox_BankCode;
	
	@FindBy(xpath = "//label[@id='P255_BANK_ACCOUNT_NAME_LABEL']")
	protected WebElement label_BankAccountName;
	
	@FindBy(xpath = "//input[@id='P255_BANK_ACCOUNT_NAME']")
	protected WebElement textbox_BankAccountName;
	
	@FindBy(xpath = "//label[@id='P255_BANK_ACCOUNT_NUMBER_LABEL']")
	protected WebElement label_BankAccountNumber;
	
	@FindBy(xpath = "//input[@id='P255_BANK_ACCOUNT_NUMBER']")
	protected WebElement textbox_BankAccountNumber;
	
	@FindBy(xpath = "//label[@id='P255_IBAN_LABEL']")
	protected WebElement label_IBAN;
	
	@FindBy(xpath = "//input[@id='P255_BANK_ACCOUNT_NUMBER']")
	protected WebElement textbox_IBAN;
	
	@FindBy(xpath = "//label[@id='P255_SORT_CODE_LABEL']")
	protected WebElement label_SortCode;
	
	@FindBy(xpath = "//input[@id='P255_SORT_CODE']")
	protected WebElement textbox_SortCode;
	
	@FindBy(xpath = "//label[@id='P255_CORRESPONDENT_SWIFT_ADDR_LABEL']")
	protected WebElement label_IntermediateBankCode;
	
	@FindBy(xpath = "//input[@id='P255_CORRESPONDENT_SWIFT_ADDR']")
	protected WebElement textbox_IntermediateBankCode;
	
	@FindBy(xpath = "//label[@id='P255_CORRESPONDENT_BANK_NAME_LABEL']")
	protected WebElement label_IntermediateBankName;
	
	@FindBy(xpath = "//input[@id='P255_CORRESPONDENT_BANK_NAME']")
	protected WebElement textbox_IntermediateBankName;
	
	@FindBy(xpath = "//label[@id='P255_INTERMEDIATE_ACCT_NUMBER_LABEL']")
	protected WebElement label_IntermediateBankNumber;
	
	@FindBy(xpath = "//input[@id='P255_INTERMEDIATE_ACCT_NUMBER']")
	protected WebElement textbox_IntermediateBankNumber;
	
	@FindBy(xpath = "//label[@id='P255_INTERMEDIATE_IBAN_LABEL']")
	protected WebElement label_IntermediateIBAN;
	
	@FindBy(xpath = "//input[@id='P255_INTERMEDIATE_IBAN']")
	protected WebElement textbox_IntermediateIBAN;
	
	@FindBy(xpath = "//label[@id='P255_ADDITIONAL_PYMNT_INFO_LABEL']")
	protected WebElement label_AdditionalPaymntInfo;
	
	@FindBy(xpath = "//input[@id='P255_ADDITIONAL_PYMNT_INFO']")
	protected WebElement textbox_AdditionalPaymntInfo;
	
	@FindBy(xpath = "//label[@id='P255_CATCH_ALL_LABEL']")
	protected WebElement label_DefaultPaymentAccount;
	
	@FindBy(xpath = "//input[@id='P255_CATCH_ALL_0']")
	protected WebElement checkbox_DefaultPaymentAccount;
	
	@FindBy(xpath = "//label[@id='P255_AGGREGATE_PAYMENT_LABEL']")
	protected WebElement label_AgreegatePayment;
	
	@FindBy(xpath = "//input[@id='P255_AGGREGATE_PAYMENT_0']")
	protected WebElement checkbox_AgreegatePayment;
	
	@FindBy(xpath = "//button[contains(.,'Save New Account')]")
	protected WebElement button_SaveNewAccount;
	
	@FindBy(xpath = "//span[@id='P255_MANDATORY']")
	protected WebElement msg_MandatoryFields;
	
	
	private SeleniumMethods com;
	
	public static String  title="Create / Edit IOT Discount Bank Account";
	public CreateEditIOTDiscountBankAccountPage() {
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
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountBankAccount, "Create / Edit IOT Discount Bank Account, BreadCrumb");
		com.isElementPresent(region_CreateEditIOTDiscountBankAccount, "Create / Edit IOT Discount Bank Account, Region");
		com.isElementPresent(label_Organisation, "Organisation, Label");
		com.isElementPresent(data_ClientName, "Client Name, Data");	
		com.isElementPresent(label_BankAccountCurrency, "Bank Account Currency, Label");
		com.isElementPresent(dropdown_BankAccountCurrency, "Bank Account Currency, DropDown");	
		com.isElementPresent(label_BankName, "Bank Name, Label");
		com.isElementPresent(textbox_BankName, "Bank Name, Textbox");	
		com.isElementPresent(label_BankAddress, "Bank Address, Label");
		com.isElementPresent(textbox_BankAddress, "Bank Address, Textbox");		
		com.isElementPresent(label_PostCode, "Post Code, Label");
		com.isElementPresent(textbox_PostCode, "Post Code, Textbox");		
		com.isElementPresent(label_Country, "Country, Label");
		com.isElementPresent(dropdown_Country, "Country, DropDown");
		com.isElementPresent(label_BankCode, "Bank Code, Label");
		com.isElementPresent(textbox_BankCode, "Bank Code, Textbox");
		com.isElementPresent(label_BankAccountName, "Bank Account Name, Label");
		com.isElementPresent(textbox_BankAccountName, "Bank Account Name, Textbox");
		com.isElementPresent(label_BankAccountNumber, "Bank Account Number, Label");
		com.isElementPresent(textbox_BankAccountNumber, "Bank Account Number, Textbox");
		com.isElementPresent(label_IBAN, "IBAN, Label");
		com.isElementPresent(textbox_IBAN, "IBAN, Textbox");
		com.isElementPresent(label_SortCode, "Sort Code, Label");
		com.isElementPresent(textbox_SortCode, "Sort Code, Textbox");
		com.isElementPresent(label_IntermediateBankCode, "Intermediate Bank Code, Label");
		com.isElementPresent(textbox_IntermediateBankCode, "Intermediate Bank Code, Textbox");
		com.isElementPresent(label_IntermediateBankName, "Intermediate Bank Name, Label");
		com.isElementPresent(textbox_IntermediateBankName, "Intermediate Bank Name, Textbox");
		com.isElementPresent(label_IntermediateBankNumber, "Intermediate Bank Number, Label");
		com.isElementPresent(textbox_IntermediateBankNumber, "Intermediate Bank Number, Textbox");
		com.isElementPresent(label_IntermediateIBAN, "Intermediate IBAN, Label");
		com.isElementPresent(textbox_IntermediateIBAN, "Intermediate IBAN, Textbox");
		com.isElementPresent(label_AdditionalPaymntInfo, "Additional Paymnt Info, Label");
		com.isElementPresent(textbox_AdditionalPaymntInfo, "Additional Paymnt Info, Textbox");
		com.isElementPresent(label_DefaultPaymentAccount, "Default Payment Account, Label");
		com.isElementPresent(checkbox_DefaultPaymentAccount, "Default Payment Account, Checkbox");
		com.isElementPresent(label_AgreegatePayment, "Agreegate Payment, Label");
		com.isElementPresent(checkbox_AgreegatePayment, "Agreegate Payment, Checkbox");
		com.isElementPresent(button_SaveNewAccount, "Save New Account, Button");
		com.isElementPresent(msg_MandatoryFields, "Mandatory Fields, Message");
	
	}

}
