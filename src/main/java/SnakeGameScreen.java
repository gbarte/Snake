import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class SnakeGameScreen implements Screen {
    private OrthographicCamera camera;
    private FitViewport viewport;
    private SpriteBatch batch;
    private Sprite sprite;
    private SnakeBody snake;
    private STATE currState;
    private ShapeRenderer shapeRenderer;

    public enum STATE {
        GAME_READY, GAME_PAUSED, GAME_PLAYING, GAME_OVER;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public FitViewport getViewport() {
        return viewport;
    }

    public void setViewport(FitViewport viewport) {
        this.viewport = viewport;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public SnakeBody getSnake() {
        return snake;
    }

    public void setSnake(SnakeBody snake) {
        this.snake = snake;
    }

    public STATE getCurrState() {
        return currState;
    }

    public void setCurrState(STATE currState) {
        this.currState = currState;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public void setShapeRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    public void show() {
        currState = STATE.GAME_READY;
        shapeRenderer = new ShapeRenderer();
        snake = new SnakeBody();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        snake.renderSnake(shapeRenderer);
        batch.end();

        switch(currState) {
            case GAME_READY: {
                System.out.println("game is ready");
                if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
                    queryInput();
                    this.currState = STATE.GAME_PLAYING;
                }
            } break;
            case GAME_PLAYING: {
                //checks for updated direction after a keypress
                queryInput();
                snake.moveSnake(snake.getCurrDir());
            }
            break;
            case GAME_OVER: {
                //present gameover screen
                System.out.println("GAME OVER");
            }
            break;
        }
//        clearScreen();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    /**
     * Listens to which key is pressed and
     * calls the updateDirection method to update the direction.
     */
    private void queryInput() {
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.S);
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.D);

        if (upPressed) updateDirection(SnakeBody.Direction.UP);
        if (downPressed) updateDirection(SnakeBody.Direction.DOWN);
        if (leftPressed) updateDirection(SnakeBody.Direction.LEFT);
        if (rightPressed) updateDirection(SnakeBody.Direction.RIGHT);
    }

    /**
     * Updates the direction by calling updateIfNotOpposite.
     * @param direction
     */
    public void updateDirection(SnakeBody.Direction direction) {
        if (!direction.equals(snake.getCurrDir())) {
            switch (direction) {
                case UP: {
                    updateIfNotOpposite(SnakeBody.Direction.UP, SnakeBody.Direction.DOWN);
                }
                break;
                case DOWN: {
                    updateIfNotOpposite(SnakeBody.Direction.DOWN, SnakeBody.Direction.UP);
                }
                break;
                case LEFT: {
                    updateIfNotOpposite(SnakeBody.Direction.LEFT, SnakeBody.Direction.RIGHT);
                }
                break;
                case RIGHT: {
                    updateIfNotOpposite(SnakeBody.Direction.RIGHT, SnakeBody.Direction.LEFT);
                }
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
        } else {
            currState = STATE.GAME_OVER;
        }
    }

}


