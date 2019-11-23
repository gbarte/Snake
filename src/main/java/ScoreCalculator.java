public class ScoreCalculator {
    private int score;

    public ScoreCalculator() {
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

    public void subtract(int points) {
        if(score > points) {
            score -= points;
        }
    }

    public void resetScore() {
        score = 0;
    }
}
