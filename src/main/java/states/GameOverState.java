package states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverState extends State {
    private Texture backGround;
    private static final String texturePath = "assets/glitchy.png";
    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public GameOverState(GameStateManager gameManager) {
        super(gameManager);
    }

    public Texture getBackGround() {
        return backGround;
    }

    public void setBackGround(Texture backGround) {
        this.backGround = backGround;
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {
        backGround = new Texture(texturePath);
        batch.begin();
        batch.draw(backGround, 0, 0, 800, 800);
        batch.end();
    }

    @Override
    public void dispose() {
        backGround.dispose();
    }
}
