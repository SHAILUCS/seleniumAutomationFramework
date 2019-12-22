package tests.MainRegression;

import java.util.Map;

import org.testng.annotations.Test;

import com.configData_Util.Constant;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.jsonUtil.JSONManager;

import objectRepository.BusinessIntelligence.FxFluctuationReport_P28;
import objectRepository.BusinessIntelligence.TrafficTrend_P37;
import objectRepository.FCHDataLoading.SDRConversionRates_P392;
import objectRepository.Operations.AlertSubscription_P26;
import objectRepository.Operations.FxFluctuation_P27;
import objectRepository.Operations.TrafficTrend_P31;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.NGCHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

public class HKT_Alerts_7465 {

	public static final String jsonFilePath = Constant.getResourcesFolderPath() + "HKT_Alerts_7465.json";

	@Test(groups="UI with Func",
			description = "T01_Alert Subscription page P26 content verification",
			dependsOnMethods={"T04_FxFluctuationPageP27_VerifyContent","T11_TrafficTrendReportP37_VerifyContent"})
	private void T01_AlertSubscriptionP26_VerifyContent() {
		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();
		
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AlertSubscription_P26, h.link_Operations, h.link_Operations_IOTRONUserConfiguration, h.link_Operations_IOTRONUserConfiguration_AlertSubscription);		
		AlertSubscription_P26 p26=new AlertSubscription_P26();
		p26.verify_UI();
	}

	@Test(groups="CRUD with Cancel button check",
			description = "T02_Fx Fluctuation PageP27_Verify CRUD Operations")
	private void T02_FxFluctuationPageP27_CRUD() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();

		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AlertSubscription_P26, h.link_Operations, h.link_Operations_IOTRONUserConfiguration, h.link_Operations_IOTRONUserConfiguration_AlertSubscription);		
		AlertSubscription_P26 p26=new AlertSubscription_P26();

		JSONManager json=new JSONManager(jsonFilePath, methodName);
		FxFluctuation_P27 p27=p26.selectHomeOpThenClickOnFxFluctEdit(json.getStr("clientName"));
		p27.verify_CRUD(methodName);
	}

	@Test(groups="Validations Check",
			description = "T03_Fx Fluctuation PageP27_Verify Validations",
			dependsOnMethods={"T02_FxFluctuationPageP27_CRUD"}
			)
	private void T03_FxFluctuationPageP27_VerifyValidations() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();

		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AlertSubscription_P26, h.link_Operations, h.link_Operations_IOTRONUserConfiguration, h.link_Operations_IOTRONUserConfiguration_AlertSubscription);		
		AlertSubscription_P26 p26=new AlertSubscription_P26();

		JSONManager json=new JSONManager(jsonFilePath, methodName);
		FxFluctuation_P27 p27=p26.selectHomeOpThenClickOnFxFluctEdit(json.getStr("clientName"));
		p27.verify_Validations();
	}
	
	@Test(groups="UI Only",
			description = "T04_Fx Fluctuation PageP27_Verify Content",
			dependsOnMethods={"T03_FxFluctuationPageP27_VerifyValidations"})
	private void T04_FxFluctuationPageP27_VerifyContent() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();

		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AlertSubscription_P26, h.link_Operations, h.link_Operations_IOTRONUserConfiguration, h.link_Operations_IOTRONUserConfiguration_AlertSubscription);		
		AlertSubscription_P26 p26=new AlertSubscription_P26();

		JSONManager json=new JSONManager(jsonFilePath, methodName);
		FxFluctuation_P27 p27=p26.selectHomeOpThenClickOnFxFluctEdit(json.getStr("clientName"));
		p27.verify_UI();
	}

	@Test(groups="Func Only",
			description = "T05_On Fx fluctuation report page all currencies that are part of "
					+ "active agreements where HKT is home operator should be displayed "
					+ "and calculated vs HKD (Hongkong Dollar)",
					dependsOnMethods={"T04_FxFluctuationPageP27_VerifyContent"})
	private void T05_verify_Currencies_On_FxFluctuationPageP27_For_ActiveAgreement() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		FxFluctuationReport_P28 p28=new FxFluctuationReport_P28();
		p28.T05_verifyCurrenciesThatArePartOfActiveAgreements(methodName);
	}

	@Test(groups="UI with Func",
			description = "T06_Fx Fluctuation ReportP28_Verify Content",
					dependsOnMethods={"T05_verify_Currencies_On_FxFluctuationPageP27_For_ActiveAgreement"})
	private void T06_FxFluctuationReportP28_VerifyContent() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();

		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.FxFluctuationReport_P28,
				h.link_BusinessIntelligence, h.link_BusinessIntelligence_AlertSubscriptionReports,
				h.link_BusinessIntelligence_AlertSubscriptionReports_FxFluctuationReport);		

		FxFluctuationReport_P28 p28=new FxFluctuationReport_P28();
		p28.verify_UI(methodName);
	}
	
	@Test(groups="UI with Func",
			description = "T07_Fx Fluctuation Report P28 Graph Results",
					dependsOnMethods={"T03_FxFluctuationPageP27_VerifyValidations"})
	private void T07_FxFluctuationReportP28GraphResults() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();
		NGCHomePage n=new NGCHomePage();
		
		// Navigating to SDRConversionRates_P392 page for getting the conversion Rates
		CustomReporter.createNode("Navigating to SDRConversionRates_P392 page for getting the conversion Rates");
		nav.traverseMenu_VerifyPageTitle( PagesTitle.SDRConversionRates_P392, n.link_DataLoading, n.link_DataLoading_SDRRates, n.link_DataLoading_SDRConversionRates);
		SDRConversionRates_P392 p392=new SDRConversionRates_P392();
		
		JSONManager json=new JSONManager(jsonFilePath, methodName,"sdrConvRates");
		String[] currenciesArr=Util.getArray("HKD","AUD","EUR","GBP");
		String[] trafPerArr=json.getStrArr("trafPeriods");
		p392.fetchConversionRatesAnd_InitMatrix(currenciesArr, trafPerArr);
		Map<String, String> hkdRates=p392.getConversionRate_ForPassedCurrency("HKD");
		Map<String, String> audRates=p392.getConversionRate_ForPassedCurrency("AUD");
		Map<String, String> eurRates=p392.getConversionRate_ForPassedCurrency("EUR");
		Map<String, String> gbpRates=p392.getConversionRate_ForPassedCurrency("GBP");
		
		
		// Navigating to FxFluctuation_P27 edit page for ensuring data is set up properly prior to start main test
		CustomReporter.createNode("Navigating to FxFluctuation_P27 edit page for ensuring data is set up properly prior to start main test");
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AlertSubscription_P26, h.link_Operations, h.link_Operations_IOTRONUserConfiguration, h.link_Operations_IOTRONUserConfiguration_AlertSubscription);		
		AlertSubscription_P26 p26=new AlertSubscription_P26();

		json=new JSONManager(jsonFilePath, methodName,"editFxFluctuation");
		FxFluctuation_P27 p27=p26.selectHomeOpThenClickOnFxFluctEdit(json.getStr("clientName"));
		p27.saveFxFluctuation(json);
		
		// Navigating to FxFluctuationReport_P28 report page for main test
		CustomReporter.createNode("Navigating to FxFluctuationReport_P28 report page for main test");
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.FxFluctuationReport_P28,
				h.link_BusinessIntelligence, h.link_BusinessIntelligence_AlertSubscriptionReports,
				h.link_BusinessIntelligence_AlertSubscriptionReports_FxFluctuationReport);
		
		FxFluctuationReport_P28 p28=new FxFluctuationReport_P28();
		p28.selectHomeOperator(json.getStr("clientName"));
			
		json=new JSONManager(jsonFilePath, methodName,"fxFluctReport");
		p28.performSearch("AUD", json.getStr("comparingFxMonth"), json.getStr("fxMonthFrom"), json.getStr("fxMonthTo"));
		p28.verifyFxFluctuationPercent_ForPassedCurrency("AUD",trafPerArr,audRates,hkdRates);
		p28.verifyFxFluctuationPercent_ForPassedCurrency("EUR",trafPerArr,eurRates,hkdRates);
		p28.verifyFxFluctuationPercent_ForPassedCurrency("GBP",trafPerArr,gbpRates,hkdRates);
 			
	}
	
	
	@Test(groups="UI with Func",
			description = "T08_Traffic TrendP31_Verify Content",
			dependsOnMethods={"T10_TrafficTrendPageP31_VerifyValidations"})
	private void T08_TrafficTrendP31_VerifyContent() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();

		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AlertSubscription_P26, h.link_Operations, h.link_Operations_IOTRONUserConfiguration, h.link_Operations_IOTRONUserConfiguration_AlertSubscription);		
		AlertSubscription_P26 p26=new AlertSubscription_P26();
		
		JSONManager json=new JSONManager(jsonFilePath, methodName);
		TrafficTrend_P31 p31=p26.selectHomeOpThenClickOnTrafTrendEdit(json.getStr("clientName"));
		p31.verify_UI();
		
	}
	
	@Test(groups="Func Only",
			description = "T09_Traffic Trend PageP31_CRUD")
	private void T09_TrafficTrendPageP31_CRUD() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();

		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AlertSubscription_P26, h.link_Operations, h.link_Operations_IOTRONUserConfiguration, h.link_Operations_IOTRONUserConfiguration_AlertSubscription);		
		AlertSubscription_P26 p26=new AlertSubscription_P26();
		
		JSONManager json=new JSONManager(jsonFilePath, methodName);
		TrafficTrend_P31 p31=p26.selectHomeOpThenClickOnTrafTrendEdit(json.getStr("clientName"));
		p31.verify_CRUD(methodName);
	}

	@Test(groups="Func Only",
			description = "T10_Traffic Trend PageP31_Verify Validations",
			dependsOnMethods={"T09_TrafficTrendPageP31_CRUD"})
	private void T10_TrafficTrendPageP31_VerifyValidations() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();

		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AlertSubscription_P26, h.link_Operations, h.link_Operations_IOTRONUserConfiguration, h.link_Operations_IOTRONUserConfiguration_AlertSubscription);		
		AlertSubscription_P26 p26=new AlertSubscription_P26();
		
		JSONManager json=new JSONManager(jsonFilePath, methodName);
		TrafficTrend_P31 p31=p26.selectHomeOpThenClickOnTrafTrendEdit(json.getStr("clientName"));
		p31.verify_Validations(methodName);
		
	}
	
	@Test(groups="Func Only",
			description = "T11_Traffic Trend Report P37_Verify Content",
					dependsOnMethods={"T10_TrafficTrendPageP31_VerifyValidations"})
	private void T11_TrafficTrendReportP37_VerifyContent() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();

		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.TrafficTrend_P37,
				h.link_BusinessIntelligence, h.link_BusinessIntelligence_AlertSubscriptionReports,
				h.link_BusinessIntelligence_AlertSubscriptionReports_TrafficTrendReport);		

		TrafficTrend_P37 p37=new TrafficTrend_P37();
		p37.verify_UI(methodName);
	}
	
	@Test(groups="Func Only",
			description = "T12_Traffic Trend Report P37_ Verify Content_ HKGTC_ User",
					dependsOnMethods={"T10_TrafficTrendPageP31_VerifyValidations"})
	private void T12_TrafficTrendReportP37_VerifyContent_HKGTC_User() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();

		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.TrafficTrend_P37,
				h.link_BusinessIntelligence, h.link_BusinessIntelligence_AlertSubscriptionReports,
				h.link_BusinessIntelligence_AlertSubscriptionReports_TrafficTrendReport);		

		TrafficTrend_P37 p37=new TrafficTrend_P37();
		p37.verify_UI_UserSpecific(methodName);
	}
}
