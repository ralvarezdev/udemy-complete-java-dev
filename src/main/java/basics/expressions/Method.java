package basics.expressions;

public class Method {
    public static void main(String[] args) {
        Method.challenge();
    }

    public static void challenge() {
        int[] testHighScore = new int[]{1500, 1000, 500, 100, 25};
        String[] testName = new String[]{"Tim", "Bob", "Percy", "Gilbert", "James"};
        int pos, score;

        System.out.println("Method Challenge");

        for (int i = 0; i < testHighScore.length; i++) {
            score = testHighScore[i];
            pos = calculateHighScorePosition(score);

            displayHighScorePosition(testName[i], pos);
        }
    }

    private static void displayHighScorePosition(String name, int pos) {
        if (pos <= 0)
            return;

        System.out.println(name + " managed to get into position " + pos + " on the high score list");
    }

    private static int calculateHighScorePosition(int score) {
        if (score < 100)
            return 4;
        if (score < 500)
            return 3;
        if (score < 1000)
            return 2;

        return 1;
    }
}
