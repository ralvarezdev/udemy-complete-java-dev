package basics.firststeps;

public class Operator {
    public static void main(String[] args) {
        Operator.challenge();
    }

    public static void challenge() {
        double var1 = 200.0;
        double var2 = 80.0;
        double r1 = 100.0 * (var1 + var2);
        double remainder = r1 % 40.0;

        boolean isMultiple = true;

        System.out.println("Operator Challenge");
        System.out.println("Remainder: " + remainder);

        if (!isMultiple)
            System.out.println("Got Some Remainder");
    }
}
