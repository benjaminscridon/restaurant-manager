package restaurant.client.waiter.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import restaurant.client.ClientSocket;
import restaurant.client.manager.ManagerMain;
import restaurant.client.manager.controller.ConverterFileToImage;
import restaurant.client.waiter.view.ProductTable;
import restaurant.server.model.Product;

/**
 * 
 * @author Beniamin
 * 
 */
public class ProductController implements Initializable {

	@FXML
	private ImageView drinkImg;
	@FXML
	private ImageView dishesImg;
	@FXML
	private ImageView desertImg;
	@FXML
	private ImageView cigaretteImg;

	@FXML
	private TableView<ProductTable> table;

	@FXML
	private TableColumn<ProductTable, String> name;
	@FXML
	private TableColumn<ProductTable, String> price;
	@FXML
	private TableColumn<ProductTable, Image> image;

	@FXML
	private ObservableList<ProductTable> info = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeButtons();
		initializeTableView();
	}

	private void initializeButtons() {
		drinkImg.setImage(new Image("/waiter/drink.png"));
		dishesImg.setImage(new Image("/waiter/dishes.png"));
		desertImg.setImage(new Image("/waiter/cake.png"));
		cigaretteImg.setImage(new Image("/waiter/cigarette.png"));
	}

	private void initializeTableView() {
		table.getStylesheets().add(getClass().getResource("/waiter/table.css").toExternalForm());

		name.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("name"));
		price.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("price"));
		image.setCellValueFactory(new PropertyValueFactory<ProductTable, Image>("image"));

		try {
			String request = "manager-product-getAllProducts";
			ClientSocket client = new ClientSocket(ManagerMain.getDefaultServer(), ManagerMain.getDefaultPort());
			client.connect();
			client.writeMessage(request);
			ArrayList<Product> products = (ArrayList<Product>) client.readObject();
			client.closeConnection();

			String request2 = "manager-product-getAllImages";
			ClientSocket client2 = new ClientSocket(ManagerMain.getDefaultServer(), ManagerMain.getDefaultPort());
			client2.connect();
			client2.writeMessage(request2);
			ArrayList<File> imageFiles = (ArrayList<File>) client2.readObject();
			ArrayList<javafx.scene.image.Image> images = (new ConverterFileToImage()).convertFilesToImages(imageFiles);
			client2.closeConnection();

			ArrayList<ProductTable> rows = new ArrayList<>();
			for (int i = 0; i < products.size(); i++) {
				ProductTable row = new ProductTable();
				row.setName(products.get(i).getName());
				row.setPrice(products.get(i).getPrice() + "");
				ImageView img=new ImageView(images.get(i));
				img.setPreserveRatio(true);
				img.setSmooth(true);
				img.setFitHeight(60);
				img.setFitWidth(60);
				row.setImage(img);
				rows.add(row);
			}
			info.addAll(rows);
			table.setItems(info);

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
