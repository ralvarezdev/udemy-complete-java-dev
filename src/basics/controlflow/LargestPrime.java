package basics.controlflow;

import java.util.Random;

// Exercise 26
public class LargestPrime {
	public static void main(String[] args) {
		int max = new Random().nextInt(Integer.MAX_VALUE);

		System.out.println("Largest prime divisor number of " + max + " is " + getLargestPrime(max));
	}

	public static int getLargestPrime(int number) {
		if (number < 0)
			return -1;

		int divisor = number / 2;

		if (For.isPrime(number))
			return number;

		while (divisor > 1) {
			if (number % divisor == 0 && For.isPrime(divisor))
				return divisor;

			divisor--;
		}

		return -1;
	}
}
