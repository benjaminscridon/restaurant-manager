package restaurant.server.model;

import java.sql.Date;

public class Order {
	
	private int id;
	private int table_no;
	private int client_id;
	private int waiter_id;
	private Date date;
	private double total;
	private String status;
	
	public Order(int id, int table_no, int client_id, int waiter_id, Date date, double total,String status) {
		this.id = id;
		this.table_no = table_no;
		this.client_id = client_id;
		this.waiter_id = waiter_id;
		this.date = date;
		this.total = total;
		this.status=status;
	}
	
	public Order(){
		
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTable_no() {
		return table_no;
	}

	public void setTable_no(int table_no) {
		this.table_no = table_no;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public int getWaiter_id() {
		return waiter_id;
	}

	public void setWaiter_id(int waiter_id) {
		this.waiter_id = waiter_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total2) {
		this.total = total2;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", table_no=" + table_no + ", client_id=" + client_id + ", waiter_id=" + waiter_id
				+ ", date=" + date + ", total=" + total + "]";
	}
	
	
	
	

}
