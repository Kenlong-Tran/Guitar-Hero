public class GuitarHeroVisualizer {
	public static void main(String[] args) {
		//Initialize x and oldSample to 0.
		int x = 0;
		double oldSample = 0.0;

		//Creates the graph of the visualizer.
		StdDraw.setXscale(0.0, 1000.0);
		StdDraw.setYscale(-3.0, 3.0);

		// Create guitar strings, for each concert.
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		GuitarString[] buttons = new GuitarString[keyboard.length()];
		for (int i = 0; i < keyboard.length(); i++) {
			double frequency = 440.0 * Math.pow(1.05956, i - 24);
			buttons[i] = new GuitarString(frequency);
		}
		
		while (true) {
			// If a key has been typed.
			if (StdDraw.hasNextKeyTyped()) {
				// Gets the key value.
				char key = StdDraw.nextKeyTyped();

				// If the key is in the index of keyboard continue with the if
				// statement.
				if (0 <= keyboard.indexOf(key)) {
					// Pluck the GuitarString associated to the key value.
					buttons[keyboard.indexOf(key)].pluck();
				}
			}
			
			// Initialize sample to 0.
			double sample = 0.0;
			
			// Accumulates the sample of each key.
			for (int i = 0; i < keyboard.length(); i++) {
				sample = sample + buttons[i].sample();
			}
			
			// Tics each key.
			for (int i = 0; i < keyboard.length(); i++) {
				buttons[i].tic();
			}

			//Draws a line from (x-1,oldSample) to (x,sample) which shows the fluctuation
			//in frequency.
			StdDraw.line(x - 1, oldSample, x, sample);
			
			//Sets the oldSample to the current sample.
			oldSample = sample;
			
			//Adds 1 to the value x.
			x++;
			
			//If x is 1,000, clears the graph and initialize x to 0.
			if (x == 1000) {
				x = 0;
				StdDraw.clear();
			}
		}
	}
}