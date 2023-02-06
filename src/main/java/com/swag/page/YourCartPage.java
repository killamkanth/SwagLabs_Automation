package com.swag.page;

import org.openqa.selenium.support.PageFactory;

import com.swag.commonfunctions.Supporters;

import Base.Base;

public class YourCartPage extends Base{

	public YourCartPage() {
		PageFactory.initElements(Base.getDriver(), this);
		
	}
	
	public boolean VerifyYourCartPageTitle() {
		
		boolean flag = Supporters.getWebElement("Your Cart").isDisplayed();
		return flag;
	}
	
	public void ClickOnCheckout() {
		Supporters.clickOnElement(Supporters.getWebElement("Checkout"),"Checkout Element");
	}
}
