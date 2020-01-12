package objects.base.factories;

import game.SnakeGame;
import gamelogic.Coordinate;
import java.util.Random;
import objects.base.Food;
import snake.SnakeBody;


public abstract class FoodFactory {

    public abstract Food createFood();

    /**
     * Randomly chooses an x and y coordinate to be used as
     * the foods coordinates.
     * @return random Coordinate on the map in game.
     */
    public Coordinate randomCoordinates() {
        Random r = new Random();
        int minX = 0;
        int minY = 0;
        int maxX = SnakeGame.WIDTH / SnakeBody.CELL_SIZE;
        int maxY = SnakeGame.HEIGHT / SnakeBody.CELL_SIZE;

        int x = r.nextInt(maxX - minX) + minX;
        int y = r.nextInt(maxY - minY) + minY;

        int coordinateX = x * SnakeBody.CELL_SIZE;
        int coordinateY = y * SnakeBody.CELL_SIZE;

        return new Coordinate(coordinateX, coordinateY);
    }

}
