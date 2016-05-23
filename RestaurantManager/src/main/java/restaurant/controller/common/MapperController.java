package restaurant.controller.common;

import restaurant.datamapper.ClientMapper;
import restaurant.datamapper.EmployeeMapper;
import restaurant.datamapper.OrderDetailMapper;
import restaurant.datamapper.OrderMapper;

public class MapperController {
	private static ClientMapper clientMapper;
	private static EmployeeMapper employeeMapper;
	private static OrderMapper orderMapper;
	private static OrderDetailMapper orderDetailMapper;

	private MapperController() {
	}

	public static ClientMapper getClientMapper() {
		if (clientMapper == null) {
			clientMapper = new ClientMapper();
		}
		return clientMapper;
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
