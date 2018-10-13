package com.org.webamazon.pages;

import java.util.List;

import org.apache.poi.xwpf.usermodel.TOC;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.org.webamazon.utils.Util;

public class Amazon_BookPage extends BasePage
{
	//various web components to select
	@FindBy(xpath = "//a[@id = 'breadcrumb-back-link']")
	WebElement textBackToSearchResult;
	
	@FindBy(xpath = "//span[@id = 'productTitle']")
	WebElement textbookName;	
	
	@FindBy(xpath = "//span[@class = 'a-size-base mediaTab_subtitle']")
	WebElement textAllPrices;	
	
	@FindBy(xpath = "//a[@class = 'a-link-normal contributorNameID']")
	WebElement textAllAuthors;
	
	@FindBy(xpath = "//span[@id = 'bookEdition']")
	WebElement textbookEdition;
	
	
	@FindBy(xpath = "//span[@class = 'a-size-base a-color-base']")
	WebElement textbookISBNs;
	
	public Amazon_BookPage()
	{
		super();
	}

	/********************************************************************************************************************
	 * Function Name : isAtAmazonScearchBookSelectedPage
	 * Parameters    : Nothing
	 * Return        : Boolean
	 * Purpose       : Check whether given book was selected
	 * 
	 *******************************************************************************************************************/
	public boolean isAtAmazonScearchBookSelectedPage() 
	{
		return Util.isElementDisplayed(textBackToSearchResult);
	}
	
	/********************************************************************************************************************
	 * Function Name : openPage
	 * Parameters    : url
	 * Return        : string - verification info
	 * Purpose       : Verify book details
	 * 
	 *******************************************************************************************************************/
	public String verifyBookInfo(String bookName, String bookPrice, String kindlePrice, String bookAuthor1,String bookAuthor2, String bookEdition, String bookISBN)
	{	
		// To form error message else empty for success
		String result = "";
		try
		{	
			//verify book name
			if(!textbookName.getText().equalsIgnoreCase(bookName))
			{
				result = result+"Mismatch in Book Name. Actual: "+textbookName.getText()+";  Expected:"+bookName+";<br> ";
			}
			
			//verify prices
			List<WebElement> prices = driver.findElements(By.xpath("//span[@class = 'a-size-base mediaTab_subtitle']"));
			if(prices.size()!= 0)
			{
				if(!prices.get(0).getText().equalsIgnoreCase(kindlePrice))
				{
					result = result+"Mismatch in Kindle Price. Actual: "+prices.get(0).getText()+";  Expected:"+kindlePrice+";<br> ";
				}	
				if(!prices.get(1).getText().equalsIgnoreCase(bookPrice))
				{
					result = result+"Mismatch in Kindle Price. Actual: "+prices.get(1).getText()+";  Expected:"+bookPrice+";<br> ";
				}	
			}
			else
			{
				result = result+"Prices not found for books";
			}
			
			//verify authors
			List<WebElement> authors = driver.findElements(By.xpath("//a[@class = 'a-link-normal contributorNameID']"));
			if(authors.size()!= 0)
			{
				if(!authors.get(0).getText().equalsIgnoreCase(bookAuthor1))
				{
					result = result+"Mismatch in Author1 name. Actual: "+authors.get(0).getText()+";  Expected:"+bookAuthor1+";<br> ";
				}	
				if(!authors.get(1).getText().equalsIgnoreCase(bookAuthor2))
				{
					result = result+"Mismatch in Author2 name. Actual: "+authors.get(1).getText()+";  Expected:"+bookAuthor2+";<br> ";
				}	
			}
			else
			{
				result = result+"Authors not found for books";
			}
			
			//verify edition name
			if(!textbookEdition.getText().equalsIgnoreCase(bookEdition))
			{
				result = result+"Mismatch in Book Edition. Actual: "+textbookEdition.getText()+";  Expected:"+bookEdition+";<br> ";
			}
			
			//verify ISBN number
			List<WebElement> isbns = driver.findElements(By.xpath("//span[@class = 'a-size-base a-color-base']"));
			if(isbns.size()!= 0)
			{
				if(!isbns.get(0).getText().equalsIgnoreCase(bookISBN))
				{
					result = result+"Mismatch inISBN name. Actual: "+isbns.get(0).getText()+";  Expected:"+bookISBN+";<br> ";
				}	
			}
			else
			{
				result = result+"ISBN not found for books";
			}
		}
		catch (Exception e)
		{
			log.info("Book Info Verification failed");		
		}	
		return result;
	}

}
