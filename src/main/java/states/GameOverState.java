package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import models.Score;
import services.leaderboard.LeaderboardService;
import states.utils.RendererHandler;

/**
 * State of the game over game.
 */
/*Suppressing this warning because we don't need getters and
    setters for UI elements. */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class GameOverState implements IState {
    private GameStateManager stateManager;
    private Stage stage;
    private Skin skin;
    private Texture background;
    private CheckBox checkBox;
    private TextField nicknameField;
    private Score score;
    private Label.LabelStyle labelStyle;

    /**
     * Constructor which creates a new GameOverState within the game.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public GameOverState(GameStateManager gameManager, Score score) {
        this.stateManager = gameManager;
        this.score = score;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal(
                "assets/quantum-horizon/skin/quantum-horizon-ui.json"));
        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(0, 255, 0, 1));
        initTitle();
        initScore();
        initCheckBox();
        initNickname();
        initSaveButton();
        initReturnButton();
        background = new Texture("assets/bg.png");
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

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Label.LabelStyle getLabelStyle() {
        return labelStyle;
    }

    public void setLabelStyle(Label.LabelStyle labelStyle) {
        this.labelStyle = labelStyle;
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
    }

    /**
     * Adds "Game Over" to the screen.
     */
    private void initTitle() {
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
        Label text = new Label("Score:", labelStyle);
        text.setSize(500, 100);
        text.setPosition(270,380);
        text.setFontScale(2);
        Label points = new Label(Integer.toString(score.getValue()), labelStyle);
        points.setSize(600, 120);
        points.setPosition(350,300);
        points.setFontScale(2);
        stage.addActor(points);
        stage.addActor(text);
    }

    /**
     * Lets player choose a nickname to display with his score
     * or user can leave empty if he wants to use his username.
     */
    private void initNickname() {
        BitmapFont bitmapFont = new BitmapFont();
        Label.LabelStyle smallLabelStyle = new Label.LabelStyle(bitmapFont,
                new Color(255,  0, 255, 1));
        Label nicknameLabel = new Label("Enter a nickname", smallLabelStyle);
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
                if (!checkBox.isChecked()) {
                    LeaderboardService ls = new LeaderboardService();
                    ls.createEntry(nicknameField.getText(), score.getValue());
                    scoreSavedConfirmation();
                } else {
                    LeaderboardService ls = new LeaderboardService();
                    ls.createEntry(SnakeGame.getUsername(), score.getValue());
                    scoreSavedConfirmation();
                }
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
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
                if (checkBox.isChecked()) {
                    nicknameField.setDisabled(true);
                } else {
                    nicknameField.setDisabled(false);
                }
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
                stateManager.setState(new MenuState(stateManager));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(returnButton);
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
        skin.dispose();
    }

    /**
     * This dialog box is shown when the user has saved the score.
     */
    private void scoreSavedConfirmation() {
        Skin uiSkin = new Skin(Gdx.files.internal("assets/cloud-form/skin/cloud-form-ui.json"));
        Dialog dialog = new Dialog("Saved score", uiSkin, "dialog") {
            public void result(Object obj) {
                System.out.println("result " + obj);
            }
        };
        dialog.text("Score has successfully been saved.");
        dialog.button("OK", true).addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stateManager.setState(new MenuState(stateManager));
            }
        });
        dialog.show(stage);
    }
}
