package objectRepository.Settlement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class GenerateSettlementStatement 
{
	private SeleniumMethods com;
	public static String title = "Generate Settlement Statement";

	public GenerateSettlementStatement() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	protected WebElement breadcrumb_IOTRONWelcome;
	
	@FindBy(xpath = "//li[contains(.,'Settlement')]")
	protected WebElement breadcrumb_Settlement;
	
	@FindBy(xpath = "//h1[contains(.,'Generate Settlement Statement')]")
	private WebElement breadcrumb_GenerateSettlementStatement;
	
	@FindBy(xpath = "//h2[contains(.,'Settlement Report Parameters')]")
	private WebElement region_SettlementReportParameters;
	
	@FindBy(xpath = "//label[@id='P680_CLIENT_MASTER_ENTITY_ID_LABEL']")
	private WebElement label_HomeOperator;
	
	@FindBy(xpath = "//select[@id='P680_CLIENT_MASTER_ENTITY_ID']")
	private WebElement dropdown_HomeOperator;
	
	@FindBy(xpath = "//label[@id='P680_IOT_AGREEMENT_ID_LABEL']")
	private WebElement label_AgreementReference;
	
	@FindBy(xpath = "//select[@id='P680_IOT_AGREEMENT_ID']")
	private WebElement dropdown_AgreementReference;
	
	@FindBy(xpath = "//label[@id='P680_TRAFFIC_DIRECTION_LABEL']")
	private WebElement label_TrafficDirection;
	
	@FindBy(xpath = "//select[@id='P680_IOT_AGREEMENT_ID']")
	private WebElement dropdown_TrafficDirection;
	
	@FindBy(xpath = "//label[@id='P680_SERVICE_EVENT_LABEL']")
	private WebElement label_ServiceEvent;
	
	@FindBy(xpath = "//select[@id='P680_SERVICE_EVENT']")
	private WebElement dropdown_ServiceEvent;
	
	@FindBy(xpath = "//label[@id='P680_CURRENCY_LABEL']")
	private WebElement label_ReportingCurrency;
	
	@FindBy(xpath = "//select[@id='P680_SERVICE_EVENT']")
	private WebElement dropdown_ReportingCurrency;
	
	@FindBy(xpath = "//label[@id='P680_TAX_LABEL']")
	private WebElement label_TAX;
	
	@FindBy(xpath = "//input[@id='P680_TAX_0']")
	private WebElement radiobutton_BothGrossandNet;
	
	@FindBy(xpath = "//label[@for='P680_TAX_0']")
	private WebElement label_BothGrossandNet;
	
	@FindBy(xpath = "//input[@id='P680_TAX_1']")
	private WebElement radiobutton_Gross;
	
	@FindBy(xpath = "//label[@for='P680_TAX_1']")
	private WebElement label_Gross;
	
	@FindBy(xpath = "//input[@id='P680_TAX_2']")
	private WebElement radiobutton_Net;
	
	@FindBy(xpath = "//label[@for='P680_TAX_2']")
	private WebElement label_Net;
	
	@FindBy(xpath = "//label[@id='P680_PER_MONTH_DETAIL_LABEL']")
	private WebElement label_PerMonthDetails;
	
	@FindBy(xpath = "//input[@id='P680_PER_MONTH_DETAIL_0']")
	private WebElement textbox_PerMonthDetails;
	
	@FindBy(xpath = "//button[contains(.,'Run Report')]")
	private WebElement button_RunReport;
	
	@FindBy(xpath = "//button[contains(.,'Generate Settlement Statement')]")
	private WebElement button_GenerateSettlementStatement;
	
	@FindBy(xpath = "//button[contains(.,'Maintain Discount Agreement')]")
	private WebElement button_MaintainDiscountAgreement;
	
	public void UIVerification() 
	{
		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_Settlement, "Settlement, BreadCrumb");
		com.isElementPresent(breadcrumb_GenerateSettlementStatement, "Generate Settlement Statement, BreadCrumb");
		com.isElementPresent(region_SettlementReportParameters, "Settlement Report Parameters, Region");	
		com.isElementPresent(label_HomeOperator, "Home Operator, Label");
		com.isElementPresent(dropdown_HomeOperator, "FAIR_USAGE, dropdown");
		com.isElementPresent(label_AgreementReference, "Agreement Reference, Label");
		com.isElementPresent(dropdown_AgreementReference, "Agreement Reference, dropdown");
		com.isElementPresent(label_TrafficDirection, "Traffic Direction, Label");
		com.isElementPresent(dropdown_TrafficDirection, "Traffic Direction, dropdown");
		com.isElementPresent(label_ServiceEvent, "Service Event, Label");
		com.isElementPresent(dropdown_ServiceEvent, "Service Event, dropdown");
		com.isElementPresent(label_ReportingCurrency, "Reporting Currency, Label");
		com.isElementPresent(dropdown_ReportingCurrency, "Reporting Currency, dropdown");
		com.isElementPresent(label_TAX, "TAX, Label");
		com.isElementPresent(radiobutton_BothGrossandNet, "Both Gross and Net, radiobutton");
		com.isElementPresent(label_BothGrossandNet, "Both Gross and Net, Label");
		com.isElementPresent(radiobutton_Gross, "Gross, radiobutton");
		com.isElementPresent(label_Gross, "Gross, Label");
		com.isElementPresent(radiobutton_Net, "Net, radiobutton");
		com.isElementPresent(label_Net, "Net, Label");
		com.isElementPresent(label_PerMonthDetails, "Per Month Details, Label");
		com.isElementPresent(textbox_PerMonthDetails, "Per Month Details, textbox");
		com.isElementPresent(button_RunReport, "Run Report, button");
		com.isElementPresent(button_GenerateSettlementStatement, "Generate Settlement Statement, button");
		com.isElementPresent(button_MaintainDiscountAgreement, "Maintain Discount Agreement, button");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
