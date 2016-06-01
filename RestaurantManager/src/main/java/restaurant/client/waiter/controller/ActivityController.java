package restaurant.client.waiter.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Beniamin
 *
 */
public class ActivityController implements Initializable {

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
		table.add("Table 1"); table.add("Table 4");
		table.add("Table 2"); table.add("Table 5");
		table.add("Table 3"); table.add("Table 6");
		table.add("Table 7"); table.add("Table 8");
		table.add("Table 9"); table.add("Table 10");
		for (String s : table)
			tables.add(s);

		tablesList.setItems(tables);
		// ----------------------------------------------------------------
		ObservableList<String> options = FXCollections.observableArrayList("Table 1", "Table 2", "Table 3");
		tablesCb.setItems(options);
		//-----------------------------------------------------------------------

	}


}
