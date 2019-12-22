package objectRepository.Reporting;

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

public class ThresholdTrackerMatrixReport {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Threshold Tracker Matrix Report";

	public ThresholdTrackerMatrixReport() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Threshold Tracker Matrix Report')]")
	private WebElement data_ThresholdTrackerMatrixReport_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Report Parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Report Parameters')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_ReportParameters;

	@FindBy(id = "P520_IOT_AGREEMENT_ID")
	private WebElement select_IOTAgreement;

	@FindBy(id = "P520_START_DATE")
	private WebElement text_SartDate;

	@FindBy(id = "P520_END_DATE")
	private WebElement text_EndDate;

	@FindBy(xpath="//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;

	public void verify_UI(String agreementRef,String startDate,String endDate) {

		com.isElementPresent(data_ThresholdTrackerMatrixReport_BreadCrumb,
				"Threshold Tracker Matrix Report heading in Bread Crumb");

		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_ReportParameters_Section, "Report Parameters Section");
		com.isElementPresent(hideshowbutton_ReportParameters, "Report Parameters Section hide show icon");

		CustomReporter.report(STATUS.INFO, "Fields verification");
		com.isElementPresent(select_IOTAgreement, "IOT Agreement dropdown");
		com.isElementPresent(text_SartDate, "Sart Date field");
		com.isElementPresent(text_EndDate, "End Date field");
		com.isElementPresent(button_RefreshReport, "Refresh Report button");

		if (performSearch(agreementRef, startDate, endDate)){
			CustomReporter.report(STATUS.INFO, "Search Results section verification");
			verify_TrafficVolumesVoiceMOActualCumulative_Section();
			verify_TrafficVolumesVoiceMOCommitted_Section();
			verify_AchievedvsCommitment_Section();
		}
	}

	private boolean performSearch(String agreementRef,String startDate,String endDate) {
		String message = "";

		if (agreementRef != null) {
			com.selectByPartialVisibleText(select_IOTAgreement , agreementRef);
			message = message + " | agreementRef= '" + agreementRef + "'";
			com.wait(4);
		}

		if (startDate != null) {
			com.sendKeys(text_SartDate, startDate);
			message = message + " | startDate= '" + startDate + "'";
		}

		if (endDate != null) {
			com.sendKeys(text_EndDate, endDate);
			message = message + " | endDate= '" + endDate + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_RefreshReport);
		if (com.isElementPresent(data_TrafficVolumesVoiceMOActualCumulative_Section, "Search Results")) {
			return true;
		}

		return false;
	}

	@FindBy(xpath = "//h2[contains(.,'Traffic Volumes Voice MO Actual Cumulative')]")
	private WebElement data_TrafficVolumesVoiceMOActualCumulative_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Traffic Volumes Voice MO Actual Cumulative')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_TrafficVolumesVoiceMOActualCumulative;

	private By tab_TrafficVolumesVoiceMOActualCumulative=By.cssSelector("table[summary='Traffic Volumes Voice MO Actual Cumulative']");
	
	private List<String> colNames = new ArrayList<>(Arrays.asList("Partner"));

	private void verify_TrafficVolumesVoiceMOActualCumulative_Section() {
		CustomReporter.report(STATUS.INFO, "'Traffic Volumes Voice MO Actual Cumulative' section content verification");
		com.isElementPresent(data_TrafficVolumesVoiceMOActualCumulative_Section, "Traffic Volumes Voice MO Actual Cumulative section heading");
		com.isElementPresent(hideshowbutton_TrafficVolumesVoiceMOActualCumulative, "Traffic Volumes Voice MO Actual Cumulative section hide show button");
		comm.verifyColumnHeaders(tab_TrafficVolumesVoiceMOActualCumulative, colNames, 1);
	}
	
	@FindBy(xpath = "//h2[contains(.,'Traffic Volumes Voice MO Committed')]")
	private WebElement data_TrafficVolumesVoiceMOCommitted_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Traffic Volumes Voice MO Committed')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_TrafficVolumesVoiceMOCommitted;
	
	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Traffic Volumes Voice MO Committed')]//following-sibling::div[contains(@class,'body')]//span[contains(.,'no data found')]")
	private WebElement data_NoDataFound_TrafficVolumesVoiceMOCommitted;

	private void verify_TrafficVolumesVoiceMOCommitted_Section() {
		CustomReporter.report(STATUS.INFO, "'Traffic Volumes Voice MO Committed' section content verification");
		com.isElementPresent(data_TrafficVolumesVoiceMOCommitted_Section, "Traffic Volumes Voice MO Committed section heading");
		com.isElementPresent(hideshowbutton_TrafficVolumesVoiceMOCommitted, "Traffic Volumes Voice MO Committed section hide show button");
		com.isElementPresent(data_NoDataFound_TrafficVolumesVoiceMOCommitted, "No Data Found Message");
	}
	
	@FindBy(xpath = "//h2[contains(.,'Achieved vs. Commitment (%)')]")
	private WebElement data_AchievedvsCommitment_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Achieved vs. Commitment (%)')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_AchievedvsCommitment;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Achieved vs. Commitment (%)')]//following-sibling::div[contains(@class,'body')]//span[contains(.,'no data found')]")
	private WebElement data_NoDataFound_AchievedvsCommitment;

	private void verify_AchievedvsCommitment_Section() {
		CustomReporter.report(STATUS.INFO, "'Achieved vs. Commitment (%)' section content verification");
		com.isElementPresent(data_AchievedvsCommitment_Section, "Achieved vs. Commitment (%) section heading");
		com.isElementPresent(hideshowbutton_AchievedvsCommitment, "Achieved vs. Commitment (%) section hide show button");
		com.isElementPresent(data_NoDataFound_AchievedvsCommitment, "No Data Found Message");
	}
	
}
