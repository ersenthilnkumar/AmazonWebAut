package com.org.webamazon.utils;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util extends Driver
{
	/********************************************************************************************************************
	 * Function Name : isElementDisplayed
	 * Parameters    : element of type WebElement
	 * Return        : Return Boolean Type Result as True if element is found else False.
	 * Purpose       : To Verify if the particular web element is displayed in a web page with a wait time of 20 seconds
	 * 
	 *******************************************************************************************************************/
	public static boolean isElementDisplayed(WebElement element) 
	{
		boolean result = false;
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			//Wait until element is displayed.
			wait.until(ExpectedConditions.visibilityOf(element));
			result = true;
		} catch (Exception e)
		{
			result = false;
		}
		return result;
	}
	
	/********************************************************************************************************************
	 * Function Name : delay
	 * Parameters    : wait of type long is the parameter
	 * Return        : void
	 * Purpose       : To increase the wait time for the function to execute
	 * 
	 *******************************************************************************************************************/
	public static void delay(long wait) 
	{
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*************************************************************************************************************************
	 * Function Name : getscreenshot
	 * Parameters    : photo name saved path
	 * Return        : file path for screen shot
	 * Purpose       :  to take sceen shot when test case was failed and attach it in report
	 *************************************************************************************************************************/
	public static String getscreenshot(String name) 
	{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "\\screenshots\\" + name + ".png";
		File newFile = null;
		// The below method will save the screen shot in d drive with name
		// "screenshot.png"
		try {
			//Copying the scrFile in the Destination filePath.
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (scrFile.isFile()) {
			//Path of the screenshot is returned if scrFile File is found in Destination Path.
			return filePath;
		}
		//No file message is returned if the scrFile is not found in the Destination Path
		return "No file";
	}
}
