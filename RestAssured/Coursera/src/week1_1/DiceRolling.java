package week1_1;

import java.util.Random;

public class DiceRolling {
	public static void simulateDice(int rolls) {
		// Step 1 - create random generator and declare an array to store the sum
		Random rand = new Random();
		int[] counts = new int[13];
		// Step 2 - roll the dice for the number of times as taken as input and store
		// the sum into array and increase count
		for (int i = 0; i < rolls; i++) {
			int d1 = rand.nextInt(6) + 1;
			int d2 = rand.nextInt(6) + 1;
			System.out.println("roll is" + d1 + "+" + d2 + "=" + (d1+d2));
			counts[d1 + d2] += 1;
		}
		for (int k = 2; k <= 12; k++) {
			System.out.println(k + "'s=\t" + counts[k] + "\t" + 100.0*counts[k]/rolls);
		}
	}

	public static void main(String[] args) {
		DiceRolling.simulateDice(15);

	}

}
