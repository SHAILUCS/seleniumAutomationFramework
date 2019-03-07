package objectRepository.AgreementCapture.Rework.AgreementDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.Constant;
import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.AgreementCapture.AddIOTAgreementDocument_P414;
import objectRepository.AgreementCapture.IOTAgreementDocuments_P419;
import objectRepository.common.PagesTitle;

public class Administration_Tab_p15_DocLibs_Sec extends Administration_Tab_p15 {

	private static final String CREATE_FILENAME = "Doclib_CREATE.pdf";
	private static final String EDIT_FILENAME1 = "Doclib_EDIT1.pdf";
	private static final String EDIT_FILENAME2 = "Doclib_EDIT2.pdf";
	private static final String CREATE_FILEPATH = Constant.getTestDataFolderPath() + "/" + CREATE_FILENAME;
	private static final String EDIT_FILEPATH1 = Constant.getTestDataFolderPath() + "/" + EDIT_FILENAME1;
	private static final String EDIT_FILEPATH2 = Constant.getTestDataFolderPath() + "/" + EDIT_FILENAME2;

	public static String title = IOTDiscountAgreementDetails_p15_Parent.title;

	public Administration_Tab_p15_DocLibs_Sec() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		tabHead=new WebTable(table_ResultTabHeader_DocLib);
		tabData=new WebTable(table_ResultTabData_DocLib);
	}

	private List<String> colNames = new ArrayList<>(Arrays.asList("", "Filename", "Description", "Uploaded By",
			"Date Uploaded", "Document Type", "Document", "View"));

	public void verify_UI() {
		super.verify_UI();
		documentLibrarySection();
	}

	@FindBy(xpath = "//h2[.='Document Library']")
	private WebElement heading_DocumentLibrary;

	@FindBy(xpath = "//div[contains(.,'Document Library')]/div/button")
	private WebElement hideShow_DocumentLibrary;

	/**Search Toolbar Objects*/
	@FindBy(xpath = "//div[@data-label='Administration']//h2[contains(.,'Document Library')]/../../..//button[contains(@id,'_column_search_root')]")
	public WebElement icon_SelectColumnToSearch;

	@FindBy(xpath = "//div[@data-label='Administration']//h2[contains(.,'Document Library')]/../../..//input[contains(@class,'a-IRR-search-field')]")
	public WebElement text_Search;

	@FindBy(xpath = "//div[@data-label='Administration']//h2[contains(.,'Document Library')]/../../..//button[.='Go']")
	public WebElement button_Go;

	@FindBy(xpath = "//div[@data-label='Administration']//h2[contains(.,'Document Library')]/../../..//div[contains(@class,'rowSelector')]//select")
	public WebElement select_RowsPerPage;

	@FindBy(xpath = "//div[@data-label='Administration']//h2[contains(.,'Document Library')]/../../..//button[.='Actions']")
	public WebElement button_Action;

	@FindBy(xpath = "//button[.='Add Document']")
	private WebElement button_AddDocument;

	private By table_ResultTabHeader_DocLib=By.xpath("//div[@data-label='Administration']//h2[contains(.,'Document Library')]/../../..//div[contains(@class,'head')]/table[@class='a-IRR-table']");
	private By table_ResultTabData_DocLib=By.xpath("//div[@data-label='Administration']//h2[contains(.,'Document Library')]/../../..//div[contains(@class,'body')]/table[@class='a-IRR-table']");

	private WebTable tabData;
	private WebTable tabHead;

	private void documentLibrarySection() {
		CustomReporter.report(STATUS.INFO, "Document Library Section ->>");
		com.isElementPresent(heading_DocumentLibrary, "heading_Document Library");
		verifyHideShowFunc(true, hideShow_DocumentLibrary, "Document Library Section");
		CustomReporter.report(STATUS.INFO, "Apex search toolbar ->>");
		com.isElementPresent(icon_SelectColumnToSearch, "icon_Select Column To Search");
		com.isElementPresent(text_Search, "text_Search");
		com.isElementPresent(button_Go, "button_Go");
		com.isElementPresent(select_RowsPerPage, "select_Rows Per Page");
		comm.verifyActionsPopupItems_WithoutSubscription(button_Action);

		com.isElementPresent(button_AddDocument, "button_Add Document");

		//***************************************************************************
		//Now adding my own document for testing the Edit, View and Download features
		//***************************************************************************
		com.click(button_AddDocument,"button_Add Document");
		com.verifyPageTitle(PagesTitle.AddIOTAgreementDocument_P414.title, true);

		AddIOTAgreementDocument_P414 addDocument_P414=new AddIOTAgreementDocument_P414(); 
		addDocument_P414.addDocument(CREATE_FILEPATH, "Deal Summary");

		if(com.isElementPresent(table_ResultTabHeader_DocLib)){
			comm.verifyColumnHeaders(table_ResultTabHeader_DocLib, colNames, 1);
			//***************************************************************************
			//EDIT link verification
			//***************************************************************************
			int row=tabData.getRowWithCellText(CREATE_FILENAME);
			int editObjCount=tabData.getChildObjectsCount(row, 1, "img");
			if (editObjCount>0) {
				CustomReporter.report(STATUS.PASS, "Edit icon is displayed in Document Library Table");
				com.navigateToAndVerifyPageTitle(tabData.getChildObject(row, 1, "img",0), PagesTitle.IOTAgreementDocuments_P419.title);
				com.navigateBack();
			} else {
				CustomReporter.report(STATUS.FAIL, "Edit icon is NOT displayed in Document Library Table");
			}

			//***************************************************************************
			//DOWNLOAD AND VIEW link verification
			//***************************************************************************
			tabHead.initHeaderColumnList(1);
			downloadLink_Verification(row, CREATE_FILENAME);
			viewLink_Verification(row);
		}

	}

	/***************************************************************************
	 * VIEW link verification
	 ***************************************************************************/
	private void viewLink_Verification(int row) {
		int docNameCol=tabHead.getColumnNumber("Filename");
		CustomReporter.report(STATUS.INFO, " >> VIEW link verification for "+tabData.getCellText(row, docNameCol));
		int viewCol=tabHead.getColumnNumber("View");
		int viewObjCount=tabData.getChildObjectsCount(row, viewCol, "a");
		if (viewObjCount>0) {
			CustomReporter.report(STATUS.PASS, "View link is displayed in Document Library Table");
			com.click(tabData.getChildObject(row, viewCol, "a",0), "View Link");
			String pageURL=com.getCurrentUrl();
			if (pageURL.contains("apex_util.get_blob")) {
				CustomReporter.report(STATUS.PASS, "PDF View page is displayed");
			} else {
				CustomReporter.report(STATUS.FAIL, "PDF View page is NOT displayed");
			}
			com.navigateBack();
		}else{
			CustomReporter.report(STATUS.FAIL, "View link is NOT displayed in Document Library Table");
		}
	}

	/***************************************************************************
	 * DOWNLOAD link verification
	 ***************************************************************************/
	private void downloadLink_Verification(int row,String downloadedFileName) {
		CustomReporter.report(STATUS.INFO, " >> DOWNLOAD link verification for "+downloadedFileName);
		int documentCol=tabHead.getColumnNumber("Document");

		int downloadObjCount=tabData.getChildObjectsCount(row, documentCol, "a");
		if (downloadObjCount>0) {
			CustomReporter.report(STATUS.PASS, "Download link is displayed in Document Library Table");
			com.click(tabData.getChildObject(row, documentCol, "a",0), "Download Link");
			String downloadedFilePath=Util.getDownloadedFilePath(downloadedFileName);
			if (downloadedFilePath!=null) {
				CustomReporter.report(STATUS.PASS, downloadedFileName+" is successfully downloaded");
				Util.deleteSpecificFile(downloadedFilePath);
			} else {
				CustomReporter.report(STATUS.FAIL, downloadedFileName+" failed to downloaded");
			}
		} else {
			CustomReporter.report(STATUS.FAIL, "Download link is NOT displayed in Document Library Table");
		}
	}

	public void CRUD_verification() {
		String description="AutomationTesting"+Util.getTimeStamp_InMilliSec();
		
		CustomReporter.report(STATUS.INFO, "Document Library CRUD ->>");
		openTab(tab_Administration);

		//***************************************************************************
		//Now ADDING my own document for testing the Edit, View and Download features
		//***************************************************************************
		com.click(button_AddDocument,"button_Add Document");
		com.verifyPageTitle(PagesTitle.AddIOTAgreementDocument_P414.title, true);

		AddIOTAgreementDocument_P414 addDocument_P414=new AddIOTAgreementDocument_P414(); 
		addDocument_P414.addDocument(EDIT_FILEPATH1, "Deal Summary");

		int row=tabData.getRowWithCellText(EDIT_FILENAME1);

		if (row>0) {
			tabHead.initHeaderColumnList(1);
			CustomReporter.report(STATUS.PASS, EDIT_FILENAME1+" is successfully added");
			//***************************************************************************
			//DOWNLOAD AND VIEW link verification
			//***************************************************************************
			downloadLink_Verification(row, EDIT_FILENAME1);
			viewLink_Verification(row);

			//***************************************************************************
			//EDIT link verification
			//***************************************************************************
			int editObjCount=tabData.getChildObjectsCount(row, 1, "img");
			if (editObjCount>0) {
				CustomReporter.report(STATUS.PASS, "Edit icon is displayed in Document Library Table");
				com.click(tabData.getChildObject(row, 1, "img",0),"Edit Icon");
				com.verifyPageTitle(PagesTitle.IOTAgreementDocuments_P419.title,true);

				IOTAgreementDocuments_P419 editDocument_P419=new IOTAgreementDocuments_P419();
				editDocument_P419.editDocument(EDIT_FILEPATH2, description, "IOT Discount Agreement");

				//***************************************************************************
				//DOWNLOAD AND VIEW link verification
				//***************************************************************************
				row=tabData.getRowWithCellText(description);
				downloadLink_Verification(row, EDIT_FILENAME2);
				viewLink_Verification(row);
				
				//***************************************************************************
				//DELETE Document verification
				//***************************************************************************
				com.click(tabData.getChildObject(row, 1, "img",0),"Edit Icon");
				com.verifyPageTitle(PagesTitle.IOTAgreementDocuments_P419.title,true);
				editDocument_P419.deleteDocument();
				
				row=tabData.getRowWithCellText(description);
				if (row==-1) {
					CustomReporter.report(STATUS.PASS, "Document deleted succesfully");
				} else {
					CustomReporter.report(STATUS.FAIL, "Document NOT deleted");
				}
			} else {
				CustomReporter.report(STATUS.FAIL, "Edit icon is NOT displayed in Document Library Table");
			}

		} else {
			CustomReporter.report(STATUS.FAIL, CREATE_FILENAME+" is failed to add");
		}
	}

}
