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
	private ImageView homeImg;
	@FXML
	private ImageView foodImg;
	@FXML
	private ImageView kitchenImg;
	@FXML
	private ImageView paymentsImg;
	@FXML
	private ImageView logoutImg;

	@FXML
	private ListView tablesList;
	@FXML
	private ObservableList<String> tables = FXCollections.observableArrayList();

	@FXML
	private ComboBox tablesCb;

	ObservableList<String> obsTablesCb;

	//------- Order ListView -------------------------
	@FXML private ListView ordersListView;
	@FXML private ObservableList<String> ordersObsList = FXCollections.observableArrayList();
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initializeButtons();
		initializeComboBox();

		tablesList.setPlaceholder(new Label("No Tables"));
		tablesList.setItems(tables);
	}

	private void initializeComboBox() {
		ArrayList<String> tablee = new ArrayList<>();
		for (int i = 1; i <= 30; i++) {
			tablee.add(new String("Table " + i));
		}
		obsTablesCb = FXCollections.observableArrayList(tablee);
		tablesCb.setItems(obsTablesCb);
	}

	@FXML
	private void removeTable() {
		String selectedTable = tablesList.getSelectionModel().getSelectedItem() + "";
		tables.remove(selectedTable);
		obsTablesCb.add(selectedTable);
		initializeComboBox();
	}

	@FXML
	private void addTable() {
		String selectedTable = tablesCb.getValue() + "";
		tables.add(tablesCb.getValue() + "");
		tablesCb.getSelectionModel().clearSelection();
		obsTablesCb.remove(selectedTable);
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

	private void initializeButtons() {
		homeImg.setImage(new Image("/waiter/home1.png"));
		foodImg.setImage(new Image("/waiter/pizza.png"));
		kitchenImg.setImage(new Image("/waiter/kitchen.png"));
		paymentsImg.setImage(new Image("/waiter/payment.png"));
		logoutImg.setImage(new Image("/waiter/logout.png"));
	}
}
