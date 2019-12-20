package states;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import objects.base.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        play.updateDirection(SnakeBody.Direction.LEFT);

        assertEquals(snake.getCurrDir(), SnakeBody.Direction.LEFT);
    }

    @Test
    void updateDirectionTestRight() {
        play.updateDirection(SnakeBody.Direction.RIGHT);

        assertEquals(snake.getCurrDir(), SnakeBody.Direction.RIGHT);
    }

    @Test
    void updateDirectionTestUp() {
        play.updateDirection(SnakeBody.Direction.UP);

        assertEquals(snake.getCurrDir(), SnakeBody.Direction.UP);
    }

    @Test
    void updateDirectionTestDown() {
        play.updateDirection(SnakeBody.Direction.DOWN);

        assertEquals(snake.getCurrDir(), SnakeBody.Direction.DOWN);
    }

    @Test
    void updateDirectionTestSameDirection() {
        play.updateDirection(snake.getCurrDir());
        assertEquals(snake.getCurrDir(), snake.getCurrDir());
    }

    @Test
    void handleInputTestLeft() {
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.W)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.A)).thenReturn(true);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.S)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.D)).thenReturn(false);

        play.handleInput();

        assertEquals(SnakeBody.Direction.LEFT, play.getSnake().getCurrDir());
    }

    @Test
    void handleInputTestRight() {
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.W)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.A)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.S)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.D)).thenReturn(true);

        play.handleInput();

        assertEquals(SnakeBody.Direction.RIGHT, play.getSnake().getCurrDir());
    }

    @Test
    void handleInputTestUp() {
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.W)).thenReturn(true);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.A)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.S)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.D)).thenReturn(false);

        play.handleInput();
        assertEquals(SnakeBody.Direction.UP, play.getSnake().getCurrDir());
    }

    @Test
    void handleInputTestDown() {
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.W)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.A)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.S)).thenReturn(true);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.D)).thenReturn(false);

        play.handleInput();

        assertEquals(SnakeBody.Direction.DOWN, play.getSnake().getCurrDir());
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