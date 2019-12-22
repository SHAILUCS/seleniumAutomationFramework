package objectRepository.AgreementCapture;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;

public class CreateEditZone {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title="Create/Edit Zone";
	public CreateEditZone() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm= new ApexCommon();
	}
	
	@FindBy(id = "P505_ZONE_NAME")
	private WebElement text_ZoneName;
	
	@FindBy(id = "P505_DESCRIPTION")
	private WebElement text_Description;
	
	@FindBy(id = "P505_COUNTRY_LIST_LEFT")
	private WebElement select_CountryList;

	@FindBy(xpath = "//button[.='Create']")
	private WebElement button_Create;
	
	@FindBy(xpath = "//button[.='Apply Changes']")
	private WebElement button_ApplyChanges;

	@FindBy(xpath = "//button[.='Delete']")
	private WebElement button_Delete;
	
	@FindBy(xpath = "//button[.='OK']")
	private WebElement button_Ok;
	
	public void createZone(String zoneName,String zoneDesc,String[] countries) {
		com.sendKeys("Zone Name", text_ZoneName, zoneName);
		com.sendKeys("Description", text_Description, zoneDesc);
		com.selectByPartialVisibleText_DoubleClick_FromArray(select_CountryList, countries);
		com.click(button_Create,"Create Button");
	}
	
	public void editZone(String zoneName) {
		com.sendKeys("Zone Name", text_ZoneName, zoneName);
		com.selectByPartialVisibleText_DoubleClick_FromCommaSeparated(select_CountryList, "HUN,IND,COM");
		com.click(button_ApplyChanges,"Apply Changes Button");
	}

	public void deleteZone() {
		com.click(button_Delete, "Delete button");
		if(com.isElementPresent(button_Ok, "Ok button on confirmation popup")){
			com.click(button_Ok);
			com.waitForElementTobe_Visible(By.xpath("//h2[contains(@class,'Alert')][contains(.,'Action Processed.')]"), "Action Processed. confirmation message");
		}
	}
	
	
	
}
