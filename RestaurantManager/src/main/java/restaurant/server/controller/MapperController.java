package restaurant.server.controller;

import restaurant.server.datamapper.ClientMapper;
import restaurant.server.datamapper.EmployeeMapper;
import restaurant.server.datamapper.OrderDetailMapper;
import restaurant.server.datamapper.OrderMapper;
import restaurant.server.datamapper.ProductMapper;

public class MapperController {
	private static ClientMapper clientMapper;
	private static EmployeeMapper employeeMapper;
	private static OrderMapper orderMapper;
	private static OrderDetailMapper orderDetailMapper;
	private static ProductMapper productMapper;
	
	private MapperController() {
	}

	public static ClientMapper getClientMapper() {
		if (clientMapper == null) {
			clientMapper = new ClientMapper();
		}
		return clientMapper;
	}
	
	public static ProductMapper getProductMapper() {
		if (productMapper == null) {
			productMapper = new ProductMapper();
		}
		return productMapper;
	}

	public static OrderMapper getOrderMapper() {
		if (orderMapper == null) {
			orderMapper = new OrderMapper();
		}
		return orderMapper;
	}

	public static EmployeeMapper getEmployeeMapper() {
		if (employeeMapper == null) {
			employeeMapper = new EmployeeMapper();
		}
		return employeeMapper;
	}

	public static OrderDetailMapper getOrderDetailMapper() {
		if (orderDetailMapper == null) {
			orderDetailMapper = new OrderDetailMapper();
		}
		return orderDetailMapper;
	}

}
