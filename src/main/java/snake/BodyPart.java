package snake;

public class BodyPart {
    private float coordinateX;
    private float coordinateY;

    public BodyPart(float coordinateX, float coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public float getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(float coordinateX) {
        this.coordinateX = coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(float coordinateY) {
        this.coordinateY = coordinateY;
    }

    /**
     * Moves body part to new coordinates.
     * @param x - New x coordinate of position of bodypart.
     * @param y - New y coordinate of position of bodypart.
     */
    void updateBodyPartPos(float x, float y) {
        this.coordinateX = x;
        this.coordinateY = y;
    }
}
