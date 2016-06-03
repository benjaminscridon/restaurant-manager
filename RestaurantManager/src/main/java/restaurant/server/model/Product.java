package restaurant.server.model;

import java.io.Serializable;

public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6843599983847264224L;
	private int product_id;
	private String name;
	private String type;
	private double price;
	private String description;
	private int quantity;

	
	
	public Product(int product_id, String name, String type, double price, String description, int quantity) {
		this.product_id = product_id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
	}

	public Product() {

	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", name=" + name + ", type=" + type + ", price=" + price
				+ ", description=" + description + ", qunatity=" + quantity + "]";
	}

}
