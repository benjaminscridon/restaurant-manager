package restaurant.server.controller.manager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


public class ManagerController {

	private ObjectInputStream inStream;
	private Socket clientSocket;

	public ManagerController(ObjectInputStream inStream, Socket clientSocket) {
		this.inStream = inStream;
		this.clientSocket = clientSocket;
	}

	public void processingRequest(String[] operation) throws IOException {

		switch (operation[1]) {
		case "employee":
			EmployeeController employeeC = new EmployeeController(inStream, clientSocket);
			employeeC.processingRequest(operation);
			break;
		case "product":
			ProductController productC = new ProductController(inStream, clientSocket);
			productC.processingRequest(operation);
			break;
		default:
			break;
		}
	}

}
