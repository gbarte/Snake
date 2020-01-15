package states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import entities.snake.SnakeBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.Sizes;
import world.GameMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

class PlayStateTwoTest {

    private transient OrthographicCamera orthographicCamera;
    private transient GameStateManager gameStateManager;
    private transient SnakeBody snakeBody;
    private transient GameMap gameMap;
    private transient PlayStateTwo playStateTwo;

    @BeforeEach
    void setUp() {
        this.orthographicCamera = Mockito.mock(OrthographicCamera.class);
        this.gameStateManager = new GameStateManager();
        this.snakeBody =
                new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);
        this.gameMap = Mockito.mock(GameMap.class);
        this.playStateTwo = new PlayStateTwo(gameStateManager, snakeBody, gameMap);
        gameStateManager.pushState(playStateTwo);
    }

    @Test
    void handleInputTest() {
        playStateTwo.handleInput();
        verify(playStateTwo.getGameMap(), atLeastOnce()).handleInput();
    }

    @Test
    void updateTest() {
        float dt = 0.25f;
        playStateTwo.update(dt);
        verify(playStateTwo.getGameMap(), atLeastOnce()).update(dt);
    }

    @Test
    void disposeTest() {
        playStateTwo.dispose();
        verify(playStateTwo.getGameMap(), atLeastOnce()).dispose(any(OrthographicCamera.class));
    }

    @Test
    void getGameMapTest() {
        GameMap other = Mockito.mock(GameMap.class);
        playStateTwo.setGameMap(other);
        assertEquals(other, playStateTwo.getGameMap());
    }

    @Test
    void setGameMapTest() {
        GameMap other = Mockito.mock(GameMap.class);
        GameMap original = playStateTwo.getGameMap();
        playStateTwo.setGameMap(other);
        assertEquals(other, playStateTwo.getGameMap());
        playStateTwo.setGameMap(original);
        assertEquals(original, playStateTwo.getGameMap());
    }

    @Test
    void getOrthographicCameraTest() {
        OrthographicCamera other = Mockito.mock(OrthographicCamera.class);
        playStateTwo.setOrthographicCamera(other);
        assertEquals(other, playStateTwo.getOrthographicCamera());
    }

    @Test
    void setOrthographicCameraTest() {
        OrthographicCamera other = Mockito.mock(OrthographicCamera.class);
        OrthographicCamera original = playStateTwo.getOrthographicCamera();
        playStateTwo.setOrthographicCamera(other);
        assertEquals(other, playStateTwo.getOrthographicCamera());
        playStateTwo.setOrthographicCamera(original);
        assertEquals(original, playStateTwo.getOrthographicCamera());
    }

    @Test
    void getGameStateManagerTest() {
        GameStateManager other = new GameStateManager();
        playStateTwo.setGameStateManager(other);
        assertEquals(other, playStateTwo.getGameStateManager());
    }

    @Test
    void setGameStateManagerTest() {
        GameStateManager other = new GameStateManager();
        GameStateManager original = playStateTwo.getGameStateManager();
        playStateTwo.setGameStateManager(other);
        assertEquals(other, playStateTwo.getGameStateManager());
        playStateTwo.setGameStateManager(original);
        assertEquals(original, playStateTwo.getGameStateManager());
    }

    @Test
    void getSnakeBody() {
        SnakeBody other =
                new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);
        playStateTwo.setSnakeBody(other);
        assertEquals(other, playStateTwo.getSnakeBody());
    }


    @Test
    void setSnakeBody() {
        SnakeBody other =
                new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);
        SnakeBody original = playStateTwo.getSnakeBody();
        playStateTwo.setSnakeBody(other);
        assertEquals(other, playStateTwo.getSnakeBody());
        playStateTwo.setSnakeBody(original);
        assertEquals(original, playStateTwo.getSnakeBody());
    }

}