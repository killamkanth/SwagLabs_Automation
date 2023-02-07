package com.swag.commonfunctions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Base.Base;

public class ScreenshotUtility 
{
	
	public static void captureScreenShot() 
	{
		try 
		{
			TakesScreenshot ts = (TakesScreenshot) Base.getDriver();
			File file = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(Base.getCurDir() + "\\Screenshots\\" + Base.getTcName()+".jpeg"));
		
			
		} catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

}
