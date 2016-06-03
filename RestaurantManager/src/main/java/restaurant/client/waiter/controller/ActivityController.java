package restaurant.client.waiter.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import restaurant.client.waiter.WaiterMain;
import restaurant.client.waiter.view.ProductTable;

/**
 * 
 * @author Beniamin
 *
 */
public class ActivityController implements Initializable {
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
	
	///
	@FXML
	private ImageView homeImg;
	@FXML
	private ImageView foodImg;
	@FXML
	private ImageView kitchenImg;
	@FXML
	private ImageView paymentsImg;
	@FXML
	private ImageView logoutImg;

	//
	@FXML
	private ImageView drinkImg;
	@FXML
	private ImageView dishesImg;
	@FXML
	private ImageView desertImg;
	@FXML
	private ImageView cigaretteImg;
	//

	@FXML
	private ListView tablesList;
	@FXML
	private ObservableList<String> tables = FXCollections.observableArrayList();

	@FXML
	private ComboBox tablesCb;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		table.getStylesheets().add(getClass().getResource("/waiter/table.css").toExternalForm());
		
		name.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("name"));
		price.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("price"));
		image.setCellValueFactory(new PropertyValueFactory<ProductTable, Image>("image"));
		Image img = new Image(getClass().getResourceAsStream("/initialPicture.png"));
		ProductTable p1=new ProductTable();
		p1.setImage(new ImageView(img));
		p1.setName("Coca Cola");
		p1.setPrice("23.5 RON");
		//info.add(p1);
		
		Image img1 = new Image(getClass().getResourceAsStream("/initialPicture.png"));
		ImageView a=new ImageView(img1);
		a.setPreserveRatio(true);
		a.setSmooth(true);
	     a.setFitHeight(50);
         a.setFitWidth(50);

		
		ProductTable p2=new ProductTable();
		p2.setImage(a);
		p2.setName("Coca Cola Zero");
		p2.setPrice("23.5 RON");
		//info.add(p2);
		
		Image img3 = new Image(getClass().getResourceAsStream("/dishes1.png"));
		ProductTable p3=new ProductTable();
		p3.setImage(new ImageView(img3));
		p3.setName("Coca Cola Zero ++");
		p3.setPrice("23.5 RON");
		info.addAll(p3, p2,p1);
		
		
		table.setItems(info);
		
		
		
		homeImg.setImage(new Image("/waiter/home1.png"));
		foodImg.setImage(new Image("/waiter/pizza.png"));
		kitchenImg.setImage(new Image("/waiter/kitchen.png"));
		paymentsImg.setImage(new Image("/waiter/payment.png"));
		logoutImg.setImage(new Image("/waiter/logout.png"));

		drinkImg.setImage(new Image("/waiter/drink.png"));
		dishesImg.setImage(new Image("/waiter/dishes.png"));
		desertImg.setImage(new Image("/waiter/cake.png"));
		cigaretteImg.setImage(new Image("/waiter/cigarette.png"));
		// ------------------------------------------------------------
		tablesList.setPlaceholder(new Label("No Content In List"));
		ArrayList<String> table = new ArrayList<>();
		table.add("Table 1");
		table.add("Table 4");
		table.add("Table 2");
		table.add("Table 5");
		table.add("Table 3");
		table.add("Table 6");
		table.add("Table 7");
		table.add("Table 8");
		table.add("Table 9");
		table.add("Table 10");
		for (String s : table)
			tables.add(s);

		tablesList.setItems(tables);
		// ----------------------------------------------------------------
		ObservableList<String> options = FXCollections.observableArrayList("Table 1", "Table 2", "Table 3");
		tablesCb.setItems(options);
		// -----------------------------------------------------------------------

	}

	@FXML
	private void logout() {
		try {
			AnchorPane login = FXMLLoader.load(getClass().getResource("/restaurant/client/waiter/view/Login.fxml"));
			WaiterMain.getRoot().setCenter(login);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
