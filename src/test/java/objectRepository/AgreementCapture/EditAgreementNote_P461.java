package objectRepository.AgreementCapture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.AgreementCapture.Rework.AgreementDetails.IOTDiscountAgreementDetails_p15_Parent;
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;

public class EditAgreementNote_P461 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.EditAgreementNote_P461.title;

	public EditAgreementNote_P461() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	private List<String> colNames = new ArrayList<>(Arrays.asList());

	@FindBy(xpath = "//button[.='Cancel']")
	public WebElement button_Cancel;
	
	@FindBy(id = "P461_NOTE_TEXT")
	private WebElement text_NoteText;

	@FindBy(id = "P461_NOTE_REASON_ID")
	private WebElement select_NoteType;

	@FindBy(xpath = "//button[.='Apply Changes']")
	private WebElement button_ApplyChanges;

	@FindBy(xpath = "//button[.='Delete']")
	private WebElement button_Delete;
	
	public void verify_UI() {

	}

	public void perform_Edit(String noteText, String noteType) {
		com.sendKeys(text_NoteText, noteText);
		com.selectByVisibleText(select_NoteType, noteType);
		com.click(button_ApplyChanges, "button_Apply Changes");
		com.verifyPageTitle(IOTDiscountAgreementDetails_p15_Parent.title, true);
	}

	public void perform_Delete() {
		com.click(button_Delete, "Button_Delete");
		com.click(comm.button_OK, "Ok On Confirmation Popup");
		com.verifyPageTitle(IOTDiscountAgreementDetails_p15_Parent.title, true);
	}

}