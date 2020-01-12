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
import gamelogic.DoubleScore;
import gamelogic.Score;

import objects.base.Apple;
import objects.base.Food;
import objects.base.factories.FoodFactory;
import objects.base.factories.PowerUpFactory;
import objects.base.factories.SimpleFoodFactory;
import snake.BodyPart;
import snake.SnakeBody;

/**
 * In-game screen.
 */
public class PlayState extends State {
    public static float DEFAULT_MOVE_TIME = 0.25f;
    @SuppressWarnings("PMD")
    private float moveTime = DEFAULT_MOVE_TIME;
    @SuppressWarnings("PMD")
    private float timer = moveTime;
    private SnakeBody snake;
    private ShapeRenderer shapeRenderer;
    private Food food;
    private Score score;
    private FoodFactory foodFactory;
    private static double powerUpTimeout = 10;


    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public PlayState(GameStateManager gameManager) {
        super(gameManager);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.GREEN);
        snake = new SnakeBody(SnakeGame.WIDTH, SnakeGame.HEIGHT);
        camera.setToOrtho(false, SnakeGame.WIDTH, SnakeGame.HEIGHT);
        score = new Score();

        foodFactory = new SimpleFoodFactory();
        food = foodFactory.createFood();
    }

    /**
     * Constructor which creates a new state within the game.
     * Method was made just to make testing easier!
     */
    public PlayState(GameStateManager gameManager, SnakeBody snake, ShapeRenderer renderer) {
        super(gameManager);
        this.snake = snake;
        this.shapeRenderer = renderer;
        this.score = new Score();
    }

    public void setMoveTime(float moveTime) {
        this.moveTime = moveTime;
    }

    public FoodFactory getFoodFactory() {
        return foodFactory;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setFoodFactory(FoodFactory foodFactory) {
        this.foodFactory = foodFactory;
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

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public void handleInput() {
        boolean quitPressed = Gdx.input.isKeyPressed(Input.Keys.Q);
        if (quitPressed) {
            gameManager.push(gameManager.getStates().peek());
            gameManager.set(new GameOverState(gameManager));
        }
        boolean pausePressed = Gdx.input.isKeyPressed(Input.Keys.P);
        if (pausePressed) {
            gameManager.push(gameManager.getStates().peek());
            gameManager.set(new PausedState(gameManager));
        }
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.W);
        if (upPressed) {
            updateDirection(SnakeBody.Direction.UP);
        }
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.S);
        if (downPressed) {
            updateDirection(SnakeBody.Direction.DOWN);
        }
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.A);
        if (leftPressed) {
            updateDirection(SnakeBody.Direction.LEFT);
        }
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.D);
        if (rightPressed) {
            updateDirection(SnakeBody.Direction.RIGHT);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        checkOutOfMap();
        checkHeadHitsBody();
        updateSnake(dt);
        isAppleEaten();

        checkPowerUpTimeout();

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
        Coordinate foodPos = food.getCoordinate();
        batch.draw(food.getTexture(), foodPos.getCoordinateX(), foodPos.getCoordinateY());
        renderScore(batch);
        batch.end();
        //Comment out next line if you don't want the grid
        drawGrid();
    }

    /**
     * Renders the current score on the screen.
     * @param batch used for drawing elements.
     */
    private void renderScore(SpriteBatch batch) {
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
            timer = moveTime;
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
    public void updateDirection(SnakeBody.Direction newDirection) {
        SnakeBody.Direction current = snake.getCurrDir();
        if (!newDirection.equals(current)) {
            switch (current) {
                case UP:
                    updateIfNotOpposite(newDirection, SnakeBody.Direction.DOWN);
                    break;
                case DOWN:
                    updateIfNotOpposite(newDirection, SnakeBody.Direction.UP);
                    break;
                case LEFT:
                    updateIfNotOpposite(newDirection, SnakeBody.Direction.RIGHT);
                    break;
                case RIGHT:
                    updateIfNotOpposite(newDirection, SnakeBody.Direction.LEFT);
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
    private void updateIfNotOpposite(SnakeBody.Direction newDir,
                                     SnakeBody.Direction oppositeDirection) {
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
        for (int x = 0; x < SnakeGame.WIDTH; x += SnakeBody.CELL_SIZE) {
            for (int y = 0; y < SnakeGame.HEIGHT; y += SnakeBody.CELL_SIZE) {
                shapeRenderer.rect(x, y, SnakeBody.CELL_SIZE, SnakeBody.CELL_SIZE);
            }
        }
        shapeRenderer.end();
    }

    /**
     * Checks whether an apple has been eaten or not.
     */
    private void isAppleEaten() {
        if (snake.getHeadCoord().equals(food.getCoordinate())) {
            food.action(this);
            food = foodFactory.createFood();
            checkAppleOnSnake();
            if (foodFactory instanceof SimpleFoodFactory) {
                activatePowerUp();
            }
        }
    }

    private void activatePowerUp() {
        if (getScore().getValue() > Apple.DEFAULT_SCORE * 10) {
            foodFactory = new PowerUpFactory();
        }
    }

    private void checkPowerUpTimeout() {
        if (moveTime != DEFAULT_MOVE_TIME || score instanceof DoubleScore) {
            powerUpTimeout -= Gdx.graphics.getDeltaTime();
        }
        if (powerUpTimeout <= 0) {
            shapeRenderer.setColor(Color.GREEN);
            moveTime = DEFAULT_MOVE_TIME;
            int currScore = score.getValue();
            score = new Score();
            score.setValue(currScore);
            powerUpTimeout = 10;
        }
    }

    /**
     * Checks whether the snakebody is over an apple or not.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    private void checkAppleOnSnake() {
        for (BodyPart bp : snake.getBodyParts()) {
            if (bp.getCoordinate().equals(food.getCoordinate())) {
                food = foodFactory.createFood();
            }
        }
    }
}


