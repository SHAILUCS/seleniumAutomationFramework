package objectRepository.DealTracker.DTModule;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;

public class DataLoading {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Data Loading";

	public DataLoading() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Data Loading')]")
	private WebElement data_DataLoading_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Upload File')]")
	private WebElement data_UploadFile_Section;

	@FindBy(id = "P13_FILE")
	private WebElement file_Upload;
	
	@FindBy(id = "P13_UPLOAD")
	private WebElement button_Upload;
	
	@FindBy(xpath = "//h2[contains(.,'Parameters')]")
	private WebElement data_Parameters_Section;

	@FindBy(id = "P13_DATE_FROM")
	private WebElement text_DateFrom;
	
	@FindBy(id = "P13_DATE_TO")
	private WebElement text_DateTo;
	
	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	private WebElement button_Refresh;
	
	public void verify_UI() {
		com.isElementPresent(data_DataLoading_BreadCrumb,
				"Data Loading heading in Bread Crumb");
		CustomReporter.report(STATUS.INFO, "Upload Section verification");
		com.isElementPresent(data_UploadFile_Section, "Upload File Section");
		com.isElementPresent(file_Upload, "Upload File field");
		com.isElementPresent(button_Upload, "Upload button");
		
		
		CustomReporter.report(STATUS.INFO, "Parameters Section verification");
		com.isElementPresent(data_Parameters_Section, "Parameters Section");
		com.isElementPresent(text_DateFrom, "Date From field");
		com.isElementPresent(text_DateTo, "Date To field");
		com.isElementPresent(button_Refresh, "Refresh button");
		
		com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
		com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
		com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
		comm.verifyActionsPopupItems_WithoutSubscription();
		
		
	}
}
