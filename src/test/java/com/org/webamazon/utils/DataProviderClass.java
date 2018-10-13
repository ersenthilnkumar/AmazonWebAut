package com.org.webamazon.utils;

import org.testng.annotations.*;

public class DataProviderClass 
{
	public DataProviderClass() 
	{
		
	}
	
	/********************************************************************************************************************
	 * Function Name : basicinfo
	 * Parameters    : NA
	 * Return        : Object 2 dimensional array of Static type
	 * Purpose       : Returns the basic info from the excel files - first sheet
	 * @throws Exception 
	 * 
	 *******************************************************************************************************************/
	@DataProvider
	public static Object[][] basicinfo() throws Exception 
	{
	//	return ReadExcel.getData("Basic Info", "Basic Info");
		return ReadExcel.getBasicData("Basic Info", "Basic Info");
	}
	
	/********************************************************************************************************************
	 * Function Name : basicinfo
	 * Parameters    : NA
	 * Return        : Object 2 dimensional array of Static type
	 * Purpose       : Returns the books info from the excel files - second sheet
	 * @throws Exception 
	 * 
	 *******************************************************************************************************************/

	@DataProvider
	public static Object[][] bookinfo() throws Exception 
	{
		return ReadExcel.getData("Books Info", "Books Info");
		
	}

}
