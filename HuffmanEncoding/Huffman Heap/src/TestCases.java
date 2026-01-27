import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertEquals;

/**
 * Cameron Rodgers
 */
public class TestCases {
	/**
	 * This test case tests the remove mehtod in the heap
	 */
	@Test
	public void TestRemove() {
		FreqTable table = new FreqTable();

		// Increment frequencies for characters in a string
		String testString = "Time for testing the remove method!";
		for (char ch : testString.toCharArray()) {
			table.incrament(ch);
		}
		Heap h = new Heap();
		Pair<Character, Integer>[] frequencies = table.getFrequencies();

		// adds new huffmanNodes to the heap
		for (int i = 0; i < frequencies.length; i++) {
			char ch = (char) frequencies[i].getKey();
			int frequency = (int) frequencies[i].getValue();
			HuffmanNode a = new HuffmanNode(ch, frequency);
			h.addElement(a);

		}
		// removes and adds elements to the Heap
		h.remove();
		h.remove();
		h.remove();
		h.remove();
		h.remove();
		h.addElement(new HuffmanNode('s', 1));
		h.remove();
		h.remove();
		h.remove();
		h.remove();
		h.remove();
		h.remove();
		h.remove();
		h.addElement(new HuffmanNode('s', 1));
		h.addElement(new HuffmanNode('a', 1));
		h.remove();
		h.addElement(new HuffmanNode('s', 10));
		h.addElement(new HuffmanNode('s', 3));
		h.addElement(new HuffmanNode('s', 0));
		h.remove();
		h.remove();
		h.remove();
		h.remove();
		h.remove();
		h.remove();
		h.remove();
		h.remove();
		h.remove();
		assertEquals(0, h.getSize());
	}
	
	/**
	 * This test case tests the addElement in the heap
	 */
	@Test
	public void testHeapAdd() {
		Heap h = new Heap();
		// creates a heap and checks if each of its children are greater than it
		h.addElement(new HuffmanNode('s', 1));
		boolean check = true;
		HuffmanNode[] nodes = new HuffmanNode[h.getSize()];
		for (int i = 0; i < h.getSize(); i++) {
			nodes[i] = h.remove();
		}
		for (int i = 0; i < nodes.length; i++) {
			int left = i * 2 + 1;
			int right = i * 2 + 2;
			if (left < nodes.length) {
				if (nodes[left].frequency < nodes[i].frequency) {
					check = false;
				}
			}
			if (right < nodes.length) {
				if (nodes[right].frequency < nodes[i].frequency) {
					check = false;
				}
			}
		}
		assertEquals(true, check);

		// adds new elements to the heap and checks if each of the value's children are
		// greater than it
		h.addElement(new HuffmanNode('s', 1));
		h.addElement(new HuffmanNode('q', 2));
		h.addElement(new HuffmanNode('d', 3));
		h.addElement(new HuffmanNode('e', 4));
		h.addElement(new HuffmanNode('h', 5));
		h.addElement(new HuffmanNode('h', 0));
		h.addElement(new HuffmanNode('h', 3));
		h.addElement(new HuffmanNode('h', 7));
		h.addElement(new HuffmanNode('h', 6));

		nodes = new HuffmanNode[h.getSize()];
		for (int i = 0; i < h.getSize(); i++) {
			nodes[i] = h.remove();
		}
		for (int i = 0; i < nodes.length; i++) {
			int left = i * 2 + 1;
			int right = i * 2 + 2;
			if (left < nodes.length && nodes[left] != null) {
				if (nodes[left].frequency < nodes[i].frequency) {
					check = false;
				}
			}
			if (right < nodes.length && nodes[right] != null) {
				if (nodes[right].frequency < nodes[i].frequency) {
					check = false;
				}
			}
		}
		assertEquals(true, check);
	}

	/**
	 * This test case tests the empty string for the heap, frequency table, and the Huffman Tree
	 */
	@Test
	public void TestEmpty() {
		String test = "";
		FreqTable table = new FreqTable();
		for (char ch : test.toCharArray()) {
			table.incrament(ch);
		}
		Pair<Character, Integer>[] frequencies = table.getFrequencies();
		Heap h = new Heap();
		for (int i = 0; i < frequencies.length; i++) {
			char ch = (char) frequencies[i].getKey();
			int frequency = (int) frequencies[i].getValue();
			HuffmanNode a = new HuffmanNode(ch, frequency);
			h.addElement(a);

		}
		HuffmanTree t = new HuffmanTree(table);
		assertEquals(0, frequencies.length);
		assertEquals("", h.toString());
		assertEquals("", t.treeToString());
	}
	
	/**
	 * This tests the frequency table and the heap with an odd number of characters
	 */
	@Test
	public void TestEvenNumberChars() {
		// adds the string chars to a frequency table, a heap, and a tree
		String test = "Time to build a tree!";
		FreqTable table = new FreqTable();
		for (char ch : test.toCharArray()) {
			table.incrament(ch);
		}
		Pair<Character, Integer>[] frequencies = table.getFrequencies();
		Heap h = new Heap();
		for (int i = 0; i < frequencies.length; i++) {
			char ch = (char) frequencies[i].getKey();
			int frequency = (int) frequencies[i].getValue();
			HuffmanNode a = new HuffmanNode(ch, frequency);
			h.addElement(a);

		}
		
		// checks that each of the heap value's children are greater than their parents
		boolean check = true;
		HuffmanNode[] n = new HuffmanNode[h.getSize()];
		for(int i = 0; i < h.getSize();i++) {
			n[i] = h.remove();
		}
		
		for (int i = 0; i < h.getSize(); i++) {
			
			int left = i * 2 + 1;
			int right = i * 2 + 2;
			if (left < frequencies.length) {
				if (n[left] != null && n[left].frequency < n[i].frequency) {
					
					check = false;
				}
			}
			if (right < frequencies.length) {
				if (n[right]!= null && n[right].frequency < n[i].frequency) {
					check = false;
				}
			}
		}
		assertEquals(true, check);
		// checks the length of the frequencies
		assertEquals(14, frequencies.length);
	}

	/**
	 * This tests the heap and the frequency table with an even number of characters
	 */
	@Test
	public void TestOddNumberChars() {
		// does the same test but with an odd number of chars for the table
		String test = "Time to build a tree Again!";
		FreqTable table = new FreqTable();
		for (char ch : test.toCharArray()) {
			table.incrament(ch);
		}
		Pair<Character, Integer>[] frequencies = table.getFrequencies();
		Heap h = new Heap();
		
		for (int i = 0; i < frequencies.length; i++) {
			char ch = (char) frequencies[i].getKey();
			int frequency = (int) frequencies[i].getValue();
			HuffmanNode a = new HuffmanNode(ch, frequency);
			h.addElement(a);
			
		}
		HuffmanNode[] n = new HuffmanNode[h.getSize()];
		for(int i = 0; i < h.getSize();i++) {
			n[i] = h.remove();
		}
		boolean check = true;
		for (int i = 0; i < h.getSize(); i++) {
			
			int left = i * 2 + 1;
			int right = i * 2 + 2;
			if (left < frequencies.length) {
				
				if (n[left] != null && n[left].frequency < n[i].frequency) {
					
					check = false;
				}
			}
			if (right < frequencies.length) {
				
				if (n[right]!= null && n[right].frequency < n[i].frequency) {
					
					check = false;
				}
			}
		}
		assertEquals(true, check);
		assertEquals(17, frequencies.length);
	}

	/**
	 * This tests that all the leaf nodes are the only nodes with the data values
	 * in the huffman Tree
	 */
	@Test
	public void TreeChildrenTest() {
		// creates multiple trees and checks that each leaf node has the data
		FreqTable table = new FreqTable();
		String testString = "Dog";
		for (char ch : testString.toCharArray()) {
			table.incrament(ch);
		}
		HuffmanTree t = new HuffmanTree(table);
		String str = "(\0 3, g 1, \0 2)(g 1, ~ , ~)(\0 2, D 1, o 1)(D 1, ~ , ~)(o 1, ~ , ~)";
		assertEquals(str, t.treeToString());
		FreqTable table2 = new FreqTable();
		testString = "Hi";
		for (char ch : testString.toCharArray()) {
			table2.incrament(ch);
		}
		t = new HuffmanTree(table2);
		str = "(\0 2, H 1, i 1)(H 1, ~ , ~)(i 1, ~ , ~)";
		assertEquals(str, t.treeToString());
	}

}