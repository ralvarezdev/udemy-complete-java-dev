package basics.expressions;

public class OverloadedMethod {
    public static void main(String[] args) {
        OverloadedMethod.challenge();
    }

    public static void challenge() {
        int foot, inches;
        double centimeters;

        System.out.println("Overloaded Method Challenge");

        for (int i = 0; i < 5; i++) {
            centimeters = convertToCentimeters(i);
            System.out.println(i + " inches = " + centimeters + " cm");
        }

        foot = 5;
        inches = 8;
        centimeters = convertToCentimeters(foot, inches);
        System.out.println(foot + " foot and " + inches + " inches = " + centimeters + " cm");
    }

    private static double convertToCentimeters(int inches) {
        final double INCH_TO_CM = 2.54;

        return inches * INCH_TO_CM;
    }

    // Overloaded Method
    private static double convertToCentimeters(int foot, int inches) {
        final int FEET_TO_INCH = 12;

        return convertToCentimeters(foot * FEET_TO_INCH + inches);
    }
}
