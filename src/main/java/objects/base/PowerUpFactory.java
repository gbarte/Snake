package objects.base;

import gamelogic.Coordinate;

import java.util.Random;

public class PowerUpFactory extends FoodFactory {

    @Override
    public Food createFood() {
        Random random = new Random();
        double num = random.nextDouble();

        Food food = new SimpleFoodFactory().createFood();
        Coordinate coordinate = randomCoordinates();
        //Lowest rarity should be checked first
        if(num <= MushroomPowerUp.rarity) {
            food = new MushroomPowerUp(coordinate);
        } else if(num <= DoubleScorePowerUp.rarity) {
            food = new DoubleScorePowerUp(coordinate);
        }
        return food;
    }
}
