package restaurant.view.manager;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class EmployeesScreen  extends ScrollPane{

	private GridPane gridPane;

	
public  void initializeGridPane() {
		gridPane = new GridPane();
		gridPane.setStyle(" -fx-border-color: #1a1a1a;");
		gridPane.setStyle(" -fx-border-color: #1a1a1a;");
		//gridPane.setTranslateX(4);
		//gridPane.setTranslateY(20);
		gridPane.setHgap(25);
		gridPane.setVgap(15); // vertical gap in pixels
		gridPane.setPadding(new Insets(10, 10, 10, 10));
	}

	public void generateCells() throws IOException {
	
		// int numberConferences = conferencesList.size();
		final int numCols = 3;
		final int numRows = 24 / numCols + 1;

		int counter = 0;

		// if (conferencesList.size() > 0) {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {

				AnchorPane cell = FXMLLoader.load(getClass().getResource("/restaurant/view/manager/Employee.fxml"));

				// Label lblNameConference = (Label)
				// cell.lookup("#nameConference");
				// if (lblNameConference != null)
				// lblNameConference.setText(conferencesList.get(counter).getName());
				//
				// Label lblIdConference = (Label) cell.lookup("#idConference");
				// if (lblIdConference != null)
				// lblIdConference.setText(conferencesList.get(counter).getId()
				// + "");
				//
				// Image image=null;
				// Object obj= getClass().getResourceAsStream("/image" +
				// lblIdConference.getText() + ".png");
				// if(obj==null){
				// image=new Image(
				// getClass().getResourceAsStream("/defaultConference.png"));
				// } else {
				// image=new Image(
				// getClass().getResourceAsStream("/image" +
				// lblIdConference.getText() + ".png"));
				// }
				//
				// ImageView imageView = (ImageView) cell.lookup("#imageView");
				// imageView.setImage(image);
				// imageView.setCache(true);
				gridPane.add(cell, j, i);

				counter++;
				if (18 == counter)
					break;
			}
			if (18 == counter)
				break;
		}

		//this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		// this.setId("conferencesScroll");
		this.getVbarPolicy().getClass();
		this.setStyle("-fx-background: #1a1a1a;");
		this.setContent(gridPane);
	 // this.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
	}

}
