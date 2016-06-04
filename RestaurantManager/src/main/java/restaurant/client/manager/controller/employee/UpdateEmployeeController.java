package restaurant.client.manager.controller.employee;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

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
import restaurant.client.ClientSocket;
import restaurant.client.manager.ManagerMain;
import restaurant.client.validator.EmailValidator;
import restaurant.client.validator.FormValidator;
import restaurant.client.validator.MobileValidator;
import restaurant.client.validator.PasswordValidator;
import restaurant.server.model.Employee;

public class UpdateEmployeeController implements Initializable {
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
	private ImageView picture;
	@FXML
	private Label message;
	@FXML
	private File file;

	private Employee employee;
	@FXML
	private Image image;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		passwordField.setText(employee.getPassword());
		confirmPassField.setText(employee.getPassword());
		nameField.setText(employee.getName());
		addressField.setText(employee.getAddress());
		birthdateField.setUserData(employee.getBirthdate());
		emailField.setText(employee.getEmail());
		mobileField.setText(employee.getMobile());
		picture.setImage(image);

	}

	@FXML
	private void back() {
		try {
			AnchorPane employeesScreen = FXMLLoader
					.load(getClass().getResource("/restaurant/client/manager/view/employee/Employees.fxml"));
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
	private void updateEmployee() {
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

			try {

				String request1 = "manager-employee-updateEmployee";
				ClientSocket client = new ClientSocket(ManagerMain.getDefaultServer(), ManagerMain.getDefaultPort());
				client.connect();
				client.writeObjectAndFile(employee, file, request1);
				String[] response = (String[]) client.readObject();
				client.closeConnection();
				if (response[0].equals("true")) {
					AnchorPane newEmployee = FXMLLoader
							.load(getClass().getResource("/restaurant/client/manager/view/employee/Add.fxml"));
					ManagerMain.getRoot().setCenter(newEmployee);
					Label msg = (Label) newEmployee.lookup("#message");
					msg.setText(response[1]);
				}
				message.setText(response[1]);

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean validateMail(String email) {
		if (new EmailValidator().validate(email) == false) {
			message.setText("Invalid email.");
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
		message.setText("");
		if (new PasswordValidator().validate(password) == false) {
			message.setText("Password must contain upper and lower letter, digit, and special character.");
			return false;
		} else if (password.equals(confirmPassword) == false) {
			message.setText("Password does not match the confirm password.");
			return false;
		}
		return true;
	}

}
