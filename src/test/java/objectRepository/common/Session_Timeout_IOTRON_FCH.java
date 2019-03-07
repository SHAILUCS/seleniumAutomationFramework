package objectRepository.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;

public class Session_Timeout_IOTRON_FCH extends IOTRONHomePage{
	
	//FCH Objects
	@FindBy(xpath="//button[contains(.,'Reporting')]")
	public WebElement link_ReportingFCH;

	@FindBy(xpath="//a[.='SLA Reporting - New']")
	public WebElement link_SLA_Reporting_New;
	
	/**@author shailendra.rajawat*/
	/*@FindBy(linkText="IOTRON")
	public WebElement link_IOTRON;*/

 	private SeleniumMethods com;
 	
 	private String expectedURL = "http://10.184.40.120/pls/apex/f?p=10132:22";
 	
 	private int sessionTimeoutInSec= 1800;

	public Session_Timeout_IOTRON_FCH() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com= new SeleniumMethods();
	}
	
   /** @author prafull.barve**/
	public void sessionTimeoutIotron() {
		
		//com.click(link_IOTRON, "Iotron link on home page");
		
		/**@author shailendra.rajawat*/
		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage();
		
		VerifyTimeOut();
	}
	
	/** @author prafull.barve**/
	public void sessionTimeoutIotron_TMO() {
		
		//com.click(link_IOTRON, "Iotron link on home page, Link");
		//com.click(link_Reporting, "Reporting, Link");
		//com.click(link_Reporting_CustomisedReportsTMO, "Customized ReportsTMO, Link");
		
		/**@author shailendra.rajawat*/
		IOTRONHomePage i=new IOTRONHomePage();
		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.CustomisedReportsTMO, i.link_Reporting, i.link_Reporting_CustomisedReportsTMO);
		
		VerifyTimeOut();
	}
	
	/** @author prafull.barve**/
	public void sessionTimeoutIotron_DealTracker() {
		
		//com.click(link_IOTRON, "Iotron link on home page, Link");
		//com.click(link_Forecasting, "Forecasting, Link");
		//com.click(link_Forecasting_DealTracker, "Forecasting Deal Tracker");
		
		/**@author shailendra.rajawat*/
		IOTRONHomePage i=new IOTRONHomePage();
		Navigator nav= new Navigator();
		nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.DealTracker, i.link_Forecasting, i.link_Forecasting_DealTracker);
		
		VerifyTimeOut();
	}
	
	/** @author prafull.barve**/
	public void sessionTimeoutFCHHomePage() {
		
		VerifyTimeOut();
	}

	/** @author prafull.barve**/
	public void sessionTimeoutFCH_SLA_Report() {
		
		//com.click(link_ReportingFCH, "Reporting link");
		//com.click(link_SLA_Reporting_New, "SLA Reporting New");
		
		
		/**@author shailendra.rajawat*/
		Navigator nav= new Navigator();
		nav.traverseMenu_VerifyPageTitle( "SLA Reports", link_ReportingFCH,link_SLA_Reporting_New);
		VerifyTimeOut();
	
	}
	
	/** @author prafull.barve**/
	private void VerifyTimeOut() {

		CustomReporter.report(STATUS.INFO, "Waiting for "+(sessionTimeoutInSec/60)+" mins");
		com.wait(sessionTimeoutInSec);
		com.refresh();

		if(com.verifyPageTitle("Login Page"))
		{
			CustomReporter.report(STATUS.PASS, "Session Timeout");
			String currentURL = com.getCurrentUrl();
			if(currentURL.equals(expectedURL))
			{
				CustomReporter.report(STATUS.PASS,"Navigate to FCH URL Passed");
			}
			else
			{
				CustomReporter.report(STATUS.FAIL,"Navigate to FCH URL Failed");
			}
		}
		else
		{
			CustomReporter.report(STATUS.FAIL, "User still logged In");
		}

	}

}
