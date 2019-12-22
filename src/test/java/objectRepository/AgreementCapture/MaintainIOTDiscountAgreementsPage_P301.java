package objectRepository.AgreementCapture;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.CustomExceptionHandler;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg.EditIOTDiscountAgreement_Tab;
import objectRepository.common.ApexCommon;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import tests.MainRegression.Module_DiscountAgreement;

public class MaintainIOTDiscountAgreementsPage_P301 {

	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	private WebElement breadcrumb_IOTRONWelcome;

	@FindBy(xpath = "//li[contains(.,'Agreement Capture')]")
	private WebElement breadcrumb_AgreementCapture;

	@FindBy(xpath = "//h1[contains(.,'Maintain IOT Discount Agreements')]")
	private WebElement breadcrumb_MaintainIOTDiscountAgreements;

	@FindBy(xpath = "//label[@id='P301_SHOW_LIVE_AGRMTS_LABEL']")
	private WebElement label_ShowAgreements;

	@FindBy(xpath = "//input[@id='P301_SHOW_LIVE_AGRMTS_0']")
	private WebElement radio_Current;

	@FindBy(xpath = "//input[@id='P301_SHOW_LIVE_AGRMTS_1']")
	private WebElement radio_All;

	@FindBy(xpath = "//input[@id='P301_SHOW_LIVE_AGRMTS_2']")
	private WebElement radio_Virtual;

	@FindBy(xpath = "//button[@title='Help Text: Show Agreements']")
	private WebElement button_help;

	@FindBy(xpath = "//label[@id='P301_COUNTRY_LABEL']")
	private WebElement label_Country;

	@FindBy(id = "P301_COUNTRY")
	private WebElement dropdown_Country;

	@FindBy(xpath = "//span[.='60 Days Remain']")
	private WebElement link_60DaysRemain;

	@FindBy(xpath = "//span[.='30 Days Remain']")
	private WebElement link_30DaysRemain;

	@FindBy(xpath = "//button[@class='a-Button a-IRR-button a-IRR-button--controls']")
	private WebElement button;

	@FindBy(xpath = "//button[contains(.,'Create IOT Discount Agreement')]")
	private WebElement button_CreateIOTDiscountAgreement;

	@FindBy(css = "td[headers='COUNTRY']")
	private List<WebElement> list_CountryCells;

	@FindBy(css = "td[headers='LINK'] a")
	private List<WebElement> list_EditLinks;

	private List<String> tableColumns = new ArrayList<>(Arrays.asList("","Agreement Reference","Legal Contract ID","Home Operators",
			"Roaming Partner(s)","Country","Agreement Start Date","Agreement End Date","Agreement Status",
			"Signature Status","Agreement Document Loaded","Date Created","Created By", "Date Updated","Updated By","Checklist Complete",
			"Days Remaining Descending","Negotiator","Rolling Agreement","Next Action","Latest User Note","Latest User Note Date",
			"Partner Negotiator","Services","Do Not Calculate","Continuation of IOT Agreement Planned","EAE Date"));

	private WebTable tabHead;
	private WebTable tabData;
	private int headerRow=1;

	public static String title="Maintain IOT Discount Agreements";
	private SeleniumMethods com;
	private ApexCommon comm;
	public MaintainIOTDiscountAgreementsPage_P301() { 
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm= new ApexCommon();
		tabHead=new WebTable(comm.table_ResultTabHeader);
		tabData=new WebTable(comm.table_ResultTabData);
	}

	public void deleteToolCreatedAgreements() {
		performSearch("Automation");
		do {
			com.click(list_EditLinks.get(0));
			new CreateEditIOTDiscountAgreement_P303().deleteAgreement();
		} while (list_EditLinks.size()>0);
	}

	public boolean performSearch(String searchVal){
		CustomReporter.report(STATUS.INFO, "Performing Search for: '"+searchVal+"'");
		boolean bool=false;
		if(com.isElementPresent(radio_All)){
			comm.performSearch(searchVal, "Agreement Reference");
			com.wait(1);
			com.selectByIndex(dropdown_Country, 0);
			com.wait(1);
			com.javaScript_Click(radio_All);
			com.wait(1);

			if(com.waitForElementTobe_Visible(By.xpath("//td[contains(normalize-space(.),'"+searchVal+"')]"), Constant.wait)){
				CustomReporter.report(STATUS.PASS, "Record '"+searchVal+"' displayed in the Search Result Table");
				bool=true;
			}else{
				CustomReporter.report(STATUS.FAIL, "Record '"+searchVal+"' is NOT displayed in the Search Result Table");
				Assert.fail("Record '"+searchVal+"' is NOT displayed in the Search Result Table");
			}
		}
		return bool;
	}

	public boolean performSearchForDeletedAgreement(String deletedAgreementName){
		ApexCommon commmon=new ApexCommon();
		boolean bool=false;
		if(com.isElementPresent(radio_All)){
			com.click(radio_All);
			com.sendKeys(commmon.text_Search, deletedAgreementName);
			com.click(commmon.button_Go);
			if(com.waitForElementTobe_Visible(By.xpath("//span[.='No Agreements have been created at this time.']"), Constant.wait)){
				CustomReporter.report(STATUS.PASS, "Record '"+deletedAgreementName+"' is Deleted");
				bool=true;
			}else{
				CustomReporter.report(STATUS.FAIL, "Record '"+deletedAgreementName+"' is NOT deleted");
			}
		}
		return bool;
	}

	public CreateEditIOTDiscountAgreement_P303 clickOnEditLink(String agreementName){
		CustomReporter.report(STATUS.INFO, "Clicking on Edit link");
		int rowNum=tabData.getRowWithCellText(agreementName);
		if (rowNum>=2) {
			int editObjCount=tabData.getChildObjectsCount(rowNum, 1, "a");
			if(editObjCount>0){
				com.click(tabData.getChildObject(rowNum, 1, "a",0));
				if (com.verifyPageTitle(CreateEditIOTDiscountAgreement_P303.title, true)) {
					return new CreateEditIOTDiscountAgreement_P303();
				}
			}
		}else {
			CustomReporter.report(STATUS.FAIL, "Result Table does not have more than 2 rows");
		}

		return null;
	}

	public CreateEditIOTDiscountAgreement_P303 clickOnCreateIOTDiscountAgreementButton() {
		com.click(button_CreateIOTDiscountAgreement,"Create IOT Discount Agreement Button");
		if (com.verifyPageTitle(CreateEditIOTDiscountAgreement_P303.title, true)) {
			return new CreateEditIOTDiscountAgreement_P303();
		}
		return null;
	}

	public void verifyFiltering_CountryDropDownFeature() {
		//Selecting the country value as Australia and then verifying in atleast 5 rows that country is displayed
		CustomReporter.createNode("verifying the country dropdown filtering");
		String country="Afghanistan";
		String selectedVal=com.getText(com.getFirstSelectedOption(dropdown_Country));
		if(selectedVal.equalsIgnoreCase(country)){
			country="Australia";
		}
		com.selectByVisibleText(dropdown_Country, country, true);
		int count=list_CountryCells.size();
		if(count>0){
			boolean flag=true;
			for (int i = 0; i < count; i++) {
				String cellText=com.getText(list_CountryCells.get(i));
				if(!cellText.toLowerCase().contains(country.toLowerCase())){
					flag=false;
					com.javaScript_ScrollIntoMIDDLEView_AndHighlight(list_CountryCells.get(i));
					break;
				}
			}

			if (flag) {
				CustomReporter.report(STATUS.PASS, "Country dropdown filtering feature is working every row is having country : "+country);
			} else {
				CustomReporter.report(STATUS.FAIL, "Country dropdown filtering feature is NOT working some row is not having country : "+country);
			}
		}else{
			CustomReporter.report(STATUS.FAIL, "Column for Country is not displayed");
		}
	}

	public void verifyFiltering_SelectColumnsFeature() {
		//Removing a displayed column then checking the column count should get reduced
		CustomReporter.createNode("verifying the select columns filtering");
		String selectedCol="Agreement Type";
		int colCountBefore=tabHead.getColCount(headerRow);
		comm.selectColumns_InReport(selectedCol);
		int colCountAfter=tabHead.getColCount(headerRow);
		if(colCountAfter>colCountBefore){
			CustomReporter.report(STATUS.PASS, "'Select Column' filtering is working, column "+selectedCol+" successfully added in the result table");
		}else{
			CustomReporter.report(STATUS.FAIL, "'Select Column' filtering is NOT working, column is NOT added in the result table");
		}
	}


	public void verifyFiltering_SaveReportFeature() {
		CustomReporter.createNode("verifying the save report feature");
		String reportName="AutomationTesting_Name";
		String reportDesc="AutomationTesting_Description";
		comm.saveReport(reportName,reportDesc);


		//verifying the newly added report func
		com.selectByIndex(comm.select_Reports, 0);
		if(com.waitForElementTobe_NotVisible(By.xpath("//li[contains(.,'Saved Report')]"),"The effect of private report '"+reportName+"'")){
			com.selectByPartialVisibleText(comm.select_Reports, reportName);
			if(com.waitForElementTobe_Visible(By.xpath("//li[contains(.,'Saved Report')]"))){
				//Removing the newly added report
				comm.removeReport(reportName);

				//Confirmation code, that the added report is removed from the report dropdown or not 
				if (com.waitForElementTobe_NotStaleAndPresent(comm.select_Reports, "")) {
					List<String> options=com.getAllOptionsVisibleText(comm.select_Reports);
					if(options.contains("1. "+reportName)){
						CustomReporter.report(STATUS.FAIL, "'"+reportName+"' is Not removed from Report dropdown");
					}else {
						CustomReporter.report(STATUS.PASS, "'"+reportName+"' is successfully removed from Report dropdown");
					}
				}
			}
		}
	}

	public void verifyFiltering_ActionFilterFeature() {
		CustomReporter.createNode("verifying the Action>filter feature");
		String filterCol="Agreement Start Date";
		String filterOperator=">";
		long year=Long.parseLong((new SimpleDateFormat("YY")).format(new Date()));
		String filterExpression="01-Jan-"+(year-1);
		comm.applyActionsFilter(filterCol,filterOperator,filterExpression);

		try{
			Date expectedDate=(new SimpleDateFormat("dd-MMM-YY")).parse(filterExpression);
			int filterColNum=tabHead.initHeaderColumnList(headerRow).getColumnNumber(filterCol);
			int lastrow=tabData.getRowCount();
			boolean flag=true;
			for (int i = headerRow+1; i <= lastrow; i++) {
				String cellText=tabData.getCellText(i, filterColNum);
				Date dateCol=(new SimpleDateFormat("dd-MMM-YY")).parse(cellText);
				if(dateCol.compareTo(expectedDate)<0){
					flag=false;
					break;
				}
			}

			if (flag) {
				CustomReporter.report(STATUS.PASS, "Action>Filter feature is working every row is falling after : "+filterExpression);
			} else {
				CustomReporter.report(STATUS.FAIL, "Action>Filter feature is NOT working some row is NOT falling after : "+filterExpression);
			}
		}catch (Exception e) {
			new CustomExceptionHandler(e);
		}

	}

	public void searchAndDeleteAgreement(String agreementName) {
		CustomReporter.createNode("Deleting Agreement "+agreementName);
		if(performSearch(agreementName)){
				CreateEditIOTDiscountAgreement_P303 createEditIOTDiscountAgreement=clickOnEditLink(agreementName); 
				createEditIOTDiscountAgreement.deleteAgreement();
		}
	}

	/**@author prafull.barve**/
	public void UIVerification() 
	{
		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "Iotron Welecome, Link");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, Link");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountAgreements, "Maintain IOT Discount Agreements, Breadcrumb");
		com.isElementPresent(label_ShowAgreements, "Show Agreements, Link");		
		com.isElementPresent(radio_All, "All, Radio");		
		com.isElementPresent(radio_Current, "Current, Radio");
		com.isElementPresent(radio_Virtual, "Virtual, Radio");		
		com.isElementPresent(label_Country, "Country, Link");		
		com.isElementPresent(dropdown_Country, "Country, DropDown");		
		com.isElementPresent(link_60DaysRemain, "60DaysRemain, Link");		
		com.isElementPresent(link_30DaysRemain, "30DaysRemain, Link");
		com.isElementPresent(button, "button");

		CustomReporter.report(STATUS.INFO, "Verifying Apex Search region content");		

		com.isElementPresent(comm.icon_SelectColumnToSearch, "SearchColumn,DropDown");		
		com.isElementPresent(comm.text_Search, "Search textfield, SearchBox");		
		com.isElementPresent(comm.button_Go, "Go Button, Button");		
		com.isElementPresent(comm.select_Reports, "Report Selection, DropDown");
		com.isElementPresent(comm.select_RowsPerPage, "Select Rows, DropDown");
		com.isElementPresent(comm.button_Action, "Action, Button");		
		//com.isElementPresent(comm.button_Next, "Next, Button");
		com.isElementPresent(comm.link_FooterTopButton, "FooterTopButton, Link");

		CustomReporter.report(STATUS.INFO, "Verifying the Table columns");
		comm.verifyColumnHeaders(comm.table_ResultTabHeader,tableColumns, 1);		
	}

	public EditIOTDiscountAgreement_Tab EditIOTDiscountAgreement(String agreementRef) {

		performSearch(agreementRef);
		WebTable tab = new WebTable(comm.table_ResultTabData);
		if(com.navigateToAndVerifyPageTitle(tab.getChildObject(2, 1, "a", 0), EditIOTDiscountAgreement_Tab.title)){
			return new EditIOTDiscountAgreement_Tab();
		}
		return null;		
	}

	public void generationOfTermSheet_COMMON(String jsonObjName) {
		JSONManager json=new JSONManager(Module_DiscountAgreement.jsonFilePath, jsonObjName);
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage= new MaintainIOTDiscountAgreementsPage_P301();
		CreateEditIOTDiscountAgreement_P303 createEditIOTDiscountAgreement= maintainIOTDiscountAgreementsPage.clickOnCreateIOTDiscountAgreementButton();
		String agreementName="AutomationTestingAgreement"+Util.getTimeStamp_InMilliSec();
		createEditIOTDiscountAgreement.createDiscAgreement(agreementName, json.getStr("clientName"),
				json.getStrArr("partner"), json.getStr("startDate"), json.getStr("endDate"),
				json.getBool("bool_checkIncludeSatelite"));
		for (int i = 0; i < json.getJsonArrLength("DiscParam"); i++) {
			createEditIOTDiscountAgreement.addDiscountParameters(i,json);	
		}
		String filePath=createEditIOTDiscountAgreement.verifyGenerateTermSheetButtonFunctionality();
		if(filePath!=null){
			createEditIOTDiscountAgreement.verifyDiscAgreement_DataInTermSheetPdf(filePath,agreementName,json.getStr("clientName"),json.getStrArr("partner"), json.getStr("startDate"), json.getStr("endDate"));
			for (int i = 0; i < json.getJsonArrLength("DiscParam"); i++) {
				createEditIOTDiscountAgreement.verifyOutboundInbound_DataInTermSheetPdf(filePath,
						json.getStr("DiscParam", i, "TrafficDirection"),
						json.getStr("DiscParam", i, "ServiceType"),
						json.getStr("DiscParam", i, "EventType"),
						json.getStr("DiscParam", i, "CalculationType"),
						json.getStr("DiscParam", i, "discBasis"),
						json.getStr("DiscParam", i, "discBasisVal"));
			}
			Util.forceDelete(filePath);
		}
		createEditIOTDiscountAgreement.deleteAgreement();
	}

}
