package objectRepository.IOTRONSystemAdministration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;

public class CreateEditClientDefinedCountry {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Create/Edit Client Defined Country";

	public CreateEditClientDefinedCountry() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm= new ApexCommon();
	}
	
	@FindBy(id = "P671_ALT_COUNTRY_NAME")
	private WebElement text_CountryName;
	
	@FindBy(id = "P671_ALT_COUNTRY_CODE")
	private WebElement text_CountryCode;
	
	@FindBy(xpath = "//button[.='Create']")
	private WebElement button_Create;

	@FindBy(xpath = "//button[.='Delete']")
	public WebElement button_Delete;
	
	@FindBy(xpath = "//div[contains(normalize-space(.),'Country Code already exists for this client')]")
	private WebElement data_DuplicateError;
		
	public String createNewCountry(String newCountry, String newCountryCode) {
		int newCountryInt=0;
		int newCountryCodeInt=0;
		String tempNewCountry="";
		String tempNewCountryCode="";
		do {
			tempNewCountry=newCountry+newCountryInt++;
			if(newCountryCodeInt++<10){
				tempNewCountryCode=newCountryCode+"0"+newCountryCodeInt;
			}else {
				tempNewCountryCode=newCountryCode+newCountryCodeInt;
			}
			
			if (newCountryCodeInt>99) {
				Assert.fail("New Country Code is reached at its max limit [<b>"+newCountryCode+"</b>], delete Older Countries to continue");
			}
			
			com.sendKeys(text_CountryName, tempNewCountry);
			com.sendKeys(text_CountryCode, tempNewCountryCode);
			com.click(button_Create);
		} while (!com.waitForElementTobe_NotVisible(data_DuplicateError, 1));
		
		return tempNewCountry;
	}

	public void deleteCountry() {
		com.click(button_Delete);
		if (com.isElementPresent(comm.button_OK, "Confirmation box")) {
			com.click(comm.button_OK);
			comm.closeApexAlert_SuccessMessagePopup("Action Processed");
		}
	}
		
}
