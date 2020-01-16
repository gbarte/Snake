package entities;

import com.badlogic.gdx.graphics.Texture;
import models.Coordinate;
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
        this.texture = new Texture(texturePath);
    }

    public GoldenApple() {

    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void actionTwo(GameMap map) {
        map.getScore().add(DEFAULT_SCORE);
        map.getSnake().growSnake(2);
    }
}
