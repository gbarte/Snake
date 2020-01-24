package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entities.snake.SnakeBody;
import utils.Sizes;
import world.CustomGameMap;
import world.GameMap;
import world.TiledGameMap;

public class PlayState implements IState {

    private OrthographicCamera orthographicCamera;
    private GameStateManager gameStateManager;
    private SnakeBody snakeBody;
    private GameMap gameMap;

    /**
     * This constructor is used to call the CustomGameMap class.
     *
     * @param gameManager Manager which keeps track of the state of the game.
     * @param id          The ID of the map.
     * @param name        The name of the map.
     * @param tileSet     The path for the theme (or set of tiles) we want to render.
     * @param bodyTexture Path to the body's texture.
     */
    public PlayState(GameStateManager gameManager, String id, String name, String tileSet,
                     String bodyTexture) {
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        orthographicCamera.update();

        this.snakeBody =
                new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);

        this.gameMap = new CustomGameMap(id, name,
                tileSet, bodyTexture, this.snakeBody, gameManager);
    }

    /**
     * This constructor is used to call a new TiledGameMap.
     *
     * @param gameManager Manager which keeps track of the state of the game.
     * @param fileName    File path to the map.
     * @param bodyTexture Path to the body's texture.
     */
    public PlayState(GameStateManager gameManager, String fileName, String bodyTexture) {
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        orthographicCamera.update();

        this.snakeBody =
                new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);
        this.gameMap = new TiledGameMap(bodyTexture, fileName, snakeBody, gameManager);
    }

    /**
     * This constructor is for testability purposes.
     *
     * @param gameStateManager The gameManager which keeps track of the state of the game.
     * @param snake            The snake that'll be displayed on the map.
     * @param gameMap          The map that gets instantiated.
     */
    public PlayState(GameStateManager gameStateManager, SnakeBody snake, GameMap gameMap) {
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
        Gdx.gl.glClearColor(0, 0, 1, 1); //changes the background color, number between 0-1
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameMap.render(orthographicCamera, batch, snakeBody);
    }

    @Override
    public void dispose() {
        gameMap.dispose(orthographicCamera);
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
