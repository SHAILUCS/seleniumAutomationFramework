package objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;

public class DocumentLibrary_Tab extends EditIOTDiscountAgreement_Tab 
{
	
	@FindBy(xpath = "//a[contains(.,'Document Library')]")
	private WebElement tab_DocumentLibrary;
	
	@FindBy(xpath = "//h2[contains(.,'Document Library')]")
	private WebElement region_DocumentLibrary;	
	
	@FindBy(xpath = "//button[contains(.,'Add Document')]")
	private WebElement button_AddDocument;
	
	private List<String> tableColumns = new ArrayList<>(Arrays.asList("","Filename","Description",
			"Uploaded By","Date Uploaded","Document Type","Document","View"));
	
	private SeleniumMethods com;
	private ApexCommon comm;
	
	public static String  title="Create / Edit IOT Discount Agreement";
	public DocumentLibrary_Tab() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm= new ApexCommon();
	}

	public void UIVerification() {
		
		com.click(tab_DocumentLibrary, "Agreement Checklist,Tab");
		CustomReporter.createNode("Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountAgreements, "Maintain IOT Discount Agreements, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountAgreement, "Create Edit IOT Discount Agreement, BreadCrumb");
		com.isElementPresent(tab_DocumentLibrary, "Agreement Checklist, tab");
		com.isElementPresent(region_DocumentLibrary, "AgreementChecklist, region");
		
		CustomReporter.createNode("Verifying Apex Search region content");
		
		com.isElementPresent(comm.icon_SelectColumnToSearch, "SearchColumn,DropDown");		
		com.isElementPresent(comm.text_Search, "Search textfield, SearchBox");		
		com.isElementPresent(comm.button_Go, "Go Button, Button");
		com.isElementPresent(comm.label_Rows, "label Rows, Label");
 		com.isElementPresent(comm.select_RowsPerPage, "Select Rows, DropDown");
		com.isElementPresent(comm.button_Action, "Action, Button");		
		
		CustomReporter.createNode("Verifying the Table columns");
		if(!com.waitForElementTobe_NotVisible(comm.table_ResultTabHeader,5)){
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, tableColumns, 1);
		}else{
			CustomReporter.report(STATUS.WARNING, "Table columns can't be verified as Data Table is not displayed");
		}
	}

}
