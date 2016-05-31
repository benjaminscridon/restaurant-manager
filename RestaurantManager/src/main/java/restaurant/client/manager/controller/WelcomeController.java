package restaurant.client.manager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import restaurant.client.manager.ManagerMain;

/**
 * 
 * @author Beniamin
 * @since 2016-05-29
 */
public class WelcomeController implements Initializable {

	@FXML
	private ImageView image;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image img = new Image("/background_restaurant.jpg");
		image.setImage(img);

	}

	@FXML
	private void logout() {
		try {
			AnchorPane login = FXMLLoader.load(getClass().getResource("/restaurant/client/manager/view/Login.fxml"));
			ManagerMain.getRoot().setCenter(login);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void setEmployeesScreen() {
		try {
			AnchorPane employeesScreen = FXMLLoader.load(getClass().getResource("/restaurant/client/manager/view/employee/Employees.fxml"));
			ManagerMain.getRoot().setCenter(employeesScreen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void setActivityScreen() {
	}

	@FXML
	private void toProducts() {
		try {
			AnchorPane productsScreen = FXMLLoader.load(getClass().getResource("/restaurant/client/manager/view/product/Products.fxml"));
			ManagerMain.getRoot().setCenter(productsScreen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void setOrdersScreen() {
	}

	@FXML
	private void setPrivacyScreen() {
	}

}
