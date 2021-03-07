import java.util.ArrayList;

/***
 * Move to Front coding Implementation for the Information Theory course at
 * École Polytechnique
 * 
 * Reference: https://rosettacode.org/wiki/Move-to-front_algorithm#Java
 * 
 * @author Marwan Nour
 *
 */
public class MTF {

	public static boolean DEBUG = true;

	/**
	 * Move-To-Front Encoder
	 * 
	 * @param transf_str: transformed string (from BWT)
	 * @param Y:          the alphabet
	 * @return the encoding R
	 */
	public static ArrayList<Integer> encode(String transfStr, String Y) {
		// Build R
		ArrayList<Integer> R = new ArrayList<>();
		StringBuilder sb = new StringBuilder(Y);
		for (char c : transfStr.toCharArray()) {
			int charIndex = sb.indexOf("" + c);
			R.add(charIndex);
			sb = sb.deleteCharAt(charIndex).insert(0, c);
		}

		return R;
	}

	/**
	 * Move-To-Front Decoder
	 * 
	 * @param R: the encoding output from MWT encoder
	 * @param Y: the alphabet
	 * @return transformed string (from BWT)
	 */
	public static String decode(ArrayList<Integer> R, String Y) {
		StringBuilder transfStr = new StringBuilder();
		StringBuilder sb = new StringBuilder(Y);
		for (int index : R) {
			char c = sb.charAt(index);
			transfStr = transfStr.append(c);
			sb = sb.deleteCharAt(index).insert(0, c);
		}

		return transfStr.toString();
	}
}
