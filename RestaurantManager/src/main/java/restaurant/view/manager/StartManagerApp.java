package restaurant.view.manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartManagerApp extends Application {


	@Override
	public void start(Stage primaryStage) throws Exception {


		BorderPane root =FXMLLoader.load(getClass().getResource("/restaurant/view/manager/Login.fxml"));
//		Image image=new Image("/restaurant4.jpg");
//		
//		ImageView img = new ImageView();
//		img.setImage(image);
//		 img.fitWidthProperty().bind(primaryStage.widthProperty());
//		
//		 root.setCenter(img);
		 
		primaryStage.setMaximized(true);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}