package tests.MainRegression;

import org.testng.annotations.Test;

import common.configData_Util.Constant;
import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.jsonUtil.JSONManager;
import objectRepository.AgreementCapture.AgreementChecklistReview;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreement_P303;
import objectRepository.AgreementCapture.MaintainIOTDiscountAgreementsPage_P301;
import objectRepository.AgreementCapture.SOXReport;
import objectRepository.AgreementCapture.ZoneDefinition;
import objectRepository.FCHOperators.ManageIOTAddendums;
import objectRepository.IOTRONSystemAdministration.MaintainIOTClients_P310;
import objectRepository.common.ApexCommon;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.LoginPage;
import objectRepository.common.NGCHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

public class Module_DiscountAgreement {

	public static final String jsonFilePath = Constant.getResourcesFolderPath() + "Module_DiscountAgreement.json";
	
	@Test(description = "discAgr_T01_'Agreement Checklist Review' - Edit feature verification")
	public void discAgr_T01_AgreementChecklistReviewEditFeature() {
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AgreementChecklistReview, ihp.link_AgreementCapture, ihp.link_AgreementCapture_AgreementChecklistReview);
		AgreementChecklistReview agreementChecklistReview= new AgreementChecklistReview();
		agreementChecklistReview.performEditing();
	}

	@Test(description = "discAgr_T02_Verify 'Bilateral' and 'Unilateral - Payer' Agreement Type check")
	public void discAgr_T02_Bilateral_UnilateralPayer_AgreementTypeCheck() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		JSONManager json;
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		String agreementName="AutomationTestingAgreement"+Util.getTimeStamp_InMilliSec();

		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture,ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		CreateEditIOTDiscountAgreement_P303 createEditIOTDiscountAgreement= maintainIOTDiscountAgreementsPage.clickOnCreateIOTDiscountAgreementButton();

		json = new JSONManager(jsonFilePath, methodName,"createDiscAgreement");
		createEditIOTDiscountAgreement.createDiscAgreement(agreementName, json.getStr("clientName"),
				json.getStrArr("partner"), json.getStr("startDate"), json.getStr("endDate"),
				json.getBool("bool_checkIncludeSatelite"));

		CustomReporter.report(STATUS.INFO, "<i>ADDING ROWS TO CHECK AGREEMENT TYPE</i>");
		createEditIOTDiscountAgreement.addDiscountParameters(0,json);					
		createEditIOTDiscountAgreement.agreementTypeCheck("Unilateral - Payer");
		createEditIOTDiscountAgreement.addDiscountParameters(1,json);

		createEditIOTDiscountAgreement.agreementTypeCheck("Bilateral");
		createEditIOTDiscountAgreement.deleteAgreement();
	}

	@Test(description = "discAgr_T03_Verify 'Generate Settlement statement', 'Calculate Agreement' functionalities along with verification of 'Settlement statement' row on 'Document Library' tab")
	public void discAgr_T03_GenerateSettlementStatement_CalculateAgreement_DocumentLibrary() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		JSONManager json;
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		String agreementName="AutomationTestingAgreement"+Util.getTimeStamp_InMilliSec();

		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture,ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		CreateEditIOTDiscountAgreement_P303 createEditIOTDiscountAgreement= maintainIOTDiscountAgreementsPage.clickOnCreateIOTDiscountAgreementButton();

		json = new JSONManager(jsonFilePath, methodName,"createDiscAgreement");
		createEditIOTDiscountAgreement.createDiscAgreement(agreementName, json.getStr("clientName"),
				json.getStrArr("partner"), json.getStr("startDate"), json.getStr("endDate"),
				json.getBool("bool_checkIncludeSatelite"));

		for (int i = 0; i < json.getJsonArrLength("DiscParam"); i++) {
			createEditIOTDiscountAgreement.addDiscountParameters(i,json);
		}
		createEditIOTDiscountAgreement.verifyMissingCalculationResults_Validation_SettlementStatement();
		createEditIOTDiscountAgreement.calculateAgreement(agreementName);
		createEditIOTDiscountAgreement.generateSettlementStatement_And_VerifyXls(agreementName,json.getStr("clientName"),json.getStrArr("partner")[0]);
		createEditIOTDiscountAgreement.verifySettlementStatementStatusInDocumentLibrary(agreementName);
		createEditIOTDiscountAgreement.deleteAgreement();
	}

	@Test(description = "discAgr_T04_'Search and Delete' Newly added agreement")
	public void discAgr_T04_Search_Delete_NewlyCreatedAgreement() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		JSONManager json;
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		String agreementName="AutomationTestingAgreement"+Util.getTimeStamp_InMilliSec();

		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture,ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		CreateEditIOTDiscountAgreement_P303 createEditIOTDiscountAgreement= maintainIOTDiscountAgreementsPage.clickOnCreateIOTDiscountAgreementButton();

		json = new JSONManager(jsonFilePath, methodName,"createDiscAgreement");
		createEditIOTDiscountAgreement.createDiscAgreement(agreementName, json.getStr("clientName"),
				json.getStrArr("partner"), json.getStr("startDate"), json.getStr("endDate"),
				json.getBool("bool_checkIncludeSatelite"));

		navigate.traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture,ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		maintainIOTDiscountAgreementsPage.performSearch(agreementName);
		maintainIOTDiscountAgreementsPage.clickOnEditLink(agreementName);
		createEditIOTDiscountAgreement.deleteAgreement();

	}


	@Test(description = "discAgr_T05_'Copy Discount Agreement' feature")
	public void discAgr_T05_CopyDiscountAgreement() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		JSONManager json;
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		String agreementName="AutomationTestingAgreement"+Util.getTimeStamp_InMilliSec();

		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture,ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		CreateEditIOTDiscountAgreement_P303 createEditIOTDiscountAgreement= maintainIOTDiscountAgreementsPage.clickOnCreateIOTDiscountAgreementButton();

		json = new JSONManager(jsonFilePath, methodName,"createDiscAgreement");
		createEditIOTDiscountAgreement.createDiscAgreement(agreementName, json.getStr("clientName"),
				json.getStrArr("partner"), json.getStr("startDate"), json.getStr("endDate"),
				json.getBool("bool_checkIncludeSatelite"));

		String copyAgreementName=createEditIOTDiscountAgreement.performCopyDiscountAgreement();

		navigate.traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture,ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		maintainIOTDiscountAgreementsPage.performSearch(copyAgreementName);
		maintainIOTDiscountAgreementsPage.clickOnEditLink(copyAgreementName);
		createEditIOTDiscountAgreement.deleteAgreement();
		new ApexCommon().removeAppliedColumnFilter(copyAgreementName);
		maintainIOTDiscountAgreementsPage.performSearch(agreementName);
		maintainIOTDiscountAgreementsPage.clickOnEditLink(agreementName);
		createEditIOTDiscountAgreement.deleteAgreement();
	}

	@Test(description = "discAgr_T06_'Maintain IOT Discount agreement filtering' feature")
	public void discAgr_T06_MaintainIOTDiscountAgreementFiltering() {
		Navigator navigate= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture,ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);		
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage= new MaintainIOTDiscountAgreementsPage_P301();
		maintainIOTDiscountAgreementsPage.verifyFiltering_CountryDropDownFeature();
		maintainIOTDiscountAgreementsPage.verifyFiltering_SelectColumnsFeature();
		maintainIOTDiscountAgreementsPage.verifyFiltering_SaveReportFeature();
		maintainIOTDiscountAgreementsPage.verifyFiltering_ActionFilterFeature();

	}



	@Test(description = "discAgr_T10_'Generation of Term Sheet' feature - AUSTA")
	public void discAgr_T10_GenerationOfTermSheet_AUSTA() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		MaintainIOTDiscountAgreementsPage_P301 agreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		agreementsPage.generationOfTermSheet_COMMON(methodName);

	}

	@Test(description = "discAgr_T11_'Generation of Term Sheet' feature - AUSOP")
	public void discAgr_T11_GenerationOfTermSheet_AUSOP() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		MaintainIOTDiscountAgreementsPage_P301 agreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		agreementsPage.generationOfTermSheet_COMMON(methodName);
	}

	@Test(description = "discAgr_T12_'Generation of Term Sheet' feature - USAW6")
	public void discAgr_T12_GenerationOfTermSheet_USAW6() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		MaintainIOTDiscountAgreementsPage_P301 agreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		agreementsPage.generationOfTermSheet_COMMON(methodName);
	}

	

	@Test(description = "discAgr_T07_'SOX Report' - Search functionality verification")
	public void discAgr_T07_SOXReportSearchFunctionalityVerification() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		SOXReport soxReport= new SOXReport();
		soxReport.discAgr_SOXReportSearchFunctionalityVerification(methodName);
	}


	@Test(description = "discAgr_T08_Zone definition Creation new zone-Editing-Deleting")
	public void discAgr_T08_ZoneDefinitionCRUDOperation() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		ZoneDefinition zoneDefinition = new ZoneDefinition();
		zoneDefinition.discAgr_ZoneDefinitionCRUDOperation(methodName);
	}

	@Test(description = "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page")
	public void discAgr_T09_ForecastingMethod_HomeCurrency_DataCheck() {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		JSONManager json;
		Navigator navigate=new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		NGCHomePage fch= new NGCHomePage();
		
		String ClientReceivableCurrency=null,PartnerPayableCurrency = null, homeCurrency_CreateIOTClient=null,defForecastMeth_CreateIOTClient=null;
		
		CustomReporter.createNode("Navigate to Operators >>Manage IOT Addendums Page and get the data");
		//Navigate to Operators Page and get the data
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.ManageIOTAddendums, fch.link_Operators,
				fch.link_Operators_RoamingAgreements, fch.link_Operators_RoamingAgreements_ManageIOTAddenda,
				fch.link_Operators_RoamingAgreements_ManageIOTAddenda_ManageIOTAddenda);
		ManageIOTAddendums manageIOTAddendums= new ManageIOTAddendums();
		String temp=manageIOTAddendums.get_ClientReceivable_And_PartnerPayableCurrency(methodName,"createDiscAgreement");
		ClientReceivableCurrency=temp.split(",")[0];
		PartnerPayableCurrency=temp.split(",")[1];

		
		CustomReporter.createNode("Navigate to Maintain IOT Clients Page and get the data");
		//Navigate to Maintain IOT Clients Page and get the data
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTClients_P310,
				ihp.link_IOTRONSystemAdministration, ihp.link_IOTRONSystemAdministration_MaintainIOTClients);
		MaintainIOTClients_P310 maintainIOTClients= new MaintainIOTClients_P310();
		temp=maintainIOTClients.get_HomeCurrency_And_DefForecastMeth(methodName,"createDiscAgreement");
		homeCurrency_CreateIOTClient=temp.split(",")[0];
		defForecastMeth_CreateIOTClient=temp.split(",")[1];

		new LoginPage().logoutThenPerformLogin("AUSOP-S");

		CustomReporter.createNode("Verifying the Home Currency and Forecast Method on Agreement Capture>>Maintain IOT Discount Agreements Page");
		
		navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301,
				ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage= new MaintainIOTDiscountAgreementsPage_P301();
		CreateEditIOTDiscountAgreement_P303 createEditIOTDiscountAgreement= maintainIOTDiscountAgreementsPage.clickOnCreateIOTDiscountAgreementButton();
		createEditIOTDiscountAgreement.verifyHomeCurrency(homeCurrency_CreateIOTClient);
		createEditIOTDiscountAgreement.verifyForecastMethod(defForecastMeth_CreateIOTClient);
		
		String agreementName="AutomationTestingAgreement"+Util.getTimeStamp_InMilliSec();
		json = new JSONManager(jsonFilePath, methodName,"createDiscAgreement");
		createEditIOTDiscountAgreement.createDiscAgreement(agreementName, json.getStr("clientName"),
				json.getStrArr("partner"), json.getStr("startDate"), json.getStr("endDate"),
				json.getBool("bool_checkIncludeSatelite"));
		
		for (int i = 0; i < json.getJsonArrLength("DiscParam"); i++) {
			createEditIOTDiscountAgreement.addDiscountParameters(i,json);
		}
		createEditIOTDiscountAgreement.calculateAgreement(agreementName);
		//TODO Check the comments of ivana, written in the notepad
		createEditIOTDiscountAgreement.generateSettlementStatement_And_VerifyXls(agreementName,json.getStr("clientName"),json.getStrArr("partner")[0]);
		createEditIOTDiscountAgreement.verifySettlementStatementStatusInDocumentLibrary(agreementName);
		
		createEditIOTDiscountAgreement.verifySettlementStatementCurrencies(ClientReceivableCurrency,PartnerPayableCurrency);
		
		createEditIOTDiscountAgreement.deleteAgreement();

	}



}
