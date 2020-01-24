package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import models.Coordinate;
import world.GameMap;


/**
 * Interface for all the Food elements that will be
 * created on the map.
 */
public interface Food {

    void action(GameMap map);

    Coordinate getCoordinate();

    void setCoordinate(Coordinate coordinate);

    void render(SpriteBatch batch);

    void setTexture(Texture texture);

    Texture getTexture();

}
