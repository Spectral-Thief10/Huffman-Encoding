/**
 * @author Tanay Desai and Lucas Tuan
 * This is the Encoder class
 */

public class Encoder {

    private static final int TABLE_SIZE = 256; // assuming ASCII

    /**
     * {@code charEncoding[c]} is the encoding of character {@code c}.
     */
    private final Encoding[] charEncoding;
    private final Encoding curCharEncoding;
    private int size = 0;

    /**
     * Fills {@link #charEncoding} with encodings of characters contained in the subtree starting
     * from {@code node}.
     *
     * {@link #curCharEncoding} must be the path to {@code node}.
     *
     * @param node
     */
    private void calcCharEncoding(HuffmanNode node) {
        /* Traverses the Huffman subtree starting at {@code node}. */

        if (node.data == '\0') {
            /* {@code node} is intermediate.
            
            Processes the left subtree of {@code node}. */
            curCharEncoding.append(false);
            calcCharEncoding(node.left);
            curCharEncoding.removeLast();

            /* Processes the right subtree of {@code node}. */
            curCharEncoding.append(true);
            calcCharEncoding(node.right);
            curCharEncoding.removeLast();
        } else {
            /* {@code node} is a leaf. */
            // Check that the location is null so a character is not counted
            // twice(only happens when there is only one character)
            if(charEncoding[node.data] == null){
                charEncoding[node.data] = curCharEncoding.copy();
                size++;
            }
        }
    }

    public Encoder(HuffmanTree encoding) {
        charEncoding = new Encoding[256];
        curCharEncoding = new Encoding();
        calcCharEncoding(encoding.getRoot());
    }

    /**
     * Character encodings from the Huffman tree passed to the constructor.
     * 
     * Every element {@code entry} of the result is an encoding of one character.
     * {@code entry.getValue()} is the encoding of the character {@code entry.getKey()}.
     * 
     * @return 
     */
    public Pair<Character, String>[] getCharEncoding() {
        Pair<Character, String>[] r = new Pair[size];
        int cur = 0;
        for (int i = 0; i < TABLE_SIZE; i++) {
            if(charEncoding[i] != null){
                r[cur] = new Pair<Character, String>((char) i, charEncoding[i].toString());
                cur++;
            }
        }
        return r;
    }

    /**
     * 
     * @param data
     * @return the encoding of {@code data} using the Huffman tree passed to the constructor
     */
    public String encode(String data) {
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            encoded.append(charEncoding[data.charAt(i)].toString());
        }
        return encoded.toString();
    }

    /**
     * @author Lucas Tuan
     * Linked list that stores the current encoding
     */
    private class Encoding{
        EncodingNode head;

        /**
         * Creates a new encoding
         */
        Encoding(){
            this.head = null;
        }

        /**
         * Adds the direction to the end of the encoding. True for right, false for left
         * @param direction
         */
        void append(boolean direction){
            if(head == null){
                head = new EncodingNode(direction);
                return;
            } else{
                head.append(direction);
            }
        }

        /**
         * Removes the last node in the list
         */
        void removeLast(){
            head = head.removeLast();
        }

        /**
         * Creates a copy of the encoding
         * @return
         */
        Encoding copy(){
            Encoding copy = new Encoding();
            copy.head = head.copy();
            return copy;
        }

        /**
         * Returns a string representation of the list converting the booleans to 1's and 0's
         */
        public String toString(){
            return head.toString();
        }

        /**
         * A node that is part of the encoding linked list
         */
        class EncodingNode{
            boolean direction;
            EncodingNode next;

            /**
             * Creates a new node with the given direction
             * @param direction true for right, false for left
             */
            EncodingNode(boolean direction){
                this.direction = direction;
                next = null;
            }

            void setNext(EncodingNode next){
                this.next = next;
            }

            /**
             * Adds a new node with the given direction to the end of the linked list
             * @param direction
             */
            void append(boolean direction){
                if(next == null){
                    next = new EncodingNode(direction);
                } else{
                    next.append(direction);
                }
            }

            /**
             * Removes the last node in the encoding
             * @return
             */
            EncodingNode removeLast(){
                if(next == null){
                    return null;
                } else{
                    next = next.removeLast();
                    return this;
                }
            }

            /**
             * Creates a copy of this node and its children
             * @return The copy
             */
            EncodingNode copy(){
                EncodingNode copy = new EncodingNode(direction);
                if(next != null){
                    copy.setNext(next.copy());
                }
                return copy;
            }

            /**
             * Returns a string representation of this node and its children
             */
            public String toString(){
                String output = "";
                if(direction){
                    output += 1 + "";
                } else{
                    output += 0 + "";
                }
                if(next != null){
                    output += next.toString();
                }
                return output;
            }
        }
    }
}
