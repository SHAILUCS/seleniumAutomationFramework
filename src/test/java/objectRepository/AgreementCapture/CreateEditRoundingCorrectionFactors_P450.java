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

public class CreateEditRoundingCorrectionFactors_P450 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.CreateEditRoundingCorrectionFactors_P450.title;
	private WebTable table;

	public CreateEditRoundingCorrectionFactors_P450() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		table=new WebTable(tab_By);
	}

	private List<String> colNames = new ArrayList<>(Arrays.asList());

	@FindBy(xpath = "//button[.='Cancel']")
	public WebElement button_Cancel;

	public void verify_UI() {

	}

	@FindBy(xpath = "//button[.='Add Row']")
	private WebElement button_AddRow;

	@FindBy(xpath = "//button[.='Delete']")
	private WebElement button_Delete;

	@FindBy(xpath = "//button[.='Submit']")
	private WebElement button_Submit;

	private By tab_By=By.cssSelector("table[summary='Create/Edit Rounding & Correction Factors']");

	private void fillValues(int row,JSONManager json) {
		table.initHeaderColumnList(1);
		com.selectByVisibleText(table.getChildObject(row, table.getColumnNumber("Service Type"), "select", 0), json.getStr("Service Type"));
		com.selectByVisibleText(table.getChildObject(row, table.getColumnNumber("Traffic Direction"), "select", 0), json.getStr("Traffic Direction"));
		com.selectByVisibleText(table.getChildObject(row, table.getColumnNumber("Event Type"), "select", 0), json.getStr("Event Type"));
		com.selectByVisibleText(table.getChildObject(row, table.getColumnNumber("Charging Increment Type"), "select", 0), json.getStr("Charging Increment Type"));
		com.sendKeys(table.getChildObject(row, table.getColumnNumber("First Increment"), "input", 0), json.getStr("First Increment"));
		com.sendKeys(table.getChildObject(row, table.getColumnNumber("Subsequent Increment"), "input", 0), json.getStr("Subsequent Increment"));
		com.sendKeys(table.getChildObject(row, table.getColumnNumber("Correction Factor (%)"), "input", 0), json.getStr("Correction Factor (%)"));

		com.click(button_Submit, "button_Submit");
		com.verifyPageTitle(IOTDiscountAgreementDetails_p15_Parent.title, true);
	}

	public void addEntry(JSONManager json) {
		CustomReporter.report(STATUS.INFO, "Adding a new row with data "+json.getMapObj());
		int oldRow=table.getRowCount();
		com.click(button_AddRow);
		com.wait(1);
		int newRow=table.getRowCount();
		if (newRow>oldRow) {
			fillValues(newRow, json);
		}else{
			CustomReporter.report(STATUS.FAIL, "New Row is not added");
		}
	}

	private int getRowforPassed_CorrectionFactor(String correctionFactorColValue) {
		int row=-1;
		table.initHeaderColumnList(1);
		for (int i = 2; i <= table.getRowCount(); i++) {
			String tempVal=com.getAttribute(table.getChildObject(i, table.getColumnNumber("Correction Factor (%)"), "input", 0),"value");
			if (tempVal.equals(correctionFactorColValue)) {
				row=i;
				break;
			}
		}
		return row;
	}
	
	public void editEntry(String correctionFactorColValue, JSONManager json) {
		CustomReporter.report(STATUS.INFO, "Editing a row with data "+json.getMapObj());
		int row=getRowforPassed_CorrectionFactor(correctionFactorColValue);
		if (row>0) {
			fillValues(row, json);
		}else{
			CustomReporter.report(STATUS.FAIL, "Row with data '"+correctionFactorColValue+"' is not found for Editing");
		}
	}

	public void deleteEntry(String correctionFactorColValue) {
		CustomReporter.report(STATUS.INFO, "Deleting a row with data "+correctionFactorColValue);

		int row=getRowforPassed_CorrectionFactor(correctionFactorColValue);
		if (row>0) {
			com.click(table.getChildObject(row, 1, "input", 0));
			com.click(button_Delete);
			com.click(comm.button_OK, "Delete Confirmation popup");
			com.verifyPageTitle(IOTDiscountAgreementDetails_p15_Parent.title, true);
		}else{
			CustomReporter.report(STATUS.FAIL, "Row with data '"+correctionFactorColValue+"' is not found for Editing");
		}
	}

}
