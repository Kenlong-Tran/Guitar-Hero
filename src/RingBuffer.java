public class RingBuffer {
	// Creates a buffer array and declares size,first, and last to 0.
	double[] buffer;
	private int size = 0;
	private int first = 0;
	private int last = 0;

	// The constructor for RingBuffer where it takes an integer called capacity
	// and
	// creates an array with that integer as the size.
	RingBuffer(int capacity) {
		buffer = new double[capacity];
	}

	// Returns the current size of the array. If the array is empty, size is 0.
	// If the array is full of values the size is the array length.
	int size() {
		return size;
	}

	// Returns true if the size is 0 which means the buffer array is empty.
	boolean isEmpty() {
		return (size == 0);
	}

	// Return true if the array is filled with values. True means the array is
	// full.
	boolean isFull() {
		return (size == buffer.length);
	}

	// Adds a value to the end of the array.
	void enqueue(double x) {
		// If the array is too full to enqueue another value, returns a runtime
		// error.
		if (isFull())
			throw new RuntimeException("Trying to add to a full stack.");

		// Increase the size of the array by 1.
		size++;
		// Saves the last value into position and then increment it by 1 for the
		// next enqueue.
		int position = last;
		last++;
		// If the last is equal to the buffer.length, change the last value to 0
		// since
		// this array is a cyclic wrap-around.
		if (last == buffer.length) {
			last = 0;
		}

		// Saves the x value into position of the buffer.
		buffer[position] = x;

	}

	// Returns the value at the first position of the list and gets rid of the
	// value.
	double dequeue() {
		// If the array is empty and is trying to return the first position,
		// returns a runtime error.
		if (isEmpty())
			throw new RuntimeException("Trying to pop an empty stack.");

		// Decreases the size by one.
		size--;

		// Saved the first value into position and then increase the first value
		// by 1 for the
		// next time dequeue is called.
		int position = first;
		first++;

		// If the first is at the end of the array, first equals 0 since the
		// array is a
		// cyclic wrap-around.
		if (first == buffer.length) {
			first = 0;
		}

		// Returns the value of buffer at position.
		return (buffer[position]);

	}

	// Returns the value at the first position and keeps the value.
	double peek() {
		// Peeking an empty buffer array will cause a run time error.
		if (isEmpty())
			throw new RuntimeException("Trying to peek an empty stack.");

		// Returns the value at the first position of buffer.
		return buffer[first];
	}

	public static void main(String[] args) {
		// Tests if RingBuffer works.

		// The array length has 5 values.
		int N = 5;
		RingBuffer buffer = new RingBuffer(N);

		// Checks if the array is full and empty before adding any values.
		System.out.println("Is the array full? " + buffer.isFull()); // Should
																		// returns
																		// false.
		System.out.println("Is the array empty? " + buffer.isEmpty()); // Should
																		// return
																		// true.

		// Checks the values of first and last when they are initially created.
		System.out.println("The first value is: " + buffer.first); // Should be
																	// 0.
		System.out.println("The last value is: " + buffer.last); // Should be 0.

		// Enqueues 5 elements so the array should be full.
		for (int i = 1; i <= N; i++) {
			buffer.enqueue(i); // buffer array is {1,2,3,4,5}
		}

		// Prints out the values of the array.
		System.out.print("Values has been added to the array. They are: ");
		for (int i = 0; i < N; i++) {
			System.out.print(buffer.buffer[i] + "  ");
		}
		System.out.println();

		// Checks the size and the peek value.
		System.out.println("The size is: " + buffer.size()); // Should return 5.
		System.out.println("The peek value is: " + buffer.peek()); // Should
																	// return 1.
		System.out.println("Is the array full? " + buffer.isFull()); // Should
																		// return
																		// true.

		// Dequeues the first 2 values.
		System.out.println("The first dequeue value is: " + buffer.dequeue()); // Should
																				// return
																				// 1.
		System.out.println("The second dequeue value is: " + buffer.dequeue()); // Should
																				// return
																				// 2.
		System.out.println("The size is: " + buffer.size()); // The size should
																// be 3.
		System.out.println("The peek value is: " + buffer.peek()); // The value
																	// should be
																	// 3.
		System.out.println("The first value is: " + buffer.first); // The value
																	// should be
																	// 2.
		System.out.println("The last value is: " + buffer.last); // The value
																	// should be
																	// 0.

	}
}