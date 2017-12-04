package com.bestbuy.project.BestBuyAutomation;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddShippingAddress {
	
	Logger log = LogManager.getLogger(AddShippingAddress.class);
	//ExtentReports reports;
	//ExtentTest test;
	
	public void addShippingAddress(WebDriver driver) {
		log.info("Add shipping address ----> Test case");
		WebElement ship = driver.findElement(By.xpath("//div[@id='profileMenu1']//a[contains(text(),'Shipping')]"));
		if (ship.isDisplayed() && ship.isEnabled()) {
			ship.click();
			log.info("Clicked on shipping address");
		}

		//WebElement addAddress = driver.findElement(By.xpath("//*[@id='profileAddressBook']//a[@href='#add']"));
		WebElement addAddress=  driver.findElement(By.className("addAddress")); // className of initial
		// Adding address:
		// 1. Check for Add an address

		if (addAddress.getText().equalsIgnoreCase("Add An Address")) {
			addAddress.click();
			driver.findElement(By.id("firstName")).sendKeys("FirstName");
			driver.findElement(By.id("lastName")).sendKeys("LastName");
			driver.findElement(By.id("addressLine1")).sendKeys("1100, test drive");
			driver.findElement(By.id("city")).sendKeys("Duluth");

			// Select state from drop-down option:
			List<WebElement> states = driver.findElements(By.xpath("//select[@id='state']/option"));

			for (WebElement i : states) {
				String value = i.getAttribute("value");
				if (value.equalsIgnoreCase("MN")) {
					i.click();
					System.out.println(value);
					break;
				}

			}

			driver.findElement(By.id("postalCode")).sendKeys("55805");
			driver.findElement(By.name("phoneNumber")).sendKeys("2185918060");
			driver.findElement(By.name("submit")).click();

			// Handling address verification:
			// -----> Address Couldn't be verified, Keep address as entered?
			// <--------------------------
			WebElement keepAddressAsEntered = driver
					.findElement(By.xpath("//div[@id='profileAddressBook']//button[contains(@class,'btn-primary')]"));

			keepAddressAsEntered.click();

		}

		// 2. Check for Add another address
//		else if (driver
//				.findElement(
//						By.xpath("//*[@id='profileAddressBook']//a[(@href='#add') and contains(@class,'btn-primary')]"))
				
		else if (driver.findElement(By.className("addAddress"))
		.getText().equalsIgnoreCase("Add Another Address")) {
			// Looping to add multiple address
			int i = 0;
			while (i < 2) {
				driver.findElement(By.xpath("//*[@id='profileAddressBook']//a[@href='#add']")).click();
				driver.findElement(By.id("firstName")).sendKeys("DFG");
				driver.findElement(By.id("lastName")).sendKeys("Hkl");
				driver.findElement(By.id("addressLine1")).sendKeys("6789, New test drive");
				driver.findElement(By.id("city")).sendKeys("Duluth");

				// Select state from drop-down option:
				List<WebElement> states = driver.findElements(By.xpath("//select[@id='state']/option"));

				for (WebElement p : states) {
					String value = p.getAttribute("value");
					if (value.equalsIgnoreCase("MN")) {
						p.click();
						break;
					}

				}

				driver.findElement(By.id("postalCode")).sendKeys("55805");
				driver.findElement(By.name("phoneNumber")).sendKeys("2185918060");
				driver.findElement(By.name("submit")).click();

				// Handling address verification:
				// -----> Address Couldn't be verified, Keep address as entered?
				// <--------------------------
				WebElement contr = driver.findElement(
						By.xpath("//div[@id='profileAddressBook']//button[contains(@class,'btn-primary')]"));
				contr.click();

				i++;
			}

		}

	}


}
