package Base;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.swag.commonfunctions.ScreenshotUtility;
import com.swag.constants.FilePaths;
import com.swag.constants.POJOSupporters;

import commonUtils.ExcelUtils;
import commonUtils.PropertyUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	private static WebDriver driver;
	private static String tcName;
	private static String curDir;
	private static PropertyUtils prConf;
	private static ExcelUtils excel ; 
	private static ExtentReports extentReports;
	private static ExtentTest extentTest;
	
	
	@Parameters("browser")
	@BeforeSuite
	public void openBrowser(@Optional("chrome")String browser)  {
		
		curDir = System.getProperty("user.dir");
		try {
			prConf = POJOSupporters.getPrConf();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		try {
			excel = POJOSupporters.getExcel();
		} catch (EncryptedDocumentException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(browser.equalsIgnoreCase("chrome")) {
			
			driver = WebDriverManager.chromedriver().create();
			inIt();
			
		} else if(browser.equalsIgnoreCase("edge")) {
			driver = WebDriverManager.edgedriver().create();
			inIt();
		} 
		
	} 
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static String getTcName() {
		return tcName;
	}


	@AfterSuite
	public void closeBrowser() {
		driver.close();
	}
	
	@BeforeTest
	public void initiateReports() {
		extentReports = new ExtentReports(FilePaths.reportPath);
	}
	
	@AfterTest
	public void generateReports() {
		extentReports.flush();
		extentReports.close();
	}
	
	public static void inIt() {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(POJOSupporters.TimeOut));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(POJOSupporters.TimeOut));
	}
	@BeforeMethod
	public void befotrTCExecution(Method method) {
		tcName = method.getName();
		System.out.println("TC going to be executed is ::  " + tcName);
		extentTest = extentReports.startTest(tcName);
	}
	
	@AfterMethod
	public void afterTCExecution(ITestResult result)  {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			System.out.println("TC is Failed " + getTcName());
			
				ScreenshotUtility.captureScreenShot();
			
			System.out.println(result.getThrowable());
			extentTest.log(LogStatus.FAIL, "Test Case is Failed :: " + getTcName() + " :: "+result.getThrowable());
		} else if(result.getStatus() == ITestResult.SUCCESS) {
			System.out.println("TC is Passed " + getTcName());
			ScreenshotUtility.captureScreenShot();
			extentTest.log(LogStatus.PASS, "Test Case is Passed :: " + getTcName());
		} else if(result.getStatus() == ITestResult.SKIP) {
			System.out.println("TC is Skipped " + getTcName());
			ScreenshotUtility.captureScreenShot();			
			extentTest.log(LogStatus.SKIP, "Test Case is Skipped :: " + getTcName());
		}
		
		extentReports.endTest(extentTest);
		
	}

	public static ExtentTest getExtentTest() {
		return extentTest;
	}

	public static String getCurDir() {
		return curDir;
	}
	
	public static PropertyUtils getPrConf() {
		return prConf;
	}
	
	public static ExcelUtils getExcel() {
		return excel;
	}


}
