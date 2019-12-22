/**
 * QlikAppTests.java
 * This class contains the @test methods, for Qlik App
 * */

package tests.qlik;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.jsonUtil.JSONManager;

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
	private static String trafficYear;
	private static String[] trafficMonthArr;
	
	private static void initParameters(String jsonObjName) {
		JSONManager json = new JSONManager(jsonFilePath, jsonObjName);
		stream = json.getStr("stream");
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
	private void dataVerification_COMMON(String appName,Locale locale) {

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
			app.exportData_VerifyWithOvRep(jsonObjName, appName, sheet, OB, trafficYear, trafficMonthArr, locale);
			app.exportData_VerifyWithOvRep(jsonObjName, appName, sheet, IB, trafficYear, trafficMonthArr, locale);

			/* Opening the sheet and exporting the OB/IB data */
			sheet = "YoY Volume Performance";
			app.openSheet(sheet);
			app.exportData_VerifyWithOvRep(jsonObjName, appName, sheet, OB, trafficYear, trafficMonthArr, locale);
			app.exportData_VerifyWithOvRep(jsonObjName, appName, sheet, IB, trafficYear, trafficMonthArr, locale);

			/* Opening the sheet and exporting the OB/IB data */
			sheet = "Data";
			app.openSheet(sheet);
			app.exportData_VerifyWithOvRep(jsonObjName, appName, sheet, OB, trafficYear, trafficMonthArr, locale);
			app.exportData_VerifyWithOvRep(jsonObjName, appName, sheet, IB, trafficYear, trafficMonthArr, locale);

		//}

		CustomReporter.report_ExitCurrentNode(STATUS.INFO, QlikGRID.generateHTMLTable());

	}
	
	/**
	 * This method downloads the data from IOTRON Overview Report and keeps it
	 * inside the app / ibOb / month folder
	 * @author shailendra.rajawat 11-Apr-2019
	 */
	private void dataDownload_IOTRON_COMMON(String appName, String commaSeparatedPmns) {
		
		// Removing the older Qlik Data downloaded files
		Util.forceDelete(Constant.getQlikDownloadsPath() + "/" + appName);
		
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
		//for (int i = 0; i < appArr.length; i++) {
			for (int j = 0; j < trafficMonthArr.length; j++) {
				// For OB
				String monthName = trafficMonthArr[j];
				String trafPeriod = Util.convertToString("MMyy",Util.convertToDate("MMMyyyy", monthName + trafficYear));
				String trafPeriodRange = trafPeriod+","+trafPeriod;
				
				CustomReporter.createNode("Downloading the data for | " + appName + " | " + trafPeriod);
				p507.performSearch(commaSeparatedPmns, trafPeriodRange, null, null, TrafficDirection.CustomerOutbound.value, Currency.SDR.value, null, true, false, 0);
				
				downloadDataFromIOTRON_OR(appName, monthName, OB);
				/*synchronized (this) {
					if(overViewReportFileExtension.contains("xl")){
						p507.clickDownloadXlsButton();
					}else if(overViewReportFileExtension.contains("csv")){
						p507.clickDownloadCsvButton();
					}else{
						Assert.fail("Invalid extension mentioned in constant overViewReportFileExtension");
					}
					String srcFilePath = Util.renameDownloadedFile(overViewReportFileExtension, overViewReportFileNameWithoutExtension + " " + monthName + overViewReportFileExtension);
					String overviewReportPathOB = Constant.getQlikDownloadsPath() + "/" + appName + "/" + overviewReportFolderName + "/" + OB;
					Util.moveFileToDirectory(srcFilePath, overviewReportPathOB);
				}*/

				//For IB
				p507.performSearch(null, null, null, null, TrafficDirection.VisitorInbound.value, null, null, false, false, 0);
				
				downloadDataFromIOTRON_OR(appName, monthName, IB);
				
				/*synchronized (this) {
					if(overViewReportFileExtension.contains("xl")){
						p507.clickDownloadXlsButton();
					}else if(overViewReportFileExtension.contains("csv")){
						p507.clickDownloadCsvButton();
					}else{
						Assert.fail("Invalid extension mentioned in constant overViewReportFileExtension");
					}
					String srcFilePath = Util.renameDownloadedFile(overViewReportFileExtension, overViewReportFileNameWithoutExtension + " " + monthName + overViewReportFileExtension);
					String overviewReportPathIB = Constant.getQlikDownloadsPath() + "/" + appName + "/" + overviewReportFolderName + "/" + IB;
					Util.moveFileToDirectory(srcFilePath, overviewReportPathIB);
				}*/
				
				// Resetting the page
				p507.resetPage();

			}
		//}
	}
	
	/**
	 * This method is created to resolve the downloaded csv/xls file access error, 
	 * one thread is accessing another threads file
	 * due to which our scripts are failing
	 * Unable to delete file: C:\Users\shailendra.rajawat\git\iotronApex5\Downloads\Overview_report_download_csv.csv
	 *	Test Data:-
	 *	[Passed Data, fileNameContains: '.csv'] 
	 *	Stack Trace :- null
	 * @author shailendra.rajawat 06-Jun-2019
	 * */
	private static synchronized void downloadDataFromIOTRON_OR(String appName, String monthName, String IB_OB){
		OverviewReport_P507 p507 = new OverviewReport_P507();
		if(overViewReportFileExtension.contains("xl")){
			p507.clickDownloadXlsButton();
		}else if(overViewReportFileExtension.contains("csv")){
			p507.clickDownloadCsvButton();
		}else{
			Assert.fail("Invalid extension mentioned in constant overViewReportFileExtension");
		}
		String srcFilePath = Util.renameDownloadedFile(overViewReportFileExtension, overViewReportFileNameWithoutExtension + " " + monthName + overViewReportFileExtension);
		String overviewReportPath = Constant.getQlikDownloadsPath() + "/" + appName + "/" + overviewReportFolderName + "/" + IB_OB;
		Util.moveFileToDirectory(srcFilePath, overviewReportPath);
	}
	

	/**
	 * Below are the tests for each App
	 * Restructuring the scripts to have different test for different clients,
	 * because of an issue in jenkins
	 * @author shailendra.rajawat 10-May-2019
	 */
	@Test(description = "T01_Data Verification - EE IOTRON Discount Management App")
	private void T01_DataVerification_EE() {
		dataVerification_COMMON("EE IOTRON Discount Management App", null);
	}
	
	@Test(description = "T02_Data Verification - GoMalta_IOTRON Discount Management App")
	private void T02_DataVerification_GoMalta() {
		dataVerification_COMMON("GoMalta_IOTRON Discount Management App", null);
	}

	@Test(description = "T03_Data Verification - HKT_IOTRON Discount Management App")
	private void T03_DataVerification_HKT() {
		dataVerification_COMMON("HKT_IOTRON Discount Management App", null);
	}
	
	@Test(description = "T04_Data Verification - JT_IOTRON Discount Management App")
	private void T04_DataVerification_JT() {
		dataVerification_COMMON("JT_IOTRON Discount Management App", null);
	}
	
	@Test(description = "T05_Data Verification - KPN_IOTRON Discount Management App")
	private void T05_DataVerification_KPN() {
		dataVerification_COMMON("KPN_IOTRON Discount Management App", null);
	}
	
	@Test(description = "T06_Data Verification - MTN Cyprus IOTRON Discount Management App")
	private void T06_DataVerification_MTN_Cyprus() {
		dataVerification_COMMON("MTN Cyprus IOTRON Discount Management App", null);
	}
	
	@Test(description = "T07_Data Verification - MTN Group_IOTRON Discount Management App")
	private void T07_DataVerification_MTN_Group() {
		dataVerification_COMMON("MTN Group_IOTRON Discount Management App", null);
	}
	
	@Test(description = "T08_Data Verification - Optus_IOTRON Discount Management App")
	private void T08_DataVerification_Optus() {
		dataVerification_COMMON("Optus_IOTRON Discount Management App", null);
	}

	@Test(description = "T09_Data Verification - Siminn_IOTRON Discount Management App")
	private void T09_DataVerification_Siminn() {
		dataVerification_COMMON("Siminn_IOTRON Discount Management App", null);
	}

	@Test(description = "T10_Data Verification - Spark_IOTRON Discount Management App")
	private void T10_DataVerification_Spark() {
		dataVerification_COMMON("Spark_IOTRON Discount Management App", null);
	}

	@Test(description = "T11_Data Verification - Telstra_IOTRON Discount Management App")
	private void T11_DataVerification_Telstra() {
		dataVerification_COMMON("Telstra_IOTRON Discount Management App", null);
	}

	@Test(description = "T12_Data Verification - TMO_IOTRON Discount Management App")
	private void T12_DataVerification_TMO() {
		dataVerification_COMMON("TMO_IOTRON Discount Management App", null);
	}

	@Test(description = "T13_Data Verification - Verizon_IOTRON Discount Management App")
	private void T13_DataVerification_Verizon() {
		dataVerification_COMMON("Verizon_IOTRON Discount Management App", null);
	}
	
	@Test(description = "T14_Data Verification - Vivacom Bulgaria IOTRON Discount Management App")
	private void T14_DataVerification_Vivacom() {
		dataVerification_COMMON("Vivacom Bulgaria IOTRON Discount Management App", null);
	}
	
	@Test(description = "T15_Data Verification - Vodafone Iceland_IOTRON Discount Management App")
	private void T15_DataVerification_Vodafon() {
		/*
		 * This Script is reporting failures due to Locale settings, For all
		 * scripts we are working with numbers where dot is the decimal
		 * separator, but this App is having numbers with comma as a decimal
		 * separator. 
		 * One solution is, we can mention the Locale.GERMANY here, so that number
		 * 123,10 can be read as 123.10, this should solve the issue
		 */
		dataVerification_COMMON("Vodafone Iceland_IOTRON Discount Management App" , Locale.GERMAN);
	}

	@Test(description = "T16_Data Verification - Nova Iceland_IOTRON Discount Management App")
	private void T16_DataVerification_NovaIceland() {
		dataVerification_COMMON("Nova Iceland_IOTRON Discount Management App", null);
	}
	

	@Test(description = "T17_Data Verification - Hutch Group_IOTRON Discount Management App")
	private void T17_DataVerification_HutchGroup() {
		dataVerification_COMMON("Hutch Group_IOTRON Discount Management App", null);
	}
	

	/**
	 * Below are the tests for each App
	 * Restructuring the scripts to have different test for different clients,
	 * because scripts were taking a lot of time in execution, I will run them in parallel to save time
	 * @author shailendra.rajawat 05-Jun-2019
	 */
	@Test(description = "T001_IOTRON Data Download - EE IOTRON Discount Management App")
	private void T001_IOTRON_DataDownload_EE() {
		dataDownload_IOTRON_COMMON("EE IOTRON Discount Management App", "GBRME, GBROR");
	}

	@Test(description = "T002_IOTRON Data Download - GoMalta_IOTRON Discount Management App")
	private void T002_IOTRON_DataDownload_GoMalta() {
		dataDownload_IOTRON_COMMON("GoMalta_IOTRON Discount Management App", "MLTGO");
	}
	
	@Test(description = "T003_IOTRON Data Download - HKT_IOTRON Discount Management App")
	private void T003_IOTRON_DataDownload_HKT() {
		dataDownload_IOTRON_COMMON("HKT_IOTRON Discount Management App", "HKGTC, HKGSI, HKGNW, HKGMC, HKGM3");
	}
	
	@Test(description = "T004_IOTRON Data Download - JT_IOTRON Discount Management App")
	private void T004_IOTRON_DataDownload_JT() {
		dataDownload_IOTRON_COMMON("JT_IOTRON Discount Management App", "GBRJT");
	}
	
	@Test(description = "T005_IOTRON Data Download - KPN_IOTRON Discount Management App")
	private void T005_IOTRON_DataDownload_KPN() {
		dataDownload_IOTRON_COMMON("KPN_IOTRON Discount Management App", "NLDPT, NLDTM");
	}
	
	@Test(description = "T006_IOTRON Data Download - MTN Cyprus IOTRON Discount Management App")
	private void T006_IOTRON_DataDownload_MTN_Cyprus() {
		dataDownload_IOTRON_COMMON("MTN Cyprus IOTRON Discount Management App", "CYPSC");
	}
	
	@Test(description = "T007_IOTRON Data Download - MTN Group_IOTRON Discount Management App")
	private void T007_IOTRON_DataDownload_MTN_Group() {
		dataDownload_IOTRON_COMMON("MTN Group_IOTRON Discount Management App", "AFGAR, BENSP, BWAGA, CIVTL, CMRMT, COGLB, GHASC, GINAG, GNBSB, IRNMI, LBRLC, NGAMN, RWAMN, SDNBT, SSDMN, SWZMN, SYRSP, UGAMN, YEMSP, ZAFMM, ZAFMN, ZMB02");
	}
	
	@Test(description = "T008_IOTRON Data Download - Optus_IOTRON Discount Management App")
	private void T008_IOTRON_DataDownload_Optus() {
		dataDownload_IOTRON_COMMON("Optus_IOTRON Discount Management App", "AUSOP");
	}
	
	@Test(description = "T009_IOTRON Data Download - Siminn_IOTRON Discount Management App")
	private void T009_IOTRON_DataDownload_Simminn() {
		dataDownload_IOTRON_COMMON("Siminn_IOTRON Discount Management App", "AAMOW, ISLOC, ISLPS, ISLTA");
	}
	
	@Test(description = "T010_IOTRON Data Download - Spark_IOTRON Discount Management App")
	private void T010_IOTRON_DataDownload_Spark() {
		dataDownload_IOTRON_COMMON("Spark_IOTRON Discount Management App", "NZLTM");
	}
	
	@Test(description = "T011_IOTRON Data Download - Telstra_IOTRON Discount Management App")
	private void T011_IOTRON_DataDownload_Telstra() {
		dataDownload_IOTRON_COMMON("Telstra_IOTRON Discount Management App", "AUSTA");
	}
	
	@Test(description = "T012_IOTRON Data Download - TMO_IOTRON Discount Management App")
	private void T012_IOTRON_DataDownload_TMO() {
		dataDownload_IOTRON_COMMON("TMO_IOTRON Discount Management App", "USAW6");
	}
	
	@Test(description = "T013_IOTRON Data Download - Verizon_IOTRON Discount Management App")
	private void T013_IOTRON_DataDownload_Verizon() {
		dataDownload_IOTRON_COMMON("Verizon_IOTRON Discount Management App", "USAVZ");
	}
	
	@Test(description = "T014_IOTRON Data Download - Vivacom Bulgaria IOTRON Discount Management App")
	private void T014_IOTRON_DataDownload_Vivacom() {
		dataDownload_IOTRON_COMMON("Vivacom Bulgaria IOTRON Discount Management App", "BGRVA");
	}
	
	@Test(description = "T015_IOTRON Data Download - Vodafone Iceland_IOTRON Discount Management App")
	private void T015_IOTRON_DataDownload_VodafoneIceland() {
		dataDownload_IOTRON_COMMON("Vodafone Iceland_IOTRON Discount Management App", "FROKA, ISLTL, XFOKA, XSLTL");
	}

	@Test(description = "T016_IOTRON Data Download - Nova Iceland_IOTRON Discount Management App")
	private void T016_IOTRON_DataDownload_NovaIceland() {
		dataDownload_IOTRON_COMMON("Nova Iceland_IOTRON Discount Management App", "ISLNO, ISLOC");
	}
	
	@Test(description = "T017_IOTRON Data Download - Hutch Group_IOTRON Discount Management App")
	private void T017_IOTRON_DataDownload_HutchGroup() {
		dataDownload_IOTRON_COMMON("Hutch Group_IOTRON Discount Management App", "AUTCA, AUTHU, DNKHU, GBRHU, IDN89, IRLDF, IRLH3, ITAH3, ITAWI, LKAHT, SWEHU, VNMVM");
	}

}
