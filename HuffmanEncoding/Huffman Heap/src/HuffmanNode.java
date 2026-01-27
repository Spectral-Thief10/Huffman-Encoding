/**
 * 
 * 
 *  This is the Node used by the Huffman coding 
 *  it has it's frequency, data, left and right neighbors. 
 *
 */
class HuffmanNode {
	char data;
    int frequency;
    HuffmanNode left, right;

    /**  
     * Constructor for leaf nodes AKA the Char
     * @param data  CHAR to be set as node data
     * @param frequency INT count of occurrence 
     */
    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        left = right = null;
    }

    /** 
     * Constructor for internal nodes the Frequency of 
     * it's children. 
     * @param frequency total from left and right 
     * @param left node child that appears more often. 
     * @param right node of least often list. 
     */
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.data = '\0';
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}