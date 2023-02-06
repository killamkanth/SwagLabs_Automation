package com.swag.commonfunctions;



import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.support.ui.Select;



import Base.Base;

public class Supporters  {
	
	public static WebElement  getWebElement(String Value) {
		WebElement ele = null;
		try {
			
			ele= Base.getDriver().findElement(By.xpath("//*[@id='" + Value + "' or contains(text(),'"+Value+"') or @class='" + Value + "']"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ele;
	}
	
	public static void waitTillElementIsVisibled(WebElement element) {
		WebDriverWait  wait = new WebDriverWait(Base.getDriver(), Duration.ofSeconds(20));
		//wait.until(ExpectedConditions.elementToBeSelected(element));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public static void waitTillElementIsClicked(WebElement element) {
		WebDriverWait  wait = new WebDriverWait(Base.getDriver(), Duration.ofSeconds(20));
		//wait.until(ExpectedConditions.elementToBeSelected(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		//element.click();
	}
	
	public static void acceptAlert() {
		
		Alert alert = Base.getDriver().switchTo().alert();
		alert.accept();
	}
	
	public static void selectValueFromDropdown(WebElement element,String value) throws InterruptedException {
		Select sel = new Select(element);
		Thread.sleep(1000);
		sel.selectByValue(value);
	}
	
	
	  public static void clickOnElement(WebElement element , String eleName) 
	  {
	  if(element.isDisplayed()) 
	  	{ 	
		  	element.click();
	  		Base.getExtentTest().log(LogStatus.INFO, "Click operation performed on " + eleName);
	  	} 
	  }
	 

}
