package co.simplon.bnppf.gwi_factory.sub1;

public class VisiP {
	
	private final static String GREETING = "Hello from VisiP !";
	
	static void message(){
		System.out.println(GREETING);
		}

	public static void messagePublic() {
		System.out.println("Public " + GREETING);
	}
	
}
