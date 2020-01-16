package states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Manages the states of the game.
 * Can change a state of the game.
 */
public class GameStateManager {
    private Stack<IState> states;

    /**
     * Constructor creates a new stack for the states.
     */
    public GameStateManager() {
        states = new Stack<>();
    }

    /**
     * Pushes a new state onto the stack.
     * @param state to be pushed on the stack.
     */
    public void pushState(IState state) {
        states.push(state);
    }

    /**
     * Pops latest state on the stack.
     */
    public void popState() {
        states.pop();
    }

    /**
     * Sets new current state.
     * @param state to become the current state.
     */
    public void setState(IState state) {
        states.pop();
        states.push(state);
    }

    /**
     * Peeks for the current state.
     * @return state that's on top.
     */
    public IState peekState() {
        return states.peek();
    }

    /**
     * Pushes the current state on top again by peeking for it.
     */
    public void reState() {
        states.push(states.peek());
    }

    /**
     * Updates the state every dt.
     * @param dt updates every dt
     */
    public void update(float dt) {
        states.peek().update(dt);
    }

    /**
     * Renders the state.
     * @param batch to be rendered.
     */
    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }

    public Stack<IState> getStates() {
        return states;
    }

    public void setStates(Stack<IState> states) {
        this.states = states;
    }
}
