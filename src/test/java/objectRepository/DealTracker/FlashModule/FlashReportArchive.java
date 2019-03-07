package objectRepository.DealTracker.FlashModule;

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

public class FlashReportArchive {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Flash report archive";

	public FlashReportArchive() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Flash report archive')]")
	private WebElement data_FlashReportArchive_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Archive')]")
	private WebElement data_Archive_Section;
	
	private List<String> colNames = new ArrayList<>(Arrays.asList("","","Report Name","Create Date","Created By","Note"));
	
	@FindBy(xpath = "//span[contains(.,'1 - ')]")
	private WebElement data_Pagination;
	

	public void verify_UI() {
		com.isElementPresent(data_FlashReportArchive_BreadCrumb, "Flash Report Archive Bread Crumb");
		
		CustomReporter.report(STATUS.INFO, "Archive Section verification");
		com.isElementPresent(data_Archive_Section, "Archive Section heading");
		com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
		com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
		com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
		comm.verifyActionsPopupItems_WithoutSubscription();
		
		//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
		comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);
		
		com.isElementPresent(data_Pagination, "Pagination data "+com.getText(data_Pagination));
		
	}

	

}
