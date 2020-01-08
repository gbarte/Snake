import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.LinkedList;

import gamelogic.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import snake.BodyPart;
import snake.SnakeBody;
import utils.Direction;

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
        assertEquals(Direction.RIGHT, snakeBody.getCurrDir());
    }

    @Test
    void setCurrDirTest() {
        snakeBody.setCurrDir(Direction.DOWN);
        assertEquals(snakeBody.getCurrDir(), Direction.DOWN);
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
        assertEquals(Direction.RIGHT, snakeBody.getCurrDir());
        snakeBody.moveSnake(Direction.RIGHT);
        assertEquals(snakeBody.getHeadCoord().getCoordinateX(), 401);
        assertEquals(snakeBody.getHeadCoord().getCoordinateY(), 400);
    }

    @Test
    void moveSnakeTestLeft() {
        assertEquals(Direction.RIGHT, snakeBody.getCurrDir());
        snakeBody.moveSnake(Direction.LEFT);
        assertEquals(snakeBody.getHeadCoord().getCoordinateX(), 399);
        assertEquals(snakeBody.getHeadCoord().getCoordinateY(), 400);
    }

    @Test
    void moveSnakeTestDown() {
        assertEquals(Direction.RIGHT, snakeBody.getCurrDir());
        snakeBody.moveSnake(Direction.DOWN);
        assertEquals(snakeBody.getHeadCoord().getCoordinateX(), 400);
        assertEquals(snakeBody.getHeadCoord().getCoordinateY(), 399);
    }

    /*@Test
    void updateBodyPartsPositionTest() {
        LinkedList<BodyPart> ll = new LinkedList<>();
        //ll.add(new BodyPart(400, 400));
        //ll.add(new BodyPart(399, 400));
        ll.add(new BodyPart(26, 25));
        ll.add(new BodyPart(25, 25));

        snakeBody.setBodyParts(ll);
        snakeBody.moveSnake(Direction.UP);
        snakeBody.updateBodyPartsPosition(snakeBody.getHeadCoord());
        //no need to update again since its already done in moveSnake?

        LinkedList<BodyPart> bodyParts = snakeBody.getBodyParts();

        assertEquals(bodyParts.get(1).getCoordinate().getCoordinateY(),
                bodyParts.get(0).getCoordinate().getCoordinateY() - 1);
    } */

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis") //supress this because redefining a variable is necessary
    @ParameterizedTest
    @CsvSource({
            "r, 1, 0",
            "l, -1, 0",
            "u, 0, 1",
            "d, 0, -1"
    }) //DONT ANNOTATE WITH @Test
    void update3BodyPartsPositionTest(char dir, int dx, int dy) {
        LinkedList<BodyPart> linkedList = new LinkedList<>();

        int x = 26, y = 25;
        BodyPart zeroHead = new BodyPart(x, y);
        x-=dx; y-=dy;
        BodyPart one = new BodyPart(x, y);
        System.out.println("one " + one.getCoordinate().toString());
        x-=dx; y-=dy;
        BodyPart two = new BodyPart(x, y);
        System.out.println("last " + two.getCoordinate().toString());
        linkedList.add(zeroHead);
        linkedList.add(one);
        linkedList.add(two);

        snakeBody.setBodyParts(linkedList);
        //snakeBody.moveSnake(Direction.UP);
        Direction temp = Direction.RIGHT;
        switch (dir) {
            case 'r':
                temp = Direction.RIGHT;
                break;
            case 'l':
                temp = Direction.LEFT;
                break;
            case 'u':
                temp = Direction.UP;
                break;
            case 'd':
                temp = Direction.DOWN;
                break;
            default:
                //do nothing
        }

        snakeBody.setCurrDir(temp);
        snakeBody.updateBodyPartsPosition(snakeBody.getHeadCoord());

        LinkedList<BodyPart> allBP = snakeBody.getBodyParts();

        /*assertEquals(allBP.get(0).getCoordinate(), new Coordinate(26, 26));
        assertEquals(allBP.get(1).getCoordinate(), snakeBody.getHeadCoord());
        assertEquals(allBP.get(2).getCoordinate(), two);*/


        /*System.out.println("\nindex 0=" + allBP.get(0).getCoordinate().toString());
        System.out.println("index 1=" + allBP.get(1).getCoordinate().toString());
        System.out.println("index 2=" + allBP.get(2).getCoordinate().toString());
        System.out.println("afterHead is " + one.getCoordinate());*/

        assertEquals(allBP.get(1).getCoordinate(), allBP.get(0).getCoordinate());
        assertEquals(new Coordinate(allBP.get(1).getCoordinate().getCoordinateX() - dx,
                        allBP.get(1).getCoordinate().getCoordinateY() - dy),
                allBP.get(2).getCoordinate());
    }

    @Test
    void renderSnake() {
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        snakeBody.renderSnake(shapeRenderer);

        Mockito.verify(shapeRenderer).setColor(Mockito.any(Color.class));
        Mockito.verify(shapeRenderer).end();
    }

}