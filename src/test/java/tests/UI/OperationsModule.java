/**
 * OperationsModule.java
 * 
 * This class will store the TestNG UI Tests of 
 * all pages that belongs to Opertions Module  
 * */

package tests.UI;

import org.testng.annotations.Test;

import objectRepository.Operations.CalculationSchedule_P689;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

public class OperationsModule {
	
	/**
	 * Navigate to P689 and verify the page UI
	 * @author shailendra.rajawat 25-Mar-2019
	 * */
	@Test(description="Verify UI of Calculation Schedule P689 page")
	private void UI_CalculationSchedule_P689() {
		IOTRONHomePage h = new IOTRONHomePage();
		Navigator nav = new Navigator();
		
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(PagesTitle.CalculationSchedule_P689, h.link_Operations,
				h.link_Operations_DataLoading, h.link_Operations_DataLoading_CalculationSchedule_P689);
		
		CalculationSchedule_P689 p689 = new CalculationSchedule_P689();
		p689.verify_UI("AUSTA");
	}
	
}
