package objectRepository.DealTracker.DTModule.DTSummary;

import java.math.RoundingMode;
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

public class DTSummary_EditMarketShareP7 extends DTSummary_MarketShareProportionalShare{
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Edit Market Share";

	public DTSummary_EditMarketShareP7() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Edit Market Share')]")
	private WebElement data_EditMarketShare_Breadcrumb;

	@FindBy(xpath = "//h2[contains(.,'Parameters')]")
	private WebElement data_Parameters_Section;

	@FindBy(id = "P7_TRAFFIC_DIRECTION_LEFT")
	private WebElement select_TrafficDirection_Left;

	@FindBy(id = "P7_TRAFFIC_DIRECTION_RIGHT")
	private WebElement select_TrafficDirection_Right;

	@FindBy(id = "P7_COUNTRY_ID_LEFT")
	private WebElement select_Country_Left;

	@FindBy(id = "P7_COUNTRY_ID_RIGHT")
	private WebElement select_Country_Right;

	@FindBy(id = "P7_SERVICE_TYPE_ID_LEFT")
	private WebElement select_ServiceType_Left;

	@FindBy(id = "P7_SERVICE_TYPE_ID_RIGHT")
	private WebElement select_ServiceType_Right;

	@FindBy(id = "P7_EVENT_TYPE_ID_LEFT")
	private WebElement select_EventType_Left;

	@FindBy(id = "P7_EVENT_TYPE_ID_RIGHT")
	private WebElement select_EventType_Right;

	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	private WebElement button_Refresh;

	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	private WebElement button_Cancel;

	@FindBy(xpath = "//h2[contains(.,'Market Share')]")
	private WebElement data_MarketShare_Section;

	@FindBy(id = "P7_NOTE")
	private WebElement text_Note;

	@FindBy(xpath = "//button[contains(.,'Save Changes to Monthly Market Share')]")
	private WebElement button_SaveChangesToMonthlyMarketShare;

	private By tab = By.cssSelector("table[summary='Market Share']");

	private List<String> colNames = new ArrayList<>(Arrays.asList("Roaming Partner", "Partner PMN", "Country Name",
			"Traffic Direction", "Service Type", "Event Type", "Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18",
			"Jun 18", "Jul 18", "Aug 18", "Sep 18", "Oct 18", "Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19",
			"Apr 19", "May 19", "Jun 19", "Jul 19", "Aug 19", "Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download")
	private WebElement link_Download;

	@FindBy(xpath = "//table[@summary='Market Share']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination;
	
	public void verify_UI() {
		CustomReporter.createNode("Verious Section headers verification");
		com.isElementPresent(data_EditMarketShare_Breadcrumb, "Edit Market Share breadcrumb");
		com.isElementPresent(data_Parameters_Section, "Parameters Section");
		com.isElementPresent(data_MarketShare_Section, "Market Share Section");

		CustomReporter.createNode("Parameter Section fields verification");
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

		CustomReporter.createNode("Market Share Section fields verification");
		com.isElementPresent(text_Note, "Note field");
		com.isElementPresent(button_SaveChangesToMonthlyMarketShare, "Save Changes To Monthly Market Share");

		//comm.printColumnHeaders(tab, 1);
		comm.verifyColumnHeaders(tab, colNames, 1);

		com.isElementPresent(link_Download, "Download Link");
		com.isElementPresent(data_Pagination, "Pagination data "+com.getText(data_Pagination));
	}

	public DTSummary_EditMarketShareP7 navigateTo_EditMarketShare() {
		com.navigateToAndVerifyPageTitle(button_EditMonthlyMarketShare, title);
		return new DTSummary_EditMarketShareP7();
	}
	
	public void checkMarketShareForTheMonthApril18_7218(String apr18, List<String> futureMonthColRowWiseList_TrafVol) {
		
		navigateTo_EditMarketShare();
		
		WebTable table= new WebTable(tab);
		table.initHeaderColumnList(1);
		
		int lastRow=table.getRowCount();
		int futureMonthCol=table.getColumnNumber(apr18);
		
		List<String> marketShareListOnPage=new ArrayList<String>();
		for (int row = 2; row < lastRow; row++) {
			String tempVal="";
			if(table.getChildObjectsCount(row, futureMonthCol, "input")>0){
				tempVal=com.getAttribute(table.getChildObject(row, futureMonthCol,"input",0),"value");
			}else{
				tempVal=table.getCellText(row, futureMonthCol);
			}
			marketShareListOnPage.add(Util.Round(tempVal,5));
		}
		CustomReporter.report(STATUS.INFO, "Market Share for ["+apr18+"] On Page "+marketShareListOnPage);
		
		List<String> marketShareListCalc=new ArrayList<String>();
		int listSize=futureMonthColRowWiseList_TrafVol.size();
		for (int i = 0; i < listSize-1; i++) {
			String percentVal="0";
			try{
				percentVal=Util.BD(futureMonthColRowWiseList_TrafVol.get(i)).divide(Util.BD(futureMonthColRowWiseList_TrafVol.get(listSize-1)),7,RoundingMode.HALF_EVEN).multiply(Util.BD("100")).toPlainString();
			}catch (Exception e) {}
			marketShareListCalc.add(Util.Round(percentVal,5));
		}
		CustomReporter.report(STATUS.INFO, "Market Share for ["+apr18+"] Calculated "+marketShareListCalc);
		
		Util.comparator_List(marketShareListCalc, marketShareListOnPage, "Market Share");
		com.click(button_Cancel);
	}

	

}
