package objectRepository.DealTracker.DTModule.DTSummary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;
import tests.MainRegression.KPN_DealTracker7216;

public class DTSummary_MarketVolume extends DTSummary_Header {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = DTSummary_Header.title;
	
	
	public DTSummary_MarketVolume() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h2[contains(.,'Market Volume')]")
	private WebElement data_MarketVolume_Section;

	@FindBy(xpath = "//button[contains(.,'Edit Monthly Volume')]")
	protected WebElement button_EditMonthlyVolume;

	private By table_MarketVolume = By.cssSelector("table[summary='Market Volume']");

	private List<String> colNames_MarketVolume = new ArrayList<>(
			Arrays.asList("Roaming Partner", "Partner PMN", "Country Name", "Traffic Direction", "Service Type",
					"Event Type", "Total 2018 Volume", "Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18", "Jun 18",
					"Jul 18", "Aug 18", "Sep 18", "Oct 18", "Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19", "Apr 19",
					"May 19", "Jun 19", "Jul 19", "Aug 19", "Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download Market Volume")
	private WebElement link_DownloadMarketVolume;
	
	@FindBy(xpath = "//table[@summary='Market Volume']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination;

	public void verify_UI() {
		com.isElementPresent(data_MarketVolume_Section, "Market Volume - Main Section heading");
		com.isElementPresent(button_EditMonthlyVolume, "Edit Monthly Volume button");

		//comm.printColumnHeaders(table_MarketVolume, 1);
		comm.verifyColumnHeaders(table_MarketVolume, colNames_MarketVolume, 1);
		com.isElementPresent(link_DownloadMarketVolume, "Download Market Volume link");
		com.isElementPresent(data_Pagination, "Pagination data "+com.getText(data_Pagination));
	}

	public void marketVolumeAdjustForecastForVoiceMtSmsMoSmsMtAndDataMBservicesBE_7235() {
		
		/*
		 * 3. Select Home Operator 
		 * 4. Select Traffic Direction, country, services, events 
		 * Traffic Direction :- Inbound 
		 * Country :- Austria
		 * Service Type:- Voice 
		 * Event Type :- MT "
		 * 5. Click on refresh report
		 */
		
		JSONManager json=new JSONManager(KPN_DealTracker7216.jsonFilePath, "7235", "VoiceMT");
		marketVolumeAdjustForecastFor_PassedServices(json);

		json=new JSONManager(KPN_DealTracker7216.jsonFilePath, "7235", "VoiceMO");
		marketVolumeAdjustForecastFor_PassedServices(json);

		json=new JSONManager(KPN_DealTracker7216.jsonFilePath, "7235", "SmsMO");
		marketVolumeAdjustForecastFor_PassedServices(json);
		
		json=new JSONManager(KPN_DealTracker7216.jsonFilePath, "7235", "DataMB");
		marketVolumeAdjustForecastFor_PassedServices(json);
		
	}
	private void marketVolumeAdjustForecastFor_PassedServices(JSONManager json) {
		CustomReporter.createNode("For "+Arrays.toString(json.getStrArr("ServiceType"))+" , "+Arrays.toString(json.getStrArr("EventType")));
		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String apr18=Util.convertToString("MMM yy", cal.getTime());
		cal.add(Calendar.YEAR, -1);
		String apr17=Util.convertToString("MMM yy", cal.getTime());
		performSearch(json);
		
		/*
		 * 6. Go to Market Volume Report 
		 * 7. Calculate Forecast Market Volume of Voice MT service for the month April 2018 
		 * "Use Formula to calculate forecast Market Volume = Market volume for April 2017 for one operator * (Operator Growth at April 2018 for one operator/100)+1
		 * Test Data Market Volume For April 2017 = 12,141 
		 * Operator level Growth for the month April 2018 = -8% 
		 * = 12141*(-0.08+1) = 11170
		 * 
		 */

		//Clicking on Market Volume >> Edit Monthly Value button 
		CustomReporter.createNode("Navigating to Edit Market Volume & Taking Market volume For "+apr18+ " And "+apr17);
		com.navigateToAndVerifyPageTitle(button_EditMonthlyVolume, DTSummary_EditMarketVolumeP5.title);
		DTSummary_EditMarketVolumeP5 editMarkVol= new DTSummary_EditMarketVolumeP5();
		List<String> apr18List=editMarkVol.getPassedColumnList(apr18);
		List<String> apr17List=editMarkVol.getPassedColumnList(apr17);
		List<String> roamingPartnerList=editMarkVol.getPassedColumnList("Roaming Partner");
		com.click(editMarkVol.button_Cancel,"button_Cancel");

		CustomReporter.createNode("Navigating to Edit Operator Growth page & Calculating Operator Growth & Verifying Calculated Operator Growth Value with displayed page value");
		DTSummary_EditOperatorGrowthP8 editOperatorGrowth=new DTSummary_EditOperatorGrowthP8();
		List<String> apr18GrowthList=editOperatorGrowth.getOperatorLevelGrowthList_7235(roamingPartnerList,apr18);
		
		for (int i = 0; i < apr18List.size()-1; i++) {
			String apr18Value=Util.removeCommas(apr18List.get(i));
			String apr17Value=Util.removeCommas(apr17List.get(i));
			String apr18Growth=apr18GrowthList.get(i);
			//Applying the formula on values then comparing
			String apr18Value_Calc="0";
			try{
				apr18Value_Calc=Util.BD(apr17Value).multiply((Util.BD(apr18Growth).divide(Util.BD("100")).add(Util.BD("1")))).toPlainString();
			}catch (Exception e) {
			}
			Util.comparator(apr18Value_Calc, apr18Value, "'"+apr18+"' and Roaming Partner '"+roamingPartnerList.get(i)+"'");
		}
		
	}

}
