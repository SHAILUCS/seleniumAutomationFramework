package objectRepository.IOTRONSystemAdministration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;

public class CreateEditIOTClient_P413 {
	private SeleniumMethods com;

	public static String title="Create/Edit IOT Client";
	public CreateEditIOTClient_P413() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	@FindBy(id = "P413_MASTER_ENTITY_ID")
	private WebElement select_Client;

	@FindBy(id = "P413_ISB_0")
	private WebElement checkbox_ISB;

	@FindBy(id = "P413_IOT_FORECAST_TYPE_ID")
	private WebElement select_DefaultForecastMethod;

	@FindBy(id = "P413_HOME_CURRENCY")
	private WebElement select_HomeCurrency;

	@FindBy(xpath = "//button[.='Set Commitment Report Size of Risk Bounds']")
	private WebElement button_SetCommitmentReportSizeOfRiskBounds;
	
	@FindBy(xpath = "//button[.='Cancel']")
	private WebElement button_Cancel;
	
	public void validateForm() {
		if(com.isSelected(checkbox_ISB)){
			CustomReporter.report(STATUS.INFO, "ISB checkbox is checked");
		}else{
			CustomReporter.report(STATUS.FAIL, "ISB checkbox is NOT checked");
		}
		validateDropdownHasValue(select_Client,"Client");
		validateDropdownHasValue(select_HomeCurrency,"Home Currency value");
		validateDropdownHasValue(select_DefaultForecastMethod,"Default Forecast Method");
		
	}

	private void validateDropdownHasValue(WebElement Obj, String dropdownName) {
		String selectedObjVal=com.getFirstSelectedOptionVisibleText(Obj);
		if(selectedObjVal!=null){	
			CustomReporter.report(STATUS.INFO, "'"+selectedObjVal+"' is selected in the "+dropdownName+" Dropdown");
		}else{
			CustomReporter.report(STATUS.FAIL, "No value is selected in "+dropdownName+" Dropdown");
		}	
	}

	public String get_HomeCurrency_And_DefForecastMeth() {
		String homeCurrency=com.getFirstSelectedOptionVisibleText(select_HomeCurrency);
		String defForecastMeth=com.getFirstSelectedOptionVisibleText(select_DefaultForecastMethod);
		return homeCurrency+","+defForecastMeth;
	}

	/**
	 * @author shailendra.rajawat 11-Jan-19
	 * <pre>
	 * This method will click on button_SetCommitmentReportSizeOfRiskBounds, which 
	 * eventually navigate on to CommitmentReportSizeOfRiskBounds_P602.
	 *  Precon: P413 should be loaded, and button_SetCommitmentReportSizeOfRiskBounds should be visible
	 *  Postcon: P602 Will be loaded, if it does not load then execution will be stopped
	 *  @return new Object of CommitmentReportSizeOfRiskBounds_P602
	 * </pre>
	 * */
	public CommitmentReportSizeOfRiskBounds_P602 clickOnSetCommitmentReportSizeOfRiskBoundsButton() {
		com.click(button_SetCommitmentReportSizeOfRiskBounds, "Set Commitment Report Size of Risk Bounds Button");
		com.verifyPageTitle(CommitmentReportSizeOfRiskBounds_P602.title, true);
		return new CommitmentReportSizeOfRiskBounds_P602();
	}

	/**
	 * @author shailendra.rajawat 11-Jan-19
	 * <pre>
	 * This method will click on button_Cancel, which 
	 * eventually navigate on to MaintainIOTClients_P310.
	 *  Precon: P413 should be loaded, and button_Cancel should be visible
	 *  Postcon: P310 Will be loaded, if it does not load then execution will be stopped
	 *  @return new Object of MaintainIOTClients_P310
	 * </pre>
	 * */
	public MaintainIOTClients_P310 clickOnCancelButton() {
		com.click(button_Cancel);
		com.verifyPageTitle(MaintainIOTClients_P310.title, true);
		return new MaintainIOTClients_P310();
	}
	
	
}
