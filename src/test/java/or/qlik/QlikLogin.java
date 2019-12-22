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

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.xlUtil.DataTable;

public class QlikLogin {

	private SeleniumMethods com;
	private QlikCommon qcom;
	public static String title = "Qlik Sense login page";

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
	
	@FindBy(xpath = "//span[starts-with(@class,'user-name')]")
	private WebElement data_UserName;
	
	@FindBy(xpath = "//span[contains(@class,'log-out')]")
	private WebElement icon_Logout;
	
	@FindBy(id = "loginbtn")
	private WebElement button_Login_OnLogoutPage;
	
	
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
			CustomReporter.report(STATUS.FAIL, "LOGIN FAILED for User | type " + type + " | username "+ userName + " Expected Title "+ QlikHub.title + " But found "+ com.getTitle());
			Assert.fail("LOGIN FAILED for User | type " + type + " | username "+ userName);
		}
		return null;
	}

	public void logout() {
		// In case login failed, then user will still be present on Login page, then we will just stop this method execution
		String title = com.getTitle();
		if(title.toLowerCase().contains("login") || !title.toLowerCase().contains("app")){
			return;
		}
		
		/* 
		 * Switching back to hub so that other app can be opened in new tab 
		 * and logging out process can be started
		 * */
		QlikHub hub = new QlikHub();
		hub.switchBackToHub();
		
		int retry = 5;
		while(true){
			// Repeat this stuff, till the passed Stream is not visible, Or the retry attempts are exceeded
			com.click_UsingAction(data_UserName, "data_UserName");
			com.wait(1);
			
			if(retry-- == 0){
				CustomReporter.report(STATUS.FAIL, "Failed to make logout icon visible after multiple Retry attempts");
				break;
			}
				
			if( ! com.waitForElementsTobe_NotVisible(icon_Logout,1)){
				CustomReporter.report(STATUS.INFO, "Logout icon displayed");
				break;
			}

		}
		
		com.click_UsingAction(icon_Logout);
		if(com.waitForElementTobe_Visible(button_Login_OnLogoutPage)){
			CustomReporter.report(STATUS.PASS, "You have been logged out. Page is displayed");
			com.click_UsingAction(button_Login_OnLogoutPage);
			com.waitForElementTobe_Visible(text_UserName,"Login Page");
		}else{
			CustomReporter.report(STATUS.FAIL, "Oh snap, what happened now??");
		}
	}

}
