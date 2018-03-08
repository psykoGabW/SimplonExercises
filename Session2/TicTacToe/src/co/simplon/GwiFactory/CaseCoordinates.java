package co.simplon.GwiFactory;

import java.util.regex.Pattern;

public class CaseCoordinates {

	private int columnIndex= -1;
	private int rowIndex= -1;
	private int[][] gameArea= null;
	
	private final Pattern patternFormat =  Pattern.compile("[A-Z][1-9]");

	/*
	 * userCoordinates must contain 2 characters max. first character should be Alpha
	 * second one should be numeric
	 */
	public void setCaseCoordinates(String userCoordinates, int[][] gameArea)
			throws NullPointerException, IllegalArgumentException, IndexOutOfBoundsException {

		if (gameArea == null) {
			throw new NullPointerException("Game area is not defined ! ");
		} else {
			this.gameArea = gameArea;
		}

		if (!inputCanBeParsed(userCoordinates)) {
			this.resetCoordinates();
			throw new IllegalArgumentException(
					"User coordinates ( "+userCoordinates+" ) can't be parsed ! Expected format : [Column][Row]. By example : B2");
		}

		char column = userCoordinates.charAt(0);
		char line = userCoordinates.charAt(1);
		this.columnIndex = (column - 'A');
		this.rowIndex = (line - '1');

		if (columnIndex < 0 || columnIndex >= gameArea.length || rowIndex < 0 || rowIndex >= gameArea.length) {
			this.resetCoordinates();
			throw new IndexOutOfBoundsException("This coordinates ( "+ userCoordinates + " ) are out of range !");
		}

		if (gameArea[columnIndex][rowIndex] != 0) {
			this.resetCoordinates();
			throw new IndexOutOfBoundsException("Your move ( " + userCoordinates + " ) is not allowed ! This position is not free...");
		}

	}

	public int getRowIndex() {
		return rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public String toString() {
		return (Character.toChars('A' + columnIndex).toString() +
				Character.toChars('1' + rowIndex).toString());
	}

	private boolean inputCanBeParsed(String input) {
		return ( (input != null) && (patternFormat.matcher(input).matches())); 		
		
	}
	
	private void resetCoordinates() {
		columnIndex= -1;
		rowIndex= -1;
		gameArea= null;
	}
	
	public void markPlayerChoice(int playerID) throws IllegalStateException  {
		if ( gameArea == null || columnIndex == -1 || rowIndex == -1) {
			throw new IllegalStateException("Coordinates are not initialized ! ");
		} else {
			gameArea[columnIndex][rowIndex]= playerID;
		}
	}

}