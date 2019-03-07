package common.testNgListener;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;

import common.configData_Util.Constant;
import common.configData_Util.STATUS;
import common.configData_Util.Util;
import common.coverageMatrixGenerator.AutomationCoverageMatrixManager;
import common.customReporting.CustomReporter;
import common.customReporting.ReportingHistoryManager;
import common.customReporting.Test;
import common.customReporting.TestRunHistoryManager;
import common.customReporting.snapshot.SnapshotManager;
import common.customReporting.snapshot.SnapshotsMovieMaker;
import common.driverManager.DriverFactory;
import common.extentReportingv3.ExtentManager;
import common.mailUtil.MailUtil;
import objectRepository.common.LoginPage;

public class CustomListener_HMap implements ITestListener,IExecutionListener{

	private ExtentReports extentReport;
	private String parallelFlag;
	private String suiteName;
	private String testName;
	
	public void onExecutionStart() {
		//System.out.println("onExecutionStart for Thread: "+Thread.currentThread().getId());
		Util.deleteFolderContentRecursively(new File(Constant.getDownloadsPath()),Constant.downloadFolderName);
		SnapshotManager.initialize();
		CustomReporter.initialize();
	}

	public void onStart(ITestContext context) {
		//System.out.println("bro onStart "+Thread.currentThread().getId());
		HashMap<TestNGKeys,String> testDataMap= new HashMap<>();
		parallelFlag=context.getSuite().getParallel();
		suiteName=context.getSuite().getName();
		testName=context.getCurrentXmlTest().getName();
		
		testDataMap.put(TestNGKeys.parallel, parallelFlag);
		testDataMap.put(TestNGKeys.browser, context.getCurrentXmlTest().getParameter(TestNGKeys.browser.value));
		testDataMap.put(TestNGKeys.platform, context.getCurrentXmlTest().getParameter(TestNGKeys.platform.value));
		testDataMap.put(TestNGKeys.remoteURL, context.getCurrentXmlTest().getParameter(TestNGKeys.remoteURL.value));
		testDataMap.put(TestNGKeys.environment, context.getCurrentXmlTest().getParameter(TestNGKeys.environment.value));
		Constant.setEnvironmentInfoSheet(context.getCurrentXmlTest().getParameter(TestNGKeys.environment.value));
		testDataMap.put(TestNGKeys.suite, context.getSuite().getName());
		testDataMap.put(TestNGKeys.test, context.getCurrentXmlTest().getName());
		extentReport = ExtentManager.GetExtentReports(testDataMap);
		CustomReporter.onStart(testDataMap);
		
		if(parallelFlag.equals("none") || parallelFlag.equals("tests")){
			DriverFactory.setUp(testDataMap);
			SnapshotManager.setUp(context.getName());
		}
		
	}

	
	public void onTestStart(ITestResult result) {
		//System.out.println("onTestStart "+Thread.currentThread().getId());
		//System.out.println("Started: "+Arrays.toString(result.getMethod().getMethodsDependedUpon()));
		HashMap<TestNGKeys,String> testDataMap=createTestObj(result);
		
		if(!parallelFlag.equals("none") && !parallelFlag.equals("tests")){
			//DriverFactory.setUp(remoteURL,browser,platform);
			DriverFactory.setUp(testDataMap);
			SnapshotManager.setUp(result.getName());
		}else{
			SnapshotManager.setRunningMethodName(result.getName());
		}
		
		String user=result.getMethod().findMethodParameters(result.getTestContext().getCurrentXmlTest()).get(TestNGKeys.user.value);
		new LoginPage().performLogin(user);
		
	}

	public void onTestSuccess(ITestResult result){
		//System.out.println("onTestSuccess "+Thread.currentThread().getId());
		new LoginPage().logout();
		String moviePath=SnapshotsMovieMaker.createMovie(SnapshotManager.getSnapshotDestinationDirectory());
		CustomReporter.onTestEnd_NonXl(moviePath);
		
		if(!parallelFlag.equals("none") && !parallelFlag.equals("tests")){
			DriverFactory.tearDown();
			SnapshotManager.tearDown();
		}
		CustomReporter.onExecutionFinish();
	}

	/**
	 * When any unhandled exception stops the execution prematurely then
	 * ERROR line will add the stack trace in the html report, also
	 * the stack trace can be seen in [TestNG Results of running
	 * suite]>>[Failure Exception] section
	 */
	public void onTestFailure(ITestResult result) {
		//System.out.println("onTestFailure "+Thread.currentThread().getId());
		new LoginPage().logout();
		result.getThrowable().printStackTrace();
		String readableStackTrace=result.getThrowable().getMessage();
		for (StackTraceElement elem : result.getThrowable().getStackTrace()) {
			readableStackTrace=readableStackTrace+"<br/>"+elem.toString();
		}
		CustomReporter.report(STATUS.ERROR, "- PREMATURE EXECUTION STOPPED : Failure Exception :- <br/><b style='color:red;font-size: small;font-family: arial,sans-serif;'>"+readableStackTrace+"</b>");
		
		String moviePath=SnapshotsMovieMaker.createMovie(SnapshotManager.getSnapshotDestinationDirectory());
		CustomReporter.onTestEnd_NonXl(moviePath);
		if(!parallelFlag.equals("none") && !parallelFlag.equals("tests")){
			
			
			DriverFactory.tearDown();
			SnapshotManager.tearDown();
		}
		CustomReporter.onExecutionFinish();
	}
	public void onTestSkipped(ITestResult result) {
		//System.out.println("onTestSkip "+Thread.currentThread().getId());
		createTestObj(result);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed: '"+(result.getMethod().getMethodsDependedUpon().length>0?Arrays.toString(result.getMethod().getMethodsDependedUpon()):null)+"'");
		CustomReporter.onTestEnd_NonXl(null);
		CustomReporter.onExecutionFinish();
	}

	private HashMap<TestNGKeys,String> createTestObj(ITestResult result){
		HashMap<TestNGKeys,String> testDataMap= new HashMap<>();
		testDataMap.put(TestNGKeys.browser, result.getMethod().findMethodParameters(result.getTestContext().getCurrentXmlTest()).get(TestNGKeys.browser.value));
		testDataMap.put(TestNGKeys.platform, result.getMethod().findMethodParameters(result.getTestContext().getCurrentXmlTest()).get(TestNGKeys.platform.value));
		
		// In case you forgot to provide description to a test method, then
		// method name will be shown in HTML report instead of {null}
		if (result.getMethod().getDescription()==null) {
			testDataMap.put(TestNGKeys.description, result.getMethod().getMethodName());	
		}else{
			testDataMap.put(TestNGKeys.description, result.getMethod().getDescription());
		}
		
		testDataMap.put(TestNGKeys.className, result.getMethod().getRealClass().getName());
		testDataMap.put(TestNGKeys.methodName, result.getMethod().getMethodName());
		testDataMap.put(TestNGKeys.priority, result.getMethod().getPriority()+"");
		testDataMap.put(TestNGKeys.remoteURL, result.getTestContext().getCurrentXmlTest().getAllParameters().get(TestNGKeys.remoteURL.value));
		testDataMap.put(TestNGKeys.group,Arrays.toString(result.getMethod().getGroups()));
		testDataMap.put(TestNGKeys.dependsOn,Arrays.toString(result.getMethod().getMethodsDependedUpon()));
		
		Test.setTotalScenario(result.getTestContext().getAllTestMethods().length);
		
		ExtentManager.createTest(testDataMap);
		CustomReporter.onTestStart(testDataMap);
		
		return testDataMap;
	}
	
	
	public void onFinish(ITestContext context) {
		//System.out.println("onFinish "+Thread.currentThread().getId());
		if(parallelFlag.equals("none") || parallelFlag.equals("tests")){
			DriverFactory.tearDown();
			SnapshotManager.tearDown();
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}
	
	public void onExecutionFinish() {
		
		System.out.println(AutomationCoverageMatrixManager.getMethodVsPageMap());
		System.out.println("===============================================================================\nCustom Report Generation STARTED "+new Date());
		CustomReporter.onExecutionFinish();
		System.out.println("Custom Report Generation ENDED "+new Date()+"\n===============================================================================");
		
		System.out.println("===============================================================================\nExtent Report Generation STARTED "+new Date());
		try{extentReport.flush();
		}catch(Exception e){e.printStackTrace();}
		System.out.println("Extent Report Generation ENDED "+new Date()+"\n===============================================================================");
		
		TestRunHistoryManager.manageTestRunHistory();
		ReportingHistoryManager.manageReportingHistory();
		MailUtil.sendNotificationMail("Suite["+suiteName+"] Test["+testName+"] Env["+Constant.getEnvironmentInfoSheet()+"] "+new Date());
		AutomationCoverageMatrixManager.generateAutomationCoverageMatrixReport();
	}

	
	
}