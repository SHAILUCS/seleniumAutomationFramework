/**
 * QlikAppOverview.java
 * When User select an App from Hub >> Streams, 
 * this page is opened in a new TAB which shows multiple sheets
 * */
package or.qlik;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.qlik.pojo.DataMap;
import or.qlik.pojo.QlikGRID;
import tests.qlik.QlikAppTests;

public class QlikAppOverview {
	private SeleniumMethods com;
	private QlikCommon qcom;
	public static String title = "App overview - Qlik Sense";

	public QlikAppOverview() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		qcom = new QlikCommon();
	}
	
	@FindBy(xpath = "//button[@title='Confirm selection']")
	private WebElement button_Filter_ConfirmSelection;

	@FindBy(xpath = "//a[@title='Close full screen']")
	private WebElement button_Filter_CloseFullScreen;

	/**
	 * Use this method as a chain to apply filter across multiple sheets
	 * This should be the first method in the chain
	 * @param button_Filter_ the object of Filter Button, whose menu needs to be opened
	 * */
	private QlikAppOverview openFilterMenu(String filterName){
		com.click_UsingAction(By.xpath("//div[@ng-class='wrapperClassList'][contains(.,'" + filterName + "')]//a"),filterName +" filter icon");
		com.wait(3);
		return this;
	}

		

	/**
	 * This method will perform the search with passed data in the provided 
	 * filter section once the data is found, it will select that. 
	 * Use this method as a chain to select multiple data in the filter popup
	 * This should be the second and second-last method in the chain
	 * @param colonSeparatedfilterSectionAndData the Text that needs to be selected such as Curreny:SDR
	 * */
	private QlikAppOverview selectFilter(String colonSeparatedfilterSectionAndData){


		String separator = ":";
		String filterSection = colonSeparatedfilterSectionAndData.split(separator)[0].trim();
		String filterData = colonSeparatedfilterSectionAndData.split(separator)[1].trim();

		By filterInput = By.xpath("//div[contains(.,'"+filterSection+"')][contains(@class,'filterpane')]//input");
		
		// Logic to try to make the filterInput object visible(max 5 attempts) 
		for (int i = 1; i <= 5; i++) {
			com.click_UsingAction(By.xpath("//h1[contains(.,'"+filterSection+"')]/a"));
			if(com.getHeight(filterInput,1) != 0)
			{
				com.sendKeys(filterInput, filterData);
				com.wait(1);
				break;
			}
		}
		
		By filterObj = By.xpath("//li[contains(.,'" + filterData + "')][@tid='listbox.item']");
		String classData = com.getAttribute(filterObj, "class");
		if(!classData.toLowerCase().contains("selected")){
			com.click_UsingAction(filterObj);
		}else{
			com.click_UsingAction(By.cssSelector("button[title='Cancel selection']"));
		}

		if(!com.waitForElementTobe_NotVisible(button_Filter_ConfirmSelection,1)){
			com.click_UsingAction(button_Filter_ConfirmSelection);
		}

		CustomReporter.report(STATUS.PASS, "Filter applied with data ["+colonSeparatedfilterSectionAndData+"]");
		return this;
	}


	/**
	 * Use this method as a chain to close the filter popup, once all filter data is selected
	 * This should be the last method in the chain
	 * */
	private QlikAppOverview closeFilter(){
		com.click_UsingAction(button_Filter_CloseFullScreen);
		com.waitForElementsTobe_NotVisible(qcom.icon_PageLoadingIndicator_Rain);
		return this;
	}

	/**
	 * Opens Sheet or Dashboard with the passed Name
	 * */
	public void openSheet(String sheetName) {
		CustomReporter.createNode("OPENING THE [" + sheetName + "] SHEET/DASHBOARD");
		if(com.waitForElementsTobe_NotVisible(qcom.icon_PageLoadingIndicator_Rain)){
			By sheetObj = By.xpath("//div[@class='qv-content-item'][contains(.,'" + sheetName + "')]/div[contains(@class,'thumb')]"); 

			// In case some other sheet is displayed then opening the Sheet navigator Popup
			if(!com.waitForElementTobe_NotVisible(qcom.button_SheetNavigatorButton,2)){
				com.click_UsingAction(qcom.button_SheetNavigatorButton, "Sheet Navigator Button");
				com.wait(1);
			}

			/* waiting till the width/height of the object is more than 0 */
			com.waitForElementTobe_Clickable(sheetObj);
			com.waitForElementsTobe_NotVisible(qcom.icon_PageLoadingIndicator_Rain);

			com.click_UsingAction(sheetObj,sheetName + " link");
			com.wait(1);
			com.waitForElementsTobe_NotVisible(qcom.icon_PageLoadingIndicator_Rain);
		}
	}


	/**
	 * This method downloads the xlsx for the passed data visualization snapshot view
	 * Once the file is downloaded to the Download/TestName folder, this method will 
	 * rename the file to the passed dataVisualizationSnapshotHeading(by removing all special chars ofcourse)
	 * @param Heading of the snapshot/view/sheet on the App Overview Page 
	 * @author shailendra.rajawat 27-Mar-2019
	 * */
	private static synchronized void exportDataForPassedChart(String chartTableOrKpiHeading) {
		SeleniumMethods com = new SeleniumMethods();
		QlikCommon qcom = new QlikCommon();
		
		CustomReporter.createNode("Exporting the chart [" + chartTableOrKpiHeading + "] data in XLSX");
		By chartTableOrKpiObj = By.xpath("//div[contains(.,'"+chartTableOrKpiHeading+"')][@class='qv-object-title-text ng-binding ng-scope']");
		com.contextClick_UsingAction(chartTableOrKpiObj);

		com.waitForElementTobe_Visible(qcom.button_Export);
		com.click_UsingAction(qcom.button_Export);

		com.waitForElementTobe_Visible(qcom.button_ExportData);
		com.click_UsingAction(qcom.button_ExportData);

		if(com.waitForElementTobe_Visible(qcom.heading_ExportCompletePopup)){
			CustomReporter.report(STATUS.INFO, "Export Complete Popup displayed");
			com.click_UsingAction(qcom.link_ClickHereToDownloadYourDataFile);
			com.wait(5);
			com.click_UsingAction(qcom.button_Close_ExportCompletePopup);
		}
	}




	/**
	 * This method is taking care of remove + apply filter on passed sheet
	 * then download the data to the specific folder.
	 * then call the method which take care of the verification of data 
	 * with IOTRON Overview Report csv month wise 
	 * @param methodName The name of method which maps with the json file
	 * @param appName the name of the app for which this data verification process is carried out and also decides the folder structure
	 * @param sheet The Name of the Sheet, which maps with json test data file and also decides the folder structure
	 * @param obIb The data needs to be download in OB or IB folder
	 * @param year The year for which this data verification is carried out, its important to export view data from sheet
	 * @param months The array of months for which this data verification is carried out
	 * @param mapOR_ObIb_Path The path of the IOTRON Overview Report csv file
	 * @author shailendra.rajawat 28-Mar-2019 
	 * @param gridLine 
	 * */
	public void exportData_VerifyWithOvRep(String methodName, String appName, String sheet, String obIb, String year, String[] months, Locale locale) {

		JSONManager json = new JSONManager(QlikAppTests.jsonFilePath, methodName, sheet, obIb);

		/* Traffic Year Filter for ex: 2018 */
		applyFilter("Traffic Year", Util.getArray("Traffic Year:"+year));

		for (String month : months) {

			// Removing existing filter
			String[] removeFilterArr = json.getStrArr("removeFilterArr");
			removeFilter(removeFilterArr);

			/* Applying Filters for ex: Visitor IB/Cust OB */
			for (int i = 0; i < json.getJsonArrLength("applyFilterObjArr"); i++) {
				String filterName = json.getStr("applyFilterObjArr", i ,"filterName");
				String[] filterData =json.getStrArr("applyFilterObjArr", i ,"filterDataArr");
				/* To make this apply filter logic dynamic, I have kept only
				 * [Traffic Month:] in the "filterDataArr" of json, if this
				 * matches then appropriate month will be appended*/ 
				if(Arrays.toString(filterData).toLowerCase().contains("traffic month")){
					applyFilter(filterName, Util.getArray(filterData[0]+month));
				}else{
					applyFilter(filterName, filterData);
				}
			}

			// Exports the data on opened sheet
			String[] exportDataArr = json.getStrArr("exportDataArr");
			if(exportDataArr != null){
				for (String chart : exportDataArr) {
					
					/*// Downloading the xlsx file for passed chart in default downloads folder
					exportDataForPassedChart(chart);
					String fileName = chart+" "+month+".xlsx";
					String filePath = Util.renameDownloadedFile("xlsx",fileName);

					// Moving the downloaded xlsx file in desired dest folder 
					String destinationFolder = Constant.getQlikDownloadsPath() + "/" + appName + "/" + sheet + "/" + obIb;
					destFilePath = Util.moveFileToDirectory(filePath, destinationFolder);

					// Verifying the data with IOTRON overview report 
					verify_Data_OvRep(sheet, destFilePath, appName, obIb, year, month, locale);*/
					
					downloadDataAndVerifyWithIOTRONData(chart, month, appName, sheet, obIb, year, locale);
					
				}
			}

		}

	}
	
	
	private static synchronized void downloadDataAndVerifyWithIOTRONData(String chart, String month, String appName,
			String sheet, String obIb, String year, Locale locale) {
		/* Downloading the xlsx file for passed chart in default downloads folder */
		exportDataForPassedChart(chart);
		String fileName = chart+" "+month+".xlsx";
		String filePath = Util.renameDownloadedFile("xlsx",fileName);

		/* Moving the downloaded xlsx file in desired dest folder */
		String destinationFolder = Constant.getQlikDownloadsPath() + "/" + appName + "/" + sheet + "/" + obIb;
		String destFilePath = Util.moveFileToDirectory(filePath, destinationFolder);

		/* Verifying the data with IOTRON overview report */
		verify_Data_OvRep(sheet, destFilePath, appName, obIb, year, month, locale);
	}

	/**
	 * Takes care of the applying filter task as a whole
	 * @param filterName the name of the filter object
	 * @param filterData String array of filter data
	 * @author shailendra.rajawat 03-Apr-2019
	 * */
	public void applyFilter(String filterName, String[] filterData) {
		if(filterName!=null){
			CustomReporter.createNode("Applying [" + filterName + "] filter with data " + Arrays.toString(filterData));
			openFilterMenu(filterName);
			for (String filterTxt : filterData) {
				selectFilter(filterTxt);
			}
			closeFilter();
		}
	}

	/**
	 * This method will verify the data from the newly downloaded xls files with
	 * already downloaded Overview Report
	 * @param sheet The name of the sheet
	 * @param destFilePath file path
	 * @param obIb
	 * @param year
	 * @param months  
	 * @param mapOR_ObIb 
	 * @param locale Locale.GERMAN or can be null
	 */
	private static void verify_Data_OvRep(String sheet, String destFilePath, String appName, String obIb, String year, String month, Locale locale) {

		String fileName = new File(destFilePath).getName();
		CustomReporter.createNode("Verifying " + obIb + " sheet ["+sheet+"] >> ["+fileName+"] "+" data with Overview Report" );
	
		DataMap mapSheet = new DataMap(locale);
		mapSheet.loadXls(destFilePath);

		Map<String, String> filter_OR = new HashMap<String, String>();

		/* Path of already downloaded IOTRON Overview Report csv to be loaded into DataMap For Verifying the values */
		String overviewReportPathObIb = Constant.getQlikDownloadsPath() + "/" + appName + "/"
				+ QlikAppTests.overviewReportFolderName + "/" + obIb + "/"
				+ QlikAppTests.overViewReportFileNameWithoutExtension + " " + month
				+ QlikAppTests.overViewReportFileExtension;
		
		/* 
		 * In case you downloaded oerview report xls using the download Xls button
		 * then below logic will prevent the test from breaking 
		 * */
		   
		DataMap mapOR_ObIb = new DataMap();
		if(QlikAppTests.overViewReportFileExtension.contains("xl")){
			// In Overciew Report excel the tabular data starts from 4, thats why we have to pass 4
			mapOR_ObIb.loadXls(overviewReportPathObIb,4);	
		}else if(QlikAppTests.overViewReportFileExtension.contains("csv")){
			mapOR_ObIb.loadCsv(overviewReportPathObIb);
		}else{
			Assert.fail("Invalid extension mentioned in constant QlikAppTests.overViewReportFileExtension");
		}
		

		// for each month, apply filter and repeat the below comparison steps

		Map<String, String> filter_Sheet = new HashMap<String, String>();
		String month_OR = Util.convertToString("MMyy",Util.convertToDate("MMMyyyy", month + year));
		filter_OR.put("Traffic Period", month_OR);

		if(sheet.equalsIgnoreCase("YoY Value Performance")){
			String matchingCol = "";

			if(fileName.contains("DATA")){
				matchingCol = year+" DATA Post Disc Net";
				filter_OR.put("Service Type", "Data");
			}else if(fileName.contains("VOICE")){
				matchingCol = year+" VOICE Post Disc Net";
				filter_OR.put("Service Type", "Voice");
			}else if(fileName.contains("SMS")){
				matchingCol = year+" SMS Post Disc Net";
				filter_OR.put("Service Type", "SMS");
			}

			filter_Sheet.put("Traffic Month", month);
			String sumTrafVol = mapSheet.getSum(matchingCol, mapSheet.applyFilter(filter_Sheet));
			String sumOR = mapOR_ObIb.getSum("Total Post-Discounted Charges Excluding Tax", mapOR_ObIb.applyFilter(filter_OR));
			Util.comparator_PageValues(matchingCol +" "+ month, "YoY Value Performance", sumTrafVol, "Overview Report", sumOR);
			QlikGRID.addRow(appName, sheet, year, month, obIb, fileName, matchingCol, sumTrafVol, sumOR);

		}else if(sheet.equalsIgnoreCase("YoY Volume Performance")){
			String matchingCol = year+" Actual Traffic Vol";

			filter_Sheet.put("Traffic Month", month);
			String sumTrafVol = mapSheet.getSum(matchingCol, mapSheet.applyFilter(filter_Sheet));

			if(fileName.contains("DATA")){
				filter_OR.put("Service Type", "Data");
			}else if(fileName.contains("VOICE")){
				filter_OR.put("Service Type", "Voice");
			}else if(fileName.contains("SMS")){
				filter_OR.put("Service Type", "SMS");
			}

			String sumOR = mapOR_ObIb.getSum("Traffic Volume  Actual", mapOR_ObIb.applyFilter(filter_OR));
			Util.comparator_PageValues("Actual Traffic Volume" +" "+ month, "YoY Value Performance", sumTrafVol, "Overview Report", sumOR);
			QlikGRID.addRow(appName, sheet, year, month, obIb, fileName, matchingCol, sumTrafVol, sumOR);

		}else if(sheet.equalsIgnoreCase("Data")){

			for (String servType : Util.getArray("Voice","SMS","Data")) {

				filter_Sheet.put("Traffic Period", month+" "+year);
				filter_Sheet.put("Service Type", servType);
				filter_OR.put("Service Type", servType);
				
				String matchingCol = "Actual Volume";
				String sumActualVolume = mapSheet.getSum(matchingCol, mapSheet.applyFilter(filter_Sheet));
				String sumActualVolume_OR = mapOR_ObIb.getSum("Traffic Volume  Actual", mapOR_ObIb.applyFilter(filter_OR));
				Util.comparator_PageValues("Actual Traffic Volume " +servType+" "+ month, "YoY Value Performance", sumActualVolume, "Overview Report", sumActualVolume_OR);
				QlikGRID.addRow(appName, sheet, year, month, obIb, fileName, matchingCol + " " + servType, sumActualVolume, sumActualVolume_OR);


				matchingCol = "Charged Volume";
				String sumChargedVolume = mapSheet.getSum(matchingCol, mapSheet.applyFilter(filter_Sheet));
				String sumBilledVolume_OR = mapOR_ObIb.getSum("Traffic Volume Billed", mapOR_ObIb.applyFilter(filter_OR));
				Util.comparator_PageValues("Charged Traffic Volume " +servType+" "+ month, "YoY Value Performance", sumChargedVolume, "Overview Report", sumBilledVolume_OR);
				QlikGRID.addRow(appName, sheet, year, month, obIb, fileName, matchingCol + " " + servType, sumChargedVolume, sumBilledVolume_OR);

				matchingCol = "TAP Gross";
				String sumTAPGross = mapSheet.getSum(matchingCol, mapSheet.applyFilter(filter_Sheet));
				String sumTapGross_OR = mapOR_ObIb.getSum("Total TAP Charge Including Tax", mapOR_ObIb.applyFilter(filter_OR));
				Util.comparator_PageValues("TAP Gross " +servType+" "+ month, "YoY Value Performance", sumTAPGross, "Overview Report", sumTapGross_OR);
				QlikGRID.addRow(appName, sheet, year, month, obIb, fileName, matchingCol + " " + servType, sumTAPGross, sumTapGross_OR);

				matchingCol = "TAP Net";
				String sumTAPNet = mapSheet.getSum(matchingCol, mapSheet.applyFilter(filter_Sheet));
				String sumTapNet_OR = mapOR_ObIb.getSum("Total TAP Charges Excluding Tax", mapOR_ObIb.applyFilter(filter_OR));
				Util.comparator_PageValues("TAP Net " +servType+" "+ month, "YoY Value Performance", sumTAPNet, "Overview Report", sumTapNet_OR);
				QlikGRID.addRow(appName, sheet, year, month, obIb, fileName, matchingCol + " " + servType, sumTAPNet, sumTapNet_OR);

				matchingCol = "Post Disc Gross";
				String sumPostDiscGross = mapSheet.getSum(matchingCol, mapSheet.applyFilter(filter_Sheet));
				String sumPostDiscGross_OR = mapOR_ObIb.getSum("Total Post-Discounted Charges Including Tax", mapOR_ObIb.applyFilter(filter_OR));
				Util.comparator_PageValues("Post Discount Gross " +servType+" "+ month, "YoY Value Performance", sumPostDiscGross, "Overview Report", sumPostDiscGross_OR);
				QlikGRID.addRow(appName, sheet, year, month, obIb, fileName, matchingCol + " " + servType, sumPostDiscGross, sumPostDiscGross_OR);

				matchingCol = "Post Disc Net";
				String sumPostDiscNet = mapSheet.getSum(matchingCol, mapSheet.applyFilter(filter_Sheet));
				String sumPostDiscNet_OR = mapOR_ObIb.getSum("Total Post-Discounted Charges Excluding Tax", mapOR_ObIb.applyFilter(filter_OR));
				Util.comparator_PageValues("Post Discount Net " +servType+" "+ month, "YoY Value Performance", sumPostDiscNet, "Overview Report", sumPostDiscNet_OR);
				QlikGRID.addRow(appName, sheet, year, month, obIb, fileName, matchingCol + " " + servType, sumPostDiscNet, sumPostDiscNet_OR);

				matchingCol = "Disc Achieved Gross";
				String sumDiscAchievedGross = mapSheet.getSum(matchingCol, mapSheet.applyFilter(filter_Sheet));
				String sumDiscAchievedGross_OR = mapOR_ObIb.getSum("Credit Note Discount Achieved Including Tax", mapOR_ObIb.applyFilter(filter_OR));
				Util.comparator_PageValues("Discount Achieved Gross " +servType+" "+ month, "YoY Value Performance", sumDiscAchievedGross, "Overview Report", sumDiscAchievedGross_OR);
				QlikGRID.addRow(appName, sheet, year, month, obIb, fileName, matchingCol + " " + servType, sumDiscAchievedGross, sumDiscAchievedGross_OR);

				matchingCol = "Disc Achieved Net";
				String sumDiscAchievedNet = mapSheet.getSum(matchingCol, mapSheet.applyFilter(filter_Sheet));
				String sumDiscAchievedNet_OR = mapOR_ObIb.getSum("Credit Note Discount Achieved Excluding Tax", mapOR_ObIb.applyFilter(filter_OR));
				Util.comparator_PageValues("Discount Achieved Net " +servType+" "+ month, "YoY Value Performance", sumDiscAchievedNet, "Overview Report", sumDiscAchievedNet_OR);
				QlikGRID.addRow(appName, sheet, year, month, obIb, fileName, matchingCol + " " + servType, sumDiscAchievedNet, sumDiscAchievedNet_OR);
			}
		}

	}


	/**
	 * This method removes the applied filter
	 * @param filterText The text contains in the filter
	 * @author shailendra.rajawat 28-Mar-2019
	 * @return this object so that chain can be made to save lines
	 * */
	private QlikAppOverview removeFilter(String[] filterTextArr) {
		if(filterTextArr != null){
			CustomReporter.createNode("Removing Filter "+Arrays.toString(filterTextArr));
			for (String filterTxt : filterTextArr) {
				By filterObj = By.xpath("//div[contains(@class,'item')][contains(.,'" + filterTxt + "')]//a");
				if(!com.waitForElementsTobe_NotVisible(filterObj,1)){
					com.click_UsingAction(filterObj, "");
					com.waitForElementsTobe_NotVisible(qcom.icon_PageLoadingIndicator_Rain);
					CustomReporter.report(STATUS.PASS, "Filter with text ["+filterTxt+"] removed successfully");
				}else{
					CustomReporter.report(STATUS.INFO, "Filter with text ["+filterTxt+"] can not be removed as it is not already applied");
				}
			}
		}
		return this;
	}

}
