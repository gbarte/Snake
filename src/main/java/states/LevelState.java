package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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
import states.utils.RendererHandler;

public class LevelState implements IState {
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 60;
    private GameStateManager stateManager;
    private Stage stage;
    private Skin skin;
    private Texture background;

    /**
     * Creates menu screen.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public LevelState(GameStateManager gameManager) {
        this.stateManager = gameManager;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal(
                "assets/quantum-horizon/skin/quantum-horizon-ui.json"));
        initTitle();
        initLevelOneButton();
        initLevelTwoButton();
        initLevelThreeButton();
        initReturnButton();
        background = new Texture("assets/bg.png");
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                handleInput(keycode);
                return false;
            }
        });
    }

    public GameStateManager getStateManager() {
        return stateManager;
    }

    public void setStateManager(GameStateManager stateManager) {
        this.stateManager = stateManager;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Texture getBackground() {
        return background;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    /**
     * Adds "Menu" to the screen.
     */
    private void initTitle() {
        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(255, 0, 255, 1));
        Label menuTitle = new Label("Select level", labelStyle);
        menuTitle.setSize(600, 120);
        menuTitle.setPosition(280, 620);
        menuTitle.setFontScale(1);
        stage.addActor(menuTitle);
    }

    /**
     * Adds level one button.
     */
    private void initLevelOneButton() {
        TextButton levelOneButton = new TextButton("Level one", skin);
        levelOneButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        levelOneButton.setPosition(400 - (levelOneButton.getWidth() / 2), 350);
        levelOneButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stateManager.setState(new PlayState(stateManager, "defaultID", "defaultName",
                        "assets/tile-set/vaporWaveSet2.png",
                        "assets/snake-texture/DefaultBody.png"));
            }

            @Override
            public boolean touchDown(
                    InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("level one");
                return true;
            }
        });
        stage.addActor(levelOneButton);
    }

    /**
     * Adds level two button.
     */
    private void initLevelTwoButton() {
        TextButton levelTwoButton = new TextButton("Level two", skin);
        levelTwoButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        levelTwoButton.setPosition(400 - (levelTwoButton.getWidth() / 2), 250);
        levelTwoButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                stateManager.setState(new PlayState(stateManager, "maps/tmx/obs.tmx",
                        "assets/snake-texture/rainbowBody.png"));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                System.out.println("level two");
                return true;
            }
        });
        stage.addActor(levelTwoButton);
    }

    /**
     * Adds level three button.
     */
    private void initLevelThreeButton() {
        TextButton levelThreeButton = new TextButton("Level three", skin);
        levelThreeButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        levelThreeButton.setPosition(400 - (levelThreeButton.getWidth() / 2), 150);
        levelThreeButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                stateManager.setState(new PlayState(stateManager, "maps/tmx/obs2.tmx",
                        "assets/snake-texture/redBlueBody.png"));
            }

            @Override
            public boolean touchDown(
                    InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pressed level three");
                return true;
            }
        });
        stage.addActor(levelThreeButton);
    }

    /**
     * Adds return button.
     */
    private void initReturnButton() {
        TextButton returnButton = new TextButton("Return", skin);
        returnButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        returnButton.setPosition(400 - (returnButton.getWidth() / 2), 50);
        returnButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                stateManager.setState(new MenuState(stateManager));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                System.out.println("pressed play");
                return true;
            }
        });
        stage.addActor(returnButton);

    }

    @Override
    public void handleInput() {
    }

    /**
     * This method is used to choose map using keyboard input.
     *
     * @param keycode The keycode indicates which key is pressed.
     */
    public void handleInput(int keycode) {
        switch (keycode) {
            case Input.Keys.NUM_1:
                stateManager.setState(new PlayState(stateManager, "defaultID", "defaultName",
                        "assets/tile-set/vaporWaveSet2.png",
                        "assets/snake-texture/DefaultBody.png"));
                break;
            case Input.Keys.NUM_2:
                stateManager.setState(new PlayState(stateManager, "maps/tmx/obs.tmx",
                        "assets/snake-texture/rainbowBody.png"));
                break;
            case Input.Keys.NUM_3:
                stateManager.setState(new PlayState(stateManager, "maps/tmx/obs2.tmx",
                        "assets/snake-texture/redBlueBody.png"));
                break;
            default:
                break;
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {
        RendererHandler.render(batch, stage, background);
    }

    @Override
    public void dispose() {
        background.dispose();
        skin.dispose();
        stage.dispose();
    }

}


