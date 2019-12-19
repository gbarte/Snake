package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * LeaderBoardState class
 * Shows a leaderboard of all players playing the gaming.
 */
public class LeaderboardState extends State {
    private Stage stage;
    private Skin skin;
    private Label title;
    private Texture backGround;

    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public LeaderboardState(GameStateManager gameManager) {
        super(gameManager);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin =  new Skin(Gdx.files.internal("assets/quantum-horizon/skin/quantum-horizon-ui.json"));
        backGround = new Texture("assets/login_screen2.png");
        initTitle();
        initRank();
    }

    /**
     * Sets the leaderboard title in the middle of the screen.
     */
    public void initTitle() {
        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(0, (float) 2.55, 0, 1));
        title = new Label("Leaderboard", labelStyle);
        title.setSize(400, 100);
        title.setPosition(160,600);
        title.setFontScale(2);
        // title.setAlignment(Align.center);
        stage.addActor(title);
    }

    /**
     * Creates a numbering on the left side of the screen and
     * adds player according to their ranking.
     */
    public void initRank() {
        setNumber(500, "1.");
        setNumber(450, "2.");
        setNumber(400, "3.");
        setNumber(350, "4.");
        setNumber(300, "5.");
        setNumber(250, "6.");
        setNumber(200, "7.");
        setNumber(150, "8.");
        setNumber(100, "9.");
        setNumber(50, "10.");
        setPlayerRank(500, "first");
        setPlayerRank(450, "second");
        setPlayerRank(400, "third");
        setPlayerRank(350, "fourth");
        setPlayerRank(300, "fifth");
        setPlayerRank(250, "sixth");
        setPlayerRank(200, "seventh");
        setPlayerRank(150, "eigth");
        setPlayerRank(100, "nint");
        setPlayerRank(50, "ten");
    }

    /**
     * Puts a given number on the stage at given position y.
     * @param y y coordinate where the number will be placed.
     * @param number number which will be shown.
     */
    public void setNumber(int y, String number) {
        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        // Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont, new Color(1, 0, 1, 1));
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(0, (float) 2.55, 0, 1));
        Label one = new Label(number, labelStyle);
        one.setSize(100, 100);
        one.setPosition(100, y);
        stage.addActor(one);
    }

    /**
     * Puts given player name on the rank position.
     * @param y y coordinate where the player will be placed.
     * @param player player which will be shown.
     */
    public void setPlayerRank(int y, String player) {
        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        // Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont, new Color(1, 0, 1, 1));
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(0, (float) 2.55, 0, 1));
        Label one = new Label(player, labelStyle);
        one.setSize(100, 100);
        one.setPosition(150, y);
        stage.addActor(one);
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
        Gdx.gl.glClearColor((float) 0.61, (float) 0.77, (float) 0.65, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(backGround, 0, 0, 800, 800);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void dispose() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameManager.update(Gdx.graphics.getDeltaTime());
        // gameManager.render(batch);
    }
}
