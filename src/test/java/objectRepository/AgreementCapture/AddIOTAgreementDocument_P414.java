package objectRepository.AgreementCapture;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;

public class AddIOTAgreementDocument_P414{
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.AddIOTAgreementDocument_P414.title;

	public AddIOTAgreementDocument_P414() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	public void verify_UI() {
		//TODO
	}
	
	@FindBy(xpath = "//center[contains(.,'Agreement Reference')]")
	private WebElement data_AgreementRef;
	
	@FindBy(css = "input[name='file']")
	private WebElement input_SelectFile;
	
	@FindBy(id = "P414_IOT_DOCUMENT_TYPE_ID")
	private WebElement select_DocumentType;

	@FindBy(xpath = "//button[.='Create']")
	private WebElement button_Create;
	
	@FindBy(xpath = "//button[.='Cancel']")
	private WebElement button_Cancel;
	
	@FindBy(css = "div[class*='dialog'] button")
	private WebElement button_CloseOnPopup;
	
	
	public void addDocument(String absoluteFilePath,String docType){
		CustomReporter.report(STATUS.INFO, "Uploding a document >>");
		//input_SelectFile.sendKeys(absoluteFilePath);
		com.sendKeys(input_SelectFile, absoluteFilePath);
		com.waitForElementTobe_Visible(button_CloseOnPopup,"Doc Uploaded Confirmation Popup");
		com.click(button_CloseOnPopup);
		com.selectByVisibleText(select_DocumentType, docType);
		com.click(button_Create);
	}
	

}
