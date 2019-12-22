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

public class DTSummary_EditMarketVolumeP5 extends DTSummary_MarketVolume{
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Edit Market Volume";

	public DTSummary_EditMarketVolumeP5() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Edit Market Volume')]")
	private WebElement data_EditMarketVolume_Breadcrumb;

	@FindBy(xpath = "//h2[contains(.,'Parameters')]")
	private WebElement data_Parameters_Section;

	@FindBy(id = "P5_TRAFFIC_DIRECTION_LEFT")
	private WebElement select_TrafficDirection_Left;

	@FindBy(id = "P5_TRAFFIC_DIRECTION_RIGHT")
	private WebElement select_TrafficDirection_Right;

	@FindBy(id = "P5_COUNTRY_ID_LEFT")
	private WebElement select_Country_Left;

	@FindBy(id = "P5_COUNTRY_ID_RIGHT")
	private WebElement select_Country_Right;

	@FindBy(id = "P5_SERVICE_TYPE_ID_LEFT")
	private WebElement select_ServiceType_Left;

	@FindBy(id = "P5_SERVICE_TYPE_ID_RIGHT")
	private WebElement select_ServiceType_Right;

	@FindBy(id = "P5_EVENT_TYPE_ID_LEFT")
	private WebElement select_EventType_Left;

	@FindBy(id = "P5_EVENT_TYPE_ID_RIGHT")
	private WebElement select_EventType_Right;

	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	private WebElement button_Refresh;

	@FindBy(xpath = "//button[contains(.,'Cancel')]") 
	public WebElement button_Cancel;

	@FindBy(xpath = "//h2[contains(.,'Traffic Volume')]")
	private WebElement data_TrafficVolume_Section;

	@FindBy(id = "P5_NOTE")
	private WebElement text_Note;
	
	@FindBy(id = "P5_CHANGE_TYPE")
	private WebElement select_Reason;
	

	@FindBy(xpath = "//button[contains(.,'Save Changes to Monthly Volume')]")
	private WebElement button_SaveChangesToMonthlyVolume;

	private By tab = By.cssSelector("table[summary='Traffic Volumes']");

	private List<String> colNames = new ArrayList<>(Arrays.asList("Roaming Partner", "Partner PMN", "Country Name",
			"Traffic Direction", "Service Type", "Event Type", "Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18",
			"Jun 18", "Jul 18", "Aug 18", "Sep 18", "Oct 18", "Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19",
			"Apr 19", "May 19", "Jun 19", "Jul 19", "Aug 19", "Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download")
	private WebElement link_Download;

	@FindBy(xpath = "//table[@summary='Traffic Volumes']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination;
	
	public void verify_UI() {
		CustomReporter.createNode("Verious Section headers verification");
		com.isElementPresent(data_EditMarketVolume_Breadcrumb, "Edit Market Volume breadcrumb");
		com.isElementPresent(data_Parameters_Section, "Parameters Section");
		com.isElementPresent(data_TrafficVolume_Section, "Market Volume Section");

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

		CustomReporter.createNode("Market Volume Section fields verification");
		com.isElementPresent(text_Note, "Note field");
		com.isElementPresent(select_Reason, "Reason dropdown");
		com.isElementPresent(button_SaveChangesToMonthlyVolume, "Save Changes To Monthly Volume button");

		//comm.printColumnHeaders(tab, 1);
		comm.verifyColumnHeaders(tab, colNames, 1);

		com.isElementPresent(link_Download, "Download Link");
		com.isElementPresent(data_Pagination, "Pagination data "+com.getText(data_Pagination));
	}

	public List<String> getPassedColumnList(String columnName) {
		List<String> temp=new ArrayList<String>();
		WebTable tabTrafVol= new WebTable(tab);
		tabTrafVol.initHeaderColumnList(1);
		int colNum=tabTrafVol.getColumnNumber(columnName);
		for (int row = 2; row <= tabTrafVol.getRowCount(); row++) {
			int inputObjCount=tabTrafVol.getChildObjectsCount(row, colNum, "input");
			if (inputObjCount>0) {
				temp.add(Util.removeCommas(com.getAttribute(tabTrafVol.getChildObject(row, colNum, "input",0),"value")));
			}else{
				temp.add(Util.removeCommas(tabTrafVol.getCellText(row, colNum)));
			}
		}
		CustomReporter.report(STATUS.INFO, "Traffic Volume List for passed column ["+columnName+"] : "+temp);
		return temp;
	}

	public List<String> checkMarketVolumeAtMarketVolumeReportForApril18_7218(String pastMonthCol,String futureMonthCol,String futureMonthForecastVal) {
		List<String> futureMonthColRowWiseList=new ArrayList<String>();
		
		NAVIGATE_To_EditMarketVolume();
		
		//TODO QUICK FIXES WILL CHANGE AFTER DEFECT IS FIXED(Added refresh button click)
		com.click(button_Refresh, "Refresh Button");
		
		String apr18TrafVolSum=getSumRowValues(futureMonthCol,futureMonthCol).get(0);
		String apr17TrafVolSum=getSumRowValues(pastMonthCol,pastMonthCol).get(0);
		
		/*
		 * "Use Formula = Market Volume for previous year for same month at a
		 * country level(Take sum value) * (Bulk update % + 1) Market volume for previous year April 2017 = 264,438 
		 * = 264,438 * (0.60+1) = 423,101"
		 */
		
		CustomReporter.createNode("As Country level growth changes corresponding Market Volume should change accordingly >> "
				+ "Formula = <br/>Market Volume for previous year for same month at a country level(Take sum value) * (Bulk update % + 1)");
		
		String temp="0";
		try{
			temp=Util.BD(futureMonthForecastVal).divide(Util.BD("100")).toPlainString();
		}catch (Exception e) {}
		String calcVal=Util.BD(apr17TrafVolSum).multiply(Util.BD(temp).add(Util.BD("1"))).toPlainString();
		Util.comparator(calcVal, apr18TrafVolSum, "As Country level growth changes corresponding Market Volume should change accordingly");
		
		//Getting the future month(Apr 18) >> row wise values for calculating market share values later on
		futureMonthColRowWiseList=getPassedColumnList(futureMonthCol);
		
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_Cancel);
		return futureMonthColRowWiseList;
	}

	public List<String> getSumRowValues(String currentMonth, String lastMonthCol) {
		List<String> temp=new ArrayList<String>();
		WebTable table= new WebTable(tab);
		int startColNum=table.initHeaderColumnList(1).getColumnNumber(currentMonth);
		int endColNum=table.getColumnNumber(lastMonthCol);
		int lastRow=table.getRowCount();
		
		for (int i = startColNum; i <= endColNum; i++) {
			int inputObjCount=table.getChildObjectsCount(lastRow, i, "input");
			if (inputObjCount>0) {
				temp.add(Util.removeCommas(com.getAttribute(table.getChildObject(lastRow, i, "input",0),"value")));
			}else{
				temp.add(Util.removeCommas(table.getCellText(lastRow, i)));
			}
		}
		
		CustomReporter.report(STATUS.INFO, "For column ["+currentMonth+ "] to ["+ lastMonthCol +"] sum of Traffic Volume values are "+ temp);
		
		return temp;
	}

	
	
	public List<String> getRowValuesForPassedOperator(String operatorName, String startColName,
			String endColName) {
		List<String> temp=new ArrayList<String>();
		WebTable table= new WebTable(tab);
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
		
		CustomReporter.report(STATUS.INFO, "For operator ["+ operatorName +"] columns ["+startColName+ "] to ["+ endColName +"] Row Market Volume values are "+ temp);
		return temp;
	}

	public DTSummary_EditMarketVolumeP5 NAVIGATE_To_EditMarketVolume() {
		com.navigateToAndVerifyPageTitle(button_EditMonthlyVolume, title);
		return new DTSummary_EditMarketVolumeP5();
	}

}
