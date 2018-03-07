package javaReference;

import java.util.Arrays;

public class TestReference {

	private static int[][] copyOfArray(int[][] input) {
		int[][] result = null;

		if (input != null) {
			result = new int[input.length][];
			for (int i = 0; i < result.length; i++) {
				if (input[i] != null) {
					result[i] = new int[input[i].length];
					for (int j = 0; j < result[i].length; j++) {
						result[i][j] = input[i][j];
					}
				}
			}
		}

		return result;
	}

	private static int[][] superficialCopy(int[][] input) {
		int[][] result = null;

		if (input != null) {
			result = new int[input.length][];
			for (int i = 0; i < result.length; i++) {
				result[i] = input[i];
			}
		}

		return result;
	}

	private static void displayArray(int[][] array) {
		if (array == null) {
			System.out.println("null");
		} else {
			System.out.print("[Hash : " + array.hashCode() + "] { ");
			for (int i = 0; i < array.length; i++) {
				if (i > 0) {
					System.out.print(", ");
				}
				if (array[i] == null) {
					System.out.print("null");
				} else {
					System.out.print("[Hash : " + array[i].hashCode() + "] { ");
					for (int j = 0; j < array[i].length; j++) {
						System.out.print((j > 0 ? ", " : "") + array[i][j]);
					}
					System.out.print(" }");
				}
			}
			System.out.println(" }");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * int[] minArray = {1,2}; int[] maxArray= {10,12,14,15};
		 * 
		 * SwapArrays.printArrays(minArray, maxArray);
		 * 
		 * int[] thirdArray = {1,2};
		 * 
		 * SwapArrays.printArrays(minArray, thirdArray);
		 */

		/*
		 * Person p1 = new Person("Nom","Prénom",1); Person p2 = new
		 * Person("Nom","Prénom",1);
		 * 
		 * if (p1.equals(p2)) { System.out.println("Twins they are ! "); }else {
		 * System.out.println("Twins they're not ... "); }
		 * 
		 */

		int[][] array1 = null;

		System.out.println("Array 1");
		displayArray(array1);

		int[][] copyOfArray1 = copyOfArray(array1);
		System.out.println("Copy of Array 1");
		displayArray(copyOfArray1);

		System.out.println();

		int[][] array2 = { { 1, 2 }, { 2, 4, 5 } };
		System.out.println("Array 2");
		displayArray(array2);
		int[][] copyOfArray2 = copyOfArray(array2);
		System.out.println("Copy of Array 2");
		displayArray(copyOfArray2);

		int[][] cloneOfArray2 = array2.clone();
		System.out.println("Clone of array 2 ");
		displayArray(cloneOfArray2);
		System.out.println();

		int[][] array3 = new int[2][];
		array3[0] = new int[] { 10, 35 };
		System.out.println("Array 3");
		displayArray(array3);

		int[][] copyOfArray3 = copyOfArray(array3);
		System.out.println("Copy of Array 3");
		displayArray(copyOfArray3);

		if (copyOfArray3.equals(array3)) {
			System.out.println("Equality by equals");
		}

		if (cloneOfArray2.equals(array2)) {
			System.out.println("Equality on clone ");
		}

		int[][] shallowCopy = superficialCopy(array2);
		if (shallowCopy.equals(array2)) {
			System.out.println("Shadow copy equals array2!");
		}

		int[][] newRefArray2 = array2;
		if (newRefArray2.equals(array2)) {
			System.out.println("Equality on reference ");
		}

		if (Arrays.equals(array2, copyOfArray2)) {
			System.out.println("Arrays equals OK !");
		}
		if (Arrays.equals(array2, cloneOfArray2)) {
			System.out.println("Arrays.equals is ok on clone");
		}
		if (Arrays.equals(newRefArray2, array2)) {
			System.out.println("Arrays.equals is ok on ref");
		}

		

		if (Arrays.deepEquals(array2, copyOfArray2)) {
			System.out.println("Arrays.deepEquals is ok on copy");
		}
		if (Arrays.deepEquals(array2, cloneOfArray2)) {
			System.out.println("Arrays.deepEquals is ok on clone");
		}
		if (Arrays.deepEquals(array2,  newRefArray2)) {
			System.out.println("Arrays.deepEquals is on on ref");
		}

		

	} // end of main

}
