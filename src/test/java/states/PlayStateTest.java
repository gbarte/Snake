package states;

import static game.SnakeGame.WIDTH;
import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.SnakeGame;
import gamelogic.Coordinates;
import objects.base.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import snake.BodyPart;
import snake.SnakeBody;


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
    void updateDirectionTest() {
        play.updateDirection(SnakeBody.Direction.RIGHT);

        assertEquals(snake.getCurrDir(), SnakeBody.Direction.RIGHT);
    }

    @Test
    void updateDirectionTest2() {
        play.updateDirection(SnakeBody.Direction.UP);

        assertEquals(snake.getCurrDir(), SnakeBody.Direction.UP);
    }

    @Test
    void updateIfNotOppositeTest1() {
        snake.setCurrDir(SnakeBody.Direction.LEFT);
        play.updateDirection(SnakeBody.Direction.RIGHT);
        assertEquals(SnakeBody.Direction.LEFT, snake.getCurrDir());
    }

    @Test
    void updateIfNotOppositeTest2() {
        snake.setCurrDir(SnakeBody.Direction.RIGHT);
        play.updateDirection(SnakeBody.Direction.LEFT);
        assertEquals(SnakeBody.Direction.RIGHT, snake.getCurrDir());
    }

    @Test
    void updateIfNotOppositeTest3() {
        snake.setCurrDir(SnakeBody.Direction.UP);
        play.updateDirection(SnakeBody.Direction.DOWN);
        assertEquals(SnakeBody.Direction.UP, snake.getCurrDir());
    }

    @Test
    void updateIfNotOppositeTest4() {
        snake.setCurrDir(SnakeBody.Direction.DOWN);
        play.updateDirection(SnakeBody.Direction.UP);
        assertEquals(SnakeBody.Direction.DOWN, snake.getCurrDir());
    }

    @Test
    void checkOutOfMapTest1() {
        snake.setHeadCoord(new Coordinates(SnakeGame.WIDTH, 10));
        play.setSnake(snake);
        play.checkOutOfMap();
        assertTrue(play.gameManager.getStates().peek() instanceof GameOverState);
    }

    @Test
    void checkOutOfMapTest2() {
        snake.setHeadCoord(new Coordinates(-1, 10));
        play.setSnake(snake);
        play.checkOutOfMap();
        assertTrue(play.gameManager.getStates().peek() instanceof GameOverState);
    }

    @Test
    void checkOutOfMapTest3() {
        snake.setHeadCoord(new Coordinates(10, SnakeGame.HEIGHT));
        play.setSnake(snake);
        play.checkOutOfMap();
        assertTrue(play.gameManager.getStates().peek() instanceof GameOverState);
    }

    @Test
    void checkOutOfMapTest4() {
        snake.setHeadCoord(new Coordinates(10, -1));
        play.setSnake(snake);
        play.checkOutOfMap();
        assertTrue(play.gameManager.getStates().peek() instanceof GameOverState);
    }

    @Test
    void checkHeadHitsBodyTest1() {
        //here snake of initial length < 3
        for (int i = 0; i < snake.getBodyParts().size(); i++) {
            snake.setHeadCoord(snake.getBodyParts().get(i).getCoordinates());
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
            snake.setHeadCoord(snake.getBodyParts().get(i).getCoordinates());
            play.setSnake(snake);
            play.checkHeadHitsBody();
            assertTrue(play.gameManager.getStates().peek() instanceof GameOverState);
        }
    }


    @Test
    void handleInputTest() {
        snake.setCurrDir(SnakeBody.Direction.RIGHT);
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.W)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.A)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.S)).thenReturn(true);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.D)).thenReturn(false);

        play.handleInput();

        assertEquals(SnakeBody.Direction.DOWN, play.getSnake().getCurrDir());
    }

    @Test
    void handleInputTest2() {
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.W)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.A)).thenReturn(true);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.S)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.D)).thenReturn(false);

        play.handleInput();

        assertEquals(SnakeBody.Direction.LEFT, play.getSnake().getCurrDir());
    }

    @Test
    void handleInputTest3() {
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.W)).thenReturn(true);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.A)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.S)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.D)).thenReturn(false);

        play.handleInput();
        assertEquals(SnakeBody.Direction.UP, play.getSnake().getCurrDir());
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
        assertEquals(apple2.getCoordinates(), apple.getCoordinates());
    }

}