package restaurant.model;

public class OrderDetail {
	
	private int order_id;
	private int product_id;
	private String product_name;
	private int quantity;
	private String status;
	
	public OrderDetail(int order_id, int product_id, String product_name, int quantity, String status) {
		super();
		this.order_id = order_id;
		this.product_id = product_id;
		this.product_name = product_name;
		this.quantity = quantity;
		this.status = status;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderDetail [order_id=" + order_id + ", product_id=" + product_id + ", product_name=" + product_name
				+ ", quantity=" + quantity + ", status=" + status + "]";
	}
	
	
	
	

}
