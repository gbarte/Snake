import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import utils.Sizes;

public class Main {
    /**
     * Main method which runs the game with the defined window size and title.
     * @param args as for all main methods.
     */
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();

        cfg.setTitle("snek");
        cfg.setWindowSizeLimits(Sizes.MIN_WIDTH_WINDOW, Sizes.MIN_HEIGHT_WINDOW,
                Sizes.MAX_WIDTH_WINDOW, Sizes.MAX_HEIGHT_WINDOW);
        cfg.setWindowedMode(Sizes.WINDOWED_MODE_WIDTH, Sizes.WINDOWED_MODE_HEIGHT);

        new Lwjgl3Application(new GameMain(), cfg);
    }
}
