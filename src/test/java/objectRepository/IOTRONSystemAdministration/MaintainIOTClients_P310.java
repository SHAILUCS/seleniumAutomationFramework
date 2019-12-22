package objectRepository.IOTRONSystemAdministration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.common.ApexCommon;
import tests.MainRegression.Module_DiscountAgreement;

public class MaintainIOTClients_P310 {
	private SeleniumMethods com;
	private ApexCommon comm;
	 
	@FindBy(xpath = "//button[.='Apply Changes']")
	private WebElement button_ApplyChanges;
	

	public static String title="Maintain IOT Clients";
	public MaintainIOTClients_P310() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}
	
	
	/**
	 * @author shailendra.rajawat 9-Jan-19
	 * <pre>
	 * This method will search for the PMN, then navigate on to  
	 * Create/Edit IOT Client page 413 for that client.
	 *  Precon: P310 should be loaded
	 *  Postcon: P413 Will be loaded, if it does not load then execution will be stopped
	 *  @param clientName This pmn will be searched in the table
	 * </pre>
	 * */
	public CreateEditIOTClient_P413 searchAndClickEdit(String clientName){
		String message=" ";
		message=message+" |clientName- '"+clientName+"'";
		CustomReporter.report(STATUS.INFO, "Performing search operation for data :"+message);
		if(comm.performSearch(clientName, "Client")){
			WebTable tab= new WebTable(comm.table_ResultTabData);
			com.click(tab.getChildObject(2, 1, "img", 0),"Edit Icon");
			com.verifyPageTitle(CreateEditIOTClient_P413.title, true);
			return new CreateEditIOTClient_P413();
		}
		return null;
	}

	public String get_HomeCurrency_And_DefForecastMeth(String... jsonObjName) {
		JSONManager json= new JSONManager(Module_DiscountAgreement.jsonFilePath, jsonObjName);
		searchAndClickEdit(json.getStr("clientName"));
		CreateEditIOTClient_P413 createEditIOTClient= new CreateEditIOTClient_P413();
		createEditIOTClient.validateForm();
		return createEditIOTClient.get_HomeCurrency_And_DefForecastMeth();
	}
		
}
