package co.simplon;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleMenu {
	public static final String FORMAT_ERROR_MSG="Saisie invalide. Il faut saisir un nombre.";
	public static final String RANGE_ERROR_MSG="Saisie invalide. Il faut saisir un nombre entre 1 et ";
	private String[] options;
	private Scanner input;
	
	public ConsoleMenu(String[] options, Scanner input) {
		this.options= options;
		this.input= input;
	}
	private boolean isValid(int i) {
		return (i >0) && (i < options.length); 
	}
	public int askSelection() {
		int result= -1;
		do {
			for(int i=0; i != options.length; ++i) {
				System.out.println(((i>0)?""+i+"." : "")+options[i]);
			}
			try {
				result= input.nextInt();
				if(!isValid(result)) {
					System.err.println(RANGE_ERROR_MSG+(options.length-1));
					result=-1;
				}
			}catch(InputMismatchException e) {
				System.err.println(FORMAT_ERROR_MSG);
			}
			input.nextLine();//flush remaining line feed in input buffer
		}while(result==-1);
		return result;
	}
	public static void main(String[] args) {
		
		String[] options= {"title", "option 1", "option 2"};
		Scanner input= new Scanner(System.in);
		ConsoleMenu menu= new ConsoleMenu(options, input);
		System.out.println(menu.askSelection());
	}

}
