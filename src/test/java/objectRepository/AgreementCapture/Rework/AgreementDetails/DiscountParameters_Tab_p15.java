package objectRepository.AgreementCapture.Rework.AgreementDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.WebTable;
import objectRepository.Forecasting.Forecasting_ForecastedTrafficVolumesSummaryReport;

public class DiscountParameters_Tab_p15 extends IOTDiscountAgreementDetails_p15_Parent{

	public static String title = IOTDiscountAgreementDetails_p15_Parent.title;

	private WebTable tabHead;
	private WebTable tabData;

	public DiscountParameters_Tab_p15() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		tabHead=new WebTable(comm.table_ResultTabHeader);
		tabData=new WebTable(comm.table_ResultTabData);
	}
	
	@FindBy(xpath = "//h2[.='Discount Parameters']")
	private WebElement heading_DiscountParameters;
	
	private List<String> colNames = new ArrayList<>(
			Arrays.asList("Discount Settlement Method", "Home PMN(s)", "Partner PMN(s)", "Discount Direction",
					"Service Type", "Event Type", "Charging Increment used in Calculation", "Selected Destinations",
					"Calculation Type", "Discount Basis", "Discount Basis Value", "Toll Rate", "Airtime Rate",
					"Discount Currency", "Discount Tax Inclusive (Gross) / Exclusive (Net)", "Balancing", "Bound Type",
					"Lower Bound", "Upper Bound", "Qualifying Service Type", "Qualifying Event Type",
					"Qualifying Discount Direction", "Qualifying Basis", "Qualifying Volume or Percentage",
					"Valid From", "Valid To", "Fair Usage Threshold", "Fair Usage Rate", "IMSI Cap Traffic in MB",
					"IMSI Cap Value", "IMSI Cap Currency", "% of Top Users in Month IMSI Cap relevant", "Reporting"));

	public void verify_UI() {
		CustomReporter.report(STATUS.INFO, "Discount Parameters Tab ->>");
		openTab(tab_DiscountParameters);
		
		com.isElementPresent(heading_DiscountParameters, "heading_Discount Parameters");
		
		CustomReporter.report(STATUS.INFO, "Apex search toolbar ->>");
		com.isElementPresent(comm.icon_SelectColumnToSearch, "icon_Select Column To Search");
		com.isElementPresent(comm.text_Search, "text_Search");
		com.isElementPresent(comm.button_Go, "button_Go");
		comm.verifyActionsPopupItems_WithRowsPerPage();
		
		//comm.printColumnHeaders(comm.table_ResultTabHeader, 1);
		comm.verifyColumnHeaders(comm.table_ResultTabHeader, colNames, 1);
		int reportingCol=tabHead.initHeaderColumnList(1).getColumnNumber("Reporting");
		String firstRowText=tabData.getCellText(2, reportingCol);
		if (firstRowText.equalsIgnoreCase("Report")) {
			CustomReporter.report(STATUS.PASS, "7.On discount parameters subpage under column Reporting, Report link is displayed");
			com.navigateToAndVerifyPageTitle(tabData.getChildObject(2, reportingCol, "a", 0), Forecasting_ForecastedTrafficVolumesSummaryReport.title);
			CustomReporter.report(STATUS.WARNING, "8. On Forecast Reprt per Agreement ( p317) click on Maintain Agreement button");
		} else {
			CustomReporter.report(STATUS.FAIL, "7.On discount parameters subpage under column Reporting, Report link is NOT displayed");
		}
				
	}

	

}
