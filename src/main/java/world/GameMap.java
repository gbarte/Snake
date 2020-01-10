package world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import gamelogic.Coordinate;
import gamelogic.Score;
import objects.base.Apple;
import snake.BodyPart;
import snake.SnakeBody;
import states.GameOverState;
import states.GameStateManager;
import utils.Direction;
import utils.Sizes;
import utils.TileType;

@SuppressWarnings("PMD")
public abstract class GameMap {

    private float timer = Sizes.MOVE_TIME;
    GameStateManager manager;
    private SnakeBody snake;
    private Apple apple;
    private Score score;
    private String bodyTexture;

    /**
     * Constructor for the GameMap that sets a default snake body texture, an apple and the snake.
     */
    public GameMap() {
        this.manager = getManager();
        this.snake = getSnake();
        this.apple = new Apple();
        this.score = new Score();
        this.bodyTexture = "assets/DefaultBody.png";
    }


    /**
     * Render entities here after subclass renders map.
     * @param camera Camera on which to render.
     * @param batch Batch to use.
     * @param snake Snake that gets passed on, can be null.
     */
    public void render(OrthographicCamera camera, SpriteBatch batch, SnakeBody snake) {
        //render entities here

        this.snake = snake;
        Texture def = new Texture(this.bodyTexture);
        TextureRegion[][] textureRegions =
                TextureRegion.split(def, Sizes.TILE_PIXELS, Sizes.TILE_PIXELS);

        batch.draw(apple.getTexture(),
                apple.getCoordinate().getCoordinateX() * Sizes.TILE_PIXELS,
                apple.getCoordinate().getCoordinateY() * Sizes.TILE_PIXELS);

        renderScore(batch);

        snake.renderSnake(batch, textureRegions);

    }

    /**
     * Update method for the snake.
     * @param delta The delta time it takes to update the snake.
     */
    public void update(float delta) {
        handleInput();
        checkOutOfMap();
        checkHeadHitsBody();
        updateSnake(delta);
        checkAppleEaten();

    }

    public abstract void dispose(OrthographicCamera camera);

    /**
     * With this you can get a tile by the pixel-position within the game's given layer.
     * @param layer The layer on which the pixel is.
     * @param x The position of the pixel on the x-axis.
     * @param y The position of the pixel on the y-axis.
     * @return The tile's type.
     */
    public TileType getTileTypeByLocation(int layer, float x, float y) {
        return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE),
                (int) (y / TileType.TILE_SIZE));
    }

    public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract int getLayers();

    public int getPixelWidth() {
        return this.getWidth() * TileType.TILE_SIZE;
    }

    public int getPixelHeight() {
        return this.getHeight() * TileType.TILE_SIZE;
    }

    public abstract SnakeBody getSnake();

    public abstract GameStateManager getManager();

    public Apple getApple() {
        return apple;
    }

    public Score getScore() {
        return score;
    }

    /**
     * This method handles the keyboard input for the snake movements.
     */
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

    /**
     * Renders the current score on the screen.
     * @param batch used for drawing elements.
     */
    public void renderScore(SpriteBatch batch) {
        BitmapFont bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.RED);
        bitmapFont.draw(batch, String.valueOf(score.getValue()),
                Sizes.DEFAULT_MINIMUM_MAP_TILES * Sizes.TILE_PIXELS,
                Sizes.DEFAULT_AMOUNT_BORDER_TILES * Sizes.TILE_PIXELS);
    }

    /**
     * Moves the snake every MOVE_TIME.
     *
     * @param delta - time interval between each step
     */
    private void updateSnake(float delta) {
        timer -= delta;
        if (timer <= 0) {
            timer = Sizes.MOVE_TIME;
            snake.moveSnake(snake.getCurrDir());
        }
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
     * Checks whether the snake (head) hits the border, // TODO remove comments!!!!!!!
     * if it hits then the state changes to GAME_OVER.
     */
    public void checkOutOfMap() {
        Coordinate currentHead = getSnake().getHeadCoord();
        TileType currentTile = getTileTypeByCoordinate(getLayers(),
                currentHead.getCoordinateX(),
                currentHead.getCoordinateY());
        if (currentTile.isCollidable()) {
            getManager().set(new GameOverState(getManager()));
            System.out.println("collides at "+currentTile.getName()+"->"+currentHead.toString());
            System.out.println("snake head " + currentHead.toString());
        }
    }

    /**
     * Checks whether the snake head hits the body.
     * If it does, then the state changes to GAME_OVER.
     */
    public void checkHeadHitsBody() {
        int minLength = 3;
        // head can touch tail only if snake has more than 3 bodyparts
        int size = getSnake().getBodyParts().size();
        if (size > minLength) {
            for (int i = 0; i < size; i++) {
                if (getSnake().getBodyParts().get(i).getCoordinate()
                        .equals(getSnake().getHeadCoord())) {
                    getManager().set(new GameOverState(getManager()));
                }
            }
        }
    }

    /**
     * Checks whether an apple has been eaten or not.
     */
    private void checkAppleEaten() {
        if (getSnake().getHeadCoord().equals(apple.getCoordinate())) {
            getScore().add(getApple().getScore());
            apple = new Apple();
            checkAppleOnSnake();
            getSnake().growSnake();
        }
    }

    /**
     * Checks whether the snakebody is over an apple or not.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    private void checkAppleOnSnake() {
        for (BodyPart bp : getSnake().getBodyParts()) {
            if (bp.getCoordinate().equals(apple.getCoordinate())) {
                apple = new Apple();
            }
        }
    }

}
