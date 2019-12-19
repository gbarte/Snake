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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuState extends State {
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
        super(gameManager);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal(
                "assets/quantum-horizon/skin/quantum-horizon-ui.json"));
        initTitle();
        initPlayButton();
        initLeaderBoardButton();
        initSignOutButton();
        background = new Texture("assets/bg.png");
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

    private void initSignOutButton() {
        TextButton signOutButton = new TextButton("Sign Out",
                new Skin(Gdx.files.internal(
                        "assets/quantum-horizon/skin/quantum-horizon-ui.json")));
        signOutButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        signOutButton.setPosition(400 - (signOutButton.getWidth() / 2), 100);
        signOutButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gameManager.set(new LoginState(gameManager));
            }

            @Override
            public boolean touchDown(
                    InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pressed leaderboard");
                return true;
            }
        });
        stage.addActor(signOutButton);
    }

    private void initLeaderBoardButton() {
        TextButton leaderBoardButton = new TextButton("Leaderboard", skin);
        leaderBoardButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        leaderBoardButton.setPosition(400 - (leaderBoardButton.getWidth() / 2), 200);
        leaderBoardButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                System.out.println("leaderboard");
                //                gameManager.set(new PlayState(gameManager));
            }

            @Override
            public boolean touchDown(
                    InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pressed leaderboard");
                return true;
            }
        });
        stage.addActor(leaderBoardButton);
    }

    private void initPlayButton() {
        TextButton playButton = new TextButton("Start Game", skin);
        playButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        playButton.setPosition(400 - (playButton.getWidth() / 2), 300);
        playButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                //                gameManager.set(new MenuState(gameManager));
                gameManager.set(new PlayState(gameManager));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                System.out.println("pressed");
                return true;
            }
        });
        stage.addActor(playButton);

    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor((float) 0.61, (float) 0.77, (float) 0.65, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, 800, 800);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        skin.dispose();
        stage.dispose();
    }


}
