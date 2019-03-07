package objectRepository.AgreementCapture.CreateEditIOTDiscountAgreementPkg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;

public class SignatureTracking_Tab extends EditIOTDiscountAgreement_Tab {
	
	@FindBy(xpath = "//a[contains(.,'Signature Tracking')]")
	private WebElement tab_SignatureTracking;
	
	@FindBy(xpath = "//h2[contains(.,'Signature Tracking')]")
	private WebElement region_SignatureTracking;
	
	private By tab = By.cssSelector("table[summary='Signature Tracking']");
	
	private List<String> tableColumns = new ArrayList<>(Arrays.asList("Home or Partner","Operator Name","First Signatory",
			"First Signatory Date","Second Signatory","Second Signatory Date","Signature Status","Last Updated",
			"Updated By","Signature Remarks"));
	
	private SeleniumMethods com;
	
	public static String  title="Create / Edit IOT Discount Agreement";
	public SignatureTracking_Tab() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	public void UIVerification() 
	{
		com.click(tab_SignatureTracking, "Signature Tracking,Tab");
		CustomReporter.createNode("Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountAgreements, "Maintain IOT Discount Agreements, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountAgreement, "Create Edit IOT Discount Agreement, BreadCrumb");
		com.isElementPresent(tab_SignatureTracking, "Signature Tracking, Tab");
		com.isElementPresent(region_SignatureTracking, "Signature Tracking, Region");
		
		CustomReporter.createNode("Verifying the Table columns");
		ApexCommon comm= new ApexCommon();
		comm.verifyColumnHeaders(tab,tableColumns, 1);		
	}
}
