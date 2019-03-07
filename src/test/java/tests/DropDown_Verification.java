package tests;

import java.util.List;

import org.testng.annotations.Test;

import objectRepository.DealTracker.DTModule.NavigatorDT;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_Header;
import objectRepository.IOTRONSystemAdministration.MaintainClientDefinedCountriesMapping;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

public class DropDown_Verification {

	/** @author prafull.barve */
	@Test(description = "DealTracker Country Dropdown Verification of NLDPT")
	public void DealTracker_Country_DropDown_P4_NLDPT() {
		Country_DropDown_Verification_COMMONS("KPN", "NLDPT");

	}

	/** @author prafull.barve */
	@Test(description = "DealTracker Country Dropdown Verification of GBROR")
	public void DealTracker_Country_DropDown_P4_GBROR() {
		Country_DropDown_Verification_COMMONS("Everything Everywhere Limited (GBROR)", "GBROR");

	}

	private void Country_DropDown_Verification_COMMONS(String name, String TADIG) {
		IOTRONHomePage ihp = new IOTRONHomePage();
		Navigator nav = new Navigator();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainClientDefinedCountriesMapping,
				ihp.link_IOTRONSystemAdministration,
				ihp.link_IOTRONSystemAdministration_MaintainClientDefinedCountriesMapping);
		MaintainClientDefinedCountriesMapping Obj = new MaintainClientDefinedCountriesMapping();
		List<String> list_expected = Obj.GetCountries(name);
		NavigatorDT navDT = new NavigatorDT();
		DTSummary_Header dts = navDT.to_DealTrackerHome().to_DealTrackerSummary();
		dts.DropDownVerifaction(TADIG, list_expected);
	}

	
	public static void main(String[] args) {
		
		System.out.println("k-1.2%.-5%".replaceAll("[^0-9.-]", ""));
		
	}
}
