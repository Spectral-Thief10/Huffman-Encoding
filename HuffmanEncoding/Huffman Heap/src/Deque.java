
public class Deque<T> {
	T[] array;
	int cap;
	int size;
	int front = 0;
	int back = 0;
	int originalCap;

	public Deque(int cap) {
		// Constructor for a given capacity
		array = (T[]) (new Object[cap]);
		this.cap = cap;
		originalCap = cap;
		size = 0;
	}

	public Deque() {
		// Constructor for no given capacity
		array = (T[]) (new Object[16]);
		cap = 16;
		size = 0;
		originalCap = cap;
	}

	public void addLast(T item) {
		if (size == cap) {
			resize();
		}
		if (size == 0) {
			front = 0;
			back = 0;
			array[back]=item;
			size++;
		} else {
			// Resizes if the size == capacity
			// Wraps the back around to the start if it is at the
			// Of the array
			if (back == cap - 1) {
				back = 0;
			} else {
				// Increments the back by 1
				back++;
			}
			// Sets the value at the back
			array[back]=item;
			size++;
		}
	}

	public void resize() {
		// Runs if the size is at max capacity
		if (size == cap) {
			// New capacity is 2 times the old capacity
			int newCap = (int) (cap * 2);
			// Creates a new array
			T[] newArray = (T[]) (new Object[newCap]);
			int e = 0;
			// Copies items from the front to the end
			// Into the new array
			for (int i = front; i < cap; i++) {
				newArray[e]=array[i];
				e++;
			}
			// Copies items from the start up until
			// The front in the new array
			for (int i = 0; i < front; i++) {
				newArray[e]=array[i];
				e++;
			}
			// Sets the old array to the new array
			array = newArray;
			// Changes the capacity to the new capacity
			cap = newCap;
			// Resets the front to 0
			front = 0;
			// Resets the back to the last element
			back = size - 1;
			// Runs if the capacity / 4 is >= the original capacity
		} else if (cap / 2 > originalCap) {
			// The new capacity is half of the current capacity
			int newCap = cap / 2;
			T[] newArray = (T[]) (new Object[newCap]);
			// If the back is less than the front
			if (back < front) {
				// Copies items from the front to the end of the array
				int e = 0;
				for (int i = front; i < cap; i++) {
					newArray[e]=array[i];
					e++;
				}
				// Starts at 0 and copies items up until the front into the array
				for (int i = 0; i < front; i++) {
					if (e < size) {
						newArray[e]=array[i];
						e++;
					}
				}
				// Runs if the back is >= front
			} else {
				// Starts at the first index and copies all the non
				// Null values into the array
				int e = 0;
				for (int i = 0; i < cap; i++) {
					if (array[i]!= null && e < size) {
						newArray[e]=array[i];
						e++;
					}
				}
			}
			// Sets the array to the newArray
			// Sets the capacity to the new capacity
			// Front goes back to 0
			// Sets back to the furthest element
			array = newArray;
			cap = newCap;
			front = 0;
			back = size - 1;

		}
		// Resizes if the size == 0 and the cap /2 > originalCap
		else if (size == 0 && cap / 2 > originalCap) {
			int newCap = cap / 2;
			T[] newArray = (T[]) (new Object[newCap]);
			// Sets the array to the newArray
			// Sets the capacity to the new capacity
			// Front goes back to 0
			// Sets back to the furthest element
			array = newArray;
			cap = newCap;
			back = 0;
			front = 0;
		}

	}

	public void addFirst(T item) {
		// Resizes if the capacity is the size
		if (size == cap) {
			resize();
		}
		// If it is empty, it sets the value at the front
		if (isEmpty()) {
			front = 0;
			back = 0;
			array[front]=item;
			size++;
		} else {

			// Wraps the front around once it reaches the end
			if (front == 0) {
				front = cap - 1;
			} else {
				// Front goes back 1 time
				front--;
			}
			array[front]=item;
			size++;
		}

	}

	public T removeFirst() {
		// Throws EmptyDeque Exception if you remove nothing
		if (isEmpty()) {
			return null;
		}
		// The value to be removed is the value at the front
		T value = (T) array[front];
		// You set that value to null to remove it
		array[front]=null;
		// Wraps the front around to 0 if it is at the end of the array
		if (front == cap - 1) {
			front = 0;
		} else {
			// Front gets incremented by 1 otherwise to go to the
			// next item in the array
			front++;
		}
		// The size gets decremented by 1 after removing an element
		size--;
		// resets the front and the back to 0
		// if the array is empty
		if (isEmpty()) {
			front = 0;
			back = 0;
		}
		// Resizes if the size is < cap / 4
		if (size < cap / 4) {
			resize();
		}
		// Returns the removed value
		return value;
	}

	public T removeLast(){
		// Throws an EmptyDequeException if the array
		// Is empty
		if (isEmpty()) {
			return null;
		}
		// Gets the back value to be removed
		T value = (T) array[back];
		// Removes the value by setting it to null
		array[back]=null;
		// Wraps the back around to the front if it reaches the starting point
		if (back == 0) {
			back = cap - 1;
		} else {
			// Otherwise it gets set back to the previous element
			back--;
		}
		// The size is decremented by 1 after removing the element
		size--;
		// Sets the back and the front to 0
		if (isEmpty()) {
			front = 0;
			back = 0;
		}
		// Resizes if the size < cap / 4
		if (size < cap / 4) {
			resize();
		}
		return value;
	}

	public T peekFirst(){
		// Throw EmptyDequeException if it is empty
		if (isEmpty()) {
			return null;
		}
		// Returns the front value
		return (T) array[front];
	}

	public T peekLast() {
		// Throw EmptyDequeException if it is empty
		if (isEmpty()) {
			return null;
		} else {
			// Returns the back value
			return (T) array[back];
		}

	}

	public boolean isEmpty() {
		// It's empty if the size is 0
		return size == 0;
	}
	
	public T get(int i) {
		if(array[i]==null) {
			return null;
		}
		else {
			return array[i];
		}
	}


	public int size() {
		// Returns the size
		return size;
	}

	public String toString() {
		// To String method
		String str = "[";
		for (int i = 0; i < cap; i++) {
			if (i == cap - 1) {
				str += array[i] + "]";
			} else {
				str += array[i] + ", ";
			}
		}
		return str;
	}

}
