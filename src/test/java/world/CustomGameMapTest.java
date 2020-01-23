package world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import entities.Food;
import entities.factories.FoodFactory;
import entities.snake.SnakeBody;
import models.Coordinate;
import models.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import states.GameOverState;
import states.GameStateManager;
import states.PausedState;
import states.PlayState;
import utils.Direction;
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

        TextureRegion fake = Mockito.mock(TextureRegion.class, "tiles");
        TextureRegion[][] fakeTiles = new TextureRegion[
                Sizes.DEFAULT_MINIMUM_MAP_TILES][Sizes.DEFAULT_MINIMUM_MAP_TILES];
        //fill up the array with fake mocks
        for (int i = 0; i < fakeTiles.length; i++) {
            for (int j = 0; j < fakeTiles[0].length; j++) {
                fakeTiles[i][j] = fake;
            }
        }

        this.tiles = fakeTiles;

        Food fakeFood = Mockito.mock(Food.class);
        Score score = new Score();
        FoodFactory fakeFactory = Mockito.mock(FoodFactory.class);
        String bodyTexture = "assets/snake-texture/DefaultBody.png";

        TextureRegion fakeHead = Mockito.mock(TextureRegion.class, "head");
        TextureRegion fakeBody = Mockito.mock(TextureRegion.class, "body");
        TextureRegion[][] fakeBodyTextureRegion = new TextureRegion[1][2];
        fakeBodyTextureRegion[0][0] = fakeHead;
        fakeBodyTextureRegion[0][1] = fakeBody;

        this.customGameMap = new CustomGameMap(this.id,
                this.name,
                this.map,
                this.tiles,
                this.snake,
                this.manager,
                fakeFood,
                score,
                fakeFactory,
                bodyTexture,
                fakeBodyTextureRegion);
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

        getGameMap().setName("fakeName");
        assertEquals(getGameMap().getName(), "fakeName");

        TextureRegion[][] fake2 = new TextureRegion[1][1];
        TextureRegion fakeTR = mock(TextureRegion.class);
        fake2[0][0] = fakeTR;
        getGameMap().setTiles(fake2);
        assertEquals(getGameMap().getTiles(), fake2);

        getGameMap().setSnake(new SnakeBody(30, 30));
        assertEquals(getGameMap().getSnake().getHeadCoord(),
                new Coordinate(15, 15));

        GameStateManager other = new GameStateManager();
        getGameMap().setManager(other);
        assertEquals(getGameMap().getManager(), other);

        String newId = "notDefault";
        getGameMap().setId(newId);
        assertEquals(getGameMap().getId(), newId);
    }

    @Test
    void renderTest() {
        OrthographicCamera camera = Mockito.mock(OrthographicCamera.class);
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);

        CustomGameMap spied = spy(getGameMap());

        spied.renderMap(camera, batch);
        verify(batch).setProjectionMatrix(camera.combined);
        verify(batch).begin();
        verify(batch, atMost(this.map.length + this.map[0].length + this.map[0][0].length))
                .draw(tiles[0][anyInt()], anyFloat(), anyFloat());

    }

    @Test
    void badWeatherRenderTest() {
        //on (5,5)

        int[][][] temp
                = new int[4][Sizes.DEFAULT_MINIMUM_MAP_TILES][Sizes.DEFAULT_MINIMUM_MAP_TILES];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 50; j++) {
                for (int k = 0; k < 50; k++) {
                    temp[i][j][k] = this.map[i][j][k];
                }
            }
        }
        this.map = temp;
        getGameMap().setMap(this.map);
        this.map[3][44][5] = -2;
        OrthographicCamera camera = Mockito.mock(OrthographicCamera.class);
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);

        CustomGameMap spied = spy(getGameMap());

        spied.renderMap(camera, batch);
        verify(batch).setProjectionMatrix(camera.combined);
        verify(batch).begin();
        verify(batch, never()).draw(tiles[0][anyInt()], anyFloat(), anyFloat());
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
        TileType tileType = getGameMap().getTileTypeByLocation(layer, x, y);
        TileType idTile = TileType.getTileTypeById(id);
        assertEquals(idTile, tileType);
        if (tileType != null) {
            assertEquals(idTile.getName(), tileType.getName());
            assertEquals(idTile.getDamage(), tileType.getDamage());
            assertEquals(idTile.getId(), tileType.getId());
            assertEquals(idTile.isCollidable(), idTile.isCollidable());
        } else {
            assertNull(tileType);
        }
    }

    @Test
    void updateTest() {

        GameMap fake = spy(getGameMap());
        assertNotNull(getGameMap());
        doCallRealMethod().when(fake).update(anyFloat());
        doNothing().when(fake).handleInput();
        doNothing().when(fake).checkOutOfMap();
        doNothing().when(fake).checkHeadHitsBody();
        doNothing().when(fake).updatePrivateMethods();

        fake.update(Sizes.MOVE_TIME);
        verify(fake).update(anyFloat());
        verify(fake).handleInput();
        verify(fake).checkOutOfMap();
        verify(fake).checkHeadHitsBody();
        verify(fake).updatePrivateMethods();

    }

    @Test
    void disposeTest() {
        OrthographicCamera camera
                = Mockito.mock(OrthographicCamera.class, Mockito.CALLS_REAL_METHODS);
        customGameMap.dispose(camera);
    }

    @Test
    void pixelWidthTest() {
        assertEquals(getGameMap().getPixelWidth(),
                customGameMap.getWidth() * TileType.TILE_SIZE);
    }

    @Test
    void pixelHeightTest() {
        assertEquals(getGameMap().getPixelHeight(),
                customGameMap.getHeight() * TileType.TILE_SIZE);
    }
}