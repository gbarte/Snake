package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import models.Coordinate;
import models.DoubleScore;
import utils.Sizes;
import world.GameMap;

/**
 * Food object which acts as a power up.
 * Main purpose of it is to double the score in the game.
 */
public class DoubleScorePowerUp implements Food {

    public static final double rarity = 0.2;
    private static final String texturePath = "assets/frog16px.png";
    private Coordinate coordinate;
    private Texture texture;


    public DoubleScorePowerUp(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public DoubleScorePowerUp() {

    }

    /**
     * Method used to render this apple one the screen with
     * an powerUp texture.
     * @param batch to draw on
     */
    public void render(SpriteBatch batch) {
        this.texture = new Texture(texturePath);
        batch.draw(texture,
                coordinate.getCoordinateX() * Sizes.TILE_PIXELS,
                coordinate.getCoordinateY() * Sizes.TILE_PIXELS);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return this.texture;
    }

    @Override
    public void action(GameMap map) {
        int currScore = map.getScore().getValue();

        DoubleScore score = new DoubleScore();
        score.setValue(currScore);
        map.setScore(score);
    }
}
