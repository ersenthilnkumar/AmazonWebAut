package com.org.webamazon.test;

import com.org.webamazon.pages.Amazon_BookPage;
import com.org.webamazon.pages.Amazon_HomePage;
import com.org.webamazon.pages.Amazon_SearchResultPage;
import com.org.webamazon.utils.*;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;

public class BasePageTest extends Driver
{
	// Global Variables for basic_essential info which are needed in various class files
	public String cutomerName;
	public String hubURL;
	public String searchkey;
	public String pcuser;
	public String testenvironment;

	// all page call objects
	public Amazon_HomePage amazonhomePg;
	public Amazon_SearchResultPage amazonsearchresultPg;
	public Amazon_BookPage amazonbookPg;

	final String snapshotPath = System.getProperty("user.dir") + "\\screenshots";

	/*************************************************************************************************************************
	 * Function Name : setup (Before Suite)
	 * Parameters    : Nothing
	 * Return        : void
	 * Purpose       : To initialize the web driver and create the Extent reporting file 	 * 
	 *************************************************************************************************************************/
	@BeforeSuite
	public void setup() throws Exception
	{
		try 
		{
			// Start time calculation
			java.util.Date date1 = new java.util.Date();
			String dateStart= new Timestamp(date1.getTime()).toString();
			//Starting Driver
			driver = startDriver("chrome");
			MyLogger.log.info("In @BeforeSuite");
			//Logging Driver start information
			MyLogger.log.info("Driver started");
			// Getting Extent Results Reporter path
			String reportPath = System.getProperty("user.dir") + "\\logs\\Execution Report as on "+(dateStart).replaceAll(":", "-")+".html";
			extent = ExtentManager.getReporter(reportPath, pcuser, testenvironment);
		}
		catch (Exception e) 
		{
			MyLogger.log.error(e.getClass().toString(), e);
		}
	}

	/*************************************************************************************************************************
	 * Function Name : teardown (After Suite)
	 * Parameters    : Nothing
	 * Return        : void
	 * Purpose       : To stop the driver and close the extent reports	 
	 *************************************************************************************************************************/

	@AfterSuite
	public void teardown() throws ParseException 
	{
		try 
		{
			MyLogger.log.info("In @AfterSuite");
			//Ending driver session
			driver.close();
			driver.quit();
			// Closing report file
			extent.close();
			MyLogger.log.info("Driver stopped");
			MyLogger.log.info("Extent report closed");
		}
		catch (Exception e) 
		{
			MyLogger.log.error(e.getClass().toString(), e);
		}
	}

	/*************************************************************************************************************************
	 * Function Name : beforetest
	 * Parameters    : none
	 * Return        : none
	 * Purpose       : This method could be used to login(primarily) or navigate to home page of a website
	 *************************************************************************************************************************/
	@BeforeTest
	public void beforetest() 
	{
		try 
		{
			MyLogger.log.info("In @BeforeTest");
			amazonhomePg = new Amazon_HomePage();
			//Navigate to Home page
			amazonhomePg.openPage(hubURL);
		} 
		catch (Exception e) 
		{
			MyLogger.log.error(e.getClass().toString(), e);
		}
	}

	/*************************************************************************************************************************
	 * Function Name : aftertest
	 * Parameters    : none
	 * Return        : none
	 * Purpose       : This method could be used to logout(primarily) or navigate to home page of a website from another page after doing some testing
	 *************************************************************************************************************************/
	@AfterTest
	public void aftertest() 
	{
		try 
		{
			MyLogger.log.info("In @BAfterTest");
			amazonhomePg = new Amazon_HomePage();
			//Navigate to Home page
			amazonhomePg.openPage(hubURL);
		} 
		catch (Exception e) 
		{
			MyLogger.log.error(e.getClass().toString(), e);
		}
	}

	/*************************************************************************************************************************
	 * Function Name : beforeclass
	 * Parameters    : none
	 * Return        : none
	 * Purpose       : This method could be used to navigate to home page(primarily) of a website
	 *************************************************************************************************************************/

	@BeforeClass
	public void beforeClass()
	{
		try 
		{
			//TO DO - IF NECESSARY
		}
		catch (Exception e) 
		{
			MyLogger.log.error(e.getClass().toString(), e);
		}
	}

	/*************************************************************************************************************************
	 * Function Name : aftertest
	 * Parameters    : none
	 * Return        : none
	 * Purpose       : This method could be used to navigate to home page(primarily) of a website from another page after doing some testing
	 *************************************************************************************************************************/
	@AfterClass
	public void afterClass() 
	{
		try 
		{
			//TO DO - IF NECESSARY
		} 
		catch (Exception e)
		{
			MyLogger.log.error(e.getClass().toString(), e);
		}
	}		
}
