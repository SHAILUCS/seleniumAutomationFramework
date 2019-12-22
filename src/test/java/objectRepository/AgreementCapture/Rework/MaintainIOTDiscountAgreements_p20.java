package objectRepository.AgreementCapture.Rework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.AgreementCapture.Rework.AgreementDetails.IOTDiscountAgreementDetails_p15_Parent;
import objectRepository.common.ApexCommon;

public class MaintainIOTDiscountAgreements_p20 {

	@FindBy(xpath = "//li[contains(.,'IOTRON - Welcome')]")
	private WebElement breadcrumb_IOTRONWelcome;

	@FindBy(xpath = "//li[contains(.,'Agreement Capture')]")
	private WebElement breadcrumb_AgreementCapture;

	@FindBy(xpath = "//h1[contains(.,'Maintain IOT Discount Agreements')]")
	private WebElement breadcrumb_MaintainIOTDiscountAgreements;

	@FindBy(xpath = "//label[.='Show Agreements']")
	private WebElement label_ShowAgreements;

	@FindBy(xpath = "//input[contains(@id,'SHOW_LIVE_AGRMTS_0')]")
	private WebElement radio_Current;

	@FindBy(xpath = "//input[contains(@id,'SHOW_LIVE_AGRMTS_1')]")
	private WebElement radio_All;

	@FindBy(xpath = "//input[contains(@id,'SHOW_LIVE_AGRMTS_2')]")
	private WebElement radio_Virtual;

	@FindBy(xpath = "//button[@title='Help Text: Show Agreements']")
	private WebElement button_help;

	@FindBy(xpath = "//label[.='Country']")
	private WebElement label_Country;

	@FindBy(xpath = "//select[contains(@id,'COUNTRY')]")
	private WebElement dropdown_Country;

	@FindBy(xpath = "//span[.='60 Days Remain']")
	private WebElement link_60DaysRemain;

	@FindBy(xpath = "//span[.='30 Days Remain']")
	private WebElement link_30DaysRemain;

	@FindBy(xpath = "//button[@class='a-Button a-IRR-button a-IRR-button--controls']")
	private WebElement button_ExpandCollapse_ReportSummary;

	@FindBy(xpath = "//button[contains(.,'Create IOT Discount Agreement')]")
	private WebElement button_CreateIOTDiscountAgreement;

	@FindBy(css = "td[headers='COUNTRY']")
	private List<WebElement> list_CountryCells;

	@FindBy(css = "td[headers='LINK'] a")
	private List<WebElement> list_EditLinks;

	private List<String> tableColumns_PrimaryReport = new ArrayList<>(Arrays.asList("","Agreement Reference","Legal Contract ID","Home Operators",
			"Roaming Partner(s)","Country","Agreement Start Date","Agreement End Date","Agreement Status",
			"Signature Status","Agreement Document Loaded","Date Created","Created By", "Date Updated","Updated By","Checklist Complete",
			"Days Remaining Descending","Negotiator","Rolling Agreement","Next Action","Latest User Note","Latest User Note Date",
			"Partner Negotiator","Services","Do Not Calculate","Continuation of IOT Agreement Planned","EAE Date"));

	private List<String> tableColumns_SecondaryReport = new ArrayList<>(Arrays.asList("", "Agreement Reference",
			"Legal Contract ID", "Home Operators", "Roaming Partner(s)", "Country", "Agreement Start Date",
			"Agreement Status", "Date Created", "Created By", "Date Updated", "Updated By", "Checklist Complete",
			"Days Remaining Descending", "Negotiator", "Rolling Agreement", "Next Action", "Latest User Note",
			"Latest User Note Date", "Partner Negotiator", "Services", "Home Group Agreement", "Agreement Type",
			"Commitment", "Status", "Do Not Calculate", "Continuation of IOT Agreement Planned", "EAE Date"));

	private WebTable tabHead;
	private WebTable tabData;
	private WebTable tabResultTab;
	private int headerRow=1;

	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "Maintain IOT Discount Agreements";

	public MaintainIOTDiscountAgreements_p20() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		tabHead=new WebTable(comm.table_ResultTabHeader);
		tabData=new WebTable(comm.table_ResultTabData);
		tabResultTab=new WebTable(comm.table_ResultTab);
	}

	public void AgrRwrk_NewIOTDiscAgrmntListPage_Content_ReportType_SelectCol_ViewLinkWithBackNav() {
		// (1) New IOT Discount Agreeement List page Content Verification
		CustomReporter.report(STATUS.INFO, "(1) New IOT Discount Agreeement List page Content Verification");

		com.isElementPresent(breadcrumb_IOTRONWelcome, "Iotron Welecome, Breadcrumb");
		com.isElementPresent(breadcrumb_AgreementCapture, "Agreement Capture, Breadcrumb");
		com.isElementPresent(breadcrumb_MaintainIOTDiscountAgreements, "Maintain IOT Discount Agreements, Breadcrumb");
		com.isElementPresent(label_ShowAgreements, "Show Agreements Label");		
		com.isElementPresent(radio_All, "All, Radio");		
		com.isElementPresent(radio_Current, "Current, Radio");
		com.isElementPresent(radio_Virtual, "Virtual, Radio");		
		com.isElementPresent(label_Country, "Country Label");		
		com.isElementPresent(dropdown_Country, "Country, DropDown");		
		com.isElementPresent(link_60DaysRemain, "60DaysRemain, Report summary");		
		com.isElementPresent(link_30DaysRemain, "30DaysRemain, Report summary");
		com.isElementPresent(button_ExpandCollapse_ReportSummary, "button_Expand Collapse_Report Summary");

		CustomReporter.report(STATUS.INFO, "Verifying Apex Search region content");		
		comm.verifyActionsPopupItems_WithSubscription();

		CustomReporter.report(STATUS.INFO, "Verifying the Table columns for Primary Report");
		comm.verifyColumnHeaders(comm.table_ResultTabHeader,tableColumns_PrimaryReport, 1);

		//	(2) Report Type func check
		CustomReporter.report(STATUS.INFO, "(2) Report Type func check");

		com.selectByPartialVisibleText(comm.select_Reports, "Agreements");
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
		By xpath=By.xpath("//li[contains(.,'Agreement Settlement maturing in Previous/Current/Next Month')]");
		com.isElementPresent(xpath,	"Filter : 'Agreement Settlement maturing in Previous/Current/Next Month'");
		String tabCellTxt=tabResultTab.getCellText(1, 1);
		if(tabCellTxt.contains("Agreement End Date")){
			CustomReporter.report(STATUS.PASS, "'"+tabCellTxt+"' row in the report is added, after changing the Report Type");
		}else {
			CustomReporter.report(STATUS.FAIL, "'Agreement End Date' row in the report is NOT added, after changing the Report Type");
		}
		CustomReporter.report(STATUS.INFO, "Verifying the Table columns for 'Agreement Settlement maturing in Previous/Current/Next Month' Report");
		comm.verifyColumnHeaders(comm.table_ResultTab,tableColumns_SecondaryReport, 2);
	
		com.selectByPartialVisibleText(comm.select_Reports, "Primary");
		com.waitForElementTobe_NotVisible(xpath,"Filter : 'Agreement Settlement maturing in Previous/Current/Next Month'");

		// (3) Select columns option check
		CustomReporter.report(STATUS.INFO, "(3) Select columns option check");
		String colNameToAddInReport="Premium Numbers at Discount Rate";
		comm.selectColumns_InReport(colNameToAddInReport);
		int colNum=tabHead.initHeaderColumnList(headerRow).getColumnNumber(colNameToAddInReport);
		if (colNum>0) {
			CustomReporter.report(STATUS.PASS, colNameToAddInReport+" Column added at "+colNum+" Position");
		}else {
			CustomReporter.report(STATUS.FAIL, colNameToAddInReport+" Column failed to add in the report");
		}

		// (4) 'View' link functionality with Back navigation
		CustomReporter.report(STATUS.INFO, "(4) 'View' link functionality with Back navigation");
		if (Util.comparator_NonNumbers("View", tabData.getCellText(2, 1), "Value in first column of page")) {
			CustomReporter.report(STATUS.PASS, "Table displaying View hyperlink in first column of page");
			com.navigateToAndVerifyPageTitle(tabData.getChildObject(2, 1,"a",0),IOTDiscountAgreementDetails_p15_Parent.title);

			CustomReporter.report(STATUS.INFO, "Clicking on Back Button, to verify functionality");
			IOTDiscountAgreementDetails_p15_Parent details=new IOTDiscountAgreementDetails_p15_Parent();
			com.navigateToAndVerifyPageTitle(details.button_Back,title);
		}

	}

	/**
	 * This method can perform all possible search scenarios on "Maintain IOT
	 * Discount Agreements" pageMaintain IOT Discount Agreements
	 * 
	 * 1. It can just change the "country", and do nothing else
	 * 2. It can just change the Agreement status to "Current" or "All" or "Virtual", and do nothing else
	 * 3. It can just select "column Name" and fill search "field" then click Go, and do nothing else
	 * 4. It can provide all search parameters and perform the search
	 * 
	 */
	public void performSearch(String searchVal, String columnToBeSearched, boolean current, boolean all, boolean virtual, String country) {
		String message=" ";
		if (country!= null){
			com.selectByVisibleText(dropdown_Country, country);
			message=message+"|Country- '"+country+"'";
		}else{
			com.selectByIndex(dropdown_Country, 0);
		}

		if (current){
			com.javaScript_Click(radio_Current);
			message=message+"|Current- '"+current+"'";
		}
		
		if (all){
			com.javaScript_Click(radio_All);
			message=message+"|All- '"+all+"'";
		}
		
		if (virtual){
			com.javaScript_Click(radio_Virtual);
			message=message+"|virtual- '"+virtual+"'";
		}

		if (searchVal!=null){
			comm.performSearch(searchVal, columnToBeSearched);
			message=message+"|searchVal- '"+searchVal+"'";
			message=message+"|columnToBeSearched- '"+columnToBeSearched+"'";
		}
		
		CustomReporter.report(STATUS.INFO, "Performing search operation for data :"+message);
		
		if (searchVal!=null){
			if(!com.isElementPresent(By.xpath("//td[contains(normalize-space(.),'"+searchVal+"')]"))){
				CustomReporter.report(STATUS.FAIL, "Record '"+searchVal+"' is NOT displayed in the Search Result Table");
				Assert.fail("Record '"+searchVal+"' is NOT displayed in the Search Result Table");
			}
		}
	}

	public boolean clickOnViewLink(String rowText){
		boolean bool=false;
		CustomReporter.report(STATUS.INFO, "Clicking on View link");
		int rowNum=2;
		if (rowText!=null) {
			rowNum=tabData.getRowWithCellText(rowText);
		}
		
		if (rowNum>=2) {
			com.navigateToAndVerifyPageTitle(tabData.getChildObject(rowNum, 1,"a",0),IOTDiscountAgreementDetails_p15_Parent.title);
			bool=true;
		}else {
			CustomReporter.report(STATUS.FAIL, "Result Table does not have more than 2 rows");
		}
		return bool;
	}


}
