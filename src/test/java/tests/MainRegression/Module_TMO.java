package tests.MainRegression;

import java.util.HashMap;

import org.testng.annotations.Test;

import common.configData_Util.Constant;
import common.customReporting.CustomReporter;
import objectRepository.CustomisedReportsTMO.IotronUsInstance;
import objectRepository.CustomisedReportsTMO.TapAccrualActualAndTrueUp;
import objectRepository.Forecasting.FinancialReport_ForecastedTAPDiscountedCharges;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

public class Module_TMO {

	public static final String jsonFilePath = Constant.getResourcesFolderPath() + "Module_TMO.json";

	@Test(description = "TMO_T01_Verify 'All columns' existence with position on 'TAP Accrual, Actual & True Up' Page")
	public void TMO_T01_VerifyAllcolumnsOn_TAPAccrualActualAndTrueUpPage() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
				ihp.link_Reporting_CustomisedReportsTMO);
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.TAPAccrualActualTrueUp,
				tmo.link_TAPAccrualActualAndTrueUp);

		TapAccrualActualAndTrueUp tapAccrualActualAndTrueUp=new TapAccrualActualAndTrueUp();
		tapAccrualActualAndTrueUp.verifyAllColumnsAreAdded(methodName,"searchParam");
		
	}

	@Test(description = "TMO_T02-3-4-5-8_Verify 'SMS MT - Accrual' & 'Total' column calculations on 'TAP Accrual, Actual & True Up' Page")
	public void TMO_T02_3_4_5_8_VerifySmsMt_Total_CalculationsOn_TAPAccrualActualAndTrueUpPage() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
				ihp.link_Reporting_CustomisedReportsTMO);
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.TAPAccrualActualTrueUp,
				tmo.link_TAPAccrualActualAndTrueUp);
		TapAccrualActualAndTrueUp tapAccrualActualAndTrueUp=new TapAccrualActualAndTrueUp();
		tapAccrualActualAndTrueUp.verify_ColumnsCalculation(methodName);
	}

	@Test(description = "TMO_T06_Verify data of 'TAP Accrual, Actual & True Up' Page with 'Financial Forecast Report'")
	public void TMO_T06_VerifyDataOf_TapAccrualActualTrueUpPage_With_FinancialForecastReport() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();

		CustomReporter.createNode("Navigating to 'Financial Report Forecasted TAP Discounted Charges' page to GET IB/OB Tap Charges and Traf Vol values");
		
		navigate.to_IOTRONHomePage();
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.FinancialReportForecastedTAPDiscountedCharges, ihp.link_Forecasting, ihp.link_Forecasting_FinancialReportForecastedTAPDiscountedCharges);

		FinancialReport_ForecastedTAPDiscountedCharges finRep=new FinancialReport_ForecastedTAPDiscountedCharges();
		HashMap<String, String> ibOb_TAPCharges_TrafVol=finRep.get_ibOb_TAPCharges_TrafVol(methodName);
		
		CustomReporter.createNode("Navigating to 'Customised Reports TMO' page to VERIFY IB/OB Tap Charges and Traf Vol values");
				
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
				ihp.link_Reporting_CustomisedReportsTMO);
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.TAPAccrualActualTrueUp,
				tmo.link_TAPAccrualActualAndTrueUp);

		TapAccrualActualAndTrueUp tapAccrualActualAndTrueUp=new TapAccrualActualAndTrueUp();
		tapAccrualActualAndTrueUp.verifyFinancialForecastRepData(ibOb_TAPCharges_TrafVol,methodName);

	}

	@Test(dependsOnMethods={"TMO_T02_3_4_5_8_VerifySmsMt_Total_CalculationsOn_TAPAccrualActualAndTrueUpPage"},
			description = "TMO_T09_Verify 'SMS MO - Accrual' & 'Total' column calculations on 'TAP Accrual, Actual & True Up' Page")
	public void TMO_T09_VerifySmsMoAccrualAndTotalColumnCalculationsOn_TapAccrualActualAndTrueUpPage() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();

		Navigator navigate= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		IotronUsInstance tmo=new IotronUsInstance();
		navigate.to_IOTRONHomePage();
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, ihp.link_Reporting,
				ihp.link_Reporting_CustomisedReportsTMO);
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.TAPAccrualActualTrueUp,
				tmo.link_TAPAccrualActualAndTrueUp);

		TapAccrualActualAndTrueUp tapAccrualActualAndTrueUp=new TapAccrualActualAndTrueUp();
		tapAccrualActualAndTrueUp.verify_ColumnsCalculation(methodName);
	}
}
