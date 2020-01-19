package world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entities.Food;
import entities.factories.FoodFactory;
import entities.snake.SnakeBody;
import models.Coordinate;
import models.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import states.GameStateManager;
import states.PlayState;
import utils.Sizes;

//Unnecessary warnings to have getters & setters for objects
//that'll be mocked anyways and/or won't need getters & setters
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public abstract class GameMapTest {

    public static final int APPLE_DEFAULT_POSITION = 10;
    public static final float DEFAULT_MOVE_TIME = Sizes.MOVE_TIME;
    GameMap gameMap;
    GameStateManager manager;
    private float moveTime = DEFAULT_MOVE_TIME;
    private float timer = moveTime;
    private SnakeBody snake;
    private Food food;
    private Score score;
    private FoodFactory foodFactory;
    private String bodyTexture;

    @BeforeEach
    abstract void init();

    //This was necessary because PMD didn't realize that this does indeed get called.
    @SuppressWarnings("PMD.MissingBreakInSwitch")
    void setUp() {
        this.gameMap = getGameMap();
        this.manager = getManager();
        this.snake = getSnake();
        this.food = Mockito.mock(Food.class);
        this.score = new Score();
        this.foodFactory = Mockito.mock(FoodFactory.class);
        this.bodyTexture = "assets/snake-texture/DefaultBody.png";
        when(food.getCoordinate())
                .thenReturn(new Coordinate(APPLE_DEFAULT_POSITION, APPLE_DEFAULT_POSITION));
        PlayState fake = Mockito.mock(PlayState.class);
        getManager().pushState(fake);
    }

    public abstract GameMap getGameMap();

    public abstract GameStateManager getManager();

    public abstract SnakeBody getSnake();

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public String getBodyTexture() {
        return bodyTexture;
    }

    public void setBodyTexture(String bodyTexture) {
        this.bodyTexture = bodyTexture;
    }

    @Test
    void testSetup() {
        assertNotNull(getGameMap().getSnake());
        assertNotNull(getManager());
        assertNotNull(getSnake().getHeadCoord());
        assertNotNull(getFood());
        assertNotNull(getScore());
        assertEquals(getScore().getValue(), 0);
        assertEquals(getBodyTexture(), "assets/snake-texture/DefaultBody.png");
        assertEquals(getFood().getCoordinate(), new Coordinate(10, 10));
    }

    @Test
    void renderTest() {
        OrthographicCamera camera = Mockito.mock(OrthographicCamera.class);
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);

        getGameMap().render(camera, batch, getSnake());
    }

    @Test
    abstract void updateTest();

    @Test
    abstract void getTileTypeByLocationTest(int layer, float x, float y, int id);
}