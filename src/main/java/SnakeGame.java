import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeGame {
    private Camera camera;
    private SpriteBatch batch;
    private Sprite sprite;
    private SnakeBody snake;
    private STATE currState;
    private SnakeBody.Direction currDir;

    private enum STATE {
        PLAYING, GAME_OVER;
    }


}
