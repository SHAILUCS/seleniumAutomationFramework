package objectRepository.Reporting;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.Constant;
import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.jsonUtil.JSONManager;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreement_P303;
import objectRepository.AgreementCapture.MaintainIOTDiscountAgreementsPage_P301;
import objectRepository.Forecasting.FinancialReport_ForecastedTAPDiscountedCharges;
import objectRepository.IOTRONSystemAdministration.MaintainClientDefinedCountries;
import objectRepository.IOTRONSystemAdministration.MaintainClientDefinedCountriesMapping;
import objectRepository.IOTRONSystemAdministration.ViewIOTTrafficData;
import objectRepository.common.ApexCommon;
import objectRepository.common.Currency;
import objectRepository.common.EventType;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.LoginPage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import objectRepository.common.ServiceType;
import objectRepository.common.TADIG;
import objectRepository.common.TrafficDirection;
import tests.MainRegression.HKT_AccrualReport_7490;
import tests.MainRegression.Module_OverviewReport;

public class OverviewReport_P507 {
	private SeleniumMethods com;
	private ApexCommon comm;
	private Navigator navigate;
	private IOTRONHomePage ihp;
	private int headerRow=2;

	public static String title="Overview Report";
	public OverviewReport_P507() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm= new ApexCommon();
		navigate = new Navigator();
		ihp = new IOTRONHomePage();
	}

	@FindBy(id = "P507_CLIENT_MASTER_ENTITY_ID_LEFT")
	private WebElement select_HomeOperator_Left;

	@FindBy(id = "P507_CLIENT_MASTER_ENTITY_ID_RIGHT")
	private WebElement select_HomeOperator_Right;

	@FindBy(id = "P507_SERVICE_TYPE_ID")
	private WebElement select_ServiceType;

	@FindBy(id = "P507_EVENT_TYPE_ID")
	private WebElement select_EventType;

	@FindBy(id = "P507_TRAFFIC_DIRECTION")
	private WebElement select_TrafficDirection;

	@FindBy(id = "P507_CURRENCY")
	private WebElement select_Currency;

	@FindBy(id = "P507_ORIGINAL_LATEST_0")
	private WebElement radio_Latest;

	@FindBy(id = "P507_ORIGINAL_LATEST_1")
	private WebElement radio_Original;

	@FindBy(id = "P507_TRAFFIC_PERIOD_ID_LEFT")
	private WebElement select_TrafficPeriodLeft;

	@FindBy(id = "P507_TRAFFIC_PERIOD_ID_RIGHT")
	private WebElement select_TrafficPeriodRight;

	@FindBy(id = "P507_CLIENT_ZONE_ID_LEFT")
	private WebElement select_Zone_Left;

	@FindBy(id = "P507_MONTHLY_LEVEL_0")
	private WebElement checkbox_DetailPerMonth;

	@FindBy(xpath = "//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;

	@FindBy(xpath = "//button[contains(.,'Reset Page')]")
	public WebElement button_ResetPage;

	@FindBy(xpath = "//button[contains(.,'Service and Event breakdown')]")
	private WebElement button_ServiceAndEventBreakdown;

	@FindBy(id = "P507_CALL_DESTINATION_LEVEL_0")
	private WebElement checkbox_MOCallDestinationBreakdown;
	
	@FindBy(xpath = "//button[contains(.,'Download CSV')]")
	private WebElement button_DownloadCSV;
	
	@FindBy(xpath = "//button[.='Download'][contains(@class,'iconRight')]")
	private WebElement button_DownloadXls;

	private List<String> colNames = new ArrayList<>(Arrays.asList("Operator/ Group Name", "Country", "PMN code",
			"Agreement Reference", "Agreement Start Date", "Agreement End Date", "Direction", "Service Type",
			"Event Type", "Traffic Volume", "Total Tap Charges Excluding Tax", "Total Tap Charge Including Tax",
			"Total Post-Discounted Charges Excluding Tax", "Total Post-Discounted Charges Including Tax",
			"Average IOT TAP level Excluding Tax", "Average IOT TAP level Including Tax",
			"Average Post-Discounted IOT Excluding Tax", "Average Post-Discounted IOT Including Tax",
			"Credit Note Discount Achieved Excluding Tax", "Credit Note Discount Achieved Including Tax",
			"Negotiator"));



	private void verifyDetailsPerMonthCheckboxFunc(String jsonFilePath,String  jsonObjName,String agreementName) {

		CustomReporter.createNode("verify Details Per Month Checkbox Func");

		//"0116,0316"
		//Fetching the Start & End Day, Month and Year from passed trafPeriodRange
		JSONManager json= new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"ORSearch");
		String trafPeriodRange=json.getStr("trafPeriodRange");
		String startTrafPeriod=Util.getFirstDay("dd-MMM-yyyy", trafPeriodRange.split(",")[0]);
		String endTrafPeriod=Util.getLastDay("dd-MMM-yyyy", trafPeriodRange.split(",")[1]);
		String startEndTrafPeriod=startTrafPeriod+","+endTrafPeriod;

		//2 . Detail per month checkbox is unselected 
		//When multiple Traffic Periods are selected and a checkbox ‘Detail per Month’ is NOT checked, Traffic Volumes and Charges are aggregated across selected Traffic Periods.
		if(performSearch(json)){

			WebTable tab= new WebTable(comm.table_ResultTab);
			comm.applyColumnFilter(headerRow, tab.initHeaderColumnList(headerRow).getColumnNumber("Agreement Reference"), agreementName);
			String aggr_TraffVol=Util.removeCommas(tab.getCellText(3, tab.getColumnNumber("Traffic Volume")));
			String aggr_TotalTAPCh_ExTax=Util.removeCommas(tab.getCellText(3, tab.getColumnNumber("Total TAP Charges Excluding Tax")));
			String aggr_TotalPostDiscCh_ExTax=Util.removeCommas(tab.getCellText(3, tab.getColumnNumber("Total Post-Discounted Charges Excluding Tax")));
			String aggr_CredNotDiscAch_ExTax=Util.removeCommas(tab.getCellText(3, tab.getColumnNumber("Credit Note Discount Achieved Excluding Tax")));

			//4. Check that Values for Traffic Volume, TAP Charge, Discounted Charge, Discount Achieved reported per traffic the same as aggregated values  across selected Traffic Periods
			com.check_CheckboxAndConfirm(checkbox_DetailPerMonth, "Detail Per Month");
			com.click(button_RefreshReport,"Refresh Button");


			//First Verify that the "Traffic Period" column gets displayed
			tab.initHeaderColumnList(headerRow);
			int col_trafVol=tab.getColumnNumber("Traffic Volume");
			int col_TotalTAPCharge=tab.getColumnNumber("Total TAP Charges Excluding Tax");
			int col_TotalPostDiscCharge=tab.getColumnNumber("Total Post-Discounted Charges Excluding Tax");
			int col_CredNotDiscAch=tab.getColumnNumber("Credit Note Discount Achieved Excluding Tax");
			if(col_trafVol>0){
				CustomReporter.report(STATUS.PASS, "Traffic Volume column displayed");
			}else{
				CustomReporter.report(STATUS.FAIL, "Traffic Volume column is NOT displayed");
			}

			//Now Verify the column values
			verifyColumnAggregateValue(aggr_TraffVol,"Traffic Volume",col_trafVol);
			verifyColumnAggregateValue(aggr_TotalTAPCh_ExTax,"Total TAP Charges Excluding Tax",col_TotalTAPCharge);
			verifyColumnAggregateValue(aggr_TotalPostDiscCh_ExTax,"Total Post-Discounted Charges Excluding Tax",col_TotalPostDiscCharge);
			verifyColumnAggregateValue(aggr_CredNotDiscAch_ExTax,"Credit Note Discount Achieved Excluding Tax",col_CredNotDiscAch);

			//Check if change of report format to Service and Event breakdown Traffic period column will still be visible
			CustomReporter.createNode("Traffic period column will still be visible after clicking on Service and Event breakdown button");
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_ServiceAndEventBreakdown, "Service and Event breakdown button");

			tab.initHeaderColumnList(headerRow);

			col_trafVol=tab.getColumnNumber("Traffic Volume");
			if(col_trafVol>0){
				CustomReporter.report(STATUS.PASS, "Traffic Volume column displayed");
			}else{
				CustomReporter.report(STATUS.FAIL, "Traffic Volume column is NOT displayed");
			}

			//For row wise details verification of Overview Rep AND Fin Rep
			comm.applyColumnFilter(headerRow, tab.getColumnNumber("Agreement Reference"), agreementName);
			List<String> rowWise_TraffVol=new ArrayList<String>();
			List<String> rowWise_TotalTAPCh_ExTax=new ArrayList<String>();
			List<String> rowWise_TotalPostDiscCh_ExTax=new ArrayList<String>();
			List<String> rowWise_CredNotDiscAch_ExTax=new ArrayList<String>();

			int rowCount=tab.getRowCount();
			col_TotalTAPCharge=tab.getColumnNumber("Total TAP Charges Excluding Tax");
			col_TotalPostDiscCharge=tab.getColumnNumber("Total Post-Discounted Charges Excluding Tax");
			col_CredNotDiscAch=tab.getColumnNumber("Credit Note Discount Achieved Excluding Tax");
			int col_TAPCharge=tab.getColumnNumber("TAP Charges");
			int col_PostDiscountedCharges=tab.getColumnNumber("Post-Discounted Charges");
			int col_DiscountAchieved=tab.getColumnNumber("Discount Achieved");

			for (int row = 3; row <= rowCount; row++) {
				rowWise_TraffVol.add(tab.getCellText(row, col_trafVol));
				rowWise_TotalTAPCh_ExTax.add(tab.getCellText(row, col_TotalTAPCharge));
				rowWise_TotalPostDiscCh_ExTax.add(tab.getCellText(row, col_TotalPostDiscCharge));
				rowWise_CredNotDiscAch_ExTax.add(tab.getCellText(row, col_CredNotDiscAch));
			}



			//Check displayed values with the ones with same parameters in Financial Forecast Report (p497)
			CustomReporter.createNode("Log in with NGC User");
			new LoginPage().logoutThenPerformLogin("NGC-S");

			CustomReporter.createNode("Verifying the Data with same parameters in Financial Forecast Report p497");
			FinancialReport_ForecastedTAPDiscountedCharges financialRep=new FinancialReport_ForecastedTAPDiscountedCharges();
			navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.FinancialReportForecastedTAPDiscountedCharges, ihp.link_Forecasting,ihp.link_Forecasting_FinancialReportForecastedTAPDiscountedCharges);
			json= new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"FinFcstSearch");
			if (financialRep.performSearch(json.getStr("homeOperator"), startEndTrafPeriod,
					json.getStr("trafDirection"), json.getStr("servType"), json.getStr("evType"),
					json.getStr("curr"), json.getStrArr("pmn"), json.getStr("countryCommaSeparated"),
					json.getStr("group"), json.getStr("taxNetOrGross"))) {
				//Now Verify the column values
				tab.initHeaderColumnList(headerRow);
				comm.applyColumnFilter(headerRow, tab.getColumnNumber("Agreement Reference"), agreementName);
				col_trafVol=tab.getColumnNumber("Traffic Volume");
				col_TAPCharge=tab.getColumnNumber("TAP Charges");
				col_PostDiscountedCharges=tab.getColumnNumber("Post-Discounted Charges");
				col_DiscountAchieved=tab.getColumnNumber("Discount Achieved");
				verifyColumnAggregateValue(aggr_TraffVol,"Traffic Volume",col_trafVol);
				verifyColumnAggregateValue(aggr_TotalTAPCh_ExTax,"TAP Charges",col_TAPCharge);
				verifyColumnAggregateValue(aggr_TotalPostDiscCh_ExTax,"Post-Discounted Charges",col_PostDiscountedCharges);
				verifyColumnAggregateValue(aggr_CredNotDiscAch_ExTax,"Discount Achieved",col_DiscountAchieved);

				//For row wise details verification of Overview Rep AND Fin Rep
				List<String> rowWise_TraffPeriod_FR=new ArrayList<String>();
				List<String> rowWise_TraffVol_FR=new ArrayList<String>();
				List<String> rowWise_TotalTAPCh_ExTax_FR=new ArrayList<String>();
				List<String> rowWise_TotalPostDiscCh_ExTax_FR=new ArrayList<String>();
				List<String> rowWise_CredNotDiscAch_ExTax_FR=new ArrayList<String>();

				rowCount=tab.getRowCount();
				int col_trafPer=tab.getColumnNumber("Traffic Period");
				col_TAPCharge=tab.getColumnNumber("TAP Charges");
				int col_PostDiscCharges=tab.getColumnNumber("Post-Discounted Charges");
				int col_DiscAch=tab.getColumnNumber("Discount Achieved");
				for (int row = 3; row <= rowCount; row++) {
					rowWise_TraffPeriod_FR.add(tab.getCellText(row, col_trafPer));
					rowWise_TraffVol_FR.add(tab.getCellText(row, col_trafVol));
					rowWise_TotalTAPCh_ExTax_FR.add(tab.getCellText(row, col_TAPCharge));
					rowWise_TotalPostDiscCh_ExTax_FR.add(tab.getCellText(row, col_PostDiscCharges));
					rowWise_CredNotDiscAch_ExTax_FR.add(tab.getCellText(row, col_DiscAch));
				}

				CustomReporter.createNode("Verifying All Row values of Overview Rep and Fin Rep");
				matchLists("Traffic Volume",rowWise_TraffPeriod_FR,rowWise_TraffVol,rowWise_TraffVol_FR);
				matchLists("TAP Charges",rowWise_TraffPeriod_FR,rowWise_TotalTAPCh_ExTax,rowWise_TotalTAPCh_ExTax_FR);
				matchLists("Post-Discounted Charges",rowWise_TraffPeriod_FR,rowWise_TotalPostDiscCh_ExTax,rowWise_TotalPostDiscCh_ExTax_FR);
				matchLists("Discount Achieved",rowWise_TraffPeriod_FR,rowWise_CredNotDiscAch_ExTax,rowWise_CredNotDiscAch_ExTax_FR);
			}

		}

	}



	private void matchLists(String colName,List<String> traffPeriod, List<String> list_OR, List<String> list_FR) {
		boolean pass=true;
		Collections.sort(list_OR);
		Collections.sort(list_FR);
		for (int i = 0; i < list_OR.size(); i++) {
			double diff=Double.parseDouble(Util.BD(list_OR.get(i)).subtract(Util.BD(list_FR.get(i))).toString());
			if(diff>1 || diff<-1){
				CustomReporter.report(STATUS.FAIL, "For column ["+colName+"], Row values are NOT matching, value displayed on Overview Rep: "+list_OR.get(i)+" value on Fin Rep: "+list_FR.get(i));
				pass=false;
				break;
			}
		}
		if(pass){
			CustomReporter.report(STATUS.PASS, "For column ["+colName+"], All Row values of Overview Rep and Fin Rep are matching");
		}
	}

	private void verifyColumnAggregateValue(String expectedValue,String colName,int colNum) {
		WebTable tab= new WebTable(comm.table_ResultTab);
		String actualVal=Util.BD(tab.getCellText(3, colNum)).add(Util.BD(tab.getCellText(4, colNum))).add(Util.BD(tab.getCellText(5, colNum))).toPlainString();
		Util.comparator(expectedValue, actualVal, colName);
	}

	public void verify_UI(String createdZone) {
		CustomReporter.createNode("Verifying UI of Overview Report");
		if(!com.waitForElementTobe_NotVisible(select_HomeOperator_Left, 1)){
			com.isElementPresent(select_HomeOperator_Left, "Home Operator shuttle");
		}

		if(com.isElementPresent(select_ServiceType, "Service Type dropdown")){
			List<String> list= ServiceType.getValues();
			List<String> listPage= com.getAllOptionsVisibleText(select_ServiceType);
			Util.comparator_List_NonNumbers(list, listPage, "Service Type dropdown");
		}

		if(com.isElementPresent(select_EventType, "Event Type dropdown")){
			List<String> list= EventType.getValues();
			List<String> listPage= com.getAllOptionsVisibleText(select_EventType);
			Util.comparator_List_NonNumbers(list, listPage, "Event Type dropdown");
		}

		if(com.isElementPresent(select_TrafficDirection, "Traffic Direction dropdown")){
			List<String> list= TrafficDirection.getValues();
			List<String> listPage= com.getAllOptionsVisibleText(select_TrafficDirection);
			Util.comparator_List_NonNumbers(list, listPage, "Traffic Direction dropdown");
		}

		com.isElementPresent(select_Currency, "Currency dropdown");
		com.isElementPresent(radio_Latest, "Information Set Radio button - Latest");
		com.isElementPresent(radio_Original, "Information Set Radio button - Original");

		if(com.isElementPresent(select_TrafficPeriodLeft)){
			List<String> listPage=null;
			if(!com.waitForElementTobe_NotVisible(select_HomeOperator_Left, 1)) {
				com.selectByPartialVisibleText_DoubleClick_FromCommaSeparated(select_HomeOperator_Left, "AUSTA");
				com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			}
			listPage= com.getAllOptionsVisibleText(select_TrafficPeriodLeft);
			if(listPage.size()>0) {
				CustomReporter.report(STATUS.PASS, "Traffic Period dropdown displayed successfully values");
			}else {
				CustomReporter.report(STATUS.FAIL, "Traffic Period dropdown does NOT displayed any values");
			}

		}

		if(createdZone!=null){
			com.isElementPresent(select_Zone_Left, "Zone shuttle");
			List<String> zoneList=com.getAllOptionsVisibleText(select_Zone_Left);
			if (zoneList.contains(createdZone)) {
				CustomReporter.report(STATUS.PASS, "Zone shuttle displayed the newly created zone "+createdZone);
			} else {
				CustomReporter.report(STATUS.FAIL, "Zone shuttle is NOT displaying the newly created zone "+createdZone);
			}
		}


		com.isElementPresent(checkbox_DetailPerMonth, "Detail Per Month checkbox");
		if(com.isElementPresent(checkbox_MOCallDestinationBreakdown, "MO Call Destination Breakdown checkbox")){
			if(!com.isSelected(checkbox_MOCallDestinationBreakdown)){
				CustomReporter.report(STATUS.PASS, "By Default 'MO Call Destination Breakdown' checkbox is unchecked");
			}else{
				CustomReporter.report(STATUS.FAIL, "'MO Call Destination Breakdown' checkbox is checked");
			}
		}
		com.isElementPresent(button_RefreshReport, "Refresh Report button");
		if(com.isElementPresent(button_ServiceAndEventBreakdown, "Service And Event Breakdown button")){
			if(!com.isSelected(button_ServiceAndEventBreakdown)){
				CustomReporter.report(STATUS.PASS, "By Default 'Service And Event Breakdown' checkbox is unchecked");
			}else{
				CustomReporter.report(STATUS.FAIL, "'Service And Event Breakdown' checkbox is checked");
			}
		}
		com.isElementPresent(button_ResetPage, "Reset Page button");
	}

	public void verify_UI() {
		verify_UI(null);
	}

	public boolean performSearch(JSONManager json) {
		return performSearch(json.getStr("clientPMNs"), json.getStr("trafPeriodRange"), json.getStr("ServiceType"),
				json.getStr("EventType"), json.getStr("TrafficDirection"), json.getStr("Currency"),
				json.getStr("latestOriginal"), json.getBool("bool_detailPerMonth"),
				json.getBool("bool_moCallDestBreakdwn"), json.getInt("int_reportIndex"));
	}

	public boolean performSearch(String clientPMNs, String trafPeriodRange, String ServiceType, String EventType,
			String TrafficDirection, String Currency, String latestOriginal, boolean bool_detailPerMonth,
			boolean bool_moCallDestBreakdwn, int int_reportIndex) {

		fillBasicSearchFields(clientPMNs, trafPeriodRange, ServiceType, EventType, TrafficDirection, Currency,
				latestOriginal, bool_detailPerMonth, bool_moCallDestBreakdwn);

		com.click(button_RefreshReport);
		if (com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator)) {
			if(int_reportIndex>-1) {
				com.selectByIndex(comm.select_Reports,int_reportIndex);
				String selectedVal=com.getText(com.getFirstSelectedOption(comm.select_Reports));
				CustomReporter.report(STATUS.INFO, "Value : '"+selectedVal+"' is selected in 'Reports dropdown'");
			}

			if(com.isElementPresent(comm.table_ResultTab, "Result Table")){
				return true;
			}
		}
		return false;
	}

	/*private boolean performSearch(TADIG clientPMNs,String trafPeriodRange,ServiceType ServiceType,EventType EventType,TrafficDirection TrafficDirection,Currency Currency,String latestOriginal,boolean bool_detailPerMonth,boolean bool_moCallDestBreakdwn, int int_reportIndex){
		return performSearch(clientPMNs.value, trafPeriodRange, ServiceType.value, EventType.value, TrafficDirection.value, Currency.value, latestOriginal, bool_detailPerMonth, bool_moCallDestBreakdwn, int_reportIndex);
	}*/

	private void fillBasicSearchFields(String clientPMNs,String trafPeriodRange,String srvType,String evType,String trfDir,String currency,String infoSet,boolean detailPerMonth,boolean moCallDestBreakdwn){

		String message=" ";

		com.javaScript_ScrollIntoMIDDLEView_AndHighlight(select_ServiceType);
		if(clientPMNs!=null){
			if(!com.waitForElementTobe_NotVisible(select_HomeOperator_Left, 1)){
				com.deselectAllValues_Shuttle_DoubleClick(select_HomeOperator_Right);
				com.selectByPartialVisibleText_DoubleClick_FromCommaSeparated(select_HomeOperator_Left, clientPMNs);
				message=message+" |clientPMNs- '"+clientPMNs+"'";
			}
		}

		if(trafPeriodRange!=null){
			if(com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator)) {
				String startTrafPeriod=trafPeriodRange.split(",")[0];
				String endTrafPeriod=trafPeriodRange.split(",")[1];
				com.deselectAllValues_Shuttle_DoubleClick(select_TrafficPeriodRight);
				comm.selectTrafficPeriod(select_TrafficPeriodLeft,startTrafPeriod,endTrafPeriod);
				message=message+" |startTrafPeriod- '"+startTrafPeriod+"'";
				message=message+" |endTrafPeriod- '"+endTrafPeriod+"'";
			}
		}

		if(srvType!=null) {
			com.selectByVisibleText(select_ServiceType, srvType);//"Voice");
			message=message+" |serviceType- '"+srvType+"'";
		}

		if(evType!=null) {
			com.selectByVisibleText(select_EventType, evType);//"MO");
			message=message+" |eventType- '"+evType+"'";
		}

		if(trfDir!=null) {
			com.selectByPartialVisibleText(select_TrafficDirection, trfDir);//"Customer/Outbound");
			message=message+" |trafficDirection- '"+trfDir+"'";
		}

		if(currency!=null) {
			com.selectByPartialVisibleText(select_Currency, currency);//"AUD");
			message=message+" |currency- '"+currency+"'";
		}

		if(infoSet!=null) {
			if(infoSet.toLowerCase().contains("latest")) {
				com.click(radio_Latest);
			}else {
				com.click(radio_Original);
			}
			message=message+" |infoSet- '"+infoSet+"'";
		}

		if(detailPerMonth) {
			com.javaScript_Click(checkbox_DetailPerMonth);
			message=message+" |detailPerMonth- '"+detailPerMonth+"'";
		}

		if(moCallDestBreakdwn) {
			com.javaScript_Click(checkbox_MOCallDestinationBreakdown);
			message=message+" |moCallDestBreakdwn- '"+moCallDestBreakdwn+"'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search operation for data :"+message);

	}

	private String performSearch_GetCountry_ForPartnerPMN(String clientPMNs, String trafPeriodRange, String partnerPMN) {
		CustomReporter.createNode("Searching overview report and getting the displayed country for "
				+ "clientPMNs["+ clientPMNs + "], trafPeriodRange[" + trafPeriodRange + "] and partnerPMN[" + partnerPMN + "]");
		String country="";

		if (performSearch(clientPMNs, trafPeriodRange, null, null, null, null, null, false, false,-1)) {
			com.selectByIndex(comm.select_Reports,0);
			WebTable tab= new WebTable(comm.table_ResultTab);
			tab.initHeaderColumnList(headerRow);
			comm.applyColumnFilter(headerRow, tab.getColumnNumber("Partner PMN"), partnerPMN);
			country=tab.getCellText(3, tab.getColumnNumber("Country"));
			CustomReporter.report(STATUS.PASS, "Country displayed on Overview Report: "+country);
			com.click(button_ResetPage);
		}

		return country;
	}


	public void OR_T05ReportColumnsMOCallDestinationBreakdownNOTselected(String jsonObjName) {

		JSONManager json= new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"ORSearch");
		if(performSearch(json)){
			String colName="Call Destination";
			WebTable tab= new WebTable(comm.table_ResultTab);
			int colnum=tab.initHeaderColumnList(headerRow).getColumnNumber(colName);
			if(colnum==-1){
				CustomReporter.report(STATUS.PASS, colName+" column is not getting displayed");
				performSearch(null, null, null, null, null, null, null, false, true, 0);
				int rowNum=tab.getRowWithCellText(colName);
				if(rowNum>0){
					CustomReporter.report(STATUS.PASS, colName+" column is displayed in the Table");
				}else{
					CustomReporter.report(STATUS.FAIL, colName+" column is NOT displayed in the Table");
				}
			}else{
				CustomReporter.report(STATUS.FAIL, colName+" column is getting displayed");
			}

		}

	}

	public void OR_T07CheckingAndCalculatingDataForCallDestinationBreakdown(String jsonObjName) {

		JSONManager json= new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"ORSearch");
		if(performSearch(json)){

			//Verifying the Calculations of {Discount Charge} and {Discount Achieved} , 
			//Getting the values of {Traffic Volume} and {Total TAP Charges Excluding Tax} for matching with IOT Traffic Data page
			String trafVol_U="trafVol_U";
			comm.applyComputation(trafVol_U, "", "Traffic Volume");
			String totalPostDiscChrgExcTax_U="totalPostDiscChrgExcTax_U";
			comm.applyComputation(totalPostDiscChrgExcTax_U, "", "Total Post-Discounted Charges Excluding Tax");
			String totalTAPChargeExTax_U="totalTAPChargeExTax_U";
			comm.applyComputation(totalTAPChargeExTax_U, "", "Total TAP Charges Excluding Tax");
			String avgPostDisIOTExTax_U="avgPostDisIOTExTax_U";
			comm.applyComputation(avgPostDisIOTExTax_U, "", "Average Post-Discounted IOT Excluding Tax");
			String credNoteDisAchExTax_U="credNoteDisAchExTax_U";
			comm.applyComputation(credNoteDisAchExTax_U, "", "Credit Note Discount Achieved Excluding Tax");

			WebTable tab= new WebTable(comm.table_ResultTab);
			tab.initHeaderColumnList(headerRow);
			int colNumTrafVol=tab.getColumnNumber(trafVol_U);
			int colNumTotalPostDiscChrgExcTax=tab.getColumnNumber(totalPostDiscChrgExcTax_U);
			int colNumTotalTAPChargeExTax=tab.getColumnNumber(totalTAPChargeExTax_U);
			int colNumAvgPostDisIOTExTax=tab.getColumnNumber(avgPostDisIOTExTax_U);
			int colNumCredNoteDisAchExTax=tab.getColumnNumber(credNoteDisAchExTax_U);


			comm.applyColumnFilter(headerRow, tab.getColumnNumber("Partner PMN"), json.getStr("partner"));
			int callDest=tab.getColumnNumber("Call Destination");

			String homeTraffVol="",intTraffVol="",locTraffVol="",totalPostDiscChrgExcTax_Page="",
					creditNoteDiscountAchieved_Page="",totalPostDiscChrgExcTax_Calc="",creditNoteDiscountAchieved_Calc="";

			CustomReporter.createNode("To calculate and check  Discounted Charge values we need to:"
					+ "<br/>"
					+ "o Total Post-Discounted Charges Excluding Tax Voice MO Home= Traffic Volume Home * Average Post-Discounted IOT Voice MO Home"
					+ "<br/>"
					+ "o Total Post-Discounted Charges Excluding Tax Voice MO Local= Traffic Volume  Local * Average Post-Discounted IOT Voice MO  Local"
					+ "<br/>"
					+ "o Total Post-Discounted Charges Excluding Tax Voice MO International= Traffic Volume International * Average Post-Discounted IOT Voice MO International"
					+ "<br/>"
					+ "<br/>"
					+ "To calculate and check Discount Achieved values we need to:"
					+ "<br/>"
					+ "o Credit Note Discount Achieved Excluding Tax Home= Total TAP Charges Excluding Tax Home  - Total Post-Discounted Charges Excluding Tax Home"
					+ "<br/>"
					+ "o Credit Note Discount Achieved Excluding Tax Local= Total TAP Charges Excluding Tax Local  - Total Post-Discounted Charges Excluding Tax Local"
					+ "<br/>"
					+ "o Credit Note Discount Achieved Excluding Tax International= Total TAP Charges Excluding Tax International  - Total Post-Discounted Charges Excluding Tax International"
					+ "<br/>"
					);

			int rowCount=tab.getRowCount();
			for (int row = headerRow+1; row <= rowCount; row++) {
				String temp=tab.getCellText(row, callDest);
				if(temp.contains("Home")){
					CustomReporter.createNode("For Home");
					homeTraffVol=Util.removeCommas(tab.getCellText(row, colNumTrafVol));
					String avgPostDisIOTExTax=tab.getCellText(row, colNumAvgPostDisIOTExTax);
					totalPostDiscChrgExcTax_Calc=Util.BD(homeTraffVol).multiply(Util.BD(avgPostDisIOTExTax)).toPlainString();
					String totalTAPChargeExTax=tab.getCellText(row, colNumTotalTAPChargeExTax);
					totalPostDiscChrgExcTax_Page=Util.removeCommas(tab.getCellText(row, colNumTotalPostDiscChrgExcTax));
					Util.comparator(totalPostDiscChrgExcTax_Calc,totalPostDiscChrgExcTax_Page, "Total Post-Discounted Charges Excluding Tax - Home");
					creditNoteDiscountAchieved_Calc=Util.BD(totalTAPChargeExTax).subtract(Util.BD(totalPostDiscChrgExcTax_Page)).toPlainString();
					creditNoteDiscountAchieved_Page=Util.removeCommas(tab.getCellText(row, colNumCredNoteDisAchExTax));
					Util.comparator(creditNoteDiscountAchieved_Calc,creditNoteDiscountAchieved_Page, "Credit Note Discount Achieved Excluding Tax - Home");
					CustomReporter.report(STATUS.INFO, "HOME - <br/>"
							+ " homeTraffVol : "+homeTraffVol+"<br/>"
							+ " totalPostDiscChrgExcTax_Calc("+totalPostDiscChrgExcTax_Calc+") = homeTraffVol("+homeTraffVol+") * avgPostDisIOTExTax("+avgPostDisIOTExTax+")<br/>"
							+ " totalPostDiscChrgExcTax_Page : "+totalPostDiscChrgExcTax_Page+"<br/>"
							+ " creditNoteDiscountAchieved_Calc("+creditNoteDiscountAchieved_Calc+") = totalTAPChargeExTax("+totalTAPChargeExTax+") - totalPostDiscChrgExcTax_Page("+totalPostDiscChrgExcTax_Page+")<br/>"
							+ " creditNoteDiscountAchieved_Page : "+creditNoteDiscountAchieved_Page);
				}else if (temp.contains("International")) {
					CustomReporter.createNode( "For International");
					intTraffVol=Util.removeCommas(tab.getCellText(row, colNumTrafVol));
					String avgPostDisIOTExTax=tab.getCellText(row, colNumAvgPostDisIOTExTax);
					totalPostDiscChrgExcTax_Calc=Util.BD(intTraffVol).multiply(Util.BD(avgPostDisIOTExTax)).toPlainString();
					String totalTAPChargeExTax=tab.getCellText(row, colNumTotalTAPChargeExTax);
					totalPostDiscChrgExcTax_Page=Util.removeCommas(tab.getCellText(row, colNumTotalPostDiscChrgExcTax));
					Util.comparator(totalPostDiscChrgExcTax_Calc,totalPostDiscChrgExcTax_Page, "Total Post-Discounted Charges Excluding Tax - International");
					creditNoteDiscountAchieved_Calc=Util.BD(totalTAPChargeExTax).subtract(Util.BD(totalPostDiscChrgExcTax_Page)).toPlainString();
					creditNoteDiscountAchieved_Page=Util.removeCommas(tab.getCellText(row, colNumCredNoteDisAchExTax));
					Util.comparator(creditNoteDiscountAchieved_Calc,creditNoteDiscountAchieved_Page, "Credit Note Discount Achieved Excluding Tax - International");
					CustomReporter.report(STATUS.INFO, "INTERNATIONAL - <br/>"
							+ " intTraffVol : "+intTraffVol+"<br/>"
							+ " totalPostDiscChrgExcTax_Calc("+totalPostDiscChrgExcTax_Calc+") = intTraffVol("+intTraffVol+") * avgPostDisIOTExTax("+avgPostDisIOTExTax+")<br/>"
							+ " totalPostDiscChrgExcTax_Page : "+totalPostDiscChrgExcTax_Page+"<br/>"
							+ " creditNoteDiscountAchieved_Calc("+creditNoteDiscountAchieved_Calc+") = totalTAPChargeExTax("+totalTAPChargeExTax+") - totalPostDiscChrgExcTax_Page("+totalPostDiscChrgExcTax_Page+")<br/>"
							+ " creditNoteDiscountAchieved_Page : "+creditNoteDiscountAchieved_Page);

				}else if (temp.contains("Local")) {
					CustomReporter.createNode("For Local");
					locTraffVol=Util.removeCommas(tab.getCellText(row, colNumTrafVol));
					String avgPostDisIOTExTax=tab.getCellText(row, colNumAvgPostDisIOTExTax);
					totalPostDiscChrgExcTax_Calc=Util.BD(locTraffVol).multiply(Util.BD(avgPostDisIOTExTax)).toPlainString();
					String totalTAPChargeExTax=tab.getCellText(row, colNumTotalTAPChargeExTax);
					totalPostDiscChrgExcTax_Page=Util.removeCommas(tab.getCellText(row, colNumTotalPostDiscChrgExcTax));
					Util.comparator(totalPostDiscChrgExcTax_Calc,totalPostDiscChrgExcTax_Page, "Total Post-Discounted Charges Excluding Tax - Local");
					creditNoteDiscountAchieved_Calc=Util.BD(totalTAPChargeExTax).subtract(Util.BD(totalPostDiscChrgExcTax_Page)).toPlainString();
					creditNoteDiscountAchieved_Page=Util.removeCommas(tab.getCellText(row, colNumCredNoteDisAchExTax));
					Util.comparator(creditNoteDiscountAchieved_Calc,creditNoteDiscountAchieved_Page, "Credit Note Discount Achieved Excluding Tax - Local");
					CustomReporter.report(STATUS.INFO, "LOCAL - <br/>"
							+ " locTraffVol : "+locTraffVol+"<br/>"
							+ " totalPostDiscChrgExcTax_Calc("+totalPostDiscChrgExcTax_Calc+") = locTraffVol("+locTraffVol+") * avgPostDisIOTExTax("+avgPostDisIOTExTax+")<br/>"
							+ " totalPostDiscChrgExcTax_Page : "+totalPostDiscChrgExcTax_Page+"<br/>"
							+ " creditNoteDiscountAchieved_Calc("+creditNoteDiscountAchieved_Calc+") = totalTAPChargeExTax("+totalTAPChargeExTax+") - totalPostDiscChrgExcTax_Page("+totalPostDiscChrgExcTax_Page+")<br/>"
							+ " creditNoteDiscountAchieved_Page : "+creditNoteDiscountAchieved_Page);

				}
			}

			CustomReporter.createNode(
					"Getting the {Total TAP Charges Excluding Tax} value for verification of {Gross Charge} value "
							+ "& Verifying the Data with <b>View IOT Traffic Data Page</b>");

			json= new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"ORSearch-Single-TrafPerRange");
			if(performSearch(json)){
				int colNumTotalTAPChargesExcludingTax=tab.getColumnNumber( "Total TAP Charges Excluding Tax");
				String loc_TotalTAPChargesExcludingTax="";
				String int_TotalTAPChargesExcludingTax="";
				String home_TotalTAPChargesExcludingTax="";
				rowCount=tab.getRowCount();
				for (int row = headerRow+1; row <= rowCount; row++) {
					String temp=tab.getCellText(row, callDest);
					if(temp.contains("Home")){
						home_TotalTAPChargesExcludingTax=Util.removeCommas(tab.getCellText(row, colNumTotalTAPChargesExcludingTax));
					}else if (temp.contains("International")) {
						int_TotalTAPChargesExcludingTax=Util.removeCommas(tab.getCellText(row, colNumTotalTAPChargesExcludingTax));
					}else if (temp.contains("Local")) {
						loc_TotalTAPChargesExcludingTax=Util.removeCommas(tab.getCellText(row, colNumTotalTAPChargesExcludingTax));
					}
				}
				CustomReporter.report(STATUS.INFO, 
						" home_TotalTAPChargesExcludingTax : "+home_TotalTAPChargesExcludingTax+"<br/>"
								+" int_TotalTAPChargesExcludingTax : "+int_TotalTAPChargesExcludingTax+"<br/>"
								+" loc_TotalTAPChargesExcludingTax : "+loc_TotalTAPChargesExcludingTax+"<br/>");


				ViewIOTTrafficData viewIOTTrafficData=new ViewIOTTrafficData();
				viewIOTTrafficData.OR_T07CheckingAndCalculatingDataForCallDestinationBreakdown(Module_OverviewReport.jsonFilePath,
						jsonObjName, homeTraffVol, intTraffVol, locTraffVol, home_TotalTAPChargesExcludingTax,
						int_TotalTAPChargesExcludingTax, loc_TotalTAPChargesExcludingTax);

			}

		}


	}


	public void OR_T06TestingMOCallDestinationBreakdownCheckbox(String jsonObjName) {

		CustomReporter.createNode("verify Call Destination Breakdown Column Data For:-  Voice MO");
		JSONManager json= new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"ORSearch");
		if(performSearch(json)){


			if(com.isElementPresent(comm.table_ResultTab, "Result Table")){
				String colName="Call Destination";
				WebTable tab= new WebTable(comm.table_ResultTab);
				tab.initHeaderColumnList(headerRow);
				int colNum=tab.getColumnNumber(colName);
				if(colNum!=-1){
					CustomReporter.report(STATUS.PASS, colName+" column is getting displayed");
					verifyCallDestinationBreakdownColumnData(colNum, "Home");
					verifyCallDestinationBreakdownColumnData(colNum, "International");
					verifyCallDestinationBreakdownColumnData(colNum, "Local");
					verifyCallDestinationBreakdownColumnData(colNum, "unknown");

					// "3. The same scenario can be and for SMS MO if client is
					// delivering SMS MO breakdown in their DCH files Check if
					// for AUSTA Client SMS MO values are also broken-down"
					CustomReporter.createNode("verify Call Destination Breakdown Column Data For:-  SMS MO");
					json= new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"ORSearch-SMSMO");
					if(performSearch(json)){
						verifyCallDestinationBreakdownColumnData(colNum, "Home");
						verifyCallDestinationBreakdownColumnData(colNum, "International");
						verifyCallDestinationBreakdownColumnData(colNum, "Local");
						verifyCallDestinationBreakdownColumnData(colNum, "unknown");
					}

					//4. For all other Service and Event Types (meaning Voice MT, SMS MT, Data MB), ‘n/a’ should  be reported
					CustomReporter.createNode("Verifying Voice MT breakdown should be 'n/a' for AUSTA Client");
					json= new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"ORSearch-VoiceMT");
					if(performSearch(json)){
						verifyCallDestinationBreakdownColumnData(colNum, "n/a");
					}

					CustomReporter.createNode("Verifying SMS MT breakdown should be 'n/a' for AUSTA Client");
					json= new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"ORSearch-SMSMT");
					if(performSearch(json)){
						verifyCallDestinationBreakdownColumnData(colNum, "n/a");
					}

					CustomReporter.createNode("Verifying DATA MB breakdown should be 'n/a' for AUSTA Client");
					json= new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"ORSearch-DataMB");
					if(performSearch(json)){
						verifyCallDestinationBreakdownColumnData(colNum, "n/a");
					}


					//6. Check if change of report format to Service and Event breakdown Call Destination column will still be visible together with Call Destination breakdown columns
					CustomReporter.createNode("Verifying Call Destination column should not change after setting report format to Service and Event breakdown");
					com.click(button_ServiceAndEventBreakdown, "Service and Event breakdown button");
					int foundColNum=tab.initHeaderColumnList(headerRow).getColumnNumber(colName);
					if(foundColNum!=-1){
						CustomReporter.report(STATUS.PASS, colName+" column is displayed in the Table");
					}else{
						CustomReporter.report(STATUS.FAIL, colName+" column is NOT displayed in the Table");
					}
				}else{
					CustomReporter.report(STATUS.FAIL, colName+" column is NOT getting displayed");
				}
			}
		}


	}

	private void verifyCallDestinationBreakdownColumnData(int colNum,String callDest) {
		if(comm.applyColumnFilter(headerRow, colNum, callDest)){
			WebTable tab=new WebTable(comm.table_ResultTab);
			String firstRowData=tab.getCellText(3, colNum);
			if(firstRowData.contains(callDest)){
				CustomReporter.report(STATUS.PASS, "Data for "+callDest+" is displayed");
			}else{
				CustomReporter.report(STATUS.FAIL, "Data for "+callDest+" is NOT displayed");
			}
			comm.removeAppliedColumnFilter(callDest);
		}

	}

	public void OR_T03_09Testingbuttons_SMOKE_PART1(String... jsonObjName) {
		JSONManager json = new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName);
		String clientPMN=json.getStr("clientPMNs");
		String traffRange=json.getStr("trafPeriodRange");
		if (performSearch(json)) {

			if(com.isElementPresent(comm.table_ResultTab, "Result Table")){
				CustomReporter.createNode( "Verifying First Row Data on Report");
				List<String> homeOperatorsList=com.getAllOptionsVisibleText(select_HomeOperator_Right);
				String homeOperatorName="";
				for (String homeOperator : homeOperatorsList) {
					homeOperatorName=homeOperator.split(" - ")[1];
					String rowText="Home Operator Name : "+homeOperatorName;
					WebTable tab= new WebTable(comm.table_ResultTab);
					int rowCount=tab.getRowWithCellText(rowText);
					if(rowCount!=-1){
						CustomReporter.report(STATUS.PASS, "Row '"+rowText+"' is successfully displayed");
					}else{
						CustomReporter.report(STATUS.FAIL, "Row '"+rowText+"' is NOT displayed");
					}
				}

				CustomReporter.createNode( "Verifying Available Reports dropdown Data");
				if(com.isElementPresent(comm.select_Reports, "Available Reports dropdown")){
					List<String> list= new ArrayList<String>(Arrays.asList(json.getStrArr("reportsList")));
					List<String> listPage= com.getAllOptionsVisibleText(comm.select_Reports);
					Util.comparator_List_NonNumbers(list, listPage, "Available Reports dropdown");
				}

				//Verifying the Download and Subscription feature
				perform_DownloadSubscription();

				CustomReporter.createNode( "Verifying Service And Event Breakdown button functionality");
				com.click(button_ServiceAndEventBreakdown,"Service And Event Breakdown button");

				String rowText=json.getStr("filter-ServType");
				By xpath=By.xpath("//a[contains(@class,'controlsLabel')][contains(.,'"+rowText+"')]");
				com.isElementPresent(xpath,"Filter for '"+rowText+"'");

				rowText=json.getStr("filter-EvType");
				xpath=By.xpath("//a[contains(@class,'controlsLabel')][contains(.,'"+rowText+"')]");
				com.isElementPresent(xpath,"Filter for '"+rowText+"'");

				rowText=json.getStr("filter-ServEvType");
				xpath=By.xpath("//th[contains(.,'"+rowText+"')]");
				com.isElementPresent(xpath,"Filter for '"+rowText+"'");

				CustomReporter.createNode( "Verifying Reset Page button functionality");
				com.click(button_ResetPage, "Reset Page button");

				if (performSearch(clientPMN, traffRange, null, null, null, null, null, false, false,-1)) {
					if(com.isElementPresent(comm.table_ResultTab, "Result Table")){
						String selectedReport=com.getText(com.getFirstSelectedOption(comm.select_Reports));
						if(selectedReport.equals(json.getStr("reportAfterReset"))){
							CustomReporter.report(STATUS.PASS, "'"+json.getStr("reportAfterReset")+"' is selected by default");
						}else{
							CustomReporter.report(STATUS.FAIL, "'"+json.getStr("reportAfterReset")+"' is NOT selected by default but '"+selectedReport+"' is selected");
						}
					}
				}
			}
		}
	}

	/**
	 * This method is checking 'Primary report' is selected by default and
	 * verifying the files are downloaded or not???
	 * */
	public void OR_T03_09Testingbuttons_SMOKE_PART2(String... jsonObjName) {
		JSONManager json = new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName);
		if (performSearch(json)) {
			if(com.isElementPresent(comm.table_ResultTab, "Result Table")){
				String selectedReport=com.getText(com.getFirstSelectedOption(comm.select_Reports));
				if(selectedReport.equals(json.getStr("selectedReport"))){
					CustomReporter.report(STATUS.PASS, "'Primary report' is selected by default");
				}else{
					CustomReporter.report(STATUS.FAIL, "'Primary report' is NOT selected by default but '"+selectedReport+"' is selected");
				}
			}
		}

		//Verifying the csv and html downloaded files 
		String absPath=Util.getDownloadedFilePath(json.getStr("downloaded-csv"));
		if(absPath!=null){
			CustomReporter.report(STATUS.PASS, "'"+json.getStr("downloaded-csv")+"' downloaded");
			Util.forceDelete(absPath);
		}else{
			CustomReporter.report(STATUS.FAIL, "'"+json.getStr("downloaded-csv")+"' is NOT downloaded");
		}

		absPath=Util.getDownloadedFilePath(json.getStr("downloaded-html"));
		if(absPath!=null){
			CustomReporter.report(STATUS.PASS, "'"+json.getStr("downloaded-html")+"' downloaded");
			Util.forceDelete(absPath);
		}else{
			CustomReporter.report(STATUS.FAIL, "'"+json.getStr("downloaded-html")+"' is NOT downloaded");
		}
	}



	private void perform_DownloadSubscription() {
		CustomReporter.createNode( "Verifying the Download and Subscription functionality");

		String subject ="Overview Report"+Util.getTimeStamp_InMilliSec();

		//Triggering the Email
		comm.downloadEMAIL(Constant.EMAIL,subject);

		//Downloading the CSV
		com.refresh();
		comm.downloadCSV();

		//Downloading the HTML
		com.refresh();
		comm.downloadHTML();

		//Verifying the sent Mail
		//MailUtil.checkMailSubject(subject);
		CustomReporter.report(STATUS.WARNING, "Mail recieved!, Confirmation code is commented, because we need to have a test email account set up");



	}

	private void verify_MarketShareAndProportionalShareWithPassedCountry(String country, String jsonFilePath,String jsonObjName) {

		CustomReporter.createNode("When MO Call Destination Breakdown is checked Market Share and Proportional Share columns should be hidden");

		JSONManager json = new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName);

		//When MO Call Destination Breakdown is checked Market Share and Proportional Sare columns should be hidden 
		json = new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"forMOCallDestBrkdwn");
		if (performSearch(json)) {
			WebTable tab= new WebTable(comm.table_ResultTab);
			int marketShareCol=tab.initHeaderColumnList(headerRow).getColumnNumber("Market Share");
			if(marketShareCol==-1){
				CustomReporter.report(STATUS.PASS, "Market Share column got hidden");
			}else{
				CustomReporter.report(STATUS.FAIL, "Market Share column did not hide");
			}
			int proportionalShareCol=tab.getColumnNumber("Proportional Share");
			if(proportionalShareCol==-1){
				CustomReporter.report(STATUS.PASS, "Proportional Share column got hidden");
			}else{
				CustomReporter.report(STATUS.FAIL, "Proportional Share column did not hide");
			}
		}


		CustomReporter.createNode("Checking market share column calculation and prop share col should have nothing");

		json = new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"forMarkShare&PropShare");

		if (performSearch(json)) {


			comm.applyComputation("MarketShare_UnRounded","","Market Share");
			comm.applyComputation("ProportionalShare_UnRounded","","Proportional Share");
			comm.applyComputation("TrafVol_UnRounded","","Traffic Volume");
			WebTable tab= new WebTable(comm.table_ResultTab);
			int marketShareCol=tab.initHeaderColumnList(headerRow).getColumnNumber("MarketShare_UnRounded");
			int proportionalShareCol=tab.getColumnNumber("ProportionalShare_UnRounded");
			int trafficVolUnRoundedCol=tab.getColumnNumber("TrafVol_UnRounded");
			//tab.initHeaderColumnList(headerRow);
			comm.applyColumnFilter(headerRow, tab.getColumnNumber("Country"), country);
			comm.setRowsPerPage("All");

			//Calculate and Verify Market Share
			int lastRow=tab.getRowCount();
			String pageMarkShare="";
			String calcMarkShare="";
			String trafVol_val="";
			int initRow=3;
			String sum_newColumn=comm.getSumOfColumnValues(initRow,trafficVolUnRoundedCol);
			Util.comparator_PageValues("-", tab.getCellText(initRow, proportionalShareCol) , "Proportional Share Col");
			for (int row = initRow; row <= lastRow; row++) {
				trafVol_val=tab.getCellText(row, trafficVolUnRoundedCol);
				if("0".equals(trafVol_val)){
					calcMarkShare="0";
				}else{
					calcMarkShare=Util.BD(trafVol_val).divide(Util.BD(sum_newColumn), 20, RoundingMode.HALF_EVEN).multiply(Util.BD("100")).toString();
				}
				pageMarkShare=tab.getCellText(row, marketShareCol);
				Util.comparator(calcMarkShare, pageMarkShare, "Market Share");
			}

			CustomReporter.createNode("Checking prop share column calculation and market share col should have nothing");
			//Calculate and Verify Proportional Share
			json = new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"forPropShare");
			if (performSearch(json)) {
				String pagePropShare="";
				String calcPropShare="";
				sum_newColumn=comm.getSumOfColumnValues(initRow,trafficVolUnRoundedCol);

				Util.comparator_PageValues("-", tab.getCellText(initRow, marketShareCol) , "Market Share Col");
				for (int row = initRow; row <= lastRow; row++) {
					trafVol_val=tab.getCellText(row, trafficVolUnRoundedCol);
					if("0".equals(trafVol_val)){
						calcPropShare="0";
					}else{
						calcPropShare=Util.BD(trafVol_val).divide(Util.BD(sum_newColumn), 20, RoundingMode.HALF_EVEN).multiply(Util.BD("100")).toString();
					}
					pagePropShare=tab.getCellText(row, proportionalShareCol);
					Util.comparator(calcPropShare, pagePropShare, "Proportional Share");
				}

			}

		}

		com.javaScript_Click(button_ResetPage);
	}

	public void verify_SearchResultSection_UI() {
		if(performSearch(TADIG.AUSTA.value, "1016,1016", ServiceType.VOICE.value, EventType.MO.value, TrafficDirection.CustomerOutbound.value, Currency.SDR.value, null, false, false, -1)){


			CustomReporter.report(STATUS.INFO, "Apex toolbar verification");
			com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Select columns for search icon");
			com.isElementPresent(comm.text_Search, "Apex Search textfield");
			com.isElementPresent(comm.button_Go, "Apex Go button");
			com.isElementPresent(comm.select_Reports, "Select Report dropdown");
			com.isElementPresent(comm.select_RowsPerPage, "Select Report dropdown");
			com.isElementPresent(comm.button_Action, "Actions button");

			com.isElementPresent(By.xpath("//div[contains(@id,'_control_panel_content')]//li[contains(.,'Operator Name')]"), "Operator Name control break");
			com.isElementPresent(By.xpath("//div[contains(@id,'_control_panel_content')]//li[contains(.,'Direction')]"), "Direction control break");
			com.isElementPresent(By.xpath("//th[contains(.,'Operator Name : Telstra Corporation Limited')]"), "Operator Name : Telstra Corporation Limited text");




			CustomReporter.report(STATUS.INFO, "Verifying the column names");
			boolean flag=true;
			for (int col = 1; col <= colNames.size(); col++) {
				WebTable tab= new WebTable(comm.table_ResultTab);
				if(!tab.getCellText(headerRow, col).equals(colNames.get(col-1))){
					CustomReporter.report(STATUS.FAIL, "Column '"+colNames.get(col-1)+"' is not present on position '"+col+"'");
					flag=false;
				}
			}
			if (flag) {
				CustomReporter.report(STATUS.PASS, "All columns are properly displayed");
			}

		}
	}

	public void OR_T04DetailPerMonthCheckbox(String jsonObjName) {

		String agreementName="AutomationTestingAgreement"+Util.getTimeStamp_InMilliSec();

		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture,ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		CreateEditIOTDiscountAgreement_P303 createEditIOTDiscountAgreement= maintainIOTDiscountAgreementsPage.clickOnCreateIOTDiscountAgreementButton();
		JSONManager json = new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"createAgreement");
		createEditIOTDiscountAgreement.createDiscAgreement(agreementName,json.getStr("clientName"),json.getStrArr("partner"),json.getStr("startDate"),json.getStr("endDate"),json.getBool("bool_checkIncludeSatelite"));

		for (int i = 0; i <json.getJsonArrLength("DiscParam") ; i++) {
			createEditIOTDiscountAgreement.addDiscountParameters(i,json);					
		}

		if(createEditIOTDiscountAgreement.calculateAgreement(agreementName)){
			navigate.traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting,ihp.link_Reporting_OverviewReport);
			verifyDetailsPerMonthCheckboxFunc(Module_OverviewReport.jsonFilePath, jsonObjName,agreementName);

		}

		//DELETING THE AGREEMENT
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture,ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		maintainIOTDiscountAgreementsPage.searchAndDeleteAgreement(agreementName);

	}

	public void OR_T10_11CountryColumn_AdditionalColumnsForVersion2(String jsonObjName) {
		String prevCountry_Page = "", newCountry_Page = "";
		String newCountry="";

		CustomReporter.createNode("Going to Overview Report to get the current country");
		JSONManager json=new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName);
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507,
				ihp.link_Reporting, ihp.link_Reporting_OverviewReport);


		prevCountry_Page = performSearch_GetCountry_ForPartnerPMN(json.getStr("clientOperator"), json.getStr("trafPeriodRange"),
				json.getStr("partnerPMN"));

		CustomReporter.createNode("Going to Maintain Client Defined Countries to create a new country");

		MaintainClientDefinedCountries maintainClientDefinedCountries = new MaintainClientDefinedCountries();
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.MaintainClientDefinedCountries,
				ihp.link_IOTRONSystemAdministration,
				ihp.link_IOTRONSystemAdministration_MaintainClientDefinedCountries); 

		newCountry=maintainClientDefinedCountries.createNewCountry(json.getStr("operator"), "Automation", "X");

		CustomReporter.createNode("Going to Maintain Client Defined Countries to check country can be deleted");

		maintainClientDefinedCountries.verifyCountry_CanBe_Deleted(newCountry);

		CustomReporter.createNode("Going to Maintain Client Defined Countries Mapping to change the country mapping");

		MaintainClientDefinedCountriesMapping clientDefinedCountriesMap = new MaintainClientDefinedCountriesMapping();
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.MaintainClientDefinedCountriesMapping,
				ihp.link_IOTRONSystemAdministration,
				ihp.link_IOTRONSystemAdministration_MaintainClientDefinedCountriesMapping); 

		if (clientDefinedCountriesMap.changeCountry(json.getStr("operator"), json.getStr("partnerPMN"), newCountry)) {
			navigate.traverseMenu_VerifyPageTitle( PagesTitle.MaintainClientDefinedCountries,
					ihp.link_IOTRONSystemAdministration,
					ihp.link_IOTRONSystemAdministration_MaintainClientDefinedCountries);

			CustomReporter.createNode("Going to Maintain Client Defined Countries to check after mapping country can not be deleted");

			maintainClientDefinedCountries.verifyCountry_CanNOTBe_Deleted(json.getStr("operator"), newCountry);

			CustomReporter.createNode("Verifying the newly created and Mapped country is reflecting on Overview Report");

			navigate.traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting,
					ihp.link_Reporting_OverviewReport);

			newCountry_Page = performSearch_GetCountry_ForPartnerPMN(json.getStr("clientOperator"), json.getStr("trafPeriodRange"),
					json.getStr("partnerPMN"));

			if (!newCountry_Page.equals(prevCountry_Page)) {
				CustomReporter.report(STATUS.PASS, "Country column value successfully changed from '"
						+ prevCountry_Page + "' to '" + newCountry_Page + "'");
			} else {
				CustomReporter.report(STATUS.FAIL, "Country column value NOT changed from '"
						+ prevCountry_Page + "' to '" + newCountry_Page + "'");
			}
		}

		CustomReporter.createNode("Reversing the country mapping");

		navigate.traverseMenu_VerifyPageTitle( PagesTitle.MaintainClientDefinedCountriesMapping,
				ihp.link_IOTRONSystemAdministration,
				ihp.link_IOTRONSystemAdministration_MaintainClientDefinedCountriesMapping);

		clientDefinedCountriesMap.changeCountry(json.getStr("operator"), json.getStr("partnerPMN"), prevCountry_Page);


		CustomReporter.createNode("Deleting the Newly Created country");

		navigate.traverseMenu_VerifyPageTitle( PagesTitle.MaintainClientDefinedCountries,
				ihp.link_IOTRONSystemAdministration,
				ihp.link_IOTRONSystemAdministration_MaintainClientDefinedCountries);
		maintainClientDefinedCountries.deleteCountry(json.getStr("operator"), newCountry);


	}

	public void OR_T17_18MarketAndProportionalShareColumn(String jsonObjName) {
		JSONManager json = new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName);

		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting,
				ihp.link_Reporting_OverviewReport);
		String country= json.getStr("country");
		verify_MarketShareAndProportionalShareWithPassedCountry(country,Module_OverviewReport.jsonFilePath, jsonObjName);

	}



	public void OR_T19MarketShareAndProportionalShareWithPseudoCountry(String jsonObjName) {


		JSONManager json = new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName);
		String operator=  json.getStr("operator");
		String partnerPMN=  json.getStr("partnerPMN");
		String prevCountry_Page=json.getStr("prevCountry_Page");
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainClientDefinedCountries,
				ihp.link_IOTRONSystemAdministration,
				ihp.link_IOTRONSystemAdministration_MaintainClientDefinedCountries);

		MaintainClientDefinedCountries maintainClientDefinedCountries = new MaintainClientDefinedCountries();

		String newCountry=maintainClientDefinedCountries.createNewCountry(operator,"Automation","X"); 

		navigate.traverseMenu_VerifyPageTitle( PagesTitle.MaintainClientDefinedCountriesMapping,
				ihp.link_IOTRONSystemAdministration,
				ihp.link_IOTRONSystemAdministration_MaintainClientDefinedCountriesMapping);

		MaintainClientDefinedCountriesMapping clientDefinedCountriesMap = new MaintainClientDefinedCountriesMapping();

		if (clientDefinedCountriesMap.changeCountry(operator, partnerPMN,newCountry)) {
			navigate.traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting,
					ihp.link_Reporting_OverviewReport);

			verify_MarketShareAndProportionalShareWithPassedCountry(newCountry,Module_OverviewReport.jsonFilePath,jsonObjName);
		}

		navigate.traverseMenu_VerifyPageTitle( PagesTitle.MaintainClientDefinedCountriesMapping,
				ihp.link_IOTRONSystemAdministration,
				ihp.link_IOTRONSystemAdministration_MaintainClientDefinedCountriesMapping);

		clientDefinedCountriesMap.changeCountry(operator,partnerPMN,prevCountry_Page);


		navigate.traverseMenu_VerifyPageTitle( PagesTitle.MaintainClientDefinedCountries,
				ihp.link_IOTRONSystemAdministration,
				ihp.link_IOTRONSystemAdministration_MaintainClientDefinedCountries);

		maintainClientDefinedCountries.deleteCountry(operator, newCountry);

	}



	public void OR_T12TrafficVolumeColumsWithoutDiscountAgreement(String jsonObjName) {

		String OB_DurInSec="", OB_ChargeDur="", IB_DurInSec="", IB_ChargeDur="", OB_TrafVol_FinRep="", IB_TrafVol_FinRep="";

		CustomReporter.createNode("Moving to View IOT Traffic Data for getting 'Duration in Seconds' column aggregate value");

		//Moving to View IOT Traffic Data for getting "Duration in Seconds" column aggregate value
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.QueryIOTSummary,
				ihp.link_IOTRONSystemAdministration, ihp.link_IOTRONSystemAdministration_QueryIOTSummary);

		ViewIOTTrafficData viewIOTTrafficData=new ViewIOTTrafficData();

		String durInSec_CharDur=viewIOTTrafficData.getDurInSec_CharDur_AggrValues(null,Module_OverviewReport.jsonFilePath,jsonObjName,"QueryIOTSummary-OB");
		OB_DurInSec=durInSec_CharDur.split(",")[0];
		OB_ChargeDur=durInSec_CharDur.split(",")[1];

		durInSec_CharDur=viewIOTTrafficData.getDurInSec_CharDur_AggrValues(null,Module_OverviewReport.jsonFilePath,jsonObjName,"QueryIOTSummary-IB");
		IB_DurInSec=durInSec_CharDur.split(",")[0];
		IB_ChargeDur=durInSec_CharDur.split(",")[1];

		CustomReporter.createNode("Moving to Financial Report for getting 'Traffic Volume' column aggregate value");

		//Moving to Financial Report for getting "Traffic Volume" column aggregate value
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.FinancialReportForecastedTAPDiscountedCharges,
				ihp.link_Forecasting, ihp.link_Forecasting_FinancialReportForecastedTAPDiscountedCharges);

		FinancialReport_ForecastedTAPDiscountedCharges finRep= new FinancialReport_ForecastedTAPDiscountedCharges();
		OB_TrafVol_FinRep=finRep.get_TrafVol_AggrValue(Module_OverviewReport.jsonFilePath,jsonObjName,"FinForecast-OB");
		IB_TrafVol_FinRep=finRep.get_TrafVol_AggrValue(Module_OverviewReport.jsonFilePath,jsonObjName,"FinForecast-IB");

		CustomReporter.createNode("Moving to Overview Report for verifying 'Traffic Volume' column aggregate value, and 'Volume set used in calculation' column value");

		//Moving to Overview Report for verifying "Traffic Volume" column aggregate value, and "Volume set used in calculation" column value
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting, ihp.link_Reporting_OverviewReport);

		String trafVol_Act_Bill=verify_TrafVolumeColumn_Values_With_FinForecast_IOTTraffReports(Module_OverviewReport.jsonFilePath, jsonObjName,"OverviewRep-OB");

		Util.comparator(OB_DurInSec, trafVol_Act_Bill.split(",")[1], "OUTBOUND - Duration in Sec{View IOT Traffic Data Rep} with Traff Vol Act{Overview Rep}");
		Util.comparator(OB_ChargeDur, trafVol_Act_Bill.split(",")[2], "OUTBOUND - Charged Dur{View IOT Traffic Data Rep} with Traff Vol Billed{Overview Rep}");
		Util.comparator(OB_TrafVol_FinRep, trafVol_Act_Bill.split(",")[0], "OUTBOUND - Traff Vol{Fin Rep} with Traff Vol{Overview Rep}");


		trafVol_Act_Bill=verify_TrafVolumeColumn_Values_With_FinForecast_IOTTraffReports(Module_OverviewReport.jsonFilePath, jsonObjName,"OverviewRep-IB");

		Util.comparator(IB_DurInSec, trafVol_Act_Bill.split(",")[1], "INBOUND - Duration in Sec{View IOT Traffic Data Rep} with Traff Vol Act{Overview Rep}");
		Util.comparator(IB_ChargeDur, trafVol_Act_Bill.split(",")[2], "INBOUND - Charged Dur{View IOT Traffic Data Rep} with Traff Vol Billed{Overview Rep}");
		Util.comparator(IB_TrafVol_FinRep, trafVol_Act_Bill.split(",")[0], "INBOUND - Traff Vol{Fin Rep} with Traff Vol{Overview Rep}");
	}



	private String verify_TrafVolumeColumn_Values_With_FinForecast_IOTTraffReports(String jsonFilePath,String... jsonObjName) {

		CustomReporter.createNode("Verifying Traffic Volume Aggr Value for "+jsonObjName[jsonObjName.length-1]);

		String OB_TrafVol="",OB_TrafVolAct="",OB_TrafVolBil="";
		JSONManager json = new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName);
		if (performSearch(json)) {


			String trafVol="trafVol",trafVolAct="trafVolAct",trafVolBill="trafVolBill";
			comm.applyComputation(trafVol, "", "Traffic Volume");
			comm.applyComputation(trafVolAct, "", "Traffic Volume Actual");
			comm.applyComputation(trafVolBill, "", "Traffic Volume Billed");
			WebTable tab= new WebTable(comm.table_ResultTab);
			int trafVol_col= tab.initHeaderColumnList(headerRow).getColumnNumber(trafVol);
			int trafVolActCol=tab.getColumnNumber(trafVolAct);
			int trafVolBilCol=tab.getColumnNumber(trafVolBill);
			int volSetCol=tab.getColumnNumber("Volume set used in calculation");
			int agrCol=tab.getColumnNumber("Agreement Reference");

			//Agreement is not configured in "Volume set used in calculation" column 'Actual' should be displayed and also in "Traffic Volume" 
			//column - value that is set in "Volume Actual" column should be visible "Traffic Volume" =" Volume Actual"
			String agrRef="";

			//for OUTBOUND "Volume set used in calculation" column
			int lastRow=tab.getRowCount();
			for (int row = headerRow+1; row < lastRow; row++) {
				agrRef=tab.getCellText(row, agrCol);
				if("-".equals(agrRef)){
					String temp=tab.getCellText(row, volSetCol);
					if(temp.contains("Actual")){
						CustomReporter.report(STATUS.PASS, jsonObjName[jsonObjName.length-1]+"- value '"+temp+"' is displayed in 'Volume set used in calculation' column");
					}else{
						CustomReporter.report(STATUS.FAIL, jsonObjName[jsonObjName.length-1]+"- value '"+temp+"' is NOT displayed in 'Volume set used in calculation' column");
					}

					if(tab.getCellText(row,trafVol_col).equals(tab.getCellText(row,trafVolActCol))){
						CustomReporter.report(STATUS.PASS, jsonObjName[jsonObjName.length-1]+"- Value of 'Traffic Volume' column is same as 'Traffic Volume Actual' column");
					}else{
						CustomReporter.report(STATUS.FAIL, jsonObjName[jsonObjName.length-1]+"- Value of 'Traffic Volume' column is NOT same as 'Traffic Volume Actual' column");
					}
					break;
				}
			}

			comm.applyColumnFilter(headerRow, tab.getColumnNumber("Partner PMN"), json.getStr("partnerPMN"));
			comm.setRowsPerPage("All");

			OB_TrafVolAct=comm.getSumOfColumnValues(3, trafVolActCol);
			OB_TrafVolBil=comm.getSumOfColumnValues(3,trafVolBilCol);
			OB_TrafVol=comm.getSumOfColumnValues(3,trafVol_col);

			comm.setRowsPerPage("1000");
			comm.removeAppliedColumnFilter("Partner PMN");
		}
		return OB_TrafVol+","+OB_TrafVolAct+","+OB_TrafVolBil;
	}

	public void TrafficVolumeColumsWithDiscountAgreement_BilledActual_With_OR_WithoutSatellite(String userType,String sat, String jsonObjName) {
		String OB_ChargeDur="", IB_ChargeDur="", OB_DurInSec="", IB_DurInSec="";

		CustomReporter.createNode("BILLED");
		String agreementName="AutomationTestingAgreement"+Util.getTimeStamp_InMilliSec();

		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture,ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		CreateEditIOTDiscountAgreement_P303 createEditIOTDiscountAgreement= maintainIOTDiscountAgreementsPage.clickOnCreateIOTDiscountAgreementButton();

		JSONManager json = new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"createAgreement-Billed");
		createEditIOTDiscountAgreement.createDiscAgreement(agreementName, json.getStr("clientName"),
				json.getStrArr("partner"), json.getStr("startDate"), json.getStr("endDate"),
				json.getBool("bool_checkIncludeSatelite"));

		for (int i = 0; i <json.getJsonArrLength("DiscParam") ; i++) {
			createEditIOTDiscountAgreement.addDiscountParameters(i,json);					
		}

		createEditIOTDiscountAgreement.calculateAgreement(agreementName);

		LoginPage login = new LoginPage();
		login.logoutThenPerformLogin(userType);

		CustomReporter.createNode("Moving to View IOT Traffic Data for getting OB/IB 'Duration in Seconds' column aggregate value");

		//Moving to View IOT Traffic Data for getting "Duration in Seconds" column aggregate value
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.QueryIOTSummary,
				ihp.link_IOTRONSystemAdministration, ihp.link_IOTRONSystemAdministration_QueryIOTSummary);

		ViewIOTTrafficData viewIOTTrafficData= new ViewIOTTrafficData();
		String durInSec_CharDur=viewIOTTrafficData.getDurInSec_CharDur_AggrValues(sat,Module_OverviewReport.jsonFilePath,jsonObjName,"viewIOTTrafficData-Search-OB");
		OB_DurInSec=durInSec_CharDur.split(",")[0];
		OB_ChargeDur=durInSec_CharDur.split(",")[1];

		//Getting the Inbound "Charged Duration" and "Duration In Seconds"
		durInSec_CharDur=viewIOTTrafficData.getDurInSec_CharDur_AggrValues(sat,Module_OverviewReport.jsonFilePath,jsonObjName,"viewIOTTrafficData-Search-IB");
		IB_DurInSec=durInSec_CharDur.split(",")[0];
		IB_ChargeDur=durInSec_CharDur.split(",")[1];

		CustomReporter.createNode("Moving to Overview Report to verify TrafVol & VolSetUsedInCalc");

		//Moving to Overview Report
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting, ihp.link_Reporting_OverviewReport);

		verify_TrafVol_VolSetUsedInCalc(Module_OverviewReport.jsonFilePath, jsonObjName, "Billed", agreementName, OB_DurInSec,OB_ChargeDur,
				IB_DurInSec,IB_ChargeDur);


		CustomReporter.createNode("ACTUAL");
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture,ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		if(maintainIOTDiscountAgreementsPage.performSearch(agreementName)){
			maintainIOTDiscountAgreementsPage.clickOnEditLink(agreementName);

			json = new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"updateAgreement-Actual");

			for (int i = 0; i <json.getJsonArrLength("DiscParam") ; i++) {
				createEditIOTDiscountAgreement.updateDiscountParameters(
						i+2,							
						json.getStr("DiscParam", i, "TrafficDirection"),
						json.getStr("DiscParam", i, "ServiceType"),
						json.getStr("DiscParam", i, "EventType"),
						json.getStr("DiscParam", i, "CalculationType"),
						json.getStr("DiscParam", i, "discBasis"),
						json.getStr("DiscParam", i, "discBasisVal"),
						json.getStr("DiscParam", i, "ChargingIncrementUsedInCalculationVal"));					
			}

			createEditIOTDiscountAgreement.calculateAgreement(agreementName);

			//Moving to Overview Report
			CustomReporter.createNode("Moving to Overview Report to verify TrafVol & VolSetUsedInCalc");

			navigate.traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting, ihp.link_Reporting_OverviewReport);

			verify_TrafVol_VolSetUsedInCalc(Module_OverviewReport.jsonFilePath, jsonObjName, "Actual", agreementName, OB_DurInSec,OB_ChargeDur,
					IB_DurInSec,IB_ChargeDur);

		}

		navigate.traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture,ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		if(maintainIOTDiscountAgreementsPage.performSearch(agreementName)){
			maintainIOTDiscountAgreementsPage.clickOnEditLink(agreementName);
			createEditIOTDiscountAgreement.deleteAgreement();

		}	


	}

	private void verify_TrafVol_VolSetUsedInCalc(String jsonFilePath, String jsonObjName, String actualOrBilled, String agreementName,String OB_DurInSec,String OB_ChargeDur,
			String IB_DurInSec,String IB_ChargeDur) {
		JSONManager json = new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"ORSearch-OB-"+actualOrBilled);

		if (performSearch(json)) {

			comm.applyComputation("OB_TrafVolBil", "", "Traffic Volume Billed");
			comm.applyComputation("OB_TrafVolAct", "", "Traffic Volume Actual");

			WebTable tab= new WebTable(comm.table_ResultTab);
			tab.initHeaderColumnList(headerRow);
			int agrCol=tab.getColumnNumber("Agreement Reference");
			int trafVolBilCol=tab.getColumnNumber("OB_TrafVolBil");
			int trafVolActCol=tab.getColumnNumber("OB_TrafVolAct");
			int volSetCol=tab.getColumnNumber("Volume set used in calculation");

			//for OUTBOUND "Volume set used in calculation" column
			comm.applyColumnFilter(headerRow, agrCol, agreementName);
			int lastRow=tab.getRowCount();
			String OB_TrafVolBil=tab.getCellText(lastRow, trafVolBilCol);
			String OB_TrafVolAct=tab.getCellText(lastRow, trafVolActCol);
			String OB_VolSet=tab.getCellText(lastRow,volSetCol);
			Util.comparator_NonNumbers("OUTBOUND - Vol Set Used In Calculation",CreateEditIOTDiscountAgreement_P303.title,json.getStr("volSetUsedInCalculation"),OverviewReport_P507.title,OB_VolSet);
			Util.comparator(OB_ChargeDur, OB_TrafVolBil, "OUTBOUND - Charged Dur{View IOT Traffic Data Rep} with Traff Vol Billed{Overview Rep}");
			Util.comparator(OB_DurInSec, OB_TrafVolAct, "OUTBOUND - Dur In Sec{View IOT Traffic Data Rep} with Traff Vol Actual{Overview Rep}");

			//for INBOUND
			json = new JSONManager(Module_OverviewReport.jsonFilePath, jsonObjName,"ORSearch-IB-"+actualOrBilled);

			if (performSearch(json)) {

				lastRow=tab.getRowCount();
				String IB_TrafVolBil=tab.getCellText(lastRow, trafVolBilCol);
				String IB_VolSet=tab.getCellText(lastRow,volSetCol);
				String IB_TrafVolAct=tab.getCellText(lastRow, trafVolActCol);
				Util.comparator(IB_DurInSec, IB_TrafVolAct, "INBOUND - Dur In Sec{View IOT Traffic Data Rep} with Traff Vol Actual{Overview Rep}");
				Util.comparator_NonNumbers("INBOUND - Vol Set Used In Calculation",CreateEditIOTDiscountAgreement_P303.title,json.getStr("volSetUsedInCalculation"),OverviewReport_P507.title,IB_VolSet);
				Util.comparator(IB_ChargeDur, IB_TrafVolBil, "INBOUND - Charged Dur{View IOT Traffic Data Rep} with Traff Vol Billed{Overview Rep}");
			}
		}

	}

	public void T04_7490_AccrRep_P25_Verify_HomeCurrencyRepData_With_OvRep(String methodName) {

		T04_7490_AccrRep_P25_Verify_HomeCurrencyRepData_With_OvRep_COMMON(methodName, "customerOB","0117","0217");

		T04_7490_AccrRep_P25_Verify_HomeCurrencyRepData_With_OvRep_COMMON(methodName, "visitorIB","0117","0217");


	}

	private void T04_7490_AccrRep_P25_Verify_HomeCurrencyRepData_With_OvRep_COMMON(String methodName, String ibOb,String traffPeriod1,String traffPeriod2) {
		JSONManager json = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName);
		String agrRef=json.getStr("agreementRef");

		// FOR VOICE MO
		CustomReporter.createNode("Verifying the values for "+ibOb+" Voice MO");

		json = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName,"overviewReport",ibOb,"voiceMO");

		performSearch(json);

		comm.performSearch(agrRef, "Agreement Reference");		
		comm.performSearch(traffPeriod1, "Traffic Period");

		comm.applyAggregateFunction("Sum", "Traffic Volume");
		comm.applyAggregateFunction("Sum", "Total TAP Charges Excluding Tax");
		comm.applyAggregateFunction("Sum", "Total Post-Discounted Charges Excluding Tax");
		comm.applyAggregateFunction("Sum", "Credit Note Discount Achieved Excluding Tax");

		T04_7490_verifyHomeLocalInternationalValues_For_VoiceMO(null, null, traffPeriod1, methodName, ibOb, "Home");

		T04_7490_verifyHomeLocalInternationalValues_For_VoiceMO("Home", null, traffPeriod1, methodName, ibOb, "Local");

		T04_7490_verifyHomeLocalInternationalValues_For_VoiceMO("Local", null, traffPeriod1, methodName, ibOb, "International");

		T04_7490_verifyHomeLocalInternationalValues_For_VoiceMO("International", traffPeriod1, traffPeriod2, methodName, ibOb, "Home");

		T04_7490_verifyHomeLocalInternationalValues_For_VoiceMO("Home", traffPeriod1, traffPeriod2, methodName, ibOb, "Local");

		T04_7490_verifyHomeLocalInternationalValues_For_VoiceMO("Local", traffPeriod1, traffPeriod2, methodName, ibOb, "International");

		comm.removeAppliedColumnFilter("Call Destination");

		comm.removeAppliedColumnFilter("Traffic Period");

		// FOR VOICE MT
		T04_7490_verifyValues_For_VoiceMT_SMS_DATA(methodName,ibOb, "voiceMT", "MTC", null, traffPeriod1);
		T04_7490_verifyValues_For_VoiceMT_SMS_DATA(methodName,ibOb, "voiceMT", "MTC", traffPeriod1, traffPeriod2);

		// FOR SMS MO
		T04_7490_verifyValues_For_VoiceMT_SMS_DATA(methodName,ibOb, "sms", "SMS", null, traffPeriod1);
		T04_7490_verifyValues_For_VoiceMT_SMS_DATA(methodName,ibOb, "sms", "SMS", traffPeriod1, traffPeriod2);

		// FOR DATA MB
		T04_7490_verifyValues_For_VoiceMT_SMS_DATA(methodName,ibOb, "data", "Data", null, traffPeriod1);
		T04_7490_verifyValues_For_VoiceMT_SMS_DATA(methodName,ibOb, "data", "Data", traffPeriod1, traffPeriod2);
	}

	public void T05_7490_AccrRep_P25_Verify_CurrentMonthDataRep_With_OvRep(String testName) {

		T05_7490_AccrRep_P25_Verify_CurrentMonthDataRep_With_OvRep_COMMON(testName, "customerOB", "0217");
		T05_7490_AccrRep_P25_Verify_CurrentMonthDataRep_With_OvRep_COMMON(testName, "visitorIB", "0217");

	}

	private void T05_7490_AccrRep_P25_Verify_CurrentMonthDataRep_With_OvRep_COMMON(String methodName,String ibOb,String trfPer) {

		JSONManager json = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName);
		String agrRef=json.getStr("agreementRef");

		// FOR VOICE MO
		CustomReporter.createNode("Verifying the values for "+ibOb+" Voice MO");

		json = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName,"overviewReport",ibOb,"voiceMO");

		performSearch(json);

		comm.performSearch(agrRef, "Agreement Reference");		
		comm.performSearch(trfPer, "Traffic Period");

		comm.applyAggregateFunction("Sum", "Traffic Volume");
		comm.applyAggregateFunction("Sum", "Total TAP Charges Excluding Tax");
		comm.applyAggregateFunction("Sum", "Total Post-Discounted Charges Excluding Tax");
		comm.applyAggregateFunction("Sum", "Credit Note Discount Achieved Excluding Tax");

		T04_7490_verifyHomeLocalInternationalValues_For_VoiceMO(null, null, trfPer, methodName, ibOb, "Home");

		T04_7490_verifyHomeLocalInternationalValues_For_VoiceMO("Home", null, trfPer, methodName, ibOb, "Local");

		T04_7490_verifyHomeLocalInternationalValues_For_VoiceMO("Local", null, trfPer, methodName, ibOb, "International");

		comm.removeAppliedColumnFilter("Call Destination");

		comm.removeAppliedColumnFilter("Traffic Period");

		// FOR VOICE MT
		T04_7490_verifyValues_For_VoiceMT_SMS_DATA(methodName,ibOb, "voiceMT", "MTC", null, trfPer);

		// FOR SMS MO
		T04_7490_verifyValues_For_VoiceMT_SMS_DATA(methodName,ibOb, "sms", "SMS", null, trfPer);

		// FOR DATA MB
		T04_7490_verifyValues_For_VoiceMT_SMS_DATA(methodName,ibOb, "data", "Data", null, trfPer);

	}

	public void T02_7490_AccrRep_P25_Verify_AgreementCurrencyRepData_With_OvRep(String testName) {

		T02_7490_AccrRep_P25_Verify_AgreementCurrencyRepData_With_OvRep_COMMON(testName, "customerOB", "0117");
		T02_7490_AccrRep_P25_Verify_AgreementCurrencyRepData_With_OvRep_COMMON(testName, "visitorIB", "0117");

	}

	private void T02_7490_AccrRep_P25_Verify_AgreementCurrencyRepData_With_OvRep_COMMON(String methodName,String ibOb,String trfPer) {
		JSONManager json = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName);
		String agrRef=json.getStr("agreementRef");

		json = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName,"overviewReport",ibOb,"voiceMO");
		performSearch(json);

		comm.performSearch(agrRef, "Agreement Reference");

		comm.applyAggregateFunction("Sum", "Traffic Volume");
		comm.applyAggregateFunction("Sum", "Total TAP Charges Excluding Tax");
		comm.applyAggregateFunction("Sum", "Total Post-Discounted Charges Excluding Tax");
		comm.applyAggregateFunction("Sum", "Credit Note Discount Achieved Excluding Tax");

		T02_7490_verifyHomeLocalInternationalValues_For_VoiceMO(null, methodName,trfPer,ibOb, "Home");

		T02_7490_verifyHomeLocalInternationalValues_For_VoiceMO("Home", methodName,trfPer,ibOb, "Local");

		T02_7490_verifyHomeLocalInternationalValues_For_VoiceMO("Local", methodName,trfPer,ibOb, "International");

		comm.removeAppliedColumnFilter("Call Destination");

		T02_7490_verifyValues_For_VoiceMT_SMS_DATA(methodName, trfPer,ibOb, "voiceMT", "MTC");

		T02_7490_verifyValues_For_VoiceMT_SMS_DATA(methodName, trfPer,ibOb, "sms", "SMS");

		T02_7490_verifyValues_For_VoiceMT_SMS_DATA(methodName, trfPer,ibOb, "data", "Data");
	}

	private void T02_7490_verifyValues_For_VoiceMT_SMS_DATA(String methodName,String trfPer,String obIb,String voiceSmsData,String mtcSmsData) {
		CustomReporter.createNode("FOR "+obIb+" >> "+voiceSmsData);

		JSONManager json = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName,"overviewReport",obIb,voiceSmsData);
		performSearch(json);

		WebTable tab= new WebTable(comm.table_ResultTab);
		tab.initHeaderColumnList(2);
		int trafVolCol=tab.getColumnNumber("Traffic Volume");
		int tapChargCol=tab.getColumnNumber("Total TAP Charges Excluding Tax");
		int discChargCol=tab.getColumnNumber("Total Post-Discounted Charges Excluding Tax");
		int discAchCol=tab.getColumnNumber("Credit Note Discount Achieved Excluding Tax");

		int lastRow=tab.getRowCount();
		json = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName, "runTimeData",trfPer,obIb,voiceSmsData);

		String trafVol_JSON=json.getStr(mtcSmsData+" Traffic Volume");
		String PAGE=tab.getCellText(lastRow, trafVolCol);
		Util.comparator_PageValues("Traffic Volume", AccrualReport_P25.title, trafVol_JSON, title, PAGE);

		String tapCharg_JSON=json.getStr(mtcSmsData+" TAP Charges Excluding Tax");
		PAGE=tab.getCellText(lastRow, tapChargCol);
		Util.comparator_PageValues("TAP Charge", AccrualReport_P25.title, tapCharg_JSON, title, PAGE);

		String discCharg_JSON=json.getStr(mtcSmsData+" Discounted Charges Excluding Tax");
		PAGE=tab.getCellText(lastRow, discChargCol);
		Util.comparator_PageValues("Disc Charge", AccrualReport_P25.title, discCharg_JSON, title, PAGE);

		String discAch_JSON=json.getStr(mtcSmsData+" Discount");
		PAGE=tab.getCellText(lastRow, discAchCol);
		Util.comparator_PageValues("Disc Achieved", AccrualReport_P25.title, discAch_JSON, title, PAGE);

	}

	private void T02_7490_verifyHomeLocalInternationalValues_For_VoiceMO(String oldFilterVal,String methodName,String trfPer,String ibOb,String homeLocInt) {

		CustomReporter.createNode("FOR "+ibOb+" >> VOICE MO >> "+homeLocInt);

		//FOR OUTBOUND >> VOICE MO >> HOME / LOCAL / INTERNATIONAL
		WebTable tab= new WebTable(comm.table_ResultTab);
		tab.initHeaderColumnList(2);
		int trafVolCol=tab.getColumnNumber("Traffic Volume");
		int tapChargCol=tab.getColumnNumber("Total TAP Charges Excluding Tax");
		int discChargCol=tab.getColumnNumber("Total Post-Discounted Charges Excluding Tax");
		int discAchCol=tab.getColumnNumber("Credit Note Discount Achieved Excluding Tax");
		if (oldFilterVal==null) {
			comm.performSearch(homeLocInt, "Call Destination");
		}else{
			comm.redefineAppliedFilterValue("Call Destination contains '"+oldFilterVal+"'", homeLocInt);
		}

		JSONManager json = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName, "runTimeData",trfPer,ibOb,"voiceMO",homeLocInt);
		int lastRow=tab.getRowCount();

		String trafVol_JSON=json.getStr("MOC "+homeLocInt+" Traffic Volume");
		String PAGE=tab.getCellText(lastRow, trafVolCol);
		Util.comparator_PageValues("Traffic Volume", AccrualReport_P25.title, trafVol_JSON, title, PAGE);

		String tapCharg_JSON=json.getStr("MOC "+homeLocInt+" TAP Charges Excluding Tax");
		PAGE=tab.getCellText(lastRow, tapChargCol);
		Util.comparator_PageValues("TAP Charge", AccrualReport_P25.title, tapCharg_JSON, title, PAGE);

		String discCharg_JSON=json.getStr("MOC "+homeLocInt+" Discounted Charges Excluding Tax");
		PAGE=tab.getCellText(lastRow, discChargCol);
		Util.comparator_PageValues("Disc Charge", AccrualReport_P25.title, discCharg_JSON, title, PAGE);

		String discAch_JSON=json.getStr("MOC "+homeLocInt+" Discount");
		PAGE=tab.getCellText(lastRow, discAchCol);
		Util.comparator_PageValues("Disc Achieved", AccrualReport_P25.title, discAch_JSON, title, PAGE);


	}

	private void T04_7490_verifyHomeLocalInternationalValues_For_VoiceMO(String oldFilterVal,String forMonth_Old,String forMonth_Current,String methodName,String ibOb,String homeLocInt) {
		CustomReporter.createNode(" >> "+forMonth_Current+" month >> "+homeLocInt);

		//FOR OUTBOUND >> VOICE MO >> HOME / LOCAL / INTERNATIONAL
		WebTable tab= new WebTable(comm.table_ResultTab);
		tab.initHeaderColumnList(2);
		int trafVolCol=tab.getColumnNumber("Traffic Volume");
		int tapChargCol=tab.getColumnNumber("Total TAP Charges Excluding Tax");
		int discChargCol=tab.getColumnNumber("Total Post-Discounted Charges Excluding Tax");
		int discAchCol=tab.getColumnNumber("Credit Note Discount Achieved Excluding Tax");

		if (forMonth_Old==null) {
			comm.performSearch(forMonth_Current, "Traffic Period");
		}else{
			comm.removeAppliedColumnFilter("Traffic Period contains");
		}


		if (oldFilterVal==null) {
			comm.performSearch(homeLocInt, "Call Destination");
		}else{
			comm.redefineAppliedFilterValue("Call Destination contains '"+oldFilterVal+"'", homeLocInt);
		}

		JSONManager json_CurrentMonth = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName, "runTimeData", forMonth_Current, ibOb);
		String fxRate=json_CurrentMonth.getStr("FX to Home Currency");

		json_CurrentMonth = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName, "runTimeData", forMonth_Current, ibOb, "voiceMO", homeLocInt);
		JSONManager json_OldMonth=null;
		if (forMonth_Old!=null) {
			json_OldMonth = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName, "runTimeData", forMonth_Old, ibOb, "voiceMO", homeLocInt);
		}

		int lastRow=tab.getRowCount();


		String trafVol_JSON=json_CurrentMonth.getStr("MOC "+homeLocInt+" Traffic Volume");
		String PAGE=tab.getCellText(lastRow, trafVolCol);
		Util.comparator_PageValues("Traffic Volume", AccrualReport_P25.title, trafVol_JSON, title, PAGE);

		if (forMonth_Old!=null) {
			comm.performSearch(forMonth_Current, "Traffic Period");
		}

		lastRow=tab.getRowCount();

		/**
		 * Converting the Overview Report > TAP Charge, by multiplying it with Accrual Report > fx rate
		 * Also to get the cumulative value > adding old month TAP Charge to the calculated value  
		 * */
		String tapCharg_JSON=json_CurrentMonth.getStr("MOC "+homeLocInt+" TAP Charges Excluding Tax");
		PAGE=tab.getCellText(lastRow, tapChargCol);
		PAGE=Util.BD(PAGE).multiply(Util.BD(fxRate)).toPlainString();
		if (forMonth_Old!=null) {
			String tapCharg_JSON_OldMonth=json_OldMonth.getStr("MOC "+homeLocInt+" TAP Charges Excluding Tax");
			PAGE=Util.BD(PAGE).add(Util.BD(tapCharg_JSON_OldMonth)).toPlainString();
		}
		Util.comparator_PageValues("TAP Charge", AccrualReport_P25.title, tapCharg_JSON, title, PAGE);

		/**
		 * Converting the Overview Report > Discounted Charges, by multiplying it with Accrual Report > fx rate
		 * Also to get the cumulative value > adding old month Discounted Charge to the calculated value 
		 * */
		String discCharg_JSON=json_CurrentMonth.getStr("MOC "+homeLocInt+" Discounted Charges Excluding Tax");
		PAGE=tab.getCellText(lastRow, discChargCol);
		PAGE=Util.BD(PAGE).multiply(Util.BD(fxRate)).toPlainString();
		if (forMonth_Old!=null) {
			String discCharg_JSON_OldMonth=json_OldMonth.getStr("MOC "+homeLocInt+" Discounted Charges Excluding Tax");
			PAGE=Util.BD(PAGE).add(Util.BD(discCharg_JSON_OldMonth)).toPlainString();
		}
		Util.comparator_PageValues("Disc Charge", AccrualReport_P25.title, discCharg_JSON, title, PAGE);

		/**
		 * Converting the Overview Report > Discounted Achieved, by multiplying it with Accrual Report > fx rate
		 * Also to get the cumulative value > adding old month Discounted Achieved to the calculated value 
		 * */
		String discAch_JSON=json_CurrentMonth.getStr("MOC "+homeLocInt+" Discount");
		PAGE=tab.getCellText(lastRow, discAchCol);
		PAGE=Util.BD(PAGE).multiply(Util.BD(fxRate)).toPlainString();
		if (forMonth_Old!=null) {
			String discAch_JSON_OldMonth=json_OldMonth.getStr("MOC "+homeLocInt+" Discount");
			PAGE=Util.BD(PAGE).add(Util.BD(discAch_JSON_OldMonth)).toPlainString();
		}
		Util.comparator_PageValues("Disc Achieved", AccrualReport_P25.title, discAch_JSON, title, PAGE);
	}

	private void T04_7490_verifyValues_For_VoiceMT_SMS_DATA(String methodName,String obIb,String voiceSmsData,String mtcSmsData,String forMonth_Old,String forMonth_Current) {

		CustomReporter.createNode("Verifying the values for "+obIb+" "+voiceSmsData+" and "+forMonth_Current+" month");


		JSONManager json_CurrentMonth = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName,"overviewReport",obIb,voiceSmsData);
		performSearch(json_CurrentMonth);

		WebTable tab= new WebTable(comm.table_ResultTab);
		tab.initHeaderColumnList(2);
		int trafVolCol=tab.getColumnNumber("Traffic Volume");
		int tapChargCol=tab.getColumnNumber("Total TAP Charges Excluding Tax");
		int discChargCol=tab.getColumnNumber("Total Post-Discounted Charges Excluding Tax");
		int discAchCol=tab.getColumnNumber("Credit Note Discount Achieved Excluding Tax");

		json_CurrentMonth = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName, "runTimeData", forMonth_Current, obIb);
		String fxRate=json_CurrentMonth.getStr("FX to Home Currency");

		if (forMonth_Old==null) {
			comm.performSearch(forMonth_Current, "Traffic Period");
		}

		int lastRow=tab.getRowCount();
		json_CurrentMonth = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName, "runTimeData", forMonth_Current, obIb,voiceSmsData);
		String trafVol_JSON=json_CurrentMonth.getStr(mtcSmsData+" Traffic Volume");
		String PAGE=tab.getCellText(lastRow, trafVolCol);
		Util.comparator_PageValues("Traffic Volume", AccrualReport_P25.title, trafVol_JSON, title, PAGE);

		JSONManager json_OldMonth=null;
		if (forMonth_Old!=null) {
			json_OldMonth = new JSONManager(HKT_AccrualReport_7490.jsonFilePath, methodName, "runTimeData", forMonth_Old, obIb,voiceSmsData);
			comm.performSearch(forMonth_Current, "Traffic Period");
		}
		lastRow=tab.getRowCount();

		String tapCharg_JSON=json_CurrentMonth.getStr(mtcSmsData+" TAP Charges Excluding Tax");
		PAGE=tab.getCellText(lastRow, tapChargCol);
		PAGE=Util.BD(PAGE).multiply(Util.BD(fxRate)).toPlainString();
		if (forMonth_Old!=null) {
			String tapCharg_JSON_OldMonth=json_OldMonth.getStr(mtcSmsData+" TAP Charges Excluding Tax");
			PAGE=Util.BD(PAGE).add(Util.BD(tapCharg_JSON_OldMonth)).toPlainString();
		}
		Util.comparator_PageValues("TAP Charge", AccrualReport_P25.title, tapCharg_JSON, title, PAGE);

		String discCharg_JSON=json_CurrentMonth.getStr(mtcSmsData+" Discounted Charges Excluding Tax");
		PAGE=tab.getCellText(lastRow, discChargCol);
		PAGE=Util.BD(PAGE).multiply(Util.BD(fxRate)).toPlainString();
		if (forMonth_Old!=null) {
			String tapCharg_JSON_OldMonth=json_OldMonth.getStr(mtcSmsData+" Discounted Charges Excluding Tax");
			PAGE=Util.BD(PAGE).add(Util.BD(tapCharg_JSON_OldMonth)).toPlainString();
		}
		Util.comparator_PageValues("Disc Charge", AccrualReport_P25.title, discCharg_JSON, title, PAGE);

		String discAch_JSON=json_CurrentMonth.getStr(mtcSmsData+" Discount");
		PAGE=tab.getCellText(lastRow, discAchCol);
		PAGE=Util.BD(PAGE).multiply(Util.BD(fxRate)).toPlainString();
		if (forMonth_Old!=null) {
			String tapCharg_JSON_OldMonth=json_OldMonth.getStr(mtcSmsData+" Discount");
			PAGE=Util.BD(PAGE).add(Util.BD(tapCharg_JSON_OldMonth)).toPlainString();
		}
		Util.comparator_PageValues("Disc Achieved", AccrualReport_P25.title, discAch_JSON, title, PAGE);

		comm.removeAppliedColumnFilter("Traffic Period contains");
	}



	/**
	 * This will reset the page by clicking Reset Page button
	 * @author shailendra.rajawat 08-Apr-2019
	 * */
	public void resetPage() {
		com.click(button_ResetPage, "Reset Page button");
	}


	/**
	 * This will click on Right side displayed Download CSV button
	 * @author shailendra.rajawat 14-May-2019
	 * */
	public void clickDownloadCsvButton(){
		com.click(button_DownloadCSV, "button_Download CSV");
	}
	
	/**
	 * This will click on Right side displayed Download Xls button, 
	 * xlsx file will be downloaded
	 * @author shailendra.rajawat 16-May-2019
	 * */
	public void clickDownloadXlsButton(){
		com.click(button_DownloadXls, "button_Download Xls");
	}

}