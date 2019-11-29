package states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {
    transient GameStateManager stateManager;
    transient MenuState state;

    @BeforeEach
    void setUp() {
        stateManager = new GameStateManager();
        state = new MenuState(stateManager);
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