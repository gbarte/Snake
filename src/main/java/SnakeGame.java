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

    public void render(float delta) {
        switch(currState) {
            case PLAYING: {
                //checks for updated direction after a keypress
                queryInput();
                //
                snake.moveSnake(currDir);
                checkOutOfMap(); // maybe move this to snakebody???
            }
            break;
            case GAME_OVER: {
                //present gameover screen
            }
            break;
        }
//        clearScreen();
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        batch.begin();
        sprite.draw(batch);
        snake.renderSnake(batch);
        batch.end();
    }

}
