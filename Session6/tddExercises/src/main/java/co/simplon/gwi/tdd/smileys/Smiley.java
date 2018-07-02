package co.simplon.gwi.tdd.smileys;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Smiley {

	static Pattern pattern = Pattern.compile("[:;][-]{0,1}[)D]");

	public static int countSmileys(List<String> arr) {
		if (arr == null) {
			return 0;
		}

		List<Object> matching = arr.stream().filter(pattern.asPredicate()).collect(Collectors.toList());

		return matching.size();
	}
}
