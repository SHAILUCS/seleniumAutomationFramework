package objectRepository.AgreementCapture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.common.ApexCommon;

public class MaintainIOTDiscountPartyDetailsPage {
	
	@FindBy(xpath = "//button[contains(.,'Create New IOT Discount Party')]")
	public WebElement button_CreateNewIOTDiscountParty;
		
	@FindBy(xpath = "//h1[contains(.,'Maintain IOT Discount Party Details')]")
	private WebElement breadcrumb_MaintainIOTDiscountPartyDetails;
	
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	private WebElement breadcrumb_IOTRONWelcome;
	
	@FindBy(xpath = "//li[contains(.,'Agreement Capture')]")
	private WebElement breadcrumb_AgreementCapture;
	
	private List<String> tableColumns = new ArrayList<>(Arrays.asList("Edit","TADIG","Organisation",
			"Contact Name","Job Title","Operator Group","Email","Tel Fixed Office",
			"Mobile","Fax"));
	
	
	private SeleniumMethods com;
	
	public static String  title="Maintain IOT Discount Party Details";
	public MaintainIOTDiscountPartyDetailsPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	
	/**@author prafull.barve**/
	public void UIVerification() 
	{
		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(button_CreateNewIOTDiscountParty, "Create New IOT Discount Party, button");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountPartyDetails, "Maintain IOT Discount Party Details, BreadCrumb");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
	
		
		CustomReporter.report(STATUS.INFO, "Verifying Apex Search region content");
		ApexCommon comm= new ApexCommon();
		com.isElementPresent(comm.icon_SelectColumnToSearch, "SearchColumn,DropDown");	
		com.isElementPresent(comm.text_Search, "Search textfield, SearchBox");		
		com.isElementPresent(comm.button_Go, "Go Button, Button");
		com.isElementPresent(comm.label_Rows, "Num Row, Label");
		com.isElementPresent(comm.select_RowsPerPage, "Select Rows, DropDown");
		com.isElementPresent(comm.button_Action, "Action, Button");	
		com.isElementPresent(comm.button_Next, "Next, Button");
		com.isElementPresent(comm.link_FooterTopButton, "FooterTopButton, Link");
		
		CustomReporter.report(STATUS.INFO, "Verifying the Table columns");
		comm.verifyColumnHeaders(comm.table_ResultTabHeader,tableColumns, 1);
		
	}


	public CreateIOTDiscountPartyDetailsPage navigateToCreatePage() {
		if(com.navigateToAndVerifyPageTitle(button_CreateNewIOTDiscountParty, CreateIOTDiscountPartyDetailsPage.title)){
			CustomReporter.report(STATUS.INFO, "Navigation complete to "+CreateIOTDiscountPartyDetailsPage.title);
			return new CreateIOTDiscountPartyDetailsPage();
		}
		return null;
	}


	public EditIOTDiscountPartyDetailsPage navigateToEditPage() {
		ApexCommon comm= new ApexCommon();
		WebTable tab = new WebTable(comm.table_ResultTabData);
		if(com.navigateToAndVerifyPageTitle(tab.getChildObject(2, 1, "img", 0), EditIOTDiscountPartyDetailsPage.title)){
			CustomReporter.report(STATUS.INFO, "Navigation complete to "+EditIOTDiscountPartyDetailsPage.title);
			return new EditIOTDiscountPartyDetailsPage();
		}
		return null;
		
	}
}
