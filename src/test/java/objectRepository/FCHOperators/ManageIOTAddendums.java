package objectRepository.FCHOperators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.common.ApexCommon;
import tests.MainRegression.Module_DiscountAgreement;

public class ManageIOTAddendums {
	private SeleniumMethods com;
	private ApexCommon comm;
	private JSONManager json;
	private WebTable tabHead;
	private WebTable tabData;
	private int headerRow=1;

	public static String title="Manage IOT Addendums";
	public ManageIOTAddendums() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm= new ApexCommon();
		tabHead = new WebTable(comm.table_ResultTabHeader);
		tabData = new WebTable(comm.table_ResultTabData);
	}

	@FindBy(id = "P579_SELECT_IOT_CLIENT")
	private WebElement select_Client;

	@FindBy(id = "P579_SELECT_IOT_PARTNER")
	private WebElement select_RoamingPartner;

	@FindBy(xpath = "//button[.='Show Addenda']")
	private WebElement button_ShowAddenda;

	public boolean performSearch(String client, String partner){
		CustomReporter.report(STATUS.INFO, "Showing the Addenda for client '"+client+"' and Roaming Partner '"+partner+"'");
		com.selectByPartialVisibleText(select_Client, client);
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		com.selectByPartialVisibleText(select_RoamingPartner, partner);
		com.click(button_ShowAddenda);
		if (com.waitForElementTobe_Visible(comm.table_ResultTabHeader,"Addenda")) {
			return true;
		}
		return false;
	}


	public String get_ClientReceivable_And_PartnerPayableCurrency(String... jsonObjName) {
		json= new JSONManager(Module_DiscountAgreement.jsonFilePath, jsonObjName);
		if(performSearch(json.getStr("clientName"), json.getStrArr("partner")[0])){
			if(com.navigateToAndVerifyPageTitle(tabData.getChildObject(headerRow+1, 1, "a", 0), EditAndUpdateIOTApproveAddendum.title)){
				EditAndUpdateIOTApproveAddendum editAndUpdateIOTApproveAddendum= new EditAndUpdateIOTApproveAddendum();
				return editAndUpdateIOTApproveAddendum.get_ClientReceivable_And_PartnerPayableCurrency();
			}

		}
		return null;
	}

}
