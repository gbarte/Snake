package models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomizerTest {

    private transient Randomizer randomizer;
    private transient List<Coordinate> all;

    @BeforeEach
    void setUp() {
        this.all = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        this.all.clear();
    }

    @Test
    void toReturnMinXAndMinY() {
        Coordinate two = new Coordinate(2, 2);
        all.add(two);
        this.randomizer = new Randomizer(2, 2, 3, 3, all);
        assertEquals(two, randomizer.getRandomCoordinate());
    }

    @Test
    void fullList() {
        Coordinate twoTwo = new Coordinate(2, 2);
        Coordinate twoThree = new Coordinate(2, 3);
        Coordinate twoFour = new Coordinate(2, 4);
        Coordinate twoFive = new Coordinate(2, 5);
        Coordinate threeTwo = new Coordinate(3, 2);
        Coordinate threeThree = new Coordinate(3, 3);
        Coordinate threeFour = new Coordinate(3, 4);
        Coordinate threeFive = new Coordinate(3, 5);
        all.add(twoTwo);
        all.add(twoThree);
        all.add(twoFour);
        all.add(twoFive);
        all.add(threeTwo);
        all.add(threeThree);
        all.add(threeFour);
        all.add(threeFive);

        this.randomizer = new Randomizer(2, 2, (5 + 1), (3 + 1), all);
        assertTrue(randomizer.getRandomCoordinate().getCoordinateX() <= 5);
        assertTrue(randomizer.getRandomCoordinate().getCoordinateX() >= 2);
        assertTrue(randomizer.getRandomCoordinate().getCoordinateY() >= 2);
        assertTrue(randomizer.getRandomCoordinate().getCoordinateY() <= 3);
    }

    @Test
    void almostFullList() {
        Coordinate twoTwo = new Coordinate(2, 2);
        Coordinate twoThree = new Coordinate(2, 3);
        Coordinate twoFour = new Coordinate(2, 4);
        Coordinate twoFive = new Coordinate(2, 5);
        all.add(twoTwo);
        all.add(twoThree);
        all.add(twoFour);
        all.add(twoFive);

        this.randomizer = new Randomizer(2, 2, (5 + 1), (3 + 1), all);
        assertTrue(randomizer.getRandomCoordinate().getCoordinateX() <= 5);
        assertTrue(randomizer.getRandomCoordinate().getCoordinateX() >= 2);
        assertTrue(randomizer.getRandomCoordinate().getCoordinateY() <= 3);
    }
}