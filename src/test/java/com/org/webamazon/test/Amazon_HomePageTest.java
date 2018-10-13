package com.org.webamazon.test;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.org.webamazon.pages.Amazon_BookPage;
import com.org.webamazon.pages.Amazon_HomePage;
import com.org.webamazon.utils.DataProviderClass;
import com.relevantcodes.extentreports.LogStatus;
import java.util.ArrayList;
import java.util.Hashtable;


public class Amazon_HomePageTest extends BasePageTest
{
	//Constructors to receive the global variables all test cases
	@Factory(dataProviderClass = DataProviderClass.class, dataProvider = "basicinfo")
	public Amazon_HomePageTest(Hashtable<String, String> data) 
	{
		this.cutomerName = data.get("Customer Name");
		this.searchkey  = data.get("Search Key");
		this.hubURL = data.get("Hub URL");
		this.pcuser = data.get("PC User");
		this.testenvironment = data.get("Environment");
	}

	/*************************************************************************************************************************
	 * Function Name : beforemethod
	 * Parameters    : none
	 * Return        : none
	 * Purpose       : TestNG annotation to do primary tasks before Home Page test cases execution. 
	 *                 Navigate to Amazon home page and do a book search
	 *************************************************************************************************************************/
	@BeforeMethod	
	public void beforemethod()
	{
		try 
		{
			amazonhomePg = new Amazon_HomePage();
			// Check home page is loaded
			if(!amazonhomePg.isAtAmazonHomePage())
			{
				// Navigate to home page if failed
				amazonhomePg.openPage(hubURL);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*************************************************************************************************************************
	 * Function Name : searchBookKeyword
	 * Parameters    : NONE
	 * Return        : NONE
	 * Purpose       : Search a specific item in a hme page 
	 *************************************************************************************************************************/
	@Test
	public void searchBookKeyword()
	{
		String result = "";
		try 
		{
			amazonhomePg = new Amazon_HomePage();			
			amazonhomePg.doSearchBook(this.searchkey);
		}
		catch (Exception e) 
		{
			//Handling exception if any error occurs in addSkillsforAgent method.
			System.out.println(e);
		} 
	}
	
	/*************************************************************************************************************************
	 * Function Name : searchBookKeyword
	 * Parameters    : NONE
	 * Return        : NONE
	 * Purpose       : Purchase a gift card in a home page
	 *************************************************************************************************************************/
	@Test
	public void purchaseGiftCard()
	{
		//TO DO - Out of scope for my given test scenario so skipped
	}

	/*************************************************************************************************************************
	 * Function Name : aftermethod
	 * Parameters    : none
	 * Return        : none
	 * Purpose       : This method could be used to navigate backs to functionality/modules home page example Gift Cards page if any in amazon
	 *************************************************************************************************************************/
	@AfterMethod
	public void aftermethod(ITestResult result) throws Exception
	{
		try 
		{
			amazonhomePg = new Amazon_HomePage();
			//Navigate to Amazon home page
			amazonhomePg.gotoAmazonHomePage();
			//Reporting test results to extent report
			extent.endTest(test);
			extent.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
