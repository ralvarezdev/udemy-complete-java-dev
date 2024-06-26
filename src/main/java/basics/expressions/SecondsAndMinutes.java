package basics.expressions;

public class SecondsAndMinutes {
    public static void main(String[] args) {
        SecondsAndMinutes.challenge();
    }

    public static void challenge() {
        System.out.println("Seconds and Minutes Challenge");

        String duration = getDurationString(8432);
        String overloadedDuration = getDurationString(72, 54);

        System.out.println(duration);
        System.out.println(overloadedDuration);
    }

    private static String getDurationString(int seconds) {
        if (seconds < 0)
            return "Invalid Value";

        final int MIN_TO_SEC = 60;
        final int HOUR_TO_MIN = 60;

        int remainingSeconds = seconds % MIN_TO_SEC;
        int minutes = seconds / MIN_TO_SEC;

        int remainingMinutes = minutes % HOUR_TO_MIN;
        int hours = minutes / HOUR_TO_MIN;

        return hours + "h " + remainingMinutes + "m " + remainingSeconds + "s";
    }

    // Overloaded Method
    private static String getDurationString(int minutes, int seconds) {
        if (minutes < 0 || seconds < 0 || seconds > 59)
            return "Invalid Value";

        final int MIN_TO_SEC = 60;

        return getDurationString(minutes * MIN_TO_SEC + seconds);
    }
}
