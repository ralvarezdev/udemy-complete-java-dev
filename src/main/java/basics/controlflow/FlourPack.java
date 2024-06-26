package basics.controlflow;

// Exercise 25
public class FlourPack {
    public static boolean canPack(int bigCount, int smallCount, int goal) {
        if (bigCount < 0 || smallCount < 0 || goal < 0)
            return false;

        final int BIG_KG = 5;
        final int SMALL_KG = 1;
        int totalCount = bigCount * BIG_KG + smallCount * SMALL_KG;

        if (totalCount < goal)
            return false;

        if (totalCount == goal)
            return true;

        int goalRemainder = goal;
        int bigCountRemainder = bigCount;

        while (goalRemainder >= BIG_KG && bigCountRemainder > 0) {
            goalRemainder -= BIG_KG;
            bigCountRemainder--;
        }

        return smallCount >= goalRemainder;
    }
}
