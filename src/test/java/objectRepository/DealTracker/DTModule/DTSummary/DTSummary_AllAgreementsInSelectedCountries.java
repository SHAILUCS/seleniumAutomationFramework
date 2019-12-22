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

public class DTSummary_AllAgreementsInSelectedCountries extends DTSummary_Header {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = DTSummary_Header.title;

	public DTSummary_AllAgreementsInSelectedCountries() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	private final String sectionName="All Agreements In Selected Countries"; 
	
	@FindBy(xpath = "(//h2[contains(.,'"+sectionName+"')])[1]")
	private WebElement data_AllAgreementsInSelectedCountries_Section1;

	@FindBy(xpath = "(//h2[contains(.,'"+sectionName+"')])[2]")
	private WebElement data_AllAgreementsInSelectedCountries_Section2;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'"+sectionName+"')]//button[contains(@class,'hideShow')]")
	private WebElement icon_Section_AllAgreementsInSelectedCountries_HideShow;

	private By table = By.cssSelector("table[summary='"+sectionName+"']");

	private List<String> colNames = new ArrayList<>(Arrays.asList("TRAFFIC_DIRECTION", "SERVICE_TYPE", "EVENT_TYPE",
			"CALCULATION_TYPE", "DISCOUNT_RATE", "DISCOUNT_CURRENCY", "BALANCE_TYPE", "CHARGING_INCREMENT",
			"REBATE_LOWER_BOUND", "REBATE_UPPER_BOUND", "BOUND_TYPE", "REBATE_VALID_FROM", "REBATE_VALID_TO",
			"DISCOUNT_METHOD", "QUALIFYING_SERVICE_TYPE_ID", "QUALIFYING_SERVICE_VOLUME", "CALL_TYPE_LINK",
			"QUALIFYING_EVENT_TYPE_ID", "QUALIFYING_DIRECTION_ID", "DISCOUNT_TYPE", "DISCOUNT_TAX", "FAIR_USAGE_RATE",
			"FAIR_USAGE_THRESHOLD", "DOMESTIC_TOLL_RATE", "AIRTIME_RATE", "DESTN_FLAG", "CAP_TRAFF_MB", "CAP_VALUE",
			"CAP_CURRENCY", "PERC_TOP_USERS", "IOT_AGREEMENT_ID"));

	@FindBy(xpath = "//table[@summary='"+sectionName+"']//parent::div//following-sibling::table[contains(@class,'pagination')]//span[contains(.,'1 -')]")
	private WebElement data_Pagination;
	
	
	public void verify_UI() {
		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_AllAgreementsInSelectedCountries_Section1,
				"All Agreements In Selected Countries Section1");
		com.isElementPresent(data_AllAgreementsInSelectedCountries_Section2,
				"All Agreements In Selected Countries Section2");
		com.isElementPresent(icon_Section_AllAgreementsInSelectedCountries_HideShow,
				"All Agreements In Selected Countries Section Hide Show button");
		com.click_UsingAction(icon_Section_AllAgreementsInSelectedCountries_HideShow);

		// comm.printColumnHeaders(table, 2);
		comm.verifyColumnHeaders(table, colNames, 2);
		
		com.isElementPresent(data_Pagination, "Pagination data "+com.getText(data_Pagination));
	}

}
