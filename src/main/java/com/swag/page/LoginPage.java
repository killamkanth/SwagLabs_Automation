package com.swag.page;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.PageFactoryFinder;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.swag.commonfunctions.Supporters;

import Base.Base;

public class LoginPage extends Base{

	public LoginPage() {
		PageFactory.initElements(Base.getDriver(), this);
			
	}
	
	public void verifyHomePageLogin(String uName,String pwd) {
		try {
			Supporters.getWebElement("user-name").sendKeys(uName);
			Base.getExtentTest().log(LogStatus.INFO	,"Username is Entered :: " + uName);
			Supporters.getWebElement("password").sendKeys(pwd);
			Base.getExtentTest().log(LogStatus.INFO	,"Password is Entered :: " + pwd);
			Supporters.getWebElement("login-button").click();
			Base.getExtentTest().log(LogStatus.INFO	,"Click action is performed on Login Button");
			
		}catch(Exception e) {
			Base.getExtentTest().log(LogStatus.INFO, e.getMessage().toString());
		}
		

	}
	
	public Boolean verify_LockedUser_ErrorMesg() {
		Boolean flag = Supporters.getWebElement("Epic sadface").isDisplayed();
		return flag;
	}
	
	public void validate_LockedUser_Mesg() {
		try {
			String textMesg = Supporters.getWebElement("Epic sadface").getText();
			Assert.assertEquals(textMesg, Base.getExcel().getSingleCellData("Swag_Labs_Data", 3, 0));
			Base.getExtentTest().log(LogStatus.PASS, "Error Message is Validated successfully");
			
		}catch(Exception e) {
			e.printStackTrace();
			Base.getExtentTest().log(LogStatus.INFO, e.getMessage());			
		}
	
	}
	
	
}
