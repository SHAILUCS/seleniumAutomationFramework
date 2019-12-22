package objectRepository.DealTracker.BudgetModule;

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
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.common.ApexCommon;

public class FutureFXRate {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Future FX rate";

	public FutureFXRate() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Future FX rate')]")
	private WebElement data_FutureFXrate_BreadCrumb;
	
	@FindBy(xpath = "//h2[contains(.,'Future Conversion Rates')]")
	private WebElement data_FutureConversionRates_Section;

	@FindBy(xpath = "//button[contains(.,'Submit')]")
	private WebElement button_Submit;

	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	private WebElement button_Cancel;

	private By tab= By.cssSelector("table[summary='Future Conversion Rates']");
	
	private List<String> colNames = new ArrayList<>(Arrays.asList("Check All","From Currency","Pegged Rate","To Currency","Created by","Creation Date","Effective From Date","Update Date","Updated by"));

	@FindBy(xpath = "//span[contains(.,'1 - ')]")
	private WebElement data_Pagination;

	@FindBy(xpath = "//button[contains(.,'Add Row')]")
	private WebElement button_AddRow;

	@FindBy(xpath = "//button[contains(.,'Delete')]")
	private WebElement button_Delete;
	
	public void verify_UI() {
		com.isElementPresent(data_FutureFXrate_BreadCrumb, "Future FX rate heading in Bread Crumb");
		CustomReporter.report(STATUS.INFO, "Future Conversion Rates Section verification");
		com.isElementPresent(data_FutureConversionRates_Section, "Future Conversion Rates Section");
		com.isElementPresent(button_Submit, "Submit button");
		com.isElementPresent(button_Cancel, "Cancel button");
		
		//comm.printColumnHeaders(tab, 1);
		comm.verifyColumnHeaders(tab, colNames, 1);
		if (com.isElementPresent(tab)) {
			CustomReporter.report(STATUS.INFO, "Clicking Add Row button and checking parameters");
			com.click(button_AddRow);
			WebTable t=new WebTable(tab);
			int lastRow=t.getRowCount();
			if(lastRow>=2){
				com.isElementPresent(t.getChildObject(lastRow, 1, "input", 0), "Select Row Checkbox");
				com.isElementPresent(t.getChildObject(lastRow, 2, "span", 0), "From currency : "+com.getText(t.getChildObject(lastRow, 2, "span", 0)));
				com.isElementPresent(t.getChildObject(lastRow, 3, "input", 0), "Pegged Rate field");
				com.isElementPresent(t.getChildObject(lastRow, 4, "select", 0), "To Currency dropdown");
				com.isElementPresent(t.getChildObject(lastRow, 7, "input", 0), "Effective From Date field");
			}
		}
		
		//com.isElementPresent(data_Pagination, "Pagination data " + com.getText(data_Pagination));
		
		com.isElementPresent(button_AddRow, "Add Row button");
		com.isElementPresent(button_Delete, "Delete button");
	}

	

}
