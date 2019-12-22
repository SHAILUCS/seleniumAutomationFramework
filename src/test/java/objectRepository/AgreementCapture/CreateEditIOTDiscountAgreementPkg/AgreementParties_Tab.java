package objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class AgreementParties_Tab extends EditIOTDiscountAgreement_Tab
{
	@FindBy(xpath = "//a[contains(.,'Agreement Parties')]")
	private WebElement tab_AgreementParties;
	
	@FindBy(xpath = "//h2[contains(.,'Agreement Parties')]")
	private WebElement region_AgreementParties;
	
	@FindBy(xpath = "//label[@id='P303_HOME_GROUP_LABEL']")
	private WebElement label_AvailableHomeGroups;
	
	@FindBy(xpath = "//select[@id='P303_HOME_GROUP']")
	private WebElement dropdown_AvailableHomeGroups;
	
	@FindBy(xpath = "//button[@title='Help Text: Available Home Groups']")
	private WebElement helpbutton_AvailableHomeGroups;
	
	@FindBy(xpath = "//label[@id='P303_CLIENT_PARTIES_LABEL']")
	private WebElement label_HomeParty;
	
	@FindBy(xpath = "//select[@id='P303_CLIENT_PARTIES_LEFT']")
	private WebElement shuttle_HomePartyLeft;
	
	@FindBy(xpath = "//select[@id='P303_CLIENT_PARTIES_RIGHT']")
	private WebElement shuttle_HomePartyRight;
	
	@FindBy(xpath = "//label[@id='P303_PARTNER_GROUP_LABEL']")
	private WebElement label_AvailblePartnerGroups;
	
	@FindBy(xpath = "//select[@id='P303_PARTNER_GROUP']")
	private WebElement dropdown_AvailblePartnerGroups;
	
	@FindBy(xpath = "//button[@title='Help Text: Available Partner Groups']")
	private WebElement helpbutton_AvailablePartnerGroups;
	
	@FindBy(xpath = "//label[@id='P303_PARTNER_PARTIES_LABEL']")
	private WebElement label_PartnerParty;
	
	@FindBy(xpath = "//select[@id='P303_PARTNER_PARTIES_LEFT']")
	private WebElement shuttle_PartnerPartyLeft;
	
	@FindBy(xpath = "//select[@id='P303_PARTNER_PARTIES_RIGHT']")
	private WebElement shuttle_PartnerPartyRight;
	
	@FindBy(xpath = "//button[contains(.,'Save Agreement Parties')]")
	private WebElement button_SaveAgreementParties;
	
	private SeleniumMethods com;
	
	public static String  title="Create / Edit IOT Discount Agreement";
	public AgreementParties_Tab() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	public void UIVerification()
	{
		com.click(tab_AgreementParties, "Agreement Parties, Tab");
		CustomReporter.createNode("Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountAgreements, "Maintain IOT Discount Agreements, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountAgreement, "Create Edit IOT Discount Agreement, BreadCrumb");
		com.isElementPresent(tab_AgreementParties, "Agreement Parties, Tab");		
		com.isElementPresent(region_AgreementParties, "Agreement Parties, Region");
		com.isElementPresent(label_AvailableHomeGroups, "Agreement Reference Template, Label");
		com.isElementPresent(dropdown_AvailableHomeGroups, "Agreement Reference Template, dropdown");
		com.isElementPresent(helpbutton_AvailableHomeGroups, "Agreement Reference Template, helpbutton");
		com.isElementPresent(label_HomeParty, "Agreement Reference Template, Label");
		com.isElementPresent(shuttle_HomePartyLeft, "Home Party Left, shuttle");
		com.isElementPresent(shuttle_HomePartyRight, "Home Party Right, shuttle");
		com.isElementPresent(label_AvailblePartnerGroups, "Availble Partner Groups, Label");
		com.isElementPresent(dropdown_AvailblePartnerGroups, "Availble Partner Groups, dropdown");
		com.isElementPresent(helpbutton_AvailablePartnerGroups, "Available Partner Groups, helpbutton");
		com.isElementPresent(label_PartnerParty, "Partner Party, Label");
		com.isElementPresent(shuttle_PartnerPartyLeft, "Partner Party Left, shuttle");
		com.isElementPresent(shuttle_PartnerPartyRight, "Partner Party Right, shuttle");
		com.isElementPresent(button_SaveAgreementParties, "Save Agreement Parties, Button");
	}
}
