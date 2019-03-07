package objectRepository.Operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;

public class CalculationStatus_P5 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.CalculationStatus_P5.title;

	private WebTable tabHead;
	private WebTable tabBody;
	public CalculationStatus_P5() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		tabHead=new WebTable(comm.table_ResultTabHeader);
		tabBody=new WebTable(comm.table_ResultTabData);
	}

	private List<String> colNames = new ArrayList<>(Arrays.asList());

	public void verify_UI() {

	}
	
	@FindBy(id = "P5_SELECT_SET")
	private WebElement select_Set;
	
	@FindBy(id = "P5_ORIGINAL_LATEST_0")
	private WebElement radio_Latest;
	
	@FindBy(id = "P5_ORIGINAL_LATEST_1")
	private WebElement radio_Original;
	
	@FindBy(xpath = "//button[.='Calculate Agreement Set']")
	private WebElement button_CalculateAgreementSet;
	
	@FindBy(xpath = "//button[.='Refresh Report']")
	private WebElement button_RefreshReport;
	
	public static void main(String[] args) {
		String status="Queue (502)";
		
		String v=status.replaceAll("[^0-9]", "");
		
		System.out.println(v);
		
		/*int agreementCount=Integer.parseInt(status.replaceAll("[^0-9]", ""));
		System.out.println(agreementCount);*/
	}
		
	public boolean performCalculation(String setName,boolean true_ForOriginal) {
		
		String message=" | Set : ["+setName+"]";
		com.selectByVisibleText(select_Set, setName);
		if (true_ForOriginal) {
			com.javaScript_Click(radio_Original);
			message = message+" | Original: [Yes]";
		}else {
			com.javaScript_Click(radio_Latest);
			message = message+" | Latest: [Yes]";
		}
		
		CustomReporter.report(STATUS.INFO,"Performing calculations for "+message);
		com.click(button_CalculateAgreementSet);
		
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator, 3600);
		
		boolean stopFlag=true;
		if (com.waitForElementTobe_Visible(comm.table_ResultTabData)) {
			int statusCol=tabHead.initHeaderColumnList(1).getColumnNumber("Status");
			
			String status=tabBody.getCellText(2, statusCol);
			int agreementCount=Integer.parseInt(status.replaceAll("[^0-9]", ""));
			
			do {
				com.wait(30);
				com.click(button_RefreshReport);
				com.waitForElementTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
				
				status=tabBody.getCellText(2, statusCol);
				
				if (!status.toLowerCase().contains("calculating") && !status.toLowerCase().contains("queue")) {
					if (status.toLowerCase().contains("calculation completed")) {
						int tempCount=Integer.parseInt(status.replaceAll("[^0-9]", ""));
						if (tempCount==agreementCount) {
							CustomReporter.report(STATUS.PASS, "Calculation Completed for set [" + setName + "] with ref Id: "
									+ tabBody.getCellText(2, tabHead.getColumnNumber("Reference ID")));
							stopFlag=false;
						}
					}
				}
				
				if (status.toLowerCase().contains("calculation failed")) {
					CustomReporter.report(STATUS.FAIL, "Calculation Failed for set [" + setName + "] with ref Id: "
							+ tabBody.getCellText(2, tabHead.getColumnNumber("Reference ID")));
					stopFlag=false;
				}
				
			} while (stopFlag);
			
			
		}
		
		return !stopFlag;
	}
}
