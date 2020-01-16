package entities;

import com.badlogic.gdx.graphics.Texture;
import models.Coordinate;
import states.PlayState;
import world.GameMap;


/**
 * Interface for all the Food elements that will be
 * created on the map.
 */
public interface Food {

    void actionTwo(GameMap map);

    Coordinate getCoordinate();

    Texture getTexture();

    void setCoordinate(Coordinate coordinate);

    void setTexture(Texture texture);

}
