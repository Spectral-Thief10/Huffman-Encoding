
/**
 * 
 * @author Cameron Rodgers
 *	Huffman Tree is the class that has all the functions and 
 *  data need to build the tree.  
 */
public class HuffmanTree {
	private final HuffmanNode root;
	
	/** Cameron
	 * Constructor gets the frequencies from FreqTable f
	 * loops through each element putting them into a priorityQueue, before taking the results 
	 * and building a tree. Finally it sets root to the head of the tree using poll. 
	 */
	public Heap heap;
	public HuffmanTree(FreqTable f) {
		heap = new Heap();
		Pair<Character,Integer>[] freq = f.getFrequencies(); // Gets all the frequencies and puts them in a Pair array
		
		for(int i = 0; i < freq.length;i++) 
		{
			HuffmanNode node = new HuffmanNode(freq[i].getKey(),freq[i].getValue()); // Creates a new huffmanNode with the frequency and the character
			heap.addElement(node); // Puts the huffmanNode into a heap
		}
		
		while(heap.getSize() > 1) {
			HuffmanNode left = heap.remove(); // Sets the left child to the head of the heap
			HuffmanNode right = heap.remove(); // Sets the right child to the head of the heap
			int sum = left.frequency + right.frequency; // Creates the sum by adding the frequencies
			heap.addElement(new HuffmanNode(sum, left, right)); // puts the newly created tree into the heap
		}

		HuffmanNode last = heap.remove();

		if(last != null && last.data != '\0'){
			HuffmanNode dummy = new HuffmanNode(0, last, last);
			root = dummy;
		} else{
			root = last; // removes the head of the heap and makes it the root
		}
	}
	
	
	/** 
	 * get Root returns the root. 
	 * @return huffmanNode root
	 */
	public HuffmanNode getRoot() {
		return root; 
	}
	
	/** 
	 * Returns a string of the huffman tree in preorder traversal using a helper method
	 */
	public String str = "";
	public String treeToString() {
		treeToStringHelper(root);
		return str;
	}
	public void treeToStringHelper(HuffmanNode node) {
		if(node == null) // returns if the node is null
		{
			return;
		}
		else {
			if(node.left==null && node.right==null) // checks if the value is a leaf node
			{
				str += "(" +node.data + " " + node.frequency + ", " + "~ " + ", ~"+")"; // adds the huffmanNode to str
			}
			else if (node.left != null && node.right == null) // checks to see if the left child is not null and the right is
			{
				str += "(" +node.data + " " + node.frequency + ", " + node.left.data + " " + node.left.frequency + ", ~"+")";
			}
			else if(node.left == null && node.right != null) // checks to see if the right child is not null and the left is 
			{
				str += "(" +node.data + " " + node.frequency + ", " + "~, " + node.right.data + " "+node.right.frequency+")";
			}
			else // adds both values to the string
			{
				str += "(" +node.data + " " + node.frequency + ", " + node.left.data+" "+node.left.frequency+", " +node.right.data+" "
						+node.right.frequency+")";
			}
			treeToStringHelper(node.left); // recursive call on the left subtree
			treeToStringHelper(node.right); // recursive call on the right subtree
		}
	}

}
