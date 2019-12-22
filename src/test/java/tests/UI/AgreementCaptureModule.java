package tests.UI;

import org.testng.annotations.Test;

import com.configData_Util.Util;

import objectRepository.AgreementCapture.AgreementChecklistReview;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAddressPage;
import objectRepository.AgreementCapture.CreateEditIOTDiscountBankAccountPage;
import objectRepository.AgreementCapture.CreateIOTDiscountContactPage;
import objectRepository.AgreementCapture.CreateIOTDiscountPartyDetailsPage;
import objectRepository.AgreementCapture.CreateIOTOperatorGroupPage;
import objectRepository.AgreementCapture.CreateZonePage;
import objectRepository.AgreementCapture.EditIOTDiscountContactPage;
import objectRepository.AgreementCapture.EditIOTDiscountPartyDetailsPage;
import objectRepository.AgreementCapture.EditZonePage;
import objectRepository.AgreementCapture.MaintainIOTDiscountAgreementsPage_P301;
import objectRepository.AgreementCapture.MaintainIOTDiscountPartyDetailsPage;
import objectRepository.AgreementCapture.MaintainIOTOperatorGroupsPage;
import objectRepository.AgreementCapture.MaintainTrafficSegmentationRules_P35;
import objectRepository.AgreementCapture.SOXReport;
import objectRepository.AgreementCapture.ZoneDefinition;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg.AgreementChecklist_Tab;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg.AgreementNotes_Tab;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg.AgreementParties_Tab;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg.CopyDiscountAgreement_Tab;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg.DiscountParameters_Tab;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg.DocumentLibrary_Tab;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg.EditIOTDiscountAgreement_Tab;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg.SettlementStatement_Tab;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg.SignatureTracking_Tab;
import objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg.UpdateIOTAgreementChecklistItempage;
import objectRepository.DealTracker.DTModule.DTSummary.EditIOTOperatorGroupPage;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import objectRepository.common.TADIG;

public class AgreementCaptureModule 
{
	/**@author prafull.barve*/
	@Test(description="UI_Maintain IOT Discount Agreements(p301)")
	public void UI_MaintainIOTDiscountAgreements_p301() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		maintainIOTDiscountAgreementsPage.UIVerification();			
	}
	
	/**@author prafull.barve*/
	@Test(description="UI_Maintain IOT Discount Party Details(p298)")
	public void UI_MaintainIOTDiscountPartyDetails_p298() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountPartyDetails, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountPartyDetails);
		MaintainIOTDiscountPartyDetailsPage MaintainIOTDiscountPartyDetailsPage=new MaintainIOTDiscountPartyDetailsPage();
		MaintainIOTDiscountPartyDetailsPage.UIVerification();			
	}
	
	/**@author prafull.barve*/
	@Test(description="Create IOT Discount Party Details(p299)")
	public void UI_CreateIOTDiscountPartyDetails_p299() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountPartyDetails, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountPartyDetails);
		MaintainIOTDiscountPartyDetailsPage MaintainIOTDiscountPartyDetailsPage = new MaintainIOTDiscountPartyDetailsPage();
		MaintainIOTDiscountPartyDetailsPage.navigateToCreatePage();
		CreateIOTDiscountPartyDetailsPage CreateIOTDiscountPartyDetailsPage = new CreateIOTDiscountPartyDetailsPage(); 
		CreateIOTDiscountPartyDetailsPage.UIVerification();
	}

	/**@author prafull.barve*/
	@Test(description="Edit IOT Discount Party Details(p299)")
	public void UI_EditIOTDiscountPartyDetails_p299() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountPartyDetails, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountPartyDetails);
		MaintainIOTDiscountPartyDetailsPage obj = new MaintainIOTDiscountPartyDetailsPage();
		obj.navigateToEditPage();
		EditIOTDiscountPartyDetailsPage EditIOTDiscountPartyDetailsPage=new EditIOTDiscountPartyDetailsPage();
		EditIOTDiscountPartyDetailsPage.UIVerification();
	}
	

	/**@author prafull.barve*/
	@Test(description="Create IOT Discount Contact(p290)")
	public void UI_CreateIOTDiscountContact_p290() 
	{

		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountPartyDetails, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountPartyDetails);
		MaintainIOTDiscountPartyDetailsPage MaintainIOTDiscountPartyDetailsPage = new MaintainIOTDiscountPartyDetailsPage();
		MaintainIOTDiscountPartyDetailsPage.navigateToCreatePage();
		CreateIOTDiscountPartyDetailsPage CreateEditIOTDiscountPartyDetailsPage=new CreateIOTDiscountPartyDetailsPage();
		CreateEditIOTDiscountPartyDetailsPage.SelectPartyandClickONCreateContact(TADIG.AUSTA);
		CreateIOTDiscountContactPage CreateIOTDiscountContactPage = new CreateIOTDiscountContactPage();
		CreateIOTDiscountContactPage.UIVerification();
	}

	/**@author prafull.barve*/
	@Test(description="Edit IOT Discount Contact(p290)")
	public void UI_EditIOTDiscountContact_p290() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountPartyDetails, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountPartyDetails);
		MaintainIOTDiscountPartyDetailsPage MaintainIOTDiscountPartyDetailsPage = new MaintainIOTDiscountPartyDetailsPage();
		MaintainIOTDiscountPartyDetailsPage.navigateToCreatePage();
		CreateIOTDiscountPartyDetailsPage CreateEditIOTDiscountPartyDetailsPage=new CreateIOTDiscountPartyDetailsPage();
		CreateEditIOTDiscountPartyDetailsPage.SelectPartyandClickONEditContact(TADIG.AUSTA);
		EditIOTDiscountContactPage EditIOTDiscountContactPage = new EditIOTDiscountContactPage();
		EditIOTDiscountContactPage.UIVerification();
	}
	
	/**@author prafull.barve*/
	@Test(description="Create/Edit IOT Discount Address(p296)")
	public void UI_CreateEditIOTDiscountAddress_p296() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountPartyDetails, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountPartyDetails);
		MaintainIOTDiscountPartyDetailsPage MaintainIOTDiscountPartyDetailsPage = new MaintainIOTDiscountPartyDetailsPage();
		MaintainIOTDiscountPartyDetailsPage.navigateToCreatePage();
		CreateIOTDiscountPartyDetailsPage CreateEditIOTDiscountPartyDetailsPage=new CreateIOTDiscountPartyDetailsPage();
		CreateEditIOTDiscountPartyDetailsPage.SelectPartyandClickONCreateAddress(TADIG.AUSTA);
		CreateEditIOTDiscountAddressPage CreateEditIOTDiscountAddressPage = new CreateEditIOTDiscountAddressPage();
		CreateEditIOTDiscountAddressPage.UIVerification();
	}
	
	/**@author prafull.barve*/
	@Test(description="Create/Edit IOT Discount Bank Account(p255)")
	public void UI_CreateEditIOTDiscountBankAccount_p255() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountPartyDetails, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountPartyDetails);
		MaintainIOTDiscountPartyDetailsPage MaintainIOTDiscountPartyDetailsPage = new MaintainIOTDiscountPartyDetailsPage();
		MaintainIOTDiscountPartyDetailsPage.navigateToCreatePage();
		CreateIOTDiscountPartyDetailsPage CreateEditIOTDiscountPartyDetailsPage=new CreateIOTDiscountPartyDetailsPage();
		CreateEditIOTDiscountPartyDetailsPage.SelectPartyandClickONCreateBankAccount(TADIG.AUSTA);
		CreateEditIOTDiscountBankAccountPage CreateEditIOTDiscountBankAccountPage = new CreateEditIOTDiscountBankAccountPage();
		CreateEditIOTDiscountBankAccountPage.UIVerification();
	}
	
	/**@author prafull.barve*/
	@Test(description="UI_Maintain IOT Operator Groups(p311)")
	public void UI_MaintainIOTOperatorGroups_p311() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTOperatorGroups, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTOperatorGroups);
		MaintainIOTOperatorGroupsPage MaintainIOTOperatorGroupsPage=new MaintainIOTOperatorGroupsPage();			
		MaintainIOTOperatorGroupsPage.UIVerification();			
	}
	
	/**@author prafull.barve*/
	@Test(description="UI_Create IOT Operator Group(p412)")
	public void UI_CreateIOTOperatorGroup_p412() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTOperatorGroups, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTOperatorGroups);
		MaintainIOTOperatorGroupsPage MaintainIOTOperatorGroupsPage=new MaintainIOTOperatorGroupsPage();
		MaintainIOTOperatorGroupsPage.navigateToCreateIOTOperatorGroupPage();
		CreateIOTOperatorGroupPage CreateIOTOperatorGroupPage = new CreateIOTOperatorGroupPage();
		CreateIOTOperatorGroupPage.UIVerification();
	}
	

	/**@author prafull.barve*/
	@Test(description="UI_EDIT IOT Operator Group(p412)")
	public void UI_EditIOTOperatorGroup_p412() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTOperatorGroups, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTOperatorGroups);
		MaintainIOTOperatorGroupsPage MaintainIOTOperatorGroupsPage=new MaintainIOTOperatorGroupsPage();
		MaintainIOTOperatorGroupsPage.navigateToEditIOTOperatorGroupPage();
		EditIOTOperatorGroupPage EditIOTOperatorGroupPage = new EditIOTOperatorGroupPage();
		EditIOTOperatorGroupPage.UIVerification();
	}
	

	/**@author prafull.barve*/
	@Test(description="UI_Agreement Checklist Review(p453)")
	public void UI_AgreementChecklistReview_p453() 
	{
		Navigator nav= new Navigator();	
		IOTRONHomePage i=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AgreementChecklistReview, i.link_AgreementCapture, i.link_AgreementCapture_AgreementChecklistReview);
		AgreementChecklistReview AgreementChecklistReview=new AgreementChecklistReview();
		AgreementChecklistReview.UIVerification();
	}
	

	/**@author prafull.barve*/
	@Test(description="UI_Update IOT Agreement Checklist Item(p455)")
	public void UI_UpdateIOTAgreementChecklistItem_p455() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage i=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.AgreementChecklistReview, i.link_AgreementCapture, i.link_AgreementCapture_AgreementChecklistReview);
		AgreementChecklistReview AgreementChecklistReview=new AgreementChecklistReview();
		AgreementChecklistReview.navigateToUpdateIOTAgreementChecklistItempage();
		UpdateIOTAgreementChecklistItempage UpdateIOTAgreementChecklistItempage = new UpdateIOTAgreementChecklistItempage();
		UpdateIOTAgreementChecklistItempage.UIVerification();
	}
	
	/**@author prafull.barve*/
	@Test(description="UI_Zone Definition(p502)")
	public void UI_ZoneDefinition_p502() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.ZoneDefinition, ihp.link_AgreementCapture, ihp.link_AgreementCapture_ZoneDefinition);
		ZoneDefinition ZoneDefinition=new ZoneDefinition();
		ZoneDefinition.UIVerification();
	}
	
	/**@author prafull.barve*/
	@Test(description="UI_CreateZone(p505)")
	public void UI_CreateZone_p505() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.ZoneDefinition, ihp.link_AgreementCapture, ihp.link_AgreementCapture_ZoneDefinition);
		ZoneDefinition ZoneDefinition=new ZoneDefinition();
		ZoneDefinition.navigateToCreateZonePage();
		CreateZonePage CreateZonePage = new CreateZonePage();
		CreateZonePage.UIVerification();
	}

	/**@author prafull.barve*/
	@Test(description="UI_EditZone(p505)")
	public void UI_EditZone_p505()    
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.ZoneDefinition, ihp.link_AgreementCapture, ihp.link_AgreementCapture_ZoneDefinition);
		ZoneDefinition ZoneDefinition=new ZoneDefinition();
		ZoneDefinition.navigateToEditZonePage();
		EditZonePage EditZonePage = new EditZonePage();
		EditZonePage.UIVerification();

	}

	/**@author prafull.barve*/
	@Test(description="UI_SOX Report(p663)")
	public void UI_SOXReport_p663() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.SOXReport, ihp.link_AgreementCapture,ihp.link_AgreementCapture_SOXReport);
		SOXReport SOXReport=new SOXReport();
		SOXReport.UIVerification(TADIG.AUSTA,"01-Jan-2018","31-Jan-2018");
	}
	

	/**@author prafull.barve*/
	@Test(description="UI_Edit IOT Discount Agreement_Tab(p303)")
	public void UI_EditIOTDiscountAgreement_Tab_p303() 
	{

		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();	
		EditIOTDiscountAgreement_Tab EditIOTDiscountAgreement_Tab = maintainIOTDiscountAgreementsPage.EditIOTDiscountAgreement("Afghanistan:MTN SEP17 AUG18");

		/*nav.to_MaintainIOTDiscountAgreements();			
			MaintainIOTDiscountAgreementsPage maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage();
			maintainIOTDiscountAgreementsPage.EditIOTDiscountAgreement();
			EditIOTDiscountAgreement_Tab EditIOTDiscountAgreement_Tab = new EditIOTDiscountAgreement_Tab();*/
		EditIOTDiscountAgreement_Tab.UIVerification();
	}

	/**@author prafull.barve*/
	@Test(description="UI_Agreement Parties_Tab(p303)")
	public void UI_AgreementParties_Tab_p303() 
	{
			Navigator nav= new Navigator();
			IOTRONHomePage ihp=new IOTRONHomePage();
			nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
			MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
			maintainIOTDiscountAgreementsPage.EditIOTDiscountAgreement("Afghanistan:MTN SEP17 AUG18");
			AgreementParties_Tab AgreementParties_Tab = new AgreementParties_Tab();
			AgreementParties_Tab.UIVerification();
	}

	/**@author prafull.barve*/
	@Test(description="UI_Discount Parameters_Tab(p303)")
	public void UI_DiscountParameters_Tab_p303() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		maintainIOTDiscountAgreementsPage.EditIOTDiscountAgreement("Afghanistan:MTN SEP17 AUG18");
		DiscountParameters_Tab DiscountParameters_Tab = new DiscountParameters_Tab();
		DiscountParameters_Tab.UIVerification();
	}

	/**@author prafull.barve*/
	@Test(description="UI_SettlementStatement_Tab(p303)")
	public void UI_SettlementStatement_Tab_p303() 
	{

		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		maintainIOTDiscountAgreementsPage.EditIOTDiscountAgreement("Afghanistan:MTN SEP17 AUG18");
		SettlementStatement_Tab SettlementStatement_Tab = new SettlementStatement_Tab();
		SettlementStatement_Tab.UIVerification();
	}

	/**@author prafull.barve*/
	@Test(description="UI_DocumentLibrary_Tab(p303)")
	public void UI_DocumentLibrary_Tab_p303() 
	{

		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		maintainIOTDiscountAgreementsPage.EditIOTDiscountAgreement("Afghanistan:MTN SEP17 AUG18");
		DocumentLibrary_Tab DocumentLibrary_Tab = new DocumentLibrary_Tab();
		DocumentLibrary_Tab.UIVerification();
	}

	/**@author prafull.barve*/
	@Test(description="UI_AgreementNotes_Tab(p303)")
	public void UI_AgreementNotes_Tab_p303() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		maintainIOTDiscountAgreementsPage.EditIOTDiscountAgreement("Afghanistan:MTN SEP17 AUG18");
		AgreementNotes_Tab AgreementNotes_Tab = new AgreementNotes_Tab();
		AgreementNotes_Tab.UIVerification();
	}

	/**@author prafull.barve*/
	@Test(description="UI_SignatureTracking_Tab(p303)")
	public void UI_SignatureTracking_Tab_p303() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		maintainIOTDiscountAgreementsPage.EditIOTDiscountAgreement("Afghanistan:MTN SEP17 AUG18");
		SignatureTracking_Tab SignatureTracking_Tab = new SignatureTracking_Tab();
		SignatureTracking_Tab.UIVerification();
	}

	/**@author prafull.barve*/
	@Test(description="UI_Copy Discount Agreement_Tab(p303)")
	public void UI_CopyDiscountAgreement_Tab_p303() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		maintainIOTDiscountAgreementsPage.EditIOTDiscountAgreement("Afghanistan:MTN SEP17 AUG18");
		CopyDiscountAgreement_Tab CopyDiscountAgreement_Tab = new CopyDiscountAgreement_Tab();
		CopyDiscountAgreement_Tab.UIVerification();
	}


	/**@author prafull.barve*/
	@Test(description="UI_Agreement Checklist_Tab(p303)")
	public void UI_AgreementChecklist_Tab_p303() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
		MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
		maintainIOTDiscountAgreementsPage.EditIOTDiscountAgreement("Afghanistan:MTN SEP17 AUG18");
		AgreementChecklist_Tab AgreementChecklist_Tab = new AgreementChecklist_Tab();
		AgreementChecklist_Tab.UIVerification();
	}

	

	/**
	 * Navigate to MaintainTrafficSegmentationRules_P35 page, 
	 * and verify the existence of page objects
	 * precon: Menu Links should be displayed on any page
	 * postcon: all objects of P35 will be verified   
	 * @author shailendra.rajawat
	 * */
	@Test(description="UI_Maintain Traffic Segmentation Rules_P35")
	public void UI_MaintainTrafficSegmentationRules_P35() 
	{
		Navigator nav= new Navigator();
		IOTRONHomePage ihp=new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(PagesTitle.MaintainTrafficSegmentationRules_P35, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainTrafficSegmentationRules_P35);
		MaintainTrafficSegmentationRules_P35 p35=new MaintainTrafficSegmentationRules_P35();
		p35.verify_UI( Util.getArray("AUSTA") );
	}
}
