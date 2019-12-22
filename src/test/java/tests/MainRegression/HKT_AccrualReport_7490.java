package tests.MainRegression;

import org.testng.annotations.Test;

import com.configData_Util.Constant;

import objectRepository.Reporting.AccrualReport_P25;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

public class HKT_AccrualReport_7490 {
	
	/**
	 * In case of DB refresh our scripts may fail, because they need an agreement 
	 * with name "Prafull_HKT_Test" to be setup in system 
	 *	1. To test All scenarios of Accrual Report
	 *		Create an agreement with following details
	 *		Login with HKGTC user, create agreement with following parameters
	 * 		1.1 Agreement Status : "Signed Agreement"
	 * 		1.2 Date : JAN 17 to DEC 17
	 * 		1.3 Client	: HKGTC 
	 * 			Partner	: AREDU
	 * 		1.4 Add Discount Parameters:
	 * 			SRE for (bith OB & IB)(and for each Serv and evt type)
	 * 		1.5 Calculate Agreement
	 * */
	
	public static final String jsonFilePath=Constant.getResourcesFolderPath()+"AccrualReportP25_7490.json";
	
	/**
	 * UI and Func Test  
	 * */
	@Test(/*priority=0,*/groups={"UI with Func"},description = "T01_7490_AccrualReport: P25 Content verification")
	private void T01_7490_AccrRep_P25_VerifyContent() {
		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AccrualReport_P25, h.link_Reporting,h.link_Reporting_AccrualReport);
		AccrualReport_P25 p25=new AccrualReport_P25();
		p25.verify_UI();
	}

	/**
	 * Func Only Test for P25 "Agreement Currency" Data correlating with Overview Report   
	 * */
	@Test(/*priority=2,*/groups={"Func Only"},description = "T02_7490_AccrualReport: P25 'Agreement Currency' report Data correlation with Overview Report")
	private void T02_7490_AccrRep_P25_Verify_AgreementCurrencyRepData_With_OvRep() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AccrualReport_P25, h.link_Reporting,h.link_Reporting_AccrualReport);
		AccrualReport_P25 p25=new AccrualReport_P25();
		
		p25.T02_AccrRep_P25_Verify_AgreementCurrencyRepData_With_OvRep(methodName);
		
	}
	
	/**
	 * Func Only Test for P25 Download buttons check
	 * */
	@Test(/*priority=1,*/groups={"Func Only"},description = "T03_7490_AccrualReport: P25 Download buttons check")
	private void T03_7490_AccrRep_P25_Verify_DownloadButtonsCheck() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AccrualReport_P25, h.link_Reporting,h.link_Reporting_AccrualReport);
		AccrualReport_P25 p25=new AccrualReport_P25();
		
		p25.AccrRep_P25_Verify_DownloadButtonsFunc(methodName);
		
	}
	
	/**
	 * Func Only Test for P25 "Home Currency" Data correlating with Overview Report
	 * */
	@Test(/*priority=3,*/groups={"Func Only"},description = "T04_7490_AccrualReport: P25 'Home Currency' report Data correlation with Overview Report")
	private void T04_7490_AccrRep_P25_Verify_HomeCurrencyRep_With_OvRep() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AccrualReport_P25, h.link_Reporting,h.link_Reporting_AccrualReport);
		AccrualReport_P25 p25=new AccrualReport_P25();
		
		p25.T04_AccrRep_P25_Verify_HomeCurrencyRep_With_OvRep(methodName);
		
	}
	
	/**
	 * Func Only Test for P25 "Home Currency" Data correlating with Overview Report
	 * */
	@Test(/*priority=4,*/groups={"Func Only"},description = "T05_7490_AccrualReport: P25 'Current Month' report Data correlation with Overview Report")
	private void T05_7490_AccrRep_P25_Verify_CurrentMonthDataRep_With_OvRep() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		Navigator nav = new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AccrualReport_P25, h.link_Reporting,h.link_Reporting_AccrualReport);
		AccrualReport_P25 p25=new AccrualReport_P25();
		
		p25.T05_7490_AccrRep_P25_Verify_CurrentMonthDataRep_With_OvRep(methodName);
			
	}
	
}
