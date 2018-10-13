package com.org.webamazon.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.org.webamazon.utils.Util;

public class Amazon_SearchResultPage extends BasePage
{
	
	@FindBy(xpath = "//span[@id = 's-result-count']")	
	WebElement textSearchResult;
	
	
	public Amazon_SearchResultPage()
	{
		super();
	}
	/********************************************************************************************************************
	 * Function Name : isAtGAXHomePage
	 * Parameters    : Nothing
	 * Return        : Boolean
	 * Purpose       : Check whether Search Page list was displayed
	 * 
	 *******************************************************************************************************************/
	public boolean isAtAmazonSerchPage() 
	{
		return Util.isElementDisplayed(textSearchResult);
	}
	
	/*************************************************************************************************************************
	 * Function Name : selectBook
	 * Parameters    : book name
	 * Return        : Books Detail Page components
	 * Purpose       : To select a given book
	 *************************************************************************************************************************/
	public Amazon_BookPage selectBook(String bookName) 
	{
		try 
		{
			// click the given book
			driver.findElement(By.xpath("//h2[text()= '"+bookName+"']")).click();
/*			List<WebElement> elements =  
			elements.get(0).click();*/
			Util.delay(1000);
			log.info("Book was selected");
		}
		catch (Exception e) 
		{
			log.error("Book was not selected");
		}
		return new Amazon_BookPage();
	}
}
