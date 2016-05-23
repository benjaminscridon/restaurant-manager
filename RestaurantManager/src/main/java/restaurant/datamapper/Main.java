package restaurant.datamapper;

import restaurant.model.OrderDetail;

public class Main {
	public static void main(String[] args){
		OrderDetailMapper om = new OrderDetailMapper();
		om.update(new OrderDetail(9,1,"tut",12,"done"));
		System.out.println();
	}

}
