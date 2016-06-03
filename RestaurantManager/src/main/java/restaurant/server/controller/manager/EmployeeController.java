package restaurant.server.controller.manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import restaurant.server.controller.ConverterImageToFile;
import restaurant.server.controller.MapperController;
import restaurant.server.model.Employee;

public class EmployeeController {

	private ObjectInputStream inStream;
	private Socket clientSocket;

	public EmployeeController(ObjectInputStream inStream, Socket clientSocket) {
		this.inStream = inStream;
		this.clientSocket = clientSocket;
	}

	public void processingRequest(String[] operation) throws IOException {

		switch (operation[2]) {
		case "getAllEmployees":

			getAllEmployees();
			break;
		case "addEmployee":
			addEmployees();
			break;
		case "getAllImages":
			getAllEmployeesImages();
			break;

		default:
			break;
		}
	}

	private void getAllEmployees() {

		try {
			ArrayList<Employee> empl = MapperController.getEmployeeMapper().findALL();
			sendResponse(empl);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getAllEmployeesImages() {
		try {
			ArrayList<BufferedImage> imgs = MapperController.getEmployeeMapper().getAllImages();
			ArrayList<File> imageFiles = (new ConverterImageToFile()).convertToFiles(imgs);
			sendResponse(imageFiles);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	private void addEmployees() {
		try {
			Employee employee = (Employee) inStream.readObject();
			File file = (File) inStream.readObject();
			boolean bool = MapperController.getEmployeeMapper().insert(employee, file);
			if (bool == true) {
				sendResponse(new String[] { "true", "Employee successfully inserted !" });
			} else {
				sendResponse(new String[] { "false", "The employee with this information already exists !" });
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
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
