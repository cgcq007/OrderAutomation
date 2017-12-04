package com.bestbuy.project.BestBuyAutomation;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NavigateInHomepageItems {

	WebDriver driver;
	String browser="chrome";
	String baseUrl="http://www.bestbuy.com";
	Logger log=LogManager.getLogger(NavigateInHomepageItems.class);
	
	BrowserSetUp setup = new BrowserSetUp();

	public List<WebElement> clickableLinks(WebDriver driver) {

		List<WebElement> linkList = new ArrayList<>();
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		elements.addAll(driver.findElements(By.tagName("img")));
		System.out.println(elements.size());

		for (WebElement e : elements) {
			if (e.getAttribute("href") != null) {
				linkList.add(e);
				System.out.println(e);
			}

		}
		return linkList;
	}
	@BeforeClass
	public void preparation(){
		driver=setup.setupBrowser(driver, browser, baseUrl);
	}
	
	@Test
	public void testFindLinks() {
// Find all the links and check the status

		List<WebElement> linksList = clickableLinks(driver);
		for (WebElement link : linksList) {
			String href = link.getAttribute("href");
			try {
				System.out.println("URL " + href + " returned " + linkStatus(new URL(href)));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}
	public String linkStatus(URL url){
		try {
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.connect();
			String responseMessage = http.getResponseMessage();
			http.disconnect();
			return responseMessage;
			}
			catch (Exception e) {
				return e.getMessage();
			}
		
	}
	@AfterClass
	public void tearDown(){
		driver.quit();
	}

}
