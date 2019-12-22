package objectRepository.DealTracker.BudgetModule;

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

import objectRepository.common.ApexCommon;
import objectRepository.common.Country;
import objectRepository.common.EventType;
import objectRepository.common.ServiceType;
import objectRepository.common.TADIG;
import objectRepository.common.TrafficDirection;

public class BudgetForecastCreation {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Budget/Forecast Creation";

	public BudgetForecastCreation() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	public void verify_UI(String startDate, String endDate, TrafficDirection TrafficDirection,
			ServiceType[] ServiceType, EventType[] EventType, Country[] Country, String region, TADIG[] TADIG,
			String agreementStatus, String negotiator) {

		com.isElementPresent(data_BudgetForecastCreation_BreadCrumb, "Budget/Forecast Creation heading in Bread Crumb");
		verifyReportParametersSection();
		if (performSearch(startDate, endDate, TrafficDirection, ServiceType, EventType, Country, region, TADIG,
				agreementStatus, negotiator)) {
			verifyManipulationSection();
		}

	}

	@FindBy(xpath = "//h1[contains(.,'Budget/Forecast Creation')]")
	private WebElement data_BudgetForecastCreation_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Report Parameters')]//button[contains(@class,'hideShow')]")
	private WebElement icon_Section_BulkUpdateForecastMarketShare_HideShow;

	@FindBy(id = "P22_REPORT_NAME")
	private WebElement data_ReportName;

	@FindBy(id = "P22_START_DATE")
	private WebElement text_StartDate;

	@FindBy(id = "P22_END_DATE")
	private WebElement text_EndDate;

	@FindBy(id = "P22_TRAFFIC_DIRECTION")
	private WebElement select_TrafficDirection;

	@FindBy(id = "P22_IOT_SERVICE_TYPE_IDS_LEFT")
	private WebElement select_ServiceType_Left;

	@FindBy(id = "P22_IOT_SERVICE_TYPE_IDS_RIGHT")
	private WebElement select_ServiceType_Right;

	@FindBy(id = "P22_IOT_EVENT_TYPE_IDS_LEFT")
	private WebElement select_EventType_Left;

	@FindBy(id = "P22_IOT_EVENT_TYPE_IDS_RIGHT")
	private WebElement select_EventType_Right;

	@FindBy(id = "P22_COUNTRY_ID_LEFT")
	private WebElement select_CountryLeft;

	@FindBy(id = "P22_COUNTRY_ID_RIGHT")
	private WebElement select_CountryRight;

	@FindBy(id = "P22_IOT_CLIENT_ZONE_ID")
	private WebElement select_Region;

	@FindBy(id = "P22_PARTNER_MASTER_ENTITY_ID_LEFT")
	private WebElement select_PartnerLeft;

	@FindBy(id = "P22_PARTNER_MASTER_ENTITY_ID_RIGHT")
	private WebElement select_PartnerRight;

	@FindBy(id = "P22_AGREEMENT_STATUS")
	private WebElement select_AgreementStatus;

	@FindBy(id = "P22_NEGOTIATOR_CONTACT_ID")
	private WebElement select_Negotiator;

	@FindBy(xpath = "//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;

	private void verifyReportParametersSection() {
		CustomReporter.report(STATUS.INFO, "Report Parameters Section verification");
		com.isElementPresent(data_ReportParameters_Section, "Report Parameters Section heading");
		com.isElementPresent(icon_Section_BulkUpdateForecastMarketShare_HideShow,
				"Report Parameters Section hide show icon");

		com.isElementPresent(data_ReportName, "Report Name data " + com.getText(data_ReportName));
		com.isElementPresent(text_StartDate, "Start Date field");
		com.isElementPresent(text_EndDate, "End Date field");
		com.isElementPresent(select_TrafficDirection, "Traffic Direction dropdown");
		com.isElementPresent(select_ServiceType_Left, "Service Type Left shuttle");
		com.isElementPresent(select_ServiceType_Right, "Service Type Right shuttle");
		com.isElementPresent(select_EventType_Left, "Event Type Left shuttle");
		com.isElementPresent(select_EventType_Right, "Event Type Right shuttle");
		com.isElementPresent(select_CountryLeft, "Country Left shuttle");
		com.isElementPresent(select_CountryRight, "Country Right shuttle");
		com.isElementPresent(select_Region, "Region dropdown");
		com.isElementPresent(select_PartnerLeft, "Partner Left shuttle");
		com.isElementPresent(select_PartnerRight, "Partner Right shuttle");
		com.isElementPresent(select_AgreementStatus, "Agreement Status dropdown");
		com.isElementPresent(select_Negotiator, "Negotiator dropdown");
		com.isElementPresent(button_RefreshReport, "Refresh Report button");
	}

	private boolean performSearch(String startDate, String endDate, TrafficDirection TrafficDirection,
			ServiceType[] ServiceType, EventType[] EventType, Country[] Country, String region, TADIG[] TADIG,
			String agreementStatus, String negotiator) {
		String message = "";

		if (startDate != null) {
			com.sendKeys(text_StartDate, startDate);
			message = message + " | startDate= '" + startDate + "'";
		}

		if (endDate != null) {
			com.sendKeys(text_EndDate, endDate);
			message = message + " | endDate= '" + endDate + "'";
		}

		if (TrafficDirection != null) {
			com.selectByPartialVisibleText(select_TrafficDirection, TrafficDirection.value);
			message = message + " | TrafficDirection= '" + TrafficDirection.value + "'";
		}

		if (ServiceType != null) {
			comm.selectMultipleValues_DoubleClick_FromArray(select_ServiceType_Left, ServiceType);
			message = message + " | ServiceType= '" + Arrays.toString(ServiceType) + "'";
		}

		if (EventType != null) {
			comm.selectMultipleValues_DoubleClick_FromArray(select_EventType_Left, EventType);
			message = message + " | EventType= '" + Arrays.toString(EventType) + "'";
		}

		if (Country != null) {
			comm.selectMultipleValues_DoubleClick_FromArray(select_CountryLeft, Country);
			message = message + " | Country= '" + Arrays.toString(Country) + "'";
		}

		if (region != null) {
			com.selectByPartialVisibleText(select_Region, region);
			message = message + " | region= '" + region + "'";
		}

		if (TADIG != null) {
			comm.selectMultipleValues_DoubleClick_FromArray(select_PartnerLeft, TADIG);
			message = message + " | Partner= '" + Arrays.toString(TADIG) + "'";
		}

		if (agreementStatus != null) {
			com.selectByPartialVisibleText(select_AgreementStatus, agreementStatus);
			message = message + " | agreementStatus= '" + agreementStatus + "'";
		}

		if (negotiator != null) {
			com.selectByPartialVisibleText(select_Negotiator, negotiator);
			message = message + " | negotiator= '" + negotiator + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_RefreshReport);
		if (com.isElementPresent(data_Manipulation_Section, "Search results")) {
			return true;
		}

		return false;
	}

	@FindBy(xpath = "//h2[contains(.,'Manipulation')]")
	private WebElement data_Manipulation_Section;

	@FindBy(xpath = "//button[contains(.,'Manual Uplift')]")
	private WebElement button_ManualUplift;

	@FindBy(xpath = "//button[contains(.,'Save Report Version')]")
	private WebElement button_SaveReportVersion;

	@FindBy(xpath = "//button[contains(.,'Generate Comparator')]")
	private WebElement button_GenerateComparator;

	@FindBy(xpath = "//button[contains(.,'Finalize Budget')]")
	private WebElement button_FinalizeBudget;

	private List<String> colNames = new ArrayList<>(Arrays.asList("Traffic Period", "Partner PMN", "Operator Name",
			"Country", "Zone", "Traffic Direction", "Service Type", "Event Type", "Actual Forecast", "Negotiator",
			"Volume", "Volume Uplift", "Revised Volume", "TAP Rate", "TAP Rate Uplift", "Revised TAP Rate", "NER",
			"NER Rate Uplift", "Revised NER Rate", "TAP Charge", "Revised TAP Charge", "Post-Discounted Charge",
			"Revised Post-Discounted Charge", "Discount Achieved", "Revised Discount Achieved"));

	@FindBy(xpath = "//span[contains(.,'1 - ')]")
	private WebElement data_Pagination;

	private void verifyManipulationSection() {
		CustomReporter.report(STATUS.INFO, "Manipulation Section verification");
		com.isElementPresent(data_Manipulation_Section, "Manipulation Section heading");

		verifyBulkUpdateVolumesSection();
		verifyBulkUpdateTAPRateSection();
		verifyBulkUpdateNETRateSection();

		com.isElementPresent(button_ManualUplift, "Manual Uplift button");

		com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex toolbar - Search Columns icon");
		com.isElementPresent(comm.text_Search, "Apex toolbar - Search textfield");
		com.isElementPresent(comm.button_Go, "Apex toolbar - Go button");
		com.isElementPresent(comm.select_RowsPerPage, "Apex toolbar - Rows dropdown");
		comm.verifyActionsPopupItems_WithoutSubscription();

		//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
		if(!com.waitForElementTobe_NotVisible(comm.table_ResultTabHeader, 1)){
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);
		}else{
			CustomReporter.report(STATUS.FAIL, "Result Table is not getting displayed");
		}

		com.isElementPresent(button_SaveReportVersion, "Save Report Version button");
		com.isElementPresent(button_GenerateComparator, "Generate Comparator button");
		com.isElementPresent(button_FinalizeBudget, "Finalize Budget button");

		if(!com.waitForElementTobe_NotVisible(data_Pagination,1)){
			com.isElementPresent(data_Pagination, "Pagination data " + com.getText(data_Pagination));
		}else{
			CustomReporter.report(STATUS.FAIL, "Pagination is not getting displayed");
		}
	}

	@FindBy(xpath = "//h2[contains(.,'Bulk Update Volumes')]")
	private WebElement data_BulkUpdateVolumes_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Bulk Update Volumes')]//button[contains(@class,'hideShow')]")
	private WebElement icon_Section_BulkUpdateVolumes_HideShow;

	@FindBy(id = "P22_BULK_VOL_VAL")
	private WebElement text_BulkUpdateVolumes;

	@FindBy(id = "P22_VOL_NOTE")
	private WebElement text_Note_BulkUpdateVolumes;

	@FindBy(id = "P22_VOL_UPDATE")
	private WebElement button_Update_BulkUpdateVolumes;

	private void verifyBulkUpdateVolumesSection() {
		CustomReporter.report(STATUS.INFO, "Bulk Update Volumes Section verification");
		com.isElementPresent(data_BulkUpdateVolumes_Section, "Bulk Update Volumes Section heading");
		com.isElementPresent(icon_Section_BulkUpdateVolumes_HideShow, "Bulk Update Volumes Section hide show icon");
		com.isElementPresent(text_BulkUpdateVolumes, "Bulk Update Volumes field");
		com.isElementPresent(text_Note_BulkUpdateVolumes, "Note field");
		com.isElementPresent(button_Update_BulkUpdateVolumes, "Update button");
	}

	@FindBy(xpath = "//h2[contains(.,'Bulk Update TAP Rate')]")
	private WebElement data_BulkUpdateTAPRate_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Bulk Update TAP Rate')]//button[contains(@class,'hideShow')]")
	private WebElement icon_Section_BulkUpdateTAPRate_HideShow;

	@FindBy(id = "P22_BULK_TAP_VAL")
	private WebElement text_BulkUpdateTAPRate;

	@FindBy(id = "P22_TAP_NOTE")
	private WebElement text_Note_BulkUpdateTAPRate;

	@FindBy(id = "P22_TAP_UPDATE")
	private WebElement button_Update_BulkUpdateTAPRate;

	private void verifyBulkUpdateTAPRateSection() {
		CustomReporter.report(STATUS.INFO, "Bulk Update TAP Rate Section verification");
		com.isElementPresent(data_BulkUpdateTAPRate_Section, "Bulk Update TAP Rate Section heading");
		com.isElementPresent(icon_Section_BulkUpdateTAPRate_HideShow, "Bulk Update TAP Rate Section hide show icon");
		com.isElementPresent(text_BulkUpdateTAPRate, "Bulk Update TAP Rate field");
		com.isElementPresent(text_Note_BulkUpdateTAPRate, "Note field");
		com.isElementPresent(button_Update_BulkUpdateTAPRate, "Update button");
	}

	@FindBy(xpath = "//h2[contains(.,'Bulk Update NET Rate')]")
	private WebElement data_BulkUpdateNETRate_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Bulk Update NET Rate')]//button[contains(@class,'hideShow')]")
	private WebElement icon_Section_BulkUpdateNETRate_HideShow;

	@FindBy(id = "P22_BULK_NET_VAL")
	private WebElement text_BulkUpdateNETRate;

	@FindBy(id = "P22_NET_NOTE")
	private WebElement text_Note_BulkUpdateNETRate;

	@FindBy(id = "P22_NET_UPDATE")
	private WebElement button_Update_BulkUpdateNETRate;

	private void verifyBulkUpdateNETRateSection() {
		CustomReporter.report(STATUS.INFO, "Bulk Update NET Rate Section verification");
		com.isElementPresent(data_BulkUpdateNETRate_Section, "Bulk Update NET Rate Section heading");
		com.isElementPresent(icon_Section_BulkUpdateNETRate_HideShow, "Bulk Update NET Rate Section hide show icon");
		com.isElementPresent(text_BulkUpdateNETRate, "Bulk Update NET Rate field");
		com.isElementPresent(text_Note_BulkUpdateNETRate, "Note field");
		com.isElementPresent(button_Update_BulkUpdateNETRate, "Update button");
	}

}
