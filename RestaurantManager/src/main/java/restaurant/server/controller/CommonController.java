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
		System.out.println("Consgtructor");
	}

	public void processingRequest(String operation) {
		System.out.println("STEPT 3.5");
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
			System.out.println("STEP 4");
			String[] info = (String[]) inStream.readObject();
			int username = Integer.parseInt(info[0].toString());
			String password = info[1].toString();
			if (MapperController.getEmployeeMapper().findEmployeeByIdAndPass(username, password)) {
				System.out.println("sTEP 5");
				Employee currentEmployee = MapperController.getEmployeeMapper().find(username);
				System.out.println("sTEP 6");
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
			System.out.println("sTEP7");
			ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
		System.out.println("STEP8");
			output.writeObject(object);
			System.out.println("Step 9");
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
