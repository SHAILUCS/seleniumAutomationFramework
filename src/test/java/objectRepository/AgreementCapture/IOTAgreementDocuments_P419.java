package objectRepository.AgreementCapture;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;

public class IOTAgreementDocuments_P419{
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.IOTAgreementDocuments_P419.title;

	public IOTAgreementDocuments_P419() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	public void verify_UI() {
		//TODO
	}
	
	@FindBy(id = "P419_IOT_AGREEMENT_DOC")
	private WebElement input_SelectFile;
	
	@FindBy(linkText = "Download")
	private WebElement link_Download;	
	
	@FindBy(id = "P419_FILENAME_DISPLAY")
	private WebElement data_FileName;
	
	@FindBy(id = "P419_DESCR")
	private WebElement text_Desc;
	
	@FindBy(id = "P419_IOT_DOCUMENT_TYPE_ID")
	private WebElement select_DocumentType;
	
	@FindBy(xpath = "//button[.='Apply Changes']")
	private WebElement button_ApplyChanges;

	@FindBy(xpath = "//button[.='Delete']")
	private WebElement button_Delete;

	@FindBy(xpath = "//button[.='OK']")
	private WebElement button_Ok;
	
	public void editDocument(String absoluteFilePath,String description,String docType){
		CustomReporter.report(STATUS.INFO, "Editing document >>");
		com.sendKeys(input_SelectFile, absoluteFilePath);
		com.sendKeys(text_Desc, description);
		com.selectByVisibleText(select_DocumentType, docType);
		com.click(button_ApplyChanges);
	}

	public void deleteDocument() {
		CustomReporter.report(STATUS.INFO, "Deleting document >>");
		com.click(button_Delete, "button_Delete");
		com.waitForElementTobe_Visible(button_Ok,"Delete Confirmation popup");
		com.click(button_Ok);
	}

}
