package objectRepository.AgreementCapture;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.configData_Util.Constant;
import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.xlUtil.DataTable;
import objectRepository.common.ApexCommon;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.NGCHomePage;

public class MaintainIOTRONPersonalFavourites {
	@FindBy(id = "P542_FAVOURITE_PAGES_LEFT")
	private WebElement select_FavPageSelectionLeft;
	
	@FindBy(id = "P542_FAVOURITE_PAGES_RIGHT")
	private WebElement select_FavPageSelectionRight;
	
	@FindBy(linkText = "Save")
	private WebElement button_Save;
	
	
	private SeleniumMethods com;
	private ApexCommon comm;

	public static String title="Maintain IOTRON Personal Favourites";
	public MaintainIOTRONPersonalFavourites() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm= new ApexCommon();
	}
	
	public void verifyAllItems() {
		String allOptions=com.getAttribute(select_FavPageSelectionLeft, "innerHTML");
		String sheetName="MaintainIOTRONPersonalFav";
		
		DataTable dataTable=new DataTable(Constant.getTestDataFilePath(),sheetName);
		
		int rowCount=dataTable.getRowCount();
		
		for (int row = 1; row < rowCount; row++) {
			String tempVal=dataTable.getValue( row, "Favourite Pages Selection");
			if(!allOptions.contains(tempVal)){
				CustomReporter.report(STATUS.FAIL, "Option '"+tempVal+"' in 'Favourite Pages Selection' is NOT present");
			}
		}
	}
	
	public void selectFavLinkAndCheckItsFunctionality() {
		
		String overviewReportStr="Overview Report";
		String discountReportingStr="Discount Reporting";
		
		com.selectByPartialVisibleText_DoubleClick_FromCommaSeparated(select_FavPageSelectionLeft, overviewReportStr+","+discountReportingStr);
		com.click(button_Save);
		
		IOTRONHomePage iotronHome= new IOTRONHomePage();
		CustomReporter.report(STATUS.INFO, "Checking '"+ overviewReportStr +"' link functionality");
		WebElement overviewObj=com.getDynamicElement(By.xpath("//a[.='"+overviewReportStr+"']"));
		com.navigateToAndVerifyPageTitle(overviewObj, overviewReportStr);
		com.navigateToAndVerifyPageTitle(iotronHome.link_Welcome, NGCHomePage.title);
		
		CustomReporter.report(STATUS.INFO, "Checking '"+ discountReportingStr +"' link functionality");
		WebElement discObj=com.getDynamicElement(By.xpath("//a[.='"+discountReportingStr+"']"));
		com.navigateToAndVerifyPageTitle(discObj, discountReportingStr);
		com.navigateToAndVerifyPageTitle(iotronHome.link_Welcome, "Welcome to Nextgen Clearing");
		
		NGCHomePage ngcHome= new NGCHomePage();
		com.navigateToAndVerifyPageTitle(ngcHome.link_PersonalFavourites, "Maintain IOTRON Personal Favourites");
		
		CustomReporter.report(STATUS.INFO, "Removing all the options and saving the Personal Favourites");
		com.deselectAllValues_Shuttle_DoubleClick(select_FavPageSelectionRight);
		com.click(button_Save);
		
		if(com.waitForElementTobe_NotVisible(By.xpath("//a[.='"+overviewReportStr+"']"),0)){
			CustomReporter.report(STATUS.PASS, "Link '"+ overviewReportStr +"' is removed from NGC home page");
		}else{
			CustomReporter.report(STATUS.FAIL, "Link '"+ overviewReportStr +"' is NOT removed from NGC home page");
		}
		
		if(com.waitForElementTobe_NotVisible(By.xpath("//a[.='"+discountReportingStr+"']"),0)){
			CustomReporter.report(STATUS.PASS, "Link '"+ discountReportingStr +"' is removed from NGC home page");
		}else{
			CustomReporter.report(STATUS.FAIL, "Link '"+ discountReportingStr +"' is NOT removed from NGC home page");
		}
	}
	
}
