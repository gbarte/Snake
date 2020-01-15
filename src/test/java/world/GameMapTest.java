package world;

import entities.snake.SnakeBody;
import models.Coordinate;
import models.Score;
import objects.base.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import states.GameStateManager;
import utils.Sizes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

//Unnecessary warnings to have getters & setters for objects
//that'll be mocked anyways and/or won't need getters & setters
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public abstract class GameMapTest {

    GameMap gameMap;
    private float timer = Sizes.MOVE_TIME;
    GameStateManager manager;
    private SnakeBody snake;
    private Apple apple;
    private Score score;
    private String bodyTexture;
    public static final int APPLE_DEFAULT_POSITION = 10;

    @BeforeEach
    abstract void init();

    //This was necessary because PMD didn't realize that this does indeed get called.
    @SuppressWarnings("PMD.MissingBreakInSwitch")
    void setUp() {
        this.gameMap = getGameMap();
        this.manager = getManager();
        this.snake = getSnake();
        this.apple = Mockito.mock(Apple.class);
        this.score = new Score();
        this.bodyTexture = "assets/DefaultBody.png";
        when(apple.getCoordinate())
                .thenReturn(new Coordinate(APPLE_DEFAULT_POSITION, APPLE_DEFAULT_POSITION));
    }

    public abstract GameMap getGameMap();

    public abstract GameStateManager getManager();

    public abstract SnakeBody getSnake();

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
        assertNotNull(getApple());
        assertNotNull(getScore());
        assertEquals(getScore().getValue(), 0);
        assertEquals(getBodyTexture(), "assets/DefaultBody.png");
        assertEquals(getApple().getCoordinate(), new Coordinate(10, 10));
    }

    @Test
    abstract void updateTest();
}