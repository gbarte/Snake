package states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import objects.base.Apple;

public class MenuState extends State {
    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gameManager) {
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
        background.dispose();
        playButton.dispose();
    }

    public Texture getBackground() {
        return background;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    public Texture getPlayButton() {
        return playButton;
    }

    public void setPlayButton(Texture playButton) {
        this.playButton = playButton;
    }
}
