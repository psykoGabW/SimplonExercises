import java.util.ArrayList;
import java.util.Scanner;

public class CaesarCipher {

	private static Scanner _myScan = new Scanner(System.in);
	private static final int ASCII_CODE_A = (int) 'A';
	private static final int ASCII_CODE_Z = (int) 'Z';

	private static final String GLOBAL_QUIT_CHOICE = "Q";
	private static final String GLOBAL_QUIT_CHOICE_LABEL = "Quit";

	private static final String GLOBAL_NEW_INPUT = "N";
	private static final String GLOBAL_PREVIOUS = "P";

	private static final String SUB_MENU_MSG_PREVIOUS_RESULT = "R";
	private static final String SUB_MENU_MSG_NEW_MSG_LABEL = "Enter a new message";
	private static final String SUB_MENU_MSG_PREVIOUS_MSG_LABEL = "Use previous message";
	private static final String SUB_MENU_MSG_PREVIOUS_RESULT_LABEL = "Use previous result";

	private static final String MAIN_MENU_CYPHER_CHOICE = "C";
	private static final String MAIN_MENU_CYPHER_CHOICE_LABEL = "Cypher";
	private static final String MAIN_MENU_DECYPHER_CHOICE = "D";
	private static final String MAIN_MENU_DECYPHER_CHOICE_LABEL = "Decypher";

	private static final String SUB_MENU_OFFSET_NEW_LABEL = "Enter a new offset";
	private static final String SUB_MENU_OFFSET_PREVIOUS_LABEL = "Use previous offset";

	private static final String[][] MAIN_MENU = { { MAIN_MENU_CYPHER_CHOICE, MAIN_MENU_CYPHER_CHOICE_LABEL },
			{ MAIN_MENU_DECYPHER_CHOICE, MAIN_MENU_DECYPHER_CHOICE_LABEL },
			{ GLOBAL_QUIT_CHOICE, GLOBAL_QUIT_CHOICE_LABEL } };

	private static final String[][] SUB_MENU_MSG = { { GLOBAL_NEW_INPUT, SUB_MENU_MSG_NEW_MSG_LABEL },
			{ GLOBAL_PREVIOUS, SUB_MENU_MSG_PREVIOUS_MSG_LABEL },
			{ SUB_MENU_MSG_PREVIOUS_RESULT, SUB_MENU_MSG_PREVIOUS_RESULT_LABEL },
			{ GLOBAL_QUIT_CHOICE, GLOBAL_QUIT_CHOICE_LABEL } };

	private static final String[][] SUB_MENU_OFFSET = { { GLOBAL_NEW_INPUT, SUB_MENU_OFFSET_NEW_LABEL },
			{ GLOBAL_PREVIOUS, SUB_MENU_OFFSET_PREVIOUS_LABEL }, { GLOBAL_QUIT_CHOICE, GLOBAL_QUIT_CHOICE_LABEL } };

	private static String cipherMessage(String strMsg, int iOffset) {
		if (iOffset == 0) {
			return strMsg;
		}

		String strResult = new String();
		for (int iCharPos = 0; iCharPos < strMsg.length(); iCharPos++) {
			int iCharCode = strMsg.codePointAt(iCharPos);

			if (iCharCode >= ASCII_CODE_A && iCharCode <= ASCII_CODE_Z) { // Only UPPERCASED letter will be cipher.

				iCharCode += iOffset;

				if (iCharCode > ASCII_CODE_Z) {
					iCharCode = ASCII_CODE_A + (iCharCode - ASCII_CODE_Z) - 1;
				} else if (iCharCode < ASCII_CODE_A) {
					iCharCode = ASCII_CODE_Z - (ASCII_CODE_A - iCharCode) + 1;
				}

			}

			strResult += (char) iCharCode;

		}

		return strResult;

	}

	/**
	 * displayMenu
	 * 
	 * Param : Array[][] of ID choice / LBL Choice (String)
	 * 
	 * return : Correct hoice made by the user
	 * 
	 */
	public static String displayMyMenu(String[][] menuChoices) {

		String strResult = "";

		if (menuChoices.length > 0) {
			boolean blnChoiceIsCorrect = false;

			System.out.println();
			System.out.println("Please make a choice : ");
			ArrayList<String> lstCorrectAnswers = new ArrayList<String>();

			for (int i = 0; i < menuChoices.length; i++) {
				if (menuChoices[i][0].length() > 0 && menuChoices[i][1].length() > 0) {
					System.out.println("   " + menuChoices[i][0] + " -> " + menuChoices[i][1]);

					lstCorrectAnswers.add(menuChoices[i][0]);

				}
			}

			do {
				System.out.print("Your choice : ");

				strResult = _myScan.nextLine();

				blnChoiceIsCorrect = (lstCorrectAnswers.contains(strResult));

				if (!blnChoiceIsCorrect) {
					System.out.println("Incorrect choice, please try again...");					
				}

			} while (!blnChoiceIsCorrect);

		}

		return strResult;

	}

	public static void main(String[] args) {

		String strMainUserChoice = "";
		String strMsg = "";
		String strResult = "";
		int iOffset = 0;

		do {
			strMainUserChoice = displayMyMenu(MAIN_MENU);

			if (strMainUserChoice.equals(MAIN_MENU_CYPHER_CHOICE)
					|| strMainUserChoice.equals(MAIN_MENU_DECYPHER_CHOICE)) {

				if (iOffset > 0) {
					System.out.println(">>> Current offset: " + Integer.toString(iOffset) + "<<<");
				}

				switch (displayMyMenu(SUB_MENU_OFFSET)) {
				case GLOBAL_QUIT_CHOICE:
					break;
				case GLOBAL_NEW_INPUT:
					iOffset = 0;
				default:
					// GLOBAL_NEW_INPUT + GLOBAL_NEW_PREVIOUS
					// To be sure iOffset != 0
					while (iOffset == 0) {
						System.out.print("Enter offset value: ");
						iOffset = _myScan.nextInt();
						_myScan.nextLine();
					}
				}

				if (!strMsg.equals("") || !strMsg.equals("")) {
					System.out.println();
					System.out.println(">>>>>>>>>>>><<<<<<<<<<<<<<");
					if (!strMsg.equals("")) {
						System.out.println("Current message: " + strMsg);
					}
					if (!strResult.equals("")) {
						System.out.println("Previous result: " + strResult);
					}
					System.out.println(">>>>>>>>>>>><<<<<<<<<<<<<<");
				}

				String strSubMenu = displayMyMenu(SUB_MENU_MSG);
				if (!strSubMenu.equals(GLOBAL_QUIT_CHOICE)) {
					switch (strSubMenu) {
					case GLOBAL_NEW_INPUT:
						strMsg = "";
						break;
					case GLOBAL_PREVIOUS:
						// Nothing to do
						break;
					case SUB_MENU_MSG_PREVIOUS_RESULT:
						strMsg = strResult;
					}
					while (strMsg == "") {
						System.out.print("Enter message to process: ");
						strMsg = _myScan.nextLine();
					}

					
					System.out.println();
					System.out.println("Input: " + strMsg);
					strResult = cipherMessage(strMsg, (strMainUserChoice.equals(MAIN_MENU_DECYPHER_CHOICE)?-1*iOffset:iOffset));
					System.out.println("Output: " + strResult);
					System.out.println();
				}

			}

		} while (!strMainUserChoice.equals(GLOBAL_QUIT_CHOICE));

		_myScan.close();
	}

}
