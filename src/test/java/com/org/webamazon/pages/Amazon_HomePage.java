package com.org.webamazon.pages;
import java.awt.List;
import com.org.webamazon.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.ArrayList;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import com.org.webamazon.utils.*;

public class Amazon_HomePage extends BasePage
{
	//various web components to select
	@FindBy(xpath = "//span[(text() = 'Amazon')]")	
	WebElement logoAmazon;

	@FindBy(xpath = "//select[@title = 'Search in']")
	WebElement dropdownCategories;

	@FindBy(xpath = "//option[text() = 'Books']")
	WebElement selectBookCategory;

	@FindBy(xpath = "//input[@id = 'twotabsearchtextbox']")
	WebElement searchBook;

	@FindBy(xpath = "//input[@value = 'Go']")
	WebElement buttonSearch;


	@FindBy(xpath = "//span[@id= 's-result-count']")
	WebElement textSearchresultCount;

	@FindBy(xpath = "//li[contains(@class, 's-result-item celwidget')]")
	WebElement listSearchResult;

	public Amazon_HomePage()
	{
		super();
	}
	/********************************************************************************************************************
	 * Function Name : isAtGAXHomePage
	 * Parameters    : Nothing
	 * Return        : Boolean
	 * Purpose       : Check whether we are in Amazon Homepage by verifying with gaxLogo
	 * 
	 *******************************************************************************************************************/
	public boolean isAtAmazonHomePage() 
	{
		return Util.isElementDisplayed(logoAmazon);
	}

	/********************************************************************************************************************
	 * Function Name : openPage
	 * Parameters    : url
	 * Return        : page
	 * Purpose       : Open Amazon home page
	 * 
	 *******************************************************************************************************************/

	public Amazon_HomePage openPage(String url) 
	{
		try 
		{
			driver.get(url);
			Util.delay(2000);
			if(isAtAmazonHomePage())
				log.info("Opened Amazon Home Page");
			else
				log.error("Not opened Amazon Home Page");
		} 
		catch (Exception e) 
		{
			log.error("Amazon Home Page not loaded");
		}
		return new Amazon_HomePage();
	}

	/********************************************************************************************************************
	 * Function Name : openPage
	 * Parameters    : book key word
	 * Return        : Search page components
	 * Purpose       : Searches in Amazon home page
	 * 
	 *******************************************************************************************************************/

	public Amazon_SearchResultPage doSearchBook(String bookType)
	{
		boolean result = false;
		try 
		{
			dropdownCategories.click();
			Util.delay(500);
			selectBookCategory.click();
			Util.delay(500);
			searchBook.sendKeys(bookType);
			Util.delay(500);
			buttonSearch.click();
			Util.delay(2000);
			if(Util.isElementDisplayed(textSearchresultCount))
				result = true;
		} 
		catch (Exception e) 
		{
			log.error("Search did not happen");
		}
		return new Amazon_SearchResultPage();
	}


	/********************************************************************************************************************
	 * Function Name : openPage
	 * Parameters    : url
	 * Return        : nothing 
	 * Purpose       : Go to  Amazon home page from other page
	 * 
	 *******************************************************************************************************************/
	public void gotoAmazonHomePage() 
	{
		try 
		{
			logoAmazon.click();
			if(isAtAmazonHomePage())
				log.info("Home page was selected");
			else
				log.error("Home page was not selected");
		}
		catch (Exception e) 
		{
			log.error("Home page was not selected");
		}
	}
}
