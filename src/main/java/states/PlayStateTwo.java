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

    GameMap gameMap;
    OrthographicCamera orthographicCamera;
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

        this.orthographicCamera = super.getCamera();
        orthographicCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        orthographicCamera.update();

        this.snakeBody =
                new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);
        gameMap = new CustomGameMap(snakeBody, gameManager); //CustomGameMap ipv TiledGameMap
        this.score = new Score();

    }

    /**
     * This constructor is for testability purposes.
     * @param gameStateManager
     * @param snake
     * @param gameMap
     * @param apple
     * @param score
     */
    public PlayStateTwo(GameStateManager gameStateManager, SnakeBody snake,
                        GameMap gameMap, Apple apple, Score score) {
        super(gameStateManager);
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
