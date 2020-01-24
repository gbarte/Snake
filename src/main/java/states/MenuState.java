package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Creates menu screen.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class MenuState implements IState {
    private GameStateManager stateManager;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 60;
    private Stage stage;
    private Skin skin;
    private Texture background;

    /**
     * Creates menu screen.
     * @param gameManager which keeps track of the state of the game.
     */
    public MenuState(GameStateManager gameManager) {
        this.stateManager = gameManager;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal(
                "assets/quantum-horizon/skin/quantum-horizon-ui.json"));
        initTitle();
        initRulesButton();
        initPlayButton();
        initSettingsButton();
        initLeaderboardButton();
        initSignOutButton();
        initRenderUsername();
        background = new Texture("assets/bg.png");
    }

    /**
     * Adds "Menu" to the screen.
     */
    private void initTitle() {
        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(255, 0, 255, 1));
        Label menuTitle = new Label("MENU", labelStyle);
        menuTitle.setSize(600, 120);
        menuTitle.setPosition(300,620);
        menuTitle.setFontScale(2);
        stage.addActor(menuTitle);
    }

    /**
     * Adds the sign out button.
     */
    private void initSignOutButton() {
        TextButton signOutButton = new TextButton("Sign Out", skin);
        signOutButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        signOutButton.setPosition(400 - (signOutButton.getWidth() / 2), 150);
        signOutButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stateManager.setState(new LoginState(stateManager));
            }

            @Override
            public boolean touchDown(
                    InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pressed signout");
                return true;
            }
        });
        stage.addActor(signOutButton);
    }

    /**
     * Adds leaderboard button.
     */
    private void initLeaderboardButton() {
        TextButton leaderboardButton = new TextButton("Leaderboard", skin);
        leaderboardButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        leaderboardButton.setPosition(400 - (leaderboardButton.getWidth() / 2), 250);
        leaderboardButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                stateManager.setState(new LeaderboardState(stateManager));
            }

            @Override
            public boolean touchDown(
                    InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pressed leaderboard");
                return true;
            }
        });
        stage.addActor(leaderboardButton);
    }

    /**
     * Add rules button.
     */
    private void initRulesButton() {
        TextButton rulesButton = new TextButton("Rules", skin);
        rulesButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        rulesButton.setPosition(400 - (rulesButton.getWidth() / 2), 350);
        rulesButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                gameRulesDialog();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                System.out.println("pressed game rules");
                return true;
            }
        });
        stage.addActor(rulesButton);
    }

    /**
     * Adds play button.
     */
    private void initPlayButton() {
        TextButton playButton = new TextButton("Start Game", skin);
        playButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        playButton.setPosition(400 - (playButton.getWidth() / 2), 450);
        playButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                stateManager.setState(new LevelState(stateManager));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                System.out.println("pressed play");
                return true;
            }
        });
        stage.addActor(playButton);

    }

    /**
     * Adds the username of logged in user to the screen.
     */
    private void initRenderUsername() {
        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(255, 0, 255, 1));
        Label renderUsername = new Label("Logged in as " + SnakeGame.username, labelStyle);
        renderUsername.setSize(100, 20);
        renderUsername.setPosition(5,775);
        renderUsername.setFontScale((float) 1);
        stage.addActor(renderUsername);
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, 800, 800);
        stage.getBatch().end();
        stage.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        skin.dispose();
        stage.dispose();
    }

    /**
     * This dialog box is shown when the user wants to start the game.
     */
    public void gameRulesDialog() {
        Skin uiSkin = new Skin(Gdx.files.internal("assets/cloud-form/skin/cloud-form-ui.json"));
        Dialog dialog = new Dialog("Rules", uiSkin, "dialog") {
            public void result(Object obj) {
                System.out.println("result " + obj);
            }
        };
        dialog.text("Use 'WASD' to move the snake.\n"
                + "Eat food to grow your snake.\n"
                + "Game will end when you either hit yourself or the wall.\n"
                + "Press p to pause the game.\n"
                + "Press q to quit the game.\n"
                + "Enjoy :) ");
        dialog.button("OK", true);
        dialog.show(stage);
    }


}
