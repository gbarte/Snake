package states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Manages the states of the game.
 * Can change a state of the game.
 */
public class GameStateManager {
    private Stack<State> states;

    /**
     * Constructor creates a new stack for the states.
     */
    public GameStateManager() {
        states = new Stack<State>();
    }

    /**
     * Pushes a new state onto the stack.
     * @param state to be pushed on the stack.
     */
    public void push(State state) {
        states.push(state);
    }

    /**
     * Pops latest state on the stack.
     */
    public void pop() {
        states.pop();
    }

    /**
     * Sets new current state.
     * @param state to become the current state.
     */
    public void set(State state) {
        states.pop();
        states.push(state);
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

    public Stack<State> getStates() {
        return states;
    }

    public void setStates(Stack<State> states) {
        this.states = states;
    }
}
