package objectRepository.DealTracker.FlashModule;

import java.util.Arrays;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;
import objectRepository.common.Country;
import objectRepository.common.EventType;
import objectRepository.common.ServiceType;
import objectRepository.common.TADIG;
import objectRepository.common.TrafficDirection;

public class YTDTrueup {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "YTD True-up";

	public YTDTrueup() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'YTD True-up')]")
	private WebElement data_YTDTrueup_BreadCrumb;

	public void verify_UI(String dateFrom, String dateTo, String brand, String[] subscriberType,
			TrafficDirection TrafficDirection, ServiceType ServiceType, EventType EventType, Country[] Country,
			String region, TADIG[] TADIG, String agreementStatus) {

		com.isElementPresent(data_YTDTrueup_BreadCrumb, "YTD True-up heading in Bread Crumb");
		verifyReportParametersSection();
		if (performSearch(dateFrom, dateTo, brand, subscriberType, TrafficDirection, ServiceType, EventType, Country,
				region, TADIG, agreementStatus)) {
			
		}

	}

	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(id = "P16_DATE_TO")
	private WebElement text_DateTo;

	@FindBy(id = "P16_COMPARATOR")
	private WebElement select_Comparator;

	@FindBy(id = "P16_CLIENT_MASTER_ENTITY_ID")
	private WebElement select_Brand;

	@FindBy(id = "P16_MVNO_LEFT")
	private WebElement select_SubscriberTypeLeft;

	@FindBy(id = "P16_MVNO_RIGHT")
	private WebElement select_SubscriberTypeRight;

	@FindBy(id = "P16_TRAFFIC_DIRECTION")
	private WebElement select_TrafficDirection;

	@FindBy(id = "P16_IOT_SERVICE_TYPE_ID")
	private WebElement select_ServiceType;

	@FindBy(id = "P16_IOT_EVENT_TYPE_ID")
	private WebElement select_EventType;

	@FindBy(id = "P16_COUNTRY_ID_LEFT")
	private WebElement select_CountryLeft;

	@FindBy(id = "P16_COUNTRY_ID_RIGHT")
	private WebElement select_CountryRight;

	@FindBy(id = "P16_IOT_CLIENT_ZONE_ID")
	private WebElement select_Region;

	@FindBy(id = "P16_PARTNER_MASTER_ENTITY_ID_LEFT")
	private WebElement select_PartnerLeft;

	@FindBy(id = "P16_PARTNER_MASTER_ENTITY_ID_RIGHT")
	private WebElement select_PartnerRight;

	@FindBy(id = "P16_AGREEMENT_STATUS")
	private WebElement select_AgreementStatus;

	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	private WebElement button_Refresh;

	private void verifyReportParametersSection() {
		CustomReporter.report(STATUS.INFO, "Report Parameters Section verification");
		com.isElementPresent(data_ReportParameters_Section, "Report Parameters Section");
		com.isElementPresent(text_DateTo, "Date To field");
		com.isElementPresent(select_Comparator, "Comparator dropdown");
		com.isElementPresent(select_Brand, "Brand dropdown");
		com.isElementPresent(select_SubscriberTypeLeft, "Subscriber Type Left shuttle");
		com.isElementPresent(select_SubscriberTypeRight, "Subscriber Type Right shuttle");
		com.isElementPresent(select_TrafficDirection, "Traffic Direction dropdown");
		com.isElementPresent(select_ServiceType, "Service Type dropdown");
		com.isElementPresent(select_EventType, "Event Type dropdown");
		com.isElementPresent(select_CountryLeft, "Country Left shuttle");
		com.isElementPresent(select_CountryRight, "Country Right shuttle");
		com.isElementPresent(select_Region, "Region dropdown");
		com.isElementPresent(select_PartnerLeft, "Partner Left shuttle");
		com.isElementPresent(select_PartnerRight, "Partner Right shuttle");
		com.isElementPresent(select_AgreementStatus, "Agreement Status dropdown");
		com.isElementPresent(button_Refresh, "Refresh button");
	}

	private boolean performSearch(String dateTo, String comparator, String brand, String[] subscriberType,
			TrafficDirection TrafficDirection, ServiceType ServiceType, EventType EventType, Country[] Country,
			String region, TADIG[] TADIG, String agreementStatus) {
		String message = "";

		if (dateTo != null) {
			com.sendKeys(text_DateTo, dateTo);
			message = message + " | dateTo= '" + dateTo + "'";
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		}

		if (comparator != null) {
			com.selectByPartialVisibleText(select_Comparator, comparator);
			message = message + " | comparator= '" + comparator + "'";
		}

		if (brand != null) {
			com.selectByPartialVisibleText(select_Brand, brand);
			message = message + " | brand= '" + brand + "'";
		}

		if (subscriberType != null) {
			com.selectByPartialVisibleText_DoubleClick_FromArray(select_SubscriberTypeLeft, subscriberType);
			message = message + " | subscriberType= '" + Arrays.toString(subscriberType) + "'";
		}

		if (TrafficDirection != null) {
			com.selectByPartialVisibleText(select_TrafficDirection, TrafficDirection.value);
			message = message + " | TrafficDirection= '" + TrafficDirection.value + "'";
		}

		if (ServiceType != null) {
			com.selectByPartialVisibleText(select_ServiceType, ServiceType.value);
			message = message + " | ServiceType= '" + ServiceType.value + "'";
		}

		if (EventType != null) {
			com.selectByPartialVisibleText(select_ServiceType, EventType.value);
			message = message + " | EventType= '" + EventType.value + "'";
		}

		if (Country != null) {
			comm.selectMultipleValues_DoubleClick_FromArray(select_CountryLeft, Country);
			message = message + " | Country= '" + Arrays.toString(Country) + "'";
		}

		if (region != null) {
			com.selectByPartialVisibleText(select_Region, region);
			message = message + " | region= '" + region + "'";
		}

		if (agreementStatus != null) {
			com.selectByPartialVisibleText(select_AgreementStatus, agreementStatus);
			message = message + " | agreementStatus= '" + agreementStatus + "'";
		}

		if (TADIG != null) {
			comm.selectMultipleValues_DoubleClick_FromArray(select_PartnerLeft, TADIG);
			message = message + " | HomeOperator= '" + Arrays.toString(TADIG) + "'";
		}
		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_Refresh);
		/*if (com.isElementPresent(data_Trends_Section, "Search results")) {
			return true;
		}*/

		return false;
	}

}
