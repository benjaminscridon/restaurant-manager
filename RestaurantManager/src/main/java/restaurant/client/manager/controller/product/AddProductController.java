package restaurant.client.manager.controller.product;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import restaurant.client.common.validator.EmailValidator;
import restaurant.client.common.validator.FormValidator;
import restaurant.client.common.validator.MobileValidator;
import restaurant.client.common.validator.PasswordValidator;
import restaurant.client.manager.ManagerMain;
import restaurant.server.model.Employee;

/**
 * 
 * @author Beniamin
 *
 */
public class AddProductController implements Initializable {

	@FXML private TextField nameField;
	@FXML private TextField descriptionField;
	@FXML private TextField priceField;
	@FXML private TextField quantityField;
	@FXML private ComboBox<String> typeField;
	@FXML private ImageView picture;
	@FXML private ImageView image;
	@FXML private Label message;
    private File file;

	public void initialize(URL location, ResourceBundle resources) {
		message.setText("");

		Image img_background = new Image("/background_restaurant.jpg");
		image.setImage(img_background);

		Image img = new Image(getClass().getResourceAsStream("/foodDefault.jpg"));
		picture.setImage(img);

		ObservableList<String> options;
		options = FXCollections.observableArrayList("drink", "cigarette","desert", "food");
		typeField.setItems(options);
	}

	@FXML
	private void back() {
		try {
			AnchorPane employeesScreen = FXMLLoader
					.load(getClass().getResource("/restaurant/client/manager/view/product/Products.fxml"));
			ManagerMain.getRoot().setCenter(employeesScreen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void toHome() {
		try {
			AnchorPane activityScreen = FXMLLoader
					.load(getClass().getResource("/restaurant/client/manager/view/Welcome.fxml"));
			ManagerMain.getRoot().setCenter(activityScreen);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void uploadPicture() {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		file = fileChooser.showOpenDialog(null);
		try {

			if (file != null) {
				BufferedImage bufferedImage = ImageIO.read(file);
				Image img = SwingFXUtils.toFXImage(bufferedImage, null);
				picture.setImage(img);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	private void addEmployee() {
		message.setText("");
//
//		String password = passwordField.getText();
//		String confirmPassword = confirmPassField.getText();
//		String email = emailField.getText();
//		String mobile = mobileField.getText();
//		String name = nameField.getText();
//		String address = addressField.getText();
//
//		String status = "";
//		if (statusField.getValue() != null) {
//			status = (String) statusField.getValue();
//		}
//
//		String birthdate = "";
//		LocalDate date = birthdateField.getValue();
//		java.sql.Date selectedDate = null;
//		if (date != null) {
//			selectedDate = java.sql.Date.valueOf(date);
//			birthdate = selectedDate.toString();
//		}
//
//		if (new FormValidator().validate(
//				new String[] { password, confirmPassword, status, email, mobile, name, address, birthdate }) == false) {
//			message.setText("Please complete all fields.");
//		} else if (validateMail(email) && validateMobile(mobile) && validatePassword(password, confirmPassword)) {
//
//			message.setText("");
//			Employee employee = new Employee();
//			employee.setAddress(address);
//			employee.setEmail(email);
//			employee.setJob_title(status);
//			employee.setMobile(mobile);
//			employee.setName(name);
//			employee.setPassword(confirmPassword);
//			employee.setHire_date(new java.sql.Date(System.currentTimeMillis()));
//			employee.setBirthdate(java.sql.Date.valueOf(date));
//
//			if (file == null) {
//				file = new File("src/main/resources/initialPicture.png");
//			}
//			if (MapperController.getEmployeeMapper().insert(employee, file)) {
//				try {
//					AnchorPane newEmployee = FXMLLoader
//							.load(getClass().getResource("/restaurant/view/manager/employee/Add.fxml"));
//					StartManagerApp.getRoot().setCenter(newEmployee);
//					Label message1 = (Label) newEmployee.lookup("#message");
//					message1.setText("The employee has been added successfully.");
//
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			} else {
//				message.setText("There is an error... Please try again.");
//			}
//		}
	}

}