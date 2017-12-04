package com.bestbuy.project.BestBuyAutomation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClickingOnItemsUnderProducts {
	
	WebDriver driver;
	String baseUrl="https://www.bestbuy.com/";
	Logger log=LogManager.getLogger(ClickingOnItemsUnderProducts.class);
	String browser="chrome";

	
	BrowserSetUp setup = new BrowserSetUp();

	@BeforeClass
	public void setUpLoad(){
		
	driver=setup.setupBrowser(driver, browser, baseUrl);

		
	}

	@AfterClass
	public void clearUp() throws Exception {
		Thread.sleep(2000);
		log.info("Hardcode wait for 2 sec"); 
		driver.quit();
		log.info("Quit browser");

	}
	
	@Test
	public void ClickingProducts(){
		
		WebElement ele= driver.findElement(By.id("menu0"));
		ele.click();
		log.info("Clicked on products tab");
		
	}

}
