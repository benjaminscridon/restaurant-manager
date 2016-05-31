package restaurant.client.manager.controller.product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.IconView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import restaurant.client.manager.ManagerMain;

/**
 * 
 * @author Beniamin
 * @since 2016-05-30
 */
public class ProductsController implements Initializable {

	@FXML
	private ImageView image;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private GridPane grid;
	@FXML private Label searchBtn;
	@FXML private TextField searchField;

	@FXML
	private void back() {
		try {
			AnchorPane welcome = FXMLLoader.load(getClass().getResource("/restaurant/client/manager/view/Welcome.fxml"));
			ManagerMain.getRoot().setCenter(welcome);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void toAddProduct() {
		try {
			AnchorPane addProduct = FXMLLoader.load(getClass().getResource("/restaurant/client/manager/view/product/Add.fxml"));
			ManagerMain.getRoot().setCenter(addProduct);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image img = new Image("/background_restaurant.jpg");
		image.setImage(img);

		
		try {
			initializeGrid();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//scrollPane.setStyle(" -fx-background-color: transparent;");
		//grid.setStyle(" -fx-background-color: red;");
	}

	private void initializeGrid() throws IOException {
		grid.setTranslateX(0);
		grid.setTranslateY(0);
		grid.setHgap(20);
		grid.setVgap(10); // vertical gap in pixels
		grid.setPadding(new Insets(10, 10, 10, 10));

		final int numCols = 3;
		final int numRows = 24 / numCols + 1;

		int counter = 0;

		// if (conferencesList.size() > 0) {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {

				AnchorPane cell = FXMLLoader
						.load(getClass().getResource("/restaurant/client/manager/view/employee/Cell.fxml"));
				grid.add(cell, j, i);

				counter++;
				if (18 == counter)
					break;
			}
			if (18 == counter)
				break;
		}
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setContent(grid);
		//scrollPane.setStyle("-fx-background: #1a1a1a;");
	}
}
