package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import snake.SnakeBody;

import static org.junit.jupiter.api.Assertions.*;

class PlayStateTest {
    transient GameStateManager stateManager;

    @BeforeEach
    void setUp() {
        stateManager = new GameStateManager();
    }

    @Test
    void getShapeRenderer() {
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        SnakeBody snake = new SnakeBody(100, 100);
        PlayState play = new PlayState(stateManager, snake, shapeRenderer);

        assertEquals(play.getShapeRenderer(), shapeRenderer);
    }

    @Test
    void setShapeRenderer() {
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        SnakeBody snake = new SnakeBody(100, 100);
        PlayState play = new PlayState(stateManager, snake, shapeRenderer);

        ShapeRenderer shapeRenderer2 = Mockito.mock(ShapeRenderer.class);
        play.setShapeRenderer(shapeRenderer2);

        assertEquals(play.getShapeRenderer(), shapeRenderer2);
    }

    @Test
    void getCamera() {
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        SnakeBody snake = new SnakeBody(100, 100);
        PlayState play = new PlayState(stateManager, snake, shapeRenderer);

        OrthographicCamera camera = new OrthographicCamera();
        play.setCamera(camera);

        assertEquals(play.getCamera(), camera);
    }

    @Test
    void getSnake() {
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        SnakeBody snake = new SnakeBody(100, 100);
        PlayState play = new PlayState(stateManager, snake, shapeRenderer);

        assertEquals(play.getSnake(), snake);
    }

    @Test
    void setSnake() {
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        SnakeBody snake = new SnakeBody(100, 100);
        PlayState play = new PlayState(stateManager, snake, shapeRenderer);

        SnakeBody snake2 = new SnakeBody(100, 100);
        play.setSnake(snake2);

        assertEquals(play.getSnake(), snake2);
    }

    @Test
    void getMOVE_TIME() {
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        SnakeBody snake = new SnakeBody(100, 100);
        PlayState play = new PlayState(stateManager, snake, shapeRenderer);

        assertEquals(play.getMOVE_TIME(), 0.25f);
    }

    @Test
    void getTimer() {
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        SnakeBody snake = new SnakeBody(100, 100);
        PlayState play = new PlayState(stateManager, snake, shapeRenderer);

        assertEquals(play.getMOVE_TIME(), play.getTimer());
    }

    @Test
    void updateDirection() {
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        SnakeBody snake = new SnakeBody(100, 100);
        PlayState play = new PlayState(stateManager, snake, shapeRenderer);

        play.updateDirection(SnakeBody.Direction.RIGHT);

        assertEquals(snake.getCurrDir(), SnakeBody.Direction.RIGHT);
    }

    @Test
    void handleInput() {
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        SnakeBody snake = new SnakeBody(100, 100);
        PlayState play = new PlayState(stateManager, snake, shapeRenderer);


        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.W)).thenReturn(true);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.A)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.S)).thenReturn(false);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.D)).thenReturn(false);

        assertEquals(SnakeBody.Direction.UP, play.getSnake().getCurrDir());
    }
}