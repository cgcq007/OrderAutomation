package com.bestbuy.project.BestBuyAutomation;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoveShippingAddress {

	Logger log = LogManager.getLogger(RemoveShippingAddress.class);
	//ExtentReports reports;
	//ExtentTest test;
	
	
	public void removeShippingAddress(WebDriver driver) throws Exception {

		log.info("Remove Shipping Address---> Test case");
		WebElement ship = driver.findElement(By.xpath("//div[@id='profileMenu1']//a[contains(text(),'Shipping')]"));
		if (ship.isDisplayed() && ship.isEnabled()) {
			ship.click();
			log.info("Clicked on shipping address");
		}
		List<WebElement> allAddresses = driver
				.findElements(By.xpath("//div[@id='profileAddressBook']//a[@class='remove']"));
		System.out.println("Initial size of addresses: " + allAddresses.size());

		for (WebElement sd : allAddresses) {

			try {

				for (WebElement pl : allAddresses.subList(1, allAddresses.size())) {
					// for (WebElement pl : allAddresses) { //Left the primary
					// shipping address
					sd = pl; // Assigning 2nd element of the list
					System.out.println("Refreshing WebElement by updating the location");
					break;
				}
				sd.click(); // Removing 2nd element of the list
				log.info("Remove address");
				WebElement removeConfirm = driver
						.findElement(By.xpath("//div[contains(@class,'in')]//button[contains(@class,'btn-primary')]"));
				removeConfirm.click();
				driver.navigate().refresh();
				WebDriverWait wait = new WebDriverWait(driver, 2);
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
						By.xpath("//div[@id='profileAddressBook']//a[@class='remove']")));
				allAddresses = driver.findElements(By.xpath("//div[@id='profileAddressBook']//a[@class='remove']"));
				// System.out.println(allAddresses.size());

			} catch (StaleElementReferenceException e) {
				System.out.println("All the shipping addresses are deleted but the primary one!");
			}
		}

	}
}
