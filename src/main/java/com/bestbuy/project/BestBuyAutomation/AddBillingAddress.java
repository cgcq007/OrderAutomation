package com.bestbuy.project.BestBuyAutomation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AddBillingAddress {
	Logger log = LogManager.getLogger(AddBillingAddress.class);

	public void fillBillingAdress(WebDriver driver, Account account) {
		try {

			driver.findElement(By.id("payment.billingAddress.firstName")).sendKeys(account.getFirstName());
			driver.findElement(By.id("payment.billingAddress.lastName")).sendKeys(account.getLastName());
			driver.findElement(By.id("payment.billingAddress.street")).sendKeys(account.getAddress());
			Select sel = new Select(driver.findElement(By.id("payment.billingAddress.state")));
			sel.selectByValue(account.getState());
			driver.findElement(By.id("payment.billingAddress.zipcode")).sendKeys(account.getZip());
			driver.findElement(By.id("payment.billingAddress.city")).sendKeys(account.getCity());

		} catch (Exception ex) {
		}
	}
}
