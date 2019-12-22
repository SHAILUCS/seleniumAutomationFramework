package objectRepository.FCHDataLoading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;

public class SDRConversionRates_P392 {
	private SeleniumMethods com;
	private ApexCommon comm;
	public static String title = PagesTitle.SDRConversionRates_P392.title;

	@FindBy(id = "P392_TRAFFIC_PERIOD_ID")
	private WebElement select_TrafPeriod;

	@FindBy(xpath = "//a[normalize-space(.)='Next']")
	private WebElement link_Next;

	@FindBy(xpath = "//a[normalize-space(.)='Previous']")
	private WebElement link_Previous;

	private By table_SDRConversionRates=By.cssSelector("table[summary='SDR Conversion Rates']");

	private WebTable webtab;
	
	private Map<String, Map<String, String>> rateMatrixMap;

	public SDRConversionRates_P392() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		webtab = new WebTable(table_SDRConversionRates);
	}

	private List<String> colNames = new ArrayList<>(Arrays.asList());

	public void verify_UI() {

	}

	/**
	 * Before this method please call fetchConversionRatesMatrix method
	 * */
	public Map<String,String> getConversionRate_ForPassedCurrency(String currency) {
		if (rateMatrixMap==null) {
			CustomReporter.report(STATUS.FAIL, "rateMatrixMap is empty ,Please call fetchConversionRatesMatrix() method of p392");
			return null;
		}
		Map<String,String> currencyMap_TrafficWise=new HashMap<String, String>();
		
		Set<String> trafPeriods=rateMatrixMap.keySet();
		
		for (String trafPeriod : trafPeriods) {
			Map<String,String> map_CurrWise=rateMatrixMap.get(trafPeriod);
			for (String curr : map_CurrWise.keySet()) {
				if (curr.equalsIgnoreCase(currency)) {
					currencyMap_TrafficWise.put(trafPeriod, map_CurrWise.get(curr));
					break;
				}
			}
		}
		CustomReporter.report(STATUS.INFO, "Conversion Rate of [SDR To "+currency+"] "+currencyMap_TrafficWise);
		return currencyMap_TrafficWise;
	}
	
	/**
	 * First Call this method to initialize the Rate Matrix, if you do so then,
	 * this will reduce the execution time significantly for multiple Currencies and Traffic periods 
	 */
	public void fetchConversionRatesAnd_InitMatrix(String[] currenciesArr, String[] trafPeriods) {
		rateMatrixMap=new HashMap<String, Map<String,String>>();

		webtab.initHeaderColumnList(1);

		for (String trafP : trafPeriods) {
			Map<String, String> curr_AND_Rate_Map=new HashMap<String, String>();
			String rateVal=null;
			com.selectByVisibleText(select_TrafPeriod, trafP);
			//com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);

			int rowCount=webtab.getRowCount();
			if (rowCount>2) {

				for (String currency : currenciesArr) {
					
					if (!com.waitForElementTobe_NotVisible(link_Previous,0)) {
						com.click(link_Previous);
						com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
					}

					//This is first way to get the Rate value, here our xpath is directly fetching 
					//the rate object for desired currency, THIS APPROACH IS FASTER
					By rateForDesiredCurrXPATH = By
							.xpath("//table[@summary='SDR Conversion Rates']/tbody/tr/td/select[contains(.,'"
									+ currency + "')]/../../td[contains(@headers,'RATE')]/input");
					if(com.waitForElementTobe_NotVisible(rateForDesiredCurrXPATH, 0)){
						if(!com.waitForElementTobe_NotVisible(link_Next, 0)){
							com.click(link_Next);
							com.waitForElementsTobe_NotVisible(comm.icon_SpinnerApex5LoadingIndicator);
						}
					}
					
					rateVal=com.getAttribute(rateForDesiredCurrXPATH, "value");
					
					curr_AND_Rate_Map.put(currency, rateVal);
					
				}
			}else{
				CustomReporter.report(STATUS.FAIL, "No Data getting displayed for selected traffic Period");
			}
			rateMatrixMap.put(trafP, curr_AND_Rate_Map);
		}

		CustomReporter.report(STATUS.INFO, "Initialized Conversion Rates Matrix for passed currencies "+Arrays.toString(currenciesArr)+" traffic periods "+Arrays.toString(trafPeriods)+" are <br/><br/>"+rateMatrixMap);
	}

}
