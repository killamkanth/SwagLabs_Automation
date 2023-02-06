package com.swag.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.swag.commonfunctions.Supporters;

import Base.Base;

public class HomePage extends Base {
	
	@FindBy(xpath="//*[@class='inventory_item']")
	List<WebElement> productList;
	
	@FindBy(xpath="//select[@data-test='product_sort_container']")
	WebElement selectElement;
	
	@FindBy(xpath="//*[@class='shopping_cart_link']/span")
	WebElement cartIconElement;
	
	public HomePage() {
		PageFactory.initElements(Base.getDriver(), this);
		
	}
	
	public void verifyHomePageTitle(String user) {
		try 
		{		
			boolean flag = Supporters.getWebElement("Products").isDisplayed();
			if(flag) 
			{
				System.out.println("Successfully logged in with " + user);
				Base.getExtentTest().log(LogStatus.PASS, "Logged in successfully as " + user);
			}
		}catch(Exception e) 
		{
			e.printStackTrace();
			Base.getExtentTest().log(LogStatus.INFO, "Error in verifying home page title as " + user);
		}
		
		//Assert.assertEquals(Supporters.getWebElement("Products").getText(), "PRODUCTS");
		
	}
	
	public void clickOnOpenMenu() {
		Supporters.getWebElement("Open Menu").click();
		Base.getExtentTest().log(LogStatus.INFO, "Click action is performed on open menu icon");
		
	}
	
	public void clickOnLogOutButton() {
		Supporters.getWebElement("Logout").click();
		Base.getExtentTest().log(LogStatus.INFO, "Click action is performed on Logout Button");

	}
	
	public int countOfProducts() {
		List<WebElement> productCount = Base.getDriver().findElements(By.xpath("//*[@class='inventory_item']"));
		int count = productCount.size();
		return count;
	}
	
	public void searchForProducts() throws InterruptedException {
		
		  try {
		  Supporters.clickOnElement(selectElement,"Dropdown Element");
		  Supporters.selectValueFromDropdown(selectElement, "hilo"); 
		  } catch(NullPointerException e) { 
			  e.printStackTrace(); }
		 
	//	Select select = new Select(Base.getDriver().findElement(By.xpath("//select[@class='product_sort_container']")));
		//select.selectByValue("hilo");
	}
	
	public void addProductsToCart() {
		Supporters.getWebElement("add-to-cart-sauce-labs-fleece-jacket").click();
		
		//Supporters.getWebElement("add-to-cart-sauce-labs-backpack").click();
		Supporters.clickOnElement(Supporters.getWebElement("add-to-cart-sauce-labs-backpack")," Add to cart Element");
		
		String prodCount = Base.getDriver().findElement(By.xpath("//a/span")).getText();
		int i = Integer.parseInt(prodCount);
		Base.getExtentTest().log(LogStatus.INFO, "Items Added to cart :: " + i);
		
	}
	
	public void clickOnCartIcon() {
		
		Supporters.clickOnElement(Supporters.getWebElement("shopping_cart_link") , " Cart Icon");
		
	}

}
