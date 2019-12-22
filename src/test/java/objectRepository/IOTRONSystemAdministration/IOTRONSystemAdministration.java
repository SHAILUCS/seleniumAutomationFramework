package objectRepository.IOTRONSystemAdministration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;

public class IOTRONSystemAdministration {
	
	public static String title="IOTRON System Administration";
	public IOTRONSystemAdministration() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	@FindBy(linkText = "Maintain IOT Clients")
	public WebElement link_MaintainIotClients;
	
	@FindBy(linkText = "Query IOT Summary")
	public WebElement link_QuerySummary;
	
	@FindBy(linkText = "Maintain Client Defined Countries")
	public WebElement link_MaintainClientDefinedCountries;
	
	@FindBy(linkText = "Maintain Client Defined Countries Mapping")
	public WebElement link_MaintainClientDefinedCountriesMapping;
	
		
}
