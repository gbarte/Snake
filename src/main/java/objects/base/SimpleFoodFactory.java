package objects.base;

import gamelogic.Coordinate;

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
