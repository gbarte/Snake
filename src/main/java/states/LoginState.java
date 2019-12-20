package states;

import auth.AuthResponse;
import auth.AuthService;
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

/**
 * Creates login screen.
 */
public class LoginState extends State {
    private Stage stage;
    private Skin skin;
    private Label title;
    private TextField usernameField;
    private TextField passWordField;
    private Texture backGround;

    /**
     * Constructor which creates a new state within the game.
     * E.g. Play/Pause/Menu.
     *
     * @param gameManager which keeps track of the state of the game.
     */
    public LoginState(GameStateManager gameManager) {
        super(gameManager);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin =  new Skin(Gdx.files.internal("assets/quantum-horizon/skin/quantum-horizon-ui.json"));
        initTitle();
        initLogin();
        initSignUp();
        backGround = new Texture("assets/bg.png");
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

    public TextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    public TextField getPassWordField() {
        return passWordField;
    }

    public void setPassWordField(TextField passWordField) {
        this.passWordField = passWordField;
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
        //        signUpButton.setPosition(300, 150);
        signUpButton.setPosition(320, 65);
        signUpButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gameManager.set(new SignUpState(gameManager));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO
                System.out.println("pressed sign up");
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
        //        loginButton.setPosition(300, 200);
        loginButton.setPosition(325, 125);
        BitmapFont bitmapFont = new BitmapFont();
        // Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont, new Color(1, 0, 1, 1));
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

        TextField passWordField = new TextField("",
                new Skin(Gdx.files.internal("assets/cloud-form/skin/cloud-form-ui.json")));
        passWordField.setSize(180, 30);
        passWordField.setPosition(300, 197);
        passWordField.isPasswordMode();
        passWordField.setPasswordCharacter('*');

        loginButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                AuthService service = new AuthService();
                AuthResponse response = service.auth(usernameField.getText(),
                        passWordField.getText());
                service.dizpose();

                if (response == AuthResponse.SUCCESS) {
                    gameManager.set(new MenuState(gameManager));
                } else {
                    // TODO: display failed authentication.
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
        stage.addActor(passWordField);
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
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(backGround, 0, 0, 800, 800);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void dispose() {
        backGround.dispose();
        skin.dispose();
        stage.dispose();
    }
}
