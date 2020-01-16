package entities.factories;

import entities.Food;
import entities.snake.SnakeBody;
import java.util.Random;
import models.Coordinate;
import states.SnakeGame;
import utils.Sizes;


/**
 * Factory creates Food elements on the screen.
 * Takes part of the factory method pattern.
 *
 */
public abstract class FoodFactory {

    public abstract Food createFood();

    /**
     * Randomly chooses an x and y coordinate to be used as
     * the foods coordinates.
     * @return random Coordinate on the map in game.
     */
    public Coordinate randomCoordinates() {
        Random r = new Random();
        int minX = Sizes.DEFAULT_AMOUNT_BORDER_TILES;
        int minY = Sizes.DEFAULT_AMOUNT_BORDER_TILES;
        int maxX = Sizes.DEFAULT_MINIMUM_MAP_TILES - Sizes.DEFAULT_AMOUNT_BORDER_TILES;
        int maxY = Sizes.DEFAULT_MINIMUM_MAP_TILES - Sizes.DEFAULT_AMOUNT_BORDER_TILES;


        int x = r.nextInt(maxX - minX) + minX;
        int y = r.nextInt(maxY - minY) + minY;

        return new Coordinate(x, y);
    }

}
