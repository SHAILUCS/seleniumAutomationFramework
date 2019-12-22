package objectRepository.Settlement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;

public class SettlementStatementHistory extends GenerateSettlementStatement
{
	private SeleniumMethods com;
	public static String title = "Settlement Statement History";

	public SettlementStatementHistory() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	@FindBy(xpath = "//h1[contains(.,'Settlement Statement History')]")
	private WebElement breadcrumb_SettlementStatementHistory;
	
	@FindBy(xpath = "//h2[contains(.,'Home Operator Select')]")
	private WebElement region_HomeOperatorSelect;
	
	@FindBy(xpath = "//label[@id='P544_HOME_OPERATOR_MEID_LABEL']")
	private WebElement label_HomeOperatorSelect;
	
	@FindBy(xpath = "//select[@id='P544_HOME_OPERATOR_MEID_LEFT']")
	private WebElement Shuttle_HomeOperatorSelectLeft;
	
	@FindBy(xpath = "//select[@id='P544_HOME_OPERATOR_MEID_RIGHT']")
	private WebElement Shuttle_HomeOperatorSelectRight;
	
	@FindBy(xpath = "//h2[contains(.,'Settlement Statement History')]")
	private WebElement region_SettlementStatementHistory;
	
	@FindBy(xpath = "//button[contains(.,'Display Settlement Statements')]")
	private WebElement button_DisplaySettlementStatements;
	
	public void UIVerification() 
	{
		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_Settlement, "Settlement, BreadCrumb");
		com.isElementPresent(breadcrumb_SettlementStatementHistory, "Settlement Statement History, BreadCrumb");
		com.isElementPresent(region_HomeOperatorSelect, "Home Operator Select, Region");
		com.isElementPresent(label_HomeOperatorSelect, "Home Operator Select, Label");
		com.isElementPresent(Shuttle_HomeOperatorSelectLeft, "Home Operator Select Left, Label");
		com.isElementPresent(Shuttle_HomeOperatorSelectRight, "Home Operator Select Right, Label");
		com.isElementPresent(region_SettlementStatementHistory, "Settlement Statement History, Region");
		com.isElementPresent(button_DisplaySettlementStatements, "Display Settlement Statements, Label");


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
