package restaurant.server.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.plaf.synth.SynthSpinnerUI;

import restaurant.server.model.Employee;

/**
 * 
 * @author Beniamin
 *
 */
public class CommonController {

	private ObjectInputStream inStream;
	private Socket clientSocket;

	public CommonController(ObjectInputStream inStream, Socket clientSocket) {
		this.inStream = inStream;
		this.clientSocket = clientSocket;
	}

	public void processingRequest(String operation) {
		System.out.println("operation "+operation);
		switch (operation) {
		case "common-login":System.out.println("There???");
			login();
			break;

		default: System.out.println("Not at all");
			break;
		}
	}

	private void login() {
		try {
			String[] info = (String[]) inStream.readObject();
			int username = Integer.parseInt(info[0].toString());
			String password = info[1].toString();
			if (MapperController.getEmployeeMapper().findEmployeeByIdAndPass(username, password)) {
				Employee currentEmployee = MapperController.getEmployeeMapper().find(username);
				sendResponse(currentEmployee);
			} else{
				sendResponse(new String("Invalid id or password."));
			}
			
		} catch (ClassNotFoundException | IOException e) {
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
