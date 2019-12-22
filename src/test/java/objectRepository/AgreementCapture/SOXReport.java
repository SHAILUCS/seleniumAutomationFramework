package objectRepository.AgreementCapture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import objectRepository.common.TADIG;
import tests.MainRegression.Module_DiscountAgreement;

public class SOXReport {
	
	private SeleniumMethods com;
	private Navigator navigate;
	private IOTRONHomePage ihp;
	private JSONManager json;
	private ApexCommon comm;	
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	private WebElement breadcrumb_IOTRONWelcome;
	
	@FindBy(xpath = "//li[contains(.,'Agreement Capture')]")
	protected WebElement breadcrumb_AgreementCapture;
	
	@FindBy(xpath = "//h1[contains(.,'SOX Report')]")
	private WebElement breadcrumb_SOXreport;
	
	@FindBy(id = "P663_HOME_OPERATOR_MEID_LEFT")
	private WebElement select_HomeOperator_LeftShuttle;
	
	@FindBy(id = "P663_HOME_OPERATOR_MEID_RIGHT")
	private WebElement select_HomeOperator_RightShuttle;
	
	@FindBy(id = "P663_DATE_FROM")
	private WebElement text_DateFrom;
	
	@FindBy(id = "P663_DATE_TO")
	private WebElement text_DateTo;
	
	@FindBy(xpath = "//button[.='Refresh']")
	private WebElement button_Refresh;
	
	@FindBy(xpath = "//h2[.='SOX Report']")
	private WebElement data_SOXreport;
	
	private List<String> tableColumns = new ArrayList<>(Arrays.asList("Agreement Reference","Roaming Partner",
			"Country","Agreement Status","Negotiator","EAE Date","General Note","Verification Note","Final Signoff Note"));
	
	public static String title="SOX report";
	public SOXReport() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		navigate= new Navigator();
		ihp=new IOTRONHomePage();
		comm= new ApexCommon();
	}
	
	
	public boolean performSearch(String TADIG,String DateFrom, String DateTo) {
		com.selectByPartialVisibleText_DoubleClick(select_HomeOperator_LeftShuttle, TADIG);
		com.sendKeys("Date From", text_DateFrom, DateFrom);
		com.sendKeys("Date To", text_DateTo, DateTo);
		com.click(button_Refresh, "Refresh");
		return com.waitForElementTobe_Visible(comm.table_ResultTabHeader,"Result Table");
	}
	
	public void UIVerification(TADIG TADIG,String DateFrom, String DateTo) {
		
		CustomReporter.createNode("Verifying page content");
		com.isElementPresent(breadcrumb_SOXreport, "SOX Report ,BreadCrumb");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");	
		com.isElementPresent(select_HomeOperator_LeftShuttle, "Home Operator Left Shuttle, Shuttle");
		com.isElementPresent(select_HomeOperator_RightShuttle, "Home Operator Right Shuttle, Shuttle");
		com.isElementPresent(text_DateFrom, "Date From, Date Picker");
		com.isElementPresent(text_DateTo, "Date To, Picker");
		com.isElementPresent(button_Refresh, "Refresh, button");
		com.isElementPresent(data_SOXreport, "SOXreport, data");
		
		
		CustomReporter.createNode("Verifying Apex Search region content");
		com.isElementPresent(comm.icon_SelectColumnToSearch, "SearchColumn,DropDown");	
		com.isElementPresent(comm.text_Search, "Search textfield, SearchBox");		
		com.isElementPresent(comm.button_Go, "Go Button, Button");
		com.isElementPresent(comm.label_Rows, "Num Row, Label");
		com.isElementPresent(comm.select_RowsPerPage, "Select Rows, DropDown");
		com.isElementPresent(comm.button_Action, "Action, Button");	
		com.isElementPresent(comm.link_FooterTopButton, "FooterTopButton, Link");	
	
		CustomReporter.createNode("Verifying the Table columns");
		performSearch(TADIG.value, DateFrom, DateTo);
		comm.verifyColumnHeaders(comm.table_ResultTabHeader,tableColumns, 1);
			

	}


	public void discAgr_SOXReportSearchFunctionalityVerification(String... jsonObjName) {
		CustomReporter.createNode("Verifying the Search Report Func");
		json = new JSONManager(Module_DiscountAgreement.jsonFilePath, jsonObjName);
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.SOXReport, ihp.link_AgreementCapture,ihp.link_AgreementCapture_SOXReport);
		performSearch(json.getStr("TADIG"),json.getStr("DateFrom"),json.getStr("DateTo"));
	}	

	
}
