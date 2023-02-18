package com.nop.pageLayer4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public WebDriver driver;

	public HomePage(WebDriver driver) {

		PageFactory.initElements(driver, this);
		
		this.driver=driver;
	}

	
	public void getUrl() {
		
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
	}
	
}
