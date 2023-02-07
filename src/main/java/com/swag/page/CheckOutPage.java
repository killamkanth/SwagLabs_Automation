package com.swag.page;

import org.openqa.selenium.support.PageFactory;

import com.swag.commonfunctions.Supporters;

import Base.Base;

public class CheckOutPage extends Base {
	
	public CheckOutPage() {
		PageFactory.initElements(Base.getDriver(), this);
		
	}
	
	
	public boolean VerifyCheckOutPageTitle() {
		
		boolean flag = Supporters.getWebElement("Checkout: Your Information").isDisplayed();
		return flag;
	}
	
	
	

}
