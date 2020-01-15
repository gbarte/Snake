package entities.factories;

import entities.Apple;
import entities.Food;
import entities.GoldenApple;
import java.util.Random;
import models.Coordinate;

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

}
