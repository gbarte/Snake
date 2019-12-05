package states;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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

public class LoginState extends ApplicationAdapter {
    Stage stage;
//    private static final int BUTTON_WIDTH = 100;
//    private static final int BUTTON_HEIGHT = 30;
    private ImageButton loginButton;
    private ImageButton signUpButton;
//    private Texture loginButtonActive;
//    private Texture signUpButtonActive;
//    private TextField usernameField;
//    private TextField passWordField;
//    private Texture snake;
//    private Texture backGround;
//    private SpriteBatch batch;

//    public LoginState(GameStateManager gameManager) {
//        super(gameManager);
//        loginButton = new Texture("login_button");
//        signUpButton = new Texture("sign_up_button");
//    }
//
//    @Override
//    public void handleInput() {
//
//    }
//
//    @Override
//    public void update(float dt) {
//
//    }

    public void create() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        TextureRegionDrawable loginUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("assets/login_button2.png"))));
        TextureRegionDrawable loginDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("assets/login_button.png"))));
        loginButton = new ImageButton(loginUp, loginDown);
        loginButton.setPosition(650, 350);
        loginButton.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("press");
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
        signUpButton.setPosition(650, 300);
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
//        batch = new SpriteBatch();
//        Skin skin = new Skin(Gdx.files.internal("assets/neon/skin/neon-ui.json"));
//        loginButton = new Texture("assets/login_button2.png");
//        loginButtonActive = new Texture("assets/login_button.png");
//        signUpButton = new Texture("assets/sign_up_button2.png");
//        signUpButtonActive = new Texture("assets/sign_up_button.png");
//        usernameField = new TextField("username", skin);
//        passWordField = new TextField("password", skin);
//        snake = new Texture("assets/snake-png-vector.png");
//        backGround = new Texture("assets/login_screen2.png");
//        backgroundSprite = new Sprite(backGround);
    }

    @Override
//    public void render(SpriteBatch batch) {
    public void render() {
        Gdx.gl.glClearColor((float) 0.61, (float) 0.77, (float) 0.65, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
//        batch.begin();
////        backgroundSprite.draw(batch);
//
//        batch.draw(backGround, 0, 0, 800, 800);
//        batch.draw(snake, 0, 0, 350, 350);
//
//        batch.draw(loginButton,  650, 350);
//        batch.draw(signUpButton, 650, 300);
//
//        inputHandler();
////        System.out.println(yCoordinate);
//
//
//        batch.end();
    }

//    public void inputHandler() {
//        float xCoordinate = Gdx.input.getX();
//        float yCoordinate = Gdx.input.getY();
//        if (xCoordinate > 650 && xCoordinate < 650 + BUTTON_WIDTH  && yCoordinate < 350 + BUTTON_HEIGHT && yCoordinate > 350) {
//            batch.draw(loginButtonActive, 650, 350);
//        } else {
//            batch.draw(loginButton, 650, 350);
//        }
//        if (xCoordinate > 650 && xCoordinate < 650 + BUTTON_WIDTH  && yCoordinate < 300 + BUTTON_HEIGHT && yCoordinate > 300) {
//            batch.draw(signUpButtonActive, 650, 300);
//        } else {
//            batch.draw(signUpButton, 650, 300);
//        }
//    }

    @Override
    public void dispose() {

    }
}
