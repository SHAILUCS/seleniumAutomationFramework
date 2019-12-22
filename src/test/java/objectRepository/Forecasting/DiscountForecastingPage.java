package objectRepository.Forecasting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;

public class DiscountForecastingPage {
	
	@FindBy(linkText="Forecast Report per Agreement")
	public WebElement link_forecastReportPerAgreement;
	
	@FindBy(linkText = "Financial Report - Forecasted TAP & Discounted Charges")
	public WebElement link_FinancialReportForecastedTAPDiscountedCharges;
	
	public static String title="Discount Forecasting";
	public DiscountForecastingPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
}
