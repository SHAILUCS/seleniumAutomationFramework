package objectRepository.Reporting;

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
import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;
import objectRepository.common.TADIG;
public class CustomerOutboundRoamingMVNOSplitReport
{
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.CustomerOutboundRoamingMVNOSplitReport .title;
	public CustomerOutboundRoamingMVNOSplitReport () {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath="//li[.='Reporting'][contains(@class,'Breadcrumb')]")
	private WebElement breadcrumb_Reporting;

	@FindBy(xpath="//h1[contains(.,'Customer/Outbound Roaming MVNO Split Report')]")
	private WebElement data_CustomerOutboundRoamingMVNOSplitReport_BreadCrumb;

	@FindBy(xpath="//h2[contains(.,'Report Parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(xpath="//div[@class='t-Region-header'][contains(.,'Report Parameters')]//button[contains(@class,'hideShow')]")
	private WebElement hideShowIcon_ReportParameters;

	@FindBy(id="P552_CLIENT_MASTER_ENTITY_ID_LEFT")
	private WebElement shuttle_HomeOperator_Left;

	@FindBy(id="P552_CLIENT_MASTER_ENTITY_ID_RIGHT")
	private WebElement shuttle_HomeOperator_Right;

	@FindBy(id="P552_DATE_FROM")
	private WebElement text_DateFrom;

	@FindBy(id="P552_DATE_TO")
	private WebElement text_DateTo;

	@FindBy(id="P552_MVNO_LEFT")
	private WebElement shuttle_MVNO_Left;

	@FindBy(id="P552_MVNO_RIGHT")
	private WebElement shuttle_MVNO_Right;

	@FindBy(xpath="//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;

	@FindBy(id="P552_VIEW_CHARTS")
	private WebElement button_ViewCharts;

	@FindBy(xpath="//h2[contains(.,'MVNO Split Report')]")
	private WebElement data_MVNOSplitReport_Section;

	@FindBy(id="P552_DOWNLOAD_CSV")
	private WebElement button_DownloadCSV;

	private List<String> colNames = new ArrayList<>(
			Arrays.asList("Traffic Period", "Client Operator", "HPMN", "VPMN", "VPMN Name", "VPMN Country", "MVNO",
					"Call Type", "Actual Traffic Volume", "Billed Traffic Volume", "TAP Charge Net [SDR]",
					"TAP Charge Gross [SDR]", "TAP Charge Net [Local Currency]", "TAP Charge Gross [Local Currency]",
					"Local Currency", "SDR to Local Pegged Rate", "TAP Charge Net [Home Currency]",
					"TAP Charge Gross [Home Currency]", "Home Currency", "SDR to Home Pegged Rate", "No Of IMSI",
					"Processed Date", "Calling country ISO Code", "Called country ISO Code", "IMSI End Range",
					"Discounted Charge Net MVNO", "Discounted Charge Gross MVNO", "Stored Rate"));

	public void verify_UI(TADIG[] TADIG,String dateFrom, String dateTo, String[] mvno){
		CustomReporter.report(STATUS.INFO, "Bread Crumb data verification");
		com.isElementPresent(breadcrumb_Reporting,"breadcrumb_Reporting");
		com.isElementPresent(data_CustomerOutboundRoamingMVNOSplitReport_BreadCrumb,"data_CustomerOutboundRoamingMVNOSplitReport_BreadCrumb");

		CustomReporter.report(STATUS.INFO, "Report Parameters Section verification");
		com.isElementPresent(data_ReportParameters_Section,"Report Parameters_Section heading");
		com.isElementPresent(hideShowIcon_ReportParameters,"Report Parameters hide Show Icon");
		com.isElementPresent(shuttle_HomeOperator_Left,"Home Operator_Left shuttle");
		com.isElementPresent(shuttle_HomeOperator_Right,"Home Operator_Right shuttle");
		com.isElementPresent(text_DateFrom,"Date From textfield");
		com.isElementPresent(text_DateTo,"Date To textfield");
		com.isElementPresent(shuttle_MVNO_Left,"MVNO_Left shuttle");
		com.isElementPresent(shuttle_MVNO_Right,"MVNO_Right shuttle");
		com.isElementPresent(button_RefreshReport,"Refresh Report button");
		com.isElementPresent(button_ViewCharts,"View Charts button");
		if (performSearch(TADIG, dateFrom, dateTo, mvno)) {
			CustomReporter.report(STATUS.INFO, "Verifying the search result section");
			com.isElementPresent(data_MVNOSplitReport_Section,"MVNO Split Report_Section heading");
			com.isElementPresent(button_DownloadCSV,"Download CSV button");

			com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
			com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
			com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
			com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
			comm.verifyActionsPopupItems_WithoutSubscription();

			//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
			comm.verifyColumnHeaders(comm.table_ResultTabHeader,colNames, 1);
		}
	}

	private boolean performSearch(TADIG[] TADIG,String dateFrom, String dateTo, String[] mvno) {
		String message = "";

		if (TADIG != null) {
			comm.selectMultipleValues_DoubleClick_FromArray(shuttle_HomeOperator_Left,TADIG);
			message = message + " | HomeOperator= '" + Arrays.toString(TADIG) + "'";
		}

		if (dateFrom != null) {
			com.sendKeys(text_DateFrom, dateFrom);
			message = message + " | dateFrom= '" + dateFrom + "'";
		}

		if (dateTo != null) {
			com.sendKeys(text_DateTo, dateTo);
			message = message + " | dateTo= '" + dateTo + "'";
		}

		if (mvno != null) {
			com.selectByPartialVisibleText_DoubleClick_FromArray(shuttle_MVNO_Left , mvno);
			message = message + " | mvno= '" + mvno + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_RefreshReport);
		if (com.isElementPresent(comm.table_ResultTabData, "Search Results")) {
			return true;
		}
		return false;
	}
}