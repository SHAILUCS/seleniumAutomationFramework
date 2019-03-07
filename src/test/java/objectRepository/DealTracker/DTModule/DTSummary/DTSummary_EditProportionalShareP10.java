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

import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.jsonUtil.JSONManager;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.common.ApexCommon;
import tests.MainRegression.KPN_DealTracker7216;

public class DTSummary_EditProportionalShareP10 extends DTSummary_MarketShareProportionalShare{
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Edit Proportional Share";
	
	public DTSummary_EditProportionalShareP10() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	public DTSummary_EditProportionalShareP10 NAVIGATE_To_EditProportionalShare() {
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_EditMonthlyProportionalShare_ProportionalShare, "Edit Monthly Proportional Share button");
		//com.click(button_EditMonthlyProportionalShare_ProportionalShare, "Edit Monthly Proportional Share button");
		if (com.verifyPageTitle(title, true)) {
			return this;
		}
		return null;
	}

	@FindBy(xpath = "//h1[contains(.,'Edit Proportional Share')]")
	private WebElement data_EditProportionalShare_Breadcrumb;

	@FindBy(xpath = "//h2[contains(.,'Parameters')]")
	private WebElement data_Parameters_Section;

	@FindBy(id = "P10_TRAFFIC_DIRECTION_LEFT")
	private WebElement select_TrafficDirection_Left;

	@FindBy(id = "P10_TRAFFIC_DIRECTION_RIGHT")
	private WebElement select_TrafficDirection_Right;

	@FindBy(id = "P10_COUNTRY_ID_LEFT")
	private WebElement select_Country_Left;

	@FindBy(id = "P10_COUNTRY_ID_RIGHT")
	private WebElement select_Country_Right;

	@FindBy(id = "P10_SERVICE_TYPE_ID_LEFT")
	private WebElement select_ServiceType_Left;

	@FindBy(id = "P10_SERVICE_TYPE_ID_RIGHT")
	private WebElement select_ServiceType_Right;

	@FindBy(id = "P10_EVENT_TYPE_ID_LEFT")
	private WebElement select_EventType_Left;

	@FindBy(id = "P10_EVENT_TYPE_ID_RIGHT")
	private WebElement select_EventType_Right;

	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	private WebElement button_Refresh;

	@FindBy(xpath = "//button[contains(.,'Cancel')]") 
	public WebElement button_Cancel;

	@FindBy(xpath = "//h2[contains(.,'Proportional Share')]")
	private WebElement data_ProportionalShare_Section;

	@FindBy(id = "P10_NOTE")
	private WebElement text_Note;

	@FindBy(xpath = "//button[contains(.,'Save Changes to Monthly Proportional Share')]")
	private WebElement button_SaveChangesToMonthlyProportionalShare;

	private By tab = By.cssSelector("table[summary='Proportional Share']");

	private List<String> colNames = new ArrayList<>(Arrays.asList("Roaming Partner", "Partner PMN", "Country Name",
			"Traffic Direction", "Service Type", "Event Type", "Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18",
			"Jun 18", "Jul 18", "Aug 18", "Sep 18", "Oct 18", "Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19",
			"Apr 19", "May 19", "Jun 19", "Jul 19", "Aug 19", "Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download")
	private WebElement link_Download;

	@FindBy(xpath = "//table[@summary='Proportional Share']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination;
	
	public void verify_UI() {
		CustomReporter.report(STATUS.INFO, "Verious Section headers verification");
		com.isElementPresent(data_EditProportionalShare_Breadcrumb, "Edit Proportional Share breadcrumb");
		com.isElementPresent(data_Parameters_Section, "Parameters Section");
		com.isElementPresent(data_ProportionalShare_Section, "Proportional Share Section");

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

		CustomReporter.report(STATUS.INFO, "Proportional Share Section fields verification");
		com.isElementPresent(text_Note, "Note field");
		com.isElementPresent(button_SaveChangesToMonthlyProportionalShare, "Save Changes To Monthly Proportional Share");

		//comm.printColumnHeaders(tab, 1);
		comm.verifyColumnHeaders(tab, colNames, 1);

		com.isElementPresent(link_Download, "Download Link");
		com.isElementPresent(data_Pagination, "Pagination data "+com.getText(data_Pagination));
	}

	public List<String> getPassedColumnList(String columnName) {
		List<String> temp=new ArrayList<String>();
		WebTable table= new WebTable(tab);
		table.initHeaderColumnList(1);
		int colNum=table.getColumnNumber(columnName);
		for (int row = 2; row < table.getRowCount(); row++) {
			int inputObjCount=table.getChildObjectsCount(row, colNum, "input");
			if (inputObjCount>0) {
				temp.add(com.getAttribute(table.getChildObject(row, colNum, "input",0),"value"));
			}else{
				temp.add(table.getCellText(row, colNum));
			}
		}
		CustomReporter.report(STATUS.INFO, "Proportional Share List for Column ["+columnName+"] : "+temp);
		return temp;

	}
	
	public void ProportionalMarketShareRemoveCorrelationBetweenVoiceMOAndOtherServicesBE_7236() {
		JSONManager json=new JSONManager(KPN_DealTracker7216.jsonFilePath, "7236","VoiceMO");
		ProportionalMarketShareRemoveCorrelationBetweenVoiceMOAndOtherServicesBE_7236_COMMON(json);

		json=new JSONManager(KPN_DealTracker7216.jsonFilePath, "7236","VoiceMT");
		ProportionalMarketShareRemoveCorrelationBetweenVoiceMOAndOtherServicesBE_7236_COMMON(json);
		
		json=new JSONManager(KPN_DealTracker7216.jsonFilePath, "7236","SmsMO");
		ProportionalMarketShareRemoveCorrelationBetweenVoiceMOAndOtherServicesBE_7236_COMMON(json);
		
		json=new JSONManager(KPN_DealTracker7216.jsonFilePath, "7236","DataMB");
		ProportionalMarketShareRemoveCorrelationBetweenVoiceMOAndOtherServicesBE_7236_COMMON(json);
		
		
	}

	
	private void ProportionalMarketShareRemoveCorrelationBetweenVoiceMOAndOtherServicesBE_7236_COMMON(JSONManager json) {
		
		CustomReporter.createNode("For "+Arrays.toString(json.getStrArr("ServiceType"))+" , "+Arrays.toString(json.getStrArr("EventType")));
		
		/*
		 * 4. Select Traffic Direction, country, services, events 
		 * "Traffic Direction :- Inbound 
		 * Country :- Austria 
		 * Service Type:- Voice 
		 * Event Type :- MO "
		 */
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String apr18 = Util.convertToString("MMM yy", cal.getTime());
		
		
		performSearch(json);
		
		CustomReporter.createNode("Navigating to Edit Market Volume to calculate prop share");
		
		DTSummary_EditMarketVolumeP5 markVol=new DTSummary_EditMarketVolumeP5();
		markVol.NAVIGATE_To_EditMarketVolume();
		List<String> marketVolumeList=markVol.getPassedColumnList(apr18);
		com.click(markVol.button_Cancel);
		
		List<String> propShareList_Calc= new ArrayList<String>();
		int marketVolListSize=marketVolumeList.size();
		for (int i = 0; i < marketVolListSize-1; i++) {
			String temp="0";
			try{
				temp=Util.Round(Util.BD(marketVolumeList.get(i)).divide(Util.BD(marketVolumeList.get(marketVolListSize-1)), 7, RoundingMode.HALF_EVEN).multiply(Util.BD("100")).toPlainString(),5);
			}catch (Exception e) {}
			propShareList_Calc.add(temp);
		}
		CustomReporter.report(STATUS.INFO, "Proportional Share Calculated "+propShareList_Calc);
		
		CustomReporter.createNode("Navigating to Edit Proportional Share to Verify prop share");
		
		NAVIGATE_To_EditProportionalShare();
		List<String> propShareList_Page=getPassedColumnList(apr18);
		Util.comparator_List(propShareList_Calc, propShareList_Page, "Proportional market share");
		com.click(button_Cancel);
	}

	
	
	public void ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForManualUpdateFE_7238() {

		JSONManager json= new JSONManager(KPN_DealTracker7216.jsonFilePath, "7238","VoiceMo");
		ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForManualUpdateFE_7238_COMMON(json);

		json= new JSONManager(KPN_DealTracker7216.jsonFilePath, "7238","VoiceMT");
		ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForManualUpdateFE_7238_COMMON(json);

		json= new JSONManager(KPN_DealTracker7216.jsonFilePath, "7238","SmsMO");
		ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForManualUpdateFE_7238_COMMON(json);

		json= new JSONManager(KPN_DealTracker7216.jsonFilePath, "7238","DataMB");
		ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForManualUpdateFE_7238_COMMON(json);

	}
	
	public void ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForManualUpdateFE_7238_COMMON(JSONManager json) {
		
		CustomReporter.createNode("For "+Arrays.toString( json.getStrArr("ServiceType"))+" , "+Arrays.toString(json.getStrArr("EventType")));
		
		/*
		 * 3. Select Home Operator 
		 * 4. Select Traffic Direction, country, services, events 
		 * "Traffic Direction :- Inbound 
		 * Country :- Austria
		 * Service Type:- Voice 
		 * Event Type :- MO " 
		 * "5. Click on refresh report "
		 */
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String nextMonth = Util.convertToString("MMM yy", cal.getTime());
		cal.add(Calendar.YEAR, -1);
		String nextMonthLastYear = Util.convertToString("MMM yy", cal.getTime());
		
		
		performSearch(json);
		
		/*
		 * 6. Click on Edit Monthly Proportional share 
		 * 7. Check Title of the page 
		 * 8. Check Region "
		 * 9. Now manually update % of Proportional market
		 * share for voice MO for the month of April 2018 across all PMN's Click
		 * on Save changes to monthly proportional share button"		 * 
		 * 10. Check proportional market share for April 2018 As well as all
		 * future month (April Onwards)
		 */
		CustomReporter.createNode("Navigating to edit Proportional Share & Manually updating proportional share");
		NAVIGATE_To_EditProportionalShare();
		com.isElementPresent(data_EditProportionalShare_Breadcrumb,"Edit Proportional Share_Breadcrumb");
		List<String> rowWiseShareValList = performAndVerify_ManualEditProportionalShare();
		
		/*
		 * "9.Check in Market volume for April 2018 Calculate Market volume for All PMN's "	
		 * "Use Formula To Verify Market Volume For April 2018 = Sum of all operator for the month April 2018* Updated Proportional
		 * market share for one PMN 
		 * = 188,626 * 13%(0.13) = 24,521 
		 * = 188,626 * 5%(0.05) = 9,431 
		 * = 188,626* 80%(0.80) = 150,901 
		 * = 188,626* 2%(0.02) = 3,773
		 */
		
		CustomReporter.createNode("Navigating to edit Market Volume & Calculating Market volume as per updated Proprtional share "+rowWiseShareValList+ "and Verifying with page displayed value");
		DTSummary_EditMarketVolumeP5 editMarketVol= new DTSummary_EditMarketVolumeP5();
		editMarketVol.NAVIGATE_To_EditMarketVolume();
		List<String> rowWiseMarketVolumeList = editMarketVol.getPassedColumnList(nextMonth);
		List<String> rowWiseMarketVolumeList_LastYear = editMarketVol.getPassedColumnList(nextMonthLastYear);
		com.click(editMarketVol.button_Cancel);
		
		int lastRowMarketVol=rowWiseMarketVolumeList.size();
		List<String> rowWiseMarketVolumeList_Rounded = new ArrayList<String>();
		List<String> rowWiseMarketVolumeList_Calc = new ArrayList<String>();
		for (int i = 0; i < lastRowMarketVol-1; i++) {
			String marketVolValueCalc="0";
			try{
				marketVolValueCalc=Util.BD(rowWiseMarketVolumeList.get(lastRowMarketVol-1)).multiply(Util.BD(rowWiseShareValList.get(i))).toPlainString();
				marketVolValueCalc=Util.BD(marketVolValueCalc).divide(Util.BD("100"),5,RoundingMode.HALF_EVEN).toPlainString();
			}catch (Exception e) {}
			rowWiseMarketVolumeList_Calc.add(Util.Round(marketVolValueCalc, 1));
			rowWiseMarketVolumeList_Rounded.add(Util.Round(rowWiseMarketVolumeList.get(i), 1));
		}
		Util.comparator_List(rowWiseMarketVolumeList_Calc, rowWiseMarketVolumeList_Rounded, "Market Volume as per updated proportional share");
		
		/*
		 * 10.Check Operator level growth for the same month "Use Formula to verify Operator level Growth for April 2018 for PMN 
		 * (AUTMK, AUTON, AUTPT ) =( (Market volume for the month April in current year 2018 / Market volume for the month April in last year 2017)-1 )*100 
		 * 1.Market volume for April 2018 for PMN's(AUTMK, AUTON, AUTPT) = 24,521
		 * 2.Market volume for April 2017 for PMN's(AUTMK, AUTON, AUTPT) = 16,964 
		 * = (24,521 / 16,964)-1)* 100 
		 * = 45% "
		 */	
		CustomReporter.createNode("Navigating to edit operator growth & calculating Operator Level growth as per Updated Proportional share "+rowWiseShareValList+ "& verifying with displayed page value");
		DTSummary_EditOperatorGrowthP8 editOperatorGrowth= new DTSummary_EditOperatorGrowthP8();
		List<String> operatorGrowthList_NextMonth = editOperatorGrowth.getOperatorLevelGrowthList(nextMonth);
		List<String> operatorGrowthList_NextMonth_Rounded = new ArrayList<String>();
		List<String> operatorGrowthList_Calc = new ArrayList<String>();
		for (int i = 0; i < operatorGrowthList_NextMonth.size(); i++) {
			String operatorGrowthCalc="0";
			try{
				operatorGrowthCalc=Util.BD(rowWiseMarketVolumeList.get(i)).divide(Util.BD(rowWiseMarketVolumeList_LastYear.get(i)),5,RoundingMode.HALF_EVEN).toPlainString();
				operatorGrowthCalc=Util.BD(operatorGrowthCalc).subtract(Util.BD("1")).toPlainString();
				operatorGrowthCalc=Util.BD(operatorGrowthCalc).multiply(Util.BD("100")).toPlainString();
			}
			catch(Exception e){
			}
				operatorGrowthList_Calc.add(Util.Round(operatorGrowthCalc,1));
				operatorGrowthList_NextMonth_Rounded.add(Util.Round(operatorGrowthList_NextMonth.get(i), 1));
		}
		Util.comparator_List(operatorGrowthList_Calc, operatorGrowthList_NextMonth_Rounded, "Operator growth as per updated proportional share ");
		
		
		/*
		 * "11. Check Tap Charge for the same month " 
		 * "Tap Charge = Tap Rate for the month April 2018 for PMN (AUTMK, AUTON, AUTPT )*Market Volume for
		 * the April 2018 for PMN (AUTMK, AUTON, AUTPT ) 
		 * Tap charge = 0.02000 * 24,521 
		 * Tap charge = 490"
		 */
		CustomReporter.createNode("Navigating to edit Tap Rate, taking Tap rate & calculating Tap Charge as per updated Proprtional share "+rowWiseShareValList);
		DTSummary_Charges charges=new DTSummary_Charges();
		List<String> tapRateList = charges.getTapRateForPassedColumn(nextMonth);
		
		List<String> tapChargeList = charges.getTapChargeForPassedColumn(nextMonth);
		tapChargeList.remove(tapChargeList.size()-1);
		
		List<String> tapChargeList_Calc = new ArrayList<String>();
		for (int i = 0; i < tapRateList.size(); i++) {
			String tapChargeCalc=Util.BD(tapRateList.get(i)).multiply(Util.BD(rowWiseMarketVolumeList_Calc.get(i))).toPlainString();
			tapChargeList_Calc.add(Util.Round(tapChargeCalc,0));
		}
		Util.comparator_List(tapChargeList_Calc, tapChargeList, "TAP Charge as per updated proportional share");
	}

	private List<String> performAndVerify_ManualEditProportionalShare() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String nextMonth = Util.convertToString("MMM yy", cal.getTime());

		WebTable table= new WebTable(tab);
		table.initHeaderColumnList(1);
		int rowCount=table.getRowCount();
		int result=100/(rowCount-2);
		List<String> rowWiseShareValList=new ArrayList<String>();
		for (int i = 2; i < rowCount; i++) {
			String temp;
			if (i==(rowCount-1)) {
				temp=100-result*(rowCount-3)+"";
			}else{
				temp=result+"";
			}
			com.sendKeys("Prop share value ["+temp+"] filled in row ["+i+"] of month ["+nextMonth+"]",table.getChildObject(i, table.getColumnNumber(nextMonth), "input", 0),temp);
			rowWiseShareValList.add(temp);
		}
		
		com.click(button_SaveChangesToMonthlyProportionalShare, "button_Save Changes To Monthly Proportional Share");
		
		//Verifying that the changes reflect on Proportional Share table
		table= new WebTable(table_ProportionalShare);
		table.initHeaderColumnList(1);
		int colNum=table.getColumnNumber(nextMonth);
		rowCount=table.getRowCount();
		boolean flag=true;
		for (int i = 2; i < rowCount; i++) {
			String val=table.getCellText(i,colNum);
			String listVal=rowWiseShareValList.get(i-2);
			if(!val.contains(listVal)){
				CustomReporter.report(STATUS.FAIL, "Value mismatch in Proportional share table Expected value="+listVal+" Actual displayed="+val);
				flag=false;
			}
		}
		if (flag) {
			CustomReporter.report(STATUS.PASS, "Values in Proportional share table are properly updated as "+rowWiseShareValList);
		}		

		return rowWiseShareValList;
	}
}
