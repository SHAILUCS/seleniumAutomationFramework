package objectRepository.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.xlUtil.DataTable;

public class ChangeEndUserPassword {

	@FindBy(id = "P229_USERNAME")
	private WebElement text_UserName;

	@FindBy(id = "P229_CURRENT_PASSWORD")
	private WebElement text_CurrentPassword;

	@FindBy(id = "P229_NEW_PASSWORD")
	private WebElement text_NewPassword;

	@FindBy(id = "P229_CONFIRM_NEW_PASSWORD")
	private WebElement text_ConfirmPassword;

	@FindBy(xpath = "//button[contains(.,'Apply Changes')]")
	private WebElement button_ApplyChanges;

	@FindBy(xpath = "//button[contains(.,'Return')]")
	private WebElement button_Return;

	public static String title = "Forgot Password Recovery";

	public ChangeEndUserPassword() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		Util.killExcelProcess();
	}
	
	public boolean checkAndChangePassword(String userName) {
		return checkAndChangePassword(text_UserName, text_CurrentPassword, text_NewPassword, text_ConfirmPassword, button_ApplyChanges, button_Return, userName);
	}
	
	public static synchronized boolean checkAndChangePassword(WebElement text_UserName, WebElement text_CurrentPassword,
			WebElement text_NewPassword, WebElement text_ConfirmPassword, WebElement button_ApplyChanges,
			WebElement button_Return, String userName) {
		SeleniumMethods com= new SeleniumMethods();
		boolean bool = false;
		CustomReporter.report(STATUS.WARNING,
				"Password expired for user: '" + userName + "' changing the password now.");
		String sheetName=Constant.getEnvironmentInfoSheet();
		
		DataTable dataTable=new DataTable(Constant.getTestDataFilePath(), sheetName);
		
		int rowCount = dataTable.getRowCount();

		for (int row = 1; row < rowCount; row++) {
			String userNameTestData = dataTable.getValue(row, "username");
			if (userName.equalsIgnoreCase(userNameTestData)) {
				String type = dataTable.getValue(row, "user type");
				com.sendKeys(text_UserName, userName);
				String oldPassword = dataTable.getValue(row, "password");
				com.sendKeys(text_CurrentPassword, oldPassword);
				String newPassword = "Auto$" + (new SimpleDateFormat("ddMMYYHHmmss").format(new Date()));
				System.out.println("New password created: '" + newPassword + "' for user: " + userName);
				com.sendKeys(text_NewPassword, newPassword);
				com.sendKeys(text_ConfirmPassword, newPassword);
				com.click(button_ApplyChanges);
				if (com.isElementPresent(button_Return, "Password change success page")) {
					String baseUrl = dataTable.getValue( 1, "url");
					com.get(baseUrl);
					LoginPage log=new LoginPage();
					if (log.sendUserPassAndClickLogin(type,userName, newPassword)) {
						dataTable.setValue(row, "password", newPassword);
						System.out.println("Password updated in excel: " + newPassword + " for user: " + userName);
						bool = true;
					}
				}
				break;
			}
		}
		return bool;
	}
}
