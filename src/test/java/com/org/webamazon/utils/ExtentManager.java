package com.org.webamazon.utils;
import com.org.webamazon.test.BasePageTest;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager extends BasePageTest {
	private static ExtentReports extent;

	/********************************************************************************************************************
	 * Function Name : getReporter
	 * Parameters    : filePath of Type String
	 * Return        : Extent of Type ExtentReports
	 * Purpose       : To get Extent reports Files from the Given location
	 * 
	 *******************************************************************************************************************/
	public synchronized static ExtentReports getReporter(String filePath, String hostname, String environment) {
		if (extent == null) {
			extent = new ExtentReports(filePath, true);
			extent.addSystemInfo("Host Name", hostname ).addSystemInfo("Environment", environment);
		}
		return extent;
	}
}

