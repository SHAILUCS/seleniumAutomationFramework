package objectRepository.AgreementCapture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.AgreementCapture.Rework.AgreementDetails.IOTDiscountAgreementDetails_p15_Parent;
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;

public class UpdateSignatureTracker_P180 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.UpdateSignatureTracker_P180.title;

	public UpdateSignatureTracker_P180() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		tab_SignTrack=new WebTable(tab_Loc);
	}

	private List<String> colNames = new ArrayList<>(Arrays.asList());

	@FindBy(xpath = "//button[.='Cancel']")
	public WebElement button_Cancel;

	@FindBy(xpath = "//button[.='Save Changes']")
	private WebElement button_SaveChanges;

	private By tab_Loc=By.cssSelector("table[summary='Update Signature Tracker']");
	private WebTable tab_SignTrack;

	public void verify_UI() {

	}

	public void update_PassedRowData(int row, JSONManager json) {

		CustomReporter.report(STATUS.INFO, "Updating the "+row+" Row Data "+json.getMapObj());

		int totalRow=tab_SignTrack.getRowCount();
		if (totalRow>=row) {
			tab_SignTrack.initHeaderColumnList(1);

			if(json.containsKey("First Signatory")){
				int col_FirstSignatory=tab_SignTrack.getColumnNumber("First Signatory");
				com.sendKeys(tab_SignTrack.getChildObject(row, col_FirstSignatory, "input", 0), json.getStr("First Signatory"));
			}

			if(json.containsKey("First Signatory Date")){
				int col_FirstSignatoryDate=tab_SignTrack.getColumnNumber("First Signatory Date");
				com.sendKeys(tab_SignTrack.getChildObject(row, col_FirstSignatoryDate, "input", 0), json.getStr("First Signatory Date"));
			}

			if(json.containsKey("Second Signatory")){
				int col_SecondSignatory=tab_SignTrack.getColumnNumber("Second Signatory");
				com.sendKeys(tab_SignTrack.getChildObject(row, col_SecondSignatory, "input", 0), json.getStr("Second Signatory"));
			}

			if(json.containsKey("Second Signatory Date")){
				int col_SecondSignatoryDate=tab_SignTrack.getColumnNumber("Second Signatory Date");
				com.sendKeys(tab_SignTrack.getChildObject(row, col_SecondSignatoryDate, "input", 0), json.getStr("Second Signatory Date"));
			}

			if(json.containsKey("Signature Status")){
				int col_SignatureStatus=tab_SignTrack.getColumnNumber("Signature Status");
				com.selectByVisibleText(tab_SignTrack.getChildObject(row, col_SignatureStatus, "select", 0), json.getStr("Signature Status"));
			}

			if(json.containsKey("Signature Remarks")){
				int col_Remarks=tab_SignTrack.getColumnNumber("Remarks");
				com.sendKeys(tab_SignTrack.getChildObject(row, col_Remarks, "input", 0), json.getStr("Signature Remarks"));
			}
			
			com.click(button_SaveChanges, "button_Save Changes");
			com.verifyPageTitle(IOTDiscountAgreementDetails_p15_Parent.title, true);
		}else {
			CustomReporter.report(STATUS.FAIL, "Signature Tracking table does not have enough rows to update the data");
		}

	}

}
