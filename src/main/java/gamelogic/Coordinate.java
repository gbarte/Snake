package gamelogic;

import java.util.Objects;

public class Coordinate {
    private int coordinateX;
    private int coordinateY;

    public Coordinate(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void addToX(int x) {
        this.coordinateX += x;
    }

    public void addToY(int y) {
        this.coordinateY += y;
    }

    public void subtractFromX(int x) {
        this.coordinateX -= x;
    }

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
