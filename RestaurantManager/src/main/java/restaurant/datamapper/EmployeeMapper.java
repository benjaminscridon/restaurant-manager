package restaurant.datamapper;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import com.mysql.jdbc.PreparedStatement;

import restaurant.model.Employee;
import restaurant.model.OrderDetail;


/**
 * 
 * @author Beniamin
 *
 */
public class EmployeeMapper {

	public synchronized boolean uploadPicture(int id, File imgfile) {

		PreparedStatement myStmt = null;
		try {

			myStmt = (PreparedStatement) DBConnection.getConnection()
					.prepareStatement("UPDATE usersdetails set" + " picture=? " + " where idUser=?");

			FileInputStream fin = new FileInputStream(imgfile);
			myStmt.setBinaryStream(1, (InputStream) fin, (int) imgfile.length());
			myStmt.setInt(2, id);
			myStmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public synchronized Image getImage(int id) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			String selectSQL = "SELECT picture From usersdetails where idUser=?;";
			myStmt = (PreparedStatement) DBConnection.getConnection().prepareStatement(selectSQL);
			myStmt.setInt(1, id);
			myRs = myStmt.executeQuery();

			byte[] bytes = null;
			if (myRs.next()) {
				bytes = myRs.getBytes(1);
			}
			if (bytes != null) {
				InputStream is = new ByteArrayInputStream(bytes);
				BufferedImage imag = ImageIO.read(is);
				Image image = imag;
				return image;
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
		}

		return null;
	}
	
	public void insert(OrderDetail o) {
		try {
			String statement = "INSERT INTO order_details(order_id, product_id, product_name, quantity, status) VALUES (?,?,?,?,?)";
			PreparedStatement dbStatement = (PreparedStatement) DBConnection.getConnection().prepareStatement(statement);
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

	public Employee getNewestEmployee() {
		Employee o = new Employee();
		try {
			String statement = "SELECT max(employee_no) FROM employee;";
			PreparedStatement dbStatement = (PreparedStatement) DBConnection.getConnection().prepareStatement(statement);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				
				int employee_no = rs.getInt("employee_no");
				o.setEmployee_no(employee_no);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	public ArrayList<OrderDetail> findALL() {
		ArrayList<OrderDetail> orders = new ArrayList<OrderDetail>();
		try {
			OrderDetail o;
			String statement = "SELECT * FROM order_details";
			PreparedStatement dbStatement = (PreparedStatement) DBConnection.getConnection().prepareStatement(statement);
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
			PreparedStatement dbStatement = (PreparedStatement) DBConnection.getConnection().prepareStatement(statement);
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
			PreparedStatement dbStatement = (PreparedStatement) DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setInt(1, o.getOrder_id());
			dbStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
}
