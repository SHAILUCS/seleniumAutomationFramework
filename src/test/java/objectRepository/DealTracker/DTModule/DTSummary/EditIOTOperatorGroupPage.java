package objectRepository.DealTracker.DTModule.DTSummary;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.AgreementCapture.CreateIOTOperatorGroupPage;

public class EditIOTOperatorGroupPage extends CreateIOTOperatorGroupPage

{
	@FindBy(xpath = "//button/span[contains(.,'Delete')]")
	private WebElement button_Delete;
	
	@FindBy(xpath = "//button/span[contains(.,'Save Changes')]")
	private WebElement button_SaveChanges;
	
	@FindBy(xpath = "//label[@id='P412_OPERATOR_FILTER_LABEL']")
	private WebElement label_OperatorFilter;
	
	@FindBy(xpath = "//input[@id='P412_OPERATOR_FILTER']")
	private WebElement textbox_OperatorFilter;
	
	@FindBy(xpath = "//label[@id='P412_IOT_OPER_GRP_MEMBER_LABEL']")
	private WebElement label_IOTOPERGRPMEMBER;
	
	@FindBy(xpath = "//select[@id='P412_IOT_OPER_GRP_MEMBER_LEFT']")
	private WebElement shuttle_IOTOPERGRPMEMBERLEFT;
	
	@FindBy(xpath = "//select[@id='P412_IOT_OPER_GRP_MEMBER_RIGHT']")
	private WebElement shuttle_IOTOPERGRPMEMBERRIGHT;
		
	
private SeleniumMethods com;
	
	public static String  title="Create/Edit IOT Operator Group";
	public EditIOTOperatorGroupPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	public void UIVerification()
	
	{
		CustomReporter.report(STATUS.INFO, "Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		com.isElementPresent(breadcrumb_MaintainIOTOperatorGroups, "Maintain IO TOperator Groups, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditIOTOperatorGroup, "Create Edit IOT Operator Group, BreadCrumb");
		com.isElementPresent(region_CreateEditIOTOperatorGroup, "Create Edit IOT Operator Group, Region");
		com.isElementPresent(label_IOTOPERATORGRPNAMELABEL, "IOT OPERATOR GRP NAME LABEL, Label");
		com.isElementPresent(textbox_IOTOPERATORGRPNAME, "IOT OPERATOR GRP NAME, Textbox");
		com.isElementPresent(helpbutton_GroupName, "Group Name, HelpButton");
		com.isElementPresent(label_GroupDescription, "Group Description, Label");
		com.isElementPresent(textbox_GroupDescription, "Group Description, Textbox");
		com.isElementPresent(helpbutton_GroupDescription, "Group Description, HelpButton");
		com.isElementPresent(label_HOMEGROUP, "HOME GROUP, Label");
		com.isElementPresent(textbox_HomeGroup, "HOME GROUP, Textbox");
		com.isElementPresent(helpbutton_HomeGroup, "Home Group, HelpButton");
		com.isElementPresent(button_Cancel, "Cancel, Button");
		com.isElementPresent(button_Delete, "Delete, Button");
		com.isElementPresent(button_SaveChanges, "SaveChanges, Button");
		com.isElementPresent(label_OperatorFilter, "Operator Filter, Label");
		com.isElementPresent(textbox_OperatorFilter, "Operator Filter, Textbox");
		com.isElementPresent(label_IOTOPERGRPMEMBER, "IOT OPER GRP MEMBER, Label");
		com.isElementPresent(shuttle_IOTOPERGRPMEMBERLEFT, "IOT OPER GRP MEMBER LEFT, Shuttle");
		com.isElementPresent(shuttle_IOTOPERGRPMEMBERRIGHT, "IOT OPER GRP MEMBER RIGHT, Shuttle");	
		
	}


}
