package com.org.webamazon.test;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.org.webamazon.pages.Amazon_BookPage;
import com.org.webamazon.pages.Amazon_HomePage;
import com.org.webamazon.pages.Amazon_SearchResultPage;
import com.org.webamazon.utils.DataProviderClass;
import com.org.webamazon.utils.MyLogger;
import com.relevantcodes.extentreports.LogStatus;

public class Amazon_BookPageTest  extends BasePageTest
{
	//Constructors to receive the global variables all test cases - Dataprovider reads the data a from the input excel sheet
	@Factory(dataProviderClass = DataProviderClass.class, dataProvider = "basicinfo")
	public Amazon_BookPageTest(Hashtable<String, String> data) 
	{

		this.cutomerName = data.get("Customer Name");
		this.searchkey = data.get("Search Key");
		this.hubURL = data.get("Hub URL");
		this.pcuser = data.get("PC User");
		this.testenvironment = data.get("Test Env");
	}

	/*************************************************************************************************************************
	 * Function Name : beforemethod
	 * Parameters    : none
	 * Return        : none
	 * Purpose       : TestNG annotation to do primary tasks before Books Page test cases execution. 
	 *                 Navigate to Amazon home page, do a book search and select a book
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
	 * Function Name : searchBookKeyword
	 * Parameters    : NONE
	 * Return        : NONE
	 * Purpose       : Verifiy the detail of a selected book. This module will repeats as much number of time Input excel data 
	 *                 contains the test cases
	 *************************************************************************************************************************/
	@Test (dataProviderClass = DataProviderClass.class, dataProvider = "bookinfo", enabled = true)
	public void verifyBookDeatails(Hashtable<String, String> data)
	{
		// variables to hold the paremeters (like name, price etc to be verified. Values will be collected thro data providers from input data excel sheet
		String searchKey = data.get("Search Key");
		String bookName = data.get("Book Name");
		String bookPaperBackPrice = data.get("Paper Back");
		String bookKnindlePrice = data.get("Kindle Price");
		String bookAuthor1 = data.get("Author 1");
		String bookAuthor2 = data.get("Author 2");
		String bookEdition = data.get("Edition");
		String bookISBN = data.get("IBSN");
		String result = "";
		
		try 
		{
			amazonbookPg = new Amazon_BookPage();
			amazonsearchresultPg = new Amazon_SearchResultPage();
			if(amazonsearchresultPg.isAtAmazonSerchPage())
			{
				amazonsearchresultPg.selectBook(bookName);
				if(amazonbookPg.isAtAmazonScearchBookSelectedPage())
				{
					result = amazonbookPg.verifyBookInfo(bookName, bookPaperBackPrice, bookKnindlePrice, bookAuthor1, bookAuthor2, bookEdition, bookISBN);
					if(result.equals(""))
					{
						MyLogger.log.info( bookName+" book name details are verified successfully");
						test.log(LogStatus.PASS, "Book details - Name, Price, Authors, Edition and ISBN verification.<br><font color=\"green\"><b>Success Message:</font></b><br>Values for thr book - <font color=\"blue\"><i>"+bookName+"</i></font> - Name, Price, Authors, Edition and ISBN are verified <font color=\"green\"><b>successfully</font></b>");
					}
					else
					{
						MyLogger.log.info( bookName+" book name details are incorrrect");
						test.log(LogStatus.FAIL, "<font color=\"red\"><b>Error Message:</font></b><br>Book name - <font color=\"blue\"><b><i>"+bookName+"</i></b></font> details are <font color=\"red\"><b>incorrect.</b><br>"+result+"</br> </font>");
						Assert.assertEquals("Book details configurations are incorrect", "Book details should be correct");
					}
				}
				else 
				{
					MyLogger.log.info( bookName+" book was not displayed");
					test.log(LogStatus.FAIL, "<font color=\"red\"><b>Error Message:</font></b><br>Book name - <font color=\"blue\"><b><i>"+bookName+"</i></b></font>was not <font color=\"red\"><b>displayed in search result.</b><br>"+result+"</br> </font>");
					Assert.assertEquals("Book was not listed in search result", "Book should be listed in search result");
				}
			}
			else
			{
				MyLogger.log.info( bookName+" book name search result was not displayed");
				test.log(LogStatus.FAIL, "<font color=\"red\"><b>Error Message:</font></b><br>Book name - <font color=\"blue\"><b><i>"+bookName+"</i></b></font> search was <font color=\"red\"><b>failed.</b><br>"+result+"</br> </font>");
				Assert.assertEquals("Search result did not happen for the given book", "Search result should display for the given book");
			}			
		}
		catch (Exception e) 
		{
			//Handling exception if any error occurs in Verifing the book info.
			System.out.println(e);
		} 
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
			amazonhomePg.gotoAmazonHomePage();
			//Reporting test results to extent report
			extent.endTest(test);
			extent.flush();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
