package objectRepository.FCHOperators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.driverManager.DriverFactory;

public class Operators {

	public static String title="Operators";
	public Operators() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(xpath = "//li/a[.='Roaming Agreements']")
	public WebElement link_RoamingAgreements;

	

}
