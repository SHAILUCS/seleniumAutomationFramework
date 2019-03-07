package objectRepository.Settlement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.driverManager.DriverFactory;

public class Settlement {
	public static String title = "Settlement";

	public Settlement() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	
	@FindBy(linkText = "Credit/Debit Note Report")
	public WebElement link_CreditDebitNoteReport;
	
	

}
