package com.bestbuy.project.BestBuyAutomation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AddNewItem {
	Logger log = LogManager.getLogger(AddNewItem.class);
	// ExtentReports reports;
	// ExtentTest test;

	public void add(WebDriver driver, String sku) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			//search
			WebElement search = driver.findElement(By.cssSelector("#gh-search-input"));
			wait.until(ExpectedConditions.elementToBeClickable(search));
			log.info("Locate the search webElement");
			search.sendKeys(sku);
			search.sendKeys(Keys.ENTER);
			
			//add
			
			WebElement add = driver.findElement(By.className("add-to-cart-button"));
			add.click();
			


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// return driver;
	}
}
