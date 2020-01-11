package objects.base;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import gamelogic.Coordinate;
import states.PlayState;


public class MushroomPowerUp implements Food {
    private final static float timeout = 5f;
    private final static float duration = 10f;
    public final static double rarity = 0.1;
    private final static float speedChange = 0.4f;
    private static final String texturePath = "assets/mushroom.png";
    private Coordinate coordinate;
    private Texture texture;


    public MushroomPowerUp(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.texture = new Texture(texturePath);
    }

    public Texture getTexture() {
        return texture;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void start(PlayState play) {
        long startTime = TimeUtils.millis();
        long elapsedTime = TimeUtils.timeSinceMillis(startTime) / 1000;

        while (elapsedTime < duration) {
            play.setSpeed(speedChange);
            elapsedTime = TimeUtils.timeSinceMillis(startTime) / 1000;
        }

        play.setSpeed(PlayState.DEFAULT_SPEED);
    }

}
