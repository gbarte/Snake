package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import entities.snake.SnakeBody;
import utils.Sizes;
import utils.TileType;
import world.CustomGameMap;
import world.GameMap;
import world.TiledGameMap;
import world.customgamemap.CustomGameMapData;
import world.customgamemap.CustomGameMapLoader;

public class PlayState implements IState {

    private OrthographicCamera orthographicCamera;
    private GameStateManager gameStateManager;
    private SnakeBody snakeBody;
    private GameMap gameMap;

    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public PlayState(GameStateManager gameManager) {
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        orthographicCamera.update();

        this.snakeBody =
                new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);
        //gameMap = new CustomGameMap(this.snakeBody, gameManager); //CustomGameMap ipv TiledGameMap

        //these are the 'customizable' things path you can pass in (for CustomGameMap)
        //String id, String name, String tileSet, String bodytexture
        CustomGameMapData mapData = CustomGameMapLoader.loadMap("defaultID", "defaultName");
        Texture texture = new Texture("assets/tile-set/setOfFive.png");
        TextureRegion[][] textureRegions
                = TextureRegion.split(texture, TileType.TILE_SIZE, TileType.TILE_SIZE);
        String bodyTexture = "assets/snake-texture/redBlueBody.png";

        //this.gameMap = new CustomGameMap("defaultID", "defaultName", "assets/tile-set/setOfFive.png", bodyTexture, this.snakeBody, gameManager);


        //TODO fix/remove
        //these are the 'customizable' things path you can pass in (for TiledGameMap)
        //String filename, String bodyTexture

        String fileName = "maps/tmx/obs.tmx";

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
        Gdx.gl.glClearColor(1, 0, 0, 1); //changes the background color, number between 0-1
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
