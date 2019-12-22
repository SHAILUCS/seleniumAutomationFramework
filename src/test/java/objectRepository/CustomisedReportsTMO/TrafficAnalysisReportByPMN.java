package objectRepository.CustomisedReportsTMO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import tests.MainRegression.Module_TMO;

public class TrafficAnalysisReportByPMN {

	private SeleniumMethods com;
	public static String title = "Traffic Analysis Report by PMN";
	private ApexCommon comm; 
	private WebTable tabData;
	private WebTable tabHead;

	public TrafficAnalysisReportByPMN() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		tabHead = new WebTable(comm.table_ResultTabHeader);
		tabData = new WebTable(comm.table_ResultTabData);
	}

	@FindBy(id = "P22_SETTLEMENT_DATE_FROM")
	private WebElement text_SettlementDateFrom;

	@FindBy(id = "P22_SETTLEMENT_DATE_TO")
	private WebElement text_SettlementDateTo;

	@FindBy(id = "P22_PROCESSING_DATE_FROM")
	private WebElement text_ProcessingDateFrom;

	@FindBy(id = "P22_PROCESSING_DATE_TO")
	private WebElement text_ProcessingDateTo;

	@FindBy(id = "P22_GENERATE_REPORT")
	private WebElement button_GenerateReport;

	private boolean performSearch(String settlementDateFrom, String settlementDateTo, String processingDateFrom,
			String processingDateTo) {
		
		fillBasicSearchFields(settlementDateFrom, settlementDateTo, processingDateFrom, processingDateTo);
		com.click(button_GenerateReport);
		if (com.isElementPresent(comm.table_ResultTab)) {
			return true;
		}
		return false;
	}

	public void fillBasicSearchFields(String settlementDateFrom, String settlementDateTo, String processingDateFrom,
			String processingDateTo) {
		String message="";
		com.javaScript_ScrollIntoBOTTOMView(text_SettlementDateFrom);
		
		if (settlementDateFrom != null){
			com.sendKeys(text_SettlementDateFrom, settlementDateFrom);
			message=message+"| settlementDateFrom- '"+settlementDateFrom+"'";
		}

		if (settlementDateTo != null){
			com.sendKeys(text_SettlementDateTo, settlementDateTo);
			message=message+"| settlementDateTo- '"+settlementDateTo+"'";
		}
		
		if (processingDateFrom != null){
			com.sendKeys(text_ProcessingDateFrom, processingDateFrom);
			message=message+"| processingDateFrom- '"+processingDateFrom+"'";
		}
		
		if (processingDateTo != null){
			com.sendKeys(text_ProcessingDateTo, processingDateTo);
			message=message+"| processingDateTo- '"+processingDateTo+"'";
		}
		CustomReporter.report(STATUS.INFO, "Performing search with data: "+message);
	}

	public void verifySmsValues(String methodName, String iBActualSmsMtVal_Pre, String iBActualSmsMtRevVal_Pre,
			String oBActualSmsMtVal_Pre, String oBActualSmsMtExpVal_Pre, String iBActualSmsMtVal_Post,
			String iBActualSmsMtRevVal_Post, String oBActualSmsMtVal_Post, String oBActualSmsMtExpVal_Post) {
		
		JSONManager json= new JSONManager(Module_TMO.jsonFilePath, methodName);
		String smsMtOrMO=json.getStr("smsMtOrMO");
		String iBPartner=json.getStr("iBPartner");
		String oBPartner=json.getStr("oBPartner");
		json= new JSONManager(Module_TMO.jsonFilePath, methodName,"searchParam_pre");
		String traffPeriod=json.getStr("traffPeriod");
		String pre_procDate=json.getStr("procDate");
		json= new JSONManager(Module_TMO.jsonFilePath, methodName,"searchParam_post");
		String post_procDate=json.getStr("procDate");
		
		String preProcLastDay = pre_procDate.substring(pre_procDate.indexOf(":") + 5, pre_procDate.indexOf(":") + 7);
		String postProcLastDay = post_procDate.substring(post_procDate.indexOf(":") + 5, post_procDate.indexOf(":") + 7);
		
		String procMonthNum = traffPeriod.substring(0, 2);
		String procYear = traffPeriod.substring(traffPeriod.length() - 2, traffPeriod.length());

		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(procYear), Integer.parseInt(procMonthNum) - 1, 1);
		String procMonthStr = (new SimpleDateFormat("MMM")).format(cal.getTime());

		String settlementDateFrom = "01-" + procMonthStr + "-" + procYear;
		String settlementDateTo_Pre = preProcLastDay + "-" + procMonthStr + "-" + procYear;
		String settlementDateTo_Post = postProcLastDay + "-" + procMonthStr + "-" + procYear;
		String processingDateFrom = "01-jan-" + procYear;
		String processingDateTo_Pre = settlementDateTo_Pre;
		String processingDateTo_Post = settlementDateTo_Post;

		CustomReporter.createNode("Pre Proc");
		
		if (performSearch(settlementDateFrom, settlementDateTo_Pre, processingDateFrom, processingDateTo_Pre)) {
			
			CustomReporter.createNode("For IB");
			
			int partnerPmnCol = tabHead.initHeaderColumnList(1).getColumnNumber("Partner PMN");
			int directionCol = tabHead.getColumnNumber("Direction");
			int smsCol = tabHead.getColumnNumber("SMS");
			int callTypeCol = tabHead.getColumnNumber("Call Type");
			int chargesWoTaxesCol = tabHead.getColumnNumber("Charges W/O Taxes");

			comm.applyColumnFilter(1, partnerPmnCol, iBPartner);
			comm.applyColumnFilter(1, directionCol, "Inbound");
			comm.applyColumnFilter(1, callTypeCol, smsMtOrMO.substring(smsMtOrMO.length()-2,smsMtOrMO.length()));

			int lastRow = tabData.getRowCount();
			String iBActualSmsMtVal_Pre_Page = tabData.getCellText(lastRow, smsCol);
			String iBActualSmsMtRevVal_Pre_Page = tabData.getCellText(lastRow, chargesWoTaxesCol);
			Util.comparator_PageValues("Pre Proc - INBOUND Actual "+smsMtOrMO, TapAccrualActualAndTrueUp.title,
					iBActualSmsMtVal_Pre, TrafficAnalysisReportByPMN.title, iBActualSmsMtVal_Pre_Page);
			Util.comparator_PageValues("Pre Proc - INBOUND Actual "+smsMtOrMO+" Rev", TapAccrualActualAndTrueUp.title,
					iBActualSmsMtRevVal_Pre, TrafficAnalysisReportByPMN.title, iBActualSmsMtRevVal_Pre_Page);

			CustomReporter.createNode("For OB");
			
			comm.removeAppliedColumnFilter("Inbound");
			comm.applyColumnFilter(1, partnerPmnCol, oBPartner);
			comm.applyColumnFilter(1, directionCol, "Outbound");

			lastRow = tabData.getRowCount();
			String oBActualSmsMtVal_Pre_Page = tabData.getCellText(lastRow, smsCol);
			String oBActualSmsMtExpVal_Pre_Page = tabData.getCellText(lastRow, chargesWoTaxesCol);
			Util.comparator_PageValues("Pre Proc - OUTBOUND Actual "+smsMtOrMO, TapAccrualActualAndTrueUp.title,
					oBActualSmsMtVal_Pre, TrafficAnalysisReportByPMN.title, oBActualSmsMtVal_Pre_Page);
			Util.comparator_PageValues("Pre Proc - OUTBOUND Actual "+smsMtOrMO+" Rev", TapAccrualActualAndTrueUp.title,
					oBActualSmsMtExpVal_Pre, TrafficAnalysisReportByPMN.title, oBActualSmsMtExpVal_Pre_Page);

			CustomReporter.createNode("Post Proc");
			
			if (performSearch(null, settlementDateTo_Post, null, processingDateTo_Post)) {
				
				CustomReporter.createNode("For OB");
				
				lastRow = tabData.getRowCount();
				String oBActualSmsMtVal_Post_Page = tabData.getCellText(lastRow, smsCol);
				String oBActualSmsMtExpVal_Post_Page = tabData.getCellText(lastRow, chargesWoTaxesCol);
				Util.comparator_PageValues("Post Proc - OUTBOUND Actual "+smsMtOrMO, TapAccrualActualAndTrueUp.title,
						oBActualSmsMtVal_Post, TrafficAnalysisReportByPMN.title, oBActualSmsMtVal_Post_Page);
				Util.comparator_PageValues("Post Proc - OUTBOUND Actual "+smsMtOrMO+" Rev", TapAccrualActualAndTrueUp.title,
						oBActualSmsMtExpVal_Post, TrafficAnalysisReportByPMN.title, oBActualSmsMtExpVal_Post_Page);

				CustomReporter.createNode("For IB");
				
				comm.removeAppliedColumnFilter("Outbound");
				comm.applyColumnFilter(1, partnerPmnCol, iBPartner);
				comm.applyColumnFilter(1, directionCol, "Inbound");

				lastRow = tabData.getRowCount();
				String iBActualSmsMtVal_Post_Page = tabData.getCellText(lastRow, smsCol);
				String iBActualSmsMtRevVal_Post_Page = tabData.getCellText(lastRow, chargesWoTaxesCol);
				Util.comparator_PageValues("Post Proc - INBOUND Actual "+smsMtOrMO, TapAccrualActualAndTrueUp.title,
						iBActualSmsMtVal_Post, TrafficAnalysisReportByPMN.title, iBActualSmsMtVal_Post_Page);
				Util.comparator_PageValues("Post Proc - INBOUND Actual "+smsMtOrMO+" Rev", TapAccrualActualAndTrueUp.title,
						iBActualSmsMtRevVal_Post, TrafficAnalysisReportByPMN.title, iBActualSmsMtRevVal_Post_Page);

			}
		}
	}

}
