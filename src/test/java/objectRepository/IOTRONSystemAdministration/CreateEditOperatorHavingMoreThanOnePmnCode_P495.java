package objectRepository.IOTRONSystemAdministration;

import java.util.Arrays;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.PagesTitle;

public class CreateEditOperatorHavingMoreThanOnePmnCode_P495 {
	private SeleniumMethods com;
	public static String title = PagesTitle.CreateEditOperatorHavingMoreThanOnePmnCode_P495.title;

	public CreateEditOperatorHavingMoreThanOnePmnCode_P495() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	public void verify_UI() {

	}

	/**
	 * This method will transfer the call to saveChanges(String[] additionalPMNs) method
	 * */
	public void perform_CreateEditOperatorHavingMoreThanOnePmnCode(JSONManager json){
		saveChanges(json.getStrArr("additionalPMNs"));
	}
	
	/**
	 * This method will make changes in the page and save the details
	 * PreCon: P495 should be loaded
	 * PostCon: P479 will be loaded
	 * @param additionalPMNs[] this will accept the array of TADIGS which need to be selected
	 * @author shailendra.rajawat 16 Jan 2019
	 * */
	public void saveChanges(String[] additionalPMNs){
		String message = " | ";
		
		if (additionalPMNs!=null) {
			com.deselectAllValues_Shuttle_DoubleClick(select_AdditionalPMN_Right);
			com.selectByPartialVisibleText_DoubleClick_FromArray(select_AdditionalPMN_Left, additionalPMNs);
			message += " additional PMNs:"+Arrays.toString(additionalPMNs)+" | ";
		}
		
		CustomReporter.report(STATUS.INFO, "Saving the changes "+message);
		com.click(button_ApplyChanges);
		com.verifyPageTitle(MaintainOperatorsHavingMoreThanOnePMNcode_P479.title, true);
	}
	
	@FindBy(xpath = "h1[.='Create / Edit Operator having more than one PMN code']")
	private WebElement heading_CreateEditOperatorHavingMoreThanOnePMNCode;
	
	@FindBy(id = "P495_PARENT_OPERATOR_ID_DISPLAY")
	private WebElement data_Operator;
	
	@FindBy(id = "P495_CHILD_OPERATOR_ID_LEFT")
	private WebElement select_AdditionalPMN_Left;
	
	@FindBy(id = "P495_CHILD_OPERATOR_ID_RIGHT")
	private WebElement select_AdditionalPMN_Right;
	
	@FindBy(xpath = "//button[.='Cancel']")
	private WebElement button_Cancel;

	@FindBy(xpath = "//button[.='Apply Changes']")
	private WebElement button_ApplyChanges;

	@FindBy(xpath = "//button[.='Delete']")
	private WebElement button_Delete;
	
}
