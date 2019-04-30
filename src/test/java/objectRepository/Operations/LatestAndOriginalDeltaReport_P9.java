package objectRepository.Operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.Constant;
import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;

public class LatestAndOriginalDeltaReport_P9 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.LatestAndOriginalDeltaReport_P9.title;

	public LatestAndOriginalDeltaReport_P9() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	private List<String> colNames = new ArrayList<>(Arrays.asList());

	public void verify_UI() {

	}

	@FindBy(id = "P9_SELECT_SET")
	private WebElement select_Set;

	@FindBy(xpath = "//button[.='Refresh Report']")
	private WebElement button_RefreshReport;

	@FindBy(xpath = "//button[.='Freeze Report']")
	private WebElement button_FreezeReport;

	public void CheckDeltaInLatestAndOriginal(String setName) {
		Constant.enableCaptureSnapshots = false;
		CustomReporter.createNode("Check Delta In Latest And Original");
		if(performSearch(setName)){
			CustomReporter.report(STATUS.FAIL, "Data displayed in Delta report, Calculation engine has some differences");

			CustomReporter.report(STATUS.INFO, "FREEZING the report, for further references");
			
			String freezeReportName="Automation Regression Test"+Util.getTimeStamp_InMilliSec();
			if(freezeReport(freezeReportName)){

				comm.setRowsPerPage_FromActionPopup("All");

				WebTable tabHead=new WebTable(comm.table_ResultTabHeader);

				int startColNum=tabHead.initHeaderColumnList(1).getColumnNumber("Traffic volume cum pp");
				int lastColNum=tabHead.getColCount(1);

				String headerRow=getStartingCol_RowData(tabHead,1);
				int rowCount=com.getDynamicElements(By.xpath("//div[contains(@class,'body')]/table[@class='a-IRR-table']//tr")).size();
				for (int row = 2; row <= rowCount; row++) {

					for (int col = startColNum; col <= lastColNum; col++) {

						String cellText=com.getText(By.xpath("//div[contains(@class,'body')]/table[@class='a-IRR-table']//tr["+row+"]/td["+col+"]"));

						double cellNum=0;
						try{
							cellNum=Double.parseDouble(cellText);
						}catch (Exception e) {}

						if (cellNum>0 || cellNum<0) {
							String childRow=getStartingCol_RowData(row);
							String table="Row = ["+row+"]"
									+"<table border=1>"
									+ headerRow
									+ childRow
									+ "</table>";
							CustomReporter.report(STATUS.INFO,  table);
							break;
						}

					}

				}
			}
		}else{
			CustomReporter.report(STATUS.PASS, "No Data displayed, in Delta report, Calculation engine is properly working for Set ["+setName+"]");
		}
		Constant.enableCaptureSnapshots = true;
	}	

	private boolean freezeReport(String reportName) {
		com.click(button_FreezeReport,"button_FreezeReport");
		com.verifyPageTitle(DeltaReportAdministration_P10.title, true);
		return new DeltaReportAdministration_P10().saveReport(reportName);
	}

	private String getStartingCol_RowData(WebTable tab, int row) {
		String cellByCellOuterHTML="";
		for (int col = 1; col <= 9; col++) {
			cellByCellOuterHTML+="<td>"+tab.getCellText(row, col)+"</td>";
		}

		return "<tr>"
				+ cellByCellOuterHTML
				+ "</tr>";
	}


	private String getStartingCol_RowData(int row) {
		String cellByCellOuterHTML="";
		for (int col = 1; col <= 9; col++) {
			cellByCellOuterHTML += "<td>" + com.getText(By.xpath(
					"//div[contains(@class,'body')]/table[@class='a-IRR-table']//tr[" + row + "]/td[" + col + "]"))
			+ "</td>";
		}

		return "<tr>"
		+ cellByCellOuterHTML
		+ "</tr>";
	}

	private boolean performSearch(String setName) {

		com.selectByVisibleText(select_Set, setName);

		com.click(button_RefreshReport);

		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);

		if(com.waitForElementTobe_NotVisible(comm.data_NoDataFoundIcon,0)){
			return true;
		}else{
			return false;
		}

	}


}
