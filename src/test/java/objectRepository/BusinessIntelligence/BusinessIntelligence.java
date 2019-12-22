package objectRepository.BusinessIntelligence;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;

public class BusinessIntelligence {
	public static String title = "Business Intelligence";

	public BusinessIntelligence() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(linkText = "Daily Traffic Analysis Report")
	public WebElement link_DailyTrafficAnalysisReport;

	@FindBy(linkText = "Daily Traffic Volumes by Partner")
	public WebElement link_DailyTrafficVolumesByPartner;

	@FindBy(linkText = "Daily Traffic Volumes by Country & Partner")
	public WebElement link_DailyTrafficVolumesByCountryPartner;
	
}
