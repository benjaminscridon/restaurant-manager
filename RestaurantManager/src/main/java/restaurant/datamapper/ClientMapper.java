package restaurant.datamapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import restaurant.model.Client;

public class ClientMapper {

	public void insert(Client c) {
		try {
			String statement = "INSERT INTO client (name, mobile, email, loyality) VALUES (?,?,?,?)";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setString(1, c.getName());
			dbStatement.setString(2, c.getMobile());
			dbStatement.setString(3, c.getEmail());
			dbStatement.setInt(4, c.getLoyality());
			dbStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Client find(int client_id) {
		try {
			Client c;
			String statement = "SELECT * FROM client where client_no=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setInt(1, client_id);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				int client_no = rs.getInt("client_no");
				String name = rs.getString("name");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				int loyality = rs.getInt("loyality");
				c = new Client(client_no, name, mobile, email, loyality);
				return c;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Client();
	}

	public ArrayList<Client> findALL() {
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			Client c;
			String statement = "SELECT * FROM client";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				int client_no = rs.getInt("client_no");
				String name = rs.getString("name");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				int loyality = rs.getInt("loyality");
				c = new Client(client_no, name, mobile, email, loyality);
				clients.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clients;
	}

	public void update(Client c) {
		try {
			String statement = "UPDATE client SET name=?,mobile=?,email=?,loyality=? where client_no=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setString(1, c.getName());
			dbStatement.setString(2, c.getMobile());
			dbStatement.setString(3, c.getEmail());
			dbStatement.setInt(4, c.getLoyality());
			dbStatement.setInt(5, c.getClient_no());
			dbStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(Client c) {
		try {
			String statement = "DELETE FROM client where client_no=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setInt(1, c.getClient_no());
			dbStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

