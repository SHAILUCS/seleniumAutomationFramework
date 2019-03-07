package objectRepository.AgreementCapture.Rework.AgreementDetails;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;

public class Details_Tab_p15 extends IOTDiscountAgreementDetails_p15_Parent{
	public Details_Tab_p15() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(xpath = "//h2[.='Details']")
	private WebElement heading_Details;
	
	@FindBy(xpath = "//div[contains(.,'Details')]/div/button")
	private WebElement hideShow_Details;

	public void verify_UI() {
		super.verify_UI();
		CustomReporter.report(STATUS.INFO, "Details TAB ->>");
		openTab(tab_Details);
		com.isElementPresent(heading_Details, "Details Tab heading");
		verifyHideShowFunc(true, hideShow_Details,"Details Section");
		
		section_AgreementParties();
		section_AgreementTermsAndParameters();
		section_AdditionalParameters();
	}

	
	@FindBy(xpath = "//h2[.='Agreements Parties']")
	private WebElement heading_AgreementsParties;

	@FindBy(css = "ul[id$='_CLIENT_PARTIES_DISPLAY'] li")
	private List<WebElement> list_Client;
	
	@FindBy(css = "ul[id$='_PARTNER_PARTIES_DISPLAY'] li")
	private List<WebElement> list_Partner;
	
	@FindBy(xpath = "//div[contains(.,'Agreements Parties')]/div/button")
	private WebElement hideShow_AgreementsParties;
	
	@FindBy(xpath = "//label[.='Home Parties']")
	private WebElement label_HomeParties;

	@FindBy(xpath = "//label[.='Partner Parties']")
	private WebElement label_PartnerParties;
	
	private void section_AgreementParties() {
		CustomReporter.report(STATUS.INFO, "Agreements Parties Section");
		com.isElementPresent(heading_AgreementsParties, "Agreements Parties Section heading");
		verifyHideShowFunc(true, hideShow_AgreementsParties,"Agreements Parties Section");

		com.isElementPresent(label_HomeParties, "label_Home Parties");
		com.isElementPresent(label_PartnerParties, "label_Partner Parties");
	}
	
	
	@FindBy(xpath = "//h2[.='Agreement terms and parameters']")
	private WebElement heading_AgreementTermsAndParameters;
	
	@FindBy(xpath = "//div[contains(.,'Agreement terms and parameters')]/div/button")
	private WebElement hideShow_AgreementTermsAndParameters;
	
	@FindBy(xpath = "//label[.='Agreement Reference']")
	private WebElement label_AgreementReference;

	@FindBy(xpath = "//label[.='Agreement Start Date']")
	private WebElement label_AgreementStartDate;

	@FindBy(xpath = "//label[.='Agreement End Date']")
	private WebElement label_AgreementEndDate;
	
	@FindBy(xpath = "//label[.='Agreement Status']")
	private WebElement label_AgreementStatus;
	
	@FindBy(xpath = "//label[.='Discount Agreement Currency']")
	private WebElement label_DiscountAgreementCurrency;
	
	private void section_AgreementTermsAndParameters() {
		CustomReporter.report(STATUS.INFO, "Agreement terms and parameters Section");
		com.isElementPresent(heading_AgreementTermsAndParameters, "Agreement Terms And Parameters Section heading");
		verifyHideShowFunc(true, hideShow_AgreementTermsAndParameters,"Agreement Terms And Parameters Section ");

		com.isElementPresent(label_AgreementReference, "label_Agreement Reference");
		com.isElementPresent(label_AgreementStartDate, "label_Agreement Start Date");
		com.isElementPresent(label_AgreementEndDate, "label_Agreement End Date");
		com.isElementPresent(label_AgreementStatus, "label_Agreement Status");
		com.isElementPresent(label_DiscountAgreementCurrency, "label_Discount Agreement Currency");
	}
	

	@FindBy(xpath = "//h2[.='Additional Parameters']")
	private WebElement heading_AdditionalParameters;
	
	@FindBy(xpath = "//div[contains(.,'Additional Parameters')]/div/button")
	private WebElement hideShow_AdditionalParameters;
	
	@FindBy(xpath = "//label[.='Is Rolling Agreement']")
	private WebElement label_IsRollingAgreement;

	@FindBy(xpath = "//label[.='Include Satellite']")
	private WebElement label_IncludeSatellite;

	@FindBy(xpath = "//label[.='Premium Numbers at Discount Rate']")
	private WebElement label_PremiumNumbersAtDiscountRate;

	@FindBy(xpath = "//label[.='Do Not Calculate']")
	private WebElement label_DoNotCalculate;

	@FindBy(xpath = "//label[.='Agreement Type']")
	private WebElement label_AgreementType;

	@FindBy(xpath = "//label[.='Home Agreement Negotiator']")
	private WebElement label_HomeAgreementNegotiator;

	@FindBy(xpath = "//label[.='Partner Agreement Negotiator']")
	private WebElement label_PartnerAgreementNegotiator;

	@FindBy(xpath = "//label[.='Forecast Method']")
	private WebElement label_ForecastMethod;

	@FindBy(xpath = "//label[.='Applicable Law Country']")
	private WebElement label_ApplicableLawCountry;

	@FindBy(xpath = "//label[.='Termination Notice Period Days']")
	private WebElement label_TerminationNoticePeriodDays;

	@FindBy(xpath = "//label[.='Dispute Period (Days)']")
	private WebElement label_DisputePeriodDays;

	@FindBy(xpath = "//label[.='Renegotiation Notice Days']")
	private WebElement label_RenegotiationNoticeDays;

	@FindBy(xpath = "//label[.='EAE Date']")
	private WebElement label_EAEDate;

	@FindBy(xpath = "//label[.='Agreement Signed Date']")
	private WebElement label_AgreementSignedDate;

	@FindBy(xpath = "//label[.='Signature Status']")
	private WebElement label_SignatureStatus;

	@FindBy(xpath = "//label[.='Next Action']")
	private WebElement label_NextAction;
	
	private void section_AdditionalParameters() {
		CustomReporter.report(STATUS.INFO, "Additional Parameters Section");
		com.isElementPresent(heading_AdditionalParameters, "Additional Parameters Section heading");
		verifyHideShowFunc(true, hideShow_AdditionalParameters,"Additional Parameters Section ");

		com.isElementPresent(label_IsRollingAgreement, "label_Is Rolling Agreement");
		com.isElementPresent(label_IncludeSatellite, "label_Include Satellite");
		com.isElementPresent(label_PremiumNumbersAtDiscountRate, "label_Premium Numbers At Discount Rate");
		com.isElementPresent(label_DoNotCalculate, "label_Do Not Calculate");
		com.isElementPresent(label_AgreementType, "label_Agreement Type");
		com.isElementPresent(label_HomeAgreementNegotiator, "label_Home Agreement Negotiator");
		com.isElementPresent(label_PartnerAgreementNegotiator, "label_Partner Agreement Negotiator");
		com.isElementPresent(label_ForecastMethod, "label_Forecast Method");
		com.isElementPresent(label_ApplicableLawCountry, "label_Applicable Law Country");
		com.isElementPresent(label_TerminationNoticePeriodDays, "label_Termination Notice Period Days");
		com.isElementPresent(label_DisputePeriodDays, "label_Dispute Period (Days)");
		com.isElementPresent(label_RenegotiationNoticeDays, "label_Renegotiation Notice Days");
		com.isElementPresent(label_EAEDate, "label_EAE Date");
		com.isElementPresent(label_AgreementSignedDate, "label_Agreement Signed Date");
		com.isElementPresent(label_SignatureStatus, "label_Signature Status");
		com.isElementPresent(label_NextAction, "label_Next Action");
	}

	public List<String> getClientTadigList() {
		return getTadigNames(list_Client);
	}
	
	public List<String> getPartnerTadigList() {
		return getTadigNames(list_Partner);
	}
	
	private List<String> getTadigNames(List<WebElement> obj) {
		List<String> tempList=new ArrayList<String>();
		for (WebElement liElem : obj) {
			String tempStr=com.getText(liElem);
			tempStr=tempStr.substring(0, 5);
			tempList.add(tempStr);
		}
		return tempList;
	}
}
