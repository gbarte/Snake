package objects.base;

import com.badlogic.gdx.graphics.Texture;
import snake.SnakeBody;

public class Apple {
    private Coordinates coordinates;
    private int score;
    private Texture texture;

    public Apple(int x, int y) {
        this.coordinates = new Coordinates(x * SnakeBody.CELL_SIZE, y * SnakeBody.CELL_SIZE);
        this.score = 10;
        this.texture = new Texture("assets/greenapplesmall.png");
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
