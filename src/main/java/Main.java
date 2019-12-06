import auth.AuthService;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import game.SnakeGame;

public class Main {
    /**
     * Main method which runs the game with the defined window size and title.
     * @param args as for all main methods.
     */
    public static void main(String[] args) {

        AuthService service = new AuthService();
        service.register("john", "12345678");
        service.dizpose();

        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle(SnakeGame.TITLE);
        cfg.setWindowSizeLimits(SnakeGame.WIDTH, SnakeGame.HEIGHT,
                SnakeGame.WIDTH, SnakeGame.HEIGHT);
        cfg.setWindowedMode(SnakeGame.WIDTH, SnakeGame.HEIGHT);
        new Lwjgl3Application(new SnakeGame(), cfg);
    }
}
