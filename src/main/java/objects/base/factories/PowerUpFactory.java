package objects.base.factories;

import java.util.Random;
import objects.base.DoubleScorePowerUp;
import objects.base.Food;
import objects.base.MushroomPowerUp;



public class PowerUpFactory extends FoodFactory {

    @Override
    public Food createFood() {
        Random random = new Random();
        double num = random.nextDouble();

        if (num <= MushroomPowerUp.rarity) {
            return new MushroomPowerUp(randomCoordinates());
        } else if (num <= DoubleScorePowerUp.rarity + MushroomPowerUp.rarity) {
            return new DoubleScorePowerUp(randomCoordinates());
        }
        return new AppleFactory().createFood();
    }
}
