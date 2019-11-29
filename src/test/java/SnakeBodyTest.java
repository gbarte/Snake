import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SnakeBodyTest {
    private transient SnakeBody snakeBody;

    @BeforeEach
    public void setUp() {
        snakeBody = new SnakeBody(800, 800);
    }

    @Test
    void getHeadXTest() {
        assertEquals(snakeBody.getHeadX(),400);
    }

    @Test
    void setHeadXTest() {
        snakeBody.setHeadX(5);
        assertEquals(5 , snakeBody.getHeadX());
    }

    @Test
    void getHeadYTest() {
        assertEquals(snakeBody.getHeadY(), 400);
    }

    @Test
    void setHeadYTest() {
        snakeBody.setHeadY(5);
        assertEquals(5, snakeBody.getHeadY());
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
        assertEquals(snakeBody.getCurrDir(), SnakeBody.Direction.UP);
    }

    @Test
    void setCurrDirTest() {
        snakeBody.setCurrDir(SnakeBody.Direction.DOWN);
        assertEquals(snakeBody.getCurrDir(), SnakeBody.Direction.DOWN);
    }

    @Test
    void getEdgeSizeTest() {
       assertEquals(snakeBody.getCELL_SIZE(), 50);
    }

    @Test
    void moveSnakeTest() {
        assertEquals(snakeBody.getCurrDir(), SnakeBody.Direction.UP);
        snakeBody.moveSnake(SnakeBody.Direction.RIGHT);
        assertEquals(snakeBody.getHeadX(), 450);
        assertEquals(snakeBody.getHeadY(), 400);

    }

    @Test
    void updateBodyPartsPositionTest() {
        LinkedList<BodyPart> ll = new LinkedList<>();
        ll.add(new BodyPart(350, 400));
        ll.add(new BodyPart(300, 400));

        snakeBody.setBodyParts(ll);
        snakeBody.moveSnake(SnakeBody.Direction.UP);
        snakeBody.updateBodyPartsPosition(snakeBody.getHeadX(), snakeBody.getHeadY());

        assertEquals(snakeBody.getBodyParts().get(1).getY(), snakeBody.getBodyParts().get(0).getY() - snakeBody.CELL_SIZE);
    }

//    @Test
//    void renderSnake() {
//        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
//        snakeBody.renderSnake(shapeRenderer);
//
//        Mockito.verify(shapeRenderer).setColor(new Color(Color.GREEN));
//        Mockito.verify(shapeRenderer).end();
//    }
}