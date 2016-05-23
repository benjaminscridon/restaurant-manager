package restaurant.controller.manager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import restaurant.controller.MainManager;
import restaurant.view.manager.EmployeesScreen;

public class ActivityController implements Initializable {

	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void logout() {
		AnchorPane loginScreen = new AnchorPane();
		try {
			loginScreen = FXMLLoader.load(getClass().getResource("/restaurant/view/common/Login.fxml"));
			MainManager.managerRoot.getChildren().setAll(loginScreen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void employeesScreen() {
		BorderPane employeesScreen = new BorderPane();
		AnchorPane leftCenterScreen = new AnchorPane();
		try {
			leftCenterScreen = FXMLLoader.load(getClass().getResource("/restaurant/view/manager/ActionEmployee.fxml"));

			EmployeesScreen gridPane = new EmployeesScreen();

			employeesScreen.setLeft(leftCenterScreen);
			employeesScreen.setCenter(gridPane);

			MainManager.managerRoot.getChildren().setAll(employeesScreen);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
