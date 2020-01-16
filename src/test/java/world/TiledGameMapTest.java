package world;

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

    @Override
    @BeforeEach
    void init() {
        this.tiledMap = Mockito.mock(TiledMap.class);
        this.tiledMapRenderer = Mockito.mock(OrthogonalTiledMapRenderer.class);
        this.fileName = "assets/def3.tmx";
        this.snake
                = new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);
        this.manager = new GameStateManager();

        Food fakeFood = Mockito.mock(Food.class);
        Score score = new Score();
        FoodFactory fakeFactory = Mockito.mock(FoodFactory.class);
        String bodyTexture = "assets/DefaultBody.png";

        this.tiledGameMap = new TiledGameMap(this.manager,
                this.snake,
                bodyTexture,
                fakeFactory,
                fakeFood,
                score,
                this.tiledMap,
                this.tiledMapRenderer,
                this.fileName);
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

    @Override
    @Test
    void updateTest() {
        //
    }


}