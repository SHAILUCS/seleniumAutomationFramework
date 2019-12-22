package objectRepository.Reporting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class RODATReportFileExport {
	private SeleniumMethods com;
	public static String title = "RODAT Report File Export";

	public RODATReportFileExport() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	@FindBy(xpath = "//h1[contains(.,'RODAT Report File Export')]")
	private WebElement data_RODATReportFileExport_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Filter')]")
	private WebElement data_Filter_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Filter')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_Filter;

	@FindBy(id = "P278_LOV_TAG_HOME_CLIENT_ME_ID")
	private WebElement select_TAGHomeOperators;
	
	@FindBy(id = "P278_LOV_TRAFFIC_PERIODS")
	private WebElement select_TrafficPeriods;

	@FindBy(xpath="//button[contains(.,'Generate RODAT Report')]")
	private WebElement button_GenerateRODATReport;
	
	@FindBy(xpath = "//h2[contains(.,'PMNs without operator reference')]")
	private WebElement data_PMNsWithoutOperatorReference_Section;
	
	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'PMNs without operator reference')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_PMNsWithoutOperatorReference;
	
	@FindBy(id = "P278_NOTE_LABEL")
	private WebElement data_Note;
	
	@FindBy(xpath = "//span[.='no data found']")
	private WebElement data_NoDataFound;
	
	
	public void verify_UI() {
		com.isElementPresent(data_RODATReportFileExport_BreadCrumb,
				"RODAT Report File Export heading in Bread Crumb");

		CustomReporter.createNode("Filter Section verification");
		com.isElementPresent(data_Filter_Section, "Filter Section heading");
		com.isElementPresent(hideshowbutton_Filter, "Filter Section hide show icon");

		CustomReporter.createNode("Fields verification");
		//com.isElementPresent(select_TAGHomeOperators, "TAG Home Operators dropdown");
		com.isElementPresent(select_TrafficPeriods, "Traffic Periods dropdown");
		com.isElementPresent(button_GenerateRODATReport, "Generate RODAT Report button");
		
		CustomReporter.createNode("PMNs Without Operator Reference Section verification");
		com.isElementPresent(data_PMNsWithoutOperatorReference_Section, "PMNs Without Operator Reference Section heading");
		com.isElementPresent(hideshowbutton_PMNsWithoutOperatorReference, "PMNs Without Operator Reference Section hide show icon");
		com.isElementPresent(data_Note, "Note={"+com.getText(data_Note)+"}");
		com.isElementPresent(data_NoDataFound, "No Data Found message");
	}
}
