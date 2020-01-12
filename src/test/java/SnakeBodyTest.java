import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.LinkedList;

import gamelogic.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import snake.BodyPart;
import snake.SnakeBody;

import static org.junit.jupiter.api.Assertions.*;

class SnakeBodyTest {
    private transient SnakeBody snakeBody;

    @BeforeEach
    public void setUp() {
        snakeBody = new SnakeBody(800, 800);
    }

    @Test
    public void constructorTest() {
        snakeBody = new SnakeBody(800, 800);
        assertEquals(2, snakeBody.getBodyParts().size());
    }

    @Test
    void getBodyPartsTest() {
        LinkedList<BodyPart> ll = new LinkedList<>();
        ll.add(new BodyPart(350, 400));
        ll.add(new BodyPart(300, 400));

        snakeBody.setBodyParts(ll);

        assertIterableEquals(snakeBody.getBodyParts(), ll);
    }

    @Test
    void setBodyPartsTest() {
        LinkedList<BodyPart> ll = new LinkedList<BodyPart>();
        ll.add(new BodyPart(1, 1));
        snakeBody.setBodyParts(ll);
        assertEquals(snakeBody.getBodyParts(), ll);
    }

    @Test
    void getCurrDirTest() {
        assertEquals(SnakeBody.Direction.RIGHT, snakeBody.getCurrDir());
    }

    @Test
    void setCurrDirTest() {
        snakeBody.setCurrDir(SnakeBody.Direction.DOWN);
        assertEquals(snakeBody.getCurrDir(), SnakeBody.Direction.DOWN);
    }

    @Test
    void getHeadCoord() {
        Coordinate c = new Coordinate(10, 10);
        snakeBody.setHeadCoord(c);
        assertEquals(snakeBody.getHeadCoord(), c);
    }

    @Test
    void setHeadCoord() {
        Coordinate c = new Coordinate(10, 10);
        snakeBody.setHeadCoord(c);
        assertEquals(snakeBody.getHeadCoord(), c);
    }

    @Test
    void growSnakeTestOne() {
        LinkedList<BodyPart> ll = snakeBody.getBodyParts();

        //test for growing first bodyPart
        ll.add(new BodyPart(450, 400));
        assertEquals(ll.get(0), snakeBody.getBodyParts().get(0));

        // test for growing by one cell
        int length = ll.size();
        ll.add(new BodyPart(450 - (length * SnakeBody.CELL_SIZE), 400));
        snakeBody.growSnake();
        assertEquals(ll, snakeBody.getBodyParts());
    }

    @Test
    void growSnakeTestMultiple() {
        LinkedList<BodyPart> ll = snakeBody.getBodyParts();

        //test for growing first bodyPart
        ll.add(new BodyPart(450, 400));
        assertEquals(ll.get(0), snakeBody.getBodyParts().get(0));

        //test for growing by multiple cells
        int oldLength = snakeBody.getBodyParts().size();
        snakeBody.growSnake(2);
        int newLength = snakeBody.getBodyParts().size();
        assertEquals(oldLength + 2, newLength);
    }

    @Test
    void growSnakeTestNegative() {
        LinkedList<BodyPart> ll = snakeBody.getBodyParts();

        //test for growing first bodyPart
        ll.add(new BodyPart(450, 400));
        assertEquals(ll.get(0), snakeBody.getBodyParts().get(0));

        //test for trying to grow by a negative amount.
        int length = snakeBody.getBodyParts().size();
        snakeBody.growSnake(-1);
        assertEquals(length, length);
    }

    @Test
    void moveSnakeTestRight() {
        assertEquals(SnakeBody.Direction.RIGHT, snakeBody.getCurrDir());
        snakeBody.moveSnake(SnakeBody.Direction.RIGHT);
        assertEquals(snakeBody.getHeadCoord().getCoordinateX(), 450);
        assertEquals(snakeBody.getHeadCoord().getCoordinateY(), 400);
    }

    @Test
    void moveSnakeTestLeft() {
        assertEquals(SnakeBody.Direction.RIGHT, snakeBody.getCurrDir());
        snakeBody.moveSnake(SnakeBody.Direction.LEFT);
        assertEquals(snakeBody.getHeadCoord().getCoordinateX(), 350);
        assertEquals(snakeBody.getHeadCoord().getCoordinateY(), 400);
    }

    @Test
    void moveSnakeTestDown() {
        assertEquals(SnakeBody.Direction.RIGHT, snakeBody.getCurrDir());
        snakeBody.moveSnake(SnakeBody.Direction.DOWN);
        assertEquals(snakeBody.getHeadCoord().getCoordinateX(), 400);
        assertEquals(snakeBody.getHeadCoord().getCoordinateY(), 350);
    }

    @Test
    void updateBodyPartsPositionTest() {
        LinkedList<BodyPart> ll = new LinkedList<>();
        ll.add(new BodyPart(350, 400));
        ll.add(new BodyPart(300, 400));

        snakeBody.setBodyParts(ll);
        snakeBody.moveSnake(SnakeBody.Direction.UP);
        snakeBody.updateBodyPartsPosition(snakeBody.getHeadCoord());

        LinkedList<BodyPart> bodyParts = snakeBody.getBodyParts();

        assertEquals(bodyParts.get(1).getCoordinate().getCoordinateY(),
                bodyParts.get(0).getCoordinate().getCoordinateY() - SnakeBody.CELL_SIZE);
    }

    @Test
    void renderSnake() {
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        snakeBody.renderSnake(shapeRenderer);

        Mockito.verify(shapeRenderer).end();
    }

}