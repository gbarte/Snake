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
import java.util.List;
import services.LeaderboardEntry;
import services.leaderboard.LeaderboardService;
import utils.Sizes;

/**
 * LeaderboardState class
 * Shows a leaderboard of all players playing the game.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class LeaderboardState implements IState {
    private GameStateManager stateManager;
    private Stage stage;
    private Skin skin;
    private Label title;
    private Texture backGround;
    private BitmapFont bitmapFont;

    /**
     * Constructor which creates a new LeaderboardsState within the game.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public LeaderboardState(GameStateManager gameManager) {
        this.stateManager = gameManager;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin =  new Skin(Gdx.files.internal("assets/quantum-horizon/skin/quantum-horizon-ui.json"));
        backGround = new Texture("assets/three.png");
        bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        initReturn();
        initTitle();
        initBoard();
    }

    /**
     * Creates return button.
     */
    private void initReturn() {
        TextButton returnButton = new TextButton("return", skin);
        //        signUpButton.setPosition(300, 150);
        returnButton.setPosition(350, 50);
        returnButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stateManager.setState(new MenuState(stateManager));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO
                System.out.println("pressed return button");
                return true;
            }
        });
        stage.addActor(returnButton);
    }

    /**
     * Sets the leaderboard title in the middle of the screen.
     */
    private void initTitle() {
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(0, (float) 2.55, 0, 1));
        title = new Label("Leaderboard", labelStyle);
        title.setSize(400, 100);
        title.setPosition(160,650);
        title.setFontScale(2);
        // title.setAlignment(Align.center);
        stage.addActor(title);
    }

    /**
     * Adds score to the player..
     */
    private void initBoard() {

        LeaderboardService service = new LeaderboardService();
        List<LeaderboardEntry> entries = service.retrieveLeaderboard();

        for (int i = 0; i < entries.size(); i++) {
            setPlayerRank(550 - 50 * i, entries.get(i).getNickname());
            setScore(550 - 50 * i, entries.get(i).getScore());
            setNumber(550 - 50 * i, i + 1 + ".");
        }

    }

    /**
     * Puts a given number on the stage at given position y.
     * @param y y coordinate where the number will be placed.
     * @param number number which will be shown.
     */
    private void setNumber(int y, String number) {
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(0, (float) 2.55, 0, 1));
        Label one = new Label(number, labelStyle);
        one.setSize(100, 100);
        one.setPosition(200, y);
        stage.addActor(one);
    }

    /**
     * Puts given player name on the rank position.
     * @param y y coordinate where the player will be placed.
     * @param player player which will be shown.
     */
    private void setPlayerRank(int y, String player) {
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(0, (float) 2.55, 0, 1));
        Label one = new Label(player, labelStyle);
        one.setSize(100, 100);
        one.setPosition(250, y);
        stage.addActor(one);
    }

    /**
     * Puts given player score on the score position.
     * @param y y coordinate where the score will be placed.
     * @param score score which will be shown.
     */
    private void setScore(int y, int score) {
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(0, (float) 2.55, 0, 1));
        Label one = new Label(Integer.toString(score), labelStyle);
        one.setSize(100, 100);
        one.setPosition(600, y);
        stage.addActor(one);
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
        stage.getBatch().draw(backGround, 0, 0, Sizes.MIN_WIDTH_WINDOW, Sizes.MIN_HEIGHT_WINDOW);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void dispose() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateManager.update(Gdx.graphics.getDeltaTime());
    }
}
