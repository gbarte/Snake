import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import states.SnakeGame;
import services.auth.AuthService;
import services.leaderboard.LeaderboardService;

/**
 * Sets a window for the game and then calls the entities.snake game.
 */
public class Main {
    /**
     * Main method which runs the game with the defined window size and title.
     * @param args as for all main methods.
     */
    public static void main(String[] args) {

        // Dummy data

        AuthService authService = new AuthService();
        authService.register("john", "12345678");

        LeaderboardService leaderboardService = new LeaderboardService();

        if (leaderboardService.retrieveLeaderboard().isEmpty()) {
            leaderboardService.createEntry("test1", 78);
            leaderboardService.createEntry("test2", 33);
            leaderboardService.createEntry("test3", 21);
            leaderboardService.createEntry("test4", 16);
            leaderboardService.createEntry("test3", 1);
        }

        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle(SnakeGame.TITLE);
        cfg.setWindowSizeLimits(SnakeGame.WIDTH, SnakeGame.HEIGHT,
                SnakeGame.WIDTH, SnakeGame.HEIGHT);
        cfg.setWindowedMode(SnakeGame.WIDTH, SnakeGame.HEIGHT);
        new Lwjgl3Application(new SnakeGame(), cfg);
    }
}
