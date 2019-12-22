package objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class AgreementChecklist_Tab extends EditIOTDiscountAgreement_Tab
{
	
	@FindBy(xpath = "//a[contains(.,'Agreement Checklist')]")
	private WebElement tab_AgreementChecklist;
	
	@FindBy(xpath = "//h2[contains(.,'Agreement Checklist')]")
	private WebElement region_AgreementChecklist;	
	
	private By tab = By.cssSelector("table[summary='Agreement Checklist']");
	
	private List<String> tableColumns = new ArrayList<>(Arrays.asList("Update","Agreement Phase","Check Sequence",
			"Checklist Item","Job Function","Completed","Date Completed","Update Date",
			"Updated By"));
	
	private SeleniumMethods com;
	
	public static String  title="Create / Edit IOT Discount Agreement";
	public AgreementChecklist_Tab() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	/**@author prafull.barve**/
	public void UIVerification() 
	{
		com.click(tab_AgreementChecklist, "Agreement Checklist,Tab");
		CustomReporter.createNode("Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountAgreements, "Maintain IOT Discount Agreements, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountAgreement, "Create Edit IOT Discount Agreement, BreadCrumb");
		com.isElementPresent(tab_AgreementChecklist, "Agreement Checklist, tab");
		com.isElementPresent(region_AgreementChecklist, "AgreementChecklist, region");
		/*
		CustomReporter.report(Status.INFO, "Verifying the Table columns");
		ApexCommon comm= new ApexCommon();
		comm.verifyColumnHeaders(tab,tableColumns, 1);	*/	
		
	}
}
