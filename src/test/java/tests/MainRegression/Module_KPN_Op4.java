package tests.MainRegression;

import org.testng.annotations.Test;

import com.configData_Util.Constant;
import com.customReporting.CustomReporter;

import objectRepository.BusinessIntelligence.DailyTrafficAnalysisReport;
import objectRepository.BusinessIntelligence.DailyTrafficVolumesByCountryAndPartner;
import objectRepository.BusinessIntelligence.DailyTrafficVolumesByPartner;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

public class Module_KPN_Op4 {

	public static final String jsonFilePath = Constant.getResourcesFolderPath() + "Module_KPN_Op4.json";

	@Test(description = "KPN_Op4_T01_'Daily Traffic Volumes by Country & Partner Page' UI Verification with NGC user")
	public void KPN_Op4_T01_CountryAndPart_UIVerification() {
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(
				PagesTitle.DailyTrafficVolumesByCountryPartner, ihp.link_BusinessIntelligence,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficVolumesByCountryPartner);
		DailyTrafficVolumesByCountryAndPartner dailyTrafficVolumesByCountryAndPartner= new DailyTrafficVolumesByCountryAndPartner();
		dailyTrafficVolumesByCountryAndPartner.verifyUIContent();
	}


	@Test(description = "KPN_Op4_T02_'Daily Traffic Volumes by Country & Partner Report Region' Verification with NGC user")
	public void KPN_Op4_T02_CountryAndPart_ReportRegion_Verification() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(
				PagesTitle.DailyTrafficVolumesByCountryPartner, ihp.link_BusinessIntelligence,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficVolumesByCountryPartner);
		DailyTrafficVolumesByCountryAndPartner dailyTrafficVolumesByCountryAndPartner= new DailyTrafficVolumesByCountryAndPartner();
		dailyTrafficVolumesByCountryAndPartner.verifyReportRegion(methodName);
	}

	@Test(description = "KPN_Op4_T03_Verify 'Traffic Volume' column value with ['Daily Traffic Analysis' Report and 'Daily Traffic Volume By Country and Partner' Report]")
	public void KPN_Op4_T03_CountryAndPart_VerifyTrafficVolumeColumnValue() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();


		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(
				PagesTitle.DailyTrafficVolumesByCountryPartner, ihp.link_BusinessIntelligence,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficVolumesByCountryPartner);
		DailyTrafficVolumesByCountryAndPartner dailyTrafficVolumesByCountryAndPartner= new DailyTrafficVolumesByCountryAndPartner();
		dailyTrafficVolumesByCountryAndPartner.verifyTrafficVolumeColumnData(methodName);
	}


	@Test(description = "KPN_Op4_T04_'Daily Traffic Volumes by Partner Page' UI Verification with NGC user")
	public void KPN_Op4_T04_Partner_UIVerification() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(
				PagesTitle.DailyTrafficVolumesByPartner, ihp.link_BusinessIntelligence,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficVolumesByPartner);
		DailyTrafficVolumesByPartner dailyTrafficVolumesByPartner=new DailyTrafficVolumesByPartner();
		dailyTrafficVolumesByPartner.verifyUIContent(methodName);
	}

	@Test(description = "KPN_Op4_T05_'Daily Traffic Volumes by Partner Report Region' Verification with NGC user")
	public void KPN_Op4_T05_Partner_ReportRegion_Verification() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();

		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(
				PagesTitle.DailyTrafficVolumesByPartner, ihp.link_BusinessIntelligence,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficVolumesByPartner);
		DailyTrafficVolumesByPartner dailyTrafficVolumesByPartner=new DailyTrafficVolumesByPartner();
		dailyTrafficVolumesByPartner.verifyReportRegion(methodName);
	}

	@Test(description = "KPN_Op4_T06_Verify 'Traffic Volume' column value with ['Daily Traffic Analysis' Report and 'Daily Traffic Volume By Partner' Report]")
	public void KPN_Op4_T06_Partner_VerifyTrafficVolumeColumnValue() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();

		CustomReporter.createNode("Navigating to 'Daily Traffic Volumes By Partner' page for GETTING traf vol");
		
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(
				PagesTitle.DailyTrafficVolumesByPartner, ihp.link_BusinessIntelligence,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficVolumesByPartner);
		DailyTrafficVolumesByPartner dailyTrafficVolumesByPartner=new DailyTrafficVolumesByPartner();
		dailyTrafficVolumesByPartner.verifyTrafficVolumeColumnData(methodName);
	}

	@Test(description = "KPN_Op4_T07_'Daily Traffic Analysis Report Page' UI Verification with NGC user")
	public void KPN_Op4_T07_TrafficAnalysis_UIVerification() {
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();

		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(
				PagesTitle.DailyTrafficAnalysisReport, ihp.link_BusinessIntelligence,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficAnalysisReport);
		DailyTrafficAnalysisReport dailyTrafficAnalysisReport=new DailyTrafficAnalysisReport();
		dailyTrafficAnalysisReport.verifyUIContent();
	}

	@Test(description = "KPN_Op4_T08_'Daily Traffic Analysis Report Region' Verification with NGC user")
	public void KPN_Op4_T08_TrafficAnalysis_ReportRegion_Verification() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();

		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(
				PagesTitle.DailyTrafficAnalysisReport, ihp.link_BusinessIntelligence,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficAnalysisReport);
		DailyTrafficAnalysisReport dailyTrafficAnalysisReport=new DailyTrafficAnalysisReport();
		dailyTrafficAnalysisReport.verifyReportRegion(methodName);
	}

	@Test(description="KPN_Op4_T09_Daily Traffic Analysis Report's- 'Report Content' Verification With 'Fin Forecast' and 'Query IOT Summary' Reports")
	public void KPN_Op4_T09_TrafficAnalysis_ReportContent_VerificationWith_FinForecast_QueryIOTSummary_Pages() {
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();

		CustomReporter.createNode("Getting Data from Daily Traffic Analysis Report");
		
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(
				PagesTitle.DailyTrafficAnalysisReport, ihp.link_BusinessIntelligence,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficAnalysisReport);
		
		DailyTrafficAnalysisReport dailyTrafficAnalysisReport=new DailyTrafficAnalysisReport();
		dailyTrafficAnalysisReport.verifyReportContent_With_FinForecast_QueryIOTSummary_Pages(methodName);
	}

	@Test(description="KPN_Op4_T10_Daily Traffic Analysis Report's- 'Chart Content' Verification")
	public void KPN_Op4_T10_TrafficAnalysis_ChartContent_Verification() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();

		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(
				PagesTitle.DailyTrafficAnalysisReport, ihp.link_BusinessIntelligence,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting,
				ihp.link_BusinessIntelligence_DailyDCHLevelReporting_DailyTrafficAnalysisReport);
		DailyTrafficAnalysisReport dailyTrafficAnalysisReport=new DailyTrafficAnalysisReport();
			dailyTrafficAnalysisReport.verifyChartContent(methodName);
	}
}
