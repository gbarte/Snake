package objects.base;

import com.badlogic.gdx.graphics.Texture;
import gamelogic.Coordinate;
import states.PlayState;

public interface Food {

    void action(PlayState play);

    Coordinate getCoordinate();

    Texture getTexture();

    void setCoordinate(Coordinate coordinate);

    void setTexture(Texture texture);

}
