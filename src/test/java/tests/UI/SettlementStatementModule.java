package tests.UI;

import org.testng.annotations.Test;

import objectRepository.Settlement.CreditDebitNoteReport;
import objectRepository.Settlement.GenerateSettlementStatement;
import objectRepository.Settlement.SettlementStatementHistory;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

public class SettlementStatementModule 
{

	/**@author prafull.barve*/
	@Test(description="UI_Setmt_Generate Settlement Statement(p680)")
	private void UI_Setmt_GenerateSettlementStatement_p680() 
	{
		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage Ihp = new IOTRONHomePage();
		nav.traverseMenu_VerifyPageTitle( PagesTitle.GenerateSettlementStatement, Ihp.link_Settlement,Ihp.link_Settlement_GenerateSettlementStatement);
		GenerateSettlementStatement GenerateSettlementStatementpage = new GenerateSettlementStatement();
		GenerateSettlementStatementpage.UIVerification();
	}

	/**@author prafull.barve*/
	@Test(description="UI_Setmt_Credit Debit Note Report(p305)")
	private void UI_Setmt_CreditDebitNoteReport_p305() 
	{
		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage Ihp = new IOTRONHomePage();
		nav.traverseMenu_VerifyPageTitle( PagesTitle.CreditDebitNoteReport, Ihp.link_Settlement,Ihp.link_Settlement_CreditDebitNoteReport);
		CreditDebitNoteReport CreditDebitNoteReportpage = new CreditDebitNoteReport();
		CreditDebitNoteReportpage.UIVerification();
	}

	/**@author prafull.barve*/
	@Test(description="UI_Setmt_Settlement Statement History(p544)")
	private void UI_Setmt_SettlementStatementHistory_p544() 
	{
		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		IOTRONHomePage Ihp = new IOTRONHomePage();
		nav.traverseMenu_VerifyPageTitle( PagesTitle.SettlementStatementHistory, Ihp.link_Settlement,Ihp.link_Settlement_SettlementStatementHistory);
		SettlementStatementHistory SettlementStatementHistorypage = new SettlementStatementHistory();
		SettlementStatementHistorypage.UIVerification();
	}

}
