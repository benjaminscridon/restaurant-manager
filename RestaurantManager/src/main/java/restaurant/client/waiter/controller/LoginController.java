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
import restaurant.client.manager.ManagerMain;
import restaurant.client.waiter.StartWaiterApp;

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
		Image img = new Image("/waiterImg1.jpg");
		image1.setImage(img);
		
		Image img2 = new Image("/waiterImg2.jpg");
		image2.setImage(img2);
	}

	@FXML
	private void login() {
		message.setText("");
		/**
		 * 1. apelez functia validate Daca returneaza true, atunci schimb
		 * panelul si specific userul, in SessionManager
		 */
		try {
			BorderPane home = FXMLLoader
					.load(getClass().getResource("/restaurant/client/manager/view/Home.fxml"));
			StartWaiterApp.getRoot().setCenter(home);

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
