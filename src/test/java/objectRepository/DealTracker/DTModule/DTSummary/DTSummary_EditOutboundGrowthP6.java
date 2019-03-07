package objectRepository.DealTracker.DTModule.DTSummary;

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

public class DTSummary_EditOutboundGrowthP6 extends DTSummary_MarketGrowth {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Edit Outbound Growth";
	
	public DTSummary_EditOutboundGrowthP6() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	

	@FindBy(xpath = "//h1[contains(.,'Edit Outbound Growth')]")
	private WebElement data_EditOutboundGrowth_Breadcrumb;

	@FindBy(xpath = "//h2[contains(.,'Parameters')]")
	private WebElement data_Parameters_Section;

	@FindBy(id = "P6_TRAFFIC_DIRECTION_LEFT")
	private WebElement select_TrafficDirection_Left;

	@FindBy(id = "P6_TRAFFIC_DIRECTION_RIGHT")
	private WebElement select_TrafficDirection_Right;

	@FindBy(id = "P6_COUNTRY_ID_LEFT")
	private WebElement select_Country_Left;

	@FindBy(id = "P6_COUNTRY_ID_RIGHT")
	private WebElement select_Country_Right;

	@FindBy(id = "P6_SERVICE_TYPE_ID_LEFT")
	private WebElement select_ServiceType_Left;

	@FindBy(id = "P6_SERVICE_TYPE_ID_RIGHT")
	private WebElement select_ServiceType_Right;

	@FindBy(id = "P6_EVENT_TYPE_ID_LEFT")
	private WebElement select_EventType_Left;

	@FindBy(id = "P6_EVENT_TYPE_ID_RIGHT")
	private WebElement select_EventType_Right;

	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	private WebElement button_Refresh;

	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	private WebElement button_Cancel;

	@FindBy(xpath = "//h2[contains(.,'Outbound Market Growth')]")
	private WebElement data_OutboundMarketGrowth_Section;

	@FindBy(id = "P6_NOTE")
	private WebElement text_Note;

	@FindBy(xpath = "//button[contains(.,'Save Changes to Outbound Growth')]")
	private WebElement button_SaveChangesToOutboundGrowth;

	private By tab = By.cssSelector("table[summary='Outbound Market Growth']");

	private List<String> colNames = new ArrayList<>(Arrays.asList("Country Name", "Traffic Direction", "Service Type",
			"Event Type", "Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18", "Jun 18", "Jul 18", "Aug 18", "Sep 18",
			"Oct 18", "Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19", "Apr 19", "May 19", "Jun 19", "Jul 19",
			"Aug 19", "Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download")
	private WebElement link_Download;

	@FindBy(xpath = "//table[@summary='Outbound Market Growth']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination;

	public void verify_UI() {
		CustomReporter.createNode("Various Section headers verification");
		com.isElementPresent(data_EditOutboundGrowth_Breadcrumb, "Edit Outbound Growth breadcrumb");
		com.isElementPresent(data_Parameters_Section, "Parameters Section");
		com.isElementPresent(data_OutboundMarketGrowth_Section, "Outbound Market Growth Section");

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

		CustomReporter.createNode("Market Growth Section fields verification");
		com.isElementPresent(text_Note, "Note field");
		com.isElementPresent(button_SaveChangesToOutboundGrowth, "Save Changes To Outbound Growth Button");

		// comm.printColumnHeaders(tab, 1);
		comm.verifyColumnHeaders(tab, colNames, 1);

		com.isElementPresent(link_Download, "Download Link");
		com.isElementPresent(data_Pagination, "Pagination data " + com.getText(data_Pagination));
	}

	public DTSummary_EditOutboundGrowthP6 navigateTo_EditOutboundGrowth() {
		com.navigateToAndVerifyPageTitle(button_EditOutboundGrowth_CountryLevel, title);
		return new DTSummary_EditOutboundGrowthP6();
	}
	
	public void forecastCountryLevelGrowthManualManipulationForObTrafficFE_7218() {
		/*
		 * steps 4-7 can not be automated "8. change Traffic Direction Traffic
		 * Direction :- Outbound Country :- Austria Service Type:- All Event
		 * Type :- All"
		 */
		JSONManager json=new JSONManager(KPN_DealTracker7216.jsonFilePath, "7218", "VoiceMO");
		forecastCountryLevelGrowthManualManipulationForObTrafficFE_7218_COMMON(json);
		
		json=new JSONManager(KPN_DealTracker7216.jsonFilePath, "7218", "VoiceMT");
		forecastCountryLevelGrowthManualManipulationForObTrafficFE_7218_COMMON(json);

		json=new JSONManager(KPN_DealTracker7216.jsonFilePath, "7218", "SmsMO");
		forecastCountryLevelGrowthManualManipulationForObTrafficFE_7218_COMMON(json);

		json=new JSONManager(KPN_DealTracker7216.jsonFilePath, "7218", "DataMB");
		forecastCountryLevelGrowthManualManipulationForObTrafficFE_7218_COMMON(json);
		
	}
	
	private void forecastCountryLevelGrowthManualManipulationForObTrafficFE_7218_COMMON(JSONManager json) {
		
		CustomReporter.createNode("For "+Arrays.toString(json.getStrArr("ServiceType"))+" , "+Arrays.toString(json.getStrArr("EventType")));
		
		performSearch(json);
		
		/**
		 * The purpose of this test is to check that 
		 *  --once we have manually/explicitly changed "Country Level Growth" values 
		 *     either using [bulk update/manual manipulation] 
		 *  --The system should not auto update those explicitly changed values 
		 *  --using the 3 months rolling average algo
		 */
		CustomReporter.createNode("Performing Bulk Update");
		
		DTSummary_MarketGrowth marketGrowthTab=new DTSummary_MarketGrowth();
		
		json=new JSONManager(KPN_DealTracker7216.jsonFilePath, "7218");
		
		String effectiveDate=marketGrowthTab.perform_BulkUpdateOutboundForecastCountryLevelGrowth(null, json.getStr("bulkUpdateVal"));
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(Util.convertToDate("dd-MMM-yyyy", effectiveDate));
		cal.add(Calendar.MONTH, 1);
		String apr18 = Util.convertToString("MMM yy", cal.getTime());
		cal.add(Calendar.YEAR, -1);
		String apr17 = Util.convertToString("MMM yy", cal.getTime());
		
		WebTable tableRead = new WebTable(table_OutboundCountryLevel);
		tableRead.initHeaderColumnList(1);
		
		String dec18=tableRead.getCellText(1, tableRead.getColCount(1));
		
		
		// Storing the values before making change
		List<String> oldValuesOfFutureMonths = new ArrayList<String>();
		for (int col = tableRead.getColumnNumber(apr18); col <= tableRead.getColCount(1); col++) {
			oldValuesOfFutureMonths.add(tableRead.getCellText(2, col));
		}
		
		CustomReporter.report(STATUS.INFO, "'Outbound Country Level Table' : Old values of Future Months "+oldValuesOfFutureMonths+" from month "+apr18+" to "+dec18);
		
		/*
		 * 9. Check page title of page (p6) 10. Check Region name on page (p6)
		 * 11. check save button
		 */
		
		CustomReporter.createNode("Navigate to Edit Outbound Growth >> Update Manual Growth For month "+apr18);
		
		navigateTo_EditOutboundGrowth();
		
		com.isElementPresent(data_OutboundMarketGrowth_Section,
				"Region Name changed from 'Market Growth' to 'Outbound Market Growth'");
		com.isElementPresent(button_SaveChangesToOutboundGrowth,
				"Button 'Save Changes to Monthly Growth' is renamed to 'Save Changes to Outbound Growth'");

		/*
		 * 13. Update Manual Growth for Voice MO For April 2018 & Click on Save
		 * Changes to Outbound Growth Manual update 60% for April 2018 for
		 * Country Austria Updated growth should reflect or propagate on below
		 * country level growth report 
		 * 14. Check growth update for one future
		 * month Reflected to all future months(Imp Check) 
		 * " Growth update to one future month should not update to all future months
		 */

		WebTable tableEdit = new WebTable(tab);
		tableEdit.initHeaderColumnList(1);
		String apr18NewVal = json.getStr("OutboundGrowthValue_CountryLvl");
		int apr18ColNum = tableEdit.getColumnNumber(apr18);
		com.sendKeys(tableEdit.getChildObject(2, apr18ColNum, "input", 0), apr18NewVal);

		com.click(button_SaveChangesToOutboundGrowth, "button_Save Changes To Outbound Growth");

		// Storing the values after making change
		List<String> newValuesOfFutureMonths = new ArrayList<String>();
		for (int col =  tableRead.getColumnNumber(apr18); col <= tableRead.getColCount(1); col++) {
			newValuesOfFutureMonths.add(tableRead.getCellText(2, col));
		}
		CustomReporter.report(STATUS.INFO, "New values of Future Months "+newValuesOfFutureMonths+" from month "+apr18+" to "+dec18);
		
		// Now comparing the values to see Growth update to one future month
		// should not update to all future months
		
		boolean flag = true;
		for (int i = 1; i < newValuesOfFutureMonths.size(); i++) {
			if (!oldValuesOfFutureMonths.get(i).equals(newValuesOfFutureMonths.get(i))) {
				CustomReporter.report(STATUS.FAIL, "Old Value " + oldValuesOfFutureMonths.get(i)
						+ " is not matching with " + newValuesOfFutureMonths.get(i));
				flag = false;
			}
		}
		if (flag) {
			CustomReporter.report(STATUS.PASS,
					"In 'Outbound Country Level Table' : Growth update to one future month is not affecting all future months");
		}

		CustomReporter.createNode("Now check Market Volume");
		/*
		 * 12. Now check Market Volume at market volume report for April 2018
		 * "Use Formula = Market Volume for previous year for same month at a
		 * country level(Take sum value) * (Bulk update % + 1) 
		 * Market volume for previous year April 2017 = 264,438 = 264,438 * (0.60+1) = 423,101"
		 */

		DTSummary_EditMarketVolumeP5 editMarketVolume= new DTSummary_EditMarketVolumeP5();
		List<String> futureMonthColRowWiseList_TrafVol=editMarketVolume.checkMarketVolumeAtMarketVolumeReportForApril18_7218(apr17,apr18,apr18NewVal);
		
		/*
		 * 14. As Country level growth manipulated check Market share 
		 * 15. Check Market share for the month April 2018 "Use Formula 
		 * =( Market Volume * for Same year April 2018 for one partner / Sum of market volume for same year April 2018 for all PMN's)*100 
		 * = Market Volume For Partner PMN's (AUTMK, AUTON, AUTPT) For April 2018 = 72,339 
		 * = Sum of Market Volume for all partner PMN's = 423,101 
		 * =( 72,339/423,101)*100 
		 * = 17 %"
		 */
		
		CustomReporter.createNode("Now check Market Share");
		
		DTSummary_EditMarketShareP7 editmarketShare=new DTSummary_EditMarketShareP7();
		editmarketShare.checkMarketShareForTheMonthApril18_7218(apr18,futureMonthColRowWiseList_TrafVol);
		
		CustomReporter.createNode("Reverting the Bulk Update changes");
		
		marketGrowthTab.perform_BulkUpdateOutboundForecastCountryLevelGrowth(null, null);
		
	}

		
}
