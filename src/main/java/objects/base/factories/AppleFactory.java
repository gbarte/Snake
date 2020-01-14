package objects.base.factories;

import gamelogic.Coordinate;
import java.util.Random;
import objects.base.Apple;
import objects.base.Food;
import objects.base.GoldenApple;

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
