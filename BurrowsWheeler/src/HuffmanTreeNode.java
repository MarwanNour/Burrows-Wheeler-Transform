
/**
 * Huffman Tree Node
 *
 * Reference: https://www.techiedelight.com/huffman-coding/
 * 
 * @author Marwan Nour marwan.nour@polytechnique.edu
 *
 */
public class HuffmanTreeNode {
	protected HuffmanTreeNode left = null;
	protected HuffmanTreeNode right = null;
	protected int charFreq;
	protected Character c;

	public HuffmanTreeNode(HuffmanTreeNode left, HuffmanTreeNode right, int charFreq, Character c) {
		this.left = left;
		this.right = right;
		this.charFreq = charFreq;
		this.c = c;
	}

	public HuffmanTreeNode(Character c, int charFreq) {
		this.c = c;
		this.charFreq = charFreq;
	}

	public HuffmanTreeNode getLeft() {
		return left;
	}

	public void setLeft(HuffmanTreeNode left) {
		this.left = left;
	}

	public HuffmanTreeNode getRight() {
		return right;
	}

	public void setRight(HuffmanTreeNode right) {
		this.right = right;
	}

	public int getCharFreq() {
		return charFreq;
	}

	public void setCharFreq(int charFreq) {
		this.charFreq = charFreq;
	}

	public char getC() {
		return c;
	}

	public void setC(Character c) {
		this.c = c;
	}

}
