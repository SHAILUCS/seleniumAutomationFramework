package objectRepository.FCHOperators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;

public class ManageRoamingAgreements {
	
	public static String title="Manage Roaming Agreements";
	public ManageRoamingAgreements() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(xpath = "//li/a[.='Manage IOT Addenda']")
	public WebElement link_ManageIOTAddenda;

}
