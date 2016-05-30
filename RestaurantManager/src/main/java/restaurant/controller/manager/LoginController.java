package restaurant.controller.manager;

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

/**
 * 
 * @author Beniamin Scridon
 * @since 2016-05-29
 */
public class LoginController implements Initializable {

	@FXML
	private ImageView image;
	@FXML
	private Label message;
	private String managerNo;
	private String password;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image img = new Image("/background_restaurant.jpg");
		if (img != null)
			image.setImage(img);
		message.setText("");
	}

	@FXML
	private void login() {
		message.setText("");
		/**
		 * 1. apelez functia validate Daca returneaza true, atunci schimb
		 * panelul si specific userul, in SessionManager
		 */
		try {
			AnchorPane activityScreen = FXMLLoader
					.load(getClass().getResource("/restaurant/view/manager/Welcome.fxml"));
			StartManagerApp.getRoot().setCenter(activityScreen);

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
