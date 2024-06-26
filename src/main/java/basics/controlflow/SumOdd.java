package basics.controlflow;

// Exercise 15
public class SumOdd {
    public static void main(String[] args) {
        int min = -10;
        int max = 10;

        for (int i = min; i <= max; i++)
            System.out.printf("Is %-5d odd? %s%n", i, isOdd(i) ? "Yes" : "No");
        System.out.println();

        min = 0;
        System.out.println("Sum odds from " + min + " to " + max + ": " + sumOdd(min, max));
    }

    public static boolean isOdd(int number) {
        if (number <= 0)
            return false;

        return number % 2 != 0;
    }

    public static int sumOdd(int start, int end) {
        int sumOdd = 0;

        if (start > end || start < 0)
            return -1;

        for (int i = start; i <= end; i++)
            if (isOdd(i))
                sumOdd += i;

        return sumOdd;
    }

}
