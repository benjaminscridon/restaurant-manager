package restaurant.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class ServerMain {

	public static void main(String[] args) throws IOException, SQLException {

//		ServerSocket serverSocket = new ServerSocket(9990);
//		int count = 0;
//		while (true) {
//			Socket client = serverSocket.accept();
//			Runnable runnable = new RestaurantServer(client, ++count);
//			Thread thread = new Thread(runnable);
//			thread.start();
//		}
		String operation = "common-payments-....";
		int flag = operation.indexOf('-');
		String operationType = operation.substring(0, flag);
		System.out.println(operationType);
	}

}
