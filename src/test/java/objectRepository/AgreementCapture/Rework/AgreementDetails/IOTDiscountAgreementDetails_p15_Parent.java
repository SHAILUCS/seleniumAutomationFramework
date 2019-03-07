package objectRepository.AgreementCapture.Rework.AgreementDetails;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;

public class IOTDiscountAgreementDetails_p15_Parent {
	protected SeleniumMethods com;
	protected ApexCommon comm;
	public static String title = "IOT Discount Agreement Details";

	public IOTDiscountAgreementDetails_p15_Parent() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//button[.='Back']")
	public WebElement button_Back;

	@FindBy(xpath = "//li[.='IOTRON - Welcome']")
	protected WebElement breadCrumb_IOTRONWelcome;

	@FindBy(xpath = "//li[.='Agreement Capture']")
	protected WebElement breadCrumb_AgreementCapture;

	@FindBy(xpath = "//li[.='Maintain IOT Discount Agreements']")
	protected WebElement breadCrumb_MaintainIOTDiscountAgreements;

	@FindBy(xpath = "//li[.='IOT Discount Agreement Details']")
	protected WebElement breadCrumb_IOTDiscountAgreementDetails;

	@FindBy(xpath = "//button[.='Calculate Agreement']")
	protected WebElement button_CalculateAgreement;

	@FindBy(xpath = "//button[.='Edit Agreement']")
	protected WebElement button_EditAgreement;

	@FindBy(xpath = "//button[.='Delete Agreement']")
	protected WebElement button_DeleteAgreement;

	@FindBy(xpath = "//button[.='Copy Agreement']")
	protected WebElement button_CopyAgreement;

	@FindBy(xpath = "//button[.='Generate Term Sheet']")
	protected WebElement button_GenerateTermSheet;

	@FindBy(xpath = "//button[.='Generate Settlement Statement']")
	protected WebElement button_GenerateSettlementStatement;

	@FindBy(xpath = "//span[.='Details']")
	protected WebElement tab_Details;

	@FindBy(xpath = "//span[.='Discount Parameters']")
	protected WebElement tab_DiscountParameters;

	@FindBy(xpath = "//span[.='Settlement']")
	protected WebElement tab_Settlement;

	@FindBy(xpath = "//span[.='Administration']")
	protected WebElement tab_Administration;

	@FindBy(xpath = "//span[.='History']")
	protected WebElement tab_History;

	protected void openTab(WebElement tabObj) {
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(tabObj,com.getText(tabObj)+" Tab");
	}
	
	/**
	 * This method tests the hide show button functionality
	 * @author shailendra.rajawat
	 * @param trueIfInitiallyOpen Pass true if the section is initially opened
	 * @param hideShowButton the WebElement/By object of hide show icon
	 * @param sectionName The name of the section whose hide show functionality is being tested
	 * */
	protected void verifyHideShowFunc(boolean trueIfInitiallyOpen,Object hideShowButton, String sectionName) {
		//aria-expanded="true" if it is open
		CustomReporter.report(STATUS.INFO, "Hide Show Button ->> START");
		if (trueIfInitiallyOpen) {
			if(com.getAttribute(hideShowButton, "aria-expanded").equalsIgnoreCase("true")){
				CustomReporter.report(STATUS.PASS, "'"+sectionName+"' Initially expanded");
				com.click(hideShowButton, "hide Show Button of '"+sectionName+"'");
				if(com.getAttribute(hideShowButton, "aria-expanded").equalsIgnoreCase("false")){
					CustomReporter.report(STATUS.PASS, "'"+sectionName+"' collapsed");
				}else {
					CustomReporter.report(STATUS.FAIL, "'"+sectionName+"' NOT collapsed");
				}
			}
		}else{
			if(com.getAttribute(hideShowButton, "aria-expanded").equalsIgnoreCase("false")){
				CustomReporter.report(STATUS.PASS, "'"+sectionName+"' Initially collapsed");
				com.click(hideShowButton, "hide Show Button of '"+sectionName+"'");
				if(com.getAttribute(hideShowButton, "aria-expanded").equalsIgnoreCase("true")){
					CustomReporter.report(STATUS.PASS, "'"+sectionName+"' expanded");
				}else {
					CustomReporter.report(STATUS.FAIL, "'"+sectionName+"' NOT expanded");
				}
			}
		}
		com.click(hideShowButton, "hide Show Button of '"+sectionName+"'");
		CustomReporter.report(STATUS.INFO, "Hide Show Button ->> END");
	}

	public void verify_UI() {
		/**
		 * This method is called by verify_UI() method of child Class "Details Tab" 
		 * */
		CustomReporter.report(STATUS.INFO, "Bread Crumb ->>");
		com.isElementPresent(breadCrumb_IOTRONWelcome, "breadCrumb_IOTRON Welcome");
		com.isElementPresent(breadCrumb_AgreementCapture, "breadCrumb_Agreement Capture");
		com.isElementPresent(breadCrumb_MaintainIOTDiscountAgreements, "breadCrumb_Maintain IOT Discount Agreements");
		com.isElementPresent(breadCrumb_IOTDiscountAgreementDetails, "breadCrumb_IOT Discount Agreement Details");

		CustomReporter.report(STATUS.INFO, "Buttons ->>");
		com.isElementPresent(button_CalculateAgreement, "button_Calculate Agreement");
		com.isElementPresent(button_EditAgreement, "button_Edit Agreement");
		com.isElementPresent(button_DeleteAgreement, "button_Delete Agreement");
		com.isElementPresent(button_CopyAgreement, "button_Copy Agreement");
		com.isElementPresent(button_GenerateTermSheet, "button_Generate Term Sheet");
		com.isElementPresent(button_GenerateSettlementStatement, "button_Generate Settlement Statement");
		com.isElementPresent(button_Back, "button_Back");

		CustomReporter.report(STATUS.INFO, "Tabs ->>");
		com.isElementPresent(tab_Details, "tab_Details");
		com.isElementPresent(tab_DiscountParameters, "tab_Discount Parameters");
		com.isElementPresent(tab_Settlement, "tab_Settlement");
		com.isElementPresent(tab_Administration, "tab_Administration");
		com.isElementPresent(tab_History, "tab_History");
	}

	public void verify_UI_VirtualAgreements() {
		CustomReporter.report(STATUS.INFO, "Bread Crumb ->>");
		com.isElementPresent(breadCrumb_IOTRONWelcome, "breadCrumb_IOTRON Welcome");
		com.isElementPresent(breadCrumb_AgreementCapture, "breadCrumb_Agreement Capture");
		com.isElementPresent(breadCrumb_MaintainIOTDiscountAgreements, "breadCrumb_Maintain IOT Discount Agreements");
		com.isElementPresent(breadCrumb_IOTDiscountAgreementDetails, "breadCrumb_IOT Discount Agreement Details");

		CustomReporter.report(STATUS.INFO, "Buttons ->>");
		com.isElementPresent(button_Back, "button_Back");

		CustomReporter.report(STATUS.INFO, "Tabs ->>");
		com.isElementPresent(tab_Details, "tab_Details");
		com.isElementPresent(tab_DiscountParameters, "tab_Discount Parameters");
		com.isElementPresent(tab_History, "tab_History");
		
	}
}
