package com.bestbuy.project.BestBuyAutomation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddShippingTest {
	
	
	Logger log = LogManager.getLogger(LogInTest.class);
	WebDriver driver;
	String browserName="chrome";
	String baseUrl="http://www.bestbuy.com";
	
	AddShippingAddress addAddress= new AddShippingAddress();
	LogInToAccount la = new LogInToAccount();
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
	public void testAddAddress(){
		la.login(driver);
		log.info("logged in before adding Address");
		addAddress.addShippingAddress(driver);
		log.info("Added address in Add Address testCase");
		
	}

}
