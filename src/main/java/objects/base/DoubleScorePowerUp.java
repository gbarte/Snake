package objects.base;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import gamelogic.Coordinate;
import states.PlayState;

public class DoubleScorePowerUp implements Food {
    private final static float timeout = 10f;
    private final static float duration = 10f;
    public final static double rarity = 0.2;
    private static final String texturePath = "assets/goldenApple.png";
    private Coordinate coordinate;
    private Texture texture;


    public DoubleScorePowerUp(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.texture = new Texture(texturePath);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Texture getTexture() {
        return texture;
    }

    @Override
    public void start(PlayState play) {
        long startTime = TimeUtils.millis();
        long elapsedTime = TimeUtils.timeSinceMillis(startTime) / 1000;

        while (elapsedTime < duration) {
            if(play.isAppleEaten()) {
                play.getScore().add(Apple.DEFAULT_SCORE);
                elapsedTime = TimeUtils.timeSinceMillis(startTime) / 1000;
            }
        }
    }

}
