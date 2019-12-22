package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entities.Player;
import snake.BodyPart;
import snake.SnakeBody;
import utils.Direction;
import utils.Sizes;
import utils.TileType;

@SuppressWarnings("PMD")
public abstract class GameMap {

    SnakeBody snake;

    /**
     * Render entities here after subclass renders map.
     * @param camera Camera on which to render.
     * @param batch Batch to use.
     */
    public void render(OrthographicCamera camera, SpriteBatch batch) {
        //render entities here
        //batch.begin();
        Player test = new Player(this);
        batch.draw(test.getTexture(),
                test.getHeadX() * Sizes.TILE_PIXELS,
                test.getHeadY() * Sizes.TILE_PIXELS);
        snake = new SnakeBody((this.getHeight() / 2), (this.getWidth() / 2));
        for (BodyPart bodyPart : snake.getBodyParts()) {
            batch.draw(test.getTexture(),
                    bodyPart.getCoordinate().getCoordinateX() * Sizes.TILE_PIXELS,
                    bodyPart.getCoordinate().getCoordinateY() * Sizes.TILE_PIXELS);
        }
        //batch.end();
    }

    public void update(float delta) {
        snake.moveSnake(SnakeBody.Direction.RIGHT);
    }

    public abstract void dispose(OrthographicCamera camera);

    /**
     * With this you can get a tile by the pixel-position within the game's given layer.
     * @param layer The layer on which the pixel is.
     * @param x The position of the pixel on the x-axis.
     * @param y The position of the pixel on the y-axis.
     * @return The tile's type.
     */
    public TileType getTileTypeByLocation(int layer, float x, float y) {
        return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE),
                (int) (y / TileType.TILE_SIZE));
    }

    public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract int getLayers();

    public int getPixelWidth() {
        return this.getWidth() * TileType.TILE_SIZE;
    }

    public int getPixelHeight() {
        return this.getHeight() * TileType.TILE_SIZE;
    }
}
