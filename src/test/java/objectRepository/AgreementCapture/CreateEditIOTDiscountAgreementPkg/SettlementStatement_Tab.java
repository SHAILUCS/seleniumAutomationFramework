package objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;

public class SettlementStatement_Tab extends EditIOTDiscountAgreement_Tab {
	
	ApexCommon comm = new ApexCommon();
	private SeleniumMethods com;
	
	public static String title = "Create / Edit IOT Discount Agreement";

	public SettlementStatement_Tab() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	public void UIVerification() 
	{
		VerifyPageContent();
		VerifySettlementStatementRegion();
		VerifySettlementTermsandParametersRegion();
		VerifyHomeOperatorSettlementTermDaysRegion();
		VerifyPartnerSettlementTermDaysRegion();
		VerifyHomeOperatorInterestPenaltyRateRegion();
		VerifyPartnerInterestPenaltyRateRegion();
		VerifyHomeOperatorSettlementCurrencyRegion();
		VerifyPartnerSettlementCurrencyRegion();
		VerifySettlementStatusChangeHistoryRegion();
		
	}
	
	@FindBy(xpath = "//a[contains(.,'Settlement Statement')]")
	private WebElement tab_SettlementStatement;
	
	private void VerifyPageContent()
	{
		CustomReporter.createNode("Verifying page content");
		com.click(tab_SettlementStatement, "Settlement Statement,Tab");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountAgreements, "Maintain IOT Discount Agreements, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountAgreement,"Create Edit IOT Discount Agreement, BreadCrumb");
		com.isElementPresent(tab_SettlementStatement, "Settlement Statement, Tab");
	}

	@FindBy(xpath = "//h2[contains(.,'Settlement Statement')]")
	private WebElement region_SettlementStatement;

	@FindBy(xpath = "//button[contains(.,'Save Changes')]")
	private WebElement button_SaveChanges;

	@FindBy(xpath = "//button[contains(.,'Generate Settlement Statement')]")
	private WebElement button_GenerateSettlementStatement;

	@FindBy(xpath = "//button[contains(.,'Reset to Default')]")
	private WebElement button_ResettoDefault;
	
	private void VerifySettlementStatementRegion()
	{
		CustomReporter.createNode("Verifying Settlement Statement Region");
		com.isElementPresent(region_SettlementStatement, "Settlement Statement, Region");
		com.isElementPresent(button_SaveChanges, "Save Changes, Button");
		com.isElementPresent(button_GenerateSettlementStatement, "Generate Settlement Statement, Button");
		com.isElementPresent(button_ResettoDefault, "Reset to Default, Button");
	}
	
	@FindBy(xpath = "//h2[contains(.,'Settlement Terms and Parameters')]")
	private WebElement region_SettlementTermsandParameters;
	
	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Settlement Terms and Parameters')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_SettlementTermsandParameters;

	@FindBy(xpath = "//label[@id='P303_IOT_SETTLEMENT_STATUS_ID_LABEL']")
	private WebElement label_SettlementStatus;

	@FindBy(xpath = "//select[@id='P303_IOT_SETTLEMENT_STATUS_ID']")
	private WebElement dropdown_SettlementStatus;

	@FindBy(xpath = "//button[@title='Help Text: Settlement Status']")
	private WebElement helpbutton_SettlementStatus;

	@FindBy(xpath = "//label[@id='P303_TAX_ON_STTL_STMT_LABEL']")
	private WebElement label_TAX_ON_STTL_STMT;

	@FindBy(xpath = "//select[@id='P303_TAX_ON_STTL_STMT']")
	private WebElement dropdown_TAX_ON_STTL_STMT;

	@FindBy(xpath = "//button[@title='Help Text: Tax on Settlement Statement Summary']")
	private WebElement helpbutton_TAX_ON_STTL_STMT;

	@FindBy(xpath = "//label[@id='P303_SETTLEMENT_STATEMENT_RULE_LABEL']")
	private WebElement label_GenerateSettlementStatementRule;

	@FindBy(xpath = "//select[@id='P303_SETTLEMENT_STATEMENT_RULE']")
	private WebElement dropdown_GenerateSettlementStatementRule;

	@FindBy(xpath = "//label[@id='P303_IOT_REBATE_FREQUENCY_ID_LABEL']")
	private WebElement label_SettlementFrequency;

	@FindBy(xpath = "//select[@id='P303_IOT_REBATE_FREQUENCY_ID']")
	private WebElement dropdown_SettlementFrequency;

	@FindBy(xpath = "//button[@title='Help Text: Settlement Frequency']")
	private WebElement helpbutton_SettlementFrequency;
	
	private void VerifySettlementTermsandParametersRegion()
	{
		CustomReporter.createNode("Verifying Settlement Terms and Parameters Region");
		com.isElementPresent(region_SettlementTermsandParameters, "Settlement Terms and Parameters, Region");
		com.isElementPresent(hideshowbutton_SettlementTermsandParameters, "Settlement Terms and Parameters, hideshowbutton");
		com.isElementPresent(label_SettlementStatus, "Settlement Status, Label");
		com.isElementPresent(dropdown_SettlementStatus, "Settlement Status, DropDown");
		com.isElementPresent(helpbutton_SettlementStatus, "Settlement Status, HelpButton");
		com.isElementPresent(label_TAX_ON_STTL_STMT, "TAX_ON_STTL_STMT, Label");
		com.isElementPresent(dropdown_TAX_ON_STTL_STMT, "TAX_ON_STTL_STMT, DropDown");
		com.isElementPresent(helpbutton_TAX_ON_STTL_STMT, "TAX_ON_STTL_STMT, HelpButton");
		com.isElementPresent(label_GenerateSettlementStatementRule, "Generate Settlement Statement Rule, Label");
		com.isElementPresent(dropdown_GenerateSettlementStatementRule, "Generate Settlement Statement Rule, DropDown");
		com.isElementPresent(label_SettlementFrequency, "Settlement Frequency, Label");
		com.isElementPresent(dropdown_SettlementFrequency, "Settlement Frequency, DropDown");
		com.isElementPresent(helpbutton_SettlementFrequency, "Settlement Frequency, HelpButton");
	}

	@FindBy(xpath = "//h2[contains(.,'Home Operator Settlement Term Days')]")
	private WebElement region_HomeOperatorSettlementTermDays;
	
	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Home Operator Settlement Term Days')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_HomeOperatorSettlementTermDays;

	private By tabHomeOperatorSettlementTermDays = By.cssSelector("table[summary='Home Operator Settlement Term Days Report']");
	
	private List<String> ColumnsHomeOperatorSettlementTermDays = new ArrayList<>(Arrays.asList("Client"));
	
	@FindBy(xpath = "//label[@id='P303_STTL_TERM_DAYS_HOME_LABEL']")
	private WebElement label_TermDaysHomeOperatorSettlementTermDaysRegion;   
	
	@FindBy(xpath = "//input[@id='P303_STTL_TERM_DAYS_HOME']")
	private WebElement textbox_TermDaysHomeOperatorSettlementTermDaysRegion; 
	
	@FindBy(xpath = "//h2[.='Home Operator Settlement Term Days']//..//..//following-sibling::div//button[.='Apply to All']")
	private WebElement button_ApplyToAllHomeOperatorSettlementTermDaysRegion;  
	
	@FindBy(xpath = "//h2[.='Home Operator Settlement Term Days']//..//..//following-sibling::div//button[.='Edit']")
	private WebElement button_EditHomeOperatorSettlementTermDays;
	
	private void VerifyHomeOperatorSettlementTermDaysRegion()
	{
		CustomReporter.createNode("Verifying Home Operator Settlement Term Days Region");
		com.isElementPresent(region_HomeOperatorSettlementTermDays, "Home Operator Settlement Term Days, Region");
		com.click(hideshowbutton_HomeOperatorSettlementTermDays, "Home Operator Settlement Term Days, hide showbutton");
		com.isElementPresent(button_EditHomeOperatorSettlementTermDays, "Home Operator Settlement Term Days, Edit Button");	
		com.isElementPresent(label_TermDaysHomeOperatorSettlementTermDaysRegion, "Term Days Home Operator Settlement Term Days Region, Label");
		com.isElementPresent(textbox_TermDaysHomeOperatorSettlementTermDaysRegion, "Term Days Home Operator Settlement Term Days Region, DropDown");
		com.isElementPresent(button_ApplyToAllHomeOperatorSettlementTermDaysRegion, "Apply To All Home Operator Settlement Term Days Region, Button");
		
		CustomReporter.createNode("Verifying Home Operator Settlement Term Days Table columns");
		comm.verifyColumnHeaders(tabHomeOperatorSettlementTermDays,ColumnsHomeOperatorSettlementTermDays, 1);
	}
	
	@FindBy(xpath = "//h2[contains(.,'Partner Settlement Term Days')]")
	private WebElement region_PartnerSettlementTermDays;
	
	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Partner Settlement Term Days')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_PartnerSettlementTermDays;

	private By tabPartnerSettlementTermDays = By.cssSelector("table[summary='Partner Settlement Term Days Report']");
	
	private List<String> ColumnsPartnerSettlementTermDays = new ArrayList<>(Arrays.asList("Partner"));
	
	@FindBy(xpath = "//label[@id='P303_STTL_TERM_DAYS_PART_LABEL']")
	private WebElement label_TermDaysPartnerSettlementTermDaysRegion;  
	
	@FindBy(xpath = "//input[@id='P303_STTL_TERM_DAYS_PART']")
	private WebElement textbox_TermDaysPartnerSettlementTermDaysRegion; 
	
	@FindBy(xpath = "//h2[.='Home Operator Settlement Term Days']//..//..//following-sibling::div//button[.='Apply to All']")
	private WebElement button_ApplyToAllPartnerSettlementTermDaysRegion;  
	
	@FindBy(xpath = "//h2[.='Partner Settlement Term Days']//..//..//following-sibling::div//button[.='Edit']")
	private WebElement button_EditPartnerSettlementTermDays;
	
	private void VerifyPartnerSettlementTermDaysRegion()
	{
		CustomReporter.createNode("Verifying Partner Settlement Term Days Region");
		com.isElementPresent(region_PartnerSettlementTermDays, "Partner Settlement Term Days, Region");
		com.click(hideshowbutton_PartnerSettlementTermDays, "Partner Settlement Term Days, hide show button");
		com.isElementPresent(button_EditPartnerSettlementTermDays, "Partner Settlement Term Days, Edit Button");
		com.isElementPresent(label_TermDaysPartnerSettlementTermDaysRegion, "Term Days Partner Settlement Term Days Region, Label");
		com.isElementPresent(textbox_TermDaysPartnerSettlementTermDaysRegion, "Term Days Partner Settlement Term Days Region, DropDown");
		com.isElementPresent(button_ApplyToAllPartnerSettlementTermDaysRegion, "Apply To All Partner Settlement Term Days Region, Button");
		
		CustomReporter.createNode("Verifying Partner Settlement Term Days Table columns");
		comm.verifyColumnHeaders(tabPartnerSettlementTermDays,ColumnsPartnerSettlementTermDays, 1);
	}
	
	@FindBy(xpath = "//h2[contains(.,'Home Operator Interest Penalty Rate')]")
	private WebElement region_HomeOperatorInterestPenaltyRate;
	
	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Home Operator Interest Penalty Rate')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_HomeOperatorInterestPenaltyRate;
	
	private By tabHomeOperatorInterestPenaltyRateReport = By.cssSelector("table[summary='Home Operator Interest Penalty Rate Report']");
	
	private List<String> ColumnsHomeOperatorInterestPenaltyRate = new ArrayList<>(Arrays.asList("Client"));
	
	@FindBy(xpath = "//label[@id='P303_INTEREST_PENAL_RATE_HOME_LABEL']")
	private WebElement label_PenaltyRatesHomeOperatorInterestPenaltyRateRegion;  
	
	@FindBy(xpath = "//input[@id='P303_INTEREST_PENAL_RATE_HOME']")
	private WebElement textbox_PenaltyRatesHomeOperatorInterestPenaltyRateRegion; 
	
	@FindBy(xpath = "//h2[.='Home Operator Interest Penalty Rate']//..//..//following-sibling::div//button[.='Apply to All']")
	private WebElement button_ApplyToAllHomeOperatorInterestPenaltyRateRegion;  
	
	@FindBy(xpath = "//h2[.='Home Operator Interest Penalty Rate Report']//..//..//following-sibling::div//button[.='Edit']")
	private WebElement button_EditHomeOperatorInterestPenaltyRateReport;
	
	private void VerifyHomeOperatorInterestPenaltyRateRegion()
	{	
		CustomReporter.createNode("Verifying Home Operator Interest Penalty Rate Region");
		com.isElementPresent(region_HomeOperatorInterestPenaltyRate, "Home Operator Interest Penalty Rate, Region");
		com.click(hideshowbutton_HomeOperatorInterestPenaltyRate, "Home Operator Interest Penalty Rate, hide show button");
		com.isElementPresent(button_EditHomeOperatorInterestPenaltyRateReport, "Home Operator Interest Penalty Rate, Edit Button");
		com.isElementPresent(label_PenaltyRatesHomeOperatorInterestPenaltyRateRegion, "Penalty Rates Home Operator Interest Penalty Rate Region, Label");
		com.isElementPresent(textbox_PenaltyRatesHomeOperatorInterestPenaltyRateRegion, "Penalty Rates Home Operator Interest Penalty Rate Region, DropDown");
		com.isElementPresent(button_ApplyToAllHomeOperatorInterestPenaltyRateRegion, "Apply To All Home Operator Interest Penalty Rate Region, Button");

		CustomReporter.createNode("Verifying Home Operator Interest Penalty Rate Table columns");
		comm.verifyColumnHeaders(tabHomeOperatorInterestPenaltyRateReport,ColumnsHomeOperatorInterestPenaltyRate, 1);
	}
	
	@FindBy(xpath = "//h2[contains(.,'Partner Interest Penalty Rate')]")
	private WebElement region_PartnerInterestPenaltyRate;
	
	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Partner Interest Penalty Rate')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_PartnerInterestPenaltyRate;
	
	@FindBy(xpath = "//label[@id='P303_INTEREST_PENAL_RATE_PART_LABEL']")
	private WebElement label_PenaltyRatePartnerInterestPenaltyRateRegion; //new
	
	@FindBy(xpath = "//input[@id='P303_INTEREST_PENAL_RATE_PART']")
	private WebElement textbox_PenaltyRatePartnerInterestPenaltyRateRegion; //new
	
	@FindBy(xpath = "//h2[.='Partner Interest Penalty Rate']//..//..//following-sibling::div//button[.='Apply to All']")
	private WebElement button_ApplyToAllPartnerInterestPenaltyRateRegion; //new
	
	private By tabPartnerInterestPenaltyRateReport = By.cssSelector("table[summary='Partner Interest Penalty Rate Report']");
	
	private List<String> ColumnsPartnerInterestPenaltyRate = new ArrayList<>(Arrays.asList("Partner"));
	
	@FindBy(xpath = "//h2[.='Partner Interest Penalty Rate Report']//..//..//following-sibling::div//button[.='Edit']")
	private WebElement button_EditPartnerInterestPenaltRateReport;
	
	private void VerifyPartnerInterestPenaltyRateRegion()
	{
		CustomReporter.createNode("Verifying Partner Interest Penalty Rate Region");
		com.isElementPresent(region_PartnerInterestPenaltyRate, "Partner Interest Penalty Rate, Region");
		com.click(hideshowbutton_PartnerInterestPenaltyRate, "Partner Interest Penalty Rate, hide show button");
		com.isElementPresent(label_PenaltyRatePartnerInterestPenaltyRateRegion, "Penalty Rate Partner Interest Penalty Rate Region, Label");
		com.isElementPresent(textbox_PenaltyRatePartnerInterestPenaltyRateRegion, "Penalty Rate Partner Interest Penalty Rate Region, Textbox");
		com.isElementPresent(button_ApplyToAllPartnerInterestPenaltyRateRegion, "Apply To All Partner Interest Penalty Rate Region, Button");
		com.isElementPresent(button_EditPartnerInterestPenaltRateReport, "Partner Interest Penalty Rate, Edit Button");

		CustomReporter.createNode("Verifying Partner Interest Penalty Rate Table columns");
		comm.verifyColumnHeaders(tabPartnerInterestPenaltyRateReport,ColumnsPartnerInterestPenaltyRate, 1);
	}

	@FindBy(xpath = "//h2[contains(.,'Home Operator C.3.2 / Settlement Currency')]")
	private WebElement region_HomeOperatorSettlementCurrency;
	
	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Home Operator C.3.2 / Settlement Currency')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_HomeOperatorSettlementCurrency;
	
	private By tabHomeOperatorSettlementCurrencyReport = By.cssSelector("table[summary='Home Operator C.3.2 / Settlement Currency Report']");
	
	private List<String> ColumnsHomeOperatorSettlementCurrency = new ArrayList<>(Arrays.asList("Client"));
	
	@FindBy(xpath = "//label[@id='P303_HOME_CURRENCY_TO_ALL_LABEL']")
	private WebElement label_CurrencyHomeOperatorSettlementCurrencyRegion; //new
	
	@FindBy(xpath = "//select[@id='P303_HOME_CURRENCY_TO_ALL']")
	private WebElement dropdown_CurrencyHomeOperatorSettlementCurrencyRegion; //new
	
	@FindBy(xpath = "//h2[.='Partner Interest Penalty Rate']//..//..//following-sibling::div//button[.='Apply to All']")
	private WebElement button_ApplyToAllHomeOperatorSettlementCurrencyRegion; //new
	
	@FindBy(xpath = "//h2[.='Home Operator C.3.2 / Settlement Currency Report']//..//..//following-sibling::div//button[.='Edit']")
	private WebElement button_EditHomeOperatorSettlementCurrencyReport;
	
	private void VerifyHomeOperatorSettlementCurrencyRegion()
	{
		CustomReporter.createNode("Verifying Home Operator Settlement Currency Region");
		com.isElementPresent(region_HomeOperatorSettlementCurrency, "Home Operator Settlement Currency, Region");
		com.isElementPresent(hideshowbutton_HomeOperatorSettlementCurrency, "Home Operator Settlement Currency, hide show button");
		com.isElementPresent(button_EditHomeOperatorSettlementCurrencyReport, "Home Operator Settlement Currency, Edit Button");
		com.isElementPresent(label_CurrencyHomeOperatorSettlementCurrencyRegion, "Currency Home Operator Settlement Currency Region, Label");
		com.isElementPresent(dropdown_CurrencyHomeOperatorSettlementCurrencyRegion, "Currency Home Operator Settlement Currency Region, Textbox");
		com.isElementPresent(button_ApplyToAllHomeOperatorSettlementCurrencyRegion, "Apply To All Partner Interest Penalty Rate Region, Button");

		CustomReporter.createNode("Verifying Home Operator Settlement Currency Table columns");
		comm.printColumnHeaders(tabHomeOperatorSettlementCurrencyReport, 1);
		comm.verifyColumnHeaders(tabHomeOperatorSettlementCurrencyReport,ColumnsHomeOperatorSettlementCurrency, 1);
	}
	
	@FindBy(xpath = "//h2[contains(.,'Partner C.3.2 / Settlement Currency')]")
	private WebElement region_PartnerSettlementCurrency;
	
	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Partner C.3.2 / Settlement Currency')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_PartnerSettlementCurrency;
	
	private By tabPartnerSettlementCurrency = By.cssSelector("table[summary='Partner C.3.2 / Settlement Currency Report']");
	
	private List<String> ColumnsPartnerSettlementCurrency = new ArrayList<>(Arrays.asList("Partner"));
	
	@FindBy(xpath = "//label[@id='P303_PARTNER_CURRENCY_TO_ALL_LABEL']")
	private WebElement label_CurrencyPartnerSettlementCurrencyRegion;
	
	@FindBy(xpath = "//select[@id='P303_PARTNER_CURRENCY_TO_ALL']")
	private WebElement dropdown_CurrencyPartnerSettlementCurrencyRegion;
	
	@FindBy(xpath = "//h2[.='Partner Interest Penalty Rate']//..//..//following-sibling::div//button[.='Apply to All']")
	private WebElement button_ApplyToAllPartnerSettlementCurrencyRegion;
	
	@FindBy(xpath = "//h2[.='Partner C.3.2 / Settlement Currency Report']//..//..//following-sibling::div//button[.='Edit']")
	private WebElement button_EditPartnerSettlementCurrency;
	
	private void VerifyPartnerSettlementCurrencyRegion()
	{
		CustomReporter.createNode("Verifying Partner Settlement Currency Region");
		com.isElementPresent(region_PartnerSettlementCurrency, "Partner Settlement Currency, Region");
		com.isElementPresent(hideshowbutton_PartnerSettlementCurrency, "Partner Settlement Currency, hide show button");
		com.isElementPresent(button_EditPartnerSettlementCurrency, "Partner Settlement Currency, Edit Button");

		CustomReporter.createNode("Verifying Partner Settlement Currency Table columns");
		comm.printColumnHeaders(tabPartnerSettlementCurrency, 1);
		comm.verifyColumnHeaders(tabPartnerSettlementCurrency,ColumnsPartnerSettlementCurrency, 1);
	}

	@FindBy(xpath = "//h2[contains(.,'Settlement Status Change History')]")
	private WebElement region_SettlementStatusChangeHistory;
	
	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Settlement Status Change History')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_SettlementStatusChangeHistory;
	
	private void VerifySettlementStatusChangeHistoryRegion()
	{
		CustomReporter.createNode("Verifying Settlement Status Change History");
		com.isElementPresent(region_SettlementStatusChangeHistory, "Settlement Status Change History, Region");
		com.click(hideshowbutton_SettlementStatusChangeHistory, "Settlement Status Change History, hide show button");
	}

}
