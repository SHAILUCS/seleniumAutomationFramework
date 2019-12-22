package objectRepository.AgreementCapture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;

public class ConfigurePMNs_P633 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Configure PMN(s)";

	public ConfigurePMNs_P633() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(id = "P633_CLIENT_PMNS_INCL_LEFT")
	private WebElement shuttle_HomeOp_Left;
	
	@FindBy(id = "P633_CLIENT_PMNS_INCL_RIGHT")
	private WebElement shuttle_HomeOp_Right;
	
	@FindBy(id = "P633_PARTNER_PMNS_INCL_LEFT")
	private WebElement shuttle_PartnerOp_Left;
	
	@FindBy(id = "P633_PARTNER_PMNS_INCL_RIGHT")
	private WebElement shuttle_PartnerOp_Right;

	@FindBy(xpath = "//button[.='Apply Changes']")
	private WebElement button_ApplyChanges;
	
	@FindBy(xpath = "//button[.='Cancel']")
	private WebElement button_Cancel;
	

	public void verify_UI() {

	}

	public void updateConfiguration(String[] homeOperatorArr, String[] partnerOperatorArr) {
		String message=" | ";
		if(homeOperatorArr!=null){
			com.deselectAllValues_Shuttle_DoubleClick(shuttle_HomeOp_Right);
			com.selectByPartialVisibleText_DoubleClick_FromArray(shuttle_HomeOp_Left, homeOperatorArr);
			message+="Home Operators: "+Arrays.toString(homeOperatorArr)+" | ";
		}
		

		if(partnerOperatorArr!=null){
			com.deselectAllValues_Shuttle_DoubleClick(shuttle_PartnerOp_Right);
			com.selectByPartialVisibleText_DoubleClick_FromArray(shuttle_PartnerOp_Left, partnerOperatorArr);
			message+="Partner Operators: "+Arrays.toString(partnerOperatorArr)+" | ";
		}
		
		CustomReporter.report(STATUS.INFO, "Updating Configuration "+message);
		com.click(button_ApplyChanges);
		com.verifyPageTitle(CreateEditIOTDiscountAgreement_P303.title);
	}

	

}
