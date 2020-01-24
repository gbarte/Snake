package entities.factories;

import entities.Food;
import entities.snake.SnakeBody;
import java.util.List;
import java.util.Random;
import models.Coordinate;
import models.Randomizer;
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
     * Use this to create food that doesn't spawn on top of the obstacles in the game.
     * @param obstacles List of all the coordinates where food can't spawn (excludes border).
     * @return Food object that isn't on top of any obstacles in the game.
     */
    public abstract Food createFood(List<Coordinate> obstacles);

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

    /**
     * This method is used to generate a random coordinate,
     * which also takes the obstacles into consideration.
     * @param obstacles List of obstacles coordinates.
     * @return A random coordinate.
     */
    public Coordinate randomCoordinates(List<Coordinate> obstacles) {
        Randomizer randomizer = new Randomizer(Sizes.DEFAULT_AMOUNT_BORDER_TILES,
                Sizes.DEFAULT_AMOUNT_BORDER_TILES,
                Sizes.DEFAULT_MINIMUM_MAP_TILES - Sizes.DEFAULT_AMOUNT_BORDER_TILES,
                Sizes.DEFAULT_MINIMUM_MAP_TILES - Sizes.DEFAULT_AMOUNT_BORDER_TILES, obstacles);
        return randomizer.getRandomCoordinate();
    }

}
