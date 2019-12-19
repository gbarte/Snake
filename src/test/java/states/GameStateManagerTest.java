package states;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.badlogic.gdx.Game;
import java.util.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GameStateManagerTest {
    transient GameStateManager stateManager;

    @BeforeEach
    void setUp() {
        stateManager = new GameStateManager();
    }

    @Test
    void push() {
        MenuState menu = Mockito.mock(MenuState.class);
        stateManager.push(menu);

        assertEquals(stateManager.getStates().pop(), menu);
    }

    @Test
    void pop() {
        MenuState menu = Mockito.mock(MenuState.class);
        PlayState play = Mockito.mock(PlayState.class);
        Stack<State> states = new Stack<>();
        states.push(menu);
        states.push(play);

        stateManager.setStates(states);

        assertEquals(stateManager.getStates().size(), 2);
    }

    @Test
    void set() {
        MenuState menu = Mockito.mock(MenuState.class);
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
        MenuState menu = Mockito.mock(MenuState.class);
        PlayState play = Mockito.mock(PlayState.class);
        Stack<State> states = new Stack<>();
        states.push(menu);
        states.push(play);
        stateManager.setStates(states);
        stateManager.update(10);

        Mockito.verify(play).update(10);
    }

    @Test
    void getStates() {
        MenuState menu = Mockito.mock(MenuState.class);
        PlayState play = Mockito.mock(PlayState.class);
        Stack<State> states = new Stack<>();
        states.push(menu);
        states.push(play);

        stateManager.setStates(states);

        assertEquals(stateManager.getStates(), states);
    }

    @Test
    void setStates() {
        MenuState menu = Mockito.mock(MenuState.class);
        PlayState play = Mockito.mock(PlayState.class);
        Stack<State> states = new Stack<>();
        states.push(menu);
        states.push(play);

        stateManager.setStates(states);
        assertEquals(stateManager.getStates().size(), states.size());
    }
}