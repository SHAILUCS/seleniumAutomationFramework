package or.qlik.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.configData_Util.STATUS;
import common.configData_Util.Util;

public class QlikGRID {
	
	private static String stream;
	private String app;
	private String sheet;
	private static String curr;
	private String traffYear;
	private String traffMonth;
	private String obIb;
	private String sheetNameDownloaded;
	private String fieldName;
	private String qlikValue;
	private String iotronValue;
	private String difference;
	private String passFail;
	
	private static Map<Long,List<QlikGRID>> listMap;
	

	public static void addRow(String appName, String sheet, String year, String month, String obIb, String fileName, String fieldName, String qlikValue, String iotronValue){
		QlikGRID gridRow = new QlikGRID();
		gridRow.setApp(appName);
		gridRow.setSheet(sheet);
		gridRow.setTraffYear(year);
		gridRow.setTraffMonth(month);
		gridRow.setObIb(obIb);
		gridRow.setSheetNameDownloaded(fileName);
		gridRow.setFieldName(fieldName);
		gridRow.setQlikValue(qlikValue);
		gridRow.setIotronValue(iotronValue);
		addObject(gridRow);
	}
	
	private static void addObject(QlikGRID obj){
		
		if(listMap == null){
			listMap = new HashMap<Long, List<QlikGRID>>();
		}

		List<QlikGRID> list = listMap.get(Thread.currentThread().getId());
		
		if (list == null) {
			list = new ArrayList<QlikGRID>();
			listMap.put(Thread.currentThread().getId(),list);
		}
		
		list.add(obj);
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public static String getStream() {
		return stream;
	}
	public static void setStream(String stream) {
		QlikGRID.stream = stream;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public static String getCurr() {
		return curr;
	}
	public static void setCurr(String curr) {
		QlikGRID.curr = curr;
	}
	public String getTraffYear() {
		return traffYear;
	}
	public void setTraffYear(String traffYear) {
		this.traffYear = traffYear;
	}
	public String getTraffMonth() {
		return traffMonth;
	}
	public void setTraffMonth(String traffMonth) {
		this.traffMonth = traffMonth;
	}
	public String getSheet() {
		return sheet;
	}
	public void setSheet(String sheet) {
		this.sheet = sheet;
	}
	public String getObIb() {
		return obIb;
	}
	public void setObIb(String obIb) {
		this.obIb = obIb;
	}
	public String getSheetNameDownloaded() {
		return sheetNameDownloaded;
	}
	public void setSheetNameDownloaded(String sheetNameDownloaded) {
		this.sheetNameDownloaded = sheetNameDownloaded;
	}
	public String getQlikValue() {
		return qlikValue;
	}
	public void setQlikValue(String qlikValue) {
		this.qlikValue = qlikValue;
	}
	public String getIotronValue() {
		return iotronValue;
	}
	public void setIotronValue(String iotronValue) {
		this.iotronValue = iotronValue;
	}
	public String getDifference() {
		if(qlikValue==null || iotronValue==null){
			return null;
		}
		difference = Util.BD(qlikValue).subtract(Util.BD(iotronValue)).toPlainString();
		return difference;
	}

	public String getPassFail() {
		if(difference==null){
			getDifference();
		}
		
		if(Util.compareNumeric(qlikValue, iotronValue)){
			passFail = STATUS.PASS.value;
		}else{
			passFail = STATUS.FAIL.value;
		}
		return passFail;
	}

	public static String generateHTMLTable() {
		
		int rowNum = 1;
		
		String row = "<tr>"
				+ "<th>" + "Sr. No." + "</th>"
				+ "<th>" + "Stream" + "</th>"
				+ "<th>" + "App"	+ "</th>"
				+ "<th>" + "Sheet"	+ "</th>"
				+ "<th>" + "Curr"	+ "</th>"
				+ "<th>" + "Traf Year"	+ "</th>"
				+ "<th>" + "Traf Month"	+ "</th>"
				+ "<th>" + "Ob/Ib"	+ "</th>"
				+ "<th>" + "Sheet Name Downloaded"	+ "</th>"
				+ "<th>" + "Field Name"	+ "</th>"
				+ "<th>" + "Qlik Value"	+ "</th>"
				+ "<th>" + "IOTRON Value"	+ "</th>"
				+ "<th>" + "Difference"	+ "</th>"
				+ "<th>" + "Pass/Fail"	+ "</th>"
				+ "</tr>";
		
		List<QlikGRID> listTemp = listMap.get(Thread.currentThread().getId());
		
		for (QlikGRID qlikRow : listTemp) {
			row = row + 
					"<tr class='" + qlikRow.getPassFail() + "'>"
					+ "<td>" + rowNum++ + "</td>"
					+ "<td>" + getStream() + "</td>"
					+ "<td>" + qlikRow.getApp() + "</td>"
					+ "<td>" + qlikRow.getSheet() + "</td>"
					+ "<td>" + getCurr() + "</td>"
					+ "<td>" + qlikRow.getTraffYear() + "</td>"
					+ "<td>" + qlikRow.getTraffMonth() + "</td>"
					+ "<td>" + qlikRow.getObIb() + "</td>"
					+ "<td>" + qlikRow.getSheetNameDownloaded() + "</td>"
					+ "<td>" + qlikRow.getFieldName() + "</td>"
					+ "<td>" + qlikRow.getQlikValue() + "</td>"
					+ "<td>" + qlikRow.getIotronValue() + "</td>"
					+ "<td>" + qlikRow.getDifference() + "</td>"
					+ "<td>" + qlikRow.getPassFail() + "</td>"
					+ "</tr>";  
		}
		
		String htmlTable = "<table><tbody>" + row + "</tbody></table>";

		/*
		 * Removing the current thread list, because for single threaded execution,
		 * Grid has ovelapping results
		 */
		if(listMap.containsKey((Thread.currentThread().getId()))){
			listMap.remove((Thread.currentThread().getId()));
		}		
		
		return htmlTable;
	}

	public String toString() {
		return "Stream: "+stream + " | " + "App: "+app + " | " + "Sheet: "+sheet + " | " + "Curr: "+curr + " | " +
				"TraffYear: "+traffYear + " | " + "TraffMonth: "+traffMonth + " | " + "ObIb: "+obIb + " | " + 
				"SheetNameDownloaded: "+sheetNameDownloaded + " | " +"FieldName: "+fieldName + " | " + "QlikValue: "+qlikValue + " | " + "IotronValue: "+iotronValue + " | " +
				"Difference: "+difference + " | " + "PassFail: "+passFail + " | " + "ListSize: "+listMap.get(Thread.currentThread().getId()).size();
	}
	
}
