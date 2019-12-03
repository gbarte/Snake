package objects.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    void getXtest() {
        Coordinates coordinates = new Coordinates(20, 25);
        assertEquals(20, coordinates.getX());
    }

    @Test
    void setXtest() {
        Coordinates coordinates = new Coordinates(20, 25);
        coordinates.setX(30);
        assertEquals(30, coordinates.getX());
    }

    @Test
    void getYtest() {
        Coordinates coordinates = new Coordinates(20, 25);
        assertEquals(25, coordinates.getY());
    }

    @Test
    void setYtest() {
        Coordinates coordinates = new Coordinates(20, 25);
        coordinates.setX(300);
        assertEquals(300, coordinates.getX());
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
    void testToString() {
        Coordinates coordinates = new Coordinates(20, 25);
        assertEquals("Coordinates are: X = 20, Y = 25", coordinates.toString());
    }
}