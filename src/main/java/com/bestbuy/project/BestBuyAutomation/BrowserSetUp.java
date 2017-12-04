package com.bestbuy.project.BestBuyAutomation;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserSetUp {
//	WebDriver driver;
//	String baseUrl;
	CloseModalAndAlert modalClose = new CloseModalAndAlert();
	Logger log = LogManager.getLogger(BrowserSetUp.class);


	public WebDriver setupBrowser(WebDriver driver, String browserName, String baseUrl) {

		if (browserName.equalsIgnoreCase("chrome")) {
//			System.setProperty("webdriver.chrome.driver",
//					"C:\\Program Files (x86)\\Google\\Chrome\\Applicationchromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			//driver.get(baseUrl);

		} else if (browserName.equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.gecko.driver",
//					"C:\\Users\\MdShaifulIslam\\Documents\\SeleniumBrowserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			//driver.get(baseUrl);

		}
				
		
		//driver = .setUp(driver, browser);
		log.info("Browser setup");
		driver.get(baseUrl);
		log.info("Loading baseUrl");

		if (driver != null) {
			log.info("got driver in before class");
		} 

		
		modalClose.closeModalIfPresent(driver);
		log.info("Handling modal popup");
//		modalClose.alertHandle(driver);
//		log.info("Check for alert");

		return driver;

	}

}
