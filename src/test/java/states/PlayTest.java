package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import game.SnakeGame;
import objects.base.Apple;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import snake.SnakeBody;

import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(GdxTestRunner.class)
public class PlayTest {
    private transient SnakeBody snake;
    private transient State state;
    private transient Apple apple;

    @Test
    void test() {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        ApplicationStart app = new ApplicationStart();
        new Lwjgl3Application(app, cfg);

        state = app.getGameManager().getStates().peek();

        PlayState play = (PlayState) state;
        snake = play.getSnake();
        apple = play.getApple();

        snake.setHeadCoord(apple.getCoordinate());
        play.isAppleEaten();

        Apple apple2 = play.getApple();

        assertFalse(apple2.getCoordinate().equals(apple.getCoordinate()));
    }

}

class ApplicationStart extends SnakeGame {

    public void create() {
        super.create();
        GameStateManager stateManager = new GameStateManager();
        PlayState state = new PlayState(stateManager);
        stateManager.push(state);
        super.setGameManager(stateManager);
        Gdx.app.exit();
    }

}

