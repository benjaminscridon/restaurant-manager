package restaurant.client.waiter.controller;

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
import restaurant.client.waiter.view.ProductTable;
import restaurant.server.controller.MapperController;
import restaurant.server.model.Product;

public class ProductController implements Initializable{

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

		name.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("name"));
		price.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("price"));
		image.setCellValueFactory(new PropertyValueFactory<ProductTable, Image>("image"));
	
		Image img = new Image(getClass().getResourceAsStream("/initialPicture.png"));
		ArrayList<ProductTable> products=new ArrayList<>();
		ProductTable p1=new ProductTable();
		p1.setImage(new ImageView(img));
		p1.setName("Coca Cola");
		p1.setPrice("23.5 RON");
		
		info.add(p1);
		
		table.setItems(info);
		
	}
	
}
