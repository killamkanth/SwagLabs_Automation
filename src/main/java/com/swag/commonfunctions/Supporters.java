package com.swag.commonfunctions;



import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;
import com.swag.constants.POJOSupporters;

import org.openqa.selenium.support.ui.Select;



import Base.Base;

public class Supporters  {
	
	
	/*
	 *  Method for generating xpath 
	 */	
	public static WebElement  getWebElement(String Value) {
		WebElement ele = null;
		try {
			
			ele= Base.getDriver().findElement(By.xpath("//*[@id='" + Value + "' or contains(text(),'"+Value+"') or @class='" + Value + "']"));
			highlightElement(Base.getDriver(),ele);
		}catch(Exception e) {
			e.printStackTrace();
			Base.getExtentTest().log(LogStatus.INFO, "Error in creating xpath for element :: " + Value);
		}
		return ele;
	}
	
	public static void waitTillElementIsVisibled(WebElement element) {
		WebDriverWait  wait = new WebDriverWait(Base.getDriver(), Duration.ofSeconds(POJOSupporters.TimeOut));
		//wait.until(ExpectedConditions.elementToBeSelected(element));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public static void waitTillElementIsClicked(WebElement element) {
		WebDriverWait  wait = new WebDriverWait(Base.getDriver(), Duration.ofSeconds(POJOSupporters.TimeOut));
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
		//Thread.sleep(1000);
		highlightElement(Base.getDriver(), element);
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
	  
	  public static void highlightElement(WebDriver driver , WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		
	  }
	 

}
