package basics.controlflow;

import java.util.Scanner;

public class MinMax {
    public static void main(String[] args) {
        MinMax.challenge();
    }

    public static void challenge() {
        System.out.println("Min and Max Challenge");

        Scanner scanner = new Scanner(System.in);

        double min = 0, max = 0, number;
        String input;
        int iter = 0;

        while (true) {
            System.out.print("Enter a Number: ");

            input = scanner.nextLine();

            try {
                number = Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                break;
            }

            if (iter == 0)
                min = max = number;
            else if (number < min)
                min = number;
            else if (number > max)
                max = number;

            iter++;
        }
        scanner.close();

        if (iter > 0) {
            System.out.println("\nMin Number: " + min);
            System.out.println("Max Number: " + max);
        }
    }
}
