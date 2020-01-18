package world;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import entities.Food;
import entities.factories.FoodFactory;
import entities.snake.SnakeBody;
import models.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import states.GameStateManager;
import utils.Sizes;

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
        this.tiledMap = Mockito.mock(TiledMap.class);
        this.tiledMapRenderer = Mockito.mock(OrthogonalTiledMapRenderer.class);
        this.fileName = "maps/tmx/11By11.tmx";
        this.snake
                = new SnakeBody(11, 11);
        this.manager = new GameStateManager();

        Food fakeFood = Mockito.mock(Food.class);
        Score score = new Score();
        FoodFactory fakeFactory = Mockito.mock(FoodFactory.class);
        String bodyTexture = "assets/snake-texture/DefaultBody.png";

        TextureRegion fakeHead = Mockito.mock(TextureRegion.class, "head");
        TextureRegion fakeBody = Mockito.mock(TextureRegion.class, "body");
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
        super.setUp();
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
        OrthographicCamera camera = Mockito.mock(OrthographicCamera.class);
        SpriteBatch batch = Mockito.mock(SpriteBatch.class);

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
        OrthographicCamera camera = Mockito.mock(OrthographicCamera.class);
        TiledGameMap one = (TiledGameMap) getGameMap();
        one.setTiledMap(spies);
        tiledGameMap.dispose(camera);
        verify(spies).dispose();
    }


}