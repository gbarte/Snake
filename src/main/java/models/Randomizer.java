package models;

import java.util.List;
import java.util.Random;

public class Randomizer {

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private List<Coordinate> all;

    /**
     * Constructor to create a randomizer.
     *
     * @param minX The smallest x-value for where the item can be placed.
     * @param minY The smallest y-value for where the item can be placed.
     * @param maxX The largest x-value for where the item can be placed.
     * @param maxY The largest y-value for where the item can be placed.
     */
    public Randomizer(int minX, int minY, int maxX, int maxY, List<Coordinate> all) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.all = all;
    }

    /**
     * Method that outputs a random coordinate that also takes obstacles into account.
     *
     * @return
     */
    public Coordinate getRandomCoordinate() {
        Random r = new Random();
        Coordinate toReturn = new Coordinate(getMinX(), getMinY());

        int x = r.nextInt(getMaxX() - getMinY()) + getMinX();
        int y = r.nextInt(getMaxY() - getMinY()) + getMinY();
        toReturn.setCoordinateX(x);
        toReturn.setCoordinateY(y);

        int maxIter = (int) (1.5 * getAll().size());
        while (getAll().contains(toReturn) && maxIter > 0) {
            toReturn.setCoordinateX(r.nextInt(getMaxX() - getMinY()) + getMinX());
            toReturn.setCoordinateY(r.nextInt(getMaxY() - getMinY()) + getMinY());
            maxIter--;
        }


        return toReturn;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public List<Coordinate> getAll() {
        return all;
    }

    public void setAll(List<Coordinate> all) {
        this.all = all;
    }
}
