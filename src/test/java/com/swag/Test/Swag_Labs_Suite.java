package com.swag.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.swag.commonfunctions.Supporters;
import com.swag.page.CheckOutPage;
import com.swag.page.HomePage;
import com.swag.page.LoginPage;
import com.swag.page.YourCartPage;
import com.swag.page.YourInformationPage;

import Base.Base;

public class Swag_Labs_Suite extends Base {
	
	LoginPage loginPage;
	HomePage homePage;
	YourCartPage yourCartPage;
	CheckOutPage checkOutPage;
	YourInformationPage yourInformationPage;
	
@Test(priority=1)
public void TC_01_Verify_Login_All_Users() throws InterruptedException {
	
	try {
			loginPage = new LoginPage();
			homePage = new HomePage();
			
			String url = Base.getPrConf().getPropertyvalue("url");
			String stnd_User = Base.getPrConf().getPropertyvalue("Standard_User");
			String locked_User = Base.getPrConf().getPropertyvalue("Locked_User");
			String prblm_User = Base.getPrConf().getPropertyvalue("Problem_User");
			String perfmnc_Glitch_User = Base.getPrConf().getPropertyvalue("Performance_User");
	
			
	
			String pwd = Base.getPrConf().getPropertyvalue("Password");
			
			Base.getDriver().get(url);
			Base.getExtentTest().log(LogStatus.INFO, "Url is loaded ::" + url);
			loginPage.verifyHomePageLogin(stnd_User,pwd);
			homePage.verifyHomePageTitle(stnd_User);
			//Assert.assertTrue(flag);
			
			homePage.clickOnOpenMenu();
			homePage.clickOnLogOutButton();
			
			loginPage.verifyHomePageLogin(prblm_User, pwd);
			
			homePage.verifyHomePageTitle(prblm_User);
			//Assert.assertTrue(flag);
			
			homePage.clickOnOpenMenu();
			homePage.clickOnLogOutButton();
			
			loginPage.verifyHomePageLogin(perfmnc_Glitch_User, pwd);
			
			Supporters.waitTillElementIsVisibled(Supporters.getWebElement("Products"));
			
			homePage.verifyHomePageTitle(perfmnc_Glitch_User);
			//Assert.assertTrue(flag);
			
			homePage.clickOnOpenMenu();
			homePage.clickOnLogOutButton();
			
			loginPage.verifyHomePageLogin(locked_User, pwd);
			
			Boolean flag = loginPage.verify_LockedUser_ErrorMesg();
			if(flag) {
				Assert.assertTrue(flag);
				//System.out.println("Sucessfully verified " + locked_User);
				Base.getExtentTest().log(LogStatus.INFO, "Sucessfully verified " + locked_User);
			}
			else {
				//System.out.println("Error in verifying " + locked_User);
				Base.getExtentTest().log(LogStatus.INFO, "Error in verifying " + locked_User);

			}
			//flag = hPage.verifyHomePageTitle(prblm_User);
			//Assert.assertTrue(flag);
		
		}catch(Exception e) {
			Base.getExtentTest().log(LogStatus.INFO, "Error in Verify_Login_All_Users " + e.getMessage());
		}
			
	}
	@Test(priority=2)
	public void TC_02_Verify_Standard_User() throws InterruptedException {
		
		try {
			
			loginPage = new LoginPage();
			homePage = new HomePage();
			yourCartPage = new YourCartPage();
			checkOutPage = new CheckOutPage();
			yourInformationPage = new YourInformationPage();
			
			String url = Base.getPrConf().getPropertyvalue("url");
			
			String stnd_User = Base.getPrConf().getPropertyvalue("Standard_User");
			String pwd = Base.getPrConf().getPropertyvalue("Password");
			
			Base.getDriver().get(url);
			Base.getExtentTest().log(LogStatus.INFO, "Url is loaded ::" + url);
			
			loginPage.verifyHomePageLogin(stnd_User,pwd);
			homePage.verifyHomePageTitle(stnd_User);
			Thread.sleep(1000);
			homePage.searchForProducts();
			Thread.sleep(1000);
			homePage.addProductsToCart();
			homePage.clickOnCartIcon();
			
			boolean status = yourCartPage.VerifyYourCartPageTitle();
			if(status) {
				Assert.assertTrue(status);
				Base.getExtentTest().log(LogStatus.INFO, "Successfully verified Your cart page Title");
			}else {
				Base.getExtentTest().log(LogStatus.INFO, "Error in verifying Your cart page Title");
		
			}
			
			yourCartPage.ClickOnCheckout();
			
			boolean verifyCheckoutPageTitle_Status = checkOutPage.VerifyCheckOutPageTitle();
			if(verifyCheckoutPageTitle_Status) {
				Assert.assertTrue(verifyCheckoutPageTitle_Status);
				Base.getExtentTest().log(LogStatus.INFO, "Successfully verified CheckOut page Title");
		
			} else {
				Base.getExtentTest().log(LogStatus.INFO, "Error in verifying CheckOut page Title");
		
			}
			
			boolean VerifyInformation_Status = yourInformationPage.VerifyYourInformationPageTitle();
			if(VerifyInformation_Status) {
				Assert.assertTrue(VerifyInformation_Status);
				Base.getExtentTest().log(LogStatus.INFO, "Successfully verified Information page Title");
			}else {
				Base.getExtentTest().log(LogStatus.INFO, "Error in verifying Information page Title");
			}
			
			String firstName = Base.getExcel().getSingleCellData("Swag_Labs_Data", 1, 0);
			String lName = Base.getExcel().getSingleCellData("Swag_Labs_Data", 1, 1);
			String value = Base.getExcel().getSingleCellData("Swag_Labs_Data", 1, 2);
			String zipCode = value.substring(0, 5);
		
			yourInformationPage.Enter_Address_Details(firstName, lName, zipCode);
			
			Supporters.clickOnElement(Supporters.getWebElement("continue")," Continue Button");
			
			boolean VerifyCheckoutOverview_Status= yourInformationPage.VerifyCheckoutOverviewPage_Title();
			if(VerifyCheckoutOverview_Status) {
				Assert.assertTrue(VerifyInformation_Status);
				Base.getExtentTest().log(LogStatus.INFO, "Successfully verified checkout overview page Title");
			} else {
				Base.getExtentTest().log(LogStatus.INFO, "Error in verifying checkout overview page Title");
		
			}
			String expValue = Base.getExcel().getSingleCellData("Swag_Labs_Data", 4, 0);
			String actValue = yourInformationPage.VerifyPaymentInformation();
			Assert.assertEquals(actValue, expValue);
		
			
			String exp_Value = Base.getExcel().getSingleCellData("Swag_Labs_Data", 5, 0);
			String act_Value = yourInformationPage.VerifyShippingInformation();
			Assert.assertEquals(act_Value, exp_Value);
			
			Supporters.clickOnElement(Supporters.getWebElement("Finish"), "Finish Button");
			
			String actMesg = Supporters.getWebElement("THANK YOU FOR YOUR ORDER").getText();
			String expMesg = Base.getExcel().getSingleCellData("Swag_Labs_Data", 6, 0);
			if(expMesg.equals(actMesg)) {
				Base.getExtentTest().log(LogStatus.INFO, "Order placed Successfully ");
			}else {
				Base.getExtentTest().log(LogStatus.INFO, "Error in placing the order ");
			}
			Supporters.clickOnElement(Supporters.getWebElement("Open Menu"), "Menu Icon");
			
			
			Supporters.waitTillElementIsClicked(Supporters.getWebElement("Logout"));
			Supporters.clickOnElement(Supporters.getWebElement("Logout"), "Logout Button");
			
		}catch(Exception e) {
			Base.getExtentTest().log(LogStatus.INFO, "Error in Verifying Standard Users " + e.getMessage());
		}
		
		
	}
	
	@Test(priority=3)
	public void TC_03_Verify_Locked_User() {
		
	try {
		loginPage = new LoginPage();
		String url = Base.getPrConf().getPropertyvalue("url");
		
		String locked_User = Base.getPrConf().getPropertyvalue("Locked_User");
		String pwd = Base.getPrConf().getPropertyvalue("Password");
		
		Base.getDriver().get(url);
		Base.getExtentTest().log(LogStatus.INFO, "Url is loaded ::" + url);
		loginPage.verifyHomePageLogin(locked_User,pwd);
		
		loginPage.validate_LockedUser_Mesg();
		
		
		Boolean flag= loginPage.verify_LockedUser_ErrorMesg();
		
		if(flag) {
			Assert.assertTrue(flag);
			Base.getExtentTest().log(LogStatus.PASS, locked_User + " Verification is done successfully");
			//System.out.println( locked_User + " Verification is done successfully");
		}
		else {
			//System.out.println( locked_User + " Verification is unsuccessfull" );
			Base.getExtentTest().log(LogStatus.FAIL, locked_User + " Verification is unsuccessfull");
	
		}		
			}catch(Exception e) {
				Base.getExtentTest().log(LogStatus.INFO, "Error in Validating  User " + e.getMessage());
			}
			
				
	}
	
	@Test(priority=4)
	public void TC_04_Validate_Problem_User() {
		try {
			loginPage = new LoginPage();
			homePage = new HomePage();
			
			String url = Base.getPrConf().getPropertyvalue("url");
			
			String prblm_User = Base.getPrConf().getPropertyvalue("Problem_User");
			String pwd = Base.getPrConf().getPropertyvalue("Password");
			
			Base.getDriver().get(url);
			Base.getExtentTest().log(LogStatus.INFO, "Url is loaded ::" + url);
			
			loginPage.verifyHomePageLogin(prblm_User,pwd);
			homePage.verifyHomePageTitle(prblm_User);
			
			//Supporters.acceptAlert();
			int size = homePage.countOfProducts();
			Base.getExtentTest().log(LogStatus.INFO, "Total Products found in HomePage are :: " + size);
			
			homePage.clickOnOpenMenu();
			homePage.clickOnLogOutButton();

			
		}catch(Exception e) {
			Base.getExtentTest().log(LogStatus.INFO, "Error in Validating Problem User " + e.getMessage());
		}
		

	}
	
	@Test(priority=5)
	public void TC_05_Validate_Performance_Glitch_User() {
		try {
			loginPage = new LoginPage();
			homePage = new HomePage();
			String url = Base.getPrConf().getPropertyvalue("url");
			
			String perfmnc_Glitch_User = Base.getPrConf().getPropertyvalue("Performance_User");
			String pwd = Base.getPrConf().getPropertyvalue("Password");
			
			Base.getDriver().get(url);
			Base.getExtentTest().log(LogStatus.INFO, "Url is loaded ::" + url);
			
			loginPage.verifyHomePageLogin(perfmnc_Glitch_User,pwd);
			Supporters.waitTillElementIsVisibled(Supporters.getWebElement("Products"));
			
			homePage.verifyHomePageTitle(perfmnc_Glitch_User);
			
			homePage.clickOnOpenMenu();
			homePage.clickOnLogOutButton();
			
		} catch(Exception e) {
			Base.getExtentTest().log(LogStatus.INFO, "Error in Validating Performance Glitch User " + e.getMessage());
		}
		
		
	}

}
