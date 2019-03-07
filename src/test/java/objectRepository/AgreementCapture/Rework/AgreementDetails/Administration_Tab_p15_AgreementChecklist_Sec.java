package objectRepository.AgreementCapture.Rework.AgreementDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.jsonUtil.JSONManager;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.AgreementCapture.UpdateIOTAgreementChecklistItem_P338;
import objectRepository.common.ApexCommon;
import objectRepository.common.NGCHomePage;
import objectRepository.common.PagesTitle;
import tests.MainRegression.AgreementRework;

public class Administration_Tab_p15_AgreementChecklist_Sec extends Administration_Tab_p15{
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = IOTDiscountAgreementDetails_p15_Parent.title;

	public Administration_Tab_p15_AgreementChecklist_Sec() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		tab=new WebTable(table_AreementChecklist);
	}

	@FindBy(xpath = "//h2[.='Agreement Checklist']")
	private WebElement heading_AgreementChecklist;

	@FindBy(xpath = "//div[contains(.,'Agreement Checklist')]/div/button")
	private WebElement hideShow_AgreementChecklist;
	
	private By table_AreementChecklist=By.cssSelector("table[summary='Agreement Checklist']");
	
	private List<String> colNames = new ArrayList<>(Arrays.asList("Update", "Agreement Phase", "Check Sequence",
			"Checklist Item", "Job Function", "Completed", "Date Completed", "Update Date", "Updated By"));

	WebTable tab;
	
	public void verify_UI() {
		super.verify_UI();
		agreementChecklist_Section();
	}

	private void agreementChecklist_Section() {
		CustomReporter.report(STATUS.INFO, "Agreement Checklist Section ->>");
		com.isElementPresent(heading_AgreementChecklist, "heading_Agreement Checklist");
		verifyHideShowFunc(true, hideShow_AgreementChecklist, "Agreement Checklist Section");
		comm.verifyColumnHeaders(table_AreementChecklist, colNames, 1);
		
		CustomReporter.report(STATUS.INFO, "Edit icon verification >>");
		if(com.isElementPresent(table_AreementChecklist)){
			
			int editIconCount=tab.getChildObjectsCount(2, 1, "img");
			if (editIconCount>0) {
				com.click(tab.getChildObject(2, 1, "img", 0),"Edit Icon");
				com.verifyPageTitle(PagesTitle.UpdateIOTAgreementChecklistItem_P338.title, true);
				
				UpdateIOTAgreementChecklistItem_P338 checklistItem_P338=new UpdateIOTAgreementChecklistItem_P338();
				com.click(checklistItem_P338.button_Cancel,"Cancel Button");
				com.verifyPageTitle(title, true);
			}
		}
		
	}

	public void CRUD_verification(int row, String jsonObjName) {
		JSONManager json=new JSONManager(AgreementRework.jsonFilePath, jsonObjName);
		
		CustomReporter.report(STATUS.INFO, "Agreement Checklist Section ->>");
		openTab(tab_Administration);
		
		int totalRows=tab.getRowCount();
		if (totalRows>=row) {
			com.click(tab.getChildObject(row, 1, "img", 0), "Edit Icon");
			com.verifyPageTitle(UpdateIOTAgreementChecklistItem_P338.title, true);
			
			UpdateIOTAgreementChecklistItem_P338 p338=new UpdateIOTAgreementChecklistItem_P338();
			p338.applyChanges(json.getStr("Completed"), json.getStr("Date Completed"));
			
			verifyChanges(row,jsonObjName);
		}
	}

	private void verifyChanges(int row, String jsonObjName) {
		JSONManager json=new JSONManager(AgreementRework.jsonFilePath, jsonObjName);
		tab.initHeaderColumnList(1);
		
		Map<String, String> tempMap=new HashMap<>();
		
		Set<String> keys=json.keySet();
		for (String key : keys) {
			int tempCol=tab.getColumnNumber(key);
			String tempVal=tab.getCellText(row, tempCol);
			tempMap.put(key, tempVal);
		}

		NGCHomePage h=new NGCHomePage();
		String expectedUserName=com.getText(h.data_UserName).replace(".", " ");
		String expectedUpdatedDate=Util.convertToString("dd-MMM-YY", new Date());
		
		int col_LastUpdated=tab.getColumnNumber("Update Date");
		int col_UpdatedBy=tab.getColumnNumber("Updated By");
		if (tab.getCellText(row, col_UpdatedBy).equalsIgnoreCase(expectedUserName)) {
			CustomReporter.report(STATUS.PASS, expectedUserName+" is successfully displayed in Agreement Checklist Table");
		} else {
			CustomReporter.report(STATUS.FAIL, expectedUserName+" is failed displayed in Agreement Checklist Table, instead "+tab.getCellText(row, col_UpdatedBy)+" is getting displayed");
		}
		
		if (tab.getCellText(row, col_LastUpdated).toLowerCase().contains(expectedUpdatedDate.toLowerCase())) {
			CustomReporter.report(STATUS.PASS, expectedUpdatedDate+"Agreement Checklist Table");
		} else {
			CustomReporter.report(STATUS.FAIL, expectedUpdatedDate+" is failed displayed in Agreement Checklist Table, instead "+tab.getCellText(row, col_LastUpdated)+" is getting displayed");
		}
		
		
		if (json.getMapObj().equals(tempMap)) {
			CustomReporter.report(STATUS.PASS, "Changes are properly reflected "+json.getMapObj());
		} else {
			CustomReporter.report(STATUS.FAIL, "Changes failed to reflect");
			CustomReporter.report(STATUS.INFO, "EXPECTED:	"+json.getMapObj());
			CustomReporter.report(STATUS.INFO, "ACTUAL:	"+tempMap);
		}
	}
	

}