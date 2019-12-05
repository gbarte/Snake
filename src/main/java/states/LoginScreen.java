package states;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.w3c.dom.Text;

public class LoginScreen extends ApplicationAdapter {
    private Stage stage;
    private Skin skin;
    private Button loginButton;
    private Button signUpButton;
    private TextField usernameField;
    private TextField passWordField;
    private Texture backGround;

    public void create() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin =  new Skin(Gdx.files.internal("assets/neon/skin/neon-ui.json"));
        loginButton = new TextButton("Login", skin);
        loginButton.setPosition(300, 200);
        loginButton.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                loginButtonClicked();
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pressed");
                return true;
            }
        });

        TextureRegionDrawable signUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("assets/sign_up_button2.png"))));
        TextureRegionDrawable signDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("assets/sign_up_button.png"))));
        signUpButton = new ImageButton(signUp, signDown);
//        signUpButton = new TextButton("Sign Up", new Skin(Gdx.files.internal("assets/neon/skin/neon-ui.json")));
        signUpButton.setPosition(300, 150);
        signUpButton.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("press");
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO
//                System.out.println("pressed");
                return true;
            }
        });
        stage.addActor(loginButton);
        stage.addActor(signUpButton);

        usernameField = new TextField("", skin);
        usernameField.setSize(150, 30);
        usernameField.setPosition(100, 200);
        passWordField = new TextField("", new Skin(Gdx.files.internal("assets/cloud-form/skin/cloud-form-ui.json")));
        passWordField.setSize(150, 30);
        passWordField.setPosition(100, 150);
        passWordField.isPasswordMode();
        passWordField.setPasswordCharacter('*');
        stage.addActor(usernameField);
        stage.addActor(passWordField);
        backGround = new Texture("assets/login_screen2.png");
    }

    private void loginButtonClicked() {
        // check credentials
        // change to gamescreen
    }

    @Override
    public void render() {
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
