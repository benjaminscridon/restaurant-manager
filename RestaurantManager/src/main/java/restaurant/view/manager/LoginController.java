package restaurant.view.manager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController implements Initializable{

	
	@FXML private ImageView image;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Image img=new Image("/background_restaurant.jpg");
		image.setImage(img);
	}

}
