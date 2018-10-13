package com.org.webamazon.test;

import java.util.Hashtable;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.org.webamazon.pages.Amazon_HomePage;
import com.org.webamazon.pages.Amazon_SearchResultPage;
import com.org.webamazon.utils.DataProviderClass;

public class Amazon_SearchResultPageTest extends BasePageTest
{
	//Constructors to receive the global variables all test cases
	@Factory(dataProviderClass = DataProviderClass.class, dataProvider = "basicinfo")
	public Amazon_SearchResultPageTest(Hashtable<String, String> data) 
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
	 * Purpose       : TestNG annotation to do primary tasks before Search Page test case execution. 
	 *                 Navigate to Amazon home page and do a book search
	 *************************************************************************************************************************/
	@BeforeMethod	
	public void beforemethod()
	{
		try 
		{
			amazonhomePg = new Amazon_HomePage();
			amazonsearchresultPg = new Amazon_SearchResultPage();
			//Checking is the home page loaded
			if(amazonhomePg.isAtAmazonHomePage())
			{
				//Search a book using a keyword
				amazonhomePg.doSearchBook(searchkey);
				//Confirming search results are produced
				if(!amazonsearchresultPg.isAtAmazonSerchPage())
				{
					// If search result are not produced retry again
					amazonhomePg.doSearchBook(searchkey);
				}			
			}
			else 
			{
				// If home page did not load it will retry again
				amazonhomePg.openPage(hubURL);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*************************************************************************************************************************
	 * Function Name : selectSpecificSearchResult
	 * Parameters    : NONE
	 * Return        : NONE
	 * Purpose       : If specific item in search result need to be selected means we need to try this function 
	 *************************************************************************************************************************/
	@Test 
	public void selectSpecificSearchResult()
	{
		//TO DO - Out of scope for my given test scenario so skipped
	}
	
	/*************************************************************************************************************************
	 * Function Name : aftermethod
	 * Parameters    : none
	 * Return        : none
	 * Purpose       : This method could be used to navigate backs to functionality/modules home page example Search page if any in amazon
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
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
