package restaurant.controller.common;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements Initializable{

	@FXML private TextField usernameField;
	@FXML private TextField passwordField;
	@FXML private Label message;
	

	public void initialize(URL location, ResourceBundle resources) {
		message.setVisible(true);
		message.setText("");
	}
	

	@FXML
	private void login() {

	}
	

}
