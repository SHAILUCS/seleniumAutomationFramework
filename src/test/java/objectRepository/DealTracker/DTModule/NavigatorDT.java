package objectRepository.DealTracker.DTModule;

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
import objectRepository.common.Navigator;

public class NavigatorDT extends Navigator {
	private DealTrackerHome home;
	public NavigatorDT() {
		home = new DealTrackerHome();
	}

	public DTSummary_Header to_DealTrackerSummary() {
		traverseMenu_VerifyPageTitle( DTSummary_Header.title, home.link_DealTrackerModule,home.link_DealTrackerModule_DealTrackerSummary);
		return new DTSummary_Header();
	}


	public CurrentMonthSteering to_CurrentMonthSteering() {
		traverseMenu_VerifyPageTitle( CurrentMonthSteering.title, home.link_DealTrackerModule,home.link_DealTrackerModule_CurrentMonthSteering);
		return new CurrentMonthSteering();
	}


	public DealTrackerSnapshotManagement to_DealTrackerSnapshotManagement() {
		traverseMenu_VerifyPageTitle( DealTrackerSnapshotManagement.title, home.link_DealTrackerModule,home.link_DealTrackerModule_SnapshotManagement);
		return new DealTrackerSnapshotManagement();
	}


	public FlashReport to_FlashReport() {
		traverseMenu_VerifyPageTitle( FlashReport.title, home.link_FlashModule,home.link_FlashModule_FlashMonthEndReport);
		return new FlashReport();
	}


	public YTDTrueup to_YTDTrueup() {
		traverseMenu_VerifyPageTitle( YTDTrueup.title, home.link_FlashModule,home.link_FlashModule_YTDTrueUp);
		return new YTDTrueup();
	}


	public FlashReportArchive to_FlashReportArchive() {
		traverseMenu_VerifyPageTitle( FlashReportArchive.title, home.link_FlashModule,home.link_FlashModule_FlashReportArchive);
		return new FlashReportArchive();
	}


	public PerformanceTracker to_PerformanceTracker() {
		traverseMenu_VerifyPageTitle( PerformanceTracker.title, home.link_BudgetModule,home.link_BudgetModule_PerformanceTracker);
		return new PerformanceTracker();
	}


	public BudgetAdministration to_BudgetAdministration() {
		traverseMenu_VerifyPageTitle( BudgetAdministration.title, home.link_BudgetModule,home.link_BudgetModule_BudgetAdministration);
		return new BudgetAdministration();
	}


	public BudgetForecastCreation to_BudgetForecastCreation() {
		traverseMenu_VerifyPageTitle( BudgetForecastCreation.title, home.link_BudgetModule,home.link_BudgetModule_BudgetForecastCreation);
		return new BudgetForecastCreation();
	}


	public BudgetTrueup to_BudgetTrueup() {
		traverseMenu_VerifyPageTitle( BudgetTrueup.title, home.link_BudgetModule,home.link_BudgetModule_BudgetTrueup);
		return new BudgetTrueup();
	}


	public FutureFXRate to_FutureFXRate() {
		traverseMenu_VerifyPageTitle( FutureFXRate.title, home.link_BudgetModule,home.link_BudgetModule_FutureFX);
		return new FutureFXRate();
	}


	public PricingCatalogue to_PricingCatalogue() {
		traverseMenu_VerifyPageTitle( PricingCatalogue.title, home.link_PricingCatalogue);
		return new PricingCatalogue();
	}

	public DailyTrafficAnalysisReport to_DailyTrafficAnalysisReportDT() {
		traverseMenu_VerifyPageTitle( DailyTrafficAnalysisReport.title, home.link_DailyTrafficAnalysisreport);
		return new DailyTrafficAnalysisReport();
	}


	public ChangeAuditReport to_ChangeAuditReport() {
		traverseMenu_VerifyPageTitle( ChangeAuditReport.title, home.link_ChangeAuditReport);
		return new ChangeAuditReport();
	}


	public DataLoading to_DataLoading() {
		traverseMenu_VerifyPageTitle( DataLoading.title, home.link_DataLoading);
		return new DataLoading();
	}


}
