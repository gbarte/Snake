package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.SnakeGame;
import gamelogic.Coordinate;
import gamelogic.Score;

import objects.base.Apple;
import snake.BodyPart;
import snake.SnakeBody;
import utils.Direction;

import static snake.SnakeBody.CELL_SIZE;

/**
 * In-game screen.
 */
public class PlayState extends State {
    protected static final float MOVE_TIME = 0.25f;
    // private Dialog gameOver;
    // private Skin skin;               //change import of cell size TODO
    private float timer = MOVE_TIME;
    private SnakeBody snake;
    private ShapeRenderer shapeRenderer;
    private Apple apple;
    private Score score;

    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public PlayState(GameStateManager gameManager) {
        super(gameManager);
        // skin = new Skin(Gdx.files.internal("assets/neon/skin/neon-ui.json"));
        // setDialogScreen();
        shapeRenderer = new ShapeRenderer();
        snake = new SnakeBody(SnakeGame.WIDTH, SnakeGame.HEIGHT);
        camera.setToOrtho(false, SnakeGame.WIDTH, SnakeGame.HEIGHT);
        apple = new Apple();
        score = new Score();
    }

    //    private void setDialogScreen() {
    //        gameOver = new Dialog("Game Over", skin);
    //    }

    /**
     * Constructor which creates a new state within the game.
     * Method was made just to make testing easier!
     */
    public PlayState(GameStateManager gameManager, SnakeBody snake, ShapeRenderer renderer) {
        super(gameManager);
        this.snake = snake;
        this.shapeRenderer = renderer;
        this.score = new Score();
        this.apple = new Apple(0, 0, 10);
    }

    //    public Dialog getGameOver() {
    //        return gameOver;
    //    }
    //
    //    public void setGameOver(Dialog gameOver) {
    //        this.gameOver = gameOver;
    //    }

    //    public Skin getSkin() {
    //        return skin;
    //    }
    //
    //    public void setSkin(Skin skin) {
    //        this.skin = skin;
    //    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public SnakeBody getSnake() {
        return snake;
    }

    public void setSnake(SnakeBody snake) {
        this.snake = snake;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    public float getTimer() {
        return timer;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }

    public Apple getApple() {
        return apple;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    @Override
    public void handleInput() {
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.W);
        if (upPressed) {
            updateDirection(Direction.UP);
        }
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.S);
        if (downPressed) {
            updateDirection(Direction.DOWN);
        }
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.A);
        if (leftPressed) {
            updateDirection(Direction.LEFT);
        }
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.D);
        if (rightPressed) {
            updateDirection(Direction.RIGHT);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        checkOutOfMap();
        checkHeadHitsBody();
        updateSnake(dt);
        checkAppleEaten();
    }

    /**
     * Clears the background, renders the batch and snake.
     * Checks what the state is and changes state and updates snake.
     *
     * @param batch - Renders again every delta amount of time.
     */
    @Override
    public void render(SpriteBatch batch) {
        snake.renderSnake(shapeRenderer);
        batch.begin();
        Coordinate appleCoord = apple.getCoordinate();
        batch.draw(apple.getTexture(), appleCoord.getCoordinateX(), appleCoord.getCoordinateY());
        renderScore(batch);
        batch.end();
        //Comment out next line if you don't want the grid
        drawGrid();
    }

    /**
     * Renders the current score on the screen.
     * @param batch used for drawing elements.
     */
    public void renderScore(SpriteBatch batch) {
        BitmapFont bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.RED);
        bitmapFont.draw(batch, String.valueOf(score.getValue()), 20, 20);
    }

    /**
     * Moves the snake every MOVE_TIME.
     *
     * @param delta - time interval between each step
     */
    private void updateSnake(float delta) {
        timer -= delta;
        if (timer <= 0) {
            timer = MOVE_TIME;
            snake.moveSnake(snake.getCurrDir());
        }
    }

    @Override
    public void dispose() {

    }

    /**
     * Updates the direction by calling updateIfNotOpposite.
     *
     * @param newDirection - direction in which the user wants to move the snake
     */
    public void updateDirection(Direction newDirection) {
        Direction current = snake.getCurrDir();
        if (!newDirection.equals(current)) {
            switch (current) {
                case UP:
                    updateIfNotOpposite(newDirection, Direction.DOWN);
                    break;
                case DOWN:
                    updateIfNotOpposite(newDirection, Direction.UP);
                    break;
                case LEFT:
                    updateIfNotOpposite(newDirection, Direction.RIGHT);
                    break;
                case RIGHT:
                    updateIfNotOpposite(newDirection, Direction.LEFT);
                    break;
                default:
                    // nothing happens
            }
        }
    }

    /**
     * Updates the position if newDir does not equal opposite direction,
     * this would mean that the snakes moves to itself.
     *
     * @param newDir            - Direction the snake wants to move to.
     * @param oppositeDirection - Direction snake comes from.
     */
    private void updateIfNotOpposite(Direction newDir,
                                     Direction oppositeDirection) {
        if (!newDir.equals(oppositeDirection)) {
            snake.setCurrDir(newDir);
        }
    }

    /**
     * Checks whether the snake (head) hits the border,
     * if it hits then the state changes to GAME_OVER.
     */
    public void checkOutOfMap() {
        if (snake.getHeadCoord().getCoordinateX() >= SnakeGame.WIDTH) {
            gameManager.set(new GameOverState(gameManager));
        }
        if (snake.getHeadCoord().getCoordinateX() < 0) {
            gameManager.set(new GameOverState(gameManager));
        }
        if (snake.getHeadCoord().getCoordinateY() >= SnakeGame.HEIGHT) {
            gameManager.set(new GameOverState(gameManager));
        }
        if (snake.getHeadCoord().getCoordinateY() < 0) {
            gameManager.set(new GameOverState(gameManager));
        }
    }

    /**
     * Checks whether the snake head hits the body.
     * If it does, then the state changes to GAME_OVER.
     */
    public void checkHeadHitsBody() {
        int minLength = 3;
        // head can touch tail only if snake has more than 3 bodyparts
        int size = snake.getBodyParts().size();
        if (size > minLength) {
            for (int i = 0; i < size; i++) {
                if (snake.getBodyParts().get(i).getCoordinate().equals(snake.getHeadCoord())) {
                    gameManager.set(new GameOverState(gameManager));
                }
            }
        }
    }

    /**
     * Draws the grid on the background.
     */
    private void drawGrid() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int x = 0; x < SnakeGame.WIDTH; x += CELL_SIZE) {
            for (int y = 0; y < SnakeGame.HEIGHT; y += CELL_SIZE) {
                shapeRenderer.rect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }
        shapeRenderer.end();
    }

    /**
     * Checks whether an apple has been eaten or not.
     */
    private void checkAppleEaten() {
        if (snake.getHeadCoord().equals(apple.getCoordinate())) {
            score.add(apple.getScore());
            apple = new Apple();
            checkAppleOnSnake();
            snake.growSnake();
        }
    }

    /**
     * Checks whether the snakebody is over an apple or not.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    private void checkAppleOnSnake() {
        for (BodyPart bp : snake.getBodyParts()) {
            if (bp.getCoordinate().equals(apple.getCoordinate())) {
                apple = new Apple();
            }
        }
    }
}


