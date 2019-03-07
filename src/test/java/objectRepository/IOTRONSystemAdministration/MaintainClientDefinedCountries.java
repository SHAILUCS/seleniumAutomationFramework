package objectRepository.IOTRONSystemAdministration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.common.ApexCommon;

public class MaintainClientDefinedCountries {
	private SeleniumMethods com;
	public static String title = "Maintain client defined countries";
	private ApexCommon comm;
	private WebTable tab;

	public MaintainClientDefinedCountries() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm= new ApexCommon();
		tab= new WebTable(comm.table_ResultTabData);
	}

	@FindBy(id = "P670_CLIENT_MASTER_ENTITY_ID")
	private WebElement select_HomeOperator;

	@FindBy(xpath = "//button[.='Create']")
	private WebElement button_Create;

	

	public String createNewCountry(String operator, String newCountry, String newCountryCode) {
		String newCountryName=newCountry;
		
		CustomReporter.createNode("Creating a new Country");
		com.selectByPartialVisibleText(select_HomeOperator, operator);
		com.click(button_Create);
		if(com.verifyPageTitle(CreateEditClientDefinedCountry.title,true)){
			CreateEditClientDefinedCountry clientDefinedCountry= new CreateEditClientDefinedCountry();
			newCountryName=clientDefinedCountry.createNewCountry(newCountry,newCountryCode);
			
			int rowFound=tab.getRowWithCellText(newCountryName);
			if(rowFound>0){
				CustomReporter.report(STATUS.PASS, "'"+newCountryName+"' got created");
			}else{
				CustomReporter.report(STATUS.FAIL, "'"+newCountryName+"' is NOT created");
			}
		}
		return newCountryName;
	}

	public void deleteCountry(String operator, String newCountry) {
		
		CustomReporter.createNode("Deleting Country "+newCountry);
		com.selectByPartialVisibleText(select_HomeOperator, operator);
		
		int foundRow=tab.getRowWithCellText(newCountry);
		if(foundRow>0){
			com.click(tab.getChildObject(foundRow, 1, "img", 0));
			CreateEditClientDefinedCountry clientDefinedCountry= new CreateEditClientDefinedCountry();
			clientDefinedCountry.deleteCountry();
			
			if(com.waitForElementTobe_NotVisible(comm.data_NoDataFoundIcon,10)){
				foundRow=tab.getRowWithCellText(newCountry);
				if(foundRow==-1){
					CustomReporter.report(STATUS.PASS, "Country deleted '"+newCountry+"', It is not displayed in the Country list table");
				}else{
					CustomReporter.report(STATUS.FAIL, "Country NOT deleted '"+newCountry+"', It is displaying in the Country list table");
				}
			}else{
				CustomReporter.report(STATUS.PASS, "Country deleted '"+newCountry+"', No data found. message is getting displayed");
			}
		}
	}

	public void verifyCountry_CanBe_Deleted(String countryName) {
		CustomReporter.createNode("verifying Country can be deleted, when they are not mapped");
		
		int foundRow=tab.getRowWithCellText(countryName);
		if(foundRow>0){
			com.click(tab.getChildObject(foundRow, 1, "img", 0));
			CreateEditClientDefinedCountry clientDefinedCountry= new CreateEditClientDefinedCountry();
			if(com.isElementPresent(clientDefinedCountry.button_Delete)){
				CustomReporter.report(STATUS.PASS, "Country can be deleted as Delete Button is getting displayed");
			}else{
				CustomReporter.report(STATUS.FAIL, "Country can not be deleted as Delete Button is not getting displayed");
			}
		}
	}

	public void verifyCountry_CanNOTBe_Deleted(String operator,String countryName) {
		CustomReporter.createNode("verifying Country can not be deleted, when they are mapped");
		
		com.selectByVisibleText(select_HomeOperator, operator, true);
		
		int foundRow=tab.getRowWithCellText(countryName);
		if(foundRow>0){
			com.click(tab.getChildObject(foundRow, 1, "img", 0));
			CreateEditClientDefinedCountry clientDefinedCountry= new CreateEditClientDefinedCountry();
			if(com.waitForElementTobe_NotVisible(clientDefinedCountry.button_Delete,5)){
				CustomReporter.report(STATUS.PASS, "Delete Button is NOT getting displayed so Country can NOT be deleted");
			}else{
				CustomReporter.report(STATUS.FAIL, "Delete Button is getting displayed so Country can be deleted");
			}
		}
	}



}
