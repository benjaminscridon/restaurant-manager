package restaurant.server.controller;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import restaurant.server.model.Employee;

public class ManagerController {

	@SuppressWarnings("unused")
	private ObjectInputStream inStream;
	private Socket clientSocket;

	public ManagerController(ObjectInputStream inStream, Socket clientSocket) {
		this.inStream = inStream;
		this.clientSocket = clientSocket;
	}

	public void processingRequest(String operation) {
		System.out.println("operation " + operation);
		switch (operation) {
		case "manager-getEmployees":
			getEmployees();
			break;
		case "manager-addEmployee":
			addEmployees();
			break;

		default:
			break;
		}
	}

	private void getEmployees() {
		ArrayList<Employee> foundEmployees = MapperController.getEmployeeMapper().findALL();
		sendResponse(foundEmployees);
	}

	private void addEmployees() {
		try {
			Employee employee = (Employee) inStream.readObject();
			File file = (File) inStream.readObject();
			MapperController.getEmployeeMapper().insert(employee, file);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
