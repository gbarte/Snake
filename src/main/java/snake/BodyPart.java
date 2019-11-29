package snake;

public class BodyPart {
    private float x, y;

    public BodyPart(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    /**
     * Moves body part to new coordinates
     * @param x - New x coordinate of position of bodypart.
     * @param y - New y coordinate of position of bodypart.
     */
    public void updateBodyPartPos(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
