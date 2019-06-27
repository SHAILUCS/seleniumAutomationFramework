/**
 * QlikCommon.java
 * Stores the objects and methods which are common to whole Qlik Application 
 * */

package or.qlik;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;

public class QlikCommon {
	private SeleniumMethods com;
	public static String title = "";

	public QlikCommon() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	/** The popping bubbles loading indicator Object, to be used in waitForElements_ToBeNotVisible method */
	public By icon_PageLoadingIndicator_Rain = By.xpath("//div[contains(@class,'rain')]");
	
	/** Need to see how and where to use this */
	@FindBy(xpath = "//div[.='Opening the app overview']")
	public WebElement data_PageLoadingIndicator_OpeningTheAppOverview;
	
	/** On Sheet Page Button displayed on Top Right corner for selecting other sheets */
	@FindBy(xpath = "//button[@title='Sheets']")
	public WebElement button_SheetNavigatorButton;

	/** Export button on popup */
	@FindBy(xpath = "//span[.='Export']")
	public WebElement button_Export;

	/** Export Data button on popup */
	@FindBy(xpath = "//span[.='Export data']")
	public WebElement button_ExportData;
	
	/** Export Complete popup */
	@FindBy(xpath = "//div[.='Export complete']")
	public WebElement heading_ExportCompletePopup;

	/** Close button on Export Complete popup */
	@FindBy(xpath = "//button[.='Close']")
	public WebElement button_Close_ExportCompletePopup;
	
	/** Click here to download your data file link on Export Complete popup */
	@FindBy(xpath = "//a[.='Click here to download your data file.']")
	public WebElement link_ClickHereToDownloadYourDataFile;
	
	
	
}
