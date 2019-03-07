package objectRepository.Reporting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.driverManager.DriverFactory;

public class DiscountReporting {
	
	public static String title="Discount Reporting";
	public DiscountReporting() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	@FindBy(linkText = "Overview Report")
	public WebElement link_OverviewReport;
	
}
