package restaurant.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import restaurant.controller.common.MapperController;
import restaurant.server.model.Employee;

//import com.google.gson.Gson;
public class RestaurantServer implements Runnable {
	private Socket connection;
	@SuppressWarnings("unused")
	private int port;
	// private Gson gson = new Gson();

	public RestaurantServer(Socket connection, int port) {
		this.connection = connection;
		this.port = port;
	}

	@Override
	public void run() {

		try {
			readFromClient(connection);
		} catch (IOException | ClassNotFoundException ex) {
		}

	}

	public void readFromClient(Socket client) throws IOException, ClassNotFoundException {

		ObjectInputStream inStream = null;
		inStream = new ObjectInputStream(client.getInputStream());
		String operation = inStream.readObject().toString();

		switch (operation) {
		case "login":
			System.out.println("login");
			String[] employeeInfo = (String[]) inStream.readObject();
			boolean foundUser = false;
			foundUser = MapperController.getEmployeeMapper().findEmployeeByIdAndPass(Integer.parseInt(employeeInfo[0]),
					employeeInfo[1]);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
			if (foundUser == true) {
				Employee employeeOutput = MapperController.getEmployeeMapper().find(Integer.parseInt(employeeInfo[0]));
				writeObject(client, employeeOutput);
			} else {
				Employee employeeOutput = new Employee();
				writeObject(client, employeeOutput);
			}
			connection.close();
		}
	}

	@SuppressWarnings("unused")
	private void sendMessage(Socket client, String message) throws IOException {

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		writer.write(message);
		writer.flush();
	}

	public void writeObject(Socket client, Object obj) throws IOException {

		ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
		output.writeObject(obj);
		output.flush();

	}

}
