package objectRepository.AgreementCapture.Rework.AgreementDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.jsonUtil.JSONManager;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.AgreementCapture.UpdateSignatureTracker_P180;
import objectRepository.common.NGCHomePage;
import tests.MainRegression.AgreementRework;

public class History_Tab_p16 extends IOTDiscountAgreementDetails_p15_Parent{
		
	public static String title = IOTDiscountAgreementDetails_p15_Parent.title;

	private WebTable webtable_Head;
	private WebTable webtable_Body;
	
	public History_Tab_p16() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		webtable_Head=new WebTable(tabHead);
		webtable_Body=new WebTable(tabBody);
	}

	@FindBy(xpath = "//h2[.='History']")
	private WebElement heading_History;
	
	@FindBy(xpath = "//h2[.='History Parameters']")
	private WebElement heading_HistoryParameters;
	
	@FindBy(xpath = "//div[contains(.,'History Parameters')]/div/button")
	private WebElement hideShow_HistoryParameters;
	
	@FindBy(id = "P15_FROM_DATE")
	private WebElement text_FromDate;

	@FindBy(id = "P15_TO_DATE")
	private WebElement text_ToDate;

	@FindBy(xpath = "//button[.='Refresh Report']")
	private WebElement button_RefreshReport;
	
	private By tabHead=By.xpath("//div[@data-label='History']//div[contains(@class,'head')]/table[@class='a-IRR-table']");
	
	private By tabBody=By.xpath("//div[@data-label='History']//div[contains(@class,'body')]/table[@class='a-IRR-table']");

	private By tab_SpiltPerDateReport=By.xpath("//div[@data-label='History']//table[@class='a-IRR-table']");
	
	/**Search Toolbar Objects*/
	@FindBy(xpath = "//div[@data-label='History']//button[contains(@id,'_column_search_root')]")
	private WebElement icon_SelectColumnToSearch;

	@FindBy(xpath = "//div[@data-label='History']//input[contains(@class,'a-IRR-search-field')]")
	private WebElement text_Search;

	@FindBy(xpath = "//div[@data-label='History']//button[.='Go']")
	private WebElement button_Go;

	@FindBy(xpath = "//div[@data-label='History']//select[contains(@id,'_saved_reports')]")
	private WebElement select_SelectReports;
	
	@FindBy(xpath = "//div[@data-label='History']//div[contains(@class,'rowSelector')]//select")
	private WebElement select_RowsPerPage;

	@FindBy(xpath = "//div[@data-label='History']//button[.='Actions']")
	private WebElement button_Action;

	@FindBy(xpath = "//div[contains(@id,'control_panel')]//span[.='Client Agreement Ref']")
	private WebElement filter_ClientAgreementRef;
	
	@FindBy(xpath = "//div[contains(@id,'control_panel')]//span[.='Section']")
	private WebElement filter_Section;
	
	private List<String> colNames_PrimaryReport = new ArrayList<>(Arrays.asList("Client Agreement Ref", "Audit Date",
			"Column Description", "Old Value", "New Value", "Audit Event", "Section", "Info", "Last Calculation Date",
			"Calculation Status", "App Session User"));
	
	private List<String> colNames_SplitPerDateReport = new ArrayList<>(Arrays.asList("Client Agreement Ref",
			"Audit Date Descending", "Column Description", "Old Value", "New Value", "Audit Event", "App Session User",
			"Info", "Last Calculation Date", "Calculation Status"));
	
	public void verify_UI() {
		CustomReporter.report(STATUS.INFO, "History Tab ->>");
		openTab(tab_History);
		
		com.isElementPresent(heading_History, "heading_History");
		com.isElementPresent(heading_HistoryParameters, "heading_History Parameters");
		verifyHideShowFunc(true, hideShow_HistoryParameters, "History Parameters Section");
		
		com.isElementPresent(text_FromDate, "text_From Date");
		com.isElementPresent(text_ToDate, "text_To Date");
		com.isElementPresent(button_RefreshReport, "button_Refresh Report");
		
		com.click(button_RefreshReport, "button_Refresh Report");
		
		if (com.isElementPresent(tabBody, "History Table")) {
			CustomReporter.report(STATUS.INFO, "Apex search toolbar ->>");
			com.isElementPresent(icon_SelectColumnToSearch, "icon_Select Column To Search");
			com.isElementPresent(text_Search, "text_Search");
			com.isElementPresent(button_Go, "button_Go");
			com.isElementPresent(select_SelectReports, "Select Reports Dropdown");
			com.isElementPresent(select_RowsPerPage, "select_Rows Per Page");
			comm.verifyActionsPopupItems_WithoutSubscription(button_Action);
			
			com.isElementPresent(filter_ClientAgreementRef, "filter_Client Agreement Ref");
			com.isElementPresent(filter_Section, "filter_Section");
			
			comm.verifyColumnHeaders(tabHead, colNames_PrimaryReport, 1);
			
			com.selectByVisibleText(select_SelectReports, "2. Split per Date", true);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			
			//comm.printColumnHeaders(tab_SpiltPerDateReport, 2);
			comm.verifyColumnHeaders(tab_SpiltPerDateReport, colNames_SplitPerDateReport, 2);
			
			com.selectByVisibleText(select_SelectReports, "1. Primary Report", true);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		}
	}

	public void CRUD_Verification(String jsonObjName) {
		Administration_Tab_p15_SignTrack_Sec signTrac=new Administration_Tab_p15_SignTrack_Sec();
		signTrac.navigateTo_UpdateSignatureTracker();
		
		UpdateSignatureTracker_P180 p180=new UpdateSignatureTracker_P180();
		JSONManager json=new JSONManager(AgreementRework.jsonFilePath, jsonObjName,"Edit Signatory1");
		p180.update_PassedRowData(2, json);
		verifyHistoryTabRecords(null,json);
		
		String oldValue=json.getStr("First Signatory");
		signTrac.navigateTo_UpdateSignatureTracker();
		json=new JSONManager(AgreementRework.jsonFilePath, jsonObjName,"Edit Signatory2");
		p180.update_PassedRowData(2, json);
		verifyHistoryTabRecords(oldValue,json);
	}

	private void verifyHistoryTabRecords(String oldValue,JSONManager json) {
		CustomReporter.report(STATUS.INFO, "History Section ->>");
		openTab(tab_History);
		com.click(button_RefreshReport, "button_Refresh Report");
		
		if (com.isElementPresent(tabBody, "History Table")) {
			webtable_Head.initHeaderColumnList(1);
			if (oldValue!=null) {
				int oldValCol=webtable_Head.getColumnNumber("Old Value");
				Util.comparator_NonNumbers(oldValue, webtable_Body.getCellText(2, oldValCol), "'Old Value' Column");
			}
			
			for (String key : json.keySet()) {
				int tempCol=webtable_Head.getColumnNumber("Column Description");
				Util.comparator_NonNumbers(key, webtable_Body.getCellText(2, tempCol), "'Column Description' Column");

				tempCol=webtable_Head.getColumnNumber("New Value");
				Util.comparator_NonNumbers(json.getStr(key), webtable_Body.getCellText(2, tempCol), "'New Value' Column");
			}
			
			int userCol=webtable_Head.getColumnNumber("App Session User");
			Util.comparator_NonNumbers(com.getText(new NGCHomePage().data_UserName), webtable_Body.getCellText(2, userCol), "'App Session User' Column");
		}
		
	}

}