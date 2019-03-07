package common.configData_Util;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import common.customReporting.CustomReporter;
import common.customReporting.snapshot.SnapshotManager;
import common.seleniumExceptionHandling.CustomExceptionHandler;
import objectRepository.common.Country;
import objectRepository.common.Currency;
import objectRepository.common.EventType;
import objectRepository.common.ServiceType;
import objectRepository.common.TADIG;
import objectRepository.common.TrafficDirection;

public class Util {

	public static void killExcelProcess() {
		try{
			String platform=System.getProperty("os.name");
			if(platform.toLowerCase().contains("win")){
				System.out.println("Killing Excel Process on "+platform);
				Runtime.getRuntime().exec("cmd /c taskkill /f /im EXCEL.EXE");
			}
		}catch(Exception e){
			new CustomExceptionHandler(e);
		}
	}
	
	/**Converts the provided String value to passed format(DD/MM/YYYY) date*/
	public static Date convertToDate(String format,String stringDate){
		try {
			return new SimpleDateFormat(format).parse(stringDate);
		} catch (ParseException e) {
			new CustomExceptionHandler(e,"Passed Data, format: '"+format +"' and date: '"+stringDate+"'");
			return null;
		}
	}

	/**Converts the provided Date to String*/
	public static String convertToString(String format,Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	/**Converts the string 0118 to passed format date(01-01-2018)*/
	public static String getFirstDay(String format,String mmYY){
		SimpleDateFormat form = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH,Integer.parseInt(mmYY.substring(0, 2))-1);
		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.set(Calendar.YEAR,Integer.parseInt("20"+mmYY.substring(2, 4)));
		return form.format(cal.getTime());
	}

	/**Converts the string 0118 to passed format date(31-01-2018)*/
	public static String getLastDay(String format,String mmYY){
		SimpleDateFormat form = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH,Integer.parseInt(mmYY.substring(0, 2))-1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		String currentYear=cal.get(Calendar.YEAR)+"";
		cal.set(Calendar.YEAR,Integer.parseInt(currentYear.substring(0, 2)+mmYY.substring(2, 4)));
		return form.format(cal.getTime());
	}

	/**Gives you the time stamp in dd_MMM_yyyy_HH_mm_ss_S format*/
	public static String getTimeStamp_InMilliSec(){
		//return (new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss_S")).format(new Date());
		return new Date().getTime()+"";
	}

	/**Converts the passed millisecond value to hh:mm:ss.sss format*/
	public static String convertToHHMMSS(long millis) {
		String hhmmss="";
		try{
			DateFormat formatter = new SimpleDateFormat("hh:mm:ss.sss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(millis);
			hhmmss=formatter.format(calendar.getTime());
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return hhmmss;
	}

	/**
	 * Converts the passed millisecond value to hh:mm:ss.sss format, you need to
	 * pass the difference of two time stamps only
	 */
	public static String timeConversion(long milliseconds) {
		int seconds=(int)milliseconds/1000;
		double sss=(double)milliseconds/1000-seconds;
		
		String sssInString=".000"; 
		try{
			sssInString=(sss+"").substring(1, 5);
		}catch (Exception e) {}
		
		final int MINUTES_IN_AN_HOUR = 60;
		final int SECONDS_IN_A_MINUTE = 60;
		int minutes = (int) (seconds / SECONDS_IN_A_MINUTE);
		seconds -= minutes * SECONDS_IN_A_MINUTE;
		int hours = minutes / MINUTES_IN_AN_HOUR;
		minutes -= hours * MINUTES_IN_AN_HOUR;
		return prefixZeroToDigit(hours) + ":" + prefixZeroToDigit(minutes) + ":" + prefixZeroToDigit((int)seconds)+sssInString;
	}

	/** private method to be used by {@link timeConversion} method*/
	private static String prefixZeroToDigit(int num){
		int number=num;
		if(number<=9){
			String sNumber="0"+number;
			return sNumber;
		}
		else
			return ""+number;
	}

	/** will remove the commas from passed string value, (12,000 will be converted to 12000)*/
	public static String removeCommas(String val){
		if(val!=null){
			if(val.trim().equals("-")){
				val="0";
			}
			
			if(val.contains(",")){
				val=val.replaceAll(",", "");
			}
		}
		return val;
	}

	/** Gives you object of BigDecimal for the passed string number, it is used to perform calculation on big digit numbers*/
	public static BigDecimal BD(String ip){
		BigDecimal bd=null;
		try {
			bd=new BigDecimal(removeCommas(ip));
		} catch (Exception e) {
			new CustomExceptionHandler(e,"Passed Data "+ip);
		}
		return bd;
	}

	/** This method will round your big string number to the passed precision Round(12.1122,2) will return 12.11*/
	public static String Round(String ip,int precision){
		BigDecimal bd=null;
		String val="0";
		try {
			bd=new BigDecimal(removeCommas(ip));
			val=bd.setScale(precision, RoundingMode.HALF_EVEN).toPlainString();
		} catch (Exception e) {
			new CustomExceptionHandler(e,"Passed Data "+ip);
		}
		return val;
	}

	/** 
	 * This method will return you the absolute file path of the 
	 * downloaded file whose name contains the passed value
	 * 
	 * Also wait for Constant.wait time, if the file not found 
	 * within the time then method will return null after timeout
	 * */
	public static String getDownloadedFilePath(String fileNameContains) {
		return getDownloadedFilePath(fileNameContains, Constant.wait);
	}


	/** 
	 * This method will return you the absolute file path of the 
	 * downloaded file whose name contains the passed value
	 * 
	 * Also wait for passed time, if the file not found 
	 * within the time then method will return null after timeout
	 * */
	public static String getDownloadedFilePath(String fileNameContains,long timeOutInSec) {
		String filePath=null;
		try{
			String folderPath=Constant.getDownloadsPath();
			File dir= new File(folderPath);
			if (dir.isDirectory()) {
				boolean fileFound = true;
				boolean timeOut = true;
				long timeOutTimeCounter= timeOutInSec;
				do {
					//System.out.println(timeOutTime);
					File[] children = dir.listFiles();
					for (int i = 0; i < children.length; i++) { 
						if (!children[i].getName().contains(".crdownload")) {
							if (children[i].isFile()) {
								if (children[i].getName().contains(fileNameContains)) { 
									String currentTestName=SnapshotManager.getSnapshotDestinationDirectory();
									
									FileUtils.copyFileToDirectory(children[i], new File(children[i].getParent()+"/"+currentTestName));
									FileUtils.forceDelete(children[i]);
									filePath=folderPath+"/"+currentTestName+"/"+children[i].getName();
									fileFound=false;
									break;
								} 
							}
						}
					}
					timeOutTimeCounter--;
					if (timeOutTimeCounter>0) {
						Thread.sleep(1000);
					}else{
						timeOut=false;
						CustomReporter.report(STATUS.INFO, "TIMEOUT FILE ["+fileNameContains+"] NOT FOUND AFTER WAITING FOR '"+timeOutInSec+"' SECONDS");
					}
				} while (fileFound && timeOut);
			} 		
		}catch (Exception e) {
			new CustomExceptionHandler(e,"Passed Data, fileNameContains: '"+fileNameContains+"'");
		}
		return filePath;
	}
	
	
	public static void verifyFile_Downloaded(String fileName,String desc) {
		String path=Util.getDownloadedFilePath(fileName);
		if (path!=null) {
			CustomReporter.report(STATUS.PASS, desc+" Downloaded "+path);
		} else {
			CustomReporter.report(STATUS.FAIL, desc+" Download failed");
		}
	}

	/** This method will delete all the contents of the passed directory, but will skip the folder which is passed in skipDirectory variable*/
	public static boolean deleteFolderContentRecursively(File dir,String skipDirectory) {
		boolean bool=false;
		try{
			if (dir.isDirectory()) { 
				File[] children = dir.listFiles();
				for (int i = 0; i < children.length; i++) { 
					boolean success = deleteFolderContentRecursively(children[i],skipDirectory);
					if (!success) { 
						return false; 
					} 
				} 
			} 
			// either file or an empty directory
			if(skipDirectory!=null && dir.getName().equals(skipDirectory)){
				//System.out.println("Skipping file or directory : " + dir.getName());
				bool=true;
			}else{
				//System.out.println("Removing file or directory : " + dir.getName());
				bool=dir.delete();
			}
		}catch (Exception e) {
			new CustomExceptionHandler(e,"Passed Data, dir: '"+dir+"' skipDirectory: '"+skipDirectory+"'");
		}
		return bool;
	}

	/** This method will only delete the file present on the passed absolute path*/
	public static synchronized boolean deleteSpecificFile(String absoluteFilePath) {
		boolean bool=false;
		try{
			File file=new File(absoluteFilePath);
			FileUtils.forceDelete(file);
			CustomReporter.report(STATUS.PASS,"File Deleted: "+absoluteFilePath);
		}catch (Exception e) {
			CustomReporter.report(STATUS.INFO,"Passed Data, absoluteFilePath: '"+absoluteFilePath+"'");
			new CustomExceptionHandler(e);
		}
		return bool;
	}

	/**"Displayed Value of [<b>"+objectAndpageDesc+"</b>] : '" + onPage + "' has matched with expected value: '"+ expected +"'"*/
	public static void comparator_PageValues(String expected, String onPage, String objectAndpageDesc) {
		String exp=Util.removeCommas(expected).toLowerCase();
		String act=Util.removeCommas(onPage).toLowerCase();
		if(act.contains(exp)){
			CustomReporter.report(STATUS.PASS, "Displayed Value of [<b>"+objectAndpageDesc+"</b>] : '<b>" + onPage + "</b>' has matched with expected value: '<b>"+ expected +"</b>'");
		}else{
			CustomReporter.report(STATUS.FAIL,"Displayed Value of [<b>"+objectAndpageDesc+"</b>] : '<b>" + onPage + "</b>' has failed to match with expected value: '<b>"+ expected +"</b>'"); 
		}
	}

	/**"Displayed Value of [<b>"+comparingObject+"</b>] on [<b>"+page1Name+"</b>] : '" + page1Val + "' has matched with value of: [<b>"+page2Name+"</b>] : '" + page2Val +"'"*/
	public static void comparator_PageValues(String comparingObject,String page1Name, String page1Val, String page2Name, String page2Val) {
		page1Val=Util.removeCommas(page1Val);
		if(page1Val.equals("-")){
			page1Val="0";
		}
		
		page2Val=Util.removeCommas(page2Val);
		if(page2Val.equals("-")){
			page2Val="0";
		}
		
		if(compare(page1Val,page2Val)){
			CustomReporter.report(STATUS.PASS, "Displayed Value of [<b>"+comparingObject+"</b>] on [<b>"+page1Name+"</b>] : '<b>" + page1Val + "</b>' has matched with value of: [<b>"+page2Name+"</b>] : '<b>" + page2Val +"</b>'");
		}else{
			CustomReporter.report(STATUS.FAIL, "Displayed Value of [<b>"+comparingObject+"</b>] on [<b>"+page1Name+"</b>] : '<b>" + page1Val + "</b>' has NOT matched with value of: [<b>"+page2Name+"</b>] : '<b>" + page2Val +"</b>'");
		}
	}

	/**"Displayed Value of [<b>"+comparingObject+"</b>] on [<b>"+page1Name+"</b>] : '" + page1Val + "' has matched with value of: [<b>"+page2Name+"</b>] : '" + page2Val +"'"*/
	public static void comparator_NonNumbers(String comparingObject,String page1Name, String page1Val, String page2Name, String page2Val) {
		if(page1Val.toLowerCase().contains(page2Val.toLowerCase()) || page2Val.toLowerCase().contains(page1Val.toLowerCase())){
			CustomReporter.report(STATUS.PASS, "Displayed Value of [<b>"+comparingObject+"</b>] on [<b>"+page1Name+"</b>] : '<b>" + page1Val + "</b>' has matched with value of: [<b>"+page2Name+"</b>] : '<b>" + page2Val +"</b>'");
		}else{
			CustomReporter.report(STATUS.FAIL, "Displayed Value of [<b>"+comparingObject+"</b>] on [<b>"+page1Name+"</b>] : '<b>" + page1Val + "</b>' has NOT matched with value of: [<b>"+page2Name+"</b>] : '<b>" + page2Val +"</b>'");
		}
	}
	
	/** Private method for performing the comparison logic - on numbers only*/
	private static boolean compare(String d1,String d2) {
		try{
			d1=(d1.trim().equals("-"))?"0":d1.replaceAll("[^0-9.-]", "");
			d2=(d2.trim().equals("-"))?"0":d2.replaceAll("[^0-9.-]", "");
			double n1=Double.parseDouble(d1);
			double n2=Double.parseDouble(d2);
			/*
			 if((100d>=n1 && n1>=0d) && (100d>=n2 && n2>=0d)){
			 	if(d1.toString().contains(d2.toString()) || d2.toString().contains(d1.toString())){
					return true;
				}
			}else if((-1d>=n1 && n1>=-100d) && (-1d>=n2 && n2>=-100d)){
				if(d1.toString().contains(d2.toString()) || d2.toString().contains(d1.toString())){
				return true;
				}
			}*/

			// If both numbers are between 1 and -1, then we will Round up both
			// numbers to 2 Decimal places and check for equality
			/*
			 * Num1			Num2		RoundUpValue1	RoundUpValue2	Equality Comparison Result
			 *	0.123456	0.213455	0.12			0.21				Fail
			 *	0.939100	-0.939100	0.94			-0.94				Fail
			 *	-1			1			-1.00			1.00				Fail
			 *	0.939100	1			0.94			1.00				Fail
			 *	0.124456	0.119965	0.12			0.12				Pass
			 * */
			if((1d>=n1 && n1>=-1d) && (1d>=n2 && n2>=-1d)){
				BigDecimal bd1=new BigDecimal(d1);
				BigDecimal bd2=new BigDecimal(d2);
				d1 = bd1.setScale(2, RoundingMode.HALF_UP).toPlainString();
				d2 = bd2.setScale(2, RoundingMode.HALF_UP).toPlainString();
				if(d1.equals(d2)){
					return true;
				}    
			}else{
				// If both numbers are greater than 1, and lesser than -1. 
				// Then we will assume that the difference of 5 in both numbers is OK
				/*
				*	n1	n2	Difference(n1-n2)	-5 <= Difference <= 5
				*	65	75		-10					Fail
				*	98	-98		196					Fail
				*	98	95		3					Pass
				*	95	98		-3					Pass
				*	95	-100	-5					Pass
				*	105	100		5					Pass
				* */
				double temp=n1-n2;
				if(5d>=temp && -5d<=temp){
					return true;
				}
			}
		}catch (Exception e) {
			new CustomExceptionHandler(e,"Passed Data, d1: '"+d1+"'"+" d2: '"+d2+"'");
		}
		return false;
	}

	/**"Expected value of [<b>"+desc+"</b>] : '<b>" + expected + "</b>' and on Page value displayed is '<b>"+onPage +"</b>'"*/
	public static boolean comparator_NonNumbers(String expected, String onPage, String desc) {
		if(expected.toLowerCase().contains(onPage.toLowerCase()) || onPage.toLowerCase().contains(expected.toLowerCase())){
			CustomReporter.report(STATUS.PASS, "Expected value of [<b>"+desc+"</b>] : '<b>" + expected + "</b>' and on Page value displayed is '<b>"+onPage +"</b>'");
			return true;
		}else{
			CustomReporter.report(STATUS.FAIL,"Expected value of [<b>"+desc+"</b>] : '<b>" + expected + "</b>' and on Page value displayed is '<b>"+onPage +"</b>'");
			return false;
		}
	}


	/**"Calulated value of [<b>"+desc+"</b>] : '" + calculated + "' and on Page value displayed is '"+onPage +"'"*/
	public static void comparator(String calculated, String onPage, String desc) {

		if(onPage.equals("-")){
			onPage=onPage.replace("-", "0");
		}
		
		if(compare(Util.removeCommas(calculated),Util.removeCommas(onPage))){
			CustomReporter.report(STATUS.PASS, "Calculated value of [<b>"+desc+"</b>] : '<b>" + calculated + "</b>' and on Page value displayed is '<b>"+onPage +"</b>'");
		}else{
			CustomReporter.report(STATUS.FAIL,"Calculated value of [<b>"+desc+"</b>] : '<b>" + calculated + "</b>' and on Page value displayed is '<b>"+onPage +"</b>'");
		}

	}

	/** 
	 * Does case insensitive matching, of shuttle values
	 * */
	public static void comparator_List_NonNumbers(List<String> expectedStrings, List<String> onPage, String desc) {
		boolean flag1=true;
		boolean flag2=true;
		Collections.sort(expectedStrings);
		Collections.sort(onPage);
		for (int i = 0; i < onPage.size(); i++) {
			String tempColName=onPage.get(i);
			if (!expectedStrings.contains(tempColName)) {
				CustomReporter.report(STATUS.FAIL, "Item displayed on Page [<b>"+tempColName+"</b>] does NOT Exist in the expected list");
				flag1=false;
			}
		}

		for (int i = 0; i < expectedStrings.size(); i++) {
			String tempColName=expectedStrings.get(i);
			if (!onPage.contains(tempColName)) {
				CustomReporter.report(STATUS.FAIL, "Item Existing in the expected list [<b>"+tempColName+"</b>] does NOT displayed on Page");
				flag2=false;
			}
		}


		if (flag1 && flag2) {
			CustomReporter.report(STATUS.PASS, "All values of [<b>"+desc+"</b>] matched with : "+expectedStrings);
		}

	}

	/** Does only case sensitive matching
	 * "Values of [<b>"+desc+"</b>] are properly displayed as "+expected*/
	public static void comparator_List(List<String> calculatedOrExpected, List<String> onPage, String desc) {
		try{
			if(onPage.containsAll(calculatedOrExpected)){
				CustomReporter.report(STATUS.PASS, "Values of [<b>"+desc+"</b>] are properly displayed as <b>"+calculatedOrExpected+"</b>");
			} else {
				boolean flag=true;
				for (int i = 0; i < onPage.size(); i++) {
					if (!compare(onPage.get(i), calculatedOrExpected.get(i))) {
						flag = false;
						CustomReporter.report(STATUS.FAIL, "Values of [<b>"+desc+"</b>] are not matching expected: <b>"+calculatedOrExpected.get(i)+"</b> actual: <b>"+onPage.get(i)+"</b>");
					}
				}

				if (flag) {
					CustomReporter.report(STATUS.PASS, "Values of [<b>"+desc+"</b>] are properly displayed as <b>"+calculatedOrExpected+"</b>");
				}
			}
		}catch (Exception e) {
			new CustomExceptionHandler(e,"Values of [<b>"+desc+"</b>] are not matching expected: <b>"+calculatedOrExpected+"</b> actual: <b>"+onPage+"</b>");
		}
	}


	/** below methos will return array object of the passed object sequence*/
	public static String[] getArray(String... strVal) {
		return strVal;
	}

	public static TADIG[] getArray(TADIG... TADIG) {
		return TADIG;
	}

	public static Country[] getArray(Country... Country) {
		return Country;
	}

	public static Currency[] getArray(Currency... Currency) {
		return Currency;
	}

	public static EventType[] getArray(EventType... EventType) {
		return EventType;
	}

	public static ServiceType[] getArray(ServiceType... ServiceType) {
		return ServiceType;
	}

	public static TrafficDirection[] getArray(TrafficDirection... TrafficDirection) {
		return TrafficDirection;
	}

	public static List<String> getList(String... values) {
		return new ArrayList<>(Arrays.asList(values));
	}
}
