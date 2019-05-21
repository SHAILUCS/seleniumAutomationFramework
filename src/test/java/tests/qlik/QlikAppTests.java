/**
 * QlikAppTests.java
 * This class contains the @test methods, for Qlik App
 * */

package tests.qlik;

import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.configData_Util.Constant;
import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.jsonUtil.JSONManager;
import objectRepository.Reporting.OverviewReport_P507;
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
	public static final String overViewReportFileExtension = ".csv";
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
				trafficMonthArr = Util.getArray("Oct","Nov","Dec");
				trafficYear = (Integer.parseInt(trafficYear) - 1)+"";
			} else if (currentMonthIndex == 1) {
				trafficMonthArr = Util.getArray("Jan");
			}  else if (currentMonthIndex == 2) {
				trafficMonthArr = Util.getArray("Jan","Feb");
			} else {
				cal.add(Calendar.MONTH, -1);
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
	 * Below are the tests for each App
	 * Restructuring the scripts to have different test for different clients,
	 * because of an issue in jenkins
	 * @author shailendra.rajawat 10-May-2019
	 */
	@Test(description = "T01_Data Verification - EE IOTRON Discount Management App")
	private void T01_DataVerification_EE() {
		dataVerification_COMMON("EE IOTRON Discount Management App");
	}
	
	@Test(description = "T02_Data Verification - GoMalta_IOTRON Discount Management App")
	private void T02_DataVerification_GoMalta() {
		dataVerification_COMMON("GoMalta_IOTRON Discount Management App");
	}

	@Test(description = "T03_Data Verification - HKT_IOTRON Discount Management App")
	private void T03_DataVerification_HKT() {
		dataVerification_COMMON("HKT_IOTRON Discount Management App");
	}
	
	@Test(description = "T04_Data Verification - JT_IOTRON Discount Management App")
	private void T04_DataVerification_JT() {
		dataVerification_COMMON("JT_IOTRON Discount Management App");
	}
	
	@Test(description = "T05_Data Verification - KPN_IOTRON Discount Management App")
	private void T05_DataVerification_KPN() {
		dataVerification_COMMON("KPN_IOTRON Discount Management App");
	}
	
	@Test(description = "T06_Data Verification - MTN Cyprus IOTRON Discount Management App")
	private void T06_DataVerification_MTN_Cyprus() {
		dataVerification_COMMON("MTN Cyprus IOTRON Discount Management App");
	}
	
	@Test(description = "T07_Data Verification - MTN Group_IOTRON Discount Management App")
	private void T07_DataVerification_MTN_Group() {
		dataVerification_COMMON("MTN Group_IOTRON Discount Management App");
	}
	
	@Test(description = "T08_Data Verification - Optus_IOTRON Discount Management App")
	private void T08_DataVerification_Optus() {
		dataVerification_COMMON("Optus_IOTRON Discount Management App");
	}

	@Test(description = "T09_Data Verification - Siminn_IOTRON Discount Management App")
	private void T09_DataVerification_Siminn() {
		dataVerification_COMMON("Siminn_IOTRON Discount Management App");
	}

	@Test(description = "T10_Data Verification - Spark_IOTRON Discount Management App")
	private void T10_DataVerification_Spark() {
		dataVerification_COMMON("Spark_IOTRON Discount Management App");
	}

	@Test(description = "T11_Data Verification - Telstra_IOTRON Discount Management App")
	private void T11_DataVerification_Telstra() {
		dataVerification_COMMON("Telstra_IOTRON Discount Management App");
	}

	@Test(description = "T12_Data Verification - TMO_IOTRON Discount Management App")
	private void T12_DataVerification_TMO() {
		dataVerification_COMMON("TMO_IOTRON Discount Management App");
	}

	@Test(description = "T13_Data Verification - Verizon_IOTRON Discount Management App")
	private void T13_DataVerification_Verizon() {
		dataVerification_COMMON("Verizon_IOTRON Discount Management App");
	}
	
	@Test(description = "T14_Data Verification - Vivacom Bulgaria IOTRON Discount Management App")
	private void T14_DataVerification_Vivacom() {
		dataVerification_COMMON("Vivacom Bulgaria IOTRON Discount Management App");
	}
	
	@Test(description = "T15_Data Verification - Vodafone Iceland_IOTRON Discount Management App")
	private void T15_DataVerification_Vodafon() {
		dataVerification_COMMON("Vodafone Iceland_IOTRON Discount Management App");
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
	private void dataVerification_COMMON(String appName) {

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
		//for(String appName : appArr){

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
		//}

		CustomReporter.report_ExitCurrentNode(STATUS.INFO, QlikGRID.generateHTMLTable());

	}

	/**
	 * This test downloads the data from IOTRON Overview Report and keeps it
	 * inside the app / ibOb / month folder
	 * @author shailendra.rajawat 11-Apr-2019
	 */
	@Test(description = "T00_IOTRON Data Download")
	private void T00_IOTRON_DataDownload() {
		
		CustomReporter.report(STATUS.INFO, "Removing all older files from "+Constant.getQlikDownloadsPath());
		Util.forceDelete(Constant.getQlikDownloadsPath());
		
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

		OverviewReport_P507 p507 = new OverviewReport_P507();
		for (int i = 0; i < appArr.length; i++) {
			for (int j = 0; j < trafficMonthArr.length; j++) {
				// For OB
				String monthName = trafficMonthArr[j];
				String trafPeriod = Util.convertToString("MMyy",Util.convertToDate("MMMyyyy", monthName + trafficYear));
				String trafPeriodRange = trafPeriod+","+trafPeriod;
				
				CustomReporter.createNode("Downloading the data for | " + appArr[i] + " | " + trafPeriod);
				p507.performSearch(pmnArr[i], trafPeriodRange, null, null, TrafficDirection.CustomerOutbound.value, Currency.SDR.value, null, true, false, 0);
				if(overViewReportFileExtension.contains("xl")){
					p507.clickDownloadXlsButton();
				}else if(overViewReportFileExtension.contains("csv")){
					p507.clickDownloadCsvButton();
				}else{
					Assert.fail("Invalid extension mentioned in constant overViewReportFileExtension");
				}
				String srcFilePath = Util.renameDownloadedFile(overViewReportFileExtension, overViewReportFileNameWithoutExtension + " " + monthName + overViewReportFileExtension);
				String overviewReportPathOB = Constant.getQlikDownloadsPath() + "/" + appArr[i] + "/" + overviewReportFolderName + "/" + OB;
				Util.moveFileToDirectory(srcFilePath, overviewReportPathOB);


				//For IB
				p507.performSearch(null, null, null, null, TrafficDirection.VisitorInbound.value, null, null, false, false, 0);
				if(overViewReportFileExtension.contains("xl")){
					p507.clickDownloadXlsButton();
				}else if(overViewReportFileExtension.contains("csv")){
					p507.clickDownloadCsvButton();
				}else{
					Assert.fail("Invalid extension mentioned in constant overViewReportFileExtension");
				}
				srcFilePath = Util.renameDownloadedFile(overViewReportFileExtension, overViewReportFileNameWithoutExtension + " " + monthName + overViewReportFileExtension);
				String overviewReportPathIB = Constant.getQlikDownloadsPath() + "/" + appArr[i] + "/" + overviewReportFolderName + "/" + IB;
				Util.moveFileToDirectory(srcFilePath, overviewReportPathIB);

				// Resetting the page
				p507.resetPage();

			}
		}
	}

}
