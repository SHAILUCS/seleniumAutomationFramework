package database;

import java.sql.ResultSet;

import common.configData_Util.Util;

public class ConnectionTester {
	public static void main(String[] args) {
		String query=ContentReader.readLineByLineJava8("E:/Automation/TestNG/iotronTestng/IOTRON-AutomationFrameworkApex5/db_queries/hktAlertsCSVemailData.sql");
		
		
		 /*rs.last();
		 
		 System.out.println(rs.getRow());*/
		 
		ResultSet rs=ConnectionManager.executeQuery(query);
		ConnectionManager.printAllText(rs);
		
		String[] trafPer={"0618","0718","0818","0918","1018","1118"};
		System.out.println();

		for (String string : trafPer) {
			String formatted=Util.convertToString("m/d/yyyy",Util.convertToDate("mmyy", string));
			System.out.println(formatted);
			System.out.println(ConnectionManager.getCellText("FLUCTUATION_PERCENT", rs, "PERIOD_START_DATE=="+formatted+" 0:0:0","CURR_TO==AUD"));
		}
		
		
		
		//String data_0618_AUD = ConnectionManager.getCellText("")
		
		ConnectionManager.closeConnection();
		
	}
}
