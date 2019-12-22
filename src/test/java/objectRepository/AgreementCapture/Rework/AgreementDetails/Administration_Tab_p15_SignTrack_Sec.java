package objectRepository.AgreementCapture.Rework.AgreementDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.AgreementCapture.UpdateSignatureTracker_P180;
import objectRepository.common.NGCHomePage;
import objectRepository.common.PagesTitle;
import tests.MainRegression.AgreementRework;

public class Administration_Tab_p15_SignTrack_Sec extends Administration_Tab_p15{

	public static String title = IOTDiscountAgreementDetails_p15_Parent.title;

	public Administration_Tab_p15_SignTrack_Sec() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		table=new WebTable(table_SignatureTracking_Loc);
	}

	@FindBy(xpath = "//h2[.='Signature Tracking']")
	private WebElement heading_SignatureTracking;

	@FindBy(xpath = "//div[contains(.,'Signature Tracking')]/div/button")
	private WebElement hideShow_SignatureTracking;

	@FindBy(xpath = "//button[.='Track Signatures']")
	private WebElement button_TrackSignatures;


	private By table_SignatureTracking_Loc=By.cssSelector("table[summary='Signature Tracking']");
	private WebTable table;

	private List<String> colNames = new ArrayList<>(Arrays.asList("Home or Partner", "Operator Name", "First Signatory",
			"First Signatory Date", "Second Signatory", "Second Signatory Date", "Signature Status", "Last Updated",
			"Updated By", "Signature Remarks"));

	public void verify_UI() {
		super.verify_UI();
		signatureTracking_Section();
	}

	private void signatureTracking_Section() {
		CustomReporter.report(STATUS.INFO, "Signature Tracking Section ->>");
		com.isElementPresent(heading_SignatureTracking, "heading_Signature Tracking");
		verifyHideShowFunc(true, hideShow_SignatureTracking, "Signature Tracking Section");
		comm.verifyColumnHeaders(table_SignatureTracking_Loc, colNames, 1);
		if(com.isElementPresent(button_TrackSignatures, "button_Track Signatures")){
			CustomReporter.report(STATUS.INFO, "Checking the Track Signatures button func >>");
			com.click(button_TrackSignatures, "button_Track Signatures");
			com.verifyPageTitle(PagesTitle.UpdateSignatureTracker_P180.title, true);

			UpdateSignatureTracker_P180 tracker_P180=new UpdateSignatureTracker_P180();
			com.click(tracker_P180.button_Cancel, "button_Cancel");
			com.verifyPageTitle(title, true);
		}
	}

	public void CRUD_verification(int row, String jsonObjName) {

		CustomReporter.report(STATUS.INFO, "Signature Tracking Section ->>");
		openTab(tab_Administration);

		com.click(button_TrackSignatures, "button_Track Signatures");
		com.verifyPageTitle(PagesTitle.UpdateSignatureTracker_P180.title, true);

		UpdateSignatureTracker_P180 tracker_P180=new UpdateSignatureTracker_P180();
		JSONManager json=new JSONManager(AgreementRework.jsonFilePath,jsonObjName);
		tracker_P180.update_PassedRowData(row,json);
		verify_UpdatedRowData(row,json);
	}

	private void verify_UpdatedRowData(int row, JSONManager json) {
		Map<String, String> tempMap=new HashMap<>();

		table.initHeaderColumnList(1);
		Set<String> keys=json.keySet();
		for (String key : keys) {
			int col_Temp=table.getColumnNumber(key);
			String tempVal=table.getCellText(row, col_Temp);
			tempMap.put(key, tempVal);
		}

		NGCHomePage h=new NGCHomePage();
		String expectedUserName=com.getText(h.data_UserName).replace(".", " ");
		String expectedUpdatedDate=Util.convertToString("dd-MMM-YY", new Date());


		int col_LastUpdated=table.getColumnNumber("Last Updated");
		int col_UpdatedBy=table.getColumnNumber("Updated By");
		if (table.getCellText(row, col_UpdatedBy).equalsIgnoreCase(expectedUserName)) {
			CustomReporter.report(STATUS.PASS, "Updated By: '"+expectedUserName+"' is successfully displayed in Signature Tracking Table");
		} else {
			CustomReporter.report(STATUS.FAIL, "Updated By: '"+expectedUserName+"' is failed displayed in Signature Tracking Table, instead '"+table.getCellText(row, col_UpdatedBy)+"' is getting displayed");
		}

		if (table.getCellText(row, col_LastUpdated).toLowerCase().contains(expectedUpdatedDate.toLowerCase())) {
			CustomReporter.report(STATUS.PASS, "Last Updated Date: '"+expectedUpdatedDate+"' displayed in Signature Tracking Table");
		} else {
			CustomReporter.report(STATUS.FAIL, "Last Updated Date: '"+expectedUpdatedDate+" is failed displayed in Signature Tracking Table, instead '"+table.getCellText(row, col_LastUpdated)+"' is getting displayed");
		}

		if (json.getMapObj().equals(tempMap)) {
			CustomReporter.report(STATUS.PASS, "All data is reflecting properly on Admin >> Signature Tracking Table's "+row+" Row "+json.getMapObj());
		} else {
			CustomReporter.report(STATUS.FAIL, "All data is failed to reflect properly on Admin >> Signature Tracking Table");
			CustomReporter.report(STATUS.INFO, "EXPECTED:	"+json.getMapObj());
			CustomReporter.report(STATUS.INFO, "ACTUAL:	"+tempMap);
		}

	}

	public void navigateTo_UpdateSignatureTracker() {
		CustomReporter.report(STATUS.INFO, "Signature Tracking Section ->>");
		openTab(tab_Administration);
		com.click(button_TrackSignatures, "button_Track Signatures");
		com.verifyPageTitle(UpdateSignatureTracker_P180.title, true);
	}

}
