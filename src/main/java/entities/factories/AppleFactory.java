package entities.factories;

import entities.Apple;
import entities.Food;
import entities.GoldenApple;
import java.util.List;
import java.util.Random;
import models.Coordinate;
import models.Randomizer;
import utils.Sizes;

/**
 * Apple Factory to create Apple/Golden Apple objects.
 * Takes part of the factory method pattern.
 * Main purpose of it is to provide apples with random coordinates.
 *
 */
public class AppleFactory extends FoodFactory {

    @Override
    public Food createFood() {
        Random random = new Random();
        double num = random.nextDouble();

        Coordinate coordinate = randomCoordinates();

        if (num <= GoldenApple.rarity) {
            return new GoldenApple(coordinate);
        }
        return new Apple(coordinate);
    }

    /**
     * Use this to create food that doesn't spawn on top of the obstacles in the game.
     *
     * @param obstacles List of all the coordinates where food can't spawn (excludes border).
     * @return Food object that isn't on top of any obstacles in the game.
     */
    @Override
    public Food createFood(List<Coordinate> obstacles) {
        Random random = new Random();
        double num = random.nextDouble();

        Coordinate coordinate = randomCoordinates(obstacles);

        if (num <= GoldenApple.rarity) {
            return new GoldenApple(coordinate);
        }
        return new Apple(coordinate);
    }

}
