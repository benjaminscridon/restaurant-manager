package restaurant.client.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket {
	
	private String hostname;
	private int port;
	Socket socketClient;

	public ClientSocket(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public void connect() throws IOException {
		System.out.println("Attepmting to connect to " + hostname + ": " + port);
		socketClient = new Socket(hostname, port);
		System.out.println("connection established");
	}

	public void readResponse() throws IOException {
		String userInput;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

		System.out.println("Response from server");

		while ((userInput = stdIn.readLine()) != null) {
			System.out.println(userInput);
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

	public void writeMessage(String s) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(socketClient.getOutputStream());
		output.writeObject(new String(s));
	}

	public void writeObject(Object obj, String s) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(socketClient.getOutputStream());
		output.writeObject(new String(s));
		output.writeObject(obj);

	}

	public Object readObject() throws IOException, ClassNotFoundException {
		ObjectInputStream inStream = null;
		inStream = new ObjectInputStream(socketClient.getInputStream());
		Object obj = inStream.readObject();
		return obj;
	}

}
