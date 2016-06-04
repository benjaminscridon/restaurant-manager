package restaurant.client.waiter.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import restaurant.client.waiter.WaiterMain;

public class LoginController implements Initializable {

	@FXML
	private ImageView image1;
	@FXML
	private ImageView image2;
	@FXML
	private Label message;
	@FXML
	private String waiterNo;
	@FXML
	private String password;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		message.setText("");
		Image img = new Image("/waiter/background_1.jpg");
		image1.setImage(img);
		
		Image img2 = new Image("/waiter/background_2.jpg");
		image2.setImage(img2);
	}

	@FXML
	public void login() {
		message.setText("");
		try {

			BorderPane activity = FXMLLoader.load(getClass().getResource("/restaurant/client/waiter/view/Activity.fxml"));
			AnchorPane productsPanel = FXMLLoader
					.load(getClass().getResource("/restaurant/client/waiter/view/Product.fxml"));
			activity.setCenter(productsPanel);
			
			WaiterMain.getRoot().setCenter(activity);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean validate() {
		/**
		 * 1. verific campurile managerNo si password sa fie introduse, daca nu
		 * afisez mesaj de eroare si returnez false; 2. verific in baza de date
		 * datele , dac nu se potrivesc afisez un mesaj si returnez false
		 * Returnez true in final;
		 */
		return false;
	}
}
