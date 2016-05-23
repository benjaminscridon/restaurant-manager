package restaurant.datamapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import restaurant.model.OrderDetail;

public class OrderDetailMapper {

	public void insert(OrderDetail o) {
		try {
			String statement = "INSERT INTO order_details(order_id, product_id, product_name, quantity, status) VALUES (?,?,?,?,?)";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setInt(1, o.getOrder_id());
			dbStatement.setInt(2, o.getProduct_id());
			dbStatement.setString(3, o.getProduct_name());
			dbStatement.setInt(4, o.getQuantity());
			dbStatement.setString(5, o.getStatus());
			dbStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<OrderDetail> find(int order_id) {
		ArrayList<OrderDetail> orders = new ArrayList<OrderDetail>();
		try {
			OrderDetail o;
			String statement = "SELECT * FROM order_details where order_id=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setInt(1, order_id);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				int order_no = rs.getInt("order_id");
				int product_id = rs.getInt("product_id");
				String product_name = rs.getString("product_name");
				int quantity = rs.getInt("quantity");
				String status = rs.getString("status");
				o = new OrderDetail(order_no, product_id, product_name, quantity, status);
				orders.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	public ArrayList<OrderDetail> findALL() {
		ArrayList<OrderDetail> orders = new ArrayList<OrderDetail>();
		try {
			OrderDetail o;
			String statement = "SELECT * FROM order_details";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				int order_no = rs.getInt("order_id");
				int product_id = rs.getInt("product_id");
				String product_name = rs.getString("product_name");
				int quantity = rs.getInt("quantity");
				String status = rs.getString("status");
				o = new OrderDetail(order_no, product_id, product_name, quantity, status);
				orders.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	public void update(OrderDetail o) {
		try {
			String statement = "UPDATE order_details SET product_id=?,product_name=?,quantity=?,status=? where order_id=? and product_id=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setInt(1, o.getProduct_id());
			dbStatement.setString(2, o.getProduct_name());
			dbStatement.setInt(3, o.getQuantity());
			dbStatement.setString(4, o.getStatus());
			dbStatement.setInt(5, o.getOrder_id());
			dbStatement.setInt(6, o.getProduct_id());
			dbStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(OrderDetail o) {
		try {
			String statement = "DELETE FROM order_details where order_id=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setInt(1, o.getOrder_id());
			dbStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
