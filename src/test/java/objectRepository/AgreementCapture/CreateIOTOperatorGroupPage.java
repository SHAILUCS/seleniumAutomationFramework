
package objectRepository.AgreementCapture;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;


public class CreateIOTOperatorGroupPage
{
	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	protected WebElement breadcrumb_IOTRONWelcome;
	
	@FindBy(xpath = "//li[contains(.,'Agreement Capture')]")
	protected WebElement breadcrumb_AgreementCapture;
	
	@FindBy(xpath = "//li[contains(.,'Maintain IOT Operator Groups')]")
	protected WebElement breadcrumb_MaintainIOTOperatorGroups;
	
	@FindBy(xpath = "//h1[contains(.,'Create/Edit IOT Operator Group')]")
	protected WebElement breadcrumb_CreateEditIOTOperatorGroup;
	
	@FindBy(xpath = "//h2[contains(.,'Create/Edit IOT Operator Group')]")
	protected WebElement region_CreateEditIOTOperatorGroup;
	
	@FindBy(xpath = "//label[@id='P412_IOT_OPERATOR_GRP_NAME_LABEL']")
	protected WebElement label_IOTOPERATORGRPNAMELABEL;
	
	@FindBy(xpath = "//input[@id='P412_IOT_OPERATOR_GRP_NAME']")
	protected WebElement textbox_IOTOPERATORGRPNAME;
	
	@FindBy(xpath = "//button[@title='Help Text: Group Name']")
	protected WebElement helpbutton_GroupName;
	
	@FindBy(xpath = "//label[@id='P412_DESCR_LABEL']")
	protected WebElement label_GroupDescription;
	
	@FindBy(xpath = "//textarea[@id='P412_DESCR']")
	protected WebElement textbox_GroupDescription;
	
	@FindBy(xpath = "//button[@title='Help Text: Group Description']")
	protected WebElement helpbutton_GroupDescription;
	
	@FindBy(xpath = "//label[@id='P412_HOME_GROUP_LABEL']")
	protected WebElement label_HOMEGROUP;
	
	@FindBy(xpath = "//label[@for='P412_HOME_GROUP_0']")
	protected WebElement textbox_HomeGroup;
	
	@FindBy(xpath = "//button[@title='Help Text: Is Home Group']")
	protected WebElement helpbutton_HomeGroup;
	
	@FindBy(xpath = "//button/span[contains(.,'Create')]")
	private WebElement button_Create;
	
	@FindBy(xpath = "//button/span[contains(.,'Cancel')]")
	protected WebElement button_Cancel;
		
    	
private SeleniumMethods com;
	
	public static String  title="Create/Edit IOT Operator Group";
	public CreateIOTOperatorGroupPage() {
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
		com.isElementPresent(button_Create, "Create, Button");
		com.isElementPresent(button_Cancel, "Cancel, Button");

	}

}
