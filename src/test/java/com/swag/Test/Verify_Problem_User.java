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

public class Verify_Problem_User extends Base {
	LoginPage loginPage;
	HomePage homePage;
	YourCartPage yourCartPage;
	CheckOutPage checkOutPage;
	YourInformationPage yourInformationPage;
	/**
	public Verify_Problem_User() {
		loginPage = new LoginPage();
		homePage = new HomePage();
		yourCartPage = new YourCartPage();
		checkOutPage = new CheckOutPage();
		yourInformationPage = new YourInformationPage();
	}
	**/
	
	public void Validate_Problem_User() {
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


	}
	
	
	public void Validate_Performance_Glitch_User() {
		
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
		
	}
	
	@Test
	public void Verify_Standard_User() throws InterruptedException {
		
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
		//HomePage hpage=new HomePage();
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
		String zipCode = Base.getExcel().getSingleCellData("Swag_Labs_Data", 1, 2);

		yourInformationPage.Enter_Address_Details(firstName, lName, zipCode);
		
		//yourInformationPage.ClickOnContinue();
		Supporters.clickOnElement(Supporters.getWebElement("continue")," Continue Button");
		
		boolean VerifyCheckoutOverview_Status= yourInformationPage.VerifyCheckoutOverviewPage_Title();
		if(VerifyCheckoutOverview_Status) {
			Assert.assertTrue(VerifyInformation_Status);
			Base.getExtentTest().log(LogStatus.INFO, "Successfully verified checkout overview page Title");
		} else {
			Base.getExtentTest().log(LogStatus.INFO, "Error in verifying checkout overview page Title");

		}
		String expValue = "SauceCard #31337";
		String actValue = yourInformationPage.VerifyPaymentInformation();
		Assert.assertEquals(actValue, expValue);
		
		String exp_Value = "FREE PONY EXPRESS DELIVERY!";
		String act_Value = yourInformationPage.VerifyShippingInformation();
		Assert.assertEquals(act_Value, exp_Value);
		
		Supporters.clickOnElement(Supporters.getWebElement("Finish"), "Finish Button");
		
		Supporters.clickOnElement(Supporters.getWebElement("Open Menu"), "Menu Icon");
		
		//Thread.sleep(1000);
		Supporters.waitTillElementIsClicked(Supporters.getWebElement("Logout"));
		Supporters.clickOnElement(Supporters.getWebElement("Logout"), "Logout Button");
		
	}
	
	
	public void Verify_Login_All_Users() throws InterruptedException {
		
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
			System.out.println("Sucessfully verified " + locked_User);
		}
		else {
			System.out.println("Error in verifying " + locked_User);
		}
		//flag = hPage.verifyHomePageTitle(prblm_User);
		//Assert.assertTrue(flag);
			
		
	}
	
	
	public void Verify_Locked_User() {
		
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
			System.out.println( locked_User + " Verification is done successfully");
		}
		else {
			System.out.println( locked_User + " Verification is unsuccessfull" );
			Base.getExtentTest().log(LogStatus.FAIL, locked_User + " Verification is unsuccessfull");

		}		
	}
}
