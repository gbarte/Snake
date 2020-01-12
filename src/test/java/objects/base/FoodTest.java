package objects.base;

import com.badlogic.gdx.graphics.Texture;
import gamelogic.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class FoodTest {

    private Food food;
    private Coordinate coordinate;
    private Texture texture;

    protected abstract Food getFood();

    @BeforeEach
    void setUp() {
        coordinate = new Coordinate(10, 15);
        texture = Mockito.mock(Texture.class);

        food = getFood();
        food.setCoordinate(coordinate);
        food.setTexture(texture);
    }


    @Test
    void getCoordinatesTest() {
        assertEquals(10, food.getCoordinate().getCoordinateX());
        assertEquals(15, food.getCoordinate().getCoordinateY());
    }

    @Test
    void setCoordinatesTest() {
        Coordinate coordinate2 = new Coordinate(20, 25);
        food.setCoordinate(coordinate2);
        assertEquals(coordinate2, food.getCoordinate());
    }

    @Test
    void getTextureTest() {
        assertEquals(texture, food.getTexture());
    }

    @Test
    void setTextureTest() {
        Texture texture2 = Mockito.mock(Texture.class);
        food.setTexture(texture2);
        assertEquals(texture2, food.getTexture());
    }

}
