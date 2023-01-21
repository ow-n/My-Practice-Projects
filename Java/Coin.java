// Lab 2 - Part 2 - Coin Toss Simulator

package cointoss;

public class Coin {

	// Constant used for Calculating Heads or Tails
	public static final double VALUE_HALF = 0.5;
	// public static final int HEADS = 1;
	// public static final int TAILS = 0;
	private String sideUp; 


	// No-arg Constructor
	public Coin() {
		// setSideUp(); // not needed
	}

	// Gets the coin side facing up
	public String getSideUp() {
		return this.sideUp;
	}

	// Sets coin side up
	public void setSideUp() {
		// Random value selects heads if less than 0.5
		if (Math.random() < VALUE_HALF) {
			this.sideUp = "heads";
		} else {
			this.sideUp = "tails";
		}
	}


	// Creates array of coins
	public void toss() { // ERROR dont need return, declare void

		// Declares Variables for Counter
		int heads = 0;
		int tails = 0;

		// Create array to store Coins
		//Coin[] coinResults = new Coin[20]; // ERROR dont need this

		// Create 20 tossed coins
		for (int i = 0; i < 20; i++) {

			// Create coin object
			/* Coin singleCoin = new Coin(); // ERROR dont need create new object


			singleCoin.setSideUp();

			// Counter
			if (singleCoin.getSideUp() == HEADS) {
				heads++;
			} else {
				tails++;
			}

			// Assigning tossed coins to its index
			coinResults[i] = singleCoin;  */

			setSideUp();

			if (this.getSideUp() == "heads") {
				heads++;
			} else {
				tails++;
			}

			System.out.printf("Toss %d: %s\n", i + 1, sideUp);

		}

		// Shows results of heads
		System.out.println("\nThe coin flipped heads " + heads + " time(s).");
		System.out.print("The coin flipped tails " + tails + " time(s).");
	}

}