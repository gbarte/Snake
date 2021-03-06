package states;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * The initialization game class.
 */
public class SnakeGame extends ApplicationAdapter {

    public static final String TITLE = "Lil' Snake";
    private GameStateManager gameManager;
    private SpriteBatch batch;
    private static String username;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gameManager = new GameStateManager();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        gameManager.pushState(new LoginState(gameManager));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameManager.update(Gdx.graphics.getDeltaTime());
        gameManager.render(batch);
    }

    public GameStateManager getGameManager() {
        return gameManager;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setGameManager(GameStateManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SnakeGame.username = username;
    }
}

