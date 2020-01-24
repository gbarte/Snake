package world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entities.Food;
import entities.factories.FoodFactory;
import entities.snake.SnakeBody;
import java.util.List;
import models.Coordinate;
import models.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import states.GameStateManager;
import states.PlayState;
import utils.Direction;
import utils.Sizes;
import utils.TileType;

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

    @ParameterizedTest
    @CsvSource({
            "51, UP",
            "19, UP",
            "29, LEFT",
            "21, LEFT",
            "47, DOWN",
            "20, DOWN",
            "32, RIGHT",
            "22, RIGHT",
    })
    void handleInputTest(int keycode, Direction curr) {
        GameStateManager manager = mock(GameStateManager.class, CALLS_REAL_METHODS);
        GameMap spies = spy(getGameMap());
        spies.handleInput(keycode, manager);
        verify(spies).updateDirection(curr);
    }

    @Test
    void renderScoreTest() {
        SpriteBatch fakeBatch = mock(SpriteBatch.class);
        BitmapFont fakeFont = mock(BitmapFont.class);
        doNothing().when(fakeFont).setColor(Color.RED);
        getGameMap().renderScore(fakeBatch, fakeFont);
        verify(fakeFont).setColor(Color.CORAL);
        verify(fakeFont).draw(fakeBatch, "Score: " + getScore().getValue(),
                Sizes.DEFAULT_AMOUNT_BORDER_TILES * Sizes.TILE_PIXELS,
                Sizes.DEFAULT_AMOUNT_BORDER_TILES * Sizes.TILE_PIXELS);
    }

    @ParameterizedTest
    @CsvSource({
            "UP, UP",
            "RIGHT, UP",
            "UP, LEFT",
            "LEFT, DOWN",
            "DOWN, RIGHT",
    })
    void updateDirectionTest(Direction currDir, Direction newDir) {
        GameMap spies = spy(getGameMap());
        getGameMap().getSnake().setCurrDir(currDir);
        spies.updateDirection(newDir);
        verify(spies).updateDirection(newDir);
    }

    @Test
    void checkOutOfMapTestNotCollidable() {
        GameMap spies = spy(getGameMap());
        GameStateManager managerSpy = spy(getManager());

        spies.checkOutOfMap(managerSpy, getScore(), TileType.WHITETILE);
        verifyZeroInteractions(managerSpy);
    }


    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    @Test
    void fillListTest() {

        //This test only works with CustomGameMap because in TiledGameMap some
        //LibGdx libraries are called that throw NullPointerExceptions.
        if (getGameMap() instanceof CustomGameMap) {
            GameMap spies = spy(getGameMap());

            ArgumentMatcher<Integer> layer = new ArgumentMatcher<>() {
                @Override
                public boolean matches(Integer argument) {
                    return argument <= getGameMap().getLayers();
                }
            };

            ArgumentMatcher<Integer> colRow = argument -> {
                return argument >= 0 && argument < getGameMap().getHeight();
            };

            when(spies.getTileTypeByCoordinate(intThat(layer), intThat(colRow), intThat(colRow)))
                    .thenReturn(TileType.DARKBLUEWALL);
            List<Coordinate> fakeList = mock(List.class);
            spies.fillList(fakeList);

            int start = Sizes.DEFAULT_AMOUNT_BORDER_TILES;
            int finish = Sizes.DEFAULT_MINIMUM_MAP_TILES - Sizes.DEFAULT_AMOUNT_BORDER_TILES;
            verify(fakeList, atMost((finish - start) * (finish - start)))
                    .add(any(Coordinate.class));
        }
    }

}