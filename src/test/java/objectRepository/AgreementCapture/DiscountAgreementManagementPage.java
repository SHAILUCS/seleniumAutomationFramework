package objectRepository.AgreementCapture;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class DiscountAgreementManagementPage {
	
	private SeleniumMethods com;
	
	@FindBy(xpath = "//th[contains(.,'Select Task')]")
	private WebElement data_SelectTask;
	
	@FindBy(linkText = "Maintain IOT Discount Agreements")
	public WebElement link_MaintainIOTDiscountAgreements;
	
	@FindBy(linkText = "Agreement Checklist review")
	public WebElement link_AgreementChecklistReview;
	
	@FindBy(linkText = "Maintain IOT Discount Party Details")
	public WebElement link_MaintainIOTDiscountPartyDetails;
	
	@FindBy(linkText = "Maintain IOT Operator Groups")
	public WebElement link_MaintainIOTOperatorGroups;
	
	@FindBy(linkText = "Zone definition")
	public WebElement link_ZoneDefinition;
	
	@FindBy(linkText = "SOX Report")
	public WebElement link_SOXReport;
	
	
	public static String  title="Discount Agreement Management";
	public DiscountAgreementManagementPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	/**@author prafull.barve**/
	public void UIVerification() 
	{
		CustomReporter.report(STATUS.INFO, "Verifying region Headers");
		com.isElementPresent(data_SelectTask, "Select Task, region");
		
		CustomReporter.report(STATUS.INFO, "Verifying Select Task region Contents");	
		com.isElementPresent(link_MaintainIOTDiscountAgreements, "Maintain IOT Discount Agreements, Link");		
		com.isElementPresent(link_AgreementChecklistReview, "Agreement Checklist review, Link");		
		com.isElementPresent(link_MaintainIOTDiscountPartyDetails, "Maintain IOT Discount Party Details, Link");		
		com.isElementPresent(link_MaintainIOTOperatorGroups, "Maintain IOT Operator Groups, Link");
		com.isElementPresent(link_ZoneDefinition, "Zone definition, Link");		
		com.isElementPresent(link_SOXReport, "SOX Report, Link");
	}
}
