package tests.MainRegression;

import org.testng.annotations.Test;

import common.configData_Util.Constant;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.jsonUtil.JSONManager;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreement_P303;
import objectRepository.AgreementCapture.MaintainIOTDiscountAgreementsPage_P301;
import objectRepository.IOTRONSystemAdministration.CommitmentReportSizeOfRiskBounds_P602;
import objectRepository.IOTRONSystemAdministration.CreateEditIOTClient_P413;
import objectRepository.IOTRONSystemAdministration.CreateEditOperatorHavingMoreThanOnePmnCode_P495;
import objectRepository.IOTRONSystemAdministration.MaintainIOTClients_P310;
import objectRepository.IOTRONSystemAdministration.MaintainOperatorsHavingMoreThanOnePMNcode_P479;
import objectRepository.Reporting.CommitmentReport_P492;
import objectRepository.common.ApexCommon;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.LoginPage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

/**
 * <pre>
 * This class contains all tests for requirement: 7393 IOTRON: HKT: Requirements Assessment - I3.8 Commitment report 
 *	7395 IOTRON: HKT: p492-Commitment Report: Adding up an additional report parameter 'Home Operator'
 *	7396 IOTRON: HKT: p492-Commitment Report: FE - Two new columns 'Home Operator' & 'Partner PMN' to be added
 *	7399 IOTRON: HKT: p602 -Process for updating all PMNs belonging to one operator with the same risk bounds
 *	7400 IOTRON: HKT: p495 - Maintaining the same risk bounds while grouping the PMN's for a operator
 * </pre>
 */
public class HKT_CommitmentReport_7393 {

	public static final String jsonFilePath = Constant.getResourcesFolderPath() + "HKT_CommitmentReport_7393.json";

	/**
	 * <pre>
	 * This T01_7395 is for NGC User Only
	 * 
	 * As per the requirement document 7395, We need to verify that the Home Operator 
	 * shuttle is getting displayed on Commitment Report P492 for following users
	 * 1. NGC
	 * 2. Clients with multiple PMN
	 * 
	 * Test Plan
	 * 1. For NGC User we can just verify shuttle is present and its affects on 
	 * 	the result of report
	 * </pre>
	 */
	@Test(groups = "UI with Func", description = "T01_7395_HKT Commitment Report Adding up an additional report parameter 'Home Operator'")
	private void T01_HKTCommitRep_7395_AddingUpAnAdditionalReportParameter_HomeOperator() {

		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h = new IOTRONHomePage();

		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(PagesTitle.CommitmentReport_P492, h.link_Reporting,
				h.link_Reporting_CommitmentReport);
		CommitmentReport_P492 p492 = new CommitmentReport_P492();
		p492.T01_HKTCommitRep_7395_AddingUpAnAdditionalReportParameter_HomeOperator(methodName);
	}

	/**
	 * <pre>
	 * This T02_7395 is for HKGTC User Only
	 * 
	 * As per the requirement document 7395, We need to verify that the Home Operator 
	 * shuttle is getting displayed on Commitment Report P492 for following users
	 * 1. NGC
	 * 2. Clients with multiple PMN
	 * Test Plan -
	 * 2. For the Group Client, we will first go the [Iotron System Administration >> Maintain Operators having more than one PMN code] Page
	 * 		and get all the PMN's which comprises this group.
	 * 		Then on Commitment report we can check shuttle has these PMN's 
	 * 		Also search is affecting the result
	 * </pre>
	 */
	@Test(groups = "UI with Func", description = "T02_7395_HKT Commitment Report Adding up an additional report parameter 'Home Operator' - HKGTC User")
	private void T02_HKTCommitRep_7395_AddingUpAnAdditionalReportParameter_HomeOperator_HKGTC() {

		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();

		CommitmentReport_P492 p492 = new CommitmentReport_P492();
		p492.T02_HKTCommitRep_7395_AddingUpAnAdditionalReportParameter_HomeOperator_HKGTC(methodName);
	}

	/**
	 * @author shailendra.rajawat
	 * <pre>
	 * This T03_7396 is for HKGTC User
	 * 
	 * As per the requirement document 7396, We need to verify that the 'Home Operator' 
	 *  and 'Partner PMN' columns are getting displayed on Commitment Report P492. Also 
	 *  there are some cases in which Selected PMN does not display in report, if they do 
	 *  not contribute towards the Commitment
	 * 
	 * Test Plan - 
	 *  1. Open commitment report, perform search and verify columns are displayed
	 *  
	 *	1. Create an agreement with following details
	 *		Login with HKGTC user, create agreement with following parameters
	 * 		1.1 Agreement Status : "Agreement Proposed"
	 * 		1.2 Date : JAN 18 to DEC 18
	 * 		1.3 Client	: HKGMC, HKGTC, HKGM3, HKGNW 
	 * 			Partner	: Select all TADIGs of "Canada Rogers" group : CANDW, CANMC CANRW
	 * 		1.4 Add Discount Parameters:
	 * 			Check PMN Level Rates checkbox
	 * 			SRE for each Serv and evt type
	 * 			Send Or Pay Fin for OB(1,750,000.00) and IB(710,000.00) (Note: Remove HKGNW, from config)
	 * 		1.5 Calculate Agreement
	 *	2. Go to Commitment Report
	 *		2.1 Perform search with(Selecting all TADIGs, 01/2018) >> Our Agreement should display in two rows one for IB and other for OB 
	 *		2.2 Perform search with(Selecting only HKGNW, 01/2018) >> Our Agreement should Not display 
	 * </pre>
	 */
	@Test(groups = "Func Only", description = "T03_7396_HKT On Commitment Report Agreement with Non-Contributing Home Operator should not display")
	private void T03_HKTCommitRep_7396_AgreementWithNonContributingHomeOpShouldNotDisplay() {
		
		String agreementName="AutomationTestingAgreement"+Util.getTimeStamp_InMilliSec();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h = new IOTRONHomePage();
		
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(PagesTitle.MaintainIOTDiscountAgreements_P301, h.link_AgreementCapture,
				h.link_AgreementCapture_MaintainIOTDiscountAgreements);
		
		MaintainIOTDiscountAgreementsPage_P301 p301=new MaintainIOTDiscountAgreementsPage_P301();  
		CreateEditIOTDiscountAgreement_P303 p303=p301.clickOnCreateIOTDiscountAgreementButton();
		JSONManager json=new JSONManager(jsonFilePath, methodName,"createDiscAgreement");
		p303.createDiscAgreement_AllParams(agreementName, json);
		
		for (int i = 0; i < json.getJsonArrLength("DiscParam"); i++) {
			p303.addDiscountParameters(i,json);
		}
		
		// Now check the PMN Level Rates checkbox
		p303.checkPMNLevelRatesCheckbox();
		
		// Configure the SOP rows, to exclude HKGNW Home Op
		json=new JSONManager(jsonFilePath, methodName,"createDiscAgreement","configurePMNs","OB");
		p303.configurePMNs(5,json);
		json=new JSONManager(jsonFilePath, methodName,"createDiscAgreement","configurePMNs","IB");
		p303.configurePMNs(6,json);
		
		// Calculate Agreement
		p303.calculateAgreement(agreementName);
		
		//Navigate to Commitment Report and verify the scenarios
		nav.traverseMenu_VerifyPageTitle(PagesTitle.CommitmentReport_P492, h.link_Reporting,
				h.link_Reporting_CommitmentReport);
		CommitmentReport_P492 p492 = new CommitmentReport_P492();
		p492.T03_HKTCommitRep_7396_TwoNewColumnsHomeOperatorAndPartnerPMNtoBeAdded(agreementName,methodName);
		
		//Deleting the Agreement
		nav.traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, h.link_AgreementCapture,h.link_AgreementCapture_MaintainIOTDiscountAgreements);
		p301.performSearch(agreementName);
		p301.clickOnEditLink(agreementName);
		p303.deleteAgreement();
	}
	
	/**
	 * @author shailendra.rajawat 10-Jan-19
	 * 
	 * <pre>
	 * This T04_7399 is for NGC User
	 * 
	 * As per the requirement document 7399, We need to verify that "When Risk Bound is changed of one PMN,
	 * 	which belong to a group. then the risk bound should be updated on every other PMNs as well". 
	 * 
	 * Test Plan - 
	 *  
	 *	1. Go to IOTRON - Welcome >> IOTRON System Administration >> Maintain IOT Clients >> Create/Edit IOT Client Page  
	 *	2. Click on Commitment Report Size of Risk Bounds >> P602 should get displayed
	 *	3. Make some changes on the page and save for client "HKGTC"
	 *	4. Open P602 for other PMNs like "HKGNW" which belong to same group, Verify the same changes should reflect on them too 
	 *	5. Navigate to Commitment Report, perform search for the "HKGTC" client
	 *	6. For(lets say) 5 rows, Verify the Risk Bound is properly displayed as per provided bounds
	 * </pre>
	 */
	@Test(groups = "Func Only", description = "T04_HKT Commit Rep_7399_Updates In Risk Bound Should Persist For All Op Of Same Group")
	private void T04_HKTCommitRep_7399_UpdatesInRiskBoundShouldPersistForAllOpOfSameGroup() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h = new IOTRONHomePage();
		
		CustomReporter.createNode("Changing the Risk Bounds for the PMN");
		//Changing the Risk Bounds for the PMN
		
		//Navigating to MaintainIOTClients_P310
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(PagesTitle.MaintainIOTClients_P310, h.link_IOTRONSystemAdministration,
				h.link_IOTRONSystemAdministration_MaintainIOTClients);
		
		MaintainIOTClients_P310 p310=new MaintainIOTClients_P310();
		JSONManager modifyJson=new JSONManager(jsonFilePath, methodName,"modify_CommitmentReportSizeOfRiskDefinition");
		
		//Searching for Client PMN on MaintainIOTClients_P310, then Navigating to CreateEditIOTClient_P413 
		CreateEditIOTClient_P413 p413=p310.searchAndClickEdit(modifyJson.getStr("pmn"));
		
		//Clicking on SetCommitmentReportSizeOfRiskBounds Button on CreateEditIOTClient_P413, then Navigating on CommitmentReportSizeOfRiskBounds_P602 
		CommitmentReportSizeOfRiskBounds_P602 p602=p413.clickOnSetCommitmentReportSizeOfRiskBoundsButton();
		
		//Modifying Bounds on CommitmentReportSizeOfRiskBounds_P602 page then Navigating back to CreateEditIOTClient_P413
		p602.modifyBounds(modifyJson);
		
		//Clicking cancel to navigate back to MaintainIOTClients_P310, so that we can search other pmn
		p413.clickOnCancelButton();
		
		//Removing older filter, so that table is displayed
		ApexCommon comm=new ApexCommon();
		comm.removeAppliedColumnFilter(modifyJson.getStr("pmn"));
		
		CustomReporter.createNode("Verifying that the Risk Bounds are updated for other PMN of same group ");
		
		JSONManager verifyJson=new JSONManager(jsonFilePath, methodName,"verify_CommitmentReportSizeOfRiskDefinition");
		
		//Searching for Client PMN on MaintainIOTClients_P310, then Navigating to CreateEditIOTClient_P413
		p310.searchAndClickEdit(verifyJson.getStr("pmn"));
		
		//Clicking on SetCommitmentReportSizeOfRiskBounds Button on CreateEditIOTClient_P413, then Navigating on CommitmentReportSizeOfRiskBounds_P602
		p413.clickOnSetCommitmentReportSizeOfRiskBoundsButton();
		
		//Verifying the Bounds on CommitmentReportSizeOfRiskBounds_P602 page then Navigating back to CreateEditIOTClient_P413
		p602.verifyBounds(modifyJson);
	}
	
	/**
	 * @author shailendra.rajawat 11-Jan-19
	 * 
	 * <pre>
	 * This T05_7399 is for HKGTC and NGC User
	 * 
	 * As per the requirement document 7399, We need to verify that "When Risk Bound is changed of one PMN,
	 * 	which belong to a group. then the risk bound should be updated on every other PMNs as well". 
	 * 
	 * Test Plan - 
	 *  
	 *	1. Go to IOTRON - Welcome >> IOTRON System Administration >> Maintain IOT Clients >> Create/Edit IOT Client Page  
	 *	2. Click on Commitment Report Size of Risk Bounds >> P602 should get displayed
	 *	3. Make some changes on the page and save for client "HKGTC"
	 *	4. Open P602 for other PMNs like "HKGNW" which belong to same group, Verify the same changes should reflect on them too 
	 *	5. Navigate to Commitment Report, perform search for the "HKGTC" client
	 *	6. Verify the Risk Bound is properly displayed as per provided bounds 
	 * </pre>
	 */
	@Test(groups = "Func Only", 
			description = "T05_HKT Commit Rep_7399_Risk Bound is properly displayed as per provided bounds On Commitment Report",
			dependsOnMethods={"T04_HKTCommitRep_7399_UpdatesInRiskBoundShouldPersistForAllOpOfSameGroup"})
	private void T05_HKTCommitRep_7399_RiskBoundIsProperlyDisplayedAsPerProvidedBoundsOnCommitmentReport() {
		
		String agreementName="AutomationTestingAgreement"+Util.getTimeStamp_InMilliSec();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h = new IOTRONHomePage();
		
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(PagesTitle.MaintainIOTDiscountAgreements_P301, h.link_AgreementCapture,
				h.link_AgreementCapture_MaintainIOTDiscountAgreements);
		
		MaintainIOTDiscountAgreementsPage_P301 p301=new MaintainIOTDiscountAgreementsPage_P301();  
		CreateEditIOTDiscountAgreement_P303 p303=p301.clickOnCreateIOTDiscountAgreementButton();
		JSONManager json=new JSONManager(jsonFilePath, methodName,"createDiscAgreement");
		p303.createDiscAgreement_AllParams(agreementName, json);
		
		for (int i = 0; i < json.getJsonArrLength("DiscParam"); i++) {
			p303.addDiscountParameters(i,json);
		}
		
		// Now check the PMN Level Rates checkbox
		p303.checkPMNLevelRatesCheckbox();
		
		// Calculate Agreement
		p303.calculateAgreement(agreementName);
		
		//Logging Out with HKGTC User and Loggin in With NGC User
		new LoginPage().logoutThenPerformLogin("T05_HKTCommitRep_7399_NGC");
		
		//Navigate to Commitment Report, fetch the values and Verify the Risk Bound Modification are reflected or not
		CommitmentReport_P492 p492 = new CommitmentReport_P492();
		p492.T05_verifyRiskBounds_Modifications(agreementName,methodName);
		
		//Deleting the Agreement
		nav.traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, h.link_AgreementCapture,h.link_AgreementCapture_MaintainIOTDiscountAgreements);
		p301.searchAndDeleteAgreement(agreementName);
		
	}
	
	
	/**
	 * @author shailendra.rajawat 15-Jan-19
	 * 
	 * <pre>
	 * This T06_7400 is for NGC User
	 * 
	 * As per the requirement document 7400, We need to verify that "When new PMN is added to a group 
	 * its Risk Bound should be over written so that it is matching with every other PMNs of that group". 
	 * 
	 * Test Plan - 
	 *  
	 * 1. Modify HKGTC group and Remove the HKGNW client 
	 *	1.1 Go to "Maintain Operators having more than one PMN code" P479
	 * 	1.2 Search for HKGTC client and click on Edit icon
	 * 	1.3 Remove the "HKGNW" client from group and save the details
	 *    
	 * 2. Modify the Risk bounds for HKGNW client
	 *	2.1. Go to IOTRON - Welcome >> IOTRON System Administration >> Maintain IOT Clients >> Create/Edit IOT Client Page
	 *	2.2. Perform Search for client and click on Edit icon
	 *	2.3. Click on Commitment Report Size of Risk Bounds >> P602 should get displayed
	 *	2.4. Note down the values first
	 *	2.4. Modify the risk bounds and save
	 *
	 * 3. Add the HKGNW client back to the group
	 *	3.1 Go to "Maintain Operators having more than one PMN code" P479
	 * 	3.2 Search for HKGTC client and click on Edit icon
	 * 	3.3 Add the "HKGNW" client from group and save the details
	 *
	 * 4. Verify the Risk bounds for HKGNW client
	 *	4.1. Go to IOTRON - Welcome >> IOTRON System Administration >> Maintain IOT Clients >> Create/Edit IOT Client Page
	 *	4.2. Perform Search for client and click on Edit icon
	 *	4.3. Click on Commitment Report Size of Risk Bounds >> P602 should get displayed
	 *	4.4. Verify the Noted down value with the page
	 *
	 * </pre>
	 */
	@Test(groups = "Func Only", 
			description = "T06_HKT Commit Rep_7400_Risk Bound is updated on newely added PMN as per the main PMN",
					dependsOnMethods={"T04_HKTCommitRep_7399_UpdatesInRiskBoundShouldPersistForAllOpOfSameGroup"})
	private void T06_HKTCommitRep_7400_RiskBoundIsUpdatedOnNewelyAddedPMN() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		Navigator nav = new Navigator();
		IOTRONHomePage h = new IOTRONHomePage();
		
		//Remove the HKGNW client from HKGTC group
		CustomReporter.createNode("Remove the HKGNW client from HKGTC group");
		
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(PagesTitle.MaintainOperatorsHavingMoreThanOnePMNcode_P479, h.link_IOTRONSystemAdministration,
				h.link_IOTRONSystemAdministration_MaintainOperatorsHavingMoreThanOnePMNCode);
		
		JSONManager json=new JSONManager(jsonFilePath, methodName);
		String mainPMN=json.getStr("mainPmn");
		String otherPMN=json.getStr("otherPmn");
		MaintainOperatorsHavingMoreThanOnePMNcode_P479 p479=new MaintainOperatorsHavingMoreThanOnePMNcode_P479();
		CreateEditOperatorHavingMoreThanOnePmnCode_P495 p495=p479.searchAndClickEdit(mainPMN);

		json=new JSONManager(jsonFilePath, methodName,"modify_P495");
		p495.perform_CreateEditOperatorHavingMoreThanOnePmnCode(json);
		
		//Store and Modify the Risk bounds for HKGNW client
		CustomReporter.createNode("Modify the Risk bounds for HKGNW client");
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainIOTClients_P310, h.link_IOTRONSystemAdministration,
				h.link_IOTRONSystemAdministration_MaintainIOTClients);
		
		MaintainIOTClients_P310 p310=new MaintainIOTClients_P310();
		CreateEditIOTClient_P413 p413=p310.searchAndClickEdit(otherPMN);
		CommitmentReportSizeOfRiskBounds_P602 p602=p413.clickOnSetCommitmentReportSizeOfRiskBoundsButton();
		
		//Storing the bounds for later use
		json=new JSONManager(jsonFilePath, methodName,"store_RiskBoundsP602");
		p602.storeBoundsInJsonFile(json);
		
		//Modifying Bounds on CommitmentReportSizeOfRiskBounds_P602 page then Navigating back to CreateEditIOTClient_P413
		json=new JSONManager(jsonFilePath, methodName,"modify_RiskBoundsP602");
		p602.modifyBounds(json);
		
		//Add the HKGNW client back to the group
		CustomReporter.createNode("Add the HKGNW client back to the group");
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainOperatorsHavingMoreThanOnePMNcode_P479, h.link_IOTRONSystemAdministration,
				h.link_IOTRONSystemAdministration_MaintainOperatorsHavingMoreThanOnePMNCode);
		p479.searchAndClickEdit(mainPMN);
		json=new JSONManager(jsonFilePath, methodName,"reset_P495");
		p495.perform_CreateEditOperatorHavingMoreThanOnePmnCode(json);
		
		//Verify the Risk bounds with the stored values for HKGNW client
		CustomReporter.createNode("Verify the Risk bounds for HKGNW client");
		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainIOTClients_P310, h.link_IOTRONSystemAdministration,
				h.link_IOTRONSystemAdministration_MaintainIOTClients);
		
		p310.searchAndClickEdit(otherPMN);
		p602=p413.clickOnSetCommitmentReportSizeOfRiskBoundsButton();
		
		//Using the already Stored bounds for verification
		json=new JSONManager(jsonFilePath, methodName,"store_RiskBoundsP602");
		p602.verifyBounds(json);
		
	}
}
