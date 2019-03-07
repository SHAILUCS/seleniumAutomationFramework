package objectRepository.IOTRONSystemAdministration;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.csvUtil.CSVManager;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.common.ApexCommon;

public class MaintainClientDefinedCountriesMapping {
	private SeleniumMethods com;
	public static String title = "Maintain client defined countries mapping";
	private ApexCommon comm;

	public MaintainClientDefinedCountriesMapping() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm= new ApexCommon();
	}
	
	@FindBy(id = "P672_CLIENT_MASTER_ENTITY_ID")
	private WebElement select_HomeOperator;
	
	@FindBy(id = "P672_COUNTRY_ID")
	private WebElement select_Country;
	
	@FindBy(xpath = "//button[.='Apply Changes']")
	private WebElement button_ApplyChanges;
	

	@FindBy(xpath = "//td[@headers='COUNTRY_NAME']")
	private List<WebElement> list_CountryName;
	

	public boolean changeCountry(String operator, String partnerPMNCommaSeparated, String newCountry) {
		CustomReporter.createNode("Modifying the country mapping");
		boolean flag=false;
		com.selectByPartialVisibleText(select_HomeOperator, operator);
		if(com.isElementPresent(comm.table_ResultTabData)){
			String[] valList=null;
			WebTable tab= new WebTable(comm.table_ResultTabData);
			if(partnerPMNCommaSeparated.contains(",")){
				valList=partnerPMNCommaSeparated.split(",");
				for (int i = 0; i < valList.length; i++) {
					String singleVal=valList[i];
					comm.applyColumnFilter(1, 3, singleVal);
					com.click(tab.getChildObject(2, 1, "img", 0));
					com.selectByVisibleText(select_Country, newCountry, true);
					com.click(button_ApplyChanges);
					String pageCountry=tab.getCellText(2, 2);
					comm.removeAppliedColumnFilter(singleVal);
					if(pageCountry.equals(newCountry)){
						flag=true;
						CustomReporter.report(STATUS.PASS, "Client country mapping modified to '"+newCountry+"'");
					}else{
						CustomReporter.report(STATUS.FAIL, "Client country mapping is not modified expected '"+newCountry+"' but actual displayed '"+pageCountry+"'");
						flag=false;
						break;
					}
				}
			}else{
				comm.applyColumnFilter(1, 3, partnerPMNCommaSeparated);
				com.click(tab.getChildObject(2, 1, "img", 0),"Edit icon");
				com.selectByVisibleText(select_Country, newCountry, true);
				com.click(button_ApplyChanges,"Apply Changes");
				String pageCountry=tab.getCellText(2, 2);
				comm.removeAppliedColumnFilter(partnerPMNCommaSeparated);
				if(pageCountry.equals(newCountry)){
					flag=true;
					CustomReporter.report(STATUS.PASS, "Client country mapping modified to '"+newCountry+"'");
				}else{
					CustomReporter.report(STATUS.FAIL, "Client country mapping is not modified expected '"+newCountry+"' but actual displayed '"+pageCountry+"'");
				}
			}
			
		}
		return flag;
	}

		public List<String> GetCountries(String operator) {
			
			com.selectByPartialVisibleText(select_HomeOperator, operator);
			
			
			//comm.setRowsPerPage("All");
			
			/*Set<String> CountryExpected = new HashSet<>();
 			WebTable tab = new WebTable(comm.table_ResultTabData);
			int lastrow = tab.getRowCount();
			for (int row = 2; row<=lastrow; row++)
			{
				String Temp = tab.getCellText(row, 2);
				CountryExpected.add(Temp);
				
			}*/
			
			/*List<String> CountryExpected = new ArrayList<>();
			for (int row = 0; row<list_CountryName.size(); row++)
			{
				String Temp = com.getText(list_CountryName.get(row));
				CountryExpected.add(Temp);
				System.out.println(row+Temp);
				
			}
			System.out.println(CountryExpected);*/
			
			
			comm.downloadCSV();
			String filePath=Util.getDownloadedFilePath("maintain_client_defined_countries_map");
			CSVManager csv=new CSVManager(filePath);
			int lastRow=csv.getRowCount();
			Set<String> CountryExpected = new HashSet<>();
			for (int row = 2; row<=lastRow; row++)
			{
				String Temp = csv.getValue(row, 1);
				CountryExpected.add(Temp);
				//System.out.println(row+Temp);
			}

			//System.out.println(CountryExpected);
			
			List<String> list_expected = new ArrayList<>(CountryExpected);
			return list_expected;
			
		}
	
	
	
	
}
