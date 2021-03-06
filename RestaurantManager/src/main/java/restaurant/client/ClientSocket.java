package restaurant.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClientSocket {

	private String hostname;
	private int port;
	Socket socketClient;

	public ClientSocket(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public void connect() {

		try {
			socketClient = new Socket(hostname, port);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The server is not running.");
		}

	}

	public void closeConnection() throws IOException {
		try {
			if (socketClient != null)
				socketClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readResponse() throws IOException {
		String userInput;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

		System.out.println("Response from server");

		while ((userInput = stdIn.readLine()) != null) {
			System.out.println(userInput);
		}
	}

	public void writeMessage(String s) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(socketClient.getOutputStream());
		output.writeObject(new String(s));
	}

	public String readMessage() throws IOException, ClassNotFoundException {
		ObjectInputStream inStream = null;
		inStream = new ObjectInputStream(socketClient.getInputStream());
		String string = (String) inStream.readObject();
		return string;
	}

	public void writeObject(Object obj, String s) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(socketClient.getOutputStream());
			output.writeObject(new String(s));
			output.writeObject(obj);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public void writeObjectAndFile(Object obj, Object obj1, String s) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(socketClient.getOutputStream());
			output.writeObject(new String(s));
			output.writeObject(obj);
			output.writeObject(obj1);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public Object readObject() throws IOException, ClassNotFoundException {
		ObjectInputStream inStream = null;
		inStream = new ObjectInputStream(socketClient.getInputStream());
		Object obj = inStream.readObject();
		return obj;
	}

}
