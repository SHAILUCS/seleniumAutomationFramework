package objectRepository.CustomisedReportsTMO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;

import objectRepository.common.Navigator;

public class CustomisedReportsTMO {

	private Navigator nav;
	public static String title = "Customised Reports (TMO)";

	public CustomisedReportsTMO() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		nav= new Navigator();
	}


	@FindBy(linkText = "Launch Customised Reports (TMO)")
	public WebElement link_LaunchCustomisedReportsTMO;

	@FindBy(css = "li[id$='_menubar_2'] span[class='a-Icon icon-menu-drop-down']")
	private WebElement icon_Menu_MonthlyDiscountAccrual;

	@FindBy(xpath = "//button[contains(.,'Monthly Discount Accrual')]")
	private WebElement link_MonthlyDiscount_MonthlyDiscountAccrual;

	@FindBy(xpath = "//button[contains(.,'Monthly Discount Actual')]")
	private WebElement link_MonthlyDiscount_MonthlyDiscountActual;	

	@FindBy(linkText = "IB Accrual Total")
	private WebElement link_MonthlyDiscountAccrual_IBAccrualTotal;

	@FindBy(linkText = "IB Accrual Voice")
	private WebElement link_MonthlyDiscountAccrual_IBAccrualVoice;

	@FindBy(linkText = "IB Accrual SMS")
	private WebElement link_MonthlyDiscountAccrual_IBAccrualSms;

	@FindBy(linkText = "IB Accrual Data")
	private WebElement link_MonthlyDiscountAccrual_IBAccrualData;

	@FindBy(linkText = "OB Accrual Total")
	private WebElement link_MonthlyDiscountAccrual_OBAccrualTotal;

	@FindBy(linkText = "OB Accrual Voice")
	private WebElement link_MonthlyDiscountAccrual_OBAccrualVoice;

	@FindBy(linkText = "OB Accrual SMS")
	private WebElement link_MonthlyDiscountAccrual_OBAccrualSms;

	@FindBy(linkText = "OB Accrual Data")
	private WebElement link_MonthlyDiscountAccrual_OBAccrualData;

	@FindBy(linkText = "IB Actual Total")
	private WebElement link_MonthlyDiscountActual_IBActualTotal;

	@FindBy(linkText = "IB Actual Voice")
	private WebElement link_MonthlyDiscountActual_IBActualVoice;

	@FindBy(linkText = "IB Actual SMS")
	private WebElement link_MonthlyDiscountActual_IBActualSms;

	@FindBy(linkText = "IB Actual Data")
	private WebElement link_MonthlyDiscountActual_IBActualData;

	@FindBy(linkText = "OB Actual Total")
	private WebElement link_MonthlyDiscountActual_OBActualTotal;

	@FindBy(linkText = "OB Actual Voice")
	private WebElement link_MonthlyDiscountActual_OBActualVoice;

	@FindBy(linkText = "OB Actual SMS")
	private WebElement link_MonthlyDiscountActual_OBActualSms;

	@FindBy(linkText = "OB Actual Data")
	private WebElement link_MonthlyDiscountActual_OBActualData;

	public MonthlyDiscountAccrual to_MonthlyDiscountAccruals_IBTotal(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountAccrual,link_MonthlyDiscountAccrual_IBAccrualTotal);
		return new MonthlyDiscountAccrual();
	}
	public MonthlyDiscountAccrual to_MonthlyDiscountAccruals_IBVoice(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountAccrual,link_MonthlyDiscountAccrual_IBAccrualVoice);
		return new MonthlyDiscountAccrual();
	}
	public MonthlyDiscountAccrual to_MonthlyDiscountAccruals_IBSms(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountAccrual,link_MonthlyDiscountAccrual_IBAccrualSms);
		return new MonthlyDiscountAccrual();
	}
	public MonthlyDiscountAccrual to_MonthlyDiscountAccruals_IBData(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountAccrual,link_MonthlyDiscountAccrual_IBAccrualData);
		return new MonthlyDiscountAccrual();
	}
	public MonthlyDiscountAccrual to_MonthlyDiscountAccruals_OBTotal(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountAccrual,link_MonthlyDiscountAccrual_OBAccrualTotal);
		return new MonthlyDiscountAccrual();
	}
	public MonthlyDiscountAccrual to_MonthlyDiscountAccruals_OBVoice(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountAccrual,link_MonthlyDiscountAccrual_OBAccrualVoice);
		return new MonthlyDiscountAccrual();
	}
	public MonthlyDiscountAccrual to_MonthlyDiscountAccruals_OBSms(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountAccrual,link_MonthlyDiscountAccrual_OBAccrualSms);
		return new MonthlyDiscountAccrual();
	}
	public MonthlyDiscountAccrual to_MonthlyDiscountAccruals_OBData(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountAccrual.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountAccrual,link_MonthlyDiscountAccrual_OBAccrualData);
		return new MonthlyDiscountAccrual();
	}
	public MonthlyDiscountActuals to_MonthlyDiscountActuals_IBTotal(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountActual,link_MonthlyDiscountActual_IBActualTotal);
		return new MonthlyDiscountActuals();
	}
	public MonthlyDiscountActuals to_MonthlyDiscountActuals_IBVoice(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountActual,link_MonthlyDiscountActual_IBActualVoice);
		return new MonthlyDiscountActuals();
	}
	public MonthlyDiscountActuals to_MonthlyDiscountActuals_IBSms(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountActual,link_MonthlyDiscountActual_IBActualSms);
		return new MonthlyDiscountActuals();
	}
	public MonthlyDiscountActuals to_MonthlyDiscountActuals_IBData(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountActual,link_MonthlyDiscountActual_IBActualData);
		return new MonthlyDiscountActuals();
	}
	public MonthlyDiscountActuals to_MonthlyDiscountActuals_OBTotal(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountActual,link_MonthlyDiscountActual_OBActualTotal);
		return new MonthlyDiscountActuals();
	}
	public MonthlyDiscountActuals to_MonthlyDiscountActuals_OBVoice(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountActual,link_MonthlyDiscountActual_OBActualVoice);
		return new MonthlyDiscountActuals();
	}
	public MonthlyDiscountActuals to_MonthlyDiscountActuals_OBSms(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountActual,link_MonthlyDiscountActual_OBActualSms);
		return new MonthlyDiscountActuals();
	}
	public MonthlyDiscountActuals to_MonthlyDiscountActuals_OBData(String method) {
		nav.traverseMenu_VerifyPageTitle( MonthlyDiscountActuals.title, icon_Menu_MonthlyDiscountAccrual,link_MonthlyDiscount_MonthlyDiscountActual,link_MonthlyDiscountActual_OBActualData);
		return new MonthlyDiscountActuals();
	}

}
