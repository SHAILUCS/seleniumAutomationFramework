package objectRepository.Settlement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;

public class CreditDebitNoteReport extends GenerateSettlementStatement{
	private SeleniumMethods com;
	public static String title = "Credit/Debit Note Report";

	public CreditDebitNoteReport() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	@FindBy(xpath = "//h1[contains(.,'Credit/Debit Note Report')]")
	private WebElement breadcrumb_CreditDebitNoteReport;
	
	@FindBy(xpath = "//h2[contains(.,'Credit/Debit Note Report')]")
	private WebElement region_CreditDebitNoteReport;
	
	@FindBy(id = "P305_CLIENT_MASTER_ENTITY_ID")
	private WebElement select_SelectClient;
	
	@FindBy(id = "P305_DATE_FROM")
	private WebElement text_CreatedDateFrom;
	
	@FindBy(id = "P305_DATE_TO")
	private WebElement text_CreatedDateTo;
	
	@FindBy(linkText = "Run Report")
	private WebElement button_RunReport;
	
	
	public void performSearch(String client,String dateFrom, String dateTo){
		CustomReporter.report(STATUS.INFO, "Performing Search");
		com.selectByPartialVisibleText(select_SelectClient, client);
		com.sendKeys(text_CreatedDateFrom, dateFrom);
		com.sendKeys(text_CreatedDateTo, dateTo);
		com.click(button_RunReport);
	}
	
	public void UIVerification() 
	{
		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_Settlement, "Settlement, BreadCrumb");
		com.isElementPresent(breadcrumb_CreditDebitNoteReport, "Generate Settlement Statement, BreadCrumb");
		com.isElementPresent(region_CreditDebitNoteReport, "Settlement Report Parameters, Region");
		
		CustomReporter.report(STATUS.INFO, "Verifying Apex Search region content");
		ApexCommon comm= new ApexCommon();
		com.isElementPresent(comm.icon_SelectColumnToSearch, "SearchColumn,DropDown");	
		com.isElementPresent(comm.text_Search, "Search textfield, SearchBox");		
		com.isElementPresent(comm.button_Go, "Go Button, Button");
		com.isElementPresent(comm.label_Rows, "Num Row, Label");
		com.isElementPresent(comm.select_RowsPerPage, "Select Rows, DropDown");
		com.isElementPresent(comm.button_Action, "Action, Button");
	}

}
