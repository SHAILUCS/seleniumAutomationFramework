package objectRepository.FCHAdministration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;

public class AddEditGroupMemberAssociation {
	
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Add/Edit Group Member Association";
	
	@FindBy(xpath = "//select[@id='P356_OPERATOR_GROUP_MEMBER_ID']")
	public WebElement dropDown_OperatorGroupMember;
	
	@FindBy(xpath = "//input[@id='P356_ACCESS_FOR_IOT_0']")
	public WebElement checkbox_AccessToIOTClientData;
	
	@FindBy(xpath = "//button[contains(.,'Create')]")
	public WebElement button_Create;
	
	public AddEditGroupMemberAssociation() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	private List<String> colNames = new ArrayList<>(Arrays.asList());

	public void verify_UI() {

	}
	
}
