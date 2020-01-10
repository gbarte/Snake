package gamelogic;

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
        Coordinate toReturn = new Coordinate(minX, minY);

        int x = r.nextInt(getMaxX() - getMinY()) + getMinX();
        int y = r.nextInt(getMaxY() - getMinY()) + getMinY();
        toReturn.setCoordinateX(x);
        toReturn.setCoordinateY(y);

        /*
        for (int i = 0; i < 20; i++) {
            int x = r.nextInt(getMaxX() - getMinY()) + getMinX();
            int y = r.nextInt(getMaxY() - getMinY()) + getMinY();

            TileType tileType =
                    map.getTileTypeByCoordinate(map.getLayers(), x, y);
            if(!tileType.isCollidable()) {
                toReturn.setCoordinateX(x);
                toReturn.setCoordinateY(y);
                break;
            }
        }
         */


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
}
