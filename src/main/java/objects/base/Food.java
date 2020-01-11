package objects.base;

import com.badlogic.gdx.graphics.Texture;
import gamelogic.Coordinate;
import states.PlayState;

public interface Food {

    void start(PlayState play);
    Coordinate getCoordinate();
    Texture getTexture();

}
