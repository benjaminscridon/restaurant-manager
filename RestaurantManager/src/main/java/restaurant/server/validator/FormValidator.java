package restaurant.server.validator;

public class FormValidator {

	public boolean validate(String... strings) {
		for (String string : strings) {
			if (string.length() == 0) {
				return false;
			}
		}
		return true;
	}
}
