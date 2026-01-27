/**
 * 
 * 
 * 
 * 	Creates a heap to insert the elements into before making the Huffman Tree
 * 	This makes the tree have a better worst and best case runtime
 */
public class Heap{
	public int size;
	public HuffmanNode[] heap;
	public int cap;
	
	/** 
	 * 
	 * Constructor creates the heap as an array
	 * initializes the size to 0 and the capacity to 5
	 */
	public Heap() {
		heap = new HuffmanNode[5];
		size = 0;
		cap = 5;
	}
	
	/** 
	 * Returns the number of elements in the heap
	 * @return size
	 */
	public int getSize() {
		return size; // Returns the number of elements in the heap
	}
	
	/** 
	 * Adds a new element to the heap
	 * Calls sink on the new element
	 * @param el
	 */
	public void addElement(HuffmanNode el) {
		if(isFull()) {
			resize(); // Resizes the heap if it is full
		}
		heap[size]= el; // Adds the new element to the heap
		size++; // Increments the size by 1
		int start = (size/2); // Gets the starting index
		for(int i = start; i >=0; i--) {
			sink(heap,i,size); // Calls sink starting at the middle index
		}
	}
	
	/** 
	 * 
	 * Creates the heap by calling sink on the current element.
	 * It keeps swapping until every parent is greater than the child
	 * @param A
	 * @param i
	 * @param sizeH
	 */
	public void sink(HuffmanNode[] A, int i, int sizeH) {
		HuffmanNode minimum = heap[i]; // Sets the top value to be the largest
		int left = 2 * i+1; // Sets the left child
		int right = 2 * i+2; // Sets the right child
		
		int index = i; // Sets the minimum index to the top value
		if(left < sizeH && heap[left].frequency<minimum.frequency) {
			minimum = heap[left]; // Sets minimum to the left child
			index = left; // Sets the minimum index to the left child index
		}
		if(right < sizeH && heap[right].frequency < minimum.frequency) {
			minimum = heap[right]; // Sets the minimum to the right child
			index = right; // Sets the minimum index to the right child
		}
		if(minimum.frequency < heap[i].frequency) {
			HuffmanNode temp = heap[i]; // Swaps the minimum and the current value
			heap[i] = minimum;
			heap[index] = temp;
			sink(heap,index,sizeH); // Calls sink on the new minimum value
		}
		
	}
	
	/** 
	 * 
	 * Resizes the heap if it is full
	 * it creates a new array that is 2 * the size of the previous
	 *  it then copies the elements over
	 */
	public void resize() {
		HuffmanNode[] arrayB = new HuffmanNode[2*cap]; // Makes a new Array that is 2 times the previous size
		if(size == cap) {
			for(int i = 0; i < cap; i++) {
				arrayB[i]=heap[i]; // Copies the elements over
			}
			heap=arrayB; // sets the original array to the new array
			cap = 2*cap; // changes the capacity size
		}
	}
	
	/** 
	 * Returns true if the heap is full and false otherwise
	 * @return boolean
	 */
	public boolean isFull() {
		if(cap == size) // Returns true if the heap is full
		{
			return true;
		}
		return false;
	}
	
	public HuffmanNode remove() {
		if(size == 0) // returns null if the heap is empty
		{
			return null;
		}
		HuffmanNode removed = heap[0]; // makes a temp huffmanNode with the head
		heap[0]=heap[size-1]; // swaps the first and last huffmanNode in the heap
		heap[size-1]=null; // sets the last huffmanNode to null
		size--; // decrements the size by q
		if(size > 1) 
		{
		sink(heap,0,size); // calls sink on the new head
		}
		return removed; // returns the removed element
		
	}
	/** 
	 * toString method for the heap
	 */
	public String toString() {
		String str = "";
		for(int i = 0; i < size; i++) {
			str+="|"+heap[i].data + " " + heap[i].frequency+"|";
		}
		return str;
	}
}