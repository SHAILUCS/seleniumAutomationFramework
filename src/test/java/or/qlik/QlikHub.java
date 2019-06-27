/**
 * QlikHub.java
 * When user Logins to Qlik, 
 * Hub is opened where multiple streams are present,
 * When User clicks on a Stream links of multiple Apps are shown to user
 * */

package or.qlik;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;

public class QlikHub {
	private SeleniumMethods com;
	private QlikCommon qcom;
	public static String title = "Qlik Sense Hub";

	public QlikHub() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		qcom = new QlikCommon();
	}

	@FindBy(css = "span[class*='user-name']")
	public WebElement link_UserName;

	@FindBy(xpath = "//span[normalize-space()='Operations - FC']")
	public WebElement link_Operations_FC;

	@FindBy(xpath = "//span[normalize-space()='Operations - IOTRON']")
	public WebElement link_Operations_IOTRON;

	@FindBy(xpath = "//span[normalize-space()='Operations - DCH']")
	public WebElement link_Operations_DCH;

	@FindBy(xpath = "//button[contains(.,'Open')]")
	private WebElement button_Open;

	@FindBy(css = "button[title='Close'][class*='fade-button']")
	private WebElement button_Close;


	/**
	 * Clicks on the Stream to show its internal apps
	 * Also Ensures the App Overview links are loaded before leaving the control 
	 * @param streamName Name of Stream, could be IOTRON, FC, or DCH
	 * @author shailendra.rajawat 26-Mar-2019
	 * */
	public void selectStream(String streamName) {

		int retry = 5;
		CustomReporter.createNode("OPENING THE ["+streamName+"] STREAM");

		while(true){
			// Repeat this stuff, till the passed Stream is not visible, Or the retry attempts are exceeded
			switch (streamName) {
			case "IOTRON":
				com.click_UsingAction(link_Operations_IOTRON, "IOTRON Stream link");
				break;
			case "FC":
				com.click_UsingAction(link_Operations_FC, "FC Stream link");
				break;
			case "DCH":
				com.click_UsingAction(link_Operations_DCH, "DCH Stream link");
				break;

			default:
				CustomReporter.report(STATUS.WARNING, "Invalid value provided, Opening IOTRON app!");
				com.click_UsingAction(link_Operations_IOTRON, "IOTRON Stream link");
				streamName = "IOTRON";
				break;
			}
			com.wait(2);
			
			if(retry-- == 0){
				CustomReporter.report(STATUS.FAIL, "Failed to select "+streamName+" after multiple Retry attempts");
				break;
			}
				
			if( ! com.waitForElementsTobe_NotVisible(By.xpath("//h2[normalize-space()='Operations - "+streamName+"']"),5)){
				CustomReporter.report(STATUS.INFO, " Dashboard opened for stream [" + streamName + "]");
				break;
			}

		}

	}

	/**
	 * Clicks on the Client App Name to show a popup with Open button
	 * Once the Open Button is clicked, The App Overview is opened in
	 * new Tab
	 * Switches the driver to the newely opened tab and
	 * Also Ensures the Sheets are loaded before leaving the control
	 * @param streamName Name of client App, example [GoMalta_IOTRON Discount Management App]
	 * @author shailendra.rajawat 26-Mar-2019
	 * @return the Object of QlikAppOverview class so that, user can call the methods of this class
	 * @author shailendra.rajawat 27-Mar-2019
	 * */
	public QlikAppOverview openClientAppOverview_InNewTab(String client) {
		CustomReporter.createNode("******************OPENING THE [" + client + "] APP IN NEW TAB******************");
		if(!com.waitForElementsTobe_NotVisible(button_Close,1)){
			com.click(button_Close);
			com.wait(1);
		}

		com.click_UsingAction(By.xpath("//span[.='"+client+"']"));
		com.wait(1);
		com.click_UsingAction(button_Open);
		com.wait(5);

		int winCount = com.getWindowHandles().size();
		if(winCount>0){
			CustomReporter.report(STATUS.PASS, "App overview of ["+ client +"] is successfully opened in New Tab");
			com.switchTo_Tab_TitleContains(client);
			com.waitForElementsTobe_NotVisible(qcom.icon_PageLoadingIndicator_Rain);
			return new QlikAppOverview();
		}
		return null;
	}

	/**
	 * Close the passed app tab, and switch back to hub, for selecting other tabs
	 * @author shailendra.rajawat 17-Apr-2019
	 * */
	public void switchBackToHub() {
		String currentPageTitle = com.getTitle(); 
		
		CustomReporter.createNode("CLOSING THE [" + currentPageTitle + "] APP, and switching Back To Hub");
		
		// Sometimes Login is failed, in that case I will not close the tab as report will not be generated because of this
		if(currentPageTitle.toLowerCase().contains("login")){
			return;
		}
		
		com.close();
		com.switchTo_Tab_TitleContains(title);
		
		// Refreshing the page so that we do not get stale element exception
		com.refresh();
		com.wait(5);
		com.waitForElementsTobe_NotVisible(qcom.icon_PageLoadingIndicator_Rain);
	}

}
