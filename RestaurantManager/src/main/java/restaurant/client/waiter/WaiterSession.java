package restaurant.client.waiter;

import restaurant.client.waiter.view.ProductTable;
import restaurant.server.model.Employee;

public class WaiterSession {

	private static Employee currentWaiter;
	private static ProductTable currentProduct;
	
	public static ProductTable getCurrentProduct() {
		return currentProduct;
	}

	public static void setCurrentProduct(ProductTable currentProduct) {
		WaiterSession.currentProduct = currentProduct;
	}

	public static Employee getCurrentWaiter() {
		return currentWaiter;
	}

	public static void setCurrentWaiter(Employee currentWaiter) {
		WaiterSession.currentWaiter = currentWaiter;
	}
	
}
