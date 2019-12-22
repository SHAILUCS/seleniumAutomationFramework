package objectRepository.DealTracker.DTModule.DTSummary;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

public class DTSummary_EditOperatorGrowthP8 extends DTSummary_MarketGrowth {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Edit Operator Growth";

	public DTSummary_EditOperatorGrowthP8() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	public DTSummary_EditOperatorGrowthP8 navigateTo_EditOperatorGrowth() {
		com.click(button_EditMonthlyGrowth_OperatorLevel, "Edit Monthly Growth - Operator Level button");
		if (com.verifyPageTitle(title, true)) {
			return this;
		}
		return null;
	}

	@FindBy(xpath = "//h1[contains(.,'Edit Operator Growth')]")
	private WebElement data_EditTrafficGrowth_Breadcrumb;

	@FindBy(xpath = "//h2[contains(.,'Parameters')]")
	private WebElement data_Parameters_Section;

	@FindBy(id = "P8_TRAFFIC_DIRECTION_LEFT")
	private WebElement select_TrafficDirection_Left;

	@FindBy(id = "P8_TRAFFIC_DIRECTION_RIGHT")
	private WebElement select_TrafficDirection_Right;

	@FindBy(id = "P8_COUNTRY_ID_LEFT")
	private WebElement select_Country_Left;

	@FindBy(id = "P8_COUNTRY_ID_RIGHT")
	private WebElement select_Country_Right;

	@FindBy(id = "P8_SERVICE_TYPE_ID_LEFT")
	private WebElement select_ServiceType_Left;

	@FindBy(id = "P8_SERVICE_TYPE_ID_RIGHT")
	private WebElement select_ServiceType_Right;

	@FindBy(id = "P8_EVENT_TYPE_ID_LEFT")
	private WebElement select_EventType_Left;

	@FindBy(id = "P8_EVENT_TYPE_ID_RIGHT")
	private WebElement select_EventType_Right;

	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	private WebElement button_Refresh;

	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	private WebElement button_Cancel;

	@FindBy(xpath = "//h2[contains(.,'Market Growth Operator Level')]")
	private WebElement data_MarketGrowthOperatorLevel_Section;

	@FindBy(id = "P8_NOTE")
	private WebElement text_Note;

	@FindBy(xpath = "//button[contains(.,'Save Changes to Monthly Growth')]")
	private WebElement button_SaveChangesToMonthlyGrowth;

	private By tab = By.cssSelector("table[summary='Market Growth Operator Level']");

	private List<String> colNames = new ArrayList<>(Arrays.asList("Roaming Partner", "Partner PMN", "Country Name",
			"Traffic Direction", "Service Type", "Event Type", "Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18",
			"Jun 18", "Jul 18", "Aug 18", "Sep 18", "Oct 18", "Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19",
			"Apr 19", "May 19", "Jun 19", "Jul 19", "Aug 19", "Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download")
	private WebElement link_Download;

	@FindBy(xpath = "//table[@summary='Market Growth Operator Level']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination;

	public void verify_UI() {
		CustomReporter.report(STATUS.INFO, "Verious Section headers verification");
		com.isElementPresent(data_EditTrafficGrowth_Breadcrumb, "Edit Operator Growth breadcrumb");
		com.isElementPresent(data_Parameters_Section, "Parameters Section");
		com.isElementPresent(data_MarketGrowthOperatorLevel_Section, "Market Growth Operator Level Section");

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
		com.isElementPresent(text_Note, "Note field");
		com.isElementPresent(button_SaveChangesToMonthlyGrowth, "Save Changes To Monthly Growth Button");

		//comm.printColumnHeaders(tab, 1);
		comm.verifyColumnHeaders(tab, colNames, 1);

		com.isElementPresent(link_Download, "Download Link");
		com.isElementPresent(data_Pagination, "Pagination data "+com.getText(data_Pagination));
	}

	public List<String> getOperatorLevelGrowthList_7235(List<String> roamingPartnerList,String forMonth) {
		List<String> temp=new ArrayList<String>();
		
		com.navigateToAndVerifyPageTitle(button_EditMonthlyGrowth_OperatorLevel, title);
		
		WebTable table= new WebTable(tab);
		table.initHeaderColumnList(1);

		int forMonthCol=table.getColumnNumber(forMonth);

		for (String roamingPartVal:roamingPartnerList) {
			int rowNum=table.getRowWithCellText(roamingPartVal);
			int inputObjCount=table.getChildObjectsCount(rowNum, forMonthCol, "input");
			if (inputObjCount>0) {
				temp.add(com.getAttribute(table.getChildObject(rowNum, forMonthCol, "input",0),"value"));
			}else{
				temp.add(table.getCellText(rowNum, forMonthCol));
			}
		}

		com.click(button_Cancel,"button_Cancel");
		CustomReporter.report(STATUS.INFO, "Operator Growth List for passed ["+roamingPartnerList+"] and for month ["+forMonth+"] : "+temp);
		return temp;
	}

	public List<String> getOperatorLevelGrowthList(String forMonth) {
		List<String> temp=new ArrayList<String>();
		navigateTo_EditOperatorGrowth();
		temp=getPassedColumnList(forMonth);		
		com.click(button_Cancel,"button_Cancel");
		CustomReporter.report(STATUS.INFO, "Operator Growth List for month ["+forMonth+"] : "+temp);
		return temp;
	}

	public void ForecastOperatorLevelGrowthAddVoiceMtSmsMoSmsMtAndDataMbgrowthInReportBE_7232() {

		/*
		 * "4. Select Traffic Direction, country, services, events
		 * "Traffic Direction :- Inbound 
		 * Country :- Austria 
		 * Service Type:- Voice 
		 * Event Type :- MT" 
		 * "5. Click on refresh report "
		 */

		JSONManager json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7232", "VoiceMT");
		ForecastOperatorLevelGrowthAddVoiceMtSmsMoSmsMtAndDataMbgrowthInReportBE_7232_COMMON(json);

		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7232", "VoiceMO");
		ForecastOperatorLevelGrowthAddVoiceMtSmsMoSmsMtAndDataMbgrowthInReportBE_7232_COMMON(json);

		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7232", "SmsMO");
		ForecastOperatorLevelGrowthAddVoiceMtSmsMoSmsMtAndDataMbgrowthInReportBE_7232_COMMON(json);

		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7232", "DataMB");
		ForecastOperatorLevelGrowthAddVoiceMtSmsMoSmsMtAndDataMbgrowthInReportBE_7232_COMMON(json);

	}

	private void ForecastOperatorLevelGrowthAddVoiceMtSmsMoSmsMtAndDataMbgrowthInReportBE_7232_COMMON(JSONManager json) {

		CustomReporter.createNode("For "+Arrays.toString(json.getStrArr("ServiceType"))+" , "+Arrays.toString(json.getStrArr("EventType")));

		performSearch(json);

		/*
		 * "6. Calculate Operator level growth for current month For Voice MT go
		 * at market volume on same page number (p4)" 
		 * "current Month is March 2018 
		 * Data of operator (AUTMK, AUTON, AUTPT) for March 2018 = 13,550
		 * Data of operator (AUTMK, AUTON, AUTPT) for March 2017 = 13,873"
		 * "7. Check calculated value in Operator level market growth report Check
		 * Result for March 2018 across pmn (AUTMK, AUTON, AUTPT) in Operator
		 * level market growth report "
		 */

		/*
		 * Trying to find the "Current Month", "Past Month" and "Future Month"
		 * Column number based on column background color
		 */ 
		WebTable table=new WebTable(table_OperatorLevel);
		int lastCol=table.getColCount(1);
		Calendar cal= Calendar.getInstance();
		String currentMonthName=Util.convertToString("MMM yy", cal.getTime());

		for (int i = 1; i <= lastCol; i++) {
			//Getting the style attribute of second Row td elements for matching the background-color should be lightgoldenrodyellow
			String styleAttribute=com.getAttribute(table.getCellObject(2, i), "style");
			if(styleAttribute.contains("lightgoldenrodyellow")){
				currentMonthName=table.getCellText(1, i);
				break;
			}
		}

		cal.setTime(Util.convertToDate("MMM yy", currentMonthName));
		cal.add(Calendar.MONTH, -1);
		String pastMonthName = Util.convertToString("MMM yy", cal.getTime());
		cal.add(Calendar.MONTH, -1);
		String monthBeforePastMonthName = Util.convertToString("MMM yy", cal.getTime());
		cal.add(Calendar.MONTH, 3);
		String futureMonthName = Util.convertToString("MMM yy", cal.getTime());

		cal.setTime(Util.convertToDate("MMM yy", currentMonthName));
		cal.add(Calendar.YEAR, -1);
		String currentMonthName_LastYear = Util.convertToString("MMM yy", cal.getTime());
		cal.setTime(Util.convertToDate("MMM yy", pastMonthName));
		cal.add(Calendar.YEAR, -1);
		String pastMonthName_LastYear = Util.convertToString("MMM yy", cal.getTime());
		
		//Click on bulk update market growth button before playing with Edit monthly market growth 
		//So it will reset all values on table
		DTSummary_MarketGrowth DTSummary_MarketGrowthObj = new DTSummary_MarketGrowth();
		DTSummary_MarketGrowthObj.perform_BulkUpdateOperatorLevelGrowth(0, null, null);

		//Now we got the current , future , and past month names we can get the growth 
		//for verifying the results of calculations
		CustomReporter.createNode("Navigating to edit Operator Growth & taking Current Month Past Month & future month value from table");
		navigateTo_EditOperatorGrowth();
		List<String> currentMonthGrowthList = getPassedColumnList(currentMonthName);
		List<String> pastMonthGrowthList = getPassedColumnList(pastMonthName);
		List<String> monthBeforePastMonthGrowthList = getPassedColumnList(monthBeforePastMonthName);
		List<String> futureMonthGrowthList = getPassedColumnList(futureMonthName);
		com.click(button_Cancel);

		CustomReporter.createNode("Navigating to edit Market Volume & Taking Market Volume from table & calculating operator growth for current month , past month, future month");
		DTSummary_EditMarketVolumeP5 editMarketVolumeP5= new DTSummary_EditMarketVolumeP5();
		editMarketVolumeP5.NAVIGATE_To_EditMarketVolume();
		List<String> currentMonthMVList = editMarketVolumeP5.getPassedColumnList(currentMonthName);
		List<String> pastMonthMVList = editMarketVolumeP5.getPassedColumnList(pastMonthName);
		

		List<String> currentMonthMVList_LastYear = editMarketVolumeP5.getPassedColumnList(currentMonthName_LastYear);
		List<String> pastMonthMVList_LastYear = editMarketVolumeP5.getPassedColumnList(pastMonthName_LastYear);
		com.click(editMarketVolumeP5.button_Cancel);

		//Performing Calculations NOW for Current Month
		for (int i = 0; i < currentMonthGrowthList.size(); i++) {
			String currentMonthOpGrowth_Calc="0";
			try{
				currentMonthOpGrowth_Calc=Util.BD(currentMonthMVList.get(i)).divide(Util.BD(currentMonthMVList_LastYear.get(i)),5,RoundingMode.HALF_EVEN).toPlainString();
				currentMonthOpGrowth_Calc=Util.BD(currentMonthOpGrowth_Calc).subtract(Util.BD("1")).toPlainString();
				currentMonthOpGrowth_Calc=Util.BD(currentMonthOpGrowth_Calc).multiply(Util.BD("100")).toPlainString();
			}catch (Exception e) {
			}
			Util.comparator(currentMonthOpGrowth_Calc, currentMonthGrowthList.get(i), "Operator Growth for Current Month '"+currentMonthName +"'");
		}

		//Performing Calculations NOW for Past Month
		for (int i = 0; i < pastMonthGrowthList.size(); i++) {
			String pastMonthOpGrowth_Calc="0";
			try{
				pastMonthOpGrowth_Calc=Util.BD(pastMonthMVList.get(i)).divide(Util.BD(pastMonthMVList_LastYear.get(i)),5,RoundingMode.HALF_EVEN).toPlainString();
				pastMonthOpGrowth_Calc=Util.BD(pastMonthOpGrowth_Calc).subtract(Util.BD("1")).toPlainString();
				pastMonthOpGrowth_Calc=Util.BD(pastMonthOpGrowth_Calc).multiply(Util.BD("100")).toPlainString();
			}catch (Exception e) {}
			Util.comparator(pastMonthOpGrowth_Calc, pastMonthGrowthList.get(i), "Operator Growth for Past Month '"+pastMonthName +"'");
		}


		//Performing Calculations NOW for Future Month
		for (int i = 0; i < futureMonthGrowthList.size(); i++) {
			String futureMonthOpGrowth_Calc="0";
			try{
				futureMonthOpGrowth_Calc=Util.BD(currentMonthGrowthList.get(i)).add(Util.BD(pastMonthGrowthList.get(i))).add(Util.BD(monthBeforePastMonthGrowthList.get(i))).toPlainString();
				futureMonthOpGrowth_Calc=Util.BD(futureMonthOpGrowth_Calc).divide(Util.BD("3"),5,RoundingMode.HALF_EVEN).toPlainString();
			}catch (Exception e) {}
			Util.comparator(futureMonthOpGrowth_Calc, futureMonthGrowthList.get(i), "Operator Growth for Future Month '"+futureMonthName +"'");
		}


	}


	public List<String> getPassedColumnList(String colName) {
		List<String> temp = new ArrayList<String>();
		WebTable tabEditOp=new WebTable(tab);
		tabEditOp.initHeaderColumnList(1);
		int colNum=tabEditOp.getColumnNumber(colName);
		for (int i = 2; i <=tabEditOp.getRowCount() ; i++) {
			int inputObjCount=tabEditOp.getChildObjectsCount(i, colNum, "input");
			if (inputObjCount>0) {
				temp.add(Util.removeCommas(com.getAttribute(tabEditOp.getChildObject(i, colNum, "input",0),"value")));
			}else{
				temp.add(Util.removeCommas(tabEditOp.getCellText(i, colNum)));
			}
		}
		CustomReporter.report(STATUS.INFO, "Operator Growth List for passed month ["+colName+"] : "+temp);
		return temp;
	}

	public void UpdateOpLvlMonthlyGrowth_7234(String roamingPartner,String passedColumn,String opLvlGrowth) {
		navigateTo_EditOperatorGrowth();
		WebTable table= new WebTable(tab);
		int futureMonth=table.initHeaderColumnList(1).getColumnNumber(passedColumn);
		int roamingPartnerRow=table.getRowWithCellText(roamingPartner);
		com.sendKeys("Op Lvl Monthly Growth (%) for month "+passedColumn, table.getChildObject(roamingPartnerRow, futureMonth, "input", 0), opLvlGrowth);
		com.click(button_SaveChangesToMonthlyGrowth, "button_Save Changes To Monthly Growth");
	}

}
