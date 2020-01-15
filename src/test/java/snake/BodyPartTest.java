package snake;

import entities.snake.BodyPart;
import models.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Sizes;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BodyPartTest {

    private transient BodyPart bodyPart;

    @BeforeEach
    void setUp() {
        bodyPart = new BodyPart(Sizes.DEFAULT_MINIMUM_MAP_TILES / 2,
                Sizes.DEFAULT_MINIMUM_MAP_TILES / 2);
    }

    @Test
    void getCoordinateTest() {
        Coordinate temp = new Coordinate(
                Sizes.DEFAULT_MINIMUM_MAP_TILES / 3,
                Sizes.DEFAULT_MINIMUM_MAP_TILES / 3);
        bodyPart.setCoordinate(temp);
        assertEquals(temp, bodyPart.getCoordinate());
    }

    @Test
    void setCoordinateTest() {
        Coordinate temp = new Coordinate(
                Sizes.DEFAULT_MINIMUM_MAP_TILES / 3,
                Sizes.DEFAULT_MINIMUM_MAP_TILES / 3);
        Coordinate old = bodyPart.getCoordinate();
        bodyPart.setCoordinate(temp);
        assertEquals(temp, bodyPart.getCoordinate());
        bodyPart.setCoordinate(old);
        assertEquals(old, bodyPart.getCoordinate());
    }

    @Test
    void updateBodyPartPosUsingXY() {
        bodyPart.updateBodyPartPos(
                Sizes.DEFAULT_MINIMUM_MAP_TILES / 5,
                Sizes.DEFAULT_MINIMUM_MAP_TILES / 5);
        assertEquals(bodyPart.getCoordinate().getCoordinateX(), Sizes.DEFAULT_MINIMUM_MAP_TILES / 5);
        assertEquals(bodyPart.getCoordinate().getCoordinateY(), Sizes.DEFAULT_MINIMUM_MAP_TILES / 5);
    }

    @Test
    void updateBodyPartPosUsingCoordinate() {
        Coordinate temp = new Coordinate(
                Sizes.DEFAULT_MINIMUM_MAP_TILES / 5,
                Sizes.DEFAULT_MINIMUM_MAP_TILES / 5);
        bodyPart.updateBodyPartPos(temp);
        assertEquals(bodyPart.getCoordinate(), temp);
    }
}