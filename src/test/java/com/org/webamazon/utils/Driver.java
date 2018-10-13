package com.org.webamazon.utils;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class Driver 
{
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;

	protected Driver() {

	}
	
	/********************************************************************************************************************
	 * Function Name : startDriver
	 * Parameters    : browser of type String 
	 * Return        : Driver of the selected browser of Type WebDriver
	 * Purpose       : To start the driver(Open Webpage) of the browser for Execution.
	 * 
	 *******************************************************************************************************************/
	@SuppressWarnings("deprecation")
	public static WebDriver startDriver(String browser) throws Exception{
		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			//If the Browser is Google Chrome
			if (browser.equalsIgnoreCase("chrome")) {
				Map<String, Object> prefs = new HashMap<String, Object>();	              
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-notifications");
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-default-apps"); 
				options.addArguments("use-fake-ui-for-media-stream");
				options.addArguments("chrome.switches","--disable-extensions");
				options.addArguments("--use-fake-device-for-media-stream");
				System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + File.separator+"driver"+File.separator+"chromedriver.exe");
				driver = new ChromeDriver(options);
			} 
			//If the browser is Mozilla Firefox
			else if (browser.equalsIgnoreCase("firefox")) 
			{
				driver = new FirefoxDriver(dc);
			} 
			//If the browser is Internet explorer
			else if (browser.equalsIgnoreCase("iexplore")) 
			{
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + File.separator+"driver"+File.separator+"IEDriverServer.exe");
				driver = new InternetExplorerDriver(dc);
			}
			//If the Execution is to be done through Headless Driver HtmlUnit
			else if (browser.equalsIgnoreCase("HTMLUnitDriver"))
			{
				driver = new HtmlUnitDriver();
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//Maximize the window
			driver.manage().window().maximize();
			// chromeDriver.get("http://www.google.com");
		} catch (Exception e) 
		{
			//MyLogger.log.debug("FAIL : Some problem in launching the driver. Please check the path");
			throw(e);
		}
		return driver;
	}

	/********************************************************************************************************************
	 * Function Name : stopDriver
	 * Parameters    : NA
	 * Return        : Returns Status of Type String (Pass Status if the Driver is stopped)
	 * Purpose       : To close the driver(close webpage) of the browser after Execution.
	 * 
	 *******************************************************************************************************************/
	public static String stopDriver() {

		if (!(driver == null)) {
			driver.close();
		}
		else {
			return ("FAIL : No driver instance to stop");
		}
		return ("PASS");
	}
}
