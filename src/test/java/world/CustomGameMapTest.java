package world;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import snake.SnakeBody;
import states.GameStateManager;
import utils.Sizes;

import static org.mockito.Mockito.when;

//Unnecessary warnings to have getters & setters for objects
//that'll be mocked anyways and/or won't need getters & setters
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class CustomGameMapTest extends GameMapTest {

    CustomGameMap customGameMap;
    String id;
    String name;
    int[][][] map;

    private TextureRegion[][] tiles;
    private SnakeBody snake;
    GameStateManager manager;


    @Override
    @BeforeEach
    void init() {
        this.snake
                = new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);
        this.manager = new GameStateManager();
        this.customGameMap = Mockito.mock(CustomGameMap.class);
        when(customGameMap.getId()).thenReturn("defaultID");
        when(customGameMap.getName()).thenReturn("defaultName");
        //int[][][] temp = Mockito.mock(int[][][].class);
        //when(customGameMap.getMap()).thenReturn(temp); // TODO change return type
        when(customGameMap.getSnake()).thenReturn(this.snake);
        setUp();
        super.setUp();
    }

    @SuppressWarnings("UseLocaleWithCaseConversions")
    void setUp() {
        this.id = customGameMap.getId();
        this.name = customGameMap.getName();
        this.map = customGameMap.getMap();
        //Texture texture = new Texture("assets/setOfFive.png");
        //this.tiles = Mockito.mock(TextureRegion[][].class, "libgdx TextureRegion[][] mock class");
    }

    @Override
    public GameMap getGameMap() {
        return this.customGameMap;
    }

    @Override
    public GameStateManager getManager() {
        return this.manager;
    }

    @Override
    public SnakeBody getSnake() {
        return this.snake;
    }

    @Test
    void testSomethingElse() {
        super.testSetup();
    }
}