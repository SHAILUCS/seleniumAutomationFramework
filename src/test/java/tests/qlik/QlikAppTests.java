/**
 * QlikAppTests.java
 * This class contains the @test methods, for Qlik App
 * */

package tests.qlik;

import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;

import common.configData_Util.Constant;
import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.jsonUtil.JSONManager;
import objectRepository.Reporting.OverviewReport_P507;
import objectRepository.common.ApexCommon;
import objectRepository.common.Currency;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import objectRepository.common.TrafficDirection;
import or.qlik.QlikAppOverview;
import or.qlik.QlikHub;
import or.qlik.pojo.QlikGRID;

public class QlikAppTests {

	public static final String jsonFilePath = Constant.getResourcesFolderPath() + "Qlik.json";
	public static final String overViewReportFileNameWithoutExtension = "Overview Report";
	public static final String overviewReportFolderName = "Overview Report";
	private static final String OB = "OB";
	private static final String IB = "IB";
	private static String stream;
	private static String[] pmnArr;
	private static String[] appArr;
	private static String trafficYear;
	private static String[] trafficMonthArr;
	
	private static void initParameters(String jsonObjName) {
		JSONManager json = new JSONManager(jsonFilePath, jsonObjName);
		stream = json.getStr("stream");
		appArr = json.getStrArr("appsArr");
		trafficYear = json.getStr("Traffic Year");
		trafficMonthArr = json.getStrArr("Traffic Month");
		/* 
		 * In case we are not providing the Traffic Year and Traffic Months in 
		 * json file, then system will set current year as traffic Year, and 
		 * selects the last 3 months(if available)
		 * */
		if(trafficYear == null || trafficYear.trim().equals("")){
			trafficYear = Util.convertToString("YYYY", new Date());
		}

		//String[] trafficMonthArr = json.getStrArr("Traffic Month");
		if(trafficMonthArr == null || trafficMonthArr.length == 0){
			Calendar cal = Calendar.getInstance();
			int currentMonthIndex = cal.get(Calendar.MONTH);
			if (currentMonthIndex == 0) {
				trafficMonthArr = Util.getArray("Jan");
			} else if (currentMonthIndex == 1) {
				trafficMonthArr = Util.getArray("Jan", "Feb");
			} else {
				String currentMonth = Util.convertToString("MMM", cal.getTime());
				cal.add(Calendar.MONTH, -1);
				String oldMonth = Util.convertToString("MMM", cal.getTime());
				cal.add(Calendar.MONTH, -1);
				String olderMonth = Util.convertToString("MMM", cal.getTime());
				trafficMonthArr = Util.getArray(olderMonth, oldMonth, currentMonth);
			}

		}

		pmnArr = json.getStrArr("pmnArr");
	}
	
	
	/**
	 * <pre>
	 * Run T02_IOTRON_DataDownload prior to running this test 
	 * This test will perform the following operations on Qlik Application
	 *  1. Opens <b>IOTRON</b> Stream
	 *  2. Repeat below steps For each "<b>App</b>"
	 *  	2.1 Opens the app in new tab
	 *  	2.2 Repeat below steps, For each "<b>sheet</b>" 
	 *  		2.2.1 Open the sheet 
	 *  		2.2.2 Apply <b>fiter</b> with data >> year, month, currency and IB/OB
	 *  		2.2.3 <b>Download</b> the xlsx file and move the file to appropriate folder
	 *  		2.2.4 <b>Load</b> the xlsx file content and <b>compare</b> its values with IOTRON's Overview Report csv
	 *  </pre>
	 * */
	@Test(description = "T01_Data Verification")
	private void T01_DataVerification() {

		String jsonObjName = "T01_DataVerification";
		initParameters(jsonObjName);
		
		QlikHub hub = new QlikHub();

		//JSONManager json = new JSONManager(jsonFilePath, jsonObjName);
		/* Selecting Stream */
		//String stream = json.getStr("stream");
		hub.selectStream(stream);
		QlikGRID.setStream(stream);
		QlikGRID.setCurr("SDR");

		//String[] appsArr = json.getStrArr("appsArr");
		//String trafficYear = json.getStr("Traffic Year");
		//String[] trafficMonthArr = json.getStrArr("Traffic Month");

		/* Here we are downloading the sheets data for each app */
		for(String appName : appArr){

			/* Opening app in new tab */
			QlikAppOverview app = hub.openClientAppOverview_InNewTab(appName);

			//json = new JSONManager(jsonFilePath, jsonObjName);

			/* Opening the sheet and exporting the OB/IB data */
			String sheet = "YoY Value Performance";
			app.openSheet(sheet);
			app.exportData_VerifyWithOvRep(jsonObjName, appName, sheet, OB, trafficYear, trafficMonthArr);
			app.exportData_VerifyWithOvRep(jsonObjName, appName, sheet, IB, trafficYear, trafficMonthArr);

			/* Opening the sheet and exporting the OB/IB data */
			sheet = "YoY Volume Performance";
			app.openSheet(sheet);
			app.exportData_VerifyWithOvRep(jsonObjName, appName, sheet, OB, trafficYear, trafficMonthArr);
			app.exportData_VerifyWithOvRep(jsonObjName, appName, sheet, IB, trafficYear, trafficMonthArr);

			/* Opening the sheet and exporting the OB/IB data */
			sheet = "Data";
			app.openSheet(sheet);
			app.exportData_VerifyWithOvRep(jsonObjName, appName, sheet, OB, trafficYear, trafficMonthArr);
			app.exportData_VerifyWithOvRep(jsonObjName, appName, sheet, IB, trafficYear, trafficMonthArr);

			/* switching back to hub so that other app can be opened in new tab */
			hub.switchBackToHub(appName);
		}

		CustomReporter.report_ExitCurrentNode(STATUS.INFO, QlikGRID.generateHTMLTable());

	}

	/**
	 * This test downloads the data from IOTRON Overview Report and keeps it
	 * inside the app / ibOb / month folder
	 * @author shailendra.rajawat 11-Apr-2019
	 */
	@Test(description = "T02_IOTRON Data Download")
	private void T02_IOTRON_DataDownload() {
		String jsonObjName = "T01_DataVerification";
		initParameters(jsonObjName);
		//JSONManager json = new JSONManager(jsonFilePath,jsonObjName);
		//String[] pmnArr = json.getStrArr("pmnArr");
		//String trafficYear = json.getStr("Traffic Year");

		
		//String[] appArr = json.getStrArr("appsArr");

		Navigator nav = new Navigator();
		IOTRONHomePage h = new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(PagesTitle.OverviewReport_P507, h.link_Reporting,
				h.link_Reporting_OverviewReport);

		ApexCommon comm = new ApexCommon();

		OverviewReport_P507 p507 = new OverviewReport_P507();
		for (int i = 0; i < appArr.length; i++) {
			for (int j = 0; j < trafficMonthArr.length; j++) {
				// For OB
				String monthName = trafficMonthArr[j];
				String trafPeriod = Util.convertToString("MMyy",Util.convertToDate("MMMyyyy", monthName + trafficYear));
				String trafPeriodRange = trafPeriod+","+trafPeriod; 
				p507.performSearch(pmnArr[i], trafPeriodRange, null, null, TrafficDirection.CustomerOutbound.value, Currency.SDR.value, null, true, false, 0);
				comm.downloadCSV();
				String srcFilePath = Util.renameDownloadedFile("csv", overViewReportFileNameWithoutExtension + " " + monthName + ".csv");
				String overviewReportPathOB = Constant.getQlikDownloadsPath() + "/" + appArr[i] + "/" + overviewReportFolderName + "/" + OB;
				Util.moveFileToDirectory(srcFilePath, overviewReportPathOB);


				//For IB
				p507.performSearch(null, null, null, null, TrafficDirection.VisitorInbound.value, null, null, false, false, 0);
				comm.downloadCSV();
				srcFilePath = Util.renameDownloadedFile("csv", overViewReportFileNameWithoutExtension + " " + monthName + ".csv");
				String overviewReportPathIB = Constant.getQlikDownloadsPath() + "/" + appArr[i] + "/" + overviewReportFolderName + "/" + IB;
				Util.moveFileToDirectory(srcFilePath, overviewReportPathIB);

				// Resetting the page
				p507.resetPage();

			}
		}
	}

}
