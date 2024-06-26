package basics.controlflow;

public class DigitSum {
    public static void main(String[] args) {
        DigitSum.challenge();
    }

    public static void challenge() {
        System.out.println("Digit Sum Challenge");

        for (int i = 0; i <= 20; i++)
            System.out.println("Sum Digits of " + i * i + " = " + sumDigits(i * i));
    }

    private static int sumDigits(int number) {
        if (number < 0)
            return -1;

        int sum = 0;

        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }

        return sum;
    }
}
