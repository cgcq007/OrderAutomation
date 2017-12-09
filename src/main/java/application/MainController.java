package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.testng.TestNG;

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
		TestNG tng = new TestNG();
		tng.setTestClasses(new Class[]{OrderThenCheckOutTest.class});
		tng.run();
	}
}
