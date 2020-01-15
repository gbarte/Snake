package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.graphics.Texture;
import models.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import entities.snake.SnakeBody;


class AppleTest {
    private transient Texture texture;

    @BeforeEach
    void setUp() {
        texture = Mockito.mock(Texture.class);
    }

    @Test
    void getCoordinatesTest() {
        Apple apple = new Apple(10, 15, 100, texture);
        assertEquals(10 * SnakeBody.CELL_SIZE, apple.getCoordinate().getCoordinateX());
        assertEquals(15 * SnakeBody.CELL_SIZE, apple.getCoordinate().getCoordinateY());
    }

    @Test
    void setCoordinatesTest() {
        Apple apple = new Apple(10, 15, 100, texture);
        Coordinate coordinate = new Coordinate(20, 25);
        apple.setCoordinate(coordinate);
        assertEquals(coordinate, apple.getCoordinate());
    }

    @Test
    void getScoreTest() {
        Apple apple = new Apple(10, 15, 100, texture);
        assertEquals(100, apple.getScore());
    }

    @Test
    void setScoreTest() {
        Apple apple = new Apple(10, 15, 100, texture);
        apple.setScore(20);
        assertEquals(20, apple.getScore());
    }

    @Test
    void getTextureTest() {
        Apple apple = new Apple(10, 15, 100, texture);
        assertEquals(texture, apple.getTexture());
    }

    @Test
    void setTextureTest() {
        Apple apple = new Apple(10, 15, 100, texture);
        Texture texture2 = Mockito.mock(Texture.class);
        apple.setTexture(texture2);
        assertEquals(texture2, apple.getTexture());
    }

}