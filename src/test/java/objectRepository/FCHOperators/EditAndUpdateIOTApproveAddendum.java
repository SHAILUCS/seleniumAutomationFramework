package objectRepository.FCHOperators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class EditAndUpdateIOTApproveAddendum {
	private SeleniumMethods com;

	public static String title="Edit and Update/IOT Approve Addendum";
	public EditAndUpdateIOTApproveAddendum() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	@FindBy(id = "P582_REC_PYMT_CURR")
	private WebElement select_ClientReceivableCurrency;

	@FindBy(id = "P582_PAY_PYMT_CURR")
	private WebElement select_PartnerPayableCurrency;

	public String get_ClientReceivable_And_PartnerPayableCurrency() {
		String ClientReceivableCurrencyValue = com.getFirstSelectedOptionVisibleText(select_ClientReceivableCurrency);
		CustomReporter.report(STATUS.INFO, "Client Receivable Currency Value: "+ClientReceivableCurrencyValue);
		String PartnerPayableCurrencyValue = com.getFirstSelectedOptionVisibleText(select_PartnerPayableCurrency);
		CustomReporter.report(STATUS.INFO, "Partner Payable Currency Value: "+PartnerPayableCurrencyValue);
		return ClientReceivableCurrencyValue+","+PartnerPayableCurrencyValue;
	}

}
