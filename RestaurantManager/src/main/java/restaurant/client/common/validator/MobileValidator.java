package restaurant.client.common.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileValidator {

	private final String MOBILE_PATTERN = "^[0-9]{10}$";
	private Pattern pattern;
	private Matcher matcher;

	public MobileValidator() {
		pattern = Pattern.compile(MOBILE_PATTERN);
	}

	public boolean validate(String mobile) {

		matcher = pattern.matcher(mobile);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}
}
