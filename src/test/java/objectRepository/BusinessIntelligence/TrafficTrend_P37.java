package objectRepository.BusinessIntelligence;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;
import tests.MainRegression.HKT_Alerts_7465;

public class TrafficTrend_P37 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.TrafficTrend_P37.title;

	public TrafficTrend_P37() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[.='Traffic trend']")
	private WebElement breadcrumb_Traffictrend;
	
	@FindBy(xpath = "//h2[.='Report Parameters']")
	private WebElement section_ReportParameters;
	
	@FindBy(xpath = "//div[contains(.,'Report Parameters')]/div/button")
	private WebElement icon_ReportParam_hideshow;
	
	@FindBy(id = "P37_CLIENT_MASTER_ENTITY_ID")
	private WebElement select_HomeOperator;
	
	@FindBy(id = "P37_PARTNER_MASTER_ENTITY_ID")
	private WebElement select_PartnerOperator;
	
	@FindBy(id = "P37_TREND_TYPE_0")
	private WebElement radio_TrendType_YoY;
	
	@FindBy(id = "P37_TREND_TYPE_1")
	private WebElement radio_TrendType_MoM;

	@FindBy(id = "P37_TP_MONTH_FROM")
	private WebElement select_TrafficPeriodFrom;
	
	@FindBy(id = "P37_TP_MONTH_TO")
	private WebElement select_TrafficPeriodTo;
	
	@FindBy(xpath = "//button[.='Refresh']")
	private WebElement button_Refresh;

	@FindBy(xpath = "//h2[contains(.,'Voice MO Outbound Traffic Trend for partner operator')]")
	private WebElement section_VoiceMoObTrafficTrend;
	
	@FindBy(xpath = "//div[contains(.,'Voice MO Outbound Traffic Trend for partner operator')]/div/button")
	private WebElement icon_VoiceMoObTrafficTrend_hideshow;
	
	@FindBy(xpath = "//h2[contains(.,'Voice MO Inbound Traffic Trend for partner operator')]")
	private WebElement section_VoiceMoIbTrafficTrend;
	
	@FindBy(xpath = "//div[contains(.,'Voice MO Inbound Traffic Trend for partner operator')]/div/button")
	private WebElement icon_VoiceMoIbTrafficTrend_hideshow;

	
	@FindBy(xpath = "//h2[contains(.,'Voice MT Outbound Traffic Trend for partner operator')]")
	private WebElement section_VoiceMtObTrafficTrend;
	
	@FindBy(xpath = "//div[contains(.,'Voice MT Outbound Traffic Trend for partner operator')]/div/button")
	private WebElement icon_VoiceMtObTrafficTrend_hideshow;
	
	
	@FindBy(xpath = "//h2[contains(.,'Voice MT Inbound Traffic Trend for partner operator')]")
	private WebElement section_VoiceMtIbTrafficTrend;
	
	@FindBy(xpath = "//div[contains(.,'Voice MT Inbound Traffic Trend for partner operator')]/div/button")
	private WebElement icon_VoiceMtIbTrafficTrend_hideshow;
	
	
	@FindBy(xpath = "//h2[contains(.,'SMS MO Outbound Traffic Trend for partner operator')]")
	private WebElement section_SmsMoObTrafficTrend;
	
	@FindBy(xpath = "//div[contains(.,'SMS MO Outbound Traffic Trend for partner operator')]/div/button")
	private WebElement icon_SmsMoObTrafficTrend_hideshow;
	
	
	@FindBy(xpath = "//h2[contains(.,'SMS MO Inbound Traffic Trend for partner operator')]")
	private WebElement section_SmsMoIbTrafficTrend;
	
	@FindBy(xpath = "//div[contains(.,'SMS MO Inbound Traffic Trend for partner operator')]/div/button")
	private WebElement icon_SmsMoIbTrafficTrend_hideshow;
	
	
	@FindBy(xpath = "//h2[contains(.,'SMS MT Outbound Traffic Trend for partner operator')]")
	private WebElement section_SmsMtObTrafficTrend;
	
	@FindBy(xpath = "//div[contains(.,'SMS MT Outbound Traffic Trend for partner operator')]/div/button")
	private WebElement icon_SmsMtObTrafficTrend_hideshow;
	
	
	@FindBy(xpath = "//h2[contains(.,'SMS MT Inbound Traffic Trend for partner operator')]")
	private WebElement section_SmsMtIbTrafficTrend;
	
	@FindBy(xpath = "//div[contains(.,'SMS MT Inbound Traffic Trend for partner operator')]/div/button")
	private WebElement icon_SmsMtIbTrafficTrend_hideshow;
	
	
	@FindBy(xpath = "//h2[contains(.,'Data Outbound Traffic Trend for partner operator')]")
	private WebElement section_DataMbObTrafficTrend;
	
	@FindBy(xpath = "//div[contains(.,'Data Outbound Traffic Trend for partner operator')]/div/button")
	private WebElement icon_DataMbObTrafficTrend_hideshow;
	
	
	@FindBy(xpath = "//h2[contains(.,'Data Inbound Traffic Trend for partner operator')]")
	private WebElement section_DataMbIbTrafficTrend;
	
	@FindBy(xpath = "//div[contains(.,'Data Inbound Traffic Trend for partner operator')]/div/button")
	private WebElement icon_DataMbIbTrafficTrend_hideshow;
	
	
	/**
	 * @author shailendra.rajawat
	 * Precondition: Login with a user account then load P37
	 * Postcondition: It will verify all objects on the page, except home operator select 
	 * 		in order to do that, It will perform search. 
	 * */
	public void verify_UI_UserSpecific(String jsonObj) {
		JSONManager json=new JSONManager(HKT_Alerts_7465.jsonFilePath, jsonObj);
		verify_UI_COMMON(json);
	}
	
	
	/**
	 * @author shailendra.rajawat
	 * Precondition: P37 should be loaded
	 * Postcondition: It will verify all objects on the page, 
	 * 		in order to do that, It will fist select home operator then perform search. 
	 * */
	public void verify_UI(String jsonObj) {
		
		CustomReporter.createNode("Traffic Trend Page header");
		com.isElementPresent(breadcrumb_Traffictrend, "breadcrumb_Traffic Trend");
		com.isElementPresent(section_ReportParameters, "section_Report Parameters");
		
		CustomReporter.createNode("Report Parameters Section");
		com.isElementPresent(icon_ReportParam_hideshow, "icon_Report Param_hideshow");
		com.isElementPresent(select_HomeOperator, "select_Home Operator");
		
		JSONManager json=new JSONManager(HKT_Alerts_7465.jsonFilePath, jsonObj);
		selectHomeOperator(json);
		
		verify_UI_COMMON(json);
	}

	/**
	 * @author shailendra.rajawat
	 * Precondition: Partner, Trend Type, Traffic Period from & to should be visible
	 * Postcondition: It will perform search using above mentioned fields and then 
	 * 		will verify search results 
	 * */
	private void verify_UI_COMMON(JSONManager json) {
		com.isElementPresent(select_PartnerOperator,"PartnerOperator select");
		com.isElementPresent(radio_TrendType_YoY,"TrendType_YoY radio");
		com.isElementPresent(radio_TrendType_MoM,"TrendType_MoM radio");
		com.isElementPresent(select_TrafficPeriodFrom,"TrafficPeriodFrom select");
		com.isElementPresent(select_TrafficPeriodTo,"TrafficPeriodTo select");
		com.isElementPresent(button_Refresh,"Refresh button");
		
		performSearch(json);

		com.isElementPresent(section_VoiceMoObTrafficTrend,"section_Voice Mo Ob Traffic Trend");
		com.isElementPresent(icon_VoiceMoObTrafficTrend_hideshow,"icon_Voice Mo Ob Traffic Trend_hideshow");
		
		com.isElementPresent(section_VoiceMoIbTrafficTrend,"section_Voice Mo Ib Traffic Trend");
		com.isElementPresent(icon_VoiceMoIbTrafficTrend_hideshow,"icon_Voice Mo Ib Traffic Trend_hideshow");
		
		com.isElementPresent(section_VoiceMtObTrafficTrend,"section_Voice Mt Ob Traffic Trend");
		com.isElementPresent(icon_VoiceMtObTrafficTrend_hideshow,"icon_Voice Mt Ob Traffic Trend_hideshow");
		
		com.isElementPresent(section_VoiceMtIbTrafficTrend,"section_Voice Mt Ib Traffic Trend");
		com.isElementPresent(icon_VoiceMtIbTrafficTrend_hideshow,"icon_Voice Mt Ib Traffic Trend_hideshow");

		com.isElementPresent(section_SmsMoObTrafficTrend,"section_Sms Mo Ob Traffic Trend");
		com.isElementPresent(icon_SmsMoObTrafficTrend_hideshow,"icon_Sms Mo Ob Traffic Trend_hideshow");

		com.isElementPresent(section_SmsMoIbTrafficTrend,"section_Sms Mo Ib Traffic Trend");
		com.isElementPresent(icon_SmsMoIbTrafficTrend_hideshow,"icon_Sms Mo Ib Traffic Trend_hideshow");
		
		com.isElementPresent(section_SmsMtObTrafficTrend,"section_Sms Mt Ob Traffic Trend");
		com.isElementPresent(icon_SmsMtObTrafficTrend_hideshow,"icon_Sms Mt Ob Traffic Trend_hideshow");

		com.isElementPresent(section_SmsMtIbTrafficTrend,"section_Sms Mt Ib Traffic Trend");
		com.isElementPresent(icon_SmsMtIbTrafficTrend_hideshow,"icon_Sms Mt Ib Traffic Trend_hideshow");

		com.isElementPresent(section_DataMbObTrafficTrend,"section_Data Mb Ob Traffic Trend");
		com.isElementPresent(icon_DataMbObTrafficTrend_hideshow,"icon_Data Mb Ob Traffic Trend_hideshow");
		
		com.isElementPresent(section_DataMbIbTrafficTrend,"section_Data Mb Ib Traffic Trend");
		com.isElementPresent(icon_DataMbIbTrafficTrend_hideshow,"icon_Data Mb Ib Traffic Trend_hideshow");
		
	}
	
	/**
	 * Overloaded
	 * @author shailendra.rajawat
	 * Precondition: P37 should be loaded, and Home Operator should be selected
	 * Postcondition: It will fill rest of the search fields then click on refresh button  
	 * */
	public void performSearch(JSONManager json) {
		performSearch(json.getStr("partner"),json.getBool("yoyOrMom"),json.getStr("trafficPerFrom"),json.getStr("trafficPerTo"));
	}

	
	/**
	 * Overloaded
	 * @author shailendra.rajawat
	 * Precondition: P37 should be loaded, and Home Operator should be selected
	 * Postcondition: It will fill rest of the search fields then click on refresh button  
	 * */
	private void performSearch(String partner, boolean yoyOrMom, String trafficPerFrom, String trafficPerTo) {
		String msg=" | ";
		
		if (partner!=null) {
			com.selectByPartialVisibleText(select_PartnerOperator, partner);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			msg=msg+" partner: "+partner+" | ";
		}
		
		if (yoyOrMom) {
			com.javaScript_Click(radio_TrendType_YoY);
			msg=msg+" YoY: true"+" | ";
		}else{
			com.javaScript_Click(radio_TrendType_MoM);
			msg=msg+" MoM: true"+" | ";
		}
		
		if (trafficPerFrom!=null) {
			com.selectByPartialVisibleText(select_TrafficPeriodFrom, trafficPerFrom);
			msg=msg+" trafficPerFrom: "+trafficPerFrom+" | ";
		}
		
		if (trafficPerTo!=null) {
			com.selectByPartialVisibleText(select_TrafficPeriodTo, trafficPerTo);
			msg=msg+" trafficPerTo: "+trafficPerTo+" | ";
		}
		
		CustomReporter.report(STATUS.INFO, "Performing search with data "+msg);
		com.click(button_Refresh, "Refresh button");
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
	}

	/**
	 * Overloaded
	 * @author shailendra.rajawat
	 * Precondition: P37 should be loaded
	 * Postcondition: It will select Home Operator which eventually load the rest of the 
	 * 		search fields.  
	 * */
	public void selectHomeOperator(JSONManager json) {
		selectHomeOperator(json.getStr("clientName"));
	}
	

	/**
	 * Overloaded
	 * @author shailendra.rajawat
	 * Precondition: P37 should be loaded
	 * Postcondition: It will select Home Operator which eventually load the rest of the 
	 * 		search fields.  
	 * */
	public void selectHomeOperator(String homeOperator) {
		com.selectByPartialVisibleText(select_HomeOperator, homeOperator);
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
	}
}
