package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.SnakeGame;
import gamelogic.Coordinates;
import java.util.Random;

import objects.base.Apple;
import snake.BodyPart;
import snake.SnakeBody;


public class PlayState extends State {
    protected static final float MOVE_TIME = 0.25f;
    private float timer = MOVE_TIME;
    private SnakeBody snake;
    private ShapeRenderer shapeRenderer;
    private Apple apple;

    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public PlayState(GameStateManager gameManager) {
        super(gameManager);
        shapeRenderer = new ShapeRenderer();
        snake = new SnakeBody(SnakeGame.WIDTH, SnakeGame.HEIGHT);
        camera.setToOrtho(false, SnakeGame.WIDTH, SnakeGame.HEIGHT);
        apple = createApple();
    }

    /**
     * Play Screen of the game.
     */
    public PlayState(GameStateManager gameManager, SnakeBody snake, ShapeRenderer renderer) {
        super(gameManager);
        this.snake = snake;
        this.shapeRenderer = renderer;
        //Since this method is more for testing purposes, we do not create an apple there.
    }

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
        Coordinates appleCoord = apple.getCoordinates();
        batch.draw(apple.getTexture(), appleCoord.getCoordinateX(), appleCoord.getCoordinateY());
        batch.end();
        //Comment out next line if you don't want the grid
        drawGrid();
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
     * @param direction - direction in which the user wants to move the snake
     */
    public void updateDirection(SnakeBody.Direction direction) {
        if (!direction.equals(snake.getCurrDir())) {
            switch (direction) {
                case UP:
                    updateIfNotOpposite(SnakeBody.Direction.UP, SnakeBody.Direction.DOWN);
                    break;
                case DOWN:
                    updateIfNotOpposite(SnakeBody.Direction.DOWN, SnakeBody.Direction.UP);
                    break;
                case LEFT:
                    updateIfNotOpposite(SnakeBody.Direction.LEFT, SnakeBody.Direction.RIGHT);
                    break;
                case RIGHT:
                    updateIfNotOpposite(SnakeBody.Direction.RIGHT, SnakeBody.Direction.LEFT);
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
        if (snake.getHeadCoord().getCoordinateX() >= SnakeGame.WIDTH - SnakeBody.CELL_SIZE) {
            System.out.println("Game oveeer");
        }
        if (snake.getHeadCoord().getCoordinateX() <= 0) {
            System.out.println("Game oveer");
        }
        if (snake.getHeadCoord().getCoordinateY() >= SnakeGame.HEIGHT - SnakeBody.CELL_SIZE) {
            System.out.println("Game over");
        }
        if (snake.getHeadCoord().getCoordinateY() <= 0) {
            System.out.println("Game oveeeeer");
        }
    }

    private void drawGrid() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int x = 0; x < SnakeGame.WIDTH; x += SnakeBody.CELL_SIZE) {
            for (int y = 0; y < SnakeGame.HEIGHT; y += SnakeBody.CELL_SIZE) {
                shapeRenderer.rect(x, y, SnakeBody.CELL_SIZE, SnakeBody.CELL_SIZE);
            }
        }
        shapeRenderer.end();
    }

    private Apple createApple() {
        Random r = new Random();
        int minX = 0;
        int minY = 0;
        int maxX = SnakeGame.WIDTH / SnakeBody.CELL_SIZE;
        int maxY = SnakeGame.HEIGHT / SnakeBody.CELL_SIZE;

        int x = r.nextInt(maxX - minX) + minX;
        int y = r.nextInt(maxY - minY) + minY;

        Apple apple = new Apple(x, y);

        return apple;
    }

    private void checkAppleEaten() {
        if (snake.getHeadCoord().equals(apple.getCoordinates())) {
            apple = createApple();
            checkAppleOnSnake();
            snake.growSnake();
        }
    }

    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    private void checkAppleOnSnake() {
        for (BodyPart bp : snake.getBodyParts()) {
            if (bp.getCoordinates().equals(apple.getCoordinates())) {
                apple = createApple();
            }
        }
    }
}


