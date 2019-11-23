package states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gameManager;

    protected State(GameStateManager gameManager) {
        this.gameManager = gameManager;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    public abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch batch);

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Vector3 getMouse() {
        return mouse;
    }

    public GameStateManager getGameManager() {
        return gameManager;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void setMouse(Vector3 mouse) {
        this.mouse = mouse;
    }

    public void setGameManager(GameStateManager gameManager) {
        this.gameManager = gameManager;
    }
}
