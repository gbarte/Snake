package world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import entities.Food;
import entities.factories.AppleFactory;
import entities.factories.FoodFactory;
import entities.factories.PowerUpFactory;
import entities.snake.BodyPart;
import entities.snake.SnakeBody;
import java.util.LinkedList;
import models.Coordinate;
import models.DoubleScore;
import models.Score;
import states.GameOverState;
import states.GameStateManager;
import states.PausedState;
import utils.Direction;
import utils.Sizes;
import utils.TileType;

@SuppressWarnings("PMD")
public abstract class GameMap {

    GameStateManager manager;
    public static final float DEFAULT_MOVE_TIME = Sizes.MOVE_TIME;
    private float moveTime = DEFAULT_MOVE_TIME;
    private float timer = moveTime;
    private SnakeBody snake;
    private Food food;
    private Score score;
    private FoodFactory foodFactory;
    private String bodyTexture;
    private static double powerUpTimeout = 10;

    /**
     * Constructor for the GameMap that sets a default snake body texture, an apple and the snake.
     */
    public GameMap() {
        this.manager = getManager();
        this.snake = getSnake();
        this.foodFactory = new AppleFactory();
        this.food = foodFactory.createFood();
        this.score = new Score();
        this.bodyTexture = "assets/DefaultBody.png";
    }

    /**
     * Constructor used to pass on a texture path for the snake's body.
     * @param bodyTexture The texture path for the snake's body.
     */
    public GameMap(String bodyTexture) {
        this.manager = getManager();
        this.snake = getSnake();
        this.foodFactory = new AppleFactory();
        this.food = foodFactory.createFood();
        this.score = new Score();
        this.bodyTexture = bodyTexture;
    }

    /**
     * Constructor (mainly) for testing purposes.
     * @param timer The timer for movement and updating.
     * @param manager The GameStateManager which sets the different stages in the game.
     * @param snake The snake for this map.
     * @param foodFactory FoodFactory factory used to create food.
     * @param food Food object that snake consumes.
     * @param score Score object to keep track of your score.
     * @param bodyTexture The texture path for the snake's skin.
     */
    public GameMap(float timer, GameStateManager manager, SnakeBody snake,
                   FoodFactory foodFactory, Food food, Score score, String bodyTexture) {
        this.timer = timer;
        this.manager = manager;
        this.snake = snake;
        this.foodFactory = foodFactory;
        this.food = food;
        this.score = score;
        this.bodyTexture = bodyTexture;
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

        batch.draw(food.getTexture(),
                food.getCoordinate().getCoordinateX() * Sizes.TILE_PIXELS,
                food.getCoordinate().getCoordinateY() * Sizes.TILE_PIXELS);

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
        updateBadApple();
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
        return this.getTileTypeByCoordinate(layer, Math.round(x / TileType.TILE_SIZE),
                Math.round(y / TileType.TILE_SIZE));
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

    public Food getFood() {
        return food;
    }

    public FoodFactory getFoodFactory() {
        return foodFactory;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public void setMoveTime(float moveTime) {
        this.moveTime = moveTime;
    }

    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }

    public void setSnake(SnakeBody snake) {
        this.snake = snake;
    }

    public static double getPowerUpTimeout() {
        return powerUpTimeout;
    }

    public static void setPowerUpTimeout(double powerUpTimeout) {
        GameMap.powerUpTimeout = powerUpTimeout;
    }

    /**
     * This method handles the keyboard input for the snake movements.
     */
    public void handleInput() {
        boolean quitPressed = Gdx.input.isKeyPressed(Input.Keys.Q);
        if (quitPressed) {    //pushes 'this' state (which is PlayStateTwo here)
            System.out.println("before restate "+getManager().getStates().toArray().length);
            this.getManager().reState();

            System.out.println("after restate "+getManager().getStates().toArray().length);
            this.getManager().setState(new GameOverState(getManager()));
        }
        boolean pausePressed = Gdx.input.isKeyPressed(Input.Keys.P);
        if (pausePressed) {
            System.out.println("b4 push " + getManager().getStates().toArray().length);
            this.getManager().pushState(this.getManager().peekState());

            System.out.println("na push " + getManager().getStates().toArray().length);
            this.getManager().setState(new PausedState(getManager()));
        }
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.W)
                || Gdx.input.isKeyJustPressed(Input.Keys.UP);
        if (upPressed) {
            updateDirection(Direction.UP);
        }
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.S)
                || Gdx.input.isKeyJustPressed(Input.Keys.DOWN);
        if (downPressed) {
            updateDirection(Direction.DOWN);
        }
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.A)
                || Gdx.input.isKeyJustPressed(Input.Keys.LEFT);
        if (leftPressed) {
            updateDirection(Direction.LEFT);
        }
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.D)
                || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT);
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
                Sizes.DEFAULT_AMOUNT_BORDER_TILES
                        * (Sizes.TILE_PIXELS - Sizes.PADDING_TILE_PIXELS),
                Sizes.DEFAULT_AMOUNT_BORDER_TILES
                        * (Sizes.TILE_PIXELS - Sizes.PADDING_TILE_PIXELS));
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
            getManager().setState(new GameOverState(getManager()));
        }
    }

    /**
     * Checks whether the snake head hits the body.
     * If it does, then the state changes to GAME_OVER.
     */
    public void checkHeadHitsBody() {
        int minLength = 2;
        // head can touch tail only if snake has more than 2 bodyparts
        int size = getSnake().getBodyParts().size();
        if (size > minLength) {
            for (int i = 1; i < size; i++) {
                if (getSnake().getBodyParts().get(i).getCoordinate()
                        .equals(getSnake().getHeadCoord())) {
                    getManager().setState(new GameOverState(getManager()));
                }
            }
        }
    }

    /**
     * Checks whether an apple has been eaten or not.
     */
    private void checkAppleEaten() {
        if (getSnake().getHeadCoord().equals(getFood().getCoordinate())) {
            getFood().actionTwo(this);
            food = foodFactory.createFood();
            checkAppleOnSnake();
            if (foodFactory instanceof AppleFactory) {
                activatePowerUp();
            }
        }
    }

    private void activatePowerUp() {
        if (getScore().getValue() > Sizes.DEFAULT_SCORE * 10) {
            foodFactory = new PowerUpFactory();
        }
    }

    private void checkPowerUpTimeout() {
        if (moveTime != DEFAULT_MOVE_TIME || score instanceof DoubleScore) {
            powerUpTimeout -= Gdx.graphics.getDeltaTime();
        }
        if (powerUpTimeout <= 0) {
            //shapeRenderer.setColor(Color.GREEN); // TODO change color
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
        for (BodyPart bp : getSnake().getBodyParts()) {
            if (bp.getCoordinate().equals(food.getCoordinate())) {
                food = foodFactory.createFood();
            }
        }
    }

    private void updateBadApple() {
        LinkedList<BodyPart> all = getSnake().getBodyParts();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getCoordinate().equals(food.getCoordinate())) {
                food = foodFactory.createFood();
            }
        }
    }

}
