package objectRepository.Operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.jsonUtil.JSONManager;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;

public class AlertSubscription_P26 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.AlertSubscription_P26.title;

	public AlertSubscription_P26() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	private List<String> colNames = new ArrayList<>(Arrays.asList());
	
	@FindBy(xpath = "//h1[.='Alert Subscription']")
	private WebElement breadcrumb_AlertSubscription;
	
	@FindBy(xpath = "//h2[.='Alert Subscription']")
	private WebElement section_AlertSubscription;
	
	@FindBy(id = "P26_CLIENT_MASTER_ENTITY_ID")
	private WebElement select_HomeOperator;
	
	@FindBy(id = "P26_TRAFFIC_TREND_0")
	private WebElement checkbox_TrafficTrend;
	
	@FindBy(id = "P26_FX_FLUCTUATION_0")
	private WebElement checkbox_FxFluctuation;

	@FindBy(xpath = "//h2[.='Traffic trend']")
	private WebElement section_TrafficTrend;
	
	@FindBy(id = "P26_TT_ALERT_ENABLED_0")
	private WebElement checkbox_TrafficTrend_AlertEnabled;
	
	@FindBy(id = "P26_TT_ALERT_SCHEDULED_DATE_DISPLAY")
	private WebElement data_TrafficTrend_AlertScheduledDate;
	
	@FindBy(id = "P26_TT_ALERT_NAME_DISPLAY")
	private WebElement data_TrafficTrend_AlertName;
	
	@FindBy(id = "P26_TT_ALERT_TEXT_DISPLAY")
	private WebElement data_TrafficTrend_AlertText;
	
	@FindBy(id = "P26_TT_EMAIL_DISPLAY")
	private WebElement data_TrafficTrend_Email;
	
	@FindBy(id = "P26_TT_MOM_0")
	private WebElement checkbox_Mom;
	
	@FindBy(id = "P26_TT_YOY_0")
	private WebElement checkbox_Yoy;
	
	@FindBy(id = "P26_TT_GROWTH_THRESHOLD_DISPLAY")
	private WebElement data_GrowthThreshold;
	
	@FindBy(id = "P26_TT_DROP_THRESHOLD_DISPLAY")
	private WebElement data_DropThreshold;
	
	@FindBy(xpath = "//h2[contains(.,'Thresholds per PMNs')]")
	private WebElement section_ThresholdsPerPmnsOperators;
	
	private By table_ThresholdPerPmn=By.cssSelector("table[summary*='Thresholds per PMNs']");
	
	@FindBy(xpath = "(//button[.='Edit'])[1]")
	public WebElement button_TrafficTrend_Edit;
	
	@FindBy(xpath = "//h2[.='Fx Fluctuation']")
	private WebElement section_FxFluctuation;
	
	@FindBy(id = "P26_FX_ALERT_ENABLED_0")
	private WebElement checkbox_FxAlert_AlertEnabled;
	
	@FindBy(id = "P26_FX_ALERT_SCHEDULED_DATE_DISPLAY")
	private WebElement data_FxAlert_AlertScheduledDate;
	
	@FindBy(id = "P26_FX_ALERT_NAME_DISPLAY")
	private WebElement data_FxAlert_AlertName;
	
	@FindBy(id = "P26_FX_ALERT_TEXT_DISPLAY")
	private WebElement data_FxAlert_AlertText;
	
	@FindBy(id = "P26_FX_EMAIL_DISPLAY")
	private WebElement data_FxAlert_Email;
	
	@FindBy(id = "P26_FX_FLUCTUATION_THRESHOLD_DISPLAY")
	private WebElement data_FxAlert_FxFluctuationAlertThreshold;
	
	@FindBy(id = "P26_FX_COMPARISON_MONTHS_NUM_DISPLAY")
	private WebElement data_FxAlert_NoOfComparisonMonths;
	
	@FindBy(xpath = "(//button[.='Edit'])[2]")
	private WebElement button_FxAlert_Edit;
	
	public void verify_UI() {
		com.isElementPresent(breadcrumb_AlertSubscription,"AlertSubscription breadcrumb");
		
		CustomReporter.createNode("Performing Search with HKGTC");
		com.selectByPartialVisibleText(select_HomeOperator, "HKGTC");
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		
		CustomReporter.createNode("Alert Subscription Section");
		com.isElementPresent(section_AlertSubscription,"AlertSubscription section");
		com.isElementPresent(select_HomeOperator,"HomeOperator select");
		com.isElementPresent(checkbox_TrafficTrend,"TrafficTrend checkbox");
		com.isElementPresent(checkbox_FxFluctuation,"FxFluctuation checkbox");

		CustomReporter.createNode("Traffic Trend Section");
		com.isElementPresent(section_TrafficTrend,"TrafficTrend section");
		com.isElementPresent(checkbox_TrafficTrend_AlertEnabled,"TrafficTrend_AlertEnabled checkbox");
		com.isElementPresent(data_TrafficTrend_AlertScheduledDate,"TrafficTrend_AlertScheduledDate data");
		com.isElementPresent(data_TrafficTrend_AlertName,"TrafficTrend_AlertName data");
		com.isElementPresent(data_TrafficTrend_AlertText,"TrafficTrend_AlertText data");
		com.isElementPresent(data_TrafficTrend_Email,"TrafficTrend_Email data");
		com.isElementPresent(checkbox_Mom,"Mom checkbox");
		com.isElementPresent(checkbox_Yoy,"Yoy checkbox");
		com.isElementPresent(data_GrowthThreshold,"GrowthThreshold data");
		com.isElementPresent(data_DropThreshold,"DropThreshold data");
		com.isElementPresent(section_ThresholdsPerPmnsOperators,"ThresholdsPerPmnsOperators section");
		com.isElementPresent(table_ThresholdPerPmn,"ThresholdPerPmn table");
		com.isElementPresent(button_TrafficTrend_Edit,"TrafficTrend_Edit button");

		CustomReporter.createNode("Fx Fluctuation Section");
		com.isElementPresent(section_FxFluctuation,"FxFluctuation section");
		com.isElementPresent(checkbox_FxAlert_AlertEnabled,"FxAlert_AlertEnabled checkbox");
		com.isElementPresent(data_FxAlert_AlertScheduledDate,"FxAlert_AlertScheduledDate data");
		com.isElementPresent(data_FxAlert_AlertName,"FxAlert_AlertName data");
		com.isElementPresent(data_FxAlert_AlertText,"FxAlert_AlertText data");
		com.isElementPresent(data_FxAlert_Email,"FxAlert_Email data");
		com.isElementPresent(data_FxAlert_FxFluctuationAlertThreshold,"FxFluctuationAlertThreshold data");
		com.isElementPresent(data_FxAlert_NoOfComparisonMonths,"NoOfComparisonMonths data");
		com.isElementPresent(button_FxAlert_Edit,"FxAlert_Edit button");
	}

	/**
	 * Precondition - p26 should be loaded
	 * Postcondition - Will select Home Op then navigate to Fx-Fluctuation P27, and verify title  
	 * */
	public FxFluctuation_P27 selectHomeOpThenClickOnFxFluctEdit(String homeOp) {
		com.selectByPartialVisibleText(select_HomeOperator, homeOp);
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		
		if(!com.isSelected(checkbox_FxFluctuation)){
			com.javaScript_Click(checkbox_FxFluctuation);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		}
		
		return clickOnFxFluctEdit();
	}

	/**
	 * Precondition - Home Operator should be already selected
	 * Postcondition - Will navigate to Fx-Fluctuation P27, and verify title  
	 * */
	public FxFluctuation_P27 clickOnFxFluctEdit() {
		com.click(button_FxAlert_Edit, "button_Fx Alert_Edit");
		if (com.verifyPageTitle(FxFluctuation_P27.title, true)) {
			return new FxFluctuation_P27();
		}
		return null;
	}
	
	/**
	 * Precondition - p26 should be loaded
	 * Postcondition - Will select Home Op then navigate to Traffic Trend p31, and verify title  
	 * */
	public TrafficTrend_P31 selectHomeOpThenClickOnTrafTrendEdit(String homeOp) {
		com.selectByPartialVisibleText(select_HomeOperator, homeOp, true);
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		
		if(!com.isSelected(checkbox_FxFluctuation)){
			com.javaScript_Click(checkbox_FxFluctuation);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		}
		
		return clickOnTrafTrendEdit();
	}
	
	/**
	 * Precondition - Home Operator should be already selected
	 * Postcondition - Will navigate to Traffic Trend P31, and verify title  
	 * */
	public TrafficTrend_P31 clickOnTrafTrendEdit() {
		com.click(button_TrafficTrend_Edit, "button_Traffic Trend_Edit");
		if (com.verifyPageTitle(TrafficTrend_P31.title, true)) {
			return new TrafficTrend_P31();
		}
		return null;
	}


	public void verifyFxFluctSectionData(JSONManager json) {
		
		Util.comparator_NonNumbers(json.getBool("alertEnabled")+"", com.isSelected(checkbox_FxAlert_AlertEnabled)+"", "Alert Enabled");
		
		String txt=com.getText(data_FxAlert_AlertScheduledDate);
		Util.comparator_NonNumbers(json.getStr("alertScheduledDate"), txt, "Alert Scheduled Date");

		txt=com.getText(data_FxAlert_AlertName);
		Util.comparator_NonNumbers(json.getStr("alertName"), txt, "Alert Name");

		txt=com.getText(data_FxAlert_AlertText);
		Util.comparator_NonNumbers(json.getStr("alertText"), txt, "Alert Text");

		txt=com.getText(data_FxAlert_Email);
		Util.comparator_NonNumbers(json.getStr("email"), txt, "Email");

		txt=com.getText(data_FxAlert_FxFluctuationAlertThreshold);
		Util.comparator_NonNumbers(json.getStr("threshold"), txt, "Threshold");

		txt=com.getText(data_FxAlert_NoOfComparisonMonths);
		Util.comparator_NonNumbers(json.getStr("comparisionMonths"), txt, "No Of Comparison Months");
	}

	public void verifyTrafficTrendSectionData(JSONManager json) {
		Util.comparator_NonNumbers(json.getBool("alertEnabled")+"", com.isSelected(checkbox_TrafficTrend_AlertEnabled)+"", "Alert Enabled");
		
		String txt=com.getText(data_TrafficTrend_AlertScheduledDate);
		Util.comparator_NonNumbers(json.getStr("alertScheduledDate"), txt, "Alert Scheduled Date");

		txt=com.getText(data_TrafficTrend_AlertName);
		Util.comparator_NonNumbers(json.getStr("alertName"), txt, "Alert Name");

		txt=com.getText(data_TrafficTrend_AlertText);
		Util.comparator_NonNumbers(json.getStr("alertText"), txt, "Alert Text");

		txt=com.getText(data_TrafficTrend_Email);
		Util.comparator_NonNumbers(json.getStr("email"), txt, "Email");

		Util.comparator_NonNumbers(json.getBool("mom")+"", com.isSelected(checkbox_Mom)+"", "MoM");
		
		Util.comparator_NonNumbers(json.getBool("yoy")+"", com.isSelected(checkbox_Yoy)+"", "YoY");
		
		txt=com.getText(data_GrowthThreshold);
		Util.comparator_NonNumbers(json.getStr("growthThreshold"), txt, "Growth Threshold");

		txt=com.getText(data_DropThreshold);
		Util.comparator_NonNumbers(json.getStr("dropThreshold"), txt, "Drop Threshold");
	}

}
