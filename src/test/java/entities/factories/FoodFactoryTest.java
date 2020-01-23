//package entities.factories;
//
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import entities.Food;
//import entities.snake.SnakeBody;
//import models.Coordinate;
//import models.Score;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import states.GameStateManager;
//import utils.Sizes;
//import world.CustomGameMap;
//import world.GameMap;
//import world.customgamemap.CustomGameMapLoader;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class FoodFactoryTest {
//
//    FoodFactory foodFactory;
//    GameMap customGameMap;
//    String id;
//    String name;
//    int[][][] map;
//    GameStateManager manager;
//    private TextureRegion[][] tiles;
//    private SnakeBody snake;
//
//    @BeforeEach
//    void setUp() {
//        this.snake
//                = new SnakeBody(Sizes.DEFAULT_MINIMUM_MAP_TILES, Sizes.DEFAULT_MINIMUM_MAP_TILES);
//        this.manager = new GameStateManager();
//
//        this.id = "defaultID";
//        this.name = "defaultName";
//        this.map = CustomGameMapLoader.generateDefaultMap(this.id, this.name).map;
//
//        TextureRegion fake = Mockito.mock(TextureRegion.class, "tiles");
//        TextureRegion[][] fakeTiles = new TextureRegion[
//                Sizes.DEFAULT_MINIMUM_MAP_TILES][Sizes.DEFAULT_MINIMUM_MAP_TILES];
//        //fill up the array with fake mocks
//        for (int i = 0; i < fakeTiles.length; i++) {
//            for (int j = 0; j < fakeTiles[0].length; j++) {
//                fakeTiles[i][j] = fake;
//            }
//        }
//
//        this.tiles = fakeTiles;
//
//        Food fakeFood = Mockito.mock(Food.class);
//        Score score = new Score();
//        FoodFactory fakeFactory = Mockito.mock(FoodFactory.class);
//        String bodyTexture = "assets/snake-texture/DefaultBody.png";
//
//        TextureRegion fakeHead = Mockito.mock(TextureRegion.class, "head");
//        TextureRegion fakeBody = Mockito.mock(TextureRegion.class, "body");
//        TextureRegion[][] fakeBodyTextureRegion = new TextureRegion[1][2];
//        fakeBodyTextureRegion[0][0] = fakeHead;
//        fakeBodyTextureRegion[0][1] = fakeBody;
//
//        List<Coordinate> obstacles = new ArrayList<>();
//
//        this.customGameMap = new CustomGameMap(this.id,
//                this.name,
//                this.map,
//                this.tiles,
//                this.snake,
//                this.manager,
//                fakeFood,
//                score,
//                fakeFactory,
//                bodyTexture,
//                fakeBodyTextureRegion,
//                obstacles);
//        super.setUp();
//    }
//
//    @Test
//    void switchToPowerUpFactoryTest() {
//        SnakeBody snake = play.getSnake();
//        Food food = play.getFood();
//
//        snake.setHeadCoord(food.getCoordinate());
//        play.update(10);
//
//        assertTrue(play.getFoodFactory() instanceof SimpleFoodFactory);
//
//        play.getScore().setValue(10000);
//        play.update(1);
//
//        Food food2 = play.getFood();
//        snake.setHeadCoord(food2.getCoordinate());
//        play.update(1);
//
//        assertTrue(play.getFoodFactory() instanceof PowerUpFactory);
//        assertNotEquals(food2.getCoordinate(), play.getFood().getCoordinate());
//
//    }
//
//    @Test
//    void randomCoordinatesTest() {
//        Coordinate coordinate = foodFactory.randomCoordinates();
//
//        assertTrue(coordinate.getCoordinateX() < Sizes.MAX_WIDTH_WINDOW);
//        assertTrue(coordinate.getCoordinateY() < Sizes.MAX_HEIGHT_WINDOW);
//    }
//}