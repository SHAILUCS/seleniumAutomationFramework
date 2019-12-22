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

import objectRepository.DealTracker.DTModule.DTSummary.EditIOTOperatorGroupPage;
import objectRepository.common.ApexCommon;

public class MaintainIOTOperatorGroupsPage 
{
	
	@FindBy(xpath = "//button[contains(.,'Create IOT Operator Group')]")
	private WebElement button_CreateIOTOperatorGroup;
	
	@FindBy(xpath = "//h1[contains(.,'Maintain IOT Operator Groups')]")
	private WebElement breadcrumb_MaintainIOTDiscountPartyDetails;
	
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	private WebElement breadcrumb_IOTRONWelcome;
	
	@FindBy(xpath = "//li[contains(.,'Agreement Capture')]")
	private WebElement breadcrumb_AgreementCapture;
	
	private List<String> tableColumns = new ArrayList<>(Arrays.asList("","Group Name","Group Type","Created By",
			"Date Created","Updated By","Date Updated"));
	
	private SeleniumMethods com;
	
	public static String  title="Maintain IOT Operator Groups";
	public MaintainIOTOperatorGroupsPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	
	/**@author prafull.barve**/
	public void UIVerification() 
	{
		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(button_CreateIOTOperatorGroup, "Create IOT Operator Group, Link");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountPartyDetails, "Maintain IOT Discount Party Details, BreadCrumb");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		
		CustomReporter.report(STATUS.INFO, "Verifying Apex Search region content");
		ApexCommon comm = new ApexCommon();
		com.isElementPresent(comm.icon_SelectColumnToSearch, "SearchColumn,DropDown");	
		com.isElementPresent(comm.text_Search, "Search textfield, SearchBox");		
		com.isElementPresent(comm.button_Go, "Go Button, Button");		
		com.isElementPresent(comm.select_RowsPerPage, "Select Rows, DropDown");
		com.isElementPresent(comm.button_Action, "Action, Button");
		com.isElementPresent(comm.label_Rows, "Num Row, Label");	
		com.isElementPresent(comm.button_Next, "Next, Button");
		com.isElementPresent(comm.link_FooterTopButton, "FooterTopButton, Link");
		
		CustomReporter.report(STATUS.INFO, "Verifying the Table columns");
		comm.verifyColumnHeaders(comm.table_ResultTabHeader,tableColumns, 1);
		
	}


	public CreateIOTOperatorGroupPage navigateToCreateIOTOperatorGroupPage() 
	{	
		if(com.navigateToAndVerifyPageTitle(button_CreateIOTOperatorGroup, CreateIOTOperatorGroupPage.title)){
			return new CreateIOTOperatorGroupPage();
		}
		return null;		
	}
	
	public EditIOTOperatorGroupPage navigateToEditIOTOperatorGroupPage() 
	{	
		ApexCommon comm= new ApexCommon();
		WebTable tab = new WebTable(comm.table_ResultTabData);
		if(com.navigateToAndVerifyPageTitle(tab.getChildObject(2, 1, "img", 0), EditIOTOperatorGroupPage.title)){
			return new EditIOTOperatorGroupPage();
		}
		return null;
	}
}
