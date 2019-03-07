package objectRepository.AgreementCapture.Rework.AgreementDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.jsonUtil.JSONManager;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.AgreementCapture.CreateEditRoundingCorrectionFactors_P450;
import objectRepository.AgreementCapture.CreateEditTrafficSplitLevels_P430;
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;
import tests.MainRegression.AgreementRework;

public class Administration_Tab_p15_TAPLevelAgrAdm_Sec extends Administration_Tab_p15 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = IOTDiscountAgreementDetails_p15_Parent.title;
	private WebTable tab_RoundingCorrectionFactors;
	private WebTable tab_TAPLevelTrafficSplit;

	public Administration_Tab_p15_TAPLevelAgrAdm_Sec() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		tab_RoundingCorrectionFactors=new WebTable(table_RoundingCorrectionFactors);
		tab_TAPLevelTrafficSplit=new WebTable(table_TAPLevelTrafficSplit);
	}
	
	@FindBy(xpath = "//h2[.='TAP level agreement administration']")
	private WebElement heading_TapLevelAgreementAdministration;

	@FindBy(xpath = "//div[contains(.,'TAP level agreement administration')]/div/button")
	private WebElement hideShow_TapLevelAgreementAdministration;
	
	public void verify_UI() {
		super.verify_UI();
		CustomReporter.report(STATUS.INFO, "'TAP level agreement administration' Section ->>");
		com.isElementPresent(heading_TapLevelAgreementAdministration, "heading_TAP level agreement administration");
		verifyHideShowFunc(true, hideShow_TapLevelAgreementAdministration , "TAP level agreement administration Section");
		
		tapLevelTrafficSplit_Section();
		roundingCorrectionFactors_Section();
	}

	@FindBy(xpath = "//h2[.='TAP Level Traffic Split']")
	private WebElement heading_TAPLevelTrafficSplit;

	@FindBy(xpath = "//div[contains(.,'TAP Level Traffic Split')]/div/button")
	private WebElement hideShow_TAPLevelTrafficSplit;

	@FindBy(xpath = "//button[.='Create/Edit Traffic Split Levels']")
	private WebElement button_CreateEditTrafficSplitLevels;
	
	private By table_TAPLevelTrafficSplit=By.cssSelector("table[summary='Traffic Split Settings']");

	private List<String> colNames_TAPLevelTrafficSplit = new ArrayList<>(Arrays.asList("Home Operator",
			"Partner Operator", "% Voice MO Home Customer Roamers", "% Voice MO Local Customer Roamers",
			"% Voice MO International Customer Roamers", "% Voice MO Home Visitor Roamers",
			"% Voice MO Local Visitor Roamers", "% Voice MO International Visitor Roamers", "Traffic Period"));

	private void tapLevelTrafficSplit_Section() {
		CustomReporter.report(STATUS.INFO, "TAP Level Traffic Split - Sub Section ->>");
		com.isElementPresent(heading_TAPLevelTrafficSplit, "heading_TAP Level Traffic Split");
		verifyHideShowFunc(true, hideShow_TAPLevelTrafficSplit, "TAP Level Traffic Split Sub - Section");
		
		if(com.isElementPresent(button_CreateEditTrafficSplitLevels, "button_Create/Edit Traffic Split Levels")){
			com.click(button_CreateEditTrafficSplitLevels, "button_Create/Edit Traffic Split Levels");
			com.verifyPageTitle(PagesTitle.CreateEditTrafficSplitLevels_P430.title, true);
			
			CreateEditTrafficSplitLevels_P430 p430=new CreateEditTrafficSplitLevels_P430();
			com.click(p430.button_Cancel, "button_Cancel");
			com.verifyPageTitle(title, true);
		}
		
		comm.verifyColumnHeaders(table_TAPLevelTrafficSplit, colNames_TAPLevelTrafficSplit, 1);
	}
	
	@FindBy(xpath = "//h2[.='Rounding & Correction Factors']")
	private WebElement heading_RoundingCorrectionFactors;

	@FindBy(xpath = "//div[contains(.,'Rounding & Correction Factors')]/div/button")
	private WebElement hideShow_RoundingCorrectionFactors;

	@FindBy(xpath = "//button[.='Create/Edit Traffic Split Levels']")
	private WebElement button_RoundingCorrectionFactors;
	
	@FindBy(xpath = "//button[.='Create/Edit Rounding & Correction Factors']")
	private WebElement button_CreateEditRoundingCorrectionFactors;
	
	private By table_RoundingCorrectionFactors=By.cssSelector("table[summary='Rounding & Correction Factors Report']");

	private List<String> colNames_RoundingCorrectionFactors = new ArrayList<>(
			Arrays.asList("Service Type", "Traffic Direction", "Event Type", "Charging Increment Type",
					"First Increment", "Subsequent Increment", "Correction Factor (%)"));

	private void roundingCorrectionFactors_Section() {
		CustomReporter.report(STATUS.INFO, "Rounding & Correction Factors - Sub Section ->>");
		com.isElementPresent(heading_RoundingCorrectionFactors, "heading_Rounding & Correction Factors");
		verifyHideShowFunc(true, hideShow_RoundingCorrectionFactors, "Rounding & Correction Factors Sub - Section");
		
		if(com.isElementPresent(button_CreateEditRoundingCorrectionFactors, "button_Create/Edit Rounding & Correction Factors")){
			com.click(button_CreateEditRoundingCorrectionFactors, "button_Create/Edit Rounding & Correction Factors");
			com.verifyPageTitle(PagesTitle.CreateEditRoundingCorrectionFactors_P450.title, true);
			
			CreateEditRoundingCorrectionFactors_P450 p450=new CreateEditRoundingCorrectionFactors_P450();
			com.click(p450.button_Cancel, "button_Cancel");
			com.verifyPageTitle(title, true);
		}
		comm.verifyColumnHeaders(table_RoundingCorrectionFactors, colNames_RoundingCorrectionFactors, 1);
	}

		

	public void CRUD_verification_TAPLevelTrafficSplit(String jsonObjName) {
		JSONManager json=new JSONManager(AgreementRework.jsonFilePath, jsonObjName);
		
		
		
	}



	public void CRUD_verification_RoundingCorrectionFactors(String jsonObjName) {
		
		JSONManager json=new JSONManager(AgreementRework.jsonFilePath, jsonObjName,"Add");
		
		CustomReporter.report(STATUS.INFO, "Rounding & Correction Factors Section ->>");
		openTab(tab_Administration);

		tab_RoundingCorrectionFactors.initHeaderColumnList(1);
		
		/*
		 * CREATE
		 * */
		com.click(button_CreateEditRoundingCorrectionFactors, "button_Create Edit Rounding Correction Factors");
		com.verifyPageTitle(CreateEditRoundingCorrectionFactors_P450.title, true);
		
		CreateEditRoundingCorrectionFactors_P450 p450=new CreateEditRoundingCorrectionFactors_P450();
		p450.addEntry(json);
		String rowIdentifier=json.getStr("Correction Factor (%)");
		verifyRowData_RoundingCorrectionFactors(rowIdentifier,json);
		
		/*
		 * EDIT
		 * */
		com.click(button_CreateEditRoundingCorrectionFactors, "button_Create Edit Rounding Correction Factors");
		com.verifyPageTitle(CreateEditRoundingCorrectionFactors_P450.title, true);
		
		json=new JSONManager(AgreementRework.jsonFilePath, jsonObjName,"Edit");
		p450.editEntry(rowIdentifier,json);
		rowIdentifier=json.getStr("Correction Factor (%)");
		verifyRowData_RoundingCorrectionFactors(rowIdentifier,json);
		
		/*
		 * DELETE
		 * */
		com.click(button_CreateEditRoundingCorrectionFactors, "button_Create Edit Rounding Correction Factors");
		com.verifyPageTitle(CreateEditRoundingCorrectionFactors_P450.title, true);
		p450.deleteEntry(rowIdentifier);
		int row=tab_RoundingCorrectionFactors.getRowWithCellText(rowIdentifier);
		if (row<0) {
			CustomReporter.report(STATUS.PASS, "Row deleted");
		} else {
			CustomReporter.report(STATUS.FAIL, "Row not deleted");
		}
		
		
	}


	private void verifyRowData_RoundingCorrectionFactors(String rowIdentifier, JSONManager json) {
		int row=tab_RoundingCorrectionFactors.getRowWithCellText(rowIdentifier);
		if (row>0) {
			String corrFact_Actual = tab_RoundingCorrectionFactors.getCellText(row,
					tab_RoundingCorrectionFactors.getColumnNumber("Correction Factor (%)"));
			String corrFact_Expected = json.getStr("Correction Factor (%)");
			if (corrFact_Actual.contains(corrFact_Expected)) {
				CustomReporter.report(STATUS.PASS, "Correction Factor (%) properly displayed as "+corrFact_Actual);
			} else {
				CustomReporter.report(STATUS.FAIL, "Correction Factor (%) incorrectly displayed as '"+corrFact_Actual+"' expected :'"+corrFact_Expected+"'");
			}
			
			Map<String, String> tempMap=new HashMap<String, String>();
			for (String key : json.keySet()) {
				tempMap.put(key, tab_RoundingCorrectionFactors.getCellText(row, tab_RoundingCorrectionFactors.getColumnNumber(key)));
			}

			json.getMapObj().remove("Correction Factor (%)");
			tempMap.remove("Correction Factor (%)");
			if (tempMap.equals(json.getMapObj())) {
				CustomReporter.report(STATUS.PASS, "All Data is properly displayed "+tempMap);
			} else {
				CustomReporter.report(STATUS.FAIL, "Data is not matching");
				CustomReporter.report(STATUS.INFO, "EXPECTED	:"+json.getMapObj());
				CustomReporter.report(STATUS.INFO, "ACTUAL	:"+tempMap);
			}
			
		}else {
			CustomReporter.report(STATUS.FAIL, "Row is not found for data "+json.getMapObj());
		}
	}
	
}
