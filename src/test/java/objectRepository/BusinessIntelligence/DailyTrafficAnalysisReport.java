package objectRepository.BusinessIntelligence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.jsonUtil.JSONManager;
import common.seleniumExceptionHandling.CustomExceptionHandler;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.Forecasting.FinancialReport_ForecastedTAPDiscountedCharges;
import objectRepository.IOTRONSystemAdministration.ViewIOTTrafficData;
import objectRepository.common.ApexCommon;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import tests.MainRegression.Module_KPN_Op4;

public class DailyTrafficAnalysisReport {

	private SeleniumMethods com;
	public static String title = "Daily Traffic Analysis Report";
	private ApexCommon comm;
	private WebTable tabHead;
	private WebTable tabData;


	public DailyTrafficAnalysisReport() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		tabHead = new WebTable(comm.table_ResultTabHeader);
		tabData=new WebTable(comm.table_ResultTabData);
	}

	@FindBy(xpath = "//h2[contains(.,'Home Operator')]")
	private WebElement data_Section_HomeOperator;

	@FindBy(xpath = "//h2[contains(.,'Select Ranges')]")
	private WebElement data_Section_SelectRanges;

	@FindBy(xpath = "//h2[contains(.,'Analysis Charts')]")
	private WebElement data_Section_Charts;

	@FindBy(xpath = "//h2[contains(.,'Analysis Report')]")
	private WebElement data_Section_Report;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Analysis Charts')]//button[@aria-expanded='false']")
	private WebElement icon_Plus_ChartsSection;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Analysis Charts')]//button[@aria-expanded='true']")
	private WebElement icon_Minus_ChartsSection;

	@FindBy(xpath = "//h2[contains(.,'Voice Volumes')]")
	private WebElement data_Section_VoiceVolumes;

	@FindBy(xpath = "//h2[contains(.,'Voice Charges')]")
	private WebElement data_Section_VoiceCharges;

	@FindBy(xpath = "//h2[contains(.,'SMS Volumes')]")
	private WebElement data_Section_SmsVolumes;

	@FindBy(xpath = "//h2[contains(.,'SMS Charges')]")
	private WebElement data_Section_SmsCharges;

	@FindBy(xpath = "//h2[contains(.,'Data Volumes')]")
	private WebElement data_Section_DataVolumes;

	@FindBy(xpath = "//h2[contains(.,'Data Charges')]")
	private WebElement data_Section_DataCharges;

	@FindBy(id = "P682_HOME_MASTER_ENTITY_ID_LEFT")
	private WebElement select_HomeOperator_Left;

	@FindBy(id = "P682_HOME_MASTER_ENTITY_ID_RIGHT")
	private WebElement select_HomeOperator_Right;

	@FindBy(id = "P682_SETTLEMENT_DATE_FROM")
	private WebElement text_SettlementDateFrom;

	@FindBy(id = "P682_SETTLEMENT_DATE_TO")
	private WebElement text_SettlementDateTo;

	@FindBy(id = "P682_PROCESSING_DATE_FROM")
	private WebElement text_ProcessingDateFrom;

	@FindBy(id = "P682_PROCESSING_DATE_TO")
	private WebElement text_ProcessingDateTo;

	@FindBy(id = "P682_CALL_DATE_FROM")
	private WebElement text_CallDateFrom;

	@FindBy(id = "P682_CALL_DATE_TO")
	private WebElement text_CallDateTo;

	@FindBy(xpath = "//button[.='Refresh']")
	private WebElement button_Refresh;

	@FindBy(xpath = "(//svg[contains(.,'Waiting for data')])[1]")
	private WebElement data_WaitingForData;

	@FindBy(id = "P682_TAX_0")
	private WebElement radio_Tax_Net;

	@FindBy(id = "P682_TAX_1")
	private WebElement radio_Tax_Gross;

	@FindBy(id = "P682_CHARGING_INCREMENT_0")
	private WebElement radio_ChargingIncrement_Actual;

	@FindBy(id = "P682_CHARGING_INCREMENT_1")
	private WebElement radio_ChargingIncrement_Billed;

	private List<String> columnList = new ArrayList<String>(Arrays.asList("Settlement Date", "Processing date",
			"Call Date Ascending", "Partner Country", "Roaming Partner", "Traffic Direction", "Service Type",
			"Event Type", "Number Of Messages", "Actual Minutes", "Billed Minutes", "Actual MB", "Billed MB",
			"TAP Charge Net", "TAP Charge Gross"));

	private List<String> sortingOrder = new ArrayList<>(
			Arrays.asList("Call Date:Ascending", "Processing Date:Ascending", "Settlement Date:Ascending",
					"Traffic Direction:Ascending", "Service Type:Descending", "Event Type:Ascending"));

	public void verifyUIContent() {
		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(select_HomeOperator_Left, "Home Operator Left shuttle");
		com.isElementPresent(select_HomeOperator_Right, "Home Operator Right shuttle");

		SimpleDateFormat form = new SimpleDateFormat("dd-MMM-yy");
		if (com.isElementPresent(text_ProcessingDateFrom, "Processing Date From field")) {
			Calendar cal = Calendar.getInstance();
			String pageval = com.getAttribute(text_ProcessingDateFrom, "value");
			cal.set(Calendar.DAY_OF_MONTH, 1);
			String tempDate = form.format(cal.getTime());
			Util.comparator_PageValues(tempDate, pageval, "Processing Date From");
		}
		if (com.isElementPresent(text_ProcessingDateTo, "Processing Date To field")) {
			Calendar cal = Calendar.getInstance();
			String pageval = com.getAttribute(text_ProcessingDateTo, "value");
			String tempDate = form.format(cal.getTime());
			Util.comparator_PageValues(tempDate, pageval, "Processing Date To");
		}
		if (com.isElementPresent(text_CallDateFrom, "Call Date From field")) {
			Calendar cal = Calendar.getInstance();
			String pageval = com.getAttribute(text_CallDateFrom, "value");
			cal.set(Calendar.DAY_OF_MONTH, 1);
			String tempDate = form.format(cal.getTime());
			Util.comparator_PageValues(tempDate, pageval, "Call Date From");
		}
		if (com.isElementPresent(text_CallDateTo, "Call Date To field")) {
			Calendar cal = Calendar.getInstance();
			String pageval = com.getAttribute(text_CallDateTo, "value");
			String tempDate = form.format(cal.getTime());
			Util.comparator_PageValues(tempDate, pageval, "Call Date To");
		}

		if (com.isElementPresent(text_SettlementDateFrom, "Settlement Date From field")) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			String pageval = com.getAttribute(text_SettlementDateFrom, "value");
			String tempDate = form.format(cal.getTime());
			Util.comparator_PageValues(tempDate, pageval, "Settlement Date From");
		}
		if (com.isElementPresent(text_SettlementDateTo, "Settlement Date To field")) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			String pageval = com.getAttribute(text_SettlementDateTo, "value");
			String tempDate = form.format(cal.getTime());
			Util.comparator_PageValues(tempDate, pageval, "Settlement Date To");
		}

		if (com.isElementPresent(button_Refresh, "Refresh button")) {
			com.click(button_Refresh);
			CustomReporter.report(STATUS.INFO, "Sections verification");
			com.isElementPresent(data_Section_HomeOperator, "Home Operator Section");
			com.isElementPresent(data_Section_SelectRanges, "Select Ranges Section");
			com.isElementPresent(data_Section_Charts, "Charts Section");
			com.isElementPresent(data_Section_Report, "Report Section");
			if (com.isElementPresent(icon_Minus_ChartsSection)) {
				CustomReporter.report(STATUS.PASS, "By default Charts Section is displayed as maximized");
			} else {
				CustomReporter.report(STATUS.FAIL, "By default Charts Section is NOT displayed as maximized");
			}

		}

	}

	public void verifyReportRegion(String methodName) {
		try {
			JSONManager json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName);
			String homeOperator=json.getStr("HomeOp");

			Calendar cal=Calendar.getInstance();
			SimpleDateFormat form= new SimpleDateFormat("dd-MMM-yy");
			String settlementDateTo=com.getAttribute(text_SettlementDateTo, "value");

			cal.setTime(form.parse(settlementDateTo));
			cal.add(Calendar.YEAR,-1);
			String settlementDateFrom=form.format(cal.getTime());

			String processingDateTo=com.getAttribute(text_ProcessingDateTo, "value");

			cal.setTime(form.parse(processingDateTo));
			cal.add(Calendar.YEAR,-1);
			String processingDateFrom=form.format(cal.getTime());

			String callDateTo=com.getAttribute(text_CallDateTo, "value");

			cal.setTime(form.parse(callDateTo));
			cal.add(Calendar.YEAR,-1);
			String callDateFrom=form.format(cal.getTime());

			if (performSearch(homeOperator, settlementDateFrom + "," + settlementDateTo,
					processingDateFrom + "," + processingDateTo, callDateFrom + "," + callDateTo)) {

				comm.verifyColumnHeaders(comm.table_ResultTabHeader, columnList, 1);

				int rowCount=tabData.getRowCount();
				List<String> numericColumnList = new ArrayList<String>(Arrays.asList("Actual Minutes", "Billed Minutes", "Actual MB", "Billed MB",
						"TAP Charge Net", "TAP Charge Gross"));
				tabHead.initHeaderColumnList(1);
				numericColumnList.forEach((temp) -> {
					int colNum = tabHead.getColumnNumber(temp);
					for (int row = 2; row <= rowCount; row++) {
						String cellText=tabData.getCellText(row, colNum);
						if(!cellText.equals("-")){
							int roundedPlace=cellText.length()-cellText.indexOf(".")-1;
							if(roundedPlace!=2){
								CustomReporter.report(STATUS.FAIL, "For column= '"+temp+"' in rowXcol= '"+row+"X"+colNum+"' the cell text='"+cellText+"' does not have numeric value rounded to 2 decimal places");
							}
							break;
						}
					}

				});
				CustomReporter.report(STATUS.INFO, "Verified all the columns : " + numericColumnList +" should have numeric value rounded to 2 decimal places");


				List<String> pageSortingOrder = comm.getSortingOrder_ColonSeparated();
				Util.comparator_List(sortingOrder, pageSortingOrder, "Sorting Order");


			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}



	private void verify_TrafficVolumeData_With_PassedPage_COMMON(JSONManager json,String PAGETITLE,String cB_VoiceMo_TrafVol,
			String cB_SmsMo_TrafVol, String cB_DataMb_TrafVol, String iB_VoiceMo_TrafVol, String iB_SmsMo_TrafVol,
			String iB_DataMb_TrafVol){

		CustomReporter.createNode("For OB Voice MO - Traf Vol");
		
		if (performSearch(json.getStr("HomeOp"), json.getStr("repStartDate") + ",null",
				json.getStr("repStartDate") + ",null",
				json.getStr("repStartDate") + "," + json.getStr("repEndDate"))) {
			tabHead.initHeaderColumnList(1);
			int actualMinCol = tabHead.getColumnNumber("Actual Minutes");
			int actualMbCol = tabHead.getColumnNumber("Actual MB");
			int numberOfMessagesCol = tabHead.getColumnNumber("Number of Messages");

			/**COMMON LOGIC Based on page*/
			if (PAGETITLE.equals(DailyTrafficVolumesByCountryAndPartner.title)) {
				comm.performSearch(json.getStr("commaSeparatedPartCntry"), "Partner Country");
			}else if (PAGETITLE.equals(DailyTrafficVolumesByPartner.title)) {
				comm.performSearch(json.getStr("RoamPart"), "Roaming Partner");
			}			

			comm.performSearch("Customer/Outbound", "Traffic Direction");
			comm.performSearch("Voice", "Service Type");
			comm.performSearch("MO", "Event Type");

			comm.setRowsPerPage("All");

			int lastRow = tabData.getRowCount();
			String CB_VoiceMoActMin = tabData.getCellText(lastRow, actualMinCol);
			Util.comparator_PageValues("OUTBOUND-Voice MO: Traffic Volume",
					PAGETITLE, cB_VoiceMo_TrafVol, DailyTrafficAnalysisReport.title,
					CB_VoiceMoActMin);

			CustomReporter.createNode("For OB SMS MO - Traf Vol");

			comm.removeAppliedColumnFilter("Voice");
			comm.performSearch("SMS", "Service Type");
			comm.applyAggregateFunction("Sum", "Number of Messages");
			lastRow = tabData.getRowCount();
			String CB_SmsMoActSum = tabData.getCellText(lastRow, numberOfMessagesCol);
			Util.comparator_PageValues("OUTBOUND-SMS MO: Traffic Volume", PAGETITLE,
					cB_SmsMo_TrafVol, DailyTrafficAnalysisReport.title, CB_SmsMoActSum);

			CustomReporter.createNode("For OB Data MB - Traf Vol");
			
			comm.removeAppliedColumnFilter("SMS");
			comm.removeAppliedColumnFilter("Event Type");
			comm.performSearch("Data", "Service Type");
			comm.applyAggregateFunction("Sum", "Actual MB");
			lastRow = tabData.getRowCount();
			String CB_DataMbActSum = tabData.getCellText(lastRow, actualMbCol);
			Util.comparator_PageValues("OUTBOUND-Data MB: Traffic Volume", PAGETITLE,
					cB_DataMb_TrafVol, DailyTrafficAnalysisReport.title, CB_DataMbActSum);

			CustomReporter.createNode("For IB Data MB - Traf Vol");
			
			comm.performSearch("Visitor/Inbound", "Traffic Direction");
			comm.removeAppliedColumnFilter("Customer/Outbound");
			lastRow = tabData.getRowCount();
			String IB_DataMbActSum = tabData.getCellText(lastRow, actualMbCol);
			Util.comparator_PageValues("INBOUND-Data MB: Traffic Volume", PAGETITLE,
					iB_DataMb_TrafVol, DailyTrafficAnalysisReport.title, IB_DataMbActSum);

			CustomReporter.createNode("For IB Voice MO - Traf Vol");
			
			comm.removeAppliedColumnFilter("Service Type");
			comm.performSearch("Voice", "Service Type");
			comm.performSearch("MO", "Event Type");
			lastRow = tabData.getRowCount();
			String IB_VoiceMoActMin = tabData.getCellText(lastRow, actualMinCol);
			Util.comparator_PageValues("INBOUND-Voice MO: Traffic Volume", PAGETITLE,
					iB_VoiceMo_TrafVol, DailyTrafficAnalysisReport.title, IB_VoiceMoActMin);

			CustomReporter.createNode("For IB SMS MO - Traf Vol");
			
			comm.removeAppliedColumnFilter("Voice");
			comm.performSearch("SMS", "Service Type");
			lastRow = tabData.getRowCount();
			String IB_SmsMoActSum = tabData.getCellText(lastRow, numberOfMessagesCol);
			Util.comparator_PageValues("INBOUND-SMS MO: Traffic Volume", PAGETITLE,
					iB_SmsMo_TrafVol, DailyTrafficAnalysisReport.title, IB_SmsMoActSum);

		}
	}


	public void verifyTrafficVolumeDataWith_DailyTrafficVolumesByPartnerPage(JSONManager json, String cB_VoiceMo_TrafVol,
			String cB_SmsMo_TrafVol, String cB_DataMb_TrafVol, String iB_VoiceMo_TrafVol, String iB_SmsMo_TrafVol,
			String iB_DataMb_TrafVol) {
		verify_TrafficVolumeData_With_PassedPage_COMMON(json, DailyTrafficVolumesByPartner.title,
				cB_VoiceMo_TrafVol, cB_SmsMo_TrafVol, cB_DataMb_TrafVol, iB_VoiceMo_TrafVol, iB_SmsMo_TrafVol,
				iB_DataMb_TrafVol);

	}

	public void verifyTrafficVolumeDataWith_DailyTrafficVolumesByCountryAndPartnerPage(JSONManager json, String cB_VoiceMo_TrafVol,
			String cB_SmsMo_TrafVol, String cB_DataMb_TrafVol, String iB_VoiceMo_TrafVol, String iB_SmsMo_TrafVol,
			String iB_DataMb_TrafVol) {
		verify_TrafficVolumeData_With_PassedPage_COMMON(json, DailyTrafficVolumesByCountryAndPartner.title,
				cB_VoiceMo_TrafVol, cB_SmsMo_TrafVol, cB_DataMb_TrafVol, iB_VoiceMo_TrafVol, iB_SmsMo_TrafVol,
				iB_DataMb_TrafVol);
	}

	private boolean performSearch(String homeOperator, String settlementDateToFrom,
			String processingDateToFrom, String callDateToFrom) {

		fillBasicSearchFields(homeOperator, settlementDateToFrom, processingDateToFrom, callDateToFrom);
		com.click(button_Refresh);
		if (com.waitForElementTobe_Visible(comm.table_ResultTabData)) {
			return true;
		}
		return false;
	}

	public void fillBasicSearchFields(String homeOperator, String settlementDateToFrom,
			String processingDateToFrom, String callDateToFrom) {

		String message = "";

		if (homeOperator != null) {
			com.deselectAllValues_Shuttle_DoubleClick(select_HomeOperator_Right);
			com.selectByPartialVisibleText_DoubleClick_FromCommaSeparated(select_HomeOperator_Left, homeOperator);
			message = message + "| homeOperator- '" + homeOperator + "'";
		}

		if (settlementDateToFrom != null  && !settlementDateToFrom.split(",")[0].equals("null")) {
			com.sendKeys(text_SettlementDateFrom, settlementDateToFrom.split(",")[0]);
			message = message + "| settlementDateFrom- '" + settlementDateToFrom.split(",")[0] + "'";
		}


		if (settlementDateToFrom != null && !settlementDateToFrom.split(",")[1].equals("null")) {
			com.sendKeys(text_SettlementDateTo, settlementDateToFrom.split(",")[1]);
			message = message + "| settlementDateTo- '" + settlementDateToFrom.split(",")[1]+ "'";
		}



		if (processingDateToFrom != null && !processingDateToFrom.split(",")[0].equals("null")) {
			com.sendKeys(text_ProcessingDateFrom, processingDateToFrom.split(",")[0]);
			message = message + "| processingDateFrom- '" + processingDateToFrom.split(",")[0] + "'";
		}

		if (processingDateToFrom != null && !processingDateToFrom.split(",")[1].equals("null")) {
			com.sendKeys(text_ProcessingDateTo, processingDateToFrom.split(",")[1]);
			message = message + "| processingDateTo- '" + processingDateToFrom.split(",")[1] + "'";
		}

		if (callDateToFrom != null && !callDateToFrom.split(",")[0].equals("null")) {
			com.sendKeys(text_CallDateFrom, callDateToFrom.split(",")[0]);
			message = message + "| callDateFrom- '" + callDateToFrom.split(",")[0] + "'";
		}

		if (callDateToFrom != null && !callDateToFrom.split(",")[1].equals("null")) {
			com.sendKeys(text_CallDateTo, callDateToFrom.split(",")[1]);
			message = message + "| callDateTo- '" + callDateToFrom.split(",")[1] + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);
	}

	public void verifyReportContent_With_FinForecast_QueryIOTSummary_Pages(String methodName)  {
		try{

			CustomReporter.createNode("For OB SMS MO - No of msgs");
			
			JSONManager json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName, "DailyTrafAnalysisPage");
			if (performSearch(json.getStr("HomeOp"), json.getStr("settlementDateToFrom"),
					json.getStr("processingDateToFrom") ,
					json.getStr("callDateToFrom") )) {

				tabHead.initHeaderColumnList(1);
				//int roamPartCol=tabHead.getColumnNumber("Roaming Partner");
				//int traffDirCol=tabHead.getColumnNumber("Traffic Direction");
				int servTypeCol=tabHead.getColumnNumber("Service Type");
				//int evTypeCol=tabHead.getColumnNumber("Event Type");
				int numOfMsgCol=tabHead.getColumnNumber("Number of Messages");
				int actMinsCol=tabHead.getColumnNumber("Actual Minutes");
				int bilMinsCol=tabHead.getColumnNumber("Billed Minutes");
				int actMbCol=tabHead.getColumnNumber("Actual MB");
				int bilMbCol=tabHead.getColumnNumber("Billed MB");
				int tapNetCol=tabHead.getColumnNumber("TAP Charge Net");
				int tapGrossCol=tabHead.getColumnNumber("TAP Charge Gross");

				
				
				/*Commenting these filters, coz on jenkins they are failing. Adding alternatives*/
				comm.performSearch(json.getStr("partnerPmn"), "Roaming Partner");
				comm.performSearch(json.getStr("trafDirection"), "Traffic Direction");
				comm.performSearch("SMS", "Service Type");
				comm.performSearch("MO", "Event Type");
				/* 
				 * comm.applyColumnFilter(1, roamPartCol,json.getStr("partnerPmn"));
				 * comm.applyColumnFilter(1, traffDirCol, json.getStr("trafDirection"));
				 * comm.applyColumnFilter(1, servTypeCol, "SMS");
				 * comm.applyColumnFilter(1, evTypeCol, "MO");
				 */
				
				comm.setRowsPerPage("All");
				
				comm.applyAggregateFunction("Sum", "Number of Messages");
				
				int lastRow = tabData.getRowCount();
				String numOfMsg=tabData.getCellText(lastRow, numOfMsgCol);
				CustomReporter.report(STATUS.INFO, "SMS MO: "
						+ "<br/>numOfMsg= "+numOfMsg
						);

				CustomReporter.createNode("For OB Voice MO - Act Min_Bill Min_Tap Net_Tap Gross");
				
				/*Commenting these filters, coz on jenkins they are failing. Adding alternatives*/
				comm.removeAppliedColumnFilter("Service Type");
				comm.performSearch("Voice", "Service Type");
				//comm.applyColumnFilter(1, servTypeCol, "Voice");
				
				comm.applyAggregateFunction("Sum", "Actual Minutes");
				comm.applyAggregateFunction("Sum", "Billed Minutes");
				comm.applyAggregateFunction("Sum", "TAP Charge Net");
				comm.applyAggregateFunction("Sum", "TAP Charge Gross");
				lastRow = tabData.getRowCount();
				String voiceMO_ActMin_BillMin_TapNet_TapGross=Util.removeCommas(tabData.getCellText(lastRow, actMinsCol))
						+","+Util.removeCommas(tabData.getCellText(lastRow, bilMinsCol))
						+","+Util.removeCommas(tabData.getCellText(lastRow, tapNetCol))
						+","+Util.removeCommas(tabData.getCellText(lastRow, tapGrossCol));
				CustomReporter.report(STATUS.INFO, "Voice MO: "
						+ "<br/>Actual Mins= "+voiceMO_ActMin_BillMin_TapNet_TapGross.split(",")[0]
								+ "<br/>Billed Mins= "+voiceMO_ActMin_BillMin_TapNet_TapGross.split(",")[1]
										+ "<br/>TAP Charge(Net)= "+voiceMO_ActMin_BillMin_TapNet_TapGross.split(",")[2]
												+ "<br/>TAP Charge(Gross)= "+voiceMO_ActMin_BillMin_TapNet_TapGross.split(",")[3]
						);

				CustomReporter.createNode("For OB Data MB- Act Mb_Bill Mb");

				/*Commenting these filters, coz on jenkins they are failing. Adding alternatives*/
				comm.removeAppliedColumnFilter("'MO'");
				comm.removeAppliedColumnFilter("Service Type");
				comm.performSearch("Data", "Service Type");
				//comm.applyColumnFilter(1, servTypeCol, "Data");
				comm.applyAggregateFunction("Sum", "Actual MB");
				comm.applyAggregateFunction("Sum", "Billed MB");
				lastRow = tabData.getRowCount();
				String dataMB_ActMb_BillMb=Util.removeCommas(tabData.getCellText(lastRow, actMbCol))
						+","+Util.removeCommas(tabData.getCellText(lastRow, bilMbCol));
				CustomReporter.report(STATUS.INFO, "Data MB: "
						+ "<br/>Actual MB= "+dataMB_ActMb_BillMb.split(",")[0]
								+ "<br/>Billed MB= "+dataMB_ActMb_BillMb.split(",")[1]
						);

				CustomReporter.createNode("Verifying Data with Financial Report Forecasted TAP Discounted Charges");
				
				Navigator nav=new Navigator();
				IOTRONHomePage ihp=new IOTRONHomePage();
				nav.traverseMenu_VerifyPageTitle( PagesTitle.FinancialReportForecastedTAPDiscountedCharges,
						ihp.link_Forecasting, ihp.link_Forecasting_FinancialReportForecastedTAPDiscountedCharges);
				FinancialReport_ForecastedTAPDiscountedCharges finForecastRep= new FinancialReport_ForecastedTAPDiscountedCharges();
				finForecastRep.verify_DailyTrafficAnalysisReport_Data(numOfMsg, voiceMO_ActMin_BillMin_TapNet_TapGross,
						dataMB_ActMb_BillMb, methodName);

				CustomReporter.createNode("Verifying Data with Query IOT Summary");
				
				nav.traverseMenu_VerifyPageTitle( PagesTitle.QueryIOTSummary,
						ihp.link_IOTRONSystemAdministration, ihp.link_IOTRONSystemAdministration_QueryIOTSummary);
				ViewIOTTrafficData iotTrafficData=new ViewIOTTrafficData();
				iotTrafficData.verify_DailyTrafficAnalysisReport_Data(numOfMsg, voiceMO_ActMin_BillMin_TapNet_TapGross,
						dataMB_ActMb_BillMb, methodName);

			}
		}catch (Exception e) {
			new CustomExceptionHandler(e);
		}

	}

	public void verifyChartContent(String methodName) {
		JSONManager json= new JSONManager(Module_KPN_Op4.jsonFilePath, methodName);
		if (performSearch(json.getStr("HomeOp"), json.getStr("settlementDateToFrom"),
				json.getStr("processingDateToFrom") ,
				json.getStr("callDateToFrom") )) {
			if(com.isElementPresent(icon_Minus_ChartsSection)){
				if(com.waitForElementTobe_NotVisible(data_WaitingForData,1,"")){
					CustomReporter.report(STATUS.PASS, "Charts displayed");
					CustomReporter.report(STATUS.INFO, "Verifing the Charts sections");
					com.isElementPresent(radio_Tax_Net, "Tax-Net Radio button");
					com.isElementPresent(radio_Tax_Gross, "Tax-Gross Radio button");
					com.isElementPresent(radio_ChargingIncrement_Actual, "Charging Increment-Actual Radio button");
					com.isElementPresent(radio_ChargingIncrement_Billed, "Charging Increment-Billed Radio button");
					com.isElementPresent(data_Section_VoiceVolumes, "Voice Volumes section");
					com.isElementPresent(data_Section_VoiceCharges, "Voice Charges section");
					com.isElementPresent(data_Section_SmsVolumes, "SMS Volumes section");
					com.isElementPresent(data_Section_SmsCharges, "SMS Charges section");
					com.isElementPresent(data_Section_DataVolumes, "Data Volumes section");
					com.isElementPresent(data_Section_DataCharges, "Data Charges section");
				}else{
					CustomReporter.report(STATUS.FAIL, "Charts are not getting displayed instead 'Waiting for data' is displaying");	
				}
			}else{
				CustomReporter.report(STATUS.FAIL, "Charts Section is not maximized");
			}
		}
	}

}
