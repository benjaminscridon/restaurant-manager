package restaurant.client.common.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

	/**
	 * This regex will enforce these rules: At least one upper case english
	 * letter At least one lower case english letter At least one digit At least
	 * one special character Minimum 8 in length
	 */
	private final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
	private Pattern pattern;
	private Matcher matcher;

	public PasswordValidator() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	public boolean validate(String password) {
		matcher = pattern.matcher(password.toString());
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}
}