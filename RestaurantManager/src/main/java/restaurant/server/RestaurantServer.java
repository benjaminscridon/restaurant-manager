package restaurant.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import restaurant.server.controller.CommonController;
import restaurant.server.controller.ManagerController;

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

			int flag = operation.indexOf('-');
			String operationType = operation.substring(0, flag);
			switch (operationType) {
			case "common":
				CommonController common = new CommonController(inStream, clientSocket);
				common.processingRequest(operation);
				break;
			case "manager":
				System.out.println("stepul900000");
				ManagerController managerController = new ManagerController(inStream, clientSocket);
				managerController.processingRequest(operation);
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

	// private void sendMessage(Socket client, String message) throws
	// IOException {
	//
	// BufferedWriter writer = new BufferedWriter(new
	// OutputStreamWriter(client.getOutputStream()));
	// writer.write(message);
	// writer.flush();
	// }
	//
	// public void writeObject(Socket client, Object obj) throws IOException {
	//
	// ObjectOutputStream output = new
	// ObjectOutputStream(clientSocket.getOutputStream());
	// output.writeObject(obj);
	// output.flush();
	//
	// }
}
