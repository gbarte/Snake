package states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class SignUpState extends State {
    private TextButton signUpButton;
    private TextButton backToLogin;
    private TextField username;
    private TextField password;

    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public SignUpState(GameStateManager gameManager) {
        super(gameManager);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void dispose() {

    }
}
