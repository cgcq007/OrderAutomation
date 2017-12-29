package com.bestbuy.project.BestBuyAutomation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;

public class IPTest {
	WebDriver driver;
	BrowserSetUp setup = new BrowserSetUp();

	@Test
	public void f() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void beforeMethod() {
		Proxy proxy = new Proxy();

		String[] PROXY = {"70.169.70.83:80" };
		String random = "";

		int index = (int) (Math.random() * PROXY.length);
		random = PROXY[index];
		proxy.setHttpProxy(random).setFtpProxy(random).setSslProxy(random);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, proxy);
//		File file = new File("C:/Program Files/Selenium/IEDriverServer.exe");
//		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
//		driver = new InternetExplorerDriver(cap);
//		driver = new ChromeDriver(cap);
		driver = setup.setupBrowser(driver,cap, "chrome", "http://whatismyipaddress.com/");
	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
