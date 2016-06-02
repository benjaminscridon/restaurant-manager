package restaurant.client.manager.controller.employee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import restaurant.client.ClientSocket;
import restaurant.client.manager.ManagerMain;
import restaurant.server.model.Employee;

/**
 * 
 * @author Beniamin
 * @since 2016-05-30
 */
public class EmployeesController implements Initializable {

	@FXML
	private ImageView image;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private GridPane grid;

	@FXML
	private void back() {
		try {
			AnchorPane welcome = FXMLLoader
					.load(getClass().getResource("/restaurant/client/manager/view/Welcome.fxml"));
			ManagerMain.getRoot().setCenter(welcome);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void addEmployee() {
		try {
			AnchorPane newEmployee = FXMLLoader
					.load(getClass().getResource("/restaurant/client/manager/view/employee/Add.fxml"));
			ManagerMain.getRoot().setCenter(newEmployee);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image img = new Image("/background_restaurant.jpg");
		image.setImage(img);

		initializeGrid();
		addStyleToGrid();	
	}

	private void initializeGrid() {
		try {
			String request = "manager-getAllEmployees";
			ClientSocket client = new ClientSocket(ManagerMain.getDefaultServer(), ManagerMain.getDefaultPort());
			client.connect();
			client.writeMessage(request);

			Employee[] response = (Employee[]) client.readObject();
			client.closeConnection();

			if (response.length > 0) {
				final int numCols = 3;
				final int numRows = response.length / numCols + 1;
				int counter = 0;

				for (int i = 0; i < numRows; i++) {
					for (int j = 0; j < numCols; j++) {

						AnchorPane cell = FXMLLoader.load(
								getClass().getResource("/restaurant/client/manager/view/employee/EmployeeCell.fxml"));

						Label info =(Label) cell.lookup("#info");
						info.setText(response[counter].getEmployee_no()+" "+response[counter].getName());
						
						//Image image = response[counter];
					//	
//						ImageView imageView = (ImageView) cell.lookup("#image");
//						imageView.setImage(image);
//						imageView.setCache(true);
						
						grid.add(cell, j, i);
						counter++;
						if (response.length == counter)
							break;
					}
					if (response.length== counter)
						break;
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	private void addStyleToGrid() {
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setContent(grid);
		// scrollPane.setStyle("-fx-background: #1a1a1a;");
		grid.setTranslateX(0);
		grid.setTranslateY(0);
		grid.setHgap(20);
		grid.setVgap(10); // vertical gap in pixels
		grid.setPadding(new Insets(10, 10, 10, 10));
	}
}
