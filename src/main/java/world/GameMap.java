package world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import models.Coordinate;
import models.DoubleScore;
import models.Score;
import states.GameOverState;
import states.GameStateManager;
import states.PausedState;
import utils.Direction;
import utils.Sizes;
import utils.TileType;

public abstract class GameMap {

    public static final float DEFAULT_MOVE_TIME = Sizes.MOVE_TIME;
    private static double powerUpTimeout = Sizes.POWER_UP_TIMEOUT;
    private GameStateManager manager;
    private float moveTime = DEFAULT_MOVE_TIME;
    private float timer = moveTime;
    private SnakeBody snake;
    private List<Coordinate> obstacles;
    private FoodFactory foodFactory;
    private Food food;
    private Score score;
    private String bodyTexture;
    private TextureRegion[][] bodyTextureRegion;

    /**
     * Constructor for the GameMap that sets a default snake body texture, an apple and the snake.
     */
    public GameMap() {
        this.manager = getManager();
        this.snake = getSnake();
        this.obstacles = new ArrayList<>();
        this.foodFactory = new AppleFactory();
        this.food = foodFactory.createFood();
        this.score = new Score();
        this.bodyTexture = "assets/snake-texture/redBlueBody.png";
        this.bodyTextureRegion = TextureRegion
                .split(new Texture(this.bodyTexture), Sizes.TILE_PIXELS, Sizes.TILE_PIXELS);
    }

    /**
     * Constructor used to pass on a texture path for the snake's body.
     *
     * @param bodyTexture The texture path for the snake's body.
     */
    public GameMap(String bodyTexture) {
        this.manager = getManager();
        this.snake = getSnake();
        this.obstacles = new ArrayList<>();
        this.foodFactory = new AppleFactory();
        this.food = foodFactory.createFood();
        this.score = new Score();
        this.bodyTexture = bodyTexture;
        this.bodyTextureRegion = TextureRegion
                .split(new Texture(bodyTexture), Sizes.TILE_PIXELS, Sizes.TILE_PIXELS);
    }

    /**
     * Constructor (mainly) for testing purposes.
     *
     * @param timer       The timer for movement and updating.
     * @param manager     The GameStateManager which sets the different stages in the game.
     * @param snake       The snake for this map.
     * @param foodFactory FoodFactory factory used to create food.
     * @param food        Food object that snake consumes.
     * @param score       Score object to keep track of your score.
     * @param bodyTexture The texture path for the snake's skin.
     */
    public GameMap(float timer, GameStateManager manager, SnakeBody snake,
                   FoodFactory foodFactory, Food food, Score score,
                   String bodyTexture, TextureRegion[][] bodyTextureRegion,
                   List<Coordinate> obstacles) {
        this.timer = timer;
        this.manager = manager;
        this.snake = snake;
        this.foodFactory = foodFactory;
        this.food = food;
        this.score = score;
        this.bodyTexture = bodyTexture;
        this.bodyTextureRegion = bodyTextureRegion;
        this.obstacles = obstacles;
    }

    public static double getPowerUpTimeout() {
        return powerUpTimeout;
    }

    public static void setPowerUpTimeout(double powerUpTimeout) {
        GameMap.powerUpTimeout = powerUpTimeout;
    }

    /**
     * Render entities here after subclass renders map.
     *
     * @param camera Camera on which to render.
     * @param batch  Batch to use.
     * @param snake  Snake that gets passed on.
     */
    public void render(OrthographicCamera camera, SpriteBatch batch, SnakeBody snake) {
        //render entities here

        this.snake = snake;
        batch.setProjectionMatrix(camera.combined);

        food.render(batch);

        renderScore(batch);

        snake.renderSnake(batch, getBodyTextureRegion());

    }

    /**
     * Update method for the snake.
     *
     * @param delta The delta time it takes to update the snake.
     */
    public void update(float delta) {
        handleInput();
        checkOutOfMap();
        checkHeadHitsBody();
        updateSnake(delta);
        //this THIS down here to update all private methods
        updatePrivateMethods();
    }

    public abstract void dispose(OrthographicCamera camera);

    /**
     * With this you can get a tile by the pixel-position within the game's given layer.
     *
     * @param layer The layer on which the pixel is.
     * @param x     The position of the pixel on the x-axis.
     * @param y     The position of the pixel on the y-axis.
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

    public abstract GameStateManager getManager();

    public void setManager(GameStateManager manager) {
        this.manager = manager;
    }

    public abstract SnakeBody getSnake();

    public void setSnake(SnakeBody snake) {
        this.snake = snake;
    }

    public List<Coordinate> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Coordinate> obstacles) {
        this.obstacles = obstacles;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public FoodFactory getFoodFactory() {
        return foodFactory;
    }

    public void setFoodFactory(FoodFactory foodFactory) {
        this.foodFactory = foodFactory;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public float getMoveTime() {
        return moveTime;
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

    public String getBodyTexture() {
        return bodyTexture;
    }

    public void setBodyTexture(String bodyTexture) {
        this.bodyTexture = bodyTexture;
    }

    public TextureRegion[][] getBodyTextureRegion() {
        return bodyTextureRegion;
    }

    public void setBodyTextureRegion(TextureRegion[][] bodyTextureRegion) {
        this.bodyTextureRegion = bodyTextureRegion;
    }

    /**
     * This method handles the keyboard input for the snake movements.
     */
    public void handleInput() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                handleInput(keycode, getManager());
                return false;
            }
        });
    }

    /**
     * This method handles the input and what to do with it.
     * The split was mainly made for testability purposes such that,
     * the libGDX functionality is separate from the testable parts.
     *
     * @param keycode The keycode indicates which key is pressed.
     * @param manager The manager needed if any other state needs to be instantiated.
     */
    public void handleInput(int keycode, GameStateManager manager) {
        switch (keycode) {
            case Input.Keys.Q:
                manager.reState();
                manager.setState(new GameOverState(manager, getScore()));
                break;
            case Input.Keys.P:
                manager.reState();
                manager.setState(new PausedState(manager, getScore()));
                break;
            case Input.Keys.W:
            case Input.Keys.UP:
                updateDirection(Direction.UP);
                break;
            case Input.Keys.A:
            case Input.Keys.LEFT:
                updateDirection(Direction.LEFT);
                break;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                updateDirection(Direction.DOWN);
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                updateDirection(Direction.RIGHT);
                break;
                /* We don't write a default in this case because if another key is
                pressed we don't care. https://stackoverflow.com/a/5241196/11213998
                It also increases the complexity of the code for no reason. **/
        }
    }

    /**
     * Renders the current score on the screen.
     *
     * @param batch used for drawing elements.
     */
    public void renderScore(SpriteBatch batch) {
        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        this.renderScore(batch, bitmapFont);
    }

    /**
     * Mainly for testability purposes.
     * Renders the current score on the screen.
     *
     * @param batch      The batch used for drawing elements.
     * @param bitmapFont This is used to render the value of the score.
     */
    public void renderScore(SpriteBatch batch, BitmapFont bitmapFont) {
        bitmapFont.setColor(Color.CORAL);
        bitmapFont.newFontCache();
        bitmapFont.draw(batch, "Score: " + score.getValue(),
                Sizes.DEFAULT_AMOUNT_BORDER_TILES * Sizes.TILE_PIXELS,
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
            timer = getMoveTime();
            getSnake().moveSnake(getSnake().getCurrDir());
        }
    }

    /**
     * Updates the direction by calling updateIfNotOpposite.
     *
     * @param newDirection - direction in which the user wants to move the snake
     */
    public void updateDirection(Direction newDirection) {
        Direction current = getSnake().getCurrDir();
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
                    /* We don't write a default in this case because if another key is
                    pressed we don't care. https://stackoverflow.com/a/5241196/11213998
                    There are only 4 directions the snake can go to.
                    It also increases the complexity of the code for no reason. **/
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
     * This method is called from the update method.
     * It's used to update private methods.
     * This split was mainly made for testability purposes.
     */
    public void updatePrivateMethods() {
        checkAppleEaten();
        updateBadApple();
        checkPowerUpTimeout();
    }

    /**
     * Checks whether the snake (head) hits the border,
     * if it hits then the state changes to GameOverState.
     */
    public void checkOutOfMap() {
        Coordinate currentHead = getSnake().getHeadCoord();
        GameStateManager manager = getManager();
        Score score = getScore();
        TileType currentTile = getTileTypeByCoordinate(getLayers(),
                currentHead.getCoordinateX(),
                currentHead.getCoordinateY());
        this.checkOutOfMap(manager, score, currentTile);
    }

    /**
     * Checks whether the snake (head) hits the border,
     * if it hits then the state changes to GameOverState.
     *
     * @param manager The GameStateManager needed to set another state
     * @param score   Current score of your game.
     */
    public void checkOutOfMap(GameStateManager manager, Score score,
                              TileType currentTile) {
        if (currentTile.isCollidable()) {
            manager.setState(new GameOverState(manager, score));
        }
    }

    /**
     * Checks whether the snake head hits the body.
     * If it does, then the state changes to GAME_OVER.
     */
    public void checkHeadHitsBody() {
        int minLength = 3;
        // head can touch tail only if snake has more than 2 bodyParts
        int size = getSnake().getBodyParts().size();
        if (size > minLength) {
            for (int i = 1; i < size; i++) {
                if (getSnake().getBodyParts().get(i).getCoordinate()
                        .equals(getSnake().getHeadCoord())) {
                    getManager().setState(new GameOverState(getManager(), score));
                }
            }
        }
    }

    /**
     * Checks whether an apple has been eaten or not.
     */
    private void checkAppleEaten() {
        if (getSnake().getHeadCoord().equals(getFood().getCoordinate())) {
            getFood().action(this);
            food = foodFactory.createFood(getObstacles());
            checkAppleOnSnake();
            if (foodFactory instanceof AppleFactory) {
                activatePowerUp();
            }
        }
    }

    private void activatePowerUp() {
        if (getScore().getValue() > Sizes.POWER_UP_ACTIVATION) {
            foodFactory = new PowerUpFactory();
        }
    }

    private void checkPowerUpTimeout() {
        if (moveTime != DEFAULT_MOVE_TIME || score instanceof DoubleScore) {
            powerUpTimeout -= Gdx.graphics.getDeltaTime();
        }
        if (powerUpTimeout <= 0) {
            //shapeRenderer.setColor(Color.GREEN); // TODO change color
            setMoveTime(DEFAULT_MOVE_TIME);
            int currScore = score.getValue();
            score = new Score();
            score.setValue(currScore);
            powerUpTimeout = Sizes.POWER_UP_TIMEOUT;
        }
    }

    /**
     * Checks whether the snakebody is over an apple or not.
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    private void checkAppleOnSnake() {
        for (BodyPart bp : getSnake().getBodyParts()) {
            if (bp.getCoordinate().equals(food.getCoordinate())) {
                food = foodFactory.createFood(getObstacles());
            }
        }
    }

    //suppressing this warning because it thinks that badFood gets redefined.
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    private void updateBadApple() {
        LinkedList<BodyPart> all = getSnake().getBodyParts();
        Coordinate foodCoordinate = getFood().getCoordinate();
        boolean badFood = getTileTypeByCoordinate(getLayers(),
                foodCoordinate.getCoordinateX(), foodCoordinate.getCoordinateY()).isCollidable();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getCoordinate().equals(food.getCoordinate()) || badFood) {
                setFood(foodFactory.createFood(getObstacles()));
                break;
            }
        }
    }

    /**
     * This method is called to fill up the list with the coordinates of all the obstacles.
     *
     * @param list The list to fill up.
     */
    public void fillList(List<Coordinate> list) {
        int start = Sizes.DEFAULT_AMOUNT_BORDER_TILES;
        int finish = Sizes.DEFAULT_MINIMUM_MAP_TILES - Sizes.DEFAULT_AMOUNT_BORDER_TILES;
        for (int i = start; i < finish; i++) {
            for (int j = start; j < finish; j++) {
                TileType currentTile = getTileTypeByCoordinate(getLayers(), i, j);
                if (currentTile.isCollidable()) {
                    list.add(new Coordinate(i, j));
                }
            }
        }
    }

}
