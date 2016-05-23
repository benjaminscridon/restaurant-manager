package restaurant.controller.manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class AddEmployeeController implements Initializable {

	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	@FXML
	private TextField nameField;
	@FXML
	private TextField addressFiled;
	@FXML
	private TextField emailField;
	@FXML
	private TextField mobile;
	@FXML
	private ComboBox<String> statusField;

	@FXML
	private DatePicker birthdateField;
	@FXML
	private ImageView image;
	@FXML
	private Label message;

	public void initialize(URL location, ResourceBundle resources) {
		message.setVisible(true);
		message.setText("");

		Image img = null;
		img = new Image(getClass().getResourceAsStream("/initialPicture.png"));
		image.setImage(img);

		ObservableList<String> options;
		options = FXCollections.observableArrayList("manager", "waiter", "kitchen");
		statusField.setItems(options);
	}

	@FXML
	private void uploadPicture() {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		File file = fileChooser.showOpenDialog(null);

		try {

			if (file != null) {
				BufferedImage bufferedImage = ImageIO.read(file);
				Image img = SwingFXUtils.toFXImage(bufferedImage, null);
				image.setImage(img);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@FXML
	private void addEmployee(){
		
	}

}
