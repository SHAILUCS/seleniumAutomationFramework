package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.configData_Util.Constant;
import common.configData_Util.STATUS;
import common.customReporting.CustomReporter;
import common.driverManager.DriverFactory;
import common.seleniumExceptionHandling.SeleniumMethods;
import common.xlUtil.DataTable;
import objectRepository.AgreementCapture.MaintainIOTDiscountAgreementsPage_P301;
import objectRepository.AgreementCapture.MaintainIOTRONPersonalFavourites;
import objectRepository.FCHAdministration.SystemUsers;
import objectRepository.common.IOTRONHomePage;
import objectRepository.common.LoginPage;
import objectRepository.common.NGCHomePage;
import objectRepository.common.Navigator;
import objectRepository.common.PagesTitle;

public class UTILITIES_Driver {

	@Parameters({"startRow","endRow"})
	@Test(description = "CREATE NEW USER")
	public void CreateNewUser(String startRow, String endRow) {
		int start=Integer.parseInt(startRow);
		int end=Integer.parseInt(endRow);
		Navigator navigate= new Navigator();
		NGCHomePage fch=new NGCHomePage();
		navigate.traverseMenu_VerifyPageTitle( PagesTitle.SystemUsers, fch.link_Administration,fch.link_Administration_SystemUsers);
			SystemUsers systemUsers=new SystemUsers();
			//for excel row 4 set i = 3 
			for (int i = start; i <= end; i++) {
				CustomReporter.createNode("Creating user [" + i + "/" + (end-start+1) + "]");
				systemUsers.createNewUser(i);
			}
	}

	@Test(description = "VALIDATE USER CREDS/AUTO CHANGES PASSWORDS IF REQUIRED")
	public void ValidateUserCreds() {
		DataTable dataTable=new DataTable(Constant.getTestDataFilePath(), Constant.getEnvironmentInfoSheet());
		DriverFactory.getDriver().get(dataTable.getValue(1, "url"));
		int count=dataTable.getRowCount();
		for (int row = 1; row < count; row++) {
			CustomReporter.createNode("Validating user [" + row + "/" + (count-1) + "]");
			LoginPage loginPage= new LoginPage();
			String userType=dataTable.getValue(row, "user type");
			//CustomListener.methodList.add(new Object(){}.getClass().getEnclosingMethod().getName()+" | "+userType);
			if(!userType.equals("")){
				if(loginPage.performLogin(userType)){
					DriverFactory.getDriver().get(dataTable.getValue(1, "url"));
				}
			}
		}
	}

	@Test(description = "DELETE TOOL CREATED AGREEMENTS")
	public void DeleteToolCreatedAgreements() {
		LoginPage login = new LoginPage();
		//CustomListener.methodList.add(new Object(){}.getClass().getEnclosingMethod().getName()+" | NGC-S");
		String methodName=new Object(){}.getClass().getEnclosingMethod().getName();

		if(login.performLogin("NGC-S")){
			Navigator navigate= new Navigator();
			IOTRONHomePage ihp=new IOTRONHomePage();
			navigate.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
			MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
			if(maintainIOTDiscountAgreementsPage!=null){
				maintainIOTDiscountAgreementsPage.deleteToolCreatedAgreements();
			}
		}
	}

	@Test(description = "'IOTRON Personal Favourites' feature")
	public void IOTRONPersonalFavourites() {
		// Page Objects
		SeleniumMethods com = new SeleniumMethods();
		LoginPage login = new LoginPage();

		// Test Flow
		//CustomListener.methodList.add(new Object(){}.getClass().getEnclosingMethod().getName()+" | NGC-S");
		String methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		if(login.performLogin("NGC-S")){
			NGCHomePage ngcHome = new NGCHomePage();
			if (com.navigateToAndVerifyPageTitle(ngcHome.link_PersonalFavourites, MaintainIOTRONPersonalFavourites.title)) {
				MaintainIOTRONPersonalFavourites maintainIOTRONPersonalFavourites = new MaintainIOTRONPersonalFavourites();
				maintainIOTRONPersonalFavourites.verifyAllItems();
				maintainIOTRONPersonalFavourites.selectFavLinkAndCheckItsFunctionality();
			}
		}
	}


	//TODO

		
	@Test(description = "IOT Discount Agreement - Page content verification")
	public void UI_IOTDiscountAgreement() {

		LoginPage login = new LoginPage();
		//CustomListener.methodList.add(new Object(){}.getClass().getEnclosingMethod().getName()+" | NGC-S");
		String methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		if(login.performLogin("NGC-S")){
			Navigator nav= new Navigator();
			IOTRONHomePage ihp=new IOTRONHomePage();
			nav.to_IOTRONHomePage().traverseMenu_VerifyPageTitle( PagesTitle.MaintainIOTDiscountAgreements_P301, ihp.link_AgreementCapture, ihp.link_AgreementCapture_MaintainIOTDiscountAgreements);
			MaintainIOTDiscountAgreementsPage_P301 maintainIOTDiscountAgreementsPage=new MaintainIOTDiscountAgreementsPage_P301();
			if(maintainIOTDiscountAgreementsPage!=null){
				//TODO Incomplete
				CustomReporter.report(STATUS.WARNING, "INCOMPLETE SCRIPT");

			}
		}
	}	

	

	


}