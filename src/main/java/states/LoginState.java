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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import services.auth.AuthResponse;
import services.auth.AuthService;
import states.utils.RendererHandler;
import utils.Sizes;

/**
 * Creates login screen.
 */
/*Suppressing this warning because we don't need getters and
    setters for UI elements. */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class LoginState implements IState {
    private GameStateManager stateManager;
    private Stage stage;
    private Skin skin;
    private Label title;
    private TextField usernameField;
    private TextField passwordField;
    private Texture background;
    private BitmapFont bitmapFont;
    private Label.LabelStyle labelStyle;

    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public LoginState(GameStateManager gameManager) {
        this.stateManager = gameManager;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin =  new Skin(Gdx.files.internal("assets/quantum-horizon/skin/quantum-horizon-ui.json"));
        bitmapFont = new BitmapFont();
        labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(255,  0, 255, 1));
        title = RendererHandler.initTitle(stage);
        initLogin();
        initPasswordField();
        initButtons();
        initSignUp();
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

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(TextField passWordField) {
        this.passwordField = passWordField;
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
     * Sets up Sign Up button.
     */
    private void initSignUp() {
        TextButton signUpButton = new TextButton("Sign up", skin);
        signUpButton.setPosition(320, 65);
        signUpButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stateManager.setState(new SignUpState(stateManager));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(signUpButton);
    }

    /**
     * Sets username textfield,
     * Login and Sign Up buttons.
     */
    private void initLogin() {
        Label usernameLabel = new Label("Username", labelStyle);
        usernameLabel.setPosition(350, 279);
        usernameField = new TextField("",
                new Skin(Gdx.files.internal("assets/cloud-form/skin/cloud-form-ui.json")));
        usernameField.setSize(180, 30);
        usernameField.setPosition(300, 247);
        stage.addActor(usernameLabel);
        stage.addActor(usernameField);
    }

    /**
     * Login and Sign Up buttons.
     */
    private void initButtons() {
        TextButton loginButton = new TextButton("Login", skin);
        loginButton.setPosition(325, 125);
        loginButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                AuthService service = new AuthService();
                AuthResponse response = service.auth(usernameField.getText(),
                        passwordField.getText());

                if (response == AuthResponse.SUCCESS) {
                    SnakeGame.setUsername(usernameField.getText());
                    stateManager.setState(new MenuState(stateManager));
                } else {
                    failedAuthenticationDialog();
                }
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pressed sign in");
                return true;
            }
        });
        stage.addActor(loginButton);
    }

    /**
     * Sets the password textfield.
     */
    private void initPasswordField() {
        Label passwordLabel = new Label("Password", labelStyle);
        passwordLabel.setPosition(350, 229);

        passwordField = new TextField("",
                new Skin(Gdx.files.internal("assets/cloud-form/skin/cloud-form-ui.json")));
        passwordField.setSize(180, 30);
        passwordField.setPosition(300, 197);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        stage.addActor(passwordLabel);
        stage.addActor(usernameField);
        stage.addActor(passwordField);
    }

    /**
     * This dialog box is shown when the authentication fails.
     */
    private void failedAuthenticationDialog() {
        Skin uiSkin = new Skin(Gdx.files.internal("assets/cloud-form/skin/cloud-form-ui.json"));
        Dialog dialog = new Dialog("Username taken", uiSkin, "dialog") {
            public void result(Object obj) {
                System.out.println("result " + obj);
            }
        };
        dialog.text("Incorrect username or password, please try again.");
        dialog.button("OK", true); //sends "true" as the result
        dialog.show(stage);
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

    @Override
    public void dispose() {
        background.dispose();
        skin.dispose();
        stage.dispose();
    }
}
