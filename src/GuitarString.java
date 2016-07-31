public class GuitarString {
	// Create local variables tic and set it to 0.
	private int tic = 0;

	// Creates a RingBuffer object called ringbuffer.
	private RingBuffer ringbuffer;

	// The contract for GuitarString if the input is a double.
	GuitarString(double frequency) {
		// Finds the N value by dividing 44,100 by frequency and rounding it
		// down.
		// Then adds 1 to it so it is the ceiling of 44,100 divided by
		// frequency.
		int N = (int) Math.round(44100 / frequency) + 1;

		// Creates a RingBuffer array with an array length of N.
		ringbuffer = new RingBuffer(N);

		// Initializes all N values to 0 on the array.
		for (int i = 0; i < N; i++) {
			ringbuffer.enqueue(0.0);
		}
	}

	// The second constructor for GuitarString which takes in an array of
	// doubles.
	// This is mainly used for testing purposes.
	GuitarString(double[] init) {
		// The length is how long the init array is.
		int length = init.length;

		// Creates a RingBuffer array with the same length as init.
		ringbuffer = new RingBuffer(length);

		// Enqueues all the values of init into ringbuffer so the array and the
		// ringbuffer array are identical.
		for (int i = 0; i < length; i++) {
			ringbuffer.enqueue(init[i]);
		}
	}

	// Replace the whole array with random values between -.5 to .5.
	void pluck() {
		// A for loop for how many items are in the array to enqueue that many
		// random values.
		for (int i = 0; i < ringbuffer.buffer.length; i++) {
			// First dequeue the value so the array will not be full.
			ringbuffer.dequeue();

			// Randomly chooses a value between -.5 and .5 and enqueues it.
			double random = Math.random() - .5;
			ringbuffer.enqueue(random);
		}
	}

	// Average the first two values and times it by a decaying factor.
	// That value is enqueue into the array and gets rid of the first value.
	void tic() {
		// Add 1 to the tic value to keep track how many times this function is
		// called.
		tic++;

		// Saves the first value by dequeueing the ringbuffer which means the
		// first value is
		// gotten rid of. Saves the second value by peeking it and keeping that
		// value.
		double first = ringbuffer.dequeue();
		double second = ringbuffer.peek();

		// Average those two values calculated previously and averaging them.
		// Then times
		// by a decaying factor of .994 and enqueue into the array.
		ringbuffer.enqueue((first + second) / 2 * .994);
	}

	// Returns the first value of the ringbuffer.
	double sample() {
		// Peeking an empty buffer array will cause a run time error.
		if (ringbuffer.isEmpty())
			throw new RuntimeException("Trying to peek an empty stack.");

		return ringbuffer.peek();
	}

	// Returns how many times the tic function has been called.
	int time() {
		return tic;
	}

	public static void main(String[] args) {
		// Enqueue those values as the initial values of test.
		double[] testArray = { 1.0, 2.0, 3.0, 4.0, 5.0 };
		GuitarString test = new GuitarString(testArray);

		// Returns each value and tic it 10 times.
		System.out.print("The first 10 values are: ");
		for (int i = 0; i < 10; i++) {
			double sample = test.sample();
			System.out.print("  " + sample);
			test.tic();
		}
		System.out.println();

		// Returns the new array after the 10 tic.
		System.out.print("The current values of the array are: ");
		for (int i = 0; i < 5; i++) {
			System.out.print(test.ringbuffer.buffer[i] + " ");
		}
		System.out.println();

		// Returns the amount of times tic has been called.
		System.out.println("The times tic is called: " + test.time()); // This
																		// should
																		// be
																		// 10.

		// Pluck the test RingBuffer where all the values are between -.5 and
		// .5.
		test.pluck();

		// Returns the values of test where they should be random values between
		// -.5 and .5.
		System.out.print("The current values after pluck are: ");
		for (int i = 0; i < 5; i++) {
			System.out.print(test.ringbuffer.buffer[i] + " ");
		}
		System.out.println();
	}
}