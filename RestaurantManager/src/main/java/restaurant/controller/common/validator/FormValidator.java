package restaurant.controller.common.validator;

public class FormValidator {

	public boolean validate(String... strings) {
		for (String string : strings) {
			System.out.println("String "+string);
			if (string.length() == 0) {
				return false;
			}
		}
		return true;
	}
}
