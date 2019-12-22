package objectRepository.AgreementCapture.Rework.AgreementDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.AgreementCapture.CreateEditIOTManualForecast_P159;
import objectRepository.common.PagesTitle;

public class Administration_Tab_p15_ManualForecast_Sec extends Administration_Tab_p15 {

	public static String title = IOTDiscountAgreementDetails_p15_Parent.title;

	public Administration_Tab_p15_ManualForecast_Sec() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		tabHead=new WebTable(table_ResultTabHeader_ManFor);
		tabData=new WebTable(table_ResultTabData_ManFor);
	}

	@FindBy(xpath = "//h2[.='Manual Forecasting']")
	private WebElement heading_ManualForecasting;

	@FindBy(xpath = "//div[contains(.,'Manual Forecasting')]/div/button")
	private WebElement hideShow_ManualForecasting;
	
	/**Search Toolbar Objects*/
	@FindBy(xpath = "//div[@data-label='Administration']//h2[contains(.,'Manual Forecasting')]/../../..//button[contains(@id,'_column_search_root')]")
	public WebElement icon_SelectColumnToSearch;

	@FindBy(xpath = "//div[@data-label='Administration']//h2[contains(.,'Manual Forecasting')]/../../..//input[contains(@class,'a-IRR-search-field')]")
	public WebElement text_Search;

	@FindBy(xpath = "//div[@data-label='Administration']//h2[contains(.,'Manual Forecasting')]/../../..//button[.='Go']")
	public WebElement button_Go;

	@FindBy(xpath = "//div[@data-label='Administration']//h2[contains(.,'Manual Forecasting')]/../../..//div[contains(@class,'rowSelector')]//select")
	public WebElement select_RowsPerPage;

	@FindBy(xpath = "//div[@data-label='Administration']//h2[contains(.,'Manual Forecasting')]/../../..//button[.='Actions']")
	public WebElement button_Action;

	@FindBy(xpath = "//button[.='Create/Edit Manual Forecast']")
	private WebElement button_CreateEditManualForecast;

	private By table_ResultTabHeader_ManFor=By.xpath("//div[@data-label='Administration']//h2[contains(.,'Manual Forecast')]/../../..//div[contains(@class,'head')]/table[@class='a-IRR-table']");
	private By table_ResultTabData_ManFor=By.xpath("//div[@data-label='Administration']//h2[contains(.,'Manual Forecast')]/../../..//div[contains(@class,'body')]/table[@class='a-IRR-table']");

	private WebTable tabData;
	private WebTable tabHead;

	private List<String> colNames = new ArrayList<>(Arrays.asList("Traffic Period", "Service Type", "Event Type",
			"Traffic Direction", "Manual Forecast Volume"));

	public void verify_UI() {
		CustomReporter.report(STATUS.INFO, "Manual Forecasting Section ->>");
		openTab(tab_Administration);
		
		com.isElementPresent(heading_ManualForecasting, "heading_Manual Forecasting");
		verifyHideShowFunc(true, hideShow_ManualForecasting, "Manual Forecasting Section");
		CustomReporter.report(STATUS.INFO, "Apex search toolbar ->>");
		com.isElementPresent(icon_SelectColumnToSearch, "icon_Select Column To Search");
		com.isElementPresent(text_Search, "text_Search");
		com.isElementPresent(button_Go, "button_Go");
		//TODO com.isElementPresent(select_RowsPerPage, "select_Rows Per Page");
		comm.verifyActionsPopupItems_WithRowsPerPage(button_Action);

		com.isElementPresent(button_CreateEditManualForecast, "button_Create/Edit Manual Forecasting");

		com.click(button_CreateEditManualForecast,"button_Create/Edit Manual Forecasting");
		com.verifyPageTitle(PagesTitle.CreateEditIOTManualForecast_P159.title, true);

		CreateEditIOTManualForecast_P159 P159=new CreateEditIOTManualForecast_P159();
		com.click(P159.button_Cancel, "P159 button_Cancel");
		
		com.verifyPageTitle(title, true);

		if(com.isElementPresent(table_ResultTabHeader_ManFor)){
			comm.verifyColumnHeaders(table_ResultTabHeader_ManFor, colNames, 1);
		}

	}

}
