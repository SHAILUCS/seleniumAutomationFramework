package objectRepository.FCHOperators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.driverManager.DriverFactory;

public class ManageIOTAddenda {
	
	public static String title="Manage IOT Addenda";
	public ManageIOTAddenda() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(xpath = "//li/a[.='Manage IOT Addenda']")
	public WebElement link_ManageIOTAddenda;

}
