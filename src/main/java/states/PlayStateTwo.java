package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import entities.snake.SnakeBody;
import utils.Sizes;
import world.CustomGameMap;
import world.GameMap;

public class PlayStateTwo implements State {

    OrthographicCamera orthographicCamera;
    GameStateManager gameStateManager;
    SnakeBody snakeBody;
    GameMap gameMap;

    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public PlayStateTwo(GameStateManager gameManager) {
//
//        this.gameStateManager = super.getGameManager();
//        this.orthographicCamera = super.getCamera();
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        orthographicCamera.update();

        this.snakeBody =
                new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);
        gameMap = new CustomGameMap(this.snakeBody, gameManager); //CustomGameMap ipv TiledGameMap

    }

    /**
     * This constructor is for testability purposes.
     * @param gameStateManager The gameManager which keeps track of the state of the game.
     * @param snake The snake that'll be displayed on the map.
     * @param gameMap The map that gets instantiated.
     */
    public PlayStateTwo(GameStateManager gameStateManager, SnakeBody snake,
                        GameMap gameMap) {
        this.gameStateManager = gameStateManager;
        this.snakeBody = snake;
        this.gameMap = gameMap;
    }

    @Override
    public void handleInput() {
        gameMap.handleInput();
    }

    @Override
    public void update(float dt) {
        gameMap.update(dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(1, 0, 0, 1); //this changes the background color, number between 0-1
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameMap.render(orthographicCamera, batch, snakeBody);
    }

    @Override
    public void dispose() {
        //batch.dispose(); ??not sure if i should dispose
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public OrthographicCamera getOrthographicCamera() {
        return orthographicCamera;
    }

    public void setOrthographicCamera(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public void setGameStateManager(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    public SnakeBody getSnakeBody() {
        return snakeBody;
    }

    public void setSnakeBody(SnakeBody snakeBody) {
        this.snakeBody = snakeBody;
    }
}
