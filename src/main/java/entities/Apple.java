package entities;

import com.badlogic.gdx.graphics.Texture;
import states.SnakeGame;
import models.Coordinate;

import java.util.Random;
import entities.snake.SnakeBody;

/**
 * Interactive food object of Apple.
 */
public class Apple {

    private static int DEFAULT_SCORE = 10;

    private Coordinate coordinate;
    private int score;
    private Texture texture;
    private static final String texturePath = "assets/greenapplesmall.png";

    /**
     * Creates an apple with a predefined texture at given coordinate in the
     * texture space (Coordinate is multiplied with cell size).
     * @param x coordinate of the apple on the map.
     * @param y coordinate of the apple on the map.
     */
    public Apple(int x, int y) {
        this.coordinate = new Coordinate(x * SnakeBody.CELL_SIZE, y * SnakeBody.CELL_SIZE);
        this.score = DEFAULT_SCORE;
        this.texture = new Texture(texturePath);
    }

    /**
     * Creates an apple with a predefined texture at Random coordinate in the
     * texture space (Coordinate is multiplied with cell size!).
     */
    public Apple() {
        Random r = new Random();
        int minX = 0;
        int minY = 0;
        int maxX = SnakeGame.WIDTH / SnakeBody.CELL_SIZE;
        int maxY = SnakeGame.HEIGHT / SnakeBody.CELL_SIZE;

        int x = r.nextInt(maxX - minX) + minX;
        int y = r.nextInt(maxY - minY) + minY;

        Coordinate coord = new Coordinate(x * SnakeBody.CELL_SIZE, y * SnakeBody.CELL_SIZE);
        this.coordinate = coord;
        this.score = 10;
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
        this.coordinate = new Coordinate(x * SnakeBody.CELL_SIZE, y * SnakeBody.CELL_SIZE);
        this.score = score;
        this.texture = texture;
    }

    public Apple(int x, int y, int score) {
        this.coordinate = new Coordinate(x * SnakeBody.CELL_SIZE, y * SnakeBody.CELL_SIZE);
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
