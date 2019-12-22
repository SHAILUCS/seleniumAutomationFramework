package objectRepository.DealTracker.DTModule.DTSummary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;

public class DTSummary_EditTAPRate extends DTSummary_Charges{
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Edit TAP Rate";

	public DTSummary_EditTAPRate() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	public DTSummary_EditTAPRate navigateTo_EditTapRate() {
		com.click(button_EditTapRate, "Edit Tap Rate button");
		if (com.verifyPageTitle(title, true)) {
			return this;
		}
		return null;
	}

	@FindBy(xpath = "//h1[contains(.,'Edit TAP Rate')]")
	private WebElement data_EditTapRate_Breadcrumb;

	@FindBy(xpath = "//h2[contains(.,'Parameters')]")
	private WebElement data_Parameters_Section;

	@FindBy(id = "P15_TRAFFIC_DIRECTION_LEFT")
	private WebElement select_TrafficDirection_Left;

	@FindBy(id = "P15_TRAFFIC_DIRECTION_RIGHT")
	private WebElement select_TrafficDirection_Right;

	@FindBy(id = "P15_COUNTRY_ID_LEFT")
	private WebElement select_Country_Left;

	@FindBy(id = "P15_COUNTRY_ID_RIGHT")
	private WebElement select_Country_Right;

	@FindBy(id = "P15_SERVICE_TYPE_ID_LEFT")
	private WebElement select_ServiceType_Left;

	@FindBy(id = "P15_SERVICE_TYPE_ID_RIGHT")
	private WebElement select_ServiceType_Right;

	@FindBy(id = "P15_EVENT_TYPE_ID_LEFT")
	private WebElement select_EventType_Left;

	@FindBy(id = "P15_EVENT_TYPE_ID_RIGHT")
	private WebElement select_EventType_Right;

	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	private WebElement button_Refresh;

	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	private WebElement button_Cancel;

	@FindBy(xpath = "//h2[contains(.,'TAP Rate')]")
	private WebElement data_TapRate_Section;

	@FindBy(id = "P15_NOTE")
	private WebElement text_Note;

	@FindBy(xpath = "//button[contains(.,'Save Changes to TAP Rate')]")
	private WebElement button_SaveChangesToTapRate;

	private By tab = By.cssSelector("table[summary='TAP Rate']");

	private List<String> colNames = new ArrayList<>(Arrays.asList("Roaming Partner", "Partner PMN", "Country Name",
			"Traffic Direction", "Service Type", "Event Type", "Jan 18", "Feb 18", "Mar 18", "Apr 18", "May 18",
			"Jun 18", "Jul 18", "Aug 18", "Sep 18", "Oct 18", "Nov 18", "Dec 18", "Jan 19", "Feb 19", "Mar 19",
			"Apr 19", "May 19", "Jun 19", "Jul 19", "Aug 19", "Sep 19", "Oct 19", "Nov 19", "Dec 19"));

	@FindBy(linkText = "Download")
	private WebElement link_Download;

	@FindBy(xpath = "//table[@summary='TAP Rate']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination;
	
	public void verify_UI() {
		CustomReporter.report(STATUS.INFO, "Verious Section headers verification");
		com.isElementPresent(data_EditTapRate_Breadcrumb, "Edit TAP Rate breadcrumb");
		com.isElementPresent(data_Parameters_Section, "Parameters Section");
		com.isElementPresent(data_TapRate_Section, "TAP Rate Section");

		CustomReporter.report(STATUS.INFO, "Parameter Section fields verification");
		com.isElementPresent(select_TrafficDirection_Left, "Traffic Direction - Left shuttle");
		com.isElementPresent(select_TrafficDirection_Right, "Traffic Direction - Right shuttle");
		com.isElementPresent(select_Country_Left, "Country - Left shuttle");
		com.isElementPresent(select_Country_Right, "Country - Right shuttle");
		com.isElementPresent(select_ServiceType_Left, "Service Type - Left shuttle");
		com.isElementPresent(select_ServiceType_Right, "Service Type - Right shuttle");
		com.isElementPresent(select_EventType_Left, "Event Type - Left shuttle");
		com.isElementPresent(select_EventType_Right, "Event Type - Right shuttle");
		com.isElementPresent(button_Refresh, "Refresh Button");
		com.isElementPresent(button_Cancel, "Cancel Button");

		CustomReporter.report(STATUS.INFO, "TAP Rate Section fields verification");
		com.isElementPresent(text_Note, "Note field");
		com.isElementPresent(button_SaveChangesToTapRate, "Save Changes To TAP Rate");

		//comm.printColumnHeaders(tab, 1);
		comm.verifyColumnHeaders(tab, colNames, 1);

		com.isElementPresent(link_Download, "Download Link");
		com.isElementPresent(data_Pagination, "Pagination data "+com.getText(data_Pagination));
	}

}
