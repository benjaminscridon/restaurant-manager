package restaurant.client.waiter.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import restaurant.client.waiter.WaiterMain;
import restaurant.client.waiter.WaiterSession;
import restaurant.client.waiter.view.ProductTable;
import restaurant.server.model.Order;
import restaurant.server.model.OrderDetail;
import restaurant.server.model.Product;

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

	// ------- Order ListView -------------------------
	@FXML
	private ListView ordersListView;
	@FXML
	private ObservableList<String> ordersObsList = FXCollections.observableArrayList();

	@FXML
	private TextField totalField;
	// ----------------

	// --calculator
	@FXML
	private TextField calculator;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initializeButtons();
		initializeComboBox();

		tablesList.setPlaceholder(new Label("No Tables"));
		tablesList.setItems(tables);
		totalField.setText("");

		ordersListView.setPlaceholder(new Label("No order"));

		calculator.setText("1");
		ordersListView.setItems(ordersObsList);
	}

	@FXML
	private void addProductToTable() {
		String currentTable = tablesList.getSelectionModel().getSelectedItem() + "";
		String numberofProducts = calculator.getText();
		ProductTable currentProduct = WaiterSession.getCurrentProduct();
		if (currentTable.equals("null") == false && numberofProducts.equals("null") == false
				&& currentProduct != null) {
			String[] selectedTable=currentTable.split(" ");

			
			//insert in database and sent to kitchen
			double sum = Double.parseDouble(numberofProducts) * Double.parseDouble(currentProduct.getPrice());
			double total=sum;
			if( totalField.getText().length()>0){
				String[] info=totalField.getText().split(" ");
				total+=Double.parseDouble(info[1]);
			}
			
			Order currentOrder=new Order();
			currentOrder.setTable_no(Integer.parseInt(selectedTable[selectedTable.length-1]));
			//currentOrder.setWaiter_id(WaiterSession.getCurrentWaiter().getEmployee_no());
			currentOrder.setTotal(total);
			currentOrder.setDate(new Date(System.currentTimeMillis()));
			currentOrder.setStatus("processing");
			//trebuie sa mi se returneze id.ul
			int order_id = 0;
			
			int quantity=Integer.parseInt(calculator.getText());
			OrderDetail orderDetail=new OrderDetail();
			orderDetail.setOrder_id(order_id);
			orderDetail.setQuantity(quantity);
			//search for id of product
			//orderDetail.setProduct_id(currentProduct);
			ordersObsList.add(
					currentProduct.getName() + "  x" + numberofProducts + "  " + currentProduct.getPrice() + " $");
			totalField.setText("Total: " + total + " $");
		} else {
			JOptionPane.showMessageDialog(null, "Select a table, product and quantity.");
		}

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

	@FXML
	private void press0() {
		if (calculator.getText().length() > 0 && calculator.getText().charAt(0) != '0') {
			calculator.setText(calculator.getText() + "0");
		}
	}

	@FXML
	private void press1() {
		calculator.setText(calculator.getText() + "1");
	}

	@FXML
	private void press2() {
		calculator.setText(calculator.getText() + "2");
	}

	@FXML
	private void press3() {
		calculator.setText(calculator.getText() + "3");
	}

	@FXML
	private void press4() {
		calculator.setText(calculator.getText() + "4");
	}

	@FXML
	private void press5() {
		calculator.setText(calculator.getText() + "5");
	}

	@FXML
	private void press6() {
		calculator.setText(calculator.getText() + "6");
	}

	@FXML
	private void press7() {
		calculator.setText(calculator.getText() + "7");
	}

	@FXML
	private void press8() {
		calculator.setText(calculator.getText() + "8");
	}

	@FXML
	private void press9() {
		calculator.setText(calculator.getText() + "9");
	}

	@FXML
	private void clearCalculator() {
		calculator.setText("");
	}
}
