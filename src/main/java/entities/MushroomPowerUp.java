package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import models.Coordinate;
import utils.Sizes;
import world.GameMap;

/**
 * Food object which acts as a power up.
 * Main purpose of it is to decrease the speed of the snake in the game.
 *
 */
public class MushroomPowerUp implements Food {

    public static final double rarity = 0.15;
    private static final String texturePath = "assets/mushroom_mario16px.png";
    private Coordinate coordinate;
    private Texture texture;


    public MushroomPowerUp(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public MushroomPowerUp() {

    }

    public void render(SpriteBatch batch) {
        this.texture = new Texture(texturePath);
        batch.draw(texture,
                coordinate.getCoordinateX() * Sizes.TILE_PIXELS,
                coordinate.getCoordinateY() * Sizes.TILE_PIXELS);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public void action(GameMap map) {
        map.setMoveTime(1.5f * GameMap.DEFAULT_MOVE_TIME);
    }
}
