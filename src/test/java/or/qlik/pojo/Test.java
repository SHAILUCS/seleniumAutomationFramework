package or.qlik.pojo;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static void main(String[] args) {
		
		Map<String, String> filterMap =  new HashMap<String, String>();
		
		DataMap map = new DataMap();
		map.loadXls("C:\\Downloads_Qlik\\IOTRON\\HKT_IOTRON Discount Management App\\Data\\IB\\Usage and Charge data.xlsx");
		System.out.println(map.getSum("Actual Volume"));
		
		
		/*DataMap.loadCsv("C:\\Downloads_Qlik\\IOTRON\\GoMalta_IOTRON Discount Management App\\Overview Report\\IB\\overview_report (13).csv");
		filterMap =  new HashMap<String, String>();
		filterMap.put("Service Type", "Voice");
		filterMap.put("Event Type", "MO");
		System.out.println(DataMap.getSum("Total TAP Charges Excluding Tax",DataMap.applyFilter(filterMap)));*/
	}
}
