package com.org.webamazon.pages;

import com.org.webamazon.utils.Driver;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;

public class BasePage extends Driver 
{
	public Logger log;
	/********************************************************************************************************************
	 * Function Name : BasePage
	 * Parameters    : Nothing
	 * Return        : NA
	 * Purpose       : loading elements and getting logger object
	 * 
	 *******************************************************************************************************************/
	public BasePage() 
	{
		loadElements();
		log = Logger.getLogger("devpinoyLogger");
	}
	/********************************************************************************************************************
	 * Function Name : loadElements
	 * Parameters    : Nothing
	 * Return        : void
	 * Purpose       : Initialize the driver 
	 * 
	 *******************************************************************************************************************/
	public void loadElements()
	{
		PageFactory.initElements(driver, this);
	}

}