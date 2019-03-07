package objectRepository.DealTracker.DTModule.DTSummary;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.csvUtil.CSVManager;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.jsonUtil.JSONManager;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.common.ApexCommon;
import tests.MainRegression.KPN_DealTracker7216;

public class DTSummary_MarketGrowth extends DTSummary_Header {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = DTSummary_Header.title;

	public DTSummary_MarketGrowth() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h2[contains(.,'Market Growth')]")
	private WebElement data_MarketGrowth_Section;

	public void verify_UI() {
		com.isElementPresent(data_MarketGrowth_Section, "Market Growth - Main Section heading");
		verifySection_BulkUpdateForecastCountryLevelGrowth();
		verifySection_OutboundCountryLevel();
		verifySection_InboundCountryLevel();
		verifySection_BulkUpdateForecastOperatorLevelGrowth();
		verifySection_OperatorLevel();
	}

	@FindBy(xpath = "//h2[contains(.,'Bulk Update Outbound Forecast Country Level Growth')]")
	private WebElement region_BulkUpdateOutboundForecastCountryLevelGrowth;

	@FindBy(xpath = "//h2[contains(.,'Bulk Update Outbound Forecast Country Level Growth')]//..//..//button")
	private WebElement hideshowbutton_BulkUpdateOutboundForecastCountryLevelGrowth;

	@FindBy(id = "P4_EFFECTIVE_FROM_COU")
	private WebElement text_EffectiveFrom_ForecastCountryLevel;

	@FindBy(id = "P4_GROWTH_NOTE")
	private WebElement text_Note_ForecastCountryLevel;

	@FindBy(id = "P4_GROWTH_BULK_UPDATE")
	private WebElement text_BulkUpdateForecastGrowth;

	@FindBy(xpath = "(//button[contains(.,'Bulk Update Forecast Growth')])[1]")
	private WebElement button_BulkUpdateForecastGrowth_CountryLevel;

	private void verifySection_BulkUpdateForecastCountryLevelGrowth() {
		CustomReporter.report(STATUS.INFO, "Bulk Update Forecast Country Level Growth Sub-Section verification");
		com.isElementPresent(region_BulkUpdateOutboundForecastCountryLevelGrowth,
				"Bulk Update Forecast Country Level Growth Sub-Section heading");
		com.isElementPresent(hideshowbutton_BulkUpdateOutboundForecastCountryLevelGrowth,
				"Bulk Update Forecast Country Level Growth Sub-Section Hide/Show icon");

		com.click_UsingAction(hideshowbutton_BulkUpdateOutboundForecastCountryLevelGrowth);
		com.isElementPresent(text_EffectiveFrom_ForecastCountryLevel, "Effective From_Forecast Country Level field");
		com.isElementPresent(text_Note_ForecastCountryLevel, "Note_Forecast Country Level field");
		com.isElementPresent(text_BulkUpdateForecastGrowth, "Bulk Update Forecast Growth(%) field");
		com.isElementPresent(button_BulkUpdateForecastGrowth_CountryLevel, "Bulk Update Forecast Growth button");
	}

	@FindBy(xpath = "//h2[contains(.,'Outbound Country Level')]")
	private WebElement region_OutboundCountryLevel;

	@FindBy(xpath = "//button[.='Edit Outbound Growth']")
	protected WebElement button_EditOutboundGrowth_CountryLevel;

	protected By table_OutboundCountryLevel = By.cssSelector("table[summary='Outbound Country Level']");

	private List<String> colNames_OutboundCountryLevel = new ArrayList<>(
			Arrays.asList("Country Name", "Traffic Direction", "Service Type", "Event Type", "Average 2018 Growth",
					"Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18", "Jun 18", "Jul 18", "Aug 18", "Sep 18", "Oct 18",
					"Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19", "Apr 19", "May 19", "Jun 19", "Jul 19", "Aug 19",
					"Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download Outbound Market Growth Country Level")
	private WebElement link_DownloadOutboundMarketGrowthCountryLevel;

	@FindBy(xpath = "//table[@summary='Outbound Country Level']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination_OutboundCountryLevel;

	private void verifySection_OutboundCountryLevel() {
		CustomReporter.report(STATUS.INFO, "Outbound Country Level Sub-Section verification");
		com.isElementPresent(region_OutboundCountryLevel, "region_ Outbound Country Level");
		com.isElementPresent(button_EditOutboundGrowth_CountryLevel, "Edit Outbound Growth button");
		//comm.printColumnHeaders(table_ForecastCountryLevel, 1);
		comm.verifyColumnHeaders(table_OutboundCountryLevel, colNames_OutboundCountryLevel, 1);
		com.isElementPresent(link_DownloadOutboundMarketGrowthCountryLevel, "Download Outbound Market Growth Country Level link");
		com.isElementPresent(data_Pagination_OutboundCountryLevel, "Pagination data "+com.getText(data_Pagination_OutboundCountryLevel));
	}

	@FindBy(xpath = "//h2[contains(.,'Inbound Country Level')]")
	private WebElement region_InboundCountryLevel;

	@FindBy(xpath = "//button[.='View Inbound Growth']")
	protected WebElement button_ViewInboundGrowth_CountryLevel;

	protected By table_InboundCountryLevel = By.cssSelector("table[summary='Inbound Country Level']");

	private List<String> colNames_InboundCountryLevel = new ArrayList<>(
			Arrays.asList("Country Name", "Traffic Direction", "Service Type", "Event Type", "Average 2018 Growth",
					"Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18", "Jun 18", "Jul 18", "Aug 18", "Sep 18", "Oct 18",
					"Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19", "Apr 19", "May 19", "Jun 19", "Jul 19", "Aug 19",
					"Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download Inbound Market Growth Country Level")
	private WebElement link_DownloadInboundMarketGrowthCountryLevel;

	@FindBy(xpath = "//table[@summary='Inbound Country Level']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination_InboundCountryLevel;

	private void verifySection_InboundCountryLevel() {
		CustomReporter.report(STATUS.INFO, "Inbound Country Level Sub-Section verification");
		com.isElementPresent(region_InboundCountryLevel, "region_ Inbound Country Level");
		com.isElementPresent(button_ViewInboundGrowth_CountryLevel, "View Inbound Growth button");
		//comm.printColumnHeaders(table_ForecastCountryLevel, 1);
		comm.verifyColumnHeaders(table_InboundCountryLevel, colNames_InboundCountryLevel, 1);
		com.isElementPresent(link_DownloadInboundMarketGrowthCountryLevel, "Download Inbound Market Growth Country Level link");
		com.isElementPresent(data_Pagination_InboundCountryLevel, "Pagination data "+com.getText(data_Pagination_InboundCountryLevel));
	}

	@FindBy(xpath="//button[.='View Inbound Growth']")
	protected WebElement button_ViewInboundGrowth;

	@FindBy(xpath = "//h2[contains(.,'Bulk Update Forecast Operator Level Growth')]")
	private WebElement region_BulkUpdateForecastOperatorLevelGrowth;

	@FindBy(xpath = "//h2[contains(.,'Bulk Update Forecast Operator Level Growth')]//..//..//button")
	private WebElement hideshowbutton_BulkUpdateForecastOperatorLevelGrowth;

	@FindBy(id = "P4_EFFECTIVE_FROM_PG")
	private WebElement text_EffectiveFrom_ForecastOperatorLevel;

	@FindBy(id = "P4_GROWTH_PMN_NOTE")
	private WebElement text_Note_ForecastOperatorLevel;

	@FindBy(xpath = "//button[contains(.,'Bulk Update Forecast Growth')]")
	private WebElement button_BulkUpdateForecastGrowth_OperatorLevel;

	private By table_BulkUpdateForecastOperatorLevel = By
			.cssSelector("table[summary='Bulk Update Forecast Operator Level Growth']");

	private List<String> colNames_BulkUpdateForecastOperatorLevel = new ArrayList<>(
			Arrays.asList("Partner Name", "Partner PLMN", "Service", "Event", "Direction", "Forecast Growth (%)"));

	private void verifySection_BulkUpdateForecastOperatorLevelGrowth() {
		CustomReporter.report(STATUS.INFO, "Bulk Update Forecast Operator Level Growth Sub-Section verification");
		com.isElementPresent(region_BulkUpdateForecastOperatorLevelGrowth,
				"Bulk Update Forecast Operator Level Growth Sub-Section heading");

		com.isElementPresent(hideshowbutton_BulkUpdateForecastOperatorLevelGrowth,
				"Bulk Update Forecast Operator Level Growth Sub-Section Hide/Show icon");

		com.click_UsingAction(hideshowbutton_BulkUpdateForecastOperatorLevelGrowth);
		com.isElementPresent(text_EffectiveFrom_ForecastOperatorLevel, "Effective From_Forecast Operator Level field");

		com.isElementPresent(text_Note_ForecastOperatorLevel, "Note_Forecast Operator Level field");
		com.isElementPresent(button_BulkUpdateForecastGrowth_OperatorLevel,
				"Bulk Update Forecast Growth_Operator Level button");

		//comm.printColumnHeaders(table_BulkUpdateForecastOperatorLevel, 1);
		comm.verifyColumnHeaders(table_BulkUpdateForecastOperatorLevel, colNames_BulkUpdateForecastOperatorLevel, 1);
	}

	@FindBy(xpath = "//h2[contains(.,'Operator Level')]")
	private WebElement data_OperatorLevel_Section;

	@FindBy(xpath = "//button[@id='editpmngrowth']")
	protected WebElement button_EditMonthlyGrowth_OperatorLevel;

	protected By table_OperatorLevel = By.cssSelector("table[summary='Operator Level']");

	private List<String> colNames_OperatorLevel = new ArrayList<>(
			Arrays.asList("Roaming Partner", "Partner PMN", "Country Name", "Traffic Direction", "Service Type",
					"Event Type", "Average 2018 Growth", "Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18", "Jun 18",
					"Jul 18", "Aug 18", "Sep 18", "Oct 18", "Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19", "Apr 19",
					"May 19", "Jun 19", "Jul 19", "Aug 19", "Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download Market Growth Operator Level")
	private WebElement link_DownloadMarketGrowthOperatorLevel;

	@FindBy(xpath = "//table[@summary='Operator Level']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination_OperatorLevel;

	private void verifySection_OperatorLevel() {
		CustomReporter.report(STATUS.INFO, "Operator Level Sub-Section verification");
		com.isElementPresent(data_OperatorLevel_Section, "Operator Level Sub-Section heading");

		com.isElementPresent(button_EditMonthlyGrowth_OperatorLevel, "Edit Monthly Growth button");

		//comm.printColumnHeaders(table_OperatorLevel, 1);
		comm.verifyColumnHeaders(table_OperatorLevel, colNames_OperatorLevel, 1);

		com.isElementPresent(link_DownloadMarketGrowthOperatorLevel, "Download Market Growth Operator Level link");

		com.isElementPresent(data_Pagination_OperatorLevel, "Pagination data "+com.getText(data_Pagination_OperatorLevel));
	}

	
	public String perform_BulkUpdateOutboundForecastCountryLevelGrowth(String bulkUpdateDate,String bulkUpdateValue) {
		String msg="";
		com.click(hideshowbutton_BulkUpdateOutboundForecastCountryLevelGrowth,
				"Bulk Update Outbound Forecast Country Level Growth, HideShowButton");
		
		if (bulkUpdateDate!=null) {
			com.sendKeys("Effective From", text_EffectiveFrom_ForecastCountryLevel, bulkUpdateDate);
			msg=msg+" | Effective From ["+bulkUpdateDate+"]";
		}else{
			bulkUpdateDate=com.getAttribute(text_EffectiveFrom_ForecastCountryLevel, "value");
			msg=msg+" | Effective From ["+bulkUpdateDate+"]";
		}
		
		if (bulkUpdateValue!=null) {
			com.sendKeys("Bulk Update Forecast Growth (%)", text_BulkUpdateForecastGrowth, bulkUpdateValue);
			msg=msg+" | Bulk Update Forecast Growth (%) ["+bulkUpdateValue+"]";
		}
		CustomReporter.report(STATUS.INFO,"performing Bulk Update Outbound Forecast Country Level Growth for data "+msg);
		com.click(button_BulkUpdateForecastGrowth_CountryLevel, "Bulk Update Forecast Growth button");
		
		return bulkUpdateDate;
	}
	
	/**@author prafull.barve*/
	public void forecastCountryLevelGrowthDisableBulkUpdateForIB_7217() {

		/* 3. Select Home Operator Home Operator:- NLDPT 
		 * 4. Select Traffic Direction, country, services, events
		 * Traffic Direction :- Select both 
		 * Country :- Austria 
		 * Service Type:- All 
		 * Event Type :- All
		 * 5. Click on refresh report
		 */

		CustomReporter.createNode("Checking regions 1. Outbound Country level Market growth and 2. Inbound Country level Market growth");
		
		/*
		 * This is converting your current year into past year(2018 to 2017)
		 * (201 to 203)
		 */
		
		JSONManager json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7217","BothDirection");
		performSearch(json);


		/*
		 * Check two regions on report 1. Outbound Country level Market growth and 
		 * 2. Inbound Country level Market growth
		 */
		com.isElementPresent(region_OutboundCountryLevel, "Outbound Country Level, Region");
		com.isElementPresent(region_InboundCountryLevel, "Inbound Country Level, Region");

		/*
		 * Select Traffic Direction, country, services, events
		 * Traffic Direction :- Outbound Country :- Austria Service Type:- All Event Type :- All"
		 */
		
		CustomReporter.createNode("Checking Bulk Update Outbound Forecast Country Level Growth option For OutboundwithAllServices");

		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7217","OutboundwithAllServices");
		performSearch(json);

		/*Check Bulk Update Outbound Forecast Country Level Growth option */

		com.isElementPresent(region_BulkUpdateOutboundForecastCountryLevelGrowth,
				"Bulk Update Outbound Forecast Country Level Growth, Region");

		/*
		 * Check outbound bulk update by doing bulk update in % for all services & click on
		 * Bulk update forecast Growth 
		 * Bulk Update Forecast Growth (%) = 80
		 */
		
		perform_BulkUpdateOutboundForecastCountryLevelGrowth(null, json.getStr("BulkUpdateValue"));
		
		/*Reading Date from effective date box & storing into effectivedate variable & 
		 * converting date from date format to String format which matches with column date in table
		 * storing date into colName variable */
		String effectiveDate = com.getAttribute(text_EffectiveFrom_ForecastCountryLevel, "value");
		Date tempDate = Util.convertToDate("dd-MMM-yyyy", effectiveDate);
		String colName = Util.convertToString("MMM yy", tempDate);
		//Calling bulk update verification loop
		BulkUpdateValueVerificationLoop(colName, json.getStr("BulkUpdateValue"));

		/*
		 * Check Outbound bulk update by doing bulk update for single service 
		 * & click on bulk update forecast Growth Select single service
		 * & do bulk update for single service change Service & event type
		 * Traffic Direction :- Outbound Country :- Austria Service Type:- Voice 
		 * Event Type :- MT Bulk Update Forecast Growth (%) = 70
		 */
		// Checking Voice MT = 70

		CustomReporter.createNode("Checking Bulk Update Outbound Forecast Country Level Growth option For OutboundwithVoiceMT");
		
		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7217","OutboundwithVoiceMT");
		performSearch(json);

		perform_BulkUpdateOutboundForecastCountryLevelGrowth(null, json.getStr("BulkUpdateValue"));
		
		//Calling bulk update verification loop
		BulkUpdateValueVerificationLoop(colName, json.getStr("BulkUpdateValue"));

		// Checking Voice MO = 80
		// Checking SMS MO = 80
		// Checking DATA MB = 80
		
		CustomReporter.createNode("Checking Bulk Update Outbound Forecast Country Level Growth option For OutboundwithMo&MB");
		
		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7217","OutboundwithMo&MB");
		performSearch(json);
		//Calling bulk update verification loop
		BulkUpdateValueVerificationLoop(colName, json.getStr("BulkUpdateValue"));

		/* Check View Monthly Growth Country Level */
		
		CustomReporter.createNode("Checking View Monthly Growth Country Level For InboundwithAllServices");
		
		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7217","InboundwithAllServices");
		performSearch(json);

		/*Click on Download InBound Market Growth Country Level */

		com.click(link_DownloadInboundMarketGrowthCountryLevel, "Download Inbound Market Growth Country Level");
		String FilePath = Util.getDownloadedFilePath("Inbound Country Level");
		if (FilePath != null) {
			CustomReporter.report(STATUS.PASS, "File Downloaded Successfully "+FilePath);

			CsvContentVerificationLoop(table_InboundCountryLevel, FilePath);
			/*
			 * WebTable table= new WebTable(table_ForecastCountryLevel);
			 * CSVManager csvVerification = new CSVManager(FilePath);
			 * CsvContentVerificationLoop(table, csvVerification);
			 */
		} else {
			CustomReporter.report(STATUS.FAIL, "File Not Downloaded");
		}
	}

	/** @author prafull.barve */
	public void CsvContentVerificationLoop(By tableObj, String FilePath) {

		// Reading & verifying data from CSV
		boolean flag = true;
		WebTable table = new WebTable(tableObj);
		CSVManager csvVerification = new CSVManager(FilePath);
		for (int row = 2; row <= csvVerification.getRowCount(); row++) {
			for (int col = 1; col <= csvVerification.getColumnCount(); col++) {
				String cvsVal = csvVerification.getValue(row, col);

				/*
				String htmlVal = ""
				int inputObjCount=table.getChildObjectsCount(row, colNum, "input");
				if (inputObjCount>0) {
					htmlVal = com.getAttribute(table.getChildObject(row, colNum, "input",0),"value");
				}else{
					htmlVal = table.getCellText(row, colNum);
				}
				 */
				String htmlVal = table.getCellText(row, col);
				if (!htmlVal.contains(cvsVal)) {
					CustomReporter.report(STATUS.FAIL,
							"CSV Value:["+cvsVal+"] is failed to match with HTML Value:["+htmlVal+ "] on Row " + row + " Col " + col);
					flag = false;
				}
			}
		}

		if (flag) {
			CustomReporter.report(STATUS.PASS, "Values of Inbound Country Level table is properly displayed on downloaded CSV");
		}
		
		
		
	}

	/** @author prafull.barve */
	public void BulkUpdateValueVerificationLoop(String colName, String BulkUpdateValue) {
		WebTable tab_OutBoundCountryLevel = new WebTable(table_OutboundCountryLevel);
		tab_OutBoundCountryLevel.initHeaderColumnList(1);
		int colNum = tab_OutBoundCountryLevel.getColumnNumber(colName);
		int lastColNum = tab_OutBoundCountryLevel.getColCount(1);
		boolean flag = true;
		for (int row = 2; row <= tab_OutBoundCountryLevel.getRowCount(); row++) {

			for (int col = colNum; col <= lastColNum; col++) {

				String celltext = tab_OutBoundCountryLevel.getCellText(row, col);
				if (!celltext.contains(BulkUpdateValue)) {
					CustomReporter.report(STATUS.FAIL, "Value not updated on Row " + row + " Col " + col +" Expected : "+BulkUpdateValue+" Actual : "+celltext);
					flag = false;
				}
			}
		}

		if (flag) {
			CustomReporter.report(STATUS.PASS, "All Value are updated");
		}
	}

	/**@author shailendra.rajawat*/
	public void ForecastCountryLevelGrowthAddVoiceMoGrowthInReportBE_7220() {
		
		JSONManager json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7220", "VoiceMO");
		ForecastCountryLevelGrowthAddVoiceMoGrowthInReportBE_7220_COMMON(json);

		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7220", "VoiceMT");
		ForecastCountryLevelGrowthAddVoiceMoGrowthInReportBE_7220_COMMON(json);

		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7220", "SmsMO");
		ForecastCountryLevelGrowthAddVoiceMoGrowthInReportBE_7220_COMMON(json);

		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7220", "DataMB");
		ForecastCountryLevelGrowthAddVoiceMoGrowthInReportBE_7220_COMMON(json);
	}

	private void ForecastCountryLevelGrowthAddVoiceMoGrowthInReportBE_7220_COMMON(JSONManager json) {

		CustomReporter.createNode("For "+Arrays.toString(json.getStrArr("ServiceType"))+" , "+Arrays.toString(json.getStrArr("EventType")));

		performSearch(json);

		Calendar cal=Calendar.getInstance();
		String currentMonth=Util.convertToString("MMM yy", cal.getTime());

		//Getting the list of Column Names and the Growth Values
		WebTable tab=new WebTable(table_InboundCountryLevel);
		int currentCol=tab.initHeaderColumnList(1).getColumnNumber(currentMonth);
		int lastCol=tab.getColCount(1);

		String lastMonthCol=tab.getCellText(1, lastCol);

		List<String> columnsNameList = new ArrayList<String>();
		List<String> growthList = new ArrayList<String>();
		List<String> growthMonthList= new ArrayList<String>();
		for (int i = currentCol; i <= lastCol; i++) {
			columnsNameList.add(tab.getCellText(1, i));
			growthMonthList.add(tab.getCellText(1, i));
			growthList.add(Util.removeCommas(tab.getCellText(2, i)));
		}
		CustomReporter.report(STATUS.INFO, "Month List "+growthMonthList);
		CustomReporter.report(STATUS.INFO, "Growth List "+growthList);

		CustomReporter.createNode("Verifying the effects on Edit Market Volume page");
		
		DTSummary_EditMarketVolumeP5 editMarketVol= new DTSummary_EditMarketVolumeP5();
		editMarketVol.NAVIGATE_To_EditMarketVolume();
		List<String> futureMonthSumValues=editMarketVol.getSumRowValues(currentMonth,lastMonthCol);

		cal.add(Calendar.YEAR,-1);
		String currentMonthPastYear=Util.convertToString("MMM yy", cal.getTime());
		cal.setTime(Util.convertToDate("MMM yy", lastMonthCol));
		cal.add(Calendar.YEAR,-1);
		String lastMonthColPastYear=Util.convertToString("MMM yy", cal.getTime());
		List<String> pastMonthSumValues=editMarketVol.getSumRowValues(currentMonthPastYear,lastMonthColPastYear);

		for (int i = 0; i < pastMonthSumValues.size(); i++) {
			String pastMonthSumVal=pastMonthSumValues.get(i);
			String futureMonthSumVal=futureMonthSumValues.get(i);

			String valueCalc="0";
			try{
				valueCalc=Util.BD(futureMonthSumVal).divide(Util.BD(pastMonthSumVal),5,RoundingMode.HALF_EVEN).toPlainString();
				valueCalc=Util.BD(valueCalc).subtract(Util.BD("1")).toPlainString();
				valueCalc=Util.BD(valueCalc).multiply(Util.BD("100")).toPlainString();
			}catch (Exception e) {}

			String valueOnPage=growthList.get(i).substring(0,growthList.get(i).length()-1);

			Util.comparator(valueCalc, valueOnPage, "Country level Market Growth for "+growthMonthList.get(i));
		}	
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(editMarketVol.button_Cancel);	
	}

	public void ForecastOperatorLevelGrowthEnableBulkUpdateForAllServicesAndEventTypesFE_7233() {
		/*
		 * 1. Login as Nextgen user 
		 * 2. Go to Forecasting-> Deal Tracker ->Deal tracker Module> Deal Tracker summary(P4) 
		 * 3. Select Home Operator 
		 * 4. Select Traffic Direction, country, services, events "
		 * 5. Click on refresh report "
		 */

		JSONManager json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7233", "VoiceMT");
		ForecastOperatorLevelGrowthEnableBulkUpdateForAllServicesAndEventTypesFE_7233_COMMON(json);

		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7233", "VoiceMO");
		ForecastOperatorLevelGrowthEnableBulkUpdateForAllServicesAndEventTypesFE_7233_COMMON(json);

		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7233", "SmsMO");
		ForecastOperatorLevelGrowthEnableBulkUpdateForAllServicesAndEventTypesFE_7233_COMMON(json);

		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7233", "DataMB");
		ForecastOperatorLevelGrowthEnableBulkUpdateForAllServicesAndEventTypesFE_7233_COMMON(json);
	}
	
	/**@author prafull.barve*/
	public String perform_BulkUpdateOperatorLevelGrowth(int RowNum, String effectiveFrom, String forecastValue)
	{
		String message=" | ";
		String operatorNameUpdated="";
		com.click(hideshowbutton_BulkUpdateForecastOperatorLevelGrowth,"hide show button_Bulk Update Forecast Operator Level Growth");

		if (effectiveFrom!=null)
		{
			com.sendKeys(text_EffectiveFrom_ForecastOperatorLevel, effectiveFrom);
			message+="effectiveFrom: "+effectiveFrom+" | ";
		}
		
		if(forecastValue!=null)
		{
			WebTable tab= new WebTable(table_BulkUpdateForecastOperatorLevel);
			int partnerNameColumnNum=tab.initHeaderColumnList(1).getColumnNumber("Partner Name");
			int forecastColumnNum=tab.getColumnNumber("Forecast Growth (%)");
	
			operatorNameUpdated=tab.getCellText(RowNum, partnerNameColumnNum);
			
			com.sendKeys(tab.getChildObject(RowNum, forecastColumnNum, "input", 0), forecastValue);
			message+="forecastValue: "+forecastValue+" | ";
			message+="operatorNameUpdated: "+operatorNameUpdated+" | ";
		}
		
		
		CustomReporter.report(STATUS.INFO, "Modifying following fields: "+message);
		com.click(button_BulkUpdateForecastGrowth_OperatorLevel,"button_Bulk Update Forecast Growth_Operator Level");
		
		return operatorNameUpdated;
	}
	
	/**@author prafull.barve*/
	public String get_OperatorLevelGrowth_EffectiveDate() {
		com.click(hideshowbutton_BulkUpdateForecastOperatorLevelGrowth,"hide show button_Bulk Update Forecast Operator Level Growth");
		String effectiveDate=com.getAttribute(text_EffectiveFrom_ForecastOperatorLevel, "value");
		return effectiveDate;
	}

	private void ForecastOperatorLevelGrowthEnableBulkUpdateForAllServicesAndEventTypesFE_7233_COMMON(JSONManager json) {

		CustomReporter.createNode("For "+Arrays.toString(json.getStrArr("ServiceType"))+" , "+Arrays.toString(json.getStrArr("EventType")));

		performSearch(json);

		/*
		 * 6. Click on "Bulk Update Forecast Operator Level Growth" 
		 * 7. Update Growth for Voice MT of partner "A1 Telekom Austria AG" For April 2018
		 * & click on Bulk update Forecast growth button 
		 * Bulk update 36% for April 2018 for PMN (AUTMK, AUTON, AUTPT) 
		 * 8. Check growth update for one future month Reflected to all future months
		 */
		
		CustomReporter.createNode("Clicking on Bulk Update Operator level growth button to resetting changes in the value before performing calculations");
		perform_BulkUpdateOperatorLevelGrowth(0, null, null);
		
		
		CustomReporter.createNode("Doing Bulk update at Operator Level Growth with Forecast value ");
		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7233", "VoiceMT");
		String forecastValue=json.getStr("Forecast Growth (%)");
		
		String operatorNameUpdated=perform_BulkUpdateOperatorLevelGrowth(2, null, forecastValue);
		
		String effectiveMonthColName=Util.convertToString("MMM yy", Util.convertToDate("dd-MMM-yyyy", get_OperatorLevelGrowth_EffectiveDate()));

		WebTable tab= new WebTable(table_OperatorLevel);
		tab.initHeaderColumnList(1);
		int affectedRowNum=tab.getRowWithCellText(operatorNameUpdated);
		int lastCol=tab.getColCount(1);
		String lastMonthColName=tab.getCellText(1, lastCol);
		boolean flag=true;
		for (int i = tab.getColumnNumber(effectiveMonthColName); i <= lastCol; i++) {
			String temp=tab.getCellText(affectedRowNum, i);
			if(!temp.contains(forecastValue)){
				CustomReporter.report(STATUS.FAIL, "For month " + tab.getCellText(1, i) + " updated value "
						+ forecastValue + " is not displayed but " + temp + " is getting displayed");
				flag=false;
			}
		}

		if (flag) {
			CustomReporter.report(STATUS.PASS, "Updated growth is reflecting on all months after effective date for Roaming Partner["+operatorNameUpdated+"]");
		}

		/*
		 * 9. Now check Market Volume at market volume report for April 2018
		 * "Use Formula = Market Volume for previous year for same month * (Bulk update % + 1) 
		 * Market volume for previous year April 2017 = 12,141 
		 * = 12141 * (0.36+1) = 16,512"
		 */
		Calendar cal= Calendar.getInstance();
		cal.setTime(Util.convertToDate("MMM yy",lastMonthColName));
		cal.add(Calendar.YEAR, -1);
		String lastMonthColName_PastYear=Util.convertToString("MMM yy", cal.getTime());
		cal.setTime(Util.convertToDate("MMM yy",effectiveMonthColName));
		cal.add(Calendar.YEAR, -1);
		String effectiveMonthColName_PastYear=Util.convertToString("MMM yy", cal.getTime());

		CustomReporter.createNode("Navigating to Edit Market volume & taking market volume from table & Calculating Market Volume as per Operator level growth changes");
		
		DTSummary_EditMarketVolumeP5 editMarketVolumeP5=new DTSummary_EditMarketVolumeP5();
		editMarketVolumeP5.NAVIGATE_To_EditMarketVolume();
		List<String> marketVolList=editMarketVolumeP5.getRowValuesForPassedOperator(operatorNameUpdated,effectiveMonthColName, lastMonthColName);
		List<String> marketVolList_PastYear=editMarketVolumeP5.getRowValuesForPassedOperator(operatorNameUpdated,effectiveMonthColName_PastYear, lastMonthColName_PastYear);
		List<String> marketVolEffectiveMonthColList=editMarketVolumeP5.getPassedColumnList(effectiveMonthColName);
		List<String> marketVolEffectiveMonthColList_PastYear=editMarketVolumeP5.getPassedColumnList(effectiveMonthColName_PastYear);
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(editMarketVolumeP5.button_Cancel,"Cancel Button");
		List<String> marketVolList_Calc= new ArrayList<String>();
		for (int i = 0; i < marketVolList.size(); i++) {
			String mvCalc="0";
			try{
				mvCalc=Util.BD(forecastValue).divide(Util.BD("100")).toPlainString();
				mvCalc=Util.BD(mvCalc).add(Util.BD("1")).toPlainString();
				mvCalc=Util.BD(mvCalc).multiply(Util.BD(marketVolList_PastYear.get(i))).toPlainString();
			}catch (Exception e) {}
			marketVolList_Calc.add(Util.Round(mvCalc,5));
		}

		Util.comparator_List(marketVolList_Calc, marketVolList, "Market Volume as per Changes in Operator level growth");


		/*
		 * 10. Now check at Tap Charge 
		 * Tap charge = Tap Rate * Market Volume for the same month April 2018
		 */

		CustomReporter.createNode("As per Operator level growth changes Calculating Tap Charge");
		DTSummary_Charges charges=new DTSummary_Charges();
		List<String> tapRateList = charges.getTapRateForPassedOperator(operatorNameUpdated,effectiveMonthColName, lastMonthColName);
		List<String> tapChargeList = charges.getTapChargeForPassedOperator(operatorNameUpdated,effectiveMonthColName, lastMonthColName);

		List<String> tapCharge_Calc=new ArrayList<String>();
		for (int i = 0; i < tapRateList.size(); i++) {
			String temp=Util.BD(tapRateList.get(i)).multiply(Util.BD(marketVolList_Calc.get(i))).toPlainString();
			tapCharge_Calc.add(Util.Round(temp,0));
		}

		Util.comparator_List(tapCharge_Calc, tapChargeList, "TAP Charge as per updated Operator level growth");


		/*
		 * 11. As Operator level growth manipulated check proportional share
		 * 12. Check Proportional share for the month April 2018 
		 * */

		CustomReporter.createNode("Navigating to Edit Proportional Share & Calculating Proportional Share as per opertor level growth changes ");
		DTSummary_EditProportionalShareP10 editProportionalShareP10=new DTSummary_EditProportionalShareP10();
		editProportionalShareP10.NAVIGATE_To_EditProportionalShare();
		List<String> propShareEffectiveMonthColList=editProportionalShareP10.getPassedColumnList(effectiveMonthColName);
		List<String> propShareEffectiveMonthColList_Calc=new ArrayList<String>();
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(editProportionalShareP10.button_Cancel);
		int size=marketVolEffectiveMonthColList.size();
		for (int i = 0; i < size-1; i++) {
			String temp="0";
			try{
				temp=Util.BD(marketVolEffectiveMonthColList.get(i)).divide(Util.BD(marketVolEffectiveMonthColList.get(size-1)),7,RoundingMode.HALF_EVEN).toPlainString();
				temp=Util.BD(temp).multiply(Util.BD("100")).toPlainString();
			}catch (Exception e) {}
			propShareEffectiveMonthColList_Calc.add(Util.Round(temp,5));
		}

		Util.comparator_List(propShareEffectiveMonthColList_Calc, propShareEffectiveMonthColList, "Updates in proportional share as Operator level growth manipulated");

		/*
		 * 13. Check country level Growth for the inbound voice MT for April 2018 
		 * "Calculate Coutnry level growth for vocie MT april 2018 Use Formula 
		 * = (( sum of the Market volume for april 2018 / sum of the market volume for april 2017)-1)*100) 
		 * = (( 126,613/71,020)-1)*100 
		 * = 78%" 
		 */
		CustomReporter.createNode("Navigating to View Inbound Growth Page & Calculating Country level growth");
		DTSummary_ViewInboundGrowthP24 viewInboundGrowthP24=new DTSummary_ViewInboundGrowthP24();
		viewInboundGrowthP24.navigateTo_ViewInboundGrowth();
		String countryLevelGrowth=viewInboundGrowthP24.getPassedColumnGrowth(effectiveMonthColName);
		com.click(viewInboundGrowthP24.button_Cancel);
		size=marketVolEffectiveMonthColList_PastYear.size()-1;
		String countryLevelGrowth_Calc ="0";
				
		try{
			countryLevelGrowth_Calc=Util.BD(marketVolEffectiveMonthColList.get(size)).divide(Util.BD(marketVolEffectiveMonthColList_PastYear.get(size)), 7, RoundingMode.HALF_EVEN).toPlainString();
			countryLevelGrowth_Calc = Util.BD(countryLevelGrowth_Calc).subtract(Util.BD("1")).toPlainString();
			countryLevelGrowth_Calc = Util.BD(countryLevelGrowth_Calc).multiply(Util.BD("100")).toPlainString();
			countryLevelGrowth_Calc = Util.Round(countryLevelGrowth_Calc, 5);
		}catch (Exception e) {}
		
		Util.comparator(countryLevelGrowth_Calc, countryLevelGrowth, "Country Level Growth");
	}


	public void ForecastOperatorLevelGrowthAddgrowthforallservicesandeventtypesinreport_FE_7231() {

		JSONManager json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7231","InboundDirection");
		performSearch(json);
		
		CustomReporter.createNode("Opertor Level table data");
		
		VerifyServicesOnTable(table_OperatorLevel, "Opertor Level table");
	}

	public void ForecastOperatorLevelGrowthEnableManualEditForAllServicesAndEventTypesFE_7234() {

		JSONManager json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7234", "VoiceMT");
		ForecastOperatorLevelGrowthEnableManualEditForAllServicesAndEventTypesFE_7234_COMMON(json);

		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7234", "VoiceMO");
		ForecastOperatorLevelGrowthEnableManualEditForAllServicesAndEventTypesFE_7234_COMMON(json);

		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7234", "SmsMO");
		ForecastOperatorLevelGrowthEnableManualEditForAllServicesAndEventTypesFE_7234_COMMON(json);

		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7234", "DataMB");
		ForecastOperatorLevelGrowthEnableManualEditForAllServicesAndEventTypesFE_7234_COMMON(json);

	}

	private void ForecastOperatorLevelGrowthEnableManualEditForAllServicesAndEventTypesFE_7234_COMMON(JSONManager json) {
		
		CustomReporter.createNode("For "+Arrays.toString(json.getStrArr("ServiceType"))+" , "+Arrays.toString(json.getStrArr("EventType")));
		
		/*
		 * 4. Select Traffic Direction, country, services, events 
		 * "Traffic Direction :- Inbound 
		 * Country :- Austria 
		 * Service Type:- Voice 
		 * Event Type :- MT 
		 * 5. Click on refresh report
		 */

		performSearch(json);

		
		json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7234");
		String forecastValue=json.getStr("BulkUpdateValue");
		perform_BulkUpdateOperatorLevelGrowth(2, null, forecastValue);
		
		
		/*
		 * 6. Click on Edit Operator Growth 
		 * 7. Check Title of page (p8) 
		 * 8. Check Region Name on page (p8) 
		 * 9. Change or edit values for each & every services monthly basis & 
		 * Click on Save changes monthly growth 
		 * 10. Update Manual Growth for Voice MT of partner "AUTAT" For April 2018 &
		 * Click on Save changes monthly growth 
		 * Manual update 63% for April 2018 for PMN (AUTMK, AUTON, AUTPT)
		 */
		Calendar cal=Calendar.getInstance();
		cal.setTime(Util.convertToDate("dd-MMM-yyyy", get_OperatorLevelGrowth_EffectiveDate()));
		cal.add(Calendar.MONTH, 1);
		String futureMonth=Util.convertToString("MMM yy", cal.getTime());
		cal.add(Calendar.YEAR, -1);
		String pastMonth=Util.convertToString("MMM yy", cal.getTime());

		WebTable opLvlTab=new WebTable(table_OperatorLevel);
		int roamingPartnerCol=opLvlTab.initHeaderColumnList(1).getColumnNumber("Roaming Partner");
		String roamingPartner=opLvlTab.getCellText(2, roamingPartnerCol);
		String lastMonth=opLvlTab.getCellText(1, opLvlTab.getColCount(1));

		List<String> futureMonthData_BeforeUpdate=getListofPassedColumns(roamingPartner,futureMonth,lastMonth);
		DTSummary_EditOperatorGrowthP8 editOpGrowth= new DTSummary_EditOperatorGrowthP8();

		CustomReporter.createNode("Updating Operator Level Monthly Growth");
		
		String opLvlGrowth=json.getStr("Edit Op Lvl Monthly Growth (%)");
		editOpGrowth.UpdateOpLvlMonthlyGrowth_7234(roamingPartner,futureMonth,opLvlGrowth);

		/*
		 * 11. Check growth update for one future month should not affect to all future
		 * months(Imp Check)
		 */
		CustomReporter.createNode("Check growth update for one future month should not affect to all future months(Imp Check)");
		
		List<String> futureMonthData_AfterUpdate=getListofPassedColumns(roamingPartner,futureMonth,lastMonth);
		futureMonthData_BeforeUpdate.remove(0);
		futureMonthData_AfterUpdate.remove(0);
		Util.comparator_List(futureMonthData_AfterUpdate, futureMonthData_BeforeUpdate, "Growth update for one future month "+futureMonth+" is not affecting values of next months till "+lastMonth);

		/*
		 * 12. Now check Market Volume at market volume report for April 2018
		 * "Use Formula 
		 * = Market Volume for previous year for same month * (Bulk update % + 1) Market volume for previous year April 2017 = 12,141 
		 * = 910 * (0.63+1) 
		 * = 1,483"
		 */

		CustomReporter.createNode("check Market Volume at market volume report,Use Formula = Market Volume for previous year for same month * (Bulk update % + 1) Market volume for previous year");
		
		DTSummary_EditMarketVolumeP5 editMarketVolumeP5=new DTSummary_EditMarketVolumeP5();
		editMarketVolumeP5.NAVIGATE_To_EditMarketVolume();

		List<String> marketVolForFutMonthCol=editMarketVolumeP5.getPassedColumnList(futureMonth);
		List<String> marketVolForFutMonthCol_PastYear=editMarketVolumeP5.getPassedColumnList(pastMonth);

		String marketVol_OnPage=editMarketVolumeP5.getRowValuesForPassedOperator(roamingPartner, futureMonth, futureMonth).get(0);
		String marketVol_PastYear=editMarketVolumeP5.getRowValuesForPassedOperator(roamingPartner, pastMonth, pastMonth).get(0);

		String marketVol_Calc="0";
		try{
			marketVol_Calc=Util.BD(opLvlGrowth).divide(Util.BD("100")).add(Util.BD("1")).toPlainString();
			marketVol_Calc=Util.BD(marketVol_Calc).multiply(Util.BD(marketVol_PastYear)).toPlainString();
			marketVol_Calc=Util.Round(marketVol_Calc, 5);
		}catch (Exception e) {}
		Util.comparator(marketVol_Calc, marketVol_OnPage, "Market Volume for ["+futureMonth+"] as per updated Operator Growth ["+opLvlGrowth+"]");
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(editMarketVolumeP5.button_Cancel, "Cancel button");

		/*
		 * 13. Now check at Tap Charge Tap charge 
		 * = Tap Rate * Market Volume for the same month April 2018
		 */
		CustomReporter.createNode("Now check at Tap Charge, Tap charge = Tap Rate * Market Volume for the same month");
		
		DTSummary_Charges charges= new DTSummary_Charges();
		String tapRate=charges.getTapRateForPassedOperator(roamingPartner, futureMonth, futureMonth).get(0);
		String tapCharge_OnPage=charges.getTapChargeForPassedOperator(roamingPartner, futureMonth, futureMonth).get(0);

		String tapCharge_Calc=Util.BD(marketVol_Calc).multiply(Util.BD(tapRate)).toPlainString();
		Util.comparator(tapCharge_Calc, tapCharge_OnPage, "TAP Charges for ["+futureMonth+"] as per updated Operator Growth ["+opLvlGrowth+"]");

		/*
		 * 14. As Operator level growth manipulated check proportional share 
		 * 15. Check Proportional share for the month April 2018
		 */
		CustomReporter.createNode("As Operator level growth manipulated check proportional share");
		
		DTSummary_EditProportionalShareP10 propShare=new DTSummary_EditProportionalShareP10();
		propShare.NAVIGATE_To_EditProportionalShare();
		List<String> propShareForFutMonth=propShare.getPassedColumnList(futureMonth);
		com.click(propShare.button_Cancel,"button_Cancel");

		List<String> propShareForFutMonth_Calc=new ArrayList<String>();
		int lastIndex=marketVolForFutMonthCol.size()-1;

		for (int i = 0; i < lastIndex; i++) {
			String temp="0";
			try{
				temp=Util.BD(marketVolForFutMonthCol.get(i)).divide(Util.BD(marketVolForFutMonthCol.get(lastIndex)), 7, RoundingMode.HALF_EVEN).toPlainString();
				temp=Util.BD(temp).multiply(Util.BD("100")).toPlainString();
			}catch (Exception e) {}
			propShareForFutMonth_Calc.add(temp);
		}

		Util.comparator_List(propShareForFutMonth_Calc, propShareForFutMonth, "Proportional Share for ["+futureMonth+"] as per updated Operator Growth ["+opLvlGrowth+"]");


		/*
		 * 17. Check country level Growth for the inbound voice MT for April
		 * 2018 
		 * "Calculate Coutnry level growth for vocie MT april 2018 Use
		 * Formula 
		 * =(( sum of the Market volume for april 2018 / sum of the
		 * market volume for april 2017)-1)*100) 
		 * =(( 125,591/71,020)-1)*100 
		 * =77%"
		 * 
		 */

		CustomReporter.createNode("Check country level Growth, Formula  =(( sum of the Market volume for april 2018 / sum of the market volume for april 2017)-1)*100) ");

		String countryLvlGrowth_Calc="0";
		try{
			countryLvlGrowth_Calc=Util.BD(marketVolForFutMonthCol.get(lastIndex)).divide(Util.BD(marketVolForFutMonthCol_PastYear.get(lastIndex)), 7, RoundingMode.HALF_EVEN).toPlainString();
			countryLvlGrowth_Calc=Util.BD(countryLvlGrowth_Calc).subtract(Util.BD("1")).toPlainString();
			countryLvlGrowth_Calc=Util.BD(countryLvlGrowth_Calc).multiply(Util.BD("100")).toPlainString();
		}catch (Exception e) {}
		DTSummary_ViewInboundGrowthP24 viewInboundGrowthP24=new DTSummary_ViewInboundGrowthP24();
		viewInboundGrowthP24.navigateTo_ViewInboundGrowth();
		String countryLvlGrowth_onPage=viewInboundGrowthP24.getPassedColumnGrowth(futureMonth);
		com.click(viewInboundGrowthP24.button_Cancel, "button_Cancel");
		Util.comparator(countryLvlGrowth_Calc, countryLvlGrowth_onPage, "Country Level Growth for ["+futureMonth+"] as per updated Operator Growth ["+opLvlGrowth+"]");

	}




	public void ForecastCountryLevelGrowthAddVoiceMOgrowthInreportFE_7219() 
	{
		/*
		 * 1.Select Home Operator Home Operator: NLDPT 2.Select Traffic
		 * Direction, country, services, events
		 * 
		 * Traffic Direction :- select both direction Country :- Austria Service
		 * Type:- All Event Type :- All"
		 */

		JSONManager json = new JSONManager(KPN_DealTracker7216.jsonFilePath, "7219","BothDirection");
		performSearch(json);

		CustomReporter.createNode("Verifying Outbound Country Level Table Data");
		
		VerifyServicesOnTable(table_OutboundCountryLevel,"Outbound Country Level Table");

		CustomReporter.createNode("Outbound Country Level csv download");
		
		com.click(link_DownloadOutboundMarketGrowthCountryLevel, "Download OutBound Market Growth Country Level");
		String FilePath = Util.getDownloadedFilePath("Outbound Country Level");
		if (FilePath != null) {
			CustomReporter.report(STATUS.PASS, "File Downloaded Successfully "+FilePath);
			CsvContentVerificationLoop(table_OutboundCountryLevel, FilePath);
		} else {
			CustomReporter.report(STATUS.FAIL, "File Not Downloaded");
		}

		CustomReporter.createNode("Verifying Inbound Country Level table Data");
		
		VerifyServicesOnTable(table_InboundCountryLevel,"Inbound Country Level table");
		
		CustomReporter.createNode("Inbound Country Level csv download");
		
		com.click(link_DownloadInboundMarketGrowthCountryLevel, "Download Inbound Market Growth Country Level");
		String FilePath1 = Util.getDownloadedFilePath("Inbound Country Level");
		if (FilePath != null) {
			CustomReporter.report(STATUS.PASS, "File Downloaded Successfully "+FilePath1);
			CsvContentVerificationLoop(table_InboundCountryLevel, FilePath1);
		} else {
			CustomReporter.report(STATUS.FAIL, "File Not Downloaded");
		}	

	}

	protected void VerifyServicesOnTable(By table_object, String Message ) {

		WebElement tdObj=com.getDynamicElement(table_object, By.xpath("tbody/tr/td[contains(.,'Voice')]//parent::tr//td[contains(.,'MT')]"));
		if (tdObj!=null) {
			CustomReporter.report(STATUS.PASS, "Voice MT is displayed in " +Message);
		} else {
			CustomReporter.report(STATUS.FAIL, "Voice MT is not displayed in " +Message);
		}

		tdObj=com.getDynamicElement(table_object, By.xpath("tbody/tr/td[contains(.,'Voice')]//parent::tr//td[contains(.,'MO')]"));
		if (tdObj!=null) {
			CustomReporter.report(STATUS.PASS, "Voice MO is displayed in " +Message);
		} else {
			CustomReporter.report(STATUS.FAIL, "Voice MO is not displayed in " +Message);
		}

		tdObj=com.getDynamicElement(table_object, By.xpath("tbody/tr/td[contains(.,'SMS')]//parent::tr//td[contains(.,'MO')]"));
		if (tdObj!=null) {
			CustomReporter.report(STATUS.PASS, "SMS MO is displayed in " +Message);
		} else {
			CustomReporter.report(STATUS.FAIL, "SMS MO is not displayed in " +Message);
		}

		tdObj=com.getDynamicElement(table_object, By.xpath("tbody/tr/td[contains(.,'Voice')]//parent::tr//td[contains(.,'MT')]"));
		if (tdObj!=null) {
			CustomReporter.report(STATUS.PASS, "SMS MT is displayed in " +Message);
		} else {
			CustomReporter.report(STATUS.FAIL, "SMS MT is not displayed in " +Message);
		}

		tdObj=com.getDynamicElement(table_object, By.xpath("tbody/tr/td[contains(.,'Data')]//parent::tr//td[contains(.,'MB')]"));
		if (tdObj!=null) {
			CustomReporter.report(STATUS.PASS, "DATA MB is displayed in " +Message);
		} else {
			CustomReporter.report(STATUS.FAIL, "DATA MB is not displayed in " +Message);
		}

	}

	private List<String> getListofPassedColumns(String roamingPartner,String startMonth, String lastMonth) {
		List<String> rowWiseMonthData=new ArrayList<String>();
		WebTable table=new WebTable(table_OperatorLevel);
		table.initHeaderColumnList(1);
		int startCol=table.getColumnNumber(startMonth);
		int lastCol=table.getColumnNumber(lastMonth);
		int rowNum=table.getRowWithCellText(roamingPartner);

		for (int colNum = startCol; colNum <= lastCol; colNum++) {
			int inputObjCount=table.getChildObjectsCount(rowNum, colNum, "input");
			if (inputObjCount>0) {
				rowWiseMonthData.add(Util.removeCommas(com.getAttribute(table.getChildObject(rowNum, colNum, "input",0),"value")));
			}else{
				rowWiseMonthData.add(Util.removeCommas(table.getCellText(rowNum, colNum)));
			}
		}
		CustomReporter.report(STATUS.INFO, "In Market Growth tab : For roamingPartner ["+roamingPartner+"] and month ["+startMonth+"] to ["+lastMonth+"], the row values are "+rowWiseMonthData);
		return rowWiseMonthData;
	}

}
