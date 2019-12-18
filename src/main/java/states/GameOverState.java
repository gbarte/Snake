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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameOverState extends State {
    private Stage stage;
    //    private Skin skin;
    private Label title;
    private Texture backGround;
    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public GameOverState(GameStateManager gameManager) {
        super(gameManager);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
//        skin =  new Skin();
        initTitle();
        backGround = new Texture("assets/glitchy.png");
    }

    /**
     * Sets title of Game Over screen.
     */
    private void initTitle() {
        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(0,0, 0, 1));
        title = new Label("Game Over", labelStyle);
        title.setSize(600, 120);
        title.setPosition(10,400);
        title.setFontScale(3);
        title.setAlignment(Align.center);
        stage.addActor(title);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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
        backGround.dispose();
//        skin.dispose();
        stage.dispose();
    }
}
