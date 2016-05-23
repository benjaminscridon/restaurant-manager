package restaurant.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author Beniamin
 * @since 2016-05-23 10:28 a.m
 */
public class MainManager extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		AnchorPane root = new AnchorPane();
		root = FXMLLoader.load(getClass().getResource("/restaurant/view/common/Login.fxml"));
		Scene scene = new Scene(root, 900, 700);

		primaryStage.setTitle("Manager App");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/manager_icon.png")));
		primaryStage.setOpacity(1.0);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}

}
