package objectRepository.DealTracker.DTModule;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;

public class DealTracker {
	public static String title = "Deal Tracker";

	public DealTracker() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(xpath = "//a[contains(.,'Launch Deal Tracker')]")
	public WebElement link_LaunchDealTracker;
	
}
