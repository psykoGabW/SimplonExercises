
public class TestItem {
	private String question;
	private String answer;
	private int points;

	private final static String FORMAT_TO_STRING = "[  HashCode : %s , Question : %s , Answer : %s , Points : %d ]";

	public TestItem(String question, String answer, int points) {
		this.question = question;
		this.answer = answer;
		this.points = points; 
	}

	public String getQuestion() {
		return question;
	}

		public String getAnswer() {
		return answer;
	}

	

	public int getPoints() {
		return points;
	}

	
	public String toString() {
		return (String.format(FORMAT_TO_STRING, this.hashCode(), question, answer, points));
	}

	public boolean equals(TestItem otherItem) {
		return ((this.question.equals(otherItem.question)) &&
				(this.answer.equals(otherItem.answer)) &&
				(this.points == otherItem.points));
	}

	public static void main(String[] args) {
		TestItem t1 = new TestItem("Q1", "A1", 1);
		TestItem t2 = new TestItem("Q3", "A2", 0);

		System.out.println(t1);
		System.out.println(t2);

		
		System.out.println("t1==t2 : " + (t1 == t2));
		System.out.println("t1.equals(t2) : " +  (t1.equals(t2)));

		TestItem t3 = t1;

		System.out.println(t3);
		System.out.println("t1==t3 : " + (t3 == t1));
		System.out.println("t1.equals(t3) : " + t1.equals(t3));

		TestItem t4 = new TestItem("Q1", "A1", 1);
		System.out.println(t4);
		System.out.println("t1==t4 : " + (t1==t4));
		System.out.println("t1.equals(t4) : " + t1.equals(t4));
		
	}

}
