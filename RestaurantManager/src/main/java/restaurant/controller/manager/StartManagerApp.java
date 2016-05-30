package restaurant.controller.manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * @author Beniamin Scridon
 * @since 2016-05-29
 */
public class StartManagerApp extends Application {

	private static BorderPane root;

	@Override
	public void start(Stage primaryStage) throws Exception {

		root = new BorderPane();
		AnchorPane login = FXMLLoader.load(getClass().getResource("/restaurant/view/manager/Login.fxml"));
		root.setCenter(login);

		Scene scene = new Scene(root, 950, 700);
		scene.getStylesheets().add(getClass().getResource("/manager.css").toExternalForm());
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/managerIcon.png")));
		primaryStage.setScene(scene);
		primaryStage.setOpacity(1.0);
		primaryStage.setResizable(false);
		primaryStage.setTitle("ManagerApp     @author Beniamin Scridon");
		primaryStage.show();
	}

	public static BorderPane getRoot() {
		return root;
	}

	public static void main(String[] args) {
		launch(args);
	}
}