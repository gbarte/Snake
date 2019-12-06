package snake;

import gamelogic.Coordinates;

public class BodyPart {
    private Coordinates coordinates;

    public BodyPart(int coordinateX, int coordinateY) {
        this.coordinates = new Coordinates(coordinateX, coordinateY);
    }

    /**
     * Moves body part to new coordinates.
     * @param x - New x coordinate of position of bodypart.
     * @param y - New y coordinate of position of bodypart.
     */
    void updateBodyPartPos(int x, int y) {
        this.coordinates.setCoordinateX(x);
        this.coordinates.setCoordinateY(y);
    }

    void updateBodyPartPos(Coordinates coordinates) {
        int x = coordinates.getCoordinateX();
        int y = coordinates.getCoordinateY();
        this.coordinates = new Coordinates(x, y);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

}
