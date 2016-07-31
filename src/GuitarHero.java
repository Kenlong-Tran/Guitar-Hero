public class GuitarHero {
	public static void main(String[] args) {

		// Sets the keyboard to the possible keys and create an array with that
		// many buttons.
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		GuitarString[] buttons = new GuitarString[keyboard.length()];

		// Create guitar strings, for each concert.
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

			// Plays the sound.
			StdAudio.play(sample);

			// Tics each key.
			for (int i = 0; i < keyboard.length(); i++) {
				buttons[i].tic();
			}
		}
	}
}