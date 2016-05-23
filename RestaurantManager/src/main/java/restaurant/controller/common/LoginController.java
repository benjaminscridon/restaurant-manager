package restaurant.controller.common;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import restaurant.controller.MainManager;

public class LoginController implements Initializable {

	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	@FXML
	private Label message;

	public void initialize(URL location, ResourceBundle resources) {
		message.setVisible(true);
		message.setText("");
	}

	@FXML
	private void login() {

		// validare campuri , plus verific in baza de date
		// daca e okey , merg pe panelul activity
		// deschid panelul in functie de statutul de user ..
		// verific ca statutul sa fie manager daca nu , reintroduceti username
		
		AnchorPane activityScreen = new AnchorPane();
		try {
			activityScreen = FXMLLoader.load(getClass().getResource("/restaurant/view/manager/Activity.fxml"));
			MainManager.managerRoot.getChildren().setAll(activityScreen);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
