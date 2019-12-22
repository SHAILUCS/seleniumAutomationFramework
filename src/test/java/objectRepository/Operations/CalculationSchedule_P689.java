/**
 * CalculationSchedule_P689.java
 * This is a Page Object class, which stores the page objects 
 * of P689 page and stores the methods which works upon those objects
 * */

package objectRepository.Operations;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import objectRepository.common.PagesTitle;

public class CalculationSchedule_P689
{
    private SeleniumMethods com;
    
    public static String title = PagesTitle.CalculationSchedule_P689.title;
    
    public CalculationSchedule_P689 () {
        PageFactory.initElements(DriverFactory.getDriver(), this);
        com = new SeleniumMethods();
    }
    
    @FindBy(xpath="//h1[.='Calculation Schedule']")
    private WebElement heading_CalculationSchedule;
    
    @FindBy(xpath="//h2[.='Select Client']")
    private WebElement section_SelectClient;
    
    @FindBy(xpath="//div[contains(.,'Select Client')]/div/button")
    private WebElement hideShow_SelectClient;
    
    @FindBy(id = "P689_LOV_SELECT_CLIENT")
	private WebElement select_Client;
    
    @FindBy(xpath="//button[.='Refresh']")
    private WebElement button_Refresh;
    
    @FindBy(xpath="//h2[.='Calculation Schedule']")
    private WebElement section_CalculationSchedule;
    
    @FindBy(xpath="//div[contains(.,'Calculation Schedule')]/div/button")
    private WebElement hideShow_CalculationSchedule;
    
    @FindBy(id="P689_NUMBER_OF_PREV_MONTHS_NEW")
    private WebElement text_NumberOfPreviousMonthsToRunTheCalculationsFor;
    
    @FindBy(id="P689_CALCULATION_FREQUENCY_0")
    private WebElement radio_CalculationFrequency_Weekly;
    
    @FindBy(id="P689_CALCULATION_FREQUENCY_1")
    private WebElement radio_CalculationFrequency_Monthly;
    
    @FindBy(id="P689_CALCULATION_FREQUENCY_2")
    private WebElement radio_CalculationFrequency_OnDemand;
    
    @FindBy(xpath="//h2[.='Weekly']")
    private WebElement section_Weekly;
    
    @FindBy(id="P689_DAY_OF_THE_WEEK")
    private WebElement select_DayOfWeek;
    
    @FindBy(xpath="//button[.='Save']")
    private WebElement button_Save;
    
	/**
	 * Verify the Page UI elements and also perform search to get all the
	 * elements visible
	 */
    public void verify_UI(String client){
        com.isElementPresent(heading_CalculationSchedule,"heading_CalculationSchedule");
        
        CustomReporter.createNode("Select Client Section verification");
        com.isElementPresent(section_SelectClient,"section_SelectClient");
        com.isElementPresent(hideShow_SelectClient,"hideShow_SelectClient");
        com.isElementPresent(select_Client, "select_Client");
        com.isElementPresent(button_Refresh,"button_Refresh");
        
        CustomReporter.createNode("Calculation Schedule Section verification");
        performSearch(client);
        com.isElementPresent(section_CalculationSchedule,"section_CalculationSchedule");
        com.isElementPresent(hideShow_CalculationSchedule,"hideShow_CalculationSchedule");
        com.isElementPresent(text_NumberOfPreviousMonthsToRunTheCalculationsFor,"text_NumberOfPreviousMonthsToRunTheCalculationsFor");
        com.isElementPresent(radio_CalculationFrequency_Weekly,"radio_CalculationFrequency_Weekly");
        com.isElementPresent(radio_CalculationFrequency_Monthly,"radio_CalculationFrequency_Monthly");
        com.isElementPresent(radio_CalculationFrequency_OnDemand,"radio_CalculationFrequency_OnDemand");
        
        CustomReporter.createNode("Weekly Section verification");
        com.isElementPresent(section_Weekly,"section_Weekly");
        com.isElementPresent(select_DayOfWeek,"select_DayOfWeek");
        com.isElementPresent(button_Save,"button_Save");
    }
    
    /**
	* Fill in the search fields and perform the search
	* precon : P680 should be loaded, and fields should be visible
	* postcon : Values will be filled in the fields, Refresh button will be clicked
	* @author shailendra.rajawat 25-Mar-2019
	* */
	private void performSearch(String client) {
		String message = " | ";

		if (client != null) {
			com.selectByPartialVisibleText(select_Client, client);
			message += "client = " + client + " | ";
		}

		CustomReporter.report(STATUS.INFO, "Performing search with data:" + message);
		com.click(button_Refresh, "button_Refresh");
		
		if(com.waitForElementTobe_Visible(section_CalculationSchedule)){
			CustomReporter.report(STATUS.PASS, "Search result are displayed");
		}else{
			CustomReporter.report(STATUS.FAIL, "Search result are NOT displayed");
			Assert.fail("Search failed for data "+client);
		}
		
	}
	

    
}