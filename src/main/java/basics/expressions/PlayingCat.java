package basics.expressions;

// Exercise 12
public class PlayingCat {
    public static boolean isCatPlaying(boolean summer, int temperature) {
        if (temperature < 25)
            return false;

        if (summer)
            return temperature <= 45;
        return temperature <= 35;
    }
}
