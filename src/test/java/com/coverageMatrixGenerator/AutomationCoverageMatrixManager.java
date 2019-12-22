package com.coverageMatrixGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.configData_Util.Constant;
import com.xlUtil.DataTable;

public class AutomationCoverageMatrixManager {
	/**
	 * Creating a hash map for storing the Method names, and list of pages that
	 * method will visit
	 */
	private static Map<String, ArrayList<String>> methodVsPage;

	private static String testWiseSheet = "AutomationCoverageMatrix";
	private static String pageWiseSheet = "PageWise";
	private static String reportPath =Constant.getReportingHistoryFolderPath()+"/AutomationCoverageMatrix.xlsx";

	/**
	 * This method will ensure that only one instance of hash map is created and
	 * shared among all the methods
	 */
	public static Map<String, ArrayList<String>> getMethodVsPageMap() {
		if (methodVsPage == null) {
			methodVsPage = new HashMap<String, ArrayList<String>>();
		}
		return methodVsPage;
	}

	/**
	 * This method will be called from,
	 * com.seleniumExceptionHandling.SeleniumMethods.getCurrentUrl() method.
	 * and this method will put the Method Name and Visited Page list into the
	 * hash map
	 */
	public static synchronized void addPageAgainstMethodKey(String pageName, String pageUrl) {
		try{
			
			if (pageName.length()<=1) {
				return;
			}
			
			// Getting the Method name from stack trace, Only picking up the method
			// name which is inside [tests] package
			StackTraceElement[] ste = Thread.currentThread().getStackTrace();
			String methodName = "###";
			for (StackTraceElement stackTraceElement : ste) {
				String tempClassName = stackTraceElement.getClassName();
				if (tempClassName.contains("tests.")) {
					methodName = stackTraceElement.getMethodName();
					break;
				}
			}

			// Getting the [project id:page num], details from the passed url
			int equalIndex = pageUrl.indexOf("=");
			int firstColonIndex = pageUrl.indexOf(":", 5);
			if(firstColonIndex<0){
				return;
			}
			int secondColonIndex = pageUrl.indexOf(":", firstColonIndex + 1);
			if(secondColonIndex<0){
				secondColonIndex=pageUrl.length();
			}
			String pageIdentifier = pageUrl.substring(equalIndex + 1, secondColonIndex);
			String visitedPage = pageName + " " + pageIdentifier;
			
			
			// Getting the reference of methodVsPage HashMap to modify its content
			Map<String, ArrayList<String>> tempMethodVsPage = getMethodVsPageMap();

			// First checking whether the method already exist in the HashMap
			if (tempMethodVsPage.containsKey(methodName)) {
				// Method exist : then get the associated ArrayList for adding the
				// visited page details
				ArrayList<String> tempPageNames = tempMethodVsPage.get(methodName);
				// If Visited Pages Array list does not contains [Page Name
				// project:page num], then we will add the item into the list
				if (!tempPageNames.contains(visitedPage)) {
					tempPageNames.add(visitedPage);
				}
			} else {
				// Method does not exist: then create a new entry in hash map with
				// key as your method name
				// and inside that key store a new array list which will contain the
				// visited page entry

				// Creating an new ArrayList to store the visited page entry
				ArrayList<String> tempPageNames = new ArrayList<>();

				// adding the visited page entry to array list
				tempPageNames.add(visitedPage);

				// Now we have the KEY: methodName and VALUE: visited page array
				// list. We will put these together in our Hash map
				tempMethodVsPage.put(methodName, tempPageNames);
			}

		}catch(Exception ee){
			System.err.println("DATA Page Name"+pageName);
			System.err.println("DATA Page URL"+pageUrl);
			ee.printStackTrace();
		}
	}


	private static void createExcelTemplate() {
		Workbook workBookObj = null;
		Sheet sheetObj=null;
		Row row=null;
		FileOutputStream outputStream=null;

		try{
			//Creating a new sheet
			workBookObj = new XSSFWorkbook();
			sheetObj = workBookObj.createSheet(testWiseSheet);
			String cellA1 = "TestNG file", cellB1 = "Class", cellC1 = "Method", cellD1 = "Test Desc";
			row=sheetObj.createRow(0);
			row.createCell(0).setCellValue(cellA1);
			row.createCell(1).setCellValue(cellB1);
			row.createCell(2).setCellValue(cellC1);
			row.createCell(3).setCellValue(cellD1);
			outputStream=new FileOutputStream(new File(reportPath));
			workBookObj.write(outputStream);
			outputStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private static void generateTestWiseMatrix() {
		try{
			//Getting the reference of methodVsPage hash map 
			Map<String, ArrayList<String>> tempMethodVsPage = getMethodVsPageMap();

			//Getting all the keys(Tests/Method Names) present in methodVsPage hash map
			Set<String> keyList=tempMethodVsPage.keySet();

			//Getting all the unique Visited Pages from hash map and putting them in a new Set 
			Set<String> visitedPagesList_distict=new HashSet<>();
			for (String key : keyList) {
				visitedPagesList_distict.addAll(tempMethodVsPage.get(key));
			}

			//This New hash map holds the key(page name) and the value(index number - What column it points on the excel file) 
			Map<String, Integer> visitedPage_ColIndex = new HashMap<>();

			//Creating an object of AutomationCoverageMatrix excel file
			DataTable writesheet = new DataTable(reportPath, testWiseSheet);
			int startColumnIndex = 4;
			int lastColumnIndex = writesheet.getColumnCount(0)-1;

			// Code to Check if the newly visited page already exist in first
			// row of excel, if it already exist then get its column index and preserve it in
			// visitedPage_ColIndex hash map for later use
			for (int colIndex = startColumnIndex; colIndex <= lastColumnIndex; colIndex++) {
				String sheet_VisitedPage=writesheet.getValue(0, colIndex);
				if(visitedPagesList_distict.contains(sheet_VisitedPage)){
					visitedPage_ColIndex.put(sheet_VisitedPage, colIndex);
					visitedPagesList_distict.remove(sheet_VisitedPage);
				}
			}

			// The pages which are not present in the excel will be added in the
			// 1st row of sheet column wise, and again preserving their indexes in
			// visitedPage_ColIndex hash map for later use
			for (String visitedPage : visitedPagesList_distict) {
				int columnCount = writesheet.getColumnCount(0);
				writesheet.setValue(0, columnCount, visitedPage);
				visitedPage_ColIndex.put(visitedPage, columnCount);
			}

			//This New hash map holds the key(test/method name) and the value(index number - what Row it points on the excel file) 
			Map<String, Integer> testName_RowIndex = new HashMap<>();

			// Code to Check if the newly executed test/method name already exist in
			// 3rd column of excel, if it already exist then get its row index and
			// preserve it in testName_RowIndex hash map for later use
			int lastRowIndex = writesheet.getRowCount()-1;
			for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {
				String sheet_TestName=writesheet.getValue(rowIndex,2);
				if(keyList.contains(sheet_TestName)){
					testName_RowIndex.put(sheet_TestName, rowIndex);
					keyList.remove(sheet_TestName);
				}
			}

			// The test/method names which are not present in the excel 3rd column
			// will be added in the sheet row wise, and again preserving its index
			// in testName_RowIndex hash map for later use 
			for (String testName : keyList) {
				int rowCount = writesheet.getRowCount();
				writesheet.setValue(rowCount, 2, testName);
				testName_RowIndex.put(testName, rowCount);
			}


			// Now we have row and col Indexes of our test vs pages.
			// Traversing through the tempMethodVsPage hash map and filling the
			// excel matrix with 'x'
			for (String key : tempMethodVsPage.keySet()) {
				List<String> visitedPagesList=tempMethodVsPage.get(key);
				int rowIndex=testName_RowIndex.get(key);
				for (String pageName : visitedPagesList) {
					int colIndex=visitedPage_ColIndex.get(pageName);
					writesheet.setValue(rowIndex, colIndex, "x");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method reads AutomationCoverageMatrix sheet and stores the test vs page data,
	 * Then it will go to PageWise sheet to put x against the existing page and test, 
	 * or if the test is not available, it will add it in the next available column
	 * and put x on the page row. 
	 * */
	private static void generatePageWiseMatrix() {
		System.out.println("STARTED generatePageWiseMatrix");
		try{
			Map<String, ArrayList<String>> pageVsMethod=new HashMap<String, ArrayList<String>>();

			DataTable readsheet = new DataTable(reportPath, testWiseSheet);

			// Code to get the page name and the list of methods that have visited that page 
			// then putting it in pageVsMethod hashmap for later use
			int startColumnIndex = 5;
			int lastColumnIndex = readsheet.getColumnCount(0)-1;
			int lastRowIndex = readsheet.getRowCount()-1;

			ArrayList<String> methodList=null;
			for (int col = startColumnIndex; col <= lastColumnIndex; col++) {
				String pageName_sheet=readsheet.getValue(0, col);
				methodList=new ArrayList<String>();
				for (int row = 1; row <= lastRowIndex; row++) {
					String flag=readsheet.getValue(row, col);
					if (flag.equalsIgnoreCase("x")) {
						String testName=readsheet.getValue(row, 2);
						methodList.add(testName);
					}
				}
				pageVsMethod.put(pageName_sheet, methodList);
			}

			//readsheet.close();

			//This New hash map holds the key(page name) and the value(index number - What column it points on the excel file) 
			Map<String, Integer> testName_ColIndex_write = new HashMap<>();

			//Creating an object of excel file
			DataTable writesheet = new DataTable(reportPath, pageWiseSheet);
			startColumnIndex = 5;
			lastColumnIndex = writesheet.getColumnCount(0)-1;

			// Code to Check if the test already exist in first
			// row of excel, if it already exist then get its column index and preserve it in
			// testName_ColIndex_write hash map for later use
			for (int colIndex = startColumnIndex; colIndex <= lastColumnIndex; colIndex++) {
				String sheet_testName=writesheet.getValue(0, colIndex);
				testName_ColIndex_write.put(sheet_testName, colIndex);
			}

			//This New hash map holds the key(test/method name) and the value(index number - what Row it points on the excel file) 
			Map<String, Integer> pageName_RowIndex_write = new HashMap<>();

			// Code to Check if the page name already exist in
			// 3rd column of excel, if it already exist then get its row index and
			// preserve it in pageName_RowIndex_write hash map for later use
			lastRowIndex = writesheet.getRowCount()-1;
			for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {
				String sheet_TestName=writesheet.getValue(rowIndex,3);
				pageName_RowIndex_write.put(sheet_TestName, rowIndex);
			}

			//MAIN x putting code
			// This code will first get the list of pages from pageVsMethod<pageName, testList> hashmap
			// then it will loop through each page and get its testList
			// And it will then try to find the row index of page from pageName_RowIndex_write
			// And it will then try to find the col index of test from testName_ColIndex_write
			// Then it will put x against the row and col index
			// In case the test is not found in the header row then it will be added at the last col num
			// And In case the page is not found in the 3rd col then it will be added at the last row num
			Set<String> pageNames=pageVsMethod.keySet();
			int pageCount=1;
			int pageTotal=pageNames.size();
			for (String page : pageNames) {
				if(page.length()>1){
					System.out.print("\n|"+pageCount+++"/"+pageTotal  +" | "+ page+"\n");
					ArrayList<String> testList=pageVsMethod.get(page);
					
					int rowIndex=-1;
					if(pageName_RowIndex_write.containsKey(page)){
						rowIndex=pageName_RowIndex_write.get(page);	
					}else{
						rowIndex = writesheet.getRowCount();
						writesheet.setValue(rowIndex, 3, page);
						pageName_RowIndex_write.put(page, rowIndex);
						System.out.println("	| NEW rowIndex "+rowIndex+" page "+page);
					}
					
					int testCount=1;
					int testTotal=testList.size();
					for (String test : testList) {
						System.out.println("	| "+testCount+++"/"+testTotal  +" | "+test);
						if(!test.contains("###")){
							int colIndex=-1;
							if(testName_ColIndex_write.containsKey(test)){
								colIndex=testName_ColIndex_write.get(test);
							}else{
								colIndex = writesheet.getColumnCount(0);
								writesheet.setValue(0, colIndex, test);
								testName_ColIndex_write.put(test, colIndex);
								System.out.println("	| NEW colIndex "+colIndex+" test "+test);
							}
							writesheet.setValue(rowIndex, colIndex, "x");
						}
					}
				}
			}


		}catch(Exception e){
			e.printStackTrace();
		}


		System.out.println("ENDED generatePageWiseMatrix");

	}


	public static void generateAutomationCoverageMatrixReport(){
		System.out.println("===============================================================================");
		System.out.println("manage Automation Coverage Matrix Report STARTED "+new Date());

		//Checking if the excel file is not already present then we will create a new file
		File f=new File(reportPath);
		if (!f.exists()) {
			createExcelTemplate();	
		}

		generateTestWiseMatrix();

		System.out.println("manage Automation Coverage Matrix Report ENDED "+new Date());
		System.out.println("===============================================================================");
	}



	public static void main(String[] args) {
		generatePageWiseMatrix();
		/*AutomationCoverageMatrix_Tester test=new AutomationCoverageMatrix_Tester();
		test.Test1();
		test.Test2();
		test.Test3();
		test.Test4();
		test.Test5();
		generateAutomationCoverageMatrixReport();*/
	}
}
