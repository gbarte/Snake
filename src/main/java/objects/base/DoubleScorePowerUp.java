package objects.base;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import gamelogic.Coordinate;
import gamelogic.DoubleScore;
import states.PlayState;

public class DoubleScorePowerUp implements Food {
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
        play.getShapeRenderer().setColor(Color.RED);
        int currScore = play.getScore().getValue();

        DoubleScore score = new DoubleScore();
        score.setValue(currScore);
        play.setScore(score);
    }

}
