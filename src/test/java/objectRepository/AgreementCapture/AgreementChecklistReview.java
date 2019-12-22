package objectRepository.AgreementCapture;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.common.ApexCommon;

public class AgreementChecklistReview {


	private SeleniumMethods com;
	private ApexCommon comm;
	
	@FindBy(xpath = "//th[contains(.,'Agreement Checklist Review')]")
	private WebElement data_AgreementChecklistReview_Header; 
	
	@FindBy(xpath = "//h1[contains(.,'Agreement Checklist Review')]")
	protected WebElement breadcrumb_AgreementChecklistReview;
	
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	protected WebElement breadcrumb_IOTRONWelcome;
	
	@FindBy(xpath = "//li[contains(.,'Agreement Capture')]")
	protected WebElement breadcrumb_AgreementCapture;
	
	@FindBy(xpath = "//button/span[@class='a-Icon a-Collapsible-icon']")
	private WebElement button_AgreementReference;
	
	@FindBy(xpath = "//label[@class='a-IRR-controlsCheckboxLabel']")
	private WebElement checkbox_AgreementReference;
	
	@FindBy(xpath = "//a/span[@id='control_text_CLIENT_AGREEMENT_REF']")
	private WebElement controlbreak_AgreementReference;
	
	@FindBy(xpath = "//button[@title='Remove Control Break']")
	private WebElement button_controlbreakremover;
	
	private List<String> tableColumns = new ArrayList<>(Arrays.asList("Check Sequence","Check Text","Agreement Phase",
			"Job Function","Check Complete","Date Completed","Date Updated","Updated By","Set Check"));

	private WebTable tab;
	private int headerRow=2;
	
	public static String title="Agreement Checklist Review";
	public AgreementChecklistReview() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm= new ApexCommon();
		tab= new WebTable(comm.table_ResultTab);
	}
	
	public void performEditing() {
		CustomReporter.createNode("Performing Editing");
		int rowNum=headerRow+1;
		int setCheckCol=tab.initHeaderColumnList(headerRow).getColumnNumber("Set Check");
		com.click(tab.getChildObject(rowNum, setCheckCol, "img", 0),"Set check icon in row num: "+(rowNum-1));
		if(com.verifyPageTitle(UpdateIOTAgreementChecklistItempage.title, true)){
			UpdateIOTAgreementChecklistItempage updateIOTAgreementChecklistItem= new UpdateIOTAgreementChecklistItempage();
			String checkComplete="Yes";
			String dateCompleted= (new SimpleDateFormat("dd-MMM-yy")).format(new Date());
			String dateUpdated= (new SimpleDateFormat("dd-MMM-yy")).format(new Date());
			updateIOTAgreementChecklistItem.makeChanges(checkComplete,dateCompleted);
			verifyModifications(headerRow, checkComplete, dateCompleted, dateUpdated);
			tab= new WebTable(comm.table_ResultTab);
			com.click(tab.getChildObject(rowNum, setCheckCol, "img", 0),"Set check icon in row num: "+(rowNum-1));
			checkComplete="N/A";
			updateIOTAgreementChecklistItem.makeChanges(checkComplete,dateCompleted);
			verifyModifications(headerRow, checkComplete, dateCompleted, dateUpdated);
		}
	}

	private void verifyModifications(int headerRow,String checkComplete,String dateCompleted,String dateUpdated) {
		CustomReporter.createNode("Verifying changes");
		int rowNum=headerRow+1;
		tab.initHeaderColumnList(headerRow);
		String checkCompletePage=tab.getCellText(rowNum, tab.getColumnNumber("Check Complete"));
		String dateCompletedPage= tab.getCellText(rowNum, tab.getColumnNumber("Date Completed"));
		String dateUpdatedPage= tab.getCellText(rowNum, tab.getColumnNumber("Date Updated"));
		if(checkComplete.equalsIgnoreCase(checkCompletePage)){
			CustomReporter.report(STATUS.PASS, "Modified check type: '"+checkComplete+"' is reflecting on the Agreement Checklist Review Page");
		}else{
			CustomReporter.report(STATUS.FAIL, "Modified check type: '"+checkComplete+"' is NOT reflecting on the Agreement Checklist Review Page instead value '"+checkCompletePage+"' is getting displayed");
		}
		if(dateCompleted.equalsIgnoreCase(dateCompletedPage)){
			CustomReporter.report(STATUS.PASS, "Modified date completed: '"+dateCompleted+"' is reflecting on the Agreement Checklist Review Page");
		}else{
			CustomReporter.report(STATUS.FAIL, "Modified date completed: '"+dateCompleted+"' is NOT reflecting on the Agreement Checklist Review Page instead value '"+dateCompletedPage+"' is getting displayed");
		}
		if(dateUpdated.equalsIgnoreCase(dateUpdatedPage)){
			CustomReporter.report(STATUS.PASS, "Updated date : '"+dateUpdated+"' is correctly reflecting on the Agreement Checklist Review Page");
		}else{
			CustomReporter.report(STATUS.FAIL, "Updated date : '"+dateUpdated+"' is NOT correctly reflecting on the Agreement Checklist Review Page instead value '"+dateUpdatedPage+"' is getting displayed");
		}

	}

	public void verifyPageContent() {
		
		com.isElementPresent(data_AgreementChecklistReview_Header, "Agreement Checklist Review, section header");
		CustomReporter.report(STATUS.INFO, "Apex Search Section content");
		com.isElementPresent(comm.button_Go, "Search icon");
		com.isElementPresent(comm.text_Search, "Search textfield");
		com.isElementPresent(comm.button_Go, "Go Button");
		com.isElementPresent(comm.button_Action, "Action Button");
		CustomReporter.report(STATUS.INFO, "Verifying the Table columns");
		com.click(comm.button_Action);
		if(com.waitForElementTobe_Visible(comm.button_SelectColumns)){
			com.click(comm.button_SelectColumns);
			if(com.waitForElementTobe_Visible(comm.select_SelectColumns_DisplayInReport)){
				List<String> optionList=com.getAllOptionsVisibleText(comm.select_SelectColumns_DisplayInReport);
				
				boolean failFlag=true;
				String colName="";
				String colListName="";
				int colNum=1;
				for (int col = 0; col < optionList.size(); col++) {
					colName=tab.getCellText(2, colNum);
					colListName=optionList.get(col);
					if(colListName.equals("Agreement Reference")){
						colNum--;
					}else{
						if(!colName.contains(colListName)){
							failFlag=false;
							break;
						}
					}
					colNum++;
				}
				if (failFlag) {
					CustomReporter.report(STATUS.PASS, "All columns are properly getting displayed");
				}else{
					CustomReporter.report(STATUS.FAIL, "'"+colName+"' column is not properly displayed, as per Select Columns >> Display Columns multiselect");
				}
			}
		}

	}
	public void UIVerification() {

		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(breadcrumb_AgreementChecklistReview, "Agreement Checklist Review");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		com.isElementPresent(button_AgreementReference, "Agreement Reference, Button");
		com.isElementPresent(checkbox_AgreementReference, "Agreement Reference, CheckBox");
		com.isElementPresent(controlbreak_AgreementReference, "Agreement Reference, ControlBreak");
		com.isElementPresent(button_controlbreakremover, "controlbreakremover, Button");

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
		comm.verifyColumnHeaders(comm.table_ResultTab,tableColumns, 2);
	}
	
	public UpdateIOTAgreementChecklistItempage navigateToUpdateIOTAgreementChecklistItempage() 
	{	
		WebTable tab = new WebTable(comm.table_ResultTab);
		int columnnumber = tab.initHeaderColumnList(2).getColumnNumber("Set Check");
		if(com.navigateToAndVerifyPageTitle(tab.getChildObject(3, columnnumber, "img", 0), UpdateIOTAgreementChecklistItempage.title)){
			return new UpdateIOTAgreementChecklistItempage();
		}
		return null;		
	}
}
