package restaurant.controller.manager;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import restaurant.controller.MainManager;

public class ActionEmployeeController {

	
	
	@FXML
	private void back() {
		AnchorPane activityScreen = new AnchorPane();
		try {
			activityScreen = FXMLLoader.load(getClass().getResource("/restaurant/view/manager/Activity.fxml"));
			MainManager.managerRoot.getChildren().setAll(activityScreen);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
