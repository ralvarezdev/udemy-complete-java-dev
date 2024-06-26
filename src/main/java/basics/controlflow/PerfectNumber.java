package basics.controlflow;

// Exercise 23
public class PerfectNumber {
    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++)
            System.out.printf("Is %-7d perfect? %s%n", i, isPerfectNumber(i) ? "Yes" : "No");
    }

    public static boolean isPerfectNumber(int number) {
        if (number < 1)
            return false;

        int sum = 0;
        int divisor = number / 2;

        while (divisor > 0) {
            if (number % divisor == 0)
                sum += divisor;
            divisor--;
        }

        return sum == number;
    }

}
