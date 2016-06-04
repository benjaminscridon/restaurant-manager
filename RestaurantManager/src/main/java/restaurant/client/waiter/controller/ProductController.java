package restaurant.client.waiter.controller;

import java.net.URL;
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

/**
 * 
 * @author Beniamin
 * 
 */
public class ProductController implements Initializable{
	
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
	
	private void initializeButtons(){
		drinkImg.setImage(new Image("/waiter/drink.png"));
		dishesImg.setImage(new Image("/waiter/dishes.png"));
		desertImg.setImage(new Image("/waiter/cake.png"));
		cigaretteImg.setImage(new Image("/waiter/cigarette.png"));
	}
	
	private void initializeTableView(){
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
	}
	
}
