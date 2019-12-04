package objects.base;

import java.util.Objects;

public class Coordinates {
    private int coordinateX;
    private int coordinateY;

    public Coordinates(int coordinateX, int coordinateY) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        Coordinates that = (Coordinates) obj;
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
        return "Coordinates are: X = " + coordinateX + ", " + "Y = " + coordinateY;
    }
}
