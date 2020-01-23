package world;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
import utils.TileType;

//Unnecessary warnings to have getters & setters for objects
//that'll be mocked anyways and/or won't need getters & setters
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
class TiledGameMapTest extends GameMapTest {

    GameMap tiledGameMap;
    TiledMap tiledMap;
    OrthogonalTiledMapRenderer tiledMapRenderer;
    String fileName;

    SnakeBody snake;
    GameStateManager manager;

    //this warning was suppressed because the temp variables declared here made
    //it easier to work with and pass arguments around.
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    @Override
    @BeforeEach
    void init() {
        this.tiledMap = mock(TiledMap.class);
        this.tiledMapRenderer = mock(OrthogonalTiledMapRenderer.class);
        this.fileName = "maps/tmx/11By11.tmx";
        this.snake = new SnakeBody(11, 11);
        this.manager = new GameStateManager();

        setUpTiledMap();

        Food fakeFood = mock(Food.class);
        Score score = new Score();
        FoodFactory fakeFactory = mock(FoodFactory.class);
        String bodyTexture = "assets/snake-texture/DefaultBody.png";

        TextureRegion fakeHead = mock(TextureRegion.class, "head");
        TextureRegion fakeBody = mock(TextureRegion.class, "body");
        TextureRegion[][] fakeBodyTextureRegion = new TextureRegion[1][2];
        fakeBodyTextureRegion[0][0] = fakeHead;
        fakeBodyTextureRegion[0][1] = fakeBody;

        this.tiledGameMap = new TiledGameMap(this.manager,
                this.snake,
                bodyTexture,
                fakeFactory,
                fakeFood,
                score,
                this.tiledMap,
                this.tiledMapRenderer,
                this.fileName,
                fakeBodyTextureRegion);
        setUpMapCells();
        super.setUp();
    }

    //this method is used to set up the tiledMap
    //and is not a detached test case
    @SuppressWarnings("PMD.DetachedTestCase")
    void setUpTiledMap() {
        TiledMapTileLayer fakeTiledMapTileLayer = mock(TiledMapTileLayer.class);
        MapLayers fakeMapLayers = mock(MapLayers.class);
        when(fakeMapLayers.get(anyInt())).thenReturn(fakeTiledMapTileLayer);

        when(tiledMap.getLayers()).thenReturn(fakeMapLayers);
        when(fakeTiledMapTileLayer.getHeight()).thenReturn(11);
        when(fakeTiledMapTileLayer.getWidth()).thenReturn(11);
        when(tiledMap.getLayers().getCount()).thenReturn(1);

        assertEquals(((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth(), 11);
        assertEquals(((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight(), 11);
        assertEquals(tiledMap.getLayers().getCount(), 1);
    }

    //this method is used to set up the map's cells
    //and is not a detached test case
    @SuppressWarnings("PMD.DetachedTestCase")
    void setUpMapCells() {

        TiledMapTile fakeId1 = Mockito.mock(TiledMapTile.class);
        TiledMapTile fakeId5 = Mockito.mock(TiledMapTile.class);
        when(fakeId1.getId()).thenReturn(1);
        when(fakeId5.getId()).thenReturn(5);

        Cell borderCell = Mockito.mock(Cell.class, "borderCell");
        when(borderCell.getTile()).thenReturn(fakeId1);

        Cell middleCell = Mockito.mock(Cell.class, "middleCell");
        when(middleCell.getTile()).thenReturn(fakeId5);

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (i == 0 || i == 10 || j == 0 || j == 10) {
                    when(((TiledMapTileLayer) tiledMap.getLayers().get(0)).getCell(i, j))
                            .thenReturn(borderCell);
                } else {
                    when(((TiledMapTileLayer) tiledMap.getLayers().get(0)).getCell(i, j))
                            .thenReturn(middleCell);
                }
            }
        }
    }

    @Override
    public GameMap getGameMap() {
        return this.tiledGameMap;
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
    void renderTest() {
        OrthographicCamera camera = mock(OrthographicCamera.class);
        SpriteBatch batch = mock(SpriteBatch.class);

        TiledGameMap spied = (TiledGameMap) spy(getGameMap());

        doNothing().when(spied).renderSuper(any(), any(), any());
        spied.render(camera, batch, this.snake);
        verify(spied.getTiledMapRenderer()).setView(camera);
        verify(spied.getTiledMapRenderer()).render();
        verify(batch).setProjectionMatrix(camera.combined);
        verify(batch).begin();
        verify(spied).renderSuper(any(), any(), any());
    }

    @Override
    @Test
    void updateTest() {
        //
    }

    @Test
    void disposeTest() {
        TiledMap spies = spy(tiledMap);
        OrthographicCamera camera = mock(OrthographicCamera.class);
        TiledGameMap one = (TiledGameMap) getGameMap();
        one.setTiledMap(spies);
        tiledGameMap.dispose(camera);
        verify(spies).dispose();
    }

    @Override
    @ParameterizedTest
    @CsvSource({
            "0, 16, 16, 5",
            "0, 0, 0, 1",
            //check out of bounds col and row
            "0, -16, 0, 0", //(-1,0)
            "0, 171, 0, 0", //(11, 0)
            "0, 0, -16, 0", //(0,-1)
            "0, 0, 171, 0", //(0,11)
            //check out of bounds layers
            "-1, 0, 0, 1",
            "1, 0, 0, 1",
            //check middle tiles
            "0, 80, 80, 5"
    })
    void getTileTypeByLocationTest(int l, float x, float y, int id) {
        assertEquals(tiledGameMap.getTileTypeByLocation(l, x, y), TileType.getTileTypeById(id));
    }

    @Test
    void cellIsNullTest() {
        TiledMapTileLayer fake1 = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        when(fake1.getCell(2, 2)).thenReturn(null);

        assertNull(tiledGameMap.getTileTypeByCoordinate(0, 2, 2));
    }

    @Test
    void tileIsNullTest() {
        TiledMapTileLayer fake1 = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        Cell fakeCell = fake1.getCell(3, 3);
        when(fakeCell.getTile()).thenReturn(null);

        assertNull(tiledGameMap.getTileTypeByCoordinate(0, 3, 3));
    }

    @Test
    void testTiledGameMapSetup() {
        TiledGameMap my = (TiledGameMap) tiledGameMap;
        assertNotNull(my.getTiledMap());
        assertEquals(my.getTiledMap(), this.tiledMap);
        assertEquals(my.getFileName(), this.fileName);
        assertEquals(my.getManager(), this.manager);

        SnakeBody oldSnake = this.snake;
        GameStateManager oldManager = this.manager;
        OrthogonalTiledMapRenderer oldRenderer = my.getTiledMapRenderer();

        OrthogonalTiledMapRenderer fakeMapRenderer = mock(OrthogonalTiledMapRenderer.class);
        SnakeBody snakeBody2 = new SnakeBody(11, 11);
        GameStateManager manager2 = new GameStateManager();
        my.setTiledMapRenderer(fakeMapRenderer);
        my.setFileName("maps/tmx/def1.tmx");
        my.setSnake(snakeBody2);
        my.setManager(manager2);

        assertEquals(my.getTiledMapRenderer(), fakeMapRenderer);
        assertEquals(my.getFileName(), "maps/tmx/def1.tmx");
        assertEquals(my.getSnake(), snakeBody2);
        assertEquals(my.getManager(), manager2);

        my.setTiledMapRenderer(oldRenderer);
        my.setFileName("maps/tmx/11By11.tmx");
        my.setSnake(oldSnake);
        my.setManager(oldManager);

    }
}