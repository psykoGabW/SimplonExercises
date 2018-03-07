import java.util.Scanner;

public class Quizz {

	private static final String MESSSAGE_FINAL_SCORE = "Your final score is : %d points !%n";
	private static final String MESSAGE_GOOD_ANSWER_RESULT = "Well done ! You get %d additional points !%n";
	private static final String MESSAGE_WRONG_ANSWER_RESULT = "What a shame... Expected answer : %s %n";

	private static Scanner _myScan = new Scanner(System.in);

	private static int score = 0;

	private static final TestItem[] testItems = {
			new TestItem("What's the weather today ?", "Rainy", 1),
			new TestItem("How do you feel ?", "Good !", 3),
			new TestItem("Put a smile on me", ":-)", 4),
			new TestItem("Do you like Java ?", "Yes !", 5)
	};

	private static void askQuestion(String strQuestion, String strCorrectAnswer, int iQAScore) {

		if (!strQuestion.equals("")) {
			System.out.println(strQuestion);

			String strUserAnswer = _myScan.nextLine();

			if (strUserAnswer.equals(strCorrectAnswer)) {
				score += iQAScore;
				System.out.format(MESSAGE_GOOD_ANSWER_RESULT, iQAScore);
			} else {
				System.out.format(MESSAGE_WRONG_ANSWER_RESULT, strCorrectAnswer);
			}
			System.out.println();
		}
	}

	private static void displayResult() {
		System.out.format(MESSSAGE_FINAL_SCORE, score);
	}

	public static void main(String[] args) {

		for (int i = 0; i < testItems.length; i++) {
			askQuestion(testItems[i].getQuestion(),
					testItems[i].getAnswer(),
					testItems[i].getPoints());
		}

		_myScan.close();
		displayResult();

	}
}
