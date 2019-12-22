package objectRepository.CustomisedReportsTMO;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
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

import objectRepository.Forecasting.FinancialReport_ForecastedTAPDiscountedCharges;
import objectRepository.common.ApexCommon;
import objectRepository.common.Navigator;
import objectRepository.common.TADIG;
import tests.MainRegression.Module_TMO;

public class TapAccrualActualAndTrueUp {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = "TAP Accrual, Actual & True Up";
	private WebTable tab;
	private WebTable tabHead;
	private int headerRow=1;
	
	public TapAccrualActualAndTrueUp() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm=new ApexCommon();
		tab = new WebTable(comm.table_ResultTabData);
		tabHead=new WebTable(comm.table_ResultTabHeader);
	}

	@FindBy(xpath = "//h1[contains(.,'TAP Accrual, Actual & True Up')]")
	private WebElement data_TAPAccrualActualTrueUp_BreadCrumb;
	
	@FindBy(xpath = "//h2[contains(.,'Filters')]")
	private WebElement data_Filters_Section;

	@FindBy(xpath = "//h2[contains(.,'TAP Accrual, Actual & True Up')]")
	private WebElement data_TAPAccrualActualTrueUp_SearchResultSection;

	@FindBy(id = "P2_MASTER_ENTITY_ID")
	private WebElement select_Operator;

	@FindBy(id = "P2_TRAFFIC_PERIOD")
	private WebElement select_TrafficPeriod;

	@FindBy(id = "P2_SNAP_DATE")
	private WebElement select_ProcessingDate;

	@FindBy(id = "P2_DISCOUNT_TYPE")
	private WebElement select_DiscountType;

	@FindBy(xpath = "//button[contains(.,'Refresh Report')]")
	private WebElement button_RefreshReport;

	@FindBy(xpath = "//button[contains(.,'Back')]")
	private WebElement button_Back;

	private List<String> columnNames = new ArrayList<>(Arrays.asList("Partner PLMN Ascending", "Discount Type",
			"Agreement Ref", "DOM/INT", "IB Accrual Voice MOU Real", "IB Accrual Voice MOU Charged",
			"IB Accrual SMS MO", "IB Accrual SMS MT", "IB Accrual DATA MB Real", "IB Accrual DATA MB Charged",
			"IB Accrual Voice Rev", "IB Accrual SMS MO Rev", "IB Accrual SMS MT Rev", "IB Accrual DATA Rev",
			"IB Accrual Total Rev", "IB Actual Voice MOU Real", "IB Actual Voice MOU Charged", "IB Actual SMS MO",
			"IB Actual SMS MT", "IB Actual DATA MB Real", "IB Actual DATA MB Charged", "IB Actual Voice Rev",
			"IB Actual SMS MO Rev", "IB Actual SMS MT Rev", "IB Actual DATA Rev", "IB Actual Total Rev",
			"IB True-Up Voice MOU Real", "IB True-Up Voice MOU Charged", "IB True-Up SMS MO", "IB True-Up SMS MT",
			"IB True-Up DATA MB Real", "IB True-Up Data MB Charged", "IB True-Up Voice Rev", "IB True-Up SMS MO Rev",
			"IB True-Up SMS MT Rev", "IB True-Up DATA Rev", "IB True-Up Total Rev", "OB Accrual Voice MOU Real",
			"OB Accrual Voice MOU Charged", "OB Accrual SMS MO", "OB Accrual SMS MT", "OB Accrual DATA MB Real",
			"OB Accrual DATA MB Charged", "OB Accrual Voice Exp", "OB Accrual SMS MO Exp", "OB Accrual SMS MT Exp",
			"OB Accrual DATA Exp", "OB Accrual Total Exp", "OB Actual Voice MOU Real", "OB Actual Voice MOU Charged",
			"OB Actual SMS MO", "OB Actual SMS MT", "OB Actual DATA MB Real", "OB Actual DATA MB Charged",
			"OB Actual Voice Exp", "OB Actual SMS MO Exp", "OB Actual SMS MT Exp", "OB Actual DATA Exp",
			"OB Actual Total Exp", "OB True-Up Voice MOU Real", "OB True-Up Voice MOU Charged", "OB True-Up SMS MO",
			"OB True-Up SMS MT", "OB True-Up DATA MB Real", "OB True-Up DATA MB Charged", "OB True-Up Voice Exp",
			"OB True-Up SMS MO Exp", "OB True-Up SMS MT Exp", "OB True-Up DATA Exp", "OB True-Up Total Exp",
			"Snapshot Date", "Processing Date", "Snapshot Date (Pre-Month End)", "Processing Date (Pre-Month End)"));

	public void verify_UI(String traffPeriod, String post_procDate, String discType) {
		
		com.isElementPresent(data_TAPAccrualActualTrueUp_BreadCrumb,
				"'TAP Accrual, Actual & True Up' page heading");

		CustomReporter.report(STATUS.INFO, "Sections verification");
		com.isElementPresent(data_Filters_Section, "Filters section");

		CustomReporter.report(STATUS.INFO, "Search fields and buttons verification");
		com.isElementPresent(select_Operator, "Operator dropdown");
		com.isElementPresent(select_TrafficPeriod, "Traffic Period dropdown");
		com.isElementPresent(select_ProcessingDate, "Processing Date dropdown");
		com.isElementPresent(select_DiscountType, "Discount Type dropdown");
		com.isElementPresent(button_RefreshReport, "Refresh Report button");
		com.isElementPresent(button_Back, "Back button");

		CustomReporter.report(STATUS.INFO, "Search Result Section");
		if (performSearch(traffPeriod, post_procDate, discType)) {

			com.isElementPresent(data_TAPAccrualActualTrueUp_SearchResultSection,
					"'TAP Accrual, Actual & True Up' Section");
			com.isElementPresent(comm.icon_SelectColumnToSearch, "Apex Toolbar - Column Selection icon");
			com.isElementPresent(comm.text_Search, "Apex Toolbar - Search textfield");
			com.isElementPresent(comm.select_RowsPerPage, "Apex Toolbar - Select Rows dropdown");
			
			comm.verifyActionsPopupItems_WithSubscription();
			
			com.isElementPresent(By.xpath("//div[contains(.,'This report took')]"),
					"'This report took x.x seconds to run' message at bottom");
			comm.verifyColumnHeaders(comm.table_ResultTabHeader,columnNames, 1);
		}
	}

	public void verify_ColumnsCalculation(String methodName) {
		
		CustomReporter.createNode("Verifying [Pre Month End IB/OB] 'Sms Mt' and 'Total' columns"
				+ "Calculations On TAP Accrual Actual And True Up Page");
		
		JSONManager json= new JSONManager(Module_TMO.jsonFilePath, methodName);
		String smsMtOrMO=json.getStr("smsMtOrMO");
		String iBPartner=json.getStr("iBPartner");
		String oBPartner=json.getStr("oBPartner");
		json= new JSONManager(Module_TMO.jsonFilePath, methodName,"searchParam_pre");
		String traffPeriod=json.getStr("traffPeriod");
		String pre_procDate=json.getStr("procDate");
		String discType=json.getStr("discType");
		json= new JSONManager(Module_TMO.jsonFilePath, methodName,"searchParam_post");
		String post_procDate=json.getStr("procDate");
		
		String dayOfMonth = pre_procDate.substring(pre_procDate.indexOf(":") + 5, pre_procDate.indexOf(":") + 7);
		String daysInMonth = post_procDate.substring(post_procDate.indexOf(":") + 5, post_procDate.indexOf(":") + 7);

		// Verifying [Pre Month End IB/OB] "Sms Mt" and "Total" columns
		// Calculations On TAP Accrual Actual And True Up Page
		
		if (performSearch(traffPeriod,pre_procDate,discType)) {
			
			tabHead.initHeaderColumnList(headerRow);

			int partnerPLMNCol = tabHead.getColumnNumber("Partner PLMN Ascending");
			int iBAccrualSmsMtCol = tabHead.getColumnNumber("IB Accrual " + smsMtOrMO);
			int iBAccrualSmsMtRevCol = tabHead.getColumnNumber("IB Accrual " + smsMtOrMO + " Rev");
			int iBActualSmsMtCol = tabHead.getColumnNumber("IB Actual " + smsMtOrMO);
			int iBActualSmsMtRevCol = tabHead.getColumnNumber("IB Actual " + smsMtOrMO + " Rev");

			CustomReporter.createNode("FOR IB");
			
			comm.applyColumnFilter(headerRow, partnerPLMNCol, iBPartner);
			String iBAccrualSmsMtVal = tab.getCellText(2, iBAccrualSmsMtCol);
			String iBAccrualSmsMtRevVal = tab.getCellText(2, iBAccrualSmsMtRevCol);
			String iBActualSmsMtVal = tab.getCellText(2, iBActualSmsMtCol);
			String iBActualSmsMtRevVal = tab.getCellText(2, iBActualSmsMtRevCol);
			
			
			String calc_iBAccrualSmsMtVal = Util.BD(iBActualSmsMtVal)
					.divide(Util.BD(dayOfMonth), 20, RoundingMode.HALF_EVEN).multiply(Util.BD(daysInMonth))
					.toPlainString();
			CustomReporter.report(STATUS.INFO, "Calculating [IB Accrual SMS MT] value using formulae : <br/>"
					+ " [IB Actual SMS MT value] / [dayOfMonth] * [daysInMonth] = "+calc_iBAccrualSmsMtVal);
			
			String calc_iBAccrualSmsMtRevVal = Util.BD(iBActualSmsMtRevVal)
					.divide(Util.BD(dayOfMonth), 20, RoundingMode.HALF_EVEN).multiply(Util.BD(daysInMonth))
					.toPlainString();
			CustomReporter.report(STATUS.INFO, "Calculating [IB Accrual SMS MT Rev] value using formulae : <br/>"
					+ " [IB Actual SMS MT Rev value] / [dayOfMonth] * [daysInMonth] = "+calc_iBAccrualSmsMtRevVal);

			Util.comparator(calc_iBAccrualSmsMtVal, iBAccrualSmsMtVal, "INBOUND - Accrual " + smsMtOrMO + " Column");
			Util.comparator(calc_iBAccrualSmsMtRevVal, iBAccrualSmsMtRevVal,
					"INBOUND - Accrual " + smsMtOrMO + " Rev Column");

			int iBAccrualTotalRevCol = tabHead.getColumnNumber("IB Accrual Total Rev");
			int iBActualTotalRevCol = tabHead.getColumnNumber("IB Actual Total Rev");

			String iBAccrualTotalRevVal = tab.getCellText(2, iBAccrualTotalRevCol);
			String iBActualTotalRevVal = tab.getCellText(2, iBActualTotalRevCol);

			String calc_iBAccrualTotalRevCol = Util.BD(tab.getCellText(2, iBAccrualTotalRevCol - 1))
					.add(Util.BD(tab.getCellText(2, iBAccrualTotalRevCol - 2)))
					.add(Util.BD(tab.getCellText(2, iBAccrualTotalRevCol - 3)))
					.add(Util.BD(tab.getCellText(2, iBAccrualTotalRevCol - 4))).toPlainString();
			CustomReporter.report(STATUS.INFO, "Calculating [IB Accrual Total Rev] col value using formulae : <br/>"
					+ " [IB Accrual DATA Rev] + [IB Accrual SMS MT Rev] + [IB Accrual SMS MO Rev] + [IB Accrual Voice Rev] = "+calc_iBAccrualTotalRevCol);
			
			String calc_iBActualTotalRevCol = Util.BD(tab.getCellText(2, iBActualTotalRevCol - 1))
					.add(Util.BD(tab.getCellText(2, iBActualTotalRevCol - 2)))
					.add(Util.BD(tab.getCellText(2, iBActualTotalRevCol - 3)))
					.add(Util.BD(tab.getCellText(2, iBActualTotalRevCol - 4))).toPlainString();
			CustomReporter.report(STATUS.INFO, "Calculating [IB Actual Total Rev] col value using formulae : <br/>"
					+ " [IB Actual DATA Rev] + [IB Actual SMS MT Rev] + [IB Actual SMS MO Rev] + [IB Actual Voice Rev] = "+calc_iBActualTotalRevCol);
			
			Util.comparator(calc_iBAccrualTotalRevCol, iBAccrualTotalRevVal, "INBOUND - Accrual Total Rev Column");
			Util.comparator(calc_iBActualTotalRevCol, iBActualTotalRevVal, "INBOUND - Actual Total Rev Column");

			CustomReporter.createNode("FOR OB");
			
			comm.applyColumnFilter(headerRow, partnerPLMNCol, oBPartner);
			int oBAccrualSmsMtCol = tabHead.getColumnNumber("OB Accrual " + smsMtOrMO);
			int oBAccrualSmsMtExpCol = tabHead.getColumnNumber("OB Accrual " + smsMtOrMO + " Exp");
			int oBActualSmsMtCol = tabHead.getColumnNumber("OB Actual " + smsMtOrMO);
			int oBActualSmsMtExpCol = tabHead.getColumnNumber("OB Actual " + smsMtOrMO + " Exp");

			String oBAccrualSmsMtVal = tab.getCellText(2, oBAccrualSmsMtCol);
			String oBAccrualSmsMtExpVal = tab.getCellText(2, oBAccrualSmsMtExpCol);
			String oBActualSmsMtVal = tab.getCellText(2, oBActualSmsMtCol);
			String oBActualSmsMtExpVal = tab.getCellText(2, oBActualSmsMtExpCol);

			String calc_oBAccrualSmsMtVal = Util.BD(oBActualSmsMtVal)
					.divide(Util.BD(dayOfMonth), 20, RoundingMode.HALF_EVEN).multiply(Util.BD(daysInMonth))
					.toPlainString();
			CustomReporter.report(STATUS.INFO, "Calculating [OB Accrual SMS MT] value using formulae : <br/>"
					+ " [OB Actual SMS MT value] / [dayOfMonth] * [daysInMonth] = "+calc_oBAccrualSmsMtVal);
			
			String calc_oBAccrualSmsMtExpVal = Util.BD(oBActualSmsMtExpVal)
					.divide(Util.BD(dayOfMonth), 20, RoundingMode.HALF_EVEN).multiply(Util.BD(daysInMonth))
					.toPlainString();
			CustomReporter.report(STATUS.INFO, "Calculating [OB Accrual SMS MT Exp] value using formulae : <br/>"
					+ " [OB Actual SMS MT Exp value] / [dayOfMonth] * [daysInMonth] = "+calc_oBAccrualSmsMtExpVal);


			Util.comparator(calc_oBAccrualSmsMtVal, oBAccrualSmsMtVal, "OUTBOUND - Accrual " + smsMtOrMO + " Column");
			Util.comparator(calc_oBAccrualSmsMtExpVal, oBAccrualSmsMtExpVal,
					"OUTBOUND - Accrual " + smsMtOrMO + " Rev Column");

			int oBAccrualTotalExpCol = tabHead.getColumnNumber("OB Accrual Total Exp");
			int oBActualTotalExpCol = tabHead.getColumnNumber("OB Actual Total Exp");

			String oBAccrualTotalRevVal = tab.getCellText(2, oBAccrualTotalExpCol);
			String oBActualTotalRevVal = tab.getCellText(2, oBActualTotalExpCol);

			String calc_oBAccrualTotalRevCol = Util.BD(tab.getCellText(2, oBAccrualTotalExpCol - 1))
					.add(Util.BD(tab.getCellText(2, oBAccrualTotalExpCol - 2)))
					.add(Util.BD(tab.getCellText(2, oBAccrualTotalExpCol - 3)))
					.add(Util.BD(tab.getCellText(2, oBAccrualTotalExpCol - 4))).toPlainString();
			CustomReporter.report(STATUS.INFO, "Calculating [OB Accrual Total Exp] col value using formulae : <br/>"
					+ " [OB Accrual DATA Exp] + [OB Accrual SMS MT Exp] + [OB Accrual SMS MO Exp] + [OB Accrual Voice Exp] = "+calc_oBAccrualTotalRevCol);
			
			String calc_oBActualTotalRevCol = Util.BD(tab.getCellText(2, oBActualTotalExpCol - 1))
					.add(Util.BD(tab.getCellText(2, oBActualTotalExpCol - 2)))
					.add(Util.BD(tab.getCellText(2, oBActualTotalExpCol - 3)))
					.add(Util.BD(tab.getCellText(2, oBActualTotalExpCol - 4))).toPlainString();
			CustomReporter.report(STATUS.INFO, "Calculating [OB Actual Total Exp] col value using formulae : <br/>"
					+ " [OB Actual DATA Exp] + [OB Actual SMS MT Exp] + [OB Actual SMS MO Exp] + [OB Actual Voice Exp] = "+calc_oBActualTotalRevCol);
			
			
			Util.comparator(calc_oBAccrualTotalRevCol, oBAccrualTotalRevVal, "OUTBOUND - Accrual Total Rev Column");
			Util.comparator(calc_oBActualTotalRevCol, oBActualTotalRevVal, "OUTBOUND - Actual Total Rev Column");

			CustomReporter.createNode("Verifying [Post Month End IB/OB] 'Sms Mt' and 'Total' columns"
					+ "Calculations On TAP Accrual Actual And True Up Page");
			
			// Verifying [Post Month End IB/OB] "Sms Mt" and "Total" columns
			// Calculations On TAP Accrual Actual And True Up Page
			if (performSearch(null, post_procDate, null)) {
				// FOR OUTBOUND
				
				CustomReporter.createNode("For OB");
				
				String oBAccrualSmsMtVal_Post = tab.getCellText(2, oBAccrualSmsMtCol);
				String oBAccrualSmsMtExpVal_Post = tab.getCellText(2, oBAccrualSmsMtExpCol);
				Util.comparator_PageValues("OUTBOUND - Accrual " + smsMtOrMO, "Pre Month End State", oBAccrualSmsMtVal,
						"Post Month End State", oBAccrualSmsMtVal_Post);
				Util.comparator_PageValues("OUTBOUND - Accrual " + smsMtOrMO + " Exp", "Pre Month End State",
						oBAccrualSmsMtExpVal, "Post Month End State", oBAccrualSmsMtExpVal_Post);

				String oBActualSmsMtVal_Post = tab.getCellText(2, oBActualSmsMtCol);
				String oBActualSmsMtExpVal_Post = tab.getCellText(2, oBActualSmsMtExpCol);

				int oBTrueUpSmsMtCol = tabHead.getColumnNumber("OB True-Up " + smsMtOrMO);
				int oBTrueUpSmsMtExpCol = tabHead.getColumnNumber("OB True-Up " + smsMtOrMO + " Exp");

				String oBTrueUpSmsMt_Post_Page = tab.getCellText(2, oBTrueUpSmsMtCol);
				String oBTrueUpSmsMtExp_Post_Page = tab.getCellText(2, oBTrueUpSmsMtExpCol);

				String oBTrueUpSmsMt_Post_Calc = Util.BD(oBActualSmsMtVal_Post)
						.subtract(Util.BD(oBAccrualSmsMtVal_Post)).toPlainString();
				String oBTrueUpSmsMtExp_Post_Calc = Util.BD(oBActualSmsMtExpVal_Post)
						.subtract(Util.BD(oBAccrualSmsMtExpVal_Post)).toPlainString();
				Util.comparator(oBTrueUpSmsMt_Post_Calc, oBTrueUpSmsMt_Post_Page,
						"OUTBOUND - True Up " + smsMtOrMO + " - Post End");
				Util.comparator(oBTrueUpSmsMtExp_Post_Calc, oBTrueUpSmsMtExp_Post_Page,
						"OUTBOUND - True Up " + smsMtOrMO + " Exp - Post End");

				String oBAccrualTotalExpVal_Post_Page = tab.getCellText(2, oBAccrualTotalExpCol);
				String oBActualTotalExpVal_Post_Page = tab.getCellText(2, oBActualTotalExpCol);

				String calc_oBAccrualTotalExpCol_Post_Calc = Util.BD(tab.getCellText(2, oBAccrualTotalExpCol - 1))
						.add(Util.BD(tab.getCellText(2, oBAccrualTotalExpCol - 2)))
						.add(Util.BD(tab.getCellText(2, oBAccrualTotalExpCol - 3)))
						.add(Util.BD(tab.getCellText(2, oBAccrualTotalExpCol - 4))).toPlainString();
				String calc_oBActualTotalExpCol_Post_Calc = Util.BD(tab.getCellText(2, oBActualTotalExpCol - 1))
						.add(Util.BD(tab.getCellText(2, oBActualTotalExpCol - 2)))
						.add(Util.BD(tab.getCellText(2, oBActualTotalExpCol - 3)))
						.add(Util.BD(tab.getCellText(2, oBActualTotalExpCol - 4))).toPlainString();
				Util.comparator(calc_oBAccrualTotalExpCol_Post_Calc, oBAccrualTotalExpVal_Post_Page,
						"OUTBOUND - Accrual Total Exp Column - Post End");
				Util.comparator(calc_oBActualTotalExpCol_Post_Calc, oBActualTotalExpVal_Post_Page,
						"OUTBOUND - Actual Total Exp Column - Post End");

				// For INBOUND
				CustomReporter.createNode("For IB");
				
				comm.applyColumnFilter(headerRow, partnerPLMNCol, iBPartner);
				String iBAccrualSmsMtVal_Post = tab.getCellText(2, iBAccrualSmsMtCol);
				String iBAccrualSmsMtRevVal_Post = tab.getCellText(2, iBAccrualSmsMtRevCol);
				Util.comparator_PageValues("INBOUND - Accrual " + smsMtOrMO, "Pre Month End State", iBAccrualSmsMtVal,
						"Post Month End State", iBAccrualSmsMtVal_Post);
				Util.comparator_PageValues("INBOUND - Accrual " + smsMtOrMO + " Rev", "Pre Month End State",
						iBAccrualSmsMtRevVal, "Post Month End State", iBAccrualSmsMtRevVal_Post);

				String iBActualSmsMtVal_Post = tab.getCellText(2, iBActualSmsMtCol);
				String iBActualSmsMtRevVal_Post = tab.getCellText(2, iBActualSmsMtRevCol);

				int iBTrueUpSmsMtCol = tabHead.getColumnNumber("IB True-Up " + smsMtOrMO);
				int iBTrueUpSmsMtRevCol = tabHead.getColumnNumber("IB True-Up " + smsMtOrMO + " Rev");

				String iBTrueUpSmsMt_Post_Page = tab.getCellText(2, iBTrueUpSmsMtCol);
				String iBTrueUpSmsMtRev_Post_Page = tab.getCellText(2, iBTrueUpSmsMtRevCol);

				String iBTrueUpSmsMt_Post_Calc = Util.BD(iBActualSmsMtVal_Post)
						.subtract(Util.BD(iBAccrualSmsMtVal_Post)).toPlainString();
				String iBTrueUpSmsMtRev_Post_Calc = Util.BD(iBActualSmsMtRevVal_Post)
						.subtract(Util.BD(iBAccrualSmsMtRevVal_Post)).toPlainString();
				Util.comparator(iBTrueUpSmsMt_Post_Calc, iBTrueUpSmsMt_Post_Page,
						"INBOUND - True Up " + smsMtOrMO + " - Post End");
				Util.comparator(iBTrueUpSmsMtRev_Post_Calc, iBTrueUpSmsMtRev_Post_Page,
						"INBOUND - True Up " + smsMtOrMO + " Rev - Post End");

				String iBAccrualTotalRevVal_Post_Page = tab.getCellText(2, iBAccrualTotalRevCol);
				String iBActualTotalRevVal_Post_Page = tab.getCellText(2, iBActualTotalRevCol);

				String calc_iBAccrualTotalRevCol_Post_Calc = Util.BD(tab.getCellText(2, iBAccrualTotalRevCol - 1))
						.add(Util.BD(tab.getCellText(2, iBAccrualTotalRevCol - 2)))
						.add(Util.BD(tab.getCellText(2, iBAccrualTotalRevCol - 3)))
						.add(Util.BD(tab.getCellText(2, iBAccrualTotalRevCol - 4))).toPlainString();
				String calc_iBActualTotalRevCol_Post_Calc = Util.BD(tab.getCellText(2, iBActualTotalRevCol - 1))
						.add(Util.BD(tab.getCellText(2, iBActualTotalRevCol - 2)))
						.add(Util.BD(tab.getCellText(2, iBActualTotalRevCol - 3)))
						.add(Util.BD(tab.getCellText(2, iBActualTotalRevCol - 4))).toPlainString();
				Util.comparator(calc_iBAccrualTotalRevCol_Post_Calc, iBAccrualTotalRevVal_Post_Page,
						"INBOUND - Accrual Total Exp Column - Post End");
				Util.comparator(calc_iBActualTotalRevCol_Post_Calc, iBActualTotalRevVal_Post_Page,
						"INBOUND - Actual Total Exp Column - Post End");

				CustomReporter.createNode("Verifying the 'SMS MT' values from 'Traffic Analysis Report by PMN'");
				
				// Verifying the 'SMS MT' values from 'Traffic Analysis Report
				// by PMN'
				TrafficAnalysisReportByPMN trafficAnalysisReportByPMN = new Navigator().to_TrafficAnalysisReportByPMN();
				trafficAnalysisReportByPMN.verifySmsValues(methodName, iBActualSmsMtVal, iBActualSmsMtRevVal, oBActualSmsMtVal, oBActualSmsMtExpVal,
						iBActualSmsMtVal_Post, iBActualSmsMtRevVal_Post, oBActualSmsMtVal_Post,
						oBActualSmsMtExpVal_Post);
			}
		}
	}

	public void verifyAllColumnsAreAdded(String... jsonObjName) {
		JSONManager json=new JSONManager(Module_TMO.jsonFilePath, jsonObjName);
		if (performSearch(json.getStr("traffPeriod"), json.getStr("procDate"), json.getStr("discType"))) {
			comm.verifyColumnHeaders(comm.table_ResultTabHeader, columnNames, 1);
		}
	}

	private boolean performSearch(String traffPeriod, String procDate, String discType) {
		
		fillBasicSearchFields(traffPeriod, procDate, discType);
		com.click(button_RefreshReport);
		if (com.isElementPresent(comm.table_ResultTab)) {
			return true;
		}
		return false;
	}

	private void fillBasicSearchFields(String traffPeriod, String procDate, String discType) {
		String message = "";
		com.selectByPartialVisibleText(select_Operator, TADIG.USAW6.value);
		message = message + "| Operator- '" + TADIG.USAW6.value + "'";
		
		if (traffPeriod != null) {
			com.selectByPartialVisibleText(select_TrafficPeriod, traffPeriod);
			com.wait(1);
			message = message + "| trafficPeriod- '" + traffPeriod + "'";
		}

		if (procDate != null) {
			com.selectByPartialVisibleText(select_ProcessingDate, procDate);
			com.wait(1);
			message = message + "| processingDate- '" + procDate + "'";
		}

		if (discType != null) {
			com.selectByPartialVisibleText(select_DiscountType, discType);
			com.wait(1);
			message = message + "| discountType- '" + discType + "'";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data: " + message);

	}
	
	public void verifyFinancialForecastRepData(HashMap<String, String> ibOb_TAPCharges_TrafVol, String methodName) {
		
		String ibTrafVol = ibOb_TAPCharges_TrafVol.get("ibTrafVol");
		String obTrafVol = ibOb_TAPCharges_TrafVol.get("obTrafVol");

		String ibTapCharges = ibOb_TAPCharges_TrafVol.get("ibTapCh");
		String obTapCharges = ibOb_TAPCharges_TrafVol.get("obTapCh");
		
		
		JSONManager json= new JSONManager(Module_TMO.jsonFilePath, methodName,"tapAccrualActualAndTrueUp");
		String ibPartner = json.getStr("ibPartner");
		String obPartner = json.getStr("obPartner");

		
		json= new JSONManager(Module_TMO.jsonFilePath, methodName,"tapAccrualActualAndTrueUp","searchParam_post");
		if (performSearch(json.getStr("traffPeriod"), json.getStr("procDate"), json.getStr("discType"))) {
			tabHead.initHeaderColumnList(headerRow);

			int partnerPLMNCol = tabHead.getColumnNumber("Partner PLMN Ascending");
			int iBActualSmsMtCol = tabHead.getColumnNumber("IB Actual SMS MT");
			int iBActualSmsMtRevCol = tabHead.getColumnNumber("IB Actual SMS MT Rev");

			CustomReporter.createNode("FOR IB");
			
			comm.applyColumnFilter(headerRow, partnerPLMNCol, ibPartner);
			String iBActualSmsMtVal = tab.getCellText(2, iBActualSmsMtCol);
			String iBActualSmsMtRevVal = tab.getCellText(2, iBActualSmsMtRevCol);

			Util.comparator_PageValues("INBOUND - 'Actual SMS MT' & 'Traffic Vol'",
					FinancialReport_ForecastedTAPDiscountedCharges.title, ibTrafVol, TapAccrualActualAndTrueUp.title,
					iBActualSmsMtVal);
			Util.comparator_PageValues("INBOUND - 'Actual SMS MT Rev' & 'TAP Charges'",
					FinancialReport_ForecastedTAPDiscountedCharges.title, ibTapCharges, TapAccrualActualAndTrueUp.title,
					iBActualSmsMtRevVal);

			CustomReporter.createNode("FOR OB");
			
			comm.applyColumnFilter(headerRow, partnerPLMNCol, obPartner);
			int oBActualSmsMtCol = tabHead.getColumnNumber("OB Actual SMS MT");
			int oBActualSmsMtExpCol = tabHead.getColumnNumber("OB Actual SMS MT Exp");

			String oBActualSmsMtVal = tab.getCellText(2, oBActualSmsMtCol);
			String oBActualSmsMtExpVal = tab.getCellText(2, oBActualSmsMtExpCol);

			Util.comparator_PageValues("OUTBOUND - 'Actual SMS MT' & 'Traffic Vol'",
					FinancialReport_ForecastedTAPDiscountedCharges.title, obTrafVol, TapAccrualActualAndTrueUp.title,
					oBActualSmsMtVal);
			Util.comparator_PageValues("OUTBOUND - 'Actual SMS MT Exp' & 'TAP Charges'",
					FinancialReport_ForecastedTAPDiscountedCharges.title, obTapCharges, TapAccrualActualAndTrueUp.title,
					oBActualSmsMtExpVal);
		}
	}

}
