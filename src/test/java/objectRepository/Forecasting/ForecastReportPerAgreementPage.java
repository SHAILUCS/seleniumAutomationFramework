package objectRepository.Forecasting;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;
import com.xlUtil.DataTable;

import objectRepository.common.ApexCommon;
import objectRepository.common.EventType;
import objectRepository.common.ServiceType;
import objectRepository.common.TrafficDirection;

public class ForecastReportPerAgreementPage {

	private final String sheetName = "ForecastReportPerAgreement";

	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	protected WebElement breadcrumb_IOTRONWelcome;

	@FindBy(xpath = "//li[contains(.,'Forecasting')]")
	protected WebElement breadcrumb_Forecasting;

	@FindBy(xpath = "//h1[contains(.,'Forecast Report per Agreement')]")
	private WebElement breadcrumb_ForecastReportperAgreement;

	@FindBy(xpath = "//h2[contains(.,'IOT Discount Agreements')]//..//..//button")
	private WebElement hideshowbutton_IOTDiscountAgreements;

	@FindBy(xpath = "//h2[contains(.,'IOT Discount Agreements')]")
	private WebElement region_IOTDiscountAgreements;

	@FindBy(xpath = "//label[@id='P317_SHOW_LIVE_AGRMTS_LABEL']")
	private WebElement label_showLiveAgreements;

	@FindBy(xpath = "//input[@id='P317_SHOW_LIVE_AGRMTS_0']")
	private WebElement radiobutton_Yes;

	@FindBy(xpath = "//label[@for='P317_SHOW_LIVE_AGRMTS_0']")
	private WebElement label_Yes;

	@FindBy(xpath = "//input[@id='P317_SHOW_LIVE_AGRMTS_1']")
	private WebElement radiobutton_No;

	@FindBy(xpath = "//label[@for='P317_SHOW_LIVE_AGRMTS_1']")
	private WebElement label_No;

	@FindBy(css = "input[name='p_t01'][value='N']")
	private WebElement radio_showLiveAgreements;

	@FindBy(xpath = "//label[@id='P317_IOT_AGREEMENT_ID_LABEL']")
	private WebElement label_Agreement;

	@FindBy(id = "P317_IOT_AGREEMENT_ID")
	private WebElement select_Agreement;

	@FindBy(xpath = "//label[@id='P317_REF_SEARCH_FILTER_LABEL']")
	private WebElement label_AgreementFilter;

	@FindBy(id = "P317_REF_SEARCH_FILTER")
	private WebElement text_AgreementFilter;

	@FindBy(xpath = "//label[@id='P317_SERVICE_TYPE_ID_LABEL']")
	private WebElement label_ServiceType;

	@FindBy(id = "P317_SERVICE_TYPE_ID")
	private WebElement select_ServiceType;

	@FindBy(xpath = "//label[@id='P317_EVENT_TYPE_ID_LABEL']")
	private WebElement label_EventType;

	@FindBy(id = "P317_EVENT_TYPE_ID")
	private WebElement select_EventType;

	@FindBy(xpath = "//label[@id='P317_ORIGINAL_LATEST_LABEL']")
	private WebElement label_InformationSet;

	@FindBy(xpath = "//input[@id='P317_ORIGINAL_LATEST_0']")
	private WebElement radio_InfoSet_Latest;

	@FindBy(xpath = "//label[@for='P317_ORIGINAL_LATEST_0']")
	private WebElement label_Latest;

	@FindBy(xpath = "//input[@id='P317_ORIGINAL_LATEST_1']")
	private WebElement radio_InfoSet_Original;

	@FindBy(xpath = "//label[@for='P317_ORIGINAL_LATEST_1']")
	private WebElement label_Original;

	@FindBy(xpath = "//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;

	@FindBy(xpath = "//button[contains(.,'Reset Page')]")
	private WebElement button_ResetPage;

	@FindBy(xpath = "//button[contains(.,'Maintain Agreement')]")
	private WebElement button_MaintainAgreement;

	@FindBy(xpath = "//h2[contains(.,'Results')]//..//..//button")
	private WebElement hideshowbutton_Results;

	@FindBy(xpath = "//h2[contains(.,'Results')]")
	private WebElement region_Results;

	@FindBy(xpath = "//h2[contains(.,'Agreement Data Customer/Outbound')]//..//..//button")
	private WebElement hideshowbutton_AgreementDataCustomerOutbound;

	@FindBy(xpath = "//h2[contains(.,'Agreement Data Customer/Outbound')]")
	private WebElement region_AgreementDataCustomerOutbound;

	@FindBy(xpath = "//h2[contains(.,'Agreement Data Visitor/Inbound')]//..//..//button")
	private WebElement hideshowbutton_AgreementDataVisitorInbound;

	@FindBy(xpath = "//h2[contains(.,'Agreement Data Visitor/Inbound')]")
	private WebElement region_AgreementDataVisitorInbound;

	private List<String> tableColumns = new ArrayList<>(Arrays.asList("", "Traffic Period Ascending",
			"Traffic Direction", "Traffic Volume Previous Period Cumulative",
			"Traffic Volume Current Period Cumulative", "Forecasted Traffic Volume EoA",
			"Charging Increment used in Calculation", "Discount Currency",
			"TAP Charge Discount Currency Excl. Tax Cumulative", "TAP Charge Discount Currency Incl. Tax Cumulative",
			"TAP Charge Discount Currency Excl. Tax EoA", "TAP Charge Discount Currency Incl. Tax EoA",
			"TAP Charge Home Currency Incl. Tax Cumulative", "Discounted Charge Discount Currency Excl. Tax EoA",
			"Discounted Charge Discount Currency Incl. Tax EoA",
			"Discounted Charge Discount Currency Excl. Tax Cumulative",
			"Discounted Charge Discount Currency Incl. Tax Cumulative",
			"Discount Achieved Discount Currency Excl. Tax Cumulative",
			"Discount Achieved Discount Currency Incl. Tax Cumulative",
			"Discount Achieved Discount Currency Excl. Tax Monthly",
			"Discount Achieved Discount Currency Incl. Tax Monthly", "TAP Charge Home Currency Excl. Tax EoA",
			"TAP Charge Home Currency Incl. Tax EoA", "Discount Achieved Home Currency Excl. Tax EoA",
			"Discount Achieved Home Currency Incl. Tax EoA", "Discount Achieved Discount Currency Excl. Tax EoA",
			"Discount Achieved Discount Currency Incl. Tax EoA"));

	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title="Forecast Report per Agreement";
	public ForecastReportPerAgreementPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm=new ApexCommon();
	}

	public void performSearch(int dataTableScenarioRow) {

		DataTable dataTable=new DataTable(Constant.getTestDataFilePath(), sheetName);
		
		String agreementName = dataTable.getValue( dataTableScenarioRow,"Agreement");
		String servType = dataTable.getValue( dataTableScenarioRow,"Service Type");
		String evType = dataTable.getValue(dataTableScenarioRow, "Event Type");


		if (com.isElementPresent(select_Agreement)) {
			com.click(radio_showLiveAgreements);
			com.selectByPartialVisibleText(select_Agreement, agreementName);
			com.wait(0.5);
			com.selectByPartialVisibleText(select_ServiceType, servType);
			com.wait(0.5);
			if(com.isElementPresent(select_EventType)){
				com.selectByVisibleText(select_EventType, evType);
				com.wait(0.5);
			}
			com.click(radio_InfoSet_Latest);
			com.click(button_RefreshReport);
			if (com.waitForElementTobe_Visible(comm.table_ResultTabData, Constant.wait)) {
				CustomReporter.report(STATUS.PASS, "Forecast result table displayed for '"+ servType + "', '" + evType+"'");
			} else {
				CustomReporter.report(STATUS.FAIL, "Forecast result table is NOT displayed for '"+ servType + "', '" + evType+"'");
			}
		}


	}

	private void comparator(String trafficDirection, String expected, String actual_onPage, String desc) {
		int difference=Integer.parseInt(Util.BD(expected).subtract(Util.BD(actual_onPage)).toBigInteger().toString()); 
		if(difference==1 || difference==0 || difference==-1){
			//System.out.println("PASS Calulated value of ["+desc+"] : '" + expected + "' and on Page value displayed is '"+actual_onPage +"'");
			CustomReporter.report(STATUS.PASS, "For '"+trafficDirection+"' Calulated value of ["+desc+"] : '" + expected + "' and on Page value displayed is '"+actual_onPage +"'");
		}else{
			//System.out.println("FAIL Calulated value of ["+desc+"] : '" + expected + "' and on Page value displayed is '"+actual_onPage +"'");
			CustomReporter.report(STATUS.FAIL,"For '"+trafficDirection+"' Calulated value of ["+desc+"] : '" + expected + "' and on Page value displayed is '"+actual_onPage +"'"); 
		}

	}





	public void performCalculations_ForecastingRep(int dataTableScenarioRow, String discBasisVal, String lowerBound) {

		/*String currentTraffPeriod = DataTable_Static.Value(Constant.getTestDataFilePath(), sheetName, dataTableScenarioRow,
				"Traffic Period");
		String currentTraffDirection = DataTable_Static.Value(Constant.getTestDataFilePath(), sheetName, dataTableScenarioRow,
				"Traffic Direction");
		performCalculations_ForecastingRep(currentTraffPeriod, currentTraffDirection, discBasisVal, lowerBound);*/	
	}

	public void performCalculations_ForecastingRep(String currentTraffPeriod , TrafficDirection TrafficDirection , String discBasisVal, String lowerBound) {
		String currentTraffDirection=TrafficDirection.value;
		WebTable headerTab=new WebTable(comm.table_ResultTabHeader);
		WebTable tab=new WebTable(comm.table_ResultTabData);
		headerTab.initHeaderColumnList(1);
		int rowCount=tab.getRowCount();
		if (rowCount > 1) {
			int currentRow=0,prevRow=0;

			for (int row = 2; row <= rowCount; row++) {
				String traffPeriod = tab.getCellText(row, headerTab.getColumnNumber("Traffic Period Ascending"));
				String traffDirection = tab.getCellText(row, headerTab.getColumnNumber("Traffic Direction"));
				if(traffPeriod.toLowerCase().contains(currentTraffPeriod.toLowerCase()) && currentTraffDirection.equals(traffDirection)){
					currentRow=row;
					prevRow=currentRow-2;
					break;
				}
			}

			//**************************
			//SRE CALCULATIONS STARTED
			//**************************
			if(currentRow!=0){
				String discRate = Util.removeCommas(discBasisVal);
				lowerBound = Util.removeCommas(lowerBound);

				String cumTraffVol=Util.removeCommas(tab.getCellText(currentRow, headerTab.getColumnNumber("Traffic Volume Current Period Cumulative")));
				String cumTAPCharge=Util.removeCommas(tab.getCellText(currentRow, headerTab.getColumnNumber("TAP Charge Discount Currency Excl. Tax Cumulative")));
				String eoaTraffVol=Util.removeCommas(tab.getCellText(currentRow, headerTab.getColumnNumber("Forecasted Traffic Volume EoA")));
				String eoaTAPCharge=Util.removeCommas(tab.getCellText(currentRow, headerTab.getColumnNumber("TAP Charge Discount Currency Excl. Tax EoA")));

				//eoaTAPRate="eoaTAPCharge / eoaTraffVol";
				String eoaTAPRate=(Util.BD(eoaTAPCharge)).divide(Util.BD(eoaTraffVol), 20, RoundingMode.HALF_EVEN).toPlainString();


				String discountedChargeEoa="";
				if(Util.BD(eoaTraffVol).compareTo(Util.BD(lowerBound))>0){
					//discountedChargeEoa = "(eoaTraffVol - lowerBound) * discRate + lowerBound * eoaTAPRate";
					BigDecimal temp1= Util.BD(eoaTraffVol).subtract(Util.BD(lowerBound));
					temp1= temp1.multiply(Util.BD(discRate));
					BigDecimal temp2= Util.BD(lowerBound).multiply(Util.BD(eoaTAPRate));
					discountedChargeEoa=temp1.add(temp2).toPlainString();
				}else{
					//discountedChargeEoa = eoaTraffVol * eoaTAPRate
					discountedChargeEoa=Util.BD(eoaTraffVol).add(Util.BD(eoaTAPRate)).toPlainString();
				}

				//System.out.println(discountedChargeEoa);
				String discountedChargeEoa_OnPage=Util.removeCommas(tab.getCellText(currentRow, headerTab.getColumnNumber("Discounted Charge Discount Currency Excl. Tax EoA"))); 

				comparator(currentTraffDirection, discountedChargeEoa, discountedChargeEoa_OnPage, "Discounted Charge EOA");

				//avgIOTRate = "discountedChargeEoa/eoaTraffVol";
				String avgIOTRate = Util.BD(discountedChargeEoa).divide(Util.BD(eoaTraffVol),20,RoundingMode.HALF_EVEN).toPlainString();
				//System.out.println("avgIOTRate "+avgIOTRate);

				//cumDiscountedCharge = "avgIOTRate*cumTraffVol";
				String cumDiscountedCharge = Util.BD(avgIOTRate).multiply(Util.BD(cumTraffVol)).toPlainString();
				//System.out.println("cumDiscountedCharge "+cumDiscountedCharge);
				String cumDiscountedCharge_OnPage=Util.removeCommas(tab.getCellText(currentRow, headerTab.getColumnNumber("Discounted Charge Discount Currency Excl. Tax Cumulative")));
				comparator(currentTraffDirection, cumDiscountedCharge, cumDiscountedCharge_OnPage, "Discounted Charge Cumulative");

				//cumDiscountAchieved = "cumTAPCharge - cumDiscountedCharge";
				String cumDiscountAchieved = Util.BD(cumTAPCharge).subtract(Util.BD(cumDiscountedCharge)).toPlainString();
				//System.out.println("cumDiscountAchieved "+ cumDiscountAchieved);
				String cumDiscountAchieved_OnPage=Util.removeCommas(tab.getCellText(currentRow, headerTab.getColumnNumber("Discount Achieved Discount Currency Excl. Tax Cumulative")));
				comparator(currentTraffDirection, cumDiscountAchieved, cumDiscountAchieved_OnPage, "Discount Achieved Cumulative");

				String prevPeriodDiscCharge = Util.removeCommas(tab.getCellText(prevRow, headerTab.getColumnNumber("Discounted Charge Discount Currency Excl. Tax Cumulative")));
				String prevPeriodDiscAchieved=Util.removeCommas(tab.getCellText(prevRow, headerTab.getColumnNumber("Discount Achieved Discount Currency Excl. Tax Cumulative")));

				//monthlyDiscCharge ="cumDiscountedCharge - prePeriodDiscCharge";
				String monthlyDiscCharge =Util.BD(cumDiscountedCharge).subtract(Util.BD(prevPeriodDiscCharge)).toPlainString();
				CustomReporter.report(STATUS.INFO, "For '"+currentTraffDirection+"' Calulated value of [Discounted Charge Monthly] : '" + monthlyDiscCharge + "'");
				//System.out.println("monthlyDiscCharge "+ monthlyDiscCharge);

				//monthlyDiscAchieved	="cumDiscountAchieved - prePeriodDiscAchieved";
				String monthlyDiscAchieved	=Util.BD(cumDiscountAchieved).subtract(Util.BD(prevPeriodDiscAchieved)).toPlainString();
				//System.out.println("monthlyDiscAchieved "+ monthlyDiscAchieved);
				String monthlyDiscAchieved_OnPage=Util.removeCommas(tab.getCellText(currentRow, headerTab.getColumnNumber("Discount Achieved Discount Currency Excl. Tax Monthly")));
				comparator(currentTraffDirection, monthlyDiscAchieved, monthlyDiscAchieved_OnPage, "Discount Achieved Monthly");

				//**************************
				//SRE CALCULATIONS ENDED
				//**************************
			}

		} else {
			CustomReporter.report(STATUS.FAIL, "No rows are getting displayed in Forecast result table");
		}

	}

	public static void main(String[] args) {
		/*String currentTraffDirection="Customer/Outbound";
		String discRate = Util.removeCommas("0.005");
		String lowerBound = Util.removeCommas("2000000");

		String cumTraffVol=Util.removeCommas("22,74,781");
		String cumTAPCharge=Util.removeCommas("1,31,397");
		String eoaTraffVol=Util.removeCommas("1,37,07,853");
		String eoaTAPCharge=Util.removeCommas("5,00,783");

		//eoaTAPRate="eoaTAPCharge / eoaTraffVol";
		String eoaTAPRate=(Util.BD(eoaTAPCharge)).divide(Util.BD(eoaTraffVol), 20, RoundingMode.HALF_EVEN).toPlainString();


		String discountedChargeEoa="";
		if(Util.BD(eoaTraffVol).compareTo(Util.BD(lowerBound))>0){
			//discountedChargeEoa = "(eoaTraffVol - lowerBound) * discRate + lowerBound * eoaTAPRate";
			BigDecimal temp1= Util.BD(eoaTraffVol).subtract(Util.BD(lowerBound));
			temp1= temp1.multiply(Util.BD(discRate));
			BigDecimal temp2= Util.BD(lowerBound).multiply(Util.BD(eoaTAPRate));
			discountedChargeEoa=temp1.add(temp2).toPlainString();
		}else{
			//discountedChargeEoa = eoaTraffVol * eoaTAPRate
			discountedChargeEoa=Util.BD(eoaTraffVol).add(Util.BD(eoaTAPRate)).toPlainString();
		}

		//System.out.println(discountedChargeEoa);
		String discountedChargeEoa_OnPage=Util.removeCommas("131,604"); 

		comparator(currentTraffDirection, discountedChargeEoa, discountedChargeEoa_OnPage, "Discounted Charge EOA");

		//avgIOTRate = "discountedChargeEoa/eoaTraffVol";
		String avgIOTRate = Util.BD(discountedChargeEoa).divide(Util.BD(eoaTraffVol),20,RoundingMode.HALF_EVEN).toPlainString();
		//System.out.println("avgIOTRate "+avgIOTRate);

		//cumDiscountedCharge = "avgIOTRate*cumTraffVol";
		String cumDiscountedCharge = Util.BD(avgIOTRate).multiply(Util.BD(cumTraffVol)).toPlainString();
		//System.out.println("cumDiscountedCharge "+cumDiscountedCharge);
		String cumDiscountedCharge_OnPage=Util.removeCommas("21,839");
		comparator(currentTraffDirection,cumDiscountedCharge, cumDiscountedCharge_OnPage, "Discounted Charge Cumulative");

		//cumDiscountAchieved = "cumTAPCharge - cumDiscountedCharge";
		String cumDiscountAchieved = Util.BD(cumTAPCharge).subtract(Util.BD(cumDiscountedCharge)).toPlainString();
		//System.out.println("cumDiscountAchieved "+ cumDiscountAchieved);
		String cumDiscountAchieved_OnPage=Util.removeCommas("109,558");
		comparator(currentTraffDirection,cumDiscountAchieved, cumDiscountAchieved_OnPage, "Discount Achieved Cumulative");

		String prevPeriodDiscCharge = Util.removeCommas("12,180");
		String prevPeriodDiscAchieved=Util.removeCommas("53,274");

		//monthlyDiscCharge ="cumDiscountedCharge - prePeriodDiscCharge";
		String monthlyDiscCharge =Util.BD(cumDiscountedCharge).subtract(Util.BD(prevPeriodDiscCharge)).toPlainString();
		//System.out.println("monthlyDiscCharge "+ monthlyDiscCharge);

		//monthlyDiscAchieved	="cumDiscountAchieved - prePeriodDiscAchieved";
		String monthlyDiscAchieved	=Util.BD(cumDiscountAchieved).subtract(Util.BD(prevPeriodDiscAchieved)).toPlainString();
		//System.out.println("monthlyDiscAchieved "+ monthlyDiscAchieved);
		String monthlyDiscAchieved_OnPage=Util.removeCommas("56,284");
		comparator(currentTraffDirection,monthlyDiscAchieved, monthlyDiscAchieved_OnPage, "Discount Achieved Monthly");
		 */
		
	}

	public void UIVerification(String yesOrNo_LiveAgreements,String Agreement,ServiceType serviceType, EventType EventType, String latestOrOriginal_InformationSet) {
		CustomReporter.createNode("Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_Forecasting, "Forecasting, BreadCrumb");
		com.isElementPresent(breadcrumb_ForecastReportperAgreement, "Forecast Report per Agreement, BreadCrumb");
		com.isElementPresent(hideshowbutton_IOTDiscountAgreements, "IOT Discount Agreements, HideShowButton");
		com.isElementPresent(region_IOTDiscountAgreements, "IOT Discount Agreements, Region");	
		com.isElementPresent(label_showLiveAgreements, "Show Live Agreements, Label");
		com.isElementPresent(radiobutton_Yes, "Yes, RadioButton");
		com.isElementPresent(label_Yes, "Yes, Label");
		com.isElementPresent(radiobutton_No, "No, Radiobutton");
		com.isElementPresent(label_No, "No, Label");
		com.isElementPresent(label_Agreement, "Agreement, Label");
		com.isElementPresent(select_Agreement, "Agreement, Select");
		com.isElementPresent(label_AgreementFilter, "Agreement Filter, Label");
		com.isElementPresent(text_AgreementFilter, "Agreement Filter, Text");
		com.isElementPresent(label_ServiceType, "Service Type, Label");
		com.isElementPresent(select_ServiceType, "Service Type, Select");
		com.isElementPresent(label_EventType, "Event Type, Label");
		com.isElementPresent(select_EventType, "Event Type, Select");
		com.isElementPresent(label_InformationSet, "Information Set, Label");
		com.isElementPresent(radio_InfoSet_Latest, "InfoSet_Latest, RadioButton");
		com.isElementPresent(label_Latest, "Latest, Label");
		com.isElementPresent(radio_InfoSet_Original, "InfoSet_Original, RadioButton");	
		com.isElementPresent(label_Original, "Original, Label");
		com.isElementPresent(button_RefreshReport, "Refresh Report, Button");
		com.isElementPresent(button_ResetPage, "Reset Page, Button");
		com.isElementPresent(button_MaintainAgreement, "Maintain Agreement, Button");
		com.isElementPresent(hideshowbutton_Results, "Results, HideShowButton");
		com.isElementPresent(region_Results, "Results, Region");
		com.isElementPresent(hideshowbutton_AgreementDataCustomerOutbound, "Agreement Data Customer Outbound, HideShowButton");
		com.isElementPresent(region_AgreementDataCustomerOutbound, "Agreement Data Customer Outbound, Region");
		com.isElementPresent(hideshowbutton_AgreementDataVisitorInbound, "Agreement Data Visitor Inbound, HideShowButton");
		com.isElementPresent(region_AgreementDataVisitorInbound, "Agreement Data Visitor Inbound, Region");

		CustomReporter.createNode("Verifying the Table columns");
		if(performSearch(yesOrNo_LiveAgreements, Agreement, serviceType, EventType, latestOrOriginal_InformationSet)){
			comm.verifyColumnHeaders(comm.table_ResultTabHeader,tableColumns, 1);
			comm.verifyActionsPopupItems_WithoutSubscription();	
		}
		else{
			CustomReporter.report(STATUS.FAIL, "Table Column Not Found");	
		}
	}

	public boolean performSearch(String yesOrNo_LiveAgreements,String Agreement,ServiceType serviceType, EventType EventType, String latestOrOriginal_InformationSet) {
		String message = "";

		if (yesOrNo_LiveAgreements != null) {
			if (yesOrNo_LiveAgreements.equalsIgnoreCase("yes")) {
				com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(radiobutton_Yes);
			}else{
				com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(radiobutton_No);
			}
			message = message + " | Show Only Live Agreements= '" + yesOrNo_LiveAgreements + "'";
		}

		if (Agreement != null) {
			com.selectByPartialVisibleText(select_Agreement, Agreement);
			message = message + " | Agreement= '" + Agreement + "'";
			com.wait(1);
		}

		if (serviceType != null) {
			com.selectByVisibleText(select_ServiceType, serviceType.value);
			message = message + " | ServiceType= '" + serviceType.value + "'";
		}

		if (EventType != null) {
			if (serviceType!=ServiceType.DATA) {
				com.selectByVisibleText(select_EventType, EventType.value);
			}
			message = message + " | EventType= '" + EventType.value + "'";
		}

		if (latestOrOriginal_InformationSet != null) {
			if (latestOrOriginal_InformationSet.equalsIgnoreCase("latest")) {
				com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(radio_InfoSet_Latest);
			}else{
				com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(radio_InfoSet_Original);
			}
			message = message + " | Information Set= '" + latestOrOriginal_InformationSet + "'";
		}


		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);
		com.click(button_RefreshReport);
		if (com.isElementPresent(comm.table_ResultTabHeader, "Result Table")) {
			return true;
		}

		return false;

	}
	
}
