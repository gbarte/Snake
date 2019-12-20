package gamelogic;

public class Score {
    private int score;

    public Score() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void add(int points) {
        score += points;
    }

    /**
     * Subtracts points from the score if you have more points
     * than the ones that will be subtracted.
     * @param points to subtract.
     */
    public void subtract(int points) {
        if (score > points) {
            score -= points;
        }
    }

    public void resetScore() {
        score = 0;
    }
}
