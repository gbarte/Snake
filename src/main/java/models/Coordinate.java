package models;

import java.util.Objects;

/**
 * Coordinate class.
 */
public class Coordinate {
    private int coordinateX;
    private int coordinateY;

    public Coordinate(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    /**
     * Get the X coordinate.
     * @return
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * Set the X coordinate.
     * @param coordinateX - new value.
     */
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    /**
     * Get the Y coordinate.
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     * Set the Y coordinate.
     * @param coordinateY - new value.
     */
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    /**
     * Add x to the current x coordinate.
     * @param x - delta value.
     */
    public void addToX(int x) {
        this.coordinateX += x;
    }

    /**
     * Add y to the current y coordinate.
     * @param y - delta value.
     */
    public void addToY(int y) {
        this.coordinateY += y;
    }

    /**
     * Subtract x from the current x coordinate.
     * @param x - delta value.
     */
    public void subtractFromX(int x) {
        this.coordinateX -= x;
    }

    /**
     * Subtract y from the current y coordinate.
     * @param y - delta value.
     */
    public void subtractFromY(int y) {
        this.coordinateY -= y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        Coordinate that = (Coordinate) obj;
        if (this.coordinateX == that.coordinateX && this.coordinateY == that.coordinateY) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, coordinateY);
    }

    @Override
    public String toString() {
        return "Coordinate are: X = " + coordinateX + ", " + "Y = " + coordinateY;
    }
}
