package states;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snake.SnakeBody;

import static org.junit.jupiter.api.Assertions.*;

class PlayStateTest {
    GameStateManager stateManager;

    @BeforeEach
    void setUp() {
        stateManager = new GameStateManager();
    }

}