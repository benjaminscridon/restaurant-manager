package restaurant.server.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import restaurant.server.model.Employee;

public class ManagerController {

	private ObjectInputStream inStream;
	private Socket clientSocket;

	public ManagerController(ObjectInputStream inStream, Socket clientSocket) {
		this.inStream = inStream;
		this.clientSocket = clientSocket;
	}

	public void processingRequest(String operation) throws IOException {
		switch (operation) {
		case "manager-getAllEmployees":
			getAllEmployees();
			break;
		case "manager-addEmployee":
			addEmployees();
			break;

		default:
			break;
		}
	}

	private void getAllEmployees() {

		try {
			ArrayList<Employee> empl = MapperController.getEmployeeMapper().findALL();
			Employee[] employees = empl.toArray(new Employee[empl.size()]);

			ArrayList<Image> img = MapperController.getEmployeeMapper().getAllImages();
			Image[] images = img.toArray(new Image[img.size()]);

			sendResponse(employees);
		} catch (Exception e) {
			e.printStackTrace();
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
