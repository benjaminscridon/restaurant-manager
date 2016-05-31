package restaurant.server.datamapper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import restaurant.server.model.Product;

public class ProductMapper {

	public void insert(Product p) {
		try {
			String statement = "INSERT INTO product (name, type, price, description, quantity) VALUES (?,?,?,?,?)";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setString(1, p.getName());
			dbStatement.setString(2, p.getType());
			dbStatement.setFloat(3, p.getPrice());
			dbStatement.setString(4,p.getDescription());
			dbStatement.setInt(5,p.getQuantity());
			dbStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
			String statement = "SELECT * FROM product";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
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

	public void update(Product p) {
		try {
			String statement = "UPDATE product SET name=?,type=?,price=?,description=? ,quantity=? where product_id=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setString(1, p.getName());
			dbStatement.setString(2, p.getType());
			dbStatement.setFloat(3, p.getPrice());
			dbStatement.setString(4,p.getDescription());
			dbStatement.setInt(5,p.getQuantity());
			dbStatement.setInt(6,p.getProduct_id());
			dbStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delete(Product p) {
		try {
			String statement = "DELETE FROM product where product_id=?";
			PreparedStatement dbStatement = DBConnection.getConnection().prepareStatement(statement);
			dbStatement.setInt(1,p.getProduct_id());
			dbStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

