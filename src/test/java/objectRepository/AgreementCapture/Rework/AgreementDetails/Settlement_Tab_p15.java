package objectRepository.AgreementCapture.Rework.AgreementDetails;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.WebTable;

public class Settlement_Tab_p15 extends IOTDiscountAgreementDetails_p15_Parent{
	public static String title = IOTDiscountAgreementDetails_p15_Parent.title;

	public Settlement_Tab_p15() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	private void verifyTableRowValues(By tabBy, List<String> expectedRowItemsList,String desc) {
		int colNum=1;
		WebTable tab=new WebTable(tabBy);
		int lastRow=tab.getRowCount();
		List<String> actualRowValuesList=new ArrayList<String>();
		for (int row = 2; row <= lastRow; row++) {
			actualRowValuesList.add(tab.getCellText(row,colNum));
		}
		Util.comparator_List_NonNumbers(expectedRowItemsList, actualRowValuesList, desc);
	}

	private void verifyTableColumnValues(By tabBy, String cell_1x1, List<String> expectedColItemsList,String desc) {
		int rowNum=1;
		WebTable tab=new WebTable(tabBy);
		

		if (tab.getCellText(1, 1).equalsIgnoreCase(cell_1x1)){
			CustomReporter.report(STATUS.PASS, cell_1x1+" successfully displayed in first row and first col of "+tabBy);
		} else {
			CustomReporter.report(STATUS.FAIL, cell_1x1+" failed to displayed in first row and first col of "+tabBy);
		}
		
		int lastCol=tab.getColCount(rowNum);
		List<String> actualColumnNamesList=new ArrayList<String>();
		for (int col = 2; col <= lastCol; col++) {
			actualColumnNamesList.add(tab.getCellText(rowNum, col));
		}
		Util.comparator_List_NonNumbers(expectedColItemsList, actualColumnNamesList, desc);
	}
	
	@FindBy(xpath = "//h2[.='Settlement']")
	private WebElement heading_Settlement;
	
	@FindBy(xpath = "//div[contains(.,'Settlement')]/div/button")
	private WebElement hideShow_Settlement;

	private List<String> clientList;
	private List<String> partnerList;
	
	public void verify_UI() {
		CustomReporter.report(STATUS.INFO, "Details TAB ->> For getting the client and partner TADIGS");
		openTab(tab_Details);
		Details_Tab_p15 detTab=new Details_Tab_p15();
		clientList=detTab.getClientTadigList();
		partnerList=detTab.getPartnerTadigList();
		
		
		CustomReporter.report(STATUS.INFO, "Settlement TAB ->>");
		openTab(tab_Settlement);
		com.isElementPresent(heading_Settlement, "Settlement Tab heading");
		verifyHideShowFunc(true, hideShow_Settlement,"Settlement Section");
		
		section_SettlementTermsAndParameters();
		section_HomeOperatorSettlementTermDays();
		section_HomeOperatorInterestPenaltyRate();
		section_HomeOperatorC_3_2_SettlementCurrency();
		section_PartnerSettlementTermDays();
		section_PartnerInterestPenaltyRate();
		section_PartnerC_3_2_SettlementCurrency();
		
	}
	
	
	@FindBy(xpath = "//h2[.='Settlement Terms and Parameters']")
	private WebElement heading_SettlementTermsAndParameters;
	
	@FindBy(xpath = "//div[contains(.,'Settlement Terms and Parameters')]/div/button")
	private WebElement hideShow_SettlementTermsAndParameters;

	@FindBy(xpath = "//label[.='Settlement Status']")
	private WebElement label_SettlementStatus;
	
	@FindBy(xpath = "//label[.='Tax on Settlement Statement Summary']")
	private WebElement label_TaxOnSettlementStatementSummary;
	
	@FindBy(xpath = "//label[.='Generate Settlement Statement Rule']")
	private WebElement label_GenerateSettlementStatementRule;
	
	@FindBy(xpath = "//label[.='Settlement Frequency']")
	private WebElement label_SettlementFrequency;

	private void section_SettlementTermsAndParameters() {
		CustomReporter.report(STATUS.INFO, "Settlement Terms and Parameters TAB ->>");
		com.isElementPresent(heading_SettlementTermsAndParameters, "Settlement Terms and Parameters Tab heading");
		verifyHideShowFunc(true, hideShow_SettlementTermsAndParameters,"Settlement Terms and Parameters Section");

		CustomReporter.report(STATUS.INFO, "Labels ->>");
		com.isElementPresent(label_SettlementStatus, "label_Settlement Status");
		com.isElementPresent(label_TaxOnSettlementStatementSummary, "label_Tax On Settlement Statement Summary");
		com.isElementPresent(label_GenerateSettlementStatementRule, "label_Generate Settlement Statement Rule");
		com.isElementPresent(label_SettlementFrequency, "label_Settlement Frequency");
	}

	@FindBy(xpath = "//h2[.='Home Operator Settlement Term Days']")
	private WebElement heading_HomeOperatorSettlementTermDays;
	
	@FindBy(xpath = "//div[contains(.,'Home Operator Settlement Term Days')]/div/button")
	private WebElement hideShow_HomeOperatorSettlementTermDays;

	private By tab_HomeOperatorSettlementTermDays=By.cssSelector("table[summary='Home Operator Settlement Term Days']");
	
	private void section_HomeOperatorSettlementTermDays() {
		CustomReporter.report(STATUS.INFO, "Home Operator Settlement Term Days TAB ->>");
		com.isElementPresent(heading_HomeOperatorSettlementTermDays, "Home Operator Settlement Term Days Tab heading");
		verifyHideShowFunc(false, hideShow_HomeOperatorSettlementTermDays,"Home Operator Settlement Term Days Section");

		CustomReporter.report(STATUS.INFO, "Table Data ->>");
		com.click(hideShow_HomeOperatorSettlementTermDays,"hideShow_Settlement Terms And Parameters");
		com.isElementPresent(tab_HomeOperatorSettlementTermDays, "Home Operator Settlement Term Days table");
		verifyTableColumnValues(tab_HomeOperatorSettlementTermDays,"Client",partnerList,"Partner Names as COL in 'Home Operator Settlement Term Days' tab");
		verifyTableRowValues(tab_HomeOperatorSettlementTermDays,clientList,"Client Names as ROW in 'Home Operator Settlement Term Days' tab");
	}

	

	@FindBy(xpath = "//h2[.='Home Operator Interest Penalty Rate']")
	private WebElement heading_HomeOperatorInterestPenaltyRate;
	
	@FindBy(xpath = "//div[contains(.,'Home Operator Interest Penalty Rate')]/div/button")
	private WebElement hideShow_HomeOperatorInterestPenaltyRate;

	private By tab_HomeOperatorInterestPenaltyRate=By.cssSelector("table[summary='Home Operator Interest Penalty Rate']");
	
	private void section_HomeOperatorInterestPenaltyRate() {
		CustomReporter.report(STATUS.INFO, "Home Operator Interest Penalty Rate TAB ->>");
		com.isElementPresent(heading_HomeOperatorInterestPenaltyRate, "Home Operator Interest Penalty Rate Tab heading");
		verifyHideShowFunc(false, hideShow_HomeOperatorInterestPenaltyRate,"Home Operator Interest Penalty Rate Section");

		CustomReporter.report(STATUS.INFO, "Table Data ->>");
		com.click(hideShow_HomeOperatorInterestPenaltyRate,"hideShow_Home Operator Interest Penalty Rate");
		com.isElementPresent(tab_HomeOperatorInterestPenaltyRate, "Home Operator Interest Penalty Rate table");
		verifyTableColumnValues(tab_HomeOperatorInterestPenaltyRate,"Client",partnerList,"Partner Names as COL in 'Home Operator Interest Penalty Rate' tab");
		verifyTableRowValues(tab_HomeOperatorInterestPenaltyRate,clientList,"Client Names as ROW in 'Home Operator Interest Penalty Rate' tab");
	}

	

	@FindBy(xpath = "//h2[.='Home Operator C.3.2 / Settlement Currency']")
	private WebElement heading_HomeOperatorC_3_2_SettlementCurrency;
	
	@FindBy(xpath = "//div[contains(.,'Home Operator C.3.2 / Settlement Currency')]/div/button")
	private WebElement hideShow_HomeOperatorC_3_2_SettlementCurrency;

	private By tab_HomeOperatorC_3_2_SettlementCurrency=By.cssSelector("table[summary='Home Operator C.3.2 / Settlement Currency']");
	
	private void section_HomeOperatorC_3_2_SettlementCurrency() {
		CustomReporter.report(STATUS.INFO, "Home Operator C.3.2 / Settlement Currency TAB ->>");
		com.isElementPresent(heading_HomeOperatorC_3_2_SettlementCurrency, "Home Operator C.3.2 / Settlement Currency Tab heading");
		verifyHideShowFunc(false, hideShow_HomeOperatorC_3_2_SettlementCurrency,"Home Operator C.3.2 / Settlement Currency Section");

		CustomReporter.report(STATUS.INFO, "Table Data ->>");
		com.click(hideShow_HomeOperatorC_3_2_SettlementCurrency,"hideShow_Home Operator C.3.2 / Settlement Currency");
		com.isElementPresent(tab_HomeOperatorC_3_2_SettlementCurrency, "Home Operator C.3.2 / Settlement Currency table");
		verifyTableColumnValues(tab_HomeOperatorC_3_2_SettlementCurrency,"Client",partnerList,"Partner Names as COL in 'Home Operator C.3.2 / Settlement Currency' tab");
		verifyTableRowValues(tab_HomeOperatorC_3_2_SettlementCurrency,clientList,"Client Names as ROW in 'Home Operator C.3.2 / Settlement Currency' tab");
	}
	
	@FindBy(xpath = "//h2[.='Partner Settlement Term Days']")
	private WebElement heading_PartnerSettlementTermDays;
	
	@FindBy(xpath = "//div[contains(.,'Partner Settlement Term Days')]/div/button")
	private WebElement hideShow_PartnerSettlementTermDays;

	private By tab_PartnerSettlementTermDays=By.cssSelector("table[summary='Partner Settlement Term Days']");
	
	private void section_PartnerSettlementTermDays() {
		CustomReporter.report(STATUS.INFO, "Partner Settlement Term Days TAB ->>");
		com.isElementPresent(heading_PartnerSettlementTermDays, "Partner Settlement Term Days Tab heading");
		verifyHideShowFunc(false, hideShow_PartnerSettlementTermDays,"Partner Settlement Term Days Section");

		CustomReporter.report(STATUS.INFO, "Table Data ->>");
		com.click(hideShow_PartnerSettlementTermDays,"hideShow_Partner Settlement Term Days");
		com.isElementPresent(tab_PartnerSettlementTermDays, "Partner Settlement Term Days table");
		verifyTableColumnValues(tab_PartnerSettlementTermDays,"Partner",clientList,"Client Names as COL in 'Partner Settlement Term Days' tab");
		verifyTableRowValues(tab_PartnerSettlementTermDays,partnerList,"Partne Names as ROW in 'Partner Settlement Term Days' tab");
	}
	
	
	@FindBy(xpath = "//h2[.='Partner Interest Penalty Rate']")
	private WebElement heading_PartnerInterestPenaltyRate;
	
	@FindBy(xpath = "//div[contains(.,'Partner Interest Penalty Rate')]/div/button")
	private WebElement hideShow_PartnerInterestPenaltyRate;

	private By tab_PartnerInterestPenaltyRate=By.cssSelector("table[summary='Partner Interest Penalty Rate']");
	
	private void section_PartnerInterestPenaltyRate() {
		CustomReporter.report(STATUS.INFO, "Partner Interest Penalty Rate TAB ->>");
		com.isElementPresent(heading_PartnerInterestPenaltyRate, "Partner Interest Penalty Rate Tab heading");
		verifyHideShowFunc(false, hideShow_PartnerInterestPenaltyRate,"Partner Interest Penalty Rate Section");

		CustomReporter.report(STATUS.INFO, "Table Data ->>");
		com.click(hideShow_PartnerInterestPenaltyRate,"hideShow_Partner Interest Penalty Rate");
		com.isElementPresent(tab_PartnerInterestPenaltyRate, "Partner Interest Penalty Rate table");
		verifyTableColumnValues(tab_PartnerInterestPenaltyRate,"Partner",clientList,"Client Names as COL in 'Partner Interest Penalty Rate' tab");
		verifyTableRowValues(tab_PartnerInterestPenaltyRate,partnerList,"Partne Names as ROW in 'Partner Interest Penalty Rate' tab");
	}
	
	@FindBy(xpath = "//h2[.='Partner C.3.2 / Settlement Currency']")
	private WebElement heading_PartnerC_3_2_SettlementCurrency;
	
	@FindBy(xpath = "//div[contains(.,'Partner C.3.2 / Settlement Currency')]/div/button")
	private WebElement hideShow_PartnerC_3_2_SettlementCurrency;

	private By tab_PartnerC_3_2_SettlementCurrency=By.cssSelector("table[summary='Partner C.3.2 / Settlement Currency']");
	
	private void section_PartnerC_3_2_SettlementCurrency() {
		CustomReporter.report(STATUS.INFO, "Partner C.3.2 / Settlement Currency TAB ->>");
		com.isElementPresent(heading_PartnerC_3_2_SettlementCurrency, "Partner C.3.2 / Settlement Currency Tab heading");
		verifyHideShowFunc(false, hideShow_PartnerC_3_2_SettlementCurrency,"Partner C.3.2 / Settlement Currency Section");

		CustomReporter.report(STATUS.INFO, "Table Data ->>");
		com.click(hideShow_PartnerC_3_2_SettlementCurrency,"hideShow_Partner C.3.2 / Settlement Currency");
		com.isElementPresent(tab_PartnerC_3_2_SettlementCurrency, "Partner C.3.2 / Settlement Currency table");
		verifyTableColumnValues(tab_PartnerC_3_2_SettlementCurrency,"Partner",clientList,"Client Names as COL in 'Partner C.3.2 / Settlement Currency' tab");
		verifyTableRowValues(tab_PartnerC_3_2_SettlementCurrency,partnerList,"Partne Names as ROW in 'Partner C.3.2 / Settlement Currency' tab");
	}
}