package objectRepository.FCHAdministration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.Constant;
import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import common.xlUtil.DataTable;

public class MaintainSystemUsers {
	private SeleniumMethods com;
	public static String title = "Maintain System Users";

	public MaintainSystemUsers() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	@FindBy(id = "P11_MASTER_ENTITY_ID")
	private WebElement select_Organisation;

	@FindBy(id = "P11_FIRST_NAME")
	private WebElement text_FirstName;

	@FindBy(id = "P11_LAST_NAME")
	private WebElement text_LastName;

	@FindBy(id = "P11_USER_NAME")
	private WebElement text_LoginUserName;

	@FindBy(id = "P11_PASSWORD")
	private WebElement text_Password;

	@FindBy(id = "P11_CONFIRM_PASSWORD")
	private WebElement text_ConfirmPassword;

	@FindBy(id = "P11_EMAIL1")
	private WebElement text_Email;

	@FindBy(id = "P11_CONTACT_ROLE_ID")
	private WebElement select_ContactRole;

	@FindBy(xpath = "//button[contains(.,'Create')]")
	private WebElement button_Create;

	@FindBy(xpath = "//h2[contains(.,'User Account Created')]")
	private WebElement data_SuccessMsg;

	@FindBy(xpath = "//button[contains(.,'Add Row')]")
	private WebElement button_AddRow;

	@FindBy(xpath = "//button[contains(.,'Submit')]")
	public WebElement button_Submit;

	@FindBy(css = "button[title='Close Notification']")
	public WebElement button_CloseNotifications;

	@FindBy(xpath = "//button[contains(.,'Add Group Member Association')]")
	private WebElement button_AddGroupMemberAssociation;

	public DataTable createUser(int row) {
		String sheetName=Constant.getEnvironmentInfoSheet();
		DataTable data= new DataTable(Constant.getTestDataFilePath(), sheetName);
		
		CustomReporter.createNode("Creating New User for "+data.getValue(row, "username"));
		com.selectByPartialVisibleText(select_Organisation, data.getValue(row, "organisation"));
		com.sendKeys(text_FirstName, data.getValue(row, "firstName"));
		com.sendKeys(text_LastName, data.getValue(row, "lastName"));
		String user=data.getValue(row, "username");
		com.sendKeys(text_LoginUserName, user);
		String pwd=data.getValue(row, "password");
		if (pwd.equals("")) {
			Util.killExcelProcess();
			pwd="Auto$" + (new SimpleDateFormat("ddMMYYHHmmss").format(new Date()));
			data.setValue(row, "password",pwd);
		}
		com.sendKeys(text_Password,pwd );
		com.sendKeys(text_ConfirmPassword, data.getValue(row, "password"));
		com.sendKeys(text_Email, data.getValue(row, "email"));
		com.selectByPartialVisibleText(select_ContactRole, data.getValue(row, "contactRole"));

		com.click(button_Create);
		if(com.waitForElementTobe_Visible(data_SuccessMsg, "User Account Created success message")){
			CustomReporter.report(STATUS.PASS, "'"+user+"' successfully created with pwd "+pwd);
		}else{
			CustomReporter.report(STATUS.FAIL, "'"+user+"' failed to create with pwd "+pwd);
		}
		return data;
	}


	public void addRole(String commaSeperatedRoles) {
		String[] roles;
		if(commaSeperatedRoles.contains(",")){
			roles=commaSeperatedRoles.split(",");
		}else{
			roles= new String[1];
			roles[0]=commaSeperatedRoles;
		}

		WebTable tab= new WebTable(By.cssSelector("table[summary='Setup Contact Roles']"));
		for (int i = 0; i < roles.length; i++) {
			int oldRowCnt=tab.getRowCount();
			com.click(button_AddRow);
			int newRowCnt=tab.getRowCount();
			if (newRowCnt>oldRowCnt) {
				com.selectByVisibleText(tab.getChildObject(newRowCnt, 2, "select", 0),roles[i],true);
			} else {
				CustomReporter.report(STATUS.FAIL, "New Row is not getting added in the Contact Roles Section");
				break;
			}
		}
		
	}


	public void addGroups(String commaSepGroups) {
		if ("y".equalsIgnoreCase(commaSepGroups)) {

			int groupMembers=0;
			do{
				com.click(button_AddGroupMemberAssociation);
				com.verifyPageTitle(AddEditGroupMemberAssociation.title);
				AddEditGroupMemberAssociation obj = new AddEditGroupMemberAssociation();
				List<String> list=com.getAllOptionsVisibleText(obj.dropDown_OperatorGroupMember);
				groupMembers=list.size();
				
				com.selectByVisibleText(obj.dropDown_OperatorGroupMember, list.get(groupMembers-1),true);
				com.javaScript_Click(obj.checkbox_AccessToIOTClientData);
				com.click(obj.button_Create);
			}while(groupMembers>2);
			
		}else{
			
			String[] groups;
			if(commaSepGroups.contains(",")){
				groups=commaSepGroups.split(",");
			}else{
				groups= new String[1];
				groups[0]=commaSepGroups;
			}

			for (int i = 0; i < groups.length; i++) {
				com.click(button_AddGroupMemberAssociation);
				com.verifyPageTitle(AddEditGroupMemberAssociation.title);
				AddEditGroupMemberAssociation obj = new AddEditGroupMemberAssociation();
				
				com.selectByVisibleText(obj.dropDown_OperatorGroupMember, groups[i],true);
				com.javaScript_Click(obj.checkbox_AccessToIOTClientData);
				com.click(obj.button_Create);
			}
			
		}
		
		
	}

}
