package com.bestbuy.project.BestBuyAutomation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class LogInTest {
	
	Logger log = LogManager.getLogger(LogInTest.class);
	WebDriver driver;
	String browserName="chrome";
	String baseUrl="http://www.bestbuy.com";
	
	
	LogInToAccount la= new LogInToAccount();
	BrowserSetUp setup= new BrowserSetUp();
	
	
	@BeforeClass
	public void setUp(){
		
		driver= setup.setupBrowser(driver, browserName, baseUrl);
	}
	
	@AfterClass
	public void tearDown() throws Exception{
		
		Thread.sleep(3000);
		driver.quit();
		
	}
	
	@Test
	public void testLogin(){
		la.login(driver);
		log.info("Login to bestbuy account");
		
		
	}

}
