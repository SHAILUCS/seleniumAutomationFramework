package tests.MainRegression;

import org.testng.annotations.Test;

import com.configData_Util.Constant;
import com.jsonUtil.JSONManager;

import objectRepository.AgreementCapture.Rework.AgreementDetails_P16;
import objectRepository.AgreementCapture.Rework.MaintainIOTDiscountAgreements_p20;
import objectRepository.AgreementCapture.Rework.AgreementDetails.Administration_Tab_p15_AgreementChecklist_Sec;
import objectRepository.AgreementCapture.Rework.AgreementDetails.Administration_Tab_p15_AgreementNotes_Sec;
import objectRepository.AgreementCapture.Rework.AgreementDetails.Administration_Tab_p15_DocLibs_Sec;
import objectRepository.AgreementCapture.Rework.AgreementDetails.Administration_Tab_p15_ManualForecast_Sec;
import objectRepository.AgreementCapture.Rework.AgreementDetails.Administration_Tab_p15_SignTrack_Sec;
import objectRepository.AgreementCapture.Rework.AgreementDetails.Administration_Tab_p15_TAPLevelAgrAdm_Sec;
import objectRepository.AgreementCapture.Rework.AgreementDetails.Details_Tab_p15;
import objectRepository.AgreementCapture.Rework.AgreementDetails.DiscountParameters_Tab_p15;
import objectRepository.AgreementCapture.Rework.AgreementDetails.History_Tab_p16;
import objectRepository.AgreementCapture.Rework.AgreementDetails.IOTDiscountAgreementDetails_p15_Parent;
import objectRepository.AgreementCapture.Rework.AgreementDetails.Settlement_Tab_p15;
import objectRepository.common.Navigator;

public class AgreementRework {

	public static final String jsonFilePath = Constant.getResourcesFolderPath() + "AgreementRework.json";

	/**
	 * UI and Func Test for "P20 - IOT Discount Agreeement List" Page  
	 * */
	@Test(groups={"UI with Func"},priority=1,description = "AgreementRework: P20 New IOT Discount Agreeement List page: (1) Content Verification, "
			+ "(2) Report Type func check, "
			+ "(3) Select columns option check, "
			+ "(4)'View' link functionality with Back navigation")
	private void AgrRwrk_P20_NewIOTDiscAgrmntListPage_Content_ReportType_SelectCol_ViewLinkWithBackNav() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		agreements.AgrRwrk_NewIOTDiscAgrmntListPage_Content_ReportType_SelectCol_ViewLinkWithBackNav();
	}

	/**
	 * UI and Func Test for P15 "Details Tab"  
	 * */
	@Test(groups={"UI with Func"},description = "AgreementRework: P15 'Agreement Details' Page (1) 'Details Tab' Content verification")
	private void AgrRwrk_P15_DetailsTab_VerifyContent() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			Details_Tab_p15 detailsTab=new Details_Tab_p15();
			detailsTab.verify_UI();
		}
		
		JSONManager json=new JSONManager(jsonFilePath, "&{Title}");
		json.getStr("IOTRONWelcomePage");
		
		
	}

	/**
	 * UI and Func Test for P15 "Discount Parameters Tab"  
	 * */
	@Test(groups={"UI with Func"},description = "AgreementRework: P15 'Agreement Details' Page (1) 'Discount Parameters Tab' Content verification")
	private void AgrRwrk_P15_DiscountParametersTab_VerifyContent() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			DiscountParameters_Tab_p15 discParamTab=new DiscountParameters_Tab_p15();
			discParamTab.verify_UI();
		}
	}

	/**
	 * UI and Func Test for P15 "Settlement Tab"  
	 * */
	@Test(groups={"UI with Func"},description = "AgreementRework: P15 'Agreement Details' Page (1) 'Settlement Tab' Content verification")
	private void AgrRwrk_P15_SettlementTab_VerifyContent() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			Settlement_Tab_p15 settlementTab=new Settlement_Tab_p15();
			settlementTab.verify_UI();
		}
	}

	/**
	 * UI and Func Test for P15 "Document Library" section under Administration Tab  
	 * */
	@Test(groups={"UI with Func"},description = "AgreementRework: P15 'Agreement Details' Page (1) 'Administration Tab >> Document Library Section' Content verification")
	private void AgrRwrk_P15_AdmTab_DocLibSec_VerifyContent() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			Administration_Tab_p15_DocLibs_Sec docLibs_Sec=new Administration_Tab_p15_DocLibs_Sec();
			docLibs_Sec.verify_UI();
		}
	}
	

	/**
	 * UI and Func Test for P15 "Signature Tracking" section under Administration Tab  
	 * */
	@Test(groups={"UI with Func"},description = "AgreementRework: P15 'Agreement Details' Page (1) 'Administration Tab >> Signature Tracking Section' Content verification")
	private void AgrRwrk_P15_AdmTab_SignTrackSec_VerifyContent() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			Administration_Tab_p15_SignTrack_Sec signTrack_Sec=new Administration_Tab_p15_SignTrack_Sec();
			signTrack_Sec.verify_UI();
		}
	}	

	/**
	 * UI and Func Test for P15 "Agreement Checklist" section under Administration Tab  
	 * */
	@Test(groups={"UI with Func"},description = "AgreementRework: P15 'Agreement Details' Page (1) 'Administration Tab >> Agreement Checklist Section' Content verification")
	private void AgrRwrk_P15_AdmTab_AgrChecklistSec_VerifyContent() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			Administration_Tab_p15_AgreementChecklist_Sec agreementChecklist_Sec=new Administration_Tab_p15_AgreementChecklist_Sec();
			agreementChecklist_Sec.verify_UI();
		}
	}	

	/**
	 * UI and Func Test for P15 "Agreement Notes" section under Administration Tab  
	 * */
	@Test(groups={"UI with Func"},description = "AgreementRework: P15 'Agreement Details' Page (1) 'Administration Tab >> Agreement Notes Section' Content verification")
	private void AgrRwrk_P15_AdmTab_AgrNotesSec_VerifyContent() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			Administration_Tab_p15_AgreementNotes_Sec agreementNotesSec=new Administration_Tab_p15_AgreementNotes_Sec();
			agreementNotesSec.verify_UI();
		}
	}

	/**
	 * UI and Func Test for P15 "TAP Level Traffic Split" section under Administration Tab  
	 * */
	@Test(groups={"UI with Func"},description = "AgreementRework: P15 'Agreement Details' Page (1) 'Administration Tab >> TAP Level Traffic Split Section' Content verification")
	private void AgrRwrk_P15_AdmTab_TapLevelTrafSplit_VerifyContent() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			Administration_Tab_p15_TAPLevelAgrAdm_Sec tapLevelAgrAdm_Sec=new Administration_Tab_p15_TAPLevelAgrAdm_Sec();
			tapLevelAgrAdm_Sec.verify_UI();
		}
	}
	
	/**
	 * UI and Func Test for P15 "History Tab"  
	 * */
	@Test(groups={"UI with Func"},description = "AgreementRework: P15 'Agreement Details' Page (1) 'History Tab Content verification")
	private void AgrRwrk_P15_HistoryTab_VerifyContent() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			History_Tab_p16 history_Tab_p16=new History_Tab_p16();
			history_Tab_p16.verify_UI();
		}
	}

	/**
	 * Regression Test for CRUD Operation on Document Library under Administration Tab  
	 * */
	@Test(groups={"Regression"},dependsOnMethods={"AgrRwrk_P15_AdmTab_DocLibSec_VerifyContent"},description = "AgreementRework: P15 'Document Library Section' CRUD Verification")
	private void AgrRwrk_P15_DocLibSec_CRUD() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			Administration_Tab_p15_DocLibs_Sec docLibs_Sec=new Administration_Tab_p15_DocLibs_Sec();
			docLibs_Sec.CRUD_verification();
		}
	}
	

	/**
	 * UI and Func Test for "Manual Forecasting" section under Administration Tab
	 * */
	@Test(groups={"UI with Func"},description = "AgreementRework: P15 'Agreement Details' Page (1) 'Administration Tab >> Manual Forecast Section' Content verification")
	private void AgrRwrk_P15_AdmTab_ManualForecast_VerifyContent() {
		//TODO Need to create an agreement once new page is fully working
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			Administration_Tab_p15_ManualForecast_Sec manualForecast_Sec=new Administration_Tab_p15_ManualForecast_Sec();
			manualForecast_Sec.verify_UI();
		}
	}
	
	
	/**
	 * Regression Test for CRUD Operation on Signature Tracking Section under Administration Tab  
	 * */
	@Test(groups={"Regression"},description = "AgreementRework: P15 'Signature Tracking Section' CRUD Verification")
	private void AgrRwrk_P15_SignTrackSec_CRUD() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			Administration_Tab_p15_SignTrack_Sec signTrack_Sec=new Administration_Tab_p15_SignTrack_Sec();
			int row=3;
			signTrack_Sec.CRUD_verification(row,"AgrRwrk_P15_SignTrackSec_CRUD");
		}
	}
	
	/**
	 * Regression Test for CRUD Operation on Agreement Checklist Section under Administration Tab  
	 * */
	@Test(groups={"Regression"},description = "AgreementRework: P15 'Agreement Checklist Section' CRUD Verification")
	private void AgrRwrk_P15_AgrChecklistSec_CRUD() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			Administration_Tab_p15_AgreementChecklist_Sec checklist_Sec=new Administration_Tab_p15_AgreementChecklist_Sec();
			int row=2;
			checklist_Sec.CRUD_verification(row,"AgrRwrk_P15_AgrChecklistSec_CRUD");
		}
	}	
	
	/**
	 * Regression Test for CRUD Operation on Agreement Notes Section under Administration Tab  
	 * */
	@Test(groups={"Regression"},description = "AgreementRework: P15 'Agreement Notes Section' CRUD Verification")
	private void AgrRwrk_P15_AgrNotes_CRUD() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			Administration_Tab_p15_AgreementNotes_Sec agreementNotesSec=new Administration_Tab_p15_AgreementNotes_Sec();
			agreementNotesSec.CRUD_verification("AgrRwrk_P15_AgrNotes_CRUD");
		}
	}
	
	/**
	 * Regression Test for CRUD Operation on TAP Level Traffic Split Section under Administration Tab  
	 * */
	@Test(groups={"Regression"},description = "AgreementRework: P15 'TAP Level Traffic Split Section' CRUD Verification")
	private void AgrRwrk_P15_TAPLevelTrafficSplit_CRUD() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			Administration_Tab_p15_TAPLevelAgrAdm_Sec tapLevelAgrAdm_Sec=new Administration_Tab_p15_TAPLevelAgrAdm_Sec();
			//TODO 
			tapLevelAgrAdm_Sec.CRUD_verification_TAPLevelTrafficSplit("AgrRwrk_P15_TAPLevelTrafficSplit_CRUD");
		}
	}
	
	/**
	 * Regression Test for CRUD Operation on Rounding & Correction Factors Section under Administration Tab  
	 * */
	@Test(groups={"Regression"},description = "AgreementRework: P15 'Rounding & Correction Factors' CRUD Verification")
	private void AgrRwrk_P15_RoundingCorrectionFactors_CRUD() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			Administration_Tab_p15_TAPLevelAgrAdm_Sec tapLevelAgrAdm_Sec=new Administration_Tab_p15_TAPLevelAgrAdm_Sec();
			tapLevelAgrAdm_Sec.CRUD_verification_RoundingCorrectionFactors("AgrRwrk_P15_RoundingCorrectionFactors_CRUD");
		}
	}
	
	/**
	 * Regression Test for P15 "History Tab"  
	 * */
	@Test(groups={"Regression"},description = "AgreementRework: P15 'History Tab' CRUD verification")
	private void AgrRwrk_P15_HistoryTab_CRUD() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		if(agreements.clickOnViewLink(null)){
			History_Tab_p16 history_Tab_p16=new History_Tab_p16();
			history_Tab_p16.CRUD_Verification("AgrRwrk_P15_HistoryTab_CRUD");
		}
	}
	
	/**
	 * UI and Func Test for P15 "Virtual Agreements"
	 * */
	@Test(groups={"UI with Func"},description = "AgreementRework: P15 'Virtual Agreements' Content verification")
	private void AgrRwrk_P15_VirtualAgreements_VerifyContent() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(MaintainIOTDiscountAgreements_p20.title, 20);
		MaintainIOTDiscountAgreements_p20 agreements = new MaintainIOTDiscountAgreements_p20();
		agreements.performSearch(null, null, false, false, true, null);
		if(agreements.clickOnViewLink(null)){
			IOTDiscountAgreementDetails_p15_Parent p15_Parent=new IOTDiscountAgreementDetails_p15_Parent();
			p15_Parent.verify_UI_VirtualAgreements();
		}
	}
	
	/**
	 * UI Test for P16 "Create Agreement - Step 1" with AUSTA user
	 * */
	@Test(groups={"UI Only"},description = "AgreementRework: P16 'Add Agreement Details Step-1' Content verification - AUSTA")
	private void AgrRwrk_P16_AddAgreementDetailsStep_1_VerifyContent_AUSTA() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(AgreementDetails_P16.title, 16);
		AgreementDetails_P16 agreementsDet = new AgreementDetails_P16();
		agreementsDet.verify_UI_AUSTA();
	}
	

	/**
	 * UI Test for P16 "Create Agreement - Step 1"  with SWEHU user Hutch
	 * */
	@Test(groups={"UI Only"},description = "AgreementRework: P16 'Add Agreement Details Step-1' Content verification - SWEHU(Hutch)")
	private void AgrRwrk_P16_AddAgreementDetailsStep_1_VerifyContent_SWEHU() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(AgreementDetails_P16.title, 16);
		AgreementDetails_P16 agreementsDet = new AgreementDetails_P16();
		agreementsDet.verify_UI_SWEHU();

	}
	

	/**
	 * UI Test for P16 "Create Agreement - Step 1"  with USAVZ user Verizon
	 * */
	@Test(groups={"UI Only"},description = "AgreementRework: P16 'Add Agreement Details Step-1' Content verification - USAVZ(Verizon)")
	private void AgrRwrk_P16_AddAgreementDetailsStep_1_VerifyContent_USAVZ() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(AgreementDetails_P16.title, 16);
		AgreementDetails_P16 agreementsDet = new AgreementDetails_P16();
		agreementsDet.verify_UI_USAVZ();

	}
	
	/**
	 * UI Test for P16 "Create Agreement - Step 1"  with USAW6 user TMO
	 * */
	@Test(groups={"UI Only"},description = "AgreementRework: P16 'Add Agreement Details Step-1' Content verification - USAW6(TMO)")
	private void AgrRwrk_P16_AddAgreementDetailsStep_1_VerifyContent_USAW6() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(AgreementDetails_P16.title, 16);
		AgreementDetails_P16 agreementsDet = new AgreementDetails_P16();
		agreementsDet.verify_UI_USAW6();

	}
	

	
	/**
	 * UI Test for P16 "Create Agreement - Step 1"  with GBROR user EE 
	 * */
	@Test(groups={"UI Only"},description = "AgreementRework: P16 'Add Agreement Details Step-1' Content verification - GBROR(EE)")
	private void AgrRwrk_P16_AddAgreementDetailsStep_1_VerifyContent_GBROR() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(AgreementDetails_P16.title, 16);
		AgreementDetails_P16 agreementsDet = new AgreementDetails_P16();
		agreementsDet.verify_UI_GBROR();
	}
	
	/**
	 * UI Test for P16 "Create Agreement - Step 1"  with POL03 user Orange Poland 
	 * */
	@Test(groups={"UI Only"},description = "AgreementRework: P16 'Add Agreement Details Step-1' Content verification - POL03(Orange Poland)")
	private void AgrRwrk_P16_AddAgreementDetailsStep_1_VerifyContent_POL03() {
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().navigateToPageNumber(AgreementDetails_P16.title, 16);
		AgreementDetails_P16 agreementsDet = new AgreementDetails_P16();
		agreementsDet.verify_UI_POL03();
	}
	
	
}