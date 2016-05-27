package restaurant.view.kitchen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Start extends Application{

	
	@Override
	public void start(Stage primaryStage) throws Exception {

		
	    BorderPane scrollPane =FXMLLoader.load(getClass().getResource("Order.fxml"));
		Scene managerScene = new Scene(scrollPane);
		

		primaryStage.setTitle("Kitchen");
		primaryStage.setOpacity(0.95);
		primaryStage.setScene(managerScene);
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}

}
