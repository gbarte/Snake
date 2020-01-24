package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import models.Score;
import states.utils.GameRulesDialog;
import states.utils.RendererHandler;

/*Suppressing this warning because we don't need getters and
    setters for UI elements. */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class PausedState implements IState {

    private GameStateManager stateManager;
    private Stage stage;
    private Skin skin;
    private Texture background;
    private Score score;

    /**
     * Constructor which creates a new Pause state within the game.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public PausedState(GameStateManager gameManager, Score score) {
        this.stateManager = gameManager;
        this.score = score;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(
                Gdx.files.internal("assets/quantum-horizon/skin/quantum-horizon-ui.json"));
        initTitle();
        initResumeButton();
        initRulesButton();
        initQuitButton();
        background = new Texture("assets/bg.png");
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                handleInput(keycode);
                return false;
            }
        });
    }


    @Override
    public void handleInput() {
    }

    /**
     * This method is used to unpause or quit the game using keyboard input.
     * @param keycode The keycode indicates which key is pressed.
     */
    public void handleInput(int keycode) {
        switch (keycode) {
            case Input.Keys.P:
                stateManager.popState();
                break;
            case Input.Keys.Q:
                stateManager.setState(new GameOverState(stateManager, score));
                break;
            default:
                break;
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        RendererHandler.render(batch, stage, background);
    }

    /**
     * Adds "Game Over" to the screen.
     */
    private void initTitle() {
        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(255, 0, 255, 1));
        Label pausedTitle = new Label("PAUSED", labelStyle);
        pausedTitle.setSize(600, 120);
        pausedTitle.setPosition(250,620);
        pausedTitle.setFontScale(2);
        stage.addActor(pausedTitle);
    }

    /**
     * Creats button which resumes game.
     */
    private void initResumeButton() {
        TextButton resumeButton = new TextButton("resume", skin);
        resumeButton.setPosition(330, 350);
        resumeButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stateManager.popState();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pressed resume...");
                return true;
            }
        });
        stage.addActor(resumeButton);
    }

    /**
     * Creats button which shows the rules.
     */
    private void initRulesButton() {
        TextButton rulesButton = new TextButton("rules", skin);
        rulesButton.setPosition(330, 250);
        rulesButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                GameRulesDialog.display(stage);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pressed menu...");
                return true;
            }
        });
        stage.addActor(rulesButton);
    }

    /**
     * Creats button which quits game and shows menu.
     */
    private void initQuitButton() {
        TextButton quitButton = new TextButton("quit", skin);
        quitButton.setPosition(330, 150);
        quitButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stateManager.setState(new GameOverState(stateManager, score));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pressed menu...");
                return true;
            }
        });
        stage.addActor(quitButton);
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
        skin.dispose();
    }

}

