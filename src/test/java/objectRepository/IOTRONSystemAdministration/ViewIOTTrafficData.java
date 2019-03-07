package objectRepository.IOTRONSystemAdministration;

import java.math.RoundingMode;

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
import objectRepository.BusinessIntelligence.DailyTrafficAnalysisReport;
import objectRepository.Reporting.OverviewReport_P507;
import objectRepository.common.ApexCommon;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import tests.MainRegression.Module_KPN_Op4;

public class ViewIOTTrafficData {
	private SeleniumMethods com;
	public static String title = "View IOT Traffic Data";
	private ApexCommon comm;
	private WebTable tabData;
	private WebTable tabHead;
	private int headerRow=1;
	
	public ViewIOTTrafficData() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm= new ApexCommon();
		tabHead= new WebTable(comm.table_ResultTabHeader);
		tabData= new WebTable(comm.table_ResultTabData);
	}

	@FindBy(id = "P426_TRAFFIC_DIRECTION")
	private WebElement select_TrafficDirection;

	@FindBy(id = "P426_CLIENT_ME_ID")
	private WebElement select_ClientOp;

	@FindBy(id = "P426_PARTNER_ME_ID")
	private WebElement select_PartnerOp;

	@FindBy(id = "P426_TRAFFIC_PERIOD_LEFT")
	public WebElement select_TrafPeriodLeft;

	@FindBy(id = "P426_TRAFFIC_PERIOD_RIGHT")
	public WebElement select_TrafPeriodRight;

	@FindBy(xpath = "//button[.='Generate Report']")
	private WebElement button_GenerateReport;

	public boolean performSearch(String cutomerVisitorRoamer, String client, String partner, String traffPeriodRange) {
		com.javaScript_ScrollIntoBOTTOMView(select_TrafficDirection);
		String message="";
		if(cutomerVisitorRoamer!=null){
			com.selectByPartialVisibleText(select_TrafficDirection, cutomerVisitorRoamer);
			message=message+" | trafficDirection- '"+cutomerVisitorRoamer+"'";
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		}

		if(client!=null){
			com.selectByPartialVisibleText(select_ClientOp, client);
			message=message+" | client- '"+client+"'";
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		}

		if(partner!=null){
			com.selectByPartialVisibleText(select_PartnerOp, partner);
			message=message+" | partner- '"+partner+"'";
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator,10);
		}

		if(traffPeriodRange!=null){
			com.deselectAllValues_Shuttle_DoubleClick(select_TrafPeriodRight);
			comm.selectTrafficPeriod(select_TrafPeriodLeft, traffPeriodRange.split(",")[0], traffPeriodRange.split(",")[1]);
			message=message+" | traffPeriodRange- '"+traffPeriodRange+"'";
		}

		com.click(button_GenerateReport);
		CustomReporter.report(STATUS.INFO, "Performed search operation for data :"+message);
		if(com.isElementPresent(comm.table_ResultTab)){
			return true;
		}
		CustomReporter.report(STATUS.FAIL, "Report NOT generated");
		return false;
	}

	public void verify_DailyTrafficAnalysisReport_Data(String numOfMsg,String voiceMO_ActMin_BillMin_TapNet_TapGross,
			String dataMB_ActMb_BillMb, String methodName) {
		
		CustomReporter.createNode("For OB SMS MO");
		
		JSONManager json=new JSONManager(Module_KPN_Op4.jsonFilePath, methodName, "ViewIOTTrafficData");
		if(performSearch(json.getStr("cutomerVisitorRoamer"),json.getStr("client"), json.getStr("partner"), json.getStr("traffPeriodRange"))){
			int numOfRecCol=tabHead.initHeaderColumnList(headerRow).getColumnNumber("No Of Records");
			int servTypeCol=tabHead.getColumnNumber("Service Type");
			int evTypeCol=tabHead.getColumnNumber("Event Type");

			comm.applyColumnFilter(headerRow, servTypeCol, "SMS");
			comm.applyColumnFilter(headerRow, evTypeCol, "MO");
			comm.setRowsPerPage("All");
			
			comm.applyAggregateFunction("Sum", "No Of Records");
			
			int lastRow=tabData.getRowCount();
			String data=tabData.getCellText(lastRow, numOfRecCol);
			Util.comparator_PageValues("Number of Messages", DailyTrafficAnalysisReport.title, numOfMsg, title, data);
			
			CustomReporter.createNode("For OB Voice MO");
			
			comm.applyColumnFilter(headerRow, servTypeCol, "Voice");
			
			String colName="Duration In Seconds";
			int dataCol=tabHead.getColumnNumber(colName);
			comm.applyAggregateFunction("Sum", colName);
			lastRow=tabData.getRowCount();
			data=tabData.getCellText(lastRow, dataCol);
			data=Util.BD(data).divide(Util.BD("60"), 20, RoundingMode.HALF_EVEN).toPlainString();
			Util.comparator_PageValues("Sum of Actual Minutes", DailyTrafficAnalysisReport.title, voiceMO_ActMin_BillMin_TapNet_TapGross.split(",")[0], title, data);
			
			colName="Charged Duration";
			dataCol=tabHead.getColumnNumber(colName);
			comm.applyAggregateFunction("Sum", colName);
			lastRow=tabData.getRowCount();
			data=tabData.getCellText(lastRow, dataCol);
			data=Util.BD(data).divide(Util.BD("60"), 20, RoundingMode.HALF_EVEN).toPlainString();
			Util.comparator_PageValues("Sum of Billed Minutes", DailyTrafficAnalysisReport.title, voiceMO_ActMin_BillMin_TapNet_TapGross.split(",")[1], title, data);

			colName="Charge Net";
			dataCol=tabHead.getColumnNumber(colName);
			comm.applyAggregateFunction("Sum", colName);
			lastRow=tabData.getRowCount();
			data=Util.BD(tabData.getCellText(lastRow, dataCol)).multiply(Util.BD("-1")).toPlainString();
			Util.comparator_PageValues("Sum of TAP Charge Net", DailyTrafficAnalysisReport.title, voiceMO_ActMin_BillMin_TapNet_TapGross.split(",")[2], title, data);
			
			colName="Charge Gross";
			dataCol=tabHead.getColumnNumber(colName);
			comm.applyAggregateFunction("Sum", colName);
			lastRow=tabData.getRowCount();
			data=Util.BD(tabData.getCellText(lastRow, dataCol)).multiply(Util.BD("-1")).toPlainString();
			Util.comparator_PageValues("Sum of TAP Charge Gross", DailyTrafficAnalysisReport.title, voiceMO_ActMin_BillMin_TapNet_TapGross.split(",")[3], title, data);
		
			CustomReporter.createNode("For OB Data MB");
			
			comm.removeAppliedColumnFilter("'MO'");
			comm.applyColumnFilter(headerRow, servTypeCol, "Data");

			colName="Volume Outgoing Kb";
			dataCol=tabHead.getColumnNumber(colName);
			comm.applyAggregateFunction("Sum", colName);
			lastRow=tabData.getRowCount();
			data=tabData.getCellText(lastRow, dataCol);
			data=Util.BD(data).divide(Util.BD("1024"), 20, RoundingMode.HALF_EVEN).toPlainString();
			Util.comparator_PageValues("Sum of Actual MB", DailyTrafficAnalysisReport.title, dataMB_ActMb_BillMb.split(",")[0], title, data);
			
			colName="Charged Kilobytes";
			dataCol=tabHead.getColumnNumber(colName);
			comm.applyAggregateFunction("Sum", colName);
			lastRow=tabData.getRowCount();
			data=tabData.getCellText(lastRow, dataCol);
			data=Util.BD(data).divide(Util.BD("1024"), 20, RoundingMode.HALF_EVEN).toPlainString();
			Util.comparator_PageValues("Sum of Billed MB", DailyTrafficAnalysisReport.title, dataMB_ActMb_BillMb.split(",")[1], title, data);
			
		}

	}

	public void OR_T07CheckingAndCalculatingDataForCallDestinationBreakdown(String jsonFilePath, String jsonObjName,
			String homeTraffVol, String intTraffVol, String locTraffVol, String home_TotalTAPChargesExcludingTax,
			String int_TotalTAPChargesExcludingTax, String loc_TotalTAPChargesExcludingTax) {
		//Navigating to View IOT Traffic Data page
		Navigator nav=new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.traverseMenu_VerifyPageTitle( PagesTitle.QueryIOTSummary,
				ihp.link_IOTRONSystemAdministration, ihp.link_IOTRONSystemAdministration_QueryIOTSummary);
			
		JSONManager json=new JSONManager(jsonFilePath, jsonObjName,"viewIOTTrafficData-Search");
			if(performSearch(json.getStr("cutomerVisitorRoamer"),json.getStr("clientPMNs"),json.getStr("partner"),json.getStr("trafPeriodRange"))){
				WebTable headerTab= new WebTable(comm.table_ResultTabHeader);
				WebTable tab= new WebTable(comm.table_ResultTabData);
				int headerRow=1;
				comm.applyColumnFilter(headerRow, headerTab.initHeaderColumnList(headerRow).getColumnNumber("Service Type"), "Voice");
				comm.applyColumnFilter(headerRow, headerTab.getColumnNumber("Event Type"), "MO");
				comm.applyColumnFilter(headerRow, headerTab.getColumnNumber("Call Destination"), "H");
				comm.setRowsPerPage("All");
				comm.applyAggregateFunction("Sum", "Duration In Seconds");
				int aggrgateRow=tab.getRowCount();
				//headerTab.initHeaderColumnList(headerRow);
				int col_durInSec=headerTab.getColumnNumber("Duration In Seconds");
				String durInSec=tab.getCellText(aggrgateRow, col_durInSec);
				String homeTraffVol_IOTRep=Util.BD(durInSec).divide(Util.BD("60"),5,RoundingMode.HALF_EVEN).toPlainString();
				CustomReporter.report(STATUS.INFO, 
						" homeTraffVol_IOTRep("+homeTraffVol_IOTRep+") = durInSec("+durInSec+") / 60");
				//Util.comparator(homeTraffVol_IOTRep,homeTraffVol,  "Traffic Volume - Home");
				Util.comparator_PageValues("Traffic Volume - Home", OverviewReport_P507.title, homeTraffVol, title, homeTraffVol_IOTRep);
				
				comm.applyColumnFilter(headerRow, headerTab.getColumnNumber("Call Destination"), "I");
				aggrgateRow=tab.getRowCount();
				durInSec=tab.getCellText(aggrgateRow, col_durInSec);
				String intTraffVol_IOTRep=Util.BD(durInSec).divide(Util.BD("60"),5,RoundingMode.HALF_EVEN).toPlainString();
				CustomReporter.report(STATUS.INFO, 
						" intTraffVol_IOTRep("+intTraffVol_IOTRep+") = durInSec("+durInSec+") / 60");
				//Util.comparator(intTraffVol_IOTRep, intTraffVol, "Traffic Volume - International");
				Util.comparator_PageValues("Traffic Volume - International", OverviewReport_P507.title, intTraffVol, title, intTraffVol_IOTRep);
				
				comm.applyColumnFilter(headerRow, headerTab.getColumnNumber("Call Destination"), "L");
				aggrgateRow=tab.getRowCount();
				durInSec=tab.getCellText(aggrgateRow, col_durInSec);
				String locTraffVol_IOTRep=Util.BD(durInSec).divide(Util.BD("60"),5,RoundingMode.HALF_EVEN).toPlainString();
				CustomReporter.report(STATUS.INFO, 
						" locTraffVol_IOTRep("+locTraffVol_IOTRep+") = durInSec("+durInSec+") / 60");
				//Util.comparator(locTraffVol_IOTRep,locTraffVol,  "Traffic Volume - Local");
				Util.comparator_PageValues("Traffic Volume - Local", OverviewReport_P507.title, locTraffVol, title, locTraffVol_IOTRep);

				//Get the Value of {Charge Gross} for each {traffic period} and multiply with SDR-AUD FX Rate
				json=new JSONManager(jsonFilePath, jsonObjName,"viewIOTTrafficData-Search-SingleTrafPer");
				if(performSearch(json.getStr("cutomerVisitorRoamer"),json.getStr("clientPMNs"),json.getStr("partner"),json.getStr("trafPeriodRange"))){
					int col_ChargeGross=headerTab.getColumnNumber( "Charge Gross");

					comm.applyAggregateFunction("Sum", "Charge Gross");
					aggrgateRow=tab.getRowCount();
					String chargeGross=tab.getCellText(aggrgateRow, col_ChargeGross);
					
					/** Go to Data Loading tab at top of the window and click on SDR Conversion Rates link, select Traffic Period 1216 and fined AUD in the table */
					String SDRFxRate_AUD_Feb17=json.getStr("SDRFxRate_AUD_Feb17");
					CustomReporter.report(STATUS.INFO, "SDRFxRate_AUD_Feb17 = "+SDRFxRate_AUD_Feb17);
					
					String locChargeGross_IOTRep=Util.BD(chargeGross).multiply(Util.BD("-"+SDRFxRate_AUD_Feb17)).toPlainString();
					CustomReporter.report(STATUS.INFO, 
							" locChargeGross_IOTRep("+locChargeGross_IOTRep+") = chargeGross("+chargeGross+") * -SDRFxRate_AUD_Feb16("+SDRFxRate_AUD_Feb17+")");
					//Util.comparator(locChargeGross_IOTRep, loc_TotalTAPChargesExcludingTax,  "Local - {Charge Gross of IOT Report} and {TAP Charges Excluding Tax of Overview Rep}");
					Util.comparator_PageValues("Local - {Charge Gross of IOT Report} and {TAP Charges Excluding Tax of Overview Rep}", OverviewReport_P507.title, loc_TotalTAPChargesExcludingTax, title, locChargeGross_IOTRep);
					
					comm.applyColumnFilter(headerRow, headerTab.getColumnNumber("Call Destination"), "I");

					aggrgateRow=tab.getRowCount();
					chargeGross=tab.getCellText(aggrgateRow, col_ChargeGross);
					String intChargeGross_IOTRep=Util.BD(chargeGross).multiply(Util.BD("-"+SDRFxRate_AUD_Feb17)).toPlainString();
					CustomReporter.report(STATUS.INFO, 
							" intChargeGross_IOTRep("+intChargeGross_IOTRep+") = chargeGross("+chargeGross+") * -SDRFxRate_AUD_Feb16("+SDRFxRate_AUD_Feb17+")");
					//Util.comparator( intChargeGross_IOTRep, int_TotalTAPChargesExcludingTax, "International - {Charge Gross of IOT Report} and {TAP Charges Excluding Tax of Overview Rep}");
					Util.comparator_PageValues("International - {Charge Gross of IOT Report} and {TAP Charges Excluding Tax of Overview Rep}", OverviewReport_P507.title, int_TotalTAPChargesExcludingTax, title, intChargeGross_IOTRep);
										
					comm.applyColumnFilter(headerRow, headerTab.getColumnNumber("Call Destination"), "H");

					aggrgateRow=tab.getRowCount();
					chargeGross=tab.getCellText(aggrgateRow, col_ChargeGross);
					String homeChargeGross_IOTRep=Util.BD(chargeGross).multiply(Util.BD("-"+SDRFxRate_AUD_Feb17)).toPlainString();
					CustomReporter.report(STATUS.INFO, 
							" homeChargeGross_IOTRep("+homeChargeGross_IOTRep+") = chargeGross("+chargeGross+") * -SDRFxRate_AUD_Feb16("+SDRFxRate_AUD_Feb17+")");
					//Util.comparator(homeChargeGross_IOTRep, home_TotalTAPChargesExcludingTax,  "Home - {Charge Gross of IOT Report} and {TAP Charges Excluding Tax of Overview Rep}");
					Util.comparator_PageValues("Home - {Charge Gross of IOT Report} and {TAP Charges Excluding Tax of Overview Rep}", OverviewReport_P507.title, home_TotalTAPChargesExcludingTax, title, homeChargeGross_IOTRep);
					
				}
			}

		
	}
	
	public String getDurInSec_CharDur_AggrValues(String sat,String jsonFilePath, String... jsonObjName) {
		CustomReporter.createNode("Getting Dur in sec, and Charged Dur Col Aggr values for "+jsonObjName[jsonObjName.length-1]);
		JSONManager json=new JSONManager(jsonFilePath, jsonObjName);
		performSearch(json.getStr("cutomerVisitorRoamer"),json.getStr("client"), json.getStr("partner"), json.getStr("traffPeriodRange"));
		comm.applyColumnFilter(1, 2, "Voice");
		comm.applyColumnFilter(1, 3, "MO");
		comm.setRowsPerPage("All");
		comm.applyAggregateFunction("Sum", "Duration In Seconds");
		comm.applyAggregateFunction("Sum", "Charged Duration");
		int lastRow=tabData.getRowCount();
		//Getting the Outbound "Duration In Seconds" and "Charged Duration"
		int durInSec_Col=tabHead.initHeaderColumnList(headerRow).getColumnNumber("Duration In Seconds");
		int chargeDur_Col=tabHead.getColumnNumber("Charged Duration");
		String DurInSec=tabData.getCellText(lastRow, durInSec_Col);
		String ChargeDur=tabData.getCellText(lastRow, chargeDur_Col);
		String DurInSec_SAT="0";
		String ChargeDur_SAT="0";
		if(sat!=null && sat.equalsIgnoreCase("SAT")){
			int satCol=tabHead.getColumnNumber("Destination Country Iso Code");
			
			CustomReporter.createNode("checking 'SAT' Column Filter Can Be Applied on [Destination Country Iso Code] column");
			
			if (comm.checkColumnFilterCanBeApplied(headerRow, satCol, "SAT")) {
				comm.applyColumnFilter(headerRow, satCol, "SAT");
				lastRow = tabData.getRowCount();
				DurInSec_SAT = tabData.getCellText(lastRow, durInSec_Col);
				DurInSec = Util.BD(DurInSec).subtract(Util.BD(DurInSec_SAT)).toPlainString();
				ChargeDur_SAT = tabData.getCellText(lastRow, chargeDur_Col);
				ChargeDur = Util.BD(ChargeDur).subtract(Util.BD(ChargeDur_SAT)).toPlainString();
				comm.removeAppliedColumnFilter("Destination Country Iso Code = 'SAT'");
			}
		}
		DurInSec=Util.BD(DurInSec).divide(Util.BD("60"), 20, RoundingMode.HALF_EVEN).toPlainString();
		ChargeDur=Util.BD(ChargeDur).divide(Util.BD("60"), 20, RoundingMode.HALF_EVEN).toPlainString();
		CustomReporter.report(STATUS.INFO, jsonObjName[jsonObjName.length-1] + " - <br/>"
				+ "DurInSec="+DurInSec+"<br/>"
				+ "ChargeDur="+ChargeDur);
		return DurInSec+","+ChargeDur;
	}



}
