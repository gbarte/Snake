package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import services.auth.AuthService;
import services.auth.RegistrationResponse;
import states.utils.RendererHandler;

/**
 * Creates sign up screen.
 */
/*Suppressing this warning because we don't need getters and
    setters for UI elements. */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class SignUpState implements IState {
    private GameStateManager stateManager;
    private Stage stage;
    private Skin skin;
    private Texture background;
    private TextField usernameField;
    private TextField passwordField;
    private Skin cloudSkin;
    private Label.LabelStyle labelStyle;

    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public SignUpState(GameStateManager gameManager) {
        this.stateManager = gameManager;
        stage = new Stage(new ScreenViewport());
        background = new Texture("assets/bg.png");
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("assets/quantum-horizon/skin/quantum-horizon-ui.json"));
        cloudSkin = new Skin(Gdx.files.internal("assets/cloud-form/skin/cloud-form-ui.json"));
        BitmapFont bitmapFont = new BitmapFont();
        labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(255,  0, 255, 1));
        RendererHandler.initTitle(stage);
        initSignUpUsername();
        initSignUpPassword();
        initSignUpButton();
        initReturn();

    }

    /**
     * Sets username textfield.
     */
    private void initSignUpUsername() {
        Label usernameLabel = new Label("Enter a username", labelStyle);

        usernameLabel.setPosition(340, 279);
        usernameField = new TextField("", cloudSkin);
        usernameField.setSize(180, 30);
        usernameField.setPosition(300, 247);

        stage.addActor(usernameLabel);
        stage.addActor(usernameField);
    }

    /**
     * Sets password textfield.
     */
    private void initSignUpPassword() {
        Label passwordLabel = new Label("Enter a password", labelStyle);
        passwordLabel.setPosition(340, 229);
        passwordField = new TextField("", cloudSkin);
        passwordField.setSize(180, 30);
        passwordField.setPosition(300, 197);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        stage.addActor(passwordLabel);
        stage.addActor(passwordField);
    }

    /**
     * Creates the Sign Up buttons.
     */
    private void initSignUpButton() {
        TextButton signUpButton = new TextButton("Sign up", skin);
        signUpButton.setPosition(320, 125);
        signUpButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                AuthService authService = new AuthService();
                RegistrationResponse response
                        = authService.register(usernameField.getText(), passwordField.getText());
                switch (response) {
                    case OCCUPIED_NAME:
                        usernameTakenDialog();
                        break;
                    case SHORT_PASSWORD:
                        passwordTooShortDialog();
                        break;
                    case SUCCESS:
                        stateManager.setState(new LoginState(stateManager));
                        break;
                    default:
                }
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pressed sign up");
                return true;
            }
        });
        stage.addActor(signUpButton);
    }

    /**
     * Creates a return button for the player to return to login screen.
     */
    private void initReturn() {
        TextButton returnButton = new TextButton("return", skin);
        returnButton.setPosition(320, 65);
        returnButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stateManager.setState(new LoginState(stateManager));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(returnButton);
    }

    /**
     * This dialog box is shown when the username is has already been taken.
     */
    private void usernameTakenDialog() {
        Dialog dialog = new Dialog("Username taken", cloudSkin, "dialog") {
            public void result(Object obj) {
                System.out.println("result " + obj);
            }
        };
        dialog.text("This username has already been taken, try a new one.");
        dialog.button("OK", true);
        dialog.show(stage);
    }

    /**
     * This dialog box is shown when the password has less than 8 characters.
     */
    private void passwordTooShortDialog() {
        Dialog dialog = new Dialog("Password too short", cloudSkin, "dialog") {
            public void result(Object obj) {
                System.out.println("result " + obj);
            }
        };
        dialog.text("Password should be longer than or equal to 8 characters.");
        dialog.button("OK", true);
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
