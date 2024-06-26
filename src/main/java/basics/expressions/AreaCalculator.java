package basics.expressions;

// Exercise 9
public class AreaCalculator {
    public static double area(double radius) {
        if (radius < 0)
            return -1.0;

        final double PI = 3.14159;

        return radius * radius * PI;
    }

    public static double area(double x, double y) {
        if (x < 0 || y < 0)
            return -1.0;

        return x * y;
    }

}
