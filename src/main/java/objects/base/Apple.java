package objects.base;

import com.badlogic.gdx.graphics.Texture;
import snake.SnakeBody;

public class Apple {
    private Coordinates coordinates;
    private int score;
    private Texture texture;
    private static final String texturePath = "assets/greenapplesmall.png";

    /**
     * Creates an apple with a predefined texture at given coordinates in the
     * texture space (Coordinate is multiplied with cell size).
     * @param x coordinate of the apple on the map.
     * @param y coordinate of the apple on the map.
     */
    public Apple(int x, int y) {
        this.coordinates = new Coordinates(x * SnakeBody.CELL_SIZE, y * SnakeBody.CELL_SIZE);
        this.score = 10;
        this.texture = new Texture(texturePath);
    }

    /**
     * Creates an apple with given texture and coordinates, as well as the score
     * that will get the player when eating this apple. This method was mainly created
     * for testing as you pass your own (mocked) texture.
     * @param x coordinate of the apple on the map.
     * @param y coordinate of the apple on the map.
     * @param score that eating this apple gives to the player.
     * @param texture of the apple on the map (has to be of the same size as cell).
     */
    public Apple(int x, int y, int score, Texture texture) {
        this.coordinates = new Coordinates(x * SnakeBody.CELL_SIZE, y * SnakeBody.CELL_SIZE);
        this.score = score;
        this.texture = texture;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
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
