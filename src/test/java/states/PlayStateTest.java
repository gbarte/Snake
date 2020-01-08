package states;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.SnakeGame;
import gamelogic.Coordinate;
import objects.base.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import snake.SnakeBody;
import utils.Direction;


class PlayStateTest {
    private transient GameStateManager stateManager;
    private transient ShapeRenderer shapeRenderer;
    private transient SnakeBody snake;
    private transient PlayState play;


    @BeforeEach
    void setUp() {
        stateManager = new GameStateManager();
        shapeRenderer = Mockito.mock(ShapeRenderer.class);
        snake = new SnakeBody(100, 100);
        play = new PlayState(stateManager, snake, shapeRenderer);
        stateManager.push(play);
    }

    @Test
    void getShapeRendererTest() {
        assertEquals(play.getShapeRenderer(), shapeRenderer);
    }

    @Test
    void setShapeRendererTest() {
        ShapeRenderer shapeRenderer2 = Mockito.mock(ShapeRenderer.class);
        play.setShapeRenderer(shapeRenderer2);

        assertEquals(play.getShapeRenderer(), shapeRenderer2);
    }

    @Test
    void getCameraTest() {
        OrthographicCamera camera = new OrthographicCamera();
        play.setCamera(camera);

        assertEquals(play.getCamera(), camera);
    }

    @Test
    void getSnakeTest() {
        assertEquals(play.getSnake(), snake);
    }

    @Test
    void setSnakeTest() {
        SnakeBody snake2 = new SnakeBody(100, 100);
        play.setSnake(snake2);

        assertEquals(play.getSnake(), snake2);
    }

    @Test
    void getTimerTest() {
        assertEquals(PlayState.MOVE_TIME, play.getTimer());
    }

    @Test
    void getAppleTest() {
        Apple apple = Mockito.mock(Apple.class);
        play.setApple(apple);

        assertEquals(play.getApple(), apple);
    }

    @Test
    void setAppleTest() {
        Apple apple2 = Mockito.mock(Apple.class);
        play.setApple(apple2);

        assertEquals(play.getApple(), apple2);
    }

    @Test
    void updateDirectionTestLeft() {
        play.updateDirection(Direction.LEFT);

        //Default direction is Right so nothing happens when you go left
        assertEquals(Direction.RIGHT, snake.getCurrDir());
    }

    @Test
    void updateDirectionTestRight() {
        play.updateDirection(Direction.RIGHT);

        assertEquals(snake.getCurrDir(), Direction.RIGHT);
    }

    @Test
    void updateDirectionTestUp() {
        play.updateDirection(Direction.UP);

        assertEquals(snake.getCurrDir(), Direction.UP);
    }

    @Test
    void updateIfNotOppositeTest1() {
        snake.setCurrDir(Direction.LEFT);
        play.updateDirection(Direction.RIGHT);
        assertEquals(Direction.LEFT, snake.getCurrDir());
    }

    @Test
    void updateIfNotOppositeTest2() {
        snake.setCurrDir(Direction.RIGHT);
        play.updateDirection(Direction.LEFT);
        assertEquals(Direction.RIGHT, snake.getCurrDir());
    }

    @Test
    void updateIfNotOppositeTest3() {
        snake.setCurrDir(Direction.UP);
        play.updateDirection(Direction.DOWN);
        assertEquals(Direction.UP, snake.getCurrDir());
    }

    @Test
    void updateIfNotOppositeTest4() {
        snake.setCurrDir(Direction.DOWN);
        play.updateDirection(Direction.UP);
        assertEquals(Direction.DOWN, snake.getCurrDir());
    }

    @Test
    void checkOutOfMapTest1() {
        snake.setHeadCoord(new Coordinate(SnakeGame.WIDTH, 10));
        play.setSnake(snake);
        play.checkOutOfMap();
        assertTrue(play.gameManager.getStates().peek() instanceof GameOverState);
    }

    @Test
    void checkOutOfMapTest2() {
        snake.setHeadCoord(new Coordinate(-1, 10));
        play.setSnake(snake);
        play.checkOutOfMap();
        assertTrue(play.gameManager.getStates().peek() instanceof GameOverState);
    }

    @Test
    void checkOutOfMapTest3() {
        snake.setHeadCoord(new Coordinate(10, SnakeGame.HEIGHT));
        play.setSnake(snake);
        play.checkOutOfMap();
        assertTrue(play.gameManager.getStates().peek() instanceof GameOverState);
    }

    @Test
    void checkOutOfMapTest4() {
        snake.setHeadCoord(new Coordinate(10, -1));
        play.setSnake(snake);
        play.checkOutOfMap();
        assertTrue(play.gameManager.getStates().peek() instanceof GameOverState);
    }

    @Test
    void checkHeadHitsBodyTest1() {
        //here snake of initial length < 3
        for (int i = 0; i < snake.getBodyParts().size(); i++) {
            snake.setHeadCoord(snake.getBodyParts().get(i).getCoordinate());
            play.setSnake(snake);
            play.checkHeadHitsBody();
            assertFalse(play.gameManager.getStates().peek() instanceof GameOverState);
        }
    }

    @Test
    void checkHeadHitsBodyTest2() {
        //here snake length > 3
        snake.growSnake(2);
        for (int i = 0; i < snake.getBodyParts().size(); i++) {
            snake.setHeadCoord(snake.getBodyParts().get(i).getCoordinate());
            play.setSnake(snake);
            play.checkHeadHitsBody();
            assertTrue(play.gameManager.getStates().peek() instanceof GameOverState);
        }
    }

    @Test
    void updateDirectionTestDown() {
        play.updateDirection(Direction.DOWN);

        assertEquals(snake.getCurrDir(), Direction.DOWN);
    }

    @Test
    void updateDirectionTestSameDirection() {
        play.updateDirection(snake.getCurrDir());
        assertEquals(snake.getCurrDir(), snake.getCurrDir());
    }

    @Test
    void updateIfNotOppositeTest() {
        play.updateDirection(Direction.LEFT);
        play.updateDirection(Direction.DOWN);
        assertEquals(snake.getCurrDir(), Direction.DOWN);
    }

    @Test
    void handleInputTestLeft() {
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.W)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.A)).thenReturn(true);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.S)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.D)).thenReturn(false);

        play.handleInput();

        //Default direction is Right so nothing happens when you go left
        assertEquals(Direction.RIGHT, play.getSnake().getCurrDir());
    }

    @Test
    void handleInputTestRight() {
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.W)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.A)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.S)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.D)).thenReturn(true);

        play.handleInput();

        assertEquals(Direction.RIGHT, play.getSnake().getCurrDir());
    }

    @Test
    void handleInputTestUp() {
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.W)).thenReturn(true);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.A)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.S)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.D)).thenReturn(false);

        play.handleInput();
        assertEquals(Direction.UP, play.getSnake().getCurrDir());
    }

    @Test
    void handleInputTestDown() {
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.W)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.A)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.S)).thenReturn(true);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.D)).thenReturn(false);

        play.handleInput();

        assertEquals(Direction.DOWN, play.getSnake().getCurrDir());
    }

    //Flaky test bellow!
    @Test
    void eatAppleTest() {
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.W)).thenReturn(true);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.A)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.S)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.D)).thenReturn(false);

        Apple apple = play.getApple();
        play.update(10);

        Apple apple2 = play.getApple();
        assertEquals(apple2.getCoordinate(), apple.getCoordinate());
    }

}