package restaurant.client.manager.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import restaurant.client.ClientSocket;
import restaurant.client.common.validator.FormValidator;
import restaurant.client.manager.SessionManager;
import restaurant.client.manager.ManagerMain;
import restaurant.server.model.Employee;

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
	@FXML
	private TextField managerNo;
	@FXML
	private PasswordField password;

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
		if (validate()) {
			String username = managerNo.getText();
			String pass = password.getText();

			String[] info = { username, pass };
			String request = "common-login";
			try {
				ClientSocket client = new ClientSocket(ManagerMain.getDefaultServer(),
						ManagerMain.getDefaultPort());
				client.connect();
				client.writeObject(info, request);

				Object obj = client.readObject();
				if (obj instanceof Employee) {
					SessionManager.setCurrentEmployee((Employee) obj);
					client.closeConnection();

					AnchorPane activityScreen = FXMLLoader
							.load(getClass().getResource("/restaurant/client/manager/view/Welcome.fxml"));
					ManagerMain.getRoot().setCenter(activityScreen);
				} else {
					message.setText(obj.toString() + "");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean validate() {

		FormValidator validator = new FormValidator();
		if (validator.validate(managerNo.getText(), password.getText()) == false) {
			message.setText("Complete all fields.");
			return false;
		} else if (managerNo.getText().matches("[0-9]+") == false) {
			message.setText("Invalid manager id.");
			return false;
		}
		return true;
	}
}
