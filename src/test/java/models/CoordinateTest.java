package models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class CoordinateTest {

    @Test
    void getXtest() {
        Coordinate coordinate = new Coordinate(20, 25);
        assertEquals(20, coordinate.getCoordinateX());
    }

    @Test
    void setXtest() {
        Coordinate coordinate = new Coordinate(20, 25);
        coordinate.setCoordinateX(30);
        assertEquals(30, coordinate.getCoordinateX());
    }

    @Test
    void getYtest() {
        Coordinate coordinate = new Coordinate(20, 25);
        assertEquals(25, coordinate.getCoordinateY());
    }

    @Test
    void setYtest() {
        Coordinate coordinate = new Coordinate(20, 25);
        coordinate.setCoordinateX(300);
        assertEquals(300, coordinate.getCoordinateX());
    }

    @Test
    void testEqualsTrue() {
        Coordinate coordinate = new Coordinate(20, 25);
        Coordinate coordinate2 = new Coordinate(20, 25);
        assertEquals(coordinate, coordinate2);
    }

    @Test
    void testEqualsFalseX() {
        Coordinate coordinate = new Coordinate(20, 25);
        Coordinate coordinate2 = new Coordinate(25, 25);
        assertFalse(coordinate.equals(coordinate2));
    }

    @Test
    void testEqualsReferenceTrue() {
        Coordinate coordinate = new Coordinate(20, 25);
        Coordinate coordinate2 = coordinate;
        assertEquals(coordinate, coordinate2);
    }

    @Test
    void testEqualsFalseY() {
        Coordinate coordinate = new Coordinate(20, 25);
        Coordinate coordinate2 = new Coordinate(20, 35);
        assertFalse(coordinate.equals(coordinate2));
    }

    @Test
    void testToString() {
        Coordinate coordinate = new Coordinate(20, 25);
        assertEquals("Coordinate are: X = 20, Y = 25", coordinate.toString());
    }
}