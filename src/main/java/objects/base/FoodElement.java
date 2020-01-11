package objects.base;

import com.badlogic.gdx.graphics.Texture;
import game.SnakeGame;
import gamelogic.Coordinate;
import snake.SnakeBody;

import java.util.Random;

public abstract class FoodElement {

    private Coordinate coordinate;
    private Texture texture;

    public FoodElement(String texturePath) {
        //this.coordinate = randomCoordinates();
        this.texture = new Texture(texturePath);
    }

}
