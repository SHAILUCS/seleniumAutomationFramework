package tests.UI;

import org.testng.annotations.Test;

import com.configData_Util.Util;

import objectRepository.AgreementCapture.ZoneDefinition;
import objectRepository.Reporting.OverviewReport_P507;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.NGCHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

public class UI_Verification {
	
	@Test(description = "UI_NGC Home Page(p35)")
	public void UI_NGCHomePage() {

		NGCHomePage ngcHome = new NGCHomePage();
		ngcHome.verify_UI();
	}
	
	/**@author prafull.barve*/
	@Test(description="Iotron_Welcome_Page")
	public void UI_IOTRONWelcome() 
	{
		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage iotronHomePage=new IOTRONHomePage();
		iotronHomePage.UIVerification();
	}

	//TODO Austa user is not yet present
	@Test(description = "UI_Report_Overview Report with AUSTA user(p507)")
	public void UI_Report_OverviewReportAUSTAUser() {
		//User = Austa
		Navigator nav= new Navigator();
		IOTRONHomePage ihp= new IOTRONHomePage();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.ZoneDefinition, ihp.link_AgreementCapture, ihp.link_AgreementCapture_ZoneDefinition);
		ZoneDefinition zoneDefinition=new ZoneDefinition();
		if(zoneDefinition!=null){
			String zoneName="AutomationTesting"+Util.getTimeStamp_InMilliSec();
			if(zoneDefinition.createZone(zoneName,"Zone Desc",Util.getArray("AIA","BRA","CAF","ESP","GHA"))){
				nav.traverseMenu_VerifyPageTitle( PagesTitle.OverviewReport_P507, ihp.link_Reporting, ihp.link_Reporting_OverviewReport);
				OverviewReport_P507 overviewReport=new OverviewReport_P507();
				if(overviewReport!=null){
					overviewReport.verify_UI();
					nav.traverseMenu_VerifyPageTitle( PagesTitle.ZoneDefinition, ihp.link_AgreementCapture, ihp.link_AgreementCapture_ZoneDefinition);
					zoneDefinition.deleteZone(zoneName);
				}

			}
		}

	}

	@Test(/*priority=1,*/description = "verify Navigation Links Functionality_ Reporting(p252)")
	private void verifyNavigationLinksFunctionality_Reporting() {

		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage iotronHomePage=new IOTRONHomePage();
		iotronHomePage.verifyNavigationLinksFunctionality_Reporting();
	}



	@Test(/*priority=2,*/description = "verify Navigation Links Functionality_ IOTRON System Administration(p252)")
	private void verifyNavigationLinksFunctionality_IOTRONSystemAdministration() {

		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage iotronHomePage=new IOTRONHomePage();
		iotronHomePage.verifyNavigationLinksFunctionality_IOTRONSystemAdministration();
	}

	@Test(/*priority=3,*/description = "verify Navigation Links Functionality_ Forecasting(p252)")
	private void verifyNavigationLinksFunctionality_Forecasting() {
		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage iotronHomePage=new IOTRONHomePage();
		iotronHomePage.verifyNavigationLinksFunctionality_Forecasting();
	}

	@Test(/*priority=4,*/description = "verify Navigation Links Functionality_ Business Intelligence(p252)")
	private void verifyNavigationLinksFunctionality_BusinessIntelligence() {

		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage iotronHomePage=new IOTRONHomePage();
		iotronHomePage.verifyNavigationLinksFunctionality_BusinessIntelligence();
	}

	@Test(/*priority=5,*/description = "verify Navigation Links Functionality_ Settlement(p252)")
	private void verifyNavigationLinksFunctionality_Settlement() {

		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage iotronHomePage=new IOTRONHomePage();
		iotronHomePage.verifyNavigationLinksFunctionality_Settlement();

	}	

	@Test(/*priority=6,*/description = "verify Navigation Links Functionality_ Operations(p252)")
	private void verifyNavigationLinksFunctionality_Operations() {

		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage iotronHomePage=new IOTRONHomePage();
		iotronHomePage.verifyNavigationLinksFunctionality_Operations();

	}


}
