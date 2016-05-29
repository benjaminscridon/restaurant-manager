package restaurant.controller.manager;

import restaurant.server.model.Employee;

/**
 * 
 * @author Beniamin
 * @since 2016-05-29
 */
public class SessionManager {

	public static Employee currentEmployee;

	private SessionManager() {

	}

	public static Employee getCurrentEmployee() {
		return currentEmployee;
	}
}
