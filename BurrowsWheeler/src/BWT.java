import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Burrows Wheeler Transform Implementation for the Information Theory course at
 * École Polytechnique
 * 
 * Reference: https://rosettacode.org/wiki/Burrows%E2%80%93Wheeler_transform#Java
 * 
 * @author Marwan Nour marwan.nour@polytechnique.edu
 * 
 */
public class BWT {

	public static boolean DEBUG = false;

	/**
	 * Sort String
	 * 
	 * @param input_str
	 * @return sorted String
	 */
	public static String sortString(String input_str) {
		char sort_ar[] = input_str.toCharArray();
		Arrays.sort(sort_ar);
		return new String(sort_ar);
	}

	/**
	 * Left Rotates the string
	 * 
	 * @param input_str
	 * @param pos
	 * @return left rotation of string by pos
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public static String rotateLeft(String input_str, int pos) throws ArrayIndexOutOfBoundsException {
		if (pos > input_str.length()) {
			throw new ArrayIndexOutOfBoundsException("Invalid Rotation Position: " + pos);
		}
		return input_str.substring(pos) + input_str.substring(0, pos);
	}

	/**
	 * Get Alphabet from given string
	 * 
	 * @param str
	 * @return a string of the alphabet
	 */
	public static String getAlphabet(String str) {
		// Create TreeSet for Y (to remove duplicates and also sort the characters)
		TreeSet<Character> Y_set = new TreeSet<>();
		for (int i = 0; i < str.length(); i++) {
			Y_set.add(str.charAt(i));
		}
		// Create Y as character array
		char Y[] = new char[Y_set.size()];
		int iter = 0;
		for (Character c : Y_set) {
			Y[iter] = c;
			iter++;
		}
		// Convert to String
		String Y_str = new String(Y);
		return Y_str;
	}

	/**
	 * Compression Transformation to apply before compressing the input text
	 * 
	 * @param input_str
	 * @return (transformed block of text, input text position in array)
	 */
	public static Pair<String, Integer> compressionTransform(String input_str) {
		/*** C1: Sort Rotations ***/
		// Create array of strings of length the length of the input string
		String str_ar[] = new String[input_str.length()];

		// Fill array with rotation
		for (int i = 0; i < input_str.length(); i++) {
			str_ar[i] = rotateLeft(input_str, i);
		}

		if (DEBUG) {
			System.out.println("----Rotation-Matrix----");
			for (int i = 0; i < str_ar.length; i++) {
				System.out.println(str_ar[i]);
			}
			System.out.println("-----------------------");
		}

		// Sort the array
		Arrays.sort(str_ar);

		if (DEBUG) {
			System.out.println("----Sorted-Matrix------");
			for (int i = 0; i < str_ar.length; i++) {
				System.out.println(str_ar[i]);
			}
			System.out.println("-----------------------");
		}

		// Find the input_str in the array
		int input_str_index = 0;
		for (int i = 0; i < str_ar.length; i++) {
			if (input_str.equals(str_ar[i])) {
				input_str_index = i;
				break;
			}
		}

		/*** C2: Find Last characters of rotations ***/
		String last_chars = "";
		// Get the characters of the last column
		for (int i = 0; i < str_ar.length; i++) {
			last_chars += str_ar[i].charAt(str_ar.length - 1);
		}

		return new Pair<String, Integer>(last_chars, input_str_index);
	}

	/***
	 * Decompression Transformation to reconstruct original string from transformed
	 * string and position in array
	 * 
	 * @param transf_str
	 * @param pos
	 * @return original input string
	 */
	public static String decompressionTransform(String transf_str, int pos) {
//		// Sort F (the last chars) to form L (the first chars)
//		String first_chars = sortString(transf_str);
//		
//		if (DEBUG) {
//			System.out.println("F = " + first_chars);
//		}

		// Create Matrix (array list of empty strings)
		ArrayList<String> str_al = new ArrayList<>();
		for (int i = 0; i < transf_str.length(); i++) {
			str_al.add("");
		}
		// Build matrix
		for (int i = 0; i < transf_str.length(); i++) {
			// Add L
			for (int j = 0; j < transf_str.length(); j++) {
				str_al.set(j, transf_str.charAt(j) + str_al.get(j));
			}
			// Sort
			Collections.sort(str_al);
		}

		if (DEBUG) {
			System.out.println("----Sorted-Matrix------");
			for (int i = 0; i < transf_str.length(); i++) {
				System.out.println(str_al.get(i));
			}
			System.out.println("-----------------------");
		}

		return str_al.get(pos);
	}
}
