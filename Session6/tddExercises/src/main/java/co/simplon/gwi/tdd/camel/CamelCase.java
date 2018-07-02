package co.simplon.gwi.tdd.camel;

public class CamelCase {

	public static String camelCase(String str) {
		if (str != null && str.length() > 0) {
			
			String[] words = str.split("[^a-zA-Z0-9]");

			str = "";
			for (String word : words) {
				if (word.length() > 0) {

					if (str.equals("")) {
						// lower case of first character for the 1st word
						str = word.substring(0, 1).toLowerCase();
					} else {
						// upper case of first character for all word except the first
						str += word.substring(0, 1).toUpperCase();
					}
					if (word.length() > 1) {
						str += word.substring(1).toLowerCase();
					}
				}
			}
		}
		return str;
	}
}
