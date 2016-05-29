package restaurant.controller.manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import restaurant.controller.common.MapperController;
import restaurant.controller.common.validator.EmailValidator;
import restaurant.controller.common.validator.FormValidator;
import restaurant.controller.common.validator.MobileValidator;
import restaurant.controller.common.validator.PasswordValidator;
import restaurant.server.model.Employee;

public class AddEmployeeController implements Initializable {

	@FXML
	private TextField passwordField;
	@FXML
	private TextField confirmPassField;
	@FXML
	private TextField nameField;
	@FXML
	private TextField addressField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField mobileField;
	@FXML
	private ComboBox<String> statusField;

	@FXML
	private DatePicker birthdateField;
	@FXML
	private ImageView image;
	@FXML
	private Label message;

	private File file;

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
		file = fileChooser.showOpenDialog(null);
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
	private void addEmployee() {
		message.setText("");

		String password = passwordField.getText();
		String confirmPassword = confirmPassField.getText();
		String email = emailField.getText();
		String mobile = mobileField.getText();
		String name = nameField.getText();
		String address = addressField.getText();

		String status = "";
		if (statusField.getValue() != null) {
			status = (String) statusField.getValue();
		}

		String birthdate = "";
		LocalDate date = birthdateField.getValue();
		java.sql.Date selectedDate = null;
		if (date != null) {
			selectedDate = java.sql.Date.valueOf(date);
			birthdate = selectedDate.toString();
		}

		if (new FormValidator().validate(
				new String[] { password, confirmPassword, status, email, mobile, name, address, birthdate }) == false) {
			message.setText("Please complete all fields.");
		} else if (validateMail(email) && validateMobile(mobile) && validatePassword(password, confirmPassword)) {

			Employee employee = new Employee();
			employee.setAddress(address);
			employee.setEmail(email);
			employee.setJob_title(status);
			employee.setMobile(mobile);
			employee.setName(name);
			employee.setPassword(confirmPassword);
			employee.setHire_date(new java.sql.Date(System.currentTimeMillis()));
			employee.setBirthdate(java.sql.Date.valueOf(date));

			if (file == null) {
				file = new File("src/main/resources/initialPicture.png");
			}
			if (MapperController.getEmployeeMapper().insert(employee, file)) {
				// change pannel
			}

		} else {
			message.setText("There is an error... Please try again.");
		}

	}

	private boolean validateMail(String email) {
		if (new EmailValidator().validate(email) == false) {
			message.setText("Invalid email.");
			return false;
		} else if (MapperController.getEmployeeMapper().findEmployeeByEmail(email) == true) {
			message.setText("The email address you entered is already in use.");
			return false;
		}
		return true;
	}

	private boolean validateMobile(String mobile) {
		if (new MobileValidator().validate(mobile) == false) {
			message.setText("Invalid mobile.");
			return false;
		}
		return true;
	}

	private boolean validatePassword(String password, String confirmPassword) {
		if (new PasswordValidator().validate(password) == false) {
			message.setText("Password must contain upper and lower letter, digit, and special character.(minimum 8");
			return false;
		} else if (password.equals(confirmPassword) == false) {
			message.setText("Password does not match the confirm password.");
			return false;
		}
		return true;
	}
}
