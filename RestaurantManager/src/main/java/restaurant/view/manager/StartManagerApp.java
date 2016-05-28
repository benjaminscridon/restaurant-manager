package restaurant.view.manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartManagerApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("here ");

		BorderPane root = new BorderPane();

		AnchorPane login = FXMLLoader.load(getClass().getResource("/restaurant/view/manager/Login.fxml"));

		// .FlowPane layout=new FlowPane();
		// root.prefWidthProperty().bind(primaryStage.widthProperty());
		// root.prefHeightProperty().bind(primaryStage.heightProperty());
		// Image image=new Image("/restaurant4.jpg");
		//
		// ImageView img = new ImageView();
		// img.setImage(image);
		// img.fitWidthProperty().bind(primaryStage.widthProperty());
		//
		// root.setCenter(img);
		// primaryStage.initStyle(StageStyle.TRANSPARENT);
		root.setCenter(login);
		primaryStage.setOpacity(1.0);
		primaryStage.setResizable(false);
		Scene scene = new Scene(root, 950, 700);
		primaryStage.setScene(scene);
		// root.prefHeightProperty().bind(primaryStage.heightProperty());
		// root.prefWidthProperty().bind(primaryStage.widthProperty());pr
		 scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

		// primaryStage.setMaximized(true);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}