package restaurant.client.manager.view.employee;

import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;

import javafx.scene.layout.GridPane;

public class Grid extends ScrollPane {

	private GridPane gridPane;

	public Grid() throws IOException {
		gridPane = new GridPane();
		initializeGridPane();
		generateCells();
	}

	public void initializeGridPane() {
		gridPane = new GridPane();
		// gridPane.setStyle(" -fx-border-color: #1a1a1a;");
		// gridPane.setStyle(" -fx-border-color: #1a1a1a;");
		gridPane.setTranslateX(4);
		gridPane.setTranslateY(20);
		gridPane.setHgap(25);
		gridPane.setVgap(15); // vertical gap in pixels
		gridPane.setPadding(new Insets(10, 10, 10, 10));
	}

	public void generateCells() throws IOException {

		// int numberConferences = employeesList.size();
		// final int numCols = 3;
		// final int numRows = numberConferences / numCols + 1;
		//
		// int counter = 0;
		//
		// if (employeesList.size() > 0) {
		// for (int i = 0; i < numRows; i++) {
		// for (int j = 0; j < numCols; j++) {
		//
		// AnchorPane cell = FXMLLoader
		// .load(getClass().getResource("/restaurant/client/manager/view/EmployeeCell.fxml"));
		//
		// Label lblNameEmployee = (Label) cell.lookup("#info");
		// lblNameEmployee.setText(employeesList.get(counter).getName());
		//
		// BufferedImage img = null;
		// img = employeesList.get(counter).getImage();
		// WritableImage image = SwingFXUtils.toFXImage(img, null);
		// ImageView imageView = (ImageView) cell.lookup("#image");
		// imageView.setImage(image);
		// gridPane.add(cell, j, i);
		//
		// counter++;
		// if (numberConferences == counter)
		// break;
		// }
		// if (numberConferences == counter)
		// break;
		// }
		//
		// setVisible(true);
		// // this.setHbarPolicy(ScrollBarPolicy.NEVER);
		// this.setId("conferencesScroll");
		// this.getVbarPolicy().getClass();
		// this.setStyle("-fx-background: #1a1a1a;");
		// this.setContent(gridPane);
		// //
		// this.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
	}

}
