package objectRepository.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.customReporting.snapshot.SnapshotManager;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.xlUtil.DataTable;

public class LoginPage {

	private SeleniumMethods com;

	@FindBy(id="P22_USERNAME")
	private WebElement text_UserName;

	@FindBy(id="P22_PASSWORD")
	private WebElement text_Password;

	@FindBy(xpath="//a[.='Login'] | //button[.='Login']")
	private WebElement button_Login;

	public static String title="Login Page";
	public LoginPage(){
		com=new SeleniumMethods();
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	public void logout(){
		String baseUrl = new DataTable(Constant.getTestDataFilePath(), Constant.getEnvironmentInfoSheet()).getValue(1, "url");
		com.navigateTo(baseUrl);
		if(com.isAlertPresent()){
			CustomReporter.report_ExitCurrentNode(STATUS.INFO,"Leave site? Alert displayed "+com.closeAlertAndGetItsText(true));
		}
		
		if(com.verifyPageTitle(title)){
			CustomReporter.report_ExitCurrentNode(STATUS.PASS, "Logout succeed");
		}else{
			CustomReporter.report_ExitCurrentNode(STATUS.FAIL, "Logout failed");
		}
	}
	
	public boolean logoutThenPerformLogin(String type){
		NGCHomePage ngcHome = new NGCHomePage();
		CustomReporter.createNode("Logging out ["+com.getText(ngcHome.data_UserName)+"] and Logging in with ["+type+"] user");
		com.click_UsingAction(ngcHome.data_UserName);
		com.click(ngcHome.link_Logout);
		return performLogin(type);
	}

	public boolean performLogin(String type) {
		CustomReporter.report(STATUS.INFO, "Login Process Start for user type: "+"<br/><b style='font-size: small;'>"+type+"</b>");
		boolean bool=false;
		SeleniumMethods com=new SeleniumMethods();
		if(com.verifyPageTitle(title,true)){
			DataTable DataTable= new DataTable(Constant.getTestDataFilePath(), Constant.getEnvironmentInfoSheet());
			int rowCount=DataTable.getRowCount();
			int credentialsRow=-1;
			for(int row=1;row<rowCount;row++){
				String userType=DataTable.getValue( row, "user type");
				if(type.equalsIgnoreCase(userType)){
					credentialsRow=row;
					break;
				}
			}
			
			if (credentialsRow!=-1) {
				String userName=DataTable.getValue(credentialsRow,"username");
				String password=DataTable.getValue(credentialsRow,"password");
				bool=sendUserPassAndClickLogin(type,userName, password);
			} else{
				CustomReporter.report(STATUS.FAIL, "Passsed user type '"+type+"' is not present in the test data sheet "+Constant.getEnvironmentInfoSheet());
			}
		}
		
		if (!bool) {
			//Assert.fail("Login Failed");
		}
		
		return bool;
	}
	
	public static synchronized boolean sendUserPassAndClickLogin(WebElement text_UserName,WebElement text_Password,WebElement button_Login, String type,String user, String pass){
		boolean bool=false;
		SeleniumMethods com=new SeleniumMethods();
		com.sendKeys(text_UserName,user);
		com.sendKeys(text_Password,pass);
		com.click(button_Login);

		if(com.getTitle().equals((NGCHomePage.title))){
			CustomReporter.report(STATUS.PASS, "'<b>" + NGCHomePage.title + "</b>' page is displayed <br/>"+com.getCurrentUrl());
			bool=true;
			NGCHomePage home= new NGCHomePage();
			if(home.verifyLoggedInUser(user)){
				CustomReporter.report(STATUS.PASS, "User type ["+type+"] with username '"+user.toUpperCase()+"' got displayed on NGC Home Page");
				SnapshotManager.takeSnapShot("NGC_HOME");
			}else{
				CustomReporter.report(STATUS.FAIL, "User type ["+type+"] with username '"+user.toUpperCase()+"' NOT displayed on NGC Home Page");
			}
		}else if(com.getTitle().equals((ChangeEndUserPassword.title))){
			ChangeEndUserPassword changeEndUserPassword=new ChangeEndUserPassword();
			bool=changeEndUserPassword.checkAndChangePassword(user);
		}else if(com.getTitle().equals((IOTRONHomePage.title))){
			bool=true;
			CustomReporter.report(STATUS.WARNING, "IOTRON Home Page is getting displayed");
			SnapshotManager.takeSnapShot("IOTRON_HOME");
		}else{
			CustomReporter.report(STATUS.FAIL, "Login Failed: '"+com.getTitle()+"' page is getting displayed for user "+user);
		}
		return bool;
	}

	public boolean sendUserPassAndClickLogin(String type,String user, String pass){
		return sendUserPassAndClickLogin(text_UserName, text_Password, button_Login, type, user, pass);
	}


}
