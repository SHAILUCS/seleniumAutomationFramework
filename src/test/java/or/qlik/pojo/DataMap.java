package or.qlik.pojo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.csvUtil.CSVManager;
import common.customReporting.CustomReporter;
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
		rowsList = new ArrayList<ArrayList<String>>();
		colNamesMap = new HashMap<String, Integer>();
		
		fileName = new File(xlsPath).getName();
		
		DataTable xls = new DataTable(xlsPath, 0);
		
		for (int col = 0; col < xls.getColumnCount(0); col++) {
			colNamesMap.put(xls.getValue(0,col), col);
		}
		
		for (int row = 1; row < xls.getRowCount(); row++) {
			ArrayList<String> colData = new ArrayList<String>();
			for (int col = 0; col < xls.getColumnCount(0); col++) {
				colData.add(xls.getValue(row, col));
			}
			rowsList.add(colData);
		}
		CustomReporter.report(STATUS.INFO, "Loaded " + rowsList.size() + " rows from xls [" + xlsPath + "]");
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
		
		CSVManager csv = new CSVManager(csvPath);

		for (int col = 0; col < csv.getColumnCount(); col++) {
			colNamesMap.put(csv.getValue(0,col), col);
		}
		
		for (int row = 1; row < csv.getRowCount(); row++) {
			ArrayList<String> sigleRow = new ArrayList<String>();
			for (int col = 0; col < csv.getColumnCount(); col++) {
				sigleRow.add(csv.getValue(row, col));
			}
			rowsList.add(sigleRow);
		}
		CustomReporter.report(STATUS.INFO, "Loaded " + rowsList.size() + " rows from csv [" + csvPath + "]");
	}

	/** returns the stored value of passed colName in the current object 
	 * @param colName
	 * @param singleRow
	 * @return 
	 * */
	private String getValue(String colName, ArrayList<String> singleRow) {
		if(colNamesMap.containsKey(colName)){
			int colIndex = colNamesMap.get(colName);
			return singleRow.get(colIndex);
		}else{
			CustomReporter.report(STATUS.ERROR, "Passed column [" + colName + "] does not exist in [" + fileName + "]");
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
				if(!filterColAndDataMap.get(colName).equalsIgnoreCase(val)){
					flag = false;
				}
			}
			
			if (flag) {
				filteredList.add(singleRow);
			}
			
		}
		CustomReporter.report(STATUS.INFO, "Applying filter with data "+filterColAndDataMap+" on File ["+fileName+"]");
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
