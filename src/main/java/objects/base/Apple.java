package objects.base;

import com.badlogic.gdx.graphics.Texture;
import gamelogic.Coordinate;
import gamelogic.Randomizer;
import utils.Sizes;

/**
 * Interactive food object of Apple.
 */
public class Apple {

    private Coordinate coordinate;
    private int score;
    private Texture texture;
    private static final String texturePath = "assets/redapple.png";

    /**
     * Creates an apple with a predefined texture at given coordinate in the
     * texture space (Coordinate is multiplied with cell size).
     * @param x coordinate of the apple on the map.
     * @param y coordinate of the apple on the map.
     */
    public Apple(int x, int y) {
        this.coordinate = new Coordinate(x, y);
        this.score = Sizes.DEFAULT_SCORE;
        this.texture = new Texture(texturePath);
    }

    /**
     * Creates an apple with a predefined texture at Random coordinate in the
     * texture space (Coordinate is multiplied with cell size!).
     */
    public Apple() {
        int minX = Sizes.DEFAULT_AMOUNT_BORDER_TILES;
        int minY = Sizes.DEFAULT_AMOUNT_BORDER_TILES;
        int maxX = Sizes.DEFAULT_MINIMUM_MAP_TILES - Sizes.DEFAULT_AMOUNT_BORDER_TILES;
        int maxY = Sizes.DEFAULT_MINIMUM_MAP_TILES - Sizes.DEFAULT_AMOUNT_BORDER_TILES;

        Randomizer randomizer = new Randomizer(minX, minY, maxX, maxY);

        Coordinate coord = randomizer.getRandomCoordinate();
        
        this.coordinate = coord;
        this.score = Sizes.DEFAULT_SCORE;
        this.texture = new Texture(texturePath);
    }

    /**
     * Creates an apple with given texture and coordinate, as well as the score
     * that will get the player when eating this apple. This method was mainly created
     * for testing as you pass your own (mocked) texture.
     * @param x coordinate of the apple on the map.
     * @param y coordinate of the apple on the map.
     * @param score that eating this apple gives to the player.
     * @param texture of the apple on the map (has to be of the same size as cell).
     */
    public Apple(int x, int y, int score, Texture texture) {
        this.coordinate = new Coordinate(x, y);
        this.score = score;
        this.texture = texture;
    }

    public Apple(int x, int y, int score) {
        this.coordinate = new Coordinate(x, y);
        this.score = score;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
