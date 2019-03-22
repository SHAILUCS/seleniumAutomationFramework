package tests.MainRegression;

import org.testng.annotations.Test;

import common.configData_Util.Constant;
import objectRepository.AgreementCapture.MaintainTrafficSegmentationRules_P35;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

public class Module_AgreementCapture {

	public static final String jsonFilePath = Constant.getResourcesFolderPath() + "Module_AgreementCapture.json";
	
	@Test(description = "T01_Verify Validations On Maintain Traffic Segmentation Rules_P35 Page")
	public void T01_VerifyValidationsOnMaintainTrafficSegmentationRules_P35(){
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		
		IOTRONHomePage h = new IOTRONHomePage();
		Navigator nav = new Navigator(); 
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(PagesTitle.MaintainTrafficSegmentationRules_P35,
				h.link_AgreementCapture, h.link_AgreementCapture_MaintainTrafficSegmentationRules_P35);
		MaintainTrafficSegmentationRules_P35 p35 = new MaintainTrafficSegmentationRules_P35();
		p35.verify_Validations(methodName);
	}
}
