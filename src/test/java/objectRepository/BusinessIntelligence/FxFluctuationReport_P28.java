package objectRepository.BusinessIntelligence;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.Constant;
import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.jsonUtil.JSONManager;
import common.seleniumExceptionHandling.SeleniumMethods;
import database.DBManager;
import database.ContentReader;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreement_P303;
import objectRepository.AgreementCapture.MaintainIOTDiscountAgreementsPage_P301;
import objectRepository.common.ApexCommon;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import tests.MainRegression.HKT_Alerts_7465;

public class FxFluctuationReport_P28 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.FxFluctuationReport_P28.title;

	public FxFluctuationReport_P28() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Fx fluctuation report')]")
	private WebElement breadcrumb_FxFluctuationReport;
	
	@FindBy(xpath = "//div[contains(.,'Report Parameters')]/div/button")
	private WebElement icon_ReportParam_hideshow;
	
	@FindBy(xpath = "//h2[.='Report Parameters']")
	private WebElement section_ReportParameters;
		
	@FindBy(id = "P28_CLIENT_MASTER_ENTITY_ID")
	private WebElement select_HomeOperator;
	
	@FindBy(id = "P28_CURRENCY")
	private WebElement select_Currency;

	@FindBy(id = "P28_COMPARING_FX_MONTH")
	private WebElement select_ComparingFxMonth;

	@FindBy(id = "P28_FX_MONTH_FROM")
	private WebElement select_FxMonthFrom;
	
	@FindBy(id = "P28_FX_MONTH_TO")
	private WebElement select_FxMonthTo;
	
	@FindBy(xpath = "//button[.='Refresh']")
	private WebElement button_Refresh;
	
	@FindBy(xpath = "//h2[contains(.,'Fx fluctuation (%) from')]")
	private WebElement section_FxFluctuationReport;
	
	@FindBy(xpath = "//div[contains(.,'Fx fluctuation (%) from')]/div/button")
	private WebElement icon_FxFluctuationReport_hideshow;
	
	@FindBy(css = "svg")
	private WebElement chart;

	public void verify_UI(String methodName) {
		
		com.isElementPresent(breadcrumb_FxFluctuationReport,"FxFluctuationReport breadcrumb");
		
		CustomReporter.createNode("Report Section Fields");
		JSONManager json=new JSONManager(HKT_Alerts_7465.jsonFilePath, methodName);
		
		com.isElementPresent(section_ReportParameters,"ReportParameters section");
		com.isElementPresent(icon_ReportParam_hideshow,"hideshow icon");
		if(com.isElementPresent(select_HomeOperator,"HomeOperator select")){
			selectHomeOperator(json.getStr("clientName"));
		}
		
		com.isElementPresent(select_Currency,"Currency select");
		com.isElementPresent(select_ComparingFxMonth,"ComparingFxMonth select");
		com.isElementPresent(select_FxMonthFrom,"FxMonthFrom select");
		com.isElementPresent(select_FxMonthTo,"FxMonthTo select");
		com.isElementPresent(button_Refresh,"Refresh button");
		
		CustomReporter.createNode("Fx Fluctuation (%) section content");
		performSearch(json);
		com.isElementPresent(section_FxFluctuationReport,"Fx Fluctuation Report section");
		com.isElementPresent(icon_FxFluctuationReport_hideshow,"Fx Fluctuation Report hide show icon");
	}
	
	public void selectHomeOperator(String homeOp) {
		if (homeOp!=null) {
			CustomReporter.report(STATUS.INFO, "Selecting Home Opeartor =["+homeOp+"]");
			CustomReporter.report(STATUS.INFO, "All options of Home Opeartor =["+com.getAllOptionsVisibleText(select_HomeOperator).toString()+"]");
			com.selectByPartialVisibleText(select_HomeOperator, homeOp);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		}
	}
	
	public void performSearch(JSONManager json) {
		performSearch(json.getStr("currency"), json.getStr("comparingFxMonth"), json.getStr("fxMonthFrom"), json.getStr("fxMonthTo"));
	}

	public void performSearch(String currency,String comparingFxMonth,String fxMonthFrom,String fxMonthTo) {
		String msg="| ";
		if (currency!=null) {
			com.selectByVisibleText(select_Currency, currency);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			msg=msg+"currency= ["+currency+"] | ";
		}
		
		if (comparingFxMonth!=null) {
			com.selectByVisibleText(select_ComparingFxMonth, comparingFxMonth);
			msg=msg+"comparingFxMonth= ["+comparingFxMonth+"] | ";
		}

		if (fxMonthFrom!=null) {
			com.selectByVisibleText(select_FxMonthFrom, fxMonthFrom);
			msg=msg+"fxMonthFrom= ["+fxMonthFrom+"] | ";
		}

		if (fxMonthTo!=null) {
			com.selectByVisibleText(select_FxMonthTo, fxMonthTo);
			msg=msg+"fxMonthTo= ["+fxMonthTo+"] | ";
		}
		
		CustomReporter.report(STATUS.INFO, "Performing search with "+msg);
		
		com.click(button_Refresh);
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		
		com.waitForElementTobe_Visible(chart, "Fx Fluctuation Chart");
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
	}

	public void T05_verifyCurrenciesThatArePartOfActiveAgreements(String methodName) {
		
		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();
		
		String agreementName="AutomationTestingAgreement"+Util.getTimeStamp_InMilliSec();
		
		//CREATING a new agreement with AUD Currency
		JSONManager json= new JSONManager(HKT_Alerts_7465.jsonFilePath, methodName,"createDiscAgreement");
		createAgreementAndVerifyCurrency(agreementName,json);
		
		//EDITING AGREEMENT
		json= new JSONManager(HKT_Alerts_7465.jsonFilePath, methodName,"editForEUR");
		editAgreementAndVerifyCurrency(agreementName, json);
		
		//EDITING AGREEMENT
		json= new JSONManager(HKT_Alerts_7465.jsonFilePath, methodName,"editForGBP");
		editAgreementAndVerifyCurrency(agreementName, json);

		//DELETING THE AGREEMENT
		CustomReporter.createNode("Navigating to Maintain IOT Discount Agreements Page to delete the agreement");
		nav.traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, h.link_AgreementCapture,h.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		if(maintainIOTDiscountAgreementsPage.performSearch(agreementName)){
			maintainIOTDiscountAgreementsPage.clickOnEditLink(agreementName);
			CreateEditIOTDiscountAgreement_P303 createEditIOTDiscountAgreement= new CreateEditIOTDiscountAgreement_P303();
			createEditIOTDiscountAgreement.deleteAgreement();
		}	
		
	}

	private void editAgreementAndVerifyCurrency(String agreementName, JSONManager json) {
		// Editing the agreement with EUR Currency
		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();

		CustomReporter.createNode("Navigating to Maintain IOT Discount Agreements Page to edit the agreement");
		
		nav.traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, h.link_AgreementCapture,h.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		
		String discCurrency=json.getStr("discCurrency");
		if(maintainIOTDiscountAgreementsPage.performSearch(agreementName)){
			maintainIOTDiscountAgreementsPage.clickOnEditLink(agreementName);
			CreateEditIOTDiscountAgreement_P303 createEditIOTDiscountAgreement= new CreateEditIOTDiscountAgreement_P303();
			createEditIOTDiscountAgreement.editDiscAgreement_AllParams(agreementName, json);
		}
		//Verifying the currency on Fx Fluctuation Page
		CustomReporter.createNode("Navigating to Fx Fluctuation Page Page to verify currency");
		nav.traverseMenu_VerifyPageTitle( PagesTitle.FxFluctuationReport_P28, h.link_BusinessIntelligence,h.link_BusinessIntelligence_AlertSubscriptionReports,h.link_BusinessIntelligence_AlertSubscriptionReports_FxFluctuationReport);
		
		String currencyItems=com.getAllOptionsVisibleText(select_Currency).toString();
		if (currencyItems.contains(discCurrency)) {
			CustomReporter.report(STATUS.PASS, discCurrency+" Currency is present in dropdown");
		} else {
			CustomReporter.report(STATUS.FAIL, discCurrency+" Currency is NOT present in dropdown");
		}
		
	}

	private void createAgreementAndVerifyCurrency(String agreementName,JSONManager json) {
		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, h.link_AgreementCapture,h.link_AgreementCapture_MaintainIOTDiscountAgreements);
		
		MaintainIOTDiscountAgreementsPage_P301 p301=new MaintainIOTDiscountAgreementsPage_P301();
		CreateEditIOTDiscountAgreement_P303 p303= p301.clickOnCreateIOTDiscountAgreementButton();
		
		String discCurrency=json.getStr("discCurrency");
		
		p303.createDiscAgreement_AllParams(agreementName, json);

		for (int i = 0; i < json.getJsonArrLength("DiscParam"); i++) {
			p303.addDiscountParameters(i,json);
		}
		
		//Verifying the currency on Fx Fluctuation Page
		CustomReporter.createNode("Navigating to Fx Fluctuation Page Page to verify currency");
		nav.traverseMenu_VerifyPageTitle( PagesTitle.FxFluctuationReport_P28, h.link_BusinessIntelligence,h.link_BusinessIntelligence_AlertSubscriptionReports,h.link_BusinessIntelligence_AlertSubscriptionReports_FxFluctuationReport);
		
		String currencyItems=com.getAllOptionsVisibleText(select_Currency).toString();
		if (currencyItems.contains(discCurrency)) {
			CustomReporter.report(STATUS.PASS, discCurrency+" Currency is present in dropdown");
		} else {
			CustomReporter.report(STATUS.FAIL, discCurrency+" Currency is NOT present in dropdown");
		}
		
	}

	public void verifyFxFluctuationPercent_ForPassedCurrency(String curr, String[] trafPerArr, Map<String, String> currRates,
			Map<String, String> hkdRates) {
		
		CustomReporter.createNode("VERIFYING THE Fx Fluctuation OF ["+curr+"] for TRAFFIC PERIOD "+Arrays.toString(trafPerArr)+" WITH DATABASE");
		
		// Sorting the traff period arrays so that we are not
		// using wrong month for calculation
		Arrays.sort(trafPerArr);
		
		// Converting the passed curr rate to HKD rate || Formula to calculate
		// EUR to HKT exchange rates as (SDR to HKT / SDR to EUR)
		Map<String, String>  curr_To_Hkd_Calc=new HashMap<String, String>();
		for (String traf : trafPerArr) {
			String temp=Util.BD(hkdRates.get(traf)).divide(Util.BD(currRates.get(traf)), 5,RoundingMode.HALF_EVEN).toPlainString();
			curr_To_Hkd_Calc.put(traf, temp);
		}
		CustomReporter.report(STATUS.INFO, "Calculated Conversion Rate of ["+curr+" To HKD] "+curr_To_Hkd_Calc);
		
		// Calculating the Fluctuation || Formula to calculate Fluctuation:
		// Fluctuation % = (Past Month / Current Month) *100 -100
		// This Depends on the the month positioning(Sorting)
		Map<String, String>  fluctuation_Percent_Calc=new HashMap<String, String>();
		for (int cur=0;cur<trafPerArr.length-1;cur++) {
			String latestMonth=trafPerArr[trafPerArr.length-1];
			String latestMonth_Curr_To_Hkd=curr_To_Hkd_Calc.get(latestMonth);
			String currentMonth_Curr_To_Hkd=curr_To_Hkd_Calc.get(trafPerArr[cur]);
			String divisor=Util.BD(currentMonth_Curr_To_Hkd).divide(Util.BD(latestMonth_Curr_To_Hkd), 5, RoundingMode.HALF_EVEN).toPlainString();
			String result=Util.BD(divisor).multiply(Util.BD("100")).subtract(Util.BD("100")).toPlainString();
			fluctuation_Percent_Calc.put(trafPerArr[cur], result);
		} 
		
		CustomReporter.report(STATUS.INFO, "Calculated values of Fluctuation % for passed currency ["+curr+"] "+fluctuation_Percent_Calc);
	
		verifyCalculationResultWithDATABASE(curr, trafPerArr,fluctuation_Percent_Calc);
	}
	
	private void verifyCalculationResultWithDATABASE(String curr, String[] trafPerArr, Map<String, String> fluctuation_Percent_Calc) {
		String query=ContentReader.readLineByLineJava8(Constant.getDbQueriesFolderPath()+"hktAlertsCSVemailData.sql");
		DBManager db = new DBManager();
		ResultSet rs=db.executeQuery(query);
		
		for (int i=1;i<trafPerArr.length-1;i++) {
			String trafP =trafPerArr[i];
			String formatted=Util.convertToString("m/d/yyyy",Util.convertToDate("mmyy", trafP));
			String fluctuationPercentDB=db.getCellText("FLUCTUATION_PERCENT", rs, "PERIOD_START_DATE=="+formatted+" 0:0:0","CURR_TO=="+curr);
			Util.comparator(fluctuation_Percent_Calc.get(trafP), fluctuationPercentDB, "Fx Fluctuation for "+curr+"(" + trafP + ")");
		}
		
		db.closeConnection();
		
	}

}
