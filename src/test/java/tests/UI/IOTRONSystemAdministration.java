package tests.UI;

import org.testng.annotations.Test;

import objectRepository.IOTRONSystemAdministration.CreateClientJobTitle;
import objectRepository.IOTRONSystemAdministration.EditClientJobTitle;
import objectRepository.IOTRONSystemAdministration.MaintainClientJobTitle;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

public class IOTRONSystemAdministration {
	/**@author prafull.barve*/
	@Test(description="UI_IOTRONSystemAdministration _Maintain Client Job Title(p695)")
	private void UI_IOTRONSystemAdministration_MaintainClientJobTitle_p695() 
	{
		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage Ihp = new IOTRONHomePage();
		nav.traverseMenu_VerifyPageTitle( PagesTitle.MaintainClientJobTitle, Ihp.link_IOTRONSystemAdministration,Ihp.link_IOTRONSystemAdministration_MaintainClientJobTitle);
		MaintainClientJobTitle MaintainClientJobTitle = new MaintainClientJobTitle();
		MaintainClientJobTitle.UIVerification("Telstra Corporation Limited");
	}

	/**@author prafull.barve*/
	@Test(description="UI_IOTRONSystemAdministration_Create Client Job Title(p696)")
	private void UI_IOTRONSystemAdministration_CreateClientJobTitle_p696() 
	{
		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage Ihp = new IOTRONHomePage();
		nav.traverseMenu_VerifyPageTitle( PagesTitle.MaintainClientJobTitle, Ihp.link_IOTRONSystemAdministration,Ihp.link_IOTRONSystemAdministration_MaintainClientJobTitle);
		CreateClientJobTitle CreateClientJobTitle = new CreateClientJobTitle();
		CreateClientJobTitle.UIVerification();
	}

	/**@author prafull.barve*/
	@Test(description="UI_IOTRONSystemAdministration_Edit Client Job Title(p696)")
	private void UI_IOTRONSystemAdministration_EditClientJobTitle_p696() 
	{
		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage Ihp = new IOTRONHomePage();
		nav.traverseMenu_VerifyPageTitle( PagesTitle.MaintainClientJobTitle, Ihp.link_IOTRONSystemAdministration,Ihp.link_IOTRONSystemAdministration_MaintainClientJobTitle);
		MaintainClientJobTitle MaintainClientJobTitle = new MaintainClientJobTitle();
		MaintainClientJobTitle.NavigateToEditClientJobTitle("Telstra Corporation Limited");
		EditClientJobTitle EditClientJobTitle = new EditClientJobTitle();
		EditClientJobTitle.UIVerification();
	}

}
