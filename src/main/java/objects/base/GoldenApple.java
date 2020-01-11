package objects.base;

import com.badlogic.gdx.graphics.Texture;
import gamelogic.Coordinate;
import states.PlayState;

public class GoldenApple implements Food {

    public static int DEFAULT_SCORE = 25;
    public static double rarity = 0.1;
    private static final String texturePath = "assets/appleYellowSmall.png";
    private Coordinate coordinate;
    private Texture texture;

    /**
     * Creates an apple with a predefined texture at Random coordinate in the
     * texture space (Coordinate is multiplied with cell size!).
     */
    public GoldenApple(Coordinate coordinate) {
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
        play.getScore().add(DEFAULT_SCORE);
    }
}
