package com.bestbuy.project.BestBuyAutomation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogInToAccount {

	// WebDriver driver;
	Logger log = LogManager.getLogger(LogInToAccount.class);
	// ExtentReports reports;
	// ExtentTest test;
	public void login(WebDriver driver) {
		try {
			//WebDriverWait wait = new WebDriverWait(driver, 5);
//			WebElement accountLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("profileMenuWrap1")));
			Thread.sleep(2000); 
			WebElement accountLink =
			 driver.findElement(By.id("profileMenuWrap1"));
			log.info("Locate the account webelement");
			accountLink.click();

			 WebElement signin = driver
			 .findElement(By.xpath("//div[@id='profileMenu1']//a[@class='action-btn' and text()='Sign In']"));
			signin.click();

			// Sign in:
			driver.findElement(By.id("fld-e")).sendKeys("huruitech@hotmail.com");
			log.info("Typing user email");
			driver.findElement(By.id("fld-p1")).sendKeys("letmebuysth");
			log.info("Typing user password");
			driver.findElement(By.className("js-submit-button")).click();
			log.info("Clicked on sign-in button");

//			WebElement profile = driver.findElement(By.id("profileMenuWrap1"));
//			String actual = profile.getText();
//			String expected = "Hi, Rui";
//			// Verifying proper sign-in:
//			Assert.assertEquals(actual, expected);
//			log.info("varifying proper login");
//			profile.click();
//			log.info("Clicked on profile link");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// return driver;

	}
	public void login(WebDriver driver, Account account) {
		try {
			//WebDriverWait wait = new WebDriverWait(driver, 5);
//			WebElement accountLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("profileMenuWrap1")));
			Thread.sleep(2000); 
			WebElement accountLink =
			 driver.findElement(By.id("profileMenuWrap1"));
			log.info("Locate the account webelement");
			accountLink.click();

			 WebElement signin = driver
			 .findElement(By.xpath("//div[@id='profileMenu1']//a[@class='action-btn' and text()='Sign In']"));
			signin.click();

			// Sign in:
			driver.findElement(By.id("fld-e")).sendKeys(account.getAccount());
			log.info("Typing user email");
			driver.findElement(By.id("fld-p1")).sendKeys(account.getPwd());
			log.info("Typing user password");
			driver.findElement(By.className("js-submit-button")).click();
			log.info("Clicked on sign-in button");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


	}

}
