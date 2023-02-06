package com.swag.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.swag.commonfunctions.Supporters;
import com.swag.page.HomePage;
import com.swag.page.LoginPage;

import Base.Base;

public class LoginPageTest extends Base {

	LoginPage loginPage;
	HomePage homePage;
	
	
	public LoginPageTest() {
		loginPage = new LoginPage();
		homePage = new HomePage();
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
		//Assert.assertTrue(flag, "Logged in Successfully ");
		
		homePage.clickOnOpenMenu();
		homePage.clickOnLogOutButton();
		
		loginPage.verifyHomePageLogin(prblm_User, pwd);
		
		homePage.verifyHomePageTitle(prblm_User);
		//Assert.assertTrue(flag, "Logged in Successfully ");
		
		homePage.clickOnOpenMenu();
		homePage.clickOnLogOutButton();
		
		loginPage.verifyHomePageLogin(perfmnc_Glitch_User, pwd);
		
		Supporters.waitTillElementIsVisibled(Supporters.getWebElement("Products"));
		
		homePage.verifyHomePageTitle(perfmnc_Glitch_User);
		//Assert.assertTrue(flag, "Logged in Successfully ");
		
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
