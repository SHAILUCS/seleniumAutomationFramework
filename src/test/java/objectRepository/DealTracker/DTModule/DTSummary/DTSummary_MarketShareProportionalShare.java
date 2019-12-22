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

public class DTSummary_MarketShareProportionalShare extends DTSummary_Header {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = DTSummary_Header.title;

	public DTSummary_MarketShareProportionalShare() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h2[contains(.,'Market Share / Proportional Share')]")
	private WebElement data_MarketShareProportionalShare_Section;

	public void verify_UI() {
		com.isElementPresent(data_MarketShareProportionalShare_Section,
				"Market Share / Proportional Share - Main Section heading");
		verifySection_BulkUpdateForecastMarketShare();
		verifySection_MarketShare();
		verifySection_BulkUpdateProportionalMarketShare();
		verifySection_ProportionalShare();
	}

	@FindBy(xpath = "//h2[contains(.,'Bulk Update Forecast Market Share')]")
	private WebElement data_BulkUpdateForecastMarketShare_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Bulk Update Forecast Market Share')]//button[contains(@class,'hideShow')]")
	private WebElement icon_Section_BulkUpdateForecastMarketShare_HideShow;

	@FindBy(id = "P4_EFFECTIVE_FROM_MS")
	private WebElement text_EffectiveFrom_ForecastMarketShare;

	@FindBy(id = "P4_NOTE_MARKET_SHARE")
	private WebElement text_Note_ForecastMarketShare;

	@FindBy(xpath = "//button[contains(.,'Bulk Update Forecast Market Share')]")
	private WebElement button_BulkUpdateForecastMarketShare_ForecastMarketShare;

	private By table_ForecastMarketShare = By.cssSelector("table[summary='Bulk Update Forecast Market Share']");

	private List<String> colNames_ForecastMarketShare = new ArrayList<>(
			Arrays.asList("Partner Name", "Partner PLMN", "Service", "Event", "Direction", "Market Share (%)"));

	private void verifySection_BulkUpdateForecastMarketShare() {
		CustomReporter.report(STATUS.INFO, "Bulk Update Forecast Market Share Sub-Section verification");
		com.isElementPresent(data_BulkUpdateForecastMarketShare_Section,
				"Bulk Update Forecast Market Share Sub-Section heading");
		com.isElementPresent(icon_Section_BulkUpdateForecastMarketShare_HideShow,
				"Bulk Update Forecast Market Share Sub-Section Hide/Show icon");

		com.click_UsingAction(icon_Section_BulkUpdateForecastMarketShare_HideShow);
		com.isElementPresent(text_EffectiveFrom_ForecastMarketShare, "Effective From field");
		com.isElementPresent(text_Note_ForecastMarketShare, "Note field");
		com.isElementPresent(button_BulkUpdateForecastMarketShare_ForecastMarketShare,
				"Bulk Update Forecast Market Share button");

		//comm.printColumnHeaders(table_ForecastMarketShare, 1);
		comm.verifyColumnHeaders(table_ForecastMarketShare, colNames_ForecastMarketShare, 1);
	}

	@FindBy(xpath = "//h2[contains(.,'Market Share')]")
	private WebElement data_MarketShare_Section;

	@FindBy(xpath = "//button[contains(.,'Edit Monthly Market Share')]")
	protected WebElement button_EditMonthlyMarketShare;

	private By table_MarketShare = By.cssSelector("table[summary='Market Share']");

	private List<String> colNames_MarketShare = new ArrayList<>(
			Arrays.asList("Roaming Partner", "Partner PMN", "Country Name", "Traffic Direction", "Service Type",
					"Event Type", "Average 2018 Market Share", "Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18",
					"Jun 18", "Jul 18", "Aug 18", "Sep 18", "Oct 18", "Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19",
					"Apr 19", "May 19", "Jun 19", "Jul 19", "Aug 19", "Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download Market Share")
	private WebElement link_DownloadMarketShare;
	
	@FindBy(xpath = "//table[@summary='Market Share']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination_MarketShare;

	private void verifySection_MarketShare() {
		CustomReporter.report(STATUS.INFO, "Market Share Sub-Section verification");
		com.isElementPresent(data_MarketShare_Section, "Market Share  Sub-Section heading");
		com.isElementPresent(button_EditMonthlyMarketShare, "Edit Monthly Market Share button");

		//comm.printColumnHeaders(table_MarketShare, 1);
		comm.verifyColumnHeaders(table_MarketShare, colNames_MarketShare, 1);
		com.isElementPresent(link_DownloadMarketShare, "Download Market Share link");
		
		com.isElementPresent(data_Pagination_MarketShare, "Pagination data "+com.getText(data_Pagination_MarketShare));
	}

	@FindBy(xpath = "//h2[contains(.,'Bulk Update Proportional Market Share')]")
	private WebElement data_BulkUpdateProportionalMarketShare_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Bulk Update Proportional Market Share')]//button[contains(@class,'hideShow')]")
	private WebElement icon_Section_BulkUpdateProportionalMarketShare_HideShow;

	@FindBy(id = "P4_EFFECTIVE_FROM_PS")
	private WebElement text_EffectiveFrom_ProportionalMarketShare;

	@FindBy(id = "P4_NOTE_PROPORTIONAL_SHARE")
	private WebElement text_Note_ProportionalMarketShare;

	@FindBy(xpath = "//button[contains(.,'Bulk Update Proportional Market Share')]")
	private WebElement button_BulkUpdateProportionalMarketShare_ProportionalMarketShare;

	private By table_ProportionalMarketShare = By.cssSelector("table[summary='Bulk Update Proportional Market Share']");

	private List<String> colNames_ProportionalMarketShare = new ArrayList<>(
			Arrays.asList("Partner Name", "Partner PLMN", "Service", "Event", "Direction", "Proportional Share (%)"));

	private void verifySection_BulkUpdateProportionalMarketShare() {
		CustomReporter.report(STATUS.INFO, "Bulk Update Forecast Operator Level Growth Sub-Section verification");
		com.isElementPresent(data_BulkUpdateProportionalMarketShare_Section,
				"Bulk Update Proportional Market Share Sub-Section heading");

		com.isElementPresent(icon_Section_BulkUpdateProportionalMarketShare_HideShow,
				"Bulk Update Proportional Market Share Sub-Section Hide/Show icon");

		com.click_UsingAction(icon_Section_BulkUpdateProportionalMarketShare_HideShow);
		com.isElementPresent(text_EffectiveFrom_ProportionalMarketShare, "Effective From field");
		com.isElementPresent(text_Note_ProportionalMarketShare, "Note field");
		com.isElementPresent(button_BulkUpdateProportionalMarketShare_ProportionalMarketShare,
				"Bulk Update Proportional Market Share button");

		//comm.printColumnHeaders(table_ProportionalMarketShare, 1);
		comm.verifyColumnHeaders(table_ProportionalMarketShare, colNames_ProportionalMarketShare, 1);
	}

	@FindBy(xpath = "//h2[contains(.,'Proportional Share')]")
	private WebElement data_ProportionalShare_Section;

	@FindBy(xpath = "//button[contains(.,'Edit Monthly Proportional Share')]")
	protected WebElement button_EditMonthlyProportionalShare_ProportionalShare;

	protected By table_ProportionalShare = By.cssSelector("table[summary='Proportional Share']");

	private List<String> colNames_ProportionalShare = new ArrayList<>(
			Arrays.asList("Roaming Partner", "Partner PMN", "Country Name", "Traffic Direction", "Service Type",
					"Event Type", "Average 2018 Proportional Share", "Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18",
					"Jun 18", "Jul 18", "Aug 18", "Sep 18", "Oct 18", "Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19",
					"Apr 19", "May 19", "Jun 19", "Jul 19", "Aug 19", "Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download Proportional Share")
	private WebElement link_DownloadProportionalShare;
	
	@FindBy(xpath = "//table[@summary='Proportional Share']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination_ProportionalShare;

	private void verifySection_ProportionalShare() {
		CustomReporter.report(STATUS.INFO, "Operator Level Sub-Section verification");
		com.isElementPresent(data_ProportionalShare_Section, "Proportional Share Sub-Section heading");

		com.isElementPresent(button_EditMonthlyProportionalShare_ProportionalShare,
				"Edit Monthly Proportional Share button");

		//comm.printColumnHeaders(table_ProportionalShare, 1);
		comm.verifyColumnHeaders(table_ProportionalShare, colNames_ProportionalShare, 1);

		com.isElementPresent(link_DownloadProportionalShare, "Download Proportional Share link");
		
		com.isElementPresent(data_Pagination_ProportionalShare, "Pagination data "+com.getText(data_Pagination_ProportionalShare));
	}

	public void ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForBulkUpdateFE_7237() {

		JSONManager json= new JSONManager(KPN_DealTracker7216.jsonFilePath, "7237","VoiceMo");
		ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForBulkUpdateFE_7237_COMMON(json);

		json= new JSONManager(KPN_DealTracker7216.jsonFilePath, "7237","VoiceMT");
		ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForBulkUpdateFE_7237_COMMON(json);

		json= new JSONManager(KPN_DealTracker7216.jsonFilePath, "7237","SmsMO");
		ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForBulkUpdateFE_7237_COMMON(json);

		json= new JSONManager(KPN_DealTracker7216.jsonFilePath, "7237","DataMB");
		ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForBulkUpdateFE_7237_COMMON(json);
		
	}
	
	private void ProportionalMarketShareRemoveCorrelationBetweenVoiceMoAndOtherServicesForBulkUpdateFE_7237_COMMON(JSONManager json) {
		
		CustomReporter.createNode("For "+Arrays.toString(json.getStrArr("ServiceType"))+" , "+Arrays.toString(json.getStrArr("EventType")));
		
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
		 * 6. Click on Bulk Update Proportional Market Share 
		 * 7. Now select April month enter % for voice MO across all PMN's & click on Bulk Update Proportional Market Share button 
		 * "Select Month April 2018 Change Bulk update for pmn's 
		 * AUTMK, AUTON, AUTPT = 13 
		 * AUTAT = 5 
		 * AUTCA, AUTHU = 80
		 * AUTMM, AUTTR = 2"
		 * 8. Check proportional market share for April 2018 As well as all future month (April Onwards)
		 */
		List<String> rowWiseShareValList = performAndVerify_BulkUpdateProportionalMarketShare();
		
		/*
		 * "9.Check in Market volume for April 2018 Calculate Market volume for All PMN's "	
		 * "Use Formula To Verify Market Volume For April 2018 = Sum of all operator for the month April 2018* Updated Proportional
		 * market share for one PMN 
		 * = 188,626 * 13%(0.13) = 24,521 
		 * = 188,626 * 5%(0.05) = 9,431 
		 * = 188,626* 80%(0.80) = 150,901 
		 * = 188,626* 2%(0.02) = 3,773
		 */
		
		CustomReporter.createNode("Navigating to Edit Market Volume Page & Taking Traffic volume for " +nextMonth+ "and " +nextMonthLastYear);
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
		
		CustomReporter.createNode("Navigating to edit Operator growth & Caculating Operator Growth & then Verifying with displayed page value");
		DTSummary_EditOperatorGrowthP8 editOperatorGrowth= new DTSummary_EditOperatorGrowthP8();
		List<String> operatorGrowthList_NextMonth = editOperatorGrowth.getOperatorLevelGrowthList(nextMonth);
		for (int i = 0; i < operatorGrowthList_NextMonth.size(); i++) {
			String operatorGrowthCalc="0";
			try{
				operatorGrowthCalc=Util.BD(rowWiseMarketVolumeList.get(i)).divide(Util.BD(rowWiseMarketVolumeList_LastYear.get(i)),5,RoundingMode.HALF_EVEN).toPlainString();
				operatorGrowthCalc=Util.BD(operatorGrowthCalc).subtract(Util.BD("1")).toPlainString();
				operatorGrowthCalc=Util.BD(operatorGrowthCalc).multiply(Util.BD("100")).toPlainString();
			}catch (Exception e) {}
				Util.comparator(operatorGrowthCalc, operatorGrowthList_NextMonth.get(i), "Operator growth as per updated proportional share");
		}
		
		
		/*
		 * "11. Check Tap Charge for the same month " 
		 * "Tap Charge = Tap Rate for the month April 2018 for PMN (AUTMK, AUTON, AUTPT )*Market Volume for
		 * the April 2018 for PMN (AUTMK, AUTON, AUTPT ) 
		 * Tap charge = 0.02000 * 24,521 
		 * Tap charge = 490"
		 */
		
		DTSummary_Charges charges=new DTSummary_Charges();
		List<String> tapRateList = charges.getTapRateForPassedColumn(nextMonth);
		
		List<String> tapChargeList = charges.getTapChargeForPassedColumn(nextMonth);
		tapChargeList.remove(tapChargeList.size()-1);
		
		for (int i = 0; i < tapRateList.size(); i++) {
			String tapChargeCalc=Util.BD(tapRateList.get(i)).multiply(Util.BD(rowWiseMarketVolumeList_Calc.get(i))).toPlainString();
			Util.comparator(tapChargeCalc, tapChargeList.get(i), "TAP Charge as per updated proportional share");			
		}
		
	}

	
	private List<String> performAndVerify_BulkUpdateProportionalMarketShare() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String nextMonth = Util.convertToString("MMM yy", cal.getTime());
		String nextMonthEffectiveDate = Util.convertToString("dd-MMM-yyyy", cal.getTime());
		com.click(icon_Section_BulkUpdateProportionalMarketShare_HideShow, "Bulk Update Proportional Market Share_Hide Show button");
		
		
		com.sendKeys(text_EffectiveFrom_ProportionalMarketShare, nextMonthEffectiveDate);
		WebTable table= new WebTable(table_ProportionalMarketShare);
		table.initHeaderColumnList(1);
		int rowCount=table.getRowCount();
		int result=100/(rowCount-1);
		List<String> rowWiseShareValList=new ArrayList<String>();
		for (int i = 2; i <= rowCount; i++) {
			String temp;
			if (i==rowCount) {
				temp=100-result*(rowCount-2)+"";
			}else{
				temp=result+"";
			}
			com.sendKeys(table.getChildObject(i, table.getColumnNumber("Proportional Share (%)"), "input", 0),temp);
			rowWiseShareValList.add(temp);
		}
		
		CustomReporter.report(STATUS.INFO, "Filling up row wise Prop Market Share values: "+rowWiseShareValList);
		
		com.click(button_BulkUpdateProportionalMarketShare_ProportionalMarketShare, "button_Bulk Update Proportional Market Share");
		
		//Verifying that the changes reflect on Proportional Share table
		table= new WebTable(table_ProportionalShare);
		table.initHeaderColumnList(1);
		int startCol=table.getColumnNumber(nextMonth);
		int lastCol=table.getColCount(1);
		rowCount=table.getRowCount();
		boolean flag=true;
		for (int i = 2; i < rowCount; i++) {
			for (int j = startCol; j <= lastCol; j++) {
				String val=table.getCellText(i,j);
				String listVal=rowWiseShareValList.get(i-2);
				if(!val.contains(listVal)){
					CustomReporter.report(STATUS.FAIL, "Value mismatch in Proportional share table for Row: ["+i+"], Column: ["+table.getCellText(1,j)+"] Expected value="+listVal+" Actual displayed="+val);
					flag=false;
				}
			}
		}
		if (flag) {
			CustomReporter.report(STATUS.PASS, "Values in Proportional share table are properly updated as "+rowWiseShareValList);
		}		
		
		return rowWiseShareValList;
	}
}
