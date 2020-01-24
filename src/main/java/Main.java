import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import states.SnakeGame;
import utils.Sizes;

/**
 * Sets a window for the game and then calls the snake game.
 */
public class Main {
    /**
     * Main method which runs the game with the defined window size and title.
     * @param args as for all main methods.
     */
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle(SnakeGame.TITLE);
        cfg.setWindowSizeLimits(Sizes.MIN_WIDTH_WINDOW, Sizes.MIN_HEIGHT_WINDOW,
                Sizes.MAX_WIDTH_WINDOW, Sizes.MAX_HEIGHT_WINDOW);
        cfg.setWindowedMode(Sizes.WINDOWED_MODE_WIDTH, Sizes.WINDOWED_MODE_HEIGHT);
        new Lwjgl3Application(new SnakeGame(), cfg);
    }
}
