package world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snake.SnakeBody;
import states.GameStateManager;
import utils.Sizes;
import utils.TileType;
import world.customgamemap.CustomGameMapData;
import world.customgamemap.CustomGameMapLoader;

class CustomGameMapTest extends GameMapTest{

    private transient CustomGameMap customGameMap;
    private transient String id;
    private transient String name;
    private transient int[][][] map;

    private transient TextureRegion[][] tiles;
    private transient SnakeBody snake;
    private transient GameStateManager manager;


    @Override
    void init() {
        setUp();
    }

    @BeforeEach
    void setUp() {
        this.id = "defaultID";
        this.name = "defaultName";
        CustomGameMapData customGameMapData =
                CustomGameMapLoader.loadMap(id, name);
        this.map = customGameMapData.map;
        Texture texture = new Texture("assets/setOfFive.png");
        this.tiles = TextureRegion.split(texture, TileType.TILE_SIZE, TileType.TILE_SIZE);
        this.snake = new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);
        this.manager = new GameStateManager();
        this.customGameMap = new CustomGameMap(this.snake, this.manager);
    }

    @Override
    public GameMap getGameMap() {
        return this.customGameMap;
    }

    @Override
    public GameStateManager getManager() {
        return null;
    }

    @Override
    public SnakeBody getSnake() {
        return null;
    }

    @Test
    void testSomethingElse() {
        super.testSomething();
    }
}