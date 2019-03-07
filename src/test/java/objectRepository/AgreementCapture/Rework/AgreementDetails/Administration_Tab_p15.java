package objectRepository.AgreementCapture.Rework.AgreementDetails;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;

public class Administration_Tab_p15 extends IOTDiscountAgreementDetails_p15_Parent{
		
	public static String title = IOTDiscountAgreementDetails_p15_Parent.title;

	public Administration_Tab_p15() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(xpath = "//h2[.='Administration']")
	private WebElement heading_Administration;

	public void verify_UI() {
		CustomReporter.report(STATUS.INFO, "Administration Tab ->>");
		openTab(tab_Administration);
		com.isElementPresent(heading_Administration, "heading_Administration");
	}

}