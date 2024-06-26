package basics.expressions;

public class IfThenElse {
    public static void main(String[] args) {
        IfThenElse.challenge();
    }

    public static void challenge() {
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;
        int finalScore;

        System.out.println("If-then-else Challenge");

        finalScore = calculateScore(score, levelCompleted, bonus);
        System.out.println("Your final score was " + finalScore);

        score = 10000;
        levelCompleted = 8;
        bonus = 200;

        finalScore = calculateScore(score, levelCompleted, bonus);
        System.out.println("Your final score was " + finalScore);
    }

    private static int calculateScore(int score, int levelCompleted, int bonus) {
        return score + levelCompleted * bonus + 1000;
    }
}
