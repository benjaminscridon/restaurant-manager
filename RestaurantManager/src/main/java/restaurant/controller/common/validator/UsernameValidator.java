package restaurant.controller.common.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator {

	private final String USERNAME_PATTERN = "^[a-z0-9_-]{5,15}$";
	private Pattern pattern;
	private Matcher matcher;

	public UsernameValidator() {
		pattern = Pattern.compile(USERNAME_PATTERN);
	}

	public boolean validate(String username) {

		matcher = pattern.matcher(username);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}
}
