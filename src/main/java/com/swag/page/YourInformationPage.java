package com.swag.page;

import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;
import com.swag.commonfunctions.Supporters;

import Base.Base;

public class YourInformationPage extends Base {
	
	public YourInformationPage() {
		PageFactory.initElements(Base.getDriver(), this);

	}
	
public boolean VerifyYourInformationPageTitle() {
		
		boolean flag = Supporters.getWebElement("Checkout: Your Information").isDisplayed();
		return flag;
	}
	
	public void ClickOnContinue() {
		Supporters.clickOnElement(Supporters.getWebElement("continue")," Continue Button");
	}
	
	public void Enter_Address_Details(String firstName,String lastName, String pinCode) {
		try {
			Supporters.getWebElement("first-name").sendKeys(firstName);
			Base.getExtentTest().log(LogStatus.INFO	, " FirstName is entered in Inputfield is " + firstName);
			
			Supporters.getWebElement("last-name").sendKeys(lastName);
			Base.getExtentTest().log(LogStatus.INFO	, " LastName is entered in Inputfield is " + lastName);


			Supporters.getWebElement("postal-code").sendKeys(pinCode);
			Base.getExtentTest().log(LogStatus.INFO	, " PinCode is entered in Inputfield is " + pinCode);
			
		}
		catch(Exception e) {
			e.getMessage();
			Base.getExtentTest().log(LogStatus.INFO, "Error in Entering Address details " + e.getMessage());
		}
	}
	public boolean VerifyCheckoutOverviewPage_Title() {
		
		boolean flag = Supporters.getWebElement("Checkout: Overview").isDisplayed();
		return flag;
	}
	
	public String VerifyPaymentInformation() {
		String paymentInfo = Supporters.getWebElement("SauceCard #31337").getText();
		return paymentInfo;
	}
	
	
	
	public String VerifyShippingInformation() {
		String shippingInfo = Supporters.getWebElement("FREE PONY EXPRESS DELIVERY!").getText();
		return shippingInfo;
	}
	
	

}
