package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import utils.TileType;
import world.customgamemap.CustomGameMapData;
import world.customgamemap.CustomGameMapLoader;

public class CustomGameMap extends GameMap {

    String id;
    String name;
    int[][][] map;

    private SpriteBatch spriteBatch;
    private TextureRegion[][] tiles;

    /**
     * Constructor for the custom game map.
     */
    public CustomGameMap() {
        CustomGameMapData customGameMapData =
                CustomGameMapLoader.loadMap("defaultID", "defaultName");
        this.id = customGameMapData.id;
        this.name = customGameMapData.name;
        this.map = customGameMapData.map;

        spriteBatch = new SpriteBatch();
        Texture texture = new Texture("assets/setOfFive.png");
        tiles = TextureRegion.split(texture, TileType.TILE_SIZE, TileType.TILE_SIZE);
    }

    @Override
    public void render(OrthographicCamera camera) {
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        for (int layer = 1; layer < getLayers(); layer++) {
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
        spriteBatch.end();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void dispose(OrthographicCamera camera) {
        spriteBatch.dispose();
    }

    @Override
    public TileType getTileTypeByLocation(int layer, float x, float y) {
        return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE),
                getHeight() - (int) (y / TileType.TILE_SIZE) - 1);
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

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public void setSpriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    public TextureRegion[][] getTiles() {
        return tiles;
    }

    public void setTiles(TextureRegion[][] tiles) {
        this.tiles = tiles;
    }
}
