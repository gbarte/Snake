package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import models.Coordinate;
import utils.Sizes;
import world.GameMap;

/**
 * Interactive food object of Golden Apple.
 * Main difference from regular apple is that it gives more
 * score points and increases the snake twice.
 */
public class GoldenApple implements Food {

    public static int DEFAULT_SCORE = 25;
    public static double rarity = 0.2;
    private static final String texturePath = "assets/goldapple16px.png";
    private Coordinate coordinate;
    private Texture texture;

    /**
     * Creates an apple with a predefined texture at Random coordinate in the
     * texture space (Coordinate is multiplied with cell size!).
     * @param coordinate of the GoldenApple to be placed.
     */
    public GoldenApple(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public GoldenApple() {

    }

    /**
     * Method used to render this apple one the screen with
     * an golden apple texture.
     * @param batch to draw on
     */
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

    @Override
    public void action(GameMap map) {
        map.getScore().add(GoldenApple.DEFAULT_SCORE);
        map.getSnake().growSnake(2);
    }
}
