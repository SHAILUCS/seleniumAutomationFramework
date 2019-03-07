package objectRepository.CustomisedReportsTMO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.driverManager.DriverFactory;

public class IotronUsInstance {
	public static String title = "IOTRON US Instance";

	public IotronUsInstance() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	@FindBy(partialLinkText = "IOTRON US Instance")
	public WebElement link_IotronUsInstance;
	
	@FindBy(partialLinkText = "TAP Accrual, Actual & True Up")
	public WebElement link_TAPAccrualActualAndTrueUp;

	@FindBy(xpath = "//button[.='Monthly Discount']")
	public WebElement link_MonthlyDiscount;
	
	@FindBy(xpath = "//button[.='Monthly Discount Accrual']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountAccrual;

	@FindBy(xpath = "//button[.='Monthly Discount Actual']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountActual;
	
	@FindBy(xpath = "//a[.='Discount Report']")
	public WebElement link_MonthlyDiscount_DiscountReport;
	
	@FindBy(xpath = "//a[.='IB Accrual Total']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountAccrual_IBAccrualTotal;
	
	@FindBy(xpath = "//a[.='IB Accrual Voice']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountAccrual_IBAccrualVoice;

	@FindBy(xpath = "//a[.='IB Accrual SMS']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountAccrual_IBAccrualSMS;

	@FindBy(xpath = "//a[.='IB Accrual Data']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountAccrual_IBAccrualData;

	@FindBy(xpath = "//a[.='OB Accrual Total']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountAccrual_OBAccrualTotal;
	
	@FindBy(xpath = "//a[.='OB Accrual Voice']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountAccrual_OBAccrualVoice;

	@FindBy(xpath = "//a[.='OB Accrual SMS']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountAccrual_OBAccrualSMS;

	@FindBy(xpath = "//a[.='OB Accrual Data']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountAccrual_OBAccrualData;
	
	@FindBy(xpath = "//a[.='IB Actual Total']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountActual_IBActualTotal;
	
	@FindBy(xpath = "//a[.='IB Actual Voice']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountActual_IBActualVoice;

	@FindBy(xpath = "//a[.='IB Actual SMS']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountActual_IBActualSMS;

	@FindBy(xpath = "//a[.='IB Actual Data']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountActual_IBActualData;

	@FindBy(xpath = "//a[.='OB Actual Total']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountActual_OBActualTotal;
	
	@FindBy(xpath = "//a[.='OB Actual Voice']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountActual_OBActualVoice;

	@FindBy(xpath = "//a[.='OB Actual SMS']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountActual_OBActualSMS;

	@FindBy(xpath = "//a[.='OB Actual Data']")
	public WebElement link_MonthlyDiscount_MonthlyDiscountActual_OBActualData;

	

	@FindBy(xpath = "//button[.='Variance Report']")
	public WebElement link_VarianceReport;

	@FindBy(xpath = "//a[.='Variance Accruals']")
	public WebElement link_VarianceReport_VarianceAccruals;

	@FindBy(xpath = "//a[.='Variance Actuals']")
	public WebElement link_VarianceReport_VarianceActuals;
	

	@FindBy(xpath = "//button[.='Commitment Reports']")
	public WebElement link_CommitmentReports;

	@FindBy(xpath = "//a[.='Commitments - Expense']")
	public WebElement link_CommitmentReports_CommitmentsExpense;

	@FindBy(xpath = "//a[.='Commitments - Revenue']")
	public WebElement link_CommitmentReports_CommitmentsRevenue;

	@FindBy(xpath = "//a[contains(.,'Snapshot Management')]")
	public WebElement link_SnapshotManagement;

	@FindBy(xpath = "//button[.='DCH Data']")
	public WebElement link_DCHData;
	
	@FindBy(xpath = "//a[.='Data Loading']")
	public WebElement link_DCHData_DataLoading;
	
	@FindBy(xpath = "//a[.='Traffic Analysis Report']")
	public WebElement link_DCHData_TrafficAnalysisReport;
	
}
