package restaurant.controller.manager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author Beniamin
 * @since 2016-05-29
 */
public class ActivityController implements Initializable {

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
			AnchorPane login = FXMLLoader.load(getClass().getResource("/restaurant/view/manager/Login.fxml"));
			StartManagerApp.getRoot().setCenter(login);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void setEmployeesScreen() {
	}

	@FXML
	private void setActivityScreen() {
	}

	@FXML
	private void setProductsScreen() {
	}

	@FXML
	private void setOrdersScreen() {
	}

	@FXML
	private void setPrivacyScreen() {
	}

}
