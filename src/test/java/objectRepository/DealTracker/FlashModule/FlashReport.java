package objectRepository.DealTracker.FlashModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
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

public class FlashReport {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Flash Report";

	public FlashReport() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	public void verify_UI(String dateFrom, String dateTo, String brand, String[] subscriberType,
			TrafficDirection TrafficDirection, ServiceType ServiceType, EventType EventType, Country[] Country,
			String region, TADIG[] TADIG, String agreementStatus) {

		CustomReporter.createNode("Verifying the report parameters");
		com.isElementPresent(data_FlashReport_BreadCrumb, "Flash Report heading in Bread Crumb");
		verifyReportParametersSection();
		CustomReporter.createNode("Verifying the result section");
		if (performSearch(dateFrom, dateTo, brand, subscriberType, TrafficDirection, ServiceType, EventType, Country,
				region, TADIG, agreementStatus)) {
			verifyTrendsSection();
			verifyBulkUpdateOverrideSection();
			verifyFlashReportSection();
		}

	}

	@FindBy(xpath = "//h1[contains(.,'Flash Report')]")
	private WebElement data_FlashReport_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(id = "P14_DATE_FROM")
	private WebElement text_DateFrom;

	@FindBy(id = "P14_DATE_TO")
	private WebElement text_DateTo;

	@FindBy(id = "P14_CLIENT_MASTER_ENTITY_ID")
	private WebElement select_Brand;

	@FindBy(id = "P14_MVNO_LEFT")
	private WebElement select_SubscriberTypeLeft;

	@FindBy(id = "P14_MVNO_RIGHT")
	private WebElement select_SubscriberTypeRight;

	@FindBy(id = "P14_TRAFFIC_DIRECTION")
	private WebElement select_TrafficDirection;

	@FindBy(id = "P14_IOT_SERVICE_TYPE_ID")
	private WebElement select_ServiceType;

	@FindBy(id = "P14_IOT_EVENT_TYPE_ID")
	private WebElement select_EventType;

	@FindBy(id = "P14_COUNTRY_ID_LEFT")
	private WebElement select_CountryLeft;

	@FindBy(id = "P14_COUNTRY_ID_RIGHT")
	private WebElement select_CountryRight;

	@FindBy(id = "P14_IOT_CLIENT_ZONE_ID")
	private WebElement select_Region;

	@FindBy(id = "P14_PARTNER_MASTER_ENTITY_ID_LEFT")
	private WebElement select_PartnerLeft;

	@FindBy(id = "P14_PARTNER_MASTER_ENTITY_ID_RIGHT")
	private WebElement select_PartnerRight;

	@FindBy(id = "P14_AGREEMENT_STATUS")
	private WebElement select_AgreementStatus;

	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	private WebElement button_Refresh;

	private void verifyReportParametersSection() {
		CustomReporter.report(STATUS.INFO, "Report Parameters Section verification");
		com.isElementPresent(data_ReportParameters_Section, "Report Parameters Section");
		com.isElementPresent(text_DateFrom, "Date From field");
		com.isElementPresent(text_DateTo, "Date To field");
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

	private boolean performSearch(String dateFrom, String dateTo, String brand, String[] subscriberType,
			TrafficDirection TrafficDirection, ServiceType ServiceType, EventType EventType, Country[] Country,
			String region, TADIG[] TADIG, String agreementStatus) {
		String message = "";

		if (dateFrom != null) {
			com.sendKeys(text_DateFrom, dateFrom);
			message = message + " | dateFrom= '" + dateFrom + "'";
		}

		if (dateTo != null) {
			com.sendKeys(text_DateTo, dateTo);
			message = message + " | dateTo= '" + dateTo + "'";
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
		if (com.isElementPresent(data_Trends_Section, "Search results")) {
			return true;
		}

		return false;
	}

	@FindBy(xpath = "//h2[contains(.,'Trends')]")
	private WebElement data_Trends_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Trends')]//button[contains(@class,'hideShow')]")
	private WebElement icon_Section_Trends_HideShow;

	private By tab_Trends = By.cssSelector("table[summary='Trends']");

	private List<String> colNames_Trends = new ArrayList<>(
			Arrays.asList("Client PMN", "From (inclusive)", "To (inclusive)", "Traffic Direction", "EU Voice MO volume",
					"EU Data volume", "EU Voice MO WoW growth", "EU Voice MO 4 week growth", "EU Data Wow growth",
					"EU Data 4 week growth", "RoW Voice MO volume", "RoW Data volume", "RoW Voice MO WoW growth",
					"RoW Voice MO 4 week growth", "RoW Data WoW growth", "RoW Data 4 week growth"));

	@FindBy(linkText = "Download trends report")
	private WebElement link_DownloadTrendsReport;

	@FindBy(xpath = "//table[@summary='Trends']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination;

	private void verifyTrendsSection() {
		CustomReporter.report(STATUS.INFO, "Trends Section verification");
		com.isElementPresent(data_Trends_Section, "Trends Section");
		com.isElementPresent(data_Trends_Section, "Trends Section hide/show icon");

		com.click_UsingAction(icon_Section_Trends_HideShow);
		//comm.printColumnHeaders(tab_Trends, 1);
		comm.verifyColumnHeaders(tab_Trends, colNames_Trends, 1);
		com.isElementPresent(link_DownloadTrendsReport, "Download Trends Report link");
		com.isElementPresent(data_Pagination, "Pagination data " + com.getText(data_Pagination));
	}

	@FindBy(xpath = "//h2[contains(.,'Bulk Uplift Override')]")
	private WebElement data_BulkUpliftOverride_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Bulk Uplift Override')]//button[contains(@class,'hideShow')]")
	private WebElement icon_Section_BulkUpliftOverride_HideShow;

	@FindBy(xpath = "//button[contains(.,'Bulk Update Uplift')]")
	private WebElement button_BulkUpdateUplift;

	@FindBy(id = "P14_EU_VOICE_MO_OUT_UPLIFT")
	private WebElement text_MoMEUVoiceMOOutboundUplift;

	@FindBy(id = "P14_EU_DATA_OUT_UPLIFT")
	private WebElement text_MoMEUDataOutboundUplift;

	@FindBy(id = "P14_ROW_VOICE_MO_OUT_UPLIFT")
	private WebElement text_MoMROWVoiceMOOutboundUplift;

	@FindBy(id = "P14_ROW_DATA_OUT_UPLIFT")
	private WebElement text_MoMROWDataOutboundUplift;

	@FindBy(id = "P14_EU_VOICE_MO_IN_UPLIFT")
	private WebElement text_MoMEUVoiceMOInboundUplift;

	@FindBy(id = "P14_EU_DATA_IN_UPLIFT")
	private WebElement text_MoMEUDataInboundUplift;

	@FindBy(id = "P14_ROW_VOICE_MO_IN_UPLIFT")
	private WebElement text_MoMROWVoiceMOInboundUplift;

	@FindBy(id = "P14_ROW_DATA_IN_UPLIFT")
	private WebElement text_MoMROWDataInboundUplift;

	private void verifyBulkUpdateOverrideSection() {
		CustomReporter.report(STATUS.INFO, "Bulk Uplift Override Section verification");
		com.isElementPresent(data_BulkUpliftOverride_Section, "Bulk Uplift Override Section");
		com.isElementPresent(icon_Section_BulkUpliftOverride_HideShow, "Bulk Uplift Override Section  hide/show icon");
		
		com.click_UsingAction(icon_Section_BulkUpliftOverride_HideShow);
		com.isElementPresent(button_BulkUpdateUplift, "Bulk Update Uplift button");
		com.isElementPresent(text_MoMEUVoiceMOOutboundUplift, "MoM EU Voice MO Outbound Uplift % textfield");
		com.isElementPresent(text_MoMEUDataOutboundUplift, "MoM EU Data Outbound Uplift % textfield");
		com.isElementPresent(text_MoMROWVoiceMOOutboundUplift, "MoM ROW Voice MO Outbound Uplift % textfield");
		com.isElementPresent(text_MoMROWDataOutboundUplift, "MoM ROW Data Outbound Uplift % textfield");
		com.isElementPresent(text_MoMEUVoiceMOInboundUplift, "MoM EU Voice MO Inbound Uplift % textfield");
		com.isElementPresent(text_MoMEUDataInboundUplift, "MoM EU Data Inbound Uplift % textfield");
		com.isElementPresent(text_MoMROWVoiceMOInboundUplift, "MoM EU Data Inbound Uplift % textfield");
		com.isElementPresent(text_MoMROWDataInboundUplift, "MoM ROW Data Inbound Uplift % textfield");
	}

	@FindBy(xpath = "//h2[contains(.,'Flash Report')]")
	private WebElement data_FlashReport_Section;

	@FindBy(xpath = "//button[contains(.,'Freeze Report')]")
	private WebElement button_FreezeReport;

	@FindBy(xpath = "//button[contains(.,'Uplift Override')]")
	private WebElement button_UpliftOverride;

	private List<String> colNames_FlashReport = new ArrayList<>(Arrays.asList("Client PMN Ascending", "Deal/No Deal",
			"Partner PMN", "Region", "Subscriber Type", "Operator Name", "Country", "Traffic Direction", "Service Type",
			"Event Type", "Date to", "Remaining Days in Current month", "Previous Month Volume",
			"Actual Volume Current Month", "Projected Volume Current Month", "MoM Growth", "Uplift",
			"Revised Projected Volume", "Revised MoM Growth", "TAP Charge (GBP) Current Month", "Tap Rate (GBP)",
			"Projected TAP Charge (GBP)", "Net Rate", "NER Snapshot Date/Time", "Projected Net Value",
			"Projected Discount"));
	
	@FindBy(xpath = "//li[contains(.,'1 -')]")
	private WebElement data_Pagination_Trends;
	
	private void verifyFlashReportSection() {
		CustomReporter.report(STATUS.INFO, "Flash Report Section verification");
		com.isElementPresent(data_FlashReport_Section, "Flash Report Section");
		com.isElementPresent(button_FreezeReport, "Freeze Report button");
		com.isElementPresent(button_UpliftOverride, "Uplift Override button");

		com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
		com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
		com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
		comm.verifyActionsPopupItems_WithoutSubscription();

		//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
		comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames_FlashReport, 1);

		com.isElementPresent(data_Pagination_Trends,"Trends section Pagination data "+com.getText(data_Pagination_Trends));
	}
}
