package tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.configData_Util.Constant;
import com.configData_Util.Util;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.xlUtil.DataTable;

import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreement_P303;
import objectRepository.AgreementCapture.DiscountAgreementManagementPage;
import objectRepository.AgreementCapture.MaintainIOTDiscountAgreementsPage_P301;
import objectRepository.Forecasting.DiscountForecastingPage;
import objectRepository.Forecasting.ForecastReportPerAgreementPage;
import objectRepository.Operations.CalculationStatus_P5;
import objectRepository.Operations.LatestAndOriginalDeltaReport_P9;
import objectRepository.Operations.MaintainAgreementSet_P4;
import objectRepository.common.CalculationType;
import objectRepository.common.EventType;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import objectRepository.common.ServiceType;
import objectRepository.common.TADIG;
import objectRepository.common.TrafficDirection;

public class CEAutomation {

	public static final String jsonFilePath=Constant.getResourcesFolderPath()+"/"+"CEAutomation.json";
	public static final String AgreementListFilePath=Constant.getTestDataFolderPath()+"/CE automation.xlsx";
	public static final String AgreementListSheetName="Agreement list";
	public static final String SetListSheetName="Set list";

	public static int getRowNumberOfSet(DataTable data) {
		String env=Constant.getEnvironmentInfoSheet();
		int lastRow=data.getRowCount();
		int row=lastRow;

		for (int i = 0; i < lastRow; i++) {
			String envSheet=data.getValue(i, "Environment");
			if (envSheet.equalsIgnoreCase(env)) {
				row=i;
				break;
			}
		}
		return row;
	}

	@Test(priority=1,groups={"CE Automation"},description = "T01 Before CE changes have been deployed to PP environment")
	public void T01_BeforeCEChangesHaveBenDeployedToPpEnvironment () {
		Navigator nav=new Navigator();

		nav.to_IOTRONHomePage().navigateToPageNumber(PagesTitle.MaintainAgreementSet_P4.title, 4);

		/*nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainAgreementSet_P4.title,
				h.link_Operations, h.link_Operations_TestingSuite, h.link_Operations_TestingSuite_MaintainAgreementSet);*/

		MaintainAgreementSet_P4 p4=new MaintainAgreementSet_P4();
		JSONManager json=new JSONManager(jsonFilePath, "T01_BeforeCeChangesHaveBenDeployedToPpEnvironment");
		String setName=json.getStr("setName");
		p4.createSet(setName, json.getStr("setDesc"), json.getBool("onlylive_Yes"));

		//Putting the Set Name in excel Last Row for future references
		DataTable data=new DataTable(AgreementListFilePath, SetListSheetName);
		int row = getRowNumberOfSet(data);
		data.setValue(row, "Created Set", setName);
		data.setValue(row, "Environment", Constant.getEnvironmentInfoSheet());

		//Reading the agreement from excel and putting them in a list
		data=new DataTable(AgreementListFilePath, AgreementListSheetName);
		List<String> agreementIdList=new ArrayList<String>();
		for (int i = 1; i < data.getRowCount(); i++) {
			agreementIdList.add(data.getValue(i, "Agreement id"));
		}

		//Selecting the agreements
		p4.selectAgreements(agreementIdList);

	}

	@Test(priority=2,groups={"CE Automation"},description = "Perform Original Calculations On Existing Set")
	public void PerformOriginalCalculationsOnExistingSet() {
		Navigator nav=new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.CalculationStatus_P5.title,
				h.link_Operations, h.link_Operations_TestingSuite, h.link_Operations_TestingSuite_CalculationStatus);


		JSONManager json=new JSONManager(jsonFilePath, "T01_BeforeCeChangesHaveBenDeployedToPpEnvironment");
		String setName=json.getStr("setName");

		CalculationStatus_P5 p5 = new CalculationStatus_P5();
		p5.performCalculation(setName, true);
	}

	@Test(priority=3,groups={"CE Automation"},description = "Perform Latest Calculations On Existing Set, then verify Delta Report")
	public void PerformLatestCalculationsOnExistingSetThenVerifyDeltaReport() {
		Navigator nav=new Navigator();
		IOTRONHomePage h=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.CalculationStatus_P5.title,
				h.link_Operations, h.link_Operations_TestingSuite, h.link_Operations_TestingSuite_CalculationStatus);


		JSONManager json=new JSONManager(jsonFilePath, "T01_BeforeCeChangesHaveBenDeployedToPpEnvironment");
		String setName=json.getStr("setName");

		CalculationStatus_P5 p5=new CalculationStatus_P5();
		if(p5.performCalculation(setName, false)){

			nav.traverseMenu_VerifyPageTitle( PagesTitle.LatestAndOriginalDeltaReport_P9.title,
					h.link_Operations, h.link_Operations_TestingSuite, h.link_Operations_TestingSuite_LatestandOriginalDeltaReport);

			LatestAndOriginalDeltaReport_P9 p9 = new LatestAndOriginalDeltaReport_P9();
			p9.CheckDeltaInLatestAndOriginal(setName);

		}
	}

	@Test(description = "SRE Calculations > Voice MO, SMS MO, DATA MB(OUTBOUND/INBOUND)")
	public void SRE_CalculationType() {
		Navigator nav= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		nav.to_IOTRONHomePage();

		String agreementName="AutomationSREAgreement"+Util.getTimeStamp_InMilliSec();
		String[] partner={TADIG.USA16.value, TADIG.USA27.value, TADIG.USA31.value, TADIG.USA58.value, TADIG.USAD1.value, TADIG.USASC.value, TADIG.USAST.value, TADIG.USAW0.value, TADIG.USAW1.value, TADIG.USAW2.value, TADIG.USAW3.value, TADIG.USAW4.value, TADIG.USAW5.value, TADIG.USAW6.value};
		String OB_discRate="0.02";
		String IB_discRate="0.01";
		String traffPeriod="01-FEB-15";
		//String agreementName="AutomationSREAgreement15_Mar_2018_18_14_46_606";
		//CODE to create a new Agreement
		boolean agreementCycleCompleted=false;
		nav.traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture,ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage= new MaintainIOTDiscountAgreementsPage_P301();
		CreateEditIOTDiscountAgreement_P303 createEditIOTDiscountAgreement=maintainIOTDiscountAgreementsPage.clickOnCreateIOTDiscountAgreementButton();
		createEditIOTDiscountAgreement.createDiscAgreement(agreementName, TADIG.AUSTA.value, partner,"01-Jan-2015","31-Dec-2015",false);
		createEditIOTDiscountAgreement.addDiscountParameters(TrafficDirection.CustomerOutbound, ServiceType.VOICE, EventType.MO, CalculationType.SingleRateEffectiveVoiceSMSData, "Value", OB_discRate ,null,null,null);
		createEditIOTDiscountAgreement.addDiscountParameters(TrafficDirection.VisitorInbound, ServiceType.VOICE, EventType.MO, CalculationType.SingleRateEffectiveVoiceSMSData, "Value", IB_discRate,null,null,null);
		createEditIOTDiscountAgreement.addDiscountParameters(TrafficDirection.CustomerOutbound, ServiceType.SMS, EventType.MO, CalculationType.SingleRateEffectiveVoiceSMSData, "Value", OB_discRate,null,null,null);
		createEditIOTDiscountAgreement.addDiscountParameters(TrafficDirection.VisitorInbound, ServiceType.SMS, EventType.MO, CalculationType.SingleRateEffectiveVoiceSMSData, "Value", IB_discRate,null,null,null);
		createEditIOTDiscountAgreement.addDiscountParameters(TrafficDirection.CustomerOutbound, ServiceType.DATA, EventType.MB, CalculationType.SingleRateEffectiveVoiceSMSData, "Value", OB_discRate,null,null,null);
		createEditIOTDiscountAgreement.addDiscountParameters(TrafficDirection.VisitorInbound, ServiceType.DATA, EventType.MB, CalculationType.SingleRateEffectiveVoiceSMSData, "Value", IB_discRate,null,null,null);
		agreementCycleCompleted=createEditIOTDiscountAgreement.calculateAgreement(agreementName);

		if (agreementCycleCompleted) {
			nav.traverseMenu_VerifyPageTitle( PagesTitle.ForecastReportperAgreement, ihp.link_Forecasting,ihp.link_Forecasting_ForecastReportPerAgreement);
			ForecastReportPerAgreementPage forecast=new ForecastReportPerAgreementPage();
			if(forecast.performSearch("no", agreementName, ServiceType.VOICE, EventType.MO,"latest")){
				forecast.performCalculations_ForecastingRep(traffPeriod, TrafficDirection.CustomerOutbound, OB_discRate, "0");
				forecast.performCalculations_ForecastingRep(traffPeriod, TrafficDirection.VisitorInbound, IB_discRate, "0");
			}
			if(forecast.performSearch("no", agreementName, ServiceType.SMS, EventType.MO,"latest")){
				forecast.performCalculations_ForecastingRep(traffPeriod, TrafficDirection.CustomerOutbound, OB_discRate, "0");
				forecast.performCalculations_ForecastingRep(traffPeriod, TrafficDirection.VisitorInbound, IB_discRate, "0");
			}
			if(forecast.performSearch("no", agreementName, ServiceType.DATA, EventType.MB,"latest")){
				forecast.performCalculations_ForecastingRep(traffPeriod, TrafficDirection.CustomerOutbound, OB_discRate, "0");
				forecast.performCalculations_ForecastingRep(traffPeriod, TrafficDirection.VisitorInbound, IB_discRate, "0");
			}



		}

	}

	@Test(description = "Forecasting Report Calculations > SRE > Voice MO, SMS MO, DATA MB(OUTBOUND/INBOUND)")
	public void forecastingReportCalculations1() {
		SeleniumMethods com= new SeleniumMethods();
		IOTRONHomePage iotronHome= new IOTRONHomePage();
		DiscountForecastingPage discountForecasting= new DiscountForecastingPage();
		ForecastReportPerAgreementPage forecastReportPerAgreement= new ForecastReportPerAgreementPage();
		DiscountAgreementManagementPage discountAgreementManagement= new DiscountAgreementManagementPage();
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreements= new MaintainIOTDiscountAgreementsPage_P301();
		CreateEditIOTDiscountAgreement_P303 createEditIOTDiscountAgreement= new CreateEditIOTDiscountAgreement_P303();

		String sheetName = "ForecastReportPerAgreement";
		DataTable dataTable=new DataTable(Constant.getTestDataFilePath(), sheetName);
		int lastRow = dataTable.getRowCount();
		ArrayList<String> discBasisList = new ArrayList<>();
		ArrayList<String> lowerBoundList = new ArrayList<>();

		// Navigating to Edit IOT Discount Agreement Page, for getting the
		// values of
		if (com.navigateToAndVerifyPageTitle(iotronHome.link_AgreementCapture,
				"Discount Agreement Management")) {
			if (com.navigateToAndVerifyPageTitle(discountAgreementManagement.link_MaintainIOTDiscountAgreements,
					"Maintain IOT Discount Agreements")) {
				String agreementName = dataTable.getValue(1,"Agreement");
				if (maintainIOTDiscountAgreements.performSearch(agreementName)) {
					maintainIOTDiscountAgreements.clickOnEditLink(agreementName);
					if (createEditIOTDiscountAgreement.navigateToTab("Discount Parameters")) {
						// Fetching the Discount Basis Value for CB Voice MO
						for (int i = 1; i <= lastRow; i++) {
							String trafDirection = dataTable.getValue( i,"Traffic Direction");
							String serviceType = dataTable.getValue( i,"Service Type");
							String eventType = dataTable.getValue( i,"Event Type");

							if (!trafDirection.equals("")) {
								String discBasisVal = createEditIOTDiscountAgreement.getColumnValue(trafDirection,
										serviceType, eventType, "Discount Basis Value");
								// Fetching the Lower Bound Value for CB
								// Voice MO
								String lowerBound = createEditIOTDiscountAgreement.getColumnValue(trafDirection,
										serviceType, eventType, "Lower Bound");
								// System.out.println(discBasisVal+" :
								// "+lowerBound);
								discBasisList.add(discBasisVal);
								lowerBoundList.add(lowerBound);
							}

						}

						// Navigating to Forecast Report per agreement and
						// performing the calculations
						if (com.navigateToAndVerifyPageTitle(iotronHome.link_Forecasting,
								"Discount Forecasting")) {
							if (com.navigateToAndVerifyPageTitle(
									discountForecasting.link_forecastReportPerAgreement,
									"Forecast Report per Agreement")) {
								for (int i = 1; i <= lastRow; i++) {
									String val = dataTable.getValue( i,"Traffic Direction");
									if (!val.equals("")) {
										forecastReportPerAgreement.performSearch(i);
										forecastReportPerAgreement.performCalculations_ForecastingRep(i,
												discBasisList.get(i - 1), lowerBoundList.get(i - 1));
									}
								}
							}
						}
					}

				}

			}
		}
	}

}
