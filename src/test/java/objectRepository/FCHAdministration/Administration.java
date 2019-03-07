package objectRepository.FCHAdministration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.driverManager.DriverFactory;

public class Administration {
	public static String title = "Administration";

	public Administration() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(linkText = "System Users")
	public WebElement link_SystemUsers;
	
}
