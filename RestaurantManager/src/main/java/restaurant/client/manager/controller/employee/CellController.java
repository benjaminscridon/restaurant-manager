package restaurant.client.manager.controller.employee;

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

public class CellController implements Initializable {

	@FXML
	private ImageView image;
	@FXML
	public Label info;

	public void initialize(URL location, ResourceBundle resources) {
		Image img = null;
		img = new Image(getClass().getResourceAsStream("/initialPicture.png"));
		image.setImage(img);
	}
	


}
