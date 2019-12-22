package objectRepository.IOTRONUserConfiguration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class ContactRoleAndTeamAssignment {
	private SeleniumMethods com;
	public static String title = "Contact Role & Team Assignment";

	public ContactRoleAndTeamAssignment() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	@FindBy(id = "P528_CONTACT_ID")
	private WebElement select_SystemUser;
	
	@FindBy(id = "P528_CONTACT_ROLE_ID")
	private WebElement select_Role;
	
	@FindBy(xpath = "//button[contains(.,'Create')]")
	private WebElement button_Create;
	
	@FindBy(css = "button[title='Close Notification']")
	private WebElement button_CloseNotifications;
	
	public void setRole(String userName, String role) {
		com.selectByPartialVisibleText(select_SystemUser, userName);
		com.selectByVisibleText(select_Role, role,true);
		com.click(button_Create, "Create button");
	
		com.wait(2);
		if(com.isElementPresent(button_CloseNotifications)){
			com.click(button_CloseNotifications);
		}
	}
	
	
}
