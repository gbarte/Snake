package gamelogic;

import gamelogic.Coordinate;
import utils.Sizes;
import utils.TileType;
import world.GameMap;

import java.util.Random;

public class Randomizer {

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    public Randomizer(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public Coordinate getRandomCoordinate() {
        Random r = new Random();

        int x = r.nextInt(getMaxX() - getMinY()) + getMinX();
        int y = r.nextInt(getMaxY() - getMinY()) + getMinY();

        return new Coordinate(x, y);
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
}
