package co.simplon.gwi.hibernate.exo01;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenuUtils {

	public ConsoleMenuUtils() {
		// TODO Auto-generated constructor stub
	}

	private static void displayObject(Object o) {
		Class<? extends Object> oClass = o.getClass();

		String objectDisplay = "";

		for (Method m : oClass.getDeclaredMethods()) {

			if (m.getName().contains("get")) {
				String attributeName = m.getName().replaceAll("get", "");
				String attributeValue = "";
				try {
					attributeValue = (String) oClass.getMethod(m.getName()).invoke(o).toString();
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					attributeValue = "Getting Value failed!";
				} catch (IllegalAccessException e) {
					attributeValue = "Getting Value failed!";
				} catch (IllegalArgumentException e) {
					attributeValue = "Getting Value failed!";
				} catch (InvocationTargetException e) {
				}

				objectDisplay += (objectDisplay.equals("") ? "" : " - ") + attributeName + " : " + attributeValue;
			}
		}

		System.out.println(objectDisplay);
	}

	/**
	 * 
	 * @param minAcceptedValue
	 *            (int >= 0)
	 * @param maxAcceptedValue
	 *            (int >= 0)
	 * @param isEmptyAccepted
	 *            (boolean) if true, user can input nothing and we return -1;
	 * @return -1 if isEmptyAccepted && user input "" int between minAcceptedValue
	 *         and maxAcceptedValue
	 */
	public static int getUserChoice(Scanner scanner, int minAcceptedValue, int maxAcceptedValue,
			boolean isEmptyAccepted) {
		int userChoice = -1;
		if (scanner == null) {
			return userChoice;
		}
		String literalChoice = "";
		// get User choice
		do {
			try {
				System.out.println("Your choice : ");
				literalChoice = scanner.nextLine();
				userChoice = Integer.parseInt(literalChoice);
			} catch (NumberFormatException n) {
				userChoice = -1;
			} finally {
				if ((!(userChoice >= minAcceptedValue && userChoice <= maxAcceptedValue))
						|| (literalChoice.isEmpty() && !isEmptyAccepted)) {
					System.out.println(
							"Please enter a number in [ " + minAcceptedValue + " ; " + maxAcceptedValue + " ]");
				}
			}
		} while (userChoice == -1 || (isEmptyAccepted && literalChoice.isEmpty()));

		if (literalChoice.isEmpty() && isEmptyAccepted) {
			userChoice = -1;
		}
		System.out.println();
		return userChoice;
	}

	public static String getUserChoice(Scanner scanner, String[] expectedValues, boolean isEmptyAccepted) {
		String userChoice = null;

		if (scanner == null) {
			return userChoice;
		}
		boolean shouldRetry = false;
		List<String> checkingList = Arrays.asList(expectedValues);

		do {
			shouldRetry = false;
			userChoice = null;

			System.out.print("Make your choice : ");
			String input = scanner.nextLine();
			input = input.trim();
			input = input.toUpperCase();

			if (!input.equals("")) {
				if (!checkingList.contains(input)) {
					System.out.println("Your choice (" + input + ") is not recognized.");
					shouldRetry = true;
					userChoice = null;
				} else {
					userChoice = input;
				}
			} else {
				userChoice = null;
				if (!isEmptyAccepted) {
					System.out.println("Empty value is not authorized.");
					shouldRetry = true;
				}
			}

			if (shouldRetry) {
				System.out.println("Please retry...");
			}

		} while (shouldRetry && (userChoice == null && !isEmptyAccepted));

		return userChoice;
	}

	public static Integer getInteger(Scanner scanner, boolean isEmptyAccepted) {
		Integer intUserInput = null;
		if (scanner == null) {
			return intUserInput;
		}
		String userInput = "";
		// get User choice
		do {
			try {
				userInput = scanner.nextLine().trim();
				if (!userInput.isEmpty()) {
					intUserInput = Integer.parseInt(userInput);
				}
			} catch (NumberFormatException n) {
				intUserInput = null;
			} finally {
				if (intUserInput == null && !userInput.isEmpty() && !isEmptyAccepted) {
					System.out.println(
							"Please enter an Integer value !");
					System.out.print(">");

				}
			}
		} while (intUserInput == null && !isEmptyAccepted );

		return intUserInput;
	}

	public static Double getDouble(Scanner scanner, boolean isEmptyAccepted) {
		Double doubleInput = null;
		if (scanner == null) {
			return doubleInput;
		}
		String userInput = "";
		// get User choice
		do {
			try {
				userInput = scanner.nextLine().trim();
				if (!userInput.isEmpty()) {
					doubleInput = Double.parseDouble(userInput);
				}
			} catch (NumberFormatException n) {
				doubleInput = null;
			} finally {
				if (doubleInput == null && !userInput.isEmpty() && !isEmptyAccepted) {
					System.out.println(
							"Please enter a Double value !");
					System.out.print(">");
				}
			}
		} while (doubleInput == null && !isEmptyAccepted);
		return doubleInput;
	}

	public static Double getDouble(Scanner scanner, boolean isEmptyAccepted, Double minAcceptedValue,
			Double maxAcceptedValue) {
		boolean acceptedValue = false;
		Double doubleInput = null;

		while (!acceptedValue) {
			doubleInput = getDouble(scanner, isEmptyAccepted);

			acceptedValue = ((doubleInput == null && isEmptyAccepted) ||
					(doubleInput >= minAcceptedValue && doubleInput <= maxAcceptedValue));
			
			if ( !acceptedValue) {
				System.out.println("Wrong input : " + doubleInput);
				System.out.println("Expected value between " + minAcceptedValue + " and " + maxAcceptedValue +
						(isEmptyAccepted? " or null (Press Enter)":""));
			}
		}
		return doubleInput;
	}

	public static Long getLong(Scanner scanner, boolean isEmptyAccepted) {
		Long longInput = null;
		if (scanner == null) {
			return longInput;
		}
		String userInput = "";
		// get User choice
		do {
			try {
				userInput = scanner.nextLine().trim();
				if (!userInput.isEmpty()) {
					longInput = Long.parseLong(userInput);
				}
			} catch (NumberFormatException n) {
				longInput = null;
			} finally {
				if (longInput == null && !userInput.isEmpty() && !isEmptyAccepted) {
					System.out.println(
							"Please enter a Long value !");
					System.out.print(">");
				}
			}
		} while (longInput == null && !isEmptyAccepted);
		return longInput;
	}
	
	public static LocalDate getLocalDate(Scanner scanner, DateTimeFormatter dTF, boolean isEmptyAccepted) {
		LocalDate localDate = null;
		if (scanner == null) {
			return localDate;
		}
		String userInput = "";
		// get User choice
		do {
			try {
				userInput = scanner.nextLine().trim();
				if (!userInput.isEmpty()) {
					localDate = LocalDate.parse(userInput, dTF);
				}
			} catch (DateTimeParseException n) {
				localDate = null;
			} finally {
				if (localDate == null && !userInput.isEmpty() && !isEmptyAccepted) {
					System.out.println(
							"Please enter Date in format : " + dTF.toString() + " ! ");
					System.out.print(">");

				}
			}
		} while (localDate == null && !isEmptyAccepted );

		return localDate;
	}

	public static LocalDateTime getLocalDateTime(Scanner scanner, DateTimeFormatter dTF, boolean isEmptyAccepted) {
		LocalDateTime localDateTime = null;
		if (scanner == null) {
			return localDateTime;
		}
		String userInput = "";
		// get User choice
		do {
			try {
				userInput = scanner.nextLine().trim();
				if (!userInput.isEmpty()) {
					localDateTime = LocalDateTime.parse(userInput, dTF);
				}
			} catch (DateTimeParseException n) {
				localDateTime = null;
			} finally {
				if (localDateTime == null && !userInput.isEmpty() && !isEmptyAccepted) {
					System.out.println(
							"Please enter Date Time in format : " + dTF.toString() + " ! ");
					System.out.print(">");

				}
			}
		} while (localDateTime == null && !isEmptyAccepted);

		return localDateTime;
	}

}
