package objectRepository.Reporting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.IOTRONSystemAdministration.CommitmentReportSizeOfRiskBounds_P602;
import objectRepository.IOTRONSystemAdministration.CreateEditIOTClient_P413;
import objectRepository.IOTRONSystemAdministration.MaintainIOTClients_P310;
import objectRepository.IOTRONSystemAdministration.MaintainOperatorsHavingMoreThanOnePMNcode_P479;
import objectRepository.common.ApexCommon;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.LoginPage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;
import tests.MainRegression.HKT_CommitmentReport_7393;

public class CommitmentReport_P492 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.CommitmentReport_P492.title;

	public CommitmentReport_P492() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Commitment Report')]")
	private WebElement data_CommitmentReport_BreadCrumb;

	@FindBy(xpath = "//h2[contains(.,'Report parameters')]")
	private WebElement data_ReportParameters_Section;

	@FindBy(xpath = "//div[@class='t-Region-header'][contains(.,'Report parameters')]//button[contains(@class,'hideShow')]")
	private WebElement hideshowbutton_ReportParameters;

	@FindBy(id = "P492_HOME_OPERATOR_LEFT")
	private WebElement select_HomeOperators;

	@FindBy(id = "P492_HOME_OPERATOR_RIGHT")
	private WebElement select_HomeOperators_Right;

	@FindBy(id = "P492_TRAFFIC_PERIOD")
	private WebElement select_TrafficPeriod;

	@FindBy(id = "P492_CURRENCY_0")
	private WebElement radio_HomeCurrency;

	@FindBy(id = "P492_CURRENCY_1")
	private WebElement radio_DiscountCurrency;

	@FindBy(xpath = "//button[contains(.,'Refresh')]")
	private WebElement button_Refresh;

	private List<String> colNames = new ArrayList<>(Arrays.asList("Performance YTD", "Performance EoA", "Size Of Risk",
			"Traffic Direction", "Agreement Reference", "Home Operator", "Partner PMN", "Country", "Deal Type",
			"Service and Event Type", "Committed Volume", "Committed Financial Value", "Tax", "Committed Rate",
			"Currency", "Agreement Term", "Months To Go", "Months To Go (%)", "Unit Fulfilment To Date",
			"Unit Fulfilment To Date (%)", "Unit Commitment Remaining", "Unit Commitment Remaining (%)",
			"Financial Fulfilment To Date", "Financial Fulfilment To Date (%)", "Financial Commitment Remaining",
			"Financial Commitment Remaining (%)", "Unit Fulfilment EoA", "Unit Fulfilment EoA (%)",
			"Financial Projection EoA", "Financial Projection EoA (%)", "Unit Projected Shortfall",
			"Projected Financial Shortfall", "IOT Rate as per Agreement", "Forecasted Average IOT rate EoA",
			"Negotiator", "Perpetual Flag", "Agreement Status"));

	/**
	 * @author shailendra.rajawat Precondition: P492 should be loaded
	 *         Postcondition: Search will be performed and content will be
	 *         verified
	 */
	public void verify_UI(String[] homeOperators, String trafPeriod, String homeOrDiscountCurrency) {

		com.isElementPresent(data_CommitmentReport_BreadCrumb, "Commitment Report heading in Bread Crumb");

		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_ReportParameters_Section, "Report Parameters Section");

		CustomReporter.report(STATUS.INFO, "Fields verification");

		if (!com.waitForElementTobe_NotVisible(select_HomeOperators, 0)) {
			com.isElementPresent(select_HomeOperators, "Home Operators shuttle");
		}

		com.isElementPresent(select_TrafficPeriod, "Traffic Period dropdown");
		com.isElementPresent(radio_HomeCurrency, "Home Currency radio button");
		com.isElementPresent(radio_DiscountCurrency, "Discount Currency radio button");

		com.isElementPresent(button_Refresh, "Refresh button");

		if (performSearch(homeOperators, trafPeriod, homeOrDiscountCurrency)) {
			CustomReporter.report(STATUS.INFO, "Search Results section verification");

			com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Search Column button");
			com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
			com.isElementPresent(comm.button_Go, "Apex Toolbar - Go Button");
			com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Rows dropdown");
			comm.verifyActionsPopupItems_WithSubscription();

		}
	}

	/**
	 * @author shailendra.rajawat Precondition: P492 should be loaded
	 *         Postcondition: Search will be performed
	 */
	public boolean performSearch(JSONManager json) {
		return performSearch(json.getStrArr("homeOperator"), json.getStr("trafPeriod"),
				json.getStr("homeOrDiscountCurrency"));
	}

	/**
	 * @author shailendra.rajawat Precondition: P492 should be loaded
	 *         Postcondition: Search will be performed
	 */
	public boolean performSearch(String[] homeOperator, String trafPeriod, String homeOrDiscountCurrency) {
		String message = "";

		if (homeOperator != null) {
			com.deselectAllValues_Shuttle_DoubleClick(select_HomeOperators_Right);
			com.selectByPartialVisibleText_DoubleClick_FromArray(select_HomeOperators, homeOperator);
			message = message + " | homeOperator= '" + Arrays.toString(homeOperator) + "'";
		}

		if (trafPeriod != null) {
			com.selectByVisibleText(select_TrafficPeriod, trafPeriod);
			message = message + " | trafPeriod= '" + trafPeriod + "'";
		}

		if (homeOrDiscountCurrency != null) {
			if (homeOrDiscountCurrency.toLowerCase().contains("home")) {
				com.click(radio_HomeCurrency);
			} else {
				com.click(radio_DiscountCurrency);
			}
			message = message + " | homeOrDiscountCurrency= '" + homeOrDiscountCurrency + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

		com.click(button_Refresh);
		if (com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex search toolbar")) {
			return true;
		}

		return false;
	}

	/**
	 * on Commitment report we are checking search is affecting the result
	 * 
	 * @author shailendra.rajawat Precondition: P492 should be loaded
	 *         Postcondition: Search will be performed, and it is checked that
	 *         searched Home Operator is getting displayed in report
	 */
	public void T01_HKTCommitRep_7395_AddingUpAnAdditionalReportParameter_HomeOperator(String methodName) {
		CustomReporter.createNode("Checking Home Operator shuttle is visible");
		com.isElementPresent(select_HomeOperators, "Home Operators shuttle");
		JSONManager json = new JSONManager(HKT_CommitmentReport_7393.jsonFilePath, methodName);
		verifySearchResultContainsPassedHomeOp(json);
	}

	/**
	 * @author shailendra.rajawat Precondition: P492 should be loaded, and Home
	 *         Operator shuttle should be displayed Postcondition: Search will
	 *         be performed, and it is checked that searched Home Operator is
	 *         getting displayed in report
	 */
	private void verifySearchResultContainsPassedHomeOp(JSONManager json) {
		CustomReporter.createNode("Checking Home Operator shuttle affects on search results");
		performSearch(json);
		String homeOp = json.getStrArr("homeOperator")[0];
		WebTable tab = new WebTable(comm.table_ResultTabData);
		int rowWithCellText = tab.getRowWithCellText(homeOp);
		if (rowWithCellText >= 1) {
			CustomReporter.report(STATUS.PASS,
					"Searched Home Operator " + homeOp + " is getting displayed in search result");
		} else {
			CustomReporter.report(STATUS.FAIL,
					"Searched Home Operator " + homeOp + " failed to display in search result");
		}
	}

	/**
	 * For the Group Client, we will first go the [Iotron System Administration
	 * >> Maintain Operators having more than one PMN code] Page and get all the
	 * PMN's which comprises this group. Then on Commitment report we can check
	 * shuttle has these PMN's Also search is affecting the result
	 * 
	 * @author shailendra.rajawat Precondition: NGC Home page should be
	 *         displayed Postcondition: Search will be performed, and it is
	 *         checked that searched Home Operator is getting displayed in
	 *         report
	 */
	public void T02_HKTCommitRep_7395_AddingUpAnAdditionalReportParameter_HomeOperator_HKGTC(String methodName) {
		Navigator nav = new Navigator();
		IOTRONHomePage h = new IOTRONHomePage();

		CustomReporter.createNode(
				"Navigating to 'Maintain Operators Having More Than One PMN code' P479 page to get the PMNs list");

		JSONManager json = new JSONManager(HKT_CommitmentReport_7393.jsonFilePath, methodName);
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(PagesTitle.MaintainOperatorsHavingMoreThanOnePMNcode_P479,
				h.link_IOTRONSystemAdministration,
				h.link_IOTRONSystemAdministration_MaintainOperatorsHavingMoreThanOnePMNCode);
		MaintainOperatorsHavingMoreThanOnePMNcode_P479 p479 = new MaintainOperatorsHavingMoreThanOnePMNcode_P479();

		List<String> pmnsArr_Expected = p479.getGroupPMNsCodesForPassedOperator(json.getStrArr("homeOperator")[0]);

		String userType = json.getStr("userType");
		new LoginPage().logoutThenPerformLogin(userType);

		CustomReporter.createNode("Navigating to 'Commitment Report' P492 page to verify PMNs list in Home Operator");

		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(PagesTitle.CommitmentReport_P492, h.link_Reporting,
				h.link_Reporting_CommitmentReport);
		List<String> pmnArr_Actual = com.getAllOptionsVisibleText(select_HomeOperators);

		boolean bool = true;
		List<String> actualList=new ArrayList<String>();
		for (int i = 0; i < pmnArr_Actual.size(); i++) {
			String actual=pmnArr_Actual.get(i);
			if(actual.contains("-")){
				actual=actual.substring(0, actual.indexOf("-")-1).trim();
			}else{
				actual=actual.substring(0, 5).trim();
			}
			actualList.add(actual);
		}

		
		for (int i = 0; i < pmnsArr_Expected.size(); i++) {
			if(!pmnsArr_Expected.contains(actualList.get(i))){
				bool=false;
				break;
			}
		}
		

		if (bool) {
			CustomReporter.report(STATUS.PASS, "PMN list displayed in Home Operator Shuttle Actual <b>" + pmnArr_Actual
					+ "</b> Expected <b>" + pmnsArr_Expected +"</b>");
		} else {
			CustomReporter.report(STATUS.FAIL, "PMN list displayed in Home Operator Shuttle Actual <b>" + pmnArr_Actual
					+ "</b> Expected <b>" + pmnsArr_Expected +"</b>");
		}

		verifySearchResultContainsPassedHomeOp(json);

	}
	
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>(Arrays.asList("HKGM3 - Hong Kong Telecommunications (HKGM3). Limited",
				"HKGMC - Hong Kong Telecommunications. (HKGMC) Limited",
				"HKGNW - Hong Kong Telecommunications (HKGNW) Limited.",
				"HKGSI - Hong Kong Telecommunications (HKGSI) Limited.",
				"HKGTC - Hong Kong Telecommunications (HKGTC) Limited"));
		
		List<String> ll = new ArrayList<String>(Arrays.asList("HKGM3","HKGMC","HKGNW","HKGSI","HKGTC"));

		boolean bool = true;
		List<String> actualList=new ArrayList<String>();
		for (int i = 0; i < ll.size(); i++) {
			String actual=l.get(i);
			if(actual.contains("-")){
				actual=actual.substring(0, actual.indexOf("-")-1).trim();
			}else{
				actual=actual.substring(0, 5).trim();
			}
			actualList.add(actual);
		}

		
		for (int i = 0; i < ll.size(); i++) {
			if(!actualList.contains(ll.get(i))){
				bool=false;
				break;
			}
		}
		
		if (bool) {
			CustomReporter.report(STATUS.PASS, "PMN list displayed in Home Operator Shuttle Actual <b>" + l
					+ "</b> Expected <b>" + ll +"<b>");
		} else {
			CustomReporter.report(STATUS.FAIL, "PMN list displayed in Home Operator Shuttle Actual <b>" + l
					+ "</b> Expected <b>" + ll +"<b>");
		}
		
	}

	/**
	 * As Per Req 7296: Results should not display for a PMN who is not
	 * contributing to the Agreement Commitment
	 * 
	 * Search results should have two columns 'Home Operator' and 'Partner PMN'
	 * Also there are some cases in which Selected PMN does not display in
	 * report, if they do not contribute towards the Commitment
	 * 
	 * @author shailendra.rajawat Precondition: p492 should be loaded
	 *         Postcondition: Search will be performed, and it is checked that
	 *         searched Home Operator is getting displayed in report
	 */
	public void T03_HKTCommitRep_7396_TwoNewColumnsHomeOperatorAndPartnerPMNtoBeAdded(String agreementName,
			String methodName) {
		CustomReporter.createNode(
				"CASE 1 - Selecting all PMNs(contributing as well as non-contributing) and verifying the agreement should get displayed");

		// CASE 1
		JSONManager json = new JSONManager(HKT_CommitmentReport_7393.jsonFilePath, methodName, "commitmentRep-CASE1");

		// Performing search with all Home Operator PMNs, and 01/2018 traffic
		// period
		// Verifying that the created agreement can be filtered
		// Also after applying filter, two rows should get displayed as we
		// already created the
		// agreement with OB & IB Commitments
		performSearch(json);

		WebTable tabHead = new WebTable(comm.table_ResultTabHeader);
		WebTable tabData = new WebTable(comm.table_ResultTabData);

		int colNumAgrRef = tabHead.initHeaderColumnList(1).getColumnNumber("Agreement Reference");
		if (comm.checkColumnFilterCanBeApplied(1, colNumAgrRef, agreementName)) {
			CustomReporter.report(STATUS.PASS, "Agreement " + agreementName + " is available for filtering");
			comm.applyColumnFilter(1, colNumAgrRef, agreementName);
			// Checking two rows for OB and IB
			int rowWithCellTxt = tabData.getRowWithCellText("Visitor/Inbound");
			if (rowWithCellTxt > 0) {
				CustomReporter.report(STATUS.PASS, "Row for Visitor/Inbound is successfully displayed");
			} else {
				CustomReporter.report(STATUS.FAIL, "Row for Visitor/Inbound is Not displayed");
			}
			rowWithCellTxt = tabData.getRowWithCellText("Customer/Outbound");
			if (rowWithCellTxt > 0) {
				CustomReporter.report(STATUS.PASS, "Row for Customer/Outbound is successfully displayed");
			} else {
				CustomReporter.report(STATUS.FAIL, "Row for Customer/Outbound is Not displayed");
			}
		} else {
			CustomReporter.report(STATUS.FAIL, "Agreement " + agreementName + " is Not available for filtering");
		}

		// CASE 2
		CustomReporter.createNode(
				"CASE 2 - Selecting only the non-contributing PMN and verifying the agreement should NOT get displayed");

		// Performing search with only one non-contributing PMN, and 01/2018
		// traffic period
		// Verifying that the created agreement can NOT be filtered
		json = new JSONManager(HKT_CommitmentReport_7393.jsonFilePath, methodName, "commitmentRep-CASE2");

		performSearch(json);

		if (com.isElementPresent(comm.data_NoDataFoundIcon)) {
			CustomReporter.report(STATUS.PASS, "Agreement " + agreementName
					+ " is Not available for filtering, because 'no data' icon is getting displayed");
		} else {
			CustomReporter.report(STATUS.FAIL, "Agreement " + agreementName
					+ " is Not available for filtering, even though a non-contributing PMN is searched");
		}
	}

	/**
	 * @author shailendra.rajawat 9-Jan-19
	 * <pre>
	 * It will <b>VERIFY</b> Small, Medium, Large and Critical Risk Bounds, 
	 * one by one on Commitment report.
	 * 
	 * This method will perform following steps
	 * 	1. go to {@link CommitmentReport_P492} 
	 * 	2. perform search with pmn and traffic period
	 * 	3. apply agreement filter
	 * 	4. note down the value of "Projected Financial Shortfall" Column first row, lets say it is "num"
	 *  5. Now this is tricky
	 *  	FOR CRITCAL
	 *  	1. go to P602, update the noted down value in 
	 *  			large Lower Bound as (unchanged), Upper Bound (num-2)
	 *  			Critical Lower Bound as (num-1), Upper Bound (blank)
	 *  	2. come back to P492, Refresh and verify first row "Size of Risk" column should say "Critical"
	 *  
	 *  	FOR LARGE
	 *  	1. go to P602, update the noted down value in
	 *  			Medium Lower Bound as (unchanged), Upper Bound (num-2) 
	 *  			Large Lower Bound as (num-1), Upper Bound (num+1)
	 *  			Critical Lower Bound as (num+2), Upper Bound (blank)
	 *  	2. come back to P492, Refresh and verify first row "Size of Risk" column should say "Large"
	 *  
	 *  	FOR MEDIUM
	 *  	1. go to P602, update the noted down value in 
	 *  			Small Lower Bound as (unchanged), Upper Bound (num-2)
	 *  			Medium Lower Bound as (num-1), Upper Bound (num+1)
	 *  			Large Lower Bound as (num+2), Upper Bound (num+3)
	 *  			Critical Lower Bound as (num+4), Upper Bound (blank)
	 *  	2. come back to P492, Refresh and verify first row "Size of Risk" column should say "Medium"
	 *  
	 *  	FOR SMALL
	 *  	1. go to P602, update the noted down value in 
	 *  			Small Lower Bound as (0), Upper Bound (num+1)
	 *  			Medium Lower Bound as (num+2), Upper Bound (num+3)
	 *  			Large Lower Bound as (num+4), Upper Bound (num+5)
	 *  			Critical Lower Bound as (num+6), Upper Bound (blank)
	 *  	2. come back to P492, Refresh and verify first row "Size of Risk" column should say "Small"
	 *  
	 * 
	 *  Precon: Agreement must be created, and calculated
	 *  Postcon: P602 values will be modified and changes will be verified on P492
	 *  @param agreementName filter will be applied on commitment report, with this agreement
	 *  @param methodName This is the method name for creating json objects
	 * </pre>
	 * */
	public void T05_verifyRiskBounds_Modifications(String agreementName,String methodName) {
		
		//Navigating to Commitment Report for getting the "Projected Financial Shortfall" Value
		CustomReporter.createNode("Navigating to Commitment Report p492 for getting the 'Projected Financial Shortfall' Value");
		Navigator nav = new Navigator();
		IOTRONHomePage h = new IOTRONHomePage();
		
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle(PagesTitle.CommitmentReport_P492, h.link_Reporting,
				h.link_Reporting_CommitmentReport);
		JSONManager json=new JSONManager(HKT_CommitmentReport_7393.jsonFilePath, methodName,"commitmentRep"); 
		
		performSearch(json);
		
		WebTable tabHead=new WebTable(comm.table_ResultTabHeader);
		WebTable tabData=new WebTable(comm.table_ResultTabData);

		int colProjectedFinancialShortfall = tabHead.initHeaderColumnList(1).getColumnNumber("Projected Financial Shortfall");
		
		comm.applyColumnFilter(1, tabHead.getColumnNumber("Agreement Reference"), agreementName);

		//formatting the value before Changing the Risk Bounds for the PMN
		String val_ProjectedFinancialShortfall=tabData.getCellText(2, colProjectedFinancialShortfall);
		val_ProjectedFinancialShortfall=Util.removeCommas(val_ProjectedFinancialShortfall);
		val_ProjectedFinancialShortfall=val_ProjectedFinancialShortfall.replace("-", "");
		
		String[] riskType={"Small","Medium","Large","Critical"};
		/*
		 *  FOR CRITCAL
		 *  1. go to P602, update the noted down value in 
		 *  		Small Lower Bound as (0), Upper Bound (99)
		 *  		Medium Lower Bound as (100), Upper Bound (999)
		 *  		large Lower Bound as (1000), Upper Bound (num-2)
	 	 *  		Critical Lower Bound as (num-1), Upper Bound (blank)
		 * */
		String[] lowerC={"0","100","1000",Util.BD(val_ProjectedFinancialShortfall).subtract(Util.BD("1")).toPlainString()};
		String[] upperC={"99","999",Util.BD(val_ProjectedFinancialShortfall).subtract(Util.BD("2")).toPlainString(),null};
		T05_navigateToP602_ModifyRiskBounds(json.getStrArr("homeOperator")[0], riskType, lowerC, upperC);
		T05_navigateToP492_VerifyTheRiskBounds(agreementName, methodName,"Critical");
		
		/*
		 *  FOR LARGE
		 *  1. go to P602, update the noted down value in
		 *  		Small Lower Bound as (0), Upper Bound (99)
		 *  		Medium Lower Bound as (100), Upper Bound (num-2) 
		 *  		Large Lower Bound as (num-1), Upper Bound (num+1)
		 *  		Critical Lower Bound as (num+2), Upper Bound (blank)
		 * */		
		String[] lowerL={"0",
				"100",
				Util.BD(val_ProjectedFinancialShortfall).subtract(Util.BD("1")).toPlainString(),
				Util.BD(val_ProjectedFinancialShortfall).add(Util.BD("2")).toPlainString()};
		String[] upperL={"99",
				Util.BD(val_ProjectedFinancialShortfall).subtract(Util.BD("2")).toPlainString(),
				Util.BD(val_ProjectedFinancialShortfall).add(Util.BD("1")).toPlainString(),
				null};
		T05_navigateToP602_ModifyRiskBounds(json.getStrArr("homeOperator")[0], riskType, lowerL, upperL);
		T05_navigateToP492_VerifyTheRiskBounds(agreementName, methodName,"Large");
		
		
		/*
		 * 	FOR MEDIUM
		 *  	1. go to P602, update the noted down value in 
		 *  			Small Lower Bound as (0), Upper Bound (num-2)
		 *  			Medium Lower Bound as (num-1), Upper Bound (num+1)
		 *  			Large Lower Bound as (num+2), Upper Bound (num+3)
		 *  			Critical Lower Bound as (num+4), Upper Bound (blank)
		 * */
		String[] lowerM={"0",
				Util.BD(val_ProjectedFinancialShortfall).subtract(Util.BD("1")).toPlainString(),
				Util.BD(val_ProjectedFinancialShortfall).add(Util.BD("2")).toPlainString(),
				Util.BD(val_ProjectedFinancialShortfall).add(Util.BD("4")).toPlainString()};
		String[] upperM={Util.BD(val_ProjectedFinancialShortfall).subtract(Util.BD("2")).toPlainString(),
				Util.BD(val_ProjectedFinancialShortfall).add(Util.BD("1")).toPlainString(),
				Util.BD(val_ProjectedFinancialShortfall).add(Util.BD("3")).toPlainString(),
				null};
		T05_navigateToP602_ModifyRiskBounds(json.getStrArr("homeOperator")[0], riskType, lowerM, upperM);
		T05_navigateToP492_VerifyTheRiskBounds(agreementName, methodName,"Medium");
		
		
		/*
		 * FOR SMALL
		 *  	1. go to P602, update the noted down value in 
		 *  			Small Lower Bound as (0), Upper Bound (num+1)
		 *  			Medium Lower Bound as (num+2), Upper Bound (num+3)
		 *  			Large Lower Bound as (num+4), Upper Bound (num+5)
		 *  			Critical Lower Bound as (num+6), Upper Bound (blank)
		 * */
		String[] lowerS={"0",
				Util.BD(val_ProjectedFinancialShortfall).add(Util.BD("2")).toPlainString(),
				Util.BD(val_ProjectedFinancialShortfall).add(Util.BD("4")).toPlainString(),
				Util.BD(val_ProjectedFinancialShortfall).add(Util.BD("6")).toPlainString()};
		String[] upperS={Util.BD(val_ProjectedFinancialShortfall).add(Util.BD("1")).toPlainString(),
				Util.BD(val_ProjectedFinancialShortfall).add(Util.BD("3")).toPlainString(),
				Util.BD(val_ProjectedFinancialShortfall).add(Util.BD("5")).toPlainString(),
				null};
		T05_navigateToP602_ModifyRiskBounds(json.getStrArr("homeOperator")[0], riskType, lowerS, upperS);
		T05_navigateToP492_VerifyTheRiskBounds(agreementName, methodName,"Small");
	}

	private void T05_navigateToP492_VerifyTheRiskBounds(String agreementName, String methodName,
			String expectedRiskBound) {
		
		CustomReporter.createNode("Navigating to Commitment Report p492 for verifying the ["+expectedRiskBound+"] Risk Bound Value");
		
		// Navigating to Commitment Report
		Navigator nav = new Navigator();
		IOTRONHomePage h = new IOTRONHomePage();

		nav.traverseMenu_VerifyPageTitle(PagesTitle.CommitmentReport_P492, h.link_Reporting,
				h.link_Reporting_CommitmentReport);
		JSONManager json = new JSONManager(HKT_CommitmentReport_7393.jsonFilePath, methodName, "commitmentRep");

		performSearch(json);

		WebTable tabHead = new WebTable(comm.table_ResultTabHeader);
		WebTable tabData = new WebTable(comm.table_ResultTabData);

		int colSizeOfRisk = tabHead.initHeaderColumnList(1).getColumnNumber("Size Of Risk");

		comm.applyColumnFilter(1, tabHead.getColumnNumber("Agreement Reference"), agreementName);

		// formatting the value before Changing the Risk Bounds for the PMN
		String actualRiskBound = tabData.getCellText(2, colSizeOfRisk);
		Util.comparator_NonNumbers(expectedRiskBound, actualRiskBound, "Risk Bounds");
	}

	private void T05_navigateToP602_ModifyRiskBounds(String pmn, String[] riskType, String[] lower, String[] upper) {
		
		CustomReporter.createNode("Navigating to CommitmentReportSizeOfRiskBounds_P602 for modifying the 'Risk Bounds' <br/>"
				+ "Risk "+ Arrays.toString(riskType)+"<br/>" 
				+ "Lower "+ Arrays.toString(lower)+"<br/>"
				+ "Upper "+ Arrays.toString(upper)+"<br/>");
		
		// Navigating to MaintainIOTClients_P310
		Navigator nav = new Navigator();
		IOTRONHomePage h = new IOTRONHomePage();

		nav.traverseMenu_VerifyPageTitle(PagesTitle.MaintainIOTClients_P310,
				h.link_IOTRONSystemAdministration, h.link_IOTRONSystemAdministration_MaintainIOTClients);

		MaintainIOTClients_P310 p310 = new MaintainIOTClients_P310();

		// Searching for Client PMN on MaintainIOTClients_P310, then Navigating
		// to CreateEditIOTClient_P413
		CreateEditIOTClient_P413 p413 = p310.searchAndClickEdit(pmn);

		// Clicking on SetCommitmentReportSizeOfRiskBounds Button on
		// CreateEditIOTClient_P413, then Navigating on
		// CommitmentReportSizeOfRiskBounds_P602
		CommitmentReportSizeOfRiskBounds_P602 p602 = p413.clickOnSetCommitmentReportSizeOfRiskBoundsButton();

		// Modifying Bounds on CommitmentReportSizeOfRiskBounds_P602 page then
		// Navigating back to CreateEditIOTClient_P413
		p602.modifyBounds(riskType, lower, upper);

	}
}
