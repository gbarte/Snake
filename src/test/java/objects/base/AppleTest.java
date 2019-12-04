package objects.base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.graphics.Texture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import snake.SnakeBody;


class AppleTest {
    Texture texture;

    @BeforeEach
    void setUp() {
        texture = Mockito.mock(Texture.class);
    }

    @Test
    void getCoordinates() {
        Apple apple = new Apple(10, 15, 100, texture);
        assertEquals(10 * SnakeBody.CELL_SIZE, apple.getCoordinates().getCoordinateX());
        assertEquals(15 * SnakeBody.CELL_SIZE, apple.getCoordinates().getCoordinateY());
    }

    @Test
    void setCoordinates() {
        Apple apple = new Apple(10, 15, 100, texture);
        Coordinates coordinates = new Coordinates(20, 25);
        apple.setCoordinates(coordinates);
        assertEquals(coordinates, apple.getCoordinates());
    }

    @Test
    void getScore() {
        Apple apple = new Apple(10, 15, 100, texture);
        assertEquals(100, apple.getScore());
    }

    @Test
    void setScore() {
        Apple apple = new Apple(10, 15, 100, texture);
        apple.setScore(20);
        assertEquals(20, apple.getScore());
    }

    @Test
    void getTexture() {
        Apple apple = new Apple(10, 15, 100, texture);
        assertEquals(texture, apple.getTexture());
    }

    @Test
    void setTexture() {
        Apple apple = new Apple(10, 15, 100, texture);
        Texture texture2 = Mockito.mock(Texture.class);
        apple.setTexture(texture2);
        assertEquals(texture2, apple.getTexture());
    }

}