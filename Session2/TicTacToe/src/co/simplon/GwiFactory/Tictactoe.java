package co.simplon.GwiFactory;

import java.util.Scanner;

class Tictactoe {

	private static final int GAME_AREA_WIDTH = 4; // For this version GAME_AREA_WIDTH must be in [3-9]

	private static final int PLAYER_ONE_ID = 1;
	private static final int PLAYER_TWO_ID = -1 * PLAYER_ONE_ID;

	private static final char DISPLAY_TOKEN_PLAYER_1 = 'X';
	private static final char DISPLAY_TOKEN_PLAYER_2 = 'O';

	private static final Scanner scan = new Scanner(System.in);

	// Keep last action in order to test only row, column and diagonals concerned

	private static boolean currentPlayerIsWinning(int currentPlayerID, CaseCoordinate userMove, int[][] gameArea) {

		if (userMove == null) {
			return false;
		}

		boolean winningMove = true;

		// Check line alignment
		for (int column = 0; (column < GAME_AREA_WIDTH && winningMove); column++) {
			winningMove = (gameArea[column][userMove.getRowIndex()] == currentPlayerID);
		}

		if (!winningMove) {
			// Check Column alignment
			winningMove = true;
			for (int row = 0; (row < GAME_AREA_WIDTH && winningMove); row++) {
				winningMove = (gameArea[userMove.getColumnIndex()][row] == currentPlayerID);
			}

			if (!winningMove && userMove.getColumnIndex() == userMove.getRowIndex()) {
				winningMove = true;
				boolean diagOneOk = true;
				boolean diagTwoOk = true;
				for (int x = 0; (x < GAME_AREA_WIDTH && winningMove); x++) {
					diagOneOk = (diagOneOk && (gameArea[x][x] == currentPlayerID));
					diagTwoOk = (diagTwoOk && (gameArea[GAME_AREA_WIDTH - x - 1][x] == currentPlayerID));
					winningMove = (diagOneOk || diagTwoOk);
				}

			}
		}

		return winningMove;
	}

	private static void displayGameAreaCase(int[][] gameArea, int x, int y) {
		System.out.print("|");
		switch (gameArea[x][y]) {
		case PLAYER_ONE_ID:
			System.out.print(DISPLAY_TOKEN_PLAYER_1);
			break;
		case PLAYER_TWO_ID:
			System.out.print(DISPLAY_TOKEN_PLAYER_2);
			break;
		default:
			System.out.print(" ");
		}

		if (x == GAME_AREA_WIDTH - 1) {
			System.out.print("|");
		}
	}

	private static void displayGameArea(int[][] gameArea) {
		// Display of blanks for column names alignment
		System.out.print("   ");
		// Display of Column names
		for (int column = 0; column < GAME_AREA_WIDTH; column++) {
			System.out.print(" " + (char) ('A' + column));
		}
		System.out.println();

		for (int line = 0; line < GAME_AREA_WIDTH; line++) {
			System.out.print(" " + (line + 1) + " ");
			for (int column = 0; column < GAME_AREA_WIDTH; column++) {
				displayGameAreaCase(gameArea, column, line);
			}
			System.out.println();
		}
	}

	/*
	 * Argument : ID of current player Return : Position wanted by the player
	 * (%column%line) with %column (char) %line (int) Example: Upperleft corner : A1
	 */
	private static CaseCoordinate getPlayerChoice(int playerID, int[][] gameArea) {

		CaseCoordinate userChoice = new CaseCoordinate();
		boolean inputIsOK = false;
		do {
			String userInput = "";
			do {

				System.out.println((playerID == PLAYER_ONE_ID ? "Player One (" + DISPLAY_TOKEN_PLAYER_1 + ") move : "
						: "Player Two (" + DISPLAY_TOKEN_PLAYER_2 + ") move : "));

				userInput = scan.nextLine();
				userInput = userInput.trim().toUpperCase();
				if (userInput.length() != 2) {
					userInput = "";
				} else {
					if ((userInput.charAt(0) < 'A' || userInput.charAt(0) > 'A' + (GAME_AREA_WIDTH - 1)) ||
							(userInput.charAt(1) < '1' || userInput.charAt(1) > '1' + (GAME_AREA_WIDTH - 1))) {
						userInput = "";
					}
				}
				if (userInput == "") {
					System.out.println("Input is not valid ! Please make a choice in game area.");
					System.out.println("Format expected : ColumnRow");
					System.out.println("Example : A2");
					displayGameArea(gameArea);

				}
			} while (userInput == "");
			userChoice.setCaseCoordinate(userInput);
			inputIsOK = userChoice.isCorrectMove(gameArea);

			if (!inputIsOK) {
				System.out.println("Your move is not allowed ! This position is not free...");
			}

		} while (!inputIsOK);

		return userChoice;

	}

	private static void markPlayerChoice(int playerID, CaseCoordinate userMove, int[][] gameArea) {

		if (userMove.isCorrectMove(gameArea)) {
			gameArea[userMove.getColumnIndex()][userMove.getRowIndex()] = playerID;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int freeCases = GAME_AREA_WIDTH * GAME_AREA_WIDTH;
		int[][] gameArea = new int[GAME_AREA_WIDTH][GAME_AREA_WIDTH];

		int currentPlayer = PLAYER_ONE_ID;
		boolean currentPlayerWin = false;

		do {
			displayGameArea(gameArea);
			CaseCoordinate userMove = getPlayerChoice(currentPlayer, gameArea);
			markPlayerChoice(currentPlayer, userMove, gameArea);
			currentPlayerWin = currentPlayerIsWinning(currentPlayer, userMove, gameArea);
			if (!currentPlayerWin) {
				currentPlayer *= -1;
				freeCases--;
			}

		} while ((freeCases != 0) && (!currentPlayerWin));

		displayGameArea(gameArea);
		if (currentPlayerWin) {
			System.out.println(
					"Congratulation " + (currentPlayer == PLAYER_ONE_ID ? "Player One" : "Player Two") + "!!!! ");
			System.out.println("You win !!!");
		} else {
			System.out.println("Drawn game !!! ");
		}

		scan.close();

	}

}
