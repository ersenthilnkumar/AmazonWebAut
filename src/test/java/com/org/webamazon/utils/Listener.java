package com.org.webamazon.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.relevantcodes.extentreports.LogStatus;

public class Listener extends Driver implements ITestListener {

	/********************************************************************************************************************
	 * Function Name : onTestStart
	 * Parameters    : result of type ITestResult
	 * Return        : void
	 * Purpose       : Start the extent report and assign category and log the status in log4j
	 * 
	 *******************************************************************************************************************/
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.startTest(result.getName()).assignCategory(result.getTestClass().toString().split("\\.")[4].replace("Test]", ""));
		test.log(LogStatus.INFO, "Test "+result.getName()+" started");
		MyLogger.log.info("Test "+result.getName()+" started");
	}

	/********************************************************************************************************************
	 * Function Name : onTestSuccess
	 * Parameters    : result of type ITestResult
	 * Return        : void
	 * Purpose       : Log the Test Result in Log4j and extent report on Test Success(Pass)
	 * 
	 *******************************************************************************************************************/
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, "Test passed");
		MyLogger.log.info("Test "+result.getName()+" passed");
		
	}

	/********************************************************************************************************************
	 * Function Name : onTestFailure
	 * Parameters    : result of type ITestResult
	 * Return        : void
	 * Purpose       : Log the Test Result in Log4j ,extent report on Test Failure(Fail) and get screenshot on failure.
	 * 
	 *******************************************************************************************************************/
	@Override
	public void onTestFailure(ITestResult result) 
	{
		String path = Util.getscreenshot(result.getName() + "_" + result.getThrowable().toString().split(":")[1]);
		test.log(LogStatus.FAIL, result.getThrowable().getMessage() + test.addScreenCapture(path));
		MyLogger.log.info("Test "+result.getName()+" failed");
		
	}

	/********************************************************************************************************************
	 * Function Name : onTestSkipped
	 * Parameters    : result of type ITestResult
	 * Return        : void
	 * Purpose       : Log the Test Result in Log4j and extent report when Test is skipped.
	 * 
	 *******************************************************************************************************************/
	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		MyLogger.log.info("Test "+result.getName()+" skipped");
		
	}

	/********************************************************************************************************************
	 * Function Name : onTestFailedButWithinSuccessPercentage
	 * Parameters    : result of type ITestResult
	 * Return        : void
	 * Purpose       : Need clarification <functionality incomplete>
	 * 
	 *******************************************************************************************************************/
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	/********************************************************************************************************************
	 * Function Name : onStart
	 * Parameters    : context of type ITestContext 
	 * Return        : void 
	 * Purpose       : Log info when onStart method is called(Need Clarification)
	 * 
	 *******************************************************************************************************************/
	@Override
	public void onStart(ITestContext context) {
		MyLogger.log.info("Calling onStart method");
		//TODO <functionality incomplete>
	}

	/********************************************************************************************************************
	 * Function Name : onFinish
	 * Parameters    : context of type ITestContext
	 * Return        : void 
	 * Purpose       : Log info when onFinish method is called(Need Clarification)
	 * 
	 *******************************************************************************************************************/
	@Override
	public void onFinish(ITestContext context) {
		MyLogger.log.info("Calling onFinish method");
		//TODO <functionality incomplete>
		
	}

}
