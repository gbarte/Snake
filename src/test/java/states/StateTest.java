package states;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import snake.SnakeBody;

class StateTest {
    transient GameStateManager stateManager;
    transient PlayState state;

    @BeforeEach
    void setUp() {
        stateManager = new GameStateManager();

        stateManager = new GameStateManager();
        ShapeRenderer shapeRenderer = Mockito.mock(ShapeRenderer.class);
        SnakeBody snake = new SnakeBody(100, 100);
        state = new PlayState(stateManager, snake, shapeRenderer);

    }

    @Test
    void getCamera() {
        OrthographicCamera camera = new OrthographicCamera();
        state.setCamera(camera);

        assertEquals(camera, state.getCamera());
    }

    @Test
    void getGameManager() {
        assertEquals(stateManager, state.getGameManager());
    }

    @Test
    void setCamera() {
        OrthographicCamera camera2 = new OrthographicCamera();
        state.setCamera(camera2);

        assertEquals(camera2, state.getCamera());
    }

    @Test
    void setGameManager() {
        GameStateManager stateManager2 = new GameStateManager();
        state.setGameManager(stateManager2);

        assertEquals(stateManager2, state.getGameManager());
    }
}