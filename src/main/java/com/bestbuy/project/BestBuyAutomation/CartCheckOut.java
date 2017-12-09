package com.bestbuy.project.BestBuyAutomation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CartCheckOut {
	Logger log = LogManager.getLogger(CartCheckOut.class);

	public void chooseToShipIfRadioPresent(WebDriver driver) {
		try {
			List<WebElement> allStrong = driver.findElements(By.tagName("strong"));
			Assert.assertTrue(!allStrong.isEmpty());
			for (WebElement e : allStrong) {
				if (e.getText().contains("FREE Shipping"))
					e.click();
			}
			System.out.println("Free ships are choosing");
		} catch (NoSuchElementException Ex) {
			System.out.println("No choice for shipping way");
		}
	}

	public List<GiftCard> readCards() {
		System.out.println("Reading Cards");
		List<GiftCard> cards = new ArrayList<GiftCard>();

		File xlsCards = new File("./GiftCards.xls");
		// 获得工作簿对象
		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(xlsCards);
			// 获得第一工作表
			Sheet sheet = workbook.getSheet("Bestbuy GC");
			if (sheet != null) {
				for (Row row : sheet) {
					GiftCard card = new GiftCard();
					card.setCardNum(row.getCell(0).toString());
					for (Cell cell : row) {
						switch (cell.getColumnIndex()) {
						case 1:
							card.setPin(cell.toString());
							break;
						case 2:
							card.setAmount(cell.toString());
							break;
						case 3:
							card.setBalance(cell.toString());
							break;
						}
					}
					cards.add(card);
					// System.out.println(card.getCardNum() + card.getPin() +
					// card.amount + card.balance);
				}
			}
			workbook.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cards;
	}

	public boolean ElementExist(WebDriver driver, By Locator) {
		try {
			driver.findElement(Locator);

			return true;
		} catch (Exception ex) {
			System.out.println("No such Element");
			return false;
		}
	}

	public void writeCards(List<GiftCard> cards) {
		System.out.println("Writing Cards");
		FileInputStream xlsCards = null;
		Workbook workbook;
		try {
			xlsCards = new FileInputStream(new File("C:\\Users\\cgcq0\\Desktop\\GiftCards.xls"));
			workbook = WorkbookFactory.create(xlsCards);
			Sheet sheet = workbook.getSheet("Bestbuy GC");

			for (GiftCard card : cards) {
				// System.out.println(card.getCardNum() + "card num");
				for (Row row : sheet) {
					System.out.println(row.getCell(0).toString() + "cell num");
					if (row.getCell(0).toString().equals(card.getCardNum())) {
						row.getCell(3).setCellValue(card.getBalance());
						break;
					} else {

						// System.out.println("WTF");
					}
				}
			}
			xlsCards.close();
			FileOutputStream fos = new FileOutputStream("C:\\Users\\cgcq0\\Desktop\\GiftCards.xls");
			workbook.write(fos);
			workbook.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void giftCardInput(WebDriver driver) {
		System.out.println("Inputing Cards");
		WebDriverWait wait = new WebDriverWait(driver, 5);
		List<GiftCard> cards = readCards();
		List<GiftCard> usingCards = cards.stream().filter(n -> Double.valueOf(n.getBalance()) != 0)
				.sorted((p1, p2) -> p1.getBalance().compareTo(p2.getBalance())).limit(10).collect(Collectors.toList());
		Optional<GiftCard> inputingCard = usingCards.stream().findFirst();
		List<GiftCard> usedCards = new ArrayList<GiftCard>();
		boolean cardExist = Optional.ofNullable(inputingCard).isPresent();
		boolean elementExist = true;
		// List<WebElement> cashMoney =
		// driver.findElements(By.class"cash-money"));
		// WebElement total = cashMoney.get(cashMoney.size() - 1);

		while (cardExist && elementExist) {
			try {
				WebElement giftCardCode = driver.findElement(By.id("payment.giftCard.code"));
				giftCardCode.sendKeys(inputingCard.get().getCardNum());
				WebElement pin = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("payment.giftCard.pin")));
				pin.sendKeys(inputingCard.get().getPin());
				Thread.sleep(1000);
				WebElement apply = wait.until(ExpectedConditions.elementToBeClickable(
						By.cssSelector("button.gift-cards__apply-btn.btn.btn-primary.btn-block.btn-sm")));
				apply.click();
				Thread.sleep(1000);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			// 转存用过的卡
			usingCards.remove(0);
			inputingCard.get().setBalance("0");
			usedCards.add(inputingCard.get());

			// 循环
			inputingCard = usingCards.stream().findFirst();
			cardExist = Optional.ofNullable(inputingCard).isPresent();
			System.out.println(elementExist);
			elementExist = ElementExist(driver, By.id("payment.giftCard.code"));

		}

		try {
			WebElement subprice = driver.findElement(By.className("alternate-payment__subprice"));
			String regEx = "(\\d+\\.\\d+)";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(subprice.getText());
			String str = m.find() == true ? m.group(0) : "";
			usedCards.get(usedCards.size() - 1).setBalance(str);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		// inputingCard.get().setBalance(subprice.getText());
		// usingCards.add(inputingCard.get());
		writeCards(usedCards);
	}

	public void billingAddress(WebDriver driver, Account account) {
		try {
			driver.findElement(By.id("payment.billingAddress.firstName")).sendKeys(account.getFirstName());
			driver.findElement(By.id("payment.billingAddress.lastName")).sendKeys(account.getLastName());
			driver.findElement(By.id("payment.billingAddress.street")).sendKeys(account.getAddress());
			Select sel = new Select(driver.findElement(By.id("payment.billingAddress.state")));
			sel.selectByValue(account.getState());
			driver.findElement(By.id("payment.billingAddress.zipcode")).sendKeys(account.getZip());
			driver.findElement(By.id("payment.billingAddress.city")).sendKeys(account.getCity());

			// driver.findElement(By.id("payment.billingAddress.firstName")).sendKeys("rui");
			// driver.findElement(By.id("payment.billingAddress.lastName")).sendKeys("hu");
			// driver.findElement(By.id("payment.billingAddress.street")).sendKeys("19901
			// SW 95th Ave LNDWK");
			// Select sel = new
			// Select(driver.findElement(By.id("payment.billingAddress.state")));
			// sel.selectByValue("OR");
			// driver.findElement(By.id("payment.billingAddress.zipcode")).sendKeys("97062");
			// driver.findElement(By.id("payment.billingAddress.city")).sendKeys("Tualatin");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void checkOut(WebDriver driver, Account account) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		// go to cart
		try {
			WebElement modal = driver.switchTo().activeElement();
			WebElement goToCart = wait.until(ExpectedConditions
					.elementToBeClickable(modal.findElement(By.linkText("No, thanks. Go to cart ›"))));

			// WebElement goToCart = modal.findElement(By.linkText("No, thanks.
			// Go to cart ›"));
			// WebElement goToCart = wait
			// .until(ExpectedConditions.presenceOfElementLocated(By.linkText("No,
			// thanks. Go to cart ›")));
			goToCart.click();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		// shipping choice
		chooseToShipIfRadioPresent(driver);

		// check out
		try {
			Thread.sleep(1000);
			WebElement checkOut = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id='sc-store-availability-target']/div/div/span/div/div[3]/div/div[1]/div[1]/div/button")));
			checkOut.click();

			// continue to payment information
			WebElement toPaymentInfo = wait.until(
					ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-lg.btn-block.btn-primary")));
			toPaymentInfo.click();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		// gift card
		giftCardInput(driver);

		// billing address
		billingAddress(driver, account);

		// place order
		try {
			WebElement placeOrder = wait.until(ExpectedConditions
					.elementToBeClickable(By.cssSelector("button.btn.btn-lg.btn-block.btn-secondary")));
			placeOrder.click();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

}
