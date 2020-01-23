package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import models.Coordinate;
import utils.Sizes;
import world.GameMap;


/**
 * Interactive food object of Apple.
 */
public class Apple implements Food {

    public static final int DEFAULT_SCORE = Sizes.DEFAULT_SCORE;
    private static final String texturePath = "assets/redapple16px.png";
    public Coordinate coordinate;
    private Texture texture;

    /**
     * Creates an apple with a predefined texture at Random coordinate in the
     * texture space (Coordinate is multiplied with cell size!).
     * @param coordinate of the apple to be placed.
     */
    public Apple(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Apple() {

    }

    public void render(SpriteBatch batch) {
        this.texture = new Texture(texturePath);
        batch.draw(texture,
                coordinate.getCoordinateX() * Sizes.TILE_PIXELS,
                coordinate.getCoordinateY() * Sizes.TILE_PIXELS);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return this.texture;
    }

    /**
     * This method takes a map's score and increases it.
     * Afterwards the entities.snake is grown;
     * @param map The map you pass from which you get all information.
     */
    @Override
    public void action(GameMap map) {
        map.getScore().add(Apple.DEFAULT_SCORE);
        map.getSnake().growSnake();
    }
}
