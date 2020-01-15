//package entities;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertIterableEquals;
//
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import entities.snake.BodyPart;
//import entities.snake.SnakeBody;
//import java.util.LinkedList;
//import models.Coordinate;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import snake.SnakeBody_BASE_39152;
//
//
//class SnakeBody_BASE_39152Test {
//    private transient SnakeBody_BASE_39152 snakeBody;
//
//    @BeforeEach
//    public void setUp() {
//        snakeBody = new SnakeBody_BASE_39152(800, 800);
//    }
//
//    @Test
//    public void constructorTest() {
//        snakeBody = new SnakeBody_BASE_39152(800, 800);
//        assertEquals(2, SnakeBody_BASE_39152.getBodyParts().size());
//    }
//
//    @Test
//    void getBodyPartsTest() {
//        LinkedList<BodyPart> ll = new LinkedList<>();
//        ll.add(new BodyPart(350, 400));
//        ll.add(new BodyPart(300, 400));
//
//        SnakeBody_BASE_39152.setBodyParts(ll);
//
//        assertIterableEquals(SnakeBody_BASE_39152.getBodyParts(), ll);
//    }
//
//    @Test
//    void setBodyPartsTest() {
//        LinkedList<BodyPart> ll = new LinkedList<BodyPart>();
//        ll.add(new BodyPart(1, 1));
//        SnakeBody_BASE_39152.setBodyParts(ll);
//        assertEquals(SnakeBody_BASE_39152.getBodyParts(), ll);
//    }
//
//    @Test
//    void getCurrDirTest() {
//        assertEquals(SnakeBody_BASE_39152.Direction.RIGHT, SnakeBody_BASE_39152.getCurrDir());
//    }
//
//    @Test
//    void setCurrDirTest() {
//        SnakeBody_BASE_39152.setCurrDir(SnakeBody_BASE_39152.Direction.DOWN);
//        assertEquals(SnakeBody_BASE_39152.getCurrDir(), SnakeBody_BASE_39152.Direction.DOWN);
//    }
//
//    @Test
//    void getHeadCoord() {
//        Coordinate c = new Coordinate(10, 10);
//        SnakeBody_BASE_39152.setHeadCoord(c);
//        assertEquals(SnakeBody_BASE_39152.getHeadCoord(), c);
//    }
//
//    @Test
//    void setHeadCoord() {
//        Coordinate c = new Coordinate(10, 10);
//        SnakeBody_BASE_39152.setHeadCoord(c);
//        assertEquals(SnakeBody_BASE_39152.getHeadCoord(), c);
//    }
//
//    @Test
//    void growSnakeTestOne() {
//        LinkedList<BodyPart> ll = SnakeBody_BASE_39152.getBodyParts();
//
//        //test for growing first bodyPart
//        ll.add(new BodyPart(450, 400));
//        assertEquals(ll.get(0), SnakeBody_BASE_39152.getBodyParts().get(0));
//
//        // test for growing by one cell
//        int length = ll.size();
//        ll.add(new BodyPart(450 - (length * SnakeBody_BASE_39152.CELL_SIZE), 400));
//        SnakeBody_BASE_39152.growSnake();
//        assertEquals(ll, SnakeBody_BASE_39152.getBodyParts());
//    }
//
//    @Test
//    void growSnakeTestMultiple() {
//        LinkedList<BodyPart> ll = SnakeBody_BASE_39152.getBodyParts();
//
//        //test for growing first bodyPart
//        ll.add(new BodyPart(450, 400));
//        assertEquals(ll.get(0), SnakeBody_BASE_39152.getBodyParts().get(0));
//
//        //test for growing by multiple cells
//        int oldLength = SnakeBody_BASE_39152.getBodyParts().size();
//        SnakeBody_BASE_39152.growSnake(2);
//        int newLength = SnakeBody_BASE_39152.getBodyParts().size();
//        assertEquals(oldLength + 2, newLength);
//    }
//
//    @Test
//    void growSnakeTestNegative() {
//        LinkedList<BodyPart> ll = SnakeBody_BASE_39152.getBodyParts();
//
//        //test for growing first bodyPart
//        ll.add(new BodyPart(450, 400));
//        assertEquals(ll.get(0), SnakeBody_BASE_39152.getBodyParts().get(0));
//
//        //test for trying to grow by a negative amount.
//        int length = SnakeBody_BASE_39152.getBodyParts().size();
//        SnakeBody_BASE_39152.growSnake(-1);
//        assertEquals(length, length);
//    }
//
//    @Test
//    void moveSnakeTestRight() {
//        assertEquals(SnakeBody_BASE_39152.Direction.RIGHT, SnakeBody_BASE_39152.getCurrDir());
//        SnakeBody_BASE_39152.moveSnake(SnakeBody_BASE_39152.Direction.RIGHT);
//        assertEquals(SnakeBody_BASE_39152.getHeadCoord().getCoordinateX(), 450);
//        assertEquals(SnakeBody_BASE_39152.getHeadCoord().getCoordinateY(), 400);
//    }
//
//    @Test
//    void moveSnakeTestLeft() {
//        assertEquals(SnakeBody_BASE_39152.Direction.RIGHT, SnakeBody_BASE_39152.getCurrDir());
//        SnakeBody_BASE_39152.moveSnake(SnakeBody_BASE_39152.Direction.LEFT);
//        assertEquals(SnakeBody_BASE_39152.getHeadCoord().getCoordinateX(), 350);
//        assertEquals(SnakeBody_BASE_39152.getHeadCoord().getCoordinateY(), 400);
//    }
//
//    @Test
//    void moveSnakeTestDown() {
//        assertEquals(SnakeBody_BASE_39152.Direction.RIGHT, SnakeBody_BASE_39152.getCurrDir());
//        SnakeBody_BASE_39152.moveSnake(SnakeBody_BASE_39152.Direction.DOWN);
//        assertEquals(SnakeBody_BASE_39152.getHeadCoord().getCoordinateX(), 400);
//        assertEquals(SnakeBody_BASE_39152.getHeadCoord().getCoordinateY(), 350);
//    }
//
//    @Test
//    void updateBodyPartsPositionTest() {
//        LinkedList<BodyPart> ll = new LinkedList<>();
//        ll.add(new BodyPart(350, 400));
//        ll.add(new BodyPart(300, 400));
//
//        SnakeBody_BASE_39152.setBodyParts(ll);
//        SnakeBody_BASE_39152.moveSnake(SnakeBody_BASE_39152.Direction.UP);
//        SnakeBody_BASE_39152.updateBodyPartsPosition(SnakeBody_BASE_39152.getHeadCoord());
//
//        LinkedList<BodyPart> bodyParts = SnakeBody_BASE_39152.getBodyParts();
//
//        assertEquals(bodyParts.get(1).getCoordinate().getCoordinateY(),
//                bodyParts.get(0).getCoordinate().getCoordinateY() - SnakeBody_BASE_39152.CELL_SIZE);
//    }
//
//    @Test
//    void renderSnake() {
//        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
//        SnakeBody_BASE_39152.renderSnake(shapeRenderer);
//
//        Mockito.verify(shapeRenderer).end();
//    }
//
//}