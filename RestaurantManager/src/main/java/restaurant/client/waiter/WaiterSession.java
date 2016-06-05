package restaurant.client.waiter;

import restaurant.client.waiter.view.ProductTable;

public class WaiterSession {

	public static ProductTable currentProduct;

	public static ProductTable getCurrentProduct() {
		return currentProduct;
	}

	public static void setCurrentProduct(ProductTable currentProduct) {
		WaiterSession.currentProduct = currentProduct;
	}
	
}
