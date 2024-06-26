package basics.expressions;

// Exercise 1
public class CheckNumber {
    public static void main(String[] args) {
        for (int i = -5; i <= 5; i++) {
            System.out.printf("%-4d is ", i);
            CheckNumber.checkNumber(i);
        }
    }

    public static void checkNumber(int number) {
        if (number > 0)
            System.out.println("positive");
        else if (number < 0)
            System.out.println("negative");
        else
            System.out.println("zero");
    }
}
