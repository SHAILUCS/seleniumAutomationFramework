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

public class AgreementNotes_Tab extends EditIOTDiscountAgreement_Tab
{
	@FindBy(xpath = "//a[contains(.,'Agreement Notes')]")
	private WebElement tab_AgreementNotes;
	
	@FindBy(xpath = "//h2[contains(.,'Agreement Notes')]")
	private WebElement region_AgreementNotes;
	
	@FindBy(xpath = "//label[@id='P303_IOT_NOTE_REASON_LABEL']")
	private WebElement label_NoteType;
	
	@FindBy(xpath = "//select[@id='P303_IOT_NOTE_REASON']")
	private WebElement dropdown_NoteType;
	
	@FindBy(xpath = "//button[@title='Help Text: Note Type']")
	private WebElement helpbutton_NoteType;
	
	@FindBy(xpath = "//label[@id='P303_NEW_AGREEMENT_NOTE_LABEL']")
	private WebElement label_NewNote;
	
	@FindBy(xpath = "//textarea[@id='P303_NEW_AGREEMENT_NOTE']")
	private WebElement textbox_NewNote;
	
	@FindBy(xpath = "//button[@id='P303_ADD_NOTE']")
	private WebElement button_AddNote;
	
	@FindBy(xpath = "//a[contains(.,'Download Notes')]")
	private WebElement link_DownloadNotes;
	
	@FindBy(xpath = "//a[contains(.,'Export Notes')]")
	private WebElement link_ExportNotes;
	
	private By tab = By.cssSelector("table[summary='Agreement Notes']");
	
	private List<String> tableColumns = new ArrayList<>(Arrays.asList("Note text","Note Type","Create Date","Created By",
			"Update date","Updated by","Edit Note"));
	
	private SeleniumMethods com;
	
	public static String  title="Create / Edit IOT Discount Agreement";
	public AgreementNotes_Tab() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}
	
	public void UIVerification() 
	{
		com.click(tab_AgreementNotes, "Agreement Checklist,Tab");
		CustomReporter.createNode("Verifying page content");
		com.isElementPresent(breadcrumb_IOTRONWelcome, "IOTRON Welcome, BreadCrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, BreadCrumb");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountAgreements, "Maintain IOT Discount Agreements, BreadCrumb");
		com.isElementPresent(breadcrumb_CreateEditIOTDiscountAgreement, "Create Edit IOT Discount Agreement, BreadCrumb");
		com.isElementPresent(tab_AgreementNotes, "Agreement Notes, Tab");
		com.isElementPresent(region_AgreementNotes, "Agreement Notes, Region");
		com.isElementPresent(label_NoteType, "Note Type, Label");
		com.isElementPresent(dropdown_NoteType, "Note Type, Dropdown");
		com.isElementPresent(helpbutton_NoteType, "Note Type, helpbutton");
		com.isElementPresent(label_NewNote, "New Note, Label");
		com.isElementPresent(textbox_NewNote, "Note Type, Textbox");
		com.isElementPresent(link_DownloadNotes, "Download Notes, Link");
		com.isElementPresent(link_ExportNotes, "Export Notes, Link");

		
		
		CustomReporter.createNode("Verifying the Table columns");
		ApexCommon comm= new ApexCommon();
		comm.verifyColumnHeaders(tab,tableColumns, 1);
		
	
	}

}
