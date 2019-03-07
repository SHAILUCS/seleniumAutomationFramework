package common.csvUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import common.configData_Util.Util;

public class CSVManager {

	private String CSV_FILE_PATH;
	private Reader reader;
	private boolean withFirstRecordAsHeader=false;

	public CSVManager(String CSV_FILE_PATH) {
		this.CSV_FILE_PATH=CSV_FILE_PATH;
	}

	public CSVManager(String CSV_FILE_PATH, boolean withFirstRecordAsHeader) {
		this.CSV_FILE_PATH=CSV_FILE_PATH;
		this.withFirstRecordAsHeader=withFirstRecordAsHeader;
	}

	private CSVParser getFreshCsvParser() {
		CSVParser csvParser = null;
		try {
			reader=new BufferedReader(new InputStreamReader(new FileInputStream(CSV_FILE_PATH),"ISO-8859-1"));
			//reader=new BufferedReader(new InputStreamReader(new FileInputStream(CSV_FILE_PATH),"utf-8"));
			//reader= Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
			if (withFirstRecordAsHeader) {
				csvParser = new CSVParser(reader, CSVFormat.DEFAULT
						.withFirstRecordAsHeader()
						.withIgnoreHeaderCase()
						.withTrim());
			}else{
				csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return csvParser;
	}


	/** @return Row Count*/
	public int getRowCount() {
		int rowCount=0;
		try{
			CSVParser p=getFreshCsvParser();
			while (p.iterator().hasNext()) {
				rowCount++;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	/** @return Column Count*/
	public int getColumnCount() {
		return getFreshCsvParser().iterator().next().size();
	}


	public static void main(String[] args) throws Exception {
		String filePath=Util.getDownloadedFilePath("maintain_client_defined_countries_map");
		CSVManager csv=new CSVManager(filePath);
		int lastRow=csv.getRowCount();
		Set<String> CountryExpected = new HashSet<>();
		for (int row = 51; row<=lastRow; row++)
		{
			String Temp = csv.getValue(row, 1);
			CountryExpected.add(Temp);
			System.out.println(row+Temp);
		}

		System.out.println(CountryExpected);
	}


	/**
	 * @return if nothing found then "", else String {@value} present in the cell
	 * 
	 * @param rowNum = 1 refers to Excel row 2 -> When the {@link withFirstRecordAsHeader} is TRUE
	 * @param colName = pass any value from first row when {@link withFirstRecordAsHeader} is TRUE
	 * 
	 * */
	public String getValue(int rowNum,String colName) {
		String value="";
		try {
			CSVParser p=getFreshCsvParser();
			for (CSVRecord csvRecord : p) {
				// Accessing values by Header names
				if (csvRecord.getRecordNumber()==rowNum) {
					value=csvRecord.get(colName);
					//byte ptext[] = value.getBytes(Charset.forName("UTF-8")); 
					//value = new String(ptext, "UTF-8"); 
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * @return if nothing found then "", else String {@value} present in the cell
	 * 
	 * @param rowNum = 1 refers to Excel row 1 -> When the {@link withFirstRecordAsHeader} is FALSE
	 * @param rowNum = 1 refers to Excel row 2 -> When the {@link withFirstRecordAsHeader} is TRUE
	 * 
	 * @param colNum = 1 refers to Excel col A
	 * */
	public String getValue(int rowNum,int colNum){
		String value="";
		try{
			CSVParser p=getFreshCsvParser();
			for (CSVRecord csvRecord : p) {
				// Accessing values by Header names
				if (csvRecord.getRecordNumber()==rowNum) {
					value=csvRecord.get(colNum-1);
					//byte ptext[] = value.getBytes(Charset.forName("UTF-8")); 
					//value = new String(ptext, "UTF-8"); 
					break;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public void printAllContent() {		
		CSVParser p=getFreshCsvParser();
		for (CSVRecord csvRecord : p) {
			// Accessing Values by Column Index
			String name = csvRecord.get(0);
			String email = csvRecord.get(1);
			String phone = csvRecord.get(2);
			String country = csvRecord.get(3);

			System.out.println("Record No - " + csvRecord.getRecordNumber());
			System.out.println("---------------");
			System.out.println("Name : " + name);
			System.out.println("Email : " + email);
			System.out.println("Phone : " + phone);
			System.out.println("Country : " + country);
			System.out.println("---------------\n\n");
		}
	}

	public void printAllContent1(){     
		CSVParser p=getFreshCsvParser();
		for (CSVRecord csvRecord : p) {
			// Accessing values by Header names
			String name = csvRecord.get("Country Name");
			String email = csvRecord.get("Traffic Direction");
			String phone = csvRecord.get("Service Type");
			String country = csvRecord.get("Event Type");


			System.out.println("Record No - " + csvRecord.getRecordNumber());
			System.out.println("---------------");
			System.out.println("Name : " + name);
			System.out.println("Email : " + email);
			System.out.println("Phone : " + phone);
			System.out.println("Country : " + country);
			System.out.println("---------------\n\n");
		}

	}

}
