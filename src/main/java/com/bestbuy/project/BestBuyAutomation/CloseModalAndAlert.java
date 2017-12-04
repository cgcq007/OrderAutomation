package com.bestbuy.project.BestBuyAutomation;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CloseModalAndAlert {
	
	
	
	
	public void alertHandle(WebDriver driver) {
		if (isAlertPresent(driver) == true) {
			System.out.println("Alert found!");
			driver.switchTo().alert().dismiss();

		}
	}

	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}
	

	public void closeModalIfPresent(WebDriver driver) {
		try {
			if (driver != null) {
				WebElement modal = driver.switchTo().activeElement();
				WebElement closeButton = modal.findElement(By.className("close"));
				closeButton.click();
				System.out.println("Closing Modal");

			} else
				System.out.println("null driver!");
		} catch (NoSuchElementException e) {
			System.out.println("No Modal popup found or error; closing the modal popup");

		}
		
	}

}
