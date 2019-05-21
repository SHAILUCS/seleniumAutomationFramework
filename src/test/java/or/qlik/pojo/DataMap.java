package or.qlik.pojo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.csvUtil.CSVManager;
import common.customReporting.CustomReporter;
import common.seleniumExceptionHandling.CustomExceptionHandler;
import common.xlUtil.DataTable;

public class DataMap {

	/** The name of the file to be used while reporting */
	private String fileName;

	/** for storing the column header names of passsed xls */
	private Map<String,Integer> colNamesMap;

	/** for storing the row wise data of passed xls */
	private List<ArrayList<String>> rowsList;

	/**
	 * A static method, which will load the data of XLSX and store it into List
	 * @param xlsPath The abs path of XLSX file
	 * @author shailendra.rajawat 01-Apr-2019
	 */
	public void loadXls(String xlsPath){
		loadXls(xlsPath, 0);
	}

	public void loadXls(String xlsPath, int colHeaderIndex){
		rowsList = new ArrayList<ArrayList<String>>();
		colNamesMap = new HashMap<String, Integer>();

		fileName = new File(xlsPath).getName();

		DataTable xls = new DataTable(xlsPath, 0);

		for (int col = 0; col < xls.getColumnCount(colHeaderIndex); col++) {
			String colName = Util.removeSpeacialCharacters(Util.normalizeSpace(xls.getValue(colHeaderIndex,col)));
			colNamesMap.put(colName, col);
		}

		for (int row = colHeaderIndex+1; row <= xls.getLastRowIndex(); row++) {
			ArrayList<String> colData = new ArrayList<String>();
			for (int col = 0; col < xls.getColumnCount(colHeaderIndex); col++) {
				String cellTxt = xls.getValue(row, col).replaceAll("[^A-Za-z0-9._ -]", "");
				if(cellTxt.equals("") || cellTxt.equals("-")){
					cellTxt = "0";
				}
				colData.add(cellTxt);
			}
			rowsList.add(colData);
		}
		CustomReporter.report(STATUS.INFO, "Loaded " + rowsList.size() + " rows from xls [" + xlsPath + "]");
	}

	public static void main(String[] args) {
		DataMap map =  new DataMap();
		map.loadCsv("C:\\Users\\shailendra.rajawat\\git\\iotronApex5\\src\\test\\java\\or\\qlik\\pojo\\Overview_report_download_csv(4) Jan.csv");
	}

	/**
	 * A static method, which will load the data of CSV and store it into List
	 * @param csvPath The abs path of CSV file
	 * @author shailendra.rajawat 02-Apr-2019
	 */
	public void loadCsv(String csvPath){
		rowsList = new ArrayList<ArrayList<String>>();
		colNamesMap = new HashMap<String, Integer>();

		fileName = new File(csvPath).getName();

		/*
		 * Removing the extra double quotes only from [Verizon_IOTRON Discount
		 * Management App], because this csv processing is slowing down the
		 * execution
		 */
		if(csvPath.contains("Verizon_IOTRON Discount Management App")){
			processCsvToRemoveDoubleQuotes(csvPath);
		}

		CSVManager csv = new CSVManager(csvPath);

		for (int col = 0; col < csv.getColumnCount(); col++) {
			String colName = Util.removeSpeacialCharacters(Util.normalizeSpace(csv.getValue(0,col)));
			colNamesMap.put(colName, col);
		}

		for (int row = 1; row < csv.getRowCount(); row++) {
			ArrayList<String> sigleRow = new ArrayList<String>();
			for (int col = 0; col < csv.getColumnCount(); col++) {
				String cellTxt = csv.getValue(row, col).replaceAll("[^A-Za-z0-9._ -]", "");
				if(cellTxt.equals("") || cellTxt.equals("-")){
					cellTxt = "0";
				}
				sigleRow.add(cellTxt);
			}
			rowsList.add(sigleRow);
		}
		CustomReporter.report(STATUS.INFO, "Loaded " + rowsList.size() + " rows from csv [" + csvPath + "]");
	}

	/**
	 * This method will process the passed csv file, and remove the extra "(double quotes) 
	 * from the cells, because the CsvManager class was throwing below exeception. 
	 * invalid char between encapsulated token and delimiter
	 * Stack Trace :- null
	 * org.apache.commons.csv.Lexer.parseEncapsulatedToken(Lexer.java:281)
	 * @author shailendra.rajawat 17-May-2019
	 * */
	private void processCsvToRemoveDoubleQuotes(String filePath) {
		// The name of the file to open.
		//String filePath = "C:\\Users\\shailendra.rajawat\\git\\iotronApex5\\src\\test\\java\\common\\csvUtil\\data.txt";
		//String fileName = "C:\\Users\\shailendra.rajawat\\Downloads\\Overview_report_download_csv(3).csv";

		try {

			String reportOut = "";
			String reportIn = new String(Files.readAllBytes(Paths.get(filePath)));

			String[] lines = reportIn.split("\n");

			for (int num = 0; num < lines.length; num++) {

				//System.out.println("*********************************************************************************************************************");
				//System.out.println(lines[num]);
				
				//ADDING THE START MIDDLE AND END, in place of starting("), Middle(",") and Last("\n)

				String newLine = "";

				// Replacing the starting " with __SINGLE__ and Replacing the ending " with __SINGLE__\n
					
				newLine = "__SINGLE__" + lines[num].substring(1,lines[num].lastIndexOf("\""));
				newLine = newLine + "__SINGLE__" + lines[num].substring(lines[num].lastIndexOf("\"")+1, lines[num].length()) +"\n";
				
				//System.out.println(newLine);
				
				
				// Replacing all the middle "," with __MIDDLE__ 
				newLine = newLine.replaceAll("\",\"", "__MIDDLE__");

				// Now we can Removing all extra " double quote, which are causing trouble in reading the csv 
				newLine = newLine.replaceAll("\"", "");

				// putting processed line data in reportOut to rewrite the csv content
				reportOut = reportOut + newLine;
			}

			//REVERSING the above changes to get the proper csv 
			reportOut = reportOut.replaceAll("__SINGLE__", "\"");
			reportOut = reportOut.replaceAll("__MIDDLE__", "\",\"");

			// Removing the existing file 
			Util.forceDelete(filePath);
			
			// Creating the file again with processed data
			Files.write(Paths.get(filePath),reportOut.getBytes(),StandardOpenOption.CREATE);
			CustomReporter.report(STATUS.INFO, "File Recreated after removing the extra \" : " + filePath);
		}
		catch(IOException ex) {
			new CustomExceptionHandler(ex, "File path: "+filePath);
		}
	}

	/** returns the stored value of passed colName in the current object 
	 * @param colName
	 * @param singleRow
	 * @return 
	 * */
	private String getValue(String colName, ArrayList<String> singleRow) {
		colName = Util.removeSpeacialCharacters(Util.normalizeSpace(colName));
		if(colNamesMap.containsKey(colName)){
			int colIndex = colNamesMap.get(colName);
			return singleRow.get(colIndex).trim();
		}else{
			CustomReporter.report(STATUS.ERROR, "Passed column [" + colName + "] does not exist in [" + fileName + "] column Map "+colNamesMap);
			return null;
		}
	}


	/**
	 * This method will process the loaded list, and will fetch the rows 
	 * which have the exact matching data as provided in the filterMap.
	 * It performs equal check.
	 * @param filterColAndDataMap The HashMap object which contains the ColName:FilterValue
	 * @return List of filtered rows  
	 * @author shailendra.rajawat 02-Apr-2019
	 * */
	public List<ArrayList<String>> applyFilter(Map<String, String> filterColAndDataMap) {
		List<ArrayList<String>> filteredList =  new ArrayList<ArrayList<String>>();

		for (ArrayList<String> singleRow : rowsList) {

			boolean flag = true;

			for (String colName : filterColAndDataMap.keySet()) {
				String val = getValue(colName,singleRow);
				String filterVal = filterColAndDataMap.get(colName);
				if(!filterVal.equalsIgnoreCase(val)){
					flag = false;
				}
			}

			if (flag) {
				filteredList.add(singleRow);
			}

		}
		CustomReporter.report(STATUS.INFO, "Applying filter with data "+filterColAndDataMap+" on File ["+fileName+"] filtered row count: ["+filteredList.size()+"]");
		return filteredList;
	}


	/**
	 * This method returns the sum of the passed column from the whole data set
	 * i.e. it will not consider any filters
	 * @return string sum
	 * */
	public String getSum(String colName){
		return getSum(colName, rowsList);
	}

	/**
	 * This method will returns the sum of passed column from the passed filtered list
	 * @usage {@link Qlik_Data}.getSum("colNameStr", {@link Qlik_Data}.applyFilter(filterMap))
	 * @param filterColAndDataMap The HashMap object which contains the ColName:FilterValue
	 * @return List of filtered rows
	 * @author shailendra.rajawat 01-Apr-2019
	 * */
	public String getSum(String colName, List<ArrayList<String>> rowsList) {
		String sum = "0";

		for (ArrayList<String> singleRow : rowsList) {
			String cellTxt = getValue(colName, singleRow);
			if(cellTxt != null){
				sum = Util.BD(cellTxt).add(Util.BD(sum)).toString();
			}else{
				CustomReporter.report(STATUS.ERROR, "Passed Column ["+colName+"] is incorrect");
				break;
			}
		}

		CustomReporter.report(STATUS.INFO, "Sum of column ["+colName+"] = "+sum+" from File ["+fileName+"]");

		return sum;
	}

}
