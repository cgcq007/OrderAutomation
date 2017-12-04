package com.bestbuy.project.BestBuyAutomation;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BestBuyHomePage {
	String browser="firefox";
	WebDriver driver;
	String baseUrl="https://www.bestbuy.com/";
	Logger log=LogManager.getLogger(BestBuyHomePage.class);
	
	BrowserSetUp setup= new BrowserSetUp();
	
	@BeforeClass
	public void loadSetUp() {
		driver=setup.setupBrowser(driver, browser, baseUrl);
	}

	@AfterClass
	public void clearUp() throws Exception {
		Thread.sleep(3000);
		log.info("Hardcode wait for 2 sec"); 
	//	driver.quit();
		log.info("Quit browser");

	}

	@Test()
	public void home() throws InterruptedException {
		
		
		//log.debug("debug debug");
			
	
		WebElement searchBox= driver.findElement(By.id("gh-search-input"));
		log.info("Locate the search box");
		WebElement ele=driver.findElement(By.className("hf-icon-search"));
		log.info("Locate the search button");
		
		searchBox.sendKeys("HP laptop");
		log.info("Typing search item");
		
		
		
		boolean boo=ele.isEnabled();
		if (boo==true){
			ele.click();
			log.info("Click on search box");
		}
		
	}

}
