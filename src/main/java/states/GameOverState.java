package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import services.auth.AuthResponse;
import services.auth.AuthService;

/**
 * State of the game over game.
 */
public class GameOverState extends State {
    private Stage stage;
    private Skin skin;
    private Texture backGround;
    private CheckBox checkBox;
    private TextField nicknameField;

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
        skin = new Skin(Gdx.files.internal(
                "assets/quantum-horizon/skin/quantum-horizon-ui.json"));
        initTitle();
        initScore();
        initCheckBox();
        initNickname();
        initSaveButton();
        initReturnButton();
        backGround = new Texture("assets/bg.png");
    }

    public Texture getBackGround() {
        return backGround;
    }

    public void setBackGround(Texture backGround) {
        this.backGround = backGround;
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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public TextField getNicknameField() {
        return nicknameField;
    }

    public void setNicknameField(TextField nicknameField) {
        this.nicknameField = nicknameField;
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
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(backGround, 0, 0, 800, 800);
        stage.getBatch().end();
        stage.draw();
    }

    /**
     * Adds "Game Over" to the screen.
     */
    private void initTitle() {
        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(255, 0, 255, 1));
        Label gameOverTitle = new Label("GAME OVER", labelStyle);
        gameOverTitle.setSize(600, 120);
        gameOverTitle.setPosition(200,620);
        gameOverTitle.setFontScale(2);
        stage.addActor(gameOverTitle);
    }

    /**
     * Adds score to the screen.
     */
    private void initScore() {
        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(0, 255, 0, 1));
        Label text = new Label("Score:", labelStyle);
        text.setSize(500, 100);
        text.setPosition(270,380);
        text.setFontScale(2);
        Label score = new Label("100", labelStyle);
        score.setSize(600, 120);
        score.setPosition(350,300);
        score.setFontScale(2);
        stage.addActor(score);
        stage.addActor(text);
    }

    /**
     * Lets player choose a nickname to display with his score
     * or user can leave empty if he wants to use his username.
     */
    private void initNickname() {
        BitmapFont bitmapFont = new BitmapFont();
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(255,  0, 255, 1));
        Label nicknameLabel = new Label("Enter a nickname", labelStyle);
        nicknameLabel.setPosition(340, 279);

        nicknameField = new TextField("",
                new Skin(Gdx.files.internal("assets/cloud-form/skin/cloud-form-ui.json")));
        nicknameField.setSize(200, 30);
        nicknameField.setPosition(300, 247);
        stage.addActor(nicknameLabel);
        stage.addActor(nicknameField);
    }

    /**
     * Creates the save button.
     */
    private void initSaveButton() {
        TextButton saveButton = new TextButton("Save score", skin);
        saveButton.setPosition(300, 145);saveButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (checkBox.isChecked()) {
                    //TODO nickname
                    System.out.println("pass username");
                    scoreSavedConfirmation();
                } else {
                    //TODO anonymous
                    System.out.println("pass nickname");
                    scoreSavedConfirmation();
                }
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("chose nickname");
                return true;
            }
        });
        stage.addActor(saveButton);
    }

    /**
     * Creates a checkbox.
     */
    private void initCheckBox() {
        checkBox = new CheckBox("Use username", skin);
        checkBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.graphics.setContinuousRendering(checkBox.isChecked());
                nicknameField.setDisabled(true);
            }
        });
        checkBox.setPosition(300, 212);
        checkBox.scaleBy(0.5f);
        stage.addActor(checkBox);
    }

    /**
     * Button which leads to the menu.
     */
    private void initReturnButton() {
        TextButton returnButton = new TextButton("Return to menu", skin);
        returnButton.setPosition(275, 75);
        returnButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gameManager.set(new MenuState(gameManager));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("chose nickname");
                return true;
            }
        });
        stage.addActor(returnButton);
    }

    @Override
    public void dispose() {
        backGround.dispose();
        stage.dispose();
        skin.dispose();

    }

    /**
     * This dialog box is shown when the user has saved the score.
     */
    public void scoreSavedConfirmation() {
        Skin uiSkin = new Skin(Gdx.files.internal("assets/cloud-form/skin/cloud-form-ui.json"));
        Dialog dialog = new Dialog("Saved score", uiSkin, "dialog") {
            public void result(Object obj) {
                System.out.println("result " + obj);
            }
        };
        dialog.text("Score has succesfully been saved.");
        dialog.button("OK", true).addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameManager.set(new MenuState(gameManager));
            }
        });
        dialog.show(stage);
    }
}
