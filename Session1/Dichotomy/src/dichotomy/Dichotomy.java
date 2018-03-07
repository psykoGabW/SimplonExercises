package dichotomy;

import java.util.Scanner;

public class Dichotomy {

	// Variables used to test dichotomyNthRoot()
	private static int iValueToSqrt = 2;
	private static int iNthRoot = 2;

	// Variables used to test dichotomyStringArray()
	private static String[] names = { "Alfred", "Bernard", "Francesca", "Marc", "Maria", "Pierre", "Thierry",
			"Thomas" };

	// Scan to get user inputs
	private static Scanner scan = new Scanner(System.in);

	public static double dichotomyNthRoot(double dblBegin, double dblEnd) {

		double dblMiddle = dblBegin + (dblEnd - dblBegin) / 2;
		double dblResult = dblBegin;

		if (Math.pow(dblMiddle, iNthRoot) > iValueToSqrt) {
			dblResult = dichotomyNthRoot(dblBegin, dblMiddle);
		} else {
			if (!(Math.abs(dblMiddle - dblBegin) < 1 * Math.pow(10, -15))) {
				dblResult = dichotomyNthRoot(dblMiddle, dblEnd);
			}

		}

		return dblResult;
	}

	public static void testDichotomyNthRootFunction() {
		System.out.print("Please enter positive integer : ");

		iValueToSqrt = scan.nextInt();
		scan.nextLine();

		System.out.print("Please enter nth root wanted : ");
		iNthRoot = scan.nextInt();
		scan.nextLine();

		System.out.println("Result : " + dichotomyNthRoot(1, 2));
	}

	public static int dichotomyStringArray(final String strNameSearched, int iStart, int iEnd) {

		int iResult = -1; // If Names is not in the array, -1 will be returned.

		int iMiddle = iStart + (iEnd - iStart) / 2;

		if (names[iMiddle].compareTo(strNameSearched) > 0) {
			iResult = dichotomyStringArray(strNameSearched, iStart, iMiddle);
		} else {
			if (names[iMiddle].compareTo(strNameSearched) == 0) {
				iResult = iMiddle;
			} else {

				if (iStart != iEnd - 1) {
					iResult = dichotomyStringArray(strNameSearched, iMiddle, iEnd);
				} else {
					if (names[iEnd].compareTo(strNameSearched) == 0) {
						iResult = iEnd;
					}
				}
			}
		}
		return iResult;

	}

	public static void testDichotomyStringArrayFx() {

		// Display of array:
		System.out.print("[ ");
		for (int i = 0; i < names.length; i++) {
			System.out.print(names[i] + ((i < names.length - 1) ? " , " : ""));
		}
		System.out.println(" ]");

		System.out.println("Which name do you search ?");
		String strResearchedName = scan.nextLine();

		int iIndexOfName = dichotomyStringArray(strResearchedName, 0, names.length - 1);

		if (iIndexOfName == -1) {
			System.out.println("No index found for : " + strResearchedName);
		} else {
			System.out.println("Index for " + strResearchedName + " is : " + iIndexOfName);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		testDichotomyStringArrayFx();

		scan.close();

	}

}
