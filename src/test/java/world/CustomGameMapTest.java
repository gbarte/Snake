package world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import entities.Food;
import entities.factories.FoodFactory;
import entities.snake.SnakeBody;
import models.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import states.GameStateManager;
import utils.Sizes;
import utils.TileType;
import world.customgamemap.CustomGameMapLoader;

//Unnecessary warnings to have getters & setters for objects
//that'll be mocked anyways and/or won't need getters & setters
@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.DataflowAnomalyAnalysis"})
public class CustomGameMapTest extends GameMapTest {

    GameMap customGameMap;
    String id;
    String name;
    int[][][] map;
    GameStateManager manager;
    private TextureRegion[][] tiles;
    private SnakeBody snake;

    @Override
    @BeforeEach
    void init() {
        this.snake
                = new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);
        this.manager = new GameStateManager();

        this.id = "defaultID";
        this.name = "defaultName";
        this.map = CustomGameMapLoader.generateDefaultMap(this.id, this.name).map;

        TextureRegion[][] textureRegions = new TextureRegion[1][2];
        textureRegions[0][0] = Mockito.mock(TextureRegion.class, "head");
        textureRegions[0][1] = Mockito.mock(TextureRegion.class, "body");
        Food fakeFood = Mockito.mock(Food.class);
        Score score = new Score();
        FoodFactory fakeFactory = Mockito.mock(FoodFactory.class);
        String bodyTexture = "assets/DefaultBody.png";

        this.tiles = textureRegions;

        this.customGameMap = new CustomGameMap(this.id,
                this.name,
                this.map,
                this.tiles,
                this.snake,
                this.manager,
                fakeFood,
                score,
                fakeFactory,
                bodyTexture);
        super.setUp();
    }

    @Override
    public CustomGameMap getGameMap() {
        return (CustomGameMap) this.customGameMap;
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
    void testCustomGameMapSetup() {
        assertNotNull(getGameMap());
        assertNotNull(getManager());
        assertNotNull(getSnake());
        assertEquals(getGameMap().getId(), this.id);
        assertEquals(getGameMap().getName(), this.name);
        assertEquals(getGameMap().getMap(), this.map);
        assertEquals(customGameMap.getSnake(), this.snake);
        assertEquals(getGameMap().getTiles(), this.tiles);
        assertEquals(customGameMap.getLayers(), 2);
        assertEquals(customGameMap.getHeight(), 50);
        assertEquals(customGameMap.getWidth(), 50);
        assertEquals(customGameMap.getManager(), this.manager);
    }

    @Test
    void renderTest() {
        OrthographicCamera camera = Mockito.mock(OrthographicCamera.class);
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);

        /*
        spy(customGameMap).render(camera, batch, this.snake);
        customGameMap.render(camera, batch, this.snake);
        verify(batch).begin();
        verify(batch).draw(tiles[0][anyInt()], anyFloat(), anyFloat());
        */
    }

    @ParameterizedTest
    @CsvSource({
            //testLayering
            "-1, 0, 0, 3", //outOfBounds
            "0, 0, 0, 3",
            "1, 0, 0, 3",
            "2, 0, 0, 3", //outOfBounds
            //topLeft
            "0, -2f, 0, 3",
            "1, 0, -1f, 3",
            "2, -0.6f, -1.3f, 3",
            "0, -7.5f, 0, 3",
            "0, -8f, 1f, 3", //(0,0) outerEdge
            "0, -8.1f, 0, 0", //(-1,0)
            "0, 16f, 16f, 3", //(1,1)
            "0, 32f, 32f, 5", //(2,2) innerEdge
            //topRight
            "0, 0f, 784f, 3", //(0,49) outerEdge
            "0, 0f, 792f, 0", //(0,50)
            "0, -9, 784f, 0", //(-1,49)
            "0, 32f, 752f, 5", //(2,47) innerEdge
            "0, 16.1f, 752.2f, 3", //(1,47)
            //bottomLeft
            "0, 784f, 0f, 3", //(49, 0) outerEdge , edge point is 792
            "0, 792f, 0f, 0", //(50, 0)
            "0, 784f, -16f, 0", //(0,49, -1)
            "0, 752f, 32f, 5", //(47,2) innerEdge
            "0, 752.2f, 16f, 3", //(47,1)
            //bottomRight
            "0, 784f, 784f, 3", //(49,49) outerEdge
            "0, 768f, 768f, 3", //(48,48)
            "0, 752f, 752f, 5", //(47,47) innerEdge
            "0, 784f, 792f, 0", //(49,50)
            "0, 792f, 784f, 0", //(50,49)
            //middle
            "0, 400f, 400f, 5", //(25, 25)

    })
    void getTileTypeByLocationTest(int layer, float x, float y, int id) {
        TileType tileType = customGameMap.getTileTypeByLocation(layer, x, y);
        TileType idTile = TileType.getTileTypeById(id);
        assertEquals(idTile, tileType);
        if (tileType != null) {
            assertEquals(idTile.getName(), tileType.getName());
            assertEquals(idTile.getDamage(), tileType.getDamage());
            assertEquals(idTile.getId(), tileType.getId());
            assertEquals(idTile.isCollidable(), idTile.isCollidable());
        }
    }

    @Test
    void updateTest() {
        /*
        GameMap fake = spy(getGameMap());
        assertNotNull(getGameMap());
        doCallRealMethod().when(fake).update(anyFloat());
        doNothing().when(fake).handleInput();
        doNothing().when(fake).checkOutOfMap();
        doNothing().when(fake).checkHeadHitsBody();

        fake.update(Sizes.MOVE_TIME);
        verify(fake).update(anyFloat());
        verify(fake).handleInput();
        verify(fake).checkOutOfMap();
        verify(fake).checkHeadHitsBody();
        */
    }

    @Test
    void disposeTest() {
        OrthographicCamera camera
                = Mockito.mock(OrthographicCamera.class, Mockito.CALLS_REAL_METHODS);
        customGameMap.dispose(camera);
    }
}