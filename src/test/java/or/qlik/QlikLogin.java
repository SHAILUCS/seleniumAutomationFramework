/**
 * QlikLogin.java
 * Login Page for Qlik Insight Application,
 * When performLogin method is called, the 
 * Insight page is loaded in the browser and login is performed
 * */

package or.qlik;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import common.configData_Util.Constant;
import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.xlUtil.DataTable;

public class QlikLogin {

	private SeleniumMethods com;
	private QlikCommon qcom;
	public static String title = "Login";

	public QlikLogin() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		qcom = new QlikCommon();
	}

	@FindBy(name = "username")
	private WebElement text_UserName;

	@FindBy(name = "pwd")
	private WebElement text_Password;

	@FindBy(id = "loginbtn")
	private WebElement button_Login;

	/**
	 * It will navigate to Qlik login page and 
	 * Based on the passed type this method will first 
	 * fetch the UserName and Password then send them to other method
	 * @author shailendra.rajawat 25-Mar-2019
	 * */
	public QlikHub performLogin(String type) {
		DataTable data= new DataTable(Constant.getTestDataFilePath(), Constant.getEnvironmentInfoSheet());
		if(com.verifyPageTitle(title,true)){
			int rowCount=data.getRowCount();
			int credentialsRow=-1;
			for(int row=1;row<rowCount;row++){
				String userType=data.getValue( row, "user type");
				if(type.equalsIgnoreCase(userType)){
					credentialsRow=row;
					break;
				}
			}

			if (credentialsRow!=-1) {
				String userName=data.getValue(credentialsRow,"username");
				String password=data.getValue(credentialsRow,"password");
				return sendUserPassAndClickLogin(type,userName, password);
			} else{
				CustomReporter.report(STATUS.FAIL, "Passsed user type '"+type+"' is not present in the test data sheet "+Constant.getEnvironmentInfoSheet());
			}
		}
		return null;
	}

	/**
	 * Passed username and password will be filled in the fields
	 * and login button will be clicked. Then an assertion will be performed
	 * @author shailendra.rajawat 25-Mar-2019
	 * */
	private QlikHub sendUserPassAndClickLogin(String type, String userName, String password) {
		com.sendKeys(text_UserName, userName);
		com.sendKeys(text_Password, password);
		com.click(button_Login);

		if(com.getTitle().equals((QlikHub.title))){
			//if(!com.waitForElementsTobe_NotVisible(By.xpath("//*[normalize-text()='Opening the hub']"),5)){
			if(com.waitForElementsTobe_NotVisible(qcom.icon_PageLoadingIndicator_Rain,30)){	
				CustomReporter.report(STATUS.PASS, "LOGIN SUCCEED for User | type " + type + " | username "+ userName);
				return new QlikHub();
			}else{
				CustomReporter.report(STATUS.FAIL, "HOME PAGE is not displayed for User | type " + type + " | username "+ userName);
				Assert.fail("HOME PAGE is not displayed for User | type " + type + " | username "+ userName);
			}
		}else{
			CustomReporter.report(STATUS.FAIL, "LOGIN FAILED for User | type " + type + " | username "+ userName);
			Assert.fail("LOGIN FAILED for User | type " + type + " | username "+ userName);
		}
		return null;
	}

	public void logout() {
			CustomReporter.report_ExitCurrentNode(STATUS.INFO, "Logout CODE TODO");
	}

}
