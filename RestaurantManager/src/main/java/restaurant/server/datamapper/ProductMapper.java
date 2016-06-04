package restaurant.server.datamapper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import restaurant.server.model.Product;

public class ProductMapper {

	public boolean insert(Product p, File filename) {
		try {
			String statement = "INSERT INTO product (name, type, price, description,image,quantity) VALUES (?,?,?, ?,?,?)";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setString(1, p.getName());
			dbStatement.setString(2, p.getType());
			dbStatement.setDouble(3, p.getPrice());
			dbStatement.setString(4, p.getDescription());

			FileInputStream fin = new FileInputStream(filename);
			dbStatement.setBinaryStream(5, (InputStream) fin, (int) filename.length());
			dbStatement.setInt(6, p.getQuantity());
			dbStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public synchronized ArrayList<BufferedImage> getAllImages() throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		ArrayList<BufferedImage> images = new ArrayList<>();
		try {
			String selectSQL = "SELECT image From restaurant.product";
			myStmt = (PreparedStatement) DBConnection.getConnection().prepareStatement(selectSQL);
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				byte[] bytes = null;
				bytes = myRs.getBytes(1);
				if (bytes != null) {
					InputStream is = new ByteArrayInputStream(bytes);
					BufferedImage imag = ImageIO.read(is);
					BufferedImage img = imag;
					images.add(img);
				}
			}
			return images;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	public Product find(int product_id) {
		try {
			Product c;
			String statement = "SELECT * FROM product where product_id=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setInt(1, product_id);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				int product_no = rs.getInt("product_id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				float price = rs.getFloat("price");
				String description = rs.getString("description");
				int qunatity = rs.getInt("qunatity");
				c = new Product(product_no, name, type, price, description, qunatity);
				return c;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Product();
	}

	public ArrayList<Product> findALL() {
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Product p;
			String statement = "SELECT * FROM restaurant.product";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				int product_no = rs.getInt("product_id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				float price = rs.getFloat("price");
				String description = rs.getString("description");
				int quantity = rs.getInt("quantity");
				p = new Product(product_no, name, type, price, description, quantity);
				products.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	public ArrayList<Product> findProductByName(String productName) {
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Product p;
			String statement = "SELECT * FROM product WHERE name=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setString(1, productName);
			ResultSet rs = dbStatement.executeQuery();
			while (rs.next()) {
				int product_no = rs.getInt("product_id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				float price = rs.getFloat("price");
				String description = rs.getString("description");
				int qunatity = rs.getInt("qunatity");
				p = new Product(product_no, name, type, price, description, qunatity);
				products.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	public boolean update(Product p, File filename) {
		try {
			String statement = "UPDATE product SET name=?,type=?,price=?,description=? ,quantity=? , image=? where product_id=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setString(1, p.getName());
			dbStatement.setString(2, p.getType());
			dbStatement.setDouble(3, p.getPrice());
			dbStatement.setString(4, p.getDescription());
			dbStatement.setInt(5, p.getQuantity());
			FileInputStream fin = new FileInputStream(filename);
			dbStatement.setBinaryStream(6, (InputStream) fin, (int) filename.length());
			dbStatement.setInt(7, p.getProduct_id());
			dbStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void delete(Product p) {
		try {
			String statement = "DELETE FROM product where product_id=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setInt(1, p.getProduct_id());
			dbStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
