package states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Abstract class state.
 */
public abstract class State {
    protected OrthographicCamera camera;
    protected GameStateManager gameManager;

    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     * @param gameManager which keeps track of the state of the game.
     */
    public State(GameStateManager gameManager) {
        this.gameManager = gameManager;
        camera = new OrthographicCamera();
    }

    public abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();

    public OrthographicCamera getCamera() {
        return camera;
    }

    public GameStateManager getGameManager() {
        return gameManager;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void setGameManager(GameStateManager gameManager) {
        this.gameManager = gameManager;
    }
}
