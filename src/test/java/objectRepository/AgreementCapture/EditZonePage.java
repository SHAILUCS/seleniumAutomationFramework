package objectRepository.AgreementCapture;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;

public class EditZonePage extends CreateZonePage 
{
	
	@FindBy(xpath = "//button/span[contains(.,'Delete')]")
	protected WebElement button_Delete;
	
	@FindBy(xpath = "//button/span[contains(.,'Apply Changes')]")
	protected WebElement button_ApplyChanges;
		
	private SeleniumMethods com;
	
	public static String  title="Create/Edit Zone";
	public EditZonePage() {
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
		com.isElementPresent(button_Delete, "Delete, button");	
		com.isElementPresent(button_ApplyChanges, "Apply Changes, button");	
	}
}
