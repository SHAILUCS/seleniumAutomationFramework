package objectRepository.Reporting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.Header;
import objectRepository.common.PagesTitle;

public class HutchQlikviewAccrualsReport extends Header{

	private SeleniumMethods com;
	public static String title = PagesTitle.HutchQlikviewAccrualsReport.title;

	public HutchQlikviewAccrualsReport() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	@FindBy(xpath = "//li[contains(.,'Hutch Qlikview Accruals Report')][contains(@class,'Breadcrumb')]")
	private WebElement breadCrumb_HutchQlikviewAccrualsReport;
	
	@FindBy(xpath="//button[contains(.,'Download CSV')]")
	private WebElement button_DownloadCSV;

	public void verify_UI() {
		com.isElementPresent(breadCrumb_IotronWelcome, "breadCrumb_IotronWelcome");
		com.isElementPresent(breadCrumb_Reporting, "breadCrumb_Reporting");
		com.isElementPresent(button_DownloadCSV, "Download CSV button");
	}
}
