package objectRepository.IOTRONUserConfiguration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.driverManager.DriverFactory;

public class IOTRONUserConfiguration {
	public static String title = "IOTRON User Configuration";
	public IOTRONUserConfiguration() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	@FindBy(linkText = "Contact Role & Team Assignment")
	public WebElement link_ContactRoleAndTeamAssignment;
	
}
