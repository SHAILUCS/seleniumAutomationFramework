package common.csvUtil;

public class CSVTester {
	public static void main(String[] args) {
		
		CSVManager csv=new CSVManager("./Downloads/Inbound Country Level.csv");
		for (int row = 1; row <= csv.getRowCount(); row++) {
			for (int col = 1; col <= csv.getColumnCount(); col++) {
				System.out.print(csv.getValue(row, col)+"\t");
			}
			System.out.println();
		}
		
		System.out.println("**************************************************************************************************");
		
		CSVManager csv1=new CSVManager("./Downloads/Inbound Country Level.csv",true);
		for (int row = 1; row <= csv1.getRowCount(); row++) {
			for (int col = 1; col <= csv1.getColumnCount(); col++) {
				System.out.print(csv1.getValue(row, col)+"\t");
			}
			System.out.println();
		}
		
		System.out.println("**************************************************************************************************");
		for (int row = 1; row <= csv1.getRowCount(); row++) {
			
				System.out.print(csv1.getValue(row, "Country Name")+"\t");
				System.out.print(csv1.getValue(row, "TrafficDirection")+"\t");
				System.out.print(csv1.getValue(row, "Service Type")+"\t");
				System.out.print(csv1.getValue(row, "Event Type")+"\t");
				System.out.print(csv1.getValue(row, "Jan 17")+"\t");
		
			System.out.println();
		}
		
	}
}
