package objects.base.factories;

import gamelogic.Coordinate;
import objects.base.Apple;
import objects.base.Food;
import objects.base.GoldenApple;

import java.util.Random;

public class SimpleFoodFactory extends FoodFactory {

    @Override
    public Food createFood() {
        Random random = new Random();
        double num = random.nextDouble();

        Coordinate coordinate = randomCoordinates();

        if(num <= GoldenApple.rarity) {
            return new GoldenApple(coordinate);
        }
        return new Apple(coordinate);
    }

}
