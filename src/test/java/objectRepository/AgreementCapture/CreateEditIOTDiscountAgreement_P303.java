package objectRepository.AgreementCapture;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.jsonUtil.JSONManager;
import com.pdfUtil.PDFUtil;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;
import com.xlUtil.DataTable;

import objectRepository.common.ApexCommon;
import objectRepository.common.CalculationType;
import objectRepository.common.EventType;
import objectRepository.common.ServiceType;
import objectRepository.common.TrafficDirection;

public class CreateEditIOTDiscountAgreement_P303 {

	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title="Create / Edit IOT Discount Agreement";
	public CreateEditIOTDiscountAgreement_P303() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
	}

	@FindBy(id = "P303_CLIENT_AGREEMENT_REF")
	private WebElement text_AgreementRef;

	@FindBy(id = "P303_INCLUDE_SATELLITE_0")
	public WebElement checkbox_IncludeSatellite;

	@FindBy(css = "select[id*='DISC_AGRMT_CURR']")
	private WebElement select_DiscountAgreementCurrency;
	
	@FindBy(id = "P303_IOT_AGREEMENT_PSS_ID")
	private WebElement select_AgreementStatus;

	@FindBy(id = "P303_IOT_FORECAST_TYPE_ID")
	private WebElement select_ForecastMethod;

	@FindBy(xpath = "//button[contains(.,'Save New Discount Agreement')]")
	private WebElement button_SaveNewDiscountAgreement;

	@FindBy(id = "P303_CLIENT_PARTIES_RIGHT")
	private WebElement select_HomePartiesIncluded;
	
	@FindBy(id = "P303_CLIENT_PARTIES_LEFT")
	private WebElement select_HomePartiesExcluded;

	@FindBy(id = "P303_PARTNER_PARTIES_LEFT")
	private WebElement select_PartnerPartiesExcluded;
	
	@FindBy(id = "P303_PARTNER_PARTIES_RIGHT")
	private WebElement select_PartnerPartiesIncluded;

	@FindBy(xpath = "//button[contains(.,'Save Agreement Parties')]")
	private WebElement button_SaveAgreementParties;

	@FindBy(xpath = "//button[contains(.,'Add Discount Parameter')]")
	private WebElement button_AddDiscountParameter;

	@FindBy(xpath = "(//button[.='Save Discount Parameters'])[2]")
	private WebElement button_SaveDiscountParameters;
	
	@FindBy(id = "P303_SHOW_PMN_LEVEL_0")
	private WebElement checkbox_PMNLevelRates;
	
	@FindBy(xpath = "//button[.='Save Changes']")
	private WebElement button_SaveChanges;

	@FindBy(id = "P303_AGREEMENT_TYPE_ID_DISPLAY")
	private WebElement data_AgreementType;
	
	private By tab_HomeOperatorSettlementCurrency=By.xpath("//table[@summary='Home Operator C.3.2 / Settlement Currency Report']");

	private By tab_PartnerSettlementCurrency=By.xpath("//table[@summary='Partner C.3.2 / Settlement Currency Report']");

	@FindBy(xpath = "//button[contains(.,'Generate Settlement Statement')]")
	private WebElement button_GenerateSettlementStatement;

	@FindBy(xpath = "//button[contains(.,'Calculate Agreement')]")
	private WebElement button_CalculateAgreement;
	
	@FindBy(xpath = "//button[contains(.,'Delete Discount Agreement')]")
	private WebElement button_DeleteDiscountAgreement;

	@FindBy(css = "td[headers='FILENAME']")
	private List<WebElement> list_FileNames;

	@FindBy(css = "td[headers='IOT_AGREEMENT_DOC'] a")
	private List<WebElement> list_DownloadLinks;

	// TODO Copy Agreement Tab
	@FindBy(xpath = "//button[contains(.,'Copy Agreement')]")
	private WebElement button_CopyAgreement;

	@FindBy(id = "P303_COPY_AGREEMENT_REF")
	private WebElement text_NewAgreementRef;

	@FindBy(css = "input[id*='AGREEMENT_START_DATE']")
	private WebElement text_NewAgreementStartDate;

	@FindBy(css = "img[title='Popup Calendar: New Agreement Start Date']")
	private WebElement icon_NewAgreementStartDate;

	@FindBy(css = "input[id*='AGREEMENT_END_DATE']")
	private WebElement text_NewAgreementEndDate;

	@FindBy(css = "img[title='Popup Calendar: New Agreement End Date']")
	private WebElement icon_NewAgreementEndDate;

	@FindBy(id = "P303_COPY_PLMN_CODES_0")
	private WebElement checkbox_UseOriginalPLMNCodes;

	@FindBy(id = "P303_COPY_IOT_DISC_AGR_0")
	private WebElement checkbox_IOTDiscAgreement;

	@FindBy(id = "P303_COPY_DEAL_SUMMARY_0")
	private WebElement checkbox_DealSummary;

	@FindBy(id = "P303_COPY_DCH_DISCOUNT_FORM_0")
	private WebElement checkbox_DCHDiscountForm;

	@FindBy(id = "P303_COPY_SETTLEMENT_STATEMENT_0")
	private WebElement checkbox_SettlementStatement;

	@FindBy(id = "P303_COPY_ALL_DOCS_0")
	private WebElement checkbox_CopyAllDocs;

	@FindBy(xpath = "//button[contains(.,'Generate Term Sheet')]")
	private WebElement button_GenerateTermSheet;

	@FindBy(id = "P303_AGREEMENT_START_DATE")
	private WebElement text_StartDate;

	@FindBy(id = "P303_AGREEMENT_END_DATE")
	private WebElement text_EndDate;

	// TODO Calendar objects
	@FindBy(css = "img[title='Popup Calendar: * Agreement Start Date']")
	private WebElement icon_StartDateCal;

	@FindBy(css = "img[title='Popup Calendar: * Agreement End Date']")
	private WebElement icon_EndDateCal;

	@FindBy(css = "a[title='Prev']")
	private WebElement icon_Cal_Prev;

	@FindBy(css = "a[title='Next']")
	private WebElement icon_Cal_Next;

	@FindBy(css = "span[class='ui-datepicker-month']")
	private WebElement data_Cal_Month;

	@FindBy(css = "span[class='ui-datepicker-year']")
	private WebElement data_Cal_Year;

	private By tab_DiscountParameters=By.cssSelector("table[summary='Discount Parameters']");

	public void checkPMNLevelRatesCheckbox(){
		com.check_CheckboxAndConfirm(checkbox_PMNLevelRates,"PMN Level Rates");
		com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
	}
	
	public boolean calculateAgreement(String agreementName) {
		CustomReporter.createNode("Calculating Agreement "+agreementName);
		boolean calcDone=false;
		CustomReporter.report(STATUS.INFO, "<i>CLICKING ON CALCULATE AGREEMENT BUTTON</i>");
		navigateToTab("Create / Edit IOT Discount Agreement");
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_CalculateAgreement);

		if (com.verifyPageTitle(ManualCalculationSingleAgreement.title, true)) {
			ManualCalculationSingleAgreement manualCalculationSingleAgreement = new ManualCalculationSingleAgreement();
			calcDone=manualCalculationSingleAgreement.performCalculateAgreementProcess(agreementName);
		}
		
		
		if (!calcDone) {
			Assert.fail("Stopping the execution of this Test because calculation is not completed for agreement '"
					+ agreementName + "' after trying multiple times");
		}
		return calcDone;
	}

	public void verifyMissingCalculationResults_Validation_SettlementStatement() {
		CustomReporter.createNode("For missing calculation results, System should not generate Settlement Statement");
		navigateToTab("Settlement Statement");
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_GenerateSettlementStatement);
		String validation = "Unable to generate Settlement Statement due to missing calculation results.";
		if (com.waitForElementTobe_Visible(By.xpath("//li[.='" + validation + "']"), Constant.wait)) {
			CustomReporter.report(STATUS.PASS, "'" + validation + "' getting displayed");
		} else {
			CustomReporter.report(STATUS.FAIL, "'" + validation + "' is not displayed");
		}
	}

	public void verifySettlementStatementCurrencies(String clientReceivableCurrency, String partnerPayableCurrency) {
		navigateToTab("Settlement Statement");
		WebTable tab=new WebTable(tab_HomeOperatorSettlementCurrency);
		String homeSettlementCurr = tab.getCellText(2, 2);
		tab=new WebTable(tab_PartnerSettlementCurrency);
		String partnerSettlementCurr = tab.getCellText(2, 2);

		if (clientReceivableCurrency.toLowerCase().contains(homeSettlementCurr.toLowerCase())) {
			CustomReporter.report(STATUS.PASS, "For Client : Selected Currency type '" + clientReceivableCurrency
					+ "' on 'Edit/Update IOT Approve Addendum' page is correctly displayed on 'Settlement Statement' page");
		} else {
			CustomReporter.report(STATUS.FAIL,
					"For Client : Currency displayed on 'Settlement Statement' page is '" + homeSettlementCurr
					+ "' and on 'Edit/Update IOT Approve Addendum' page is '" + clientReceivableCurrency + "'");
		}

		if (partnerPayableCurrency.toLowerCase().contains(partnerSettlementCurr.toLowerCase())) {
			CustomReporter.report(STATUS.PASS, "For Partner : Selected Currency type '" + partnerPayableCurrency
					+ "' on 'Edit/Update IOT Approve Addendum' page is correctly displayed on 'Settlement Statement' page");
		} else {
			CustomReporter.report(STATUS.FAIL,
					"For Partner : Currency displayed on 'Settlement Statement' page is '" + partnerSettlementCurr
					+ "' and on 'Edit/Update IOT Approve Addendum' page is '" + partnerPayableCurrency + "'");
		}
	}
	public void createDiscAgreement(String agreementName, String clientName, String[] partnerPartiesExcluded,
			String startDate, String endDate,boolean checkIncludeSatelite) {
		CustomReporter.createNode("creating Disc Agreement "+agreementName);
		String message ="| agreementName: '" + agreementName + "' | startDate '"+startDate+"' | endDate '"+endDate+"'";
		com.sendKeys(text_AgreementRef, agreementName);
		com.sendKeys(text_StartDate,startDate);
		com.sendKeys(text_EndDate,endDate);
		/*selectDate(icon_StartDateCal, startDate);
		selectDate(icon_EndDateCal, endDate);*/
		if(checkIncludeSatelite){
			com.check_CheckboxAndConfirm(checkbox_IncludeSatellite, "Include Satellite");
			message = message +" | checkIncludeSatelite: "+checkIncludeSatelite;
		}
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_SaveNewDiscountAgreement);
		CustomReporter.report(STATUS.INFO, "Creating an New Discount Agreement with "+message);
		com.wait(1);
		
		if(navigateToTab("Agreement Parties")){
			String selectedHomeParty=com.getAllOptionsVisibleText(select_HomePartiesIncluded).toString();
			if(selectedHomeParty.contains(clientName)){
				CustomReporter.report(STATUS.INFO, "client '" + clientName+ "' is selected");
			}else{
				CustomReporter.report(STATUS.FAIL, "client '" + clientName + "' is NOT selected");
			}
			
			com.selectByPartialVisibleText_DoubleClick_FromArray(select_PartnerPartiesExcluded, partnerPartiesExcluded);
			CustomReporter.report(STATUS.INFO, "partner '" + Arrays.toString(partnerPartiesExcluded) + "' is selected");

			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_SaveAgreementParties);
		}
	}
	
	
	
	private String fillFields_AllParams(String agreementName, String[] clientName, 
			String[] partnerPartiesExcluded,String legalContractId,String startDate, 
			String endDate, String discCurrency,boolean isRolling_true,
			boolean includeSat_true,boolean premiumNoAtDiscRate_true, String agreementStatus,
			String agreementType,String homeAgrNego,String partnerAgrNego,String forecastMeth,
			String applicableCountry,String terminationDays,String disputeDays,String renegotiationDays,
			String agrSignedDate, String nextAction,boolean contOfFixedTerm_true
			){
		
		/*//TODO Method Caller HELPER
		createDiscAgreement_AllParams(agreementName, json.getStr("clientName"), json.getStrArr("partnerPartiesExcluded"),
				json.getStr("legalContractId"), json.getStr("startDate"), json.getStr("endDate"),
				json.getStr("discCurrency"), json.getBool("isRolling_true"), json.getBool("includeSat_true"),
				json.getBool("premiumNoAtDiscRate_true"), json.getStr("agreementStatus"), json.getStr("agreementType"),
				json.getStr("homeAgrNego"), json.getStr("partnerAgrNego"), json.getStr("forecastMeth"),
				json.getStr("applicableCountry"), json.getStr("terminationDays"), json.getStr("disputeDays"),
				json.getStr("renegotiationDays"), json.getStr("agrSignedDate"), json.getStr("nextAction"),
				json.getBool("contOfFixedTerm_true"));

		 * */
		
		/*//TODO JSON Object HELPER
		"clientName":"HKGTC", 
		"partnerPartiesExcluded":["AUSTA"],
		"legalContractId":null,
		"startDate":"01-Jan-2018", 
		"endDate":"31-Dec-2018", 
		"discCurrency":"AUD",
		"isRolling_true":false,
		"includeSat_true":false,
		"premiumNoAtDiscRate_true":false, 
		"agreementStatus":"Agreement Proposed",
		"agreementType":null,
		"homeAgrNego":null,
		"partnerAgrNego":null,
		"forecastMeth":null,
		"applicableCountry":null,
		"terminationDays":null,
		"disputeDays":null,
		"renegotiationDays":null,
		"agrSignedDate":null, 
		"nextAction":null,
		"contOfFixedTerm_true":false
		*/
		
		
		
		String message ="";
		
		if (agreementName != null) {
			com.sendKeys(text_AgreementRef, agreementName);
			message = message +" | agreementName: "+agreementName;
		}
		if (legalContractId != null) {
			CustomReporter.report(STATUS.WARNING, "legalContractId Object not added");
			message = message +" | legalContractId: "+legalContractId;
		}

		if (startDate != null) {
			com.sendKeys(com.getInteractableWebElementFromList(text_StartDate),startDate);
			message = message +" | startDate: "+startDate;
		} 

		if (endDate != null) {
			com.sendKeys(com.getInteractableWebElementFromList(text_EndDate),endDate);
			message = message +" | endDate: "+endDate;
		} 

		if (discCurrency != null) {
			com.selectByPartialVisibleText(select_DiscountAgreementCurrency, discCurrency);
			message = message +" | discCurrency: "+discCurrency;
		}

		if (isRolling_true) {
			CustomReporter.report(STATUS.WARNING, "isRolling Object is not added");
			message = message +" | isRolling_true: "+isRolling_true;
		}

		if (includeSat_true) {
			com.check_CheckboxAndConfirm(checkbox_IncludeSatellite, "Include Satellite");
			message = message +" | includeSat_true: "+includeSat_true;
		}

		if (premiumNoAtDiscRate_true) {
			CustomReporter.report(STATUS.WARNING, "premiumNoAtDiscRate object is disabled");
			message = message +" | premiumNoAtDiscRate_true: "+premiumNoAtDiscRate_true;
		} 

		if (agreementStatus != null) {
			com.selectByVisibleText(select_AgreementStatus, agreementStatus);
			message = message +" | agreementStatus: "+agreementStatus;
		}

		if (agreementType != null) {
			CustomReporter.report(STATUS.WARNING, "agreementType Object is not added");
			message = message +" | agreementType: "+agreementType;
		}

		if (homeAgrNego != null) {
			CustomReporter.report(STATUS.WARNING, "homeAgrNego Object is not added");
			message = message +" | homeAgrNego: "+homeAgrNego;
		}

		if (partnerAgrNego != null) {
			CustomReporter.report(STATUS.WARNING, "partnerAgrNego Object is not added");
			message = message +" | partnerAgrNego: "+partnerAgrNego;
		}
		
		if (forecastMeth != null) {
			CustomReporter.report(STATUS.WARNING, "forecastMeth Object is not added");
			message = message +" | forecastMeth: "+forecastMeth;
		}
		
		if (applicableCountry != null) {
			CustomReporter.report(STATUS.WARNING, "applicableCountry Object is not added");
			message = message +" | applicableCountry: "+applicableCountry;
		}

		if (terminationDays != null) {
			CustomReporter.report(STATUS.WARNING, "terminationDays Object is not added");
			message = message +" | terminationDays: "+terminationDays;
		}

		if (disputeDays != null) {
			CustomReporter.report(STATUS.WARNING, "disputeDays Object is not added");
			message = message +" | disputeDays: "+disputeDays;
		}

		if (renegotiationDays != null) {
			CustomReporter.report(STATUS.WARNING, "renegotiationDays Object is not added");
			message = message +" | renegotiationDays: "+renegotiationDays;
		}

		if (agrSignedDate != null) {
			CustomReporter.report(STATUS.WARNING, "agrSignedDate Object is not added");
			message = message +" | agrSignedDate: "+agrSignedDate;
		} 

		if (nextAction != null) {
			CustomReporter.report(STATUS.WARNING, "nextAction Object is not added");
			message = message +" | nextAction: "+nextAction;
		}
		
		if (contOfFixedTerm_true) {
			CustomReporter.report(STATUS.WARNING, "contOfFixedTerm_true Object is not added");
			message = message +" | contOfFixedTerm_true: "+contOfFixedTerm_true;
		}
		
		return message;
		
	}
	
	public void editDiscAgreement_AllParams(String agreementName, JSONManager json){
		editDiscAgreement_AllParams(agreementName, json.getStrArr("clientName"), json.getStrArr("partnerPartiesExcluded"),
				json.getStr("legalContractId"), json.getStr("startDate"), json.getStr("endDate"),
				json.getStr("discCurrency"), json.getBool("isRolling_true"), json.getBool("includeSat_true"),
				json.getBool("premiumNoAtDiscRate_true"), json.getStr("agreementStatus"), json.getStr("agreementType"),
				json.getStr("homeAgrNego"), json.getStr("partnerAgrNego"), json.getStr("forecastMeth"),
				json.getStr("applicableCountry"), json.getStr("terminationDays"), json.getStr("disputeDays"),
				json.getStr("renegotiationDays"), json.getStr("agrSignedDate"), json.getStr("nextAction"),
				json.getBool("contOfFixedTerm_true"));
	}
	
	public void editDiscAgreement_AllParams(String agreementName, String[] clientName, 
			String[] partnerPartiesExcluded,String legalContractId,String startDate, 
			String endDate, String discCurrency,boolean isRolling_true,
			boolean includeSat_true,boolean premiumNoAtDiscRate_true, String agreementStatus,
			String agreementType,String homeAgrNego,String partnerAgrNego,String forecastMeth,
			String applicableCountry,String terminationDays,String disputeDays,String renegotiationDays,
			String agrSignedDate, String nextAction,boolean contOfFixedTerm_true){
		
		CustomReporter.createNode("editing Disc Agreement "+agreementName);
		
		navigateToTab("Create / Edit IOT Discount Agreement");
		
		String message = fillFields_AllParams(agreementName, clientName, partnerPartiesExcluded, legalContractId,
				startDate, endDate, discCurrency, isRolling_true, includeSat_true, premiumNoAtDiscRate_true,
				agreementStatus, agreementType, homeAgrNego, partnerAgrNego, forecastMeth, applicableCountry,
				terminationDays, disputeDays, renegotiationDays, agrSignedDate, nextAction, contOfFixedTerm_true);
		
		
		com.click(com.getInteractableWebElementFromList(button_SaveChanges));
		CustomReporter.report(STATUS.INFO, "Editing Discount Agreement with "+message);
		com.wait(1);
		
		if (partnerPartiesExcluded!=null) {
			if(navigateToTab("Agreement Parties")){
				com.deselectAllValues_Shuttle_DoubleClick(select_PartnerPartiesIncluded);
				com.selectByPartialVisibleText_DoubleClick_FromArray(select_PartnerPartiesExcluded, partnerPartiesExcluded);
				CustomReporter.report(STATUS.INFO, "partner '" + Arrays.toString(partnerPartiesExcluded) + "' is selected");
			
				com.click(button_SaveAgreementParties);
			}
		}
		
	}
	
	public void createDiscAgreement_AllParams(String agreementName, JSONManager json){
		createDiscAgreement_AllParams(agreementName, json.getStrArr("clientName"), json.getStrArr("partnerPartiesExcluded"),
				json.getStr("legalContractId"), json.getStr("startDate"), json.getStr("endDate"),
				json.getStr("discCurrency"), json.getBool("isRolling_true"), json.getBool("includeSat_true"),
				json.getBool("premiumNoAtDiscRate_true"), json.getStr("agreementStatus"), json.getStr("agreementType"),
				json.getStr("homeAgrNego"), json.getStr("partnerAgrNego"), json.getStr("forecastMeth"),
				json.getStr("applicableCountry"), json.getStr("terminationDays"), json.getStr("disputeDays"),
				json.getStr("renegotiationDays"), json.getStr("agrSignedDate"), json.getStr("nextAction"),
				json.getBool("contOfFixedTerm_true"));
	}
	
	public void createDiscAgreement_AllParams(String agreementName, String[] clientNames, 
			String[] partnerPartiesExcluded,String legalContractId,String startDate, 
			String endDate, String discCurrency,boolean isRolling_true,
			boolean includeSat_true,boolean premiumNoAtDiscRate_true, String agreementStatus,
			String agreementType,String homeAgrNego,String partnerAgrNego,String forecastMeth,
			String applicableCountry,String terminationDays,String disputeDays,String renegotiationDays,
			String agrSignedDate, String nextAction,boolean contOfFixedTerm_true
			) {
		CustomReporter.createNode("creating Disc Agreement "+agreementName);
		
		String message = fillFields_AllParams(agreementName, clientNames, partnerPartiesExcluded, legalContractId,
				startDate, endDate, discCurrency, isRolling_true, includeSat_true, premiumNoAtDiscRate_true,
				agreementStatus, agreementType, homeAgrNego, partnerAgrNego, forecastMeth, applicableCountry,
				terminationDays, disputeDays, renegotiationDays, agrSignedDate, nextAction, contOfFixedTerm_true);
		
		
		com.click(button_SaveNewDiscountAgreement);
		CustomReporter.report(STATUS.INFO, "Creating an New Discount Agreement with "+message);
		com.wait(1);
		
		if(navigateToTab("Agreement Parties")){
			String selectedHomeParty=com.getAllOptionsVisibleText(select_HomePartiesIncluded).toString();
			
			// In case we want to select multiple home operators, 
			// then array will have more than 1 elements
			// then we will select all the desired TADIG from the shuttle
			// using below if logic
			if(clientNames.length>1){
				List<String> tempList=Arrays.asList(clientNames);
				String[] newList=new String[clientNames.length-1];
				int index=0;
				for (int i = 0; i < tempList.size(); i++) {
					if(selectedHomeParty.contains(tempList.get(i))){
						CustomReporter.report(STATUS.INFO, "client '" + tempList.get(i)+ "' is already selected");
					}else {
						newList[index++]=tempList.get(i);
					}
				}
				CustomReporter.report(STATUS.INFO, "Selecting the rest of clients '" + Arrays.toString(newList)+ "'");
				com.selectByPartialVisibleText_DoubleClick_FromArray(select_HomePartiesExcluded, newList);
			}else{
				if(selectedHomeParty.contains(clientNames[0])){
					CustomReporter.report(STATUS.INFO, "client '" + Arrays.toString(clientNames)+ "' is selected");
				}else{
					CustomReporter.report(STATUS.FAIL, "client '" + Arrays.toString(clientNames) + "' is NOT selected");
				}
			}
			com.selectByPartialVisibleText_DoubleClick_FromArray(select_PartnerPartiesExcluded, partnerPartiesExcluded);
			CustomReporter.report(STATUS.INFO, "partner '" + Arrays.toString(partnerPartiesExcluded) + "' is selected");

			com.click(button_SaveAgreementParties);
		}
	}
	
	public void agreementTypeCheck(String expectedAgreementType) {
		navigateToTab("Create / Edit IOT Discount Agreement");
		if (com.getText(data_AgreementType).contains(expectedAgreementType)) {
			CustomReporter.report(STATUS.PASS, "'" + expectedAgreementType + "' displayed in Agreement Type");
		} else {
			CustomReporter.report(STATUS.FAIL,
					"'" + expectedAgreementType + "' is NOT displayed in Agreement Type instead : '"
							+ com.getText(data_AgreementType) + "' is displayed");
		}
	}

	public void updateDiscountParameters(int row, String discDir, String servType, String evType, String calcType, String discBasis,
			String discBasisVal,String ChargingIncrementUsedInCalculationVal) {
		String message="";
		navigateToTab("Discount Parameters");
		WebTable tab=new WebTable(tab_DiscountParameters);
		tab.initHeaderColumnList(1);
		int col=0;
		if(discDir!=null){
			col=tab.getColumnNumber("Discount Direction");
			com.selectByVisibleText(tab.getChildObject(row, col, "select", 0),discDir);
			message=message+" | discDir: "+discDir;
		}

		if(servType!=null){
			col=tab.getColumnNumber("Service Type");
			com.selectByVisibleText(tab.getChildObject(row, col, "select", 0),servType);
			message=message+" | servType: "+servType;
		}
		if(evType!=null){
			col=tab.getColumnNumber("Event Type");
			com.selectByVisibleText(tab.getChildObject(row, col, "select", 0),evType);
			message=message+" | evType: "+evType;
		}
		if(calcType!=null){
			col=tab.getColumnNumber("Calculation Type");
			com.selectByPartialVisibleText(tab.getChildObject(row, col, "select", 0),calcType);
			message=message+" | calcType: "+calcType;
		}
		if(discBasis!=null){
			col=tab.getColumnNumber("Discount Basis");
			com.selectByVisibleText(tab.getChildObject(row, col, "select", 0),discBasis);
			message=message+" | discBasis: "+discBasis;
		}
		if(discBasisVal!=null){
			col=tab.getColumnNumber("Discount Basis Value");
			com.sendKeys(tab.getChildObject(row, col, "input", 0),discBasisVal);
			message=message+" | discBasisVal: "+discBasisVal;
		}
		if(ChargingIncrementUsedInCalculationVal!=null){
			col=tab.getColumnNumber("Charging Increment used in Calculation");
			com.selectByVisibleText(tab.getChildObject(row, col, "select", 0),ChargingIncrementUsedInCalculationVal);
			message=message+" | ChargingIncrementUsedInCalculationVal: "+ChargingIncrementUsedInCalculationVal;
		}

		CustomReporter.report(STATUS.INFO,
				"Updating discount parameters "+message);

		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_SaveDiscountParameters);

	}
	
	public void addDiscountParameters(TrafficDirection customeroutbound, ServiceType voice, EventType mo,
			CalculationType calcType, String discBasis, String oB_discRate,
			String chargingIncrementUsedInCalculationVal,
			String BoundType,String LowerBound) {
		addDiscountParameters(customeroutbound.value, voice.value, mo.value, calcType.value, discBasis, oB_discRate, chargingIncrementUsedInCalculationVal,BoundType,LowerBound);
	}


	
	/**
	 * @author shailendra.rajawat
	 * <pre>
	 * This method is responsible to modify the Home & Partner Operators, for
	 * a specific Row of discount agreement
	 * 
	 * Precon: 
	 * 	1. Page 303 should be displayed
	 * 	2. "PMN Level Rates" checkbox should be checked
	 * 	3. Discount Parameters tab should have atleast one row
	 * Postcon:
	 * 	1. Method will click configure on passed row, then save 
	 * 		the modifications on P633.
	 * 	2. Page 303 will get displayed
	 * 
	 * @param rowNum Row on which the PMNs needs to be configured
	 * @param homeOperatorArr String Array of Home Operators that needs to be selected, send <code>null</code> if you dont want to change existing value
	 * @param partnerOperatorArr String Array of Partner Operators that needs to be selected, send <code>null</code> if you dont want to change existing value 
	 * </pre>
	 * */
	public void configurePMNs(int rowNum, JSONManager json) {
		configurePMNs(rowNum, json.getStrArr("homeOp"), json.getStrArr("partnerOp"));
	}
	public void configurePMNs(int rowNum, String[] homeOperatorArr, String[] partnerOperatorArr) {
		CustomReporter.createNode("configuring the PMNs for row "+rowNum);
		
		navigateToTab("Discount Parameters");
		
		// To make it failsafe First verifying "PMN Level Rates" checkbox is checked
		if(!com.isSelected(checkbox_PMNLevelRates)){
			checkPMNLevelRatesCheckbox();
		}
	
		
		WebTable tab = new WebTable(tab_DiscountParameters);
		tab.initHeaderColumnList(1);
		int lastRow = tab.getRowCount();
		if(rowNum<=lastRow){
			int colConfigure=tab.getColumnNumber("Configure PMNs");
			com.click(tab.getChildObject(rowNum, colConfigure, "a", 0), "Configure Link");
			com.verifyPageTitle(ConfigurePMNs_P633.title);
			ConfigurePMNs_P633 p633=new ConfigurePMNs_P633();
			p633.updateConfiguration(homeOperatorArr,partnerOperatorArr);
		}else{
			CustomReporter.report(STATUS.FAIL, "Passed rowNum "+rowNum+" exceeds the total row count "+lastRow);
		}
	}
	
	public void addDiscountParameters(int jsonArrIndex,JSONManager json) {
		addDiscountParameters(
				json.getStr("DiscParam", jsonArrIndex, "TrafficDirection"),
				json.getStr("DiscParam", jsonArrIndex, "ServiceType"),
				json.getStr("DiscParam", jsonArrIndex, "EventType"),
				json.getStr("DiscParam", jsonArrIndex, "CalculationType"),
				json.getStr("DiscParam", jsonArrIndex, "discBasis"),
				json.getStr("DiscParam", jsonArrIndex, "discBasisVal"),
				json.getStr("DiscParam", jsonArrIndex, "ChargingIncrementUsedInCalculationVal"),
				(json.containsKey("DiscParam", jsonArrIndex, "BoundType")?json.getStr("DiscParam", jsonArrIndex, "BoundType"):null),
				(json.containsKey("DiscParam", jsonArrIndex, "LowerBound")?json.getStr("DiscParam", jsonArrIndex, "LowerBound"):null));
	}
	
	public void addDiscountParameters(String discDir, String servType, String evType, String calcType, String discBasis,
			String discBasisVal,String ChargingIncrementUsedInCalculationVal,String BoundType,String LowerBound) {
		CustomReporter.createNode("Adding Discount Parameters");
		String message="";
		navigateToTab("Discount Parameters");
		WebTable tab = new WebTable(tab_DiscountParameters);
		tab.initHeaderColumnList(1);
		int oldRowNum = tab.getRowCount();
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_AddDiscountParameter);
		com.wait(1);
		int newRowNum = tab.getRowCount();
		int col=0;
		if (newRowNum > oldRowNum) {
			if(discDir!=null){
				col=tab.getColumnNumber("Discount Direction");
				com.selectByVisibleText(tab.getChildObject(newRowNum, col, "select", 0),discDir);
				message=message+" | discDir: "+discDir;
			}

			if(servType!=null){
				col=tab.getColumnNumber("Service Type");
				com.selectByVisibleText(tab.getChildObject(newRowNum, col, "select", 0),servType);
				message=message+" | servType: "+servType;
			}
			if(evType!=null){
				col=tab.getColumnNumber("Event Type");
				com.selectByVisibleText(tab.getChildObject(newRowNum, col, "select", 0),evType);
				message=message+" | evType: "+evType;
			}
			if(calcType!=null){
				col=tab.getColumnNumber("Calculation Type");
				com.selectByPartialVisibleText(tab.getChildObject(newRowNum, col, "select", 0),calcType);
				message=message+" | calcType: "+calcType;
			}
			if(discBasis!=null){
				col=tab.getColumnNumber("Discount Basis");
				com.selectByVisibleText(tab.getChildObject(newRowNum, col, "select", 0),discBasis);
				message=message+" | discBasis: "+discBasis;
			}
			if(discBasisVal!=null){
				col=tab.getColumnNumber("Discount Basis Value");
				com.sendKeys(tab.getChildObject(newRowNum, col, "input", 0),discBasisVal);
				message=message+" | discBasisVal: "+discBasisVal;
			}
			
			if(ChargingIncrementUsedInCalculationVal!=null){
				col=tab.getColumnNumber("Charging Increment used in Calculation");
				com.selectByVisibleText(tab.getChildObject(newRowNum, col, "select", 0),ChargingIncrementUsedInCalculationVal);
				message=message+" | ChargingIncrementUsedInCalculationVal: "+ChargingIncrementUsedInCalculationVal;
			}

			if(BoundType!=null){
				col=tab.getColumnNumber("Bound Type");
				com.selectByVisibleText(tab.getChildObject(newRowNum, col, "select", 0),BoundType);
				message=message+" | BoundType: "+BoundType;
			}

			if(LowerBound!=null){
				col=tab.getColumnNumber("Lower Bound");
				com.sendKeys(tab.getChildObject(newRowNum, col, "input", 0),LowerBound);
				message=message+" | LowerBound: "+LowerBound;
			}

			CustomReporter.report(STATUS.INFO,
					"Selected discount parameters "+ message);

		}
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_SaveDiscountParameters);
	}
	
	public void verifyHomeCurrency(String homeCurrency_CreateIOTClient) {
		String pageVal = com.getFirstSelectedOptionVisibleText(select_DiscountAgreementCurrency);
		if (pageVal.trim().equalsIgnoreCase(homeCurrency_CreateIOTClient.trim())) {
			CustomReporter.report(STATUS.PASS, "Selected Currency '" + homeCurrency_CreateIOTClient
					+ "' on 'Create IOT Client' page is correctly displayed on 'Create IOT Discount Agreement' page");
		} else {
			CustomReporter.report(STATUS.FAIL,
					"Currency displayed on 'Create IOT Client' page is '" + homeCurrency_CreateIOTClient
					+ "' and on 'Create IOT Discount Agreement' page is '" + pageVal + "'");
		}
	}

	public void verifyForecastMethod(String defForecastMeth_CreateIOTClient) {
		String pageVal = com.getFirstSelectedOptionVisibleText(select_ForecastMethod);
		if (pageVal.trim().equalsIgnoreCase(defForecastMeth_CreateIOTClient.trim())) {
			CustomReporter.report(STATUS.PASS, "Selected Forecast Method '" + defForecastMeth_CreateIOTClient
					+ "' on 'Create IOT Client' page is correctly displayed on 'Create IOT Discount Agreement' page");
		} else {
			CustomReporter.report(STATUS.FAIL,
					"Forecast Method displayed on 'Create IOT Client' page is '" + defForecastMeth_CreateIOTClient
					+ "' and on 'Create IOT Discount Agreement' page is '" + pageVal + "'");
		}

	}

	private void selectDate(WebElement calObj, String date) {
		int day = Integer.parseInt(date.substring(0, 2));
		String month = date.substring(3, 6);
		int year = Integer.parseInt(date.substring(7, date.length()));
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(calObj);
		if (com.waitForElementTobe_Visible(icon_Cal_Prev, 5)) {
			int yearVal = Integer.parseInt(com.getText(data_Cal_Year));

			if (year < yearVal) {
				// going past
				do {
					com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(icon_Cal_Prev);
				} while (com.waitForElementTobe_NotVisible(By.xpath("(//td[@data-year='" + year + "'])[1]"), 0));
			} else if (year > yearVal) {
				// going future
				do {
					com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(icon_Cal_Next);
				} while (com.waitForElementTobe_NotVisible(By.xpath("(//td[@data-year='" + year + "'])[1]"), 0));
			}

			int monthVal = getMonth(month);
			int monthPage = Integer.parseInt(com.getAttribute(
					com.getDynamicElement(By.xpath("(//td[@data-year='" + year + "'])[1]")), "data-month"));
			if (monthVal < monthPage) {
				// going past
				do {
					com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(icon_Cal_Prev);
				} while (com.waitForElementTobe_NotVisible(By.xpath("(//td[@data-month='" + monthVal + "'])[1]"), 0));
			} else if (monthVal > monthPage) {
				// going future
				do {
					com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(icon_Cal_Next);
				} while (com.waitForElementTobe_NotVisible(By.xpath("(//td[@data-month='" + monthVal + "'])[1]"), 0));
			}

			// Select the date
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(com.getDynamicElement(By.xpath("//a[.='" + day + "']")));
			com.wait(1);
		}
	}

	private int getMonth(String monthStr) {
		int mon = -1;
		if (monthStr.toLowerCase().contains("jan")) {
			mon = 0;
		} else if (monthStr.toLowerCase().contains("feb")) {
			mon = 1;
		} else if (monthStr.toLowerCase().contains("mar")) {
			mon = 2;
		} else if (monthStr.toLowerCase().contains("apr")) {
			mon = 3;
		} else if (monthStr.toLowerCase().contains("may")) {
			mon = 4;
		} else if (monthStr.toLowerCase().contains("jun")) {
			mon = 5;
		} else if (monthStr.toLowerCase().contains("jul")) {
			mon = 6;
		} else if (monthStr.toLowerCase().contains("aug")) {
			mon = 7;
		} else if (monthStr.toLowerCase().contains("sep")) {
			mon = 8;
		} else if (monthStr.toLowerCase().contains("oct")) {
			mon = 9;
		} else if (monthStr.toLowerCase().contains("nov")) {
			mon = 10;
		} else if (monthStr.toLowerCase().contains("dec")) {
			mon = 11;
		}
		return mon;
	}

	public boolean navigateToTab(String tabName) {
		boolean bool = false;
		com.wait(1);
		WebElement tabLink = com.getDynamicElement(By.xpath("//span[.='" + tabName + "']"));
		// if(com.isElementPresent(tabLink,"link for Tab '"+tabName+"'")){
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(tabLink);
		if (com.waitForElementTobe_Visible(
				com.getDynamicElement(By.xpath("//h2[contains(@class,'Region')][.='" + tabName + "']")), 30)) {
			CustomReporter.report(STATUS.PASS, "Tab '" + tabName + "' opened");
			bool = true;
		} else {
			CustomReporter.report(STATUS.FAIL, "Tab '" + tabName + "' is not opened");
		}
		// }
		return bool;
	}

	public String getColumnValue(String discDirection, String servType, String eveType, String opColName) {
		String colValue = "0";
		String colHeader = "";
		switch (opColName) {
		case "Discount Basis Value":
			colHeader = "Discount Basis Value";
			break;
		case "Lower Bound":
			colHeader = "Lower Bound";
			break;
		}
		int row = getRowNum(discDirection, servType, eveType);
		WebElement Obj = com.getDynamicElement(
				By.cssSelector("table[id='report_rebateparameters'] table[class*='Standard'] tr:nth-child(" + row
						+ ") td[headers='" + colHeader + "'] input"));
		String temp = com.getAttribute(Obj, "value");
		if (!temp.equals("")) {
			colValue = temp;
		}
		CustomReporter.report(STATUS.INFO, "For '" + discDirection + "',  '" + servType + "', '" + eveType + "' '"
				+ opColName + "' value is : '" + colValue + "'");

		return colValue;
	}

	private int getRowNum(String discDirection, String servType, String eveType) {
		int rowNum = 0;
		List<WebElement> rowList = com
				.getDynamicElements(By.cssSelector("table[id='report_rebateparameters'] table[class*='Standard'] tr"));
		int rowCount = rowList.size();

		for (int row = 2; row <= rowCount; row++) {
			WebElement discountDirection = com.getDynamicElement(
					By.cssSelector("table[id='report_rebateparameters'] table[class*='Standard'] tr:nth-child(" + row
							+ ") td[headers='Discount Direction'] select"));
			String discountDirectionVal = com.getText(com.getFirstSelectedOption(discountDirection));

			if (discountDirectionVal.toLowerCase().contains(discDirection.toLowerCase())) {
				WebElement serviceType = com.getDynamicElement(
						By.cssSelector("table[id='report_rebateparameters'] table[class*='Standard'] tr:nth-child("
								+ row + ") td[headers='Service Type'] select"));
				String serviceTypeVal = com.getText(com.getFirstSelectedOption(serviceType));

				if (serviceTypeVal.toLowerCase().contains(servType.toLowerCase())) {
					WebElement eventType = com.getDynamicElement(
							By.cssSelector("table[id='report_rebateparameters'] table[class*='Standard'] tr:nth-child("
									+ row + ") td[headers='Event Type'] select"));
					String eventTypeVal = com.getText(com.getFirstSelectedOption(eventType));
					if (eventTypeVal.toLowerCase().contains(eveType.toLowerCase())) {
						rowNum = row;
						break;
					}
				}
			}

		}
		return rowNum;
	}

	public void generateSettlementStatement_And_VerifyXls(String agreementName, String client, String partner) {
		CustomReporter.createNode("Generate Settlement Statement And Verify Xls");
		
		navigateToTab("Settlement Statement");
		CustomReporter.report(STATUS.INFO,
				"Verifying the settlement statement xls file data and download funtionality");
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_GenerateSettlementStatement);
		String fileName = "Settlement_" + agreementName;
		String filePath = Util.getDownloadedFilePath(fileName);
		if (filePath != null) {
			CustomReporter.report(STATUS.PASS, "Settlement Statement file name:'" + fileName + "' downloaded");
			verifyDataInSettlementStatementXls(filePath, agreementName, client, partner);
			Util.forceDelete(filePath);
		} else {
			CustomReporter.report(STATUS.FAIL, "Settlement Statement file name:'" + fileName + "' not downloaded");
		}
	}

	private void verifyDataInSettlementStatementXls(String filePath, String agreementName, String client,
			String partner) {
		
		DataTable dataTable=new DataTable(filePath, 0);
		
		String agreementNameXls = dataTable.getValue(1, 5);
		if (agreementNameXls.toLowerCase().contains(agreementName.toLowerCase())) {
			CustomReporter.report(STATUS.PASS, "Agreement Name '" + agreementNameXls + "' displayed in F2 cell");
		} else {
			CustomReporter.report(STATUS.FAIL,
					"Agreement Name '" + agreementNameXls + "' is NOT displayed in F2 cell");
		}

		verifyClientPartnerRowsData(dataTable, 6, 9, 1, client, "Customer/Outbound B7:B10");
		verifyClientPartnerRowsData(dataTable, 6, 9, 2, partner, "Customer/Outbound C7:C10");
		verifyClientPartnerRowsData(dataTable, 22, 25, 1, partner, "Visitor/Inbound B23:B26");
		verifyClientPartnerRowsData(dataTable, 22, 25, 2, client, "Visitor/Inbound C23:C26");

	}

	private void verifyClientPartnerRowsData(DataTable dataTable, int startRow, int endRow, int colNum,
			String clientPartner, String cells) {
		boolean flag = true;
		for (int row = startRow; row <= endRow; row++) {
			String dataXls = dataTable.getValue(row, colNum);
			if (!clientPartner.toLowerCase().contains(dataXls.toLowerCase())) {
				flag = false;
				break;
			}
		}
		if (flag) {
			CustomReporter.report(STATUS.PASS, "'" + clientPartner + "' displayed properly in " + cells + " cells");
		} else {
			CustomReporter.report(STATUS.FAIL, "'" + clientPartner + "' is NOT displayed in " + cells + " cells");
		}
	}

	public void verifySettlementStatementStatusInDocumentLibrary(String agreementName) {
		
		CustomReporter.createNode("Verify Settlement Statement Status In Document Library Tab");
		
		navigateToTab("Document Library");
		
		int counter = 1;
		String expectedStatus = "Settlement Statement";
		String actualStatus = null;
		do {
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(comm.button_Go);
			com.wait(5);
			if (!com.waitForElementTobe_NotVisible(By.xpath("//td[contains(.,'Settlement_"+ agreementName + "')]"),10)) {
				actualStatus = com.getText(By.xpath("//td[@headers='DOCUMENT_TYPE']"));
				break;
			}
			counter++;
			if (counter == 5) {
				break;
			}
		} while (true);

		if (expectedStatus.equalsIgnoreCase(actualStatus)) {
			CustomReporter.report(STATUS.PASS,
					"For Downloading a Settlement statement file with name: '"
							+ com.getText(
									By.xpath("//td[@headers='FILENAME']"))
							+ "' got displayed in the Document Library table after trying " + counter
							+ " times with 5 sec wait");
		} else {
			CustomReporter.report(STATUS.FAIL, "Tried " + counter
					+ " times with 5 sec wait but Settlement statement file is not displayed in the Document Library table");
		}

	}

	public String performCopyDiscountAgreement() {
		CustomReporter.report(STATUS.INFO, "Verifying the 'Copy Discount Agreement' feature");
		String agreementName = "Copy_AutomationTesting" + Util.getTimeStamp_InMilliSec();
		navigateToTab("Copy Discount Agreement");
		com.sendKeys("New Agreement Reference", text_NewAgreementRef, agreementName);

		text_NewAgreementStartDate=com.getInteractableWebElementFromList(text_NewAgreementStartDate);
		String oldStartDate = com.getAttribute(text_NewAgreementStartDate, "value");
		String newStartDate = oldStartDate.substring(0, 2) + "-" + oldStartDate.substring(3, 6) + "-"
				+ (Integer.parseInt(oldStartDate.substring(oldStartDate.length() - 4)) - 1) + "";
		com.sendKeys(text_NewAgreementStartDate, newStartDate);
		com.checkBlank(text_NewAgreementStartDate, "New Agreement Start Date");

		text_NewAgreementEndDate=com.getInteractableWebElementFromList(text_NewAgreementEndDate);
		String oldEndDate = com.getAttribute(text_NewAgreementEndDate, "value");
		String newEndDate = oldEndDate.substring(0, 2) + "-" + oldEndDate.substring(3, 6) + "-"
				+ (Integer.parseInt(oldEndDate.substring(oldEndDate.length() - 4)) - 1) + "";
		com.sendKeys(text_NewAgreementEndDate, newEndDate);
		com.checkBlank(text_NewAgreementEndDate, "New Agreement End Date");

		com.check_CheckboxAndConfirm(checkbox_UseOriginalPLMNCodes, "Use Original PLMN Codes");
		com.check_CheckboxAndConfirm(checkbox_IOTDiscAgreement, "IOT Disc Agreement");
		com.check_CheckboxAndConfirm(checkbox_DealSummary, "DealSummary");
		com.check_CheckboxAndConfirm(checkbox_DCHDiscountForm, "DCH Discount Form");
		com.check_CheckboxAndConfirm(checkbox_SettlementStatement, "Settlement Statement");
		com.check_CheckboxAndConfirm(checkbox_CopyAllDocs, "Copy All Docs");

		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_CopyAgreement);

		if (com.waitForElementTobe_Visible(By.xpath("//h2[contains(.,'Agreement Copied')]"), Constant.wait)) {
			CustomReporter.report(STATUS.PASS, "'Agreement Copied' success message displayed");
		} else {
			CustomReporter.report(STATUS.FAIL, "'Agreement Copied' success message NOT displayed");
		}

		return agreementName;

	}

	public void deleteAgreement() {
		CustomReporter.createNode("DELETING AGREEMENT");
		navigateToTab("Create / Edit IOT Discount Agreement");
		com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_DeleteDiscountAgreement);
		com.click(comm.button_OK, "OK button on confirmation popup");
		if (com.waitForElementTobe_Visible(By.xpath("//h2[contains(.,'Deleted IOT Discount Agreement')]"))) {
			CustomReporter.report(STATUS.PASS, "Agreement Deleted successfully");
		} else {
			CustomReporter.report(STATUS.FAIL, "Agreement not Deleted");
		}
	}

	public String verifyGenerateTermSheetButtonFunctionality() {
		String filePath = null;
		CustomReporter.createNode("Verifying the Generate Term Sheet button Functionality");
		navigateToTab("Document Library");
		int countBefore = list_FileNames.size();
		navigateToTab("Create / Edit IOT Discount Agreement");
		if (com.waitForElementTobe_Visible(button_GenerateTermSheet, "Generate Term Sheet button")) {
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(button_GenerateTermSheet, "Generate Term Sheet button");
			if (com.waitForElementTobe_Visible(
					By.xpath("//h2[.='Term Sheet generated - This will be saved in the document library']"),
					"'Term Sheet generated - This will be saved in the document library'")) {
				navigateToTab("Document Library");
				int countAfter = list_FileNames.size();
				if (countAfter > countBefore) {
					com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(list_DownloadLinks.get(0), "First row Download link");
					String fileName = com.getText(list_FileNames.get(0));
					filePath = Util.getDownloadedFilePath(fileName);
					CustomReporter.createNode("Verifying the Term Sheet pdf file data and download funtionality");
					if (filePath != null) {
						CustomReporter.report(STATUS.PASS, "Term Sheet pdf file name:'" + fileName + "' downloaded");
					} else {
						CustomReporter.report(STATUS.FAIL,
								"Term Sheet pdf file name:'" + fileName + "' not downloaded");
					}
				} else {
					CustomReporter.report(STATUS.FAIL, "Term Sheet document is not added in Document Library Tab");
				}

			}
		}
		return filePath;
	}

	private void matchData(String actual, String expected, String desc) {
		if (actual.toLowerCase().contains(expected.toLowerCase())) {
			CustomReporter.report(STATUS.PASS, desc + " '" + expected + "' is displayed");
		} else {
			CustomReporter.report(STATUS.FAIL, desc + " '" + expected + "' is NOT displayed");
		}
	}

	public void verifyOutboundInbound_DataInTermSheetPdf(String filePath, String discDir, String servType,
			String evType, String calcType, String discBasis, String discBasisVal) {
		CustomReporter.createNode( "Verifying the "+discDir+" data in Term Sheet Pdf");
		String pdfData = PDFUtil.getText(filePath);

		String start = discDir;
		String end = null;
		if(discDir.contains("Visitor/Inbound")){
			end ="Page 1 of 1";
		}else{
			end ="Visitor/Inbound";
		}

		if (pdfData.contains(start) && pdfData.contains(end)) {
			int startIndex = pdfData.indexOf(start) + start.length();
			int endIndex = pdfData.indexOf(end);
			String subString = pdfData.substring(startIndex, endIndex);
			matchData(subString, servType+" "+evType, "Service Type and Event Type");
			matchData(subString, discBasis+" "+discBasisVal, "Value and Discount Rate");

			String[] calcTypeArr = calcType.split(" ");
			int counter = 0;
			for (String temp : calcTypeArr) {
				if (subString.toLowerCase().contains(temp.toLowerCase())) {
					counter++;
				}
			}
			if (counter == calcTypeArr.length) {
				CustomReporter.report(STATUS.PASS, "Calculation type '" + calcType + "' is displayed");
			} else {
				CustomReporter.report(STATUS.FAIL, "Calculation type '" + calcType + "' is NOT displayed");
			}

		} else {
			CustomReporter.report(STATUS.FAIL, "Pdf does not have'" + start + "' and '" + end + "'");
		}
	}

	public void verifyDiscAgreement_DataInTermSheetPdf(String filePath, String agreementName, String clientName,
			String[] partner, String startDate, String endDate) {
		CustomReporter.createNode("Verifying the Agreement Ref, Start and End Date data in Term Sheet Pdf");
		String pdfData = PDFUtil.getText(filePath);

		String start = "Discount Agreement Reference";
		String end = "Customer/Outbound";
		if (pdfData.contains(start) && pdfData.contains(end)) {
			int startIndex = pdfData.indexOf(start) + start.length();
			int endIndex = pdfData.indexOf(end);
			String subString = pdfData.substring(startIndex, endIndex);
			matchData(subString, agreementName, "Agreement Name");
			matchData(subString, startDate, "Start Date");
			matchData(subString, endDate, "End Date");

			start = "Parties";
			startIndex = pdfData.indexOf(start) + start.length();
			endIndex = startIndex + 6;
			subString = pdfData.substring(startIndex, endIndex).trim();
			matchData(clientName, subString, "Client PMN");

			startIndex = pdfData.indexOf(start, endIndex + 1) + start.length();
			endIndex = startIndex + 6;
			subString = pdfData.substring(startIndex, endIndex).trim();
			matchData(partner[0], subString, "Partner PMN");

		} else {
			CustomReporter.report(STATUS.FAIL, "Pdf does NOT have'" + start + "' and '" + end + "'");
		}
	}


}
