package restaurant.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import restaurant.server.controller.CommonController;
import restaurant.server.controller.manager.ManagerController;

public class RestaurantServer extends Thread {

	private ServerSocket serverSocket;
	private Socket clientSocket;

	public RestaurantServer(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				processingRequest();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void processingRequest() {
		try {
			ObjectInputStream inStream = new ObjectInputStream(clientSocket.getInputStream());
			String operation = inStream.readObject().toString();

			String[] operationType = operation.split("-");
			switch (operationType[0]) {
			case "common":
				CommonController common = new CommonController(inStream, clientSocket);
				common.processingRequest(operationType);
				break;
			case "manager":
				ManagerController managerController = new ManagerController(inStream, clientSocket);
				managerController.processingRequest(operationType);
				break;
			case "waiter":
				break;
			case "kitchen":
				break;
			default:
				break;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
