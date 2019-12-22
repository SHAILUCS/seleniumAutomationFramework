package objectRepository.AgreementCapture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.AgreementCapture.Rework.AgreementDetails.IOTDiscountAgreementDetails_p15_Parent;
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;

public class UpdateIOTAgreementChecklistItem_P338 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.UpdateIOTAgreementChecklistItem_P338.title;

	public UpdateIOTAgreementChecklistItem_P338() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	private List<String> colNames = new ArrayList<>(Arrays.asList());

	@FindBy(xpath = "//button[.='Cancel']")
	public WebElement button_Cancel;

	@FindBy(id = "P338_COMPLETED_0")
	private WebElement radio_No;

	@FindBy(id = "P338_COMPLETED_1")
	private WebElement radio_Yes;

	@FindBy(id = "P338_COMPLETED_2")
	private WebElement radio_NA;

	@FindBy(id = "P338_DATE_COMPLETED")
	private WebElement text_DateCompleted;

	@FindBy(xpath = "//button[.='Apply Changes']")
	private WebElement button_ApplyChanges;

	public void verify_UI() {

	}

	public void applyChanges(String noYesNa,String date) {
		String message="{";
		if (noYesNa!=null) {
			message=message+"radio= '"+noYesNa+"',";
			if (noYesNa.equalsIgnoreCase("no")) {
				com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(radio_No);
			}else if (noYesNa.equalsIgnoreCase("yes")) {
				com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(radio_Yes);
			}else if (noYesNa.equalsIgnoreCase("n/a")) {
				com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(radio_NA);
			}
		}

		if (date!=null) {
			com.sendKeys(text_DateCompleted, date);
			message=message+" date completed= '"+date+"'}";
		}

		CustomReporter.report(STATUS.INFO, "Filling the values "+message);
		com.click(button_ApplyChanges);
		com.verifyPageTitle(IOTDiscountAgreementDetails_p15_Parent.title, true);
	}
}
