package objectRepository.DealTracker.DTModule.DTSummary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.common.ApexCommon;

public class DTSummary_Charges extends DTSummary_Header {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = DTSummary_Header.title;

	public DTSummary_Charges() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h2[contains(.,'Charges')]")
	private WebElement data_Charges_Section;

	public void verify_UI() {
		com.isElementPresent(data_Charges_Section, "Charges - Main Section heading");
		verifySection_BulkUpdateTAPRate();
		verifySection_TAPRate();
		verifySection_TAPCharge();

	}

	@FindBy(xpath = "//h2[contains(.,'Bulk Update TAP Rate')]")
	private WebElement data_BulkUpdateTAPRate_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Bulk Update TAP Rate')]//button[contains(@class,'hideShow')]")
	private WebElement icon_Section_BulkUpdateTAPRate_HideShow;

	@FindBy(id = "P4_BULK_TAP_RATE")
	private WebElement text_BulkUpdateTAPRate;

	@FindBy(id = "P4_EFFECTIVE_FROM")
	private WebElement text_EffectiveFrom_BulkUpdateTAPRate;

	@FindBy(id = "P4_NOTE_TAP_RATE")
	private WebElement text_Note_BulkUpdateTAPRate;

	@FindBy(xpath = "//button[contains(.,'Bulk Update TAP Rate')]")
	private WebElement button_BulkUpdateTAPRate;

	private void verifySection_BulkUpdateTAPRate() {
		CustomReporter.report(STATUS.INFO, "Bulk Update Forecast Market Share Sub-Section verification");
		com.isElementPresent(data_BulkUpdateTAPRate_Section, "Bulk Update TAP Rate Sub-Section heading");
		com.isElementPresent(icon_Section_BulkUpdateTAPRate_HideShow,
				"Bulk Update TAP Rate Sub-Section Hide/Show icon");

		com.click_UsingAction(icon_Section_BulkUpdateTAPRate_HideShow);
		com.isElementPresent(text_BulkUpdateTAPRate, "Bulk Update TAP Rate field");
		com.isElementPresent(text_EffectiveFrom_BulkUpdateTAPRate, "Effective From field");
		com.isElementPresent(text_Note_BulkUpdateTAPRate, "Note field");
		com.isElementPresent(button_BulkUpdateTAPRate, "Bulk Update TAP Rate button");
	}

	@FindBy(xpath = "//h2[contains(.,'TAP Rate')]")
	private WebElement data_TAPRate_Section;

	@FindBy(xpath = "//button[contains(.,'Edit Tap Rate')]")
	protected WebElement button_EditTapRate;

	private By table_TAPRate = By.cssSelector("table[summary='TAP Rate']");

	private List<String> colNames_TAPRate = new ArrayList<>(
			Arrays.asList("Roaming Partner", "Partner PMN", "Country Name", "Traffic Direction", "Service Type",
					"Event Type", "Average 2018 TAP Rate", "Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18", "Jun 18",
					"Jul 18", "Aug 18", "Sep 18", "Oct 18", "Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19", "Apr 19",
					"May 19", "Jun 19", "Jul 19", "Aug 19", "Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download TAP Rate")
	private WebElement link_DownloadTAPRate;

	@FindBy(xpath = "//table[@summary='TAP Rate']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination_TAPRate;

	
	private void verifySection_TAPRate() {
		CustomReporter.report(STATUS.INFO, "TAP Rate Sub-Section verification");
		com.isElementPresent(data_TAPRate_Section, "TAP Rate Sub-Section heading");
		com.isElementPresent(button_EditTapRate, "Edit TAP Rate button");

		comm.printColumnHeaders(table_TAPRate, 1);
		comm.verifyColumnHeaders(table_TAPRate, colNames_TAPRate, 1);

		com.isElementPresent(link_DownloadTAPRate, "Download TAP Rate link");
		
		com.isElementPresent(data_Pagination_TAPRate, "Pagination data "+com.getText(data_Pagination_TAPRate));
	}

	@FindBy(xpath = "//h2[contains(.,'TAP Charge')]")
	private WebElement data_BulkUpdateProportionalMarketShare_Section;

	private By table_TAPCharge = By.cssSelector("table[summary='TAP Charge']");

	private List<String> colNames_TAPCharge = new ArrayList<>(
			Arrays.asList("Roaming Partner", "Partner PMN", "Country Name", "Traffic Direction", "Service Type",
					"Event Type", "Total 2018 TAP Charge", "Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18", "Jun 18",
					"Jul 18", "Aug 18", "Sep 18", "Oct 18", "Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19", "Apr 19",
					"May 19", "Jun 19", "Jul 19", "Aug 19", "Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download TAP Charge")
	private WebElement link_DownloadTAPCharge;

	@FindBy(xpath = "//table[@summary='TAP Charge']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination_TAPCharge ;

	private void verifySection_TAPCharge() {
		CustomReporter.report(STATUS.INFO, "Bulk Update Forecast Operator Level Growth Sub-Section verification");
		com.isElementPresent(data_BulkUpdateProportionalMarketShare_Section, "TAP Charge Sub-Section heading");
		comm.printColumnHeaders(table_TAPCharge, 1);
		comm.verifyColumnHeaders(table_TAPCharge, colNames_TAPCharge, 1);

		com.isElementPresent(link_DownloadTAPCharge, "Download TAP Charge link");
		
		com.isElementPresent(data_Pagination_TAPCharge, "Pagination data "+com.getText(data_Pagination_TAPCharge));
	}

	public List<String> getTapRateForPassedColumn(String colName) {
		List<String> list=new ArrayList<String>();
		WebTable table= new WebTable(table_TAPRate);
		table.initHeaderColumnList(1);
		for (int row = 2; row <= table.getRowCount(); row++) {
			list.add(table.getCellText(row, table.getColumnNumber(colName)));
		}
		CustomReporter.report(STATUS.INFO, "TAP Rate for passed column [" + colName + "] : " + list);
		return list;
	}

	public List<String> getTapChargeForPassedColumn(String colName) {
		List<String> list=new ArrayList<String>();
		WebTable table= new WebTable(table_TAPCharge);
		table.initHeaderColumnList(1);
		
		By sort=By.xpath("//table[@summary='TAP Charge']//th[.='Roaming Partner']//span[contains(@class,'sortIcon')][contains(@class,'asc')]");
		if(com.waitForElementTobe_NotVisible(sort,1))
		{
			com.click(table.getChildObject(1, 1, "a", 0));
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			com.waitForElementTobe_Visible(sort);
		}
		for (int row = 2; row <= table.getRowCount(); row++) {
			list.add(Util.removeCommas(table.getCellText(row, table.getColumnNumber(colName))));
		}
		CustomReporter.report(STATUS.INFO, "TAP Charge for passed column [" + colName + "] : " + list);
		return list;
	}

	public List<String> getTapRateForPassedOperator(String operatorName, String startColName,
			String endColName) {
		List<String> temp=new ArrayList<String>();
		WebTable table= new WebTable(table_TAPRate);
		int startColNum=table.initHeaderColumnList(1).getColumnNumber(startColName);
		int endColNum=table.getColumnNumber(endColName);
		int desiredRow=table.getRowWithCellText(operatorName);
		
		for (int i = startColNum; i <= endColNum; i++) {
			int inputObjCount=table.getChildObjectsCount(desiredRow, i, "input");
			if (inputObjCount>0) {
				temp.add(Util.removeCommas(com.getAttribute(table.getChildObject(desiredRow, i, "input",0),"value")));
			}else{
				temp.add(Util.removeCommas(table.getCellText(desiredRow, i)));
			}
			
		}
		
		CustomReporter.report(STATUS.INFO, "TAP Rate values for operator ["+ operatorName +"] Row and columns ["+startColName+ "] to ["+ endColName +"], are "+ temp);
		return temp;
	}

	public List<String> getTapChargeForPassedOperator(String operatorName, String startColName,
			String endColName) {
		List<String> temp=new ArrayList<String>();
		WebTable table= new WebTable(table_TAPCharge);
		int startColNum=table.initHeaderColumnList(1).getColumnNumber(startColName);
		int endColNum=table.getColumnNumber(endColName);
		int desiredRow=table.getRowWithCellText(operatorName);
		
		for (int i = startColNum; i <= endColNum; i++) {
			
			int inputObjCount=table.getChildObjectsCount(desiredRow, i, "input");
			if (inputObjCount>0) {
				temp.add(Util.removeCommas(com.getAttribute(table.getChildObject(desiredRow, i, "input",0),"value")));
			}else{
				temp.add(Util.removeCommas(table.getCellText(desiredRow, i)));
			}
			
		}
		
		CustomReporter.report(STATUS.INFO, "TAP Charge values For operator ["+ operatorName +"] Row, and columns ["+startColName+ "] to ["+ endColName +"] are "+ temp);
		return temp;
	}

}
