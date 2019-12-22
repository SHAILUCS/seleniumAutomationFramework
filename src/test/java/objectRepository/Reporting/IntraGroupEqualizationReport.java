package objectRepository.Reporting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.EventType;
import objectRepository.common.ServiceType;

public class IntraGroupEqualizationReport
{
	private SeleniumMethods com;
	public static String title = "Intra Group Equalization Report";
	public IntraGroupEqualizationReport() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	@FindBy(xpath="//h1[contains(.,'Intra Group Equalization Report')]")
	private WebElement data_IntraGroupEqualizationReport_BreadCrumb;

	@FindBy(xpath="//h2[contains(.,'Report Parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Report Parameters')]//button[contains(@class,'hideShow')]")
	private WebElement hideShowIcon_ReportParameters;

	@FindBy(id="P513_IOT_AGREEMENT_ID")
	private WebElement dropdown_IotAgreement;

	@FindBy(id="P513_IOT_SERVICE_TYPE_ID")
	private WebElement dropdown_ServiceType;

	@FindBy(id="P513_IOT_EVENT_TYPE_ID")
	private WebElement dropdown_EventType;

	@FindBy(id="P513_INFORMATION_SET_0")
	private WebElement radioButton_Latest;

	@FindBy(id="P513_INFORMATION_SET_1")
	private WebElement radioButton_Original;

	@FindBy(xpath="//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;

	@FindBy(xpath="//li[.='Reporting'][contains(@class,'Breadcrumb')]")
	private WebElement breadcrumb_Reporting;

	@FindBy(xpath="//h2[contains(.,'Equalization report')]")
	private WebElement data_EqualizationReport_Section;

	@FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Equalization report')]//button[contains(@class,'hideShow')]")
	private WebElement hideShowIcon_EqualizationReport;

	@FindBy(id="P513_CURRENCY_LABEL")
	private WebElement data_HomeCurrency;

	public void verify_UI(String agreementRef,ServiceType ServiceType,EventType EventType, String latestOrOriginal) {
		CustomReporter.createNode("Bread Crumb data verification");
		com.isElementPresent(breadcrumb_Reporting,"breadcrumb_Reporting");
		com.isElementPresent(data_IntraGroupEqualizationReport_BreadCrumb,"data_IntraGroupEqualizationReport_BreadCrumb");

		CustomReporter.createNode("Report Parameters Section verification");
		com.isElementPresent(data_ReportParameters_Section,"Report Parameters Section heading");
		com.isElementPresent(hideShowIcon_ReportParameters,"Report Parameters hide Show Icon");
		com.isElementPresent(dropdown_IotAgreement,"Iot Agreement dropdown");
		com.isElementPresent(dropdown_ServiceType,"Service Type dropdown");
		com.isElementPresent(dropdown_EventType,"Event Type dropdown");
		com.isElementPresent(radioButton_Latest,"Latest radio Button");
		com.isElementPresent(radioButton_Original,"Original radio Button");
		com.isElementPresent(button_RefreshReport,"Refresh Report button");
		
		CustomReporter.createNode("Verifying the result section");
		if(performSearch(agreementRef, ServiceType, EventType, latestOrOriginal)){
			CustomReporter.report(STATUS.INFO, "Search results Section verification");
			com.isElementPresent(data_EqualizationReport_Section,"data_EqualizationReport_Section");
			com.isElementPresent(hideShowIcon_EqualizationReport,"hideShowIcon_EqualizationReport");
			com.isElementPresent(data_HomeCurrency,"data_HomeCurrency");
		}
	}

	private boolean performSearch(String agreementRef,ServiceType ServiceType,EventType EventType, String latestOrOriginal) {

		String message = "";

		if (agreementRef != null) {
			com.selectByPartialVisibleText(dropdown_IotAgreement , agreementRef);
			message = message + " | agreementRef= '" + agreementRef + "'";
		}

		if (ServiceType != null) {
			com.selectByVisibleText(dropdown_ServiceType , ServiceType.value);
			message = message + " | ServiceType= '" + ServiceType.value + "'";
		}

		if (EventType != null) {
			com.selectByVisibleText(dropdown_EventType , EventType.value);
			message = message + " | EventType= '" + EventType.value + "'";
		}

		if (latestOrOriginal != null) {
			if (latestOrOriginal.toLowerCase().contains("latest")) {
				com.click(radioButton_Latest);
			} else {
				com.click(radioButton_Original);
			}
			message = message + " | latestOrOriginal= '" + latestOrOriginal + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_RefreshReport);
		if (com.isElementPresent(data_EqualizationReport_Section, "Search Results")) {
			return true;
		}
		return false;
	}

}