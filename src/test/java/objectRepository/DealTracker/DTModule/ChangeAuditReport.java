package objectRepository.DealTracker.DTModule;

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
import objectRepository.common.EventType;
import objectRepository.common.ServiceType;
import objectRepository.common.TADIG;
import objectRepository.common.TrafficDirection;

public class ChangeAuditReport {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Change Audit Report";

	public ChangeAuditReport() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}
	
	@FindBy(xpath = "//h1[contains(.,'Change Audit Report')]")
	private WebElement data_ChangeAuditReport_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(id = "P3_FROM_AUDIT_DATE")
	private WebElement text_FromAuditDate;
	
	@FindBy(id = "P3_TO_AUDIT_DATE")
	private WebElement text_ToAuditDate;
	
	@FindBy(id = "P3_ITEM_CHANGED")
	private WebElement select_ItemChanged;
	
	@FindBy(id = "P3_TRAFFIC_DIRECTION")
	private WebElement select_TrafficDirection;
	
	@FindBy(id = "P3_IOT_SERVICE_TYPE_ID")
	private WebElement select_ServiceType;
	
	@FindBy(id = "P3_IOT_EVENT_TYPE_ID")
	private WebElement select_EventType;

	@FindBy(id = "P3_PARTNER_MEIDS_LEFT")
	private WebElement select_PartnerPMNLeft;

	@FindBy(id = "P3_PARTNER_MEIDS_RIGHT")
	private WebElement select_PartnerPMNRight;
	
	@FindBy(xpath="//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;
	
	@FindBy(xpath = "//h2[contains(.,'Change Audit Report')]")
	private WebElement data_ChangeAuditReport_Section;

	private List<String> colNames = new ArrayList<>(Arrays.asList("Audit Date Descending", "Updated By", "Partner Name",
			"Partner PLMN", "Country Name", "Traffic Period", "Traffic Direction", "Service Type", "Event Type",
			"Item Changed", "New Value", "Old Value", "Note", "Reason"));

	@FindBy(xpath = "//span[contains(.,'1 -')]")
	private WebElement data_PaginationText;
	
	public void verify_UI(String fromAuditDate, String toAuditDate, String itemChanged, TrafficDirection TrafficDirection,ServiceType ServiceType,EventType EventType,TADIG TADIG) {
		
		com.isElementPresent(data_ChangeAuditReport_BreadCrumb,
				"Change Audit Report heading in Bread Crumb");

		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_ReportParameters_Section, "Report Parameters Section");

		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(text_FromAuditDate, "From Audit Date field");
		com.isElementPresent(text_ToAuditDate, "To Audit Date field");
		com.isElementPresent(select_ItemChanged, "Item Changed dropdown");
		com.isElementPresent(select_TrafficDirection, "Traffic Direction dropdown");
		com.isElementPresent(select_ServiceType, "Service Type dropdown");
		com.isElementPresent(select_EventType, "Event Type dropdown");
		com.isElementPresent(select_PartnerPMNLeft, "Partner PMN Left shuttle");
		com.isElementPresent(select_PartnerPMNRight, "Partner PMN Right shuttle");
		com.isElementPresent(button_RefreshReport, "Refresh Report button");

		
		if (performSearch(fromAuditDate, toAuditDate, itemChanged, TrafficDirection, ServiceType, EventType, TADIG)) {
			CustomReporter.report(STATUS.INFO, "Search Results section verification");
			com.isElementPresent(data_ChangeAuditReport_Section, "Change Audit Report Section");

			com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
			com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
			com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
			com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
			comm.verifyActionsPopupItems_WithSubscription();

			//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);

			com.isElementPresent(data_PaginationText, "Pagination " + com.getText(data_PaginationText));	
		}
	}

	private boolean performSearch(String fromAuditDate, String toAuditDate, String itemChanged, TrafficDirection TrafficDirection,ServiceType ServiceType,EventType EventType,TADIG TADIG) {
		String message = "";

		if (fromAuditDate != null) {
			com.sendKeys(text_FromAuditDate, fromAuditDate);
			message = message + " | fromAuditDate= '" + fromAuditDate + "'";
		}

		if (toAuditDate != null) {
			com.sendKeys(text_ToAuditDate, toAuditDate);
			message = message + " | toAuditDate= '" + toAuditDate + "'";
		}

		if (itemChanged != null) {
			com.selectByPartialVisibleText(select_ItemChanged, itemChanged);
			message = message + " | itemChanged= '" + itemChanged + "'";
		}

		if (TrafficDirection != null) {
			com.selectByVisibleText(select_TrafficDirection, TrafficDirection.value);
			message = message + " | TrafficDirection= '" + TrafficDirection.value + "'";
		}

		if (ServiceType != null) {
			com.selectByVisibleText(select_ServiceType, ServiceType.value);
			message = message + " | ServiceType= '" + ServiceType.value + "'";
		}

		if (EventType != null) {
			com.selectByVisibleText(select_EventType, EventType.value);
			message = message + " | EventType= '" + EventType.value + "'";
		}

		if (TADIG != null) {
			com.selectByPartialVisibleText_DoubleClick(select_PartnerPMNLeft, TADIG.value);
			message = message + " | TADIG= '" + TADIG.value + "'";
		}
		
		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_RefreshReport);
		if (com.isElementPresent(comm.table_ResultTabHeader, "Result Table")) {
			return true;
		}

		return false;
	}


}
