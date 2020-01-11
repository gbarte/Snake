package objects.base;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import gamelogic.Coordinate;
import states.PlayState;

import java.awt.*;


public class MushroomPowerUp implements Food {
    public final static double rarity = 0.15;
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
        play.setSpeed(PlayState.DEFAULT_SPEED / 2);
        play.getShapeRenderer().setColor(Color.PURPLE);
    }

}
