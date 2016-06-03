package restaurant.server.controller.manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import restaurant.server.controller.ConverterImageToFile;
import restaurant.server.controller.MapperController;
import restaurant.server.model.Employee;
import restaurant.server.model.Product;

public class ProductController {
	private ObjectInputStream inStream;
	private Socket clientSocket;

	public ProductController(ObjectInputStream inStream, Socket clientSocket) {
		this.inStream = inStream;
		this.clientSocket = clientSocket;
	}

	public void processingRequest(String[] operation) throws IOException {

		switch (operation[2]) {
		case "addProduct":
			addProduct();
			break;

		case "getAllProducts":
			getAllProducts();
			break;
		case "getAllImages":
			getAllProductsImages();
			break;
		default:
			break;
		}
	}

	private void addProduct() {
		try {
			Product product = (Product) inStream.readObject();
			File file = (File) inStream.readObject();
			boolean bool = MapperController.getProductMapper().insert(product, file);
			if (bool == true) {
				sendResponse(new String[] { "true", "The product was successfully added !" });
			} else {
				sendResponse(new String[] { "false", "The product with this information already exists !" });
			}
			System.out.println("Hei there , I am on the server...");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void getAllProducts() {

		try {
			ArrayList<Product> products = MapperController.getProductMapper().findALL();
			sendResponse(products);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getAllProductsImages() {
		try {
			ArrayList<BufferedImage> imgs = MapperController.getProductMapper().getAllImages();
			ArrayList<File> imageFiles = (new ConverterImageToFile()).convertToFiles(imgs);
			sendResponse(imageFiles);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void sendResponse(Object object) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
			output.writeObject(object);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
