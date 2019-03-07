package objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;

public class DiscountParameters_Tab extends EditIOTDiscountAgreement_Tab {
	@FindBy(xpath = "//a[contains(.,'Discount Parameters')]")
	private WebElement tab_DiscountParameters;

	@FindBy(xpath = "//h2[contains(.,'Discount Parameters')]")
	private WebElement region_DiscountParameters;

	@FindBy(xpath = "//label[@id='P303_FILTER_MSG_LABEL']")
	private WebElement label_ShowAdditionalAttributesForAgreementSetUp;

	@FindBy(xpath = "//button[@title='Help Text: Show Additional Attributes for Agreement set-up']")
	private WebElement helpbutton_ShowAdditionalAttributesForAgreementSetUp;

	@FindBy(xpath = "//input[@id='P303_SHOW_BALANCED_0']")
	private WebElement checkbox_Balancing;

	@FindBy(xpath = "//label[@for='P303_SHOW_BALANCED_0']")
	private WebElement label_Balancing;

	@FindBy(xpath = "//input[@id='P303_SHOW_DESTINATIONS_0']")
	private WebElement checkbox_CallDestination;

	@FindBy(xpath = "//label[@for='P303_SHOW_DESTINATIONS_0']")
	private WebElement label_CallDestination;

	@FindBy(xpath = "//input[@id='P303_SHOW_FAIR_USAGE_0']")
	private WebElement checkbox_FAIR_USAGE;

	@FindBy(xpath = "//label[@for='P303_SHOW_FAIR_USAGE_0']")
	private WebElement label_FAIR_USAGE;

	@FindBy(xpath = "//input[@id='P303_SHOW_QUALIF_SERVICE_0']")
	private WebElement checkbox_QUALIF_SERVICE;

	@FindBy(xpath = "//label[@for='P303_SHOW_QUALIF_SERVICE_0']")
	private WebElement label_QUALIF_SERVICE;

	@FindBy(xpath = "//input[@id='P303_SHOW_PMN_LEVEL_0']")
	private WebElement checkbox_PMN_LEVEL;

	@FindBy(xpath = "//label[@for='P303_SHOW_PMN_LEVEL_0']")
	private WebElement label_PMN_LEVEL;

	@FindBy(xpath = "//input[@id='P303_SHOW_AIRTIME_TOLL_0']")
	private WebElement checkbox_AIRTIME_TOLL;

	@FindBy(xpath = "//label[@for='P303_SHOW_AIRTIME_TOLL_0']")
	private WebElement label_AIRTIME_TOLL;

	@FindBy(xpath = "//input[@id='P303_SHOW_DATES_0']")
	private WebElement checkbox_IntermediateDates;

	@FindBy(xpath = "//label[@for='P303_SHOW_DATES_0']")
	private WebElement label_IntermediateDates;

	@FindBy(xpath = "//input[@id='P303_SHOW_CAP_0']")
	private WebElement checkbox_IMSICaps;

	@FindBy(xpath = "//label[@for='P303_SHOW_CAP_0']")
	private WebElement label_IMSICaps;

	@FindBy(xpath = "//input[@id='P303_SHOW_MARKET_SHARE_0']")
	private WebElement checkbox_MarketShare;

	@FindBy(xpath = "//label[@for='P303_SHOW_MARKET_SHARE_0']")
	private WebElement label_MarketShare;

	@FindBy(xpath = "//button[contains(.,'Save Discount Parameters')]")
	private WebElement button_SaveDiscountParameters;

	@FindBy(xpath = "//button[contains(.,'Add Discount Parameter')]")
	private WebElement button_AddDiscountParameter;

	@FindBy(xpath = "//button[contains(.,'Delete Discount Parameter')]")
	private WebElement button_DeleteDiscountParameter;

	private By tab = By.cssSelector("table[summary='Discount Parameters']");

	private List<String> tableColumns = new ArrayList<>(Arrays.asList("Check All", "Discount Settlement Method",
			"Discount Direction", "Service Type", "Event Type", "Charging Increment used in Calculation",
			"Destination & Call Type", "Selected Destinations", "Calculation Type", "Discount Basis",
			"Discount Basis Value", "Discount Currency", "Discount Tax Inclusive (Gross) / Exclusive (Net)",
			"Bound Type", "Lower Bound", "Upper Bound", "Reporting"));

	private SeleniumMethods com;

	public static String title = "Create / Edit IOT Discount Agreement";

	public DiscountParameters_Tab() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	public void UIVerification() {
		com.click(tab_DiscountParameters, "Discount Parameters,Tab");
		CustomReporter.createNode("Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountAgreements, "Maintain IOT Discount Agreements, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountAgreement,
				"Create Edit IOT Discount Agreement, BreadCrumb");
		com.isElementPresent(tab_DiscountParameters, "Discount Parameters, Tab");
		com.isElementPresent(region_DiscountParameters, "Discount Parameters, Region");
		com.isElementPresent(label_ShowAdditionalAttributesForAgreementSetUp,
				"Show Additional Attributes For Agreement Set-Up, Label");
		com.isElementPresent(helpbutton_ShowAdditionalAttributesForAgreementSetUp,
				"Show Additional Attributes For Agreement Set-Up, helpbutton");
		com.isElementPresent(checkbox_Balancing, "Balancing, checkbox");
		com.isElementPresent(label_Balancing, "Balancing, Label");
		com.isElementPresent(checkbox_CallDestination, "Call Destination, checkbox");
		com.isElementPresent(label_CallDestination, "Call Destination, Label");
		com.isElementPresent(checkbox_FAIR_USAGE, "FAIR_USAGE, checkbox");
		com.isElementPresent(label_FAIR_USAGE, "FAIR_USAGE, Label");
		com.isElementPresent(checkbox_QUALIF_SERVICE, "QUALIF_SERVICE, checkbox");
		com.isElementPresent(label_QUALIF_SERVICE, "QUALIF_SERVICE, Label");
		com.isElementPresent(checkbox_PMN_LEVEL, "PMN_LEVEL, checkbox");
		com.isElementPresent(label_PMN_LEVEL, "PMN_LEVEL, Label");
		com.isElementPresent(checkbox_AIRTIME_TOLL, "AIRTIME_TOLL, checkbox");
		com.isElementPresent(label_AIRTIME_TOLL, "AIRTIME_TOLL, Label");
		com.isElementPresent(checkbox_IntermediateDates, "Intermediate Dates, checkbox");
		com.isElementPresent(label_IntermediateDates, "Intermediate Dates, Label");
		com.isElementPresent(checkbox_IMSICaps, "IMSI Caps, checkbox");
		com.isElementPresent(label_IMSICaps, "IMSI Caps, Label");
		com.isElementPresent(checkbox_MarketShare, "Market Share, checkbox");
		com.isElementPresent(label_MarketShare, "Market Share, Label");
		com.isElementPresent(button_SaveDiscountParameters, "Save Discount Parameters, Button");
		com.isElementPresent(button_AddDiscountParameter, "Add Discount Parameter, Button");
		com.isElementPresent(button_DeleteDiscountParameter, "Delete Discount Parameter, Button");

		CustomReporter.createNode("Verifying the Table columns");
		ApexCommon comm = new ApexCommon();
		comm.printColumnHeaders(tab, 1);
		comm.verifyColumnHeaders(tab,tableColumns, 1);
	}

}
