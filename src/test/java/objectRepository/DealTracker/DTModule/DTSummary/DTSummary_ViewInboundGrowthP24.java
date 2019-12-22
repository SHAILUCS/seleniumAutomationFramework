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
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.common.ApexCommon;
import tests.MainRegression.KPN_DealTracker7216;

public class DTSummary_ViewInboundGrowthP24 extends DTSummary_MarketGrowth {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "View Inbound Growth";

	public DTSummary_ViewInboundGrowthP24() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	public DTSummary_ViewInboundGrowthP24 navigateTo_ViewInboundGrowth() {
		com.click(button_ViewInboundGrowth_CountryLevel, "View Inbound Growth_Country Level button");
		if (com.verifyPageTitle(title, true)) {
			return this;
		}
		return null;
	}

	@FindBy(xpath = "//h1[contains(.,'View Inbound Growth')]")
	private WebElement data_ViewInboundGrowth_Breadcrumb;

	@FindBy(xpath = "//h2[contains(.,'Parameters')]")
	private WebElement data_Parameters_Section;

	@FindBy(id = "P24_TRAFFIC_DIRECTION_LEFT")
	private WebElement select_TrafficDirection_Left;

	@FindBy(id = "P24_TRAFFIC_DIRECTION_RIGHT")
	private WebElement select_TrafficDirection_Right;

	@FindBy(id = "P24_COUNTRY_ID_LEFT")
	private WebElement select_Country_Left;

	@FindBy(id = "P24_COUNTRY_ID_RIGHT")
	private WebElement select_Country_Right;

	@FindBy(id = "P24_SERVICE_TYPE_ID_LEFT")
	private WebElement select_ServiceType_Left;

	@FindBy(id = "P24_SERVICE_TYPE_ID_RIGHT")
	private WebElement select_ServiceType_Right;

	@FindBy(id = "P24_EVENT_TYPE_ID_LEFT")
	private WebElement select_EventType_Left;

	@FindBy(id = "P24_EVENT_TYPE_ID_RIGHT")
	private WebElement select_EventType_Right;

	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	private WebElement button_Refresh;

	@FindBy(xpath = "//button[contains(.,'Cancel')]") 
	public WebElement button_Cancel;

	@FindBy(xpath = "//h2[contains(.,'Inbound Market Growth')]")
	private WebElement data_InboundMarketGrowth_Section;

	private By tab = By.cssSelector("table[summary='Inbound Market Growth']");

	private List<String> colNames = new ArrayList<>(Arrays.asList("Country Name",
			"Traffic Direction", "Service Type", "Event Type", "Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18",
			"Jun 18", "Jul 18", "Aug 18", "Sep 18", "Oct 18", "Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19",
			"Apr 19", "May 19", "Jun 19", "Jul 19", "Aug 19", "Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download")
	private WebElement link_Download;

	@FindBy(xpath = "//table[@summary='Inbound Market Growth']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination;

	public void verify_UI() {
		CustomReporter.report(STATUS.INFO, "Verious Section headers verification");
		com.isElementPresent(data_ViewInboundGrowth_Breadcrumb, "View Inbound Growth breadcrumb");
		com.isElementPresent(data_Parameters_Section, "Parameters Section");
		com.isElementPresent(data_InboundMarketGrowth_Section, "Inbound Market Growth Section");

		CustomReporter.report(STATUS.INFO, "Parameter Section fields verification");
		com.isElementPresent(select_TrafficDirection_Left, "Traffic Direction - Left shuttle");
		com.isElementPresent(select_TrafficDirection_Right, "Traffic Direction - Right shuttle");
		com.isElementPresent(select_Country_Left, "Country - Left shuttle");
		com.isElementPresent(select_Country_Right, "Country - Right shuttle");
		com.isElementPresent(select_ServiceType_Left, "Service Type - Left shuttle");
		com.isElementPresent(select_ServiceType_Right, "Service Type - Right shuttle");
		com.isElementPresent(select_EventType_Left, "Event Type - Left shuttle");
		com.isElementPresent(select_EventType_Right, "Event Type - Right shuttle");
		com.isElementPresent(button_Refresh, "Refresh Button");
		com.isElementPresent(button_Cancel, "Cancel Button");

		CustomReporter.report(STATUS.INFO, "Market Growth Section fields verification");

		//comm.printColumnHeaders(tab, 1);
		comm.verifyColumnHeaders(tab, colNames, 1);

		com.isElementPresent(link_Download, "Download Link");
		com.isElementPresent(data_Pagination, "Pagination data "+com.getText(data_Pagination));
	}

	public String getPassedColumnGrowth(String colName) {
		String temp = "0";
		WebTable tabEditOp=new WebTable(tab);
		tabEditOp.initHeaderColumnList(1);
		int colNum=tabEditOp.getColumnNumber(colName);
		temp=Util.removeCommas(tabEditOp.getCellText(2, colNum));
		CustomReporter.report(STATUS.INFO, "For ["+ colName +"] Inbound Country Growth value is "+ temp);
		return temp;
	}
	
	public void ForecastCountryLevelGrowthEnableViewOptionForIBTrafficFE_7230() {
		/*
		 * 1.Select Home Operator Home Operator: NLDPT 2.Select Traffic
		 * Direction, country, services, events
		 * 
		 * Traffic Direction :- select both direction Country :- Austria Service
		 * Type:- All Event Type :- All"
		 */
		
		JSONManager json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7230","InboundAllServices");
		performSearch(json);
		
		CustomReporter.createNode("Navigating to View Inbound Growth");
		com.navigateToAndVerifyPageTitle(button_ViewInboundGrowth, title);
		
		CustomReporter.createNode("Inbound Market Growth Table data");
		
		VerifyServicesOnTable(tab,"Inbound Market Growth Table");
		
		CustomReporter.createNode("Inbound Country Level csv");
		
		com.click(link_Download, "Download link");
		String FilePath = Util.getDownloadedFilePath("Inbound Country Level");
		if (FilePath != null) {
			CustomReporter.report(STATUS.PASS, "File Downloaded Successfully "+FilePath);
			CsvContentVerificationLoop(tab, FilePath);
		} else {
			CustomReporter.report(STATUS.FAIL, "File Not Downloaded");
		}
		
	}
}
