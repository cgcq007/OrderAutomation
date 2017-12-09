package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.bestbuy.project.BestBuyAutomation.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController {
	@FXML
	private TextField sku;

	@FXML
	protected void submitOrder(ActionEvent event) {
		try {
			String content = sku.getText();
			File file = new File("./sku.txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			fw.close();
			System.out.println("sku writing done!");
		} catch (Exception ex) {

		}

		// XmlSuite suite = new XmlSuite();
		// suite.setName("testng");
		//
		// XmlTest test = new XmlTest(suite);
		// test.setName("TmpTest");
		// List<XmlClass> classes = new ArrayList<XmlClass>();
		// classes.add(new XmlClass("servers.testcase.Demo"));
		// classes.add(new XmlClass("servers.testcase.LoginCase"));
		// test.setXmlClasses(classes);
		//
		// List<XmlSuite> suites = new ArrayList<XmlSuite>();
		// suites.add(suite);
		// TestNG tng = new TestNG();
		// tng.setXmlSuites(suites);
		// tng.run();

		TestNG tng = new TestNG();
		tng.setTestClasses(new Class[] { OrderThenCheckOutTest.class });
		tng.run();
	}
}
