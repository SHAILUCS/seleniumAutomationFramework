package tests.SessionTimeout;

import java.io.File;

import common.configData_Util.Constant;
import common.xlUtil.DataTable;

public class PerformanceTestXlManager {
	
	static String perfTestFilePath=Constant.getTestDataFolderPath()+"/PerformanceTestReport.xlsx";
	static String perfTestSheetName="Test";
	
	public static void main(String[] args) throws InterruptedException {
		/*Date d1=new Date();
		
		Thread.sleep(65230);

		Date d2=new Date();*/
		
		long seconds=65230;//d2.getTime()-d1.getTime();
		
		final int MINUTES_IN_AN_HOUR = 60;
		final int SECONDS_IN_A_MINUTE = 60;
		int minutes = (int) (seconds / SECONDS_IN_A_MINUTE);
		seconds -= minutes * SECONDS_IN_A_MINUTE;
		int hours = minutes / MINUTES_IN_AN_HOUR;
		minutes -= hours * MINUTES_IN_AN_HOUR;
		String v= prefixZeroToDigit(hours) + ":" + prefixZeroToDigit(minutes) + ":" + prefixZeroToDigit((int)seconds);
		System.out.println(v);
	
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
	
	public static void main1(String[] args) {
		
		File f=new File(perfTestFilePath);
		
		if (f.exists()) {
			System.out.println("File is present");
		}else{
			System.out.println("File is NOT present");
			DataTable.createExcelTemplate(perfTestFilePath, perfTestSheetName, "TestName");
		}
		
		String testRunInfo="After Release 4.4.1";
		putTestData("p252 Opening IOTRON tab", "1.6", testRunInfo);

		putTestData("p301 Maintain IOT Discount Agreements with Nextgen user", "1.7", testRunInfo);
		
		putTestData("p301 Maintain IOT Discount Agreements with Nextgen user - open page - Live agreements", "1.8", testRunInfo);
		
	}
	
	private static void putTestData(String testNameData, String timeTakenData,String runInfoData) {
	
		DataTable xl=new DataTable(perfTestFilePath, perfTestSheetName);
		
		int rowCount=xl.getRowCount();
		
		int lastColumn=xl.getColumnCount(0);
		
		System.out.println(rowCount + ":" + lastColumn);
		
		boolean flag=true;
		
		int effectiveColumn=xl.getColumnHeaderNum(runInfoData);
		if (effectiveColumn==-1) {
			effectiveColumn=lastColumn;
		}
		
		xl.setValue(0, effectiveColumn, runInfoData);
		
		//Matching
		for (int row = 1; row < rowCount; row++) {
			String xlCellData=xl.getValue(row, 0);
			if (xlCellData.equalsIgnoreCase(testNameData)) {
				xl.setValue(row, effectiveColumn, timeTakenData);
				flag=false;
				break;
			}
		}

		if (flag) {
			//Directly Putting the data
			xl.setValue(rowCount, 0, testNameData);
			xl.setValue(rowCount, effectiveColumn, timeTakenData);
		}
	
	}
}
