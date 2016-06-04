package restaurant.client.manager.controller.product;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import restaurant.client.ClientSocket;
import restaurant.client.manager.ManagerMain;
import restaurant.client.manager.controller.UploadPicture;
import restaurant.client.validator.FormValidator;
import restaurant.server.model.Product;

public class UpdateProductController implements Initializable {

	@FXML
	private TextField nameField;
	@FXML
	private TextField descriptionField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField quantityField;
	@FXML
	private ComboBox<String> typeField;
	@FXML
	private ImageView picture;
	@FXML
	private Image image;
	@FXML
	private Label message;
	private File file;

	Product product;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		nameField.setText(product.getName());
		descriptionField.setText(product.getDescription());
		priceField.setText(String.valueOf(product.getPrice()));
		quantityField.setText(String.valueOf(product.getQuantity()));
		picture.setImage(image);

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
		file = (new UploadPicture().uploadPicture());
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
	private void updateProduct() {
		message.setText("");

		String name = nameField.getText();
		String description = descriptionField.getText();
		String price = priceField.getText();
		String quantity = quantityField.getText();
		String type = "";
		if (typeField.getValue() != null) {
			type = (String) typeField.getValue();
		}

		if (new FormValidator().validate(new String[] { name, description, price, quantity, type }) == false) {
			message.setText("Please complete all fields.");
		} else if (validatePrice(price) && validateQuantity(quantity)) {

			Product product = new Product();
			product.setName(name);
			product.setDescription(description);
			product.setPrice(Double.parseDouble(price));
			product.setQuantity(Integer.parseInt(quantity));
			product.setType(type);

			if (file == null) {
				file = new File("src/main/resources/foodDefault.jpg");
			}
			try {

				String request = "manager-product-updateProduct";
				ClientSocket client = new ClientSocket(ManagerMain.getDefaultServer(), ManagerMain.getDefaultPort());
				client.connect();
				client.writeObjectAndFile(product, file, request);
				String[] response = (String[]) client.readObject();
				client.closeConnection();
				if (response[0].equals("true")) {
					AnchorPane newProduct = FXMLLoader
							.load(getClass().getResource("/restaurant/client/manager/view/product/Add.fxml"));
					Label msg = (Label) newProduct.lookup("#message");
					ManagerMain.getRoot().setCenter(newProduct);
					msg.setText(response[1]);
				}
				message.setText(response[1]);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			message.setText("There is an error... Please try again.");
		}

	}

	private boolean validatePrice(String priceS) {
		double price = Double.parseDouble(priceS);
		if (price <= 0) {
			message.setText("Invalid price");
			return false;
		}
		return true;
	}

	private boolean validateQuantity(String quantityS) {
		int quantity = Integer.parseInt(quantityS);
		if (quantity <= 0) {
			message.setText("Invalid quantity.");
			return false;
		}
		return true;
	}

}
