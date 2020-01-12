package world;

import gamelogic.Score;
import objects.base.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snake.SnakeBody;
import states.GameStateManager;
import utils.Sizes;

import static org.junit.jupiter.api.Assertions.*;

public abstract class GameMapTest {

    private transient GameMap gameMap;
    private float timer = Sizes.MOVE_TIME;
    private transient GameStateManager manager;
    private transient SnakeBody snake;
    private Apple apple;
    private Score score;
    private String bodyTexture;

    @BeforeEach
    abstract void init();

    @BeforeEach
    void setUp() {
        this.gameMap = getGameMap();
        this.manager = getManager();
        this.snake = getSnake();
        this.apple = new Apple(0, 0);
        this.score = new Score();
        this.bodyTexture = "assets/DefaultBody.png";
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
    void testSomething() {
        setUp();
        assertNotNull(getSnake().getHeadCoord());
    }
}