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
import states.utils.RendererHandler;
import utils.Sizes;

/**
 * LeaderBoardState class
 * Shows a leaderboard of all players playing the gaming.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class LeaderboardState implements IState {
    private GameStateManager stateManager;
    private Stage stage;
    private Skin skin;
    private Label title;
    private Texture background;
    private BitmapFont bitmapFont;
    private Label.LabelStyle labelStyle;

    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public LeaderboardState(GameStateManager gameManager) {
        this.stateManager = gameManager;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin =  new Skin(Gdx.files.internal("assets/quantum-horizon/skin/quantum-horizon-ui.json"));
        background = new Texture("assets/three.png");
        bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(0, (float) 2.55, 0, 1));
        initReturn();
        initTitle();
        initBoard();
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

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }

    public Texture getBackground() {
        return background;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    public BitmapFont getBitmapFont() {
        return bitmapFont;
    }

    public void setBitmapFont(BitmapFont bitmapFont) {
        this.bitmapFont = bitmapFont;
    }

    public Label.LabelStyle getLabelStyle() {
        return labelStyle;
    }

    public void setLabelStyle(Label.LabelStyle labelStyle) {
        this.labelStyle = labelStyle;
    }

    /**
     * Creates return button.
     */
    private void initReturn() {
        TextButton returnButton = new TextButton("return", skin);
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
        title = new Label("Leaderboard", labelStyle);
        title.setSize(400, 100);
        title.setPosition(160,650);
        title.setFontScale(2);
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
        labelStyle = new Label.LabelStyle(bitmapFont,
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
        labelStyle = new Label.LabelStyle(bitmapFont,
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
        labelStyle = new Label.LabelStyle(bitmapFont,
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
        RendererHandler.render(batch, stage, background);
        Gdx.gl.glClearColor((float) 0.61, (float) 0.77, (float) 0.65, 1);
    }

    @Override
    public void dispose() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateManager.update(Gdx.graphics.getDeltaTime());
    }
}
