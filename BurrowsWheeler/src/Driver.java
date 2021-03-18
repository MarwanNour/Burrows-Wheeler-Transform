import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class containing the main function for the project
 * 
 * @author Marwan Nour marwan.nour@polytechnique.edu
 *
 */
public class Driver {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.print("Please Input a String: ");
		String input_str = scan.nextLine();
		scan.close();

		System.out.println("Input String:\t" + input_str);

		// Compression Transformation
		System.out.println("\n---------------- COMPRESSION TRANSFORM ----------------");
		Pair<String, Integer> pair = BWT.compressionTransform(input_str);

		String transf_str = pair.t;
		int I = pair.u;

		System.out.println("\nL (transf_str):\t" + transf_str);
		System.out.println("I = \t\t" + I);

		String Y = BWT.getAlphabet(input_str);
		System.out.println("Y = \t\t" + Y);

		// MTF Encoding
		System.out.println("\n--------------------- MTF ENCODING --------------------");
		ArrayList<Integer> R = MTF.encode(transf_str, Y);

		System.out.print("MTF Encoded R:\t");
		for (int i = 0; i < R.size(); i++) {
			System.out.print(R.get(i) + ", ");
		}
		System.out.println();

		// Huffman Encoding
		System.out.println("\n------------------ HUFFMAN ENCODING -------------------");
		Huffman.buildHuffmanTree(input_str);
		System.out.println();
		
		System.out.println("MTF STRING = " + R.toString());
		String mtf = "";
		for(int i = 0; i < R.size(); i++) {
			mtf+= (char) (R.get(i).intValue() + 32);
		}
		System.out.println(mtf);
		

//		Huffman.buildHuffmanTree(R.toString()); // Don't run huffman on the array itself (useless commas, spaces and brackets)
		Huffman.buildHuffmanTree(mtf);
		

		// MTF Decoding
		System.out.println("\n--------------------- MTF DECODING --------------------");

		String mtf_decoded = MTF.decode(R, Y);

		System.out.println("MTF Decoded L:\t" + mtf_decoded);

		// Decompression Transformation
		System.out.println("\n--------------- DECOMPRESSION TRANSFORM ---------------");
		String str = BWT.decompressionTransform(pair.t, pair.u);
		System.out.println("Decoded:\t" + str);
	}

}
