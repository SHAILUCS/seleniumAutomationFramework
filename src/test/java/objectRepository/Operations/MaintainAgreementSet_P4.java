package objectRepository.Operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
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

public class MaintainAgreementSet_P4 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.MaintainAgreementSet_P4.title;

	public MaintainAgreementSet_P4() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		tabfindAgreement_Head=new WebTable(comm.table_ResultTabHeader);
		tabfindAgreement_Body=new WebTable(comm.table_ResultTabData);
	}

	private List<String> colNames = new ArrayList<>(Arrays.asList());

	public void verify_UI() {

	}

	@FindBy(id = "P4_SET_NAME")
	private WebElement text_SetName;
	
	@FindBy(id = "P4_DESCRIPTION")
	private WebElement text_Description;

	@FindBy(id = "P4_SHOW_ONLY_LIVE_AGREEMENTS_0")
	private WebElement radio_OnlyLive_Yes;

	@FindBy(id = "P4_SHOW_ONLY_LIVE_AGREEMENTS_1")
	private WebElement radio_OnlyLive_No;
	
	@FindBy(xpath = "//button[.='Create Set']")
	private WebElement button_CreateSet;
	
	@FindBy(xpath = "//h2[contains(.,'Set Is Created Successfully')]")
	private WebElement successMsg_SetIsCreatedSuccessfully;

	private WebTable tabfindAgreement_Head;
	private WebTable tabfindAgreement_Body;
	
	@FindBy(xpath = "//button[.='Add To The Set']")
	private WebElement button_AddToTheSet;
	
	public void createSet(String setName,String setDesc,boolean onlylive_Yes) {
		String message="";
		
		if (onlylive_Yes){
			com.javaScript_Click(radio_OnlyLive_Yes);
			message=" | Show Only Live Agreements : [Yes]"+message;
		}else{
			com.javaScript_Click(radio_OnlyLive_No);
			message=" | Show Only Live Agreements : [No]"+message;
		}
		
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		
		if (setName!=null){
			com.sendKeys(text_SetName, setName);
			message=" | Name: ["+setName+"]"+message;
		}
		
		if (setDesc!=null){
			com.sendKeys(text_Description, setDesc);
			message=" | Desc: ["+setDesc+"]"+message;
		}
		
		CustomReporter.report(STATUS.INFO, "Creating set with data "+message);
		com.click(button_CreateSet);
		
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		
		com.waitForElementTobe_Visible(successMsg_SetIsCreatedSuccessfully, setName+" Set Is Created Successfully");
	}
	
	public void selectAgreements(List<String> agreementList) {
		
		CustomReporter.createNode("Adding Agreements from excel");
		if (com.isElementPresent(comm.table_ResultTabData)) {
			com.javaScript_Click(radio_OnlyLive_No,"Show live agreements : No");
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			
			String codeAppliedFilterInnerText=null;
			for (String singleAgreementId : agreementList) {
				if (codeAppliedFilterInnerText==null) {
					comm.applyActionsFilter("Agreement id", "=", singleAgreementId);
				}else{
					comm.redefineAppliedFilterValue(codeAppliedFilterInnerText,singleAgreementId);
				}
				
				codeAppliedFilterInnerText=singleAgreementId;
				if(com.waitForElementTobe_NotVisible(comm.data_NoDataFoundIcon,3)){
					com.javaScript_Click(com.waitForElementTobe_Clickable(tabfindAgreement_Body.getChildObject(2, 1, "input", 0)));
					com.click(button_AddToTheSet);
					com.waitForElementTobe_Visible(By.xpath("(//h2[.='Selected Agreements']/../..//following-sibling::div//table)[2]//td[.='"+singleAgreementId+"']"),"Added "+singleAgreementId);
				}else{
					CustomReporter.report(STATUS.FAIL, "Failed to add "+singleAgreementId);
				}
			}
			
		}
		
	}
}
