package objectRepository.Operations;

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
import common.seleniumExceptionHandling.WebTable;
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;

public class DeltaReportAdministration_P10 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.DeltaReportAdministration_P10.title;

	public DeltaReportAdministration_P10() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	private List<String> colNames = new ArrayList<>(Arrays.asList());

	public void verify_UI() {

	}

	@FindBy(id = "P10_REPORT_NAME")
	private WebElement text_reportName;
	
	@FindBy(xpath = "//button[.='Save Report']")
	private WebElement button_SaveReport;
	
	public boolean saveReport(String reportName) {
		com.sendKeys(text_reportName, reportName);
		com.click(button_SaveReport);
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator,600);
		
		WebTable tab=new WebTable(comm.table_ResultTabData);
		int row=tab.getRowWithCellText(reportName);
		if (row!=-1) {
			CustomReporter.report(STATUS.PASS, "Report freezed and saved with Name [<b>"+tab.getCellText(row, 2)+"</b>]");
			com.click(tab.getChildObject(row, 1, "img", 0));
			com.verifyPageTitle("Freezed Report", true);
			return true;
		}else{
			CustomReporter.report(STATUS.FAIL, "Report freeze failed");
			return false;
		}
	}

	

}
