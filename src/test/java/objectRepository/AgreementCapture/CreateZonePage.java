package objectRepository.AgreementCapture;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;

public class CreateZonePage extends ZoneDefinition {
	
	@FindBy(xpath = "//li[contains(.,'Zone Definition')]")
	protected WebElement breadcrumb_Zonedefination;
	
	@FindBy(xpath = "//h1[contains(.,'Create/Edit Zone')]")
	protected WebElement breadcrumb_CreateEditZone;
	
	@FindBy(xpath = "//h2[contains(.,'Zone definition')]")
	protected WebElement region_ZoneDefinition;
	
	@FindBy(xpath = "//label[@id='P505_ZONE_NAME_LABEL']")
	protected WebElement label_ZONENAME;
	
	@FindBy(xpath = "//input[@id='P505_ZONE_NAME']")
	protected WebElement textbox_ZONENAME;
	
	@FindBy(xpath = "//label[@id='P505_DESCRIPTION_LABEL']")
	protected WebElement label_Description;
	
	@FindBy(xpath = "//input[@id='P505_DESCRIPTION']")
	protected WebElement textbox_Description;
	
	@FindBy(xpath = "//label[@id='P505_COUNTRY_LIST_LABEL']")
	protected WebElement label_CountryList;
	
	@FindBy(xpath = "//select[@id='P505_COUNTRY_LIST_LEFT']")
	protected WebElement shuttle_CountryListLeft;
	
	@FindBy(xpath = "//select[@id='P505_COUNTRY_LIST_RIGHT']")
	protected WebElement shuttle_CountryListRight;
	
	@FindBy(xpath = "//button/span[contains(.,'Cancel')]")
	protected WebElement button_Cancel;
	
	@FindBy(xpath = "//button/span[contains(.,'Create')]")
	protected WebElement button_Create;
	
	
private SeleniumMethods com;
	
	public static String  title="Create/Edit Zone";
	public CreateZonePage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	
	public void UIVerification()
	{
		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Discount Agreement Management, BreadCrumb");
		com.isElementPresent(breadcrumb_Zonedefination, "Zone Definition, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditZone, "Zone Definition, BreadCrumb");
		com.isElementPresent(region_ZoneDefinition, "Zone Definition, Region");
		com.isElementPresent(label_ZONENAME, "ZONE NAME, Label");
		com.isElementPresent(textbox_ZONENAME, "ZONE NAME, textbox");
		com.isElementPresent(label_Description, "Description, label");
		com.isElementPresent(textbox_Description, "Description, textbox");
		com.isElementPresent(label_CountryList, "Country List, textbox");
		com.isElementPresent(shuttle_CountryListLeft, "Country List Left, Shuttle");
		com.isElementPresent(shuttle_CountryListRight, "Country List Right, Shuttle");
		com.isElementPresent(button_Cancel, "Cancel, button");
		com.isElementPresent(button_Create, "Create, button");	

	}

}
