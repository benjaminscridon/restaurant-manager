package restaurant.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;


public class ServerMain {

	public static void main(String[] args) throws IOException, SQLException {

		RestaurantServer server=new RestaurantServer(444);
		server.start();
	}

}
