package objectRepository.FCHAdministration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.xlUtil.DataTable;
import objectRepository.IOTRONUserConfiguration.ContactRoleAndTeamAssignment;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.NGCHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

public class SystemUsers {
	private SeleniumMethods com;
	public static String title = PagesTitle.SystemUsers.title;

	public SystemUsers() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	@FindBy(xpath = "//button[contains(.,'Create New User')]")
	private WebElement button_CreateNewUser;


	public void createNewUser(int row) {
		if(com.navigateToAndVerifyPageTitle(button_CreateNewUser,MaintainSystemUsers.title)){
			MaintainSystemUsers users= new MaintainSystemUsers();
			
			DataTable data=users.createUser(row); 
			String commaSeperatedRoles=data.getValue(row, "setupContactRoles");
			
			CustomReporter.createNode("Adding Roles for "+data.getValue(row, "username"));
			users.addRole(commaSeperatedRoles);
			
			String commaSepGroups=data.getValue(row, "AddGroups");
			if (!"".equalsIgnoreCase(commaSepGroups)) {
				CustomReporter.createNode("Adding Groups for "+data.getValue(row, "username"));
				users.addGroups(commaSepGroups);
			}
			
			com.click(users.button_Submit);

			com.wait(2);
			if(com.waitForElementTobe_Visible(users.button_CloseNotifications)){
				com.click(users.button_CloseNotifications);
			}
			
			
			String org=data.getValue(row, "organisation").toUpperCase();
			if(!org.equalsIgnoreCase("NEXTGEN")){
				CustomReporter.createNode("Setting RW Role in ContactRoleTeamAssignment for "+data.getValue(row, "username"));
				Navigator nav = new Navigator();
				IOTRONHomePage iotron=new IOTRONHomePage();
				nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.ContactRoleTeamAssignment,
						iotron.link_Operations, iotron.link_Operations_IOTRONUserConfiguration,
						iotron.link_Operations_IOTRONUserConfiguration_ContactRoleTeamAssignment);
				ContactRoleAndTeamAssignment role=new ContactRoleAndTeamAssignment();
				String userName=data.getValue(row,"username").toUpperCase();
				role.setRole(userName,org+"_RW");
				NGCHomePage fch=new NGCHomePage();
				nav.to_NGCHomePage().traverseMenu_VerifyPageTitle( PagesTitle.SystemUsers, fch.link_Administration,fch.link_Administration_SystemUsers);
			}
		}
	}
}
