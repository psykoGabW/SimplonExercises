package co.simplon.GwiFactory;

public class CaseCoordinate{
	
	private int columnIndex;
	private int rowIndex;
	
	/*
	 * userCoordinate must contain 2 characters max.
	 * 		first character should be Alpha 
	 * 		second one should be numeric
	 */
	public void setCaseCoordinate(String userCoordinate) {
		char column= userCoordinate.charAt(0);
		char line = userCoordinate.charAt(1);
		this.columnIndex= (column - 'A');
		this.rowIndex = (line - '1') ;
	}
	
	public int getRowIndex() {
		return rowIndex;
	}
	public int getColumnIndex() {
		return columnIndex;
	}
	
	public String toString() {
		return( Character.toChars('A'+columnIndex).toString()+
				Character.toChars('1'+rowIndex).toString());
				}
	
	public boolean isCorrectMove(int[][] gameArea)
	{
		boolean result = false;
		
		if ( (gameArea != null && columnIndex>=0 && columnIndex < gameArea.length) &&
				(gameArea[columnIndex] != null && rowIndex>=0 && rowIndex < gameArea[columnIndex].length)
				) {
			result = (gameArea[columnIndex][rowIndex]==0);
		}
		
		return result;
	}
	
}	