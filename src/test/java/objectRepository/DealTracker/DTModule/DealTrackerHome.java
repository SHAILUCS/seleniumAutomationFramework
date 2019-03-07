package objectRepository.DealTracker.DTModule;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import objectRepository.DealTracker.BudgetModule.BudgetAdministration;
import objectRepository.DealTracker.BudgetModule.BudgetForecastCreation;
import objectRepository.DealTracker.BudgetModule.BudgetTrueup;
import objectRepository.DealTracker.BudgetModule.FutureFXRate;
import objectRepository.DealTracker.BudgetModule.PerformanceTracker;
import objectRepository.DealTracker.BudgetModule.PricingCatalogue;
import objectRepository.DealTracker.DTModule.DTSummary.DTSummary_Header;
import objectRepository.DealTracker.FlashModule.FlashReport;
import objectRepository.DealTracker.FlashModule.FlashReportArchive;
import objectRepository.DealTracker.FlashModule.YTDTrueup;
import objectRepository.common.Header;
import objectRepository.common.Navigator;

public class DealTrackerHome extends Header {
	private SeleniumMethods com;
	private Navigator nav;
	
	public static String title = "Welcome";
	
	public DealTrackerHome() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		nav= new Navigator();
	}

	@FindBy(xpath = "//a[contains(.,'Welcome')]")
	public WebElement link_Welcome;
	
	@FindBy(xpath = "//button[contains(.,'Deal Tracker Module')]")
	public WebElement link_DealTrackerModule;
	
	@FindBy(xpath = "//a[contains(.,'Deal Tracker Summary')]")
	public WebElement link_DealTrackerModule_DealTrackerSummary;
	
	@FindBy(xpath = "//a[contains(.,'Current Month Steering')]")
	public WebElement link_DealTrackerModule_CurrentMonthSteering;
	
	@FindBy(xpath = "//a[contains(.,'Snapshot Management')]")
	public WebElement link_DealTrackerModule_SnapshotManagement;
	
	@FindBy(xpath = "//button[contains(.,'Flash Module')]")
	public WebElement link_FlashModule;

	@FindBy(xpath = "//a[contains(.,'Actual report')]")
	public WebElement link_FlashModule_ActualReport;

	@FindBy(xpath = "//a[contains(.,'Flash Month-end Report')]")
	public WebElement link_FlashModule_FlashMonthEndReport;
		
	@FindBy(xpath = "//a[contains(.,'YTD True-up')]")
	public WebElement link_FlashModule_YTDTrueUp;
	
	@FindBy(xpath = "//a[contains(.,'Flash report archive')]")
	public WebElement link_FlashModule_FlashReportArchive;
	
	@FindBy(xpath = "//button[contains(.,'Budget Module')]")
	public WebElement link_BudgetModule;

	@FindBy(xpath = "//a[contains(.,'Performance Tracker')]")
	public WebElement link_BudgetModule_PerformanceTracker;
	
	@FindBy(xpath = "//a[contains(.,'Budget Administration')]")
	public WebElement link_BudgetModule_BudgetAdministration;
	
	@FindBy(xpath = "//a[contains(.,'Budget/Forecast Creation')]")
	public WebElement link_BudgetModule_BudgetForecastCreation;
	
	@FindBy(xpath = "//a[contains(.,'Budget True-up')]")
	public WebElement link_BudgetModule_BudgetTrueup;
	
	@FindBy(xpath = "//a[contains(.,'Future FX')]")
	public WebElement link_BudgetModule_FutureFX;

	@FindBy(xpath = "//a[contains(.,'Pricing Catalogue')]")
	public WebElement link_PricingCatalogue;
	
	@FindBy(xpath = "//a[contains(.,'Daily Traffic Analysis report')]")
	public WebElement link_DailyTrafficAnalysisreport;
	
	@FindBy(xpath = "//a[contains(.,'Data Loading')]")
	public WebElement link_DataLoading;
	
	@FindBy(xpath = "//a[contains(.,'Change Audit Report')]")
	public WebElement link_ChangeAuditReport;
	
	public void verifyMenuLinksAndHeaderData(){
		CustomReporter.report(STATUS.INFO, "Header section content verification");
		com.isElementPresent(link_Header_IOTRON, "IOTRON link");
		if(com.isElementPresent(link_Header_UserId, "User Id data")){
			com.click(link_Header_UserId);
			com.isElementPresent(link_Header_ChangePassword, "Change Password link");
			com.isElementPresent(link_Header_Logout, "Logout link");
		}
		
		CustomReporter.report(STATUS.INFO, "Menu Links verification");
		nav.traverseMenu_VerifyPageTitle( DealTrackerHome.title, link_Welcome);
		nav.traverseMenu_VerifyPageTitle( DTSummary_Header.title, link_DealTrackerModule,link_DealTrackerModule_DealTrackerSummary);
		nav.traverseMenu_VerifyPageTitle( CurrentMonthSteering.title, link_DealTrackerModule,link_DealTrackerModule_CurrentMonthSteering);
		nav.traverseMenu_VerifyPageTitle( DealTrackerSnapshotManagement.title, link_DealTrackerModule,link_DealTrackerModule_SnapshotManagement);
		
		
		CustomReporter.report(STATUS.WARNING, "'Actual report' page is not working");
		//nav.traverseMenu_VerifyPageTitle( "Actual report", link_FlashModule,link_FlashModule_ActualReport);
		nav.traverseMenu_VerifyPageTitle( FlashReport.title, link_FlashModule,link_FlashModule_FlashMonthEndReport);
		nav.traverseMenu_VerifyPageTitle( YTDTrueup.title, link_FlashModule,link_FlashModule_YTDTrueUp);
		nav.traverseMenu_VerifyPageTitle( FlashReportArchive.title, link_FlashModule,link_FlashModule_FlashReportArchive);
		
		nav.traverseMenu_VerifyPageTitle( PerformanceTracker.title, link_BudgetModule,link_BudgetModule_PerformanceTracker);
		nav.traverseMenu_VerifyPageTitle( BudgetAdministration.title, link_BudgetModule,link_BudgetModule_BudgetAdministration);
		nav.traverseMenu_VerifyPageTitle( BudgetForecastCreation.title, link_BudgetModule,link_BudgetModule_BudgetForecastCreation);
		nav.traverseMenu_VerifyPageTitle( BudgetTrueup.title, link_BudgetModule,link_BudgetModule_BudgetTrueup);
		nav.traverseMenu_VerifyPageTitle( FutureFXRate.title, link_BudgetModule,link_BudgetModule_FutureFX);
		
		nav.traverseMenu_VerifyPageTitle( PricingCatalogue.title, link_PricingCatalogue);
		nav.traverseMenu_VerifyPageTitle( DailyTrafficAnalysisReport.title, link_DailyTrafficAnalysisreport);
		nav.traverseMenu_VerifyPageTitle( DataLoading.title, link_DataLoading);
		nav.traverseMenu_VerifyPageTitle( ChangeAuditReport.title, link_ChangeAuditReport);		
	}
}
