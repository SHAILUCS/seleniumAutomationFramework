package objectRepository.AgreementCapture.Rework.AgreementDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.AgreementCapture.EditAgreementNote_P461;
import objectRepository.common.ApexCommon;
import objectRepository.common.NGCHomePage;
import objectRepository.common.PagesTitle;
import tests.MainRegression.AgreementRework;

public class Administration_Tab_p15_AgreementNotes_Sec extends Administration_Tab_p15{
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = IOTDiscountAgreementDetails_p15_Parent.title;
	private WebTable tab;

	public Administration_Tab_p15_AgreementNotes_Sec() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		tab=new WebTable(table_AgreementNotes);
	}

	@FindBy(xpath = "//h2[.='Agreement Notes']")
	private WebElement heading_AgreementNotes;

	@FindBy(xpath = "//div[contains(.,'Agreement Notes')]/div/button")
	private WebElement hideShow_AgreementNotes;

	private By table_AgreementNotes=By.cssSelector("table[summary='Agreement Notes']");

	private List<String> colNames = new ArrayList<>(Arrays.asList("Note Text", "Note Type", "Create Date", "Created By",
			"Update Date", "Updated By", "Edit Note"));

	@FindBy(id="P15_IOT_NOTE_REASON")
	private WebElement select_NoteType;

	@FindBy(id="P15_NEW_AGREEMENT_NOTE")
	private WebElement text_NewNote;

	@FindBy(xpath = "//button[.='Add Note to this Agreement']")
	private WebElement button_AddNoteToThisAgreement;

	@FindBy(partialLinkText = "Download Notes")
	private WebElement link_DownloadNotes;

	@FindBy(partialLinkText = "Export Notes")
	private WebElement link_ExportNotes;

	public void verify_UI() {
		super.verify_UI();
		agreementNotes_Section();
	}

	private void agreementNotes_Section() {
		CustomReporter.report(STATUS.INFO, "Agreement Notes Section ->>");
		com.isElementPresent(heading_AgreementNotes, "heading_Agreement Notes");
		verifyHideShowFunc(true, hideShow_AgreementNotes, "Agreement Notes Section");

		com.isElementPresent(select_NoteType, "select_Note Type");
		com.isElementPresent(text_NewNote, "text_New Note");
		com.isElementPresent(button_AddNoteToThisAgreement, "button_Add Note To This Agreement");

		int noteRow=addNewNote("General Note", "AuotmationTestingNote");
		//comm.printColumnHeaders(table_AgreementNotes, 1);
		comm.verifyColumnHeaders(table_AgreementNotes, colNames, 1);

		int editNoteCol=tab.initHeaderColumnList(1).getColumnNumber("Edit Note");

		int editObjCount=tab.getChildObjectsCount(noteRow, editNoteCol, "img");
		if (editObjCount>0) {
			com.click(tab.getChildObject(noteRow, editNoteCol, "img", 0), "Edit Icon");
			com.verifyPageTitle(PagesTitle.EditAgreementNote_P461.title, true);

			EditAgreementNote_P461 editNote_P461=new EditAgreementNote_P461();
			com.click(editNote_P461.button_Cancel, "Cancel Button");
			com.verifyPageTitle(title, true);
		}

		if(com.isElementPresent(link_DownloadNotes, "link_Download Notes")){
			com.click(link_DownloadNotes, "link_Download Notes");
			String notesFile=Util.getDownloadedFilePath("agreement_notes.csv");
			if (notesFile!=null) {
				CustomReporter.report(STATUS.PASS, notesFile+" notes file successfully downloaded");
				Util.forceDelete(notesFile);
			} else {
				CustomReporter.report(STATUS.FAIL, "Notes file download failed");
			}
		}
		if(com.isElementPresent(link_ExportNotes, "link_Export Notes")){
			com.click(link_ExportNotes, "link_Export Notes");
			String notesFile=Util.getDownloadedFilePath("Agreement_Notes.pdf");
			if (notesFile!=null) {
				CustomReporter.report(STATUS.PASS, notesFile+" notes file successfully downloaded");
				Util.forceDelete(notesFile);
			} else {
				CustomReporter.report(STATUS.FAIL, "Notes file Export failed");
			}
		}
	}

	public int addNewNote(String noteType, String note) {
		CustomReporter.report(STATUS.INFO, "Adding a new Note : ["+note+"] of type : ["+noteType+"]");
		com.selectByVisibleText(select_NoteType, noteType);
		com.sendKeys(text_NewNote, note);
		com.click(button_AddNoteToThisAgreement);


		int newNoteRow=tab.getRowWithCellText(note);
		if (newNoteRow>0) {
			CustomReporter.report(STATUS.PASS, "Note ["+note+"] added");
		} else {
			CustomReporter.report(STATUS.FAIL, "Note ["+note+"] failed to add");
		}

		return newNoteRow;
	}

	public void CRUD_verification(String jsonObjName) {
		JSONManager json=new JSONManager(AgreementRework.jsonFilePath, jsonObjName,"Add");
		
		CustomReporter.report(STATUS.INFO, "Agreement Notes Section ->>");
		openTab(tab_Administration);

		tab.initHeaderColumnList(1);
		
		//ADDING NEW NOTE
		int row=addNewNote(json.getStr("Note Type"),json.getStr("Note Text"));
		verify_AddedNote(json);
		
		//EDITING NOTE
		json=new JSONManager(AgreementRework.jsonFilePath, jsonObjName,"Edit");
		editNote(row,json);
		verify_EditedNote(json);
		
		//DELETING NOTE
		deleteNote(json.getStr("Note Text"));
		verify_DeletedNote(json.getStr("Note Text"));
	}
	
	private void verify_DeletedNote(String noteText) {
		int row=tab.getRowWithCellText(noteText);
		if (row==-1) {
			CustomReporter.report(STATUS.PASS, noteText+" successfully deleted");
		} else {
			CustomReporter.report(STATUS.FAIL, noteText+" failed to delete");
		}
	}

	private void deleteNote(String noteText) {
		CustomReporter.report(STATUS.INFO, "Deleting Note");
		int row=tab.getRowWithCellText(noteText);
		int editCol=tab.getColumnNumber("Edit Note");
		com.click(tab.getChildObject(row, editCol, "img", 0),"Edit Note");
		com.verifyPageTitle(PagesTitle.EditAgreementNote_P461.title, true);
		
		EditAgreementNote_P461 p461=new EditAgreementNote_P461();
		p461.perform_Delete();
	}

	private void editNote(int row, JSONManager json) {
		CustomReporter.report(STATUS.INFO, "Editing Note : "+json.getMapObj());
		int editCol=tab.getColumnNumber("Edit Note");
		com.click(tab.getChildObject(row, editCol, "img", 0),"Edit Note");
		com.verifyPageTitle(PagesTitle.EditAgreementNote_P461.title, true);
		
		EditAgreementNote_P461 p461=new EditAgreementNote_P461();
		p461.perform_Edit(json.getStr("Note Text"),json.getStr("Note Type"));
	}
	
	private void verify_EditedNote(JSONManager json) {
		int row=tab.getRowWithCellText(json.getStr("Note Text"));
		int col_NoteType=tab.getColumnNumber("Note Type");
		if (tab.getCellText(row, col_NoteType).equalsIgnoreCase(json.getStr("Note Type"))) {
			CustomReporter.report(STATUS.PASS, json.getStr("Note Type")+" is successfully displayed in Agreement Notes Table");
		} else {
			CustomReporter.report(STATUS.FAIL, json.getStr("Note Type")+" is failed displayed in Agreement Notes Table, instead "+tab.getCellText(row, col_NoteType)+" is getting displayed");
		}

		NGCHomePage h=new NGCHomePage();
		String expectedUserName=com.getText(h.data_UserName).replace(".", " ");
		String expectedUpdatedDate=Util.convertToString("dd-MMM-YY", new Date());

		int col_CreatedDate=tab.getColumnNumber("Update Date");
		int col_CreatedBy=tab.getColumnNumber("Updated By");
		if (tab.getCellText(row, col_CreatedBy).equalsIgnoreCase(expectedUserName)) {
			CustomReporter.report(STATUS.PASS, expectedUserName+" in Updated By col is successfully displayed in Agreement Notes Table");
		} else {
			CustomReporter.report(STATUS.FAIL, expectedUserName+" in Updated By col is failed displayed in Agreement Notes Table, instead "+tab.getCellText(row, col_CreatedBy)+" is getting displayed");
		}

		if (tab.getCellText(row, col_CreatedDate).toLowerCase().contains(expectedUpdatedDate.toLowerCase())) {
			CustomReporter.report(STATUS.PASS, expectedUpdatedDate+" in Updated Date col is successfully displayed in Agreement Notes Table");
		} else {
			CustomReporter.report(STATUS.FAIL, expectedUpdatedDate+" is failed displayed in Updated Date col of Agreement Notes Table, instead "+tab.getCellText(row, col_CreatedDate)+" is getting displayed");
		}

	}

	private void verify_AddedNote(JSONManager json) {
		int row=tab.getRowWithCellText(json.getStr("Note Text"));
		int col_NoteType=tab.getColumnNumber("Note Type");
		if (tab.getCellText(row, col_NoteType).equalsIgnoreCase(json.getStr("Note Type"))) {
			CustomReporter.report(STATUS.PASS, json.getStr("Note Type")+" is successfully displayed in Agreement Notes Table");
		} else {
			CustomReporter.report(STATUS.FAIL, json.getStr("Note Type")+" is failed displayed in Agreement Notes Table, instead "+tab.getCellText(row, col_NoteType)+" is getting displayed");
		}

		NGCHomePage h=new NGCHomePage();
		String expectedUserName=com.getText(h.data_UserName).replace(".", " ");
		String expectedUpdatedDate=Util.convertToString("dd-MMM-YY", new Date());

		int col_CreatedDate=tab.getColumnNumber("Create Date");
		int col_CreatedBy=tab.getColumnNumber("Created By");
		if (tab.getCellText(row, col_CreatedBy).equalsIgnoreCase(expectedUserName)) {
			CustomReporter.report(STATUS.PASS, expectedUserName+" in Created By col is successfully displayed in Agreement Notes Table");
		} else {
			CustomReporter.report(STATUS.FAIL, expectedUserName+" in Created By col is failed displayed in Agreement Notes Table, instead "+tab.getCellText(row, col_CreatedBy)+" is getting displayed");
		}

		if (tab.getCellText(row, col_CreatedDate).toLowerCase().contains(expectedUpdatedDate.toLowerCase())) {
			CustomReporter.report(STATUS.PASS, expectedUpdatedDate+" in Created Date col is successfully displayed in Agreement Notes Table");
		} else {
			CustomReporter.report(STATUS.FAIL, expectedUpdatedDate+" is failed displayed in Created Date col of Agreement Notes Table, instead "+tab.getCellText(row, col_CreatedDate)+" is getting displayed");
		}

	}
}
