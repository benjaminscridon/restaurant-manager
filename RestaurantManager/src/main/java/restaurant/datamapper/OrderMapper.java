package restaurant.datamapper;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import restaurant.server.model.Order;

public class OrderMapper {

	public void insert(Order o) {
		try {
			String statement = "INSERT INTO orders(table_no, client_id, waiter_id, date, total) VALUES (?,?,?,?,?)";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setInt(1, o.getTable_no());
			dbStatement.setInt(2, o.getClient_id());
			dbStatement.setInt(3, o.getWaiter_id());
			dbStatement.setDate(4, o.getDate());
			dbStatement.setFloat(5, o.getTotal());
			dbStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Order find(int order_id) {
		try {
			Order o;
			String statement = "SELECT * FROM orders where id=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setInt(1, order_id);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				int order_no = rs.getInt("id");
				int table_no = rs.getInt("table_no");
				int client_id = rs.getInt("client_id");
				int waiter_id = rs.getInt("waiter_id");
				Date date = rs.getDate("date");
				float total =rs.getFloat("total");
				o = new Order(order_no,table_no,client_id,waiter_id,date,total);
				return o;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Order();
	}

	public ArrayList<Order> findALL() {
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			Order o;
			String statement = "SELECT * FROM orders";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				int order_no = rs.getInt("id");
				int table_no = rs.getInt("table_no");
				int client_id = rs.getInt("client_id");
				int waiter_id = rs.getInt("waiter_id");
				Date date = rs.getDate("date");
				float total =rs.getFloat("total");
				o = new Order(order_no,table_no,client_id,waiter_id,date,total);
				orders.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	public void update(Order o) {
		try {
			String statement = "UPDATE orders SET table_no=?,client_id=?,waiter_id=?,date=?,total=? where id=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setInt(1, o.getTable_no());
			dbStatement.setInt(2, o.getClient_id());
			dbStatement.setInt(3, o.getWaiter_id());
			dbStatement.setDate(4, o.getDate());
			dbStatement.setFloat(5, o.getTotal());
			dbStatement.setInt(6, o.getId());
			dbStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(Order o ) {
		try {
			String statement = "DELETE FROM orders where id=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setInt(1, o.getId());
			dbStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}


