import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * Huffman Coding implementation for the for the Information Theory course at
 * École Polytechnique
 * 
 * Reference: https://www.techiedelight.com/huffman-coding/
 * 
 * @author Marwan Nour marwan.nour@polytechnique.edu
 *
 */
public class Huffman {

	/**
	 * isLeaf checks if node is a leaf in huffman tree
	 * 
	 * @param node
	 * @return true if leaf, false otherwise
	 */
	public static boolean isLeaf(HuffmanTreeNode node) {
		return (node.getLeft() == null && node.getRight() == null);
	}

	/**
	 * Huffman Encoder
	 * 
	 * @param node
	 * @param inputStr
	 * @param huffmanCode
	 */
	public static void encode(HuffmanTreeNode node, String inputStr, HashMap<Character, String> huffmanCode) {
		if (node == null) {
			return;
		}

		if (isLeaf(node)) {
			if (inputStr.length() > 0) {
				huffmanCode.put(node.getC(), inputStr);
			} else {
				huffmanCode.put(node.getC(), "1");
			}
		}

		encode(node.getLeft(), inputStr + '0', huffmanCode);
		encode(node.getRight(), inputStr + '1', huffmanCode);
	}

	/**
	 * Huffman Decoder
	 * 
	 * @param node
	 * @param index
	 * @param sb
	 * @return index of char in tree (recursive)
	 */
	public static int decode(HuffmanTreeNode node, int index, StringBuilder sb) {
		if (node == null) {
			return index;
		}

		if (isLeaf(node)) {
			System.out.print(node.getC()); // print or add it to a String that is passed as parameter
			return index;
		}

		index++;

		if (sb.charAt(index) == '0') {
			node = node.getLeft();
		} else {
			node = node.getRight();
		}

		index = decode(node, index, sb);
		return index;
	}

	/**
	 * Huffman Tree Builder for decoding given string
	 * 
	 * @param inputStr
	 */
	public static void buildHuffmanTree(String inputStr) {
		// handle empty string
		if (inputStr == null || inputStr.length() == 0) {
			return;
		}

		// Get frequencies of characters, store in HashMap
		HashMap<Character, Integer> freq = new HashMap<>();
		for (char c : inputStr.toCharArray()) {
			freq.put(c, freq.getOrDefault(c, 0) + 1);
		}

		// Create priority queue for nodes in tree
		PriorityQueue<HuffmanTreeNode> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.getCharFreq()));

		// Add new tree node for each character in the queue
		for (Entry<Character, Integer> entry : freq.entrySet()) {
			pq.add(new HuffmanTreeNode(entry.getKey(), entry.getValue()));
		}

		// Loop until queue size is 1 (root is left)
		while (pq.size() != 1) {

			// Poll nodes with highest frequency from queue
			HuffmanTreeNode left = pq.poll();
			HuffmanTreeNode right = pq.poll();

			int sum = left.getCharFreq() + right.getCharFreq();
			pq.add(new HuffmanTreeNode(left, right, sum, null));
		}

		// Get root
		HuffmanTreeNode root = pq.peek();

		// Traverse Huffman tree and store codes in HashMap
		HashMap<Character, String> huffmancode = new HashMap<>();
		encode(root, "", huffmancode);

		// print codes
		System.out.println("Codes:  \t" + huffmancode);
		System.out.println("InputStr: " + inputStr);

		// Print encoded string
		StringBuilder sb = new StringBuilder();
		for (char c : inputStr.toCharArray()) {
			sb.append(huffmancode.get(c));
		}
		System.out.println("Encoded String:\t" + sb);
		System.out.print("Decoded String:\t");

		if (isLeaf(root)) {
			// Special case: a, aa, aaa ...
			while (root.charFreq-- > 0) {
				System.out.print(root.getC());
			}
		} else {
			// Decode
			int index = -1;
			while (index < sb.length() - 1) {
				index = decode(root, index, sb);
			}
		}
		System.out.println();
		
	}

}
