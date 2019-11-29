package states;

import com.badlogic.gdx.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class GameStateManagerTest {
    private GameStateManager stateManager;

    @BeforeEach
    void setUp() {
        stateManager = new GameStateManager();
    }

    @Test
    void push() {
        MenuState menu = new MenuState(stateManager);
        stateManager.push(menu);

        assertEquals(stateManager.getStates().pop(), menu);
    }

    @Test
    void pop() {
        MenuState menu = new MenuState(stateManager);
        PlayState play = Mockito.mock(PlayState.class);
        Stack<State> states = new Stack<>();
        states.push(menu);
        states.push(play);

        stateManager.setStates(states);

        assertEquals(stateManager.getStates().size(), 2);
    }

    @Test
    void set() {
        MenuState menu = new MenuState(stateManager);
        PlayState play = Mockito.mock(PlayState.class);
        Stack<State> states = new Stack<>();
        states.push(menu);
        states.push(play);
        stateManager.setStates(states);

        stateManager.set(menu);

        assertEquals(stateManager.getStates().pop(), menu);
    }

    @Test
    void update() {
    }

    @Test
    void render() {
    }

    @Test
    void getStates() {
        MenuState menu = new MenuState(stateManager);
        PlayState play = Mockito.mock(PlayState.class);
        Stack<State> states = new Stack<>();
        states.push(menu);
        states.push(play);

        stateManager.setStates(states);

        assertEquals(stateManager.getStates(), states);
    }

    @Test
    void setStates() {
        MenuState menu = new MenuState(stateManager);
        PlayState play = Mockito.mock(PlayState.class);
        Stack<State> states = new Stack<>();
        states.push(menu);
        states.push(play);

        stateManager.setStates(states);
        assertEquals(stateManager.getStates().size(), states.size());
    }
}