package com.nop.utilities4;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport4 {

	public static ExtentReports getExtentReport() {
		
		String reportPath = System.getProperty("user.dir")+"\\Test_Output\\extentR.html";
		
		File path = new File (reportPath);
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); 
		
		reporter.config().setDocumentTitle("Nop Commerce Report");
		reporter.config().setReportName("NOP Application Test Report");
		
		ExtentReports extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Anshul");
		return extent;
		
		
		
	}
	
	
}
