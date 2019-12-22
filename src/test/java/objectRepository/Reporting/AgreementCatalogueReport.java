package objectRepository.Reporting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.ApexCommon;
import objectRepository.common.Header;
import objectRepository.common.PagesTitle;

public class AgreementCatalogueReport extends Header{
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.AgreementCatalogueReport.title;

	public AgreementCatalogueReport() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}
	
	@FindBy(xpath = "//li[contains(.,'Agreement Catalogue Report')][contains(@class,'Breadcrumb')]")
	protected WebElement breadCrumb_AgreementCatalogueReport;
	
	@FindBy(xpath="//h2[contains(.,'Report Parameters')]")
    private WebElement data_ReportParameters_Section;
	
	@FindBy(xpath="//h2[contains(.,'Agreement Catalogue Report')]")
    private WebElement data_AgreementCatalogueReport_Section;

	@FindBy(id = "P651_CLIENT_ME_ID_LEFT")
	private WebElement select_HomeOp_Left;

	@FindBy(id = "P651_CLIENT_ME_ID_RIGHT")
	private WebElement select_HomeOp_Right;

	@FindBy(id = "P651_SHOW_LIVE_AGRMTS_0")
	private WebElement radio_ShowLive_Yes;

	@FindBy(id = "P651_SHOW_LIVE_AGRMTS_1")
	private WebElement radio_ShowLive_No;

	@FindBy(id = "P651_IOT_AGREEMENT_ID")
	private WebElement select_Agreement;
	
	@FindBy(id = "P651_REF_SEARCH_FILTER")
	private WebElement text_AgreementFilter;
	
	@FindBy(id = "P651_TRAFFIC_DIRECTION")
	private WebElement select_TrafficDirection;
	
	@FindBy(id = "P651_IOT_SERVICE_TYPE_ID")
	private WebElement select_ServType;
	
	@FindBy(id = "P651_IOT_EVENT_TYPE_ID")
	private WebElement select_EventType;
	
	@FindBy(xpath="//button[.='Refresh Report']")
    private WebElement button_RefreshReport;
	
	@FindBy(xpath="//button[.='Reset Page']")
	private WebElement button_ResetPage;
	
	private List<String> colNames = new ArrayList<>(Arrays.asList("Traffic Direction", "Agreement Reference",
			"Agreement Start Date Descending", "Agreement End Date", "Client PMN", "Client PMN Country", "Partner PMN",
			"Partner PMN Country", "Discount Method", "Service Type", "Event Type", "Calculation Type",
			"Discount Client PMN(s)", "Discount Partner PMN(s)", "Discount Type", "Discount Tax",
			"Discount Currency", "Balance Type", "Charging Increment", "Lower Bound", "Upper Bound", "Bound Type",
			"Valid From", "Valid To", "Qualifying Direction", "Qualifying Service Type", "Qualifying Event Type",
			"Qualifying Volume", "Qualifying Basis", "Fair Usage Rate", "Fair Usage Threshold", "Domestic Toll Rate",
			"Airtime Rate", "Destn Flag", "Country Name", "Cap Traff Mb", "Cap Value", "Cap Currency",
			"Perc Top Users"));

	public void verify_UI(String[] homeOp, boolean liveAgreement_true, String agreement, String agreementFilter,
			String trafDir, String srvType, String evtType) {
		CustomReporter.createNode("Breadcrumb Section verification");
		com.isElementPresent(breadCrumb_IotronWelcome, "bread Crumb_Iotron Welcome");
		com.isElementPresent(breadCrumb_Reporting, "bread Crumb_Reporting");
		com.isElementPresent(breadCrumb_AgreementCatalogueReport, "bread Crumb_Agreement Catalogue Report");
        
		CustomReporter.createNode("Report Parameters verification");
		com.isElementPresent(data_ReportParameters_Section, "data_ReportParameters_Section");
		com.isElementPresent(select_HomeOp_Left, "select_HomeOp_Left");
		com.isElementPresent(select_HomeOp_Right, "select_HomeOp_Right");
		com.isElementPresent(radio_ShowLive_Yes, "radio_ShowLive_Yes");
		com.isElementPresent(radio_ShowLive_No, "radio_ShowLive_No");
		com.isElementPresent(select_Agreement, "select_Agreement");
		com.isElementPresent(text_AgreementFilter, "text_AgreementFilter");
		com.isElementPresent(select_TrafficDirection, "select_TrafficDirection");
		com.isElementPresent(select_ServType, "select_ServType");
		com.isElementPresent(select_EventType, "select_EventType");
		com.isElementPresent(button_RefreshReport, "button_RefreshReport");
		com.isElementPresent(button_ResetPage, "button_ResetPage");
		
		CustomReporter.createNode("Report Section verification");
		performSearch(homeOp, liveAgreement_true, agreement, agreementFilter, trafDir, srvType, evtType);
        com.isElementPresent(data_AgreementCatalogueReport_Section, "data_Agreement Catalogue Report_Section heading");
        com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
  		com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
  		com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
  		com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
  		comm.verifyActionsPopupItems_WithoutSubscription();
  		
  		if(com.isElementPresent(comm.button_MaximizeReport, "button_Maximize Report")){
  			com.click(comm.button_MaximizeReport,"button_MaximizeReport");
  			com.isElementPresent(comm.button_RestoreReport, "button_Restore Report");
  		}
  		
  		//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
  		comm.verifyColumnHeaders(comm.table_ResultTabHeader,colNames, 1);
	}
	
	/**
	 * This method will fill passed parameters in the report then perform the
	 * search
	 * PreCon: Agreement Catalogue Report Page 651 should be loaded
	 * PostCon: Search Result Report will be displayed, or execution 
	 * 		will be stopped if No data found message is displayed
	 * @author shailendra.rajawat 20-Feb-2019
	 */
	public void performSearch(String[] homeOp, boolean liveAgreement_true, String agreement, String agreementFilter,
			String trafDir, String srvType, String evtType) {
		String message=" | ";
		
		if(homeOp != null){
			com.deselectAllValues_Shuttle_DoubleClick(select_HomeOp_Right);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			com.selectByPartialVisibleText_DoubleClick_FromArray(select_HomeOp_Left, homeOp);
			com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
			message +=  "homeOp: "+Arrays.toString(homeOp)+" | ";
		}
		
		if (liveAgreement_true) {
			com.javaScript_Click(radio_ShowLive_Yes);
			message +=  "showLiveAgreement: Yes | ";
		}else{
			com.javaScript_Click(radio_ShowLive_No);
			message +=  "showLiveAgreement: No | ";
		}
		
		if(agreement != null){
			com.selectByPartialVisibleText(select_Agreement, agreement);
			message +=  "agreement: "+agreement+" | ";
		}
		
		if(agreementFilter != null){
			//Incomplete
			message +=  "agreementFilter: "+agreementFilter+" | ";
		}

		if(trafDir != null){
			com.selectByVisibleText(select_TrafficDirection, trafDir);
			message +=  "trafDir: "+trafDir+" | ";
		}

		if(srvType != null){
			com.selectByVisibleText(select_ServType, srvType);
			message +=  "srvType: "+srvType+" | ";
		}
		
		if(evtType != null){
			com.selectByVisibleText(select_EventType, evtType);
			message +=  "evtType: "+evtType+" | ";
		}
		
		CustomReporter.report(STATUS.INFO, "Performing search with data "+message);
		com.click(button_RefreshReport, "button_RefreshReport");
		
		if(!com.waitForElementTobe_NotVisible(comm.data_NoDataFoundIcon,10)){
			CustomReporter.report(STATUS.FAIL, "No Data Found message is getting displayed");
			Assert.fail("No Data Found message is getting displayed");
		}
		
		if(com.waitForElementsTobe_Present(comm.table_ResultTabHeader)){
			CustomReporter.report(STATUS.PASS, "Result report is getting displayed");
		}
	}
}
