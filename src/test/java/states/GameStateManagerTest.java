package states;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

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
        MenuState menu = mock(MenuState.class);
        stateManager.pushState(menu);

        assertEquals(stateManager.getStates().pop(), menu);
    }

    @Test
    void pop() {
        MenuState menu = mock(MenuState.class);
        PlayState play = mock(PlayState.class);
        Stack<IState> states = new Stack<>();
        states.push(menu);
        states.push(play);

        stateManager.setStates(states);

        assertEquals(stateManager.getStates().size(), 2);
    }

    @Test
    void set() {
        MenuState menu = mock(MenuState.class);
        PlayState play = mock(PlayState.class);
        Stack<IState> states = new Stack<>();
        states.push(menu);
        states.push(play);
        stateManager.setStates(states);

        stateManager.setState(menu);

        assertEquals(stateManager.getStates().pop(), menu);
    }

    @Test
    void update() {
        MenuState menu = mock(MenuState.class);
        PlayState play = mock(PlayState.class);
        Stack<IState> states = new Stack<>();
        states.push(menu);
        states.push(play);
        stateManager.setStates(states);
        stateManager.update(10);

        Mockito.verify(play).update(10);
    }

    @Test
    void getStates() {
        MenuState menu = mock(MenuState.class);
        PlayState play = mock(PlayState.class);
        Stack<IState> states = new Stack<>();
        states.push(menu);
        states.push(play);

        stateManager.setStates(states);

        assertEquals(stateManager.getStates(), states);
    }

    @Test
    void setStates() {
        MenuState menu = mock(MenuState.class);
        PlayState play = mock(PlayState.class);
        Stack<IState> states = new Stack<>();
        states.push(menu);
        states.push(play);

        stateManager.setStates(states);
        assertEquals(stateManager.getStates().size(), states.size());
    }

    @Test
    void reStateTest() {
        PlayState playState = mock(PlayState.class);
        stateManager.pushState(playState);

        stateManager.reState();
        assertEquals(stateManager.getStates().size(), 2);
        assertEquals(stateManager.peekState(), playState);
    }

    @Test
    void popTest() {
        PlayState playState = mock(PlayState.class);
        MenuState menuState = mock(MenuState.class);
        stateManager.pushState(playState);
        stateManager.pushState(menuState);

        stateManager.popState();
        assertEquals(stateManager.peekState(), playState);
        assertEquals(stateManager.getStates().size(), 1);
    }

    @Test
    void renderTest() {
        //
    }
}