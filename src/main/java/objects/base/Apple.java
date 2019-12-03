package objects.base;

import com.badlogic.gdx.graphics.Texture;
import snake.SnakeBody;

public class Apple {
    private Coordinates coordinates;
    private int score;
    private Texture texture;
    private static final String texturePath = "assets/greenapplesmall.png";

    public Apple(int x, int y) {
        this.coordinates = new Coordinates(x * SnakeBody.CELL_SIZE, y * SnakeBody.CELL_SIZE);
        this.score = 10;
        this.texture = new Texture(texturePath);
    }
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
