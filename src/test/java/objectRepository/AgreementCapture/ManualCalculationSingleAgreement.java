package objectRepository.AgreementCapture;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.Constant;
import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.common.ApexCommon;

public class ManualCalculationSingleAgreement {
	private SeleniumMethods com;
	private ApexCommon comm;

	public static String title="Manual Calculation - Single Agreement";
	public ManualCalculationSingleAgreement() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm= new ApexCommon();
	}

	@FindBy(xpath = "//button[contains(.,'Calculate Agreement')]")
	private WebElement button_CalculateAgreement;

	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	private WebElement button_Refresh;
	
	@FindBy(xpath = "//button[contains(.,'Back')]")
	private WebElement button_Back;

	
	public boolean performCalculateAgreementProcess(String agreementName) {
		boolean calcDone=false;
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_CalculateAgreement);
		String message="Agreement has been put in queue for calculation.";
		if (com.waitForElementTobe_Visible(By.xpath("//h2[.='"+message+"']"), Constant.wait)) {
			CustomReporter.report(STATUS.PASS, "'"+message+"' displayed");
			WebTable tab= new WebTable(comm.table_ResultTabData);
			int rowNum=tab.getRowWithCellText(agreementName);
			String status="";
			int counter=1;
			while(true) {
				com.wait(10);
				com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_Refresh);
				status=tab.getCellText(rowNum, 6);
				counter++;
				if(counter==60){
					CustomReporter.report(STATUS.FAIL, "Tried "+counter+" times with 10 seconds wait but calculation is not completed for agreement '"+agreementName+"'");
					break;
				}
				
				if (status!=null && status.contains("Calculation completed")){
					CustomReporter.report(STATUS.PASS, "Calculation completed for agreement '"+agreementName+"' after trying '"+counter+"' times with '10' seconds wait");
					calcDone=true;
					break;
				}
			}
			
		}else{
			CustomReporter.report(STATUS.FAIL, "'"+message+"' is NOT displayed");
		}
		
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_Back);
		
		return calcDone;
	}
	
}
