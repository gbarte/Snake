package states;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import snake.SnakeBody;

public class PlayState extends State {
    private final float MOVE_TIME = 0.25f;
    private float timer = MOVE_TIME;
    private SnakeBody snake;
    private ShapeRenderer shapeRenderer;

    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public PlayState(GameStateManager gameManager) {
        super(gameManager);
        shapeRenderer = new ShapeRenderer();
        snake = new SnakeBody(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
    }
    public PlayState(GameStateManager gameManager, SnakeBody snake, ShapeRenderer renderer) {
        super(gameManager);
        this.snake = snake;
        this.shapeRenderer = renderer;
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

    public float getMOVE_TIME() {
        return MOVE_TIME;
    }

    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }

    @Override
    public void handleInput() {
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.S);
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.D);

        if (upPressed) {updateDirection(SnakeBody.Direction.UP);}
        if (downPressed) {updateDirection(SnakeBody.Direction.DOWN);}
        if (leftPressed) updateDirection(SnakeBody.Direction.LEFT);
        if (rightPressed) updateDirection(SnakeBody.Direction.RIGHT);
    }

    @Override
    public void update(float dt) {
        handleInput();
        checkOutOfMap();
        updateSnake(dt);
    }

    /**
     * Clears the background, renders the batch and snake.
     * Checks what the state is and changes state and updates snake.
     * @param batch - Renders again every delta amount of time.
     */
    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        snake.renderSnake(shapeRenderer);
        //Comment out next line if you don't want the grid
        drawGrid();
        batch.end();
    }

    /**
     * Moves the snake every MOVE_TIME.
     * @param delta
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
     * @param direction
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
            }
        }
    }

    /**
     * Updates the position if newDir does not equal opposite direction,
     * this would mean that the snakes moves to itself.
     * @param newDir - Direction the snake wants to move to.
     * @param oppositeDirection - Direction snake comes from.
     */
    private void updateIfNotOpposite(SnakeBody.Direction newDir, SnakeBody.Direction oppositeDirection) {
        if (!newDir.equals(oppositeDirection)) {
            snake.setCurrDir(newDir);
        }
    }

    /**
     * Checks whether the snake (head) hits the border,
     * if it hits then the state changes to GAME_OVER.
     */
    public void checkOutOfMap() {
        if (snake.getHeadX() >= Gdx.graphics.getWidth() - snake.CELL_SIZE) {
            System.out.println("Game oveeer");
        }
        if (snake.getHeadX() <= 0) {
            System.out.println("Game oveer");
        }
        if (snake.getHeadY() >= Gdx.graphics.getHeight() -  snake.CELL_SIZE) {
            System.out.println("Game over");
        }
        if (snake.getHeadY() <= 0) {
            System.out.println("Game oveeeeer");
        }
    }

    private void drawGrid() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for(int x=0;x<Gdx.graphics.getWidth() ;x+=snake.CELL_SIZE){
            for(int y=0;y<Gdx.graphics.getHeight();y+=snake.CELL_SIZE){
                shapeRenderer.rect(x, y, snake.CELL_SIZE, snake.CELL_SIZE);
            }
        }
        shapeRenderer.end();
    }

}


