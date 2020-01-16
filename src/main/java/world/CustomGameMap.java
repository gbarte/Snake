package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import entities.Food;
import entities.factories.FoodFactory;
import entities.snake.SnakeBody;
import models.Score;
import states.GameStateManager;
import utils.Sizes;
import utils.TileType;
import world.customgamemap.CustomGameMapData;
import world.customgamemap.CustomGameMapLoader;

@SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
public class CustomGameMap extends GameMap {

    String id;
    String name;
    int[][][] map;
    GameStateManager manager;
    private TextureRegion[][] tiles;
    private SnakeBody snake;

    /**
     * Constructor for the CustomGameMap class with the minimum required setup.
     *
     * @param snake   The snake for this game.
     * @param manager The GameStateManager which sets the different stages in the game.
     */
    public CustomGameMap(SnakeBody snake, GameStateManager manager) {
        this("defaultID", "defaultName", "assets/setOfFive.png", "assets/DefaultBody.png",
                snake, manager);
    }

    /**
     * Constructor for the CustomGameMap class which would allow us to pass on specific arguments,
     * in order to set a specific CustomGameMap.
     *
     * @param id          The ID of the map.
     * @param name        The name of the map.
     * @param tileSet     The path for the theme (or set of tiles) we want to render.
     * @param bodytexture The texture path for the snake's body.
     * @param snake       The snake for this game.
     * @param manager     The GameStateManager which sets the different stages in the game.
     */
    public CustomGameMap(String id, String name, String tileSet, String bodytexture,
                         SnakeBody snake, GameStateManager manager) {
        super(bodytexture);
        this.id = id;
        this.name = name;
        this.snake = snake;
        CustomGameMapData customGameMapData =
                CustomGameMapLoader.loadMap(id, name);
        this.map = customGameMapData.map;
        Texture texture = new Texture(tileSet);
        tiles = TextureRegion.split(texture, TileType.TILE_SIZE, TileType.TILE_SIZE);
        this.manager = manager;
    }

    /**
     * This constructor is mainly used for testing purposes.
     *
     * @param id          The ID of the map.
     * @param name        The name of the map.
     * @param map         The map with all the tile type's numbers.
     * @param tiles       Container for the theme of our map's tiles.
     * @param snake       The snake for this game.
     * @param manager     The GameStateManager which sets the different stages in the game.
     * @param food        Food object that snake consumes.
     * @param score       Score object to keep track of your score.
     * @param foodFactory FoodFactory factory used to create food.
     * @param bodyTexture The texture path for the snake's skin.
     */
    public CustomGameMap(String id, String name, int[][][] map, TextureRegion[][] tiles,
                         SnakeBody snake, GameStateManager manager,
                         Food food, Score score, FoodFactory foodFactory, String bodyTexture) {
        super(Sizes.MOVE_TIME, manager, snake, foodFactory, food, score, bodyTexture);
        this.id = id;
        this.name = name;
        this.snake = snake;
        this.map = map;
        this.tiles = tiles;
        this.manager = manager;
    }

    @Override
    public void render(OrthographicCamera camera, SpriteBatch spriteBatch, SnakeBody snake) {
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        for (int layer = 0; layer < getLayers(); layer++) {
            for (int row = 0; row < getHeight(); row++) {
                for (int col = 0; col < getWidth(); col++) {
                    TileType type = this.getTileTypeByCoordinate(layer, col, row);
                    if (type != null) {
                        spriteBatch.draw(tiles[0][type.getId() - 1], (col * TileType.TILE_SIZE),
                                (row * TileType.TILE_SIZE));

                    }
                }
            }
        }
        //after rendering map up here^ u wanna render entities on the map
        //which is what u do in the super class GameMap
        super.render(camera, spriteBatch, this.snake);
        spriteBatch.end();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void dispose(OrthographicCamera camera) {

    }

    @Override
    public TileType getTileTypeByLocation(int layer, float x, float y) {
        return this.getTileTypeByCoordinate(layer, Math.round(x / TileType.TILE_SIZE),
                getHeight() - Math.round(y / TileType.TILE_SIZE) - 1);
    }

    @Override
    public TileType getTileTypeByCoordinate(int layer, int col, int row) {
        //check if its out of bounds first
        if (col < 0 || col >= getWidth() || row < 0 || row >= getHeight()) {
            return null;
        }
        //check if layer isn't out of bounds
        if (layer >= getLayers() || layer < 0) {
            layer = getLayers() - 1;
        }
        //check if there's a tile on that layer
        int id = map[layer][getHeight() - row - 1][col];

        if (id == 0) {
            layer--;
            id = map[layer][getHeight() - row - 1][col];
        }

        TileType toReturn = TileType.getTileTypeById(id);
        return toReturn;
    }

    @Override
    public int getWidth() {
        return map[0][0].length;
    }

    @Override
    public int getHeight() {
        return map[0].length;
    }

    @Override
    public int getLayers() {
        return map.length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[][][] getMap() {
        return map;
    }

    public void setMap(int[][][] map) {
        this.map = map;
    }

    public TextureRegion[][] getTiles() {
        return tiles;
    }

    public void setTiles(TextureRegion[][] tiles) {
        this.tiles = tiles;
    }

    public SnakeBody getSnake() {
        return snake;
    }

    public void setSnake(SnakeBody snake) {
        this.snake = snake;
    }

    public GameStateManager getManager() {
        return manager;
    }

    public void setManager(GameStateManager manager) {
        this.manager = manager;
    }
}
