package tests.SessionTimeout;

import org.testng.annotations.Test;

import objectRepository.common.Session_Timeout_IOTRON_FCH;

public class Verification_SessionTimeout {
	
	/**@author prafull.barve*/
	@Test(description = "session Timeout Iotron")
	public void Testing_sessionTimeoutIotron() {
		Session_Timeout_IOTRON_FCH Session_Timeout_IOTRON_FCH = new Session_Timeout_IOTRON_FCH();
		Session_Timeout_IOTRON_FCH.sessionTimeoutIotron();
	}
	
	/**@author prafull.barve*/
	@Test(description = "session Timeout Iotron TMO")
	public void Testing_sessionTimeoutIotron_TMO() {
		Session_Timeout_IOTRON_FCH Session_Timeout_IOTRON_FCH = new Session_Timeout_IOTRON_FCH();
		Session_Timeout_IOTRON_FCH.sessionTimeoutIotron_TMO();
	}
	
	/**@author prafull.barve*/
	@Test(description = "session Timeout Iotron Deal Tracker")
	public void Testing_sessionTimeoutIotron_DealTracker() {
		Session_Timeout_IOTRON_FCH Session_Timeout_IOTRON_FCH = new Session_Timeout_IOTRON_FCH();
		Session_Timeout_IOTRON_FCH.sessionTimeoutIotron_DealTracker();
	}
	
	/**@author prafull.barve*/
	@Test(description = "session Timeout FCH Home Page")
	public void Testing_sessionTimeoutFCHHomePage() {
		Session_Timeout_IOTRON_FCH Session_Timeout_IOTRON_FCH = new Session_Timeout_IOTRON_FCH();
		Session_Timeout_IOTRON_FCH.sessionTimeoutFCHHomePage();
	}
	
	/**@author prafull.barve*/
	@Test(description = "Testing session Timeout FCH SLA Report")
	public void Testing_sessionTimeout_FCHSLAReport() {
		Session_Timeout_IOTRON_FCH Session_Timeout_IOTRON_FCH = new Session_Timeout_IOTRON_FCH();
		Session_Timeout_IOTRON_FCH.sessionTimeoutFCH_SLA_Report();
	}

}
