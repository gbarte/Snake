package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gamelogic.Score;
import objects.base.Apple;
import snake.SnakeBody;
import utils.Sizes;
import world.CustomGameMap;
import world.GameMap;

public class PlayStateTwo extends State {

    protected static final float MOVE_TIME = Sizes.MOVE_TIME;
    private float timer = MOVE_TIME;
    GameMap gameMap;
    OrthographicCamera orthographicCamera;
    //SpriteBatch batch;
    SnakeBody snakeBody;
    private Apple apple;
    private Score score;

    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public PlayStateTwo(GameStateManager gameManager) {
        super(gameManager);
        /*
        this(gameManager,
                new SpriteBatch(),
                new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES),
                new CustomGameMap(
                        new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES,
                                Sizes.DEFAULT_MINIMUM_MAP_TILES)),
                new Apple(),
                new Score());
         */
        this.orthographicCamera = super.getCamera();
        orthographicCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        orthographicCamera.update();

        //this.batch = new SpriteBatch();
        this.snakeBody =
                new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);
        gameMap = new CustomGameMap(snakeBody); //CustomGameMap ipv TiledGameMap
        this.apple = new Apple();
        this.score = new Score();

    }

    /**
     * This constructor is for testability purposes.
     * @param gameStateManager
     * @param batch
     * @param snake
     * @param gameMap
     * @param apple
     * @param score
     */
    public PlayStateTwo(GameStateManager gameStateManager, SpriteBatch batch,
                        SnakeBody snake, GameMap gameMap, Apple apple, Score score) {
        super(gameStateManager);
        //this.batch = batch;
        this.snakeBody = snake;
        this.gameMap = gameMap;
        this.apple = apple;
        this.score = score;
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

        //uncomment to be able to move the map
        /*if (Gdx.input.isTouched()) {
            orthographicCamera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
            orthographicCamera.update();
        } */

        /*
        if (Gdx.input.justTouched()) {
            Vector3 position = orthographicCamera.unproject(
                    new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            //might have to change the layer to 1!!!
            TileType type = getGameMap().getTileTypeByLocation(getGameMap().getLayers() - 1,
                    position.x, position.y);

            if (type != null) {
                System.out.println("Id is " + type.getId() + " name is " + type.getName());
                System.out.println("tile coordinate is x=" + (int) position.x / TileType.TILE_SIZE
                        + " & y=" + (gameMap.getHeight() - (int) position.y / TileType.TILE_SIZE));
            }
        } */
        gameMap.render(orthographicCamera, batch, snakeBody);
    }

    @Override
    public void dispose() {
        gameMap.dispose(camera);
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

    public SnakeBody getSnakeBody() {
        return snakeBody;
    }

    public void setSnakeBody(SnakeBody snakeBody) {
        this.snakeBody = snakeBody;
    }

    public Apple getApple() {
        return apple;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
