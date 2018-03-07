package javaReference;

import java.util.Arrays;

class SwapArrays {

	public static void printArrays(int[] a1, int[] a2) {

		if (a1.length < a2.length) {
			int[] aBuffer = a1;
			a1 = a2;
			a2 = aBuffer;
		}

		printArray(a1);
		printArray(a2);

		if (arrayEquality(a1, a2)) {
			System.out.println("I think both arrays are equals");
		}

		if (Arrays.equals(a1, a2)) {
			System.out.println("The 2 arrays are equals ! ");
		}

	}

	private static void printArray(int[] arrayToDisplay) {
		System.out.print("[");
		for (int i = 0; i < arrayToDisplay.length; i++) {
			System.out.print(arrayToDisplay[i]);
			if (i < arrayToDisplay.length - 1) {
				System.out.print(",");
			}
		}
		System.out.println("]");
	}

	public static boolean arrayEquality(int[] a, int[] b) {
		boolean arrayEqualityResult = false;

		// Pour comparer mon tableau a et mon tableau b
		// Je compare les valeurs de chaque cellule

		// On considère que les tableaux sont égaux
		// si toutes les valeurs des cellules sont égales

		if (a == null && b == null) {
			arrayEqualityResult = true;
		} else {
			if ( a != null && b != null && a.length == b.length) {
				arrayEqualityResult = true;
				int i = 0;
				while (arrayEqualityResult && (i < a.length)) {
					arrayEqualityResult = (a[i] == b[i]);
					i++;
				}
			}
		}

		return arrayEqualityResult;
	}

}
