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
import utils.Sizes;

/**
 * Creates login screen.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class LoginState implements IState {
    private GameStateManager stateManager;
    private Stage stage;
    private Skin skin;
    private Label title;
    private Texture backGround;

    /**
     * Constructor which creates a new LoginState within the game.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public LoginState(GameStateManager gameManager) {
        this.stateManager = gameManager;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin =  new Skin(Gdx.files.internal("assets/quantum-horizon/skin/quantum-horizon-ui.json"));
        initTitle();
        initLogin();
        initSignUp();
        backGround = new Texture("assets/bg.png");
    }

    /**
     * Sets title of login screen.
     */
    private void initTitle() {
        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(0, 255, 0, 1));
        title = new Label("Lil' Snake", labelStyle);
        title.setSize(600, 120);
        title.setPosition(100,550);
        title.setFontScale(3);
        title.setAlignment(Align.center);
        stage.addActor(title);
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
     * Sets username and password textfield,
     * Login and Sign Up buttons.
     */
    private void initLogin() {
        TextButton loginButton = new TextButton("Login", skin);
        loginButton.setPosition(325, 125);
        BitmapFont bitmapFont = new BitmapFont();
        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont,
                new Color(255,  0, 255, 1));
        Label usernameLabel = new Label("Username", labelStyle);
        usernameLabel.setPosition(350, 279);

        TextField usernameField = new TextField("",
                new Skin(Gdx.files.internal("assets/cloud-form/skin/cloud-form-ui.json")));
        usernameField.setSize(180, 30);
        usernameField.setPosition(300, 247);

        Label passwordLabel = new Label("Password", labelStyle);
        passwordLabel.setPosition(350, 229);

        TextField passwordField = new TextField("",
                new Skin(Gdx.files.internal("assets/cloud-form/skin/cloud-form-ui.json")));
        passwordField.setSize(180, 30);
        passwordField.setPosition(300, 197);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        loginButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                AuthService service = new AuthService();
                AuthResponse response = service.auth(usernameField.getText(),
                        passwordField.getText());


                if (response == AuthResponse.SUCCESS) {
                    SnakeGame.username = usernameField.getText();
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
        stage.addActor(usernameLabel);
        stage.addActor(passwordLabel);
        stage.addActor(usernameField);
        stage.addActor(passwordField);
    }

    /**
     * This dialog box is shown when the authentication fails.
     */
    public void failedAuthenticationDialog() {
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
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(backGround, 0, 0, Sizes.MIN_WIDTH_WINDOW, Sizes.MIN_HEIGHT_WINDOW);
        stage.getBatch().end();
        stage.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        backGround.dispose();
        skin.dispose();
        stage.dispose();
    }
}
