package com.nop.utilities4;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.nop.baseTest4.BaseTest4;

public class ListenerSetup extends BaseTest4 implements ITestListener {

	ExtentReports extent = ExtentReport4.getExtentReport();
	ExtentTest test ;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe
	
	
	
	@Override
	public void onTestStart(ITestResult result) {
		logger.info("test Started");
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);  //unique thread id(ErrorValidationTest)->test
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
		extentTest.get().fail(result.getThrowable());//
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String ssPath=getScreenshot(result.getMethod().getMethodName(),driver);
		System.out.println(ssPath);
		extentTest.get().addScreenCaptureFromPath(ssPath, result.getMethod().getMethodName());
		logger.info("test Failed ..Took ScreenShot");
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().skip(result.getThrowable());
		
		logger.info("test skipped ...");
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		logger.info("Result locked in the report");
	}

}
