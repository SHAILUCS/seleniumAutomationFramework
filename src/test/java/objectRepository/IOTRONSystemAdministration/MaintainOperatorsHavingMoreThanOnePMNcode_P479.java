package objectRepository.IOTRONSystemAdministration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.seleniumExceptionHandling.WebTable;

import objectRepository.common.ApexCommon;
import objectRepository.common.PagesTitle;

public class MaintainOperatorsHavingMoreThanOnePMNcode_P479 {
	private SeleniumMethods com;
	private ApexCommon comm;
	WebTable tab_Head;
	WebTable tab_Data;
	
	public static String title = PagesTitle.MaintainOperatorsHavingMoreThanOnePMNcode_P479.title;

	public MaintainOperatorsHavingMoreThanOnePMNcode_P479() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new ApexCommon();
		tab_Head=new WebTable(comm.table_ResultTabHeader);
		tab_Data=new WebTable(comm.table_ResultTabData);
	}

	private List<String> colNames = new ArrayList<>(Arrays.asList());

	public void verify_UI() {

	}

	/**
	 * This method will search for the PMN and then click on edit icon for that PMN
	 * @param the pmn which needs to be searched
	 * @return Object of CreateEditOperatorHavingMoreThanOnePmnCode_P495 page
	 * PreCon: P479 should be loaded
	 * Postcon: P495 will be loaded
	 * @author shailendra.rajawat 16 Jan 2019
	 */
	public CreateEditOperatorHavingMoreThanOnePmnCode_P495 searchAndClickEdit(String operator) {
		comm.performSearch(operator);
		
		WebTable tabData=new WebTable(comm.table_ResultTabData);
		
		int row=tabData.getRowWithCellText(operator);
		
		com.click(tabData.getChildObject(row, 1, "img", 0));
		
		return new CreateEditOperatorHavingMoreThanOnePmnCode_P495();
	}

	
	/**
	 * For the passed @param operator Group Client, it will search for Operator and @return all PMNs belong to the same group
	 * 
	 * @author shailendra.rajawat
	 * Precondition: P479 should be loaded
	 * Postcondition: Search will be performed 
	 * @return 
	 * */
	public List<String> getGroupPMNsCodesForPassedOperator(String operator) {
		comm.performSearch(operator);

		int pmnCodesCol=tab_Head.initHeaderColumnList(1).getColumnNumber("PMN codes");
		
		String pmnCodes=tab_Data.getCellText(2, pmnCodesCol);
		
		List<String> pmnCodesList=Arrays.asList(pmnCodes.replace(" ", "").split(","));
		
		return pmnCodesList;
	}

}
