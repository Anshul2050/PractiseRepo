package com.nop.baseTest4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.nop.pageLayer4.HomePage;

public class BaseTest4 {

	public WebDriver driver;
	public Properties prop;
	public HomePage hp_obj;
	public static Logger logger;
     
	@BeforeTest
	public void start() {

		logger = Logger.getLogger("Nop Commer Test Report");
		PropertyConfigurator.configure("Log4jfile.properties");
		logger.info("Test Execution Started");

	}
    
	@AfterTest
	public void finish() {

		logger.info("Test Execution stopped");

	}

	public WebDriver initialiseBrowser() {
		
		String configPath = System.getProperty("user.dir") + "\\src\\test\\resources\\configs\\config.properties";
		String browserName = null;
		try {
			FileInputStream fis = new FileInputStream(configPath);
			prop = new Properties();
			prop.load(fis);
			browserName = prop.getProperty("browser");

		} catch (Exception e) {

			e.printStackTrace();
		}
		System.out.println(browserName);

		if (browserName.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();
			logger.info("Chrome Browser Initialised");

		}

		if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			logger.info("FireFox Browser Initialised");
		}

		if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			logger.info("Edge Browser Initialised");
		}

		if (browserName.equalsIgnoreCase("ie")) {
			driver = new InternetExplorerDriver();
			logger.info("InternetExplorer Browser Initialised");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.info("Browser Opened");
		return driver;

	}

	public String getScreenshot(String testCase, WebDriver driver) {
		logger.info("Taking ScreenShot");
		String desPath = System.getProperty("user.dir") + "\\Test_Output\\";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File(desPath + testCase + ".png");
		try {
			FileHandler.copy(src, des);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Taking ScreenShot");
		return desPath + testCase + ".png";
        
	}

	@BeforeMethod
	public void launchApplication() {
		
		driver = initialiseBrowser();
		
		logger.info("Browser Initialised");
		driver.get("https://demo.nopcommerce.com/");
		hp_obj = new HomePage(driver);

	}

	@AfterMethod
	public void tearDown() {
		logger.info("Quiting the browser");
//		driver.quit();
		;
	}
}
