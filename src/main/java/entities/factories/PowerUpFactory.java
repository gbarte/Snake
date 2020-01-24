package entities.factories;

import entities.DoubleScorePowerUp;
import entities.Food;
import entities.MushroomPowerUp;

import java.util.List;
import java.util.Random;
import models.Coordinate;


/**
 * PowerUpFactory to create either power ups or apple objects.
 * Takes part of the factory method pattern.
 * Main purpose of it is to provide powerUps/apples with random coordinates.
 */
public class PowerUpFactory extends FoodFactory {

    @Override
    public Food createFood() {
        Random random = new Random();
        double num = random.nextDouble();

        if (num <= MushroomPowerUp.rarity) {
            return new MushroomPowerUp(randomCoordinates());
        }
        if (num <= DoubleScorePowerUp.rarity + MushroomPowerUp.rarity) {
            return new DoubleScorePowerUp(randomCoordinates());
        }
        return new AppleFactory().createFood();
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

        if (num <= MushroomPowerUp.rarity) {
            return new MushroomPowerUp(randomCoordinates(obstacles));
        }
        if (num <= DoubleScorePowerUp.rarity + MushroomPowerUp.rarity) {
            return new DoubleScorePowerUp(randomCoordinates(obstacles));
        }
        return new AppleFactory().createFood();
    }
}
