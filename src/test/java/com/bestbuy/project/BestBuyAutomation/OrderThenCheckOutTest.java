package com.bestbuy.project.BestBuyAutomation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

public class OrderThenCheckOutTest {

	Logger log = LogManager.getLogger(OrderThenCheckOutTest.class);
	WebDriver driver;
	String browserName = "chrome";
	String baseUrl = "http://www.bestbuy.com";

	AddNewItem addItem = new AddNewItem();
	LogInToAccount la = new LogInToAccount();
	BrowserSetUp setup = new BrowserSetUp();
	CartCheckOut cartcheck = new CartCheckOut();
	List<Account> accounts = new ArrayList<Account>();
	String sku = "5805312";

	// String[] name = null;

	@BeforeTest
	public void initializeData() {
		System.out.println("Reading Accounts");

		File xlsAccounts = new File("C:\\Users\\cgcq0\\Desktop\\Accounts.xls");
		// 获得工作簿对象
		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(xlsAccounts);
			// 获得第一工作表
			Sheet sheet = workbook.getSheet("BestBuy");
			if (sheet != null) {
				for (Row row : sheet) {
					Account account = new Account();
					account.setAccount(row.getCell(0).toString());
					for (Cell cell : row) {
						switch (cell.getColumnIndex()) {
						case 1:
							account.setPwd(cell.toString());
							break;
						case 2:
							account.setFirstName(cell.toString());
							break;
						case 3:
							account.setLastName(cell.toString());
							break;
						case 4:
							account.setAddress(cell.toString());
							break;
						case 5:
							account.setCity(cell.toString());
							break;
						case 6:
							account.setState(cell.toString());
							break;
						case 7:
							account.setZip(cell.toString());
							break;
						}
					}
					accounts.add(account);
				}
			}
			workbook.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@BeforeClass
	public void setSku() {
		File txtSku = new File("./sku.txt");
		try {
			FileInputStream fileInputStream = new FileInputStream(txtSku);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			sku = sb.toString().trim();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@BeforeMethod
	public void setUp() {
		driver = setup.setupBrowser(driver, browserName, baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {

		Thread.sleep(5000);
		driver.quit();

	}

	@DataProvider(name = "accountName")
	public Object[][] createdata() {
		Object[][] accountIdx = new Object[accounts.size()][1];
		for (Account account : accounts) {
			accountIdx[accounts.indexOf(account)][0] = accounts.indexOf(account);
		}
		return accountIdx;
	}

	@Test(dataProvider = "accountName")
	public void testOrderThenCheckOut(int accountIdx) throws Exception {

		la.login(driver, accounts.get(accountIdx));
		addItem.add(driver, sku);
		cartcheck.checkOut(driver, accounts.get(accountIdx));

		// la.login(driver);
		// log.info("logged in before adding new Item");
		// addItem.add(driver);
		// log.info("Added new Item");
		// cartcheck.checkOut(driver);
		log.info("Check out");

	}

}
