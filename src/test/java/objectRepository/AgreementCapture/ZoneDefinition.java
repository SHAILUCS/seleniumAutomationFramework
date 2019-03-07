package objectRepository.AgreementCapture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.jsonUtil.JSONManager;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.common.ApexCommon;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import tests.MainRegression.Module_DiscountAgreement;

public class ZoneDefinition {
	private SeleniumMethods com;
	private Navigator navigate;
	private IOTRONHomePage ihp;
	private ApexCommon comm;
	private WebTable tabHead;
	private WebTable tabData;
	private JSONManager json;
	private CreateEditZone createEditZone;
	
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	protected WebElement breadcrumb_IOTRONWelcome;
	
	@FindBy(xpath = "//li[contains(.,'Agreement Capture')]")
	protected WebElement breadcrumb_AgreementCapture;
	
	@FindBy(xpath = "//h1[contains(.,'Zone Definition')]")
	protected WebElement breadcrumb_Zonedefination;
	
	@FindBy(xpath = "//h2[contains(.,'Client Zone List')]//..//..//button")
	private WebElement hideshowbutton_ClientZoneList;
	
	@FindBy(xpath = "//h2[contains(.,'Client Zone List')]")
	private WebElement region_ClientZoneList;
	
	@FindBy(xpath = "//button[contains(.,'Create new zone')]")
	private WebElement button_CreateNewZone;
	
	@FindBy(xpath = "//span[@id='P502_COUNTRY_STATS']")
	private WebElement message_CountryStats;
	
	private List<String> tableColumns = new ArrayList<>(Arrays.asList("","Zone Name","Description","Create Date",
			"Created by","Update Date","Updated by","Client Team Name"));
	
	public static String title="Zone definition";
	public ZoneDefinition() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		navigate= new Navigator();
		ihp=new IOTRONHomePage();
		comm=new ApexCommon();
		tabHead=new WebTable(comm.table_ResultTabHeader);
		tabData=new WebTable(comm.table_ResultTabData);
		createEditZone= new CreateEditZone();
	}
	
	///private By table_ResultTab=By.cssSelector("table[class='apexir_WORKSHEET_DATA']");
	
	@FindBy(xpath = "//th[contains(.,'Client Zone List')]")
	private WebElement data_ClientZoneList_Header;
	
	public boolean createZone(String zoneName,String zoneDesc,String[] countries) {
		CustomReporter.createNode("Creating a new Zone");
		boolean bool=false;
		
		if(com.navigateToAndVerifyPageTitle(button_CreateNewZone,CreateEditZone.title)){
			createEditZone.createZone(zoneName, zoneDesc, countries);
			bool=true;
		}
		return bool;
	}

	public boolean searchZone(String zoneName) {
		CustomReporter.createNode("Performing Search for newly created Zone");
		boolean searchDone=false;
		
		//Have to keep this code as is, coz it is throwing StaleElement Exception during the run
		com.sendKeys(comm.text_Search, zoneName);
		com.click(comm.button_Go);

		if (com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator,10)) {
			int rowNum=tabData.getRowWithCellText(zoneName);
			if(rowNum>0){
				CustomReporter.report(STATUS.PASS, "Record '"+zoneName+"' displayed in the Search Result Table");
				searchDone=true;
			}else{
				CustomReporter.report(STATUS.FAIL, "Record '"+zoneName+"' is NOT displayed in the Search Result Table");
			}
		}

		return searchDone;
	}

	public void editZone(String zoneName) {
		CustomReporter.createNode("Editing the newly created Zone");
		com.click(tabData.getChildObject(2, 1, "img", 0), "Edit icon");
		createEditZone.editZone(zoneName);
	}

	public void deleteZone(String zoneName) {
		CustomReporter.createNode("Deleting the newly created Zone");
		comm.performSearch(zoneName, "Zone Name");
		com.click(tabData.getChildObject(2, 1, "img", 0), "Edit icon");
		createEditZone.deleteZone();
		comm.performUnsuccessfulSearch(zoneName);
	}	
	
		public void UIVerification() {
		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "AgreementCapture, BreadCrumb");
		com.isElementPresent(breadcrumb_Zonedefination, "Zone Defination, BreadCrumb");
		com.isElementPresent(button_CreateNewZone, "Createnewzone, Button");
		com.isElementPresent(hideshowbutton_ClientZoneList, "Client Zone List, HideShowButton");
		com.isElementPresent(region_ClientZoneList, "Client Zone List, Region");
		com.isElementPresent(message_CountryStats, "Country Stats, Message");
	
		CustomReporter.report(STATUS.INFO, "Verifying Apex Search region content");
		
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
		
		public CreateZonePage navigateToCreateZonePage() 
		{
			if(com.navigateToAndVerifyPageTitle(button_CreateNewZone, CreateZonePage.title)){
				return new  CreateZonePage();
			}
			return null;	
		}
		
		public EditZonePage navigateToEditZonePage() {
			WebTable tab = new WebTable(comm.table_ResultTabData);
			if(com.navigateToAndVerifyPageTitle(tab.getChildObject(2, 1, "img", 0), EditZonePage.title)){
				return new EditZonePage();
			}
			return null;		
		}

		public void discAgr_ZoneDefinitionCRUDOperation(String... jsonObjName) {
			navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.ZoneDefinition, ihp.link_AgreementCapture, ihp.link_AgreementCapture_ZoneDefinition);

			json=new JSONManager(Module_DiscountAgreement.jsonFilePath, jsonObjName);
			String zoneName=json.getStr("zoneName");
			if(createZone(zoneName,json.getStr("zoneDescription"),json.getStrArr("Countries"))){
				if(searchZone(zoneName)){
					zoneName=zoneName+"_EDITED";
					editZone(zoneName);
					deleteZone(zoneName);
				}
			}
		}
	
}
