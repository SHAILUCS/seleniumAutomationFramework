package objectRepository.common;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Quotes;

import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.CustomExceptionHandler;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;

public class ApexCommon extends SeleniumMethods {

	public ApexCommon() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	/**Result Table Object*/
	public By table_ResultTab=By.cssSelector("table[class='a-IRR-table']");
	public By table_ResultTabHeader=By.cssSelector("div[class*='head']>table[class='a-IRR-table']");
	public By table_ResultTabData=By.cssSelector("div[class*='body']>table[class='a-IRR-table']");

	/** GRID Table Objects */
	public By tab_Left_GRID_Head = By.xpath("(//table[@class='a-GV-table a-GV-table--checkbox'])[1]");
	public By tab_Left_GRID_Body = By.xpath("(//table[@class='a-GV-table a-GV-table--checkbox'])[2]");
	public By tab_Right_GRID_Head = By.xpath("(//table[@class='a-GV-table'])[1]");
	public By tab_Right_GRID_Body = By.xpath("(//table[@class='a-GV-table'])[2]");
	
	
	/**Applied Filter Table Object*/
	public By table_AppliedFilterTab=By.id("apexir_CONTROL_PANEL_COMPLETE");

	/** No Data Found Object span[class='a-Icon icon-irr-no-results'] */
	public By data_NoDataFoundIcon=By.cssSelector("span[class='a-Icon icon-irr-no-results']");

	/** Validation Message Popup Object */
	@FindBy(css = "div[class='a-Notification a-Notification--error']")
	public WebElement data_ValidationMessagePopup;
		
	/**Page loader Object*/
	public By icon_SpinnerApex5LoadingIndicator=By.xpath("//*[contains(@class,'Processing')]");

	/** Column header -> Rollover list*/
	@FindBy(css = "div[id$='sort_widget_search'] a")
	public List<WebElement> link_RolloverList;

	/** Column header -> Rollover input*/
	@FindBy(css = "div[id$='sort_widget_search'] input")
	public WebElement text_RolloverSearchField;

	/**Apex remove selected values from shuttle**/
	@FindBy(xpath = "//button[.='Reset']")
	public WebElement button_Reset;
	
	/**GRID Object - Report button on Action popup**/
	@FindBy(xpath = "//button[.='Report']")
	public WebElement button_Report_GRID;

	/** Common to GRID and normal tables */
	@FindBy(xpath = "//button[.='Help'][@role='menuitem']")
	public WebElement button_Help; 

	/** Search Toolbar Objects */
	@FindBy(css = "button[id$='_column_search_root']")
	public WebElement icon_SelectColumnToSearch;

	/** GRID OBJECT - Select columns to search */
	@FindBy(css = "button[title='Select columns to search']")
	public WebElement icon_SelectColumnToSearch_GRID;

	@FindBy(css = "input[class='a-IRR-search-field']")
	public WebElement text_Search;
	
	/** GRID OBJECT - Text Search field */
	@FindBy(css = "input[id$='toolbar_search_field']")
	public WebElement text_Search_GRID;

	/** Common to GRID and normal tables */
	@FindBy(xpath = "//button[.='Go']")
	public WebElement button_Go;
	
	@FindBy(css = "div[class*='rowSelector'] select")
	public WebElement select_RowsPerPage;

	
	/**
	 * GRID has shuttles in it, but at a time only one is visible, so by using
	 * getInteractiveElement method we can get which one is visible and can
	 * perform actions
	 */
	@FindBy(xpath = "//div[contains(@class,'GV')]//select[contains(@id,'LEFT')]")
	public WebElement shuttle_LeftVisible_GRID;
	
	/**
	 * GRID has shuttles in it, but at a time only one is visible, so by using
	 * getInteractiveElement method we can get which one is visible and can
	 * perform actions
	 */
	@FindBy(xpath = "//div[contains(@class,'GV')]//select[contains(@id,'RIGHT')]")
	public WebElement shuttle_RightVisible_GRID;
	
	/**
	 * GRID has input fields in it, but at a time only one is visible, so by using
	 * getInteractiveElement method we can get which one is visible and can
	 * perform actions
	 */
	@FindBy(xpath = "//div[contains(@class,'GV')]/input[starts-with(@id,'C')]")
	public WebElement input_TextfieldVisible_GRID;
	
	/** Common to GRID and normal tables */
	@FindBy(xpath = "//button[.='Actions']")
	public WebElement button_Action;

	/** GRID OBJECT - Edit checkbox, presented as a button, use Javascript click */
	@FindBy(css = "input[name='edit']")
	public WebElement button_Edit_GRID;

	/** GRID OBJECT - Save button*/
	@FindBy(xpath = "//button[.='Save']")
	public WebElement button_Save_GRID;
	
	/** GRID OBJECT - Add Row button*/
	@FindBy(xpath = "//button[.='Add Row']")
	public WebElement button_AddRow_GRID;

	/** GRID OBJECT - Delete Rows button*/
	@FindBy(xpath = "//button[.='Delete Rows']")
	public WebElement button_DeleteRows_GRID;

	/** GRID OBJECT - Reset button*/
	@FindBy(xpath = "//button[.='Reset Rows']")
	public WebElement button_Reset_GRID;
	
	/** GRID OBJECT - Download button*/
	@FindBy(xpath = "//button[.='Download']")
	public WebElement button_DownloadXls_GRID;
	
	public By select_Reports=By.cssSelector("select[id$='_saved_reports']");

	@FindBy(xpath = "//label[@for='apexir_SAVED_REPORTS']")
	public WebElement label_Reports;

	@FindBy(xpath = "//label[contains(.,'Rows')]")
	public WebElement label_Rows;

	@FindBy(xpath = "//button[@title='Next']")
	public WebElement button_Next;

	@FindBy(xpath = "//a[@id='t_Footer_topButton']")
	public WebElement link_FooterTopButton;

	//TODO Actions >> Select Columns Objects
	@FindBy(xpath = "//button[.='Select Columns']")
	public WebElement button_SelectColumns;

	/** GRID Objects - Columns button on Action Popup */
	@FindBy(xpath = "//button[.='Columns']")
	public WebElement button_SelectColumns_GRID;
	
	@FindBy(xpath = "//button[.='Save Report']")
	public WebElement button_SaveReport;

	@FindBy(css = "input[id$='_report_name']")
	private WebElement text_Name_SaveReport;

	@FindBy(css = "input[id$='_report_description']")
	private WebElement text_Description_SaveReport;

	/** Works for both GRID and normal tables */
	@FindBy(xpath = "//button[.='Download'][@role='menuitem']")
	public WebElement button_Download;

	/** Works for both GRID and normal tables */
	@FindBy(xpath = "//button[.='Filter']")
	public WebElement button_Filter;

	/** GRID OBJECT - Data button on Action popup */
	@FindBy(xpath = "//button[.='Data']")
	public WebElement button_Data_GRID;
	
	/** Common to both GRID and normal table */
	@FindBy(xpath = "//button[.='Chart']")
	public WebElement button_Chart;
	
	@FindBy(xpath = "//button[.='Subscription']")
	public WebElement button_Subscription;

	@FindBy(css = "select[title='Do Not Display']")
	public WebElement select_SelectColumns_DoNotDisplay;

	@FindBy(css = "select[title='Display in Report']")
	public WebElement select_SelectColumns_DisplayInReport;

	@FindBy(xpath = "//button[.='Apply']")
	public WebElement button_Apply;

	@FindBy(xpath = "//button[.='Cancel']")
	public WebElement button_Cancel;

	@FindBy(css = "select[id$='_column_name']")
	private WebElement select_ActionFilter_Column;

	@FindBy(css = "select[title='Operator']:not([style*='display: none'])")
	private WebElement select_ActionFilter_Operator;

	@FindBy(css = "td:not([style*='display: none']):not([style*='display:none'])>input[title='Expression']")
	private WebElement text_ActionFilter_Expression;

	//TODO Format option objects

	@FindBy(xpath = "//button[.='Rows Per Page']")
	public WebElement button_RowsPerPage;
		
	/** Common to both GRID and normal tables */
	@FindBy(xpath = "//button[.='Format']")
	public WebElement button_Format;

	@FindBy(xpath = "//button[.='Sort']")
	public WebElement button_Sort;

	@FindBy(xpath = "//button[.='Control Break']")
	public WebElement button_ControlBreak;

	@FindBy(xpath = "//button[.='Highlight']")
	public WebElement button_Highlight;

	@FindBy(xpath = "//button[.='Compute']")
	public WebElement button_Compute;

	@FindBy(xpath = "//button[.='Aggregate']")
	public WebElement button_Aggregate;

	@FindBy(xpath = "//button[.='Group By']")
	public WebElement button_GroupBy;

	@FindBy(css = "select[id$='aggregate_by']")
	private WebElement select_Format_Function;

	@FindBy(css = "select[id$='number_columns']")
	private WebElement select_Format_Column;

	@FindBy(xpath = "//button[.='Download'][contains(@id,'actions')]")
	public WebElement icon_Download;

	@FindBy(xpath = "//a[.='CSV']")
	public WebElement icon_Download_CSV;

	@FindBy(xpath = "//a[.='HTML']")
	public WebElement icon_Download_HTML;

	@FindBy(xpath = "//a[.='Email']")
	public WebElement icon_Download_Email;

	@FindBy(css = "input[id$='email_to']")
	public WebElement text_To;

	@FindBy(css = "input[id$='email_subject']")
	public WebElement text_Subject;

	@FindBy(xpath = "//button[.='Send']")
	public WebElement button_Send;


	@FindBy(css = "input[id$='column_heading']")
	private WebElement text_ColumnHeading;

	@FindBy(css = "textarea[id$='computation_expr']")
	private WebElement text_ComputationExp;

	/** @author shailendra.rajawat 17Jan18*/
	@FindBy(css = "button[title='View Report']")
	public WebElement button_ViewReport;

	/** @author shailendra.rajawat 17Jan18*/
	@FindBy(css = "button[title='View Chart']")
	public WebElement button_ViewChart;

	/** @author shailendra.rajawat 17Jan18*/
	@FindBy(css = "button[title='View Group By']")
	public WebElement button_ViewGroupBy;

	@FindBy(xpath = "//button[.='OK']")
	public WebElement button_OK;
	
	/** To Make the report occupy the browser's full screen space*/
    @FindBy(xpath="//button[@title='Maximize']")
    public WebElement button_MaximizeReport;
    
    /** To Make the report occupy its initial screen space*/
    @FindBy(xpath="//button[@title='RESTORE']")
    public WebElement button_RestoreReport;
	
	/**
	 * performSearch is a common method that can be reused in all pages where the Apex Search feature is used
	 * @param searchVal Value you want to search
	 * @param colName the column on which the search to be performed
	 * @return true if the Search Result Table displays the searched value, else will return false after waiting for Constant.wait timeout
	 */
	public boolean performSearch(String searchVal,String colName){
		return selectColumnName_ProvideSearchVal_ClickGoButton(searchVal, colName);
	}
	
	private boolean selectColumnName_ProvideSearchVal_ClickGoButton(String searchVal,String colName){
		
		if (colName!=null) {
			javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(getInteractableWebElementFromList(icon_SelectColumnToSearch));
			waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator);
			
			
			By xp=By.xpath("//button[contains(@id,'column_search_drop')][normalize-space(.)='"+colName+"']");
			
			if(waitForElementsTobe_NotVisible(xp,1)){
				//if the column names popup is not getting displayed, we will try one more time 
				javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(getInteractableWebElementFromList(icon_SelectColumnToSearch));
				waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator);
				WebElement colNameObj=getInteractableWebElementFromList(xp);
				click_UsingAction(colNameObj);
				wait(1);
			}else{
				WebElement colNameObj=getInteractableWebElementFromList(xp);
				click_UsingAction(colNameObj);
				wait(1);
			}
		}
		
		sendKeys(getInteractableWebElementFromList(text_Search), searchVal);
		click(getInteractableWebElementFromList(button_Go));
		waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator);
		
		if(colName != null){
			return waitForElementTobe_Visible(getInteractableWebElementFromList(By.xpath("//li[contains(normalize-space(),'" + searchVal + "')][contains(normalize-space(),'" + colName + "')]")),"Search filter for data ["+searchVal+"] on Column ["+colName+"]");
		}else{
			return waitForElementTobe_Visible(getInteractableWebElementFromList(By.xpath("//li[contains(normalize-space(),'" + searchVal + "')]")),"Search filter for data ["+searchVal+"] on All Columns");
		}
	}
	
	public void redefineAppliedFilterValue(String innerTextAppliedFilter, String newFilterText) {
		click(By.xpath("//li[contains(.,\""+innerTextAppliedFilter+"\")]//a"));
		waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator);
		sendKeys(text_ActionFilter_Expression, newFilterText);
		click(button_Apply);
		waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator);
	}
	
	public boolean performSearch(String searchVal){
		return performSearch(searchVal, null);
	}

	public boolean performUnsuccessfulSearch(String zoneName) {
		return performUnsuccessfulSearch(zoneName, null);
	}

	/**
	 * performUnsuccessfullSearch is a common method which checks that the search item is not found, and can be reused in all pages where the Apex Search feature is used
	 * @param searchVal Value you want to search
	 * @param colNum Don't provide column number blindly, see the effects, testing is required
	 * @return true if the Search Result Table doest not displays the searched value and displays No Data Found Message, else will return false after waiting for Constant.wait timeout
	 */
	public boolean performUnsuccessfulSearch(String searchVal, String colName) {
		selectColumnName_ProvideSearchVal_ClickGoButton(searchVal,colName);
		return waitForElementTobe_NotStaleAndPresent(data_NoDataFoundIcon,"No Data Found message for searched ["+searchVal+"]");
	}

	public boolean checkColumnFilterCanBeApplied(int row,int col,String data) {
		boolean bool=true;
		WebTable tab= new WebTable(table_ResultTab);
		//javaScript_ScrollIntoBOTTOMView(tab.getCellObject(row, col));
		click(tab.getChildObject(row, col, "a", 0));
		if (waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator)) {
			if(link_RolloverList.size()>=1){
				sendKeys( text_RolloverSearchField, data);
				String xpath="//a[@style='display: block;'][contains(normalize-space(.),'"+data+"')]";
				if(waitForElementTobe_NotVisible(By.xpath(xpath),1)){
					bool=false;
				}

			}
		}
		if (bool) {
			CustomReporter.report(STATUS.INFO, "Filter for data '"+data+"' can be applied on column: '"+tab.getCellText(row, col)+"'");
		} else {
			CustomReporter.report(STATUS.INFO, "Filter for data '"+data+"' can NOT be applied on column: '"+tab.getCellText(row, col)+"'");
		}
		return bool;
	}

	public boolean applyColumnFilter(WebTable tab,int row,int col,String data) {
		boolean bool=false;
		//javaScript_ScrollIntoBOTTOMView(tab.getChildObject(row, col, "a", 0));
		click(tab.getChildObject(row, col, "a", 0));
		if (waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator)) {
			if(link_RolloverList.size()>=1){
				sendKeys(text_RolloverSearchField, data);
				String xpath="//a[contains(@style,'block')][contains(normalize-space(.),'"+data+"')]";
				click(By.xpath(xpath));
				if (waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator)) {
					if(waitForElementsTobe_Present(By.xpath("//li[contains(.,"+Quotes.escape(data)+")]"))){
						bool=true;
					}
				}
			}
		}
		if (bool) {
			CustomReporter.report(STATUS.PASS, "Filter applied on column: '<b>"+tab.getCellText(row, col)+"</b>' with data : '<b>"+data+"</b>'");
		} else {
			CustomReporter.report(STATUS.FAIL, "Filter NOT applied on column: '<b>"+tab.getCellText(row, col)+"</b>' with data : '<b>"+data+"</b>'");
		}
		return bool;
	}

	
	public boolean applyColumnFilter(int row,int col,String data) {
		WebTable tab= new WebTable(table_ResultTab);
		return applyColumnFilter(tab, row, col, data);
	}

	public boolean removeReport(String reportName) {
		WebElement appliedFilterParent_Li=getDynamicElement(By.xpath("//li[contains(.,"+Quotes.escape(reportName)+")]"));
		if (appliedFilterParent_Li!=null) {
			WebElement appliedFilterChild_X_Button=getDynamicElement(appliedFilterParent_Li,By.cssSelector("button"));
			if (appliedFilterChild_X_Button!=null) {
				click(appliedFilterChild_X_Button);
				if (waitForElementTobe_Visible(button_Apply,10)) {
					click(button_Apply);
					if (waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator)) {
						CustomReporter.report(STATUS.PASS, "Report removed, containing text: '"+reportName+"'");
						return true;
					}
				}

			}
		}
		CustomReporter.report(STATUS.FAIL, "Report is NOT removed, containing text: '"+reportName+"'");
		return false;
	}



	public boolean removeAppliedColumnFilter(String filterVal) {
		WebElement appliedFilterParent_Li=getDynamicElement(By.xpath("//li[contains(.,"+Quotes.escape(filterVal)+")]"));
		if (appliedFilterParent_Li!=null) {
			WebElement appliedFilterChild_X_Button=getDynamicElement(appliedFilterParent_Li,By.cssSelector("button"));
			if (appliedFilterChild_X_Button!=null) {
				wait(.5);
				javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(appliedFilterChild_X_Button);
				if (waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator)) {
					CustomReporter.report(STATUS.PASS, "Filter removed, containing text: '"+filterVal+"'");
					return true;
				}

			}
		}
		CustomReporter.report(STATUS.FAIL, "Filter is NOT removed, containing text: '"+filterVal+"'");
		return false;
	}



	public boolean applyAggregateFunction(String function,String column){
		//javaScript_ScrollIntoBOTTOMView_AndHighlight(button_Action);
		click(button_Action);
		performAction(new Actions(DriverFactory.getDriver()).moveToElement(button_Format).build());
		wait(1);
		if(isElementPresent(button_Aggregate)){
			click(button_Aggregate);
			if(waitForElementTobe_Visible(button_Apply)){
				if(isElementPresent(select_Format_Function)){
					selectByVisibleText(select_Format_Function, function);
					selectByVisibleText(select_Format_Column, column);
					wait(1);
					click_UsingAction(button_Apply);
					wait(1);
					if(waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator)){
						
						// While running the scripts on jenkins, 
						// I encountered a problem where the popup to apply aggregate function 
						// was not getting removed. So adding this check 
						if(!waitForElementTobe_NotVisible(select_Format_Function, 1)){
							CustomReporter.report(STATUS.FAIL, "Function '" + function + "' FAILED to apply on column '"
									+ column + "', the popup of Apply Aggregate function is not removed from the view");
							
							// Also before returning false, I would like to close the popup using the cancel button
							click(getInteractableWebElementFromList(button_Cancel), "Cancel Button");
							
							return false;
						}
						
						CustomReporter.report(STATUS.PASS, "Function '"+function+"' applied on column '"+column+"'");
						return true;
					}
				}
			}
		}
		CustomReporter.report(STATUS.FAIL, "Function '"+function+"' failed to apply on column '"+column+"'");
		return false;
	}



	public boolean setRowsPerPage(String rowVal){
		selectByVisibleText(getInteractableWebElementFromList(select_RowsPerPage), rowVal, true);
		if(waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator)){
			CustomReporter.report(STATUS.PASS, "Rows are successfully set to: "+rowVal);
			return true;
		}
		CustomReporter.report(STATUS.FAIL, "Rows is NOT set to: "+rowVal);
		return false;
	}
	
	public boolean setRowsPerPage_FromActionPopup(String rows) {
		//javaScript_ScrollIntoBOTTOMView_AndHighlight(button_Action);
		click(button_Action);
		performAction(new Actions(DriverFactory.getDriver()).moveToElement(button_RowsPerPage).build());
		wait(1);
		if(isElementPresent(By.xpath("//button[.='"+rows+"']"))){
			click(By.xpath("//button[.='"+rows+"']"));
			waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator);
			CustomReporter.report(STATUS.PASS, "Process of Setting Row to ["+rows+"] is completed");
			return true;
		}
		CustomReporter.report(STATUS.FAIL, "Setting Row process failed");
		return false;
	}

	/**Works for a span of single year only*/
	public void selectTrafficPeriod(WebElement selectObj,String startTrafPeriod, String endTrafPeriod) {
		int counter=Integer.parseInt(Util.BD(endTrafPeriod).subtract(Util.BD(startTrafPeriod)).divide(Util.BD("100")).toPlainString())+1;
		for (int i = 0; i < counter; i++) {
			String temp=Util.BD(startTrafPeriod).add(Util.BD(i+"00")).toString();
			temp="0"+temp;
			String tpVal=temp.substring(temp.length()-4, temp.length());
			selectByPartialVisibleText_DoubleClick(selectObj, tpVal);
		}
	}

	public void selectMultipleValues_DoubleClick_FromArray(WebElement selectObj, TADIG[] arrValues) {
		for (int i = 0; i < arrValues.length; i++) {
			selectByPartialVisibleText_DoubleClick(selectObj, arrValues[i].value);
		}
	}

	public void selectMultipleValues_DoubleClick_FromArray(WebElement selectObj, ServiceType[] arrValues) {
		for (int i = 0; i < arrValues.length; i++) {
			selectByVisibleText_DoubleClick(selectObj, arrValues[i].value);
		}
	}

	public void selectMultipleValues_DoubleClick_FromArray(WebElement selectObj, EventType[] arrValues) {
		for (int i = 0; i < arrValues.length; i++) {
			selectByVisibleText_DoubleClick(selectObj, arrValues[i].value);
		}
	}

	public void selectMultipleValues_DoubleClick_FromArray(WebElement selectObj, TrafficDirection[] arrValues) {
		for (int i = 0; i < arrValues.length; i++) {
			selectByVisibleText_DoubleClick(selectObj, arrValues[i].value);
		}
	}

	public void selectMultipleValues_DoubleClick_FromArray(WebElement selectObj, Country[] arrValues) {
		for (int i = 0; i < arrValues.length; i++) {
			selectByVisibleText_DoubleClick(selectObj, arrValues[i].value);
		}
	}


	public boolean saveReport(String reportName, String reportDesc) {
		//javaScript_ScrollIntoBOTTOMView_AndHighlight(button_Action);
		click(button_Action);
		click(button_SaveReport);
		if(waitForElementTobe_Visible(text_Name_SaveReport)){
			sendKeys(text_Name_SaveReport, reportName);
			sendKeys(text_Description_SaveReport, reportDesc);
			click(button_Apply);
			if(waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator)){
				if (waitForElementTobe_Visible(By.xpath("//li[contains(.,'Saved Report =')]"), 5)) {
					CustomReporter.report(STATUS.PASS, "Report "+reportName+" is successfully saved");
					return true;
				}
			}
		}
		CustomReporter.report(STATUS.FAIL, "Report "+reportName+" is not saved");
		return false;
	}

	public boolean selectColumns_InReport(String columnsCommaSeparated) {
		//javaScript_ScrollIntoBOTTOMView_AndHighlight(button_Action);
		click(button_Action);
		wait(1);
		click(button_SelectColumns);
		if(waitForElementTobe_Visible(select_SelectColumns_DoNotDisplay)){
			selectByPartialVisibleText_DoubleClick_FromCommaSeparated(select_SelectColumns_DoNotDisplay, columnsCommaSeparated);
			click(button_Apply);
			wait(5);
			if(waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator)){
				CustomReporter.report(STATUS.PASS, "Process to add Columns ["+columnsCommaSeparated+"] in report is completed");
				return true;
			}
		}
		CustomReporter.report(STATUS.FAIL, "Process to add Columns [ "+columnsCommaSeparated+"] in report is failed");
		return false;
	}

	public String getSumOfColumnValues(int startRow, int colNum){
		WebTable tab= new WebTable(table_ResultTab);
		String sum_newColumn="0";
		int lastRow=tab.getRowCount();
		for (int row = startRow; row <= lastRow; row++) {
			String cellText=tab.getCellText(row, colNum);
			if(cellText.equals("-")){
				cellText="0";
			}
			sum_newColumn=Util.BD(cellText).add(Util.BD(sum_newColumn)).toPlainString();
		}
		return sum_newColumn;
	} 

	/**
	 * Fills the text in the GRID cell
	 * @author shailendra.rajawat 22-Mar-2019
	 * */
	public void fillGRIDcellText(WebTable grid, int rowNum, int colNum, String val) {
		try {
			
		
		click(grid.getCellObject(rowNum, colNum));
		//clear(getInteractableWebElementFromList(input_TextfieldVisible_GRID));
		//clear(switchTo_ActiveElement());
		DriverFactory.getDriver().switchTo().activeElement().clear();
		wait(.25);
		click(grid.getCellObject(rowNum, colNum));
		//sendKeys(getInteractableWebElementFromList(input_TextfieldVisible_GRID), val);
		//sendKeys(switchTo_ActiveElement(), val);
		DriverFactory.getDriver().switchTo().activeElement().sendKeys(val+Keys.TAB+Keys.TAB);
		wait(.25);
		
		} catch (Exception e) {
			new CustomExceptionHandler(e, "GRID " + grid + " ROW " + rowNum + " COL " + colNum + " VAL " + val );
		}
	}
	
	public boolean applyActionsFilter(String filterCol, String filterOperator, String filterExpression) {
		//javaScript_ScrollIntoBOTTOMView_AndHighlight(button_Action);
		click(button_Action);
		wait(1);
		if(isElementPresent(button_Filter)){
			click(button_Filter);
			if (waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator)) {
				selectByVisibleText(select_ActionFilter_Column, filterCol);
				wait(1);
				selectByVisibleText(select_ActionFilter_Operator, filterOperator);
				sendKeys(text_ActionFilter_Expression, filterExpression);
				click(button_Apply);
				if (waitForElementTobe_Visible(By.xpath("//li[contains(.,'"+filterCol+" "+filterOperator+"')]"))) {
					CustomReporter.report(STATUS.PASS, "Filter '"+filterCol+" "+filterOperator+" "+filterExpression+"' is successfully applied");
					return true;
				}
			}
		}
		CustomReporter.report(STATUS.FAIL, "Filter '"+filterCol+" "+filterOperator+" "+filterExpression+"' is failed to apply");
		return false;
	}

	public boolean applyComputation(String newColHeading, String function, String column) {
		//javaScript_ScrollIntoBOTTOMView_AndHighlight(button_Action);
		click(button_Action);
		performAction(new Actions(DriverFactory.getDriver()).moveToElement(button_Format).build());
		wait(1);
		if(isElementPresent(button_Compute)){
			click(button_Compute);
			if(waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator)){
				if(isElementPresent(text_ColumnHeading)){
					sendKeys(text_ColumnHeading, newColHeading);
					WebTable tab= new WebTable(By.cssSelector("table[id$='computation_columns']"));
					int lastRow=tab.getRowCount();
					String cellText="";
					for (int row = 1; row <= lastRow; row++) {
						cellText=tab.getCellText(row, 2);
						if(cellText.contains(column)){
							cellText=tab.getCellText(row, 1);
							cellText=cellText.substring(0, cellText.length()-1);
							break;
						}
					}


					sendKeys(text_ComputationExp, function+"("+cellText+")");
					click(button_Apply);

					if(waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator)){
						CustomReporter.report(STATUS.PASS, "Computation '"+function+"("+cellText+")' applied on column '"+column+"' and displayed in column '"+newColHeading+"'");
						return true;
					}
				}
			}
		}
		CustomReporter.report(STATUS.FAIL, "Computation '"+function+"("+column+")' failed to applied and displayed in column '"+newColHeading+"'");
		return false;
	}

	/** @return  list.add(column+":"+direction)*/
	public List<String> getSortingOrder_ColonSeparated() {
		List<String> list=new ArrayList<String>();
		//javaScript_ScrollIntoBOTTOMView_AndHighlight(button_Action);
		click(button_Action);
		performAction(new Actions(DriverFactory.getDriver()).moveToElement(button_Format).build());
		wait(.50);
		if(isElementPresent(button_Sort)){
			click(button_Sort);
			if(waitForElementTobe_Visible(button_Apply)){
				WebTable tab= new WebTable(By.xpath("//table[@class='a-IRR-dialogTable']"));
				int lastRow=tab.getRowCount();
				for (int row = 1; row <= lastRow; row++) {
					if(tab.getChildObjectsCount(row, 2, "select")>0){
						String column=getFirstSelectedOptionVisibleText(tab.getChildObject(row, 2, "select", 0));
						String direction=getFirstSelectedOptionVisibleText(tab.getChildObject(row, 3, "select", 0));
						list.add(column+":"+direction);
					}
				}

			}
		}
		return list;
	}

	public void verifyColumnHeaders(By byObj,List<String> expectedColNames,int headerRow){

		if(isElementPresent(byObj, "Result Table")){
			WebTable tab = new WebTable(byObj);
			boolean flag=true;
			List<String> actualColNames=new ArrayList<>();
			for (int i = 0; i < expectedColNames.size(); i++) {
				String tempColName=tab.getCellText(headerRow, i+1);
				actualColNames.add(tempColName);
				if(!expectedColNames.get(i).equalsIgnoreCase(tempColName)){
					CustomReporter.report(STATUS.FAIL, "Column '"+expectedColNames.get(i)+"' not found but column '"+tempColName+"' is getting displayed");
					flag=false;
				}
			}

			if (flag) {
				CustomReporter.report(STATUS.PASS, "All columns found : "+expectedColNames);
			}else{
				CustomReporter.report(STATUS.INFO, "COLUMNS EXPECTED: ["+getStringifiedColNames(expectedColNames)+"]");
				CustomReporter.report(STATUS.INFO, "COLUMNS ACTUAL : ["+getStringifiedColNames(actualColNames)+"]");
			}
		}
	}

	/**
	 * Returns the double quoted comma separated string
	 * i.e. if you pass an List with values => v1,v2,v3
	 * You will get => "v1", "v2", "v3"
	 * So that you can directly copy it from html report or console 
	 * and paste to Java Class file :)
	 * 
	 * @author shailendra.rajawat 07-May-2019
	 * */
	private String getStringifiedColNames(List<String> colNamesList){
		String colNames="";
		for (String temp: colNamesList) {
			colNames=colNames+", \""+temp+"\"";
		}
		return colNames.length()>1 ? colNames.substring(2) : colNames; 
	}
	
	public void verifyActionsPopupItems_WithRowsPerPage() {
		verifyActionsPopupItems_WithRowsPerPage(button_Action);
	}
	
	public void verifyActionsPopupItems_WithRowsPerPage(Object button_Action) {
		verifyActionsPopupItems_WithSubscription(button_Action);
		isElementPresent(button_RowsPerPage, "Apex Toolbar - Rows Per Page option");
	}
	
	public void verifyActionsPopupItems_WithSubscription() {
		verifyActionsPopupItems_WithSubscription(button_Action);
	}
	
	public void verifyActionsPopupItems_WithSubscription(Object button_Action) {
		verifyActionsPopupItems_WithoutSubscription(button_Action);
		isElementPresent(button_Subscription, "Apex Toolbar - Subscription Button");					
	}

	public void verifyActionsPopupItems_WithoutSubscription() {
		verifyActionsPopupItems_WithoutSubscription(button_Action);
	}
	
	/**
	 * Verifies the Action Popup items of GRID
	 * */
	public void verifyActionsPopupItems_GRID() {
		verifyActionsPopupItems_GRID(button_Action);
	}
	
	/**
	 * Verifies the Action Popup items of GRID
	 * */
	public void verifyActionsPopupItems_GRID(Object button_Action) {
		isElementPresent(button_Action, "Apex Toolbar - Actions button");
		javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_Action);
		isElementPresent(button_SelectColumns_GRID, "Apex Toolbar - Select Columns Button");
		isElementPresent(button_Filter, "Apex Toolbar - Filter Button");
		isElementPresent(button_Data_GRID, "Apex Toolbar - Data Button");
		isElementPresent(button_Format, "Apex Toolbar - Format Button");
		isElementPresent(button_Chart, "Apex Toolbar - Chart Button");
		isElementPresent(button_Report_GRID, "Apex Toolbar - Report Button");
		isElementPresent(button_Download, "Apex Toolbar - Download Button");
		isElementPresent(button_Help, "Apex Toolbar - Help Button");
	}
	
	public void verifyActionsPopupItems_WithoutSubscription(Object button_Action) {
		isElementPresent(button_Action, "Apex Toolbar - Actions button");
		javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_Action);
		isElementPresent(button_SelectColumns, "Apex Toolbar - Select Columns Button");
		isElementPresent(button_Filter, "Apex Toolbar - Filter Button");
		isElementPresent(button_Format, "Apex Toolbar - Format Button");
		isElementPresent(button_SaveReport, "Apex Toolbar - Save Report Button");
		isElementPresent(button_Reset, "Apex Toolbar - Reset Button");
		isElementPresent(button_Help, "Apex Toolbar - Help Button");
		isElementPresent(button_Download, "Apex Toolbar - Download Button");
	}

	
	public void printColumnHeaders(By tableLocator,int headerRow){
		WebTable tab = new WebTable(tableLocator);
		int lastCol=tab.getColCount(headerRow);
		String colNames="";
		for (int i = 1; i <= lastCol; i++) {
			colNames=colNames+",\""+tab.getCellText(headerRow, i)+"\"";
		}
		CustomReporter.report(STATUS.INFO, colNames.substring(1, colNames.length()));
		System.out.println(colNames.substring(1, colNames.length()));
	}
	

	public void downloadEMAIL(String mailTo,String subject) {
		//javaScript_ScrollIntoBOTTOMView_AndHighlight(button_Action);
		click(getInteractableWebElementFromList(button_Action));
		if(waitForElementTobe_Visible(getInteractableWebElementFromList(icon_Download))){
			click(getInteractableWebElementFromList(icon_Download));
			waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator);
				click(getInteractableWebElementFromList(icon_Download_Email),"Email icon");
				sendKeys("SUBJECT", getInteractableWebElementFromList(text_Subject), subject);
				sendKeys("TO", getInteractableWebElementFromList(text_To), mailTo);
				click(getInteractableWebElementFromList(button_Send),"Send button");
				waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator);
		}

	}
	
	public void downloadCSV() {
		//javaScript_ScrollIntoBOTTOMView_AndHighlight(button_Action);
		click(getInteractableWebElementFromList(button_Action));
		
		if(isElementPresent(icon_Download)){
			//TODO int lastItem=getDynamicElements(icon_Download);
			click(getInteractableWebElementFromList(icon_Download));
			waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator);
				click(getInteractableWebElementFromList(icon_Download_CSV),"CSV icon");
				wait(2);
				click(getInteractableWebElementFromList(button_Cancel));
		}
	}

	public void downloadHTML() {
		//javaScript_ScrollIntoBOTTOMView_AndHighlight(button_Action);
		click(getInteractableWebElementFromList(button_Action));
		if(isElementPresent(getInteractableWebElementFromList(icon_Download))){
			click(getInteractableWebElementFromList(icon_Download));
			waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator);
				click(getInteractableWebElementFromList(icon_Download_HTML),"HTML icon");
				wait(2);
				click(getInteractableWebElementFromList(button_Cancel));
		}
	}

	public void verifyValidationMessage(String data, String expected) {
		waitForElementsTobe_NotVisible(icon_SpinnerApex5LoadingIndicator);
		if(waitForElementTobe_Visible(data_ValidationMessagePopup, 5)){
			String msg=getText(data_ValidationMessagePopup);
			if(msg.toLowerCase().trim().contains(expected.toLowerCase().trim())){
				CustomReporter.report(STATUS.PASS, "Validation for ["+expected+"] is successfully displayed for data ["+data+"]");
			}else {
				CustomReporter.report(STATUS.FAIL, "Validation for ["+expected+"] is NOT displayed for data ["+data+"]");
			}
		}
	}
	
	/**
	 * Precondition- Apex success Alert should be visible with a close button 
	 * Postcondition- Click on close button and will close the alert
	 * @author shailendra.rajawat 
	 * */
	public void closeApexAlert_SuccessMessagePopup(String withText){
		if(!waitForElementTobe_NotVisible(By.xpath("//h2[contains(.,'"+withText+"')]"), 1)){
			click(By.xpath("//div[contains(@class,'Alert')][contains(.,'"+withText+"')]//button"),"Close icon of Apex Alert["+withText+"]");
		}
	}

}