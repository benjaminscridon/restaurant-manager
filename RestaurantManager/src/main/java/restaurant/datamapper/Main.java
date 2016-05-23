package restaurant.datamapper;

import java.sql.Date;

import restaurant.model.Order;

public class Main {

	public static void main(String[] args) {
		OrderMapper om = new OrderMapper();
		om.insert(new Order(1,1,2,1,new Date(23),123.4f));
		System.out.println("done");

	}

}
