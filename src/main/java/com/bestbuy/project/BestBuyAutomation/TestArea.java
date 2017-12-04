package com.bestbuy.project.BestBuyAutomation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestArea {
	public static void main(String[] args) {
		File xlsAccounts = new File("C:\\Users\\cgcq0\\Desktop\\Accounts.xls");
		// 获得工作簿对象
		Workbook workbook;
		List<Account> accounts = new ArrayList<Account>();
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

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		for (Account a : accounts) {
			System.out.println(a.getAccount());
		}
		List<String> nameList = new ArrayList<String>();
		for (Account account : accounts) {
			nameList.add(account.getAccount());
		}
		String[] name = (String[]) nameList.toArray(new String[nameList.size()]);
		System.out.println(name[0]);

		
		Object[][] accountIdx = new Object[accounts.size()][1];
		for (Account account : accounts) {
			accountIdx[accounts.indexOf(account)][0] = accounts.indexOf(account);
		}
		for(int i = 0; i< accounts.size(); i++)
		System.out.println(accountIdx[i][0]);
	}

}