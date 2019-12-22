package objectRepository.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;

public class Header {
	
	public Header() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(xpath = "//div[@class='t-Header-navBar']//a[contains(.,'Welcome')]")
	protected WebElement link_Header_Welcome;

	@FindBy(xpath = "//div[@class='t-Header-navBar']//a[contains(.,'IOTRON')]")
	protected WebElement link_Header_IOTRON;

	@FindBy(xpath = "//div[@class='t-Header-navBar']//a[contains(.,'Feedback')]")
	protected WebElement link_Header_Feedback;

	@FindBy(xpath = "//div[@class='t-Header-navBar']//a[contains(.,'Help')]")
	protected WebElement link_Header_Help;

	@FindBy(xpath = "//div[@class='t-Header-navBar']//button")
	protected WebElement link_Header_UserId;

	@FindBy(xpath = "//div[contains(@class,'Menu')]//a[contains(.,'Last Login')]")
	protected WebElement link_Header_LastLogin;

	@FindBy(xpath = "//div[contains(@class,'Menu')]//a[contains(.,'Change Password')]")
	protected WebElement link_Header_ChangePassword;
	
	@FindBy(xpath = "//div[contains(@class,'Menu')]//a[contains(.,'Logout')]")
	protected WebElement link_Header_Logout;
	
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')][contains(@class,'Breadcrumb')]")
	protected WebElement breadCrumb_IotronWelcome;

	@FindBy(xpath = "//li[contains(.,'IOTRON System Administration')][contains(@class,'Breadcrumb')]")
	protected WebElement breadCrumb_IOTRONSystemAdministration;

	@FindBy(xpath = "//li[contains(.,'Agreement Capture')][contains(@class,'Breadcrumb')]")
	protected WebElement breadCrumb_AgreementCapture;
	
	@FindBy(xpath = "//li[contains(.,'Reporting')][contains(@class,'Breadcrumb')]")
	protected WebElement breadCrumb_Reporting;

	@FindBy(xpath = "//li[contains(.,'Forecasting')][contains(@class,'Breadcrumb')]")
	protected WebElement breadCrumb_Forecasting;

	@FindBy(xpath = "//li[contains(.,'Settlement')][contains(@class,'Breadcrumb')]")
	protected WebElement breadCrumb_Settlement;

	@FindBy(xpath = "//li[contains(.,'Operations')][contains(@class,'Breadcrumb')]")
	protected WebElement breadCrumb_Operations;
	
}
