package gamelogic;

/**
 * The class that handles score recording and operations.
 */
public class Score {
    private int value;

    public Score() {
        value = 0;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void add(int points) {
        value += points;
    }

    /**
     * Subtracts points from the value if you have more points
     * than the ones that will be subtracted.
     * @param points to subtract.
     */
    public void subtract(int points) {
        if (value > points) {
            value -= points;
        }
    }

    public void resetScore() {
        value = 0;
    }
}
