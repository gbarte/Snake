package entities;

import com.badlogic.gdx.graphics.Texture;
import models.Coordinate;
import world.GameMap;

/**
 * Food object which acts as a power up.
 * Main purpose of it is to increase the speed of the snake in the game.
 *
 */
public class MushroomPowerUp implements Food {

    public static final double rarity = 0.15;
    private static final String texturePath = "assets/mushroom_mario16px.png";
    private Coordinate coordinate;
    private Texture texture;


    public MushroomPowerUp(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.texture = new Texture(texturePath);
    }

    public MushroomPowerUp() {

    }

    public Texture getTexture() {
        return texture;
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

    @Override
    public void actionTwo(GameMap map) {
        map.setMoveTime(map.DEFAULT_MOVE_TIME);
    }
}
