package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LoginState extends State {
    private Stage stage;
    private Skin skin;
    private Label title;
    private Button loginButton;
    private Button signUpButton;
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
        backGround = new Texture("assets/login_screen2.png");
    }

    private void initTitle() {
//        BitmapFont bitmapFont = new BitmapFont();
//        Label.LabelStyle labelStyle = new Label.LabelStyle(bitmapFont, Color.GREEN);
//        title = new Label("Schitzosnake", labelStyle);
        title = new Label("Shitzosnake", skin);
        title.setSize(600, 120);
        title.setPosition(100 ,450);
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

    public Button getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }

    public Button getSignUpButton() {
        return signUpButton;
    }

    public void setSignUpButton(Button signUpButton) {
        this.signUpButton = signUpButton;
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

    public Texture getBackGround() {
        return backGround;
    }

    public void setBackGround(Texture backGround) {
        this.backGround = backGround;
    }

    private void initSignUp() {
        TextureRegionDrawable signUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("assets/sign_up_button2.png"))));
        TextureRegionDrawable signDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("assets/sign_up_button.png"))));
        signUpButton = new ImageButton(signUp, signDown);
//        signUpButton = new TextButton("Sign Up", new Skin(Gdx.files.internal("assets/neon/skin/neon-ui.json")));
        signUpButton.setPosition(300, 150);
        signUpButton.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gameManager.set(new SignUpState(gameManager));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO
                System.out.println("pressed");
                return true;
            }
        });
        passWordField = new TextField("", new Skin(Gdx.files.internal("assets/cloud-form/skin/cloud-form-ui.json")));
        passWordField.setSize(150, 30);
        passWordField.setPosition(100, 150);
        passWordField.isPasswordMode();
        passWordField.setPasswordCharacter('*');
        stage.addActor(signUpButton);
        stage.addActor(passWordField);

    }

    private void initLogin() {
        loginButton = new TextButton("Login", skin);
        loginButton.setPosition(300, 200);
        loginButton.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                gameManager.set(new MenuState(gameManager));
                gameManager.set(new PlayState(gameManager));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pressed");
                return true;
            }
        });
        usernameField = new TextField("", skin);
        usernameField.setSize(150, 30);
        usernameField.setPosition(100, 200);
        stage.addActor(loginButton);
        stage.addActor(usernameField);
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

    }
}
