package basics.controlflow;

// Exercise 22
public class GreatestCommonDivisor {
    public static int getGreatestCommonDivisor(int first, int second) {
        if (first < 10 || second < 10)
            return -1;

        int greatestDivisor = Math.min(first, second);

        while (greatestDivisor > 1) {
            if (first % greatestDivisor == 0 && second % greatestDivisor == 0)
                return greatestDivisor;

            greatestDivisor--;
        }

        return 1;
    }
}
