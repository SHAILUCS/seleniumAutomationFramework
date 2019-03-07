package tests.MainRegression;

import org.testng.annotations.Test;

import common.configData_Util.Constant;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import objectRepository.AgreementCapture.ZoneDefinition;
import objectRepository.Reporting.OverviewReport_P507;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

public class Module_OverviewReport {

	public static final String jsonFilePath = Constant.getResourcesFolderPath() + "Module_OverviewReport.json";

	/** @author shailendra.rajawat
	 * Running the test with OR_T01_NGC User
	 */
	@Test(/*priority=1,*/description = "OR_T01 Reporting Parameters Nextgen user")
	public void OR_T01ReportingParametersNextgenUser() {
		Navigator navigate = new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting,
				ihp.link_Reporting_OverviewReport);
		OverviewReport_P507 overviewReport = new OverviewReport_P507();
		overviewReport.verify_UI();
	}

	/** @author shailendra.rajawat
	 * Running the test with OR_T02_AUSOP User
	 */
	@Test(/*priority=2,*/description = "OR_T02 Reporting Parameters AUSOP user")
	public void OR_T02ReportingParametersAUSOPUser() {
		Navigator navigate = new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.ZoneDefinition,
				ihp.link_AgreementCapture, ihp.link_AgreementCapture_ZoneDefinition);
		ZoneDefinition zoneDefinition = new ZoneDefinition();
		String zoneName = "AutomationTesting" + Util.getTimeStamp_InMilliSec();
		if (zoneDefinition.createZone(zoneName,"Zone Desc",Util.getArray("BHS","CAF"))) {
			navigate.traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting,
					ihp.link_Reporting_OverviewReport);
			OverviewReport_P507 overviewReport = new OverviewReport_P507();
			overviewReport.verify_UI(zoneName);
			navigate.traverseMenu_VerifyPageTitle( PagesTitle.ZoneDefinition, ihp.link_AgreementCapture,
					ihp.link_AgreementCapture_ZoneDefinition);
			zoneDefinition.deleteZone(zoneName);
		}
	}

	/** @author shailendra.rajawat
	 * Running the test with OR_T03_NGC User
	 */
	@Test(/*priority=3,*/description = "OR_T03_09 Testing buttons & Download, Subscription")
	public void OR_T03_09Testingbuttons_SMOKE() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();		
		
		Navigator navigate = new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting,
				ihp.link_Reporting_OverviewReport);
		
		OverviewReport_P507 overviewReport = new OverviewReport_P507();
		overviewReport.OR_T03_09Testingbuttons_SMOKE_PART1(methodName, "part1");
		
		/*new LoginPage().logoutThenPerformLogin("OR_T03");
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting,
				ihp.link_Reporting_OverviewReport);*/
		
		overviewReport.OR_T03_09Testingbuttons_SMOKE_PART2(methodName, "part2");
	}

	/** @author shailendra.rajawat
	 * Running the test with OR_T04_AUSOP User
	 */
	@Test(/*priority=4,*/description = "OR_T04 Detail Per Month Checkbox")
	public void OR_T04DetailPerMonthCheckbox() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		OverviewReport_P507 overviewReport = new OverviewReport_P507();
		overviewReport.OR_T04DetailPerMonthCheckbox(methodName);

	}

	/** @author shailendra.rajawat
	 * Running the test with OR_T05_NGC User
	 */
	@Test(/*priority=5,*/description = "OR_T05 Report Columns MO Call Destination Breakdown: NOT selected")
	public void OR_T05ReportColumnsMOCallDestinationBreakdownNOTselected() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		Navigator navigate = new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting,
				ihp.link_Reporting_OverviewReport);
		OverviewReport_P507 overviewReport = new OverviewReport_P507();
		overviewReport.OR_T05ReportColumnsMOCallDestinationBreakdownNOTselected(methodName);
	}

	/** @author shailendra.rajawat
	 * Running the test with OR_T06_NGC User
	 */
	@Test(/*priority=6,*/description = "OR_T06 Testing MO Call Destination Breakdown (Checkbox)")
	public void OR_T06TestingMOCallDestinationBreakdownCheckbox() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		Navigator navigate = new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting,
				ihp.link_Reporting_OverviewReport);
		OverviewReport_P507 overviewReport = new OverviewReport_P507();
		overviewReport.OR_T06TestingMOCallDestinationBreakdownCheckbox(methodName);
	}

	/** @author shailendra.rajawat
	 * Running the test with OR_T07_NGC User
	 */
	@Test(/*priority=7,*/description = "OR_T07 Checking and calculating data for Call Destination Breakdown")
	public void OR_T07CheckingAndCalculatingDataForCallDestinationBreakdown() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		CustomReporter.createNode("Verifying the Calculations of {Discount Charge} and {Discount Achieved} ,"
				+ "Getting the values of {Traffic Volume} and {Total TAP Charges Excluding Tax} for matching with IOT Traffic Data page");
	
		Navigator navigate = new Navigator();
		IOTRONHomePage ihp = new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting,
				ihp.link_Reporting_OverviewReport);
		OverviewReport_P507 overviewReport = new OverviewReport_P507();
		overviewReport.OR_T07CheckingAndCalculatingDataForCallDestinationBreakdown(methodName);

	}

	/** @author shailendra.rajawat
	 * Running the test with OR_T10_NGC User
	 */
	@Test(/*priority=8,*/description = "OR_T10_11 'Check Additional columns for version2' & 'Country Column'")
	public void OR_T10_11CountryColumn_AdditionalColumnsForVersion2() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		OverviewReport_P507 overviewReport = new OverviewReport_P507();
		overviewReport.OR_T10_11CountryColumn_AdditionalColumnsForVersion2(methodName);

	}

	/** @author shailendra.rajawat
	 * Running the test with OR_T19_NGC User
	 */
	@Test(/*priority=13,*/description = "OR_T19 Market Share and Proportional share with Pseudo Country")
	public void OR_T19MarketShareAndProportionalShareWithPseudoCountry() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		OverviewReport_P507 overviewReport = new OverviewReport_P507();
		overviewReport.OR_T19MarketShareAndProportionalShareWithPseudoCountry(methodName);

	}

	/** @author shailendra.rajawat
	 * Running the test with OR_T12_NGC User
	 * 
	 * Note: No prior data setup is required, i.e. agreement creation is not needed
	 * 
	 * In this test we are directly matching the value of "traffic volume"[Charged/Billed] in following three reports
	 * 	1. Query IOT Summary(just divide the values by 60 prior matching)
	 * 	2. Financial Report - Forecasted TAP & Discounted Charges
	 * 	3. Overview Report(Partner pmn filter is applied)    
	 * 
	 * Please pay attention
	 * 	The traffic period on all three reports should be same and data should be available for both OB/IB
	 * 
	 *  Test Data is fetched from json {@code jsonFilePath} 
	 *  
	 *  QueryIOTSummary-OB  							QueryIOTSummary-IB - 
	 *  DurInSec=14821.9998 > divide by 60 >> 247.0333	DurInSec=2778.9996  == divide by 60 >>  46.31
	 *  ChargeDur=18360 > divide by 60 >>  306			ChargeDur=3090  == divide by 60 >> 51.5
	 *  
	 *  FinForecast-OB - 								FinForecast-IB - 
	 *  TrafVol_FinRep=247								TrafVol_FinRep=46
	 *  
	 *  OverviewReport OB								OverviewReport IB
	 *  Traff Vol Act{Overview Rep}] : '247'			Traff Vol Act{Overview Rep}] : '46'
	 *  Traff Vol Billed{Overview Rep}] : '306'			Traff Vol Billed{Overview Rep}] : '52'
	 *  Traff Vol{Overview Rep}] : '247'				Traff Vol{Overview Rep}] : '46'
	 *  
	 */
	@Test(/*priority=9,*/description = "OR_T12 Traffic Volume colums without Discount Agreement")
	public void OR_T12TrafficVolumeColumsWithoutDiscountAgreement() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		OverviewReport_P507 overviewReport = new OverviewReport_P507();
		overviewReport.OR_T12TrafficVolumeColumsWithoutDiscountAgreement(methodName);
	}

	/** @author shailendra.rajawat
	 * Running the test with OR_T17_NGC User
	 */
	@Test(/*priority=12,*/description = "OR_T17-18 Market & Proportional share column")
	public void OR_T17_18MarketAndProportionalShareColumn() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		OverviewReport_P507 overviewReport = new OverviewReport_P507();
		overviewReport.OR_T17_18MarketAndProportionalShareColumn(methodName);
	}

	/** @author shailendra.rajawat
	 * Running the test with OR_T13_AUSOP User
	 * 
	 * Note: New Agreement is required
	 * 
	 * In this test we are matching the value of "traffic volume"[Charged/Billed] in following two reports
	 * 	1. Query IOT Summary(just divide the values by 60 prior matching)
	 * 	2. Overview Report(Agreement name filter is applied)    
	 * 
	 * Please pay attention
	 * 	The traffic period on all three reports should be same and data should be available for both OB/IB
	 * 
	 *  Test Data is fetched from json {@code jsonFilePath} 
	 *  
	 *  QueryIOTSummary-OB  							QueryIOTSummary-IB - 
	 *  DurInSec= > divide by 60 >> 					DurInSec=  == divide by 60 >>  
	 *  ChargeDur= > divide by 60 >>  					ChargeDur=  == divide by 60 >> 
	 *  
	 *  OverviewReport OB								OverviewReport IB
	 *  Traff Vol Act{Overview Rep}] : ''				Traff Vol Act{Overview Rep}] : ''
	 *  Traff Vol Billed{Overview Rep}] : ''			Traff Vol Billed{Overview Rep}] : ''
	 *  Traff Vol{Overview Rep}] : ''					Traff Vol{Overview Rep}] : ''
	 *  
	 */
	@Test(/*priority=10,*/description = "OR_T13_15 Traffic Volume columns with Discount Agreement - Billed & Actual")
	public void OR_T13_15TrafficVolumeColumnsWithDiscountAgreement_Billed_Actual() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		OverviewReport_P507 overviewReport = new OverviewReport_P507();
		overviewReport.TrafficVolumeColumsWithDiscountAgreement_BilledActual_With_OR_WithoutSatellite("OR_T13_NGC",null,methodName);
	}

	/** @author shailendra.rajawat
	 * Running the test with OR_T14_AUSOP User
	 * */
	@Test(/*priority=11,*/description = "OR_T14_16 Traffic Volume colums with Discount Agreement - Billed & Actual Without Satellite")
	public void OR_T14_16TrafficVolumeColumsWithDiscountAgreement_BilledActual_WithoutSatellite() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		OverviewReport_P507 overviewReport = new OverviewReport_P507();
		overviewReport.TrafficVolumeColumsWithDiscountAgreement_BilledActual_With_OR_WithoutSatellite("OR_T14_NGC","SAT", methodName);
	}

}
