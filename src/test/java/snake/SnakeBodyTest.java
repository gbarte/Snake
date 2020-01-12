package snake;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import gamelogic.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import utils.Direction;
import utils.Sizes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

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
    void getHeadCoordTest() {
        Coordinate c = new Coordinate(10, 10);
        snakeBody.setHeadCoord(c);
        assertEquals(snakeBody.getHeadCoord(), c);
    }

    @Test
    void setHeadCoordTest() {
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

    //suppress this because redefining a variable is necessary
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    @ParameterizedTest
    @CsvSource({
            "RIGHT, 1, 0",
            "LEFT, -1, 0",
            "UP, 0, 1",
            "DOWN, 0, -1"
    }) //DONT ANNOTATE WITH @Test
    void update3BodyPartsPositionTest(Direction dir, int dx, int dy) {

        int x = 26;
        int y = 25;
        BodyPart zeroHead = new BodyPart(x, y);
        System.out.println("zero " + zeroHead.getCoordinate().toString());
        x -= dx;
        y -= dy;
        BodyPart one = new BodyPart(x, y);
        System.out.println("one " + one.getCoordinate().toString());
        x -= dx;
        y -= dy;
        BodyPart two = new BodyPart(x, y);
        System.out.println("two " + two.getCoordinate().toString());

        LinkedList<BodyPart> linkedList = new LinkedList<>();
        linkedList.add(zeroHead);
        linkedList.add(one);
        linkedList.add(two);

        snakeBody.setBodyParts(linkedList);
        //snakeBody.moveSnake(Direction.UP);
        Direction temp = dir;

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

    @ParameterizedTest
    @CsvSource({
            "RIGHT, -90f",
            "LEFT, 90f",
            "UP, 0f",
            "DOWN, 180f"
    })
    void renderSnakeTest(Direction dir, float rotation) {
        this.snakeBody =
                new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);
        snakeBody.setCurrDir(dir);
        //SpriteBatch batch = Mockito.mock(SpriteBatch.class);

        /*
        TextureRegion[][] textureRegions =
                TextureRegion.split(new Texture("assets/DefaultBody.png"),
                        Sizes.TILE_PIXELS, Sizes.TILE_PIXELS);
        TextureRegion head = textureRegions[0][0];
        TextureRegion body = textureRegions[0][1];
        */

        /*
        TextureRegion[][] textureRegions = Mockito.mock(TextureRegion[][].class);
        TextureRegion head = Mockito.mock(TextureRegion.class);
        TextureRegion body = Mockito.mock(TextureRegion.class);
        when(textureRegions[0][0]).thenReturn(head);
        when(textureRegions[0][1]).thenReturn(body);



        snakeBody.renderSnake(batch, textureRegions);
        int x = snakeBody.getHeadCoord().getCoordinateX() * Sizes.TILE_PIXELS;
        int y = snakeBody.getHeadCoord().getCoordinateY() * Sizes.TILE_PIXELS;
        verify(batch).draw(any(TextureRegion.class),
                x,
                y,
                (float) Sizes.TILE_PIXELS / 2, (float) Sizes.TILE_PIXELS / 2,
                Sizes.TILE_PIXELS, Sizes.TILE_PIXELS, 1, 1,
                rotation, true);
        verify(snakeBody.getBodyParts().get(any()), atMost(snakeBody.getBodyParts().size() - 1));
         */
    }

}