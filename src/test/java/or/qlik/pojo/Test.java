package or.qlik.pojo;

public class Test {
	
	public static void main(String[] args) {
		QlikGRID.addRow("Nova Iceland", "Data", "2018", "Jan", "OB", "fileName1", "fieldName1", "1000", "1001");
		QlikGRID.addRow("Nova Iceland", "Data", "2018", "Jan", "OB", "fileName1", "fieldName2", "1002", "1002");
		QlikGRID.addRow("Nova Iceland", "Data", "2018", "Jan", "OB", "fileName1", "fieldName2", "1010", "1000");
		
		
		System.out.println(QlikGRID.generateHTMLTable());
	}
}
