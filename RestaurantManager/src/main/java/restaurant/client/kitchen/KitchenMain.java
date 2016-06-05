package restaurant.client.kitchen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class KitchenMain extends Application {

	private static BorderPane root;

	@Override
	public void start(Stage primaryStage) throws Exception {

		root = new BorderPane();
		AnchorPane kitchenScreen = FXMLLoader.load(getClass().getResource("/restaurant/client/kitchen/view/Kitchen.fxml"));
		root.setCenter(kitchenScreen);

		Scene scene = new Scene(root, 950, 700);
		scene.getStylesheets().add(getClass().getResource("/css/kitchen.css").toExternalForm());
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/kitchen/kitchen4.png")));
		primaryStage.setScene(scene);
		primaryStage.setOpacity(1.0);
		primaryStage.setResizable(false);
		primaryStage.setTitle("KitchenApp     @author Beniamin Scridon");
		primaryStage.show();
	}

	public static BorderPane getRoot() {
		return root;
	}

	public static void setRoot(BorderPane pane) {
		root = pane;
	}

	public static void main(String[] args) {
		launch(args);
	}
}