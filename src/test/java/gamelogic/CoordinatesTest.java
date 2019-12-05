package gamelogic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class CoordinatesTest {

    @Test
    void getXtest() {
        Coordinates coordinates = new Coordinates(20, 25);
        assertEquals(20, coordinates.getCoordinateX());
    }

    @Test
    void setXtest() {
        Coordinates coordinates = new Coordinates(20, 25);
        coordinates.setCoordinateX(30);
        assertEquals(30, coordinates.getCoordinateX());
    }

    @Test
    void getYtest() {
        Coordinates coordinates = new Coordinates(20, 25);
        assertEquals(25, coordinates.getCoordinateY());
    }

    @Test
    void setYtest() {
        Coordinates coordinates = new Coordinates(20, 25);
        coordinates.setCoordinateX(300);
        assertEquals(300, coordinates.getCoordinateX());
    }

    @Test
    void testEquals() {
        Coordinates coordinates = new Coordinates(20, 25);
        Coordinates coordinates2 = new Coordinates(20, 25);
        assertEquals(coordinates, coordinates2);
    }

    @Test
    void testEquals2() {
        Coordinates coordinates = new Coordinates(20, 25);
        Coordinates coordinates2 = new Coordinates(25, 25);
        assertFalse(coordinates.equals(coordinates2));
    }

    @Test
    void testEquals3() {
        Coordinates coordinates = new Coordinates(20, 25);
        Coordinates coordinates2 = coordinates;
        assertEquals(coordinates, coordinates2);
    }

    @Test
    void testToString() {
        Coordinates coordinates = new Coordinates(20, 25);
        assertEquals("Coordinates are: X = 20, Y = 25", coordinates.toString());
    }
}