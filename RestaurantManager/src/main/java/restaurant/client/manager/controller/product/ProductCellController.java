package restaurant.client.manager.controller.product;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProductCellController implements Initializable {

	@FXML
	private ImageView image;
	@FXML
	private Label info;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image img = null;
		img = new Image(getClass().getResourceAsStream("/foodDefault.jpg"));
		image.setImage(img);
	}
	


}
