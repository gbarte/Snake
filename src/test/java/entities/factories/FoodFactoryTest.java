package entities.factories;

import entities.Food;
import models.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Sizes;

import static org.junit.jupiter.api.Assertions.*;

abstract class FoodFactoryTest {

    FoodFactory foodFactory;

    abstract FoodFactory getFoodFactory();

    @BeforeEach
    void setUp() {
        foodFactory = getFoodFactory();
    }

    @Test
    void createFoodTest() {
        Food food = foodFactory.createFood();
    }

    @Test
    void randomCoordinatesTest() {
        Coordinate coordinate = foodFactory.randomCoordinates();

        assertTrue(coordinate.getCoordinateX() < Sizes.MAX_WIDTH_WINDOW);
        assertTrue(coordinate.getCoordinateY() < Sizes.MAX_HEIGHT_WINDOW);
    }
}