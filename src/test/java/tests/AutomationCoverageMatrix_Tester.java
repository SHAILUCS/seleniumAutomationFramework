package tests;

import common.coverageMatrixGenerator.AutomationCoverageMatrixManager;

public class AutomationCoverageMatrix_Tester {
	public void Test1() {
		AutomationCoverageMatrixManager.addPageAgainstMethodKey("Page1", "http://10.184.40.120/pls/apex/f?p=10132:1::::::");
	}

	public void Test2() {
		AutomationCoverageMatrixManager.addPageAgainstMethodKey("Page2", "http://10.184.40.120/pls/apex/f?p=10132:2::::::");
		AutomationCoverageMatrixManager.addPageAgainstMethodKey("Page4", "http://10.184.40.120/pls/apex/f?p=10132:4::::::");
	}
	
	public void Test3() {
		AutomationCoverageMatrixManager.addPageAgainstMethodKey("Page1", "http://10.184.40.120/pls/apex/f?p=10132:1::::::");
	}

	public void Test4() {
		AutomationCoverageMatrixManager.addPageAgainstMethodKey("Page2", "http://10.184.40.120/pls/apex/f?p=10132:2::::::");
	}
	
	public void Test5() {
		AutomationCoverageMatrixManager.addPageAgainstMethodKey("Page4", "http://10.184.40.120/pls/apex/f?p=10132:4::::::");
	}
}
