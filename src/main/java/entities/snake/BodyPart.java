package entities.snake;

import models.Coordinate;

public class BodyPart {

    private Coordinate coordinate;

    public BodyPart(int coordinateX, int coordinateY) {
        this.coordinate = new Coordinate(coordinateX, coordinateY);
    }

    /**
     * Moves body part to new coordinate.
     * @param x - New x coordinate of position of bodypart.
     * @param y - New y coordinate of position of bodypart.
     */
    public void updateBodyPartPos(int x, int y) {
        this.coordinate.setCoordinateX(x);
        this.coordinate.setCoordinateY(y);
    }

    /**
     * Update body part's position to a new coordinate.
     * @param coordinate Coordinate object with the new coordinates.
     */
    public void updateBodyPartPos(Coordinate coordinate) {
        int x = coordinate.getCoordinateX();
        int y = coordinate.getCoordinateY();
        this.coordinate = new Coordinate(x, y);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

}
